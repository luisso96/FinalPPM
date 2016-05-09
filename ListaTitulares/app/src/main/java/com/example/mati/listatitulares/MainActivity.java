package com.example.mati.listatitulares;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Titular[] datos = new Titular[]{
            new Titular("Título 1", "Subtítulo largo 1"),
            new Titular("Título 2", "Subtítulo largo 2"),
            new Titular("Título 3", "Subtítulo largo 3"),
            new Titular("Título 4", "Subtítulo largo 4"),
            new Titular("Título 5", "Subtítulo largo 5")
};

ListView miLista;

public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String mensaje;
        miLista = (ListView) findViewById(R.id.ListTitular);

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        miLista.setAdapter(adaptador);
        }

class AdaptadorTitulares extends ArrayAdapter {
    Activity context;

    AdaptadorTitulares(Activity context) {
        super(context, R.layout.listitem_titular, datos);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_titular, null);
        TextView lblTitulo = (TextView) item.findViewById(R.id.LblTitulo);
        lblTitulo.setText(datos[position].getTitulo());
        TextView lblSubtitulo = (TextView) item.findViewById(R.id.LblSubTitulo);
        lblSubtitulo.setText(datos[position].getSubtitulo());
        return (item);
    }
}

}