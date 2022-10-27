package jeu.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
// j'ai fais un package pour que ce soit + simple car il suffit d'import cette classe et le son marche
// pour l'instant j'ai mis que le son du saut, je sais pas si on met un son de fond ? 
// Y'a le son pour le tir mais comme il est pas encore apparent, j'attends avant de le mettre
public class Audio {
	private Clip clip;
	
	public Audio(String son) {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(son));
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (Exception e) {
			
		}
	}
	
	public Clip getClip() {
		return clip;
	}
	
	public void play() {
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}
	
	public static void playSound(String son) {
		Audio s = new Audio(son);
		s.play();
	}
}
