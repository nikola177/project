package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class AdminGoodLogin extends JFrame {

	private JPanel contentPane;
	
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldName;
	private JTextField textFieldLastname;
	private JTextField textFieldPhone;
	private JTextField textFieldCity;
	private JTextField textFieldEmail;
	
	private JLabel lblEmail;
	private JLabel lblCity;
	private JLabel lblLastname;
	private JLabel lblPassword;
	private JLabel lblUsername;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblSex;
	private JLabel lblUsernameIncrease;
	private JLabel lblIncreaseYourMoney;
	private JLabel labelAdminUsername;
	private JLabel lblPayingToCustomers;
	
	private JButton btnCreateUser;
	private JButton btnMostInactive;
	private JButton btnMostActive;
	private JButton btnAllUsers;
	private JButton btnOkForIncreasingMoney;
	private JButton btnForPayment;
	
	private JPanel panelForIncreasingMoneyCredit;
	private JPanel panelForPayingMoneyToCustomers;
	
	private JScrollPane scrollPane;
	
	private JCheckBox chckbxMale, chckbxFemale;
	private String queryForCreatingUser, queryForShowingAllUsers;
	private JTable table;
	private JLabel lblAmount;
	private JTextField textFieldUsernameForIncrease;
	private JTextField textFieldAmountForIncrease;
	private JLabel lblUsername_1;
	private JLabel lblPayment;
	private JTextField textFieldPayUsername;
	private JTextField textFieldPayment;
	private JButton btnSignOut;
	
	private String adminUsername;
	
	public AdminGoodLogin() {
		setTitle("Admin Section");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 482);
		setVisible(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCreateUser = new JButton("Create User");
		btnCreateUser.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnCreateUser.setBounds(31, 277, 123, 34);
		contentPane.add(btnCreateUser);
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblUsername.setBounds(10, 23, 63, 23);
		contentPane.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textFieldUsername.setBounds(83, 24, 86, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(15);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblPassword.setBounds(10, 57, 63, 14);
		contentPane.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textFieldPassword.setBounds(83, 55, 86, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(15);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblName.setBounds(10, 89, 63, 14);
		contentPane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(83, 86, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(15);
		
		lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLastname.setBounds(10, 114, 63, 14);
		contentPane.add(lblLastname);
		
		textFieldLastname = new JTextField();
		textFieldLastname.setBounds(83, 111, 86, 20);
		contentPane.add(textFieldLastname);
		textFieldLastname.setColumns(15);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblPhone.setBounds(10, 139, 63, 14);
		contentPane.add(lblPhone);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textFieldPhone.setBounds(83, 136, 86, 20);
		contentPane.add(textFieldPhone);
		textFieldPhone.setColumns(15);
		
		chckbxFemale = new JCheckBox("female");
		chckbxFemale.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		chckbxFemale.setBounds(57, 160, 63, 23);
		contentPane.add(chckbxFemale);
		
		lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblSex.setBounds(10, 164, 63, 14);
		contentPane.add(lblSex);
		
		chckbxMale = new JCheckBox("male");
		chckbxMale.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		chckbxMale.setBounds(117, 160, 66, 23);
		contentPane.add(chckbxMale);
		
		lblCity = new JLabel("City");
		lblCity.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCity.setBounds(10, 190, 63, 14);
		contentPane.add(lblCity);
		
		textFieldCity = new JTextField();
		textFieldCity.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textFieldCity.setBounds(83, 187, 86, 20);
		contentPane.add(textFieldCity);
		textFieldCity.setColumns(15);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblEmail.setBounds(10, 215, 46, 14);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Simplified Arabic", Font.BOLD | Font.ITALIC, 13));
		textFieldEmail.setBounds(82, 215, 86, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(30);
		
		btnAllUsers = new JButton("All users");
		btnAllUsers.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnAllUsers.setBounds(217, 44, 135, 35);
		contentPane.add(btnAllUsers);
		
		btnMostActive = new JButton("Most active");
		btnMostActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int numberOfUsers = 1000;
				int numberOfMostActiveUsers = 1000/5 + 1;
				 String query = "SELECT count(*) FROM Users";
				 String queryForShowUsersOnCondition;
				
				try {
					PreparedStatement pst = Login.getConnection().prepareStatement(query);
					
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()) {
						numberOfUsers = Integer.parseInt(rs.getString(1));
					}
	
						numberOfMostActiveUsers = numberOfUsers/5 + 1;
					
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
				String numberOfMostActiveUsersAsString = Integer.toString(numberOfMostActiveUsers);
				
				queryForShowUsersOnCondition = "SELECT TOP " + numberOfMostActiveUsersAsString + " username,name,lastname,phone,money,numberOfBet,rowCounter FROM Users ORDER BY numberOfBet DESC";
				
				showUsersOnCondition(queryForShowUsersOnCondition);
				
			}
		});
		btnMostActive.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnMostActive.setBounds(473, 44, 135, 35);
		contentPane.add(btnMostActive);
		
		btnMostInactive = new JButton("Most inactive");
		btnMostInactive.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			int numberOfUsers = 1000;
				int numberOfMostInactiveUsers = 1000/5 + 1;
				 String query = "SELECT count(*) FROM Users";
				 String queryForShowUsersOnCondition;
				
				try {
					PreparedStatement pst = Login.getConnection().prepareStatement(query);
					
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()) {
						numberOfUsers = Integer.parseInt(rs.getString(1));
					}
	
						numberOfMostInactiveUsers = numberOfUsers/5 + 1;
					
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
				String numberOfMostActiveUsersAsString = Integer.toString(numberOfMostInactiveUsers);
				
				queryForShowUsersOnCondition = "SELECT TOP " + numberOfMostActiveUsersAsString + " username,name,lastname,phone,money,numberOfBet,rowCounter FROM Users ORDER BY numberOfBet ASC";
				
				showUsersOnCondition(queryForShowUsersOnCondition);
						
			}
		});
		btnMostInactive.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnMostInactive.setBounds(721, 44, 135, 35);
		contentPane.add(btnMostInactive);

		table = new JTable();

		table.setEnabled(false);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(217, 95, 640, 161);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		contentPane.add(scrollPane);

		panelForIncreasingMoneyCredit = new JPanel();
		panelForIncreasingMoneyCredit.setBackground(new Color(175, 238, 238));
		panelForIncreasingMoneyCredit.setBorder(new LineBorder(new Color(128, 0, 128), 2, true));
		panelForIncreasingMoneyCredit.setBounds(252, 264, 267, 167);
		contentPane.add(panelForIncreasingMoneyCredit);
		panelForIncreasingMoneyCredit.setLayout(null);
		
		lblUsernameIncrease = new JLabel("Username");
		lblUsernameIncrease.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsernameIncrease.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsernameIncrease.setBounds(20, 45, 72, 21);
		panelForIncreasingMoneyCredit.add(lblUsernameIncrease);
		
		lblIncreaseYourMoney = new JLabel("Increase user money credit");
		lblIncreaseYourMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncreaseYourMoney.setBounds(10, 13, 230, 21);
		lblIncreaseYourMoney.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelForIncreasingMoneyCredit.add(lblIncreaseYourMoney);
		
		lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.LEFT);
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAmount.setBounds(20, 90, 72, 21);
		panelForIncreasingMoneyCredit.add(lblAmount);
		
		textFieldUsernameForIncrease = new JTextField();
		textFieldUsernameForIncrease.setBounds(91, 45, 102, 21);
		panelForIncreasingMoneyCredit.add(textFieldUsernameForIncrease);
		textFieldUsernameForIncrease.setColumns(10);
		
		textFieldAmountForIncrease = new JTextField();
		textFieldAmountForIncrease.setBounds(91, 88, 102, 21);
		panelForIncreasingMoneyCredit.add(textFieldAmountForIncrease);
		textFieldAmountForIncrease.setColumns(10);
		
		btnOkForIncreasingMoney = new JButton("Ok");
		btnOkForIncreasingMoney.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnOkForIncreasingMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				double amount=0;
				
				String user = textFieldUsernameForIncrease.getText();
				
				if(user.length()==0) {
					JOptionPane.showMessageDialog(null, "You didnt enter the username");
					textFieldAmountForIncrease.setText("");
					return;
				}
				
				if(validationForAmount()==1){

				amount = round(Double.parseDouble(textFieldAmountForIncrease.getText()), 2);
				
				textFieldAmountForIncrease.setText(String.valueOf(amount));
				
				double oldMoney = 0;

				int indicator = 1;
				try {
					PreparedStatement pstmt = Login.getConnection().prepareStatement("SELECT money FROM Users WHERE username=" + "'" + user+"'");
					PreparedStatement pstmt1 = Login.getConnection().prepareStatement("SELECT money FROM Users WHERE username=" + "'" + user+"'");
					
					ResultSet rs1 = pstmt1.executeQuery();
					ResultSet rs = pstmt.executeQuery();
					int i = 0;
					
					while(rs1.next()) {
						i++;
					}

					if(i==1) {
						while(rs.next()) {
							oldMoney = round(Double.parseDouble(rs.getString("money")), 2);
						}
					}
					else {
						
						indicator = 0;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					amount = round(amount + oldMoney, 2);
				
				if(indicator==1) try {
					PreparedStatement stmt = Login.getConnection().prepareStatement("update Users set money=" + "'" + amount + "'" +  " where username="+ "'" + user +"'");
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Your action has been successfully finished.");
					textFieldUsernameForIncrease.setText("");
					textFieldAmountForIncrease.setText("");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no user " + textFieldUsernameForIncrease.getText());
					textFieldUsernameForIncrease.setText("");
					textFieldAmountForIncrease.setText("");
				}
				
				}
				
			}
		});
		btnOkForIncreasingMoney.setBounds(133, 119, 60, 23);
		panelForIncreasingMoneyCredit.add(btnOkForIncreasingMoney);
		
		panelForPayingMoneyToCustomers = new JPanel();
		panelForPayingMoneyToCustomers.setBackground(new Color(175, 238, 238));
		panelForPayingMoneyToCustomers.setBorder(new LineBorder(new Color(128, 0, 128), 2, true));
		panelForPayingMoneyToCustomers.setBounds(549, 264, 267, 167);
		panelForPayingMoneyToCustomers.setLayout(null);
		contentPane.add(panelForPayingMoneyToCustomers);
		
		lblPayingToCustomers = new JLabel("Pay money to customer");
		lblPayingToCustomers.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPayingToCustomers.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayingToCustomers.setBounds(10, 13, 230, 21);
		panelForPayingMoneyToCustomers.add(lblPayingToCustomers);
		
		lblUsername_1 = new JLabel("Username");
		lblUsername_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsername_1.setBounds(20, 45, 66, 21);
		panelForPayingMoneyToCustomers.add(lblUsername_1);
		
		lblPayment = new JLabel("Payment");
		lblPayment.setHorizontalAlignment(SwingConstants.LEFT);
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPayment.setBounds(20, 90, 66, 21);
		panelForPayingMoneyToCustomers.add(lblPayment);
		
		textFieldPayUsername = new JTextField();
		textFieldPayUsername.setBounds(91, 45, 102, 21);
		panelForPayingMoneyToCustomers.add(textFieldPayUsername);
		textFieldPayUsername.setColumns(10);
		
		textFieldPayment = new JTextField();
		textFieldPayment.setBounds(91, 88, 102, 21);
		panelForPayingMoneyToCustomers.add(textFieldPayment);
		textFieldPayment.setColumns(10);
		
		btnForPayment = new JButton("Ok");
		
		btnForPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String user = textFieldPayUsername.getText();
				
				if(user.length()==0) {
					JOptionPane.showMessageDialog(null, "You didnt enter the username");
					textFieldPayment.setText("");
					return;
				}
				
				if(validationForPayment()==0) return;
				
				textFieldPayment.setText(String.valueOf(round(Double.valueOf(textFieldPayment.getText()),2)));
				
				Double payment = round(Double.parseDouble(textFieldPayment.getText()),2);
				
				
				double oldMoney = 0;
				double newMoney = 0;
				int indicator = 1;
				try {
					PreparedStatement pstmt = Login.getConnection().prepareStatement("SELECT money FROM Users WHERE username=" + "'" + user+"'");
					PreparedStatement pstmt1 = Login.getConnection().prepareStatement("SELECT money FROM Users WHERE username=" + "'" + user+"'");
					
					ResultSet rs1 = pstmt1.executeQuery();
					ResultSet rs = pstmt.executeQuery();
					int i = 0;
					
					while(rs1.next()) {
						i++;
					}

					if(i==1) {
						while(rs.next()) {
							oldMoney = Double.parseDouble(rs.getString("money"));
							oldMoney = round(oldMoney, 2);
						}
					}
					else {
						
						indicator = 0;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(payment>oldMoney && indicator==1) {
					JOptionPane.showMessageDialog(null, "There is no enough money credit for pay.");
					return;
				}
				newMoney = round(oldMoney - payment, 2);
				
				if(indicator==1) try {
					PreparedStatement stmt = Login.getConnection().prepareStatement("update Users set money=" + "'" + newMoney + "'" +  " where username="+ "'" + user +"'");
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Your action has been successfully finished.");
					textFieldPayUsername.setText("");
					textFieldPayment.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no user " + textFieldPayUsername.getText());
					textFieldPayUsername.setText("");
					textFieldPayment.setText("");
				}
				
			}
		});
		
		btnForPayment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnForPayment.setBounds(133, 119, 60, 23);
		panelForPayingMoneyToCustomers.add(btnForPayment);
		
		btnSignOut = new JButton("Sign out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int k = JOptionPane.showConfirmDialog(null, "Are you sure that you want sign out?", "Admin Sign out", JOptionPane.YES_NO_OPTION);// 0 , 1, -1. 0 je Yes
				if(k==0) {
					
					AdminMain adminMain = new AdminMain();
					adminMain.setVisible(true);
					dispose();
					
				}
		
			}
		});
		btnSignOut.setBounds(800, 11, 110, 23);
		contentPane.add(btnSignOut);
		
		labelAdminUsername = new JLabel();
		labelAdminUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		labelAdminUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelAdminUsername.setBounds(642, 7, 148, 29);
		contentPane.add(labelAdminUsername);
		

		
		btnCreateUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(validationForCreatinguser()==0) return;
				//
				int alreadyExist = 0;
				
				String query = "SELECT * FROM Users WHERE username=" + "'" + textFieldUsername.getText() + "'" + "";
				
				try {
					PreparedStatement pst = Login.getConnection().prepareStatement(query);
									
					ResultSet rs = pst.executeQuery();
					int i = 0;
					
					while(rs.next()) {
						i++;
					}
					if(i==1) {
						
						alreadyExist = 1;
					}
	
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				//
			
					queryForCreatingUser = "INSERT INTO Users (username,password,name,lastname,phone,sex,city,email,money,numberOfBet) VALUES (?,?,?,?,?,?,?,?,0,0);";
					
				if(alreadyExist == 0)
					try {
						
					PreparedStatement statement = Login.getConnection().prepareStatement(queryForCreatingUser);
					statement.setString(1, textFieldUsername.getText());
					statement.setString(2, textFieldPassword.getText());
					statement.setString(3, textFieldName.getText());
					statement.setString(4, textFieldLastname.getText());
					statement.setString(5, textFieldPhone.getText());
					
					String sex;
					if(chckbxFemale.isSelected()) sex = "female";
					else sex = "male";
					
					statement.setString(6, sex);
					statement.setString(7, textFieldCity.getText());
					statement.setString(8, textFieldEmail.getText());
					
					statement.executeUpdate();
					statement.close();
	
					JOptionPane.showMessageDialog(null, "Creating user has been finished successfully.");
					
					resetForm();
					}
				catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Creating user has been failed.");
				}
				else JOptionPane.showMessageDialog(null, "That user already  exist. Change username.");
				
			}
			
		});
		
		btnAllUsers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
	
				queryForShowingAllUsers = "SELECT username,name,lastname,phone,money,numberOfBet,rowCounter FROM Users ORDER BY rowCounter DESC";
				showUsersOnCondition(queryForShowingAllUsers);
				}
		
			});

	}
	
	int validationForAmount() {
		String s = textFieldAmountForIncrease.getText();
		
		if(s.length()==0) {
			
			JOptionPane.showMessageDialog(null, "Enter the amount.");
			return 0;
		}
		
		if(s.charAt(0)=='.') {
			
			JOptionPane.showMessageDialog(null, "Point cant be on the first place.");
			return 0;
		}
		
		String arrayOfAllowedCharacters = "0123456789.";
		
		int numberOfPoint = 0;
		
		for(int i=0; i<s.length(); i++) {
			
			if(s.charAt(i)=='.') numberOfPoint++;
			
			if(arrayOfAllowedCharacters.indexOf(s.charAt(i))==-1)
				{
						
						JOptionPane.showMessageDialog(null, "Allowed characters are digits and point.");
						return 0;
						
				}
		}
		if(numberOfPoint>1) {
			
			JOptionPane.showMessageDialog(null, "You entered more than 1 point.");
			return 0;
		}

		 if(Double.parseDouble(s)<1.0) {
			 JOptionPane.showMessageDialog(null, "Enter the amount bigger or equal to 1 Euro.");
			
			return 0;
		}
		else {
			if(Double.parseDouble(s)>10000) {
				
				JOptionPane.showMessageDialog(null, "Maximum amount is 10000");
				return 0;
			}
			
		}
		return 1;
	}
	
	public int validationForCreatinguser() {
		String word; 
		
		word = textFieldUsername.getText();
		
		if(word.length()==0) {
			JOptionPane.showMessageDialog(null, "You didnt enter the username.");
			return 0;
		}
		
		if(word.length()<4 || word.length()>10){
			JOptionPane.showMessageDialog(null, "Username must have between 4 and 10 characters.");
			return 0;
		}
		
		word = textFieldPassword.getText();
		
		if(word.length()==0) {
			JOptionPane.showMessageDialog(null, "You didnt enter the password.");
			return 0;
		}
		
		if(word.length()<4 || word.length()>10) {
			JOptionPane.showMessageDialog(null, "Password must have between 4 and 10 characters.");
			return 0;
		}
		
		word = textFieldName.getText();
		
		if(word.length()==0) {
			JOptionPane.showMessageDialog(null, "You didnt enter the name.");
			return 0;
		}
		
		if(word.length()<2 || word.length()>10) {
			JOptionPane.showMessageDialog(null, "Name must have between 2 and 10 characters.");
			return 0;
		}
		
		word = textFieldLastname.getText();
		
		if(word.length()==0) {
			JOptionPane.showMessageDialog(null, "You didnt enter the lastname.");
			return 0;
		}
		
		if(word.length()<2 || word.length()>10) {
			JOptionPane.showMessageDialog(null, "Lastname must have between 2 and 10 characters.");
			return 0;
		}
		
		word = textFieldPhone.getText();
		
		if(word.length()==0) {
			JOptionPane.showMessageDialog(null, "You didnt enter the phone number.");
			return 0;
		}
		
		if(word.length()<6 || word.length()>14) {
			JOptionPane.showMessageDialog(null, "Phone number must have between 6 and 14 characters.");
			return 0;
		}
		
		String arrayOfAllowedCharacters = "+0123456789";
		
		
		for(int i=0; i<word.length(); i++) {
			
			if(arrayOfAllowedCharacters.indexOf(word.charAt(i))==-1)
				{
				JOptionPane.showMessageDialog(null, "Phone number can have digits and + .");
						return 0;
						
				}
		}
		
		if((chckbxFemale.isSelected() && chckbxMale.isSelected()) || !(chckbxFemale.isSelected() || chckbxMale.isSelected())) {
			JOptionPane.showMessageDialog(null, "You must select one checkbox.");
			return 0;
		}
		
		word = textFieldCity.getText();
		
		if(word.length()==0) {
			JOptionPane.showMessageDialog(null, "You didnt enter a city.");
			return 0;
		}
		
		if(word.length()<2 || word.length()>14) {
			JOptionPane.showMessageDialog(null, "City must have between 2 and 14 characters.");
			return 0;
		}
		
		word = textFieldEmail.getText();
		if(word.length()==0) {
			JOptionPane.showMessageDialog(null, "You didnt enter the email.");
			return 0;
		}
		
		if(word.length()<10 || word.length()>26) {
			JOptionPane.showMessageDialog(null, "Email must have between 10 and 26 characters.");
			return 0;
		}

		return 1;
	}
	public void resetForm() {
		textFieldUsername.setText("");
		textFieldPassword.setText("");
		textFieldName.setText("");
		textFieldLastname.setText("");
		textFieldPhone.setText("");
		chckbxFemale.setSelected(false);
		chckbxMale.setSelected(false);
		textFieldCity.setText("");
		textFieldEmail.setText("");
	}

	public void showUsersOnCondition(String query) {
		
			try {
				PreparedStatement pst = Login.getConnection().prepareStatement(query);
				
				ResultSet rs = pst.executeQuery();
			
				DefaultTableModel tableModel = new DefaultTableModel();

		        //Retrieve meta data from ResultSet
		        ResultSetMetaData metaData = rs.getMetaData();

		        //Get number of columns from meta data
		        int columnCount = metaData.getColumnCount();
		        

		        //Get all column names from meta data and add columns to table model
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
		        		
		        		tableModel.addColumn(metaData.getColumnLabel(columnIndex));
		     
		            }

		        //Create array of Objects with size of column count from meta data
		        Object[] row = new Object[columnCount];
		

		        //Scroll through result set
		        while (rs.next()){
		        	
		            //Get object from column with specific index of result set to array of objects
		            for (int i = 0; i < columnCount; i++){
		            	
		                row[i] = rs.getObject(i+1);
		            }
		            //Now add row to table model with that array of objects as an argument
		            tableModel.addRow(row);
		        }

		        //Now add that table model to your table
		        table.setModel(tableModel);
			}catch(Exception e) {
					e.printStackTrace();
				}
			}
	
	public int validationForPayment() {
	String s = textFieldPayment.getText();
		
		if(s.length()==0) {
			
			JOptionPane.showMessageDialog(null, "Enter the payment amount.");
			return 0;
		}
		
		if(s.charAt(0)=='.') {
			
			JOptionPane.showMessageDialog(null, "Point cant be on the first place.");
			return 0;
		}
		
		String arrayOfAllowedCharacters = "0123456789.";
		
		int numberOfPoint = 0;
		
		for(int i=0; i<s.length(); i++) {
			
			if(s.charAt(i)=='.') numberOfPoint++;
			
			if(arrayOfAllowedCharacters.indexOf(s.charAt(i))==-1)
				{
						
						JOptionPane.showMessageDialog(null, "Allowed characters are digits and point.");
						return 0;
						
				}
		}
		if(numberOfPoint>1) {
			
			JOptionPane.showMessageDialog(null, "You entered more than 1 point.");
			return 0;
		}

		 if(Double.parseDouble(s)<0) {
			 JOptionPane.showMessageDialog(null, "Enter positive payment amount.");
			
			return 0;
		}
	
		return 1;
	}
	public double round(double value, int places) {
		BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	
	public void setUsernameToLabel(String name) {
		labelAdminUsername.setText(name);
	}
	}


