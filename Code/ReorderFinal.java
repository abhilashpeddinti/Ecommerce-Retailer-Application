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
import javax.swing.table.TableModel;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class ReorderFinal {

	public JFrame frame;
	public String user;
	public JTable table;
	public String product_name;
	public double totalprice;
	public String product_id;
	public String quantity;
	public String userName;
	public ArrayList deets=new ArrayList<>();
	public int addtocart;
	public JTextField textField;
	public String orderid="0";
	public String original_order_id;
	public String previous_order="0";
	public String ordernum2="";
	public String customerid,street,city,state,country,zip;
	public float price;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReorderFinal window = new ReorderFinal();
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
	public ReorderFinal() {
		
		initialize();
		
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
		
		Vector rows = new Vector();
		Vector headers = new Vector();
		headers.addElement("Product ID");
		headers.addElement("Quantity");
		headers.addElement("Total Price");

		table = new JTable(rows, headers);
		
		JLabel lblReorderFinal = new JLabel("Order details");
		lblReorderFinal.setForeground(Color.RED);
		lblReorderFinal.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblReorderFinal.setBounds(175, 13, 140, 20);
		frame.getContentPane().add(lblReorderFinal);
		
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(12, 65, 408, 124);
		frame.getContentPane().add(scrollPane);
		
		textField = new JTextField();
		textField.setBounds(316, 220, 104, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Total Price");
		lblNewLabel.setBounds(232, 223, 83, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Reorder");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				ThankyouPage tp= new ThankyouPage();
				
				try {
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					Statement stmt = con.createStatement();
					Statement stmt2 = con.createStatement();
					Statement stmt3 = con.createStatement();
					Statement stmt4 = con.createStatement();
					Statement stmt5 = con.createStatement();
					String sql="select  reorder_id from minions.reorder order by reorder_id desc limit 1"; 
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next())
					{
						orderid=rs.getString(1);
						 
						if (orderid.length() > 3) {
							  String orderno= orderid.substring(orderid.length() - 3);
							  int ordernum=Integer.parseInt(orderno)+1; 
							  ordernum2=orderid.substring(0, orderid.length()-3)+Integer.toString(ordernum);
							  
							  
							} 
						
					}
					else
					{
						orderid="1";
						
						
					}
					
					//customer_id,order_date,total_price,street,city,state,country,zip
					//String sql2="Select role from login_details where user_id ='"+user.getText().toString()+"' and password = '"+pass.getText().toString()+"'";
					String sql2="Select customer_id from login_details where user_id='"+userName+"'";
					ResultSet rs2=stmt2.executeQuery(sql2);
					if(rs2.next()) {
						customerid=rs2.getString(1);
					}
					String sql3="Select street,city, state,country,zip from customer where customer_id='"+customerid+"'";
					ResultSet rs3=stmt3.executeQuery(sql3);
					if(rs3.next()) {
						street=rs3.getString(1);
						city=rs3.getString(2);
						country=rs3.getString(4);
						state=rs3.getString(3);
						zip=rs3.getString(5);
					}
					String vendor_id=null;
					String sql4="Select vendor_id from minions.order where order_id='"+original_order_id+"'";
					ResultSet rs5=stmt5.executeQuery(sql4);
					if(rs5.next()) {
						vendor_id=rs5.getString(1);
						//JOptionPane.showMessageDialog(null, vendor_id);
					}
					String x= "INSERT INTO minions.reorder(`reorder_id`,`order_id`,`vendor_id`,`customer_id`,`delivery_date`,`order_status`) VALUES (?,?,?,?,?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(x);
				      preparedStmt.setString(1, ordernum2);
				     // JOptionPane.showMessageDialog(null, original_order_id);
				      preparedStmt.setString(2, original_order_id);
				     // JOptionPane.showMessageDialog(null, "Vendor id "+vendor_id);
				      preparedStmt.setString(3,vendor_id );
				      preparedStmt.setString(4,customerid);
				      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				      Calendar cal = Calendar.getInstance();
				      String deliveryDate=dateFormat.format(cal.getTime());
				      preparedStmt.setString(5, deliveryDate);
				      preparedStmt.setString(6,"placed");
				     //JOptionPane.showMessageDialog(null, x);
				      preparedStmt.executeUpdate();
				      
				     // JOptionPane.showMessageDialog(null, table.getValueAt(1, 1).toString());
				     // for(int i=0;i<addtocart;i++) {
					      
					  //    String x1= "INSERT INTO minions.product_order(`order_id`,`product_id`,`quantity`,`unit_price`,`total_price`) VALUES (?,?,?,?,?)";
						//	PreparedStatement preparedStmt1 = con.prepareStatement(x1);
						 //     preparedStmt1.setString(1, "Reorder"+ordernum2);
						      
						 //     preparedStmt1.setString(2, table.getValueAt(i, 0).toString());
						      
						 //     preparedStmt1.setString(3,table.getValueAt(i, 1).toString());
						 //     float prices=Float.valueOf(table.getValueAt(i, 2).toString())/Float.valueOf(table.getValueAt(i, 1).toString());
						//      preparedStmt1.setString(4, String.valueOf(prices));
						 //     preparedStmt1.setString(5,table.getValueAt(i, 2).toString());
						//      preparedStmt1.executeUpdate();
						      
					    //  }
					      
				      JOptionPane.showMessageDialog(null, "Your order is placed, your order number is "+ordernum2);
					con.close();
				}
				
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
		//lblNewLabel.setText(Float.toString(price));
		
		
	}
}
