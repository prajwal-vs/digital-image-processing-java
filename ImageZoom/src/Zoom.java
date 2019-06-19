

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Zoom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // TODO code application logic here
           int width = 963;    //width of the image
    int height = 640;   //height of the image
    BufferedImage image = null;
    File f = null;

    //read image
    try{
      f = new File("lenna.png"); //image file path
      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      image = ImageIO.read(f);
      
      BufferedImage translatedImage = zoomImage(image,10);
      ImageIO.write(translatedImage, "png", new File("output_zoom.png"));
      System.out.println("Translated the image");

    }catch(IOException e){
      System.out.println("Error: "+e);
    }}
        
        
         public static BufferedImage zoomImage(BufferedImage image, int zoom) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width * zoom,
                height * zoom,
                image.getType());
        int rgb;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rgb = image.getRGB(x, y);
                for (int i = 0; i < zoom; i++) {
                    for (int j = 0; j < zoom; j++) {
                        result.setRGB(x * zoom + i,
                                y * zoom + j,
                                rgb);
                    }
                }
            }
        }
        return result;
    }
    }
    

