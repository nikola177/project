package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Font;

public class GameOddOrEven extends GamePanel {

	JButton btnOdd;
	JButton btnEven;
	
	JLabel labelGameOddOrEven;
	JLabel lblYouBet;
	JLabel labelGameSumFirst5;
	JLabel lblOddEvenResult;
	JLabel lblDrawnNumber;
	
	int oddeven = 0;
	private JLabel labelPrediction;
	
	public GameOddOrEven(JButton btnLucky7, JButton btnOddOrEven,  JButton btnSumFirst5, 
			JPanel mainPanel, BackgroundGamePanel bgp, JLabel pointerToLabelMoney, double moneyForCurrentUser, String username) {

		super(btnLucky7, btnOddOrEven, btnSumFirst5, mainPanel, bgp, pointerToLabelMoney, moneyForCurrentUser, username);
		
		labelGameOddOrEven = new JLabel("Choose result");
		labelGameOddOrEven.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelGameOddOrEven.setHorizontalAlignment(SwingConstants.CENTER);
		labelGameOddOrEven.setBounds(148, 11, 133, 26);
		add(labelGameOddOrEven);
		
		lblYouBet = new JLabel("You say it will be : ");
		lblYouBet.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouBet.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblYouBet.setSize(153, 20);
		lblYouBet.setLocation(100, 87);
		add(lblYouBet);
		lblYouBet.setVisible(true);
		
		lblOddEvenResult = new JLabel();
		lblOddEvenResult.setFont(new Font("Tahoma", Font.BOLD, 13));
		Border border = BorderFactory.createLineBorder(new Color(153, 0, 102), 2);
		lblOddEvenResult.setBorder(border);
		
		lblOddEvenResult.setBorder(border);
		
		lblOddEvenResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblOddEvenResult.setSize(75, 36);
		lblOddEvenResult.setLocation(197, 129);
		add(lblOddEvenResult);
		lblOddEvenResult.setVisible(false);
	
		btnOdd = new JButton("Odd");
		btnOdd.setBounds(67, 42, 89, 23);
		btnOdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub NEPARAN
				oddeven = 1;
				lblYouBet.setVisible(true);
				labelPrediction.setText("Odd");
				labelPrediction.setVisible(true);
			
			}
			
		});
		add(btnOdd);
		
		btnEven = new JButton("Even");
		btnEven.setBounds(270, 42, 89, 23);
		btnEven.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub PARAN
				oddeven = 2;
				lblYouBet.setVisible(true);
				labelPrediction.setText("Even");
				labelPrediction.setVisible(true);
			}
			
		});
		add(btnEven);
		
		lblDrawnNumber = new JLabel("Drawn number :");
		lblDrawnNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDrawnNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrawnNumber.setBounds(67, 129, 120, 36);
		add(lblDrawnNumber);
		
		labelPrediction = new JLabel("");
		labelPrediction.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelPrediction.setBounds(253, 87, 57, 20);
		add(labelPrediction);
		
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			reset();

			}
			
		});
		
		btnBet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(oddeven==0) {
					JOptionPane.showMessageDialog(null, "Choose odd or even before starting bet.");
					return;
				}
				
				if(validationForStake()==0) return; 
				
				
				 btnOdd.setEnabled(false);
				 btnEven.setEnabled(false);
				 textFieldStake.setEditable(false);
				
				increaseNumberOfBet();
				
				updateMoneyDueBet();
				
				behaviorWhilePullingNumbers(3);
				
				hideAndShowSignOutButton(3);
				
				lblGreetingCard.setVisible(true);
				lblComforting.setVisible(true);
				Random rand = new Random(); 
				 int x = 1 + rand.nextInt(44);
				 int winOrLose;
				 
				 if(x%2==0 && oddeven==2) winOrLose = 1;
				 else if(x%2==1 && oddeven==1) winOrLose = 1;
				 else winOrLose = 0;
			
				  ReminderForLabels rfl = new ReminderForLabels(2);
				  rfl.setInd(1);
				  rfl.setWinOrLose(winOrLose);
				  rfl.setResultString(String.valueOf(x));
				  rfl.setLabel(lblOddEvenResult);
	
				  if(winOrLose==1) {
					  
					  double prolazno = 1.80 * round(Double.valueOf(textFieldStake.getText()), 2);
					  
					  prolazno = round(prolazno, 2);
						
					  double moneyAfterWin = round(prolazno + getMoneyForCurrentUser(), 2);
			
					  creatingRemForLabAndDataBase(pointerToLabelMoney, 10, getUsername(), Double.toString(moneyAfterWin), 3);
					  
					  ReminderForLabels rflOddEvenGain = new ReminderForLabels(3);
					  rflOddEvenGain.setLabel(lblUserGain);
					  rflOddEvenGain.setResultString(lblUserGain.getText()+ " " + String.valueOf(prolazno));
					  
					    
					  ReminderForLabels reminderForGreetingCard = new ReminderForLabels(3);
					  reminderForGreetingCard.setLabel(lblGreetingCard);
					  
					  reminderForGreetingCard.setResultString("YOU WON!");
					 
				  }else {
					  ReminderForLabels rflOddEvenGain = new ReminderForLabels(3);
					  rflOddEvenGain.setLabel(lblUserGain);
					  rflOddEvenGain.setResultString(lblUserGain.getText()+ " " + String.valueOf(0.0));
					  
					  ReminderForLabels reminderForGreetingCard = new ReminderForLabels(3);
					  reminderForGreetingCard.setLabel(lblComforting);
					  
					  reminderForGreetingCard.setResultString("You lost this time.");
				  }
	  
				 oddeven=0;
			}
			
		});
		
	}
	@Override
	public void reset() {
		
		 super.reset();
		 
		 labelPrediction.setText("");
		 lblOddEvenResult.setText("");
		 oddeven = 0;//
		 btnOdd.setEnabled(true);
		 btnEven.setEnabled(true);
	}
}
