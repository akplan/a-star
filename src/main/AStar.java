package main;
import model.Maze;

/*
 * Main class for executing A*.
 * Author: Alyssa Plan
 * Date Created: 7/3/2016
 */
public class AStar {
	public static void main(String[] args){
		Maze blah;
		if(args.length < 3) System.out.println("invalid number of args");
		else{
			System.out.println("yay");
			blah = new Maze(args[0],args[1],args[2]);
			blah.printMaze();
		}
	}
}
