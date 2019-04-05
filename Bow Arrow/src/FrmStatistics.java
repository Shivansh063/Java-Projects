import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.*;    
import sun.audio.*;
public class FrmStatistics extends JFrame  {  
	private static final long serialVersionUID = 1L;
	JLabel scoreCompliment,scoreLabel,scoreBoard,lblNewHighScore;
	FileOutputStream fos;
	JButton resume,nextLevel,quit,mainMenu,nextGame,scorePoint;   
	Font fontStyle;
	Color fontClr;
	JPanel p1;
	Clip clip;

	Insets gapBtwMenuQuitNext;

	static int level=0;




	FrmStatistics(BowArrow obj,int level) {
		FrmStatistics.level=level;
		
	  
		
		
	    setLayout(null);
		setContentPane(new JLabel(new ImageIcon("images/GameOver.png")));  // to add image in back ground
	    fontStyle= new Font("Chiller",Font.BOLD,44);  // chiller to give text style
	    scoreCompliment = new JLabel();

	    if(BowArrow.score[BowArrow.level-1]>=750){
	    	scoreCompliment.setText("You Played Excellent!!!");
            playSound_mad();
	    }
	     else if(BowArrow.score[BowArrow.level-1]>=500 && BowArrow.score[BowArrow.level-1]<750){
	        playSound_mad();
		    scoreCompliment.setText("Well Done!!!");
	    }
	    else if(BowArrow.score[BowArrow.level-1]>100 && BowArrow.score[BowArrow.level-1]<=500)
		    scoreCompliment.setText("You can do Better!!!");
	    else 
	    	scoreCompliment.setText("You need a Eye Check-Up");
	    
		scoreCompliment.setBounds(300,10,500,150);
		scoreCompliment.setFont( fontStyle);
		fontClr= new Color(0,0,255);
		scoreCompliment.setForeground(Color.blue);
		add(scoreCompliment,BorderLayout.NORTH);
//--------------------------------------------------------------------------------------------------		
	    scoreLabel= new JLabel("Your Score :");
	    
	    scoreLabel.setFont(fontStyle);
	    fontClr= new Color(187,182,165);
	    scoreLabel.setForeground(fontClr);
	    scoreLabel.setBounds(400,100,200,100);
	    add(scoreLabel);
//-------------------------------------------------------------------------------------------------------	    
	    scoreBoard = new JLabel(""+BowArrow.score[BowArrow.level-1]);
	   
	    scoreBoard.setFont(fontStyle);
	    fontClr= new Color(147,82,115);
	    scoreBoard.setForeground(Color.RED); 
	    scoreBoard.setBounds(300,100,200,100);
	    add(scoreBoard);
//---------------------------------------------------------------------------------------------------
	    int rank;	
	    if(BowArrow.score[BowArrow.level-1]> Integer.parseInt(MainMenuOfArrow.p1.getProperty("rank1-"+BowArrow.level))){
	    		rank=1;
	    		MainMenuOfArrow.p1.setProperty("rank4-"+BowArrow.level,MainMenuOfArrow.p1.getProperty("rank3-"+BowArrow.level));
	    		MainMenuOfArrow.p1.setProperty("rank3-"+BowArrow.level,MainMenuOfArrow.p1.getProperty("rank2-"+BowArrow.level));
	    		MainMenuOfArrow.p1.setProperty("rank2-"+BowArrow.level,MainMenuOfArrow.p1.getProperty("rank1-"+BowArrow.level));	    			    		
	    }
	    else if(BowArrow.score[BowArrow.level-1]> Integer.parseInt(MainMenuOfArrow.p1.getProperty("rank2-"+BowArrow.level))){
	    		rank=2;
	    		MainMenuOfArrow.p1.setProperty("rank4-"+BowArrow.level,MainMenuOfArrow.p1.getProperty("rank3-"+BowArrow.level));
	    		MainMenuOfArrow.p1.setProperty("rank3-"+BowArrow.level,MainMenuOfArrow.p1.getProperty("rank2-"+BowArrow.level));	    		
	    }
	    else if(BowArrow.score[BowArrow.level-1]> Integer.parseInt(MainMenuOfArrow.p1.getProperty("rank3-"+BowArrow.level))){
    		rank=3;
    		MainMenuOfArrow.p1.setProperty("rank4-"+BowArrow.level,MainMenuOfArrow.p1.getProperty("rank3-"+BowArrow.level));
	    }
	    else if(BowArrow.score[BowArrow.level-1]> Integer.parseInt(MainMenuOfArrow.p1.getProperty("rank4-"+BowArrow.level)))
    		rank=4;
	    else
	    	rank=0;
	    if(rank>0){
	    		lblNewHighScore= new JLabel("<html>New High Score<br> Your rank is: "+rank+"</html>");
	    		
	    		lblNewHighScore.setFont(fontStyle);
	    	    fontClr= new Color(147,82,115);
	    	    lblNewHighScore.setForeground(Color.white); 
	    	    lblNewHighScore.setBounds(200,200,400,100);
	    	    add(lblNewHighScore);
	    	    MainMenuOfArrow.p1.setProperty("rank"+rank+"-"+BowArrow.level,BowArrow.score[BowArrow.level-1]+"" );
	    	    writeScores();
	    	}
	    
	    
//-------------------------------------------------------------------------------------------------------	    
	    mainMenu= new JButton("Main Menu");
	    
	    mainMenu.setFont(fontStyle);
	    fontClr= new Color(147,82,115);
	    mainMenu.setForeground(Color.RED); 
	    mainMenu.setBounds(150,400,600,100);
		add(mainMenu);
	    mainMenu.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae){	
	    		dispose();
	    		clip.stop();
	    		obj.dispose();  // to dipose game Screen
	    		new MainMenuOfArrow(); 
	    		// to go main menu after playing game   
	    	}
	    });

