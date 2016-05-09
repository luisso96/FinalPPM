package com.example.luis.dibujosaleatorios;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class VistaFormasAleatorias extends View {


    public VistaFormasAleatorias(Context context) { // constructor con context
        super(context);
    }

    public VistaFormasAleatorias(Context context, AttributeSet atributos) { // constructor con context y atributos
        super(context, atributos);
    }


    private Integer[] coloresFondo = { Color.CYAN, Color.GRAY, Color.LTGRAY,Color.MAGENTA,
                                       Color.YELLOW, Color.WHITE };


    private Paint[] mForegrounds = { crearPincel(Color.BLACK), crearPincel(Color.BLUE),
                                     crearPincel(Color.GREEN), crearPincel(Color.RED)};


    private Bitmap[] imagenes = {
        crearBitmap(R.drawable.angel),
        crearBitmap(R.drawable.cool),
        crearBitmap(R.drawable.llorando),
        crearBitmap(R.drawable.feliz),
        crearBitmap(R.drawable.enfadado)
    };

    private String cadena = "Luis";


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(UtilidadesRandom.randomElement(coloresFondo));

        int largo = getWidth();
        int ancho = getHeight();
        int mediaLargoForma = largo/5;

        for(int i=0; i<20; i++) {
            dibujarCirculoAleatorio    (canvas, largo, ancho, mediaLargoForma);
            dibujarRectanguloAleatorio (canvas, largo, ancho, mediaLargoForma);
            dibujarTextoAleatorio      (canvas, largo, ancho, mediaLargoForma);
            dibujarBitmapAleatorio     (canvas, largo, ancho);
        }
    }


    private Paint crearPincel(int color) {
        Paint pincel = new Paint();
        pincel.setColor(color);
        return(pincel);
    }


    private Bitmap crearBitmap(int bitmapId) {
        return(BitmapFactory.decodeResource(getResources(), bitmapId));
    }


    private void dibujarCirculoAleatorio(Canvas canvas, int largo, int ancho, int mediaLargoForma) {
        float x = UtilidadesRandom.randomFloat(largo);
        float y = UtilidadesRandom.randomFloat(ancho);
        float radius = UtilidadesRandom.randomFloat(mediaLargoForma / 2);
        Paint circleColor = UtilidadesRandom.randomElement(mForegrounds);
        canvas.drawCircle(x, y, radius, circleColor);
    }


    private void dibujarRectanguloAleatorio(Canvas canvas, int largo, int ancho, int mediaLargoForma) {
        float left = UtilidadesRandom.randomFloat(largo);
        float top = UtilidadesRandom.randomFloat(ancho);
        float width = UtilidadesRandom.randomFloat(mediaLargoForma);
        float right = left + width;
        float bottom = top + width;
        Paint squareColor = UtilidadesRandom.randomElement(mForegrounds);
        canvas.drawRect(left, top, right, bottom, squareColor);
    }


    private void dibujarBitmapAleatorio(Canvas canvas, int largo, int ancho) {
        float left = UtilidadesRandom.randomFloat(largo);
        float top = UtilidadesRandom.randomFloat(ancho);
        Bitmap pic = UtilidadesRandom.randomElement(imagenes);
        canvas.drawBitmap(pic, left, top, null);
    }


    private void dibujarTextoAleatorio(Canvas canvas, int largo, int ancho, int mediaLargoForma) {
        float x = UtilidadesRandom.randomFloat(largo);
        float y = UtilidadesRandom.randomFloat(ancho);
        float textSize = UtilidadesRandom.randomFloat(mediaLargoForma);
        Paint textPaint = UtilidadesRandom.randomElement(mForegrounds);
        textPaint.setTextSize(textSize);
        canvas.drawText(cadena, x, y, textPaint);
    }

}