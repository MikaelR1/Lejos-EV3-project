import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class Motors extends Thread {

<<<<<<< HEAD
    private DataExchange DE;
    private EV3ColorSensor cs;
    private EV3LargeRegulatedMotor leftMotor;
    private EV3LargeRegulatedMotor rightMotor;
    private final int ACCELERATION = 1000;

    public Motors(DataExchange dataExchange) {
        this.DE = dataExchange;
        leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    }

    public Motors(EV3ColorSensor cs) {
        this.cs = cs;
        leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    }

    @Override
    public void run() {

        while (true) {

            switch (DE.getCommand()) {

                case DataExchange.FOLLOW_LINE: // Follow line

                    leftMotor.setAcceleration(ACCELERATION);
                    rightMotor.setAcceleration(ACCELERATION);

                    while (DE.getCommand() == DataExchange.FOLLOW_LINE) {
                    	System.out.println(DE.getDeviation());
                        leftMotor.setSpeed(200 + (int) (DE.getDeviation() * 500));
                        rightMotor.setSpeed(200 - (int) (DE.getDeviation() * 500));
                        leftMotor.forward();
                        rightMotor.forward();
                    }
                    break;

                case DataExchange.STOP: // Stop

                    leftMotor.stop();
                    rightMotor.stop();
                    break;

                case DataExchange.AVOID: // Avoid obstacle

                    DE.setObstacleDetected(false); // Reset obstacle detection flag
                    rightMotor.rotate(180);
                    rightMotor.stop();
                    leftMotor.setSpeed(100);
                    rightMotor.setSpeed(50);
                    leftMotor.forward();
                    rightMotor.forward();

                    if(DE.getDeviation() <= 0.20)
                    	DE.setCommand(1);
                    
                    while (!DE.getObstacleDetected()) {
                        // Keep rotating until obstacle is detected
                    }

                    leftMotor.stop();
                    rightMotor.stop(); // Stop after avoiding obstacle
                    break;
            }
        }
    }
}
=======
	private DataExchange dataExchange;
	private EV3ColorSensor cs;
	private EV3LargeRegulatedMotor leftMotor;
	private EV3LargeRegulatedMotor rightMotor;
	private final int FORWARD_SPEED = 200;
	private final int ACCELERATION = 1000;

	public Motors(DataExchange dataExchange) {
		this.dataExchange = dataExchange;
		leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	}

	public Motors(EV3ColorSensor cs) {
		this.cs = cs;
		leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	}

	@Override
	public void run() {

		while (true) {

			switch (dataExchange.getCommand()) {

			case DataExchange.FOLLOW_LINE: // Follow line

				leftMotor.setAcceleration(ACCELERATION);
				rightMotor.setAcceleration(ACCELERATION);

				while (dataExchange.getCommand() == DataExchange.FOLLOW_LINE) {
					System.out.println(dataExchange.getDeviation());
					leftMotor.setSpeed(200 + (int) (dataExchange.getDeviation() * 500));
					rightMotor.setSpeed(200 - (int) (dataExchange.getDeviation() * 500));
					leftMotor.forward();
					rightMotor.forward();
				}
				break;

			case DataExchange.STOP: // Stop

				leftMotor.stop();
				rightMotor.stop();
				break;

			case DataExchange.AVOID: // Avoid obstacle

				dataExchange.setObstacleDetected(false); // Reset obstacle detection flag
				leftMotor.rotate(-180);
				rightMotor.rotate(180);
				leftMotor.stop();
				rightMotor.stop();
				leftMotor.setSpeed(200);
				rightMotor.setSpeed(200);
				leftMotor.forward();
				rightMotor.forward();

				Thread.sleep(1000);
				leftMotor.rotate(180);
				rightMotor.rotate(-180);
				leftMotor.stop();
				rightMotor.stop();
				leftMotor.setSpeed(200);
				rightMotor.setSpeed(200);
				leftMotor.forward();
				rightMotor.forward();
				Thread.sleep(2000);

				leftMotor.rotate(-180);
				rightMotor.rotate(180);

				
				// Move forward until the robot detects the black track again
				while (cs.getColorID() != dataExchange.getBlack) {
					leftMotor.forward();
					rightMotor.forward();
					break;
				}
			}
		}
	}
}

// leftMotor.stop();
// rightMotor.stop(); // Stop after avoiding obstacle
>>>>>>> e4af026 (Motors test)
