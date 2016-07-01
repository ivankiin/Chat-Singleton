package hilos;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import vista.VentanaServidor;

public class ThreadRecibeServ implements Runnable{
	private VentanaServidor vtn;
	private String mensaje;
	private ObjectInputStream entrada;
	private Socket cliente;
	
	/**
	 * Inicializar chatServer y configurar GUI
	 */
	public ThreadRecibeServ(Socket cliente, VentanaServidor vtn) {
		this.cliente = cliente;
		this.vtn = vtn;
	}
	
	public void mostrarMensaje (String mensaje){
		vtn.areaTexto.append(mensaje);
	}

	@Override
	public void run() {
		try {
			entrada = new ObjectInputStream(cliente.getInputStream());
		} catch (IOException ex) {
			Logger.getLogger(ThreadRecibeServ.class.getName()).log(Level.SEVERE, null, ex);			
		}
		do {	// Procesa los mensajes enviados desde el servidor
			try {	// Leer el mensaje y mostrarlo
				mensaje = (String) entrada.readObject();	// Leer nuevo mensaje
				vtn.mostrarMensaje(mensaje);
			}	// Fin try
			catch (SocketException ex) {				
			}
			catch (EOFException e) {
				vtn.mostrarMensaje("Fin de la conexion");
				break;
			}	// Fin del catch
			catch (IOException ex) {
				Logger.getLogger(ThreadRecibeServ.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ClassNotFoundException classNotFoundException){
				vtn.mostrarMensaje("Objeto desconocido");
			} // fin catch
		} while (!mensaje.equals("Servidor>>> TERMINATE"));	// Ejecuta hasta que el server escriba TERMINATE
		
		try {
			entrada.close();	// Cierra input Stream
			cliente.close();	// Cierra Socket
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		vtn.mostrarMensaje("Fin de la conexion");
		System.exit(0);
	}
}
