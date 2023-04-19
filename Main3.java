import lejos.hardware.Button;

public class Main3 {

	// Comment
	private static DataExchange DE;
	private static ColorSensor CS;
	private static ObstacleDetector OD;
	private static Motors MO;

	public static void main(String[] args) {

		// Create data exchange object
		DE = new DataExchange();

		// Create line follower thread
		CS = new ColorSensor(DE);

		// Create obstacle detector thread
		OD = new ObstacleDetector(DE);

		// Create motor thread
		MO = new Motors(DE);

		// Start threads
		CS.start();
		OD.start();
		MO.start();

		Button.waitForAnyPress();
		System.exit(0);
	}
}
