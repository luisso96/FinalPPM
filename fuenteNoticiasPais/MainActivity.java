package com.example.alopez.accesohttp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

   private static final String TAG="Http conexion";
    private Button boton;
  private TextView texto;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        boton = (Button) findViewById(R.id.Boton1);
        texto = (TextView) findViewById(R.id.texto1);

        final String urlS = "http://www.elpais.com/rss/feed.html?feedId=1022";
        //Escuchador del botón. Acciones en caso de pulsar el botón
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                new AsyncHttpTask().execute(urlS);
            }
        });

    }

    public  class AsyncHttpTask extends AsyncTask<String, String, String> {
        String response="";//check Internet conenction.

        private void checkInternetConenction(){

            ConnectivityManager check = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if (check != null)
            {
                NetworkInfo[] info = check.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i <info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        {
                            Toast.makeText(MainActivity.this, "Internet is connected",
                                    Toast.LENGTH_SHORT).show();
                        }
           }
            else{
                Toast.makeText(MainActivity.this, "not conencted to internet",
                        Toast.LENGTH_SHORT).show();
            }
        }

        protected void onPreExecute(){
            checkInternetConenction();
        }

        protected String doInBackground(String... params) {
            InputStream inputStream = null;

            HttpURLConnection urlConnection = null;

            String salida = "";
            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
               /* urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");*/
                urlConnection.connect();
                if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                    InputStream is =  urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    int i = 0;
                    int j = 0;
                    String linea = reader.readLine();
                    while (linea != null) {
                        //Si encontramos la etiqueta <title> podemos recuperar la noticia
                        if (linea.indexOf("<title>") >= 0) {
                            i = linea.indexOf("<title>") + 16;
                            j = linea.indexOf("</title>") - 3;
                            salida += linea.substring(i, j);
                            salida += "\n----------------\n";
                        }
                        //Leemos la siguiente línea
                        linea = reader.readLine();
                    }
                    reader.close();/* Close Stream */
                    if (is != null) {
                        is.close();
                    }
                }
                publishProgress(salida);
            }catch(Exception e){
                salida= new String("Exception: " + e.getMessage());
            }
            finally {
            urlConnection.disconnect();
            }
            return salida;
        }
        protected void onProgressUpdate(String... pasos) {
            Toast.makeText(MainActivity.this, pasos[0], Toast.LENGTH_SHORT).show();
            texto.append(pasos[0]);
        }


       protected void onPostExecute(){   //String result) {
            //Download complete. Lets update UI
           //  texto.append(result);

        }
    } // de AsynckTask;


}