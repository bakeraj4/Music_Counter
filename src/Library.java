import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;


public class Library {
	private HashMap<String, ArrayList<Albumn>> myLib;
	private AlbumnComp comp =new AlbumnComp();
	
	Library(){
		myLib= new HashMap<String, ArrayList<Albumn>>();
	}
	
	public boolean hasBand(String band){
		return myLib.containsKey(band);
	}
	
	public boolean hasAlbumn(String band, String alb){
		for(int i=0;i<myLib.get(band).size();i++){
			if(myLib.get(band).get(i).getTitle().equals(alb))
				return true;
		}
		return false;
	}
	
	public boolean hasSong(String band, String alb, String title){
		for(int i=0;i<myLib.get(band).size();i++){//goes through the albumns
			if(myLib.get(band).get(i).getTitle().equals(alb)){//the correct albumn 
				return myLib.get(band).get(i).getSongs().contains(title);//if it contains the song
			}
		}
		return false;//not in there
	}
	
	public void addBand(String band){
		if(!myLib.containsKey(band)){//only adds if the band isn't in the map
			myLib.put(band, new ArrayList<Albumn>());
		}
	}
	
	public void addAlb(String band, String Alb){
		myLib.get(band).add(new Albumn(Alb, band));
		Collections.sort(myLib.get(band),comp);
	}
	
	public void addSong(String band, String alb,Song sng){
		for(int i=0;i<myLib.get(band).size();i++){//goes through all of a bands albumns
			if(myLib.get(band).get(i).getTitle().equals(alb)){//finds the albumn that the song is a part of
				if(!myLib.get(band).get(i).getSongs().contains(sng)){//checks that the song isn't already added
					myLib.get(band).get(i).addSong(sng);
				}
			}
		}
	}
	
	public int albumnsForBand(String band){
		return myLib.get(band).size();
	}
	
	
	public String toString(){
		String str="";
		Set<String> myBands=myLib.keySet();
		
		for(String st: myBands){
			str+=st+" has "+myLib.get(st).size()+" many albumns.\n\t";
			for(Albumn alb: myLib.get(st)){
				str+=alb.toString();//+"\n";
			}
		}
		
		return str;
	}
	
	private class AlbumnComp implements Comparator<Albumn>{
		@Override
		public int compare(Albumn arg0, Albumn arg1) {
			if(arg0.getSongs().size()<arg1.getSongs().size())
				return 1;
			else if(arg0.getSongs().size()==arg1.getSongs().size())
				return ((arg0.getTitle().compareToIgnoreCase(arg1.getTitle())>0)?1:-1);
			return -1;
		}
		
	}
	
	/**
	 * TODO - make a copmarator such that the albumns are sorted by the number of plays, tie breaker albumn with a single track
	 * that is the highest, if tie again then second most .... if tie on last song the by alpha by albumn
	 */
}
