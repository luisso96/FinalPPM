package com.example.luis.panaderia;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.panaderia.Objetos.Usuario;
import com.example.luis.panaderia.Otros.SQLiteHelper;
import com.example.luis.panaderia.Otros.VentanaDialogo;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        this.setTitle("Lista de Usuarios");

        llenarArray();

        Adaptador adaptador = new Adaptador(this);
        lista = (ListView)findViewById(R.id.lsUsuarios);
        lista.setAdapter(adaptador);
    }

    class ViewHolder{
        TextView tvId, tvUsuario, tvContrasenya;
    }

    SQLiteHelper sqliteHelper = new SQLiteHelper(this, "panaderia.sqlite", null, 1);

    Usuario[] usuarios;
    int num;
    public ListView lista;

    ViewHolder holder;


    class Adaptador extends ArrayAdapter<Usuario> {
        Activity context;

        public Adaptador(Activity context) {
            super(context, R.layout.vista_item_lista_usuarios, usuarios);
            this.context = context;
        }

        public View getView(int i, View convertView, ViewGroup parent) {

            View item = convertView;

            if(item==null) {
                LayoutInflater inflater = getLayoutInflater();
                item = inflater.inflate(R.layout.vista_item_lista_usuarios, parent, false);

                holder = new ViewHolder();

                holder.tvId = (TextView) item.findViewById(R.id.tvId);
                holder.tvUsuario = (TextView) item.findViewById(R.id.tvUsuario);
                holder.tvContrasenya = (TextView) item.findViewById(R.id.tvContrasenya);

                item.setTag(holder);
            }
            else
                holder = (ViewHolder)item.getTag();

            holder.tvId.setText(String.valueOf(usuarios[i].getId()));
            holder.tvUsuario.setText(usuarios[i].getUsuario());
            holder.tvContrasenya.setText(usuarios[i].getContrasenya());


            num = i;
            ImageButton borrarUsuario = (ImageButton)item.findViewById(R.id.borrarUsuario);
            borrarUsuario.setOnClickListener(new View.OnClickListener() {
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
            Cursor cursor = bd.rawQuery("SELECT * FROM usuarios", null);
            int cantidad = cursor.getCount();
            int i = 0;
            usuarios = new Usuario[cantidad];

            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(0);
                    String usuario = cursor.getString(1);
                    String contrasenya = cursor.getString(2);

                    usuarios[i] = new Usuario(id,usuario,contrasenya);
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
        bd.execSQL("DELETE FROM usuarios WHERE id = " + usuarios[num].getId());
        mostrarToast("Usuario borrado correctamente");
        recargarLista();
    }



    public void recargarLista() {
        Intent home_intent = new Intent(getApplicationContext(), AdminActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
