package Kiosk2;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

/* 키오스크의 GUI를 생성하는 클래스
 * 메인 화면, 메뉴 화면, 결제화면
 * 
 */
public class MainGUI2 extends JFrame {
	private static final long serialVersionUID = 1L;
	static final int frameWidth = 800;
	static final int frameHeight = 900;
	static Font bfont = new Font("", Font.PLAIN, 25);
	static Font kfont = new Font("", Font.PLAIN, 18);
	static Font mfont = new Font("", Font.PLAIN, 16);
	
	static int numOfMenu = 4;
	static int numOfPayment = 4;
	static String imageAddr = "src/Kiosk/Images/";
		
	// 메인 프레임 생성
	public MainGUI2() {
		super("휴게소_키오스크");
		setLayout(null);
		
		// 메인 프레임 크기 설정
		setBounds(100, 100, frameWidth, frameHeight);
		setVisible(false);
		setBackground(Color.WHITE);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}