package com.example.luis.panaderia.Otros;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.luis.panaderia.R;

public class DibujoActivity extends AppCompatActivity {

    private VistaDibujo vistaDibujo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujo);

        vistaDibujo = (VistaDibujo) findViewById(R.id.view);
        registerForContextMenu(vistaDibujo);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logo, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opc1:
                Toast.makeText(this, "Opción 1 del menú contextual", Toast.LENGTH_LONG).show();
                return true;
            case R.id.opc2:
                Toast.makeText(this,"Opción 2 del menú contextual",Toast.LENGTH_LONG).show();
                return true;
            default:
                return true;
        }
    }

}
