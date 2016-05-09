package com.example.luis.panaderia.Otros;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class VentanaDialogo {

    public Runnable respestaSI = null;
    public Runnable respuestaNO = null;

    public boolean Confirm(Activity act, String Title, String ConfirmText, String CancelBtn,
                           String OkBtn, Runnable aProcedure, Runnable bProcedure) {

        respestaSI = aProcedure;
        respuestaNO = bProcedure;

        AlertDialog dialog = new AlertDialog.Builder(act).create();
        dialog.setTitle(Title);
        dialog.setMessage(ConfirmText);
        dialog.setCancelable(false);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, OkBtn,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        respestaSI.run();
                    }
                });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, CancelBtn,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        respuestaNO.run();
                    }
                });

        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.show();
        return true;
    }
}