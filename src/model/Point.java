package model;

import model.Maze;

public class Point {

	private int x;
	private int y;
	private double cost;
	
	// Constructor for Point object.
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, double cost){
		this.x = x;
		this.y = y;
		this.cost = cost;
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
	
	public double getCost(){
		return cost;
	}
	
	public void setCost(double cost){
		this.cost = cost;
	}	
	
	// Calculates Euclidean Distance between two points.
	public double getEuclideanDistance(Point a, Point b){
		double distance;
		
		int xdistance = Math.abs(a.getX()-b.getX());
		int ydistance = Math.abs(a.getY()-b.getY());
		
		distance = Math.sqrt((ydistance * ydistance) + (xdistance * xdistance));
		
		return distance;
	}
	
	public double calculateCost(Point current){
		return 1 + getEuclideanDistance(this,current);
	}
	
	//The toString adds +1 to each coordinate because it's indexed at 1.
	public String toString(){
		return "("+(getX()+1)+","+(getY()+1)+")";
	}
	
	// checks to see whether a point can move to the specified area.
	// int x and y are assumed indexed at 0.
	public boolean canMoveThere(int x, int y, Maze maze){
		// Check if desired point is out of bounds.
		if(x < 0 || x > 100 || y < 0 || y > 100){ // specified x/y is out of bounds
			return false;
		}else if(Math.abs(Math.abs(getX())-x) > 1 || Math.abs(Math.abs(getY()+1)) > 1){ // desired point is greater than one step
			return false;
		}else if(Math.abs(Math.abs(getX())-x) == 1 && Math.abs(Math.abs(getY()+1)) == 1){ // desired point is an illegal diagonal move
			return false;
		}else if(maze.getMaze()[x][y] == '#'){
			return false;
		}else{
			return true;
		}
	}
	
}
