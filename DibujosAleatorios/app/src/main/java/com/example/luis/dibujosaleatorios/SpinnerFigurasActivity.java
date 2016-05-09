package com.example.luis.dibujosaleatorios;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SpinnerFigurasActivity extends AppCompatActivity {

    public Spinner miSpinner;

    // static porque los usamos en la clase VistaFormaSpinner para dibujar las figuras
    static EditText campo1, campo2;
    static String opcion = "";
    static double largo, ancho;
    static double lado;

    private ObjetosSpinner[] objetosSpinner = new ObjetosSpinner[]{
            new ObjetosSpinner("Selecciona figura", "para calcular", R.drawable.figdefecto),
            new ObjetosSpinner("Circulo", "PI * (Radio*2)", R.drawable.circ),
            new ObjetosSpinner("Cuadrado", "Lado * Lado", R.drawable.cuad),
            new ObjetosSpinner("Rectangulo", "Base * Anchura", R.drawable.rect)
    };


    // CLASE PARA EL SPINNER PERSONALIZADO
    class MiAdaptador extends ArrayAdapter<ObjetosSpinner> {

        Activity context;

        MiAdaptador(Activity context) {
            super(context, R.layout.vista_item_spinner, objetosSpinner);
            this.context = context;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            convertView = inflater.inflate(R.layout.vista_item_spinner, null);

            TextView zona = (TextView)convertView.findViewById(R.id.tvFigura);
            zona.setText(objetosSpinner[position].getFigura());

            TextView continente =(TextView)convertView.findViewById(R.id.tvFormula);
            continente.setText(objetosSpinner[position].getFormula());

            ImageView imagen = (ImageView)convertView.findViewById(R.id.ivImagen);
            imagen.setImageResource(objetosSpinner[position].getImagen());

            return convertView;
        }
    }


    public static int obtenerRadio(){
        double radio = Double.parseDouble(campo1.getText().toString());
        return ((int) radio); // .cast hace casting para pasar double a int
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_figuras);

        final Button calcular = (Button)findViewById(R.id.btCalcular);
        final TextView resultado = (TextView)findViewById(R.id.tvResultado);
        final View figura = (View)findViewById(R.id.viVista);
        campo1 = (EditText)findViewById(R.id.etCampo1);
        campo2 = (EditText)findViewById(R.id.etCampo2);

        MiAdaptador adaptador = new MiAdaptador(this);
        miSpinner = (Spinner)findViewById(R.id.sp1);
        miSpinner.setAdapter(adaptador);


        // EVENTO DEL CLICK DEL BOTON CALCULAR
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                switch (opcion) {

                    //if(campo1.getText().toString().equals("") || campo1.getText().toString() == null)

                    case "circulo":
                        figura.setVisibility(View.VISIBLE);
                        double radio = Double.parseDouble(campo1.getText().toString());
                        final double areaCirculo = (radio * radio) * Math.PI;
                        resultado.setText("Área del circulo: " + decimalFormat.format(areaCirculo));
                        break;
                    case "cuadrado":
                        figura.setVisibility(View.VISIBLE);
                        lado = Double.parseDouble(campo1.getText().toString());
                        final double areaCuadrado = (lado * lado);
                        resultado.setText("Área del cuadrado: " + decimalFormat.format(areaCuadrado));
                        break;
                    case "rectangulo":
                        figura.setVisibility(View.VISIBLE);
                        largo = Double.parseDouble(campo1.getText().toString());
                        ancho = Double.parseDouble(campo2.getText().toString());
                        final double areaRectangulo = (largo * ancho);
                        resultado.setText("Área del rectangulo: " + decimalFormat.format(areaRectangulo));
                        break;
                }

            }
        });


        // EVENTO DE CLICK SOBRE CADA ITEM DEL SPINNER
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int i, long id) {

                switch (i) {
                    case 1:
                        opcion = "circulo";
                        campo1.setVisibility(EditText.VISIBLE);
                        campo2.setVisibility(EditText.INVISIBLE);
                        calcular.setVisibility(Button.VISIBLE);
                        resultado.setVisibility(TextView.VISIBLE);
                        campo1.setHint("Introduce radio:");
                        break;
                    case 2:
                        opcion = "cuadrado";
                        campo1.setVisibility(EditText.VISIBLE);
                        campo2.setVisibility(EditText.INVISIBLE);
                        calcular.setVisibility(Button.VISIBLE);
                        resultado.setVisibility(TextView.VISIBLE);
                        campo1.setHint("Introduce lado:");
                        break;
                    case 3:
                        opcion = "rectangulo";
                        campo1.setVisibility(EditText.VISIBLE);
                        campo2.setVisibility(EditText.VISIBLE);
                        calcular.setVisibility(Button.VISIBLE);
                        resultado.setVisibility(TextView.VISIBLE);
                        campo1.setHint("introduce Largo:");
                        campo2.setHint("introduce Ancho:");
                        break;
                    default:
                        figura.setVisibility(View.INVISIBLE);
                        campo1.setVisibility(EditText.INVISIBLE);
                        campo2.setVisibility(EditText.INVISIBLE);
                        calcular.setVisibility(Button.INVISIBLE);
                        resultado.setVisibility(TextView.INVISIBLE);
                        break;
                }

            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

    }
}
