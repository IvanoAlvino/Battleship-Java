package battlefield;

public class Monitor extends Grid {

	// Constructor
	public Monitor(int size) {
		super(size);
	}

	// print special Grid: ' ' if never shot, '0' if for misses, 'X' for hits
	@Override
	public void printGrid() {
		int lineID;
		// print column ID
		System.out.print(" 0123456789");
		System.out.print("\n0");
		for (int r=0; r < size; r++) {
			for (int c=0; c < size; c++) {
				// print space if never used
				if ( field[r][c] == 0)
					System.out.print('-');
				// O if shot
				else if ( field[r][c] == -1)
					System.out.print('0');
				// X if ship is hit
				else if ( field[r][c] == 1 )
					System.out.print('X');
			}
			lineID = r+1;
			if (lineID < size)
				System.out.print("\n" + lineID);
		}
		System.out.println();
	}
	
	// return if already shot in this coordinates
	public boolean alreadyShot(int x, int y) {
		if ( field[x][y] == 0 ) return false;
		return true;
	}
	
	// set a shot in the field based on hit or miss
	public void setShot( int x, int y, boolean hit) {
		if (hit)
			field[x][y] = 1;
		else field[x][y] = -1;
		return;
	}

}
