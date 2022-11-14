import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Note {
	int duration;
	int xPosClicked;
	int yPosClicked;
	private BufferedImage sixteenthNoteImage;
	private BufferedImage eightNoteImage;
	private BufferedImage quarterNoteImage;
	private BufferedImage halfNoteImage;
	private BufferedImage wholeNoteImage;
	BufferedImage noteImage;
	String pitch;

	int starting_x=0;
	int starting_y=0;

	int[] bounds_x;
	int[] bounds_y;

	boolean selected = false;
	boolean dragged = false;

	public Note(int duration, int xPosClicked, int yPosClicked) {
		// TODO Auto-generated constructor stub

		this.duration = duration;
		this.xPosClicked = xPosClicked;
		this.yPosClicked = yPosClicked;

		try {

			sixteenthNoteImage = ImageIO.read(getClass().getResource("/images/sixteenthNote.png"));
			eightNoteImage = ImageIO.read(getClass().getResource("/images/eighthNote.png"));
			quarterNoteImage = ImageIO.read(getClass().getResource("/images/quarterNote.png"));
			halfNoteImage = ImageIO.read(getClass().getResource("/images/halfNote.png"));
			wholeNoteImage = ImageIO.read(getClass().getResource("/images/wholeNote.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (this.duration == 20) {
			noteImage = sixteenthNoteImage;

		}

		if (this.duration == 40) {
			noteImage = eightNoteImage;

		}
		if (this.duration == 60) {
			noteImage = quarterNoteImage;

		}
		if (this.duration == 80) {
			noteImage = halfNoteImage;

		}
		if (this.duration == 100) {
			noteImage = wholeNoteImage;

		}
	}

	public void paint(Graphics g) {


		if (this.dragged == false || this.dragged == true ) {
			
			//Means this is a new Note
			//When simply adding
			// Change starting coordinates and bounds everytime Note is added/moved/rendered
			starting_x = this.xPosClicked - getPositionPoint()[0];
			starting_y = this.yPosClicked - getPositionPoint()[1];
//			g.drawRect(starting_x, starting_y,  noteImage.getTileWidth(), noteImage.getTileHeight());
//			g.drawLine(starting_x, starting_y, (starting_x+ noteImage.getTileWidth()), starting_y);
//			System.out.println(this.xPosClicked + " " +this.yPosClicked+ "  "+  noteImage.getTileWidth() + " " + noteImage.getTileHeight());
//			System.out.println(starting_x+ " " +starting_y+ "  "+  noteImage.getTileWidth() + " " + noteImage.getTileHeight());
//			System.out.println(this.xPosClicked+ noteImage.getTileWidth() + " "  + (this.yPosClicked + noteImage.getTileHeight()));
			bounds_x = new int[] { starting_x, starting_x + noteImage.getTileWidth() };
			bounds_y = new int[] { starting_y, starting_y + noteImage.getTileHeight() };
			
			if (selected == true) {
				g.drawRect(starting_x, starting_y, noteImage.getTileWidth(), noteImage.getTileHeight());
			}

			// this.xPos-getPositionPoint()[0], this.yPos-getPositionPoint()[1]
			// to render note image from different coordinate. However
			// do not modify coordinates of original click.
//			System.out.println("not in drag");
			g.drawImage(noteImage, starting_x, starting_y, null);
			this.dragged = false;
		} 
//		if (this.dragged == true){
//			starting_x = (this.xPosClicked - getPositionPoint()[0]);
//			starting_y =(this.yPosClicked - getPositionPoint()[1]);
//			bounds_x = new int[] { starting_x, starting_x + noteImage.getTileWidth() };
//			bounds_y = new int[] { starting_y, starting_y + noteImage.getTileHeight() };
//			
//			if (selected == true) {
//				g.drawRect(starting_x, starting_y, noteImage.getTileWidth(), noteImage.getTileHeight());
//			}
//			g.drawImage(noteImage, starting_x, starting_y, null);
//			
////			System.out.println("in drag");
//			this.dragged = false;
//			
//		}
		
		
		
	}

//	public void highlightBounds() {
//		starting_x =  this.xPosClicked-getPositionPoint()[0];
//		starting_y =  this.yPosClicked-getPositionPoint()[1];
//		g.drawRect(starting_x, starting_y,  noteImage.getTileWidth(), noteImage.getTileHeight());
//	}

	public int[] getPositionPoint() {
		// Get center of bottom round part of note
		int[] coordinateCenterRoundPart;
		if (this.duration == 20) {
			// sixteenth note
			return new int[] { 6, 35 };

		} else if (this.duration == 40) {
			return new int[] { 15, 36 };

		} else if (this.duration == 60) {
			return new int[] { 7, 35 };

		} else if (this.duration == 80) {
			return new int[] { 15, 34 };

		} else {
			// this.duration== 100
			// whole note
			return new int[] { 10, 6 };

		}

	}

}
