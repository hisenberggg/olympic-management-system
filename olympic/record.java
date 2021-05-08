import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class record extends Frame
{

	Label lblhd = new Label("PREVIOUS RECORDS");
	Label lblevent = new Label("EVENTS");
	Label lblwin = new Label("WINNERS");
	Label lblyr = new Label("YEAR");
	Label lblyr1 = new Label("-");
	Choice chevent=new Choice();
	Choice chwin=new Choice();
	Button btnclose=new Button("CLOSE");


	record()
	{
		super("Previous Records Viewer");
		setLayout( null );

		lblhd.setBounds( 80, 50, 350 , 30 );

		lblevent.setBounds( 50, 150, 150 , 30 );
		chevent.setBounds( 220, 150, 250 , 30 );

		lblwin.setBounds( 50, 250, 150 , 30 );
		chwin.setBounds( 220, 250, 250 , 30 );

		lblyr.setBounds( 50, 350, 150 , 30 );
		lblyr1.setBounds( 220, 350, 150 , 30 );
		btnclose.setBounds( 150, 400, 150 , 50 );



		Font f = new Font("Arial", Font.BOLD, 30);
		Font f1 = new Font("Arial", Font.ITALIC+Font.BOLD, 20);
		Font f2 = new Font("Arial", Font.BOLD, 20);
		lblhd.setFont(f);
		lblhd.setForeground(Color.red);
		lblhd.setBackground(Color.yellow);
		btnclose.setBackground(Color.yellow);

		lblevent.setFont(f1);
		lblwin.setFont(f1);
		lblyr.setFont(f1);
		lblyr1.setFont(f2);
		chevent.setFont(f2);
		chwin.setFont(f2);
		btnclose.setFont(f2);

		add(lblhd);
		add(lblevent);
		add(lblwin);
		add(lblyr);
		add(lblyr1);
		add(chwin);
		add(chevent);
		add(btnclose);

		Connection con=null;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
					Statement st=con.createStatement();
					String s="Select distinct(ename) from events order by ename";
					ResultSet rs=st.executeQuery(s);
						chevent.add("-select event-");
					while(rs.next())
						chevent.add(rs.getString("ename"));
				}
				catch(Exception ex)
				{
				}
				finally
				{
					try
					{
						con.close();
					}
					catch(Exception ex)
					{
					}
				}

		chevent.addItemListener( new ListListener() );
		chwin.addItemListener( new List1Listener() );
		btnclose.addActionListener( new BtnListener());
		

		setSize( 500, 500 );
		setVisible(true);
	}

	class ListListener implements ItemListener
	{
		public void itemStateChanged( ItemEvent ie)
		{
			chwin.removeAll();
			lblyr1.setText("-");
			Connection con=null;
				try
				{
					String ch=chevent.getSelectedItem();
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
					Statement st=con.createStatement();
					String s="Select pname from record where ename='"+ch+"'";
					ResultSet rs=st.executeQuery(s);
					chwin.add("--");
					while(rs.next())
						chwin.add(rs.getString("pname"));
				}
				catch(Exception ex)
				{
					
				}
				finally
				{
					try
					{
						con.close();
					}
					catch(Exception ex)
					{
					}
				}
		}
	}

	class List1Listener implements ItemListener
	{
		public void itemStateChanged( ItemEvent ie)
		{
			Connection con=null;
				try
				{
					String ch=chwin.getSelectedItem();
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
					Statement st=con.createStatement();
					String s="Select dow from record where pname='"+ch+"'";
					ResultSet rs=st.executeQuery(s);
					rs.next();
					String yr=rs.getString("dow");
					String []yr1=yr.split("-");
					yr=yr1[0];
					lblyr1.setText(yr);
					// chwin.add("--");
					// while(rs.next())
					// 	chwin.add(rs.getString("pname"));
				}
				catch(Exception ex)
				{
					
				}
				finally
				{
					try
					{
						con.close();
					}
					catch(Exception ex)
					{
					}
				}
		}
	}

	class BtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			String s=ae.getActionCommand();
			
			 if(s.equals("CLOSE"))
				{
					dispose();
				}
			
		}
	}

	public static void main(String[ ] args )
	{
		record c = new record();
	}
}