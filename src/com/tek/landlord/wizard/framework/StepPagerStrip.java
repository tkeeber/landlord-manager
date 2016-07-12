/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tek.landlord.wizard.framework;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import com.tek.landlord.R;

public class StepPagerStrip extends View {
    private static final int[] ATTRS = new int[] {
        android.R.attr.gravity
    };
    private int mPageCount;
    private int mCurrentPage;

    private int mGravity = Gravity.LEFT | Gravity.TOP;
    private float mTabWidth;
    private float mTabHeight;
    private float mTabSpacing;

    private Paint mPrevTabPaint;
    private Paint mSelectedTabPaint;
    private Paint mSelectedLastTabPaint;
    private Paint mNextTabPaint;

    private RectF mTempRectF = new RectF();

    // private Scroller mScroller;

    private OnPageSelectedListener mOnPageSelectedListener;

    public StepPagerStrip(Context context) {
        this(context, null, 0);
    }

    public StepPagerStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepPagerStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);
        this.mGravity = a.getInteger(0, this.mGravity);
        a.recycle();

        final Resources res = getResources();
        this.mTabWidth = res.getDimensionPixelSize(R.dimen.step_pager_tab_width);
        this.mTabHeight = res.getDimensionPixelSize(R.dimen.step_pager_tab_height);
        this.mTabSpacing = res.getDimensionPixelSize(R.dimen.step_pager_tab_spacing);

        this.mPrevTabPaint = new Paint();
        this.mPrevTabPaint.setColor(res.getColor(R.color.step_pager_previous_tab_color));

        this.mSelectedTabPaint = new Paint();
        this.mSelectedTabPaint.setColor(res.getColor(R.color.step_pager_selected_tab_color));

        this.mSelectedLastTabPaint = new Paint();
        this.mSelectedLastTabPaint.setColor(res.getColor(R.color.step_pager_selected_last_tab_color));

        this.mNextTabPaint = new Paint();
        this.mNextTabPaint.setColor(res.getColor(R.color.step_pager_next_tab_color));
    }

    public void setOnPageSelectedListener(OnPageSelectedListener onPageSelectedListener) {
        this.mOnPageSelectedListener = onPageSelectedListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (this.mPageCount == 0) {
            return;
        }

        float totalWidth = this.mPageCount * (this.mTabWidth + this.mTabSpacing) - this.mTabSpacing;
        float totalLeft;
        boolean fillHorizontal = false;

        switch (this.mGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
        case Gravity.CENTER_HORIZONTAL:
            totalLeft = (getWidth() - totalWidth) / 2;
            break;
        case Gravity.RIGHT:
            totalLeft = getWidth() - getPaddingRight() - totalWidth;
            break;
        case Gravity.FILL_HORIZONTAL:
            totalLeft = getPaddingLeft();
            fillHorizontal = true;
            break;
        default:
            totalLeft = getPaddingLeft();
        }

        switch (this.mGravity & Gravity.VERTICAL_GRAVITY_MASK) {
        case Gravity.CENTER_VERTICAL:
            this.mTempRectF.top = (int) (getHeight() - this.mTabHeight) / 2;
            break;
        case Gravity.BOTTOM:
            this.mTempRectF.top = getHeight() - getPaddingBottom() - this.mTabHeight;
            break;
        default:
            this.mTempRectF.top = getPaddingTop();
        }

        this.mTempRectF.bottom = this.mTempRectF.top + this.mTabHeight;

        float tabWidth = this.mTabWidth;
        if (fillHorizontal) {
            tabWidth = (getWidth() - getPaddingRight() - getPaddingLeft() - (this.mPageCount - 1) * this.mTabSpacing) / this.mPageCount;
        }

        for (int i = 0; i < this.mPageCount; i++) {
            this.mTempRectF.left = totalLeft + (i * (tabWidth + this.mTabSpacing));
            this.mTempRectF.right = this.mTempRectF.left + tabWidth;
            canvas.drawRect(this.mTempRectF, i < this.mCurrentPage ? this.mPrevTabPaint : (i > this.mCurrentPage ? this.mNextTabPaint
                    : (i == this.mPageCount - 1 ? this.mSelectedLastTabPaint : this.mSelectedTabPaint)));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(View.resolveSize((int) (this.mPageCount * (this.mTabWidth + this.mTabSpacing) - this.mTabSpacing) + getPaddingLeft()
                + getPaddingRight(), widthMeasureSpec), View.resolveSize((int) this.mTabHeight + getPaddingTop() + getPaddingBottom(), heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        scrollCurrentPageIntoView();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mOnPageSelectedListener != null) {
            switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int position = hitTest(event.getX());
                if (position >= 0) {
                    this.mOnPageSelectedListener.onPageStripSelected(position);
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    private int hitTest(float x) {
        if (this.mPageCount == 0) {
            return -1;
        }

        float totalWidth = this.mPageCount * (this.mTabWidth + this.mTabSpacing) - this.mTabSpacing;
        float totalLeft;
        boolean fillHorizontal = false;

        switch (this.mGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
        case Gravity.CENTER_HORIZONTAL:
            totalLeft = (getWidth() - totalWidth) / 2;
            break;
        case Gravity.RIGHT:
            totalLeft = getWidth() - getPaddingRight() - totalWidth;
            break;
        case Gravity.FILL_HORIZONTAL:
            totalLeft = getPaddingLeft();
            fillHorizontal = true;
            break;
        default:
            totalLeft = getPaddingLeft();
        }

        float tabWidth = this.mTabWidth;
        if (fillHorizontal) {
            tabWidth = (getWidth() - getPaddingRight() - getPaddingLeft() - (this.mPageCount - 1) * this.mTabSpacing) / this.mPageCount;
        }

        float totalRight = totalLeft + (this.mPageCount * (tabWidth + this.mTabSpacing));
        if (x >= totalLeft && x <= totalRight && totalRight > totalLeft) {
            return (int) (((x - totalLeft) / (totalRight - totalLeft)) * this.mPageCount);
        } else {
            return -1;
        }
    }

    public void setCurrentPage(int currentPage) {
        this.mCurrentPage = currentPage;
        invalidate();
        scrollCurrentPageIntoView();

        // TODO: Set content description appropriately
    }

    private void scrollCurrentPageIntoView() {
        // TODO: only works with left gravity for now
        //
        // float widthToActive = getPaddingLeft() + (mCurrentPage + 1) * (mTabWidth + mTabSpacing)
        // - mTabSpacing;
        // int viewWidth = getWidth();
        //
        // int startScrollX = getScrollX();
        // int destScrollX = (widthToActive > viewWidth) ? (int) (widthToActive - viewWidth) : 0;
        //
        // if (mScroller == null) {
        // mScroller = new Scroller(getContext());
        // }
        //
        // mScroller.abortAnimation();
        // mScroller.startScroll(startScrollX, 0, destScrollX - startScrollX, 0);
        // postInvalidate();
    }

    public void setPageCount(int count) {
        this.mPageCount = count;
        invalidate();

        // TODO: Set content description appropriately
    }

    public static interface OnPageSelectedListener {
        void onPageStripSelected(int position);
    }

    //
    // @Override
    // public void computeScroll() {
    // super.computeScroll();
    // if (mScroller.computeScrollOffset()) {
    // setScrollX(mScroller.getCurrX());
    // }
    // }
}
