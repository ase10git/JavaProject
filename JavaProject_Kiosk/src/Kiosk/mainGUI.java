package Kiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	static final int frameWidth = 800;
	static final int frameHeight = 900;
	static Font kfont;
	
	JPanel takeOutOrEat;
	
	// 메인 프레임 생성
	public mainGUI() {
		super("메인 프레임");
		setLayout(null);
		
		// 메인 화면 환영 문구
		JPanel mainTitle = new JPanel(new BorderLayout());
		mainTitle.setBounds(0, 0, 800, 100);
		mainTitle.setBackground(Color.PINK);
		mainTitle.setVisible(true);
		
		JLabel titleName = new JLabel("<html><body style='text-align: center'>부평 휴게소<br>부평 휴게소에 온 것을 환영합니다!</html>");
		titleName.setSize(200, 100);
		titleName.setFont(getkFont());
		titleName.setHorizontalAlignment(JLabel.CENTER);
		titleName.setHorizontalTextPosition(JLabel.CENTER);
		
		mainTitle.add("Center", titleName);
		add(mainTitle);
		
		// 메인 프레임 크기 설정
		setBounds(100, 100, frameWidth, frameHeight);
		setVisible(true);
		setBackground(Color.WHITE);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	} // end mainGUI
	
	public void addTakeOrEatPanel() {
		// 매장 이용 방법 선택
		takeOutOrEat = new JPanel(null);
		JLabel info = new JLabel("매장 이용 방법을 선택해주세요.");
		JButton takeOut = new JButton("포장");	
		JButton eat = new JButton("매장에서 식사");
		
		// 버튼 크기 설정 및 안내 문구 추가
		takeOut.setFont(getkFont());
		eat.setFont(getkFont());
		info.setFont(getkFont());
		
		takeOutOrEat.setBounds(0, 100, 800, 800);
		takeOut.setBounds(0, 500, frameWidth/2, 300);
		eat.setBounds(frameWidth/2, 500, frameWidth/2, 300);
		info.setBounds(frameWidth/2-150, 400, 300, 100);
		
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setHorizontalTextPosition(JLabel.CENTER);
		
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
		eat.addActionListener(takeEatButtonAction);
		
		takeOutOrEat.add(info);
		takeOutOrEat.add(takeOut);
		takeOutOrEat.add(eat);
		
		add(takeOutOrEat);
		takeOutOrEat.setVisible(true);
		
		setTakeOrEatPanel(takeOutOrEat);
	} // end add TakeOrEatPanel
	
	public void setTakeOrEatPanel(JPanel jp) {
		this.takeOutOrEat = jp;
	}
	
	public JPanel getTakeOrEatPanel() {
		return takeOutOrEat;
	}
	
	// 폰트
	public static Font getkFont() {
		 kfont = new Font("", Font.PLAIN, 18);
		 return kfont;
	}
	
	// 식사 패널 추가 및 프로그램 실행
	public void run() {
		addTakeOrEatPanel();
	}
}
