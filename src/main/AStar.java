package main;

import model.Maze;
import java.util.PriorityQueue;

/*
 * Main class for executing A*.
 * Author: Alyssa Plan
 * Date Created: 7/3/2016
 */
public class AStar {
	//public 
	
	public static void main(String[] args){
		Maze maze;
		if(args.length < 3 || args[0].equalsIgnoreCase("help")){
			System.out.println("Format: AStar <file path to maze> <start point> <goal point>");
			System.out.println("Points are formatted as: (<line number>, <column number>)");
		}else{
			maze = new Maze(args[0],args[1],args[2]);
			if(maze.getMaze() == null || maze.getStart() == null || maze.getGoal() == null){
				System.out.println("Error initializing maze object.");
				return;
			}
			maze.printMaze();
		}
	}
}
