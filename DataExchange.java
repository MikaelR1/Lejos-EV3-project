
	public class DataExchange {

    // ObstacleDetector
    private volatile boolean obstacleDetected = false;
    // avoid obstacle
    private volatile boolean avoidObstacle = false;
    //comment
    // Robot follow commands
    public final static int FOLLOW_LINE = 1;
    public final static int STOP = 2;
    public final static int AVOID = 3;

    private int command = FOLLOW_LINE;
    private float deviation = 0;

    public void setObstacleDetected(boolean status) {
        this.obstacleDetected = status;
    }

    public boolean getObstacleDetected() {
        return obstacleDetected;
    }

    public void setAvoidObstacle(boolean avoid) {
        this.avoidObstacle = avoid;
    }

    public boolean getAvoidObstacle() {
        return avoidObstacle;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getCommand() {
        return command;
    }

    public float getDeviation() {
        return deviation;
    }

    public void setDeviation(float deviation) {
        this.deviation = deviation;
    }
}
