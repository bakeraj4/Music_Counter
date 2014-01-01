import java.awt.*;  
import java.awt.event.*;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.net.*;  
import javax.imageio.ImageIO;  
   
public class FrameImage{  
    public FrameImage(){//TODO an arrayList of Strings that will be the urls  
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
}  
   
class FrameImagePanel extends Panel{  
    BufferedImage image;  //TODO will need to change to an []
   
    public FrameImagePanel(){  
        loadImage();  
    }  
   
    public void paint(Graphics g){  
        super.paint(g); 
        int x = (getWidth() - image.getWidth())/2;  
        int y = (getHeight() - image.getHeight())/2;  
        g.drawImage(image, x, y, this); 
        //TODO this method will need to change to handle the array list of buffered images and places them in the correct area (some type of loop and place in the spiral in reverse such that the ones toward the front are on top of the previuos ones)
        
        //TODO need to save the image
    }  
   
    public Dimension getPreferredSize(){//TODO will need to change image[i] and the parameter of i 
        return new Dimension(image.getWidth(), image.getHeight());  
    }  
   
    private void loadImage(){//TODO a for loop for the arraylist of url srings [i]
        try  
        {  
            URL url = new URL("https://scontent-b-iad.xx.fbcdn.net/hphotos-prn2/1384034_745841162097861_1937544369_n.jpg");  
            image = ImageIO.read(url);  
        }catch(Exception e){
        }
    }  
}