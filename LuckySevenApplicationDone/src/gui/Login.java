package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConnectionToDB.MyConnection;


import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
//import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel lblVirtualLuckySeven;
	private JLabel lblPoweredByNikola;
	private JButton btnAdmin;
	private JButton btnUser;
	private MyConnection myConnection;
	private static Connection connection;
	AdminMain adminMain;
	UserMain userMain;
	private static Login frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		
		myConnection = new MyConnection();
		connection = myConnection.getconn();
		
		setTitle("Lucky Seven");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 359);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 128));
		contentPane.setForeground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblVirtualLuckySeven = new JLabel("Lucky Seven");
		lblVirtualLuckySeven.setForeground(Color.LIGHT_GRAY);
		lblVirtualLuckySeven.setBackground(Color.BLACK);
		lblVirtualLuckySeven.setHorizontalAlignment(SwingConstants.CENTER);
		lblVirtualLuckySeven.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblVirtualLuckySeven.setBounds(173, 35, 314, 43);
		contentPane.add(lblVirtualLuckySeven);
		
		btnAdmin = new JButton("Admin");
		btnAdmin.setBackground(Color.WHITE);
		btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdmin.setBounds(246, 138, 167, 30);
		contentPane.add(btnAdmin);
		
		btnUser = new JButton("User");
		btnUser.setBackground(Color.WHITE);
		btnUser.setForeground(new Color(0, 0, 0));
		btnUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUser.setBounds(246, 192, 167, 30);
		contentPane.add(btnUser);
		
		lblPoweredByNikola = new JLabel("Developed by Nikola Rogovi\u0107");
		lblPoweredByNikola.setForeground(Color.LIGHT_GRAY);
		lblPoweredByNikola.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoweredByNikola.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblPoweredByNikola.setBounds(366, 263, 258, 30);
		contentPane.add(lblPoweredByNikola);
		
		
		btnAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				adminMain = new AdminMain();
				adminMain.setVisible(true);
				
				frame.dispose();
				
			}
			
		});
		
		btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				userMain = new UserMain();
				userMain.setVisible(true);
				frame.dispose();
				
			}
		});
		
	}
	
	static Connection getConnection() {
		return connection;
	}
}
