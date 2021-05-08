import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class college extends Frame
{
	Label lblhd = new Label("ADD COLLEGE FORM");
	Label lblcid = new Label("College id");
	Label lblcname = new Label("College name");
	Label lblcadd = new Label("College Address");
	Label lblcid1 = new Label("-");
	TextField txtcname = new TextField();
	TextField txtcadd = new TextField();
	Label lblmsg = new Label("-");
	Button btnsave = new Button("SAVE");
	Button btnnew = new Button("NEW");
	Button btnclose = new Button("CLOSE");


	college()
	{
		super("ADD COLLEGE");
		setLayout( null );

		lblhd.setBounds( 80, 50, 320 , 30 );

		lblcid.setBounds( 50, 100, 150 , 30 );
		lblcid1.setBounds( 220, 100, 200 , 30 );

		lblcname.setBounds( 50, 150, 150 , 30 );
		txtcname.setBounds( 220, 150, 200 , 30 );
		txtcname.setEditable(false);

		lblcadd.setBounds( 50, 200, 150 , 30 );
		txtcadd.setBounds( 220, 200, 200 , 30 );
		txtcadd.setEditable(false);

		lblmsg.setBounds(50,250,400,30);

		btnsave.setBounds(50,350,100,30);
		btnnew.setBounds(150,350,100,30);
		btnclose.setBounds(250,350,100,30);

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
		add(lblcid);
		add(lblcname);
		add(lblcadd);
		add(lblcid1);
		add(lblmsg);
		add(txtcadd);
		add(txtcname);
		add(btnnew);
		add(btnsave);
		add(btnclose);

		btnsave.addActionListener( new BtnListener());
		btnnew.addActionListener( new BtnListener());
		btnclose.addActionListener( new BtnListener());

		setSize( 500, 500 );
		setVisible(true);
	}

	void clearFields()
	{
		lblcid1.setText("");
		txtcname.setText("");
		lblmsg.setText("-");
		txtcadd.setText("");
		txtcname.requestFocus();
	}

	class BtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String s=e.getActionCommand();

			if(s.equals("SAVE"))
			{				
				Connection con=null;				
				Statement st=null;

				try
				{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
				st=con.createStatement();
				int id=Integer.parseInt(lblcid1.getText());
				String name=txtcname.getText();
				String ad=txtcadd.getText();
				String str="insert into addcollege  values("+id+",'"+name+"','"+ad+"',"+"0)";
				
				st.executeUpdate(str);
				 
				lblmsg.setText("College record added.. !");

				}
				catch(Exception ea)
				{
				lblmsg.setText("Error .." +ea);
				}

				finally
				{
				try
				{
				con.close();
				}
				catch(Exception ex)
				{ }
				}

			}

			else if(s.equals("NEW"))
			{
				
				btnsave.setVisible(true);
				txtcname.setEditable(true);
				txtcadd.setEditable(true);
				clearFields();
				Connection con=null;
				Statement st=null;

				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/olympic","root","");
				    st=con.createStatement();
		
					String qry = "Select max(cid)+1 as x from addcollege";
					ResultSet rs=st.executeQuery(qry);
					rs.next();
					if(rs.getInt("x")==0)
						lblcid1.setText("1");
                    else				
					lblcid1.setText(""+rs.getInt("x"));
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
			{
				dispose();
			}
			
		}
	}
	

	public static void main(String[ ] args )
	{
		college c = new college();
	}

}