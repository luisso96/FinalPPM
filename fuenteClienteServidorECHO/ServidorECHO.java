import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorECHO {

	public static void main(String[] args) {
		try {
			//Creamos un socket en el puerto 7
			ServerSocket socket = new ServerSocket(7);
			System.out.println("Creamos socket");
			//Esperamos a que lleguen peticiones de clientes
			while (true) {
				System.out.println("Espero petición");
				//Obtenemos el cliente desde el que nos están realizando una petición
				Socket cliente = socket.accept();
				System.out.println("Espero petición2");
				//Para leer del socket lo que nos pide el cliente
				BufferedReader entrada = new BufferedReader(
						new InputStreamReader(cliente.getInputStream()));
				//Para escribir en el socket la respuesta del servidor
				PrintWriter salida = new PrintWriter(
						new OutputStreamWriter(cliente.getOutputStream()));
				//Obtenemos la petición del cliente
				String datos = entrada.readLine();
				//Escribimos en el socket lo mismo que nos llegó desde el cliente.
				//De esta forma estamos comportándonos como un servidor de ECHO.
				salida.println(datos);
				//Cerramos la conexión con el cliente
				cliente.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
