
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;

public class SignIn_page {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn_page window = new SignIn_page();
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
	public SignIn_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 591, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCustomerRegistrationPage = new JLabel("Customer Registration Page");
		lblCustomerRegistrationPage.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCustomerRegistrationPage.setForeground(Color.RED);
		lblCustomerRegistrationPage.setBounds(137, 13, 225, 26);
		frame.getContentPane().add(lblCustomerRegistrationPage);
		
		JLabel lblPleaseFillThe = new JLabel("Please fill the details below to create an account:");
		lblPleaseFillThe.setBounds(12, 42, 300, 16);
		frame.getContentPane().add(lblPleaseFillThe);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setBounds(12, 68, 76, 16);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblNewLabel = new JLabel("Last Name*");
		lblNewLabel.setBounds(12, 97, 76, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Gender");
		lblNewLabel_1.setBounds(12, 126, 76, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("City");
		lblNewLabel_2.setBounds(12, 155, 56, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(12, 184, 56, 16);
		frame.getContentPane().add(lblState);
		
		JLabel lblPostalCode = new JLabel("Street");
		lblPostalCode.setBounds(12, 213, 76, 16);
		frame.getContentPane().add(lblPostalCode);
		
		JLabel lblPermanentAddress = new JLabel("Postal Code");
		lblPermanentAddress.setBounds(12, 242, 126, 16);
		frame.getContentPane().add(lblPermanentAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(12, 271, 112, 16);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblUsername = new JLabel("Username*");
		lblUsername.setBounds(12, 299, 76, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password*");
		lblPassword.setBounds(12, 328, 97, 16);
		frame.getContentPane().add(lblPassword);
		
		JRadioButton rdbtnIWantTo = new JRadioButton("I want to be a part of the frequent customer program");
		rdbtnIWantTo.setBounds(22, 428, 382, 25);
		frame.getContentPane().add(rdbtnIWantTo);
		
		textField = new JTextField();
		textField.setBounds(151, 65, 271, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(151, 94, 271, 22);
		frame.getContentPane().add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(151, 152, 271, 22);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(151, 181, 271, 22);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(151, 210, 271, 22);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(151, 239, 116, 22);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(151, 268, 271, 22);
		frame.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		
		textField_8.setColumns(10);
		textField_8.setBounds(151, 296, 271, 22);
		frame.getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(151, 325, 271, 22);
		frame.getContentPane().add(textField_9);
		
		String freq[] = {"Yes","No"};
		DefaultComboBoxModel<String> combofreq = new DefaultComboBoxModel<String>(freq);
		
		
		String gen[] = {"Female","Male"};
		DefaultComboBoxModel<String> combogen = new DefaultComboBoxModel<String>(gen);
		JComboBox comboBox_1 = new JComboBox(combogen);
		comboBox_1.setBounds(151, 124, 116, 20);
		frame.getContentPane().add(comboBox_1);
		
		
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rdbtval;
				try {
					
					if((textField_8.getText()).isEmpty()==false && textField_9.getText().isEmpty()==false  && textField.getText().isEmpty()==false && textField_1.getText().isEmpty()==false ) {
						
						//
										
					if (rdbtnIWantTo.isSelected()) {
						
						rdbtval= "Yes";
										}
					else {
						rdbtval= "No";
					}
					
					String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
					int c = 6;
					StringBuilder cust_id = new StringBuilder();
					while (c!= 0) {
					int rand = (int)(Math.random()*str.length());					
					cust_id.append(str.charAt(rand));
					c --;
					}
					
					String combval= (String)comboBox_1.getSelectedItem();
					
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					
					
					String sql="insert into customer(customer_id,first_name,last_name,gender,frequent_shopper,street,city,state,country,zip,phone,email) "
							+ "values('"+cust_id.toString()+"','"+textField.getText()+"','"+textField_1.getText()+"','"+combval+"','"+rdbtval+"','"+textField_5.getText()+"',"
									+ "'"+textField_3.getText()+"','"+textField_4.getText()+"','"+textField_2.getText()+"','"+textField_6.getText()+"',"
											+ "'"+textField_7.getText()+"','"+textField_10.getText()+"')";
					
					
					
					String sql1 ="insert into login_details(user_id,password,store_id,customer_id,vendor_id,role) values('"+textField_8.getText()+"',"
							+ "'"+textField_9.getText()+"',null,'"+cust_id.toString()+"',null,'Customer')";		
					
					stmt.executeUpdate(sql);
					
					stmt.executeUpdate(sql1);
					
					JOptionPane.showMessageDialog(null,"Signed Up","Welcome to Minions.Inc",JOptionPane.INFORMATION_MESSAGE ); 
					frame.dispose();
					FirstScreen fs = new FirstScreen();
					fs.frame.setVisible(true);
					
						
					}
					
					else {
						JOptionPane.showMessageDialog(null,"Please Enter mandatory fields","Welcome to Minions.Inc",JOptionPane.INFORMATION_MESSAGE ); 
					}
					
					
				}
				catch(Exception e1) {
					
					System.out.print("Could not insert tuple. "+e1);
				
				
				
			}
			}});
		btnSignUp.setBounds(153, 460, 97, 25);
		frame.getContentPane().add(btnSignUp);
		
		JLabel lblNewLabel_3 = new JLabel("Email Address");
		lblNewLabel_3.setBounds(12, 361, 97, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_10 = new JTextField();
		textField_10.setBounds(151, 358, 271, 20);
		frame.getContentPane().add(textField_10);
		textField_10.setColumns(10);
		
		JList list = new JList();
		list.setBounds(180, 412, 1, 1);
		frame.getContentPane().add(list);
		
		JLabel lblNewLabel_5 = new JLabel("Country");
		lblNewLabel_5.setBounds(279, 243, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(336, 240, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
	
	}	
}