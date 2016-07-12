package com.tek.landlord;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * QuickAction dialog, shows action list as icon and text like the one in Gallery3D app. Currently supports vertical and horizontal layout.
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 * 
 *         Contributors: - Kevin Peck <kevinwpeck@gmail.com>
 */
public class QuickAction extends PopupWindows implements OnDismissListener {
    private View mRootView;
    private ImageView mArrowUp;
    private ImageView mArrowDown;
    private LayoutInflater mInflater;
    private ViewGroup mTrack;
    private ScrollView mScroller;
    private OnActionItemClickListener mItemClickListener;
    private OnDismissListener mDismissListener;

    private List<ActionItem> actionItems = new ArrayList<ActionItem>();

    private boolean mDidAction;

    private int mChildPos;
    private int mInsertPos;
    private int mAnimStyle;
    private int mOrientation;
    private int rootWidth = 0;

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public static final int ANIM_GROW_FROM_LEFT = 1;
    public static final int ANIM_GROW_FROM_RIGHT = 2;
    public static final int ANIM_GROW_FROM_CENTER = 3;
    public static final int ANIM_REFLECT = 4;
    public static final int ANIM_AUTO = 5;

    /**
     * Constructor for default vertical layout
     * 
     * @param context
     *            Context
     */
    public QuickAction(Context context) {
        this(context, VERTICAL);
    }

    /**
     * Constructor allowing orientation override
     * 
     * @param context
     *            Context
     * @param orientation
     *            Layout orientation, can be vartical or horizontal
     */
    public QuickAction(Context context, int orientation) {
        super(context);

        this.mOrientation = orientation;

        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (this.mOrientation == HORIZONTAL) {
            setRootViewId(R.layout.popup_horizontal);
        } else {
            setRootViewId(R.layout.popup_vertical);
        }

        this.mAnimStyle = ANIM_AUTO;
        this.mChildPos = 0;
    }

    /**
     * Get action item at an index
     * 
     * @param index
     *            Index of item (position from callback)
     * 
     * @return Action Item at the position
     */
    public ActionItem getActionItem(int index) {
        return this.actionItems.get(index);
    }

    public void clearActionItems() {
    	actionItems.clear();
    	mTrack.removeAllViews();
    	mInsertPos = 0;
    	this.mChildPos =0;
    	
    }
    /**
     * Set root view.
     * 
     * @param id
     *            Layout resource id
     */
    public void setRootViewId(int id) {
        this.mRootView = this.mInflater.inflate(id, null);
        this.mTrack = (ViewGroup) this.mRootView.findViewById(R.id.tracks);

        this.mArrowDown = (ImageView) this.mRootView.findViewById(R.id.arrow_down);
        this.mArrowUp = (ImageView) this.mRootView.findViewById(R.id.arrow_up);

        this.mScroller = (ScrollView) this.mRootView.findViewById(R.id.scroller);

        // This was previously defined on show() method, moved here to prevent force close that occured
        // when tapping fastly on a view to show quickaction dialog.
        // Thanx to zammbi (github.com/zammbi)
        this.mRootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        setContentView(this.mRootView);
    }

    /**
     * Set animation style
     * 
     * @param mAnimStyle
     *            animation style, default is set to ANIM_AUTO
     */
    public void setAnimStyle(int mAnimStyle) {
        this.mAnimStyle = mAnimStyle;
    }

