import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class mainmenu extends Frame
{
	Label lbl=new Label("ANNUAL COLLEGE OLYMPICS");
	Button btnclg=new Button("ADD COLLEGE");
	Button btnen=new Button("ENROLL");
	Button btnpre=new Button("PREVIOUS RECORDS");
	Button btnexit=new Button("EXIT");


	mainmenu()
	{
		super("mainmenu");
		setLayout( null );
		lbl.setBounds(80,50,500,50);
		btnclg.setBounds(200,200,250,50);
		btnen.setBounds(200,270,250,50);
		btnpre.setBounds(200,340,250,50);
		btnexit.setBounds(200,410,250,50);

		btnpre.setBackground(Color.yellow);
		btnexit.setBackground(Color.yellow);
		btnclg.setBackground(Color.yellow);
		btnen.setBackground(Color.yellow);

		add(lbl);
		add(btnexit);
		add(btnclg);
		add(btnen);
		add(btnpre);

		Font f = new Font("Arial", Font.BOLD, 30);
		Font f1 = new Font("Arial", Font.ITALIC+Font.BOLD, 30);
		Font f2 = new Font("Arial", Font.BOLD, 20);
		lbl.setForeground(Color.red);
		lbl.setBackground(Color.yellow);
		lbl.setFont(f1);
		btnen.setFont(f2);
		btnclg.setFont(f2);
		btnpre.setFont(f2);
		btnexit.setFont(f2);

		btnclg.addActionListener( new BtnListener());
		btnen.addActionListener( new BtnListener());
		btnpre.addActionListener( new BtnListener());
		btnexit.addActionListener( new BtnListener());
		

		Image img = Toolkit.getDefaultToolkit().getImage("bg.jpg");
		setIconImage(img);
		setSize( 700, 500 );
		setVisible(true);
	}

	class BtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			String s=ae.getActionCommand();
			if(s.equals("ADD COLLEGE"))
			{
				college c=new college();
				
			}
			else if(s.equals("ENROLL"))
			{
				enroll e=new enroll();
			}
			else if(s.equals("PREVIOUS RECORDS"))
			{
				record r=new record();
			}
			else if(s.equals("EXIT"))
				{System.exit(0);}
		}
	}

	public static void main(String[ ] args )
	{
		mainmenu c = new mainmenu();
		/*class BackgroundPanel extends Panel
		{
			Image img;
			public BackgroundPanel()
			{
		
			
				img=Toolkit.getDefaultToolkit().createImage("bg.jpg");
			
				public void paint(Graphics g)
				{
					
						g.drawImage(img,0,0,null);
					
				}
			}
		}	*/
	}	
}