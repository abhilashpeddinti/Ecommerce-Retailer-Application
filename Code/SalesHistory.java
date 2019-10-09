import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalesHistory {

	public JFrame frame;
	public JTable table;
	private JButton btnBack;
	public String userName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesHistory window = new SalesHistory();
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
	public SalesHistory() {
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
		
		JLabel lblSalesHistory = new JLabel("Sales History");
		lblSalesHistory.setForeground(Color.RED);
		lblSalesHistory.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblSalesHistory.setBounds(161, 25, 113, 16);
		frame.getContentPane().add(lblSalesHistory);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(53, 65, 333, 124);
		frame.getContentPane().add(scrollPane);
		
		Vector rows = new Vector();
		Vector headers = new Vector();
		headers.addElement("Product ID");
		headers.addElement("Quantity");
		

		table = new JTable(rows, headers);
		//table = new JTable();
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StoreManagerHomePage shm=new StoreManagerHomePage();
				shm.userName=userName;
				shm.frame.setVisible(true);
			}
		});
		btnBack.setBounds(269, 16, 97, 25);
		frame.getContentPane().add(btnBack);
	}

}
