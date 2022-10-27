
import javax.swing.*;
public class Main {
	public static Fond fond;
	
	public static void main(String [] args) {
		JFrame fenetre = new JFrame("Fire Run!");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(700,360);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);
		
		fond = new Fond();
		
		fenetre.setContentPane(fond);
		fenetre.setVisible(true);
		
	}
}
