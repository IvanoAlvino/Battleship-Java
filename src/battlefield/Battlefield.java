package battlefield;

public class Battlefield extends Grid {

	// Constructor
	public Battlefield(int size) {
		super(size);
	}

	// print special Grid: ' ' if sea, ID if ship or X if already hit
	@Override
	public void printGrid() {
		int lineID;
		// print column ID
		System.out.print(" 0123456789");
		System.out.print("\n0");
		for (int r=0; r < super.size; r++) {
			for (int c=0; c < super.size; c++) {
				// print space if nothing, S if ship
				if ( super.field[r][c] == 0)
					System.out.print('-');
				else if ( super.field[r][c] > 0)
					System.out.print(super.field[r][c]);
				else System.out.print("X");
			}
			lineID = r+1;
			if (lineID < size)
				System.out.print("\n" + lineID);
		}
		System.out.println();
	}
	
	// place a ship over the battlefield
	// Return true if placed, false otherwise
	public boolean placeShip( Ship s, int x, int y, boolean horizontal) {
		// check if out of boundaries
		if ( x<0 || y<0 || x>=size || y>=size) return false;
		// check if ship already present
		if (horizontal) {
			for (int c=y; c < y + s.getLenght(); c++)
				if ( c >= size || field[x][c] != 0)
					return false;
		}
		else {
			for (int r=x; r < x + s.getLenght(); r++)
				if ( r >= size || field[r][y] != 0)
					return false;
		}
		
		// I can put the new ship
		if (horizontal) {
			for (int c=y; c < y + s.getLenght(); c++)
				field[x][c] = s.getID();
		}
		else {
			for (int r=x; r < x + s.getLenght(); r++)
				field[r][y] = s.getID();
		}
		return true;
	}
	
	public void markHit(int x, int y) {
		field[x][y] = -2;
	}
	
	// return the ID of the ship is present, 
	public int shipPresent( int x, int y ) {
		if ( field[x][y] == 0 ) return -1;
		else return field[x][y];
	}

}
