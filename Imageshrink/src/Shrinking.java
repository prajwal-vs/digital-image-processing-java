
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Shrinking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   String inputFile = "lenna.png";
		String shrinkedFile = "output_shrink.jpg";

		try {
			BufferedImage image = ImageIO.read(new File(inputFile));
			//For shrinking
			BufferedImage shrinkImage = shrink(image,0.5);
			ImageIO.write(shrinkImage, "jpg", new File(shrinkedFile));
			System.out.println("Shrinked the image");

		} catch (IOException e) {
		}		

    }
        
     public static BufferedImage shrink(BufferedImage image, double n) {

	    int w = (int)(n * image.getWidth());
	    int h = (int)(n * image.getHeight());

	    BufferedImage shrinkedImage = new BufferedImage(w, h, image.getType());

	    for (int y=0; y < h; ++y)
	        for (int x=0; x < w; ++x)
	        	shrinkedImage.setRGB(x, y, image.getRGB((int)(x/n), (int)(y/n)));

	    return shrinkedImage;

	}

    }
    
    

