import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class CartPageCustomer {

	public JFrame frame;
	public String user;
	public int user_id=0;
	public JTable table;
	public String product_name;
	public double totalprice;
	public String product_id;
	public String quantity;
	public String userName;
	public String[][] deets=new String[4][4];
	public int addtocart;
	private JTextField textField;
	public String orderid="0";
	public String ordernum2="";
	public String customerid,street,city,state,country,zip;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartPageCustomer window = new CartPageCustomer();
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
	public CartPageCustomer() {
		
		initialize();
		//NewCustomerHomePage nch=new NewCustomerHomePage();
		//nch.userName=userName;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		DB_Connection db = new DB_Connection();
		Connection con=db.get_connection();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCartPageCustomer = new JLabel("Cart Page");
		lblCartPageCustomer.setForeground(Color.RED);
		lblCartPageCustomer.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCartPageCustomer.setBounds(175, 13, 89, 20);
		frame.getContentPane().add(lblCartPageCustomer);
		
		JButton btnNewButton = new JButton("Place an Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//JOptionPane.showMessageDialog(null, userName);
				ThankyouPage tp= new ThankyouPage();
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					Statement stmt = con.createStatement();
					Statement stmt2 = con.createStatement();
					Statement stmt3 = con.createStatement();
					Statement stmt4 = con.createStatement();
					Statement stmt5 = con.createStatement();
					Statement stmt6 = con.createStatement();
					Statement stmt8 = con.createStatement();
					String sql="select  order_id from minions.order order by order_id desc limit 1"; 
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next())
					{
						orderid=rs.getString(1);
						 
						if (orderid.length() > 3) {
							  String orderno= orderid.substring(orderid.length() - 4);
							  int ordernum=Integer.parseInt(orderno)+1; 
							  ordernum2=orderid.substring(0, orderid.length()-4)+Integer.toString(ordernum);
							  
							  
							} 
						
					}
					else
					{
						orderid="1";
						
						
					}
					if(totalprice!=0.0) {
					
					//customer_id,order_date,total_price,street,city,state,country,zip
					//String sql2="Select role from login_details where user_id ='"+user.getText().toString()+"' and password = '"+pass.getText().toString()+"'";
					String sql2="Select customer_id from login_details where user_id='"+userName+"'";
					ResultSet rs2=stmt2.executeQuery(sql2);
					if(rs2.next()) {
						customerid=rs2.getString(1);
					}
					if(customerid!=null) {
					String sql3="Select street,city, state,country,zip from customer where customer_id='"+customerid+"'";
					ResultSet rs3=stmt3.executeQuery(sql3);
					if(rs3.next()) {
						street=rs3.getString(1);
						city=rs3.getString(2);
						country=rs3.getString(4);
						state=rs3.getString(3);
						zip=rs3.getString(5);
					}
					}
					
					int store_id=0;
					String sql6="select store_id from login_details where user_id='"+userName+"'";
					ResultSet rs6=stmt6.executeQuery(sql6);
					
					if(rs6.next()) {
						
						//JOptionPane.showMessageDialog(null, "Storeid "+rs6.getString(1));
						if(rs6.getString(1)!=null)
							store_id=Integer.parseInt(rs6.getString(1));
						else
							store_id=1;
						
					}
					if(store_id!=1 && customerid==null) {
						String sql8="Select street,city, state,country,zip from store where store_id='"+store_id+"'";
						ResultSet rs8=stmt8.executeQuery(sql8);
						if(rs8.next()) {
							street=rs8.getString(1);
							city=rs8.getString(2);
							country=rs8.getString(4);
							state=rs8.getString(3);
							zip=rs8.getString(5);
						}
					}
					else
						store_id=1;
					
					String x= "INSERT INTO minions.order(`order_id`,`store_id`,`customer_id`,`order_date`,`total_price`,`street`,`city`,`state`,`country`,`zip`,`delivery_date`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(x);
				      preparedStmt.setString(1, ordernum2);
				      preparedStmt.setInt(2, store_id);
				      preparedStmt.setString(3,customerid);
				      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				      Calendar cal = Calendar.getInstance();
				     String dates=dateFormat.format(cal.getTime());
				    
				      preparedStmt.setString (4, dates);
				      preparedStmt.setDouble(5,totalprice);
				      preparedStmt.setString(6, street);
				      preparedStmt.setString(7,city);
				      preparedStmt.setString(8, state);
				      preparedStmt.setString(9, country);
				      preparedStmt.setString(10, zip);
				      cal.add(Calendar.DATE, 5);
				      String deliveryDate=dateFormat.format(cal.getTime());
				      preparedStmt.setString(11, deliveryDate);
				      preparedStmt.executeUpdate();
				      
					//stmt2.executeUpdate(x);                    
					//JOptionPane.showMessageDialog(null, addtocart);
				      int final_quantity=0;
				      
				     for(int i=0;i<addtocart;i++) {
				    	 
				    	 String sql4="Select stock_qty from store_inventory where store_id='"+store_id+"' and product_id='"+deets[i][0]+"'";
			    		  ResultSet rs4=stmt4.executeQuery(sql4);
					if(rs4.next()) {
						final_quantity=Integer.parseInt(rs4.getString(1)) - Integer.parseInt(deets[i][2]);
					}
					if(final_quantity>=Integer.parseInt(deets[i][2])) {
					//JOptionPane.showMessageDialog(null, final_quantity);
				      String x1= "INSERT INTO minions.product_order(`order_id`,`product_id`,`quantity`,`unit_price`,`total_price`) VALUES (?,?,?,?,?)";
						PreparedStatement preparedStmt1 = con.prepareStatement(x1);
					      preparedStmt1.setString(1, ordernum2);
					      preparedStmt1.setString(2, deets[i][0]);
					      preparedStmt1.setString(3,deets[i][2]);
					      float price=Float.valueOf(deets[i][3])/Float.valueOf(deets[i][2]);
					      preparedStmt1.setString(4, String.valueOf(price));
					      preparedStmt1.setString(5,deets[i][3]);
					      preparedStmt1.executeUpdate();
					     
					      
							PreparedStatement preparedStmt2 = con.prepareStatement("Update store_inventory set stock_qty=? where store_id=?");
							preparedStmt2.setString(1, Integer.toString(final_quantity));
							preparedStmt2.setString(2, Integer.toString(store_id));
							preparedStmt2.executeUpdate();
										
				      }
					else {
						JOptionPane.showMessageDialog(null, "Product "+deets[i][0]+" is out of stock");
					}
				     }
				     JOptionPane.showMessageDialog(null, "Your order is placed, your order number is "+ordernum2);
					con.close();
				
					}}
				
				catch (Exception ex)
				{
					System.out.println(ex);
				}
				tp.user=userName;
				tp.frame.setVisible(true);
			}
			
		});
		
		btnNewButton.setBounds(45, 219, 144, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Logout");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//JOptionPane.showMessageDialog(null, userName);
				FirstScreen nch = new FirstScreen();
				//nch.user="Customer";
				nch.userName=userName;
			    nch.frame.setVisible(true);
				//Productdetails pd= new Productdetails();
				//pd.frame.setVisible(true);
			}
		});
		btnBack.setBounds(302, 12, 97, 25);
		frame.getContentPane().add(btnBack);
		
		JButton btnViewOrderHistory = new JButton("View Order History");
		btnViewOrderHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//JOptionPane.showMessageDialog(null, userName);
				OrderHistory oh=new OrderHistory();
				oh.user=userName;
				oh.frame.setVisible(true);
				
			}
		});
		btnViewOrderHistory.setBounds(45, 278, 143, 25);
		frame.getContentPane().add(btnViewOrderHistory);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(12, 65, 408, 124);
		frame.getContentPane().add(scrollPane);
		
		textField = new JTextField();
		textField.setBounds(316, 220, 104, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		scrollPane.setViewportView(table);
		
		JButton btnViewOrder = new JButton("View Order");
		btnViewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames= {"Product ID ","Product","Quantity","Total Price"};
				String totlprice=Double.toString(totalprice);
				String[][] rows ;
				
				textField.setText(totlprice);
				table = new JTable(deets,columnNames);
				scrollPane.setViewportView(table);
			}
		});
		btnViewOrder.setBounds(12, 12, 151, 25);
		frame.getContentPane().add(btnViewOrder);
		
		JLabel lblNewLabel = new JLabel("Total Price");
		lblNewLabel.setBounds(232, 223, 83, 16);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}
