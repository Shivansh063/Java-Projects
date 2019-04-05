import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.*;
public class MainMenuOfArrow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JLabel lblMainMenu,lblLevel,lblHighScore,lblHelp,lblHelpText,lblRank1,lblRank2,lblRank3,lblRank4,
	lblRank1Score,lblRank2Score,lblRank3Score,lblRank4Score;
	int level=1;
	Clip clip;
	Font fontStyle;
	Color fontClr,fontClr2;
	JButton btnStart,btnLevel,btnHighScore,btnHelp,btnBack,btnBack2,btnBack3,btnLevel1,btnLevel2,btnLevel3,btnLevel4,
	        btnScoreLevel1,btnScoreLevel2,btnScoreLevel3,btnScoreLevel4,quit;
	static Properties p1;
	static FileInputStream fis;
	static FileOutputStream fos; 
	MainMenuOfArrow(){
		setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
		d.width=(d.width-getSize().width)/2;
		d.height=(d.height-getSize().height)/2;
		setLocation(d.width,d.height);      // to locate at center of screen
		setResizable(false);
		setUndecorated(true);
		setContentPane(new JLabel(new ImageIcon("images/menuImage.jpg")));
		readScores(); 
	
		

		btnStart= new JButton("Start");
		btnStart.setBounds(1000,100,300,80);
		setStyleButton(btnStart);
		add(btnStart);
		
		btnStart.addActionListener(new ActionListener() {
	  	  	public void actionPerformed(ActionEvent ae){
	  	  		dispose();
	  	  		new BowArrow(level);
	  	  		clip.stop();
	  	   	}
	  	});
	    
		//Level
	    btnLevel= new JButton("Level");
	    btnLevel.setBounds(1000,200,300,80);
	    setStyleButton(btnLevel);
	    btnLevel.addActionListener(new ActionListener() {
	  	    public void actionPerformed(ActionEvent ae){	
	  	    	showScreen(2);
	  	    }
	  	});
	    add(btnLevel);

		lblLevel= new JLabel("Choose Your Level");
    	lblLevel.setBounds(850,30,500,50);
		setStyleHead(lblLevel);
    	add(lblLevel);

	    btnLevel1= new JButton("Level 1");
	    btnLevel1.setBounds(1000,120,300,80);
	    setStyleButton(btnLevel1);
        add(btnLevel1);
	    btnLevel1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae){
        		level=1;
        		toggleColorOfButton(btnLevel1);
        	}
        });
		    
	    btnLevel2= new JButton("Level 2");
        btnLevel2.setBounds(1000,220,300,80);
        setStyleButton(btnLevel2);
        btnLevel2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae){
        		level=2;
        		toggleColorOfButton(btnLevel2);	
        	}
        });
        add(btnLevel2);
        
        btnLevel3= new JButton("Level 3");
        btnLevel3.setBounds(1000,320,300,80);
        setStyleButton(btnLevel3);
        btnLevel3.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent ae){
	       		level=3;
	       		toggleColorOfButton(btnLevel3);	
	       	}
	       });
        add(btnLevel3);
    
        btnLevel4= new JButton("Level 4");
        btnLevel4.setBounds(1000,420,300,80);
        setStyleButton(btnLevel4);
        btnLevel4.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent ae){
	        		level=4;
	        		toggleColorOfButton(btnLevel4);
	        	}
	        });
        add(btnLevel4);

        //High Score
	    btnHighScore= new JButton("High Score");
	    btnHighScore.setBounds(1000,300,300,80);
	    setStyleButton(btnHighScore);
	    btnHighScore.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae){
	    		showScreen(3);	
	  	    }
	  	});   
	    add(btnHighScore);
	    
		lblHighScore= new JLabel("Meet Our Experts");
    	lblHighScore.setBounds(900,80,500,70);
		setStyleHead(lblHighScore);
    	add(lblHighScore);
    
	    btnScoreLevel1= new JButton("Star Of Level 1");
        btnScoreLevel1.setBounds(1000,170,300,80);
        setStyleButton(btnScoreLevel1);
        btnScoreLevel1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae){
        		toggleColorOfButton(btnScoreLevel1);
        		rankDisplay(1);
        	}
        });
        add(btnScoreLevel1);
	        
        btnScoreLevel2= new JButton("Star Of Level 2");
        btnScoreLevel2.setBounds(1000,270,300,80);
        setStyleButton(btnScoreLevel2);
        btnScoreLevel2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae){
        		toggleColorOfButton(btnScoreLevel2);
        		rankDisplay(2);
        	}
        });
        add(btnScoreLevel2);
        
        btnScoreLevel3= new JButton("Star Of Level 3");
        btnScoreLevel3.setBounds(1000,370,300,80);
	    setStyleButton(btnScoreLevel3);
	    btnScoreLevel3.addActionListener(new ActionListener() {
  	      	public void actionPerformed(ActionEvent ae){
  	       		toggleColorOfButton(btnScoreLevel3);
  	       		rankDisplay(3);
  	       	}
  	    });
	    add(btnScoreLevel3);
	        
	    btnScoreLevel4= new JButton("Star Of Level 4");
	    btnScoreLevel4.setBounds(1000,470,300,80);
	    setStyleButton(btnScoreLevel4);
	    btnScoreLevel4.addActionListener(new ActionListener() {
  	       	public void actionPerformed(ActionEvent ae){
  	       		toggleColorOfButton(btnScoreLevel4);
  	       		rankDisplay(4);
  	       	}
  	    });
	    add(btnScoreLevel4);

    	lblRank1= new JLabel("Rank-1");
	    lblRank1.setBounds(900,100,200,80);
	    setStyleOtherLabel(lblRank1);
	    add(lblRank1);
			
	    lblRank1Score= new JLabel();	    
	    lblRank1Score.setBounds(1150,100,150,80);
	    setStyleOtherLabel(lblRank1Score);
		add(lblRank1Score);

		lblRank2= new JLabel("Rank-2");
	    lblRank2.setBounds(900,200,200,80);
	    setStyleOtherLabel(lblRank2);
	    add(lblRank2);
			
		lblRank2Score= new JLabel();
	    lblRank2Score.setBounds(1150,200,150,80);
	    setStyleOtherLabel(lblRank2Score);
	    add(lblRank2Score);
			
		lblRank3= new JLabel("Rank-3");
	    lblRank3.setBounds(900,300,200,80);
	    setStyleOtherLabel(lblRank3);
	    add(lblRank3);
			
		lblRank3Score= new JLabel();
	    lblRank3Score.setBounds(1150,300,150,80);
	    setStyleOtherLabel(lblRank3Score);
	    add(lblRank3Score);
			
		lblRank4= new JLabel("Rank-4");
	    lblRank4.setBounds(900,400,200,80);
	    setStyleOtherLabel(lblRank4);
	    add(lblRank4);
		
		lblRank4Score= new JLabel();
	    lblRank4Score.setBounds(1150,400,150,80);
	    setStyleOtherLabel(lblRank4Score);
	    add(lblRank4Score);
	    
	    //Help
	    btnHelp= new JButton("Help");
	    setStyleButton(btnHelp);
	    btnHelp.setBounds(1000,400,300,80);	  	    
	    btnHelp.addActionListener(new ActionListener() {
	  	    public void actionPerformed(ActionEvent ae){
	  	    	showScreen(4);
	  	    }
	  	});
	    add(btnHelp);

	    lblHelp= new JLabel("Help");
	    lblHelp.setBounds(1000,50,500,70);
		setStyleHead(lblHelp);
	    add(lblHelp);
			
		lblHelpText = new JLabel("<html>Press Space to throw arrow<br>or click on the character <br>alt+f4 to stop game any time</html>");
    	lblHelpText.setBounds(750,300,600,350);
    	setStyleOtherLabel(lblHelpText);
    	add(lblHelpText);

    	//quit
	    quit= new JButton("Quit");
	    quit.setBounds(1000,500,300,80);    
	    setStyleButton(quit);
	    quit.addActionListener(new ActionListener() {
	  	    public void actionPerformed(ActionEvent ae){
	  	    		System.exit(0);
	  	    }
	  	});
	    add(quit);

		btnBack= new JButton("Back");
		btnBack.setBounds(1050,600,200,100);
		setStyleOtherButton(btnBack);
	    btnBack.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae){
	    		showScreen(1);
	    	}
	  	});
	    add(btnBack);

		btnBack2= new JButton("Back");
		btnBack2.setBounds(1050,600,200,100);
		setStyleOtherButton(btnBack2);
	    btnBack2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae){
	    		showScreen(3);
	    	}
	  	});
	    add(btnBack2);
	    showScreen(1);
	    toggleColorOfButton(btnLevel1);
		playSound();
	    setVisible(true);}
		
		public void playSound(){
			
	    try{
			String s1="music/industry_mad.wav";
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(s1).getAbsoluteFile());
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
			 clip.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	
	
		

		
	 
	
		public static void main(String[] args) {
         new MainMenuOfArrow();
	}

	void showScreen(int n){
		
		btnStart.setVisible(false);
		btnLevel.setVisible(false);
		btnHighScore.setVisible(false);
		btnHelp.setVisible(false);
		lblLevel.setVisible(false);
		btnLevel1.setVisible(false);
		btnLevel2.setVisible(false);
		btnLevel3.setVisible(false);
		btnLevel4.setVisible(false);
		btnBack.setVisible(false);
		lblHighScore.setVisible(false);
		btnScoreLevel1.setVisible(false);
		btnScoreLevel2.setVisible(false);
		btnScoreLevel3.setVisible(false);
		btnScoreLevel4.setVisible(false);
		btnBack2.setVisible(false);
		lblHelp.setVisible(false);
		lblHelpText.setVisible(false);
		lblRank1.setVisible(false);
		lblRank1Score.setVisible(false);
		lblRank2.setVisible(false);
		lblRank2Score.setVisible(false);
		lblRank3.setVisible(false);
		lblRank3Score.setVisible(false);
		lblRank4.setVisible(false);
		lblRank4Score.setVisible(false);
		quit.setVisible(false);
		if(n==1){
			
			btnStart.setVisible(true);
			btnLevel.setVisible(true);
			btnHighScore.setVisible(true);
			btnHelp.setVisible(true);
			quit.setVisible(true);
			
		}
		else if(n==2){
			lblLevel.setVisible(true);
			btnLevel1.setVisible(true);
			btnLevel2.setVisible(true);
			btnLevel3.setVisible(true);
			btnLevel4.setVisible(true);
			btnBack.setVisible(true);
			
		}
		else if(n==3){
			lblHighScore.setVisible(true);
			btnScoreLevel1.setVisible(true);
			btnScoreLevel2.setVisible(true);
			btnScoreLevel3.setVisible(true);
			btnScoreLevel4.setVisible(true);
			btnBack.setVisible(true);
		
		}
		else if(n==4){
			lblHelp.setVisible(true);
			lblHelpText.setVisible(true);
			btnBack.setVisible(true);
			
		}
		else if(n==5){
			lblRank1.setVisible(true);
			lblRank1Score.setVisible(true);
			lblRank2.setVisible(true);
			lblRank2Score.setVisible(true);
			lblRank3.setVisible(true);
			lblRank3Score.setVisible(true);
			lblRank4.setVisible(true);
			lblRank4Score.setVisible(true);
			btnBack2.setVisible(true);
			
		}
	}
    public static void readScores(){
		try{
			p1 =new Properties();
			fis= new FileInputStream("RankRecord.txt");  //do not give space after = as we need int
			p1.load(fis);
			fis.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
    }
    public void rankDisplay(int n){
    	showScreen(5);
		lblRank1Score.setText(p1.getProperty("rank1-"+n));  // to set the rank score
		lblRank2Score.setText(p1.getProperty("rank2-"+n));
		lblRank3Score.setText(p1.getProperty("rank3-"+n));
		lblRank4Score.setText(p1.getProperty("rank4-"+n)); 
	}
    void setStyleHead(JLabel cmp){
		fontStyle= new Font("Georgia",Font.BOLD,50);
		cmp.setFont(fontStyle);
		fontClr= new Color(0, 0, 51);
		cmp.setForeground(fontClr);
    }
    void setStyleOtherLabel(JLabel cmp){
	    fontStyle= new Font("Bookman Old Style",Font.BOLD,44);
	    cmp.setFont(fontStyle);
	    fontClr= new Color(0, 0, 0);
		cmp.setForeground(fontClr);
    }
    void setStyleButton(JButton cmp){
		fontStyle= new Font("Dialog",Font.BOLD,34);
	    cmp.setFont(fontStyle);
	    fontClr= new Color(255, 255, 255,170);  // 4th argument is the opacity
	    cmp.setForeground(fontClr); 
	    fontClr= new Color(153, 0, 0);
	    cmp.setBackground(fontClr);
	    cmp.setBorderPainted(false);
	    cmp.setFocusPainted(false);
    }
    void setStyleOtherButton(JButton cmp){
    	fontStyle= new Font("Microsoft Sans Serif",Font.BOLD,50);
    	cmp.setFont(fontStyle);
    	fontClr= new Color(255, 204, 153);
    	cmp.setForeground(fontClr); 
    	cmp.setContentAreaFilled(false);
    	cmp.setBorderPainted(false);
    	cmp.setFocusPainted(false);
    }
    void toggleColorOfButton(JButton btnLevel){
		fontClr= new Color(0, 0, 180);
		fontClr2= new Color(0, 150, 130);
		if(btnLevel==btnLevel1)
			btnLevel1.setBackground(fontClr);
		else
			btnLevel1.setBackground(fontClr2);
		if(btnLevel==btnLevel2)
			btnLevel2.setBackground(fontClr);
		else
			btnLevel2.setBackground(fontClr2);
		if(btnLevel==btnLevel3)
			btnLevel3.setBackground(fontClr);
		else
			btnLevel3.setBackground(fontClr2);
		if(btnLevel==btnLevel4)
			btnLevel4.setBackground(fontClr);
		else
			btnLevel4.setBackground(fontClr2);
		if(btnLevel==btnScoreLevel1)
			btnScoreLevel1.setBackground(fontClr);
		else
			btnScoreLevel1.setBackground(fontClr2);
		if(btnLevel==btnScoreLevel2)
			btnScoreLevel2.setBackground(fontClr);
		else
			btnScoreLevel2.setBackground(fontClr2);
		if(btnLevel==btnScoreLevel3)
			btnScoreLevel3.setBackground(fontClr);
		else
			btnScoreLevel3.setBackground(fontClr2);
		if(btnLevel==btnScoreLevel4)
			btnScoreLevel4.setBackground(fontClr);
		else
			btnScoreLevel4.setBackground(fontClr2);
    }
}

