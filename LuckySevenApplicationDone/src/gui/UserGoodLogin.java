package gui;

import java.util.Timer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import javax.swing.border.LineBorder;


public class UserGoodLogin extends JFrame {

	private JPanel contentPane;
	
	
	private String username;
	
	private JButton btnChangePassword;
	
	private JButton btnSumFirst5;
	private JButton btnOddOrEven;
	private JButton btnLucky7;
	static JButton btnSignOut;
	
	private JLabel lblWelcomeBack;
	private JLabel lblChooseGame;
	private JLabel lblCurrentMoney;
	private JLabel labelMoney;
	private JLabel lblTodayIsBonus;

	private JTextField textFieldNewPassword;

	private JPanel mainPanel;
	
	private BackgroundGamePanel bgp;

	private GameLucky7 gameLucky7;
	private GameSumFirst5 gameSumFirst5;
	private GameOddOrEven gameOddOrEven;

	CardLayout cardLayout;

	Timer timer;
	private static double moneyForCurrentUser;
	

	public UserGoodLogin(String username) {
		cardLayout = new CardLayout();
		setTitle("Lucky Seven");
		setVisible(true);
		
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 595);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblWelcomeBack = new JLabel(getUsername() + ", welcome back!");
		lblWelcomeBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblWelcomeBack.setBounds(188, 11, 418, 51);
		lblWelcomeBack.setForeground(new Color(128, 0, 128));
		contentPane.add(lblWelcomeBack);
		
		textFieldNewPassword = new JTextField();
		textFieldNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		textFieldNewPassword.setBackground(Color.LIGHT_GRAY);
		textFieldNewPassword.setBounds(37, 193, 136, 23);
		contentPane.add(textFieldNewPassword);
		textFieldNewPassword.setColumns(15);
		
