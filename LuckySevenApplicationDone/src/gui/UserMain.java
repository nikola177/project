package gui;


import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblUserSection;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JLabel lblPassword;
	private JLabel lblUsername;
	private JButton btnSignin;
	
	UserGoodLogin userGoodLogin;
	
	public UserMain() {
		setTitle("Lucky Seven");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 650, 359);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(128, 0, 128));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		lblUserSection = new JLabel("Live Your passion with us!");
		lblUserSection.setForeground(Color.WHITE);
		lblUserSection.setBounds(10, 11, 614, 45);
		lblUserSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserSection.setFont(new Font("Tahoma", Font.ITALIC, 16));
		contentPane.add(lblUserSection);
		
		btnSignin = new JButton("Sign in");
		btnSignin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSignin.setBounds(276, 225, 107, 30);
		contentPane.add(btnSignin);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(276, 117, 130, 30);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(192, 116, 73, 30);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(175, 170, 90, 30);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(276, 171, 130, 30);
		contentPane.add(passwordField);
		
		btnSignin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String query = "SELECT * FROM Users WHERE username=? AND password=?";
				
				try {
					PreparedStatement pst = Login.getConnection().prepareStatement(query);
					pst.setString(1, textFieldUsername.getText());
					pst.setString(2, passwordField.getText());
					
					PreparedStatement pst1 = Login.getConnection().prepareStatement(query);
					pst1.setString(1, textFieldUsername.getText());
					pst1.setString(2, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					ResultSet rs1 = pst1.executeQuery();
					int i = 0;
					int id=0;
					
					while(rs.next()) {
						i++;
					}
					if(i==1) {
			
						userGoodLogin = new UserGoodLogin(textFieldUsername.getText());
						userGoodLogin.setVisible(true);
						
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
