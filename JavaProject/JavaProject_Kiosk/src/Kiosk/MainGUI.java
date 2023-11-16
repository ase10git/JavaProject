package Kiosk;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/* 키오스크의 GUI를 생성하는 클래스
 * 메인 화면, 메뉴 화면, 결제화면
 * 
 */
public class MainGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	static final int frameWidth = 800;
	static final int frameHeight = 900;
	static Font kfont = new Font("", Font.PLAIN, 18);
	int numOfMenu = 4;
	
	JPanel takeOutOrEat;
	JPanel menuGUI;
	JPanel creditGUI;
	JPanel basketGUI;
	
	// 메인 프레임 생성 =============================================================
	public MainGUI() {
		
		super("mainFrame");
		setLayout(null);
		
		// 메인 화면 환영 문구(상시 고정 패널)
		JPanel mainTitle = new JPanel(new BorderLayout());
		mainTitle.setBounds(0, 0, 800, 100);
		mainTitle.setBackground(Color.PINK);
		mainTitle.setVisible(true);
		
		JLabel titleName = new JLabel("<html><body style='text-align: center'>부평 휴게소<br>부평 휴게소에 온 것을 환영합니다!</html>");
		titleName.setSize(200, 100);
		titleName.setFont(kfont);
		titleName.setHorizontalAlignment(JLabel.CENTER);
		titleName.setHorizontalTextPosition(JLabel.CENTER);
		
		mainTitle.add("Center", titleName);
		add(mainTitle);
		
		// 패널 추가
		addTakeOrEatPanel();
		addMenuPanel();
		addCreditPanel();
		addBasketPanel();
		
		// 패널 표시
		takeOutOrEat = getTakeOrEatPanel();
		menuGUI = getMenuPanel();
		creditGUI = getCreditPanel();
		basketGUI = getBasketPanel();
		
		takeOutOrEat.setVisible(true);
		menuGUI.setVisible(false);
		creditGUI.setVisible(false);
		basketGUI.setVisible(false);
		
		// 메인 프레임 크기 설정
		setBounds(100, 100, frameWidth, frameHeight);
		setVisible(true);
		setBackground(Color.WHITE);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
	} // end mainGUI ============================================================
	
	// 매장 이용 방법 선택 (JPanel takeOutOrEat) ========================================
	public void addTakeOrEatPanel() {
		
		takeOutOrEat = new JPanel(null);
		takeOutOrEat.setVisible(true);
		takeOutOrEat.setBounds(0, 100, 800, 800);
		
		// 버튼 및 안내 문구 추가
		JLabel info = new JLabel("매장 이용 방법을 선택해주세요.");
		JButton takeOut = new JButton("포장");	
		JButton eat = new JButton("매장에서 식사");
		
		takeOut.setBounds(0, 500, frameWidth/2, 300);
		takeOut.setFont(kfont);
		
		eat.setBounds(frameWidth/2, 500, frameWidth/2, 300);
		eat.setFont(kfont);
		
		info.setBounds(frameWidth/2-150, 400, 300, 100);
		info.setFont(kfont);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setHorizontalTextPosition(JLabel.CENTER);
		
		// 매장이용 버튼 액션 추가
		ActionListener takeEatButtonAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				takeOutOrEat.setVisible(false);
				
				// 메뉴 패널 등장
				menuGUI.setVisible(true);
				// 바구니 패널 등장
				basketGUI.setVisible(true);
			}
		};
		
		// 버튼 설정 및 추가
		takeOut.addActionListener(takeEatButtonAction);
		eat.addActionListener(takeEatButtonAction);
		
		takeOutOrEat.add(info);
		takeOutOrEat.add(takeOut);
		takeOutOrEat.add(eat);
		
		// JPanel 객체 저장
		add(takeOutOrEat);
		setTakeOrEatPanel(takeOutOrEat);
	} // end add TakeOrEatPanel ===============================================
	
	// 메뉴 패널 추가(JPanel menuGUI) ==============================================
	public void addMenuPanel() {
		
		menuGUI = new JPanel(null);
		menuGUI.setBounds(0, 100, frameWidth, 550);
		menuGUI.setBackground(Color.WHITE);
		menuGUI.setVisible(false);
		
		// 메뉴 버튼 추가
		JButton[] menubt = new JButton[numOfMenu];
		menubt[0] = new JButton("분식");
		menubt[1] = new JButton("일식");
		menubt[2] = new JButton("한식");
		menubt[3] = new JButton("양식");
		
		// 메뉴 화면들 추가
		JPanel[] menu = new JPanel[numOfMenu];
		menu[0] = addMenu1();
		menu[1] = addMenu2();
		menu[2] = addMenu3();
		menu[3] = addMenu4();
		
		// 메뉴 버튼 액션 추가
		ActionListener menuButtonAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "분식") {
					menu[0].setVisible(true);
					menu[1].setVisible(false);
					menu[2].setVisible(false);
					menu[3].setVisible(false);
				} else if (e.getActionCommand() == "일식") {
					menu[0].setVisible(false);
					menu[1].setVisible(true);
					menu[2].setVisible(false);
					menu[3].setVisible(false);
				} else if (e.getActionCommand() == "한식") {	
					menu[0].setVisible(false);
					menu[1].setVisible(false);
					menu[2].setVisible(true);
					menu[3].setVisible(false);
				} else if (e.getActionCommand() == "양식") {
					menu[0].setVisible(false);
					menu[1].setVisible(false);
					menu[2].setVisible(false);
					menu[3].setVisible(true);
				} 
			}
		};

		// 버튼 설정 및 버튼 추가
		for (int i = 0; i < menubt.length; i++) {
			menubt[i].setBounds(i*100, 0, 100, 100);
			menubt[i].setFont(kfont);
			menuGUI.add(menubt[i]);
			menubt[i].addActionListener(menuButtonAction);
		}	
	
		// 메뉴 패널 객체 저장
		add(menuGUI);
		setMenuPanel(menuGUI);
	} // end addMenuGUI -------------------------------------------------------
	
	// 메뉴 종류 화면 추가 ---------------------------------------------------------
	public JPanel addMenu1() { // 분식
		
		
		JPanel menu1 = new JPanel();
		menu1.setLayout(null);
		
		String menu_snack[] = {"김밥","참치김밥","치즈김밥","쫄면","떡볶이","떡볶이","떡볶이"};
		
		JButton[] bt_snack = new JButton[menu_snack.length];
		bt_snack[0] = new JButton(new ImageIcon("src/Kiosk/Images/김밥.png"));
		bt_snack[1] = new JButton("라면");
		bt_snack[2] = new JButton("쫄면");		
		bt_snack[3] = new JButton("떡볶이");
		bt_snack[4] = new JButton("떡볶이2");
		bt_snack[5] = new JButton("떡볶이2");
		bt_snack[6] = new JButton("떡볶이2");
		
		
		TextField num[] = new TextField[menu_snack.length];
		JLabel snackName[] = new JLabel[menu_snack.length];
		Button minus[] = new Button[menu_snack.length];
		Button plus[] = new Button[menu_snack.length];
		JButton ok[] = new JButton[menu_snack.length];
		
		
		
		
		for(int i = 0; i< menu_snack.length; i++) {
			
			//bt_snack[i] = new JButton(menu_snack[i]);
			if(i<5) {
				bt_snack[i].setBounds(20+i*150,30,130,130);
			}else if(i<10) {
				bt_snack[i].setBounds(20+(i-5)*150,250,130,130);
			}
			
			
			
			
			snackName[i] = new JLabel(menu_snack[i]);
			snackName[i].setBounds(bt_snack[i].getX()+40,bt_snack[i].getY()-25,115,20);
			
			num[i] = new TextField("0");
			num[i].setBackground(Color.white);
			num[i].setBounds(bt_snack[i].getX()+40,bt_snack[i].getY()+140,50,20);
			
			minus[i] = new Button("-");
			minus[i].setBounds(bt_snack[i].getX()+10,num[i].getY(),20,20);
			minus[i].setEnabled(true);
			
			plus[i] = new Button("+");
			plus[i].setBounds(bt_snack[i].getX()+100,num[i].getY(),20,20);
			plus[i].setEnabled(true);
			
			ok[i] = new JButton("확인");
			ok[i].setBounds(bt_snack[i].getX()+15,num[i].getY()+30,100,20);
			
			
			menu1.add(ok[i]);
			menu1.add(bt_snack[i]);
			menu1.add(snackName[i]);
			menu1.add(num[i]);
			menu1.add(minus[i]);
			menu1.add(plus[i]);
		}
		
		
	
		
		
		
		menu1.setBounds(0, 100, 800, 700);
		menu1.setBackground(Color.LIGHT_GRAY);
		menu1.setVisible(true);
		
		menuGUI.add(menu1);
		return menu1;
	}
	
	public JPanel addMenu2() { // 일식
		
		JPanel menu2 = new JPanel();
		menu2.setLayout(null);
		
		String menu_japan[] = {"초밥","우동","돈까스","모밀","튀김"};
		
		JButton[] bt_japan = new JButton[5];
		bt_japan[0] = new JButton(new ImageIcon("src/Kiosk/Images/김밥.png"));
		bt_japan[1] = new JButton("라면");
		bt_japan[2] = new JButton("쫄면");		
		bt_japan[3] = new JButton("떡볶이");
		bt_japan[4] = new JButton("떡볶이2");
		
		
		TextField num[] = new TextField[bt_japan.length];
		JLabel snackName[] = new JLabel[bt_japan.length];
		Button minus[] = new Button[bt_japan.length];
		Button plus[] = new Button[bt_japan.length];
		JButton ok[] = new JButton[bt_japan.length];
		
		
		
		
		for(int i = 0; i< bt_japan.length; i++) {
			
			//bt_snack[i] = new JButton(menu_snack[i]);
			bt_japan[i].setBounds(20+i*150,30,130,130);
			
			
			snackName[i] = new JLabel(menu_japan[i]);
			snackName[i].setBounds(bt_japan[i].getX()+40,bt_japan[i].getY()-25,115,20);
			
			num[i] = new TextField("0");
			num[i].setBackground(Color.white);
			num[i].setBounds(bt_japan[i].getX()+40,bt_japan[i].getY()+160,50,20);
			
			minus[i] = new Button("-");
			minus[i].setBounds(bt_japan[i].getX()+10,num[i].getY(),20,20);
			minus[i].setEnabled(true);
			
			plus[i] = new Button("+");
			plus[i].setBounds(bt_japan[i].getX()+100,num[i].getY(),20,20);
			plus[i].setEnabled(true);
			
			
			
			menu2.add(bt_japan[i]);
			menu2.add(snackName[i]);
			menu2.add(num[i]);
			menu2.add(minus[i]);
			menu2.add(plus[i]);
		}
		
		
	
		menu2.setBounds(0, 100, 800, 700);
		menu2.setBackground(Color.LIGHT_GRAY);
		menu2.setVisible(true);
		
		menuGUI.add(menu2);
		return menu2;
	}
	
	public JPanel addMenu3() { // 한식
		
		JPanel menu3 = new JPanel(new GridLayout(3, 2));
		
		menu3.setBounds(0, 100, 800, 700);
		menu3.setBackground(Color.PINK);
		menu3.setVisible(false);
		
		//양식 메뉴 생성
		JButton[] menuWestern = new JButton[4];
		menuWestern[0] = new JButton("스파게티");
		menuWestern[1] = new JButton("피자");
		menuWestern[2] = new JButton("햄버거");
		menuWestern[3] = new JButton("스프");

		for (int i = 0; i < menuWestern.length; i++) {
			menuWestern[i].setFont(kfont);
			menu3.add(menuWestern[i]);
		}
			
		menuGUI.add(menu3);
		return menu3;
	}
	
	public JPanel addMenu4() { // 한식
		
		JPanel menu4 = new JPanel(new GridLayout(3, 2));
		
		menu4.setBounds(0, 100, 800, 700);
		menu4.setBackground(Color.CYAN);
		menu4.setVisible(false);
		
		//한식 메뉴 생성
		JButton[] menuKorean = new JButton[4];
		menuKorean[0] = new JButton("비빔밥");
		menuKorean[1] = new JButton("불고기");
		menuKorean[2] = new JButton("갈비탕");
		menuKorean[3] = new JButton("육개장");

		for (int i = 0; i < menuKorean.length; i++) {
			menuKorean[i].setFont(kfont);
			menu4.add(menuKorean[i]);
		}
		
		menuGUI.add(menu4);
		return menu4;
	}
	// end addMenus ==========================================================
	
	// 장바구니 패널 추가(JPanel basketGUI) ========================================
	public void addBasketPanel() {
		
		basketGUI = new JPanel(null);
		basketGUI.setBounds(0, 650, frameWidth, 250);
		basketGUI.setVisible(false);
		basketGUI.setBackground(Color.MAGENTA);	
		
		// 선택한 메뉴 title, 리스트, 버튼 추가
		JLabel basketTitle = new JLabel("선택한 메뉴");
		basketTitle.setFont(kfont);
		basketTitle.setBounds(10, 0, 150, 50);
		basketTitle.setHorizontalTextPosition(JLabel.LEFT);
			
		JTextArea chosenMenu = new JTextArea();		
		JButton back = new JButton("처음으로 돌아가기");
		JButton payment = new JButton("결제");
		
		payment.setFont(kfont);
		payment.setBounds(600, 80, 190, 140);
		back.setFont(kfont);
		back.setBounds(600, 0, 190, 80);
		
		ActionListener basketButtonAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == back) {
					basketGUI.setVisible(false);
					creditGUI.setVisible(false);
					takeOutOrEat.setVisible(true);
				} else if (e.getSource() == payment) {
					menuGUI.setVisible(false);
					basketGUI.setVisible(false);
					creditGUI.setVisible(true);
				}
			}
		};
		
		payment.addActionListener(basketButtonAction);
		back.addActionListener(basketButtonAction);
		
		basketGUI.add(basketTitle);
		basketGUI.add(chosenMenu);
		basketGUI.add(payment);
		basketGUI.add(back);
		
		// 장바구니 객체 추가
		add(basketGUI);
		setBasketPanel(basketGUI);
	} // end addBasketGUI =======================================================
	
	// 결제창 추가(JPanel creditGUI) ===============================================
	public void addCreditPanel() {
		creditGUI = new JPanel(null);
		creditGUI.setBounds(100, 100, 500, 700);
		creditGUI.setVisible(false);
		creditGUI.setBackground(Color.WHITE);	
		
		// 결제창 객체 추가
		add(creditGUI);
		setCreditPanel(creditGUI);
	} // end addCreditGUI =======================================================
	
	// 패널 객체 저장 및 불러오기 =======================================================
	public void setTakeOrEatPanel(JPanel takeOutOrEat) {
		this.takeOutOrEat = takeOutOrEat;
	}
	
	public JPanel getTakeOrEatPanel() {
		return takeOutOrEat;
	}
	
	public void setMenuPanel(JPanel menuGUI) {
		this.menuGUI = menuGUI;
	}
	
	public JPanel getMenuPanel() {
		return menuGUI;
	}
	
	public void setCreditPanel(JPanel creditGUI) {
		this.creditGUI = creditGUI;
	}
	
	public JPanel getCreditPanel() {
		return creditGUI;
	}
	
	public void setBasketPanel(JPanel basketGUI) {
		this.basketGUI = basketGUI;
	}
	
	public JPanel getBasketPanel() {
		return basketGUI;
	}
	// end setter getter ======================================================
	
}
