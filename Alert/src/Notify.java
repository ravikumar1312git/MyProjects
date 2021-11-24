import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Notify {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		String name[]=args[0].split("-");
		String val="";
		List<String> dis=new ArrayList<String>();
		NotifyWindow win=new NotifyWindow();
		while(true)
		{
		for(String nm:name)
		{
		URL url = new URL("http://finance.google.com/finance/info?client=ig&q=NSE%3A"+nm);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		String strTemp = "";
		while(null != (strTemp = br.readLine())) {
			if(strTemp.startsWith(",\"l\" :"))
			{
				val=strTemp.split(":")[1].trim().replaceAll(",", "").replaceAll("\"", "");
				dis.add("  "+nm+" : "+val);
				break;
			}
			//System.out.println(nm+" "+val);
			//dis+=nm+" "+val;
			}
		}
		win.window(dis);
		//System.out.println(i++);
		dis.clear();
		System.gc();
		}
		//NotifyWindow n=new NotifyWindow();
		//n.window(name);
}}
