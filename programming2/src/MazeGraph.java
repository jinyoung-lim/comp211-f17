/*
 * File: MazeGraph.java
 * Author: Devin Bjelland; based on MazeGraph.py by Susan Fox
 * Date: September 2014
 *
 * Contains a program to read in mazes from a file. It assumes that the file has
 * no blank lines. Each line in the file represents a row of the maze. Each line
 * must be the same length: the number of columns in the maze. Each character
 * represents a grid square. Possible characters are either space for an open
 * square, X for a wall square, S for the starting point, or G for the goal.
 * This program then constructs an undirected graph to represent the maze
 *
 * Modified by JJ Lim, Daniel Lim in September 2017
 *    Speficically, DFS, BFS, reconstructPath methods newly written (using
 *    pseudocodes provided by Prof.Susan Fox), and some other classes are modified
 *    to go along with DFS, BFS, reconstructPath methods.
 *
 * Possible Improvements: This code was translated line by line from Prof. Susan
 * Fox's Python code and thus lacks optimization to Java language. More careful
 * organization of codes and consideration of class and method types would enhance
 * readability and possibly performance.
 */

import sun.rmi.server.InactiveGroupException;

import java.util.*;
import java.io.*;


public class MazeGraph {
    static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this.getClass() != other.getClass()) {
                return false;
            }
            else {
                if (this.x == ((Position) other).x && this.y == ((Position) other).y)
                    return true;
                else
                    return false;
            }
        }
    } // end of Position class


    /**
     * This class holds the information that is passed to the DFS and BFS algorithms.
     * It just contains the startNode, goalNode and the graph. It has simple getters.
     */
    static class ProcessedGraph {
        public int startNode;
        public int goalNode;
        public ListGraph graph;

        public ProcessedGraph(int startNode, int goalNode, ListGraph graph) {
            this.startNode = startNode;
            this.goalNode = goalNode;
            this.graph = graph;
        }

        public int getStartNode() {
            return startNode;
        }

        public int getGoalNode() {
            return goalNode;
        }

        public ListGraph getGraph() {
            return graph;
        }

    } // end of ProcessedGraph class

    static class ProcessedMaze {
        public Position startNode;
        public Position goalNode;
        public ArrayList<Position> openSquares;

        public ProcessedMaze(Position startNode, Position goalNode, ArrayList<Position> openSquares) {
            this.startNode = startNode;
            this.goalNode = goalNode;
            this.openSquares = openSquares;
        }
    }  // end of ProcessedMaze class

    /**
     * Takes in a filename and reads the maze found there. Mazes are a series
     * of lines. It is assumed that the outline of the maze will all be filled in;
     * any characters before the first X or after the last X is discarded.  It builds
     * a list whose contents are each rows of the maze. Each row is represented as a string.
     *
     * @param filename file path of the maze file including the file name
     * @return reading of the maze file in the form of ArrayList of String
     */
    public static ArrayList<String> readMaze(String filename) {
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while((line = br.readLine()) != null) {
                result.add(line.trim());
            }
            br.close();
        } catch(IOException e) {

        }

        return result;
    }

    /**
     * Takes in the maze represented as a list of strings (one string per row), and
     * it prints the maze as is.
     * @param mazelist
     */
    public static void printMaze(ArrayList<String> mazelist) {
        for(String line : mazelist) {
            System.out.println(line);
        }
        System.out.println();
    }

    /**
     * Every open square in the maze becomes a node in the graph. It will
     * have an edge to every open square that is immediately to the left, right,
     * above, or below it. First the program must determine all the open
     * squares, giving each an (row, col) coordinate. The node number for each
     * square corresponds to the position of its coordinates in the open square
     * list.
     * @param mazelist
     * @return
     */
    public static ProcessedGraph mazeToGraph(ArrayList<String> mazelist) {
        ProcessedMaze maze = collectOpenSquares(mazelist);
        if(maze != null) {
            int startNode = maze.openSquares.lastIndexOf(maze.startNode);
            int goalNode = maze.openSquares.lastIndexOf(maze.goalNode);
            int numOpen = maze.openSquares.size();
            ListGraph mazegraph = new ListGraph(numOpen);

            for (int i = 0; i < numOpen; i++) {
                int x = maze.openSquares.get(i).x;
                int y = maze.openSquares.get(i).y;
                for (int j = -1; j <= 1; j += 2) {
                    int k = 0;
                    Position neigh = new Position(x+j, y+k);
                    if (maze.openSquares.contains(neigh)) {
                        int neighIdx = maze.openSquares.lastIndexOf(neigh);
                        mazegraph.addEdge(i, neighIdx);
                    }
                }
                for (int k = -1; k <= 1; k += 2) {
                    int j = 0;
                    Position neigh = new Position(x+j, y+k);
                    if (maze.openSquares.contains(neigh)) {
                        int neighIdx = maze.openSquares.lastIndexOf(neigh);
                        mazegraph.addEdge(i, neighIdx);
                    }
                }

            }

            return new ProcessedGraph(startNode, goalNode, mazegraph);
        } else {
            return null;
        }
    }

    /**
     * Collects open squares from the maze.
     * @param mazelist unprocessed maze
     * @return ProcessedMaze with the positions of start node and goal node, and a list of open nodes
     */
    public static ProcessedMaze collectOpenSquares(ArrayList<String> mazelist) {
        ArrayList<Position> openList = new ArrayList<Position>();
        Position startPos = null;
        Position goalPos = null;
        for (int row = 0; row < mazelist.size(); row++) {
            String rowString = mazelist.get(row);
            for (int col = 0; col < rowString.length(); col++) {
                char val = rowString.charAt(col);
                val = Character.toUpperCase(val);
                if (" SG".indexOf(val) != -1) {
                    openList.add( new Position(row, col));
                }
                if ("S".indexOf(val) != -1) {
                    startPos = new Position(row, col);
                } else if ("G".indexOf(val) != -1) {
                    goalPos = new Position(row, col);
                }
            }
        }
        if (startPos != null && goalPos != null)
            return (new ProcessedMaze(startPos, goalPos, openList));
        else
            return null;
    }

    /**
     * This is a utility function. It counts the open spaces in the same way as collectOpenSquares,
     * but it creates a copy of the maze list of strings, with every open space replaced by a number
     * that represents its node number. To keep it compact, only the ones-place digit is stored;
     * you can deduce the rest of the number from context. At the end of each line, it prints the
     * last node number occurring on that line, and the node numbers for the start and goal if found.
     *
     * @param unmarkedMaze
     * @return marked maze in ArrayList of String
     */
    public static ArrayList<String> nodeMarkedMaze(ArrayList<String> unmarkedMaze) {

        int count = 0;
        ArrayList<String> newMazeList = new ArrayList<String>();
        for (String row : unmarkedMaze) {
            int startFound = -1;
            int goalFound = -1;
            String newrow = "";
            for (int i = 0; i < row.length(); i++) {
                char sqVal = row.charAt(i);
                sqVal = Character.toUpperCase(sqVal);
                if ("X".lastIndexOf(sqVal) != -1) {
                    newrow = newrow + "X";
                } else if(" SG".lastIndexOf(sqVal) != -1) {
                    String val = String.valueOf(count % 10);
                    if ("S".equals(sqVal)) {
                        val = "S";
                        startFound = count;
                    } else if ("G".equals(sqVal)) {
                        val = "G";
                        goalFound = count;
                    }
                    newrow = newrow + val;
                    count++;
                } else {
                    System.out.println("Error");
                }
            }
            newrow = newrow + "     " + (count - 1);
            if (startFound != -1) {
                newrow = newrow + "    Start at " + startFound;
            }
            if (goalFound != -1) {
                newrow = newrow + "    Goal at " + goalFound;
            }

            newMazeList.add(newrow);
        }
        return newMazeList;
    }

    /**
     * Takes in a ProcessedGraph object which contains a graph represented as an adjacency list, the node
     * number for the starting point, and for the goal point. It computes and returns a path from start to
     * goal (if one exists), using the Depth-First Seach algorithm. The search starts from the startNode,
     * and continues only until the goalNode is reached. If there is no path from start to goal then an empty
     * list is returned.
     *
     * @param processedGraph
     * @return
     */
    public static LinkedList<Integer> BFS(ProcessedGraph processedGraph) {
        int startN = processedGraph.getStartNode();
        int goalN = processedGraph.getGoalNode();
        ListGraph listGraph = processedGraph.getGraph();

        HashMap<Integer, Integer> preds = new HashMap<>();
        List<Integer> visited = new ArrayList<>();
        Queue<Integer> frontierQueue = new LinkedList<>();

        preds.put(startN, -1);
        visited.add(startN);
        frontierQueue.add(startN);

        while(!frontierQueue.isEmpty()) {
            int currN = frontierQueue.remove(); // removes top of the stack and returns it

            if (currN == goalN) {
                return reconstructPath(preds, startN, goalN); // found the path
            }
            else {
                List neighs = listGraph.getNeighbors(currN);

                for (int i = 0, size = neighs.size(); i < size; i++) {
                    int neigh = (int)neighs.get(i);
                    if (!visited.contains(neigh)) {
                        visited.add(neigh);
                        preds.put(neigh, currN);
                        frontierQueue.add(neigh);
                    }
                }
            }
        }

        return new LinkedList<Integer>();
    }

    /**
     * Reconstructs the path from goal node to start node using predecessor hash map.
     * @param preds a hash map where the keys are nodes of a graph and value is the predecessor in search
     *             (the back or tree edges)
     * @param startN starting node's integer number
     * @param goalN goal node's integer number
     * @return
     */
    public static LinkedList<Integer> reconstructPath(HashMap<Integer, Integer> preds, int startN, int goalN) {
        int currN = goalN;
        LinkedList<Integer> path = new LinkedList<>();

        while (currN != startN) {
            path.add(0, currN);
            currN = preds.get(currN);
        }
        path.add(0, startN);

        return path;
    }


    /**
     * It computes and returns a path from start to goal (if one exists), using the Breadth-First Seach algorithm.
     * The search starts from the startNode, and continues only until the goalNode is reached. If there is no path
     * from start to goal then an empty list is returned.
     *
     * @param processedGraph contains a graph represented as an adjacency list, the node number for the starting point,
     * and for the goal point.
     * @return a path from start to goal (if one exists), null otherwise
     */
    public static LinkedList<Integer> DFS(ProcessedGraph processedGraph) {
        int startN = processedGraph.getStartNode();
        int goalN = processedGraph.getGoalNode();
        ListGraph listGraph = processedGraph.getGraph();

        HashMap<Integer, Integer> preds = new HashMap<>();
        List<Integer> visited = new ArrayList<>();
        Stack<Integer> frontierStack = new Stack<>();

        preds.put(startN, -1);
        visited.add(startN);
        frontierStack.push(startN);

        while(!frontierStack.isEmpty()) {
            int currN = frontierStack.pop(); // removes top of the stack and returns it

            if (currN == goalN) {
                return reconstructPath(preds, startN, goalN); // found the path
            }
            else {

                List neighs = listGraph.getNeighbors(currN);

                for (int i = 0, size = neighs.size(); i < size; i++) {
                    int neigh = (int)neighs.get(i);
                    if (!visited.contains(neigh)) {
                        visited.add(neigh);
                        preds.put(neigh, currN);
                        frontierStack.push(neigh);
                    }
                }
            }
        }

        return new LinkedList<Integer>();
    }


    /**
     * Takes a filename as input. It reads the maze from that file, and
     * prints it. You can print the node-marked version instead by uncommenting
     * the next lines. Next it converts the maze to be a graph, returning the
     * graph object and the node numbers of the start and goal nodes. Once you
     * have define BFS and DFS, uncomment these lines to test and print the
     * result.
     *
     * @param mazeFile file path of the maze file including the file name
     */
    public static void testMaze(String mazeFile) {
        ArrayList<String> unprocessedMaze = readMaze(mazeFile);         // reads the maze from a file
        printMaze(unprocessedMaze);
        ProcessedGraph processedGraph = mazeToGraph(unprocessedMaze);    // converts the maze to a graph for you
        ArrayList<String> mazeCopy = nodeMarkedMaze(unprocessedMaze);
        printMaze(mazeCopy);
        System.out.println("StartNode=" + processedGraph.startNode + " GoalNode=" + processedGraph.goalNode);  // shows how to access start and goal.
        // To access the graph, you would use processedGraph.graph
        LinkedList<Integer> path1 = DFS(processedGraph);
        printPath("DFS", path1);
        LinkedList<Integer> path2 = BFS(processedGraph);
        printPath("BFS", path2);
        System.out.println();
    }


    /**
     * Prints out the algorithm name given in string and the path of the maze solved
     * with that algorithm.
     * @param alg name of the algorithm in string, "DFS" or "BFS"
     * @param path path of the maze using the algorithm
     */
    public static void printPath(String alg, LinkedList<Integer> path) {
        if (path.size() != 0) {
            System.out.println(alg + ": path is " + path.size() + " long:" + path);
        } else  {
            System.out.println(alg + ": No Path found!");
        }
    }

    /**
     * Main method of the class.
     * @param args
     */
    public static void main(String args[]) {
        // smaller mazes
        testMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze1.txt");
        testMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze2.txt");
        testMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze4.txt");
        testMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze6.txt");

        // larger mazes (uncomment each for testing)
//        testMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze3.txt");
//        testMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze5.txt");
//        testMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze7.txt");
//
//        // same maze with startN and endN positions switched
//        testMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze8.txt");
//        testMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze9.txt");

        //Test the running time on larger mazes (mazes 3, 5, 8) to compare between DFS and BFS.
        System.out.println();
        System.out.println("///////////// RUNNING TIME COMPARISON for Larger Mazes /////////////");
        ProcessedGraph processedGraph3 = mazeToGraph(readMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze3.txt"));
        long maze3DFSStartTime = System.currentTimeMillis();
        DFS(processedGraph3);
        long maze3DFSEndTime = System.currentTimeMillis();
        System.out.println("Total DFS Time for maze3: " + (maze3DFSEndTime-maze3DFSStartTime));
        long maze3BFSStartTime = System.currentTimeMillis();
        BFS(processedGraph3);
        long maze3BFSEndTime = System.currentTimeMillis();
        System.out.println("Total BFS Time for maze3: " + (maze3BFSEndTime-maze3BFSStartTime));
        System.out.println();

        ProcessedGraph processedGraph5 = mazeToGraph(readMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze5.txt"));
        long maze5DFSStartTime = System.currentTimeMillis();
        DFS(processedGraph5);
        long maze5DFSEndTime = System.currentTimeMillis();
        System.out.println("Total DFS Time for maze5: " + (maze5DFSEndTime-maze5DFSStartTime));
        long maze5BFSStartTime = System.currentTimeMillis();
        BFS(processedGraph5);
        long maze5BFSEndTime = System.currentTimeMillis();
        System.out.println("Total BFS Time for maze5: " + (maze5BFSEndTime-maze5BFSStartTime));
        System.out.println();

        ProcessedGraph processedGraph7 = mazeToGraph(readMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze7.txt"));
        long maze7DFSStartTime = System.currentTimeMillis();
        DFS(processedGraph7);
        long maze7DFSEndTime = System.currentTimeMillis();
        System.out.println("Total DFS Time for maze7: " + (maze7DFSEndTime-maze7DFSStartTime));
        long maze7BFSStartTime = System.currentTimeMillis();
        BFS(processedGraph7);
        long maze7BFSEndTime = System.currentTimeMillis();
        System.out.println("Total BFS Time for maze7: " + (maze7BFSEndTime-maze7BFSStartTime));
        System.out.println();

        System.out.println("///////////// RUNNING TIME COMPARISON for Same maze different StartN, GoalN /////////////");
        ProcessedGraph processedGraph8 = mazeToGraph(readMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze8.txt"));
        long maze8DFSStartTime = System.currentTimeMillis();
        DFS(processedGraph8);
        long maze8DFSEndTime = System.currentTimeMillis();
        System.out.println("Total DFS Time for maze8: " + (maze8DFSEndTime-maze8DFSStartTime));
        long maze8BFSStartTime = System.currentTimeMillis();
        BFS(processedGraph8);
        long maze8BFSEndTime = System.currentTimeMillis();
        System.out.println("Total BFS Time for maze8: " + (maze8BFSEndTime-maze8BFSStartTime));
        System.out.println();

        ProcessedGraph processedGraph9 = mazeToGraph(readMaze("/Users/JJ/IdeaProjects/comp221-f17/comp211-f17/programming2/src/maze9.txt"));
        long maze9DFSStartTime = System.currentTimeMillis();
        DFS(processedGraph9);
        long maze9DFSEndTime = System.currentTimeMillis();
        System.out.println("Total DFS Time for maze9: " + (maze9DFSEndTime-maze9DFSStartTime));
        long maze9BFSStartTime = System.currentTimeMillis();
        BFS(processedGraph9);
        long maze9BFSEndTime = System.currentTimeMillis();
        System.out.println("Total BFS Time for maze9: " + (maze9BFSEndTime-maze9BFSStartTime));
        System.out.println();
    }
}
