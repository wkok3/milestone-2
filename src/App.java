import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

import dollar.DollarRecognizer;
import dollar.Result;

public class App {
	
	private static void createAndShowGUI() {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;
		JMenu submenu;
		JLabel statusBarLabel = new JLabel("Status bar");
		JLabel numStavesLabel = new JLabel("4");
		JLabel contentLabel = new JLabel(
				"My Music Editor. Showing " + Integer.valueOf(numStavesLabel.getText()) + " Staves");
		JButton newStaffButton = new JButton("New Staff");
		JButton deleteStaffButton = new JButton("Delete Staff");

		JFrame frame = new JFrame("My Music Editor");
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Related to Recognizer
		ArrayList<Point2D> points = null;
		DollarRecognizer recog = new DollarRecognizer();
		JLabel status;
		JComboBox<String> combo;
		Result lastResult = null;
		// private Canvas panel;
		final String[] templateStrings = { "triangle", "x", "rectangle", "circle", "check", "caret",
				"zig-zag", "arrow", "left square bracket", "right square bracket", "v", "delete", "left curly brace",
				"right curly brace", "star", "pigtail", "flat", "half note", "quarter note", "eighth note",
				"sixteenth note", "half rest", "eighth rest", "sixteenth rest" };

		
		/**
		 * Menus
		 */
		menuBar = new JMenuBar();
		// File menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		// File menu items
		menuItem = new JMenuItem("Exit", KeyEvent.VK_E);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("test action listener");
				System.exit(0);

			}

		});

		menu.add(menuItem);

		// Edit menu
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(menu);

		// Edit menu items
		menuItem = new JMenuItem("New Staff", KeyEvent.VK_N);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("New Staff");

			}

		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Delete Staff", KeyEvent.VK_D);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("Delete Staff");
			}

		});
		menu.add(menuItem);

//		
		frame.setLayout(new BorderLayout());

		/**
		 * Status bar area
		 */

		// Add bottom panel with OK and Cancel buttons to bottom of JFrame
		statusBarLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.add(statusBarLabel, BorderLayout.SOUTH);

		/**
		 * Left side (controls area)
		 * https://stackoverflow.com/questions/36968589/is-it-possible-to-add-multiple-components-to-jpanel-in-one-statement
		 */
		JPanel leftPanel = new JPanel();

		JButton selectButton = new JButton("Select");
		selectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("Select");
			}

		});
		JButton penButton = new JButton("Pen");
		penButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("Pen");
			}

		});
		JPanel toolPanel = new JPanel();
		toolPanel.setLayout(new FlowLayout());
		toolPanel.add(selectButton);
		toolPanel.add(penButton);

		// newStaffButton defined at start
//		newStaffButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				statusBarLabel.setText("New Staff");
//				
//				Integer numStavesInt =Integer.valueOf(numStavesLabel.getText());
//				if (numStavesInt == 1){
//					deleteStaffButton.setEnabled(true);
//				}
//				numStavesInt =Integer.valueOf(numStavesLabel.getText()) + 1;
//				String numStavesStr = numStavesInt.toString();
//				numStavesLabel.setText("");
//				numStavesLabel.setText(numStavesStr);
//				contentLabel.setText("");
//				contentLabel.setText("My Music Editor. Showing " +  numStavesStr +  " Staves");
//			}
//			
//		});

//		//deleteStaffButton defined at start
//		deleteStaffButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				statusBarLabel.setText("Delete Staff");
//				Integer numStavesInt =Integer.valueOf(numStavesLabel.getText()); 
//				if (numStavesInt>1){
//					numStavesInt =Integer.valueOf(numStavesLabel.getText()) - 1;
//					String numStavesStr = numStavesInt.toString();
//					numStavesLabel.setText("");
//					numStavesLabel.setText(numStavesStr);
//					contentLabel.setText("");
//					if (numStavesInt == 1) {
//						contentLabel.setText("My Music Editor. Showing " +  numStavesStr +  " Staff");
//						deleteStaffButton.setEnabled(false);
//					}else {
//						contentLabel.setText("My Music Editor. Showing " +  numStavesStr +  " Staves");	
//					}
//					
//				}
//				
//				
//				
//			}
//			
//		});
		JPanel staffPanel = new JPanel();
		staffPanel.setLayout(new FlowLayout());
		staffPanel.add(newStaffButton);
		staffPanel.add(deleteStaffButton);

		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("Play");
			}

		});
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("Stop");
			}

		});
		JPanel operationPanel = new JPanel();
		operationPanel.setLayout(new FlowLayout());
		operationPanel.add(playButton);
		operationPanel.add(stopButton);

		// Create the radio buttons.
		JRadioButton noteRadioButton = new JRadioButton("Note");
		noteRadioButton.setActionCommand("Note");
		noteRadioButton.setSelected(true);
		noteRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("Note");
			}

		});

		JRadioButton restRadioButton = new JRadioButton("Rest");
		restRadioButton.setActionCommand("Rest");
		restRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("Rest");
			}

		});

		JRadioButton flatRadioButton = new JRadioButton("Flat");
		flatRadioButton.setActionCommand("Flat");
		flatRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("Flat");
			}

		});

		JRadioButton sharpRadioButton = new JRadioButton("Sharp");
		sharpRadioButton.setActionCommand("Sharp");
		sharpRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusBarLabel.setText("Sharp");
			}

		});

		// Group the radio buttons, it'll take care of
		// deselecting the previously selected button when the user selects another
		// button in the group.
		ButtonGroup group = new ButtonGroup();
		group.add(noteRadioButton);
		group.add(restRadioButton);
		group.add(flatRadioButton);
		group.add(sharpRadioButton);

		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS));
		radioButtonPanel.add(noteRadioButton);
		radioButtonPanel.add(restRadioButton);
		radioButtonPanel.add(flatRadioButton);
		radioButtonPanel.add(sharpRadioButton);

		// Create the Slider
		JSlider notesSlider = new JSlider(JSlider.VERTICAL, 20, 100, 20);
