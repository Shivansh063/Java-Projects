import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;
import java.io.IOException;
import java.io.*;
import javax.swing.filechooser.*;
import java.text.SimpleDateFormat;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;



class MyFrame extends JFrame implements ActionListener {
  JMenu file, edit, format, view, help;
  JMenuBar mbar;
  JMenuItem cut, copy, save, saveas, print, paste, new1, open, exit;
  JMenuItem wordwrap, fon, status, t1 ,t2, undo, delo, selall, find, gota, datetime,replace;
  JRadioButtonMenuItem m1,m2,m3;
  ButtonGroup bg;
  ImageIcon ii;
  JTextArea ta1;
  UndoManager undo1;
  boolean saveFlag=true, newFlag=true;
  File curFile;
  String curFileName="",curFilePath="";
  JScrollPane jsp;
  Font defaultFnt;

  MyFrame(){
		bg=new ButtonGroup();
		m1=new JRadioButtonMenuItem("Bold");
		m2=new JRadioButtonMenuItem("Italics");
		m3=new JRadioButtonMenuItem("Bold/Italics");
		bg.add(m1);
		bg.add(m2);
		bg.add(m3);
    ii = new ImageIcon("forbidden.jpg");
    setIconImage(ii.getImage());

Font f1 = new Font(Font.SERIF, Font.PLAIN, 20);

undo1= new UndoManager();

    ta1 =new JTextArea();
		new1=new JMenuItem("New");
		new1.setMnemonic(KeyEvent.VK_N);
		open=new JMenuItem("Open...");
		save=new JMenuItem("Save");
    datetime=new JMenuItem("Date/Time");
		exit=new JMenuItem("Exit");
		cut=new JMenuItem("Cut");
    undo=new JMenuItem("Undo");
    delo=new JMenuItem("Delete");
    replace=new JMenuItem("Replace...");
    selall=new JMenuItem("Select All");
    find=new JMenuItem("Find");
    gota=new JMenuItem("Go To...");
    saveas=new JMenuItem("Save As...");
    print=new JMenuItem("Print");
		copy=new JMenuItem("Copy");
    wordwrap=new JMenuItem("Word Wrap");
    fon=new JMenuItem("Font...");
		paste=new JMenuItem("Paste");

    jsp=new JScrollPane(ta1);


    status=new JMenuItem("Status Bar");
    t1=new JMenuItem("About Developer");
    t2=new JMenuItem("About Notepad");

		



		

		file=new JMenu("File");
    format=new JMenu("Format");
    view=new JMenu("View");
    help=new JMenu("Help");
    ta1.setFont(f1);

    add(jsp);






		file.add(new1);
		file.add(open);
		file.add(save);
    file.add(saveas);
		file.addSeparator();
    file.add(print);
		
		file.add(exit);

		edit=new JMenu("Edit");
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
    edit.add(undo);
    edit.addSeparator();
    edit.add(delo);
    edit.add(selall);
    edit.add(replace);
    edit.add(find);
    edit.add(gota);
    edit.addSeparator();
		edit.add(m1);
		edit.add(m2);
		edit.add(m3);
    edit.add(datetime);


    save.setMnemonic(KeyEvent.VK_S);
    open.setMnemonic(KeyEvent.VK_O);
    print.setMnemonic(KeyEvent.VK_P);
    copy.setMnemonic(KeyEvent.VK_C);
    find.setMnemonic(KeyEvent.VK_F);
    gota.setMnemonic(KeyEvent.VK_G);
    selall.setMnemonic(KeyEvent.VK_A);



    save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
    new1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
    paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
    exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
    selall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    gota.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
    undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
    find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
    datetime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
    replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));


   cut.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
       String s1=ta1.getSelectedText();
       StringSelection selection=new StringSelection(s1);
       Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();
      clip.setContents(selection,selection);
      ta1.setText(new StringBuffer(ta1.getText()).delete(ta1.getSelectionStart(),ta1.getSelectionEnd()).toString());
     }   });
     copy.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent ae){
         String s1=ta1.getSelectedText();
         StringSelection selection=new StringSelection(s1);
         Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();
        clip.setContents(selection,selection);
       }   });


       t2.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
           new Notepad(MyFrame.this);
         }
       });

   paste.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
       try{
       Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();
       String s1=ta1.getText();
       ta1.setText(s1.substring(0,ta1.getCaretPosition())+(String)clip.getContents(this).getTransferData(DataFlavor.stringFlavor)+s1.substring(ta1.getCaretPosition(),s1.length()));
     }
     catch(UnsupportedFlavorException e){
       e.printStackTrace();
     }

      catch(IOException e){
        e.printStackTrace();
      }
    }   });
   exit.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
       if(saveFlag=true)
         System.exit(1);
      else
      saveAs();
     }
   });
   save.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
              sAve();
     }
   });
   saveas.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
       saveAs();
     }
   });



  find.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent ae){
        String s=ta1.getText();
        if(s==null){}
        else{
        new DlgFind(MyFrame.this);
    }
  }});



  ta1.getDocument().addUndoableEditListener(new UndoableEditListener(){
                    public void undoableEditHappened(UndoableEditEvent e) {
                        undo1.addEdit(e.getEdit());
                }
});

  undo.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent ae){
      if(newFlag==true && saveFlag==false){
        ta1.setText("");}
      else{
        try {
            undo1.undo();}
         catch (CannotRedoException cre) {
            cre.printStackTrace();
        }}
      }
      });


  new1.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent ae){

     if(saveFlag==true){
         ta1.setText("");
           Font f2=new Font(Font.SERIF,Font.PLAIN,20);
           ta1.setFont(f2);

         saveFlag=true;
         newFlag=true;}
     else{
       int n=JOptionPane.showOptionDialog(MyFrame.this,"Quit", "Notepad",0,JOptionPane.QUESTION_MESSAGE,null,new String[]{"Save","Dont't Save","Cancel"},"Save");
       if(n==0)
        sAve();
      else if(n==1){
        ta1.setText("");
          Font f2=new Font(Font.SERIF,Font.PLAIN,20);
          ta1.setFont(f2);
        newFlag=true;
        saveFlag=true;}
    }}
  });


   m1.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
     Font f1=new Font(Font.SERIF,Font.BOLD,20);
     ta1.setFont(f1);
     }
   });


   m2.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
     Font f1=new Font(Font.SERIF,Font.ITALIC,20);
     ta1.setFont(f1);
     }
   });



   m3.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
     Font f1=new Font(Font.SERIF,Font.BOLD+Font.ITALIC,20);
     ta1.setFont(f1);
     }
   });



    delo.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        ta1.setText(ta1.getText().substring(0,ta1.getSelectionStart())+ta1.getText().substring(ta1.getSelectionEnd(),ta1.getText().length()));

      }
    });

    selall.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        ta1.setSelectionStart(0);
			ta1.setSelectionEnd(ta1.getText().length());}});


      replace.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          new ReplaceBox(MyFrame.this);
        }
      });


   open.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ae){
       JFileChooser jfc=new JFileChooser("C:\\Users\\user\\Desktop");
       FileNameExtensionFilter filter1 =new FileNameExtensionFilter("Text Documents",".txt");
       jfc.addChoosableFileFilter(filter1);
       jfc.setFileFilter(filter1);
       int code=jfc.showOpenDialog(MyFrame.this);
        if(code==JFileChooser.APPROVE_OPTION){
          curFile=jfc.getSelectedFile();
          curFileName=curFile.getName();
			    curFilePath=curFile.getPath();
          try{
            FileInputStream fis=new FileInputStream(curFile);
            int ch;
            ta1.setText("");
            while((ch=fis.read())!=-1)
            {   Font f2=new Font(Font.SERIF,Font.PLAIN,20);
              ta1.setFont(f2);
              ta1.append((char)ch+"");

            }

            fis.close();
          }
          catch(IOException e){
            e.printStackTrace();
          }
          newFlag=false;
          saveFlag=true;
        }
        else if(code == JFileChooser.CANCEL_OPTION)
	        JOptionPane.showMessageDialog(MyFrame.this,"No File Selected");

     }
   });



   setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   addWindowListener(new WindowAdapter(){
     public void windowClosing(WindowEvent we){
       JFrame f=(JFrame)we.getWindow();
       if(saveFlag == false){
         int n=JOptionPane.showOptionDialog(MyFrame.this,"Do you really want to Quit","Save or Cancel Dialog",0,JOptionPane.QUESTION_MESSAGE,null,new String[]{"Save","Don't Save","Cancel"},"Save");
        if(n == 0)
        sAve();
        else if(n==1)
         f.dispose();}
         else
         f.dispose();
     }
   });
   ta1.getDocument().addDocumentListener(new DocumentListener(){
      public void changedUpdate(DocumentEvent e){saveFlag=false;}
    public void removeUpdate(DocumentEvent e){saveFlag=false;}
      public void insertUpdate(DocumentEvent e){saveFlag=false;}
  });



    setLocationRelativeTo(null);

    format.add(wordwrap);
    format.add(fon);


  view.add(status);

  help.add(t1);
  help.addSeparator();
  help.add(t2);

		mbar=new JMenuBar();
		mbar.add(file);
		mbar.add(edit);
    mbar.add(format);
    mbar.add(view);
    mbar.add(help);
    setLocationRelativeTo(null);


		setJMenuBar(mbar);
    setTitle("Untitled-Notepad");
		setSize(500,500);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

}
public static void main(String args[]){
  new MyFrame();
}
void sAve(){
 if(newFlag==true)
    saveAs();
    else{
      try{
        FileOutputStream fos =new FileOutputStream(curFile);
        String s1=ta1.getText();
        fos.write(s1.getBytes());
        fos.close();
        }
   catch(IOException e){
   e.printStackTrace();
    }
    setTitle(curFileName.substring(0,curFileName.lastIndexOf(".")) + " - Notepad");
    newFlag=false;
    saveFlag=true;
}
}
void saveAs()
{		JFileChooser jfc=new JFileChooser("c:\\javaprog");
		FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Text Files", "txt");
		jfc.addChoosableFileFilter(filter1);
		jfc.setFileFilter(filter1);
		int code=jfc.showSaveDialog(this);
		if(code == JFileChooser.APPROVE_OPTION){
			curFile=jfc.getSelectedFile();
			curFileName=curFile.getName();
			curFilePath=curFile.getPath();
			if(curFileName.indexOf(".")==-1){
				curFileName+=".txt";
				curFile=new File(curFilePath,curFileName);
			}
			try{
				FileOutputStream fos=new FileOutputStream(curFile);
				String s1=ta1.getText();
				fos.write(s1.getBytes());
				fos.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			setTitle(curFileName.substring(0,curFileName.lastIndexOf(".")) + " - Notepad");
			newFlag=false;
			saveFlag=true;
		}
	}
  public void actionPerformed(ActionEvent ae){}
}


