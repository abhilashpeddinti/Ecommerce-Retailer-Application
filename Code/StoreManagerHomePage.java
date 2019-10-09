import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class StoreManagerHomePage {

	public JFrame frame;
	public String userName="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreManagerHomePage window = new StoreManagerHomePage();
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
	public StoreManagerHomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblStoremanagerHomepage = new JLabel("StoreManager HomePage");
		lblStoremanagerHomepage.setForeground(Color.RED);
		lblStoremanagerHomepage.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblStoremanagerHomepage.setBounds(125, 13, 199, 33);
		frame.getContentPane().add(lblStoremanagerHomepage);
		
		JButton btnViewInventory = new JButton("View Inventory");
		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Inventory in= new Inventory();
				in.userName=userName;
				in.frame.setVisible(true);
			}
		});
		btnViewInventory.setBounds(155, 59, 126, 25);
		frame.getContentPane().add(btnViewInventory);
		
		JButton btnViewSalesHistory = new JButton("View Order History");
		
		btnViewSalesHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//JOptionPane.showMessageDialog(null, userName);
				SalesHistory sh= new SalesHistory();
				try {
				DB_Connection db = new DB_Connection();
				Connection con=db.get_connection();
				Statement stmt2 = con.createStatement();
				String sql2=" select product_id, count(product_id) from minions.product_order where order_id in (Select order_id from minions.order where store_id in \r\n" + 
						" (Select store_id from minions.login_details where user_id='"+userName+"')) group by product_id";
				ResultSet rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					
					DefaultTableModel model = (DefaultTableModel) sh.table.getModel();
					model.addRow(new Object[]{rs2.getString(1), rs2.getString(2)});
					
				}
				sh.userName=userName;
				sh.frame.setVisible(true);
			}
				catch(Exception ex) {System.out.println(ex);}
			}
		});
		btnViewSalesHistory.setBounds(144, 97, 156, 25);
		frame.getContentPane().add(btnViewSalesHistory);
		
		JButton btnRecordASale = new JButton("Record a Sale");
		btnRecordASale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				NewCustomerHomePage ch= new NewCustomerHomePage();
				ch.userName=userName;
				//JOptionPane.showMessageDialog(null, userName);
				ch.btnViewOrderHistory.setVisible(false);
				try {
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					Statement stmt = con.createStatement();
					
					String sql="select DISTINCT c.name  from category c join  product_category pc on c.category_id=pc.category_id "
							+ "join store_inventory si on pc.product_id=si.product_id  join login_details l "
							+ "on si.store_id = l.store_id where l.user_id='"+userName+"'";
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next())
					{
						ch.comboBox.addItem(rs.getString(1));
					}
					
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				ch.frame.setVisible(true);
			}
		});
		btnRecordASale.setBounds(155, 180, 126, 25);
		frame.getContentPane().add(btnRecordASale);
		
		JButton btnReorder = new JButton("Reorder");
		btnReorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Reorder rd = new Reorder();
				rd.user=userName;
				rd.frame.setVisible(true);
				try {
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					Statement stmt2 = con.createStatement();
					String sql2="select order_id from minions.order o join  minions.login_details c on o.store_id=c.store_id  where user_id='"+userName+"' order by order_date desc";
					ResultSet rs2 = stmt2.executeQuery(sql2);
					while (rs2.next()) {
						rd.comboBox.addItem(rs2.getString(1));
					}}
					catch(Exception ex) {System.out.println(ex);}
			}
		});
		btnReorder.setBounds(169, 252, 97, 25);
		frame.getContentPane().add(btnReorder);
		
		JButton btnCreateANew = new JButton("Create a New Order");
		btnCreateANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				NewStoreManagerPage ch= new NewStoreManagerPage();
				ch.userName=userName;
				ch.frame.setVisible(true);
				
			}
		});
		btnCreateANew.setBounds(131, 218, 169, 25);
		frame.getContentPane().add(btnCreateANew);
		
		JButton btnViewTopSales = new JButton("View Top Sales");
		btnViewTopSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				TopSales ts=new TopSales();
				ts.userName=userName;
				ts.frame.setVisible(true);
				
			}
		});
		btnViewTopSales.setBounds(154, 135, 127, 25);
		frame.getContentPane().add(btnViewTopSales);
	}

}
