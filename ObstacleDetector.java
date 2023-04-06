import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ObstacleDetector extends Thread {

	private DataExchange DE;
	private EV3UltrasonicSensor us;
	private SampleProvider ultraDistance;
	private float[] ultraData;

	public ObstacleDetector(DataExchange dataExchange) {
		this.DE = dataExchange;
		us = new EV3UltrasonicSensor(SensorPort.S1);
		ultraDistance = us.getDistanceMode();
		ultraData = new float[ultraDistance.sampleSize()];
	}

	@Override

	public void run() {

		while (true) {
			ultraDistance.fetchSample(ultraData, 0);
			float distance = ultraData[0] * 100;
			LCD.drawString("Distance: " + distance, 0,5);
			LCD.refresh();

			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (distance <= 30 ) {
				DE.setObstacleDetected(true);
				System.out.println("Obstacle detected");
				DE.setCommand(2);
				Delay.msDelay(1000);
			
			} else {
				DE.setObstacleDetected(false);
			}
		}
	}
}

