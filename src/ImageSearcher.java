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
	private ArrayList<String> myURLs=new ArrayList<String>();
	
	ImageSearcher(ArrayList<String> bands){
		myBands=bands;
		findAll();
	}

	void findAll(){
		for(String str: myBands)
			find(str);
	}
	
	void find(String str){
		try {
			URL resURL=new URL(google+URLEncoder.encode(str+" band cover art :wikimedia",charset));
			InputStreamReader urlReader= new InputStreamReader(resURL.openStream(),charset);
			GoogleResults googleRes= new Gson().fromJson(urlReader, GoogleResults.class);
			//System.out.println(googleRes.getResponseData().getResults().get(0).getTitle());
			/*for(int i=0;i<googleRes.getResponseData().getResults().size();i++){ 
				System.out.println(googleRes.getResponseData().getResults().get(i).getUrl());
			}*/
			myURLs.add(googleRes.getResponseData().getResults().get(0).getUrl());
		} catch (MalformedURLException e) {
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	ArrayList<String> getURLs(){
		return myURLs;
	}
	
	//TODO create the collage of the images obtained and save it
}
