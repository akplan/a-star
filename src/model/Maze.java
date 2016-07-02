package model;

/* TODO:
 * - complete parsemaze and parsepoint functions
 * - figure out what the format is for points
 */

public class Maze {
	private char[][] maze;
	
	// Start and Goal locations are stored in a 2d Array.
	private int[] start;
	private int[] goal;
	
	// Getter/setter functions for each object.
	public char[][] getMaze(){
		return maze;
	}
	
	public void setMaze(char[][] maze){
		this.maze = maze;
	}
	
	public int[] getStart(){
		return start;
	}
	
	public void setStart(int[] start){
		this.start = start;
	}
	
	public int[] getGoal(){
		return goal;
	}
	
	public void setGoal(int[] goal){
		this.goal = goal;
	}
	
	// Constructors for Maze object.
	public Maze(String start, String goal, String filepath){
		setStart(parsePoints(start));
		setGoal(parsePoints(goal));
		setMaze(parseMaze(filepath));
	}
	
	// For parsing the Maze textfile.
	public char[][] parseMaze(String filepath){
		char[][] newMaze = new char[100][100];
		
		return newMaze;
	}
	
	// For parsing Start and Goal points.
	public int[] parsePoints(String point){
		int[] newPoint = new int[2];
		
		return newPoint;
	}
}
