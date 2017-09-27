""" File: MazeGraph.py
Author: Susan Fox
Date: August 2014

Contains a program to read in mazes from a file. It assumes that the file has
no blank lines. Each line in the file represents a row of the maze. Each line
must be the same length: the number of columns in the maze. Each character
represents a grid square. Possible qcharacters are either space for an open
square, X for a wall square, S for the starting point, or G for the goal.

This program then constructs an undirected graph to represent the maze"""

from programming2.Graphs import ListGraph
from programming2.FoxQueue import Queue
import sys


def readMaze(filename):
    """Takes in a filename and reads the maze found there. Mazes are a series
    of lines. It is assumed that the outline of the maze will all be filled in;
    any characters before the first X or after the last X is discarded.  It builds
    a list whose contents are each rows of the maze. Each row is represented as a string."""
    inFile = open(filename, 'r')
    mazeRows = []
    for row in inFile:
        mazeRows.append(row.strip())
    return mazeRows



def printMaze(mazeList):
    """Takes in the maze represented as a list of strings (one string per row), and
    it prints the maze as is."""
    for row in mazeList:
        print(row)
        
        

def mazeToGraph(mazeList):
    """Every open square in the maze becomes a node in the graph. It will
    have an edge to every open square that is immediately to the left, right,
    above, or below it. First the program must determine all the open
    squares, giving each an (row, col) coordinate. The node number for each
    square corresponds to the position of its coordinates in the open square
    list."""
    openSquares, startPos, goalPos = collectOpenSquares(mazeList)
    startNode = openSquares.index(startPos)
    goalNode = openSquares.index(goalPos)
    
    
    numOpen = len(openSquares)
    mazeGraph = ListGraph(numOpen)
    
    for sqPos in range(numOpen):
        (r, c) = openSquares[sqPos]
        for neigh in [(r, c - 1), (r, c + 1), (r - 1, c), (r + 1, c)]:
            if neigh in openSquares:
                neighPos = openSquares.index(neigh)
                mazeGraph.addEdge(sqPos, neighPos)
    return mazeGraph, startNode, goalNode



def collectOpenSquares(maze):
    """Given a list of strings representing a maze, this puts together a
    list of all the open squares, marked by either space, S, or G. The list
    contains the (row, col) coordinate of each open square. It also stores and returns
    the (x, y) position of the starting and goal points, as well."""
    openList = []
    startPos = None
    goalPos = None
    for row in range(len(maze)):
        rowStr = maze[row]
        for col in range(len(rowStr)):
            sqVal = rowStr[col]
            if sqVal.upper() in ' SG':
                openList.append( (row, col) )
            if sqVal.upper() == 'S':
                startPos = (row, col)
            elif sqVal.upper() == 'G':
                goalPos = (row, col)
    return openList, startPos, goalPos



def nodeMarkedMaze(mazeList):
    """This is a utility function. It counts the open spaces in the same way as collectOpenSquares,
    but it creates a copy of the maze list of strings, with every open space replaced by a number
    that represents its node number. To keep it compact, only the ones-place digit is stored; 
    you can deduce the rest of the number from context. At the end of each line, it prints the
    last node number occurring on that line, and the node numbers for the start and goal if found."""
    count = 0
    newMazeList = []
    for row in mazeList:
        rowList = []
        startFound = False
        goalFound = False
        for sqVal in row:
            sqVal = sqVal.upper()
            if sqVal == 'X':
                rowList.append(sqVal)
            elif sqVal in " SG":
                val = str(count % 10)
                if sqVal == 'S':
                    val = 'S'
                    startFound = count
                elif sqVal == 'G':
                    val = 'G'
                    goalFound = count
                rowList.append(val)
                count += 1      
            else:
                print("ERROR")
        newRow = "".join(rowList)
        newRow += "     " + str(count - 1)
        if startFound:
            newRow += "    Start at " + str(startFound)
        if goalFound:
            newRow += "    Goal at " + str(goalFound)
        newMazeList.append(newRow)
    return newMazeList



def DFS(graph, startNode, goalNode):
    """Takes in a graph represented as an adjacency list, the node number for the starting point,
    and for the goal point. It computes and returns a path from start to goal (if one exists), using
    the Depth-First Seach algorithm. The search starts from the startNode, and continues only until the
    goalNode is reached. If there is no path from start to goal then None is returned."""
    pass # remove this and put in your code.  Feel free to define more than one function (helpers are...helpful!)
        

  
    
