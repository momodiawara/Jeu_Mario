public class Score{
protected int score;
protected int highScore;

public Score(){
 this.score=0;
 //this.highScore = Main.fond.score.score;
}

public void highScore(){
if(Main.fond.score.score <= Main.fond.score.highScore){
Main.fond.score.highScore = Main.fond.score.highScore ;
}if(Main.fond.score.score > Main.fond.score.highScore){
Main.fond.score.highScore = Main.fond.score.score;
}
}

public int getScore(){
return this.score;
}

public void setScorePlus(int i){
	this.score +=i;
}

public void setScoreMoins(int i){
	this.score -=i;
}
}
