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
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class NewCustomerHomePage {

	public JFrame frame;
	public String user="Customer";
	public int store_id=1;
	public String product_name="";
	public String pro_desc;
	public String product_id;
	public String userName;
	private JTextField textField_1;
	private double price;
	public int quantity=0;
	public String user_id="";
	public String[][] deets=new String[10][4];
	public int addtocart=0;
	public JButton btnViewCartPage;
	public JButton btnViewOrderHistory;
	public JComboBox comboBox;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomerHomePage window = new NewCustomerHomePage();
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
	public NewCustomerHomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CartPageCustomer cpc= new CartPageCustomer();
		OrderHistory oh=new OrderHistory();
		oh.user=userName;
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
		
		comboBox = new JComboBox();
		comboBox.setBounds(188, 61, 93, 22);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(188, 102, 93, 22);
		frame.getContentPane().add(comboBox_1);
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(188, 152, 93, 22);
		frame.getContentPane().add(comboBox_2);
/*try {
			DB_Connection db = new DB_Connection();
			Connection con=db.get_connection();
			Statement stmt = con.createStatement();
			
			String sql="select c.name,pc.product_id  from category c join  product_category pc on c.category_id=pc.category_id "
					+ "join store_inventory si on pc.product_id=si.product_id where si.store_id="+store_id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				comboBox.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {
			System.out.println(ex);
		}*/
		JLabel lblProductId = new JLabel("Product Name");
		lblProductId.setBounds(58, 280, 137, 16);
		frame.getContentPane().add(lblProductId);
		
		JLabel lblProductPrice = new JLabel("Product Price");
		lblProductPrice.setBounds(58, 393, 118, 16);
		frame.getContentPane().add(lblProductPrice);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(188, 390, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAddToCart_1 = new JButton("Next");
		btnAddToCart_1.setBounds(184, 208, 97, 25);
		frame.getContentPane().add(btnAddToCart_1);
		
		
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			try {
				DB_Connection db = new DB_Connection();
				Connection con=db.get_connection();
				Statement stmt = con.createStatement();
				
				String sql="select p.product_name,p.product_id from product p join product_brand pb on p.product_id=pb.product_id "
						+ "join brand b on b.brand_id=pb.brand_id join store_inventory si on si.product_id=p.product_id"
						+ " where b.name='"+comboBox_1.getSelectedItem()+"' and si.store_id="+store_id;
				ResultSet rs = stmt.executeQuery(sql);
				comboBox_2.removeAllItems();
				while(rs.next())
				{
					
					comboBox_2.addItem(rs.getString(1));
					//product_id=rs.getString(2);
					
				}
				if(comboBox_2.getSelectedItem().toString().isEmpty())
				{
					product_name=comboBox_2.getItemAt(1).toString();
				}
				else { 
					product_name=comboBox_2.getSelectedItem().toString();
					Statement stmt2 = con.createStatement();
					//JOptionPane.showMessageDialog(null, product_name);
					String sql2="select product_id from product where product_name='"+product_name+"'";
					ResultSet rs2 = stmt2.executeQuery(sql2);
					//comboBox_2.removeAllItems();
					while(rs2.next()) {
						product_id=rs2.getString(1);
						//JOptionPane.showMessageDialog(null, "product_id modified "+product_id);
					}
				
				}
				con.close();
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		try {
			DB_Connection db = new DB_Connection();
			Connection con=db.get_connection();
			Statement stmt2 = con.createStatement();
			String sql2="Select store_id from login_details where user_id='"+userName+"'";
			ResultSet rs2= stmt2.executeQuery(sql2);
			if(rs2.next()) { 
				if (rs2.getString(1)!=null)
						store_id= Integer.parseInt(rs2.getString(1));
				else
					store_id=1;
				}
			else
				store_id=1;
			
			Statement stmt = con.createStatement();
			String sql="Select b.name from brand b join product_brand pb on b.brand_id=pb.brand_id "
					+ "join product_category pc on pc.product_id=pb.product_id join category pt on pc.category_id=pt.category_id join store_inventory "
					+ "si on si.product_id=pc.product_id  "
					+ "where pt.name ='"+comboBox.getSelectedItem().toString()+"' and si.store_id="+store_id;
			ResultSet rs = stmt.executeQuery(sql);
			if(comboBox_1.getItemCount()!=0)
				//comboBox_1.
				comboBox_1.removeAllItems();
			while(rs.next())
			{
				comboBox_1.addItem(rs.getString(1));	
			}
			con.close();
		}
		catch(Exception ex1) {
			System.out.println(ex1);
		}
		
		
		
		
		
			}
		});
		
		JButton btnAddToCart = new JButton("AddtoCart");
		btnAddToCart.setBounds(217, 501, 97, 25);
		frame.getContentPane().add(btnAddToCart);
		
		btnViewCartPage = new JButton("View Cart Page ");
		btnViewCartPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				JOptionPane.showMessageDialog(null, "Click on View order to view items added to cart");
				cpc.frame.setVisible(true);
			}
		});
		btnViewCartPage.setBounds(200, 569, 156, 25);
		frame.getContentPane().add(btnViewCartPage);
		
		JLabel lblProductDescription = new JLabel("Product Description");
		lblProductDescription.setBounds(57, 326, 118, 16);
		frame.getContentPane().add(lblProductDescription);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setBounds(188, 323, 396, 52);
		frame.getContentPane().add(textArea);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(57, 428, 118, 16);
		frame.getContentPane().add(lblQuantity);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(187, 425, 66, 22);
		frame.getContentPane().add(comboBox_3);
		comboBox_3.addItem("0");
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(58, 457, 118, 16);
		frame.getContentPane().add(lblSize);
		
		JComboBox comboBox_4 = new JComboBox();
		
		comboBox_4.setBounds(188, 454, 44, 22);
		frame.getContentPane().add(comboBox_4);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setEditable(false);
		textArea_1.setBounds(188, 277, 396, 39);
		frame.getContentPane().add(textArea_1);
		
		btnViewOrderHistory = new JButton("View Order History");
		btnViewOrderHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
				DB_Connection db = new DB_Connection();
				Connection con=db.get_connection();
				Statement stmt2 = con.createStatement();
				String sql2="select order_id,c.store_id from minions.order o join  minions.login_details c on o.customer_id=c.customer_id  where user_id='"+userName+"' order by order_date desc";
				ResultSet rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					oh.comboBox.addItem(rs2.getString(1));
					
				}
				oh.user=userName;
				//JOptionPane.showMessageDialog(null, userName);
				oh.frame.setVisible(true);
				
				}
				catch (Exception ex) {
					System.out.println(ex);
				}
				
			}
		});
		btnViewOrderHistory.setBounds(444, 569, 156, 25);
		frame.getContentPane().add(btnViewOrderHistory);
		
		for (int i=1;i<101;i++)
		{
			comboBox_3.addItem(i);
		}
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		quantity=Integer.parseInt(comboBox_3.getSelectedItem().toString());}});
		
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, userName);
				
				
				textArea_1.setText(product_name);

				cpc.userName=userName;
					if ( quantity!=0 && !(textArea.getText().trim().equals("")) && !(textArea_1.getText().trim().equals("")))  
					{
						
						//JOptionPane.showMessageDialog(null, "product id is "+product_id);
						//cpc.userName=userName;
						cpc.user=user;
						cpc.product_name=product_name;
						//cpc.totalprice=price;
						deets[addtocart][0]= product_id;
						deets[addtocart][1]=product_name;
						deets[addtocart][2]=String.valueOf(quantity);
						deets[addtocart][3]=String.valueOf(price*quantity);
						cpc.totalprice+=price*quantity;
						addtocart++;
						cpc.addtocart=addtocart;
						//frame.revalidate();
						JOptionPane.showMessageDialog(null, "Added to cart.Select more items or Click View Cart Page.");
						textArea.setText("");
						textArea_1.setText("");
						textField_1.setText("");
						comboBox_4.setSelectedIndex(-1);
						
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Enter a valid quantity/ Click Next");
					}
					
					
				}
				
					}
				);
		cpc.deets=deets;
		
		btnAddToCart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(product_name);
				try {
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					Statement stmt = con.createStatement();
					Statement stmt2 = con.createStatement();
					Statement stmt3= con.createStatement();
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
						
						
						textArea_1.setText(product_name);
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
						comboBox_4.addItem(rs3.getString(1));
					}
					con.close();
			}
				catch (Exception ex) {
					System.out.println(ex);
				}	
			}});
	
		
		
	}
}

