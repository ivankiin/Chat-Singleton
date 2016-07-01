package principal;

import singlenton.SinglentonSer;

/**
 * 
 * @author ivan
 *
 */

public class Servidor {
	
	public static void main(String[] args){
		SinglentonSer ser = SinglentonSer.instancia();
		ser.conectar();		
	}	
}
