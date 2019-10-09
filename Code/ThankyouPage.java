import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ThankyouPage {

	public JFrame frame;
	public String user="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThankyouPage window = new ThankyouPage();
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
	public ThankyouPage() {
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
		
		JLabel lblThankYouFor = new JLabel("Thank you for your order");
		lblThankYouFor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
		lblThankYouFor.setForeground(Color.RED);
		lblThankYouFor.setBounds(128, 91, 230, 41);
		frame.getContentPane().add(lblThankYouFor);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				FirstScreen fs= new FirstScreen();
				fs.frame.setVisible(true);
			}
		});
		btnLogout.setBounds(168, 165, 97, 25);
		frame.getContentPane().add(btnLogout);
	}

}
