package singlenton;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import hilos.ThreadEnviaServ;
import hilos.ThreadRecibeServ;
import vista.VentanaServidor;

/**
 * 
 * @author ivan
 *
 */
public final class SinglentonSer {
	private static final SinglentonSer ser = new SinglentonSer();
	private ExecutorService executor;
	private static ServerSocket servidor;
	private static Socket conexion;
	private static String ip;
	private VentanaServidor vtn = new VentanaServidor();
	
	/**
	 * Constructor
	 */
	private SinglentonSer (){
		//ip = "127.0.0.1";	// TODO
		ip= "localhost";
	}
	
	/**
	 * Metodo que retorna la unica instancia generada en esta clase
	 * 
	 * @return instancia unica
	 */
	public static SinglentonSer instancia (){
		return ser;
	}
	
	/**
	 * Metodo que realiza la conexion
	 */
	public void conectar(){
		executor = Executors.newCachedThreadPool();	//Para correr los threads
		
		try {
			//vtn.mostrarMensaje("No se encuentra Servidor");
			servidor = new ServerSocket(11111, 100);
			vtn.mostrarMensaje("Esperando Cliente ...");
			
			// Bucle infinito para esperar conexiones de los clientes
			while (true){
				try {
					conexion = servidor.accept();	// Permite al servidor aceptar conexiones
					vtn.mostrarMensaje("Conexion Establecida");
					//vtn.mostrarMensaje("Conectado a: "+ conexion.getInetAddress().getHostName());
					vtn.mostrarMensaje("Conectado a: ivan_martinez_server.com");
					vtn.habilitarTexto(true);	// Permite escribir texto para enviar
					
					// Ejecucion de los threads
					executor.execute(new ThreadRecibeServ(conexion, vtn));
					executor.execute(new ThreadEnviaServ(conexion, vtn));		
					
				} catch (IOException ex) {
					Logger.getLogger(SinglentonSer.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} catch (IOException ex){
			Logger.getLogger(SinglentonSer.class.getName()).log(Level.SEVERE, null, ex);
		}	// Fin del catch
		finally {			
		}
		executor.shutdown();
	}
}