    /**
     * Set listener for action item clicked.
     * 
     * @param listener
     *            Listener
     */
    public void setOnActionItemClickListener(OnActionItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    /**
     * Add action item
     * 
     * @param action
     *            {@link ActionItem}
     */
    public void addActionItem(ActionItem action) {
        this.actionItems.add(action);

        String title = action.getTitle();
        Drawable icon = action.getIcon();

        View container;

        if (this.mOrientation == HORIZONTAL) {
            container = this.mInflater.inflate(R.layout.action_item_horizontal, null);
        } else {
            container = this.mInflater.inflate(R.layout.action_item_vertical, null);
        }

        ImageView img = (ImageView) container.findViewById(R.id.iv_icon);
        TextView text = (TextView) container.findViewById(R.id.tv_title);

        if (icon != null) {
            img.setImageDrawable(icon);
        } else {
            img.setVisibility(View.GONE);
        }

        if (title != null) {
            text.setText(title);
        } else {
            text.setVisibility(View.GONE);
        }

        final int pos = this.mChildPos;
        final int actionId = action.getActionId();

        container.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (QuickAction.this.mItemClickListener != null) {
                    QuickAction.this.mItemClickListener.onItemClick(QuickAction.this, pos, actionId);
                }

                if (!getActionItem(pos).isSticky()) {
                    QuickAction.this.mDidAction = true;

                    dismiss();
                }
            }
        });

        container.setFocusable(true);
        container.setClickable(true);

        if (this.mOrientation == HORIZONTAL && this.mChildPos != 0) {
            View separator = this.mInflater.inflate(R.layout.horiz_separator, null);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);

            separator.setLayoutParams(params);
            separator.setPadding(5, 0, 5, 0);

            this.mTrack.addView(separator, this.mInsertPos);

            this.mInsertPos++;
        }

        this.mTrack.addView(container, this.mInsertPos);

        this.mChildPos++;
        this.mInsertPos++;
    }

    /**
     * Show quickaction popup. Popup is automatically positioned, on top or bottom of anchor view.
     * 
     */
    public void show(View anchor) {
        preShow();

        int xPos, yPos, arrowPos;

        this.mDidAction = false;

        int[] location = new int[2];

        anchor.getLocationOnScreen(location);

        Rect anchorRect = new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[1] + anchor.getHeight());

        // mRootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        this.mRootView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        int rootHeight = this.mRootView.getMeasuredHeight();

        if (this.rootWidth == 0) {
            this.rootWidth = this.mRootView.getMeasuredWidth();
        }

        int screenWidth = this.mWindowManager.getDefaultDisplay().getWidth();
        int screenHeight = this.mWindowManager.getDefaultDisplay().getHeight();

        // automatically get X coord of popup (top left)
        if ((anchorRect.left + this.rootWidth) > screenWidth) {
            xPos = anchorRect.left - (this.rootWidth - anchor.getWidth());
            xPos = (xPos < 0) ? 0 : xPos;

            arrowPos = anchorRect.centerX() - xPos;

        } else {
            if (anchor.getWidth() > this.rootWidth) {
                xPos = anchorRect.centerX() - (this.rootWidth / 2);
            } else {
                xPos = anchorRect.left;
            }

            arrowPos = anchorRect.centerX() - xPos;
        }

        int dyTop = anchorRect.top;
        int dyBottom = screenHeight - anchorRect.bottom;

        boolean onTop = (dyTop > dyBottom) ? true : false;

        if (onTop) {
            if (rootHeight > dyTop) {
                yPos = 15;
                LayoutParams l = this.mScroller.getLayoutParams();
                l.height = dyTop - anchor.getHeight();
            } else {
                yPos = anchorRect.top - rootHeight;
            }
        } else {
            yPos = anchorRect.bottom;

            if (rootHeight > dyBottom) {
                LayoutParams l = this.mScroller.getLayoutParams();
                l.height = dyBottom;
            }
        }

        showArrow(((onTop) ? R.id.arrow_down : R.id.arrow_up), arrowPos);

        setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);

        this.mWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);
    }

    /**
     * Set animation style
     * 
     * @param screenWidth
     *            screen width
     * @param requestedX
     *            distance from left edge
     * @param onTop
     *            flag to indicate where the popup should be displayed. Set TRUE if displayed on top of anchor view and vice versa
     */
    private void setAnimationStyle(int screenWidth, int requestedX, boolean onTop) {
        int arrowPos = requestedX - this.mArrowUp.getMeasuredWidth() / 2;

        switch (this.mAnimStyle) {
        case ANIM_GROW_FROM_LEFT:
            this.mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
            break;

        case ANIM_GROW_FROM_RIGHT:
            this.mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
            break;

        case ANIM_GROW_FROM_CENTER:
            this.mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
            break;

        case ANIM_REFLECT:
            this.mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Reflect : R.style.Animations_PopDownMenu_Reflect);
            break;

        case ANIM_AUTO:
            if (arrowPos <= screenWidth / 4) {
                this.mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
            } else if (arrowPos > screenWidth / 4 && arrowPos < 3 * (screenWidth / 4)) {
                this.mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
            } else {
                this.mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
            }

            break;
        }
    }

    /**
     * Show arrow
     * 
     * @param whichArrow
     *            arrow type resource id
     * @param requestedX
     *            distance from left screen
     */
    private void showArrow(int whichArrow, int requestedX) {
        final View showArrow = (whichArrow == R.id.arrow_up) ? this.mArrowUp : this.mArrowDown;
        final View hideArrow = (whichArrow == R.id.arrow_up) ? this.mArrowDown : this.mArrowUp;

        final int arrowWidth = this.mArrowUp.getMeasuredWidth();

        showArrow.setVisibility(View.VISIBLE);

        ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams) showArrow.getLayoutParams();

        param.leftMargin = requestedX - arrowWidth / 2;

        hideArrow.setVisibility(View.INVISIBLE);
    }

    /**
     * Set listener for window dismissed. This listener will only be fired if the quicakction dialog is dismissed by clicking outside the dialog or clicking on sticky item.
     */
    public void setOnDismissListener(QuickAction.OnDismissListener listener) {
        setOnDismissListener(this);

        this.mDismissListener = listener;
    }

    @Override
    public void onDismiss() {
        if (!this.mDidAction && this.mDismissListener != null) {
            this.mDismissListener.onDismiss();
        }
    }

    /**
     * Listener for item click
     * 
     */
    public interface OnActionItemClickListener {
        public abstract void onItemClick(QuickAction source, int pos, int actionId);
    }

    /**
     * Listener for window dismiss
     * 
     */
    public interface OnDismissListener {
        public abstract void onDismiss();
    }
}