		btnChangePassword = new JButton("Change password");
		
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textFieldNewPassword.getText().length()<4) {
					JOptionPane.showMessageDialog(null, "Your password must has at least 4 characters.");
					textFieldNewPassword.setText("");
					return;
				}
				
				 try {
					PreparedStatement stmt = Login.getConnection().prepareStatement("update Users set password=" + "'" + textFieldNewPassword.getText() + "'" +  " where username="+ "'" + getUsername()+"'");
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Your password has been successfully changed.");
					textFieldNewPassword.setText("");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		btnChangePassword.setBounds(22, 227, 164, 31);
		contentPane.add(btnChangePassword);
		
	    lblChooseGame = new JLabel("Choose game:");
		lblChooseGame.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChooseGame.setForeground(new Color(128, 0, 128));
		lblChooseGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseGame.setBounds(56, 325, 109, 32);
		contentPane.add(lblChooseGame);
		
	    btnLucky7 = new JButton("Lucky 7");
	    
		btnLucky7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				cardLayout.show(mainPanel, "gameLucky7");
				btnLucky7.setVisible(false);//
				btnOddOrEven.setVisible(false);
				btnSumFirst5.setVisible(false);
		
					}
				});
		
		btnLucky7.setBounds(10, 368, 190, 23);
		contentPane.add(btnLucky7);
		
		btnSumFirst5 = new JButton("Sum First 5");
		btnSumFirst5.setBounds(10, 402, 190, 23);
		contentPane.add(btnSumFirst5);
		
		btnSumFirst5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, "gameSumFirst5");
				btnLucky7.setVisible(false);
				btnOddOrEven.setVisible(false);
				btnSumFirst5.setVisible(false);
			
			}
			
		});
		
		btnOddOrEven = new JButton("Odd or Even");
		btnOddOrEven.setBounds(10, 436, 190, 23);
		contentPane.add(btnOddOrEven);
		
		btnOddOrEven.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cardLayout.show(mainPanel, "gameOddOrEven");
				btnLucky7.setVisible(false);
				btnOddOrEven.setVisible(false);
				btnSumFirst5.setVisible(false);
				
			}
			
		});
		
		mainPanel = new JPanel(cardLayout);
		
		mainPanel.setBorder(new LineBorder(new Color(128, 0, 128), 2, true));
		mainPanel.setBounds(234, 141, 469, 392);

		bgp = new BackgroundGamePanel();
		
		String query = "SELECT money FROM Users WHERE username=" + "'" + getUsername() + "'" + "";
		String s = "";
		
		try {
			PreparedStatement pstmt = Login.getConnection().prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
	
			while(rs.next()) {
				s = rs.getString("money");
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(s.length()>0) {
			labelMoney = new JLabel(s); 
			 setMoneyForCurrentUser (Double.parseDouble(s));
		}
		else {
			labelMoney = new JLabel("Error");
			moneyForCurrentUser = -1.0;
		}

		Border border = BorderFactory.createLineBorder(new Color(153, 0, 102), 2);
		
		labelMoney.setBorder(border);
		labelMoney.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelMoney.setForeground(new Color(128, 0, 128));
		labelMoney.setHorizontalAlignment(SwingConstants.CENTER);
		labelMoney.setBounds(138, 89, 109, 31);
		contentPane.add(labelMoney);
		
		gameLucky7 = new GameLucky7(btnLucky7, btnOddOrEven, btnSumFirst5, mainPanel, bgp, labelMoney, getMoneyForCurrentUser(), getUsername());
		gameLucky7.setCardLayout(getCardLayout());
		
		
		gameSumFirst5 = new GameSumFirst5(btnLucky7, btnOddOrEven, btnSumFirst5, mainPanel, bgp, labelMoney, getMoneyForCurrentUser(), getUsername());
		gameSumFirst5.setCardLayout(getCardLayout());
		
		
		gameOddOrEven = new GameOddOrEven(btnLucky7, btnOddOrEven, btnSumFirst5, mainPanel, bgp, labelMoney, getMoneyForCurrentUser(), getUsername());
		gameOddOrEven.setCardLayout(getCardLayout());
		
		mainPanel.add(bgp, "bgp");
		
		mainPanel.add(gameLucky7, "gameLucky7");
		
		mainPanel.add(gameSumFirst5, "gameSumFirst5");
		
	    mainPanel.add(gameOddOrEven, "gameOddOrEven");

		contentPane.add(mainPanel);
		
		lblCurrentMoney = new JLabel("Current money :");
		lblCurrentMoney.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentMoney.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCurrentMoney.setForeground(new Color(128, 0, 128));
		lblCurrentMoney.setBackground(Color.BLACK);
		lblCurrentMoney.setBounds(10, 89, 118, 31);
		contentPane.add(lblCurrentMoney);	
		
		lblTodayIsBonus = new JLabel("BONUS days for Lucky 7 are Monday and Friday!");
		lblTodayIsBonus.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTodayIsBonus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTodayIsBonus.setBounds(269, 73, 389, 23);
		lblTodayIsBonus.setForeground(new Color(128, 0, 128));
		
		contentPane.add(lblTodayIsBonus);
		
		btnSignOut = new JButton("Sign out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				int k = JOptionPane.showConfirmDialog(null, "Are you sure that you want sign out?", "Sign out", JOptionPane.YES_NO_OPTION);//values: 0 , 1, -1; 0 represents Yes
			    if(k==0) {
			    	gameLucky7.reset();
			    	gameSumFirst5.reset();
			    	gameOddOrEven.reset();
			    	
			    	UserMain userMain = new UserMain();
					userMain.setVisible(true);
					dispose();
			    }
		
			}
		});
		btnSignOut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSignOut.setBounds(616, 29, 101, 23);
		contentPane.add(btnSignOut);

}


	public String getUsername() {
		return username;
	}

	public static void setMoneyForCurrentUser(double x) {
		moneyForCurrentUser = x;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
	public static double getMoneyForCurrentUser() {
		return moneyForCurrentUser;
	}
	public static JButton getbtnSignOut() {
		return btnSignOut;
	}
}
