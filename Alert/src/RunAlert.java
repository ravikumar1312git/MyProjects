

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.html.SubmittableElement;
import com.gargoylesoftware.htmlunit.javascript.host.Node;
import com.sun.media.sound.Toolkit;


public class RunAlert implements ActionListener {
	public String stck=null;
	public String exc=null;
	public float highLimit=0.0f;
	public float lowLimit=0.0f;
	public boolean flag=false;
	public String stock;
	String s;
	Player play;
	public JLabel stckName;
	JTextField editStckName;
	ButtonGroup ex;
	JLabel exchange;
	JLabel limitPr;
	JLabel lowLimitPr;
	JTextField editLimit;
	JTextField editLowLimit;
	ButtonGroup greatLess;
	JButton start;
	JRadioButton nse;
	JRadioButton bse;
	JRadioButton greater;
	JRadioButton lesser;
	JFrame ui1;
	JFrame ui2;
	JRadioButton stop;
	JRadioButton change;
	JButton ok;
	
	public void firstUI()
	{
		ui1=new JFrame("Alert");
		ui1.setSize(400, 300);
		ImageIcon ico=new ImageIcon("/../source/index.jpg");
		ui1.setIconImage(ico.getImage());
		ui1.getContentPane().setLayout(new BorderLayout());
		stckName=new JLabel();
		stckName.setText("Name : ");
	     editStckName=new JTextField();
		/*stckName.setAlignmentX(50);
		stckName.setAlignmentY(50);
		editStckName.setAlignmentX(80);
		editStckName.setAlignmentY(50);*/
		editStckName.setText("                         ");
		JPanel panel1=new JPanel();
		panel1.add(stckName);
		panel1.add(editStckName);
		
		exchange=new JLabel("Exchange : ");
		nse=new JRadioButton();
		nse.setText("NSE");
		nse.setSelected(true);
		bse=new JRadioButton();
		bse.setText("BSE");
		ex=new ButtonGroup();
		ex.add(bse);
		ex.add(nse);
		JPanel panel2=new JPanel();
		panel2.add(exchange);
		panel2.add(bse);
		panel2.add(nse);
		
		limitPr=new JLabel("Higher Limit Price : ");
		editLimit=new JTextField("                 ");
		JPanel panel3=new JPanel();
		panel3.add(limitPr);
		panel3.add(editLimit);
		
		lowLimitPr=new JLabel("Lower Limit Price : ");
		editLowLimit=new JTextField("                ");
		JPanel panel4=new JPanel();
		panel4.add(lowLimitPr);
		panel4.add(editLowLimit);
		
		
		/*Container con=new Container();
		con.setLayout(new BoxLayout(con,BoxLayout.PAGE_AXIS));
		con.add(panel1);
		con.add(panel2);*/
		JPanel con=new JPanel();
		con.setLayout(new GridLayout(2, 2));
		con.setSize(250, 250);
		con.add(panel1);
		con.add(panel2);
		con.add(panel3);
		con.add(panel4);
		
		ui1.add(con, BorderLayout.CENTER);
		ui1.setResizable(false);
		ui1.setBackground(Color.RED);
		
		start=new JButton();
		start.setText("**Start Alert**");
		JPanel submit=new JPanel();
		submit.setLayout(new BorderLayout());
		submit.add(start, BorderLayout.CENTER);
		
		ui1.add(submit, BorderLayout.SOUTH);
		ui1.setVisible(true);
		
		start.addActionListener(this);
		
		try {
			Thread.sleep(20000);
			if(flag)
				this.process();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("stop"))
		{
			editLimit.setEditable(false);
			editLowLimit.setEditable(false);
		}
		else if(e.getActionCommand().equals("change"))
		{
			editLimit.setEditable(true);
			editLowLimit.setEditable(true);
		}
		else if(e.getActionCommand().equals("ok"))
		{
			play.close();
			if(stop.isSelected())
				System.exit(0);
			else if(change.isSelected())
			{
				highLimit=Float.parseFloat(editLimit.getText().trim());
				lowLimit=Float.parseFloat(editLowLimit.getText().trim());
			}
			ui2.setVisible(false);
			ui2=null;
		}
		else{
			highLimit=Float.parseFloat(editLimit.getText().trim());
			lowLimit=Float.parseFloat(editLowLimit.getText().trim());
			stck=editStckName.getText().trim();
			if(nse.isSelected())
				exc="NSE";
			else if(bse.isSelected())
				exc="BSE";
			
			
			ui1.setVisible(false);
			ui1.setEnabled(false);
			ui1=null;
			flag=true;
		}
		return;
	}
	public void process() throws IOException
	{
	WebClient client=new WebClient();
	client.setJavaScriptEnabled(false);
	client.setThrowExceptionOnFailingStatusCode(false);
	HtmlPage page1=client.getPage("https://in.finance.yahoo.com/");
	/*HtmlForm form1=page1.getFormByName("topsearch");
	HtmlTextInput searchText=form1.getElementById("search_str");
	searchText.setAttribute("value","ongc");
	HtmlAnchor anchor1=page1.getAnchorByHref("javascript:;");
	HtmlPage page2=anchor1.click();
	HtmlElement price=page2.getElementById("Bse_Prc_tick");
	System.out.println(price.asText());*/
	HtmlElement in=page1.getElementById("txtQuotes");
	if(exc.equals("NSE"))
		stck=stck+".ns";
	else
		stck=stck+".bo";
	in.setAttribute("value", stck);
	HtmlSubmitInput button=(HtmlSubmitInput) page1.getElementById("btnQuotes");
	HtmlPage page2=(HtmlPage) button.click();
	
	while(page2!=null)
	{
	try{
	String id="yfs_l84_"+stck;
	NodeList list=page2.getElementsByTagName("h2");
	for(int i=0;i<list.getLength();i++)
	{
		if(list.item(i).getTextContent().startsWith(stck.substring(0, 1).toUpperCase()))
			s=list.item(i).getTextContent();
	}
	if(s!=null && !s.isEmpty())
	{
		if(s.contains("("))
			stock=s.substring(0, s.indexOf("(")).trim();
		else
			stock=stck;
	}
	else
		stock=stck;
	System.out.println(stock+" --- "+page2.getElementById(id).asText()+" --- "+new GregorianCalendar().get(Calendar.HOUR_OF_DAY)+":"+new GregorianCalendar().get(Calendar.MINUTE));
	HtmlElement priceEle=page2.getElementById(id);
	String price=priceEle.asText();
	//System.out.println(Float.parseFloat(price));
	if(Float.parseFloat(price)>highLimit || Float.parseFloat(price)<lowLimit)
	{
		ui2=new JFrame("AlerT");
		ui2.setSize(450, 200);
		ui2.setResizable(false);
		ui2.getContentPane().setLayout(new FlowLayout());
		ImageIcon ic=new ImageIcon("/../source/stock-market-3.jpg");
		ui2.setIconImage(ic.getImage());
		/*try {
			ui1.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Ravi/index.jpg")))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//java.awt.Toolkit.getDefaultToolkit().beep();
		
		
		JLabel msg=new JLabel(stock+" has crossed the set price Rs."+price+" !!!");
		JPanel con2=new JPanel();
		
		stop=new JRadioButton();
		stop.setText("Stop ");
		stop.setActionCommand("stop");
		stop.addActionListener(this);
		change=new JRadioButton();
		change.setText("Change ");
		change.setActionCommand("change");
		change.addActionListener(this);
		ButtonGroup tog=new ButtonGroup();
		tog.add(stop);
		tog.add(change);
		
		JPanel a=new JPanel();
		a.add(limitPr);
		a.add(editLimit);
		
		JPanel b=new JPanel();
		b.add(lowLimitPr);
		b.add(editLowLimit);
		
		ok=new JButton("OK");
		ok.setActionCommand("ok");
		con2.add(ok);
		JPanel con3=new JPanel();
		con3.setLayout(new GridLayout(2,2));
		con3.add(stop);
		con3.add(a);
		con3.add(change);
		con3.add(b);
		
		ui2.getContentPane().add(msg);
		ui2.getContentPane().add(new JLabel("                "));
		ui2.getContentPane().add(con3);
		ui2.getContentPane().add(ok);
		ui2.toFront();
		ui2.setVisible(true);
		ok.addActionListener(this);
		/*FileInputStream fis=new FileInputStream("/../source/SIREN.mp3");
		play=new Player(fis);
		play.play();*/
		Thread.sleep(30000);
	} }
	catch (Exception e) {
		// TODO Auto-generated catch block
	}
	if(ui2!=null)
	{
	ui2.setVisible(false);
	ui2=null;
	}
	page2=(HtmlPage) page2.refresh();
	}
	}
}
