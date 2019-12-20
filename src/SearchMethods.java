import java.util.*;

public class SearchMethods
{
	public static void main(String[] args)
	{
		String[][] level1State = new String[][]{{"-", "-", "-", "-"}, {"A", "-", "-", "-"}, {"-", "B", "-", "-"}, {"-", "C", "-", "*"}};
		String[][] level2State = new String[][]{{"-", "-", "-", "-"}, {"A", "-", "-", "-"}, {"-", "B", "-", "-"}, {"-", "-", "C", "*"}};
		String[][] level3State = new String[][]{{"-", "-", "-", "-"}, {"A", "-", "-", "-"}, {"-", "-", "-", "-"}, {"-", "B", "C", "*"}};
		String[][] level4State = new String[][]{{"-", "-", "-", "-"}, {"-", "-", "-", "-"}, {"A", "-", "-", "-"}, {"-", "B", "C", "*"}};

		String[][] defaultStartState = new String[][]{{"-", "-", "-", "-"}, {"-", "-", "-", "-"}, {"-", "-", "-", "-"}, {"A", "B", "C", "*"}};

		// Uncomment whichever algorithm you want to be used
		//breadthFirstSearch(defaultStartState);
		depthFirstSearch(defaultStartState);
		//iterativeDeepening(defaultStartState);
		//aStar(defaultStartState);
	}

	// Runs a breadth first search to find a string of moves to get from the start state to the end state
	private static int breadthFirstSearch(String[][] startState)
	{
		int nodeCount = 0;
		Queue<Node> queue = new LinkedList<>();
		Node node = new Node(startState, "");

		while(!node.isGoalState())
		{
			for(Node child : node.branch())
			{
					queue.add(child);
					nodeCount++;
			}

			node = queue.remove();
			System.out.println("Node count: " + nodeCount + " - Path: " + node.getPath() + " (" + node.getPath().length() + ")");
		}

		System.out.println("DONE");
		System.out.println("Path: " + node.getPath()  + " (" + node.getPath().length() + ")");
		System.out.println("Node Count: " + nodeCount);

		return nodeCount;
	}

	// Runs a depth first search to find a string of moves to get from the start state to the end state
	private static int depthFirstSearch(String[][] startState)
	{
		int nodeCount = 0;
		Stack<Node> stack = new Stack<>();
		Node node = new Node(startState, "");

		while(!node.isGoalState())
		{
			List<Node> children = Arrays.asList(node.branch());
			Collections.shuffle(children);

			for(Node child : children)
			{
				stack.push(child);
				nodeCount++;
			}

			node = stack.pop();
			System.out.println("Node count: " + nodeCount + " - Path: " + node.getPath() + " (" + node.getPath().length() + ")");
		}

		System.out.println("DONE");
		System.out.println("Path: " + node.getPath()  + " (" + node.getPath().length() + ")");
		System.out.println("Node Count: " + nodeCount);

		return nodeCount;
	}

	// Runs a depth first search limited to the depth given to find a string of moves to get from the start state to the end state
	private static int depthLimitedSearch(String[][] startState, int limit, int nodeCount)
	{
		Stack<Node> stack = new Stack<>();
		Node node = new Node(startState, "");

		while(!node.isGoalState())
		{
			if(node.getPath().length() < limit)
			{
				Node[] children = node.branch();

				for(Node child : children)
				{
					stack.push(child);
					nodeCount++;
				}
			}

			if(stack.empty())
			{
				return nodeCount;
			}

			node = stack.pop();
			System.out.println("Node count: " + nodeCount + " - Path: " + node.getPath() + " (" + node.getPath().length() + ") - Limit: " + limit);
		}

		System.out.println("DONE");
		System.out.println("Path: " + node.getPath() + " (" + node.getPath().length() + ")");
		System.out.println("Node Count: " + nodeCount);

		return -1;
	}

	// Runs an iterative deepening search to find a string of moves to get from the start state to the end state
	private static int iterativeDeepening(String[][] startState)
	{
		int limit = 0;
		int nodeCount = 0;
		int totalNodeCount =0;

		while(nodeCount >= 0)
		{
			nodeCount = depthLimitedSearch(startState, limit, nodeCount);
			totalNodeCount += nodeCount;
			limit++;
		}

		return totalNodeCount;
	}

	// Runs an A* search to find a string of moves to get from the start state to the end state
	private static int aStar(String[][] startState)
	{
		int nodeCount = 0;
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		Node node = new Node(startState, "");

		while(!node.isGoalState())
		{
			for(Node child : node.branch())
			{
				nodeCount++;
				priorityQueue.add(child);
			}

			node = priorityQueue.remove();
			System.out.println("Node count: " + nodeCount + " - Path: " + node.getPath() + " (" + node.getPath().length() + ")");
		}

		System.out.println("DONE");
		System.out.println("Path: " + node.getPath() + " (" + node.getPath().length() + ")");
		System.out.println("Node Count: " + nodeCount);

		return nodeCount;
	}
}