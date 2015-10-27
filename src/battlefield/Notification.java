package battlefield;

public class Notification {
	private String message = null;
	public final static String HIT = "HIT";
	public final static String MISS = "MISS";
	public final static String HITSUNK = "HIT & SUNK";
	public final static String SUNK = "SUNK";
	
	// Constructor
	public Notification( String m ) {
		this.message = m;
	}
	
	// get the message
	public String getMessage() {
		return this.message;
	}
}
