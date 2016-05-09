package com.example.luis.panaderia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luis.panaderia.Otros.SQLiteHelper;

public class ResultadoActivity extends AppCompatActivity {

    int numImagen, idUsuario;
    String pan, tamano, complemento, unidades, precio;

    ListView lista;
    ImageView imagen;
    Button guardarBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        this.setTitle("Cuenta");

        imagen    = (ImageView)findViewById(R.id.ivPan);
        guardarBD = (Button)findViewById(R.id.btGuardar);

        Bundle  bundle = getIntent().getExtras();

        String cadPan    = bundle.getString("PAN");
        String cadTamano    = bundle.getString("TAMANO");
        String cadComplemento = bundle.getString("COMPLEMENTO");
        double numPrecio    = bundle.getDouble("PRECIO");
        int numUnidades     = bundle.getInt("UNIDADES");
        numImagen           = bundle.getInt("IMAGEN");
        idUsuario           = bundle.getInt("IDUSUARIO");

        pan = "Pan: " + cadPan;
        tamano = "Tamaño: " + cadTamano;
        complemento = "Complemento: " + cadComplemento;
        unidades = "Unidades: " + numUnidades;
        precio = "Precio: " + Double.toString(numPrecio) + " €";

        String[] registros = {pan, tamano, complemento, unidades, precio};

        lista = (ListView)findViewById(R.id.listaResumen);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registros);
        lista.setAdapter(arrayAdapter);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            imagen.setBackground(getDrawable(numImagen));
        else
            imagen.setBackgroundDrawable(getResources().getDrawable(numImagen));

        guardarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarPedido();
            }
        });
    }

    public void insertarPedido(){

        SQLiteHelper admin = new SQLiteHelper(this,"panaderia.sqlite", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("usuarioId" , idUsuario);
        contentValues.put("pan"    , pan);
        contentValues.put("tamano"    , tamano);
        contentValues.put("complemento" , complemento);
        contentValues.put("unidades"  , unidades);
        contentValues.put("precio"    , precio);
        contentValues.put("imagen"    , numImagen);

        bd.insert("Pedidos", null, contentValues);
        bd.close();

        Toast.makeText(this, "Pedido guardado correctamente", Toast.LENGTH_SHORT).show();
    }
}