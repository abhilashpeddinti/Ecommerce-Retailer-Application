
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.sql.*;

public class FirstScreen {

	public JFrame frame;
	public String userRole="";
	public  String userName="";
	public JTextField user;
	private JPasswordField pass;
	String role;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstScreen window = new FirstScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 347);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Minions Inc.");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(117, 13, 272, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSignInAs = new JLabel("Sign in as :");
		lblSignInAs.setBounds(58, 143, 126, 16);
		frame.getContentPane().add(lblSignInAs);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(58, 66, 78, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(58, 95, 70, 16);
		frame.getContentPane().add(lblPassword);
		
		user = new JTextField();
		user.setBounds(176, 63, 116, 22);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(176, 95, 116, 22);
		frame.getContentPane().add(pass);
		pass.setColumns(10);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					String sql="Select role from login_details where user_id ='"+user.getText().toString()+"' and password = '"+pass.getText().toString()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next())
					{
						role=rs.getString(1);
						if (role.equals("Customer")) {
							
							
							JOptionPane.showMessageDialog(null, "Login Successful");
							NewCustomerHomePage nch=new NewCustomerHomePage();	
							nch.userName=user.getText();
							frame.dispose();
							nch.frame.setVisible(true);
							try {
								//DB_Connection db = new DB_Connection();
								//Connection con=db.get_connection();
								Statement stmt2 = con.createStatement();
								
								String sql2="select DISTINCT c.name  from category c join  product_category pc on c.category_id=pc.category_id "
										+ "join store_inventory si on pc.product_id=si.product_id where si.store_id=1";
								ResultSet rs2 = stmt2.executeQuery(sql2);
								while(rs2.next())
								{
									nch.comboBox.addItem(rs2.getString(1));
								}
								
							}
							catch(Exception ex) {
								System.out.println(ex);
							}
						}
						
						else {
							
							JOptionPane.showMessageDialog(null, "Not a Customer");
						}
						
						
					}
					else
					{
						user.setText("");
						pass.setText("");
						JOptionPane.showMessageDialog(null, "Login Failure");
						frame.revalidate();
						
					}
					con.close();
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
				
			}
		});
		btnCustomer.setBounds(176, 139, 97, 25);
		frame.getContentPane().add(btnCustomer);
		
		JButton btnStoreManager = new JButton("Store Manager");
		btnStoreManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					String sql="Select role from login_details where user_id ='"+user.getText().toString()+"' and password = '"+pass.getText().toString()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next())
					{
						role=rs.getString(1);
						if (role.equals("Store Manager")) {
							
							frame.dispose();
							StoreManagerHomePage shm = new StoreManagerHomePage();
							shm.userName=user.getText();
							shm.frame.setVisible(true);
						}
						
						else {
							
							JOptionPane.showMessageDialog(null, "Not a Store Manager");
						}
						
						
					}
					else
					{
						user.setText("");
						pass.setText("");
						JOptionPane.showMessageDialog(null, "Login Failure");
						frame.revalidate();
						
					}
					con.close();
				}
				catch (Exception e1)
				{
					System.out.println(e1);
				}
				
			}
		});
		btnStoreManager.setBounds(176, 177, 126, 25);
		frame.getContentPane().add(btnStoreManager);
		
		JButton btnVendor = new JButton("Vendor");
		btnVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					String sql="Select role from login_details where user_id ='"+user.getText().toString()+"' and password = '"+pass.getText().toString()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next())
					{
						role=rs.getString(1);
						if (role.equals("Vendor")) {
							
							frame.dispose();
							Vendor v= new Vendor();
							v.userName=user.getText();
							v.frame.setVisible(true);
						}
						
						else {
							
							JOptionPane.showMessageDialog(null, "Not a Vendor");
						}
						
						
					}
					else
					{
						user.setText("");
						pass.setText("");
						JOptionPane.showMessageDialog(null, "Login Failure");
						frame.revalidate();
						
					}
					con.close();
				}
				catch (Exception e2)
				{
					System.out.println(e2);
				}
				
			}
		});
		btnVendor.setBounds(176, 215, 97, 25);
		frame.getContentPane().add(btnVendor);
		
		JLabel lblNewLabel_1 = new JLabel("Sign up as :");
		lblNewLabel_1.setBounds(58, 271, 78, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnCustomer_1 = new JButton("Customer");
		btnCustomer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SignIn_page sp=new SignIn_page();
				sp.frame.setVisible(true);
			}
		});
		btnCustomer_1.setBounds(176, 267, 97, 25);
		frame.getContentPane().add(btnCustomer_1);
		
		
	}
}