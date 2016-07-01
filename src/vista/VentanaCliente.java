package vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author ivan
 *
 */
public class VentanaCliente extends JFrame{

	public JTextField campoTexto = new JTextField();	// Para mostrar mensajes de los usuarios
	public JTextArea areaTexto = new JTextArea();	// Para ingresar mensaje a enviar
	
	private Color blanco = new Color(250, 250, 250);
	private Color negro = new Color(000, 000, 000);
	private Color naranja = new Color(255, 	0, 	0);
	
	/**
	 * Constructor de la clase donde se inicializan todos los componentes
	 * de la ventana principal del cliente
	 */
	public VentanaCliente () {
		super.setTitle("Cliente");	// Titulo de la ventana
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);	// Agrega la opcion de cerrar la ventana
		cargarControles();	// Metodo que sirve para cargar los componentes
		super.setSize(300, 320);	// Tamaño de la ventana	
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}
	
	/**
	 * Metodo que tiene la funcion de cargar los controles
	 */
	private void cargarControles() {
		campoTexto.setEditable(false);	// No permite que sea editable el campo de texto
		add(campoTexto, BorderLayout.NORTH);	//Coloca el campo de texto en la parte superior
		
		areaTexto.setEditable(false);
		add(new JScrollPane(areaTexto), BorderLayout.CENTER);
		areaTexto.setBackground(blanco);
		areaTexto.setForeground(naranja);
		campoTexto.setForeground(negro);
	}
	
	/**
	 * Metodo para mostrar texto en displayArea
	 */
	public void mostrarMensaje (String mensaje){
		areaTexto.append(mensaje + "\n");
	}
	
	/**
	 * Metodo para habilitar el texto en campoTexto
	 */
	public void habilitarTexto (boolean editable) {
		campoTexto.setEditable(editable);
	}
	
}
