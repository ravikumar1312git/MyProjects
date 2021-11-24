import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.host.Node;


public class Gann {

	/**
	 * @param args
	 */
	@SuppressWarnings(value = {})
	public static void main(String[] args){
		// TODO Auto-generated method stub
		try{
			File f=new File("C:/Users/ravdurai/Desktop/stock/Gann_Output.txt");
			PrintStream ps=new PrintStream(f);
			System.setOut(ps);
			WebClient clnt=new WebClient(BrowserVersion.FIREFOX_3);
			clnt.setUseInsecureSSL(true); //ignore ssl certificate
			clnt.setThrowExceptionOnScriptError(false);
			clnt.setCssEnabled(false);
			clnt.setThrowExceptionOnFailingStatusCode(false);
			clnt.waitForBackgroundJavaScriptStartingBefore(200);
			clnt.waitForBackgroundJavaScript(20000);
			System.out.println(new Date());
			HtmlPage page1=null;
			try {
				page1 = clnt.getPage("http://pivottrading.co.in/trade/realtimeAdvancedGann.php");
			} catch (Exception e) {
			}
			HtmlSelect select=(HtmlSelect) page1.getElementById("scrip");
			//System.out.println(page1.getElementsByTagName("input").item(0));
			List<Scrip> scripList=new ArrayList<Scrip>();
			String[] scripNames={"ACC","ADANIPORTS","AMBUJACEM","ASIANPAINT","AXISBANK","BAJAJ-AUTO","BANKBARODA","BHARTIARTL","BHEL","BOSCHLTD","BPCL","CAIRN","CIPLA"
					,"COALINDIA","DRREDDY","GAIL","GRASIM","HCLTECH","HDFC","HDFCBANK","HEROMOTOCO","HINDALCO","HINDUNILVR","ICICIBANK","IDEA",
					"INDUSINDBK","INFY","ITC","KOTAKBANK","LT","LUPIN","M&M","MARUTI","NTPC","ONGC","PNB","POWERGRID","RELIANCE","SBIN","SUNPHARMA",
					"TATAMOTORS","TATAPOWER","TATASTEEL","TCS","TECHM","ULTRACEMCO","VEDL","WIPRO","YESBANK","ZEEL"};
			for(int k=0;k<scripNames.length;k++)
			{
				Scrip s=new Scrip();
				s.name=scripNames[k];
				scripList.add(s);
				
			}
			
			for(Scrip x:scripList)
			{
				select.setSelectedAttribute(select.getOptionByValue(x.name), true);
				HtmlSubmitInput submit=(HtmlSubmitInput) page1.getElementsByTagName("input").item(0);
				HtmlPage page2=null;
				try {
					page2 = submit.click();
				} catch (Exception e) {
				}
		//
		//		HtmlButton b=(HtmlButton)page1.getElementsByName("input").get(1);
		//		HtmlPage page2=b.click();
				//pw.println("Buy at:"+page2.getElementById("buyAtValue").asText()+" Buy Targets"+page2.getElementById("buyTargetValue").asText());
				/*System.out.println("before");
				 int size=page2.getElementsByTagName("input").getLength();
				System.out.println("eles: "+size);
				for(int h=0;h<size;h++)
				{
					System.out.println(h+" : "+page2.getElementsByTagName("input").item(h));
				}*/
				x.buy=Float.parseFloat(page2.getElementById("buyAtValue").asText());
				x.buyTarget=page2.getElementById("buyTargetValue").asText();
				HtmlTextInput ltpr=(HtmlTextInput)page2.getElementsByTagName("input").item(2);
				x.LTP=Float.parseFloat(ltpr.asText());
				x.sell=Float.parseFloat(page2.getElementById("sellAtValue").asText());
				x.sellTarget=page2.getElementById("sellTargetValue").asText();
				
			}
			
			for(Scrip y:scripList)
			{
				if(y.LTP<=(y.sell*1.002) || y.LTP >=(y.buy*0.998))
				{
					System.out.println(y.name+" : "+y.LTP);
					System.out.println("	Buy at : "+y.buy+" Buy Targets : "+y.buyTarget);
					System.out.println("	Sell at : "+y.sell+" Sell Targets : "+y.sellTarget);
					System.out.println();
					
				}
				
			}
			
		}
		catch(Exception e)
		{
			
		}
	}

}
