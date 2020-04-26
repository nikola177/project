package gui;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class ReminderForButtons {

	Timer timer;
	int x;
	int ind;

	MyButtons myButton;
	JButton button;

    public ReminderForButtons(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
        
	}

    class RemindTask extends TimerTask {
        public void run() {
            
            	if(x==1)myButton.shotNumber(); //1 for random 17 generated numbers
            	else if(x==2) myButton.makeWhiteNumber(); //2 for hit numbers
            	else if(x==3) button.setVisible(true); // 3 and 4 for showing back to button RESET and button BET
            	else if(x==4) button.setEnabled(true);
            	else if(x==5) button.setEnabled(true);
            
            	timer.cancel(); //Terminate the timer thread
         
        }
    }
    
    public void setX(int x) {
    	this.x = x;
    }

	public void setResultMesage(int ind) {
		this.ind = ind;
		
	}
	public void setCurrentButton(MyButtons myButton) {
		this.myButton = myButton;
	}
	public void setButton(JButton button) {
		this.button = button;
	}

	
}
