//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

import java.util.Iterator;

/**
 *  A single window (sub-window) being displayed in the window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 * @author C.Artis & K. Raven Russel.
 */
public class Window {
	/**
	 *  Default width and height (in pixels) of a square.
	 */
	private static final int SQUARE_SIZE = 20;
	
	/**
	 *  The next id when a new window is created.
	 */
	private static int nextID = 1;
	
	/**
	 *  The X position of the upper left corner of this window.
	 */
	private int upperLeftX;
	
	/**
	 *  The Y position of the upper left corner of this window.
	 */
	private int upperLeftY;
	
	/**
	 *  The width of this window.
	 */
	private int width;
	
	/**
	 *  The height of this window.
	 */
	private int height;
	
	/**
	 *  The color of all squares in this window.
	 */
	private Color color;
	
	/**
	 *  The id of this window.
	 */
	private int id;
	
	/**
	 *  The squares in this window.
	 */
	private SquareList squares;
	
	//You need to save whether or not
	//the window is selected somehow...
	//< YOUR_CODE_HERE >
	/**
	 * Selection of this window.
	 */
	private boolean selected;

	/**
	 *  Initialize a window with the given position,
	 *  dimensions, and color.
	 *  
	 *  @param upperLeftX the X position of the upper left corner
	 *  @param upperLeftY the Y position of the upper left corner
	 *  @param width the width of the window
	 *  @param height the height of the window
	 *  @param c the color of the window
	 */
	public Window (int upperLeftX, int upperLeftY, int width, int height, Color c) {
		this.upperLeftX = upperLeftX;
		this.upperLeftY = upperLeftY;
		this.width = width;
		this.height = height;
		this.color = c;
		this.id = nextID;
		nextID++;
		this.squares = new SquareList();
	}
	
	/**
	 * Sets whether or not this window is currently selected.
	 * @param selected value to set selected to
	 */
	public void setSelected(boolean selected) {
		//sets whether or not this window is currently selected
		//< YOUR_CODE_HERE >
		this.selected = selected;
	}
	
	/**
	 * Gets whether or not this window is currently selected.
	 * @return value of this selected
	 */
	public boolean getSelected() {
		//gets whether or not this window is currently selected
		//< YOUR_CODE_HERE >
		return this.selected; //dummy return, replace this!
	}
	
	/**
	 *  Gets the width of the window.
	 *  
	 *  @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 *  Gets the height of the window.
	 *  
	 *  @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 *  Gets the upper left x position of the window.
	 *  
	 *  @return the upper left x position
	 */
	public int getUpperLeftX() {
		return upperLeftX;
	}
	
	/**
	 *  Gets the upper left y position of the window.
	 *  
	 *  @return the upper left y position
	 */
	public int getUpperLeftY() {
		return upperLeftY;
	}
	
	/**
	 *  Gets the list of squares contained in this window.
	 *  
	 *  @return the list of squares
	 */
	public SquareList getSquares() {
		return squares;
	}
	
	/**
	 * Returns whether or not a given x and y position are contained within this window.
	 * @param x x position
	 * @param y y position
	 * @return boolean, true if x and y is in square
	 */
	public boolean contains (int x, int y) {
		//Returns whether or not a given x and y
		//position are contained within this window.
		
		if ((x >= this.upperLeftX) && (y >= this.upperLeftY)  && (x <= this.upperLeftX+this.width) && (y <= this.upperLeftY+this.height)) {
			return true;
		}
		
		//Note: upper left x and y are _inclusive_.
		
		return false; //dummy return, replace this!
	}
	
	/**
	 *  Sorts the squares in the window by their creation time
	 *  (lower ids were created first).
	 */
	public void sortCreation() {
		squares.sortCreation();
	}
	
	/**
	 *  Sorts squares by their location (same idea as
	 *  sorting windows by locations).
	 */
	public void sortLoc() {
		squares.sortLoc();
	}

	/**
	 *  Determines which of this window's squares cover the pixel 
	 *  coordinate (x,y). If there are any, remove them.  If there were
	 *  none, add a square centered at (x,y) to this window's squares,
	 *  making sure it doesn't extend outside the window.
	 *  Note: this method assumes that this window contains
	 *  the point (x,y). It does not try to handle invalid
	 *  input outside the window.
	 *  
	 *  @param x the x of the position to investigate
	 *  @param y the y of the position to investigate
	 */
	public void handleClick (int x, int y) {
		if (!squares.handleClick (x, y)) {
			int leftDistance = min (SQUARE_SIZE/2, x-upperLeftX);
			int rightDistance = min (SQUARE_SIZE/2, upperLeftX+width-x);
			int upDistance = min (SQUARE_SIZE/2, y-upperLeftY);
			int downDistance = min (SQUARE_SIZE/2, upperLeftY+height-y);
			int xdistance = min (leftDistance, rightDistance);
			int ydistance = min (upDistance, downDistance);
			int size = 2 * min (xdistance, ydistance);
			squares.add (new Square (x, y, size, color));
		}
	}

	/**
	 *  Draws the window by first drawing a white-filled rectangle,
	 *  then drawing the border, then telling all the squares in the 
	 *  window to draw (paint) themselves.	
	 *  
	 *  @param g the graphics to paint on, assumed not null
	 */
	public void paint (Graphics g) {
		g.setColor (Color.white);
		g.fillRect (upperLeftX, upperLeftY, width, height);
		g.setColor (Color.black);
		
		if(getSelected()) {
			Stroke oldStroke = ((Graphics2D) g).getStroke();
			((Graphics2D) g).setStroke(new BasicStroke(2)); //thicker border for selected item
			g.drawRect (upperLeftX, upperLeftY, width, height);
			((Graphics2D) g).setStroke(oldStroke);
		}
		else {
			g.drawRect (upperLeftX, upperLeftY, width, height);
		}
		
		g.setColor(Color.black);
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		((Graphics2D)g).drawString("Squares: "+squares.numSquares(),upperLeftX+10,upperLeftY+20);
		
		Iterator iter = squares.elements ( );
		while (iter.hasNext ( )) {
			Square sq = (Square) iter.next ( );
			sq.paint (g);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals (Object o) {
		if(o instanceof Window) {
			Window r = (Window) o;
			return upperLeftX == r.upperLeftX && 
				upperLeftY == r.upperLeftY && 
				width == r.width && 
				height == r.height;
		}
		return false;
	}
	
	/**
	 *  Returns the larger of a and b.
	 *  
	 *  @param a the first value
	 *  @param b the seconf value
	 *  @return the larger value
	 */
	private static int max (int a, int b) {
		return a>b? a: b;
	}
	
	/**
	 *  Returns the smaller of a and b.
	 *  
	 *  @param a the first value
	 *  @param b the seconf value
	 *  @return the smaller value
	 */
	private static int min (int a, int b) {
		return a<b? a: b;
	}
	/**
	 * Main method.
	 * @param args N/A
	 */
	public static void main(String[] args){

	}
}
