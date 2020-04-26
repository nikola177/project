package gui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameLucky7 extends GamePanel implements Bonus{

	
	static int maxClickedNumber = 10;
	static int ClickedNumber=0;
	private JPanel panel;
	private int[] clickedNumbersArray = new int[10];
	private int[] randomGenerated17NumbersArray = new int[17];
	private String activeNumberButtons;
	
	MyButtons[][] butArray = new MyButtons[5][8];
	
	public GameLucky7(JButton btnLucky7, JButton btnOddOrEven,  JButton btnSumFirst5, 
			JPanel mainPanel, BackgroundGamePanel bgp, JLabel pointerToLabelMoney, double moneyForCurrentUser, String username) {
		
		super(btnLucky7, btnOddOrEven, btnSumFirst5, mainPanel, bgp, pointerToLabelMoney, moneyForCurrentUser, username);
		setBounds(new Rectangle(39, 347, 117, 23));
		
		activeNumberButtons = "active";
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,8));
		
		panel.setBounds(0, 0, 470, 200);
		
		add(panel);
		
		
		for(int i=0; i<5; i++)
			for(int j=0; j<8; j++) {
				butArray[i][j] = new MyButtons( 0, String.valueOf(i*8+j+1), i*8+j+1);
				panel.add(butArray[i][j]);
			}
		
		for(int i=0; i<5; i++)
			for(int j=0; j<8; j++) {
				MyButtons helpButton = butArray[i][j];
				helpButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(helpButton.getState()==2) helpButton.setState(0);
						
						helpButton.setState(helpButton.getState() + 1);
							
						if(helpButton.getState()%2==1) {
							ClickedNumber++;
							if(activeNumberButtons.equals("active")) helpButton.setBackground(new Color(102,100,100));
							
						}
						else if(helpButton.getState()%2==0) {
							ClickedNumber--;
							if(activeNumberButtons.equals("active")) helpButton.setBackground(new Color(200,0,0));
						}
						
					}
					
				});
			}
		
		btnBet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(getClickedNumber()<10) JOptionPane.showMessageDialog(null, "You clicked " + getClickedNumber() + " numbers.");
				else if(getClickedNumber()>10) JOptionPane.showMessageDialog(null, "You clicked " + getClickedNumber() + " numbers.");
				else{
					
					if(validationForStake()==0) return;									

					 textFieldStake.setEditable(false);
					 
					 increaseNumberOfBet();
					
					 updateMoneyDueBet();
					 
					 activeNumberButtons="inactive";
					 
					 behaviorWhilePullingNumbers(37);
					 
					 hideAndShowSignOutButton(37);
					 				 
					 createArrayOfChosenNumber(); // Creating array of 10 chosen numbers for bet.
					 
					 randomGenerated17NumbersArray = generate17Numbers(); // Creating array of random 17 numbers from 1 to 40. 
						 
					
					 System.out.println("Number of hit numbers is " + numberOfHitNumbers());
					 
					 Congratulations();
				
					 double prolazno = countingGain()+ getMoneyForCurrentUser();
					 
					 prolazno = round(prolazno, 2);
						
					 creatingRemForLabAndDataBase(pointerToLabelMoney, 10, getUsername(), String.valueOf(prolazno), 37);
					

					 ReminderForLabels rfl = new ReminderForLabels(37);
					 rfl.setLabel(lblUserGain);
					 rfl.setResultString(lblUserGain.getText()+ " " + Double.toString(countingGain()));
					 
					 int time = 2;
					  
					 for(int k=0; k<17; k++) {
						 for(int i=0; i<5; i++)
							 for(int j=0; j<8; j++) {
								 if(butArray[i][j].getNumber()==randomGenerated17NumbersArray[k]) {
									 
									 ReminderForButtons rfb = new ReminderForButtons(time);
									 time = time + 2;
									
									 rfb.setCurrentButton(butArray[i][j]);
									 rfb.setX(2);
									 
								 }
							 }
					 }
 
					 for(int i=0; i<5; i++)
						 for(int j=0; j<8; j++) {
							 if(butArray[i][j].getState()==1) {
								 for(int k=0; k<17; k++)
									 if(butArray[i][j].getNumber()==randomGenerated17NumbersArray[k]) {
										
										 ReminderForButtons rfb = new ReminderForButtons(36);
										 rfb.setCurrentButton(butArray[i][j]);
										 rfb.setX(1);
									 }
							 }
						 }
				}
				
			}
			
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					reset();
				}
			
			});
		
	}

	public static int getClickedNumber() {
		return ClickedNumber;
	}
	public static void setClickedNumbers(int x) {
		ClickedNumber = x;
	}
	@Override
	public void reset() {
		
		super.reset();
		
		activeNumberButtons="active";
	
		for(int i=0; i<5; i++)
			for(int j=0; j<8; j++) {
				butArray[i][j].setBackground(new Color(200,0,0));
				butArray[i][j].setState(0);
				setClickedNumbers(0);
				butArray[i][j].setForeground(Color.BLACK);	
				butArray[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));

			}

		lblPotentionalBonus.setText("");
		lblPotentionalBonus.setVisible(false);
	}
	
	public void createArrayOfChosenNumber() {
		int i,j,k=0;
		 
		 for(i=0; i<5; i++)
			 for(j=0; j<8; j++) {
				 if(butArray[i][j].getState()==1) 
					 {
					 	clickedNumbersArray[k] = butArray[i][j].getNumber();
					 	k++;
					 }
			 }
	}
	
	public int[] generate17Numbers() {
		int randArr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,20
				,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};
		int[] array = new int[17];

		for (int i = 0; i < 17; i++) {
			Random rand = new Random(); 
			
		        int r = i + rand.nextInt(40 - i);
		        array[i] = randArr[r];
		        randArr[r] = randArr[i];
		}
		
		return array;
	}
	
	public int numberOfHitNumbers() {
		int br = 0;
		 for(int i=0; i<10; i++)
			 for(int j=0; j<17; j++) {
				 if(clickedNumbersArray[i]==randomGenerated17NumbersArray[j]) br++;
			 }
		 
		 return br;
	}
	
	public double countingGain() {
		int x = numberOfHitNumbers();
		double stake = Double.valueOf(textFieldStake.getText());
		stake = round(stake, 2);
		double gain;
		if(x<4) gain =  0.0;	// We wish you more luck next time! 
		else if(x==4) gain = stake; 	// REFUNDS
		else if(x==5) gain = stake*7;	// WIN
		else if(x==6) gain = stake*30; 	//JACKPOT
		else gain = stake*70; // MEGA JACKPOT
		
		gain = round(gain, 2);
		String day = getDay();
		if(day=="Friday" || day=="Monday") {
			if(numberOfHitNumbers()>=4) {
				ReminderForLabels reminderForBonus = new ReminderForLabels(37);
				reminderForBonus.setLabel(lblPotentionalBonus);
				reminderForBonus.setResultString("BONUS: 50%");
				reminderForBonus.setInd(1);
			}
		
			gain = round(gain * 1.50, 2);
		}
		return gain;
	}
	
	public void Congratulations() {
		
		ReminderForLabels reminderForCongratulationsLabels = new ReminderForLabels(37);
		
		if(numberOfHitNumbers()<5) reminderForCongratulationsLabels.setLabel(lblComforting);
		
		else if(numberOfHitNumbers()>4) reminderForCongratulationsLabels.setLabel(lblGreetingCard);
		
		if(numberOfHitNumbers()<4) reminderForCongratulationsLabels.setResultString("We wish you more luck next time.");
		else if(numberOfHitNumbers()==4) reminderForCongratulationsLabels.setResultString("You hit: "+numberOfHitNumbers() +" numbers" +", money refounded.");
		else if(numberOfHitNumbers()==5) reminderForCongratulationsLabels.setResultString("You hit: "+numberOfHitNumbers() +" numbers" + ", WIN!");
		else if(numberOfHitNumbers()==6) reminderForCongratulationsLabels.setResultString("You hit: "+numberOfHitNumbers() +" numbers" + ", JACKPOT!");
		else if(numberOfHitNumbers()>6) reminderForCongratulationsLabels.setResultString("You hit: "+numberOfHitNumbers() +" numbers" + ", MEGA JACKPOT!");
		
		
	}

	@Override
	public String getDay() {
		Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        String namedDay;
        switch (day) {
            case 1:
            	namedDay = "Sunday";
                break;
            case 2:
            	namedDay = "Monday";
                break;
            case 3:
            	namedDay = "Tuesday";
                break;
            case 4:
            	namedDay = "Wednesday";
                break;
            case 5:
            	namedDay = "Thursday";
                break;
            case 6:
            	namedDay = "Friday";
                break;
                
                default: namedDay = "Saturday";
            }
        return namedDay;
	}
}

