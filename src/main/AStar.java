package main;

import model.Maze;
import model.Node;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* HW 1: Implementing A*
 * AStar.java: Main class for executing A*.
 * Author: Alyssa Plan
 */
public class AStar {

	/* "Hey now, you're an A*, get your game on, go play!
	 * Hey now, you're an A*, get the path now, go! hey!" -- Smash Mouth, A-Star (1999)
	 * 
	 * Implementation of A* Algorithm. Returns a list of all nodes taken.
	 */
	public static ArrayList<Node> AStarMethod2K16(Maze maze, PriorityQueue<Node> openList, ArrayList<Node> closedList){
		ArrayList<Node> path = new ArrayList<Node>();
		Node currentNode;
		openList = new PriorityQueue<Node>(11,new NodeCostComparator());
		closedList = new ArrayList<Node>();
		
		Node start = maze.getStart();
		// adding information on cost, heuristics, total cost
		start.setMoveCost(0);
		start.setHeuristic(start.getEuclideanDistance(maze.getGoal()));
		start.setFullCost(start.getMoveCost() + start.getHeuristic());
		openList.add(start);
		
		while(!openList.isEmpty()){
			currentNode = openList.poll();
			//System.out.println(maze.getGoal().getPoint().toString());
			//System.out.println("Current node: "+currentNode.getPoint().toString());
			if(currentNode.equals(maze.getGoal())){ // reached goal, time to build a path
				Node ptr = currentNode;
				while(ptr != null){
					path.add(ptr);
					ptr = ptr.getParent();
				}
				Collections.reverse(path); // is it worth it? let me work it. put my thing down, flip it and reverse it.
				return path;
			}
			closedList.add(currentNode);
			
			ArrayList<Node> neighbors = currentNode.getNeighbors(maze);
			//if(neighbors.isEmpty()) System.out.println("is empty");
			
			for(Node neighbor : neighbors){
				if(!closedList.contains(neighbor)){ // neighbor doesn't exist in closed list
					
					// calculating cost + setting parent for neighbor node
					neighbor.setMoveCost(currentNode.getMoveCost() + 1);
					neighbor.setHeuristic(neighbor.getEuclideanDistance(maze.getGoal()));
					neighbor.setFullCost(neighbor.getMoveCost() + neighbor.getHeuristic());
					neighbor.setParent(currentNode);
					
					// iterate through openlist, see if it contains the neighbor.
					Node openNode = null;
					Iterator<Node> ptr = openList.iterator();
					
					while(ptr.hasNext()){
						Node pointCompare = ptr.next();
						if(neighbor.equals(pointCompare)){ // is in openList!
							openNode = pointCompare;
							break;
						}
					}
					
					if(openNode == null){
						openList.add(neighbor);
					}else{
						if(neighbor.getMoveCost() < openNode.getMoveCost()){ // found a lower-cost path!
							openNode.setMoveCost(neighbor.getMoveCost());
							openNode.setFullCost(openNode.getMoveCost() + openNode.getHeuristic());
							openNode.setParent(neighbor.getParent());
						}
					}
				}
			}
		}
		return path;
	}

	public static void main(String[] args){
		Maze maze;
		if(args.length < 3 || args[0].equalsIgnoreCase("help")){ // ensure there's the correct number of args
			System.out.println("Format: AStar <file path to maze> <start point> <goal point>");
			System.out.println("Points are formatted as: (<line number>, <column number>)");
			return;
		}
		
		maze = new Maze(args[0],args[1],args[2]);
		if(maze.getMaze() == null || maze.getStart() == null || maze.getGoal() == null){
			System.out.println("Error initializing maze object.");
			return;
		}
		//maze.printMaze();
		PriorityQueue<Node> openList = null;
		ArrayList<Node> closedList = null;
		ArrayList<Node> path = AStarMethod2K16(maze,openList,closedList);


		if(!path.isEmpty()){ // There's a path. Let's print the nodes!
			System.out.print("[");
			for(Node n : path){
				System.out.print(n.getPoint().toString());
				if(path.indexOf(n) != (path.size()-1)) System.out.print(" "); //stops from trailing spaces.
			}
			System.out.println("]");
			//maze.printPath(path); commented out, but displays the path graphically.
		}else{ // path is null, no path is possible.
			System.out.println("no possible path!");
		}
		
	}
}

// Comparator for priority queue. Still kinda salty that it
// insists on an int compare.
class NodeCostComparator implements Comparator<Node>{
	public int compare(Node a, Node b){
		return (int)(a.getFullCost() - b.getFullCost());
	}
}