class DlgFind extends JDialog implements ActionListener{
  JLabel lbl;
  JButton bu1,bu2;
  JTextField tf1;
  JCheckBox chkMatchCase;
  JRadioButton rdoUp,rdoDown;
	ButtonGroup bg;
	JPanel panRadio;
  boolean flag=false;
  DlgFind(MyFrame myframe){
    super(myframe, "Find",false);
    setLayout(null);
    Font f= new Font(Font.SERIF,Font.BOLD,20);
    lbl=new JLabel("Find What");
    lbl.setBounds(5,10,100,50);
    lbl.setFont(f);
    add(lbl);
    bu1=new JButton("Find Next");
    bu1.setBounds(320,10,100,50);
    add(bu1);
    bu2=new JButton("Cancel");
    bu2.setBounds(320,80,100,50);
    add(bu2);
    tf1=new JTextField();
    tf1.setBounds(130,15,170,30);
    add(tf1);
    chkMatchCase=new JCheckBox("Match Case");
    chkMatchCase.setBounds(20,150,100,70);
    add(chkMatchCase);
    rdoUp=new JRadioButton("Up");
		rdoDown=new JRadioButton("Down",true);
		bg=new ButtonGroup();
		bg.add(rdoUp);
		bg.add(rdoDown);
    panRadio=new JPanel();
		panRadio.setBorder(BorderFactory.createTitledBorder("Direction"));
		panRadio.add(rdoUp);
		panRadio.add(rdoDown);
		panRadio.setLayout(new FlowLayout(FlowLayout.LEFT));
		panRadio.setBounds(170, 80, 100, 80);
		add(panRadio);

    setVisible(true);



    bu1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String strFindWhat=tf1.getText();
				boolean matchCase=chkMatchCase.isSelected();
				int dir=1;
				if(rdoUp.isSelected())
					dir=-1;
				else if(rdoDown.isSelected())
					dir=1;
				int curPos=myframe.ta1.getCaretPosition();
				String strFindIn=myframe.ta1.getText();
				int pos;
				if(matchCase==true){
					if(dir==1)
						pos=strFindIn.indexOf(strFindWhat,curPos);
					else{
						if(flag==false){
							flag=true;
						}
						else
							curPos-=strFindWhat.length();
						pos=strFindIn.lastIndexOf(strFindWhat,curPos);
					}
				}
				else{
					if(dir==1)
						pos=strFindIn.toUpperCase().indexOf(strFindWhat.toUpperCase(),curPos);
					else
						pos=strFindIn.toUpperCase().lastIndexOf(strFindWhat.toUpperCase(),curPos);
				}
				if(pos==-1){
					JOptionPane.showMessageDialog(DlgFind.this, "Text Not Found");
				}
				else{
					myframe.ta1.setSelectionStart(pos);
					myframe.ta1.setSelectionEnd(pos+strFindWhat.length());
					myframe.ta1.requestFocus();
				}
			}
		});



		bu2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
			}
		});

		setSize(500,250);
		setLocationRelativeTo(null);
		setVisible(true);
}
  public void actionPerformed(ActionEvent ae){}
}

