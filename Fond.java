import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
@SuppressWarnings("serial")
public class Fond extends JPanel{
	
    protected ImageIcon iconeFond;
    protected Image imgFond;
    protected Image imgFond2;
    protected int xFond1;
    protected int xFond2;
    protected int dx;
    protected int xPos;
    protected int ySol; //hauteur sol
    protected int hauteurPlafond; //hauteur du plafond courant
    protected int c = 0;
    protected static enum ETAT{
	MENU,
	JEU,
	PAUSE,
	PERDU
    };
    
    protected static ETAT etat = ETAT.JEU;
    protected PPrincipal perso;
    protected LinkedList<Objet> listObjet= new LinkedList<Objet>();
    protected LinkedList<Ennemi1> listEnnemi= new LinkedList<Ennemi1>();
    protected LinkedList<Ennemi1> listEnnemi2= new LinkedList<Ennemi1>(); 
    protected LinkedList<Tir> listeTir = new LinkedList<Tir>();
    protected LinkedList<Objet.Nourritures> listNourritures= new LinkedList<Objet.Nourritures>();
    protected Tir tir ;
    protected static Score score;   
    protected Font police;
    protected Menu menu;
    protected static JProgressBar Vie;
    protected static JProgressBar Vie2;
    protected static boolean estTir;        


    public Fond() {
	super();
	this.xFond1 = -50;
	this.xFond2 = 750;
	this.dx = 0;
	this.xPos = -1;
	this.ySol = 293;
	this.hauteurPlafond = 0;
	this.score = new Score();
	iconeFond = new ImageIcon(getClass().getResource("Image/fond.png"));
	this.imgFond = this.iconeFond.getImage();
	this.imgFond2 = this.iconeFond.getImage();
	this.perso = new PPrincipal(300,275);
	this.tir = new Tir(this.perso.ord,300);
	estTir = false;
	Objet.obstacle(listObjet);
	Ennemi1.ennemis(listEnnemi);
	Ennemi1.ennemis2(listEnnemi2);
	Objet.Nourritures.gagneVie(listNourritures);
	this.setFocusable(true);
	this.requestFocusInWindow();
	this.addKeyListener(new Action(this));
	this.police = new Font("Arial",Font.BOLD, 15);

	Vie = new JProgressBar(0,100000);
	Vie.setPreferredSize(new Dimension(200,30));
	Vie.setBackground(Color.red);
	Vie.setForeground(Color.green);
	Vie.setValue(100000);
	this.add(Vie);

	javax.swing.Timer t = new javax.swing.Timer(3, new ActionListener() {
          public void actionPerformed(ActionEvent e) {
	        Main.fond.repaint();
		c+=1;
          }
	    });
	t.start();
	
    }
	
