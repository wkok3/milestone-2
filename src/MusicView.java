import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import dollar.Result;

public class MusicView extends JComponent {

	ArrayList<Staff> staves = new ArrayList<Staff>();
	ArrayList<Note> notes = new ArrayList<Note>();
	ArrayList<Rest> rests = new ArrayList<Rest>();
	int mvHeight;
	int numStaves;
	// Initial setting of 4 staves and 5 empty spaces
	double staveSizedSpacesOnMusicView;
	double oneStaffSpaceHeight;
	boolean selected = false;
	Result lastResult;
	Point point;
	 ArrayList<Point2D> points;

	public MusicView(int mvHeight, int numStaves) {
		this.mvHeight = mvHeight;
		this.numStaves = numStaves;
		staveSizedSpacesOnMusicView = (numStaves * 2) + 1;
		oneStaffSpaceHeight = (mvHeight * (1.0 / staveSizedSpacesOnMusicView));

		completeStavesList();
	}

	public void completeStavesList() {
		// Add staff to staves, update staves DS to include new staffs
//		return new Staff();
		System.out.println("testing");

//		staves.add(s);

		staves.clear();
		List<double[]> yStartPosAndHeight = getYStartPosAndHeight();

		for (int i = 0; i < numStaves; i++) {
			if (i == numStaves - 1) {
				Staff s = new Staff(10, (60 * yStartPosAndHeight.get(i)[0]), 600, (60 * yStartPosAndHeight.get(i)[1]),
						true);
				staves.add(s);
			} else {
				Staff s = new Staff(10, (60 * yStartPosAndHeight.get(i)[0]), 600, (60 * yStartPosAndHeight.get(i)[1]),
						false);
				staves.add(s);
			}

			// Dynamically resize staff based on height of MV
//				Staff s = new Staff(10,(oneStaffSpaceHeight60*yStartPosAndHeight.get(i)[0]), getWidth()-10, 
//						(oneStaffSpaceHeight*yStartPosAndHeight.get(i)[1]), false);

//				System.out.println("#pixels height per staff: " + (staffHeight*yStartPosAndHeight[i][0] - staffHeight*yStartPosAndHeight[i][1]));

		}

	}

	public int addStaff() {
//		System.out.println("addStaff");
		numStaves = staves.size() + 1;
		staveSizedSpacesOnMusicView = (numStaves * 2) + 1;
		// Each addition of a staff will result in 2 stave spaces
		// being added to MusicView, 1 for actual staff drawn and 1 empty space
		// ,each staff is 51 pixels tall
//		this.setSize(new Dimension(600,getHeight()+ (51*2)));
		// This actually adds correct amount to bottom, not mvHeight+ (60*2)
		mvHeight = (int) (staveSizedSpacesOnMusicView * 60);
		oneStaffSpaceHeight = (mvHeight * (1.0 / staveSizedSpacesOnMusicView));

		// paintComponent will be called continuously
		// if setSize() is in paintComponent, so put it here.
//		This makes it so mv does not dynamically resize and lengthen or widen the staff drawings if JFrame is resized
		// ,resizes mv after setSize and setPreferredSize in App.Java
		this.setSize(new Dimension(600, mvHeight));

		// setPreferredSize is important because increasing mv
		// size will not cause scrollbar to show all, only
		// by changing preferred size will vertical scrollbar show all added content
		this.setPreferredSize(new Dimension(600, mvHeight));

		completeStavesList();

//		this.invalidate();
		this.revalidate();
		this.repaint();
//		staffHeight = (getHeight()*(1.0/staveSizedSpacesOnMusicView));
//		Staff s = new Staff(10,(staffHeight*yStartPosAndHeight[i][0]), getWidth()-10, (staffHeight*yStartPosAndHeight[i][1]),  false);

		return numStaves;

	}

	public int deleteStaff() {
//		System.out.println("deleteStaff");
		numStaves = staves.size() - 1;
		staveSizedSpacesOnMusicView = (numStaves * 2) + 1;
		// Each addition of a staff will result in 2 stave spaces
		// being added to MusicView, 1 for actual staff drawn and 1 empty space
		// ,each staff is 51 pixels tall
//		this.setSize(new Dimension(600,getHeight()+ (51*2)));
		// This actually adds correct amount to bottom, not mvHeight+ (60*2)
		mvHeight = (int) (staveSizedSpacesOnMusicView * 60);
		oneStaffSpaceHeight = (mvHeight * (1.0 / staveSizedSpacesOnMusicView));

		// paintComponent will be called continuously
		// if setSize() is in paintComponent, so put it here.
//		This makes it so mv does not dynamically resize and lengthen or widen the staff drawings if JFrame is resized
		// ,resizes mv after setSize and setPreferredSize in App.Java
		this.setSize(new Dimension(600, mvHeight));

		// setPreferredSize is important because increasing mv
		// size will not cause scrollbar to show all, only
		// by changing preferred size will vertical scrollbar show all added content
		this.setPreferredSize(new Dimension(600, mvHeight));

		completeStavesList();

//		this.invalidate();
		this.revalidate();
		this.repaint();
//		staffHeight = (getHeight()*(1.0/staveSizedSpacesOnMusicView));
//		Staff s = new Staff(10,(staffHeight*yStartPosAndHeight[i][0]), getWidth()-10, (staffHeight*yStartPosAndHeight[i][1]),  false);
		return numStaves;

	}

