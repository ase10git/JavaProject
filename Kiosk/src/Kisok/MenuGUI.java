package Kisok;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuGUI extends JPanel{

	public MenuGUI() {
		setLayout(null);
		setBounds(100, 100, 500, 700);
		setVisible(true);
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
		

	}
}
