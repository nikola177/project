package gui;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class ReminderForLabels {

	Timer timer;
	int x;
	int ind;
	int winOrLose=-1;
	JLabel label;
	String result;
	String username;
	  public ReminderForLabels(int seconds) {
	        timer = new Timer();
	        x = 1;
	        timer.schedule(new RemindTask(), seconds*1000);
	        ind = -1;
	        
		}
	
	class RemindTask extends TimerTask {
        public void run() {
           
        	//x = 10 -> writing in database
        	
        		label.setText(result);
        		if(ind==1) { //ind = 1 is for OddOrEven game , ( and for GameSumFirst5 game)
        			
        			label.setVisible(true); //this label has blue border (rectangle). Alse label shows choosen number.
        									//Due the resets methods text to this labels is going to set to empty string.
        		}
        		
        		if(x==10) {
        			
					try {
						PreparedStatement stmt = Login.getConnection().prepareStatement("update Users set money=" + "'" + result + "'" +  " where username="+ "'" + getUsername()+"'");
						stmt.executeUpdate();
						 UserGoodLogin.setMoneyForCurrentUser(Double.valueOf(result));
						 GamePanel.setMoneyForCurrentUserNoDB(Double.valueOf(result));
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error!");
						e1.printStackTrace();
					}
        			
        		}
        		
            	timer.cancel(); //Terminate the timer thread
         
        }
	
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public void setResultString(String result) {
		this.result = result;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setInd(int ind) {
		this.ind = ind;
	}
	public void setWinOrLose(int winOrLose) {
		this.winOrLose = winOrLose;
	}
	public int getWinOrLose() {
		return winOrLose;
	}
}
