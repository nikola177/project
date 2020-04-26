package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class AdminMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblAdminSection;
	private JTextField adminTextField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblAdminUsername;
	private JLabel lblAdminPassword;
	
	
	AdminGoodLogin adminGoodLogin;
	
	
	private String query;

	public AdminMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Lucky Seven");
		setBounds(100, 100, 650, 359);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(128, 0, 128));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAdminSection = new JLabel("Admin Section");
		lblAdminSection.setForeground(Color.WHITE);
		lblAdminSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminSection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdminSection.setBounds(10, 30, 614, 43);
		contentPane.add(lblAdminSection);
		
		lblAdminUsername = new JLabel("Username");
		lblAdminUsername.setForeground(Color.WHITE);
		lblAdminUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAdminUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdminUsername.setBounds(192, 116, 73, 30);
		contentPane.add(lblAdminUsername);
		
		adminTextField = new JTextField();
		adminTextField.setForeground(Color.DARK_GRAY);
		adminTextField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		adminTextField.setBounds(276, 117, 130, 30);
		contentPane.add(adminTextField);
		adminTextField.setColumns(15);
		
		lblAdminPassword = new JLabel("Password");
		lblAdminPassword.setForeground(Color.WHITE);
		lblAdminPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdminPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAdminPassword.setBounds(175, 170, 90, 30);
		contentPane.add(lblAdminPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setColumns(15);
		passwordField.setBounds(276, 171, 130, 30);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Sign in");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(276, 225, 107, 30);
		contentPane.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				query = "SELECT * FROM Admins WHERE username=? AND password=?";
				
				try {
					PreparedStatement pst = Login.getConnection().prepareStatement(query);
					pst.setString(1, adminTextField.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					int i = 0;
					
					while(rs.next()) {
						i++;
					}
					if(i==1) {
					
						adminGoodLogin = new AdminGoodLogin();
						
						adminGoodLogin.setVisible(true);
						adminGoodLogin.setUsernameToLabel(adminTextField.getText());
						dispose();
					}
					else {
			
						JOptionPane.showMessageDialog(null, "Incorect Username or Password!");
						
					}
					
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
	}
}
