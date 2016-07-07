package model;

import model.Maze;


/* HW 1: 		Implementing A*
 * Point.java: 	Stores information about location.
 * Author: 		Alyssa Plan
 */
public class Point {

	private int x;
	private int y;
	
	// Constructor for Point object.
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// Getter/Setter methods for Point object.
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public String toString(){
		return "("+(getX())+","+(getY())+")";
	}
	
	// checks to see whether a point can move to the specified area.
	// int x and y are assumed indexed at 1.
	public boolean canMoveThere(int x, int y, Maze maze){
		// Check if desired point is out of bounds.
		if(x < 1 || x > 101 || y < 1 || y > 101){ // specified x/y is out of bounds
			//System.out.println("out of bounds");
			return false;
		}else if(Math.abs(getX()-x) > 1 || Math.abs(getY()-y) > 1){ // desired point is greater than one step
			//System.out.println("greater than 1 step");
			return false;
		}else if((Math.abs(getX()-x) == 1) && (Math.abs(getY()-y) == 1)){ // desired point is an illegal diagonal move
			//System.out.println("diagonal");
			return false;
		}else if(maze.getMaze()[x-1][y-1] == '#'){
			//System.out.println("obstacle");
			return false;
		}else{
			return true;
		}
	}
	
	// Prettier constructor.
	public boolean canMoveThere(Point point, Maze maze){
		return canMoveThere(point.getX(),point.getY(),maze);
	}
	
}
