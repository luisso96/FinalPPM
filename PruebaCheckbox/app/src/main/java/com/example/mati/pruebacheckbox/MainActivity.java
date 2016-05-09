package com.example.mati.pruebacheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chkBoxCycling;
    CheckBox chkBoxTeaching;
    CheckBox chkBoxBlogging;
    Button btnHobby;
    TextView txtHobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialUISetup();
    }

    public void initialUISetup()
    {
        chkBoxCycling = (CheckBox) findViewById(R.id.chkBoxCycling);
        chkBoxTeaching = (CheckBox) findViewById(R.id.chkBoxTeaching);
        chkBoxBlogging = (CheckBox) findViewById(R.id.chkBoxBlogging);
        btnHobby = (Button)findViewById(R.id.btnHobby);
        txtHobby = (TextView)findViewById(R.id.txtHobby);

        btnHobby.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getHobbyClick(v);
            }
        });
    }
    public void getHobbyClick(View v)
    {  String strMessage = "";

        if(chkBoxCycling.isChecked())
        {
            strMessage+="Cycling ";
        }
        if(chkBoxTeaching.isChecked())
        {
            strMessage+="Teaching ";
        }
        if(chkBoxBlogging.isChecked())
        {
            strMessage+="Blogging ";
        }
        showTextNotification(strMessage);
    }

    public void showTextNotification(String msgToDisplay)
    {
        txtHobby.setText(msgToDisplay);
        //Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();

    }
} // de la clase