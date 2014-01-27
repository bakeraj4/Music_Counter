import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.imageio.*;

import com.google.gson.Gson;

public class ImageSearcher {
	static final String google = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
    static final String charset = "UTF-8";
	private ArrayList<String> myBands;
	private ArrayList<URL> myURLs=new ArrayList<URL>();
	ImageSearcher(ArrayList<String> bands){
		myBands=bands;
		find("Avenged Sevenfold");
	}

	void find(String str){
		try {
			URL resURL=new URL(google+URLEncoder.encode(str+" band cover art",charset));
			InputStreamReader urlReader= new InputStreamReader(resURL.openStream(),charset);
			GoogleResults googleRes= new Gson().fromJson(urlReader, GoogleResults.class);
			//System.out.println(googleRes.getResponseData().getResults().get(0).getTitle());
			for(int i=0;i<googleRes.getResponseData().getResults().size();i++){ 
				System.out.println(googleRes.getResponseData().getResults().get(i).getUrl());
			}
		} catch (MalformedURLException e) {
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//TODO make a web crawler to obtain an image, the url's, for each band
		//most likely will be the wikipedia page for the band and just take the image.
	
	//TODO create the collage of the images obtained and save it
}
