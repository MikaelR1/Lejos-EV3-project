
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor extends Thread {

	private DataExchange DE;
	private EV3ColorSensor cs;
	private SampleProvider redSample;
	private float[] redSampleData;

	public ColorSensor(DataExchange dataExchange) {
		this.DE = dataExchange;
		cs = new EV3ColorSensor(SensorPort.S2);
		redSample = cs.getRedMode();
		redSampleData = new float[redSample.sampleSize()];
	}

	@Override
	
	public void run() {
		
		// INITIALIZE COLORSENSOR
		cs.setFloodlight(true);
		
		// Threshold values
		float black = 0.10f;
		float white = 0.30f;

		//infinite loop
		while(true) {
		
		//comment
		redSample.fetchSample(redSampleData, 0);
		
		//pattern to calculate deviation
		float deviation = (redSampleData[0] - (black + white) / 2);
		
		// Pass deviation variable to DataExchange object
        DE.setDeviation(deviation);
		}
	}
}	
