public class Main {	
	public static void main(String []args){
		Reader r=new Reader();
		ImageSearcher is=new ImageSearcher(r.getRanking());
		FrameImage fi=new FrameImage(is.getURLs());
	}
}
