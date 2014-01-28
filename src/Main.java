public class Main {	
	public static void main(String []args){
		//TODO if there is a 'backlog' use that and pass to the imageSearcher, other wise read from the file(s)
		Reader r=new Reader();
		ImageSearcher is=new ImageSearcher(r.getRanking());
		FrameImage fi=new FrameImage(is.getURLs());
		
		//TODO if there is no internet connection- there will be a java.net.UnkownHostException
			//due to the ajax.googleapis. So somehow store the list of bands to a file to latter
			//work on.
		
	}
}
