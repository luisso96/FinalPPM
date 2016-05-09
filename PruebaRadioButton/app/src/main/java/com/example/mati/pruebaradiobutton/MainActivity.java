package com.example.mati.pruebaradiobutton;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView lblMensaje = (TextView) findViewById(R.id.LblSeleccion);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.gruporb);
        rg.clearCheck();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group.getCheckedRadioButtonId()==R.id.radio1){
                    lblMensaje.setText("Opcion seleccionada: 1");
                }else if (group.getCheckedRadioButtonId()==R.id.radio2){
                    lblMensaje.setText("Opcion seleccionada: 2");
                } else {
                    lblMensaje.setText("No toques hay, profavor");
                }

            }
        });

    }

}


