import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;

class enroll extends Frame
{

	Label lblhd = new Label("Enrollment Form");
	Label lblpid = new Label("Participant ID:");
	Label lblpid1 = new Label("-");
	Label lbldate = new Label("Date of Enrollment:");
	Label lbldate1 = new Label("-");
	Label lblfname = new Label("First name");
	Label lbllname = new Label("Last name");
	Label lblcname = new Label("College name");
	Label lblcid = new Label("College id");
	Label lblcid1 = new Label("-");
	Label lblevents = new Label("Events");
	Label lbleid = new Label("Event ID");
	Label lbleid1 = new Label("-");
	Label lblmsg = new Label("-");
	TextField txtfname = new TextField();
	TextField txtlname = new TextField();
	Choice chcname = new Choice();
	Choice chevents = new Choice();
	Button btnsave = new Button("SAVE");
	Button btnnew = new Button("NEW");
	Button btnclose = new Button("CLOSE");


	



	enroll()
	{
		super("ENROLL");
		setLayout( null );

		lblhd.setBounds( 80, 50, 250 , 30 );

		lblpid.setBounds( 50, 100, 150 , 30 );
		lblpid1.setBounds( 220, 100, 150 , 30 );
		lbldate.setBounds( 400, 100, 150 , 30 );
		lbldate1.setBounds( 570, 100, 150 , 30 );

		lblfname.setBounds( 50, 150, 150 , 30 );
		txtfname.setBounds( 220, 150, 150 , 30 );
		txtfname.setEditable(false);
		lbllname.setBounds( 400, 150, 150 , 30 );
		txtlname.setBounds( 570, 150, 150 , 30 );
		txtlname.setEditable(false);

		lblcname.setBounds( 50, 200, 150 , 30 );
		chcname.setBounds( 220, 200, 150 , 30 );
		lblcid.setBounds( 400, 200, 150 , 30 );
		lblcid1.setBounds( 570, 200, 150 , 30 );

		lblevents.setBounds( 50, 250, 150 , 30 );
		chevents.setBounds( 220, 250, 150 , 30 );
		lbleid.setBounds( 400, 250, 150 , 30 );
		lbleid1.setBounds( 570, 250, 150 , 30 );

		lblmsg.setBounds( 50, 300, 600 , 30 );

		btnsave.setBounds(150,350,100,30);
		btnnew.setBounds(250,350,100,30);
		btnclose.setBounds(350,350,100,30);

		Font f = new Font("Arial", Font.BOLD, 30);
		Font f1 = new Font("Arial", Font.BOLD, 20);
		lblhd.setFont(f);
		lblhd.setForeground(Color.red);
		lblhd.setBackground(Color.yellow);

		lblmsg.setFont(f1);
		lblmsg.setForeground(Color.red);
		lblmsg.setBackground(Color.yellow);

		btnsave.setBackground(Color.yellow);
		btnsave.setVisible(false);
		btnnew.setBackground(Color.yellow);
		btnclose.setBackground(Color.yellow);

		add(lblhd);
		add(lblpid);
		add(lblpid1);
		add(lbldate);
		add(lbldate1);
		add(lblfname);
		add(lbllname);
		add(lblcname);
		add(lblcid);
		add(lblcid1);
		add(lblevents);
		add(lbleid);
		add(lbleid1);
		add(lblmsg);
		add(txtfname);
		add(txtlname);
		add(chcname);
		add(chevents);
		add(btnnew);
		add(btnsave);
		add(btnclose);

		Calendar cal =Calendar.getInstance();
		String	dt="";
		dt=dt+cal.get(Calendar.DATE)+"/";
		dt=dt+(cal.get(Calendar.MONTH)+1)+"/";
		dt=dt+cal.get(Calendar.YEAR);
		lbldate1.setText(dt);

		Connection con=null;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
					Statement st=con.createStatement();
					String s="Select distinct(cname) from addcollege order by cname";
					ResultSet rs=st.executeQuery(s);
						chcname.add("-select college-");
					while(rs.next())
						chcname.add(rs.getString("cname"));

					String s1="Select distinct(ename) from events order by ename";
					ResultSet rs1=st.executeQuery(s1);
						chevents.add("-select event-");
					while(rs1.next())
						chevents.add(rs1.getString("ename"));
				}
				catch(Exception ex)
				{
					lblmsg.setText("Error"+ex);
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
		chcname.addItemListener( new ListListener() );
		chevents.addItemListener( new List1Listener() );

		btnsave.addActionListener( new BtnListener());
		btnnew.addActionListener( new BtnListener());
		btnclose.addActionListener( new BtnListener());

		setSize( 800, 500 );
		setVisible(true);
	}