def BFS(graph, startNode, goalNode):
    """Takes in a graph represented as an adjacency list, the node number for the starting point,
    and for the goal point. It computes and returns a path from start to goal (if one exists), using
    the Breadth-First Seach algorithm. The search starts from the startNode, and continues only until the
    goalNode is reached. If there is no path from start to goal then None is returned."""
    pass # remove this and put in your code.  Feel free to define more than one function (helpers are...helpful!)
        



def testMaze(mazefile):
    """Takes a filename as input. It reads the maze from that file, and
    prints it. You can print the node-marked version instead by uncommenting
    the next lines. Next it converts the maze to be a graph, returning the
    graph object and the node numbers of the start and goal nodes. Once you
    have define BFS and DFS, uncomment these lines to test and print the
    result."""

    mz1 = readMaze(mazefile)
    printMaze(mz1)
    #mz2 = nodeMarkedMaze(mz1)
    #printMaze(mz2)
    mzg1, startN, goalN = mazeToGraph(mz1)
    print("StartNode =", startN, "GoalNode =", goalN)
    #path1 = BFS(mzg1, startN, goalN)
    #printPath("BFS", path1)
    #path2 = DFS(mzg1, startN, goalN)
    #printPath("DFS", path2)



def printPath(alg, path):
    """Given a string for the algorithm and the generated path, prints
    the path if there is one, labeled by the algorithm."""
    if path != None:
        print(alg + ": path is", len(path), "long:", path)
    else:
        print(alg + ": No path found!")
    
    
    
    

# For a big maze like the 3rd one, DFS done with recursion will pass the limit
# Python sets.  This next line doubles the depth of recursion Python will allow.
sys.setrecursionlimit(2000)
   
# If you run this file directly in python (not importing it), then the following will be run 
if __name__ == '__main__':
    printMaze(readMaze("Sample Mazes/maze1.txt"))

    # testMaze("maze1.txt")
    # testMaze("maze2.txt")
    # testMaze("maze3.txt")
    # testMaze("maze4.txt")



    
