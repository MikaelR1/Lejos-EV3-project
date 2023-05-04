import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReceiveData extends Thread {
	
		private DataExchange DE;
		URL url = null;
		HttpURLConnection conn = null;
		InputStreamReader isr = null;
		BufferedReader br=null;
		String s=null;
		
		public ReceiveData(DataExchange dataExchange) {
			this.DE = dataExchange;
		}
	
	@Override
	// Override the run() method of the Thread class
	public void run() {
		try {

			url = new URL("http://192.168.0.103:8080/rest/testing/newrobovalues");
			conn = (HttpURLConnection)url.openConnection();
  
			if (conn==null) {
	  			System.out.println("No connection!!!");
			}
			InputStream is=null;
			try {
				is=conn.getInputStream();
			}
			catch (Exception e) {
	  			System.out.println("Exception conn.getInputSteam()");
	  			e.printStackTrace();
	            System.out.println("Cannot get InputStream!");
			}
			isr = new InputStreamReader(is);
      		br=new BufferedReader(isr);
      		
      		s=br.readLine();
      		String [] arvot=s.split(" ");
      		int speedmulti = Integer.parseInt(arvot[0]);
      		int acceleration = Integer.parseInt(arvot[1]);
      		float black_value = Float.parseFloat(arvot[2]);
      		float white_value = Float.parseFloat(arvot[3]);
      		
      		DE.setSpeedmulti(speedmulti);
      		DE.setAcceleration(acceleration);
      		DE.setBlack_value(black_value);
      		DE.setWhite_value(white_value);
      		
		}
  		catch(Exception e) {
  			e.printStackTrace();
            System.out.println("Some problem!");
  		}
	}
}	
