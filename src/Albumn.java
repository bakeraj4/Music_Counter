import java.util.ArrayList;


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
	
	public String toString(boolean band){
		String str="";
		if(band)
			str+=this.creator+"\n\t";
		for(Song s: songs)
			str+=s.getCreator()+"\n\t";
		return str;
	}
	
	//TODO to string(boolean)
		//albumn name number of songs//if true <band's name> albumn name nuber of songs
			//go through each song for the song tile
	
	/**
	 * TODO - make a copmarator such that the albumns are sorted by the number of plays, tie breaker albumn with a single track
	 * that is the highest, if tie again then second most .... if tie on last song the by alpha by albumn
	 */
}
