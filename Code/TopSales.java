
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.Font;
import java.awt.Insets;
import java.awt.List;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class TopSales {

	public JFrame frame;
	private JTextField textField_1;
	public  int cat1=0;  int cat2=0;
	public  String combval2="";
	public  String combval3="";
	double tprice1=0; double tprice2=0;
	String combproduct="";
	String combproduct_id ="";
	ArrayList combproductlist= new ArrayList();
	String  comboState ="";
	String  query;
	public String userName;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopSales window = new TopSales();
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
	public TopSales() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 648, 612);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTopSales = new JLabel("Top Sales");
		lblTopSales.setForeground(Color.RED);
		lblTopSales.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTopSales.setBounds(164, 13, 120, 20);
		frame.getContentPane().add(lblTopSales);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					String sql="select product_name,store_id,max(qty)\r\n" + 
							"from product\r\n" + 
							"natural join (select product_id,store_id,sum(quantity) as qty\r\n" + 
							" from minions.`order` \r\n" + 
							" join minions.`product_order`\r\n" + 
							" on minions.`order`.order_id = minions.`product_order`.order_id \r\n" + 
							" where store_id is not null\r\n" + 
							" group by store_id,product_id) as top_prod \r\n" + 
							" where product.product_id= top_prod.product_id\r\n" + 
							" group by store_id";
					ResultSet rs = stmt.executeQuery(sql);
					Vector top_prod = new Vector();
					while(rs.next())
					{
						Vector row = new Vector();
					        row.add(rs.getString(1));
					        row.add(rs.getInt(2));
							row.add(rs.getInt(3));
					        top_prod.add(row);
						
					}
					
					Vector columns = new Vector();
					columns.add("Product Name");
					columns.add("Store Id");
					columns.add("Quantity");
					
					JTable jtable = new JTable(top_prod, columns);

				    
					JFrame newFrame = new JFrame("Top Selling Products In Each Store ");
					newFrame.setVisible(true);
					newFrame.getContentPane().add(new JScrollPane(jtable));
					newFrame.pack();
					
					con.close();
				}
				catch (Exception e1)
				{
					System.out.println(e1);
				}
				
			}
		});
		btnNewButton.setBounds(41, 87, 146, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblTopSellingProducts = new JLabel("Top selling products by Store:");
		lblTopSellingProducts.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTopSellingProducts.setBounds(41, 49, 379, 16);
		frame.getContentPane().add(lblTopSellingProducts);
		
		JLabel lblTopSellingProducts_1 = new JLabel("Top selling products by State:");
		lblTopSellingProducts_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTopSellingProducts_1.setBounds(41, 142, 232, 16);
		frame.getContentPane().add(lblTopSellingProducts_1);
		
		JComboBox comboBox_1 = new JComboBox();
		try {
			DB_Connection db = new DB_Connection();
			Connection con = db.get_connection();
			Statement stmt =con.createStatement();
			
			String sql="\r\n" + 
					" SELECT distinct state FROM minions.order \r\n" + 
					" join product_order\r\n" + 
					" where minions.`order`.order_id = minions.`product_order`.order_id \r\n" + 
					" order by state";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				String cat = rs.getString(1);
				comboBox_1.addItem(cat);
				
			}
			
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		comboBox_1.setBounds(148, 171, 162, 22);
		frame.getContentPane().add(comboBox_1);
		
		JButton button = new JButton("Search");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String comboState = (String) comboBox_1.getSelectedItem();
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					String sql="select product_name,qty,state\r\n" + 
							"from product\r\n" + 
							"natural join\r\n" + 
							"(select product_id,minions.order.order_id,sum(quantity) as qty,state from product_order\r\n" + 
							"join\r\n" + 
							"minions.order\r\n" + 
							"on minions.`order`.order_id = minions.`product_order`.order_id \r\n" + 
							"where state ='"+comboState+"'\r\n" + 
							"group by product_id) as top_states\r\n" + 
							"order by qty desc\r\n" + 
							"LIMIT 5\r\n" + 
							"";
					ResultSet rs = stmt.executeQuery(sql);
					Vector top_prod = new Vector();
					while(rs.next())
					{
						Vector row = new Vector();
					        row.add(rs.getString(1));
					        row.add(rs.getInt(2));
							row.add(rs.getString(3));
					        top_prod.add(row);
						
					}
					
					Vector columns = new Vector();
					columns.add("Product Name");
					columns.add("Quantity Sold");
					columns.add("State");
					
					JTable jtable = new JTable(top_prod, columns);

				    
					JFrame newFrame = new JFrame("Top Selling Products In Each Store ");
					newFrame.setVisible(true);
					newFrame.getContentPane().add(new JScrollPane(jtable));
					newFrame.pack();
					
					con.close();
				}
				catch (Exception e1)
				{
					System.out.println(e1);
				}
				
			}
		});
		button.setBounds(364, 170, 97, 25);
		frame.getContentPane().add(button);
		
		JLabel lblProductSales = new JLabel("Compare product Sales");
		lblProductSales.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProductSales.setBounds(41, 232, 175, 16);
		frame.getContentPane().add(lblProductSales);		
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(197, 296, 168, 22);
		frame.getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(197, 330, 168, 22);
		frame.getContentPane().add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();		
		comboBox_4.setBounds(197, 263, 168, 22);
		frame.getContentPane().add(comboBox_4);
		
		try {
			DB_Connection db = new DB_Connection();
			Connection con = db.get_connection();
			Statement stmt =con.createStatement();
			
			String sql="SELECT name FROM minions.category where name is not null and name!='' ";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				String cat = rs.getString(1);
				comboBox_4.addItem(cat);
				
			}
			
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				
								
				String combval= (String)comboBox_4.getSelectedItem();			
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					comboBox_2.removeAllItems();
					comboBox_3.removeAllItems();
					
					String sql="select product_name from product natural join product_category natural join category where name='"+combval+"'";
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next())
					{
						String cat = rs.getString(1);
						comboBox_2.addItem(cat);
						comboBox_3.addItem(cat);
						
					}
					
					con.close();
				}
				catch (Exception e2)
				{
					System.out.println(e2); 
				}				
							
			}
		});
		
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				combval2= (String)comboBox_2.getSelectedItem();	
				combval3= (String)comboBox_3.getSelectedItem();	
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					
					String sql1="select sum(quantity) from product_order natural join product\r\n" + 
							     "where product_name='"+combval2+"' group by product_name" ;
					ResultSet rs = stmt.executeQuery(sql1);
					
					while(rs.next())
					{
						 cat1 = rs.getInt(1);
						
					} 
								
				
					con.close();				
				}							
				 
				catch (Exception e2)
				{
					System.out.println(e2); 
				}	
				
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					
					String sql="select sum(quantity) from product_order natural join product\r\n" + 
						     "where product_name='"+combval3+"' group by product_name";
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next())
					{
						 cat2 = rs.getInt(1);
						
					} 			

				}							
				 
				catch (Exception e2)
				{
					System.out.println(e2); 
				}	
				
				

				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					
					String sql1="select sum(total_price) from product_order natural join product\r\n" + 
						     "where product_name='"+combval2+"' group by product_name";
					ResultSet rs = stmt.executeQuery(sql1);
					
					while(rs.next())
					{
						tprice1 = rs.getInt(1);
						
					} 				
					

				}							
				 
				catch (Exception e2)
				{
					System.out.println(e2); 
				}	
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					
					String sql2="select sum(total_price) from product_order natural join product\r\n" + 
						     "where product_name='"+combval3+"' group by product_name";
					ResultSet rs1 = stmt.executeQuery(sql2);
					
					while(rs1.next())
					{
						tprice2 = rs1.getInt(1);
						
					} 			

				}							
				 
				catch (Exception e2)
				{
					System.out.println(e2); 
				}	
				
				
				String com="";
				if (cat1 > cat2) {
					
					com="Sales of product"+combval2+"are more than product " +combval3+"\n\n" ;
				}
				
				else if (cat1 < cat2) {
					
					com= "Sales of product \n"+combval3+"\n are more than product \n" +combval2+"\n\n" ;
				}
				
				else{
					
					com = "Sales of product \n"+combval2+"\n are equal to product \n" +combval3+"\n\n";
				}
								
				  JTextArea textArea = new JTextArea(com,10,80);
				  textArea.setFont(new Font("Serif", Font.BOLD, 16));
				  textArea.append("Total quantity sold for product '"+combval2+"' is '" +cat1+"' with a Total Price of '"+tprice1+"'\n");
				  textArea.append("Total quantity sold for product '"+combval3+"' is '" +cat2+"' with a Total Price of '"+tprice2+"'\n");
			      
			      textArea.setEditable(false);      
			      JScrollPane scrollPane = new JScrollPane(textArea);			      		     
			      JOptionPane.showMessageDialog(frame, scrollPane);
				
			      
			      
			}
			
			
			
		});
		btnCompare.setBounds(407, 295, 97, 25);
		frame.getContentPane().add(btnCompare);	
		
		
		
		
		
		JLabel lblCategory = new JLabel("Select Category:");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(41, 265, 124, 16);
		frame.getContentPane().add(lblCategory);
		
		JLabel lblItemsBoughtIn = new JLabel("Items bought in addition to a product");
		lblItemsBoughtIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemsBoughtIn.setBounds(41, 387, 324, 18);
		frame.getContentPane().add(lblItemsBoughtIn);
		
		JLabel lblProduct = new JLabel("Select Product:");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduct.setBounds(41, 426, 97, 16);
		frame.getContentPane().add(lblProduct);		
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StoreManagerHomePage sp = new StoreManagerHomePage();
				sp.userName=userName;
				sp.frame.setVisible(true);
			}
		});
		btnBack.setBounds(462, 13, 97, 25);
		frame.getContentPane().add(btnBack);
		
		textField_1 = new JTextField();
		
		
		
		textField_1.setBounds(132, 519, 233, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblQueryTheSales = new JLabel("Type Query:");
		lblQueryTheSales.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQueryTheSales.setBounds(41, 521, 97, 16);
		frame.getContentPane().add(lblQueryTheSales);
		
		JButton btnQuery = new JButton("Query");
		btnQuery.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					query = textField_1.getText().toString();
					ResultSet rs = stmt.executeQuery(query);
					
					ResultSetMetaData metaData = rs.getMetaData();
					int cols= metaData.getColumnCount();
					Vector qry = new Vector();
					while(rs.next())
					{
						Vector row = new Vector();
						for(int i=1;i<=cols;i++) {
														
							row.addElement(rs.getObject(i));
							
						}
						
					        qry.add(row);
						
					}
					Vector columns = new Vector();
					
					for(int i=1;i<=cols;i++) {
						if ((metaData.getTableName(i)).equals("login_details")) {
							
							JOptionPane.showMessageDialog(frame, "User can not access this table");
							break;
						}
						else {
							columns.addElement(metaData.getColumnLabel(i));
						}
						
						
						
					}	
								
					
					JTable jtable = new JTable(qry, columns);

				    
					JFrame newFrame = new JFrame("Query ");
					newFrame.setVisible(true);
					newFrame.getContentPane().add(new JScrollPane(jtable));
					newFrame.pack();
					
					con.close();
				}
				catch (Exception e1)
				{
					System.out.println(e1);
				}
			}
		});
		btnQuery.setBounds(407, 518, 97, 25);
		frame.getContentPane().add(btnQuery);
		
		JLabel lblNewLabel = new JLabel("Select First Product:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(41, 299, 124, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSelectSecondProduct = new JLabel("Select Second Product:");
		lblSelectSecondProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectSecondProduct.setBounds(41, 333, 146, 14);
		frame.getContentPane().add(lblSelectSecondProduct);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 239, 494, -10);
		frame.getContentPane().add(separator);
		
		JComboBox comboBox_5 = new JComboBox();
		
		try {
			DB_Connection db = new DB_Connection();
			Connection con = db.get_connection();
			Statement stmt =con.createStatement();
			comboBox_2.removeAllItems();
			comboBox_3.removeAllItems();
			
			String sql="select distinct product_name from product \r\n" + 
					"join\r\n" + 
					"(select product_id from product_order \r\n" + 
					"where order_id in(select order_id from minions.product_order\r\n" + 
					"group by order_id having count(product_id)>1)) as prod_id\r\n" + 
					"on product.product_id = prod_id.product_id;\r\n" + 
					"";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				String cat = rs.getString(1);
				comboBox_5.addItem(cat);
								
			}
			
			con.close();
		}
		catch (Exception e2)
		{
			System.out.println(e2); 
		}				
		comboBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combproduct= (String)comboBox_5.getSelectedItem();					
			}
			
			
			
		});
		comboBox_5.setBounds(148, 424, 222, 20);
		frame.getContentPane().add(comboBox_5);
		
		
		JButton button_2 = new JButton("Search");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextArea textArea = new JTextArea("",10,80);		
				
				textArea.setFont(new Font("Serif", Font.BOLD, 16));
				JScrollPane scrollPane = null;
				textArea.setText("");
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con = db.get_connection();
					Statement stmt =con.createStatement();
					
					
					String sql2="select product_id from product where product_name='"+combproduct+"' ";
					ResultSet rs1 = stmt.executeQuery(sql2);
					
					while(rs1.next())
					{
						combproduct_id = rs1.getString(1);
						
					} 	
					
					String sql3="select product_name from product \r\n" + 
							"natural join\r\n" + 
							"(select product_id from product_order\r\n" + 
							"where order_id in(select order_id from product_order\r\n" + 
							"where product_id = '"+combproduct_id+"') and product_id != '"+combproduct_id+"'\r\n" + 
							"group by product_id order by sum(quantity) desc) as prod_id\r\n" + 
							"where product.product_id = prod_id.product_id\r\n" + 
							"Limit 3;\r\n" + 
							"";
					ResultSet rs2 = stmt.executeQuery(sql3);
					
					while(rs2.next())
					{
												
						combproductlist.add(rs2.getString(1));
					} 
					
					if (combproductlist.isEmpty()) {
						
						JOptionPane.showMessageDialog(frame, "No other product has been ordered in addition to this product");
					}
					else {  
						textArea.append("Top products bought along with product '"+combproduct+"' are: \n\n");
						
						for (int i = 0; i < combproductlist.size(); i++) {
							
						
							  textArea.append((String) combproductlist.get(i));
							  textArea.append("\n");
							 
							}
						
						 textArea.setEditable(false);      
					     scrollPane = new JScrollPane(textArea);
					
					JOptionPane.showMessageDialog(frame, scrollPane);
					textArea.removeAll();
					combproductlist.clear();}
					
					  
				      
				      			      		     
				      
					

										
				}
				catch (Exception e2)
				{
					System.out.println(e2); 
				}	
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(407, 422, 97, 25);
		frame.getContentPane().add(button_2);
		
		JLabel lblNewLabel_1 = new JLabel("Select State:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(41, 175, 97, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Query The Sales:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(41, 483, 146, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
	}
}