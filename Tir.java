import javax.swing.*;
import java.awt.*;
public class Tir{
   protected int x,y,hauteur,largeur;
   protected ImageIcon tir,tir2;
   protected Ennemi1 e1;
   protected Ennemi1 e2;
   protected Objet o;


   public Tir(int x, int y){
	this.x = x;
	this.y = y;
	this.hauteur = 20;
	this.largeur = 23;
	this.tir = new ImageIcon(getClass().getResource("Image/tir.png"));
	this.tir2 = new ImageIcon(getClass().getResource("Image/tir2.png"));
   }

   public int estTire(){
	if(Main.fond.perso.versDroite){
	return this.x += 2;
	}if(!Main.fond.perso.versDroite){
	return this.x -=2;
	}
	return x;
    }


   public void affiche(Graphics g){
	if(Main.fond.perso.versDroite){
	g.drawImage(this.tir.getImage(),this.estTire(),Main.fond.perso.ord+25,null);
	}else if(Main.fond.perso.saut){
	g.drawImage(this.tir.getImage(),this.estTire(),Main.fond.perso.ord+25,null);
	}else{
	g.drawImage(this.tir2.getImage(),this.estTire(),Main.fond.perso.ord+25,null);
	}
	}


	public void collisionObjet(Object ob){
	if(ob instanceof Ennemi1){
		Ennemi1 e=(Ennemi1)ob;
		if(e.estVivant){
			if(e.vie <= 0 && !e.b){
		    e.estVivant = false;
			Main.fond.score.setScorePlus(30); 
			}else if (e.vie <= 0 && e.b){
 			e.estVivant = false;
			Main.fond.score.setScorePlus(50); 
			}else{
			e.vie -= 1;
			}	
		}
	    }
	}

	public boolean collisionArriere(Objet o){
	if(this.largeur < o.x + o.largeur || this.largeur > o.x + o.largeur || this.y + this.hauteur <= o.y || this.y >= o.y + o.hauteur){
	return false;
	}
	return true;
	}

	public boolean collisionArriere(Personnage p){
	if(this.largeur < p.absc + p.largeur || this.largeur > p.absc + p.largeur || this.y + this.hauteur <= p.absc || this.y >= p.absc + p.taille){
	return false;
	}
	return true;
	}

	public boolean collisionAvant(Objet o){
	if(this.x + this.largeur < o.x || this.x + this.largeur > o.x || this.y + this.hauteur <= o.y || this.y >= o.y + o.hauteur){
	return false;
	}
	return true;
	}

	public boolean collisionAvant(Personnage p){
	if(this.x + this.largeur < p.absc || this.x + this.largeur > p.absc || this.x + this.hauteur <= p.ord || this.y >= p.ord + p.taille){
	return false;
	}
	return true;
	}

	public boolean collisionEcran(){
	return (this.x + this.largeur > 800 || this.x + this.largeur < 0);
	}

}
