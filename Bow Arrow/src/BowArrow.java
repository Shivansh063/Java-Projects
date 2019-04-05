import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.*;
class BowArrow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblDisc,lblArrowCount,lblArrow[],lblScore,lblScorel,lblScoreV,lblHitsl,lblHits,lblHitsV,lblMissl,lblMiss,lblMissV,lbllevel,lblpresent;
	private ImageIcon icoDisc,icoArrow,icoArcher,icoScore;
	private JButton btnshooter;
	private Thread discThread,arrowThread;
	private Font flabel,flevel;
	private boolean arrowReached=true;
	private int frmWidth=1400,frmHeight=700;
	private int discX=600,discY=0,discWidth=140,discHeight=170;
	private int arrowstX=201,arrowX,arrowY=320,arrowWidth=70,arrowHeight=10;
	private int shooterX=0,shooterY=200,shooterWidth=200,shooterHeight=300;
	private int scoreX=1080,scoreY=80,scoreWidth=100,scoreHeight=100;
	private int scorelX=1050,scorelY=50,scorelWidth=100,scorelHeight=30;
	private int hitsX=1080,hitsY=205,hitsWidth=100,hitsHeight=100;
	private int hitslX=1050,hitslY=175,hitslWidth=100,hitslHeight=30;
	private int missedX=1080,missedY=330,missedWidth=100,missedHeight=100;
	private int missedlX=1050,missedlY=300,missedlWidth=100,missedlHeight=30;
	private int levelX=250,levelY=70,levelWidth=250,levelHeight=50;
	private int white=10,black=20,blue=50,red=75,yellow=100,present=0; 
	private int currArrow=0;
	private int arrowCount=10;
	private int diffDisc=1;
	private int hits=0,miss=0,arrowSpeed=10;
	private float boardSpeed=10;
	static public int score[]=new int[4],level=0;
//-------------------------------------------------------------------------------------------
	void init(){
		arrowSpeed=level*10000;
		boardSpeed=(float)((5-level+1)*1.75);   // to manage the levels after clicking next
		discX=750;
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    ImageIcon ii=new ImageIcon("images/bgimg.png");
	    setContentPane(new JLabel(new ImageIcon(ii.getImage().getScaledInstance(frmWidth, frmHeight, Image.SCALE_SMOOTH))));
	    
	    addWindowListener(new WindowAdapter() {
			@SuppressWarnings("deprecation")
			public void windowClosing(WindowEvent we) {
	    		discThread.stop();
	    		if(arrowThread!=null)
	    			arrowThread.stop();
	    		dispose();
	    	}
	    });
	    setLayout(null);
	    getContentPane().setBackground(Color.CYAN);
	    setResizable(false);
	    
	    icoScore=new ImageIcon("images/scores.png");
	    flabel=new Font(Font.SERIF,Font.BOLD,25);
	    flevel=new Font(Font.SERIF,Font.BOLD,38);

	    lblScorel=new JLabel("SCORE");
	    lblScorel.setBounds(scorelX, scorelY, scorelWidth, scorelHeight);
	    lblScorel.setFont(flabel);
	    add(lblScorel);
	    lblScoreV=new JLabel("   0");
	    lblScoreV.setBounds(scoreX, scoreY, scoreWidth, scoreHeight);
	    lblScoreV.setFont(flabel);
	    lblScoreV.setForeground(Color.BLACK);
	    add(lblScoreV);	    
	    
	    lblHitsl=new JLabel("HITS");
	    lblHitsl.setBounds(hitslX,hitslY,hitslWidth,hitslHeight);
	    lblHitsl.setFont(flabel);
	    add(lblHitsl);
	    lblHitsV=new JLabel("   0");
	    lblHitsV.setBounds(hitsX,hitsY,hitsWidth,hitsHeight);
	    lblHitsV.setFont(flabel);
	    lblHitsV.setForeground(Color.BLACK);
	    add(lblHitsV);
	    
	    lblMissl=new JLabel("MISSED");
	    lblMissl.setBounds(missedlX,missedlY,missedlWidth,missedlHeight);
	    lblMissl.setFont(flabel);
	    add(lblMissl);
	    lblMissV=new JLabel("   0");
	    lblMissV.setBounds(missedX,missedY,missedWidth,missedHeight);
	    lblMissV.setFont(flabel);
	    lblMissV.setForeground(Color.BLACK);
	    add(lblMissV);
	   
	    
	    lbllevel = new JLabel("LEVEL "+level);
	    lbllevel.setBounds(levelX,levelY,levelWidth,levelHeight);
	    lbllevel.setFont(flevel);
		lbllevel.setForeground(Color.blue);
	    add(lbllevel);
	    
	    icoArcher=new ImageIcon("images/archer.png");
	    btnshooter=new JButton(new ImageIcon(icoArcher.getImage().getScaledInstance(shooterWidth, shooterHeight, Image.SCALE_SMOOTH)));
	    btnshooter.setBounds(shooterX,shooterY, shooterWidth,shooterHeight);
        btnshooter.setContentAreaFilled(false);
        btnshooter.setBorderPainted(false);
	    btnshooter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(arrowReached==true && currArrow<arrowCount){
					arrowReached=false;
					arrowThread=new Thread() {
						public void run() {
							moveArrow(lblArrow[currArrow]);
						}
					};
					arrowThread.start();
				}
			}	
	    });
	    add(btnshooter);
	    
	    lblArrowCount = new JLabel(arrowCount+" ");
	    lblArrowCount.setBounds(arrowstX,arrowY+arrowHeight,arrowWidth*2,arrowHeight*3);
	    lblArrowCount.setFont(flabel);
	    add(lblArrowCount);
	    
	    icoArrow=new ImageIcon("images/wood-arrow.png");
	    lblArrow=new JLabel[arrowCount];
	    for(int i=0;i<arrowCount;i++){
	    	lblArrow[i]=new JLabel(new ImageIcon(icoArrow.getImage().getScaledInstance(arrowWidth, arrowHeight, Image.SCALE_SMOOTH)));
	    	lblArrow[i].setBounds(arrowstX,arrowY,arrowWidth,arrowHeight);
	    	add(lblArrow[i]);
	    }
	    
	    lblpresent=new JLabel(" ");
	    lblpresent.setSize(arrowWidth*2,arrowHeight*3);
	    lblpresent.setFont(flabel);
	    lblpresent.setForeground(Color.ORANGE);
	    lblpresent.setVisible(false);
	    add(lblpresent);
	    
	    setSize(frmWidth,frmHeight);
	    setVisible(true);
	    disc();
	}
