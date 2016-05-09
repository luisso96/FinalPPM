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


public class VistaFormasPulsar extends View {


    public VistaFormasPulsar(Context context) { // constructor con contexto
        super(context);
    }

    public VistaFormasPulsar(Context context, AttributeSet atributos) { // constructor con contexto y atributos
        super(context, atributos);
    }


    private List <ShapeDrawable> formas = new ArrayList<ShapeDrawable>();

    private Integer[] colores = { Color.BLACK, Color.BLUE, Color.GREEN, Color.RED };


    @Override
    protected void onDraw(Canvas canvas) { // dibujamos forma en arraylist
        super.onDraw(canvas);
        for(ShapeDrawable forma: formas) {
            forma.draw(canvas);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) { // evento al tocar pantalla
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int)event.getX();
            int y = (int)event.getY();
            if (!isDeletingExistingShape(x, y)) { // si en la posicion no existe ninguna forma añade
                formas.add(makeShapeDrawable(x, y));
            }
            invalidate();
            return (true);
        } else {
            return (false);
        }
    }


    private boolean isDeletingExistingShape(int x, int y) {
        for(ShapeDrawable forma: formas) {
            Rect limites = forma.getBounds();
            if (limites.contains(x, y)) { // si el la posicion ya existe la forma la eliminamos
                formas.remove(forma);
                return(true);
            }
        }
        return(false);
    }


    private ShapeDrawable makeShapeDrawable(int x, int y) {

        int maxLargo = getWidth()/10;
        int maxAncho = getHeight()/10;
        Shape figura;

        if (Math.random() < 0.5) { // sortea entre dibujar rectangulo y circulo
            figura = new OvalShape();
        }
        else {
            figura = new RectShape();
        }

        ShapeDrawable shapeD = new ShapeDrawable(figura);
        int largo = UtilidadesRandom.randomInt(maxLargo)+5;
        int ancho = UtilidadesRandom.randomInt(maxAncho)+5;
        shapeD.setBounds(x-largo/2, y-ancho/2, x+largo/2, y+ancho/2);
        shapeD.getPaint().setColor(UtilidadesRandom.randomElement(colores));

        return(shapeD);
    }


}