package gui;

import javax.swing.JPanel;

import java.awt.Color;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;

public class BackgroundGamePanel extends JPanel {

	JLabel lblLucky7;
	JLabel lblSumFirst5;
	JLabel lblOddOrEven;
	int indicator = 0;
	
	JButton btnAboutGames;
	
	public BackgroundGamePanel() {
		setLayout(null);
		setBackground(new Color(135, 206, 235));
		lblLucky7 = new JLabel();
		lblLucky7.setVisible(false);
		lblLucky7.setText("<html><div style=" + "'" + "color: #800080;" + " padding: 0px 10px 10px 10px;"+ "margin: center;" +"'" +
		"><h3>Lucky 7 game:</h3>There are 40 numbers from 1 to 40. Player must chose his 10 ( unique) of them. After that begins drawing 17" + 
				" unique numbers ( from 1 to 40). Among those 17 numbers, player must has at least 4 his numbers. Quotas:<br>for 4 hit numbers quota is 1,<br>" + 
				"for 5 hit numbers quota is 7,<br>" + 
				"for 6 hit numbers quota is 30,<br>" + 
				"for 7 or more hit numbers quota is 70.</div></html>");
		
		lblLucky7.setBounds(0, 22, 450, 148);
		add(lblLucky7);
		lblSumFirst5 = new JLabel();
		lblSumFirst5.setVisible(false);
		lblSumFirst5.setText("<html><div style=" + "'" + "color: #800080;" + " padding: 0px 10px 10px 10px;"+ "margin: center;" +"'"+
		"><h3>Sum First 5 game:</h3>There is drawing 5 unique numbers in range 1 to 40. Player needs to chose result event."
				+ " Result event represents sum of drawed 5 numbers is more or less than 102.5.<br>Quota is 1.80.</div></html>");
		lblSumFirst5.setBounds(0, 181, 450, 98);
		add(lblSumFirst5);
		
		lblOddOrEven = new JLabel();
		lblOddOrEven.setVisible(false);
		lblOddOrEven.setText("<html><div style=" + "'" + "color: #800080;" + " padding: 0px 10px 10px 10px;"+ "margin: center;" +"'"+
		"><h3>Odd or Even game:</h3>There is drawing 1 number in range 1 to 40. Player needs to chose result event."
				+ " Result event represents drawed number is odd or even.<br>Quota is 1.80.");
		lblOddOrEven.setBounds(0, 287, 450, 93);
		add(lblOddOrEven);
		
		btnAboutGames = new JButton("About games");
		btnAboutGames.setBounds(332, 0, 118, 23);
		add(btnAboutGames);
		
		btnAboutGames.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				if(indicator==1) {
					lblLucky7.setVisible(false);
					lblSumFirst5.setVisible(false);
					lblOddOrEven.setVisible(false);
					indicator = 0;
					btnAboutGames.setText("About games");
					return;
				}
				else if(indicator==0) {
					lblLucky7.setVisible(true);
					lblSumFirst5.setVisible(true);
					lblOddOrEven.setVisible(true);
					indicator = 1;
					btnAboutGames.setText("Hide");
					return;
				}
			
			}
			
		});
		
		
	
	}
}
