package com.meevii.customview.hencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.meevii.customview.Utils;

public class DashBoard extends View {

    private static final float RADIUS = Utils.dp2px(150);

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF = new RectF();
    private Path  path  = new Path();

    private PathEffect pathEffect;


    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(4));
        path.addRect(0, 0, Utils.dp2px(4), Utils.dp2px(15), Path.Direction.CW);
        pathEffect = new PathDashPathEffect(path, Utils.dp2px(20), 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(w / 2 - RADIUS, h / 2 - RADIUS, w / 2 + RADIUS, h / 2 + RADIUS);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectF, 120, 300, false, paint);
        paint.setPathEffect(pathEffect);
        canvas.drawArc(rectF, 120, 300, false, paint);
        paint.setPathEffect(null);
    }
}
