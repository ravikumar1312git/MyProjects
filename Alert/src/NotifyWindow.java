import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class NotifyWindow implements ActionListener{
	public static Color clr=null;
	JFrame f=new JFrame();
	JLabel rx=null;
	//JButton close=null;
	Calendar cal=null;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	public void window(List<String> name) throws Exception
	{
		f.dispose();
		f=new JFrame();
		f.setAlwaysOnTop (true);
		Container c=new Container();
		c.setLayout(new GridLayout(name.size()+1, 1));
		//close=new JButton();
		//close.setText("X");
		//JPanel j=new JPanel();
		//j.setOpaque(true);
		//j.setBackground(Color.red);
	//	c.add(j);
		JLabel time=new JLabel();
		cal = Calendar.getInstance();
        time.setText(sdf.format(cal.getTime()));
		c.add(time);
	for(String s:name)
	{
		rx=new JLabel();
		rx.setText(s);
		c.add(rx);
	}
	
	//c.add(close);
	f.add(c);
	f.setUndecorated(true);
	f.setSize(150,(name.size()*15)+15);
	f.setVisible(true);
	Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
	Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(f.getGraphicsConfiguration());
	
	f.setLocation(0, scrSize.height - toolHeight.bottom - f.getHeight());
	//close.addActionListener(this);
	Thread.sleep(5000);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
