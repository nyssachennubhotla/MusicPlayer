import java.util.ArrayList;

public class Song  {
	protected String artist; 
	protected String title; 
	protected String album; 
	protected String path;
	protected static int num = 0; 
	
	public Song(){
		num++;  
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getNum(){
		return num; 
	}

	@Override
	public String toString() {
		return "Number of songs" + " " + num + "\n" +  artist + "\n" +  title + "\n" + album + "\n" + path;
	}
	public int compareTo(String key) {
		String inputTitle = key;  
		System.out.println("inputTitle is" + inputTitle);
		String currentTitle = this.getTitle(); 
		System.out.println("currentTitle is" + currentTitle);
		if (currentTitle.compareTo(inputTitle) > 0){
			return 1; 
		}
		else if (currentTitle.compareTo(inputTitle) < 0){
			return -1; 
		}
		return 0;
	}




	


	
	
	
	
	
}
