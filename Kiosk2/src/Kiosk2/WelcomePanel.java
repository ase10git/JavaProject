package Kiosk2;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
//매장 방문 환영 패널을 생성하는 클래스
public class WelcomePanel extends MainGUI2{
	private JPanel welcome;
	private JLabel titleName;
	
	// 클래스 기본 생성자
	// 매장 방문 환영 패널을 생성하고, 객체 정보를 저장할 클래스
	public WelcomePanel() {}
	
	// 메인 화면 환영 패널 만들기
	public JPanel makeWelcomePanel() {		
		JPanel welcome = new JPanel(new BorderLayout());
		welcome.setBounds(0, 0, 800, 100);
		welcome.setBackground(Color.WHITE);
		welcome.setVisible(true);
		
		return welcome;
	}
	
	// 메인 화면 환영 패널에 문구 만들기
	public JLabel makeWelcomeLabel() {
		JLabel titleName = new JLabel("<html><body style='text-align: center'>부평 휴게소<br>부평 휴게소에 온 것을 환영합니다!</html>");
		titleName.setSize(200, 100);
		titleName.setFont(kfont);
		titleName.setHorizontalAlignment(JLabel.CENTER);
		titleName.setHorizontalTextPosition(JLabel.CENTER);
		
		return titleName;
	}
	
	public void addComponentToPanel(JPanel welcomele, JLabel titleName) {
		welcomele.add("Center", titleName);
	
		// 클래스에 객체 저장하기
		setwelcomelePanel(welcomele);
		setTitleName(titleName);
	}

	public void setwelcomelePanel(JPanel welcomele) {
		this.welcome = welcomele;
	}
	
	public JPanel getwelcomelePanel() {
		return welcome;
	}
	
	public void setTitleName(JLabel titleName) {
		this.titleName = titleName;
	}
	
	public JLabel getTitleName() {
		return titleName;
	}
}
