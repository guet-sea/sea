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
	
	    private JFrame frame = new JFrame("�ܳ����");
	    private JTextField userText = new JTextField(20);//��¼������û���
	    private JPasswordField passwordText = new JPasswordField(20);//��¼����������
	    private  JTextField userTextRegister = new JTextField(20);//ע�������û��������
	    private  JPasswordField passwordText1 = new JPasswordField(20);//ע�������
	    private  JPasswordField passwordText2 = new JPasswordField(20);
	    private TextField text1=new TextField(20);//����������ʾ��
		private TextField text2=new TextField(20);//������ʾ��
	    private TextField text3=new TextField(20);//�ȼ���ʾ��
	    private TextField text4=new TextField(20);//������ʾ��
	    private JButton loginButton = new JButton("��¼");//��¼����
	    private JButton registerButton = new JButton("ע��");//ע�ᰴ��
	    private JButton submitButton = new JButton("�ύ");//ע����ύ����
	    private JButton cancelButton = new JButton("ȡ��");//ȡ��ע��İ���
	    private JPanel panel = new JPanel();
        private JButton exitButton;//��¼����
        
        private String user=null;//�û�id
        private String password=null;//����
		private int weight=0;//��������
		private int level=1;//�ؿ�
		private int stepnum1=0;//̰�����Ĳ���
		private int stepnum2=0;//�û�ѡ��Ĳ���
		private int score=0;//����
		
		
