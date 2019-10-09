import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class NewStoreManagerPage {

	public JFrame frame;
	public String user="Customer";
	public int store_id=1;
	public String product_name="";
	public String pro_desc;
	public String product_id;
	public String userName;
	private JTextField textField;
	private JTextField textField_1;
	private double price;
	public int quantity=0;
	String orderid, ordernum2;
	public String[][] deets=new String[10][4];
	public int addtocart=0;
	public String vendor_id;
	public String street,city,state,country,zip;
	public float unit_price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewStoreManagerPage window = new NewStoreManagerPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public NewStoreManagerPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CartPageCustomer cpc= new CartPageCustomer();
		frame = new JFrame();
		frame.setBounds(100, 100, 678, 672);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCustomerHomepage = new JLabel("Products");
		lblCustomerHomepage.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCustomerHomepage.setForeground(Color.RED);
		lblCustomerHomepage.setBounds(188, 13, 168, 22);
		frame.getContentPane().add(lblCustomerHomepage);
		
		JLabel lblSelectBrand = new JLabel("Select Product Category");
		lblSelectBrand.setBounds(38, 64, 138, 16);
		frame.getContentPane().add(lblSelectBrand);
		
		JLabel lblSelectProductCategory = new JLabel("Select Brand ");
		lblSelectProductCategory.setBounds(38, 105, 156, 16);
		frame.getContentPane().add(lblSelectProductCategory);
		
		JLabel lblNewLabel = new JLabel("Select Product");
		lblNewLabel.setBounds(38, 155, 93, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(188, 61, 93, 22);
		frame.getContentPane().add(comboBox);
		try {
			DB_Connection db = new DB_Connection();
			Connection con=db.get_connection();
			//System.out.println(con);
			//Class.forName("com.mysql.jdbc.Driver");
			//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/world","wbadmin","aongpg1");
			Statement stmt = con.createStatement();
		
			String sql="select DISTINCT c.name  from category c join  product_category pc on c.category_id=pc.category_id "
					+ "join store_inventory si on pc.product_id=si.product_id where si.store_id="+store_id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				
				//CustHomePage cs= new CustHomePage();
				//cs.setVisible(true);
				//CustomerHome cs1= new CustomerHome();
				//cs1.frame.setVisible(true);
				comboBox.addItem(rs.getString(1));
				//pro_desc=rs.getString(2);
				//product_id=rs.getString(3);
				
			}
			
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		JLabel lblProductId = new JLabel("Product Name");
		lblProductId.setBounds(58, 277, 137, 16);
		frame.getContentPane().add(lblProductId);
		
		JLabel lblProductPrice = new JLabel("Product Price");
		lblProductPrice.setBounds(58, 368, 118, 16);
		frame.getContentPane().add(lblProductPrice);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(188, 274, 400, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(188, 365, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAddToCart_1 = new JButton("Next");
		btnAddToCart_1.setBounds(184, 208, 97, 25);
		frame.getContentPane().add(btnAddToCart_1);
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(188, 102, 93, 22);
		frame.getContentPane().add(comboBox_1);
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(188, 152, 93, 22);
		frame.getContentPane().add(comboBox_2);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		try {
			DB_Connection db = new DB_Connection();
			Connection con=db.get_connection();
			Statement stmt = con.createStatement();
			String sql="Select b.name from brand b join product_brand pb on b.brand_id=pb.brand_id "
					+ "join product_category pc on pc.product_id=pb.product_id join category pt on pc.category_id=pt.category_id  join store_inventory si on si.product_id=pb.product_id "
					+ "where pt.name ='"+comboBox.getSelectedItem().toString()+"' and si.store_id='"+store_id+"'";
			ResultSet rs = stmt.executeQuery(sql);
			comboBox_1.removeAllItems();
			while(rs.next())
			{
				
				//CustHomePage cs= new CustHomePage();
				//cs.setVisible(true);
				//CustomerHome cs1= new CustomerHome();
				//cs1.frame.setVisible(true);
				comboBox_1.addItem(rs.getString(1));
				
			}
			con.close();
		}
		catch(Exception ex1) {
			System.out.println(ex1);
		}
		
		
		
		try {
			DB_Connection db = new DB_Connection();
			Connection con=db.get_connection();
			Statement stmt = con.createStatement();
			String sql="select p.product_name,p.product_id from product p join product_brand pb on p.product_id=pb.product_id "
					+ "join brand b on b.brand_id=pb.brand_id join store_inventory si on si.product_id=p.product_id where b.name='"+comboBox_1.getSelectedItem()+"' and si.store_id='"+store_id+"'";
			ResultSet rs = stmt.executeQuery(sql);
			comboBox_2.removeAllItems();
			while(rs.next())
			{
				
				comboBox_2.addItem(rs.getString(1));
				product_id=rs.getString(2);
				
			}
			if(comboBox_2.getSelectedItem().toString().isEmpty())
			{
				product_name=comboBox_2.getItemAt(1).toString();
			}
			else 
				product_name=comboBox_2.getSelectedItem().toString();
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex);
		}	
		
			}
		});
		
		JButton btnAddToCart = new JButton("Order");
		btnAddToCart.setBounds(212, 520, 97, 25);
		frame.getContentPane().add(btnAddToCart);
		
		JLabel lblProductDescription = new JLabel("Product Description");
		lblProductDescription.setBounds(58, 319, 118, 16);
		frame.getContentPane().add(lblProductDescription);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(188, 306, 396, 52);
		frame.getContentPane().add(textArea);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(58, 397, 118, 16);
		frame.getContentPane().add(lblQuantity);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(188, 400, 66, 22);
		frame.getContentPane().add(comboBox_3);
		comboBox_3.addItem("0");
		
		JLabel lblVendor = new JLabel("Vendor");
		lblVendor.setBounds(58, 467, 56, 16);
		frame.getContentPane().add(lblVendor);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(186, 464, 81, 22);
		frame.getContentPane().add(comboBox_4);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(58, 438, 56, 16);
		frame.getContentPane().add(lblSize);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(188, 435, 65, 22);
		frame.getContentPane().add(comboBox_5);
		
		JButton btnLogout = new JButton("Back");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				StoreManagerHomePage shm= new  StoreManagerHomePage();
				shm.userName=userName;
				shm.frame.setVisible(true);
			}
		});
		btnLogout.setBounds(413, 23, 97, 25);
		frame.getContentPane().add(btnLogout);
		
		for (int i=1;i<101;i++)
		{
			comboBox_3.addItem(i);
		}
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		quantity=Integer.parseInt(comboBox_3.getSelectedItem().toString());}});
		
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				textField.setText(product_name);

					
					if ( quantity!=0 )  
					{
						try {
						DB_Connection db = new DB_Connection();
						Connection con=db.get_connection();
						String sql="select  order_id from minions.order order by order_id desc limit 1"; 
						Statement stmt2 = con.createStatement();
						ResultSet rs = stmt2.executeQuery(sql);
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
						String sql3="select  vendor_id from minions.vendor where name='"+comboBox_4.getSelectedItem().toString()+"'"; 
						Statement stmt3 = con.createStatement();
						ResultSet rs3 = stmt3.executeQuery(sql3);
						if(rs3.next()) {
							vendor_id=rs3.getString(1);
						}
						else
							vendor_id="new";
						
						Statement stmt6 = con.createStatement();
						//JOptionPane.showMessageDialog(null, userName);
						String sql6="select store_id from login_details where user_id='"+userName+"'";
						ResultSet rs6=stmt6.executeQuery(sql6);
						
						if(rs6.next()) {
							
							//JOptionPane.showMessageDialog(null, "Storeid "+rs6.getString(1));
							if(rs6.getString(1)!=null)
								store_id=Integer.parseInt(rs6.getString(1));
							else
								store_id=1;
							
						}
						Statement stmt8 = con.createStatement();
						if(store_id!=1) {
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
						Statement stmt9 = con.createStatement();
							      String sql9="select unit_price from store_inventory where product_id in(Select product_id from product "
											+ "where product_name= '"+product_name+"')";
									ResultSet rs9=stmt9.executeQuery(sql9);
						if(rs9.next()) { unit_price=Float.parseFloat(rs9.getString(1));}			
						Statement stmt = con.createStatement();
						String x= "INSERT INTO minions.order(`order_id`,`store_id`,`order_date`,`total_price`,`street`,`city`,`state`,`country`,`zip`,`delivery_date`,`vendor_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
						PreparedStatement preparedStmt = con.prepareStatement(x);
					      preparedStmt.setString(1, ordernum2);
					      preparedStmt.setInt(2, store_id);
					      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					      Calendar cal = Calendar.getInstance();
					     String dates=dateFormat.format(cal.getTime());
					    
					      preparedStmt.setString (3, dates);
					      String totalprice;
					      
					      float total=unit_price*(Integer.parseInt(comboBox_3.getSelectedItem().toString()));
					      preparedStmt.setFloat(4,total);
					      preparedStmt.setString(5, street);
					      preparedStmt.setString(6,city);
					      preparedStmt.setString(7, state);
					      preparedStmt.setString(8, country);
					      preparedStmt.setString(9, zip);
					      preparedStmt.setString(10, null);
					     preparedStmt.setString(11, vendor_id);
					      preparedStmt.executeUpdate();
						
					      String x1= "INSERT INTO minions.product_order(`order_id`,`product_id`,`quantity`,`unit_price`,`total_price`) VALUES (?,?,?,?,?)";
							PreparedStatement preparedStmt1 = con.prepareStatement(x1);
						      preparedStmt1.setString(1, ordernum2);
						      preparedStmt1.setString(2, product_id);
						      preparedStmt1.setString(3,comboBox_3.getSelectedItem().toString());
						    //  float price=Float.valueOf(deets[i][3])/Float.valueOf(deets[i][2]);
						      preparedStmt1.setString(4, String.valueOf(unit_price));
						      preparedStmt1.setString(5,Float.toString(total));
						      preparedStmt1.executeUpdate();
						      JOptionPane.showMessageDialog(null, "Your order is placed, your order number is "+ordernum2);
						      frame.dispose();
						      ThankyouPage tp=new ThankyouPage();
						      tp.user=userName;
						      tp.frame.setVisible(true);
								con.close();
						      
								//PreparedStatement preparedStmt2 = con.prepareStatement("Update store_inventory set stock_qty=? where store_id=?");
								//preparedStmt2.setString(1, Integer.toString(final_quantity));
								//preparedStmt2.setString(2, Integer.toString(store_id));
								//preparedStmt2.executeUpdate();
					}
						catch(Exception ex) {System.out.println( ex);}}
					else {
						JOptionPane.showMessageDialog(null, "Enter a valid quantity");
					}
					
				}
				
					}
				);
		cpc.deets=deets;
		btnAddToCart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(product_name);
				try {
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					Statement stmt = con.createStatement();
					Statement stmt2 = con.createStatement();
					Statement stmt3= con.createStatement();
					Statement stmt4=con.createStatement();
					String sql="select description from product where product_name='"+product_name+"'";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						pro_desc=rs.getString(1);
						textArea.setText(pro_desc);
						
						
					}
					String sql2="select unit_price from store_inventory where product_id in(Select product_id from product "
							+ "where product_name= '"+product_name+"')";
					ResultSet rs2=stmt2.executeQuery(sql2);
					
					
					
					while(rs2.next())
					{
						
						
						textField.setText(product_name);
						textField_1.setText(rs2.getString(1));
						price=Double.parseDouble(rs2.getString(1));
						//Integer.parseInt(rs2.getString(1));
						//pd.lblPrice.setText(rs2.getString(1));
						//pdc.price=rs2.getString(1);
						//pdc.lblPrice.setText(rs2.getString(1));
						
					}
					String sql3="select distinct size from product_size where product_id in(Select product_id from product "
							+ "where product_name= '"+product_name+"')";
					ResultSet rs3=stmt3.executeQuery(sql3);
					while(rs3.next())
					{
						comboBox_5.addItem(rs3.getString(1));
					}
					
					String sql4="select v.name from vendor v join product_vendor pv on v.vendor_id=pv.vendor_id where pv.product_id in(Select product_id from product "  
													+ "where product_name= '"+product_name+"')";
					ResultSet rs4=stmt4.executeQuery(sql4);
					while(rs4.next())
					{
						comboBox_4.addItem(rs4.getString(1));
					}
					
					con.close();
			}
				catch (Exception ex) {
					System.out.println(ex);
				}	
			}});
	
		
		
	}
}

