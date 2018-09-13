package com.meevii.customview.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PaoPaoView extends View {

    private Paint paint;
    private RectF rect;
    private Path path;
    private int width;
    private int height;

    public PaoPaoView(Context context) {
        super(context);
        init();
    }

    public PaoPaoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaoPaoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        switch (mode) {
            case MeasureSpec.AT_MOST:
                width = size;
                break;
            case MeasureSpec.EXACTLY:
                width = size;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = getSuggestedMinimumWidth();
                break;
        }
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        switch (modeHeight) {
            case MeasureSpec.AT_MOST:
                height = sizeHeight;
                break;
            case MeasureSpec.EXACTLY:
                height = sizeHeight;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = getSuggestedMinimumHeight();
                break;
        }
        rect.set(0, 0, width, height);
        setMeasuredDimension(width, height+30);// +30 是小三角形是的高度
    }


    public void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rect = new RectF();
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("PAO", "rect" + rect.toString());
        Log.d("PAO", "width:" + width + "_height:" + height);
        //画圆角矩形
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rect, 10, 10, paint);
        //画三角形
        path.moveTo(width / 2 - 30, height);
        path.lineTo(width / 2 + 30, height);
        path.lineTo(width / 2, height + 30);
        path.close();
        canvas.drawPath(path, paint);
    }
}
