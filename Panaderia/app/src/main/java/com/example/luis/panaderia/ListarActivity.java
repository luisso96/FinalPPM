package com.example.luis.panaderia;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.panaderia.Objetos.Pedido;
import com.example.luis.panaderia.Otros.SQLiteHelper;
import com.example.luis.panaderia.Otros.VentanaDialogo;

public class ListarActivity extends AppCompatActivity {

    class ViewHolder{
        TextView tvId, tvPan, tvTamano, tvComplemento, tvUnidades, tvPrecio;
        ImageView ivImagen, btBorrar;
    }

    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        this.setTitle("Lista de pedidos");

        Bundle  bundle = getIntent().getExtras();
        idUsuario  = bundle.getInt("ID");

        llenarArray();

        Adaptador adaptador = new Adaptador(this);
        lista = (ListView)findViewById(R.id.lsPedidos);
        lista.setAdapter(adaptador);
    }


    SQLiteHelper sqliteHelper = new SQLiteHelper(this, "panaderia.sqlite", null, 1);

    Pedido[] pedidos;
    int num;
    public ListView lista;

    ViewHolder holder;


    class Adaptador extends ArrayAdapter<Pedido> {
        Activity context;

        public Adaptador(Activity context) {
            super(context, R.layout.vista_item_lista_pedidos, pedidos);
            this.context = context;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int i, View convertView, ViewGroup parent) {

            View item = convertView;

            if(item==null) {
                LayoutInflater inflater = getLayoutInflater();
                item = inflater.inflate(R.layout.vista_item_lista_pedidos, parent, false);

                holder = new ViewHolder();

                num = i;
                holder.btBorrar = (ImageButton) item.findViewById(R.id.borrarItem);
                holder.tvId = (TextView) item.findViewById(R.id.tId);
                holder.tvPan = (TextView) item.findViewById(R.id.tPan);
                holder.tvTamano = (TextView) item.findViewById(R.id.tTamano);
                holder.tvComplemento = (TextView) item.findViewById(R.id.tComplemento);
                holder.tvUnidades = (TextView) item.findViewById(R.id.tUnidades);
                holder.tvPrecio = (TextView) item.findViewById(R.id.tPrecio);
                holder.ivImagen = (ImageView) item.findViewById(R.id.iImagen);

                item.setTag(holder);
            }
            else
                holder = (ViewHolder)item.getTag();

            holder.tvId.setText("ID: " + pedidos[i].getId());
            holder.tvPan.setText(pedidos[i].getPan());
            holder.tvTamano.setText(pedidos[i].getTamano());
            holder.tvComplemento.setText(pedidos[i].getComplemento());
            holder.tvUnidades.setText(pedidos[i].getUnidades());
            holder.tvPrecio.setText(pedidos[i].getPrecio());
            holder.btBorrar.setTag(String.valueOf(pedidos[i].getId()));

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                holder.ivImagen.setBackground(getDrawable(pedidos[i].getImagen()));
            else
                holder.ivImagen.setBackgroundDrawable(getResources().getDrawable(pedidos[i].getImagen()));


            holder.btBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarConfirmacion();
                }
            });


            return (item);
        }
    }

    public void llenarArray() {

        SQLiteHelper sqliteHelper = new SQLiteHelper(this, "panaderia.sqlite", null, 1);
        SQLiteDatabase bd = sqliteHelper.getReadableDatabase();

        if(bd != null) {
            Cursor cursor = bd.rawQuery("SELECT * FROM Pedidos p WHERE p.usuarioId = " + idUsuario, null);
            int cantidad = cursor.getCount();
            int i = 0;
            pedidos = new Pedido[cantidad];

            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(0);
                    String pan = cursor.getString(2);
                    String tamano = cursor.getString(3);
                    String complemento = cursor.getString(4);
                    String unidades = cursor.getString(5);
                    String precio = cursor.getString(6);
                    int imagen = cursor.getInt(7);

                    pedidos[i] = new Pedido(id,pan,tamano,complemento,unidades,precio,imagen);
                    i++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        }
    }

    public void mostrarToast(String texto){
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
    }

    public void mostrarConfirmacion() {
        VentanaDialogo appdialog = new VentanaDialogo();
        appdialog.Confirm(this, "Confirmación", "Seguro quieres eliminar?", "NO", "SI", aceptar(), rechazar());
    }

    public Runnable aceptar(){
        return new Runnable() {
            public void run() {
                borrarPedido();
            }
        };
    }
    public Runnable rechazar(){
        return new Runnable() {
            public void run() {
                mostrarToast("Operación cancelada");
            }
        };
    }

    public void borrarPedido(){
        final SQLiteDatabase bd = sqliteHelper.getReadableDatabase();
        bd.execSQL("DELETE FROM Pedidos WHERE id = " + pedidos[num].getId());
        mostrarToast("Pedido borrado correctamente");
        recargarLista();
    }


    public void recargarLista() {
        Intent intent = new Intent(getApplicationContext(), ListarActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", idUsuario);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}