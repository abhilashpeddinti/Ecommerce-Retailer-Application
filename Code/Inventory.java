
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

//import net.proteanit.sql.DbUtils;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class Inventory {

	public JFrame frame;
	public JTable table; 
	private JButton btnBack;
	public	String	product_id;
	public	String	stock_qty;
	public	String	price;
	public  String	userName;
	public	int	store;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
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
	public Inventory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblStoreInventory = new JLabel("Store Inventory");
		lblStoreInventory.setForeground(Color.RED);
		lblStoreInventory.setBounds(132, 31, 124, 24);
		frame.getContentPane().add(lblStoreInventory);
		
		
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StoreManagerHomePage sm = new StoreManagerHomePage();
				sm.userName = userName;
				sm.frame.setVisible(true);
				
			}
		});
		btnBack.setBounds(274, 31, 97, 25);
		frame.getContentPane().add(btnBack);
		
		JButton btnNewButton = new JButton("Load Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//String sql="select product_name,stock_qty,unit_price from store_inventory "
							//+ "natural join product where store_id = "+userName;
					 String sql = "select b.product_name,a.stock_qty,a.unit_price from store_inventory a,product b,login_details c\r\n" + 
							"where a.store_id = c.store_id\r\n" + 
							"and   a.product_id = b.product_id\r\n" + 
							"and   c.user_id = '"+userName+"'"; 
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					PreparedStatement stmt = con.prepareStatement(sql);

					ResultSet rs=stmt.executeQuery();
					Vector inv = new Vector();
					while(rs.next())
					{
						Vector row = new Vector();
					    row.add(rs.getString(1));
					    row.add(rs.getInt(2));
					    row.add(rs.getString(3));
					    inv.add(row);
						
					}
					
					Vector columns = new Vector();
					columns.add("Product Name");
					columns.add("Stock Qty");
					columns.add("Unit Price");
					
					JTable jtable = new JTable(inv, columns);

				    
					JFrame newFrame = new JFrame("Products in the Store Inventory");
					newFrame.setVisible(true);
					newFrame.getContentPane().add(new JScrollPane(jtable));
					newFrame.pack();
					
					con.close();
					
				
				}
				catch (Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		btnNewButton.setBounds(132, 116, 97, 25);
		frame.getContentPane().add(btnNewButton);
}
}