package Kisok;

import java.awt.Color;
import java.awt.FlowLayout;
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
		setBounds(100, 100, 500, 700);
		setVisible(true);
		setBackground(Color.WHITE);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		addPanels();
	}
	
	public void addPanels() {
		// 환영 및 이용방법 패널 이름 추가
		JLabel titleName = new JLabel("부평 휴게소");
		JLabel welcome = new JLabel("부평 휴게소에 온 것을 환영합니다!");
		titleName.setBounds(210, 0, 100, 100);
		welcome.setBounds(150, 0, 200, 200);
		
		add(titleName);
		add(welcome);
		
		// 새 패널 추가
		// 버튼 액션 활성화 시 메뉴 패널
		takeOrEatPanel();
		
		MenuGUI menu = new MenuGUI();
		BasketGUI basket = new BasketGUI();
		add(menu);
		//add(basket);
	}
	
	public void takeOrEatPanel() {
		JPanel takeOutOrEat = new JPanel();
		JButton takeOut = new JButton("테이크아웃");
		JButton Eat = new JButton("매장식사");
		
		takeOut.setBounds(10, 250, 100, 100);
		Eat.setBounds(200, 250, 100, 100);
		
		takeOutOrEat.setBounds(getBounds());
		takeOutOrEat.add(takeOut);
		takeOutOrEat.add(Eat);
		
		add(takeOutOrEat);
	}
	
}
