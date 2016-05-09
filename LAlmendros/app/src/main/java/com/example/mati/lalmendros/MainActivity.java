package com.example.mati.lalmendros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    CheckBox chkBoxGrande;
    CheckBox chkBoxIngred;
    CheckBox chkBoxQueso;
    String pizzaElegida;

    double valorPizza = 0;
    double resultado = 0;
    double cantidad = 0;
    double suplemento = 0;
    boolean transporte = false;
    TextView resultadoTXV;
    Spinner miSpinner;
    final static String pizza[] = {"MARGARITA", "TRES QUESOS", "BARBACOA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miSpinner = (Spinner) findViewById(R.id.spinnerPizza);
        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pizza);

        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); /* MIRAR BIEN( es diferente al ListaView) */
        miSpinner.setAdapter(miAdaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //Accion de selecion de las listas
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                if (pizza[position] == "MARGARITA") {
                    valorPizza = 12;
                    pizzaElegida = "MARGARITA";
                } else if (pizza[position] == "TRES QUESOS") {
                    valorPizza = 15;
                    pizzaElegida = "TRES QUESOS";
                } else if (pizza[position] == "BARBACOA") {
                    valorPizza = 18;
                    pizzaElegida = "BARBACOA";
                } else {
                    valorPizza = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Cheks boxs
        chkBoxGrande = (CheckBox) findViewById(R.id.chkBoxGrande);
        chkBoxIngred = (CheckBox) findViewById(R.id.chkBoxIngred);
        chkBoxQueso = (CheckBox) findViewById(R.id.chkBoxQueso);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.gruporb);
        rg.clearCheck();




        resultadoTXV = (TextView) findViewById(R.id.txtResultado);
        final Button botonTotal = (Button) findViewById(R.id.btnTotal);
        final EditText textoNumero = (EditText) findViewById(R.id.EditTxtNumero);

        //Borrar el texto inicial del EditText
        textoNumero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textoNumero.setText("");
            }
        });

        botonTotal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                //Comprueba si es adomicilio
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(group.getCheckedRadioButtonId()==R.id.radioLocal){
                            transporte = false;
                        }else if (group.getCheckedRadioButtonId()==R.id.radioDomicilio){
                            transporte = true;
                        } else {
                            transporte = false;
                        }

                    }
                });

                //Comprobacionde ingredientes extra
                if(chkBoxGrande.isChecked())
                {
                    suplemento += 1;
                }
                if(chkBoxIngred.isChecked())
                {
                    suplemento += 1;
                }
                if(chkBoxQueso.isChecked())
                {
                    suplemento += 1;
                }

                //Cantidad de pizzas
                try {
                    cantidad = Integer.parseInt(textoNumero.getText() + "");
                } catch (Exception e) {

                }
                //Calculo del valor
                if (transporte == true){
                    resultado = (valorPizza+suplemento)*cantidad;
                    resultado += resultado*0.10;
                } else {
                    resultado = (valorPizza+suplemento)*cantidad;
                }
                //Pintado del resultado
                resultadoTXV.setText(resultado+"€");

                //cambio de ventana
                Intent miIntent = new Intent(MainActivity.this, PantallaFactura.class);
                Bundle miBundle = new Bundle();

                String[] infoPaso ={pizzaElegida, valorPizza + "", suplemento + "", cantidad + "", transporte + "", resultado + ""};

                miBundle.putStringArray("TEXTO",infoPaso);
                miIntent.putExtras(miBundle);
                startActivityForResult(miIntent, 0);

                suplemento = 0;
            }

        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
