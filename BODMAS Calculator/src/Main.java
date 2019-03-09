import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class Main extends Application {
    TextField txtinput;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    Button btnadd,btnsub,btnmul,btndiv,btnclr,btneql,btnbrackets,btnbodmastoggle;
    Label lbldisp;
    String txtfld,anss="";
	
    int brackettoggle=2,BODMAStoggle=2;
    @Override
    public void start(Stage primaryStage){
        txtinput=new TextField();
        btn1=new Button("1");
        btn2=new Button("2");
        btn3=new Button("3");
        btn4=new Button("4");
        btn5=new Button("5");
        btn6=new Button("6");
        btn7=new Button("7");
        btn8=new Button("8");
        btn9=new Button("9");
        btn0=new Button("0");
        btnadd=new Button("+");
        btnsub=new Button("-");
        btnmul=new Button("x");
        btndiv=new Button("/");
        btneql=new Button("=");
        btnclr=new Button("CLEAR");
        btnbrackets=new Button(" )  ( ");
        btnbodmastoggle=new Button("Toggle BODMAS");
        lbldisp= new Label();

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(20);
		lbldisp.setAlignment(Pos.CENTER);
        lbldisp.setStyle("-fx-border-color: #000; -fx-padding: 5px; -fx-font-size: 18px ;");
        txtinput.setStyle(" -fx-font-size: 16px");
        btneql.setStyle("-fx-font-size : 30px");
        //lbldisp.setStyle("-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
        //txtinput.setStyle("-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");

        root.add(txtinput,0,0,4,1);
        root.add(lbldisp,0,8,4,1);
        root.add(btn1,0,2);
        root.add(btn2,1,2);
        root.add(btn3,2,2);
        root.add(btnadd,3,2);
        root.add(btn4,0,3);
        root.add(btn5,1,3);
        root.add(btn6,2,3);
        root.add(btnsub,3,3);
        root.add(btn7,0,4);
        root.add(btn8,1,4);
        root.add(btn9,2,4);
        root.add(btnclr,3,4);
        root.add(btn0,0,5,1,2);
        root.add(btnmul,1,5);
        root.add(btndiv,1,6);
        root.add(btneql,2,5,2,2);
        root.add(btnbrackets,0,7,2,1);
        root.add(btnbodmastoggle,2,7,2,1);


        //set widths of all controls in separate method
        setWidths();
        attachCode();
		
		

        //usual stuff
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setWidths(){
        //set widths of all controls
        btn0.setPrefWidth(70);
        btn1.setPrefWidth(70);
        btn2.setPrefWidth(70);
        btn3.setPrefWidth(70);
        btn4.setPrefWidth(70);
        btn5.setPrefWidth(70);
        btn6.setPrefWidth(70);
        btn7.setPrefWidth(70);
        btn8.setPrefWidth(70);
        btn9.setPrefWidth(70);
        btnadd.setPrefWidth(70);
        btnsub.setPrefWidth(70);
        btnmul.setPrefWidth(70);
        btndiv.setPrefWidth(70);
        btnclr.setPrefWidth(70);
        txtinput.setPrefWidth(1000);
        btneql.setPrefWidth(160);
        lbldisp.setPrefWidth(1000);
        btnbodmastoggle.setPrefWidth(160);
        btnbrackets.setPrefWidth(160);

        btneql.setPrefHeight(70);
        btn0.setPrefHeight(70);
        txtinput.setPrefHeight(60);
        lbldisp.setPrefHeight(60);
		
		
		
		 
    }
    public void attachCode()
    {
        //have each button run BTNCODE when clicked
        btnadd.setOnAction(this::btncode);
        btnsub.setOnAction(this::btncode);
        btnmul.setOnAction(this::btncode);
        btndiv.setOnAction(this::btncode);
        btnclr.setOnAction(this::btncode);
        btn0.setOnAction(this::btncode);
        btn1.setOnAction(this::btncode);
        btn2.setOnAction(this::btncode);
        btn3.setOnAction(this::btncode);
        btn4.setOnAction(this::btncode);
        btn5.setOnAction(this::btncode);
        btn6.setOnAction(this::btncode);
        btn7.setOnAction(this::btncode);
        btn8.setOnAction(this::btncode);
        btn9.setOnAction(this::btncode);
        btneql.setOnAction(this::btncode);
        btnbodmastoggle.setOnAction(this::btncode);
        btnbrackets.setOnAction(this::btncode);

    }
    public static char[] deleteArraychar(char c,char a[],int u){
        char b[]=new char [25];
        int j=0;
        for(int i=0;i<=u;i++){
            if(a[i]!=c){
                b[j]=a[i];
                j=j+1;
            }
        }
        return b;
    }
    public static double[] deleteArraydouble(double c,double a[],int u){
        double b[]=new double [25];
        int j=0;
        for(int i=0;i<=u;i++){
            if(a[i]!=c){
                b[j]=a[i];
                j=j+1;
            }
        }
        return b;
    }
    public static double CalculatorMain (String input){
        String buffer;
        double numbuffer;
        int u=0,l;
        char buf,check;
        input=input.trim();
        l=input.length();
        if(input.charAt(0)=='(' && input.charAt(l-1)==')')
            input=input.substring(1,l-1);
        input=input+" ";
        l=input.length();
        String buffRecursive;
        double num[]=new double[25];
        char func[]=new char[25];
        for(int i=0;i<l;i++){
            char chk=input.charAt(i);
            if(!Character.isDigit(chk)){
                if(chk=='('){
                    for(int j=i+1;i<l;j++){
                        char chk0=input.charAt(j);
                        if (chk0==')'){
                            buffRecursive=input.substring(i+1,j);
                            num[u] =CalculatorMain(buffRecursive);
                            check=input.charAt(j+1);
                            if(check!=' ') {
                                func[u]=input.charAt(j+1);
                                input=input.substring(j+2);
                                u = u + 1;
                                l = input.length();
                                i = 0;
                            }
                            else{
                                u = u + 1;
                                l = 0;
                                i = 0;
                            }
                            break;
                        }
                    }
                }
                else if(chk!='.') {
                    if(chk!=' ') {
                        check=input.charAt(0);
                        if (!Character.isDigit(check)){//check == '(') {
                            buffer = input.substring(1, i);
                        }
                        else {
                            buffer = input.substring(0, i);
                        }
                        numbuffer = Double.parseDouble(buffer);
                        num[u] = numbuffer;
                        buf = input.charAt(i);
                        func[u] = buf;
                        input = input.substring(i + 1);
                        u = u + 1;
                        l = input.length();
                        i = 0;
                    }
                }
            }
        }
        if (Character.isDigit(input.charAt(0))) {
            input = input.trim();
            numbuffer = Double.parseDouble(input);
            num[u] = numbuffer;
        }
        else{
            input = input.trim();
            input=input.substring(1);
            numbuffer = Double.parseDouble(input);
            num[u] = numbuffer;
        }
        for(int i=0;i<=u;i++){
            switch(func[i]){
                case '+':
                    num[i+1]=num[i]+num[i+1];
                    break;

                case '*':
                    num[i+1]=num[i]*num[i+1];
                    break;

                case '/':
                    num[i+1]=num[i]/num[i+1];
                    break;

                case '-':
                    num[i+1]=num[i]-num[i+1];
                    break;
            }
        }
        double ans=num[u];
        //String output=String.valueOf(ans);
        //output=removePoint(output);
        return(ans);
    }
    public double calcBODMAS(String input){
        String buffer;
        double numbuffer;
        int u=0,l,cons=0;
        char buf,check;
        input=input.trim();
        l=input.length();
        if(input.charAt(0)=='(' && input.charAt(l-1)==')')
            input=input.substring(1,l-1);
        input=input+" ";
        l=input.length();
        String buffRecursive;
        double num[]=new double[25];
        char func[]=new char[25];
        for(int i=0;i<l;i++){
            char chk=input.charAt(i);
            if(!Character.isDigit(chk)){
                if(chk=='('){
                    for(int j=i+1;i<l;j++){
                        char chk0=input.charAt(j);
                        if (chk0==')'){
                            buffRecursive=input.substring(i+1,j);
                            num[u] =calcBODMAS(buffRecursive);
                            check=input.charAt(j+1);
                            if (check == ' ') {
                                u = u + 1;
                                l = 0;
                                i = 0;
                            }
                            else {
                                func[u]=input.charAt(j+1);
                                input=input.substring(j+2);
                                u = u + 1;
                                l = input.length();
                                i = 0;
                            }
                            break;
                        }
                    }
                }
                else if(chk!='.') {
                    if(chk!=' ') {
                        check=input.charAt(0);
                        if (!Character.isDigit(check)){//check == '(') {
                            buffer = input.substring(1, i);
                        }
                        else {
                            buffer = input.substring(0, i);
                        }
                        numbuffer = Double.parseDouble(buffer);
                        num[u] = numbuffer;
                        buf = input.charAt(i);
                        func[u] = buf;
                        input = input.substring(i + 1);
                        u = u + 1;
                        l = input.length();
                        i = 0;
                    }
                }
            }
        }
        if (Character.isDigit(input.charAt(0))) {
            input = input.trim();
            numbuffer = Double.parseDouble(input);
            num[u] = numbuffer;
        }
        else{
            input = input.trim();
            input=input.substring(1);
            numbuffer = Double.parseDouble(input);
            num[u] = numbuffer;
        }
        for(int i=0;i<=4;i++){
            cons=cons+1;
            for(int j=0;j<=u;j++){
                if(cons==1){
                    if(func[j]=='/'){
                        num[j]=num[j]/num[j+1];
                        func[j]='x';
                        func=deleteArraychar('x',func,u);
                        num[j+1]=1.147;
                        num=deleteArraydouble(1.147,num,u);
                        u=u-1;
                    }
                }
                if(cons==2){
                    if(func[j]=='*'){
                        num[j]=num[j]*num[j+1];
                        func[j]='x';
                        func=deleteArraychar('x',func,u);
                        num[j+1]=1.147;
                        num=deleteArraydouble(1.147,num,u);
                        u=u-1;
                    }
                }
                if(cons==3){
                    if(func[j]=='+'){
                        num[j]=num[j]+num[j+1];
                        func[j]='x';
                        func=deleteArraychar('x',func,u);
                        num[j+1]=1.147;
                        num=deleteArraydouble(1.147,num,u);
                        u=u-1;
                    }
                }
                if(cons==4){
                    if(func[j]=='-'){
                        num[j]=num[j]-num[j+1];
                        func[j]='x';
                        func=deleteArraychar('x',func,u);
                        num[j+1]=1.147;
                        num=deleteArraydouble(1.147,num,u);
                        u=u-1;
                    }
                }
            }
        }

        double ans=num[0];
        //String output=String.valueOf(ans);
        //output=removePoint(output);
        return(ans);

    }
    public void btncode(ActionEvent xy){
        double answer;
        //e tells us which button was clicked
        if(xy.getSource()==btn0){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"0");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btn1){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"1");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btn2){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"2");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btn3){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"3");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btn4){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"4");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btn5){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"5");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btn6){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"6");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btn7){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"7");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btn8){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"8");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btn9){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"9");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btnadd){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"+");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btnsub){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"-");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btnmul){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"*");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btndiv){
            txtfld=txtinput.getText();
            txtinput.setText(txtfld+"/");
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btnclr){
            lbldisp.setText(" ");
            txtinput.setText("");
            anss="";
            //txtinput.requestFocus();
        }
        else if(xy.getSource()==btnbrackets){
            brackettoggle=brackettoggle+1;
            txtfld=txtinput.getText();
            if(brackettoggle%2==0){
                txtinput.setText(txtfld+")");
            }
            else{
                txtinput.setText(txtfld+"(");
            }
        }
        else if(xy.getSource()==btnbodmastoggle){
            BODMAStoggle=BODMAStoggle+1;
            if(BODMAStoggle%2==0){
                lbldisp.setText(anss+"BODMAS RULE ENABLED");
            }
            else{
                lbldisp.setText(anss+"BODMAS RULE DISABLED");
            }
        }
        else if(xy.getSource()==btneql){
            txtfld=txtinput.getText();
            if(BODMAStoggle%2==0) {
                answer = calcBODMAS(txtfld);
            }
            else{
                answer = CalculatorMain(txtfld);
            }
            anss=String.valueOf(answer);
            anss=removePoint(anss);
            lbldisp.setText("RESULT   :"+anss);

        }

    }
    public String removePoint(String in){
        int l=in.length();
        char ch,ch1;
        for(int i=l-1;i>0;i--){
            ch=in.charAt(i);
            ch1=in.charAt(i-1);
            if((ch=='0')&&(ch1=='.')){
                in=in.substring(0,i-1);
                break;
            }
        }
        return(in);
    }
}
