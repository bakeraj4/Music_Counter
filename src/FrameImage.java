import java.awt.*;  
import java.awt.event.*;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.net.*;  
import java.util.ArrayList;

import javax.imageio.ImageIO;  
   
public class FrameImage{  
    ArrayList<String> myURLs;
    public FrameImage(ArrayList<String> arr){
    	myURLs=arr;
    	Panel Pane = new Panel();  
        Pane.add(new FrameImagePanel());  
        Frame f = new Frame();  
        f.addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e){  
                System.exit(0);  
            }  
        });  
        f.add(Pane); 
        //TODO change the sizes
        f.setSize(400,400);  
        f.setLocation(200,200);  
        f.setVisible(true);  
    }  
	   
	class FrameImagePanel extends Panel{  
	    BufferedImage image[];
	   
	    public FrameImagePanel(){
	    	image=new BufferedImage[myURLs.size()];
	        loadImage();  
	    }  
	   
	    public void paint(Graphics g){  
	        super.paint(g); 
	        int x = (getWidth() - image[0].getWidth())/2;  
	        int y = (getHeight() - image[0].getHeight())/2;  
	        g.drawImage(image[0], x, y, this); 
	        //TODO this method will need to change to handle the array list of buffered images and places them in the correct area (some type of loop and place in the spiral in reverse such that the ones toward the front are on top of the previuos ones)
	        
	        //TODO need to save the image
	    }  
	   
	    public Dimension getPreferredSize(){//TODO will need to change image[i] and the parameter of i 
	        return new Dimension(image[0].getWidth(), image[0].getHeight());  
	    }  
	   
	    private void loadImage(){//TODO a for loop for the arraylist of url srings [i]
	        try{  
	            URL url = new URL(myURLs.get(0));//("https://scontent-b-iad.xx.fbcdn.net/hphotos-prn2/1384034_745841162097861_1937544369_n.jpg");  
	            image[0] = ImageIO.read(url);  
	        }catch(Exception e){
	        }
	    }  
	}
}