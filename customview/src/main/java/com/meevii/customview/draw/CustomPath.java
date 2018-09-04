package com.meevii.customview.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomPath extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    public CustomPath(Context context) {
        super(context);
        init();
    }

    public CustomPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);//
        path.setFillType(Path.FillType.WINDING);
        //////path
        //第一类： addXxx() ——添加子图形        Direction.CW :顺时针   CCW  ：逆时针
//        path.addCircle(300, 300, 200, Path.Direction.CW);
//        path.addCircle(400, 300, 200, Path.Direction.CW);
        //其他图形类似

//        第二组：xxxTo() ——画线（直线或曲线）
        path.lineTo(100,100);
        path.rLineTo(100,0);

        // 使用 path 对图形进行描述（这段描述代码不必看懂）
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}
