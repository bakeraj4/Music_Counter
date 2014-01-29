import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {	
	private static final String FileName="backlog.txt";
	private static ArrayList<String> TheBands=new ArrayList<String>();
	static ImageSearcher is;
	
	/*public static void main(String []args){
		//TODO if there is a 'backlog' use that and pass to the imageSearcher, other wise read from the file(s)
		Reader r=new Reader();
		is=new ImageSearcher(r.getRanking());
		is.findAll();
		//the above line will throw an exception if it cannot connect to the internet
		FrameImage fi=new FrameImage(is.getURLs());
		
		//TODO if there is no internet connection- there will be a java.net.UnkownHostException
			//due to the ajax.googleapis. So somehow store the list of bands to a file to latter
			//work on.
		
	}*/
	
	public static void main(String []agrs){
		try{//assumes that there's a file
			readFile();
		}catch(IOException e){
			Reader reader=new Reader();TheBands=new ArrayList<String>(reader.getRanking());//assuming tghis is a copy constructor
		}
		is=new ImageSearcher(TheBands);
		//try
		//is.findAll();
		//Frame Image
		//(if there was the internet error create the file and write the band names one per line to the file)

		//catch the internet error
		//save to the txt file

	}
	
	static void readFile()throws IOException{
		Scanner scanner=new Scanner(new FileInputStream(FileName));
		while(scanner.hasNext())
			TheBands.add(scanner.nextLine());
		scanner.close();
		File f=new File(FileName);
		f.delete();//removes the file from the directory
	}
}
