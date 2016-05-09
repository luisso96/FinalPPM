package com.example.mati.solobici;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Grafico {
    private Drawable drawable; // Imagen que dibujaremos
    private double posX;
    private double posY; // Posición en la pantalla
    private double incX;
    private double incY; // Velocidad de desplazamiento
    private int angulo;
    private int rotacion;// Ángulo y velocidad rotación
    private int ancho;
    private int alto; // Dimensiones de la imagen
    private int radioColision; // Determinar si chocamos
    // Vista donde dibujamos el gráfico
    private View view;
    // Para determinar el espacio a borrar
    public static final int MAX_VELOCIDAD = 20;

    //Inicializamos los atributos de esta clase
    public Grafico(View view, Drawable drawable) {
        this.view = view;
        this.drawable = drawable;
        setAncho(drawable.getIntrinsicWidth());
        setAlto(drawable.getIntrinsicHeight());
        setRadioColision((getAlto() + getAncho()) / 4);
    }

    //Dibujamos el gráfico en su posición actual
    public void dibujaGrafico(Canvas canvas) {
        canvas.save();
        int x = (int) (getPosX() + getAncho() / 2);
        int y = (int) (getPosY() + getAlto() / 2);
        canvas.rotate((float) getAngulo(), (float) x, (float) y);
        drawable.setBounds((int) getPosX(), (int) getPosY(), (int) getPosX() + getAncho(),
                (int) getPosY() + getAlto());
        drawable.draw(canvas);
        canvas.restore();
        //Calculo área donde no podrán solaparse/chocar
        //otros gráficos con este
        int rInval = (int) distanciaE(0, 0, getAncho(), getAlto()) / 2 + MAX_VELOCIDAD;
        view.invalidate(x - rInval, y - rInval, x + rInval, y + rInval);
    };

    //Correccion posición si el gráfico sale de la pantalla
    //En estos casos aparece por el otro lado de la pantalla
    public void incrementaPos() {
        setPosX(getPosX() + getIncX());
        // Si salimos de la pantalla, corregimos posición
        if (getPosX() < -getAncho() / 2) {
            setPosX(view.getWidth() - getAncho() / 2);
        }
        if (getPosX() > view.getWidth() - getAncho() / 2) {
            setPosX(-getAncho() / 2);
        }
        setPosY(getPosY() + getIncY());
        // Si salimos de la pantalla, corregimos posición
        if (getPosY() < -getAlto() / 2) {
            setPosY(view.getHeight() - getAlto() / 2);
        }
        if (getPosY() > view.getHeight() - getAlto() / 2) {
            setPosY(-getAlto() / 2);
        }
        setAngulo(getAngulo() + getRotacion()); // Actualizamos ángulo
    }

    //Nos devuelve la distancia entre dos objetos Grafico
    public double distancia(Grafico g) {
        return distanciaE(getPosX(), getPosY(), g.getPosX(), g.getPosY());
    }

    //Nos devuelve si se produce o no colisión
    public boolean verificaColision(Grafico g) {
        return (distancia(g) < (getRadioColision() + g.getRadioColision()));
    }

    public static double distanciaE(double x, double y, double x2, double y2) {
        return Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public int getRotacion() {
        return rotacion;
    }

    public void setRotacion(int rotacion) {
        this.rotacion = rotacion;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getRadioColision() {
        return radioColision;
    }

    public void setRadioColision(int radioColision) {
        this.radioColision = radioColision;
    }
}