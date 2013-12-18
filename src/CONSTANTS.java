public class CONSTANTS {
	private static CONSTANTS instance=null; 
	
	public static final String ID3_TAG = "TAG";
    public static final int BYTE_128 = 128;
    public static final int[] OFFSET_TAG = new int[] { 0, 3 };
    public static final int[] OFFSET_TITLE = new int[] { 3, 33 };
    public static final int[] OFFSET_ARTIST = new int[] { 33, 63 };
    public static final int[] OFFSET_YEAR = new int[] { 93, 97 };
    public static final int[] OFFSET_ALBUM = new int[] { 63, 93 };
 
    // indexer
    public static final int FROM = 0;
    public static final int TO = 1;

    
    protected CONSTANTS(){
    	
    }
    
    public static CONSTANTS getInstance(){
    	if(instance==null)
    		instance =new CONSTANTS();
    	return instance;
    }
}
