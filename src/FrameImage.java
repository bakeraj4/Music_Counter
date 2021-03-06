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
    private ArrayList<String> myURLs;
    final int numPerRow=10;
    final int imageSize=100;
    public FrameImage(ArrayList<String> arr){
    	myURLs=arr;
    	FrameImagePanel Pane = new FrameImagePanel();
    	Pane.setBounds(0, 0, 1000, (imageSize*((arr.size()%numPerRow==0)?1:arr.size()%numPerRow)));
    	Pane.setBackground(Color.BLACK); 
        Frame f = new Frame();  
        f.addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e){  
                System.exit(0);  
            }  
        });  
        f.add(Pane);
        this.takeScreenShot(Pane);
        f.setSize(Pane.getWidth(), Pane.getHeight()+55);//55 was added so things aren't cut off because of the title bar
        f.setLocation(200,200);  
        f.setVisible(true);  
    }  
    
    public void takeScreenShot(Component comp){    
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        File results=new File("RESULTS_"+dateFormat.format(new Date()).replace('/', '_')+".jpg");
    	BufferedImage finalImage = new BufferedImage(comp.getWidth(),comp.getHeight(),BufferedImage.TYPE_INT_RGB);
    	Graphics2D g2d=finalImage.createGraphics();
    	comp.paint(g2d);
    	try {//TODO have the user choose the location of the image
			javax.imageio.ImageIO.write(finalImage, "jpeg",results);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	   
	class FrameImagePanel extends JPanel{  
	    BufferedImage image[];
	   
	    public FrameImagePanel(){
	    	image=new BufferedImage[myURLs.size()];
	        loadImage(); 
	        repaint();
	    }  
	   
	    public void paint(Graphics g){  
	        super.paint(g);
	        for(int i=0,x=0,y=0;i<image.length;i++){
	        	g.drawImage(image[i].getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH), x, y, this); 
	        	if(x==(9*imageSize)){
	        		x=0;
	        		y+=imageSize;
	        	}
	        	else
	        		x+=imageSize;
	        }
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