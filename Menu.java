import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Menu extends JPanel{

protected ImageIcon menu;
protected ImageIcon pause;
protected ImageIcon gameover;

public Menu(){
this.menu = new ImageIcon(getClass().getResource("Image/menu.png"));
this.pause = new ImageIcon(getClass().getResource("Image/pause.png"));
this.gameover = new ImageIcon(getClass().getResource("Image/gameover.png"));
setFocusable(true);
requestFocusInWindow();
addMouseListener(new Action(Main.fond));
}


public void paint(Graphics g){
if(Main.fond.etat == Main.fond.etat.MENU){
g.drawImage(this.menu.getImage(),0,0,null);
JLabel label1 = new JLabel();
JLabel label2 = new JLabel();
JLabel label3 = new JLabel();
label1.setIcon(new ImageIcon(getClass().getResource("Image/play.png")));
label2.setIcon(new ImageIcon(getClass().getResource("Image/quitter.png")));
label1.addMouseListener(new Action(Main.fond));
label2.addMouseListener(new Action(Main.fond));
this.add(label1);
this.add(label2);
}if(Main.fond.etat == Main.fond.etat.PAUSE){
g.drawImage(this.pause.getImage(),0,0,null);
JLabel label1 = new JLabel();
JLabel label2 = new JLabel();
JLabel label3 = new JLabel();
label1.setIcon(new ImageIcon(getClass().getResource("Image/resume.png")));
label2.setIcon(new ImageIcon(getClass().getResource("Image/replay.png")));
label3.setIcon(new ImageIcon(getClass().getResource("Image/quitter.png")));
label1.addMouseListener(new Action(Main.fond));
label2.addMouseListener(new Action(Main.fond));
label3.addMouseListener(new Action(Main.fond));
this.add(label1);
this.add(label2);
this.add(label3);
}if(Main.fond.etat == Main.fond.etat.PERDU){
g.drawImage(this.gameover.getImage(),0,0,null);
JLabel label1 = new JLabel();
JLabel label2 = new JLabel();
label1.setIcon(new ImageIcon(getClass().getResource("Image/replay.png")));
label2.setIcon(new ImageIcon(getClass().getResource("Image/quitter.png")));
label1.setLocation(360,400);
label2.setLocation(340,400);
label1.addMouseListener(new Action(Main.fond));
label2.addMouseListener(new Action(Main.fond));
this.add(label1);
this.add(label2);
}
}


}
