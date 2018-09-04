package com.meevii.customview.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomPoint extends View {

    private Paint paint = new Paint();

    public CustomPoint(Context context) {
        super(context);
        init();
    }

    public CustomPoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomPoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(50);
        paint.setStrokeCap(Paint.Cap.BUTT);//
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(50, 50, paint);
    }
}
