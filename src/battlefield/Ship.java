package battlefield;

public class Ship {
	private String name;
	private int ID;
	private int lenght;
	private int life;
	private boolean alive;
	
	// Constructor
	public Ship(String name, int lenght, int id) {
		this.name = name;
		this.lenght = lenght;
		this.life = lenght;
		this.alive = true;
		this.ID = id;
	}
	
	// return if ship is alive
	public boolean isAlive() {
		return this.alive;
	}
	
	// reduce life of this ship
	public void reduceLife() {
		this.life--;
		if (this.life == 0) {
			this.alive = false;
		}
		return; 
	}
	
	// get lenght
	public int getLenght() {
		return this.lenght;
	}
	
	// get ID
	public int getID() {
		return this.ID;
	}
	
	// get name
	public String getName() {
		return this.name;
	}
}
