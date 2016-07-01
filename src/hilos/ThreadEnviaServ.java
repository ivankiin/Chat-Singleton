package hilos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import vista.VentanaServidor;

/**
 * 
 * @author ivan
 *
 */
public class ThreadEnviaServ implements Runnable{
	private VentanaServidor vtn;
	private ObjectOutputStream salida;
	private String mensaje;
	private Socket conexion;
	
	public ThreadEnviaServ(Socket conexion, VentanaServidor vtn){
		this.conexion = conexion;
		this.vtn = vtn;
		
		// Evento que ocurre al escribir en el areaTexto
		vtn.campoTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				mensaje = event.getActionCommand();
				enviarDatos(mensaje);	// Se envia el mensaje
				vtn.campoTexto.setText("");	// Borra el texto del enterfield
			}	// Fin del metodo actionPerformed					
		}
		);	// Fin de la llamada a addActionListener			
	}
	
	// Enviar objeto a cliente
	private void enviarDatos(String mensaje){
		try{
			salida.writeObject("Servidor>>> " +mensaje);
			salida.flush();	// Flush salida a cliente
			vtn.mostrarMensaje("Servidor>>> " + mensaje);
		}
		catch (IOException ioException){
			vtn.mostrarMensaje("Error escribiendo mensaje");			
		}
	}
	
	//Manipula areaPantalla en el hilo despachador de eventos
	public void mostrarMensaje(String mensaje) {
		vtn.areaTexto.append(mensaje);
	}

	@Override
	public void run() {
		try {
			salida = new ObjectOutputStream(conexion.getOutputStream());
			salida.flush();
		} catch (SocketException ex) {
		} catch ( IOException ioException) {
			ioException.printStackTrace();
		} catch (NullPointerException ex) {		
		}		
	}

}