//        notesSlider.setMinorTickSpacing(1);
		notesSlider.setMajorTickSpacing(20);
		notesSlider.setPaintTicks(true);
		notesSlider.setSnapToTicks(true);
		notesSlider.setInverted(true);
		Hashtable labelTable = new Hashtable();
		labelTable.put(new Integer((int) 20), new JLabel("Sixteenth"));
		labelTable.put(new Integer((int) 40), new JLabel("Eighth"));
		labelTable.put(new Integer((int) 60), new JLabel("Quarter"));
		labelTable.put(new Integer(80), new JLabel("Half"));
		labelTable.put(new Integer(100), new JLabel("Whole"));

		notesSlider.setLabelTable(labelTable);
		notesSlider.setPaintLabels(true);

		// JPanel for Radio btns and slider
		JPanel radioSliderPanel = new JPanel();
		radioSliderPanel.setLayout(new FlowLayout());
		radioSliderPanel.add(radioButtonPanel);
		radioSliderPanel.add(notesSlider);

		JPanel leftBoxLayoutPanel = new JPanel();
		leftBoxLayoutPanel.setLayout(new BoxLayout(leftBoxLayoutPanel, BoxLayout.Y_AXIS));
		leftBoxLayoutPanel.add(toolPanel);
		leftBoxLayoutPanel.add(staffPanel);
		leftBoxLayoutPanel.add(operationPanel);
		leftBoxLayoutPanel.add(radioSliderPanel);

		int leftPanelHeight = toolPanel.getHeight() + staffPanel.getHeight() + operationPanel.getHeight()
				+ radioSliderPanel.getHeight();
		leftPanelHeight = (int) (leftPanelHeight + 350);
		leftPanel.setMinimumSize(new Dimension(selectButton.getWidth() + penButton.getWidth(), leftPanelHeight));
		leftPanel.add(leftBoxLayoutPanel);

		frame.add(leftPanel, BorderLayout.WEST);

		/**
		 * Main portion of window, content area
		 */

		contentLabel.setHorizontalAlignment(JLabel.CENTER);
		contentLabel.setVerticalAlignment(JLabel.CENTER);
		contentLabel.setHorizontalTextPosition(JLabel.CENTER);
		contentLabel.setVerticalTextPosition(JLabel.CENTER);

		final int h = leftPanelHeight;

//		MusicView mv  = new MusicView(leftPanelHeight+350, 4);

		MusicView mv = new MusicView(leftPanelHeight + 350, 4);

		// JScrollPane contentLabelScrollPane = new JScrollPane(contentLabel);

		// This makes it so mv is at least this width and resizes and re-draws staves
		// dynamically by lengthening and widening
//		mv.setSize(600, leftPanelHeight+350);
		mv.setSize(600, 600);
//		To tell parent components or layout managers that this is the ideal size
//		mv.setPreferredSize(new Dimension(600, leftPanelHeight+350));
		mv.setPreferredSize(new Dimension(600, 600));

		// mv.setMaximumSize(new Dimension(600, leftPanelHeight+350));
		mv.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				Point p = SwingUtilities.convertPoint(mv, e.getPoint(), mv);
				mv.points.add(p);
				Result result = recog.recognize(mv.points);
				System.out.println("\nGot result: " + result.getName());
				System.out.println("\tScore=" + result.getScore());
				System.out.println("\tBounding Box =" + result.getBoundingBox());
