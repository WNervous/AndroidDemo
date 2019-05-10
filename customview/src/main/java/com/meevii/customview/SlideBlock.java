package com.meevii.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

public class SlideBlock extends View {

    private Paint paint      = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint slidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF rect      = new RectF();
    private RectF slideRect = new RectF();

    private @ColorInt
    int slideColor;
    private @ColorInt
    int blockColor;

    private int blockWidth;


    private int max;
    private int progress;


    public SlideBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSlideBlock();
        handleAttributeSet(context, attrs);
        init();
    }

    private void initSlideBlock() {
        max = 100;
        progress = 0;
    }

    private void handleAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlideBlock);
        slideColor = typedArray.getColor(R.styleable.SlideBlock_slide_color, Color.BLACK);
        blockColor = typedArray.getColor(R.styleable.SlideBlock_block_color, Color.RED);
        typedArray.recycle();
    }

    private void init() {
        paint.setColor(slideColor);
        slidePaint.setColor(blockColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect.set(0, 0, w, h);
        if (blockWidth > w || blockWidth == 0) {
            blockWidth = w / 5;
        }
        slideRect.set(0, 0, blockWidth, h);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制外部边框
        canvas.drawRoundRect(rect, getHeight() / 2f, getHeight() / 2f, paint);
        //绘制内部滑动
        canvas.drawRoundRect(slideRect, getHeight() / 2f, getHeight() / 2f, slidePaint);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        int i = getWidth() - blockWidth;
        float i1 = (i * 1f) / max;
        float v = i1 * progress;
        slideRect.set(v, 0, v + blockWidth, getHeight());
        invalidate();
    }


}
