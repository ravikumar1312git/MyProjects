import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		URL url = new URL("http://finance.google.com/finance/info?client=ig&q=NSE%3AINDUSINDBK");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		String strTemp = "";
		while(null != (strTemp = br.readLine())) {
			if(strTemp.startsWith(",\"l\" :"))
			{
				String val=strTemp.split(":")[1].trim().replaceAll(",", "").replaceAll("\"", "");
				//System.out.println(strTemp.split(":")[1]);
				System.out.println(Double.parseDouble(val));
			}
			
		}
		//System.out.println(strTemp.split(",").length);
		//String val=(((strTemp.split(","))[3]).split(":"))[1];
		//System.out.println(val);
	}

}