//----------------------------------------------------------------------------------------------------	
	public BowArrow(int level){
		super("Archer Level-"+level);  // to set the title of the frame 
		this.level=level;
		score[level-1]=0;                                 // so that older score do not get added into score of new level		
		discThread=new Thread() {
			public void run(){
				init();
			}
		};
		discThread.start();
	}
//-----------------------------------------------------------------------------------------------	
	void moveArrow(JLabel lblArrow) {
		int hitPos=0;
		boolean hit=false;
		for(arrowX=arrowstX;arrowX<discX;arrowX++){
			lblArrow.setLocation(arrowX,arrowY);
			try{
				Thread.sleep(0,arrowSpeed);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			revalidate();
		}
		
		//int i=level;
		if((arrowY >= discY && arrowY <= (discY+discHeight)) && ((arrowX+arrowWidth) == (discX+discWidth/2))){
     	    hitPos=arrowY-discY;
			
     	    hit=true;
			hits++;                               
		
			if((hitPos>=0 && hitPos<=17) ||(hitPos>=150 && hitPos<=170)){
				score[BowArrow.level-1]+=white;
	            present=white;
			}
			else if((hitPos>=18 && hitPos<=35) ||(hitPos>=134 && hitPos<=149)){
				score[BowArrow.level-1]+=black;
				present=black;
			}
			else if((hitPos>=36 && hitPos<=52) ||(hitPos>=117 && hitPos<=133)){
				score[BowArrow.level-1]+=blue;
				present=blue;
			}
			else if((hitPos>=53 && hitPos<=68) ||(hitPos>=102 && hitPos<=116)){
				score[BowArrow.level-1]+=red;
				present=red;
			}
			else{
				score[BowArrow.level-1]+=yellow;
				present=yellow;
			}
			freeze(hitPos,lblArrow);
		}
		if(hit==false){
			score[BowArrow.level-1]-=5;
			miss++;
		}
		lblScoreV.setText("  "+score[BowArrow.level-1]);
		lblHitsV.setText("  "+hits);
		lblMissV.setText("  "+miss);
			
	    lblArrow.setVisible(false);
	    lblpresent.setVisible(false);
	    arrowReached=true;
       	currArrow++;
	    lblArrowCount.setText(arrowCount-currArrow+" left");
	    if(currArrow==arrowCount){	
	    	MainMenuOfArrow.readScores();     // to update high score
	    	new FrmStatistics(this,level);
	    	System.out.println("passing to from Statics"+level);
	    }
	}	
//-----------------------------------------------------------------------------------------------	
	void freeze(int check,JLabel lblArrow){
		int i=0;
		while(i<200){
			lblArrow.setLocation(arrowX,discY+check);
			lblpresent.setText("+"+present);
			lblpresent.setLocation(arrowX+arrowWidth/2,discY+check-arrowHeight*2);
			lblpresent.setVisible(true);
			try {
				Thread.sleep(10);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			revalidate();
		}
	}
//-----------------------------------------------------------------------------------------------	
	void disc() {
		icoDisc=new ImageIcon("images/board.png");
		lblDisc=new JLabel(new ImageIcon(icoDisc.getImage().getScaledInstance(discWidth, discHeight , Image.SCALE_SMOOTH)));
		lblDisc.setBounds(discX, discY, discWidth, discHeight);
		add(lblDisc);	
		while(true) {
			lblDisc.setLocation(discX,discY);
			discY+=diffDisc;
			if(discY==frmHeight-discHeight)
				diffDisc=-1;
			if(discY==0)
				diffDisc=1;
			try{
				Thread.sleep((int)boardSpeed);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			revalidate();
		}
	}
}
