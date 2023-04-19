/**
 * A class for exchanging data between different parts of the robot program.
 */
public class DataExchange {


	private int command = FOLLOW_LINE; // The current robot command
	private float deviation = 0; // The deviation of the robot from the line

	/**
	 * Sets the obstacle detection status.
	 * 
	 * @param status the new status of obstacle detection
	 */
	public void setObstacleDetected(boolean status) {
		this.obstacleDetected = status;
	}

	/**
	 * Returns the obstacle detection status.
	 * 
	 * @return true if obstacle is detected, false otherwise
	 */
	public boolean getObstacleDetected() {
		return obstacleDetected;
	}

	/**
	 * Sets the avoidance status of obstacle.
	 * 
	 * @param avoid the new avoidance status of obstacle
	 */
	public void setAvoidObstacle(boolean avoid) {
		this.avoidObstacle = avoid;
	}

	/**
	 * Returns the avoidance status of obstacle.
	 * 
	 * @return true if obstacle avoidance is enabled, false otherwise
	 */
	public boolean getAvoidObstacle() {
		return avoidObstacle;
	}

	/**
	 * Sets the current robot command.
	 * 
	 * @param command the new command for the robot to execute
	 */
	public void setCommand(int command) {
		this.command = command;
	}

	/**
	 * Returns the current robot command.
	 * 
	 * @return the current robot command
	 */
	public int getCommand() {
		return command;
	}

	/**
	 * Returns the deviation of the robot from the line.
	 * 
	 * @return the deviation of the robot from the line
	 */
	public float getDeviation() {
		return deviation;
	}

	/**
	 * Sets the deviation of the robot from the line.
	 * 
	 * @param deviation the new deviation of the robot from the line
	 */
	public void setDeviation(float deviation) {
		this.deviation = deviation;
	}
}