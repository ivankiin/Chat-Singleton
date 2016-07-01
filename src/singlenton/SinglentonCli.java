package singlenton;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import hilos.ThreadEnviaCli;
import hilos.ThreadRecibeCli;
import vista.VentanaCliente;

public final class SinglentonCli {
	private static final SinglentonCli cli = new SinglentonCli();
	private ExecutorService executor;
	private static ServerSocket servidor;
	private static Socket cliente;
	//private static String ip = "127.0.0.1";
	private static String ip = "localhost";
	private VentanaCliente vtn = new VentanaCliente();
	
	/**
	 * Constructor
	 */
	private SinglentonCli () {
	}
	
	/**
	 * Metodo que retorna la unica instancia generada en esta clase
	 * 
	 * @return instancia unica
	 */
	public static SinglentonCli instancia() {
		return cli;
	}
	
	/**
	 * Metodo que realiza la conexion
	 */
	public void conectar() {
		executor = Executors.newCachedThreadPool();	//Para correr los threads
		
		try {
			vtn.mostrarMensaje("Esperando por un servidor");
			cliente = new Socket(InetAddress.getByName(ip), 11111);	// Comunicarme con el servidor
			vtn.mostrarMensaje("Conectado a: "+cliente.getInetAddress().getHostName());
			
			vtn.habilitarTexto(true);	//Habilita el texto
			
			//Ejecucion de los threads
			executor.execute(new ThreadRecibeCli(cliente, vtn));
			executor.execute(new ThreadEnviaCli(cliente, vtn));
			
		} catch (IOException ex) {
			Logger.getLogger(SinglentonCli.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally {			
		}
		executor.shutdown();
	}

}
