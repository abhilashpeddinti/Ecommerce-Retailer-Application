import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class Vendor {

	public JFrame frame;
	public String userName;
	public String updateQuery;
	public String updateQuery2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vendor window = new Vendor();
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
	public Vendor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblOrderApprovals = new JLabel("Order Approvals");
		lblOrderApprovals.setBounds(140, 13, 135, 20);
		lblOrderApprovals.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblOrderApprovals.setForeground(Color.RED);
		frame.getContentPane().add(lblOrderApprovals);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(204, 115, 144, 26);
		frame.getContentPane().add(comboBox);
		
		
		
		JLabel lblOrderId = new JLabel("Order ID");
		lblOrderId.setBounds(76, 118, 69, 20);
		frame.getContentPane().add(lblOrderId);
		
		JButton Approve = new JButton("Approve");
		Approve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DB_Connection db = new DB_Connection();
					Connection con1=db.get_connection();
					Statement stmt = con1.createStatement();
					Statement stmt2 = con1.createStatement();
					updateQuery = "update minions.store_inventory a,minions.product_order b,minions.order c \r\n" + 
							"set	a.stock_qty	= a.stock_qty +  b.quantity,\r\n" + 
							"		c.delivery_date = curdate() + 3\r\n" + 
							"where a.product_id = b.product_id\r\n" + 
							"and a.store_id = c.store_id\r\n" + 
							"and b.order_id = c.order_id\r\n" + 
							"and c.delivery_date is null\r\n" + 
							"and b.order_id = '"+comboBox.getSelectedItem().toString()+"'";
					stmt.executeUpdate(updateQuery);

					updateQuery2 = "update store_inventory a,reorder b,minions.order c,product_order d\r\n" + 
							"set	a.stock_qty	= a.stock_qty +  d.quantity,\r\n" + 
							"		b.delivery_date = curdate() + 3,\r\n" + 
							"		c.delivery_date = curdate() + 3,\r\n" + 
							"       b.order_status = 'delivered'\r\n" + 
							"where a.product_id = d.product_id\r\n" + 
							"and a.store_id = c.store_id\r\n" + 
							"and b.order_id = c.order_id\r\n" + 
							"and b.order_id = d.order_id\r\n" + 
							"and b.reorder_id = '"+comboBox.getSelectedItem().toString()+"'" + 
							"and b.order_status = 'placed'";
					stmt2.executeUpdate(updateQuery2);
					
					con1.close();
					JOptionPane.showMessageDialog(null, "Approved");
					frame.revalidate();
					//ThankyouPage tp=new ThankyouPage();
					//tp.frame.setVisible(true);
				}
				catch (Exception ex)
				{
					System.out.println(ex);
				}
				/*
				try {
					DB_Connection db = new DB_Connection();
					Connection con2=db.get_connection();
					Statement stmt2 = con2.createStatement();
					updateQuery2 = "update store_inventory a,reorder b,minions.order c,product_order d\r\n" + 
							"set	a.stock_qty	= a.stock_qty +  d.quantity,\r\n" + 
							"		b.delivery_date = curdate() + 3,\r\n" + 
							"		c.delivery_date = curdate() + 3,\r\n" + 
							"       b.order_status = 'delivered'\r\n" + 
							"where a.product_id = d.product_id\r\n" + 
							"and a.store_id = c.store_id\r\n" + 
							"and b.order_id = c.order_id\r\n" + 
							"and b.order_id = d.order_id\r\n" + 
							"and b.reorder_id = '"+comboBox.getSelectedItem().toString()+"'" + 
							"and b.order_status = 'placed'";
					stmt2.executeUpdate(updateQuery2);
					con2.close();
				}
				catch (Exception ex)
				{
					System.out.println(ex);
				}
				*/
			}
		});
		Approve.setBounds(63, 170, 115, 29);
		frame.getContentPane().add(Approve);
		
		JButton btnNewButton_1 = new JButton("View Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Cartpagevendor cv= new Cartpagevendor();
				//cv.reorder	=	"110-29-6051";
				cv.reorder	=comboBox.getSelectedItem().toString();
				cv.userName	=	userName;
				cv.frame.setVisible(true);
			}
			
		});
		btnNewButton_1.setBounds(248, 170, 115, 29);
		frame.getContentPane().add(btnNewButton_1);
		JButton btnNewButton = new JButton("Load Orders");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comboBox.removeAll();
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					Statement stmt = con.createStatement();
					
					String sql	="select a.reorder_id from reorder a join login_details b "
							+ "where a.vendor_id = b.vendor_id and a.order_status = 'placed' "
							+ "and b.user_id = '"+userName+"'";
					
					ResultSet rs = stmt.executeQuery(sql);
					
					
					while(rs.next())
					{
						comboBox.addItem(rs.getString(1));
					}
					
					rs.close();
				}
					catch (Exception ex)
					{
						System.out.println(ex);
					}
					try {
						DB_Connection db = new DB_Connection();
						Connection con=db.get_connection();
						Statement stmt2 = con.createStatement();
						String sql2 = "select a.order_id from minions.order a join login_details b "
								+ "where a.vendor_id = b.vendor_id and a.delivery_date is null "
								+ "and b.user_id = '"+userName+"'";
					
					ResultSet rs2 = stmt2.executeQuery(sql2);
					while(rs2.next())
					{
						comboBox.addItem(rs2.getString(1));
					}
					
				}
				catch (Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		btnNewButton.setBounds(121, 60, 154, 25);
		frame.getContentPane().add(btnNewButton);		
		
		JButton btnNewButton_2 = new JButton("LogOut");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ThankyouPage tp=new ThankyouPage();
				tp.frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(174, 215, 97, 25);
		frame.getContentPane().add(btnNewButton_2);
	}
}