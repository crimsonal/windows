//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.awt.Color;
import java.awt.Graphics;

/**
 *  A single square in a window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 * @author C.Artis & K. Raven Russel.
 */
public class Square {
	/**
	 *  The next id when a new square is created.
	 */
	private static int nextID = 0;
	
	/**
	 *  The X position of the upper left corner of this square.
	 */
	private int upperLeftX;
	
	/**
	 *  The Y position of the upper left corner of this square.
	 */
	private int upperLeftY;
	
	/**
	 *  The size of this square.
	 */
	private int size;
	
	/**
	 *  The color this square.
	 */
	private Color color;
	
	/**
	 *  The id of this square.
	 */
	private int id;
	
	/**
	 *  Initialize a square with the given position (specified 
	 *  relative to the WindowManager in which all this is being
	 *  run), dimensions, and color.
	 *  
	 *  @param centerX the x position of the center of this square
	 *  @param centerY the y position of the center of this square
	 *  @param size the size of this square
	 *  @param c the color of this square
	 */
	public Square (int centerX, int centerY, int size, Color c) {
		this.upperLeftX = centerX - size/2;
		this.upperLeftY = centerY - size/2;
		this.size = size;
		this.color = c;
		this.id = nextID;
		nextID++;
	}

	/**
	 * Returns whether or not a position is contained given x and y (inclusive).
	 * @param x x position
	 * @param y y position
	 * @return boolean if pos is contained withtin square
	 */
	public boolean contains (int x, int y) {
		//Returns whether or not a given x and y
		//position are contained within this square.
		
		if ((x >= this.upperLeftX) && (y >= this.upperLeftY)  && (x <= this.upperLeftX+this.size) && (y <= this.upperLeftY+this.size)) {
			return true;
		}

		//Note: upper left x and y are _inclusive_.
		
		return false; //dummy return, replace this!
	}

	/**
	 *  Fetches the id of this square.
	 *  
	 *  @return the id of this square
	 */
	public int id() {
		return id;
	}
	
	/**
	 *  Gets the upper left x position of the square.
	 *  
	 *  @return the upper left x position
	 */
	public int getUpperLeftX() {
		return upperLeftX;
	}
	
	/**
	 *  Gets the upper left y position of the square.
	 *  
	 *  @return the upper left y position
	 */
	public int getUpperLeftY() {
		return upperLeftY;
	}
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals (Object o) {
		if(o instanceof Square) {
			Square sq = (Square) o;
			return upperLeftX == sq.upperLeftX && 
				upperLeftY == sq.upperLeftY && 
				size == sq.size;
		}
		return false;
	}
	
	/**
	 *  Draw the square, surrounded by a border.
	 *  
	 *  @param g the graphics to paint on, assumed not null
	 */
	public void paint (Graphics g) {
		//Draws a square on the graphics (g) using methods outlined in
		//the project description.
		
		//You should draw a filled square-shaped-rectangle using
		//the color of this square. You should then draw a black
		//border in the same space.
		
		g.setColor(Color.BLACK);
		g.drawRect(this.upperLeftX, this.upperLeftY, this.size, this. size);
		g.setColor(this.color);
		g.fillRect(this.upperLeftX, this.upperLeftY, this.size, this. size);
	}

	/**
	 * Main  method for testing.
	 * @param args N/A
	 */
	public static void main(String[] args) {
		Square sq = new Square(1, 0, 50, Color.BLACK);
		System.out.println(sq.contains(1, 25));
	}
}

