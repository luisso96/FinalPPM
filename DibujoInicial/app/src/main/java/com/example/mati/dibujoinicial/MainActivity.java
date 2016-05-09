package com.example.mati.dibujoinicial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiDibujo(this));
    }

    class MiDibujo extends View {
        public MiDibujo(Context c){

            super(c);
        }

        protected void onDraw(Canvas lienzo){


            Paint miPincel= new Paint();
            RectF miRec = new RectF(1,1,50,50);

            miPincel.setColor(Color.RED);
            miPincel.setStyle(Paint.Style.STROKE);
            miPincel.setStrokeWidth(8);
            for (int i=1; i<1000; i+=50)
                lienzo.drawCircle(500,500,50+i,miPincel);


            miPincel.setColor(Color.GREEN);
            lienzo.drawRect(1,1,50,50,miPincel);

            miPincel.setColor(Color.BLUE);
            miPincel.setStrokeWidth(5);
            miPincel.setTextSize(60);
            lienzo.drawText("MI CIRCULO",500,900,miPincel);

        }
    }
}
