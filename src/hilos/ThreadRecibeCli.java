package hilos;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import vista.VentanaCliente;

public class ThreadRecibeCli implements Runnable{
	private VentanaCliente cli;
	private String mensaje;
	private ObjectInputStream entrada;
	private Socket cliente;
	
	// Inicializar chatServer y configurar GUI
	public ThreadRecibeCli(Socket cliente, VentanaCliente cli){
		this.cliente = cliente;
		this.cli = cli;	
	}
	
	public void mostrarMensaje(String mensaje) {
		cli.areaTexto.append(mensaje);
	}
	
	public void run (){
		try {
			entrada = new ObjectInputStream(cliente.getInputStream());
		} catch (IOException e) {
			Logger.getLogger(ThreadRecibeCli.class.getName()).log(Level.SEVERE, null, e);
		}
		do {	// Procesa los mensajes enviados desde el servidor
			try {	// Leer el mensaje y mostrarlo
				mensaje = (String) entrada.readObject();	// Leer nuevo mensaje
				cli.mostrarMensaje(mensaje);
			}
			catch(SocketException e){
			}
			catch (EOFException eofException) {
				cli.mostrarMensaje("Fin de la conexion");
				break;
			}
			catch (IOException e) {
				Logger.getLogger(ThreadRecibeCli.class.getName()).log(Level.SEVERE, null, e);
			}
			catch (ClassNotFoundException e) {
				cli.mostrarMensaje("Objeto desconocido");
			}	// Fin de catch
			
		} while (!mensaje.equals("Cliente>>> TERMINATE"));	//Ejecuta hasta que el server escriba TERMINATE
		
		try {
			entrada.close();	// Cierra entrada Stream
			cliente.close();	// Cierra socket
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		cli.mostrarMensaje("Fin de la conexion");
		System.exit(0);
	}

}
