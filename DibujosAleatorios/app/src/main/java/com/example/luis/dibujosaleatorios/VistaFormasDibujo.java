package com.example.luis.dibujosaleatorios;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;


public class VistaFormasDibujo extends View {

    private List<ShapeDrawable> shapes = new ArrayList<ShapeDrawable>();

    private Integer[] mColors = { Color.BLACK, Color.BLUE, Color.GREEN, Color.RED };

    public VistaFormasDibujo(Context context) {
        super(context);
    }


    public VistaFormasDibujo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(ShapeDrawable shape: shapes) {
            shape.draw(canvas);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int)event.getX();
            int y = (int)event.getY();
            if (!isDeletingExistingShape(x, y)) {
                shapes.add(makeShapeDrawable(x, y));
            }
            invalidate();
            return (true);
        } else {
            return (false);
        }
    }


    private boolean isDeletingExistingShape(int x, int y) {
        for(ShapeDrawable shape: shapes) {
            Rect bounds = shape.getBounds();
            if (bounds.contains(x, y)) {
                shapes.remove(shape);
                return(true);
            }
        }
        return(false);
    }


    private ShapeDrawable makeShapeDrawable(int x, int y) {

        int maxWidth = getWidth()/10;
        int maxHeight = getHeight()/10;
        Shape shape;

        if (Math.random() < 0.5) {
            shape = new OvalShape();
        }
        else {
            shape = new RectShape();
        }

        ShapeDrawable shapeD = new ShapeDrawable(shape);
        int width = UtilidadesRandom.randomInt(maxWidth)+5;
        int height = UtilidadesRandom.randomInt(maxHeight)+5;
        shapeD.setBounds(x-width/2, y-height/2, x+width/2, y+height/2);
        shapeD.getPaint().setColor(UtilidadesRandom.randomElement(mColors));

        return(shapeD);
    }


}