//		BufferedImage bg = null;
	    public Login(){//��¼���湹�췽��    
	        
	        frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-300, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-300,350,200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        // ������
	        frame.add(panel);
	        /* 
	         * �����û�����ķ����������������
	         */
	        placeLogin(panel);
	        // ���ý���ɼ�
	        frame.setVisible(true);
	    }
	    public int RandomNum() {//��������������Χ1��3000
			
			return (int)(1+Math.random()*3000);
		}
	    public int StepNum(int x) {//̰���㷨��ⲽ������
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
			return count;//���ز���
		}
	    
	    
	    private void placeGameframe(JPanel panel) {//������Ϸ����
	    	
	    panel.setBounds(0, 0, 700, 400);
	    panel.setLayout(new GridLayout(1, 2));
//	    frame.setDefaultCloseOperation(exit());
	    weight=RandomNum();
		stepnum1=(int)StepNum(weight);
		text1.setText(Integer.toString(weight)+"t");
		text2.setText(Integer.toString(weight)+"t");
		text3.setText(Integer.toString(level));
		text4.setText(Integer.toString(score));
	    Label label2=new Label("����ʣ��������:");
		Label label1=new Label("�����������:       ");
		Label label3=new Label("��ǰ�ؿ�:                ");
		Label label4=new Label("����ǰ�ķ�����:   ");
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
		exitButton = new JButton("�˳���Ϸ");
//		exitButton.setBounds(0,200, 80, 25);
		exit.add(exitButton);
		show.add(exit);
		Button btn1=new Button("1");//���Ȱ���
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
	    
		private  void placeRegister(JPanel panel ) {//����ע�����
	    	 panel.setLayout(null);
	 	     
		        // ���� JLabel
		        JLabel userLabel = new JLabel("�û���:");
		        userLabel.setBounds(10,20,80,25);
		        panel.add(userLabel);

		        /* 
		         * �����ı��������û�����
		         */
		        userTextRegister.setBounds(100,20,165,25);
		        panel.add(userTextRegister);

		        // ����������ı���
		        JLabel passwordLabel1 = new JLabel("����:");
		        passwordLabel1.setBounds(10,50,80,25);
		        panel.add(passwordLabel1);
		        passwordText1.setBounds(100,50,165,25);
		        panel.add(passwordText1);
		        //ȷ������
		        JLabel passwordLabel2 = new JLabel("ȷ������:");
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
	    public class GameServices  implements ActionListener{//��Ϸ�����ұ�button���¼�����

			@Override
			public void actionPerformed(ActionEvent e) {
				Button btn=(Button)e.getSource();
				String plus=btn.getLabel();
				System.out.println(plus);
				if((weight-Integer.parseInt(plus))==0) {//�Ѿ�ѡ�����
					if(stepnum2+1-stepnum1<=0) {//��̰���㷨���Ĳ����ٻ��ߵ��ڣ���һ�ٷ�
						score=score+100;
					}else if(100-(stepnum2+1-stepnum1)*5<=0){//�۵�����û���ˣ���һ��
						score++;
					}else {
						score=score+(100-(stepnum2+1-stepnum1)*5);//���ҵ�̰���㷨���Ĳ����Ƚϣ�ÿ��һ����5��
					}
					if(score>=80*level) {//���ִ���ƽ��ÿ��80�֣����سɹ�
						JOptionPane.showMessageDialog(null, "��ϲ��������");
						level++;
						weight=RandomNum();
						stepnum1=StepNum(weight);
						stepnum2=0;//���³�ʼ��
						text1.setText(Integer.toString(weight)+"t");
						text2.setText(Integer.toString(weight)+"t");
						text3.setText(Integer.toString(level));
						text4.setText(Integer.toString(score));
					}else {
						JOptionPane.showMessageDialog(null, "���ź�����ʧ�ܣ������´���");
						if((100-(stepnum2+1-stepnum1)*5)<=0)score=score-1;//����ֻ��һ��
						if((100-(stepnum2+1-stepnum1)*5)>0) {
							score=score-(100-(stepnum2+1-stepnum1)*5);
						}
						weight=RandomNum();
						stepnum1=StepNum(weight);
						stepnum2=0;//���³�ʼ��
						text1.setText(Integer.toString(weight)+"t");
						text2.setText(Integer.toString(weight)+"t");
						text3.setText(Integer.toString(level));
						text4.setText(Integer.toString(score));
					}
					return;
				}
				if((weight-Integer.parseInt(plus))<0) {//���ȳ���
					JOptionPane.showMessageDialog(null, "��ѡ��ĳ�������������", "��ʾ",JOptionPane.ERROR_MESSAGE); 
					return;
				}
				//����ûѡ�꣬Ҳû�г���
				weight=(weight-Integer.parseInt(plus));
				text2.setText(Integer.toString(weight)+"t");
				stepnum2++;
			}
    }
	    
	    
	    private void placeLogin(JPanel panel) {
	        

	        panel.setLayout(null);

	        // ���� JLabel
	        JLabel userLabel = new JLabel("�û���:");
	        /* ������������������λ�á�
	         * setBounds(x, y, width, height)
	         * x �� y ָ�����Ͻǵ���λ�ã��� width �� height ָ���µĴ�С��
	         */
	        userLabel.setBounds(10,20,80,25);
	        panel.add(userLabel);

	        /* 
	         * �����ı��������û�����
	         */
	        userText.setBounds(100,20,165,25);
	        panel.add(userText);

	        // ����������ı���
	        JLabel passwordLabel = new JLabel("����:");
	        passwordLabel.setBounds(10,50,80,25);
	        panel.add(passwordLabel);

	        /* 
	         *�����������������ı���
	         * �����������Ϣ���Ե�Ŵ��棬���ڰ�������İ�ȫ��
	         */
	        
	        passwordText.setBounds(100,50,165,25);
	        panel.add(passwordText);

	        // ������¼��ť
	        
	        loginButton.setBounds(80, 90, 80, 25);
	        registerButton.setBounds(200, 90, 80, 25);
	        panel.add(registerButton);
	        panel.add(loginButton);
	        loginButton.addActionListener(this);
	        registerButton.addActionListener(this); 
	    }

		@Override
		public void actionPerformed(ActionEvent e) {//������¼������ע�ᰴ����ȡ���������ύ����
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
					Db= mySQL.search(userTextRegister.getText());//��ѯ���ݿ��Ƿ�������û�
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	if(Db.isEmpty()) {//�����ǿձ���û�и��û�������ע��
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
					JOptionPane.showMessageDialog(null, "ע��ɹ�");
	        	}
	        	else {
	        		JOptionPane.showMessageDialog(null, "���û��Ѿ�����", "��ʾ",JOptionPane.ERROR_MESSAGE);
	        		return;
				}
			}
		    else {
				JOptionPane.showMessageDialog(null, "�������벻һ��", "��ʾ",JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "����д�˺Ż�������", "��ʾ",JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(null, "��½�ɹ�");
						this.level=Integer.parseInt(DB.get(2));
						this.score=Integer.parseInt(DB.get(3));
						panel.removeAll();
						frame.setBounds(0, 0, 700, 400);
						placeGameframe(panel);
						frame.add(panel);
						
						return;
					}else {
						JOptionPane.showMessageDialog(null, "�˺Ż����������", "��ʾ",JOptionPane.ERROR_MESSAGE);
						return;
					}
			}else {
				JOptionPane.showMessageDialog(null, "�û������ڣ���", "��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
	}
		public static void main(String[] args) {
			Login Game=new Login();
		}
}