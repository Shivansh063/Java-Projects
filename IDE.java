import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.util.*;
public class IDE extends JFrame implements ActionListener {
	JMenuBar mbar;
	JMenu mnuFile,mnuBuild,mnuWindow;
	JMenuItem mitNew,mitOpen,mitSave,mitSaveAs,mnuExit;
	JMenuItem mitCompile,mitRun;
	JMenuItem mitCloseCurrentTab,mitCloseAllTabs;
	JTabbedPane jtp;
	JScrollPane jspError;
	JPanel panBottom;
	JTextArea jtaError;
	JSplitPane panev;
	int index=0;
	Font defaultFnt;
	JToolBar jtb;
	JButton btnCompile,btnRun;
	Vector<FileDetails> vecFileList=new Vector<>();
	IDE(){
		setTitle("My Java IDE");
		
		btnCompile=new JButton("Compile");
		btnCompile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				compile();
			}
		});
		btnRun=new JButton("Run");
		btnRun.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				run();
			}
		});
		
		jtb=new JToolBar();
		jtb.add(btnCompile);
		jtb.add(btnRun);
		add(jtb,"North");
		
		mitNew=new JMenuItem("New");
		mitOpen=new JMenuItem("Open");
		mitSave=new JMenuItem("Save");
		mitSaveAs=new JMenuItem("Save As");
		mnuExit=new JMenuItem("Exit");
		mitCompile=new JMenuItem("Compile");
		mitRun=new JMenuItem("Run");
		mitCloseCurrentTab=new JMenuItem("Close Current Tab");
		mitCloseAllTabs=new JMenuItem("Close All Tabs");
		
		mitNew.addActionListener(this);
		mitOpen.addActionListener(this);
		mitSave.addActionListener(this);
		mitSaveAs.addActionListener(this);
		mnuExit.addActionListener(this);
		mitCompile.addActionListener(this);
		mitRun.addActionListener(this);
		mitCloseCurrentTab.addActionListener(this);
		mitCloseAllTabs.addActionListener(this);
		
		mnuFile=new JMenu("File");
		mnuFile.add(mitNew);
		mnuFile.add(mitOpen);
		mnuFile.add(mitSave);
		mnuFile.add(mitSaveAs);
		mnuFile.add(mnuExit);
		
		mnuBuild=new JMenu("Build");
		mnuBuild.add(mitCompile);
		mnuBuild.add(mitRun);
		
		mnuWindow=new JMenu("Window");
		mnuWindow.add(mitCloseCurrentTab);
		mnuWindow.add(mitCloseAllTabs);
		mnuWindow.setVisible(false);
		
		mbar=new JMenuBar();
		mbar.add(mnuFile);
		mbar.add(mnuBuild);
		mbar.add(mnuWindow);
		
		jtp=new JTabbedPane();
		
		defaultFnt=new Font(Font.SANS_SERIF,Font.PLAIN,20);
		jtaError=new JTextArea();
		jtaError.setFont(defaultFnt);
		jspError=new JScrollPane(jtaError);
		panBottom=new JPanel();
		panBottom.setLayout(new GridLayout(1,1));
		panBottom.add(jspError);
		
		panev=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panev.setTopComponent(jtp);
		panev.setBottomComponent(panBottom);
		panev.setDividerLocation(500);
		
		add(panev);
		setJMenuBar(mbar);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String s1=ae.getActionCommand();
		if(s1.equalsIgnoreCase("new")){
			JFileChooser jfc=new JFileChooser("c:/javaprog");
	       	FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Java Files", "java");
			jfc.addChoosableFileFilter(filter1);
			jfc.setFileFilter(filter1);
			jfc.showSaveDialog(this);			
			File currentFile=convertFileName(jfc.getSelectedFile());
			JTextArea currentJta=new JTextArea();
			currentJta.setFont(defaultFnt);
			
			JTextArea lineJta=new JTextArea("1");
			currentJta.getDocument().addDocumentListener(new DocumentListener(){
				@Override
				public void changedUpdate(DocumentEvent arg0) {
					lineJta.setText(lineNumbers(currentJta));
				}
				@Override
				public void insertUpdate(DocumentEvent arg0) {
					lineJta.setText(lineNumbers(currentJta));
				}
				@Override
				public void removeUpdate(DocumentEvent arg0) {
					lineJta.setText(lineNumbers(currentJta));
				}
			});
			lineJta.setFont(defaultFnt);
			lineJta.setBackground(Color.LIGHT_GRAY);
			lineJta.setForeground(Color.blue);
			Border b1=BorderFactory.createLineBorder(Color.BLACK);
			lineJta.setBorder(b1);
			
			JScrollPane currentJsp=new JScrollPane(currentJta);
			currentJsp.setRowHeaderView(lineJta);
			
			JPanel currentPan=new JPanel();
			currentPan.setLayout(new GridLayout(1,1));
			currentPan.add(currentJsp);
			jtp.addTab(currentFile.getName(),currentPan);
			vecFileList.addElement(new FileDetails(currentFile,index++,false,currentJta));
			currentJta.requestFocus();
			mnuWindow.setVisible(true);
		}
		else if(s1.equalsIgnoreCase("open")){
			JFileChooser jfc=new JFileChooser("c:/javaprog");
	       	FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Java Files", "java");
			jfc.addChoosableFileFilter(filter1);
			jfc.setFileFilter(filter1);
			jfc.showOpenDialog(this);			
			File currentFile=convertFileName(jfc.getSelectedFile());
			JTextArea currentJta=new JTextArea();
			currentJta.setFont(defaultFnt);
			JPanel currentPan=new JPanel();
			currentPan.setLayout(new GridLayout(1,1));
			JScrollPane currentJsp;
			try{
				FileInputStream fis=new FileInputStream(currentFile);
				byte b[]=new byte[(int)currentFile.length()];
				fis.read(b);
				currentJta.setText(new String(b));
				fis.close();
				currentJsp=new JScrollPane(currentJta);
				
				JTextArea lineJta=new JTextArea("1");
				currentJta.getDocument().addDocumentListener(new DocumentListener(){
					@Override
					public void changedUpdate(DocumentEvent arg0) {
						lineJta.setText(lineNumbers(currentJta));
					}
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						lineJta.setText(lineNumbers(currentJta));
					}
					@Override
					public void removeUpdate(DocumentEvent arg0) {
						lineJta.setText(lineNumbers(currentJta));
					}
				});
				lineJta.setFont(defaultFnt);
				lineJta.setBackground(Color.LIGHT_GRAY);
				lineJta.setForeground(Color.blue);
				Border b1=BorderFactory.createLineBorder(Color.BLACK);
				lineJta.setBorder(b1);
				lineJta.setText(lineNumbers(currentJta));
				currentJsp.setRowHeaderView(lineJta);
				
				
				currentPan.add(currentJsp);
				jtp.addTab(currentFile.getName(),currentPan);
				vecFileList.addElement(new FileDetails(currentFile,index++,false,currentJta));
				jtp.setSelectedIndex(index-1);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			mnuWindow.setVisible(true);
		}
		else if(s1.equalsIgnoreCase("save")){
			int currentTab=jtp.getSelectedIndex();
			File f=null;
			JTextArea jt=null;
			for(int i=0;i<vecFileList.size();i++){
				if(vecFileList.get(i).tabIndex==currentTab){
					f=vecFileList.get(i).name;
					jt=vecFileList.get(i).jta;
				}
			}
			try{
				FileOutputStream fos=new FileOutputStream(f);
				fos.write(jt.getText().getBytes());
				fos.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		else if(s1.equalsIgnoreCase("save as")){
			int currentTab=jtp.getSelectedIndex();
			File f=null;
			JTextArea jt=null;
			for(int i=0;i<vecFileList.size();i++){
				if(vecFileList.get(i).tabIndex==currentTab){
					f=vecFileList.get(i).name;
					jt=vecFileList.get(i).jta;
				}
			}
			JFileChooser jfc=new JFileChooser("c:/javaprog");
	       	FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Java Files", "java");
			jfc.addChoosableFileFilter(filter1);
			jfc.setFileFilter(filter1);
			jfc.showSaveDialog(this);			
			File newFile=convertFileName(jfc.getSelectedFile());
			try{
				FileOutputStream fos=new FileOutputStream(newFile);
				fos.write(jt.getText().getBytes());
				fos.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			JTextArea newJta=new JTextArea();
			newJta.setFont(defaultFnt);
			newJta.setText(jt.getText());
			JScrollPane newJsp=new JScrollPane(newJta);
			
			JTextArea lineJta=new JTextArea("1");
			newJta.getDocument().addDocumentListener(new DocumentListener(){
				@Override
				public void changedUpdate(DocumentEvent arg0) {
					lineJta.setText(lineNumbers(newJta));
				}
				@Override
				public void insertUpdate(DocumentEvent arg0) {
					lineJta.setText(lineNumbers(newJta));
				}
				@Override
				public void removeUpdate(DocumentEvent arg0) {
					lineJta.setText(lineNumbers(newJta));
				}
			});
			lineJta.setFont(defaultFnt);
			lineJta.setBackground(Color.LIGHT_GRAY);
			lineJta.setForeground(Color.blue);
			Border b1=BorderFactory.createLineBorder(Color.BLACK);
			lineJta.setBorder(b1);
			lineJta.setText(lineNumbers(newJta));
			JScrollPane currentJsp=new JScrollPane(newJta);
			currentJsp.setRowHeaderView(lineJta);
			
			JPanel newPan=new JPanel();
			newPan.setLayout(new GridLayout(1,1));
			newPan.add(newJsp);
			jtp.addTab(newFile.getName(),newPan);
			vecFileList.addElement(new FileDetails(newFile,index++,false,newJta));
			jtp.setSelectedIndex(index-1);
		}
		else if(s1.equalsIgnoreCase("exit")){
			System.exit(0);
		}
		else if(s1.equalsIgnoreCase("compile")){
			compile();
		}
		else if(s1.equalsIgnoreCase("run")){
			run();
		}
		else if(s1.equalsIgnoreCase("close current tab")){
			if(jtp.getTabCount()!=0){
				int idx=jtp.getSelectedIndex();
				for(int i=idx+1;i<vecFileList.size();i++){
					vecFileList.get(i).tabIndex--;
				}
				vecFileList.removeElementAt(idx);
				jtp.remove(idx);
				index--;
			}
			if(jtp.getTabCount()==0)
				mnuWindow.setVisible(false);
		}
		else if(s1.equalsIgnoreCase("close all tabs")){
			jtp.removeAll();
			vecFileList=new Vector<>();
			index=0;
			mnuWindow.setVisible(false);
		}
	}
	String lineNumbers(JTextArea jta){
		String s1="1";
		int n=jta.getLineCount();
		for(int i=2;i<=n;i++){
			s1+="\n"+i;
		}
		return s1;
	}
	File convertFileName(File f){
		String fn=f.getName();
		String fp=f.getParent();
		if(!fn.endsWith(".java")){
			fn=fn+".java";
		}
		f=new File(fp,fn);
		return f;
	}
	void compile(){
		int idx=0;
		try{
			idx=jtp.getSelectedIndex();
			String ss=vecFileList.get(idx).name.getAbsolutePath();
			Runtime rt=Runtime.getRuntime();
			Process p=rt.exec("cmd /c javac " + ss);
			InputStream is=p.getErrorStream();
			int n;
			n=is.read();
			if(n==-1){
				jtaError.setText("Compile Sucessfull");
				vecFileList.get(idx).compileFlag=true;
			}
			else{
				jtaError.setText("");
				while(n!=-1){
					jtaError.append((char)n+"");
					n=is.read();
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	void run(){
		int idx=0;
		try{
			idx=jtp.getSelectedIndex();
			if(vecFileList.get(idx).compileFlag==true){
				String ss=vecFileList.get(idx).name.getName();
				ss=ss.substring(0,ss.length()-5);
				Runtime rt=Runtime.getRuntime();
				rt.exec("cmd /c start cmd /k java " + ss,null,new File(vecFileList.get(idx).name.getParent()));
			}
			else{
				JOptionPane.showMessageDialog(this, "File Not Compiled");
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		new IDE();
	}
}
class FileDetails{
	File name;
	int tabIndex;
	JTextArea jta;
	boolean compileFlag;
	FileDetails(File name,int tabIndex,boolean compileFlag,JTextArea jta){
		this.name=name;
		this.tabIndex=tabIndex;
		this.compileFlag=compileFlag;
		this.jta=jta;
	}
}
