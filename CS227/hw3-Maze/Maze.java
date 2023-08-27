package hw3;

import maze.MazeObserver;
import maze.Status;

/**
 * A Maze consists of MazeCells arranged in a 2D grid.
 * 
 * @author Noah Hoss
 */
public class Maze {

	/**
	 * Observer to be notified in case of changes to cells in this maze.
	 */
	private MazeObserver observer;

	// Lets create the maze
	private MazeCell[][] maze;
	private MazeCell mazeStart;
	private MazeCell mazeGoal;

	/**
	 * Constructs a maze based on a 2D grid. The given strings, rows, represent rows
	 * of a maze, where '#' represents a wall, a blank represents a possible path,
	 * 'S' represents the starting cell, and '$' represents the goal cell. The maze
	 * must be rectangular, which means the Strings in the given rows must have the
	 * same length. Also, there must be only one start cell and one goal cell in the
	 * maze. For each MazeCell in the maze, set its owner to be the current maze,
	 * its status as GOAL if it is the goal cell, UNVISITED if it is not the goal
	 * nor the wall. For each MazeCell that is accessible (not a wall), its
	 * accessible neighbors MUST be added in the order of top, left, bottom, right.
	 * Cells on the boundary of the maze or near a wall will have fewer accessible
	 * neighbors.
	 * 
	 * @param rows - The maze
	 */
	public Maze(String[] rows) {
		// These are used to check if there are multiple end's or starts.
		int startCount = 0;
		int endCount = 0;
		// return if the maze is not rectangular.
		for (int i = 0; i < rows.length; i++) {
			if (rows[0].length() != rows[i].length()) {
				return;
			}
		}
		// return if there is more than one start point
		for (int x = 0; x < rows.length; x++) {
			int positionY = rows[x].length() - 1;
			for (int y = 0; y <= positionY; y++) {
				if (rows[x].charAt(y) == 'S') {
					mazeStart = new MazeCell(x, y);
					mazeStart.setStatus(Status.UNVISITED);
					startCount++;
					positionY--;

				}
			}
			if (startCount > 1) {
				System.out.println("Given maze has more than one start");
				return;
			}
		}
		// return if there is more than one end point
		for (int x = 0; x < rows.length; x++) {
			int positionY = rows[x].length() - 1;
			for (int y = 0; y <= positionY; y++) {
				if (rows[x].charAt(y) == '$') {
					mazeGoal = new MazeCell(x, y);
					mazeGoal.setStatus(Status.GOAL);
					endCount++;
					positionY--;

				}
			}
			if (endCount > 1) {
				System.out.println("Given maze has more than one end");
				return;
			}
		}

		// We got past checking if the maze is valid! Woo! now let's continue
		// Create a new 2d array of MazeCells that is the correct size
		MazeCell[][] maze = new MazeCell[rows.length][rows[0].length()];
		// let's construct the maze!
		for (int x = 0; x < rows.length; x++) {
			int positionY = rows[0].length();
			for (int y = 0; y < positionY; y++) {
				;
				if (rows[x].charAt(y) == '#') {
					maze[x][y] = new MazeCell(x, y);
					maze[x][y].setOwner(this);
					maze[x][y].setStatus(Status.WALL);
				}
				// If it's the goal...set the status to goal
				else if (rows[x].charAt(y) == '$') {
					maze[x][y] = new MazeCell(x, y);
					maze[x][y].setOwner(this);
					maze[x][y].setStatus(Status.GOAL);
				}
				// If it's anything else, set it as unvisited as stated.
				else if (rows[x].charAt(y) == 'S' || rows[x].charAt(y) == ' ') {
					maze[x][y] = new MazeCell(x, y);
					maze[x][y].setOwner(this);
					maze[x][y].setStatus(Status.UNVISITED);
				}
			}
			positionY--;
		}
		// Let's create neighbors now, I figure it's easier to do after creation because
		// then we can check
		// the status of cells rather than doing it all janky like
		for (int x = 0; x < rows.length; x++) {
			int positionY = rows[0].length();
			for (int y = 0; y < positionY; y++) {
				// If the position is a wall we don't need to add neighbors, so we continue.
				if (rows[x].charAt(y) == '#') {
					continue;
				}
				// If it's anything else, set the neighbors.
				if (rows[x].charAt(y) == 'S' || rows[x].charAt(y) == ' ' || rows[x].charAt(y) == '$') {
					// Value above, if it's not a wall then add it as a neighbor
					if (y - 1 >= 0 && maze[x][y - 1].getStatus() != Status.WALL) {
						maze[x][y].addNeighbor(maze[x][y - 1]);
					}
					// Value to the left, if it's not a wall then add it as a neighbor
					if (x - 1 >= 0 && maze[x - 1][y].getStatus() != Status.WALL) {
						maze[x][y].addNeighbor(maze[x - 1][y]);
					}
					// Value below, if it's not a wall then add it as a neighbor
					if (y + 1 != rows[0].length() && maze[x][y + 1].getStatus() != Status.WALL) {
						maze[x][y].addNeighbor(maze[x][y + 1]);
					}
					// Value to the right, if it's not a wall then add it as a neighbor
					if (x + 1 != rows.length && maze[x + 1][y].getStatus() != Status.WALL) {
						maze[x][y].addNeighbor(maze[x + 1][y]);
					}
				}
				positionY--;
			}
		}

	}

	/**
	 * Returns the cell at the given position
	 * 
	 * @param row - The row
	 * @param col - The column
	 * @return - The cell at given row and column
	 */
	public MazeCell getCell(int row, int col) {
		return maze[row][col];
	}

	/**
	 * Returns the number of rows in this maze.
	 * 
	 * @return row number
	 */
	public int getRows() {
		return maze[0].length;
	}

	/**
	 * Returns the number of columns in this maze.
	 * 
	 * @return column number
	 */
	public int getColumns() {
		return maze.length;
	}

	/**
	 * Returns the start cell for this maze.
	 * 
	 * @return The maze cell
	 */
	public MazeCell getStart() {
		return mazeStart;
	}

	/**
	 * Returns the goal cell for this maze.
	 * 
	 * @return The goal cell.
	 */
	public MazeCell getGoal() {
		return mazeGoal;
	}

	/**
	 * Sets the observer for the cells of this maze.
	 * 
	 * @param obs
	 */
	public void setObserver(MazeObserver obs) {
		observer = obs;
	}

	public void updateStatus(MazeCell cell) {
		if (observer != null) {
			observer.updateStatus(cell);
		}
	}
}