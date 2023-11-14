package Kisok;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainGUI extends JFrame {
	
	// 메인 프레임 생성
	public mainGUI() {
		super("메인 프레임");
		setLayout(null);
		
		// 메인 화면 환영 문구
		JLabel titleName = new JLabel("부평 휴게소");
		JLabel welcome = new JLabel("부평 휴게소에 온 것을 환영합니다!");
		titleName.setBounds(0, 0, 100, 100);
		welcome.setBounds(0, 100, 200, 200);
		
		add("Center", titleName);
		add("Center", welcome);
		
		// 매장 이용 방법 선택
		JPanel takeOutOrEat = new JPanel(null);
		JButton takeOut = new JButton("테이크아웃");
		JButton Eat = new JButton("매장식사");
		
		takeOutOrEat.setBounds(100, 100, 800, 900);
		takeOut.setBounds(10, 250, 100, 100);
		Eat.setBounds(200, 250, 100, 100);
		
		takeOutOrEat.add(takeOut);
		takeOutOrEat.add(Eat);
		
		add(takeOutOrEat);
		takeOutOrEat.setVisible(true);
		
		// 매장이용 버튼 액션 추가
		ActionListener takeEatButtonAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				takeOutOrEat.setVisible(false);
				
				// 메뉴 패널 등장
				MenuGUI menu = new MenuGUI();
				add("Center", menu);
				menu.setVisible(true);
				
				// 바구니 패널 등장
				BasketGUI basket = new BasketGUI();
				add("South", basket);
				basket.setVisible(true);
			}
		};
		
		takeOut.addActionListener(takeEatButtonAction);
		Eat.addActionListener(takeEatButtonAction);
		
		// 메인 프레임 크기 설정
		setBounds(100, 100, 800, 900);
		setVisible(true);
		setBackground(Color.WHITE);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

}
