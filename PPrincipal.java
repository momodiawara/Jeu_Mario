import java.awt.*;
import javax.swing.*;

public class PPrincipal extends Personnage {
    protected Image imgp;
    protected ImageIcon icop;
    protected boolean saut,tir;
    protected static int vie=100000;
    protected static int vitessey=0;
    protected static int g=1;
  
    
    public Image getImage(){return this.imgp;}
	
	
    public PPrincipal(int x,int y) {
	super(x,y,48,25);
	icop= new ImageIcon("Image/persoMarcheDroite.png");
	imgp=icop.getImage();
	this.saut = false;
	this.tir = false;
    }
	
    public boolean isSaut(){return this.saut;}
    public void setY(int y){super.ord=y;}
    
    @Override
    public Image deplacement(String s, int b){  //redefinition du deplacement specifique a notre personnage different des ennemis
    	// b est la frequence
    	String str;
    	ImageIcon  ico;
    	Image img;
    	    
    	if(this.marche==false || Main.fond.xPos<=0) {// personnage arreter et a gauche de l'ecran
    	    if(this.versDroite==true){str="Image/"+ s +"ArretDroite.png";}
    	    else{str="Image/"+ s +"ArretGauche.png";}
    	}
    	else{
    	    this.compteur+=1;
    	    if(compteur/b==0){
    		if(this.versDroite == true){str = "Image/" + s + "ArretDroite.png";}
    		else{str = "Image/" + s + "ArretGauche.png";}
    	    }
	    else {
		if(this.versDroite == true) str = "Image/" + s + "MarcheDroite.png";
    		else { str = "Image/" + s + "MarcheGauche.png";}
    		if(this.compteur ==2 * b){this.compteur = 0;}
    	    }
    	}
    	ico = new ImageIcon(getClass().getResource(str));
    	img = ico.getImage();
    	return img;
    }



    public Image saute(){
    	ImageIcon ico;
    	Image img;
    	String s;
	this.vitessey +=g;
	//Lorsque le perso monte
	if(this.vitessey <= 40){ 
	    if(this.ord > Main.fond.hauteurPlafond){ //this.ord = sommet de la tete du perso si ord = hauteurplafond c'est que sa tete touche le plafond
		this.ord -= 4; //montee de mario
	    }
	    else{this.vitessey = 41;}
	    if(this.versDroite) s = "Image/persoMarcheDroite.png";
	    // vu qu'on peut sauter vers la droite
       	    else{s = "Image/persoMarcheGauche.png"; }// idem vers la gauche
        	    //retour au sol du perso
	}
	else if(this.ord + this.taille < Main.fond.ySol){
	     this.ord += 1;   // on descend moins vite que la montee 
            if(this.versDroite){s = "Image/persoMarcheDroite.png";}
	    else{s = "Image/persoMarcheGauche.png";}
        }else{
	    if(this.versDroite){s = "Image/persoArretDroite.png";}
	    else{s = "Image/persoArretGauche.png";}
	    this.saut = false;
	    this.vitessey =0 ;
        }
	ico = new ImageIcon(getClass().getResource(s));
	img = ico.getImage();
	return img;	
	
    }
    
    public void contact( Object ob){
		if( ob instanceof Objet){
		    Objet o= (Objet)ob;
		    if( (super.collisionAvant(o) && this.versDroite) || (super.collisionArrier(o) && !this.isVersDroite())){
			Main.fond.dx = 0;
			this.marche = false;
		    }
		    if(super.collisionDessous(o) && saut){
			// si le personnage saute le sol devient la hauteur de l'objet
			Main.fond.ySol = o.y;
		    }
		    else if(!super.collisionDessous(o)){
			Main.fond.ySol = 293;
			if(!this.saut){this.ord = 275;}
		    }
		    if( super.collisionDessus(o)){
			Main.fond.hauteurPlafond = o.y+o.hauteur;
		    }
		    else if (!super.collisionDessus(o) && this.saut==false){
			Main.fond.hauteurPlafond = 0;
		    }
		}
		if( ob instanceof Ennemi1){
		    Ennemi1 d= (Ennemi1)ob;
		    if( super.collisionAvant(d) || super.collisionArrier(d) || super.collisionDessus(d)){
			if(!d.b){
			vie = vie - 1;
			}else if(d.b){
			vie = vie - 3;
			}
			Fond.Vie.setValue(vie);
			if(vie<=0){
			    this.marche = false;
			    this.estVivant = false;
			}
		    }
		}
    }

	public boolean contactN( Objet.Nourritures c){
	int r = (int)Math.random() * (2 - 0);
	if( super.collisionAvant(c) || super.collisionArrier(c) || super.collisionDessous(c) || super.collisionDessus(c)  && this.estVivant) {
	    if(r == 0){
	    vie+=5000;
	    Fond.Vie.setValue(vie);
	     }else if (r == 1){
		 vie-=5000;
	    Fond.Vie.setValue(vie);
		}
	    return true;
	}
	return false;
    }

   public Image meurt(){
    	ImageIcon ico;
    	Image img;
    	String s;
	if(this.versDroite) s = "Image/persoArretDroite.png";
	else{s = "Image/persoArretGauche.png";}
	vitessey++;
	if(vitessey>100) this.setY(vitessey-1); 
	ico = new ImageIcon(getClass().getResource(s));
	img = ico.getImage();
	return img;
     }

}
