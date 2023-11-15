package Kiosk;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuGUI extends JPanel{
	private static final long serialVersionUID = 1L;
	private static int numOfMenu = 4;
	
	public MenuGUI() {
		// 패널 설정
		setLayout(null);
		setBounds(0, 0, 800, 700);
		setBackground(Color.WHITE);	
		
		// 메뉴 버튼 추가
		JButton[] menubt = new JButton[numOfMenu];
		menubt[0] = new JButton("분식");
		menubt[1] = new JButton("일식");
		menubt[2] = new JButton("한식");
		menubt[3] = new JButton("양식");
		
		// 메뉴 화면들 추가
		JPanel[] menu = new JPanel[numOfMenu];
		JPanel menu1 = addMenu1();
		JPanel menu2 = addMenu2();
		JPanel menu3 = addMenu3();
		JPanel menu4 = addMenu4();
		
		// 메뉴 버튼 액션 추가
		ActionListener menuButtonAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "분식") {
					menu1.setVisible(true);
					menu2.setVisible(false);
					menu3.setVisible(false);
					menu4.setVisible(false);
				} else if (e.getActionCommand() == "일식") {
					menu1.setVisible(false);
					menu2.setVisible(true);
					menu3.setVisible(false);
					menu4.setVisible(false);
				} else if (e.getActionCommand() == "한식") {	
					menu1.setVisible(false);
					menu2.setVisible(false);
					menu3.setVisible(true);
					menu4.setVisible(false);
				} else if (e.getActionCommand() == "양식") {
					menu1.setVisible(false);
					menu2.setVisible(false);
					menu3.setVisible(false);
					menu4.setVisible(true);
				} 
			}
		};

		for (int i = 0; i < menubt.length; i++) {
			menubt[i].setBounds(i*100, 0, 100, 100);
			add(menubt[i]);
			menubt[i].addActionListener(menuButtonAction);
		}			
	} // end MenuGUI
	
	// 메뉴 종류 화면 추가
	public JPanel addMenu1() {
		JPanel menu1 = new JPanel();
		menu1.setLayout(new FlowLayout());
		menu1.setBounds(100, 100, 800, 700);
		menu1.setBackground(Color.BLUE);
		menu1.setVisible(false);
		
		JLabel test1 = new JLabel("분식 메뉴판");
		menu1.add("Center", test1);
		
		add(menu1);
		return menu1;
	}
	
	public JPanel addMenu2() {
		JPanel menu2 = new JPanel();
		menu2.setLayout(new FlowLayout());
		menu2.setBounds(100, 100, 800, 700);
		menu2.setBackground(Color.YELLOW);	
		menu2.setVisible(false);
		
		JLabel test1 = new JLabel("일식 메뉴판");
		menu2.add(test1);
		
		add(menu2);
		return menu2;
	}
	
	public JPanel addMenu3() {
		JPanel menu3 = new JPanel();
		menu3.setLayout(new FlowLayout());
		menu3.setBounds(100, 100, 800, 700);
		menu3.setBackground(Color.PINK);
		menu3.setVisible(false);
		
		JLabel test1 = new JLabel("한식 메뉴판");
		menu3.add(test1);
		
		add(menu3);
		return menu3;
	}
	
	public JPanel addMenu4() {
		JPanel menu4 = new JPanel();
		menu4.setLayout(new FlowLayout());
		menu4.setBounds(100, 100, 800, 700);
		menu4.setBackground(Color.CYAN);
		menu4.setVisible(false);
		
		JLabel test1 = new JLabel("양식 메뉴판");
		menu4.add(test1);
		
		add(menu4);
		return menu4;
	}

}
