import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Staff {
	int xStartPos, w;
	int yStartPos, h;
	int prevH;
	boolean lastStaff;
	List<Object> symbols = new ArrayList<>();
	
	private BufferedImage trebleClefImage;
	private BufferedImage commonTimeImage;
	private BufferedImage flatImage;
	private BufferedImage sharpImage;
	private BufferedImage naturalImage;
	
	public Staff(int xStartPos, double yStartPos, int w, double h,  boolean lastStaff) {
		this.xStartPos = xStartPos;
		this.yStartPos = (int) yStartPos;
		this.w = w;
		this.h = (int) h;
		this.prevH = (int) prevH;
		this.lastStaff = lastStaff;
		
		
		try {
			trebleClefImage = ImageIO.read(getClass().getResource("/images/trebleClef.png"));
			commonTimeImage = ImageIO.read(getClass().getResource("/images/commonTime.png"));
			flatImage = ImageIO.read(getClass().getResource("/images/flat.png"));
			sharpImage = ImageIO.read(getClass().getResource("/images/sharp.png"));
			naturalImage = ImageIO.read(getClass().getResource("/images/natural.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
	//paintComponent() code would just iterate through a 
	//list of the Staff objects that you maintain and call 
	//the paint() method on each, passing its (paintComponent's)graphics object.
	//vertical position of staff
	int f5YPos = yStartPos + (((h-yStartPos)/4)*0);
	int d5YPos = yStartPos + (((h-yStartPos)/4)*1);
	int b5YPos = yStartPos + (((h-yStartPos)/4)*2);
	int g4YPos = yStartPos + (((h-yStartPos)/4)*3);
	int e4YPos = yStartPos + (((h-yStartPos)/4)*4);
	
	
		
//	System.out.println(xStartPos + " " + yStartPos + " " + e4YPos + " " +h);
	//drawing staff based on resizing width
//	g.drawLine(xStartPos, f5YPos, w, f5YPos);
//	g.drawLine(xStartPos, d5YPos, w, d5YPos);

	
	
//	g.drawLine(xStartPos, g4YPos, w, g4YPos);
//	g.drawLine(xStartPos, e4YPos, w, e4YPos);
	
	//Draw staff based on hardcoded width
	g.drawLine(xStartPos, f5YPos, 600, f5YPos);
	g.drawLine(xStartPos, d5YPos, 600, d5YPos);
	g.drawLine(xStartPos, b5YPos, 600, b5YPos);
	g.drawLine(xStartPos, g4YPos, 600, g4YPos);
	g.drawLine(xStartPos, e4YPos, 600, e4YPos);
	
	//Draw vertical lines at left and right edges of staff
	g.drawLine(xStartPos,  f5YPos, xStartPos, e4YPos);
//	System.out.println("height per staff: " + (e4YPos - f5YPos));
//	g.drawLine(w, f5YPos, w, e4YPos);
	g.drawLine(600, f5YPos, 600, e4YPos);
	
	if(lastStaff == true) {
		//draw thin and thick line for end staff
//		g.fillRect(w, f5YPos, (int)(5), e4YPos-f5YPos+1);
//		g.drawLine(w-3, f5YPos, w-3, e4YPos);
		g.fillRect(600, f5YPos, (int)(5), e4YPos-f5YPos+1);
		g.drawLine(600-3, f5YPos, 600-3, e4YPos);
	}
//	System.out.println("test: " + w+ " " + w*1.1 + " " +f5YPos + " " +(e4YPos-f5YPos));
	//Thick and thin line for end staff
	
	
	//Draw treble cleft and common time symbols
	g.drawImage(trebleClefImage, xStartPos, f5YPos-15, null);
    Image scaledCommonTimeImage = commonTimeImage.getScaledInstance((int)(commonTimeImage.getWidth()*0.70), (int)(commonTimeImage.getHeight()*0.70), e4YPos);
	g.drawImage(scaledCommonTimeImage, xStartPos+50, f5YPos+8, null);
	
	
	
	
	
	}
	
	public String getPitch(int noteYPos) {
//		System.out.println(noteYPos);
		int f5YPos = yStartPos + (((h-yStartPos)/4)*0);
		int d5YPos = yStartPos + (((h-yStartPos)/4)*1);
		int b5YPos = yStartPos + (((h-yStartPos)/4)*2);
		int g4YPos = yStartPos + (((h-yStartPos)/4)*3);
		int e4YPos = yStartPos + (((h-yStartPos)/4)*4);
		//if within bottom 3 and top (1) pixels of string 
		//e4, then it's e4. 
		if(noteYPos >= e4YPos-1 && noteYPos <= e4YPos+3 ) {
			return "e4";
		}
		
		//If from top (1->) of e4 to g4 (bottom 2) then it's F4
		if(noteYPos >= g4YPos + 4 && noteYPos < e4YPos-1) {
			return "f4";
		}
		
		//If from g4(bottom 2) to g4 (top 1) then it's g4/
		if(noteYPos >= g4YPos-1 &&  noteYPos<g4YPos+4) {
			return "g4";
		}
		//If from g4(top 1->) to b5 (bottom 2) then it's A5
		if(noteYPos >= b5YPos+4 && noteYPos<g4YPos-1) {
			return "a5";
		}
		//If from B5 (bottom 2) to b5(top 1->) then it's B5
		if(noteYPos >= b5YPos-1 && noteYPos < b5YPos+4) {
			return "b5";
		}
		//If from B5 (top 1->) to D5(bottom 2) then it's C5
		if(noteYPos >= d5YPos+4 && noteYPos < b5YPos-1) {
			return "c5";
		}
		//If from D5(bottom 2) to D5 (top 1) it's D5
		if(noteYPos >= d5YPos-1 &&  noteYPos < d5YPos+4) {
			return "d5";
		}
		//If from D5 (top 1->) to F5(bottom 2) then it's E5
		if(noteYPos >= f5YPos +4 && noteYPos < d5YPos-1) {
			return "e5";
			
		}
		//If from F5(bottom 2) to F5(top 3-> then it's F5
		if(noteYPos>=f5YPos-3 && noteYPos <f5YPos+4) {
			return "f5";
		}
		return null;
		
		
	}
	
	
}
