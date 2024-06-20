package hw3;

import java.awt.Point;
import java.util.ArrayList;

import maze.Status;

/**
 * Implementation of MazeCell that has a location in a 2D plane.
 * @author Noah Hoss
 */
public class MazeCell {
	/**
	 * the maze to which this MazeCell belongs.
	 */
	private Maze owner;
	/**
	 * Status of this cell.
	 */
	private Status status;

	/**
	 * Point of this cell
	 */
	private int pointX, pointY;

	/**
	 * Timestamp of this cell
	 */
	private int timeStamp;

	/**
	 * Parent of this cell
	 */
	private MazeCell cellParent;

	/*
	 * ArrayList of MazeCell of the neighbors to this cell
	 */
	private ArrayList<MazeCell> neighbors;

	/**
	 * Constructs a maze cell whose location is specified by the given row and
	 * column, whose status is WALL by default, and whose parent is null. The cell
	 * initially has no neighbors. Its initial time stamp is 0.
	 * 
	 * @param row The row
	 * @param col The column
	 */
	public MazeCell(int row, int col) {
		pointX = row;
		pointY = col;
		status = Status.WALL;
		setParent(null);
		timeStamp = 0;
		neighbors = new ArrayList<MazeCell>();
	}

	/**
	 * Adds an observer for changes in this cell's status.
	 * 
	 * @param maze The maze to which this cell belongs
	 */
	public void setOwner(Maze maze) {
		owner = maze;
	}

	/**
	 * Returns this cell's location as a point, which contains its row and column.
	 * 
	 * @return location
	 */
	public Point getLocation() {
		return new Point(pointX, pointY);
	}

	/*
	 * Adds a neighbor to this cell.
	 * 
	 * @param m The maze cell
	 */
	public void addNeighbor(MazeCell m) {
		neighbors.add(m);
	}

	/**
	 * Returns the neighbors of the current cell. If a cell has no neighbor, the
	 * method must still return an ArrayList, which is empty.
	 * 
	 * @return neighbors
	 */
	public ArrayList<MazeCell> getNeighbors() {
		return neighbors;
	}

	/**
	 * Update the status of this cell and notifies the owner that this current
	 * cell's status has changed
	 * 
	 * @param cell
	 */
	public void setStatus(Status s) {
		status = s;
		if (owner != null) {
			owner.updateStatus(this);
		}
	}

	/**
	 * return the status of the current cell
	 * 
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @return Returns a string representation of this cell's row and column numbers
	 *         enclosed by a pair of parenthesis, e.g., (3, 4), or (10, 0).
	 */
	public String toString() {
		return "(" + pointX + ", " + pointY + ")";

	}

	/**
	 * Gets the parent of this cell. This method returns null if the current cell
	 * has no parent.
	 * 
	 * @return parent cell
	 */
	public MazeCell getParent() {
		return cellParent;
	}

	/**
	 * Sets the parent of this cell with the given parent.
	 * 
	 * @param parent The parent cell
	 */
	public void setParent(MazeCell parent) {
		cellParent = parent;
	}

	/**
	 * Returns the time stamp of this cell
	 * 
	 * @return time stamp
	 */
	public int getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp of this cell
	 * 
	 * @param time time stamp
	 */
	public void setTimeStamp(int time) {
		timeStamp = time;
	}

	/**
	 * Computes the Manhattan distance between this cell and other cell. The
	 * distance between two points measured along axes at right angles. In a plane
	 * with p1 at (x1, y1) and p2 at (x2, y2), it is |x1 - x2| + |y1 - y2|.
	 * 
	 * @param other - other cell
	 * @return the Manhattan distance
	 */
	public int distance(MazeCell other) {
		int rowDis = Math.abs(pointX - other.pointX);
		int colDis = Math.abs(pointY - other.pointY);
		// return the total distance
		return rowDis + colDis;
	}
}