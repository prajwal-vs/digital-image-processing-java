
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Histoplot {
public static void main(String[] args) {
    
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Histoplot();
            }
        });
        
       
    }

    public int[][] getPixels(BufferedImage image) {
        int iw = image.getWidth();
        int ih = image.getHeight();
        int[][] pixels = new int[iw][ih];
        
        if (image.getType() == 5){
            DataBufferByte db = (DataBufferByte)image.getRaster().getDataBuffer();
            byte[] pixelarray = db.getData();
            
            for (int x = 0; x < iw; x++ ) {
                for (int y = 0; y < ih; y++ ) {
                    pixels[x][y] = pixelarray[x + y * iw] &0xFF;
                }
            }
        }
        
        return pixels;
    }
    
    public Histoplot() {
    	
   	 	File file = new File("lenna.png");
   	 	BufferedImage image;
        int [] histogram;

   	 	try {
			image = ImageIO.read(file);

	        if(image.getType() == 10){
	            histogram = new int[256];
	        } else {
	            histogram = new int[2001];
	        }
	        int[][] pixels = getPixels(image);
	       
	        for (int x = 0; x < pixels.length; x++){
	            for (int y = 0; y < pixels[0].length; y++){
	                histogram[pixels[x][y]]++;
	            }
	        }
   	 	
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(new Graph(histogram)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);}
   	 	catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
    }

    protected class Graph extends JPanel {

        protected static final int MIN_BAR_WIDTH = 4;
        private int[] histogram;

        public Graph(int[] histogram) {
            this.histogram = histogram;
            int width = (histogram.length * MIN_BAR_WIDTH) + 11;
            Dimension minSize = new Dimension(width, 128);
            Dimension prefSize = new Dimension(width, 256);
            setMinimumSize(minSize);
            setPreferredSize(prefSize);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (histogram != null) {
                int xOffset = 5;
                int yOffset = 5;
                int width = getWidth() - 1 - (xOffset * 2);
                int height = getHeight() - 1 - (yOffset * 2);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(xOffset, yOffset, width, height);
                int barWidth = Math.max(MIN_BAR_WIDTH,
                        (int) Math.floor((float) width
                        / (float) histogram.length));
                System.out.println("width = " + width + "; size = "
                        + histogram.length + "; barWidth = " + barWidth);
                int maxValue = 0;
                for (int i=0;i<histogram.length;i++) {
                    int value = histogram[i];
                    maxValue = Math.max(maxValue, value);
                }
                int xPos = xOffset;
                for (int i=0;i<histogram.length;i++) {
                    int value = histogram[i];
                    int barHeight = Math.round(((float) value
                            / (float) maxValue) * height);
                    g2d.setColor(new Color(12, 16, 116));
                    int yPos = height + yOffset - barHeight;
//Rectangle bar = new Rectangle(xPos, yPos, barWidth, barHeight);
                    Rectangle2D bar = new Rectangle2D.Float(
                            xPos, yPos, barWidth, barHeight);
                    g2d.fill(bar);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.draw(bar);
                    xPos += barWidth;
                }
                g2d.dispose();
            }
        }
    }
    
}
