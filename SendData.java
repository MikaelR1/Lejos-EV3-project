import java.net.HttpURLConnection;
import java.net.URL;

public class SendData extends Thread {

	private DataExchange DE;
	private String baseURL = "http://192.168.0.103:8080/rest/testing/speedfromrobot/";

	public SendData(DataExchange dataExchange) {
		this.DE = dataExchange;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// Construct the URL with the current speed values
				String urlStr = baseURL + DE.getLcurrent_speed() + "/" + DE.getRcurrent_speed();
				URL url = new URL(urlStr);

				// Open a connection to the URL
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				// Check the response code and handle any errors
				int responseCode = connection.getResponseCode();
				if (responseCode != 200) {
					System.out.println("Failed to save data. Response code: " + responseCode);
				}

				// Close the connection
				connection.disconnect();

			} catch (Exception e) {
				System.out.println("Failed to save data: " + e.getMessage());
			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