	public String addSymbol(String symbolToAdd, int duration, int xPos, int yPos) {
		if (symbolToAdd == "Note") {
			Note n = new Note(duration, xPos, yPos);
			notes.add(n);
//			return n.noteImage;

			// Get note's pitch
			for (Note n1 : notes) {
				for (Staff s : staves) {
					String pitch = s.getPitch(n1.yPosClicked);
					if (pitch != null) {
						// The note might be located at invalid
						// coordinates for the last stave, but a valid one
						// for another, thus don't let it be assigned null
						// unless the user put the note at a place invalid even for the
						// last stave.
						n1.pitch = pitch;
					}

				}
			}

			this.revalidate();
			this.repaint();
			// return pitch of new note added
			return n.pitch;

		} else {
			Rest r = new Rest(duration, xPos, yPos);
			rests.add(r);
//			return r.restImage;
			this.revalidate();
			this.repaint();

			return null;
		}

//		System.out.println("pitch: " + notes.get(notes.size() - 1).pitch);
//		this.revalidate();
//		this.repaint();

//		return null;

	}

	public String dragSymbol(int xPos, int yPos, String symbolType) {
		// TODO Auto-generated method stub
//		Note latestNote = notes.get(notes.size() - 1);

		if (symbolType == "note") {
			Note latestNote = noteSelected(xPos, yPos);
			latestNote.xPosClicked = xPos;
			latestNote.yPosClicked = yPos;
//			System.out.println("dragged before set note: " + latestNote.dragged);
			latestNote.dragged = true;

			// Update current pitch of dragged Note
			boolean setAfterDragging = false;
			for (Staff s : staves) {
				String pitch = s.getPitch(latestNote.yPosClicked);
				if (pitch != null) {
					setAfterDragging = true;
					latestNote.pitch = pitch;
				}
			}
			if (!setAfterDragging) {
				latestNote.pitch = null;
			}

//			System.out.println("dragged: " + latestNote.dragged);
			this.revalidate();
			this.repaint();

			return latestNote.pitch;

		} else if (symbolType == "rest") {
			Rest latestRest = restSelected(xPos, yPos);
			latestRest.xPos = xPos;
			latestRest.yPos = yPos;
//			System.out.println("dragged before set: " + latestRest.dragged);
			latestRest.dragged = true;

			this.revalidate();
			this.repaint();
			return null;
		}
		return null;

	}

	public String checkSymbolSelected(int xPos, int yPos) {

		if (noteSelected(xPos, yPos) == null && restSelected(xPos, yPos) == null) {
			return "none";
		}
		if (noteSelected(xPos, yPos) != null) {
			return "note";
		}
		if (restSelected(xPos, yPos) != null) {
			return "rest";
		}

		revalidate();
		repaint();
		return null;
	}

	public Note noteSelected(int xPos, int yPos) {

		for (Rest r : rests) {
			// make sure when clicking outside of previously
			// selected rest, will deselect them. Also deselects
			// previous rest when selecting new rest.
			r.selected = false;
		}
		for (Note n : notes) {
			// make sure when clicking outside of previously
			// selected note, will deselect them. Also deselects
			// previous note when selecting new note.
			n.selected = false;
		}

		for (Note n : notes) {
			if (n.bounds_x == null) {
				return null;
			}
			if (xPos >= n.bounds_x[0] && xPos <= n.bounds_x[1]) {
				if (yPos >= n.bounds_y[0] && yPos <= n.bounds_y[1]) {
//					System.out.println("set as true");
					n.selected = true;
					revalidate();
					repaint();
//					n.selected = false;
//					System.out.println("set as false");

					return n;
				}
			}

		}
		return null;
	}

	public Rest restSelected(int xPos, int yPos) {
		for (Rest r : rests) {
			// make sure when clicking outside of previously
			// selected rest, will deselect them. Also deselects
			// previous rest when selecting new rest.
			r.selected = false;
		}

		for (Note n : notes) {
			// make sure when clicking outside of previously
			// selected note, will deselect them. Also deselects
			// previous note when selecting new note.
			n.selected = false;
		}

		for (Rest r : rests) {
			if (r.bounds_x == null) {
				return null;
			}
			if (xPos >= r.bounds_x[0] && xPos <= r.bounds_x[1]) {
				if (yPos >= r.bounds_y[0] && yPos <= r.bounds_y[1]) {
//					System.out.println("set as true");
					r.selected = true;
					revalidate();
					repaint();
//					n.selected = false;
//					System.out.println("set as false");

					return r;
				}
			}

		}
		return null;
	}

