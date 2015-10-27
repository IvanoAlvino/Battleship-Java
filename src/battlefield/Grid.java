package battlefield;

public abstract class Grid {
	public int size;
	
	// 0 if nothing, ID if a ship
	public int[][] field = null;
	
	// Constructor
	public Grid(int size) {
		this.size = size;
		field = new int[size][size];
		// initialize field
		for (int r=0; r < size; r++)
			for (int c=0; c < size; c++)
				field[r][c] = 0;
	}
	
	// get size of the Grid
	public int getSize() {
		return this.size;
	}
	
	// abstract class to print the Grid
	public abstract void printGrid();
}
