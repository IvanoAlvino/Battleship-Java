package battlefield;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class Player {
	private String name;
	private Battlefield battlefield;
	private Monitor monitor;
	private boolean alive;
	private Stack<Notification> notifications;
	private Ship[] ships = new Ship[5];
	private static Scanner scanIn;
	
	// Constructor
	public Player(String n, int size) {
		this.name = n;
		this.battlefield = new Battlefield(size);
		this.monitor = new Monitor(size);
		this.alive = true;
		this.notifications = new Stack<Notification>();
		this.ships[0] = new Ship("Aircraft Carrier", 5, 1);
		this.ships[1] = new Ship("Battleship", 4, 2);
		this.ships[2] = new Ship("Submarine", 3, 3);
		this.ships[3] = new Ship("Cruiser", 2, 4);
		this.ships[4] = new Ship("Patrol Boat", 1, 5);
	}
	
	/* shot received, return a notification informing what happened */
	public Notification shot(int x, int y) {
		// return a notification informing if hit, miss, hit&sunk 
		int outcome = this.battlefield.shipPresent(x, y) -1;
		
		if ( outcome >= 0) {
			// hit
			this.battlefield.markHit(x, y);
			// check if also sunk
			ships[outcome].reduceLife();
			// Ship is still alive
			if ( ships[outcome].isAlive() )
				return new Notification(Notification.HIT);
			// Ship is dead
			else return new Notification(Notification.HITSUNK);
		}
		return new Notification(Notification.MISS);
	}
	
	/* getter for the battlefield, showing where this player has ships */
	public Battlefield getBattlefield() {
		return this.battlefield;
	}
	
	/* getter for the monitor, showing where this player has already shot */
	public Monitor getMonitor() {
		return this.monitor;
	}
	
	// set as dead
	public void setDead() {
		this.alive = false;
	}
	
	/* count the number of the ships */
	public int shipsCount() {
		int count = 0;
		for (int i=0; i < ships.length; i++)
			if ( ships[i].isAlive() ) 
				count++;
		return count;
	}
	
	/* set a new notification for this player */
	public void setNotification( Notification n ) {
		this.notifications.push(n);
		return;
	}
	
	/* retrieve a new Notification if present */
	public Notification getNotification() {
		if ( this.notifications.empty() )
			return null;
		else 
			return this.notifications.pop();
	}
	
	/* place all ships in the battlefield */
	public void setShips() {
		int x, y, hor;
		boolean horizontal;
		scanIn = new Scanner(System.in);
		boolean placed = false;
		// place every ship
		for ( int i=0; i < ships.length; i++) {
			placed = false;
			horizontal = false;
			while ( !placed ) {
				try {
					System.out.println("Ship " + ships[i].getName() + " x: ");
					x = scanIn.nextInt();
					System.out.println("Ship " + ships[i].getName() + " y: ");
					y = scanIn.nextInt();
					System.out.println("Ship " + ships[i].getName() + " horizontal(1/0): ");
					hor = scanIn.nextInt();
					if ( hor == 1) {
						horizontal = true;
					}
					placed = this.battlefield.placeShip(ships[i], x, y, horizontal);
					if (placed) 
						System.out.println("Ship " + ships[i].getName() + " placed in "+x+"-"+y+"\n--------------");
				} catch (InputMismatchException ime) {
					System.out.println("Error, insert only integers");
					scanIn.next();
				}
			}
		}
		System.out.println("Battlefield");
		this.battlefield.printGrid();
		return;
	}
	
	/* return if the player is alive or not */
	public boolean isAlive() {
		return this.alive;
	}
	
	/* return name of player */
	public String getName() {
		return this.name;
	}
}