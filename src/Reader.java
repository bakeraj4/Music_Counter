import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Reader {

	public CONSTANTS consts=CONSTANTS.getInstance();
	private FileInputStream fis;
	private Library myLib=new Library();
	
	private static final String defaultDirectory="F:\\music";
	private static final String backupDirectory="C:\\Users\\Aaron\\Dropbox\\Avenged Sevenfold";
	
	Reader(){
		//TODO get the users input for a directory that they would like to use
		String rootDirectory=defaultDirectory;
		
		//get user input of the location
		//check that it makes sense
		//set rootDirectory
		
		try {
			findFiles(new File(rootDirectory));
			System.out.println(myLib.toString());
		} catch (Exception e) {//has an error getting to the folder on the hard disk
			try {
				rootDirectory=backupDirectory;
				findFiles(new File(rootDirectory));
				System.out.println(myLib.toString());
			} catch (Exception ee) {
			}
		}
	}
	
	private void findFiles(File root) throws IOException {
		File[] filesHere=root.listFiles();
		for(File fil: filesHere){
			if(fil.isDirectory() && fil.getCanonicalPath().equals(fil.getAbsolutePath())){
				findFiles(fil);
			}
			else if(fil.canRead() && fil.isFile() && isMusicFile(fil)){
				fis =new FileInputStream(fil);
				int size=(int)fil.length();
				fis.skip(size-CONSTANTS.BYTE_128);
				byte[] chunk=new byte[CONSTANTS.BYTE_128];
				fis.read(chunk);
				String id3 =new String(chunk);
	            String tag = id3.substring(CONSTANTS.OFFSET_TAG[CONSTANTS.FROM], CONSTANTS.OFFSET_TAG[CONSTANTS.TO]);
	            if(tag.equals(CONSTANTS.ID3_TAG)) {
	                String mTitle = (id3.substring(CONSTANTS.OFFSET_TITLE[CONSTANTS.FROM], CONSTANTS.OFFSET_TITLE[CONSTANTS.TO])).trim();
	                String mArtist = (id3.substring(CONSTANTS.OFFSET_ARTIST[CONSTANTS.FROM], CONSTANTS.OFFSET_ARTIST[CONSTANTS.TO])).trim();
	                String mAlbum = (id3.substring(CONSTANTS.OFFSET_ALBUM[CONSTANTS.FROM], CONSTANTS.OFFSET_ALBUM[CONSTANTS.TO])).trim();
	                Song s=new Song(mArtist,mTitle);
	                if(!(mTitle.equals("")||mArtist.equals("")||mAlbum.equals("")))
	                	songCreator(mArtist,mAlbum,mTitle,s);
	                else
	                	System.out.println(fil.getName()+" cannot be processed.");
	            }
	            fis.close();
			}		
		}
	}
	
	private void songCreator(String mArtist, String mAlbum, String mTitle, Song s){
		if(myLib.hasBand(mArtist)){
        	if(myLib.hasAlbumn(mArtist, mAlbum)){//check has albumn
        		if(!myLib.hasSong(mArtist, mAlbum, mTitle)){//check if !has song
        			myLib.addSong(mArtist, mAlbum, s);
        		}
        	}
        	else
        		addSongtoNewAlbumn(mArtist, mAlbum, s);
        }
        else{
        	myLib.addBand(mArtist);
        	addSongtoNewAlbumn(mArtist, mAlbum, s);
        }
	}
	
	private void addSongtoNewAlbumn(String Artist, String Alb, Song s){
		myLib.addAlb(Artist, Alb);
		myLib.addSong(Artist, Alb, s);
	}
	
	private boolean isMusicFile(File fil){
		if(fil.getName().endsWith(".mp3"))
			return true;
		return false;
	}
	
	public ArrayList<String> getRanking(){
		return myLib.rankings();
	}
}
