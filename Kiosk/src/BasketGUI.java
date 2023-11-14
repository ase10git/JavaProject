package Kisok;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BasketGUI extends JPanel{
	
	public BasketGUI() {
		setLayout(null);
		setBounds(0, 400, 500, 700); // Y 변경 필요
		setVisible(true);
		setBackground(Color.WHITE);	
		
		JLabel basketTitle = new JLabel("선택한 메뉴");
		basketTitle.setBounds(0, 0, 150, 100);
		add(basketTitle);
		
		JTextArea chosenMenu = new JTextArea();
		add(chosenMenu);
		
		JButton credit = new JButton("결제");
		credit.setBounds(0, 0, 100, 100);
		add(credit);
		// 버튼 액션 추가 필요
	}
}
