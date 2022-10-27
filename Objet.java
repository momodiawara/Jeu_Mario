import java.util.*;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Objet {
    protected int largeur,hauteur,x,y;
    protected Image imgObjet;
    protected ImageIcon icoObjet;
    protected Tir tir;
    
    public Objet(int x,int y,int largeur, int hauteur) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.icoObjet = new ImageIcon(getClass().getResource(quelleImage(largeur,hauteur)));
		this.imgObjet = this.icoObjet.getImage();
	}

	public String quelleImage(int i, int j){
	String s ="";
	if(i == 43 && j == 65){
	s = "Image/tuyauRouge.png";
	}else if(i == 30 && j == 30){
	s = "Image/bloc.png";
	}
	return s;
	}
	

	public void move() {
		if(Main.fond.xPos>=0 ) {this.x = this.x - Main.fond.dx;}
	}
    public Image getImgObjet(){return this.imgObjet;}

    protected static void obstacle(LinkedList<Objet> l){
	Objet obj1_1 = new Objet(800,270,43,65);
	l.add(obj1_1);
	int i=0;
	int y=0;
	boolean c=true;
	while(c){
	    i=l.size()-1;
	    y=l.get(i).x;
	    int d=(int)(Math.random() * (650-600)) + 600;
	    if( d>=624){
		l.add(new Objet(y+d,270,43,65));
	    }
	    else{
		int h=(int)(Math.random()*(60-1))+1;
		l.add(new Objet(y+d,215-h,30,30));
	    }
		    
	    if(i==100){ c=false;}
	}
    }

	public boolean collisionArriere(Tir tir){
	if(this.x < tir.x + tir.largeur || this.x > tir.x+tir.largeur){
	return false;
	}
	return true;
	}


	public static class Nourritures extends Objet{
		protected Image imgNourriture;
		protected ImageIcon icoNourriture;
		public Nourritures(int x, int y){
		    super(x,y,27,30);
		    this.icoNourriture = new ImageIcon(getClass().getResource("Image/nourriture.jpg"));
		    this.imgNourriture = this.icoNourriture.getImage();
		}


		 public void move() {
		if(Main.fond.xPos>=0 ) {this.x = this.x - Main.fond.dx;}
		    }


	    public Image getImgNourriture(){return this.imgNourriture;}


		protected static void gagneVie(LinkedList<Nourritures> n){
		    Nourritures n1 = new Nourritures(3500,1800);
		    n.add(n1);
		    for(int i=0; i<10;i++){
			n.add(new Nourritures(n.get(i).x+2000,180));
		    }
		}
    }

}
