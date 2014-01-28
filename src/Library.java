import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

/**
 * This class is used to manage the data about the music: the bands, ablums,
 * and songs.
 * @author Aaron
 *
 */
public class Library {
	private HashMap<String, ArrayList<Albumn>> myLib;
	private AlbumnComp comp =new AlbumnComp();
	
	/**
	 * A constructor that initializes the hashmp.
	 */
	Library(){
		myLib= new HashMap<String, ArrayList<Albumn>>();
	}
	
	/**
	 * This method checks if the container has the given band.
	 * @param band the band to look for
	 * @return true if the container has the band, false if it dosen't
	 */
	public boolean hasBand(String band){
		return myLib.containsKey(band);
	}
	
	/**
	 * This method checks if a given band has an album. It goes 
	 * through the albums for a given band and returns if it was
	 * found or not.
	 * @param band the given band
	 * @param alb the name of the album to find
	 * @return true if it was found, false if it wasn't
	 */
	public boolean hasAlbumn(String band, String alb){
		for(int i=0;i<myLib.get(band).size();i++){
			if(myLib.get(band).get(i).getTitle().equals(alb))
				return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the container has the song. It goes through
	 * the albums and checks if the correct album has the song. 
	 * @param band the name of the band
	 * @param alb the name of the album
	 * @param title the name of the song
	 * @return true if it is in the table, false if it cannot be found
	 */
	public boolean hasSong(String band, String alb, String title){
		for(int i=0;i<myLib.get(band).size();i++){//goes through the albumns
			if(myLib.get(band).get(i).getTitle().equals(alb)){//the correct albumn 
				return myLib.get(band).get(i).getSongs().contains(title);//if it contains the song
			}
		}
		return false;//not in there
	}
	
	/**
	 * This method adds a band to the table if it isn't already.
	 * @param band the name of the band.
	 */
	public void addBand(String band){
		if(!myLib.containsKey(band)){//only adds if the band isn't in the map
			myLib.put(band, new ArrayList<Albumn>());
		}
	}
	
	/**
	 * This method adds an album and then sorts it.
	 * @param band the name of the band of the album
	 * @param Alb the name of the album
	 */
	public void addAlb(String band, String Alb){
		myLib.get(band).add(new Albumn(Alb, band));
		Collections.sort(myLib.get(band),comp);
	}
	
	/**
	 * This method adds a song for a band. It checks if the band and 
	 * album exist and if the song isn't already there beforeadding it.
	 * @param band the name of the band
	 * @param alb the name of the album
	 * @param sng the song to be added
	 */
	public void addSong(String band, String alb,Song sng){
		for(int i=0;i<myLib.get(band).size();i++){//goes through all of a bands albumns
			if(myLib.get(band).get(i).getTitle().equals(alb)){//finds the albumn that the song is a part of
				if(!myLib.get(band).get(i).getSongs().contains(sng)){//checks that the song isn't already added
					myLib.get(band).get(i).addSong(sng);
				}
			}
		}
	}
	
	/**
	 * This method gets the number of albums for a given band.
	 * @param band the name of the band
	 * @return the number of albums
	 */
	public int albumnsForBand(String band){
		return myLib.get(band).size();
	}
	
	/**
	 * The method is used to represent the data in a string format
	 * that can be easily read.
	 */
	public String toString(){
		String str="";
		Set<String> myBands=myLib.keySet();
		
		for(String st: myBands){
			str+=st+" has "+myLib.get(st).size()+" many albumns.";
			for(Albumn alb: myLib.get(st)){
				str+="\n\t"+alb.toString();
			}
		}
		return str;
	}
	
	/**
	 * This class is used to sort the albums.
	 * @author Aaron
	 *
	 */
	private class AlbumnComp implements Comparator<Albumn>{
		@Override
		/**
		 * The sort compares the number of songs in the album and uses the
		 * name as a tie breaker.
		 */
		public int compare(Albumn arg0, Albumn arg1) {
			if(arg0.getSongs().size()<arg1.getSongs().size())
				return 1;
			else if(arg0.getSongs().size()==arg1.getSongs().size())
				return ((arg0.getTitle().compareToIgnoreCase(arg1.getTitle())>0)?1:-1);
			return -1;
		}
		
	}
	
	/**
	 * This method creates a copy of the list of band names and then sorts
	 * them based on the ranking. 
	 * @return the 'sorted' names
	 */
	public ArrayList<String> rankings(){
		ArrayList<String> ret= new ArrayList<String>(myLib.keySet());
		for(int i=ret.size()-1;i>0;i--){
			for(int j=i;j>0;j--){
				if(importance(ret.get(j))>importance(ret.get(j-1))){//swap
					String tmp= ret.get(j);
					ret.set(j, ret.get(j-1));
					ret.set(j-1, tmp);
				}
			}
		}
		return ret;
	}

	/**
	 * This method gives a score to the bands so they can be ranked. The
	 * score is number of albums multiplied by the total number of songs.
	 * @param string the band's name
	 * @return a score
	 */
	private int importance(String string){
		int numSongs=0;
		for(Albumn alb: myLib.get(string))
			numSongs+=alb.numSongs();
		return myLib.get(string).size()*numSongs;
	}
}
