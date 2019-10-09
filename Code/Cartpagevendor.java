import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

//import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

public class Cartpagevendor {

	public JFrame frame;
	public String reorder;
	public String userName;

	public JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cartpagevendor window = new Cartpagevendor();
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
	public Cartpagevendor() {
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
		
		JLabel lblOrderDetails = new JLabel("Order Details");
		lblOrderDetails.setBounds(164, 16, 105, 20);
		lblOrderDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblOrderDetails.setForeground(Color.RED);
		frame.getContentPane().add(lblOrderDetails);
		
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Vendor vc= new Vendor();
				vc.userName = userName;
				vc.frame.setVisible(true);
			}
		});
		btnBack.setBounds(15, 12, 65, 29);
		frame.getContentPane().add(btnBack);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				FirstScreen fs= new FirstScreen();
				fs.frame.setVisible(true);
			}
		});
		btnLogout.setBounds(325, 12, 88, 29);
		frame.getContentPane().add(btnLogout);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(94, 89, 250, 134);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnViewOrder = new JButton("View Order");
		btnViewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					Statement stmt = con.createStatement();
					String sql="select a.product_id,c.product_name,a.quantity from product_order a,reorder b,product c"
							+ " where  a.order_id = b.order_id and a.product_id = c.product_id and b.reorder_id = '"+reorder+"'"; 
					String sql2="select  a.product_id,b.product_name,a.quantity from product_order a join product b where "
							+ "a.product_id = b.product_id and order_id ='"+reorder+"'";
					ResultSet rs = stmt.executeQuery(sql);
					//table.setModel(DbUtils.resultSetToTableModel(rs));
					Vector inv = new Vector();
					
					
					if(rs.next())
					{	
						rs.close();
						ResultSet rs1 = stmt.executeQuery(sql);
						while(rs1.next()) {
						Vector row = new Vector();
						row.add(rs1.getString(1));
						row.add(rs1.getString(2));
						row.add(rs1.getInt(3));
						inv.add(row);
						}
						rs1.close();					
					}
					  else {
						rs.close();
						ResultSet rs2 = stmt.executeQuery(sql2);
						while(rs2.next()) {
						Vector row = new Vector();
						row.add(rs2.getString(1));
						row.add(rs2.getString(2));
						row.add(rs2.getInt(3));
						inv.add(row);
						}
						rs2.close();
					  }
					  
					Vector columns = new Vector();
					columns.add("Product ID");
					columns.add("Product Name");
					columns.add("Quantity");
						
					JTable jtable = new JTable(inv, columns);

					    
					JFrame newFrame = new JFrame("Products in the Order");
					newFrame.setVisible(true);
					newFrame.getContentPane().add(new JScrollPane(jtable));
					newFrame.pack();
				}
				catch (Exception ex)
				{
					System.out.println(ex);
				}
				
			}
		});
		btnViewOrder.setBounds(153, 44, 115, 29);
		frame.getContentPane().add(btnViewOrder);
	}
}