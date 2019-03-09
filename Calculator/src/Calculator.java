 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
public class Calculator extends JFrame implements ActionListener{
	JPanel panTop,panMemory,panBottom;
	JLabel lblResult,lblOldResult;
	String strMemory[]={"MC","MR","M+","M-","MS"};
	ImageIcon ii;
	JButton btnMemory[]=new JButton[5];
	String strNames[]={"%","sqrt","sqr","1/x","CE","C","back","/","7","8","9","*","4","5","6","-","1","2","3","+","Sign","0",".","="};
	JButton btnArray[]=new JButton[24];
	
	boolean flag=false,flagMemory=false;
	double val1=0,memory=0;
	String op="";
	Calculator(){
		//Panel Top
		Border emptyBorder=BorderFactory.createEmptyBorder();
		lblResult=new JLabel("0");
		lblResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResult.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
		lblOldResult=new JLabel("");
		lblOldResult.setHorizontalAlignment(SwingConstants.RIGHT);
		panMemory=new JPanel();
		panMemory.setLayout(new GridLayout(1,5,10,10));
		for(int i=0;i<btnMemory.length;i++){
			btnMemory[i]=new JButton(strMemory[i]);
			btnMemory[i].setBorder(emptyBorder);
			btnMemory[i].setBackground(new Color(240,240,240));
			btnMemory[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					String s1=ae.getActionCommand();
					String str=lblResult.getText();
					double d=Double.parseDouble(str);
					if(s1.equalsIgnoreCase("mc")){
						memory=0;
						flagMemory=false;
					}
					else if(s1.equalsIgnoreCase("mr")){
						lblResult.setText(val(memory+""));
					}
					else if(s1.equalsIgnoreCase("m+")){
						memory+=d;
						flagMemory=true;
					}
					else if(s1.equalsIgnoreCase("m-")){
						memory-=d;
						flagMemory=true;
					}
					else if(s1.equalsIgnoreCase("ms")){
						memory=d;
						flagMemory=true;
					}
					if(flagMemory==false){
						btnMemory[0].setEnabled(false);
						btnMemory[1].setEnabled(false);
					}
					else{
						btnMemory[0].setEnabled(true);
						btnMemory[1].setEnabled(true);
					}
				}
			});
			
