package gui;


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

public class GameSumFirst5 extends GamePanel {

	JLabel boxes[];
	JLabel lblNewLabel;
	JButton btnLessThan102p5;
	JButton btnMoreThan102p5;
	JLabel lblYouSayIt;
	JLabel labelPrediction;
	JLabel lblDrawnNumbers;
	JLabel lblResultSum;
	
	int array[];
	
	int indicator = 0;
	
	public GameSumFirst5(JButton btnLucky7, JButton btnOddOrEven,  JButton btnSumFirst5, 
			JPanel mainPanel, BackgroundGamePanel bgp, JLabel pointerToLabelMoney, double moneyForCurrentUser, String username) {
		
		super(btnLucky7, btnOddOrEven, btnSumFirst5, mainPanel, bgp, pointerToLabelMoney, moneyForCurrentUser, username);
		
		boxes = new JLabel[5];
		array = new int[5];
		
		lblNewLabel = new JLabel("Choose less or more");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(141, 11, 162, 23);
		add(lblNewLabel);
		
		btnLessThan102p5 = new JButton("< 102.5");

		btnLessThan102p5.setBounds(63, 49, 89, 23);
		add(btnLessThan102p5);
		
		btnMoreThan102p5 = new JButton("> 102.5");
		btnMoreThan102p5.setBounds(293, 49, 89, 23);
		add(btnMoreThan102p5);
		
		lblYouSayIt = new JLabel("You say it will be : ");
		lblYouSayIt.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblYouSayIt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYouSayIt.setBounds(86, 94, 143, 23);
		add(lblYouSayIt);
		
		 labelPrediction = new JLabel("");
		 labelPrediction.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelPrediction.setHorizontalAlignment(SwingConstants.LEFT);
		labelPrediction.setBounds(239, 94, 143, 23);
		add(labelPrediction);
		
		 lblDrawnNumbers = new JLabel("Drawn numbers :");
		 lblDrawnNumbers.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDrawnNumbers.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDrawnNumbers.setBounds(10, 128, 115, 36);
		add(lblDrawnNumbers);
		
		 lblResultSum = new JLabel("Sum :");
		 lblResultSum.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblResultSum.setHorizontalAlignment(SwingConstants.LEFT);
		lblResultSum.setBounds(41, 175, 128, 37);
		add(lblResultSum);
		int x = 141;
		Border border = BorderFactory.createLineBorder(new Color(128, 0, 128), 2);
		for(int i=0; i<5; i++) {
			boxes[i] = new JLabel();
			boxes[i].setBounds(x, 128, 46, 37);
			x = x + 50;
			boxes[i].setBorder(border);
			boxes[i].setHorizontalAlignment(SwingConstants.CENTER);
			boxes[i].setFont(new Font("Tahoma", Font.BOLD, 13));
			add(boxes[i]);
		}
		
		btnLessThan102p5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indicator = 1;
				labelPrediction.setText("Less than 102.5");
			}
		});
		
		btnMoreThan102p5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indicator = 2;
				labelPrediction.setText("More than 102.5");
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnBet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(indicator==0) {
					JOptionPane.showMessageDialog(null, "You didn't choose bet event( More or Less).");
						return;
				}
				if(validationForStake()==0) return;
				
				textFieldStake.setEditable(false);
				btnLessThan102p5.setEnabled(false);
				btnMoreThan102p5.setEnabled(false);
				
				increaseNumberOfBet();
				
				updateMoneyDueBet();
				
				behaviorWhilePullingNumbers(12);

				hideAndShowSignOutButton(12);
				
				int sum = 0;
				int time = 2;
				array = generate5Numbers();
				
				for(int i=0; i<5; i++) {
					
					ReminderForLabels rfl = new ReminderForLabels(time);
					time = time + 2;
					rfl.setLabel(boxes[i]);
					rfl.setResultString(String.valueOf(array[i]));
					
					sum = sum + array[i];
				}
				
				ReminderForLabels rfl = new ReminderForLabels(time);
				rfl.setLabel(lblResultSum);
				rfl.setResultString(lblResultSum.getText() + "" + String.valueOf(sum));
				
				ReminderForLabels reminderForResultOfSum5 = new ReminderForLabels(12);
				
				int temporaryInd= 0;
				
				if(indicator==1) {
					if(sum<102.5) {
						
						reminderForResultOfSum5.setLabel(lblGreetingCard);
						reminderForResultOfSum5.setResultString("YOU WON!");
						temporaryInd = 1;
					}
					else {
						reminderForResultOfSum5.setLabel(lblComforting);
						reminderForResultOfSum5.setResultString("You lost");
					}
				}
				else if(indicator==2){
					if(sum>102.5) {
						reminderForResultOfSum5.setLabel(lblGreetingCard);
						reminderForResultOfSum5.setResultString("YOU WON!");
						temporaryInd = 1;
					}
					else {
						reminderForResultOfSum5.setLabel(lblComforting);
						reminderForResultOfSum5.setResultString("You lost");
					}
				}
				ReminderForLabels reminderForGain = new ReminderForLabels(12);
				reminderForGain.setLabel(lblUserGain);
				
				double prolazno = 0;
				if(temporaryInd==1) {
					
					prolazno = 1.80*round(Double.valueOf(textFieldStake.getText()), 2);
					
					prolazno = round(prolazno, 2);
				
					reminderForGain.setResultString(lblUserGain.getText()+ " " + String.valueOf(prolazno));
				
					double moneyAfterWin = round(getMoneyForCurrentUser() + prolazno, 2);
				
					creatingRemForLabAndDataBase(pointerToLabelMoney, 10, getUsername(), Double.toString(moneyAfterWin), 12);
		
				}
				else if(temporaryInd==0) {
					
					reminderForGain.setResultString(lblUserGain.getText()+ " " + Double.toString(0.0));
				}
				
			}
			
		});
	}
	
	public int[] generate5Numbers() {
		int randArr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,20
				,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};
		int[] array = new int[5];

		for (int i = 0; i < 5; i++) {
			Random rand = new Random(); 
			
		        int r = i + rand.nextInt(40 - i);
		        array[i] = randArr[r];
		        randArr[r] = randArr[i];
		}
		
		return array;
	}
	@Override
	public void reset() {
		super.reset();
		indicator = 0;
		labelPrediction.setText("");
		for(int i=0; i<5; i++) boxes[i].setText("");
		
		lblResultSum.setText("Sum : ");
		
		btnLessThan102p5.setEnabled(true);
		btnMoreThan102p5.setEnabled(true);
	}

}
