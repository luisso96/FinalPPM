package com.example.luis.dibujosaleatorios;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class VistaFormaSpinner extends View {

    // CONSTRUCTOR CON CONTEXT
    public VistaFormaSpinner(Context context) {
        super(context);
    }

    // CONSTRUCTOR CON CONTEXT Y ATRIBUTOS
    public VistaFormaSpinner(Context context, AttributeSet atributos) {
        super(context, atributos);
    }

    Paint pincel = new Paint();


    private void dibujarCirculo(Canvas canvas, int largo, int ancho, int radio) {
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(largo, ancho, radio, pincel);
    }

    private void dibujarRectangulo(Canvas canvas, int x, int y, int largo, int ancho) {
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, largo, ancho, pincel);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int wid = this.getWidth();
        int hei = this.getHeight();
        int radio = SpinnerFigurasActivity.obtenerRadio();

        switch(SpinnerFigurasActivity.opcion) {
            case "circulo":
                dibujarCirculo(canvas, wid / 2, hei / 2, radio);
                break;
            case "rectangulo":
                dibujarRectangulo(canvas, 20, 20, ((int) SpinnerFigurasActivity.largo), ((int) SpinnerFigurasActivity.ancho));
                break;
            case "cuadrado":
                dibujarRectangulo(canvas, 20, 20, ((int) SpinnerFigurasActivity.lado), ((int) SpinnerFigurasActivity.lado));
                break;
            default:
                break;
        }
    }

}