			btnMemory[i].addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent me){
					JButton b1=(JButton)me.getSource();
					b1.setBackground(Color.LIGHT_GRAY);
				}
				public void mouseExited(MouseEvent me){
					JButton b1=(JButton)me.getSource();
					b1.setBackground(new Color(240,240,240));
				}
			});
			panMemory.add(btnMemory[i]);			
		}
		btnMemory[0].setEnabled(false);
		btnMemory[1].setEnabled(false);
		panTop=new JPanel();
		panTop.setLayout(new GridLayout(3,1,10,10));
		panTop.add(lblOldResult);
		panTop.add(lblResult);
		panTop.add(panMemory);
		add(panTop,"North");
		
		//Panel Bottom
		panBottom=new JPanel();
		panBottom.setLayout(new GridLayout(6,4,10,10));
		for(int i=0;i<btnArray.length;i++){
			btnArray[i]=new JButton(strNames[i]);
			btnArray[i].setBorder(emptyBorder);
			btnArray[i].addActionListener(this);
			if(Character.isDigit(strNames[i].charAt(0)) && !strNames[i].equals("1/x")){
				btnArray[i].setBackground(Color.WHITE);
				btnArray[i].addMouseListener(new MouseAdapter(){
					public void mouseEntered(MouseEvent me){
						JButton b1=(JButton)me.getSource();
						b1.setBackground(Color.LIGHT_GRAY);
					}
					public void mouseExited(MouseEvent me){
						JButton b1=(JButton)me.getSource();
						b1.setBackground(Color.WHITE);
					}
				});
			}
			else{
				btnArray[i].setBackground(Color.LIGHT_GRAY);
				btnArray[i].addMouseListener(new MouseAdapter(){
					public void mouseEntered(MouseEvent me){
						JButton b1=(JButton)me.getSource();
						b1.setBackground(new Color(153,204,255));
					}
					public void mouseExited(MouseEvent me){
						JButton b1=(JButton)me.getSource();
						b1.setBackground(Color.LIGHT_GRAY);
					}
				});
			}
			panBottom.add(btnArray[i]);
		}
		ii = new ImageIcon("C:/Users/user/Desktop/Practice/Calculator/image/calc.jpg");
		setIconImage(ii.getImage());
		add(panBottom);
		setLocation(200,200);
		setSize(400,500);
		setTitle("Calculator");
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Calculator();
	}
	public void actionPerformed(ActionEvent ae){
		String s1=((JButton)ae.getSource()).getLabel();
		if(Character.isDigit(s1.charAt(0)) && !s1.equals("1/x")){
			if(flag==false)
				lblResult.setText(val(lblResult.getText()+s1.charAt(0)));//concat
			else{
				lblResult.setText(val(s1));//overwrite
				flag=false;
			}
		}
		else if(s1.equals("+") || s1.equals("-") || s1.equals("*") || s1.equals("/")){
			lblOldResult.setText(lblOldResult.getText()+lblResult.getText()+s1);
			if(op!= null && !op.isEmpty()){
				double val2=Double.parseDouble(lblResult.getText());
				switch(op){
					case "+":
						val1=val1+val2;
						break;
					case "-":
						val1=val1-val2;
						break;
					case "*":
						val1=val1*val2;
						break;
					case "/":
						val1=val1/val2;
						break;
				}
				lblResult.setText(val(val1+""));
			}
			val1=Double.parseDouble(lblResult.getText());
			op=s1;
			flag=true;
		}
		else if(s1.equals("=")){
			double val2=Double.parseDouble(lblResult.getText());
			switch(op){
				case "+":
					val1=val1+val2;
					break;
				case "-":
					val1=val1-val2;
					break;
				case "*":
					val1=val1*val2;
					break;
				case "/":
					val1=val1/val2;
					break;
			}
			lblResult.setText(val(val1+""));
			lblOldResult.setText("");
			val1=0;
			op="";
			flag=true;
		}
		else if(s1.equals("CE")){
			lblResult.setText("0");
			flag=true;
		}
		else if(s1.equals("C")){
			lblResult.setText("0");
			lblOldResult.setText("");
			op="";
			val1=0;
			flag=true;
		}	
		else if(s1.equalsIgnoreCase("back")){
			String str=lblResult.getText();
			str=str.substring(0,str.length()-1);
			lblResult.setText(val(str));
			flag=false;
		}	
		else if(s1.equalsIgnoreCase("1/x")){
			String str=lblResult.getText();
			if(str!=null && !str.isEmpty())
			{
				if(str.equals("0"))
					lblResult.setText("can't divide by zero");
				else{
					lblResult.setText(val(1/Double.parseDouble(str)+""));
					lblOldResult.setText("1/("+str+")");
				}	
			}
			flag=true;
		}
		else if(s1.equalsIgnoreCase("sqrt")){
			try{
				String str=lblResult.getText();
				if(Double.parseDouble(str)>=0){
					lblResult.setText(val(Math.sqrt(Double.parseDouble(str))+""));
					lblOldResult.setText("sqrt("+val(str)+")");
				}
				else
					lblResult.setText("Invalid Input");
			}
			catch(NumberFormatException e){
				lblResult.setText("0");
			}
			flag=true;
		}
		else if(s1.equalsIgnoreCase("sqr")){
			String str=lblResult.getText();
			double d=Double.parseDouble(str);
			lblResult.setText(val(d*d+""));
			lblOldResult.setText("sqr("+val(d+"")+")");
			flag=true;
		}
		else if(s1.equalsIgnoreCase("sign")){
			String str=lblResult.getText();
			if(str!=null && !str.isEmpty() && !str.equals("0")){
				lblResult.setText(val(Double.parseDouble(str)*-1+""));
			}
		}
		else if(s1.equalsIgnoreCase(".")){
			if(flag==false)
				lblResult.setText(lblResult.getText()+".");
			else{
				lblResult.setText("0.");
				flag=false;
			}
		}
		else if(s1.equalsIgnoreCase("%")){
			String str=lblResult.getText();
			double per=Double.parseDouble(str);
			lblResult.setText(val(val1*per/100+""));
		}
	}
	String val(String s1){
		if(s1!=null && !s1.isEmpty()){
			double d=Double.parseDouble(s1);
			if((int)d==d)
				return ((int)d)+"";
			else
				return d+"";
		}
		else
			return "0";
	}
}
//flag=true overlap
//falg=false concat