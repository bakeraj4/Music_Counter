/**
 * @author Aaron
 *
 */
public class Song{
	private String creator;
	private String name;
	
	Song(){
		setCreator(null);
		setName(null);
	}
	
	Song(String creat, String title){
		setCreator(creat);
		setName(title);
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
		return this.creator+" wrote "+this.name+".";
	}
}
