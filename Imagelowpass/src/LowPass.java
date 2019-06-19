
import java.awt.image.Kernel;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.io.*;
import javax.imageio.*;


public class LowPass { 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        try {
 BufferedImage buff_original;
 buff_original = ImageIO.read(new File("lenna.png"));
 float val=1f/9f;
 //System.out.println(val);
float[]data={ val, val, val,val, val, val, val, val, val };

 Kernel kernel = new Kernel(3, 3,data);
 BufferedImageOp ConOp = new ConvolveOp(kernel);
 buff_original = ConOp.filter(buff_original, null);
 JPanel content = new JPanel();
 content.setLayout(new FlowLayout());
 
 content.add(new JLabel(new ImageIcon(buff_original)));
 JFrame f = new JFrame("Convolution Image ");
 f.addWindowListener(new WindowAdapter() {
 @Override
 public void windowClosing(WindowEvent e) {
 System.exit(0);
 }
 });
 f.setContentPane(content);
 f.pack();
 f.setVisible(true);
 } catch (IOException e) {
 }
 }}
    
    

