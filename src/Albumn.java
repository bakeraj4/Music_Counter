import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Albumn {
	private int totalPlays;
	private String creator;
	private String title;
	private ArrayList<Song> songs=new ArrayList<Song>();
	
	Albumn(){
		totalPlays=0;
		setTitle(null);
		setCreator(null);
	}
	
	Albumn(String name, String creat){
		totalPlays=0;
		setTitle(name);
		setCreator(creat);
	}
	
	void setTotalPlays(){
		totalPlays=0;//reset it
		for(Song s: songs)
			totalPlays+= s.getNumPlayed();
	}

	public int getTotalPlays() {
		return totalPlays;
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
		this.totalPlays+=s.getNumPlayed();
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
		str+=this.getTitle()+" has "+songs.size()+" many songs.\n\t\t";
		for(int i=0;i<songs.size();i++){
			if(i==songs.size()-1)
				str+=songs.get(i).getName()+"\n\t";
			else
				str+=songs.get(i).getName()+"\n\t\t";
		}
		return str;
	}
}
