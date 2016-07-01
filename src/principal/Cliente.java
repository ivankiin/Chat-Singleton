package principal;

import singlenton.SinglentonCli;

/**
 * 
 * @author ivan
 *
 */

public class Cliente {
	
	public static void main(String[] args){
		SinglentonCli cli = SinglentonCli.instancia();
		cli.conectar();
	}

}
