import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Albumn {
	private String creator;
	private String title;
	private ArrayList<Song> songs=new ArrayList<Song>();
	
	Albumn(){
		setTitle(null);
		setCreator(null);
	}
	
	Albumn(String name, String creat){
		setTitle(name);
		setCreator(creat);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public ArrayList<Song> getSongs(){
		return songs;
	}
	
	public void addSong(Song s){
		songs.add(s);
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public int numSongs(){
		return songs.size();
	}
	
	public String toString(){
		String str="";
		str+=this.getTitle()+" has "+songs.size()+" many songs.";
		for(int i=0;i<songs.size();i++){
				str+="\n\t\t"+songs.get(i).getName();
		}
		return str+"\n";
	}
}
