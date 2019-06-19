import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Roatate {
	public static void main(String args[]) {
		Roatate r = new Roatate();
		try {
			r.ro180();
			//r.rotate90ToLeft();
			r.rotate90ToRight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	public void ro180() throws IOException
	{
		String outputFileLocation ="rotatedimage.png";
	    BufferedImage img=ImageIO.read(new File("verified.png"));
	    int width = img.getWidth(); //the Width of the original image
		int height = img.getHeight();//the Height of the original image

		BufferedImage returnImage = new BufferedImage( width, height, img.getType()  );
	
		for( int x = 0; x < width; x++ ) {
			for( int y = 0; y < height; y++ ) {
				returnImage.setRGB( (width - x - 1), (height - y - 1), img.getRGB( x, y  )  );
			}
		}
		ImageIO.write(returnImage, "png", new File("rotated.png"));
	}
	
	
	
	public void rotate90ToRight( ) throws IOException{
		//String outputFileLocation ="rotatedimage.png";
	    BufferedImage img=ImageIO.read(new File("verified.png"));
	    int width = img.getWidth(); //the Width of the original image
		int height = img.getHeight();//the Height of the original image

		BufferedImage returnImage = new BufferedImage( width, height, img.getType()  );
		for( int x = 0; x < width; x++ ) {
			for( int y = 0; y < height; y++ ) {
				returnImage.setRGB( height - y -1, x, img.getRGB( x, y  )  );

			}
		}
		ImageIO.write(returnImage, "png", new File("rotated90right.png"));
	}
	
	
	public void rotate90ToLeft( ) throws IOException{
		//String outputFileLocation ="rotatedimage.png";
	    BufferedImage img=ImageIO.read(new File("verified.png"));
	    int width = img.getWidth(); //the Width of the original image
		int height = img.getHeight();//the Height of the original image

		BufferedImage returnImage = new BufferedImage( width, height, img.getType()  );
		for( int x = 0; x < width; x++ ) {
			for( int y = 0; y < height; y++ ) {
				returnImage.setRGB(y, width - x - 1, img.getRGB( x, y  )  );
	
			}
		}
		ImageIO.write(returnImage, "png", new File("rotated90left.png"));
	}
	
}