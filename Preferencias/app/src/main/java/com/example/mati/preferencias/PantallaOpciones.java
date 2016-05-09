package com.example.mati.preferencias;

import android.os.Bundle;
import android.preference.PreferenceActivity;
public class PantallaOpciones extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones);
    }
}
