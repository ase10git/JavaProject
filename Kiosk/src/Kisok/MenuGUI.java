package Kiosk;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private static int numOfMenu = 4;

	public MenuGUI() {
		// 패널 설정
		setLayout(null);
		setBounds(0, 100, 800, 700);
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
				if (e.getActionCommand() == "분식") {
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
			menubt[i].setBounds(i * 100, 0, 100, 100);
			add(menubt[i]);
			menubt[i].addActionListener(menuButtonAction);
		}

	} // end MenuGUI

	// 메뉴 종류 화면 추가
	public JPanel addMenu1() {
		JPanel menu1 = new JPanel();
		GridLayout gl = new GridLayout(3, 2);
		menu1.setLayout(gl);
		menu1.setBounds(0, 100, 800, 700);
		menu1.setVisible(true);

		// 분식 메뉴 버튼 생성
		JButton[] menuSnack = new JButton[4];
		menuSnack[0] = new JButton("김밥");
		menuSnack[1] = new JButton("라면");
		menuSnack[2] = new JButton("쫄면");
		menuSnack[3] = new JButton("떡볶이");

		for (int i = 0; i < menuSnack.length; i++) {
			menu1.add(menuSnack[i]);
		}

		add(menu1);
		return menu1;
	}

	public JPanel addMenu2() {
		JPanel menu2 = new JPanel();
		GridLayout gl = new GridLayout(3, 2);
		menu2.setLayout(gl);
		menu2.setBounds(0, 100, 800, 700);
		menu2.setVisible(false);

		// 일식 메뉴 버튼 생성
		JButton[] menuJapanese = new JButton[4];
		menuJapanese[0] = new JButton("초밥");
		menuJapanese[1] = new JButton("우동");
		menuJapanese[2] = new JButton("돈까스");
		menuJapanese[3] = new JButton("모밀");

		for (int i = 0; i < menuJapanese.length; i++) {
			menu2.add(menuJapanese[i]);
		}

		add(menu2);
		return menu2;
	}

	public JPanel addMenu3() {
		JPanel menu3 = new JPanel();
		GridLayout gl = new GridLayout(3, 2);
		menu3.setLayout(gl);
		menu3.setBounds(0, 100, 800, 700);
		menu3.setVisible(false);

		
		//양식 메뉴 생성
		JButton[] menuWestern = new JButton[4];
		menuWestern[0] = new JButton("스파게티");
		menuWestern[1] = new JButton("피자");
		menuWestern[2] = new JButton("햄버거");
		menuWestern[3] = new JButton("스프");

		for (int i = 0; i < menuWestern.length; i++) {
			menu3.add(menuWestern[i]);
		}

		add(menu3);
		return menu3;
	}

	public JPanel addMenu4() {
		JPanel menu4 = new JPanel();
		GridLayout gl = new GridLayout(3, 2);
		menu4.setLayout(gl);
		menu4.setBounds(0, 100, 800, 700);
		menu4.setVisible(false);

		//한식 메뉴 생성
		JButton[] menuKorean = new JButton[4];
		menuKorean[0] = new JButton("비빔밥");
		menuKorean[1] = new JButton("불고기");
		menuKorean[2] = new JButton("갈비탕");
		menuKorean[3] = new JButton("육개장");

		for (int i = 0; i < menuKorean.length; i++) {
			menu4.add(menuKorean[i]);
		}

		add(menu4);
		return menu4;
	}

}
