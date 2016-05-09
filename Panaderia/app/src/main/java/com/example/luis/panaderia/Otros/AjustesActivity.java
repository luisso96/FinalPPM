package com.example.luis.panaderia.Otros;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.luis.panaderia.R;

public class AjustesActivity extends PreferenceActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ajustes);
    }

}