    public void paintComponent(Graphics g) {

	super.paintComponent(g);
	Graphics g2 = g;
	if(this.etat == etat.JEU){
	for ( int i = 0; i<listObjet.size();i++){
	    if(perso.proche(listObjet.get(i))){ 
		perso.contact(listObjet.get(i));
		}
	    for( int j=0;j<listEnnemi.size();j++){
		if(listEnnemi.get(j).proche(listObjet.get(i))){
		 listEnnemi.get(j).contact(listObjet.get(i));
		}
		if(perso.proche(listEnnemi.get(j))){
		 perso.contact(listEnnemi.get(j));
		}
		if(listEnnemi.get(j).estVivant==false){
	 listEnnemi.remove(listEnnemi.get(j));
		}	
	    }
	    for( int j=0;j<listEnnemi2.size();j++){
		if(listEnnemi2.get(j).proche(listObjet.get(i))){
		 listEnnemi2.get(j).contact(listObjet.get(i));
		}
		if(perso.proche(listEnnemi2.get(j))){
		 perso.contact(listEnnemi2.get(j));
		}
		if(listEnnemi2.get(j).estVivant==false){
		 listEnnemi2.remove(listEnnemi2.get(j));
		}
	    }		
	}

	for( int j=0;j<listNourritures.size();j++){
	    if(perso.proche(listNourritures.get(j))){
		if(perso.contactN(listNourritures.get(j))){
	 	listNourritures.remove(listNourritures.get(j));	
		}
	   }
   	 }


	for ( int i = 0; i<listObjet.size();i++){ 
	listObjet.get(i).move();
	}


	for ( int i = 0; i<listEnnemi.size();i++){
	    if(c%2==0){
		listEnnemi.get(i).bouge();
	}
	}
	for ( int i = 0; i<listEnnemi2.size();i++){
	    if(c%2==0){
		listEnnemi2.get(i).bouge();
	}
	}	
	for ( int i = 0; i<listNourritures.size();i++){
	listNourritures.get(i).move();
	}

	this.movingFond();

	JLabel label = new JLabel();
	g2.drawImage(this.imgFond,this.xFond1,0,null);
	g2.drawImage(this.imgFond2, this.xFond2,0,null);
	label.setIcon(new ImageIcon(getClass().getResource("Image/pause2.png")));
	label.setLocation(700,150);
	label.setSize(15,15);
	label.addMouseListener(new Action(this));
	this.add(label);
	if(estTir == true){
			for(int a =0 ; a<listeTir.size();a++){
			tir = listeTir.get(a);
			tir.affiche(g);
			if(listeTir.size() > 0){
				for(int j = 0; j<listObjet.size();j++){
				for(int i = 0; i < listeTir.size();i++){
					if(listeTir.size() >0 && listeTir.get(i).collisionAvant(listObjet.get(j)) ){
						System.out.println("Collision Objet Avant "+listeTir.get(i).collisionAvant(listObjet.get(j)));
						listeTir.remove(listeTir.get(i));
					}else if (listeTir.size() >0 && listeTir.get(i).collisionArriere(listObjet.get(j)) ){
						System.out.println("Collision Objet Arriere "+listeTir.get(i).collisionArriere(listObjet.get(j)));
						listeTir.remove(listeTir.get(i));
					}	
				break;
				}
				}
			for(int l = 0;l<listEnnemi.size();l++){
			for(int i = 0;i<listeTir.size();i++){
				if( listeTir.size() >0 && listeTir.get(i).collisionAvant(listEnnemi.get(l)) ){
					System.out.println("Collision Ennemi1 Avant "+listeTir.get(i).collisionAvant(listEnnemi.get(l)) );
					listeTir.get(i).collisionObjet(listEnnemi.get(l));
					listeTir.remove(listeTir.get(i));
				}
				else if(listeTir.size() >0 && listeTir.get(i).collisionArriere(listEnnemi.get(l)) ){
					System.out.println("Collision Ennemi1 Arriere "+listeTir.get(i).collisionArriere(listEnnemi.get(l)) );
					listeTir.get(i).collisionObjet(listEnnemi.get(l));
				}
				break;
		}
			for(int m = 0;m<listEnnemi2.size();m++){
			for (int i = 0;i<listeTir.size();i++){
				if( listeTir.size() >0 && listeTir.get(i).collisionAvant(listEnnemi2.get(m)) ){
					System.out.println("Collision Ennemi2 Avant "+listeTir.get(i).collisionAvant(listEnnemi2.get(m)));
					listeTir.get(i).collisionObjet(listEnnemi2.get(m));
					listeTir.remove(listeTir.get(i));
				}else if(listeTir.size() >0 && listeTir.get(i).collisionArriere(listEnnemi2.get(m))){
					System.out.println("Collision Ennemi2 Arriere "+listeTir.get(i).collisionArriere(listEnnemi2.get(m)));
					listeTir.get(i).collisionObjet(listEnnemi2.get(m));
					listeTir.remove(listeTir.get(i));
				}
			break;
			}
			}
			if(listeTir.size() >0 && listeTir.get(a).collisionEcran()){
			listeTir.remove(listeTir.get(a));
			}
			break;
			}
		}
	}
	}



	for (int i = 0; i<listObjet.size();i++){g2.drawImage(listObjet.get(i).getImgObjet(),listObjet.get(i).x,listObjet.get(i).y,null);}
	for (int i = 0; i<listNourritures.size();i++){g2.drawImage(listNourritures.get(i).getImgNourriture(),listNourritures.get(i).x,listNourritures.get(i).y,null);}

	for (int i = 0; i<listEnnemi.size();i++){
	    if(listEnnemi.get(i).estVivant){ g2.drawImage(listEnnemi.get(i).deplacement("ennemi",45),listEnnemi.get(i).absc,listEnnemi.get(i).ord,null);}
	    else{g2.drawImage(null,listEnnemi.get(i).absc,listEnnemi.get(i).ord+20,null);}
	}
	for (int i = 0; i<listEnnemi2.size();i++){
	    if(listEnnemi2.get(i).estVivant){
	    g2.drawImage(listEnnemi2.get(i).deplacement("ennemi2",45),listEnnemi2.get(i).absc,listEnnemi2.get(i).ord,null);
	    }
	    else{g2.drawImage(null,listEnnemi2.get(i).absc,listEnnemi2.get(i).ord+20,null);}
	}
	if(perso.estVivant){	
	if(this.perso.isSaut()){g2.drawImage(this.perso.saute(),this.perso.absc,this.perso.ord,null);}
	else{g2.drawImage(this.perso.deplacement("perso",30),this.perso.absc,this.perso.ord,null);}
	}else{
	g2.drawImage(this.perso.meurt(),this.perso.absc,this.perso.ord,null);
	}
	
	for (int i=0;i<listEnnemi.size();i++){listEnnemi.get(i).move();}
	for (int i=0;i<listEnnemi2.size();i++){listEnnemi2.get(i).move();}
	g2.setFont(police);
	g2.drawString("Score : "+score.score,5,15);
	Font t= new Font("Arial", Font.BOLD,50);
	g2.setFont(t);
	this.score.highScore();
	if(Partie1()){
	g2.drawString("PARTIE TERMINEE ! ", 100,160);
	g2.drawString("Highscore " + this.score.highScore, 50,100);
	}
	}else if(this.etat == etat.MENU){
	this.menu.paint(g2);
	}else if(this.etat == etat.PERDU){
	this.menu.paint(g2);
	}else if(this.etat == etat.PAUSE){
	this.menu.paint(g2);
	}
	
    }



    public void movingFond() {
	if(this.xPos >= 0 ) {
	    this.xPos = this.xPos + this.dx;		
	    this.xFond1 = this.xFond1 - this.dx;
	    this.xFond2 = this.xFond2 - this.dx;
	}
	if(this.xFond1 == -800){this.xFond1 = 800;}
	else if (this.xFond2 == -800){this.xFond2 = 800;}
	else if (this.xFond1 == 800){this.xFond1 = -800;}
	else if (this.xFond2 == 800){this.xFond2 = -800;}
    }

     public boolean Partie1(){
	return (perso.vie<=0);
	
    }

}
