# TreeSearchComparison
 Uses various tree search algorithms to solve the Blocksword Tile Puzzle. The number of nodes generated is counted so that the algorithm's complexities can be compared.

 To change which algorithm is used, uncomment the desired line in the main method inside of SearchMethods.java
 - Depth first search is enabled initially


 Algorithms currently implemented:

 - Breadth first search
    The node closest to the root of the tree is explored first until the goal state is achieved.
    Operates using a queue.

 - Depth first search
    One of the children of the most recently expanded nodes is explored first until the goal state is achieved.
    Operates using a stack.
    Which child to explore (out of the 4 possible) is chosen by random to avoid infinitely chosing the same direction wich would never result in reaching the goal state.

 - Depth limited search
    Runs a depth first search (without randomness) but limits it to a set depth. If all nodes at the given depth are explored without reaching the goal state, a depth first is run again from scratch with the depth increased by 1.

 - A* search
    Uses A* tree search with a heuristic to find the goal state.
    Operates using a priority queue with the priority being calculated off of the current depth of the node + the heuristic estimate to how much further it is to the goal state.
    The heuristic function calculates the total combined distance that each of the letter blocks is away from their positions in the goal state.
