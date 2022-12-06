package banco;

import javax.swing.JOptionPane;

import conta.Contas;

public class Banco {
	public static void main(String[] args) {
		Contas ct = new Contas();
		
			Object[] obj = {"Realize o cadastro e lembre-se de não colocar dados reais de contas bancarias, apenas "
					+ "dados ficticos, mas lembre dos dados que ira colocar para que possa ser realizado o login!"};
			
			JOptionPane.showMessageDialog(null, obj);
			
	        ct.opcoes();
	    }
	}

