
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


public class Histospec {
public static int GRAYLEVEL = 256;
	public static int IMAGESIZE = 256;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    int[] histogram = new int[GRAYLEVEL];
		int[] newHistogram = new int[GRAYLEVEL];
		int[] equalized = new int[GRAYLEVEL];
		int i, j; /* control variable */

		/* Calculation of histogram */
		for (i = 0; i < GRAYLEVEL; i++) {
			histogram[i] = 0;
			newHistogram[i] = 0;
			equalized[i] = 0;
		}
		File file = new File("lenna.png");
        BufferedImage img;
		try {
			img = ImageIO.read(file);
			int width = img.getWidth();
			int height = img.getHeight();
			WritableRaster wr = img.getRaster();
			
			int[][] newData = new int[width][height];
			int[][] lookup = new int[width][height];
			Random rand = new Random();
			
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					newData[x][y] = rand.nextInt(256); // generate the new data
					histogram[wr.getSample(x, y, 0)]++; // at the same time, populate the original
					newHistogram[newData[x][y]]++; // and populate the newHistogram
					// lookup[x][y] = Math.abs(newData[x][y] - data.getPixel(x, y));
				}
			}

			for (i = 0; i < GRAYLEVEL; i++) {
				int tmp = Math.abs(Math.min(histogram[i], newHistogram[i]));
				int index = 0;
				for (j = 0; j < GRAYLEVEL; j++) {
					int r = Math.abs(Math.min(histogram[i], newHistogram[j]));
					if (r <= tmp) {
						tmp = r;
						index = j;
					}
				}
				equalized[i] = newHistogram[index];
			}

			for (i = 0; i < width; i++) {
				for (j = 0; j < height; j++) {
					lookup[i][j] = wr.getSample(i, j, 0) + Math.abs(equalized[j % GRAYLEVEL]);
				}
			}

			BufferedImage theImage = new BufferedImage(width, height,img.getType());
			WritableRaster er = theImage.getRaster();
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					er.setSample(x, y, 0, lookup[x][y]);
				}
			}
			theImage.setData(er);
			File outputfile = new File("outputspec.png");
	        ImageIO.write(theImage, "png", outputfile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
    
}
