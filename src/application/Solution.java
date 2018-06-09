/**
 *  @author Jacob Schwartz
 *  
 *  @date 4/29/2018
 *  
 *  @purpose Solve 3x3 grid puzzle using BFS
 */

package application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * 
 * @purpose Store variations in a queue along with its possible moves in tree formation
 *
 */

class Node {
		
		public int[] data;
		public Node parent;
		public int move;
		
		public Node(int[] data) {
			this.data = data;
		}
		
		public Node() {
			//Do Nothing
		}
		
		
	}

public class Solution {

	/******************************************   Implementation Here  ***************************************/

	/*			Implementation here: you need to implement the Breadth First Search Method				 				*/
	/*			Please refer the instruction document for this function in details								     	*/
	public static void breadthFirstSearch(int[] graph, int m, Vector<Integer> solution) {
		PathQueue<Node> queue = new PathQueue<Node>(); //Queue to store all possible moves to solve the puzzle
		ArrayList<int[]> foundList = new ArrayList<int[]>(); //Store previously found solutions to eliminate duplication
		
		int missing = location(graph, m); //Identify location of missing piece
		
		boolean found = false;
		Node root = new Node(graph); 
		root.move = missing;
		Node correctPath = new Node(); //Will be replaced by final node in correct solutions path
		
		if(complete(graph)) { //Determine if provided graph is in correct placement
			found = true;
			correctPath = root;
		}
		
		queue.add(root);
		foundList.add(root.data);
		
		while(!found) { //Use BFS to create and determine best possible solution
			Node cur = queue.remove();
			int[] possible = getAdj(cur.move);
			
			for(int pos : possible) { //Loop through all possible moves
				int[] temp = new int[cur.data.length];
				System.arraycopy(cur.data, 0, temp, 0, cur.data.length);
				
				swap(temp, cur.move, pos);
				Node newNode = new Node(temp);
				
				if(!checkList(newNode, foundList)) { //Only test if they have not been previously tested
					newNode.parent = cur;
					newNode.move = pos;
					queue.add(newNode);
					foundList.add(newNode.data);
					
					if(complete(newNode.data)) {
						found = true;
						correctPath = newNode;
					}
				}
			}
		}
		
		Node path = correctPath;
		while(path != root) { //Move backwards from the correct solution to create the solution path
			solution.add(0, path.move);
			path = path.parent;
		}
		solution.add(0, missing);
	}
	
	/**
	 * @purpose 
	 * 		Test if provided order is correct
	 * 
	 * @param arr
	 * 		Provided array to be tested
	 * 
	 * @return
	 * 		Whether or not the answer has been found
	 */
	
	public static boolean complete(int[] arr) {
		int[] correct = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		
		if(Arrays.equals(arr, correct)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * @purpose
	 * 		Determine if the provided setup has been previously found
	 * 
	 * @param node
	 * 		Provided graph
	 * 
	 * @param found
	 * 		List of all previously found setups
	 * 
	 * @return
	 * 		If the setup is new
	 */
	
	public static boolean checkList(Node node, ArrayList<int[]> found) {
		for(int i = 0; i < found.size(); i++) {
			if(Arrays.equals(node.data, found.get(i))) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @purpose
	 * 		Find the pieces location in the array
	 * 
	 * @param graph
	 * 		Array the number must be found in
	 * 
	 * @param num
	 * 		Piece number
	 * 
	 * @return
	 * 		Location of the piece in the provided array
	 */
	
	private static int location(int[] graph, int num) {
		for(int i = 0; i < 9; i++) {
			if(graph[i] == num) {
				return i;
			}
		}
		
		return num;
	}
	
	/**
	 * @purpose
	 * 		Swap location of two pieces in an array
	 * 
	 * @param arr
	 * 		Provided array
	 * 
	 * @param x & y
	 * 		Pieces to be swapped
	 */
	
	private static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	/**
	 * @purpose
	 * 		Find all positions a selection can swap with
	 * 
	 * @param pos
	 * 		Position of the current index
	 * 
	 * @return
	 *		Possible swap locations
	 */
	
	private static int[] getAdj(int pos) {
		int[] connections0 = {1,3};
		int[] connections1 = {0,2,4};
		int[] connections2 = {1,5};
		int[] connections3 = {0,4,6};
		int[] connections4 = {1,3,5,7};
		int[] connections5 = {2,4,8};
		int[] connections6 = {3,7};
		int[] connections7 = {4,6,8};
		int[] connections8 = {5,7};
		
		switch(pos) {
			case 0:
				return connections0;
			case 1:
				return connections1;
			case 2:
				return connections2;
			case 3:
				return connections3;
			case 4:
				return connections4;
			case 5:
				return connections5;
			case 6:
				return connections6;
			case 7:
				return connections7;
			case 8:
				return connections8;
			default: 
				return null;
		}
		
		
	}
}
