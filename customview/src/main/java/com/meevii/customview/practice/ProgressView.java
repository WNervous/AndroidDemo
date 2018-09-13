package com.meevii.customview.practice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.meevii.customview.R;

public class ProgressView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF = new RectF();
    /**
     * 进度progress
     */
    private int mProgress;
    /**
     * 进度条的颜色
     */
    private @ColorInt
    int progressColor;
    /**
     * 进度条的背景颜色
     */
    private @ColorInt
    int progressBgColor;

    private float mProgressWidth;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        progressBgColor = a.getColor(R.styleable.ProgressView_pv_progressBgColor, Color.GRAY);
        progressColor = a.getColor(R.styleable.ProgressView_pv_progressColor, Color.GREEN);
        mProgressWidth = a.getDimensionPixelSize(R.styleable.ProgressView_pv_progressWidth, 20);
        a.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.set(100, 100, 400, 400);
        paint.setColor(progressBgColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mProgressWidth);
        canvas.drawArc(rectF, 0, 360, false, paint);
        paint.setColor(progressColor);
        canvas.drawArc(rectF, 270, 360 * mProgress / 100, false, paint);
    }

    public int getCurrentProgress() {
        return mProgress;
    }

    public void setCurrentProgress(int mCurrentProgress) {
        this.mProgress = mCurrentProgress;
        invalidate();
    }

}
