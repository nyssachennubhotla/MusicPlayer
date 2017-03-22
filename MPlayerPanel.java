//Nyssa Chennubhotla 
//MPlayerPanel = main class for Project4 
//Class contains code for Interface 
//Also contains Buttons for interface and these methods make calls to methods in Load Class 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import java.io.BufferedReader;
import java.io.FileReader;


public class MPlayerPanel extends JPanel {
	
	private JButton playButton, stopButton, exitButton, loadMp3Button, saveButton, openButton;
	private JTable table = null;
	PlayerThread soundToPlay = null;
	
	MPlayerPanel() {
	
	this.setLayout(new BorderLayout());
	
	// buttonPanelTop contains the top row of buttons:
	// load mp3 files, save and open
	JPanel buttonPanelTop = new JPanel();
	buttonPanelTop.setLayout(new GridLayout(1,3));
	loadMp3Button = new JButton("Load mp3");
	saveButton = new JButton("Save Library");
	openButton = new JButton("Load Library");

	loadMp3Button.addActionListener(new MyButtonListener());
	saveButton.addActionListener(new MyButtonListener());
	openButton.addActionListener(new MyButtonListener());

	buttonPanelTop.add(loadMp3Button);
	buttonPanelTop.add(saveButton);
	buttonPanelTop.add(openButton);
	this.add(buttonPanelTop, BorderLayout.NORTH);
		
	
	// Bottom panel with panels: Play, Stop, Exit buttons
	JPanel buttonPanelBottom = new JPanel();
	buttonPanelBottom.setLayout(new GridLayout(1,3));
	playButton = new JButton("Play");
	stopButton = new JButton("Stop");
	exitButton = new JButton("Exit");

	playButton.addActionListener(new MyButtonListener());
	stopButton.addActionListener(new MyButtonListener());
	exitButton.addActionListener(new MyButtonListener());

	buttonPanelBottom.add(playButton);
	buttonPanelBottom.add(stopButton);
	buttonPanelBottom.add(exitButton);
	this.add(buttonPanelBottom, BorderLayout.SOUTH);
	setBackground(Color.pink); 
	
}
	class MyButtonListener implements ActionListener {
		

		public void actionPerformed(ActionEvent e) {
			// The function that does something whenever each
			// button is pressed
			ArrayList <Song> songList = new ArrayList<Song>(); 
			
			
			try{
				Load newLoad = new Load();
				//newLoad.sort(songList);
				songList = newLoad.startLoad(new File("/Users/NyssaChennubhotla/Desktop/Music"));
			}catch (Exception e1){
				e1.printStackTrace();
			}
			if (e.getSource() == loadMp3Button) {
				System.out.println("Load mp3 button");
				
				// FILL IN CODE - make the calls to other methods/classes, do NOT place all the code here
				
				// 1) read all the mp3 files from mp3 directory
				// 2) extract tags and load songs into the array of songs
				// 3) Create a JTable from the songs and add it to the panel
				// Replace the code below with the actual song data: 
				// right now it is hard coded and just demonstrates how to use JTable
				
				// make an object of type
					int i = songList.size();
					String[][] tableElems = new String[songList.size()][2];
					String[] columnNames = {"Title", "Artist"};
					
					for (i = 0; i < songList.size(); i++){
						Song songtemp = songList.get(i); 
						tableElems[i][0] = songList.get(i).getTitle();
						tableElems[i][1] = songList.get(i).getArtist();
						
					}
					
					table = new JTable(tableElems, columnNames );
					JScrollPane scrollPane = new JScrollPane( table );
					add( scrollPane, BorderLayout.CENTER );
					 
					updateUI();
			
			}
			else if (e.getSource() == saveButton) {
				//saveButton should get from arraylist and save 
				// FILL IN CODE :  make the calls to other methods/classes, do NOT place all the code here
				// save the song catalog into a file called "songCatalog". 
				// The format is described in the handout.
				
				
				JTextArea a = new JTextArea(); 
				for (int i = 0; i < songList.size(); i++)
					 a.append(songList.get(i).toString()); 
				
				JPanel view = new JPanel();
				view.add(a);
				JScrollPane scrollPane = new JScrollPane(view); 
				add(scrollPane, BorderLayout.CENTER); 
				setBackground(Color.pink);
				updateUI();
			}
			else if (e.getSource() == openButton) {
				// FILL IN  CODE:  make the calls to other methods/classes, do NOT place all the code here
				// open the file songCatalog and load songs
				// into the arraylist of songs
				JTextArea textArea = new JTextArea();
				BufferedReader br = null; 
; 				Song sg = new Song(); 
				ArrayList <Song> slist = new ArrayList<Song>();
				File file = (new File("/Users/NyssaChennubhotla/Desktop/nchennubhotla/nchennubhotla/cs112/Project4/project4/test.txt")); 
				String value = null; 
				try {
					br = new BufferedReader(new FileReader(file));
					while((value = br.readLine()) != null){
						String a = sg.album = br.readLine(); 
						//System.out.println(a);
						String b=  sg.artist = br.readLine(); 
						//System.out.println(b);
						 String c = sg.path = br.readLine(); 
						//System.out.println(c);
						String d = sg.title = br.readLine(); 
						//System.out.println(d);
						 slist.add(sg); 
						 textArea.append(a);
						 textArea.append(b);
						 textArea.append(c);
						 textArea.append(d);
						 
					}
				} catch(IOException e1){
					e1.printStackTrace(); 
				}

				JPanel view = new JPanel();
				view.add(textArea);
				JScrollPane scrollPane = new JScrollPane(view); 
				textArea.setEditable(false);
				add(scrollPane); 
				setBackground(Color.pink);
				updateUI();
			}
				// FILL IN CODE: make the calls to other methods/classes, do NOT place all the code here
				// find the selected song in the arraylist of songs
				// create a thread to play it as described in the handout.
				
				else if (e.getSource() == playButton) {
					System.out.println("Play button");
					int selectedSong = table.getSelectedRow();
					String songPath = songList.get(selectedSong).getPath();
					soundToPlay = new PlayerThread(songPath); 
					soundToPlay.start();
					System.out.println("Play button done");
				}
				else if (e.getSource() == stopButton) {
					// FILL IN CODE: make the calls to other methods/classes, do NOT place all the code here
					// stop the current running thread
					int selectedSong = table.getSelectedRow();
					String songPath = songList.get(selectedSong).getPath();
					soundToPlay.getPlayer().close(); 
				}
			else if (e.getSource() == exitButton) {
				// Exit the program
				System.exit(0);
			}
		}
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame ("Mp3 player");
	      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	      MPlayerPanel panel  = new MPlayerPanel();
	      panel.setPreferredSize(new Dimension(400,400));
	      frame.getContentPane().add (panel);
	      frame.pack();
	      frame.setVisible(true);
	      
	}



	
	
	
}