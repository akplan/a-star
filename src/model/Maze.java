package model;

import model.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/* HW 1: 		Implementing A*
 * Maze.java: 	Stores information about obstacles, and prints the map.
 * Author: 		Alyssa Plan
 */
public class Maze {
	private char[][] maze;
	
	// Start and Goal locations are stored in a 2d Array.
	private Node start;
	private Node goal;
	
	// Getter/setter functions for each object.
	public char[][] getMaze(){
		return maze;
	}
	
	public void setMaze(char[][] maze){
		this.maze = maze;
	}
	
	public Node getStart(){
		return start;
	}
	
	public void setStart(Node start){
		this.start = start;
	}
	
	public Node getGoal(){
		return goal;
	}
	
	public void setGoal(Node goal){
		this.goal = goal;
	}

	
	// Constructors for Maze object.
	public Maze(String filepath, String start, String goal){
		setMaze(parseMaze(filepath));
		setStart(new Node(parsePoints(start)));
		setGoal(new Node(parsePoints(goal)));
		
		if (this.maze == null){ // checks to see if maze was properly initialized
			System.out.println("ERROR initializing Maze.");
		}
	}
	
	// For parsing the Maze text file.
	public char[][] parseMaze(String filepath){
		char[][] newMaze = new char[101][101];
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
			System.out.println("Error: Point is on top of an obstacle and cannot be reached.");
			return null;
		}
		newPoint = new Point(x,y);
		
		return newPoint;
	}
	

	
	// Prints maze for debug purposes.
	public void printMaze(){
		System.out.println("Start: "+getStart().getPoint().toString());
		System.out.println("Goal: "+getGoal().getPoint().toString());
		for(int i = 0; i < this.getMaze().length;i++){
			for(int j = 0; j < this.getMaze()[0].length;j++){
				if(i == getStart().getPoint().getX()-1 && j == getStart().getPoint().getY()-1){
					System.out.print("S");
				}else if(i == getGoal().getPoint().getX()-1 && j == getGoal().getPoint().getY()-1){
					System.out.print("G");
				}else{
					System.out.print(this.getMaze()[i][j]);
				}	
			}
			System.out.println();
		}
	}
	
	/* Prints the A* path taken to get to the goal,
	 * for debug purposes.
	 * I have an easier time visualizing it than
	 * looking at a list of numbers.
	 */
	public void printPath(ArrayList<Node> path){
		System.out.println("Start: "+getStart().getPoint().toString());
		System.out.println("Goal: "+getGoal().getPoint().toString());
		for(int i = 1; i <= this.getMaze().length;i++){
			for(int j = 1; j <= this.getMaze()[0].length;j++){
				Node tmp = new Node(new Point(i,j));
				if(tmp.equals(getStart())){
					System.out.print("S");
				}else if(tmp.equals(getGoal())){
					System.out.print("G");
				}else if(path.contains(tmp)){
					System.out.print("x");
				}else{
					System.out.print(this.getMaze()[i-1][j-1]);
				}	
			}
			System.out.println();
		}
	}
	
}
