package com.example.luis.panaderia.Otros;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;

public class VistaDibujo extends View {

    Paint paint  = new Paint();

    Path pathBarra = new Path();

    private ShapeDrawable circulo1, circulo2;


    public VistaDibujo(Context context, AttributeSet atributeSet) {
        super(context, atributeSet);

        circulo1 = new ShapeDrawable(new OvalShape());
        circulo1.getPaint().setColor(Color.rgb(150, 125, 70));
        circulo1.setBounds(550, 200, 650, 400);

        circulo2 = new ShapeDrawable(new OvalShape());
        circulo2.getPaint().setColor(Color.rgb(150, 125, 70));
        circulo2.setBounds(50, 500, 150, 700);

    }


    protected void onDraw(Canvas canvas) {

        circulo1.draw(canvas);
        circulo2.draw(canvas);


        paint.setStrokeWidth(2);
        paint.setColor(Color.rgb(150, 125, 70)); // marron
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(34f);

        Point bp1 = new Point(100,700);
        Point bp2 = new Point(100,500);
        Point bp3 = new Point(600,200);
        Point bp4 = new Point(600,400);

        pathBarra.setFillType(Path.FillType.EVEN_ODD);

        pathBarra.moveTo(bp1.x, bp1.y);

        pathBarra.lineTo(bp2.x, bp2.y);
        pathBarra.lineTo(bp3.x, bp3.y);
        pathBarra.lineTo(bp4.x, bp4.y);

        pathBarra.close();

        canvas.drawPath(pathBarra, paint);

    }
}