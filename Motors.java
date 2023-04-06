import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class Motors extends Thread {

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