//---------------------------------------------------------------------------------------------------------	    
	    quit= new JButton("Quit");
	    
	    quit.setFont(fontStyle);
	    fontClr= new Color(147,82,115);
	    quit.setForeground(Color.RED); 
	    quit.setBounds(350,400,165,100);
	    quit.setContentAreaFilled(false);
	    quit.setBorderPainted(false);
	    quit.setFocusPainted(false);
	  	quit.addActionListener(new ActionListener() {
	  	   	public void actionPerformed(ActionEvent ae){
	  	   		clip.stop();
	  	   		System.exit(0);
	  	   	}
	  	});
	    add(quit);
//-------------------------------------------------------------------------------------------------	   
	    nextLevel= new JButton("Next");
	    
	    nextLevel.setFont(fontStyle);
	    nextLevel.setForeground(Color.white); 
	    nextLevel.setBounds(400,300,110,100); 
	    nextLevel.setContentAreaFilled(false);
	  	nextLevel.setBorderPainted(false);
	  	nextLevel.setFocusPainted(false);
	  	nextLevel.addActionListener(new ActionListener() {
	  	   	public void actionPerformed(ActionEvent ae){
	  	   		FrmStatistics.level++;
	  	   		clip.stop();
	  	   		obj.dispose();   // close BowArrow frame
	  	   		dispose();   // close FrmStatistics frame
	  	   		System.out.println("Level after adding"+FrmStatistics.level);
	  	   		new BowArrow(FrmStatistics.level);  // start new BowArrow game   //max 4
	  	   	}
	  	});
	  	if(level<4 && BowArrow.score[BowArrow.level-1]>500)
	  	{
	  		//if(level!=4)
	  		System.out.println("Local level"+level);
	  		add(nextLevel);
	  	}
		
		
//---------------------------------------------------------------------------------------------------
	  	    resume= new JButton("Again");
		    
		    resume.setFont(fontStyle);
		    resume.setForeground(Color.white); 
		    resume.setBounds(0,300,150,100); 
		    resume.setContentAreaFilled(false);
		    resume.setBorderPainted(false);
		    resume.setFocusPainted(false);
		    resume.addActionListener(new ActionListener(){
		  	   	public void actionPerformed(ActionEvent ae){
		  	   		    clip.stop();
		  	        	obj.dispose();   // close BowArrow frame
	  	   		        dispose();   // close FrmStatistics frame
	  	   	        	new BowArrow(FrmStatistics.level);  // start new BowArrow game 
		  	   	}  
		  	}); 
		    add(resume);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setSize(400,400);
		setTitle("Result");
		setResizable(false);
		
	    setVisible(true);
    }     
	   public void playSound_mad(){
     	try{
			String s2="music/clapping.wav";
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(s2).getAbsoluteFile());
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
			clip.start();
		    clip.loop(Clip.LOOP_CONTINUOUSLY);
	   }catch (Exception eX) {
	        System.out.println("Error with playing sound.");
	        eX.printStackTrace();
	    }
		
     }
	public void writeScores(){
			try{
				fos= new FileOutputStream("RankRecord.txt");  
				MainMenuOfArrow.p1.store(fos,"");
				fos.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
	    }

	public static void main(String args[]){
	    new FrmStatistics();
	    }
}

