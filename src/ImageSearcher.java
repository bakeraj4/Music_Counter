import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
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
	}

	void findAll() throws UnknownHostException{
		for(String str: myBands)
			find(str);
	}
	
	void find(String str) throws UnknownHostException{
		try {
			URL resURL=new URL(google+URLEncoder.encode(str+" :last.fm",charset));
			InputStreamReader urlReader= new InputStreamReader(resURL.openStream(),charset);
			GoogleResults googleRes= new Gson().fromJson(urlReader, GoogleResults.class);
			System.out.println("Finding: "+str);
			myURLs.add(googleRes.getResponseData().getResults().get(0).getUrl());
			//TODO find out why after a point all are giving errors, is there a limit of times I can use the google search i counted and 72 was the last one before printing 'error'
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} //try catching the excepting then throwing it by hand
	}
	
	ArrayList<String> getURLs(){
		return myURLs;
	}
}
