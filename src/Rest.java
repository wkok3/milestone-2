import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rest {

	int duration;
	int xPos; 
	int yPos;
	private BufferedImage sixteenthRestImage;
	private BufferedImage eightRestImage;
	private BufferedImage quarterRestImage;
	private BufferedImage halfRestImage;
	private BufferedImage wholeRestImage;
	
	BufferedImage restImage;
	
	
	int[] bounds_x;
	int[] bounds_y;

	boolean selected = false;
	boolean dragged = false;
	
	int starting_x = 0;
	int starting_y = 0;
	
	
	public Rest(int duration, int xPos, int yPos) {
		// TODO Auto-generated constructor stub
		this.duration = duration;
		this.xPos = xPos;
		this.yPos = yPos;
		
		try {
			sixteenthRestImage = ImageIO.read(getClass().getResource("/images/sixteenthRest.png"));
			eightRestImage = ImageIO.read(getClass().getResource("/images/eighthRest.png"));
			quarterRestImage = ImageIO.read(getClass().getResource("/images/quarterRest.png"));
			halfRestImage = ImageIO.read(getClass().getResource("/images/halfRest.png"));
			wholeRestImage = ImageIO.read(getClass().getResource("/images/wholeRest.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(this.duration== 20 ) {
			restImage= sixteenthRestImage;
			
		}
		
		if(this.duration== 40 ) {
			restImage= eightRestImage;
			
		}
		if(this.duration== 60 ) {
			restImage= quarterRestImage;
			
		}
		if(this.duration== 80 ) {
			restImage= halfRestImage;
			
		}
		if(this.duration== 100 ) {
			restImage= wholeRestImage;
			
		}
	}
	public void paint(Graphics g) {
		
		starting_x = this.xPos - 6;
		starting_y = this.yPos- 3;

		bounds_x = new int[] { starting_x, starting_x + restImage.getTileWidth() };
		bounds_y = new int[] {starting_y, starting_y+ restImage.getTileHeight() };
		
		if (selected == true) {
			g.drawRect(starting_x, starting_y, restImage.getTileWidth(), restImage.getTileHeight());
		}

		// this.xPos-getPositionPoint()[0], this.yPos-getPositionPoint()[1]
		// to render note image from different coordinate. However
		// do not modify coordinates of original click.
//		System.out.println("not in drag");
		g.drawImage(restImage, starting_x,starting_y, null);
		this.dragged = false;
	}
	
}
