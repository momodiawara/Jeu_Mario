import jeu.audio.Audio;
import java.awt.event.*;
public class Action implements KeyListener,MouseListener {
    protected Fond fond;
    public Action(Fond f){ fond=f;}
	//@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(fond.xPos == -1) {
				fond.xPos = 0;
				fond.xFond1 = -50;
				fond.xFond2 = 750;
			}
			fond.perso.marche = true;
			fond.perso.versDroite = true;
			fond.dx = 1 ;
			fond.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {	
			fond.perso.marche = true;
			fond.perso.versDroite = false;
			fond.dx = -1;
			fond.repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){//perso saute
		fond.perso.saut = true;
		Audio.playSound("/Audio/saut.wav");
		fond.repaint();		
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
		fond.perso.tir = true;
		fond.estTir = true;
		Tir tir = new Tir(Main.fond.perso.absc,300);
		fond.listeTir.add(tir);
		fond.repaint();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		fond.perso.marche = false;
		fond.dx = 0;
		fond.perso.tir = false;
	}
	@Override
	public void keyTyped(KeyEvent e){}

 public void mouseClicked(MouseEvent e){
	this.fond.etat = fond.etat.PAUSE;
   }

   public void mouseEntered(MouseEvent e){

   }


public void mouseExited(MouseEvent e){

   }


public void mousePressed(MouseEvent e){

   }

public void mouseReleased(MouseEvent e){

   }
}
