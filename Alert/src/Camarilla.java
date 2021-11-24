import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Camarilla {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List<Stock> stocks=new ArrayList<Stock>();
		Stock tmpStk=null;
		URL url=null;
		File f=new File("C:/Users/ravdurai/Desktop/stock/CamOutput.txt");
		PrintStream ps=new PrintStream(f);
		System.setOut(ps);
		Scanner scan=new Scanner(new File("C:/Users/ravdurai/Desktop/stock/CamInput.txt"));
		while(scan!=null && scan.hasNext())
		{
			String temp=scan.nextLine();
			String cont[]=temp.split("\\s+");
			tmpStk=new Stock();
			tmpStk.name=cont[0];
			tmpStk.high=Double.parseDouble(cont[1]);
			tmpStk.low=Double.parseDouble(cont[2]);
			tmpStk.close=Double.parseDouble(cont[3]);
			tmpStk.h4=((tmpStk.high-tmpStk.low)*0.55)+tmpStk.close;
			tmpStk.h3=((tmpStk.high-tmpStk.low)*0.275)+tmpStk.close;
			tmpStk.h2=((tmpStk.high-tmpStk.low)*0.183)+tmpStk.close;
			tmpStk.h1=((tmpStk.high-tmpStk.low)*0.0916)+tmpStk.close;
			tmpStk.l1=tmpStk.close-((tmpStk.high-tmpStk.low)*0.0916);
			tmpStk.l2=tmpStk.close-((tmpStk.high-tmpStk.low)*0.183);
			tmpStk.l3=tmpStk.close-((tmpStk.high-tmpStk.low)*0.275);
			tmpStk.l4=tmpStk.close-((tmpStk.high-tmpStk.low)*0.55);
			stocks.add(tmpStk);
		}
		/*System.out.println("Name : "+stocks.get(50).name);
		System.out.println("H4 : "+stocks.get(50).h4);
		System.out.println("H3 : "+stocks.get(50).h3);
		System.out.println("H2 : "+stocks.get(50).h2);
		System.out.println("H1 : "+stocks.get(50).h1);
		System.out.println("L1 : "+stocks.get(50).l1);
		System.out.println("L2 : "+stocks.get(50).l2);
		System.out.println("L3 : "+stocks.get(50).l3);
		System.out.println("L4 : "+stocks.get(50).l4);*/

		System.out.println(new Date());
		System.out.println();
			for(Stock k:stocks)
			{
				url = new URL("http://finance.google.com/finance/info?client=ig&q=NSE%3A"+k.name);
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				String strTemp = "";
				while(null != (strTemp = br.readLine())) {
					if(strTemp.startsWith(",\"l\" :"))
					{
						String val=strTemp.split(":")[1].trim().replaceAll(",", "").replaceAll("\"", "");
						//System.out.println(strTemp.split(":")[1]);
						double curVal=Double.parseDouble(val);
						if(curVal<k.h4 && curVal>k.h3 )
						{
							System.out.println("Name : "+k.name+" Current : "+curVal);
							System.out.println("	H4 : "+k.h4);
							System.out.println("	 H3 : "+k.h3);
							System.out.println("	   H2 : "+k.h2);
							System.out.println("		H1 : "+k.h1);							
							System.out.println(" ");
						}
						else if(curVal>k.l4 && curVal<k.l3)
						{
							System.out.println("Name : "+k.name+" Current : "+curVal);
							System.out.println("		L1 : "+k.l1);
							System.out.println("	   L2 : "+k.l2);
							System.out.println("	 L3 : "+k.l3);
							System.out.println("	L4 : "+k.l4);
							System.out.println(" ");
						}
					}
			}
				
			}
		

	}

}
