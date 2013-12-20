import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Reader {

	public CONSTANTS consts=CONSTANTS.getInstance();
	private FileInputStream fis;
	private Library myLib=new Library();
	
	Reader(){
		String rootDirectory="F:\\music";//"C:\\Users\\Aaron\\Dropbox\\Avenged Sevenfold";//"F:\\music\\Avenged Sevenfold";//
		try {
			findFiles(new File(rootDirectory));
			System.out.println(myLib.toString());
		} catch (IOException e) {
			//System.out.println(myLib.toString());
			System.out.println(myLib.rankings());
			e.printStackTrace();
		}
	}
	
	private void findFiles(File root) throws IOException {
		File[] filesHere=root.listFiles();
		for(File fil: filesHere){
			if(fil.isDirectory() && fil.getCanonicalPath().equals(fil.getAbsolutePath())){
				findFiles(fil);
			}
			else if(fil.canRead() && fil.isFile() /*&& (fil.getName().contains(searchParam))-could be used for mp3, mp4, etc*/){
				fis =new FileInputStream(fil);
				int size=(int)fil.length();
				fis.skip(size-CONSTANTS.BYTE_128);
				byte[] chunk=new byte[CONSTANTS.BYTE_128];
				fis.read(chunk);
				String id3 =new String(chunk);
	            String tag = id3.substring(CONSTANTS.OFFSET_TAG[CONSTANTS.FROM], CONSTANTS.OFFSET_TAG[CONSTANTS.TO]);
	            if(tag.equals(CONSTANTS.ID3_TAG) && isMusicFile(fil)) {
	                String mTitle = (id3.substring(CONSTANTS.OFFSET_TITLE[CONSTANTS.FROM], CONSTANTS.OFFSET_TITLE[CONSTANTS.TO])).trim();
	                String mArtist = (id3.substring(CONSTANTS.OFFSET_ARTIST[CONSTANTS.FROM], CONSTANTS.OFFSET_ARTIST[CONSTANTS.TO])).trim();
	                String mYear = (id3.substring(CONSTANTS.OFFSET_YEAR[CONSTANTS.FROM], CONSTANTS.OFFSET_YEAR[CONSTANTS.TO])).trim();
	                String mAlbum = (id3.substring(CONSTANTS.OFFSET_ALBUM[CONSTANTS.FROM], CONSTANTS.OFFSET_ALBUM[CONSTANTS.TO])).trim();
	                Song s=new Song(0,mArtist,mTitle);
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
        //System.out.println(s.toString());
        //System.out.println();
	}
	
	private void addSongtoNewAlbumn(String Artist, String Alb, Song s){
		myLib.addAlb(Artist, Alb);
		myLib.addSong(Artist, Alb, s);
	}
	
	private boolean isMusicFile(File fil){
		if(fil.getName().endsWith(".mp3"))
			return true;
		//TODO other file extensions
		return false;
	}
	
	
}
