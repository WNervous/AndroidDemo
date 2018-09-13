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
    public static final String TAG = "ProgressView";
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
    /**
     * 进度条宽度
     */
    private float mProgressWidth;

    private int mHeight;
    private int mWidth;

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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                mHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "height:AT_MOST");
                mHeight = 400;
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "height:UNSPECIFIED");
                break;
        }
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                mWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "width:AT_MOST");
                mWidth = 400;
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "width:UNSPECIFIED");
                break;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.MAGENTA);
        Log.d(TAG, "width:" + mWidth + "__height:" + mHeight);
        rectF.set(mProgressWidth / 2, mProgressWidth / 2, mWidth - mProgressWidth / 2, mWidth - mProgressWidth / 2);
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
