import java.awt.Image;
import java.util.*;
import javax.swing.ImageIcon;

public class Ennemi1 extends Personnage{
	
	protected Image imgEnnemi;
	protected ImageIcon icoEnnemi;
	
	protected final int arret = 15; // vitesse du deplacement du personnage (si on fait un systeme d'incrementation de difficulte on fera une boucle qui diminue cette variable)
	protected int dxEnnemi1,vie; // 1 quand il se deplace vers la droite et -1 vers la gauche
	protected Tir tir;
	protected boolean b;

	public Ennemi1(int x,int y,boolean b) {
		super(x,y,27,30); // position du champignon et sa taille
		super.versDroite = true; // il est initialement tourne vers la droite et peut marcher
		super.marche = true;
		this.dxEnnemi1 = 1;
		this.b = b;
		if(!b){
		this.vie = 5;
		this.icoEnnemi = new ImageIcon(getClass().getResource("Image/ennemiArretDroite.png"));
		this.imgEnnemi = this.icoEnnemi.getImage();
		}else if(b){
		this.vie = 10;
		this.icoEnnemi = new ImageIcon(getClass().getResource("Image/ennemi2ArretGauche.png"));
	    	this.imgEnnemi = this.icoEnnemi.getImage();
		}
	}
	
	
	
	public Image getImageEnnemi() {return imgEnnemi;}

	public void bouge() {
		if(super.versDroite == true)this.dxEnnemi1 = 1;
		else {this.dxEnnemi1 = -1;}
		super.absc += this.dxEnnemi1; // met a jour l'abscisse des ennemis afin de les deplacer
	}
	

	public void contact(Object ob) { // test collison avant quand l'ennemi est vers la droite et arriere quand vers la gauche
	    if(ob instanceof Objet){
		Objet o = (Objet)ob;
		if(super.collisionAvant(o) == true && this.versDroite == true) {
			super.versDroite = false;
			this.dxEnnemi1 = -1; //repart dans l'autre sens car notre ennemi est toujours en mouvement donc -1 le remettra vers la gauche
		} else if (super.collisionArrier(o) == true && this.versDroite == false) {
			super.versDroite = true;
			this.dxEnnemi1 = 1;
		}
	    }
	    if(ob instanceof PPrincipal){
		PPrincipal p=(PPrincipal)ob;
		if(super.collisionAvant(p) && estVivant){
		    dxEnnemi1=-1;
		}
		else if (super.collisionArrier(p) && estVivant){
		    dxEnnemi1=-1;
		}
	    }
	}

	public boolean collisionTir(Object ob){
	if(ob instanceof Tir){
		Tir t=(Tir)ob;
		if(super.collisionAvant(t) && estVivant){
		if(vie <= 0){
		    estVivant = false;
		}else{
		vie -= 1;
		}	
		    return true;
		}
		else if (super.collisionArriere(t) && estVivant){
			if(vie <= 0){
		    estVivant = false;
		}else{
		vie -= 1;
		}
		    return true;
		}
	    }
	return false;
	}


     public static void ennemis(LinkedList<Ennemi1> e ){
	Ennemi1 e1 = new Ennemi1(900,300,false);
	 e.add(e1);
	 int i=0;
	 int y=0;
	 boolean c=true;
	 while(c){
	     i=e.size()-1;
	     y=e.get(i).absc;
	     int d=(int)(Math.random() * (950-600)) + 600;
	     if( d>=750){
		 e.add(new Ennemi1(y+d,300,false));
	     }
	     if(i==60){ c=false;}
	 }
     }



	protected static void ennemis2(LinkedList<Ennemi1> e ){
	Ennemi1 e2 = new Ennemi1(8000,277,true);
	 e.add(e2);
	 int i=0;
	 int y=0;
	 boolean c=true;
	 while(c){
	     i=e.size()-1;
	     y=e.get(i).absc;
	     int d=(int)(Math.random() * (1000-700)) + 700;
	     if( d>=800) e.add(new Ennemi1(y+d,277,true));
	     if(i==20) c=false;
	 }
	 
     }
	
    
}
