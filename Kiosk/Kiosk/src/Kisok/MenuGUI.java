package Kisok;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuGUI extends JPanel{

	public MenuGUI() {
		setLayout(null);
		setBounds(0, 0, 800, 700);
		setBackground(Color.WHITE);	
		// 메뉴 버튼 추가
		addMenuButton();
	}
	
	public void addMenuButton() {
		Menu1GUI menu1 = new Menu1GUI();
		Menu2GUI menu2 = new Menu2GUI();
		Menu3GUI menu3 = new Menu3GUI();
		Menu4GUI menu4 = new Menu4GUI();
		
		JButton menu1bt = new JButton("분식");
		JButton menu2bt = new JButton("일식");
		JButton menu3bt = new JButton("한식");
		JButton menu4bt = new JButton("양식");
		
		ActionListener menuButtonAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == menu1bt) {
					menu1.setVisible(true);
				} else if (e.getSource() == menu2bt) {
					menu2.setVisible(true);
				} else if (e.getSource() == menu3bt) {
					menu3.setVisible(true);
				} else if (e.getSource() == menu4bt) {
					menu4.setVisible(true);
				} 
			}
		};
		
		menu1bt.addActionListener(menuButtonAction);
		menu2bt.addActionListener(menuButtonAction);
		menu3bt.addActionListener(menuButtonAction);
		menu4bt.addActionListener(menuButtonAction);
		
		add(menu1bt);
		add(menu2bt);
		add(menu3bt);
		add(menu4bt);
		// Button Action과 GUI 객체 연결 및 창 띄우기 추가-----------

	}
}
