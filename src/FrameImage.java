import java.awt.*;  
import java.awt.event.*;  
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.JPanel;

import java.io.*;  
import java.net.*;  
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

   
public class FrameImage{  
    ArrayList<String> myURLs;
    public FrameImage(ArrayList<String> arr){
    	myURLs=arr;
    	FrameImagePanel Pane = new FrameImagePanel();
    	Pane.setBounds(0, 0, 100, 100);
    	Pane.setBackground(Color.BLACK); 
        Frame f = new Frame();  
        f.addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e){  
                System.exit(0);  
            }  
        });  
        f.add(Pane);
       this.takeScreenShot(Pane);
        //TODO change the sizes
        f.setSize(400,400);  
        f.setLocation(200,200);  
        f.setVisible(true);  
    }  
    
    public void takeScreenShot(Component comp){    
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        File results=new File("RESULTS_"+dateFormat.format(new Date()).replace('/', '_')+".jpg");
        
    	BufferedImage finalImage = new BufferedImage(comp.getWidth(),comp.getHeight(),BufferedImage.TYPE_INT_RGB);
    	Graphics2D g2d=finalImage.createGraphics();
    	comp.paint(g2d);
    	try {
			javax.imageio.ImageIO.write(finalImage, "jpeg",results);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	   
	class FrameImagePanel extends JPanel{  
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
	        
	    }
	  
	    public Dimension getPreferredSize(){//TODO will need to change image[i] and the parameter of i 
	        return new Dimension(image[0].getWidth(), image[0].getHeight());  
	    }  
	   
	    private void loadImage(){
	        try{  
	        	URL url; 
	            int i=0;
	            for(String bands:myURLs){
	            	url = new URL(bands);
	            	image[i] = ImageIO.read(url); 
		            i++;
	            }
	        }catch(Exception e){
	        }
	    }  
	}
}