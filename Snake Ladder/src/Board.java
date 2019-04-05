import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
public class Board extends JFrame {
	ImageIcon diceImage,animatedImage,player1Image,player2Image;
	JLabel lblBoard,lblDice,lblAnimated,lblPlayer1,lblPlayer2,lblTurn;
	JPanel panBoard,panLeft;
	JButton btnClose,btnTurn;
	JTextField tf1=new JTextField(10);
	boolean six=false,six2=false,six3=false;
	int boardWidth=500,boardHeight=500,panelWidth=200,randomNumber=1,turn=1,curPosP1=0,curPosP2=0,curXP1,curYP1,curXP2,curYP2,dir=1,dir2=1,speed=10;
	int snakes[][]= {{17,7},{54,34},{62,19},{64,60},{87,24},{93,73},{95,75},{98,79}};
	int ladder[][]= {{1,38},{4,14},{9,31},{21,42},{28,84},{51,67},{71,91},{80,100}};	
	Board(){
		panBoard=new MyJPanel();
		panBoard.setPreferredSize(new Dimension(boardWidth,boardHeight));
		player1Image=new ImageIcon("images/blue.png");
		player2Image=new ImageIcon("images/green.png");
		lblPlayer1=new JLabel(new ImageIcon(player1Image.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		lblPlayer2=new JLabel(new ImageIcon(player2Image.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		panBoard.setLayout(null);
		lblPlayer1.setBounds(25, 475, 25, 25);
		lblPlayer2.setBounds(0, 475, 25, 25);
		lblPlayer1.setVisible(false);
		lblPlayer2.setVisible(false);
		panBoard.add(lblPlayer1);
		panBoard.add(lblPlayer2);
		add(panBoard);
		
		panLeft=new JPanel();
		panLeft.setBackground(Color.green);
		panLeft.setPreferredSize(new Dimension(panelWidth,boardHeight));
		panLeft.setLayout(null);
		//tf1.setBounds(0,0,50,50);
		//panLeft.add(tf1);
		btnClose=new JButton("Close");
		setStyleButton(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		btnClose.setBounds(50,50,100,50);
		panLeft.add(btnClose);
		
		btnTurn=new JButton("Turn");
		setStyleButton(btnTurn);
		btnTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				lblDice.setVisible(false);
				lblAnimated.setVisible(true);
				new Thread() {
					public void run() {
						try {
							Thread.sleep(1000);
						}
						catch(InterruptedException e) {
							e.printStackTrace();
						}
						lblAnimated.setVisible(false);
						Random r1=new Random();
						randomNumber=r1.nextInt(6)+1;
						//or
						//randomNumber=Integer.parseInt(tf1.getText());
						
						
						diceImage=new ImageIcon("images/"+randomNumber+".png");
						lblDice.setIcon(new ImageIcon(diceImage.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
						lblDice.setVisible(true);
						revalidate();
						if(randomNumber!=6) {
							speed=10;
							moveCoin(turn,randomNumber,1);
							six=false;
							six2=false;
							six3=false;
						}
						else{
							if(six2==true) {
								six3=true;
								randomNumber=0;
								JOptionPane.showMessageDialog(Board.this, "3 sixes so can't move");
								six=false;
								six2=false;
								six3=false;
							}
							else if(six==true)
								six2=true;
							else
								six=true;
						}
						if(randomNumber!=6) {
							if(turn==1) {
								turn=2;
								lblTurn.setText("Player-2's Turn");
							}	
							else {
								turn=1;
								lblTurn.setText("Player-1's Turn");							
							}
						}
						else {
							lblTurn.setText(lblTurn.getText()+" again");
						}
								
					}
				}.start();
			}
		});
		btnTurn.setBounds(50,200,100,50);
		panLeft.add(btnTurn);
		
		diceImage=new ImageIcon("images/1.png");
		lblDice=new JLabel(new ImageIcon(diceImage.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		lblDice.setBounds(50, 300, 50, 50);
		panLeft.add(lblDice);
		lblDice.setVisible(false);
		
		animatedImage=new ImageIcon("images/animated_dice.gif");
		lblAnimated=new JLabel(new ImageIcon(animatedImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		lblAnimated.setBounds(50, 400, 100, 100);
		panLeft.add(lblAnimated);
		lblAnimated.setVisible(false);

		lblTurn=new JLabel("Player-1's Turn");
		lblTurn.setBounds(5,150,200,50);
		setStyleLabel(lblTurn);
		panLeft.add(lblTurn);		
		
		add(panLeft,"West");
		setUndecorated(true);
		setSize(boardWidth+panelWidth,boardHeight);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	void moveCoin(int player,int n,int sign) {	
		if(player==1 && curPosP1!=0 || player==2 && curPosP2!=0) {
			if(six2==true) {
				n+=12;
			}
			else if(six==true) {
				n+=6;
			}
		}
		if(player==1 && curPosP1==0 || player==2 && curPosP2==0) {
			if(six2==true) {
				n+=6;
			}
		}
		if(player==1) {
			if(sign!=-1) {
				if(curPosP1+n>100) {
					JOptionPane.showMessageDialog(this, "Can't Move Player 1");
					return;
				}
			}			
			if(curPosP1==0){
				if(n!=6 && six==false) {
					JOptionPane.showMessageDialog(this, "Can't Move Player 1 you need 6");
					return;
				}
				curXP1=-25;
				curYP1=475;
				lblPlayer1.setBounds(curXP1,curYP1,25,25);
				lblPlayer1.setVisible(true);
				revalidate();
			}
			for(int i=1;i<=n;i++) {
				if(curPosP1!=0 && curPosP1%10==0) {
					dir=-dir;
					curYP1-=(50*sign);
					i++;
					curPosP1+=sign;
					if(i>n) {
						lblPlayer1.setBounds(curXP1,curYP1,25,25);
						break;
					}
				}
				for(int j=1;j<=50;j++) {
					curXP1+=(dir*sign);
					lblPlayer1.setBounds(curXP1,curYP1,25,25);
					try {
						Thread.sleep(speed);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					revalidate();
				}
				curPosP1+=sign;
			}
			int result=search(curPosP1);
			if(result!=curPosP1) {
				six=false;
				six2=false;
				six3=false;
				speed=1;
				if(result>curPosP1) {//Ladder
					new Thread() {
						public void run() {
							playSound(1);
						}
					}.start();
					moveCoin(player,result-curPosP1,1);
				}
				else{//Snake
					new Thread() {
						public void run() {
							playSound(2);
						}
					}.start();
					moveCoin(player,curPosP1-result,-1);
				}
			}
		}
		else {
			if(sign!=-1) {
				if(curPosP2+n>100) {
					JOptionPane.showMessageDialog(this, "Can't Move Player 2");
					return;
				}
			}			
			if(curPosP2==0){
				if(n!=6 && six==false) {
					JOptionPane.showMessageDialog(this, "Can't Move you need 6");
					return;
				}
				curXP2=-50;
				curYP2=475;
				lblPlayer2.setBounds(curXP2,curYP2,25,25);
				lblPlayer2.setVisible(true);
				revalidate();
			}
			for(int i=1;i<=n;i++) {
				if(curPosP2!=0 && curPosP2%10==0) {
					dir2=-dir2;
					curYP2-=(50*sign);
					i++;
					curPosP2+=sign;
					if(i>n) {
						lblPlayer2.setBounds(curXP2,curYP2,25,25);
						break;
					}
				}
				for(int j=1;j<=50;j++) {
					curXP2+=(dir2*sign);
					lblPlayer2.setBounds(curXP2,curYP2,25,25);
					try {
						Thread.sleep(speed);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					revalidate();
				}
				curPosP2+=sign;
			}
			int result=search(curPosP2);
			if(result!=curPosP2) {
				six=false;
				six2=false;
				six3=false;
				speed=1;
				if(result>curPosP2) {//ladder
					new Thread() {
						public void run() {
							playSound(1);
						}
					}.start();
					moveCoin(player,result-curPosP2,1);
				}
				else {//snake
					new Thread() {
						public void run() {
							playSound(2);
						}
					}.start();
					moveCoin(player,curPosP2-result,-1);
				}
			}
		}
		if(curPosP1==100) {
			JOptionPane.showMessageDialog(this, "Player 1 wins");
			dispose();
		}
		if(curPosP2==100) {
			JOptionPane.showMessageDialog(this, "Player 2 wins");
			dispose();
		}
	}
	void setStyleButton(JButton cmp){
			Font fontStyle= new Font("Ravie",Font.BOLD,18);
		    cmp.setFont(fontStyle);
		    Color fontClr= new Color(219, 216, 212,255);  // 4th argument is the opacity
		    cmp.setForeground(fontClr); 
		    fontClr= new Color(112, 34, 37);
		    cmp.setBackground(fontClr);
		    cmp.setBorderPainted(false);
		    cmp.setFocusPainted(false);
	}
    void setStyleLabel(JLabel cmp){
		Font fontStyle= new Font("Forte",Font.BOLD,14);
		cmp.setFont(fontStyle);
		Color fontClr= new Color(249, 2, 23);
		cmp.setForeground(fontClr);
    }
	int search(int n) {
		for(int i=0;i<snakes.length;i++) {
			if(snakes[i][0]==n)
				return snakes[i][1];
		}
		for(int i=0;i<ladder.length;i++) {
			if(ladder[i][0]==n)
				return ladder[i][1];
		}
		return n;
	}
	public void playSound(int code) {
	    try {
	    	String s1;
	    	if(code == 1) {
	    		s1="music/ladder.wav";
	    	}
	    	else {
	    		s1="music/snake.wav";
	    	}
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(s1).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	public static void main(String[] args) {
		new Board();
	}

}
class MyJPanel extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon boardImage=new ImageIcon("images/board.png");
		g.drawImage(boardImage.getImage(), 0, 0, 500, 500, null);
	}
}