class ReplaceBox extends JDialog{
  JLabel lbl,lbl2;
  JButton bu1,bu2,bu3,bu4;
  JTextField tf1,tf2;
  JCheckBox chkMatchCase;
  JRadioButton rdoUp,rdoDown;
	ButtonGroup bg;
	JPanel panRadio;
  boolean flag=false;
  ReplaceBox(MyFrame myframe){
    super(myframe, "Replace",false);
    setLayout(null);
    Font f= new Font(Font.SERIF,Font.BOLD,15);
    lbl=new JLabel("Find What");
    lbl.setBounds(5,10,100,50);
    lbl.setFont(f);
    add(lbl);
    lbl2=new JLabel("Replace With");
    lbl2.setBounds(5,40,100,50);
    lbl2.setFont(f);
    add(lbl2);
    bu1=new JButton("Find Next");
    bu1.setBounds(320,10,100,30);
    add(bu1);
    bu2=new JButton("Replace");
    bu2.setBounds(320,45,100,30);
    add(bu2);
    bu3=new JButton("Replace All");
    bu3.setBounds(320,80,100,30);
    add(bu3);
    bu4=new JButton("Cancel");
    bu4.setBounds(320,115,100,30);
    add(bu4);
    tf1=new JTextField();
    tf1.setBounds(130,15,170,30);
    add(tf1);
    tf2=new JTextField();
    tf2.setBounds(130,50,170,30);
    add(tf2);
    chkMatchCase=new JCheckBox("Match Case");
    chkMatchCase.setBounds(20,150,100,70);
    add(chkMatchCase);
    rdoUp=new JRadioButton("Up");
   rdoDown=new JRadioButton("Down",true);
   bg=new ButtonGroup();
   bg.add(rdoUp);
   bg.add(rdoDown);
   panRadio=new JPanel();
   panRadio.setBorder(BorderFactory.createTitledBorder("Direction"));
   panRadio.add(rdoUp);
   panRadio.add(rdoDown);
   panRadio.setLayout(new FlowLayout(FlowLayout.LEFT));
   panRadio.setBounds(170, 120, 100, 80);
   add(panRadio);




    setSize(500,250);
		setLocationRelativeTo(null);
    setVisible(true);





    bu1.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent ae){
  				find(myframe);
  			}});




  		bu2.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent ae){
  				String with=tf2.getText();
  				int pos=myframe.ta1.getCaretPosition();
  				int len=myframe.ta1.getSelectedText().length();
  				String mainString=myframe.ta1.getText();
  //				System.out.println(mainString.substring(0,pos));
  	//			System.out.println(with);
  		//		System.out.println(mainString.substring(pos+len,mainString.length()));
  				myframe.ta1.setText(mainString.substring(0,pos-len)+with+mainString.substring(pos,mainString.length()));
  				myframe.ta1.setCaretPosition(pos-len+with.length());
  				find(myframe);
  			}
  		});


  		bu3.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent ae){
  				String what=tf1.getText();
  				String with=tf2.getText();
  				String mainString=myframe.ta1.getText();
  				myframe.ta1.setText(mainString.replaceAll(what, with));
  			}
  		});


  		bu4.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent ae){
  				dispose();
  			}
  		});

  	}
  	void find(MyFrame myframe){
  		String strFindWhat=tf1.getText();
  		boolean matchCase=chkMatchCase.isSelected();
  		int dir=1;
  		if(rdoUp.isSelected())
  			dir=-1;
  		else if(rdoDown.isSelected())
  			dir=1;
  		int curPos=myframe.ta1.getCaretPosition();
  		String strFindIn=myframe.ta1.getText();
  		int pos;
  		if(matchCase==true){
  			if(dir==1)
  				pos=strFindIn.indexOf(strFindWhat,curPos);
  			else{
  				if(flag==false){
  					flag=true;
  				}
  				else
  					curPos-=strFindWhat.length();
  				pos=strFindIn.lastIndexOf(strFindWhat,curPos);
  			}
  		}
  		else{
  			if(dir==1)
  				pos=strFindIn.toUpperCase().indexOf(strFindWhat.toUpperCase(),curPos);
  			else
  				pos=strFindIn.toUpperCase().lastIndexOf(strFindWhat.toUpperCase(),curPos);
  		}
  		if(pos==-1){
  			JOptionPane.showMessageDialog(ReplaceBox.this, "Text Not Found");
  		}
  		else{
  			myframe.ta1.setSelectionStart(pos);
  			myframe.ta1.setSelectionEnd(pos+strFindWhat.length());
  		  myframe.ta1.requestFocus();
  		}
  	}
  }