	public void deleteSymbol() {
		for (int i = 0; i < notes.size(); i++) {
			if (notes.get(i).selected == true) {
				notes.remove(i);
				revalidate();
				repaint();
				return;
			}
		}
		for (int i = 0; i < rests.size(); i++) {
			if (rests.get(i).selected == true) {
				rests.remove(i);
				revalidate();
				repaint();
				return;
			}
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		// draw components on screen, called when rendering MusicView on screen
//System.out.println("paintComponent called");
		g.setColor(Color.red);
		g.drawRect(0, 0, getWidth(), getHeight());

		for (Staff s : staves) {

			s.paint(g);
		}

//		for (Note note : notes) {
//			note.paint(g);
//		}
//		for (Rest rest : rests) {
//			rest.paint(g);
//		}

		g.setColor(Color.black);
		Graphics2D g2 = (Graphics2D) g;
		if (g != null && points != null) {
			System.out.println("in loop" + "points size" + points.size());
			for (int i = 0; i < points.size() - 1; i++) {
				Point2D p1 = points.get(i);
				Point2D p2 = points.get(i + 1);
				g2.setStroke(new BasicStroke(4));
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
				System.out.println(p1.getX() + " " + p1.getY()+ p2.getX() + " " + p2.getY());
			}
		}
	}

	public List<double[]> getYStartPosAndHeight() {
		List<double[]> yStartPosAndHeight = new ArrayList<>();
		double init = 1.0;

		// for yStartPosAndHeight: 1.1, 1.9 etc instead of 1->2 etc because need top and
		// bottom border, so make staff narrower.

		// to create multiplier values for staff height
		// like this: double[][] yStartPosAndHeight = {{1.1, 1.9}, {3.1,3.9}, {5.1,
		// 5.9}, {7.1,7.9}, {9.1,9.9}, {11.1, 11.9}};
		// to multiply with staffHeight =
		// (getHeight()*(1.0/staveSizedSpacesOnMusicView));
		// to get top and bottom y coordinates of a staff
		for (int i = 0; i < numStaves; i++) {
			yStartPosAndHeight.add(new double[] { init + 0.1, init + 0.9 });
			init += 2;
		}

		return yStartPosAndHeight;

	}

	{
		// Code to resize

//		int numStaves = staves.size();
//		int numSpaces = numStaves +1;
//		double staveSizedSpacesOnMusicView = numStaves + numSpaces;
//		staffHeight = (getHeight()*(1.0/staveSizedSpacesOnMusicView));
//		Staff s = new Staff(10,(staffHeight*yStartPosAndHeight[i][0]), getWidth()-10, (staffHeight*yStartPosAndHeight[i][1]),  false);

//		this.invalidate();
//		this.getParent().setSize(new Dimension(300,300));
//		this.setSize(new Dimension(300, 1000));
//		this.setMinimumSize(new Dimension(300,300));
//		this.setMaximumSize(new Dimension(300,300));
////		this.setMinimumSize(new Dimension(1000,700));
//		this.repaint();
//		this.setBounds(0, 0, 300, 300);
	}

	{

		// Code to create staves
//		staves.clear();
//		double[][] yStartPosAndHeight = {{1.1, 1.9}, {3.1,3.9}, {5.1, 5.9}, {7.1,7.9}, {9.1,9.9}, {11.1, 11.9}};
//		for(int i =0; i<6; i++) {
//			//Use x: 10 to getWidth()-10 for (left, right) borders for each staff to allow for padding
//			//(staffHeight*yStartPosAndHeight[i][0]) and (staffHeight*yStartPosAndHeight[i][1]) are 
//			//for (top, bottom) borders for each staff to allow for spacing
//			if(i != 5){
//				Staff s = new Staff(10,(staffHeight*yStartPosAndHeight[i][0]), getWidth()-10, (staffHeight*yStartPosAndHeight[i][1]), false);
////				System.out.println("#pixels height per staff: " + (staffHeight*yStartPosAndHeight[i][0] - staffHeight*yStartPosAndHeight[i][1]));
//				addStaffDefault(s);
//			}else {
//				Staff s = new Staff(10,(staffHeight*yStartPosAndHeight[i][0]), getWidth()-10, (staffHeight*yStartPosAndHeight[i][1]), true);
//				addStaffDefault(s);
//			}
//		}

	}

	public void renderUserStroke(ArrayList<Point2D> points, Point p, Result lastResult) {
		// TODO Auto-generated method stub
		System.out.println("point p: " + p);
		this.points = points;
		this.point= p;
		this.lastResult = lastResult;
		revalidate();
		repaint();
		
//		points = test.getPoints();
//		Graphics2D g2 = (Graphics2D) g;

		// get the last template result we matched, if there was one.
//		Result result = test.getLastResult();
//
//		if (points == null)
//			return;
//
//		for (int i = 0; i < points.size() - 1; i++) {
//			Point2D p1 = points.get(i);
//			Point2D p2 = points.get(i + 1);
//			g2.setStroke(new BasicStroke(4));
//			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
//		}
	}

}
