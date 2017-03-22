import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javazoom.jl.player.advanced.*;
public class JLayerTest {
	public static void main(String[] args) throws FileNotFoundException{
		ArrayList<Song> sList = new ArrayList<Song>(); 
		Song a = new Song(); 
		Song b = new Song(); 
		Song c = new Song(); 
		a.setTitle("Yrookelyn"); 
		b.setTitle("ALOVE");
		c.setTitle("Brooklyn");
		sList.add(a);
		sList.add(b);
		sList.add(c);
		System.out.println(sList);
		Load newL = new Load(); 
		newL.sort(sList);
		
		
	}
}
