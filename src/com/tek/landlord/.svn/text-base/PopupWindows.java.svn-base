package com.tek.landlord;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Custom popup window.
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 * 
 */
public class PopupWindows {
    protected Context mContext;
    protected PopupWindow mWindow;
    protected View mRootView;
    protected Drawable mBackground = null;
    protected WindowManager mWindowManager;

    /**
     * Constructor.
     * 
     * @param context
     *            Context
     */
    public PopupWindows(Context context) {
        this.mContext = context;
        this.mWindow = new PopupWindow(context);

        this.mWindow.setTouchInterceptor(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    PopupWindows.this.mWindow.dismiss();

                    return true;
                }

                return false;
            }
        });

        this.mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * On dismiss
     */
    protected void onDismiss() {
    }

    /**
     * On show
     */
    protected void onShow() {
    }

    /**
     * On pre show
     */
    protected void preShow() {
        if (this.mRootView == null) {
            throw new IllegalStateException("setContentView was not called with a view to display.");
        }

        onShow();

        if (this.mBackground == null) {
            this.mWindow.setBackgroundDrawable(new BitmapDrawable());
        } else {
            this.mWindow.setBackgroundDrawable(this.mBackground);
        }

        this.mWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        this.mWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.mWindow.setTouchable(true);
        this.mWindow.setFocusable(true);
        this.mWindow.setOutsideTouchable(true);

        this.mWindow.setContentView(this.mRootView);
    }

    /**
     * Set background drawable.
     * 
     * @param background
     *            Background drawable
     */
    public void setBackgroundDrawable(Drawable background) {
        this.mBackground = background;
    }

    /**
     * Set content view.
     * 
     * @param root
     *            Root view
     */
    public void setContentView(View root) {
        this.mRootView = root;

        this.mWindow.setContentView(root);
    }

    /**
     * Set content view.
     * 
     * @param layoutResID
     *            Resource id
     */
    public void setContentView(int layoutResID) {
        LayoutInflater inflator = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        setContentView(inflator.inflate(layoutResID, null));
    }

    /**
     * Set listener on window dismissed.
     * 
     * @param listener
     */
    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        this.mWindow.setOnDismissListener(listener);
    }

    /**
     * Dismiss the popup window.
     */
    public void dismiss() {
        this.mWindow.dismiss();
    }
}