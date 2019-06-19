
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class HighPass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
         try {
 BufferedImage buff_original;
 //System.out.println("hh");
 buff_original = ImageIO.read(new File("lenna.png"));
 float val=1f/9f;
float[] lowpass={ val, val, val,val, val, val, val, val, val };
float [] allpass={0,0,0,0,1,0,0,0,0};
float [] output={0,0,0,0,0,0,0,0,0};
for(int i=0;i<=8;i++)
{
    
    output[i]=allpass[i]-lowpass[i];
    
    
}

 Kernel kernel = new Kernel(3, 3,output);
 BufferedImageOp ConOp = new ConvolveOp(kernel);
 buff_original = ConOp.filter(buff_original, null);
 JPanel content = new JPanel();
 content.setLayout(new FlowLayout());
 // label to load image
 content.add(new JLabel(new ImageIcon(buff_original)));
 JFrame f = new JFrame(" Image ");
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
        // TODO code application logic here
    }
    
}
