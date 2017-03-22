//Nyssa Chennubhotla 
//Project 4 - Song class
//this class uses the jaudiotagger jar to extract tags from mp3 flyers 
//this class also has a method with recursion to look for mp3 files in directory 
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException; 

public class Load  {
	
	ArrayList <Song> list = new ArrayList <Song>();
	
	public ArrayList <Song> startLoad(File inputPath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
		traverseDirectory(inputPath);
		return list; 
	}
		public void traverseDirectory(File inputFile) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
			if (inputFile.isFile() == true){
				if (inputFile.getAbsolutePath().endsWith(".mp3")){
					System.out.println("Found mp3 " + inputFile);
					this.audioTag(inputFile); 
				}
			}
			
			if (inputFile.isDirectory() == true){
				File [] musicFiles = inputFile.listFiles(); 
				for (int i = 0; i < musicFiles.length; i++){
					traverseDirectory(musicFiles[i]);
				}
			}
		}
		public void audioTag(File inputPath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
				if (inputPath.getAbsolutePath().endsWith(".mp3")){
					AudioFile f = AudioFileIO.read(inputPath);
					Tag tag = f.getTag();
					String artist = tag.getFirst(FieldKey.ARTIST);
					String album = tag.getFirst(FieldKey.ALBUM);
					String title = tag.getFirst(FieldKey.TITLE);
					String workingDir = System.getProperty("user.dir");
					String path = (inputPath.getAbsolutePath());
					Song s = new Song();
					s.getNum();
					s.setArtist(artist);
					s.setAlbum(album); 
					s.setTitle(title);
					s.setPath(path);
					list.add(s);
					PrintWriter writer = new PrintWriter("test.txt"); 
					for (Song str: list){
						writer.println(str);
					}
					writer.close();
				}
					
		}
		public static void save(String fileName){
			String file = ("/Users/NyssaChennubhotla/Desktop/nchennubhotla/nchennubhotla/cs112/Project4/project4/test.txt"); 
			BufferedReader br = null; 
			ArrayList <Song> slist = new ArrayList<Song>();
			Song sg = new Song(); 
			String value = null; 
			try {
				br = new BufferedReader(new FileReader(file));
				while((value = br.readLine()) != null){
					String a = sg.album = br.readLine(); 
//					System.out.println(a);
					String b=  sg.artist = br.readLine(); 
//					System.out.println(b);
					 String c = sg.path = br.readLine(); 
//					System.out.println(c);
					String d = sg.title = br.readLine(); 
					 slist.add(sg); 
				}
			} catch(IOException e){
				e.printStackTrace(); 
			}
			
		}

	public static ArrayList<Song> sort(ArrayList<Song> arr){
			System.out.println(arr);
			String key;  
			for (int i = 1; i < arr.size(); i++){
				int x = i -1; 
				key = arr.get(i).getTitle(); 
				System.out.println("key is" + key);
				int j = i; 
				j = i - 1; 
				while (j >= 0){
					 if(arr.get(j).compareTo(key) > 0 ){
						break; 
					}
					 i = j;
					i--;
				}
				key = arr.get(x).getTitle();
				System.out.println("second key" + key);
				
			}
			return sort(arr);
			
		}
	
	}
		
	

			
		
	

