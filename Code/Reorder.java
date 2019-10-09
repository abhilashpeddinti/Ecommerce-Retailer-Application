import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Reorder {

	public JFrame frame;
	public String user;
	public JTable table;
	public String[][] deets=new String[4][4];
	public ArrayList arr=new ArrayList<>();
	public int addtocart=0;
	public JComboBox comboBox;
	public String order;
	public float price=0.0f;
	public String userName;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reorder window = new Reorder();
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
	public Reorder() {
		initialize();
	}
		
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 278);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		comboBox = new JComboBox();
		comboBox.setBounds(155, 70, 103, 22);
		frame.getContentPane().add(comboBox);
		JLabel lblReorder = new JLabel("Order History");
		lblReorder.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		lblReorder.setForeground(Color.RED);
		lblReorder.setBounds(155, 13, 116, 28);
		frame.getContentPane().add(lblReorder);
		
		//user=userName;
		//JOptionPane.showMessageDialog(null, user);
		JButton btnOrder = new JButton("View Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.dispose();
					//JOptionPane.showMessageDialog(null, user);
					DB_Connection db = new DB_Connection();
					Connection con=db.get_connection();
					//System.out.println(con);
					//Class.forName("com.mysql.jdbc.Driver");
					//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/world","wbadmin","aongpg1");
					Statement stmt = con.createStatement();
					
						
						//oh.frame.setVisible(true);
						
						
					String sql="select count(*) from minions.order o join  minions.login_details c on o.store_id=c.store_id  where user_id='"+user+"'";
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next())
					{
						
						if (Integer.parseInt(rs.getString(1))==0) {
							JOptionPane.showMessageDialog(null, "No orders to display");
						}
						else
						{
							
							ReorderFinal rf= new ReorderFinal();
							rf.userName=user;
							
							try {
								order=comboBox.getSelectedItem().toString();
								rf.original_order_id=order;
								//JOptionPane.showMessageDialog(null, "Order id "+order);
								Statement stmt3 = con.createStatement();
								String sql3="select o.product_id,o.quantity,o.total_price from minions.product_order o   where order_id='"+order+"'";
								ResultSet rs3 = stmt3.executeQuery(sql3);
								while (rs3.next()) {
									
									DefaultTableModel model = (DefaultTableModel) rf.table.getModel();
									model.addRow(new Object[]{rs3.getString(1), rs3.getString(2), rs3.getString(3)});
									price+=Float.parseFloat(rs3.getString(3));
									addtocart++;
								}
								rf.addtocart=addtocart;
								rf.price=price;
								//JOptionPane.showMessageDialog(null, price);
								rf.textField.setText(Float.toString(price));
						}
							catch (Exception ex)
							{
								System.out.println(ex);
							}
							
							rf.frame.setVisible(true);
							//rf.ordernum2=arr.;
						
					}
					
				}
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
			}

			
			
		});
		btnOrder.setBounds(42, 132, 316, 25);
		frame.getContentPane().add(btnOrder);
		
		
		
		
		//btnOrder.setVisible(false);
		
		
		
	}
}