//				updateStatus(result);
//				this.lastResult = result;
				mv.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				mv.setFocusable(true);
				mv.requestFocusInWindow();
				String symbolTypeSelected = mv.checkSymbolSelected(e.getX(), e.getY());
				if (symbolTypeSelected != "none" && symbolTypeSelected != null) {
					// Select note if click is within bounds of a note
//					System.out.println("note selected");	
					// checkSymbolSelected would have highlighted bounds already
				} else {
					// Only add note if click is not within bounds of another note
					String symbolToAdd = group.getSelection().getActionCommand();
					int duration = notesSlider.getValue();
					String pitch = mv.addSymbol(symbolToAdd, duration, e.getX(), e.getY());
					if (pitch != null) {
						statusBarLabel.setText(pitch);
					} else {
						statusBarLabel.setText("Not within range of a pitch");
					}

				}
				
				
				// translate to canvas coordinate system
				ArrayList<Point2D> points = null;
				points = new ArrayList<Point2D>();
				// translate to canvas coordinate system
				Point p = SwingUtilities.convertPoint(mv, e.getPoint(), mv);
				points.add(p);
				Result lastResult = null;
				lastResult = null;
				mv.renderUserStroke(points, p, lastResult);
				
//				mv.repaint();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		mv.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

				if (mv.checkSymbolSelected(e.getX(), e.getY()) == "note") {
					String pitch = mv.dragSymbol(e.getX(), e.getY(), "note");
					if (pitch != null) {
						statusBarLabel.setText(pitch);
					} else {
						statusBarLabel.setText("Not within range of a pitch");
					}
				} else if (mv.checkSymbolSelected(e.getX(), e.getY()) == "rest") {
					String pitch = mv.dragSymbol(e.getX(), e.getY(), "rest");
					if (pitch != null) {
						statusBarLabel.setText(pitch);
					} else {
						statusBarLabel.setText("Not within range of a pitch");
					}
				}
				
				Point p = SwingUtilities.convertPoint(mv, e.getPoint(), mv);
				mv.points.add(p);
				mv.repaint();

			}
		});

		mv.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
//				System.out.println(e.getKeyText(e.getKeyCode()));
//				System.out.println("keytyped");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
//				System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Delete") {
					mv.deleteSymbol();
				}
//				System.out.println("keytyped");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		JScrollPane musicViewScrollPane = new JScrollPane(mv);
		musicViewScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		musicViewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		musicViewScrollPane.setPreferredSize(new Dimension(600, 600));
//		musicViewScrollPane.vertical

		// Add scrollable text area to center of JFrame
		frame.add(musicViewScrollPane, BorderLayout.CENTER);

		// newStaffButton defined at start

		newStaffButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

//				System.out.println("action command: "+ e.getActionCommand());
				statusBarLabel.setText("New Staff");
				int numStaves = mv.addStaff();
				if (numStaves > 1) {
					deleteStaffButton.setEnabled(true);
				}
			}

		});

//		deleteStaffButton defined at start
		deleteStaffButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("action command: "+ e.getActionCommand());
				statusBarLabel.setText("Delete Staff");
//				Integer numStavesInt =Integer.valueOf(numStavesLabel.getText()); 
//				if (numStavesInt>1){
//					numStavesInt =Integer.valueOf(numStavesLabel.getText()) - 1;
//					String numStavesStr = numStavesInt.toString();
//					numStavesLabel.setText("");
//					numStavesLabel.setText(numStavesStr);
//					contentLabel.setText("");
//					if (numStavesInt == 1) {
//						contentLabel.setText("My Music Editor. Showing " +  numStavesStr +  " Staff");
//						deleteStaffButton.setEnabled(false);
//					}else {
//						contentLabel.setText("My Music Editor. Showing " +  numStavesStr +  " Staves");	
//					}
//					
//				}
				int numStaves = mv.deleteStaff();
				if (numStaves == 1) {
					deleteStaffButton.setEnabled(false);
				}
			}

		});

		// leftPanelHeight+200 to ensure enough height for treble cleft to be in right
		// position
		frame.setMinimumSize(new Dimension(leftPanel.getWidth() + 230, leftPanelHeight + 350));
		frame.pack();
		frame.setVisible(true);

		frame.setJMenuBar(menuBar);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();

			}
		});

	}

	{
		// Resize code

//		mv.addComponentListener(new ComponentListener() {
//		
//		@Override
//		public void componentResized(ComponentEvent e) {
//			e.getComponent().setMaximumSize(new Dimension(600, 600+350));
//			e.getComponent().setMaximumSize(new Dimension(600, 600+350));
//		}
//
//		@Override
//		public void componentMoved(ComponentEvent e) {
//			// TODO Auto-generated method stub
//			e.getComponent().setMaximumSize(new Dimension(600, 600+350));
//			e.getComponent().setMaximumSize(new Dimension(600, 600+350));
//		}
//
//		@Override
//		public void componentShown(ComponentEvent e) {
//			// TODO Auto-generated method stub
//			e.getComponent().setMaximumSize(new Dimension(600, 600+350));
//			e.getComponent().setMaximumSize(new Dimension(600, 600+350));
//		}
//
//		@Override
//		public void componentHidden(ComponentEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	});
	}

}
