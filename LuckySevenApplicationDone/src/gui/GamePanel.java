package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class GamePanel extends JPanel {


	CardLayout cardLayout;
	
	JButton btnLucky7;
	JButton btnOddOrEven;
	JButton btnSumFirst5;
	
	BackgroundGamePanel bgp;
	JPanel mainPanel;
	
	JLabel lblEnterYourStake;
	JTextField textFieldStake;
	JLabel lblUserGain;
	//JLabel lblInfAboutResult;
	JLabel lblPotentionalBonus;
	JButton btnBet;
	JButton btnReset;
	
	JLabel pointerToLabelMoney;
	static double moneyForCurrentUser;
	JButton btnExitGame;
	
	String arrayOfAllowedCharacters;
	String username;
	
	JLabel lblGreetingCard;
	JLabel lblComforting;
	
	
	
	
		public GamePanel(JButton btnLucky7, JButton btnOddOrEven,  JButton btnSumFirst5, JPanel mainPanel,
				BackgroundGamePanel bgp, JLabel pointerToLabelMoney, double moneyForCurrentUser,String username) {
		
		this.btnLucky7 = btnLucky7;
		this.btnOddOrEven = btnOddOrEven;
		this.btnSumFirst5 = btnSumFirst5;
		this.mainPanel = mainPanel;
		this.bgp = bgp;
		this.pointerToLabelMoney = pointerToLabelMoney;
		setMoneyForCurrentUser(moneyForCurrentUser);
		this.username = username;
		this.setBounds(39, 347, 117, 23);//
		btnReset = new JButton("RESET");
		btnReset.setBounds(20, 350, 114, 23);
		add(btnReset);
		
	
		lblGreetingCard = new JLabel("");
		lblGreetingCard.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreetingCard.setForeground(new Color(128, 0, 128));
		lblGreetingCard.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblGreetingCard.setBounds(2, 200, 445, 100);
		add(lblGreetingCard);
		
		lblComforting = new JLabel("");
		lblComforting.setHorizontalAlignment(SwingConstants.CENTER);
		lblComforting.setForeground(new Color(128, 0, 128));
		lblComforting.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComforting.setBounds(20, 229, 441, 31);
		add(lblComforting);
		//----------------
		
		arrayOfAllowedCharacters = "0123456789.-";
		setLayout(null);
		setBackground(new Color(135, 206, 235));
		lblEnterYourStake = new JLabel("YOUR STAKE :");
		lblEnterYourStake.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterYourStake.setForeground(new Color(128, 0, 128));
		lblEnterYourStake.setBounds(20, 316, 87, 23);
		add(lblEnterYourStake);
		
		textFieldStake = new JTextField();
		textFieldStake.setBounds(104, 317, 62, 20);
		add(textFieldStake);
		textFieldStake.setColumns(10);
		
		lblUserGain = new JLabel("YOUR GAIN : ");
		lblUserGain.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserGain.setForeground(new Color(128, 0, 128));
		lblUserGain.setBounds(190, 316, 187, 18);
		add(lblUserGain);
		
		lblPotentionalBonus = new JLabel();
		lblPotentionalBonus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPotentionalBonus.setForeground(new Color(128, 0, 128));
		lblPotentionalBonus.setBounds(370, 316, 147, 18);
		lblPotentionalBonus.setVisible(false);
		add(lblPotentionalBonus);
		
		btnBet = new JButton("BET");
		btnBet.setForeground(Color.WHITE);
		btnBet.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBet.setBounds(166, 350, 121, 23);
		btnBet.setBackground(new Color(128, 0, 128));
		add(btnBet);
		
		btnExitGame = new JButton("EXIT");
	
		btnExitGame.setBounds(319, 350, 114, 23);
		add(btnExitGame);
		
		exit();
		
	}
	public void exit() {
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cardLayout.show(mainPanel, "bgp");
				btnLucky7.setVisible(true);
				btnOddOrEven.setVisible(true);
				btnSumFirst5.setVisible(true);
				reset();
			}
		});
	}
	
	public void reset() {
		btnBet.setVisible(true);
		lblGreetingCard.setText("");
		lblComforting.setText("");
		lblUserGain.setText("YOUR GAIN : ");
		
		textFieldStake.setText("");
		textFieldStake.setEditable(true);
		
	}
	
	
	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
	
	public static double getMoneyForCurrentUser() {
		return moneyForCurrentUser;
	}
	
	public static void setMoneyForCurrentUser(double mFCU) {
		moneyForCurrentUser = mFCU;
	}
	
	public int validationForStake() {
		
		
		String ccc = textFieldStake.getText();
		
		if(ccc.equals("")) {
			
			JOptionPane.showMessageDialog(null, "Enter the stake.");
			return 0;
		}
		
		for(int i=0; i<ccc.length(); i++) {
			
			if(arrayOfAllowedCharacters.indexOf(ccc.charAt(i))==-1)
				{
						
						JOptionPane.showMessageDialog(null, "Allowed characters are digits and point.");
						return 0;
						
				}
		}
		int numberOfPoint = 0;
		
		for(int i=0; i<ccc.length(); i++) {
			
			if(ccc.charAt(i)=='.') numberOfPoint++;
			
		}
		if(numberOfPoint>1) {
			JOptionPane.showMessageDialog(null, "You entered more than one point!");
			return 0;
		}

		
		if(Double.parseDouble(textFieldStake.getText())<1.0) {
			
			JOptionPane.showMessageDialog(null, "Minimum stake is 1 Euro.");
			return 0;
		}
	
		if(Double.parseDouble(textFieldStake.getText())>getMoneyForCurrentUser()) {
				
				JOptionPane.showMessageDialog(null, "You dont have enough money.");
				return 0;
			}
			
		
		
		double x = Double.parseDouble(textFieldStake.getText());
		x = round(x, 2);

		textFieldStake.setText(String.valueOf(x));
		return 1;
	}
	
	
	
	public void updateMoneyDueBet() {
		
		double temporary = round(Double.valueOf(textFieldStake.getText()),2);
		
		double newMoney = getMoneyForCurrentUser()-temporary;
		
		newMoney = round(newMoney, 2);
		
		try {
			PreparedStatement stmt = Login.getConnection().prepareStatement("update Users set money=" + "'" + String.valueOf(newMoney) + "'" +  " where username="+ "'" + getUsername()+"'");
			stmt.executeUpdate();
			
			setMoneyForCurrentUserNoDB(newMoney);
			
			JOptionPane.showMessageDialog(null, "Your bet successfully started.");
			pointerToLabelMoney.setText(String.valueOf(newMoney));
			UserGoodLogin.setMoneyForCurrentUser(newMoney);
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public String getUsername() {
		return username;
	}
	public void creatingRemForLabAndDataBase(JLabel labelForMoney, int x, String username, String moneyAfterWin, int seconds) {
		
		  ReminderForLabels rflOddEven = new ReminderForLabels(seconds);
		  rflOddEven.setLabel(labelForMoney);
		  rflOddEven.setX(x);
		  rflOddEven.setUsername(username);
		  rflOddEven.setResultString(moneyAfterWin);
		  
	}
	
	public static void setMoneyForCurrentUserNoDB(double newMoney) { 
		  GameOddOrEven.setMoneyForCurrentUser(newMoney);
		  GameLucky7.setMoneyForCurrentUser(newMoney);
		  GameSumFirst5.setMoneyForCurrentUser(newMoney);
	}
	
	public void behaviorWhilePullingNumbers(int time) {
		 ReminderForButtons exit = new ReminderForButtons(time);
		 exit.setButton(btnExitGame);
		 exit.setX(3);
		 
		 ReminderForButtons reset = new ReminderForButtons(time);
		 reset.setButton(btnReset);
		 reset.setX(3);
		 
		 btnBet.setVisible(false);
		 btnReset.setVisible(false);
		 btnExitGame.setVisible(false);
	}
	
	public double round(double value, int places) {
		BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public void increaseNumberOfBet() {
		
		String newNumberOfBet = "0";
		
		 String query = "SELECT numberOfBet FROM Users WHERE username=?";
		
		try {
			PreparedStatement pst = Login.getConnection().prepareStatement(query);
			pst.setString(1, getUsername());
			
			
			ResultSet rs = pst.executeQuery();
		
			
			while(rs.next()) {
				newNumberOfBet = rs.getString(1);
			}
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		int newNumberOfBetAsInt = Integer.parseInt(newNumberOfBet);
		newNumberOfBetAsInt++;
		
		try {
			PreparedStatement stmt = Login.getConnection().prepareStatement("update Users set numberOfBet=" + "'" + String.valueOf(newNumberOfBetAsInt) + "'" +  " where username="+ "'" + getUsername()+"'");
			stmt.executeUpdate();
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void hideAndShowSignOutButton(int seconds) {
		 UserGoodLogin.getbtnSignOut().setEnabled(false);
		 ReminderForButtons ReminderForButtons = new ReminderForButtons(seconds);
		 ReminderForButtons.setButton(UserGoodLogin.getbtnSignOut());
		 ReminderForButtons.setX(5);
	}

	
}
