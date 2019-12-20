public class Node implements Comparable<Node>
{
	private String[][] state;
	private String path;

	Node(String[][] grid, String path)
	{
		this.state = grid;
		this.path = path;
	}

	String getPath()
	{
		return this.path;
	}

	// Creates a new 2d array containing the current state of this node
	private String[][] getState()
	{
		String[][] newState = new String[4][4];

		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				newState[i][j] = state[i][j];
			}
		}

		return newState;
	}

	// Prints out the state of this node
	public void print()
	{
		for (String[] y : state)
		{
			for (String x : y)
			{
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}

	// Checks to see if the A, B and C blocks in this nodes state are in the same positions of the goal state
	boolean isGoalState()
	{
		return state[1][1].equals("A") && state[2][1].equals("B") && state[3][1].equals("C");
	}

	// Returns an array of all the children of the current nodes - node wil be null if it would not be valid
	Node[] branch()
	{
		return new Node[] {getChild(2), getChild(3), getChild(0), getChild(1)};
	}

	// Generates child node for the state of the grid after making a left move
	private Node getChild(int direction) // 0-Left, 1-Right, 2-Up, 3-Down
	{
		String[][] currentState = getState();
		String target;

		// Find player position
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				if(currentState[i][j].equals("*"))
				{
					if(direction == 0) // Left
					{
						if(j > 0)
						{
							target = currentState[i][j - 1];
							currentState[i][j - 1] = "*";
							currentState[i][j] = target;

							return new Node(currentState, this.path + "L");
						}
						else
						{
							return new Node(currentState, this.path + "L");
						}
					}
					else if(direction == 1) // Right
					{
						if(j < 3)
						{
							target = currentState[i][j + 1];
							currentState[i][j + 1] = "*";
							currentState[i][j] = target;
							return new Node(currentState, this.path + "R");
						}
						else
						{
							return new Node(currentState, this.path + "R");
						}
					}
					else if(direction == 2) // Up
					{
						if(i > 0)
						{
							target = currentState[i - 1][j];
							currentState[i - 1][j] = "*";
							currentState[i][j] = target;
							return new Node(currentState, this.path + "U");
						}
						else
						{
							return new Node(currentState, this.path + "U");
						}
					}
					else if(direction == 3) // Down
					{
						if(i < 3)
						{
							target = currentState[i + 1][j];
							currentState[i + 1][j] = "*";
							currentState[i][j] = target;
							return new Node(currentState, this.path + "D");
						}
						else
						{
							return new Node(currentState, this.path + "D");
						}
					}
					else
					{
						return null;
					}
				}
			}
		}

		return null;
	}

	// Calculates the minimum number of moves needed to get to the goal state
	private int getHeuristic()
	{
		int ax = 0, ay = 0, bx = 0, by = 0, cx = 0, cy = 0;

		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				switch(state[i][j])
				{
					case "A":
						ax = j;
						ay = i;
						break;
					case "B":
						bx = j;
						by = i;
						break;
					case "C":
						cx = j;
						cy = i;
						break;
				}
			}
		}

		int aDist = Math.abs(ax - 1) + Math.abs(ay - 1);
		int bDist = Math.abs(bx - 1) + Math.abs(by - 2);
		int cDist = Math.abs(cx - 1) + Math.abs(cy - 3);

		return aDist + bDist + cDist;
	}

	@Override
	public int compareTo(Node node)
	{
		int thisScore = this.getPath().length() + this.getHeuristic();
		int nodeScore = node.getPath().length() + node.getHeuristic();

		return Integer.compare(thisScore, nodeScore);
	}
}