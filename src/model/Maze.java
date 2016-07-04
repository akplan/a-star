package model;

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
			
			line = reader.readLine();
			for(int i=0; i<newMaze.length && line != null; i++){
				char[] linearray = line.toCharArray();
				newMaze[i] = linearray;
				line = reader.readLine();
			}
			
			fis.close();
			reader.close();
		}catch(FileNotFoundException e1){
			System.out.println("ERROR: file \""+filepath+"\" does not exist.");
			e1.printStackTrace();
			return null;
		}catch(IOException e2){
			System.out.println("Error: IOException");
			e2.printStackTrace();
			return null;
		}
		return newMaze;
	}
	
	// For parsing Start and Goal points.
	public int[] parsePoints(String point){
		int[] newPoint = new int[2];
		return newPoint;
	}
	
	// Prints maze for debug purposes.
	public void printMaze(){
		for(int i = 0; i < this.getMaze().length;i++){
			for(int j=0; j < this.getMaze()[0].length;j++){
				System.out.print(this.getMaze()[i][j]);
			}
			System.out.println();
		}
	}
	
}
