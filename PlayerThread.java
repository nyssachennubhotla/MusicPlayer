//Nyssa Chennubhotla 
//Project 4- PlayerThread 
//uses another JAR jlayer to use in start method and stop method 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.player.Player; 
import javazoom.jl.decoder.JavaLayerException;

class PlayerThread extends Thread {
	Player pl;
	PlayerThread(String filename) {
		FileInputStream file;
		try {
			// filename here contains mp3 you want to play
			file = new FileInputStream(filename);
			pl = new Player(file);

		} catch (FileNotFoundException e) {
			e.getMessage();
		} 
		catch (JavaLayerException e) {
			e.getMessage();
		}

	}
	
	public Player getPlayer(){
		return pl; 
	}
	
	public void run() {
		try {
			pl.play();
		}
		catch(Exception e) {
			e.getMessage();
		}
	} 
}  