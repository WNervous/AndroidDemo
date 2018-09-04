package com.meevii.customview.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomOval extends View {
    private Paint paint = new Paint();

    public CustomOval(Context context) {
        super(context);
        init();
    }

    public CustomOval(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomOval(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(100f, 600f, 400f,800f, paint);
    }
}
