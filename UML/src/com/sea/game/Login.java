package com.sea.game;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Login implements ActionListener{
	
	    private JFrame frame = new JFrame("曹冲秤象");
	    private JTextField userText = new JTextField(20);//登录界面的用户框
	    private JPasswordField passwordText = new JPasswordField(20);//登录界面的密码框
	    private  JTextField userTextRegister = new JTextField(20);//注册界面的用户名输入框
	    private  JPasswordField passwordText1 = new JPasswordField(20);//注册密码框
	    private  JPasswordField passwordText2 = new JPasswordField(20);
	    private TextField text1=new TextField(20);//大象重量显示框
		private TextField text2=new TextField(20);//余重显示框
	    private TextField text3=new TextField(20);//等级显示框
	    private TextField text4=new TextField(20);//分数显示框
	    private JButton loginButton = new JButton("登录");//登录按键
	    private JButton registerButton = new JButton("注册");//注册按键
	    private JButton submitButton = new JButton("提交");//注册的提交按键
	    private JButton cancelButton = new JButton("取消");//取消注册的按键
	    private JPanel panel = new JPanel();
        private JButton exitButton;//登录按键
        
        private String user=null;//用户id
        private String password=null;//密码
		private int weight=0;//大象重量
		private int level=1;//关卡
		private int stepnum1=0;//贪心求解的步数
		private int stepnum2=0;//用户选择的步数
		private int score=0;//分数
		
		
//		BufferedImage bg = null;
	    public Login(){//登录界面构造方法    
	        
	        frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-300, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-300,350,200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        // 添加面板
	        frame.add(panel);
	        /* 
	         * 调用用户定义的方法并添加组件到面板
	         */
	        placeLogin(panel);
	        // 设置界面可见
	        frame.setVisible(true);
	    }
	    public int RandomNum() {//随机方法，随机范围1到3000
			
			return (int)(1+Math.random()*3000);
		}
	    public int StepNum(int x) {//贪心算法求解步数方法
			float tem=x;
			int count=0;
			float j=0;
			while(tem>0) {
				for(int i=12;i>=0;i--) {
					j=(float) Math.pow(2, i);
					if(tem-j>=0) {
						tem=tem-j;
						count++;
						break;
					}
				}
				if(tem==0)break;
			}
			return count;//返回步数
		}
	    
	    
	    private void placeGameframe(JPanel panel) {//绘制游戏界面
	    	
	    panel.setBounds(0, 0, 700, 400);
	    panel.setLayout(new GridLayout(1, 2));
//	    frame.setDefaultCloseOperation(exit());
	    weight=RandomNum();
		stepnum1=(int)StepNum(weight);
		text1.setText(Integer.toString(weight)+"t");
		text2.setText(Integer.toString(weight)+"t");
		text3.setText(Integer.toString(level));
		text4.setText(Integer.toString(score));
	    Label label2=new Label("大象剩余重量是:");
		Label label1=new Label("大象的重量是:       ");
		Label label3=new Label("当前关卡:                ");
		Label label4=new Label("您当前的分数是:   ");
		Panel show=new Panel();
		Panel select=new Panel();
		show.setLayout(new FlowLayout());
		Panel show1=new Panel();
		Panel show2=new Panel();
		Panel show3=new Panel();
		Panel show4=new Panel();
		Panel exit=new Panel();
		show1.add(label1);
		show1.add(text1);
		show2.add(label2);
		show2.add(text2);
		show3.add(label3);
		show3.add(text3);
		show4.add(label4);
		show4.add(text4);
		show.add(show1);
		show.add(show2);
		show.add(show3);
		show.add(show4);
//		show.add(textintroduction);
		exitButton = new JButton("退出游戏");
//		exitButton.setBounds(0,200, 80, 25);
		exit.add(exitButton);
		show.add(exit);
		Button btn1=new Button("1");//秤砣按键
		Button btn2=new Button("2");
		Button btn3=new Button("4");
		Button btn4=new Button("8");
		Button btn5=new Button("16");
		Button btn6=new Button("32");
		Button btn7=new Button("64");
		Button btn8=new Button("128");
		Button btn9=new Button("256");
		Button btn10=new Button("512");
		Button btn11=new Button("1024");
		Button btn12=new Button("2048");
		
//		show.add(exit);
		select.setLayout(new GridLayout(3, 4));
		select.add(btn1);
		select.add(btn2);
		select.add(btn3);
		select.add(btn4);
		select.add(btn5);
		select.add(btn6);
		select.add(btn7);
		select.add(btn8);
		select.add(btn9);
		select.add(btn10);
		select.add(btn11);
		select.add(btn12);
		panel.add(show);
		panel.add(select);
		
		btn1.addActionListener(new GameServices());
		btn2.addActionListener(new GameServices());
		btn3.addActionListener(new GameServices());
		btn4.addActionListener(new GameServices());
		btn5.addActionListener(new GameServices());
		btn6.addActionListener(new GameServices());
		btn7.addActionListener(new GameServices());
		btn8.addActionListener(new GameServices());
		btn9.addActionListener(new GameServices());
		btn10.addActionListener(new GameServices());
		btn11.addActionListener(new GameServices());
		btn12.addActionListener(new GameServices());
		exitButton.addActionListener(this);
			
		}
	    
		private  void placeRegister(JPanel panel ) {//绘制注册界面
	    	 panel.setLayout(null);
	 	     
		        // 创建 JLabel
		        JLabel userLabel = new JLabel("用户名:");
		        userLabel.setBounds(10,20,80,25);
		        panel.add(userLabel);

		        /* 
		         * 创建文本域用于用户输入
		         */
		        userTextRegister.setBounds(100,20,165,25);
		        panel.add(userTextRegister);

		        // 输入密码的文本域
		        JLabel passwordLabel1 = new JLabel("密码:");
		        passwordLabel1.setBounds(10,50,80,25);
		        panel.add(passwordLabel1);
		        passwordText1.setBounds(100,50,165,25);
		        panel.add(passwordText1);
		        //确认密码
		        JLabel passwordLabel2 = new JLabel("确认密码:");
		        passwordLabel2.setBounds(10,80,80,25);
		        panel.add(passwordLabel2);
		        passwordText2.setBounds(100,80,165,25);
		        panel.add(passwordText2);
		        
		        submitButton.setBounds(80, 130, 80, 25);
		        cancelButton.setBounds(200, 130, 80, 25);
		        panel.add(submitButton);
		        panel.add(cancelButton);
		        submitButton.addActionListener(this);
		        cancelButton.addActionListener(this);
	    }
	    public class GameServices  implements ActionListener{//游戏界面右边button的事件处理

			@Override
			public void actionPerformed(ActionEvent e) {
				Button btn=(Button)e.getSource();
				String plus=btn.getLabel();
				System.out.println(plus);
				if((weight-Integer.parseInt(plus))==0) {//已经选完秤砣
					if(stepnum2+1-stepnum1<=0) {//比贪心算法求解的步数少或者等于，得一百分
						score=score+100;
					}else if(100-(stepnum2+1-stepnum1)*5<=0){//扣到本轮没分了，给一分
						score++;
					}else {
						score=score+(100-(stepnum2+1-stepnum1)*5);//与我的贪心算法求解的步数比较，每多一步扣5分
					}
					if(score>=80*level) {//积分大于平均每局80分，闯关成功
						JOptionPane.showMessageDialog(null, "恭喜您晋级了");
						level++;
						weight=RandomNum();
						stepnum1=StepNum(weight);
						stepnum2=0;//重新初始化
						text1.setText(Integer.toString(weight)+"t");
						text2.setText(Integer.toString(weight)+"t");
						text3.setText(Integer.toString(level));
						text4.setText(Integer.toString(score));
					}else {
						JOptionPane.showMessageDialog(null, "很遗憾晋级失败，请重新闯关");
						if((100-(stepnum2+1-stepnum1)*5)<=0)score=score-1;//本轮只得一分
						if((100-(stepnum2+1-stepnum1)*5)>0) {
							score=score-(100-(stepnum2+1-stepnum1)*5);
						}
						weight=RandomNum();
						stepnum1=StepNum(weight);
						stepnum2=0;//重新初始化
						text1.setText(Integer.toString(weight)+"t");
						text2.setText(Integer.toString(weight)+"t");
						text3.setText(Integer.toString(level));
						text4.setText(Integer.toString(score));
					}
					return;
				}
				if((weight-Integer.parseInt(plus))<0) {//秤砣超重
					JOptionPane.showMessageDialog(null, "您选择的秤砣重量过大了", "提示",JOptionPane.ERROR_MESSAGE); 
					return;
				}
				//秤砣没选完，也没有超重
				weight=(weight-Integer.parseInt(plus));
				text2.setText(Integer.toString(weight)+"t");
				stepnum2++;
			}
    }
	    
	    
	    private void placeLogin(JPanel panel) {
	        

	        panel.setLayout(null);

	        // 创建 JLabel
	        JLabel userLabel = new JLabel("用户名:");
	        /* 这个方法定义了组件的位置。
	         * setBounds(x, y, width, height)
	         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
	         */
	        userLabel.setBounds(10,20,80,25);
	        panel.add(userLabel);

	        /* 
	         * 创建文本域用于用户输入
	         */
	        userText.setBounds(100,20,165,25);
	        panel.add(userText);

	        // 输入密码的文本域
	        JLabel passwordLabel = new JLabel("密码:");
	        passwordLabel.setBounds(10,50,80,25);
	        panel.add(passwordLabel);

	        /* 
	         *这个类似用于输入的文本域
	         * 但是输入的信息会以点号代替，用于包含密码的安全性
	         */
	        
	        passwordText.setBounds(100,50,165,25);
	        panel.add(passwordText);

	        // 创建登录按钮
	        
	        loginButton.setBounds(80, 90, 80, 25);
	        registerButton.setBounds(200, 90, 80, 25);
	        panel.add(registerButton);
	        panel.add(loginButton);
	        loginButton.addActionListener(this);
	        registerButton.addActionListener(this); 
	    }

		@Override
		public void actionPerformed(ActionEvent e) {//监听登录按键，注册按键，取消按键，提交按键
			Object button= e.getSource();
			if(button.equals(registerButton)) {
				this.panel.removeAll();
				this.frame.setBounds(0, 0, 400, 250);
				placeRegister(this.panel);
				frame.add(this.panel);
			}
			if(button.equals(submitButton)) {
		        if(passwordText1.getText().equals(passwordText2.getText())) {
	        	MySQL mySQL=new MySQL();
	        	ArrayList< String> Db=null;
	        	try {
					Db= mySQL.search(userTextRegister.getText());//查询数据库是否有这个用户
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	if(Db.isEmpty()) {//返回是空表明没有该用户，可以注册
	        		try {
						mySQL.newUser(userTextRegister.getText(), passwordText1.getText());
						return;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		this.panel.removeAll();
					this.frame.setBounds(0, 0, 350, 200);
					placeLogin(this.panel);
					frame.add(this.panel);
					JOptionPane.showMessageDialog(null, "注册成功");
	        	}
	        	else {
	        		JOptionPane.showMessageDialog(null, "该用户已经存在", "提示",JOptionPane.ERROR_MESSAGE);
	        		return;
				}
			}
		    else {
				JOptionPane.showMessageDialog(null, "两次密码不一致", "提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
			if(button.equals(cancelButton)) {
				this.panel.removeAll();
				this.frame.setBounds(0, 0, 350, 200);
				placeLogin(this.panel);
				frame.add(this.panel);
				return;
			}
			if(button.equals(exitButton)) {
				MySQL exit=new MySQL();
				try {
					exit.update(user,Integer.toString(level),Integer.toString(score));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
			if(button.equals(loginButton)) {
				MySQL login=new MySQL();
				 this.user=userText.getText();
				 this.password=passwordText.getText();
				ArrayList<String> DB=null;
				String DbUser=null;
				String DbPassword=null;
				if(user.equals(null)||password.equals(null)) {
					JOptionPane.showMessageDialog(null, "请填写账号或者密码", "提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					DB= login.search(user);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(!DB.isEmpty()) {
					DbUser=DB.get(0);
					DbPassword=DB.get(1);
					if(DbUser.equals(user)&&DbPassword.equals(password)) {
						JOptionPane.showMessageDialog(null, "登陆成功");
						this.level=Integer.parseInt(DB.get(2));
						this.score=Integer.parseInt(DB.get(3));
						panel.removeAll();
						frame.setBounds(0, 0, 700, 400);
						placeGameframe(panel);
						frame.add(panel);
						
						return;
					}else {
						JOptionPane.showMessageDialog(null, "账号或者密码错误", "提示",JOptionPane.ERROR_MESSAGE);
						return;
					}
			}else {
				JOptionPane.showMessageDialog(null, "用户不存在！！", "提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
	}
		public static void main(String[] args) {
			Login Game=new Login();
		}
}