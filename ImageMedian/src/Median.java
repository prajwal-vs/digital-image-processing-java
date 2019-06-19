
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;


public class Median {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
 
        File f=new File("lenna.png");                               //Input Photo File
        Color[] pixel=new Color[9];
        int[] Gray=new int[9];
        File output=new File("output_median.png");
        BufferedImage img=ImageIO.read(f);
        for(int i=1;i<img.getWidth()-1;i++)
            for(int j=1;j<img.getHeight()-1;j++)
            {
               pixel[0]=new Color(img.getRGB(i-1,j-1));
               pixel[1]=new Color(img.getRGB(i-1,j));
               pixel[2]=new Color(img.getRGB(i-1,j+1));
               pixel[3]=new Color(img.getRGB(i,j+1));
               pixel[4]=new Color(img.getRGB(i+1,j+1));
               pixel[5]=new Color(img.getRGB(i+1,j));
               pixel[6]=new Color(img.getRGB(i+1,j-1));
               pixel[7]=new Color(img.getRGB(i,j-1));
               pixel[8]=new Color(img.getRGB(i,j));
               for(int k=0;k<9;k++){
                   Gray[k]=pixel[k].getRGB();
               }
               Arrays.sort(Gray);
               
               img.setRGB(i,j,new Color(Gray[4]).getRGB());
            }
        ImageIO.write(img,"png",output);
    }
}