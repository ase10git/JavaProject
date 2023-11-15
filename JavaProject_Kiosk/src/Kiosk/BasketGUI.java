package Kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BasketGUI extends JPanel{

	private static final long serialVersionUID = 1L;

	
	public BasketGUI() {
		
		setLayout(null);
		setBounds(0, 650, mainGUI.frameWidth, 250); // Y 변경 필요
		setVisible(true);
		setBackground(Color.MAGENTA);	
		
		// 선택한 메뉴 title
		JLabel basketTitle = new JLabel("선택한 메뉴");
		basketTitle.setFont(mainGUI.getkFont());
		basketTitle.setBounds(10, 0, 150, 50);
		basketTitle.setHorizontalTextPosition(JLabel.LEFT);
		add(basketTitle);
		
		// 선택한 메뉴 리스트 
		JTextArea chosenMenu = new JTextArea();
		add(chosenMenu);
		
		
		// 결제, 돌아가기 버튼 추가
		JButton back = new JButton("처음으로 돌아가기");
		JButton payment = new JButton("결제");
		
		payment.setFont(mainGUI.getkFont());
		payment.setBounds(600, 80, 190, 140);
		back.setFont(mainGUI.getkFont());
		back.setBounds(600, 0, 190, 80);
		
		add(payment);
		add(back);
		
		ActionListener basketButtonAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == back) {
					setVisible(false);
					
				} else if (e.getSource() == payment) {
					CreditGUI credit = new CreditGUI();
					credit.setVisible(true);
					setVisible(false);
				}
				
			}
		};
		
		payment.addActionListener(basketButtonAction);
		back.addActionListener(basketButtonAction);
	}
}
