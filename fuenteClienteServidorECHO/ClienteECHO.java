package org.aguilar.clienteecho;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ClienteECHO extends Activity {
	private TextView salida;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        salida = (TextView) findViewById(R.id.TextView01);
        peticionCliente();
    }
    
    private void peticionCliente() {
    	//Socket = IP + PUERTO al que queremos conectarnos
    	//En este caso utilizaremos un servidor que está en el mismo ordenador
    	//String ip = "169.254.212.7";
    	String ip = "localhost";
    	int puerto = 7;
    	try {
    		Socket socket = new Socket(ip,puerto);
    		//Para leer del socket
    		BufferedReader entrada = new BufferedReader (
    				new InputStreamReader(socket.getInputStream()));
    		//Para escribir en el socket
    		PrintWriter salida = new PrintWriter(
    				new OutputStreamWriter(socket.getOutputStream()));
    		//Enviamos algo al socket, o sea, al servidor
    		log("Enviamos una cadena al servidor de echo...");
    		salida.println("Hola Cefire Cheste");
    		//Mostramos lo que estamos recibiendo
    		log("Recibimos del servidor la cadena: " + entrada.readLine());
    		socket.close();
    	} catch (Exception e) {
    		log("Error: " + e.toString());
    	}
    }
    
    private void log(String cadena) {
    	salida.append(cadena + "\n");
    }
}