package model;

import model.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* TODO:
 * - complete parse point functions
 * - figure out what the format is for points
 */

public class Maze {
	private char[][] maze;
	
	// Start and Goal locations are stored in a 2d Array.
	private Point start;
	private Point goal;
	private Point current;
	
	// Getter/setter functions for each object.
	public char[][] getMaze(){
		return maze;
	}
	
	public void setMaze(char[][] maze){
		this.maze = maze;
	}
	
	public Point getStart(){
		return start;
	}
	
	public void setStart(Point start){
		this.start = start;
	}
	
	public Point getGoal(){
		return goal;
	}
	
	public void setGoal(Point goal){
		this.goal = goal;
	}
	
	public Point getCurrent(){
		return current;
	}
	
	public void setCurrent(Point current){
		this.current = current;
	}
	
	// Constructors for Maze object.
	public Maze(String filepath, String start, String goal){
		setMaze(parseMaze(filepath));
		setStart(parsePoints(start));
		setGoal(parsePoints(goal));
		setCurrent(getStart());
		
		if (this.maze == null){ // checks to see if maze was properly initialized
			System.out.println("ERROR initializing Maze.");
		}
	}
	
	// For parsing the Maze text file.
	public char[][] parseMaze(String filepath){
		char[][] newMaze = new char[100][100];
		FileInputStream fis;
		BufferedReader reader;
		
		// initializes FileInputStream
		try{
			fis = new FileInputStream(filepath);
			reader = new BufferedReader(new InputStreamReader(fis));
			String line;
			
			//Reads the input in one line at a time
			line = reader.readLine();
			for(int i=0; i<newMaze.length && line != null; i++){
				char[] linearray = line.toCharArray();
				newMaze[i] = linearray;
				line = reader.readLine();
			}
			
			fis.close();
			reader.close();
		}catch(FileNotFoundException e1){ // file can't be found/don't have permission to access file
			System.out.println("ERROR: file \""+filepath+"\" does not exist.");
			e1.printStackTrace();
			return null;
		}catch(IOException e2){ // IOException
			System.out.println("Error: IOException");
			e2.printStackTrace();
			return null;
		}
		return newMaze;
	}
	
	// For parsing Start and Goal points.
	// Points are formatted as such: (x,y)
	public Point parsePoints(String point){
		Point newPoint;
		int x, y;
		
		// Check #1: See if input is properly formatted.
		
		// Check if input contains properly closed parenthesis.
		if(point.charAt(0) != '(' || point.charAt(point.length()-1) != ')'){
			System.out.println("Error: point is improperly formatted.");
			return null;
		}
		String[] parsepoint = point.substring(1,point.length()-1).split(",");
		
		// Check if input is comma separated and input is not larger than two points.
		if (parsepoint.length != 2){
			System.out.println("Error: point is improperly formatted.");
			return null;
		}
		
		// Check if input is an integer.
		try{
			x = Integer.valueOf(parsepoint[0]);
			y = Integer.valueOf(parsepoint[1]);
		}catch(NumberFormatException e){
			System.out.println("Error: point is not an integer.");
			e.printStackTrace();
			return null;
		}
		
		// Check #2: Affirm that the numbers are valid input.
		
		// Check if point is out of bounds.
		if(x < 1 || x > 101 || y < 1 || y > 101){
			System.out.println("Error: Point specified is out of bounds.");
			return null;
		}
		
		// Check if point can be reached (point is not on an obstacle.)
		if(this.getMaze()[x-1][y-1] == '#'){
			System.out.println("Error: Point cannot be reached.");
			return null;
		}
		newPoint = new Point(x-1,y-1);
		
		return newPoint;
	}
	

	
	// Prints maze for debug purposes.
	public void printMaze(){
		System.out.println("Start: "+getStart().toString());
		System.out.println("Goal: "+getGoal().toString());
		for(int i = 0; i < this.getMaze().length;i++){
			for(int j=0; j < this.getMaze()[0].length;j++){
				if(i == getStart().getX() && j == getStart().getY()){
					System.out.print("S");
				}else if(i == getGoal().getX() && j == getGoal().getY()){
					System.out.print("G");
				}else{
					System.out.print(this.getMaze()[i][j]);
				}	
			}
			System.out.println();
		}
	}
	
	public boolean move(int x, int y){
		if(!current.canMoveThere(x, y, this)){
			return false;
		}else{
			current = new Point(x,y);
			return true;
		}
	}
	
}
