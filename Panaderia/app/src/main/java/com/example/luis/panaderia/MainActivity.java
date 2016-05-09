package com.example.luis.panaderia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.panaderia.Objetos.Pan;
import com.example.luis.panaderia.Otros.AjustesActivity;
import com.example.luis.panaderia.Otros.DibujoActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ViewHolder holder;
    LinearLayout pantalla;
    Spinner spinner;
    RadioGroup radioGroup;
    CheckBox integral, sinGluten, tierno;
    EditText unidades;
    Button pedir;

    String usuarioLogueado;
    String cadpan;
    String cadTamano = "normal";
    String complemento ="";
    int cantidad, imagenPan, idRecibido;
    double preciopan, precioTamano, precioComplemento, precioTotal;

    private Pan[] pan = new Pan[]{
            new Pan("Barra" , "1" , R.drawable.barra),
            new Pan("Rollo"     , "1.5" , R.drawable.rollo),
            new Pan("Quemado"    , "2" , R.drawable.quemado),
            new Pan("Bollo"   , "0.5"   ,  R.drawable.bollo)
    };

    class Adaptador extends ArrayAdapter<Pan> {

        Activity context;
        Adaptador(Activity context) {
            super(context, R.layout.vista_item_spinner, pan);
            this.context = context;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            if(item==null){
                LayoutInflater inflater = getLayoutInflater();
                item = inflater.inflate(R.layout.vista_item_spinner, parent, false);

                holder = new ViewHolder();

                holder.pan = (TextView)item.findViewById(R.id.tvPan);
                holder.precio = (TextView)item.findViewById(R.id.tvPrecio);
                holder.imagen = (ImageView)item.findViewById(R.id.ivImagen);

                item.setTag(holder);
            }
            else
                holder = (ViewHolder)item.getTag();

            holder.pan.setText(pan[position].getPan());
            holder.precio.setText(pan[position].getPrecio());
            holder.imagen.setImageResource(pan[position].getImagen());

            cadpan = pan[position].getPan();
            preciopan = Double.parseDouble(pan[position].getPrecio());
            imagenPan = pan[position].getImagen();

            return (item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Realizar pedido");

        Bundle bundle = getIntent().getExtras();
        idRecibido = bundle.getInt("IDUSUARIO");
        usuarioLogueado = bundle.getString("NOMUSUARIO");

        pantalla = (LinearLayout)findViewById(R.id.llPrincipal);
        integral = (CheckBox)findViewById(R.id.cbPapas);
        sinGluten = (CheckBox)findViewById(R.id.cbAceitunas);
        tierno = (CheckBox)findViewById(R.id.cbCacahuetes);
        unidades = (EditText)findViewById(R.id.etUnidades);

        Adaptador adaptador = new Adaptador(this);
        spinner = (Spinner)findViewById(R.id.sp1);
        spinner.setAdapter(adaptador);

        radioGroup = (RadioGroup) findViewById(R.id.grupoRadio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.rb1 == radioGroup.getCheckedRadioButtonId()) {
                    cadTamano = "vaso normal";
                    precioTamano = 0;
                } else {
                    cadTamano = "vaso grande";
                    precioTamano = preciopan * 0.3;
                }
            }
        });


        pedir = (Button)findViewById(R.id.btPedir);
        pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (unidades.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Te falta introducir unidades!", Toast.LENGTH_SHORT).show();
                } else {

                    if (integral.isChecked()) {
                        complemento += " integral";
                        precioComplemento += 1;
                    }
                    if (sinGluten.isChecked()) {
                        complemento += " sin gluten";
                        precioComplemento += 1;
                    }
                    if (tierno.isChecked()) {
                        complemento += " tierno";
                        precioComplemento += 1;
                    }

                    cantidad = Integer.parseInt(unidades.getText().toString());
                    precioTotal = (preciopan + precioTamano + precioComplemento) * cantidad;

                    Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("PAN", cadpan);
                    bundle.putString("TAMANO", cadTamano);
                    bundle.putString("COMPLEMENTO", complemento);
                    bundle.putDouble("PRECIO", precioTotal);
                    bundle.putInt("IDUSUARIO", idRecibido);
                    bundle.putInt("UNIDADES", cantidad);
                    bundle.putInt("IMAGEN", imagenPan);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    protected void onResume(){
        super.onResume();
        final Context context = this;

        precioComplemento = 0;
        complemento = "";

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean tema    = sharedPreferences.getBoolean("opcTema", false);

        if(tema)
            pantalla.setBackgroundColor(ContextCompat.getColor(context, R.color.miGris));
        else
            pantalla.setBackgroundColor(Color.WHITE);

        boolean musica  = sharedPreferences.getBoolean("opcMusica", false);
        MediaPlayer miMusica = MediaPlayer.create(getApplicationContext(), R.raw.acdc);
        if(musica) {
            miMusica.start();
        }
        else {
            if(miMusica.isPlaying()){
                try {
                    miMusica.stop();
                    miMusica.prepare();
                } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id. itAjustes:
                startActivity(new Intent(this, AjustesActivity.class));
                return true;
            case R.id. opcion2:
                Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", idRecibido);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id. opcion3:
                startActivity(new Intent(this, DibujoActivity.class));
                return true;
            case R.id. opcion4:
                startActivity(new Intent(this, NoticiasActivity.class));
                return true;
            case R.id. opcion5:
                startActivity(new Intent(this, AcercaDeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class ViewHolder{
        TextView pan, precio;
        ImageView imagen;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Salir")
                .setMessage("Seguro quieres salir?")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }}).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}