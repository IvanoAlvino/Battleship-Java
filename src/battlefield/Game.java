package battlefield;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

	private static Scanner scanIn;

	private static void pressAnyKeyToContinue() {
	        System.out.println("Press any key to continue");
	        try {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	}
	
	private static void playTurn( Player p1, Player p2 ) {
		int x = -1, y = -1;
		
		System.out.println("\n\n\n\n\n");
		System.out.println("Player " + p1.getName() + ": your Turn!");
		
		Notification n = p1.getNotification();
		while (n != null) {
			System.out.println(n.getMessage());
			n = p1.getNotification();
		}
		
		// print Battlefield
		System.out.println("My Battlefield:");
		p1.getBattlefield().printGrid();
		// print Monitor
		System.out.println("\nHUD:");
		p1.getMonitor().printGrid();
		int sunk = 5 - p2.shipsCount();
		System.out.println("\nDestroyed " + sunk + "\t# of ships: " + p2.shipsCount());
		
		boolean correct = false;
		scanIn = new Scanner(System.in); 
		// read coordinates from stdin
		while (!correct) {
			try {
				System.out.print("Shot x: ");
				x = scanIn.nextInt();
				System.out.print("Shot y: ");
				y = scanIn.nextInt();
				if ( x >= 0 && x < 10 && y >= 0 && y < 10)
					correct = true;
			} catch (InputMismatchException ime) {
				System.out.println("Error: insert only integers.");
				scanIn.next();
			}
		}
		
		// shot 
		Notification ans = p2.shot(x, y);
		String message = ans.getMessage();
		System.out.println(message);
		
		// generate notification
		if ( message.compareTo(Notification.HIT) == 0 ) {
			p2.setNotification(new Notification("Ship was hit in " + x + "-" + y));
			// update monitor
			p1.getMonitor().setShot(x, y, true);
		}
		else if ( message.compareTo(Notification.HITSUNK) == 0 ) {
			p2.setNotification(new Notification("Ship was sunk in " + x + "-" + y));
			// update monitor
			p1.getMonitor().setShot(x, y, true);
		}
		else if ( message.compareTo(Notification.MISS) == 0 ) {
			// update monitor
			p1.getMonitor().setShot(x, y, false);
		}
		
		// check if dead
		if ( p2.shipsCount() == 0 ) {
			p2.setDead();
		}
		pressAnyKeyToContinue();
		return;
	}
	
	public static void main(String[] args) {
		
		// Create players
		Player p1 = new Player("Ivano", 10);
		Player p2 = new Player("Maria", 10);
		
		// set ships
		System.out.println("Player 1 place shipment");
		p1.setShips();
		System.out.println("Player 2 place shipment");
		p2.setShips();
		
		// start game
		while (p1.isAlive() && p2.isAlive()) {
			// P1 Turn
			playTurn(p1, p2);
			// P2 Turn
			playTurn(p2, p1);
		}
		
		// somebody won
		if (p1.isAlive()) {
			System.out.println("PLAYER 1 WON!!!\nCongratulations!!");
		}
		else {
			System.out.println("PLAYER 2 WON!!!\nCongratulations!!");
		}
	}

}
