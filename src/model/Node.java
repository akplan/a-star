package model;

import java.util.ArrayList;

public class Node {
	
	/* HW 1: 		Implementing A*
	 * Node.java: 	Stores information about cost (movement + heuristic).
	 * Author: 		Alyssa Plan
	 */
	
	private int moveCost;
	private double heuristic;
	private double fullCost;
	Point point;
	
	private Node parent;
	
	// getter/setter methods

	public double getFullCost() {
		return fullCost;
	}

	public void setFullCost(double fullCost) {
		this.fullCost = fullCost;
	}

	public Point getPoint(){
		return point;
	}
	
	public void setPoint(Point point){
		this.point = point;
	}

	public int getMoveCost() {
		return moveCost;
	}

	public void setMoveCost(int moveCost) {
		this.moveCost = moveCost;
	}

	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	// Calculates Euclidean Distance between two nodes.
	public double getEuclideanDistance(Node goal){
			double distance;
			
			int xdistance = Math.abs(this.getPoint().getX()-goal.getPoint().getX());
			int ydistance = Math.abs(this.getPoint().getY()-goal.getPoint().getY());
			
			distance = Math.sqrt((ydistance * ydistance) + (xdistance * xdistance));
			
			return distance;
	}
	
	// Finds all possible neighbors and puts them in a list.
	public ArrayList<Node> getNeighbors(Maze maze){
		ArrayList<Node> neighbors = new ArrayList<Node>();
		int x = this.getPoint().getX();
		int y = this.getPoint().getY();
		
		Point up = new Point(x-1,y);
		Point down = new Point(x+1,y);
		Point left = new Point(x,y-1);
		Point right = new Point(x,y+1);
		
		Point[] points = {up, down, left, right};
		for(int i = 0; i < points.length; i++){
			if(this.getPoint().canMoveThere(points[i], maze)){
				Node neighbor = new Node(points[i]);
				neighbors.add(neighbor);
			}
		}
		
		return neighbors;
	}
	
	// Constructor.
	public Node(Point point){
		this.point = point;
	}
	
	/* Overridden equals(), to ensure that two nodes with the same coordinates
	 * but different costs (from different paths) are considered "equal" */
    @Override
    public boolean equals(Object object)
    {
        boolean isSame = false;

        if (object != null && object instanceof Node)
        {
            isSame = this.getPoint().getX() == ((Node) object).getPoint().getX() && this.getPoint().getY() == ((Node) object).getPoint().getY();
        }

        return isSame;
    }
}