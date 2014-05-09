import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {	
	private static final String FileName="backlog.txt";
	private static ArrayList<String> TheBands=new ArrayList<String>();
	static ImageSearcher is;
	
	public static void main(String []agrs){
		try{//assumes that there's a file
			readFile();
		}catch(IOException e){
			Reader reader=new Reader();
			TheBands=new ArrayList<String>(reader.getRanking());//assuming this is a copy constructor
		}
		is=new ImageSearcher(TheBands);
		try{
			is.findAll();
			FrameImage fi= new FrameImage(is.getURLs());
		}catch(UnknownHostException e){//its not getting caught
			//there will be a java.net.UnkownHostException due to the ajax.googleapis not having internet.
			saveFile();
		}
	}
	
	private static void saveFile() {
		try{
			PrintWriter writer= new PrintWriter(FileName,"UTF-8");
			for(String str:TheBands)
				writer.write(str+"\n");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
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