"""
Below I have copied the results of the nameMarkedMaze function, which labels open
spaces by the ones-place digit for the corresponding node in the graph. At the end
of each line it prints the last node value on that line. And it prints the node number
for the start and goal when it finds them.  This can make it easier to trace the path
and check whether your searching algorithms are correct.

Maze 1:
XXXXXXXXXXXXXXXXXXXX     -1
X01234567X890X12345X     15
XX6XXX7XXX8X9XXXXX0X     20
X123X456789X0123456X     36
XX78XXXXX9XXXX0XXXXX     40
X1234567X8901X23456X     56
X7XXXX8XX9XX01X2XXXX     62
XSX45678XX9XX012345X     75    Start at 63
X6X7XXX8X90X12X3XXXX     83
X4X567X890XX1XX23X4X     94
X5XX6XXXXXX789XX0X1X     101
X2345X678X90X1X2345X     115
X6XXXX7X8X9X01XXXX2X     122
X345678X901XX2345G7X     137    Goal at 136
XXXXXXXXXXXXXXXXXXXX     137


Maze 2:
XXXXXXX     -1
XS1234X     4
XXX567X     7
X890X1X     11
X234X5X     15
X6X78GX     19    Goal at 19
XXXXXXX     19


Maze 3:
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX     -1
X0123456789012345678901234XXXXXXXXXXXXXXXXXX567890123456789012XXX345678901234567X     57
X8901234567890123456789012XXXXXXXXXXXXXXXXX3456789012345678901XXXX23456789012345X     115
X67890S2345678901234567890XXXXXXXXXXXXXXXX12345678901234567890XXXX12345678901234X     174    Start at 121
X56789012345X6789012345678XXXXXXXXXXXXXXX901234567890123456789XXXX01234567890123X     233
X4567890123XXX456789012345XXXXXXXXXXXXXX678901234567890123456789012345678901234XX     294
X567890123XXXXX45678901234XXXXXXXXXXXXX5678901234567890123456789012345678901234XX     354
X56789012XXXXXXX3456789012XXXXXXXXXXXX3456789012345678901234567890123456789012XXX     412
X3456789XXXXXXXXX012345678XXXXXXXXXXX90123456789012345678901234567890123456789XXX     469
X012345XXXXXXXXXXX67890123XXXXXXXXXX456789012345678901234567890123456789012345XXX     525
X67890XXXXXXXXXXXXX1234567XXXXXXXXX890123456X789X012X34567X8X90X1234X56X789X01XXX     571
X2345XXXXXXXXXXXXXXX678901XXXXXXXX23456789X0123X456X7890X1X2XX345X678X901X23XXXXX     613
X456XXXXXXXXXXXXXXXXX78901XXXXXXX234567X89012X345678X90123X45X6789X012345X678XXXX     658
X90X12345678901234567X8901XXXXXX23X45678901X2345678901X2345678901X234567X89012XXX     722
X3X4567890123456789012X345XXXXX6789012345678901234X567890123456X789012345678X9XXX     789
X0X1234567890123456789X012XXXX34567890123456789012345678901234XXXXXXXXXXXXXXXXXXX     844
X56X78901234567890123X4567XXX890123456789XXXXXXXXXXXX01234567890XXXXXXXXXXXXXXXXX     890
X123XXXXXXXXXXXXXXXXX45678XXX9012345678901XXXXXXXXXXXXX2345678901234567890123456X     936
X7890XXXXXXXXXXXXXXX123456XX7890123456789012345678901234567890123XXXXXXX45678901X     991
X23456XXXXXXXXXXXXX7890123X45678XXXXXXXXXXXX901234567XXXX890123456XXXXXXXX789012X     1032
X345678XXXXXXXXXXX90123456789012XXXXXXXXXXXX345678901XXX234567890123456789012345X     1085
X6789012XXXXXXXXX345678901234567XXXXXXXXXXXX890123456789012345678901234567890123X     1143
X45678901XXXXXXX2345678901234567XXXXXXXXXXXX890123456789012345678901234567890123X     1203
X456789012XXXXX34567890123456789XXXXXXXXXXXX012345678901234567890123456789012345X     1265
X6789012345XXX678901234567890123XXXXXXXXXXXX456789012345678901234567890123456789X     1329
X01234567890X1234567890123456789XXXXXXXXXXXX012345678901234567890123456789012345X     1395
X6789012345678901234567890123456XXXXXXXXXXXX78901234567XXXXXXXXXX890123456789012X     1452
X3456789012345678901234567890123XXXXXXXXXXXX45678901234X56789012X34567890123XXXXX     1513
X4567890123456789012345678901234XXXXXXXXXXXX56789012345X67890123X45678901234X567X     1577
X8901234567890123456789012345678XXXXXXXXXXXX90123456789X0123X456X78901234567X890X     1640
XXXXXXXXXXXXXXXXXXXX123456789012XXXXXXXXXXXX34567890123X4567X890X123456789G1X234X     1684    Goal at 1680
X5678901234567890123456789012345XXXXXXXXXXXX67890123456X7890X123X45678901234X567X     1747
X8XXXXXXXXXXXXXXXXXX901234567890XXXXXXXXXXXX12345678901X2345X678X90123456789X012X     1792
X345678901234567890X12345678901234567890123456789012345X6789X012X34567890123X456X     1866
XXXXXXXXXXXXXXXXXX7X89012345678901234567890123456789012X3456X789X01234567890X123X     1923
X456789012345678901X23456789012345678901234567890123456X7890X123X4567890123X4567X     1997
X8XXXXXXXXXXXXXXXXXX90123456789012345678901234567890123X4567X890X123456789X01234X     2054
X567890123456789012X34567890123456789012345678901234567X8901X234X56789012X345678X     2128
XXXXXXXXXXXXXXXXXX9X01234567890123456789012345678901234X5678X90123456789X0123456X     2186
X789X012X345X678X90X1234567890123456789012345678901234567890X1234567890X12345678X     2258
X9X012X345X678X901234567890123456789012345678901234567890123X456789012X345678901X     2331
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX     2331


Maze 4:
XXXXXXXXXXXXXXXXXXXX     -1
XS1234567XX89012345X     15
X67890123XX45678901X     31
X23456789XX01234567X     47
X89012345XX67890123X     63
X45678901XX23456789X     79
X01234567XX89012345X     95
X67890123XX45678901X     111
X23456789XX01234567X     127
X89012345XX67890123X     143
X45678901XX23456789X     159
X01234567XX89012345X     175
X67890123XX45678901X     191
X23456789XX0123456GX     207    Goal at 207
XXXXXXXXXXXXXXXXXXXX     207
"""