	void clearFields()
	{
		lblpid1.setText("");
		lblcid1.setText("");
		lbleid1.setText("");
		txtfname.setText("");
		txtlname.setText("");
		lblmsg.setText("-");
		chcname.select(0);
		chevents.select(0);
		txtfname.requestFocus();
	}

	class ListListener implements ItemListener
	{
		public void itemStateChanged( ItemEvent ie)
		{
			Connection con=null;
				try
				{
					String ch=chcname.getSelectedItem();
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
					Statement st=con.createStatement();
					String s="Select cid from addcollege where cname='"+ch+"'";
					ResultSet rs=st.executeQuery(s);

					rs.next();
					lblcid1.setText(rs.getInt("cid")+"");
					
				}
				catch(Exception ex)
				{
					lblmsg.setText("Error"+ex);
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
					String ch=chevents.getSelectedItem();
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
					Statement st=con.createStatement();
					String s="Select eid from events where ename='"+ch+"'";
					ResultSet rs=st.executeQuery(s);

					rs.next();
					lbleid1.setText(rs.getInt("eid")+"");
					
				}
				catch(Exception ex)
				{
					lblmsg.setText("Error"+ex);
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
			if(s.equals("SAVE"))
			{
				Connection con=null;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
					Statement st=con.createStatement();
				
					String pid=lblpid1.getText();
					String fn=txtfname.getText();
					String ln=txtlname.getText();
					String cn=chcname.getSelectedItem();
					String en=chevents.getSelectedItem();
					String cid=lblcid1.getText();
					String eid=lbleid1.getText();
					String dt=lbldate1.getText();

					String d1[]=dt.split("/");
					dt=d1[2]+"-"+d1[1]+"-"+d1[0];
					
					
					String qry="Insert into enroll values("+pid+",'"+fn+"','"+ln+"','"+cn+"','"+en+"',"+cid+","+eid+",'"+dt+"')";
					st.executeUpdate(qry);
					qry="Update events set nop=nop+1 where eid="+eid;
					st.executeUpdate(qry);
					qry="Update addcollege set nop=nop+1 where cid="+cid;
					st.executeUpdate(qry);
					lblmsg.setText(" Enrollment Successful !!");
				}
				catch(Exception ex)
				{
					lblmsg.setText("Error :"+ex);
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
			else if(s.equals("NEW"))
			{
				btnsave.setVisible(true);
				txtfname.setEditable(true);
				txtlname.setEditable(true);
				clearFields();
				Connection con=null;
				Statement st=null;

				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
				    st=con.createStatement();
		
					String qry = "Select max(pid)+1 as x from enroll";
					ResultSet rs=st.executeQuery(qry);
					rs.next();
					if(rs.getInt("x")==0)
						lblpid1.setText("1");
                    else				
					lblpid1.setText(""+rs.getInt("x"));
				}
				
				catch(Exception ex)
				{
					lblmsg.setText("Error"+ex);
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
			else if(s.equals("CLOSE"))
				{dispose();}
		}
	}

	public static void main(String[ ] args )
	{
		enroll c = new enroll();
	}
}