class Notepad extends JDialog{

    JPanel p1,p2,p3;
    JButton bu1;
    JTextArea ta1;
    JTextField tf1;
    JLabel lb1,lb2;
    ImageIcon ii;


  Notepad(MyFrame myframe){
     super(myframe,"About Notepad",true);
     setLayout(null);
       p1 = new JPanel();
       ta1 = new JTextArea("Notepad is a common text-only (plain text) editor. <br> Created by Shivansh Gupta. <br> This Notpad is one of my java project similar to actual Notepad but different visuals.");
       p1.setBounds(30,25,400,100);
       ta1.setBounds(25,200,400,250);
       ImageIcon ii = new ImageIcon("cute-ball-windows-icon-png-16.png");
       lb1=new JLabel("NOTEPAD", lb1.CENTER);

       Font f1 = new Font(Font.SERIF,Font.BOLD,40);
       lb1.setBounds(200,50,80,50);
       lb1.setForeground(Color.blue);
       lb2 = new JLabel(ii);
       Color clr1 = new Color(211,211,211);
       Color clr2 = new Color(255,255,255);
       p1.setBackground(clr2);
       ta1.setBackground(clr2);
       lb2.setForeground(clr1);
       lb2.setBounds(30,50,80,150);
       lb1.setFont(f1);
          p1.add(lb2); p1.add(lb1);

       add(p1);
       add(ta1);
       bu1=new JButton("OK");
       add(bu1); 
       bu1.setBounds(370,400,100,25);
       bu1.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
           dispose();
         }
       });
       setSize(500,500);
       setLocationRelativeTo(null);
       setVisible(true);
    }
  }
