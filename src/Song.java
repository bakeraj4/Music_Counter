/**
 * @author Aaron
 *
 */
public class Song{
	private int numPlayed;
	private String creator;
	private String name;
	
	Song(){
		setNumPlayed(0);
		setCreator(null);
		setName(null);
	}
	
	Song(int num, String creat, String title){
		setNumPlayed(num);
		setCreator(creat);
		setName(title);
	}

	public int getNumPlayed() {
		return numPlayed;
	}

	public void setNumPlayed(int numPlayed) {
		this.numPlayed = numPlayed;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return this.creator+" wrote "+this.name+" and has been played "+this.numPlayed+" many times.";
	}
}
