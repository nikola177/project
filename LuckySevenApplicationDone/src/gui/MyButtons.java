package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class MyButtons extends JButton {

	int state;
	int number;
		
	public MyButtons(int state, String numberAsString, int number) {
		super(numberAsString);
		this.state = state;
		this.number = number;
		setBackground(new Color(200,0,0));
		setForeground(Color.BLACK);	
		setSize(4,4);
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
		
	}
	public void shotNumber() {
		setBackground(new Color(128, 0, 128));
		Border bored = BorderFactory.createLineBorder(Color.WHITE, 2);
	    setBorder(bored);
	}
	public void makeWhiteNumber() {
		setForeground(Color.WHITE);	
		}
	

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	
	
}
