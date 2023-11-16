package Kiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

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
	int numOfPayment = 4;
	
	JPanel takeOutOrEat;
	JPanel menuGUI;
	JPanel creditGUI;
	JPanel basketGUI;
	JPanel orderEndGUI;
	JTextArea basketText;
	JScrollPane basketPane;
	
	String payment = "";
	
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
		
		JPanel menu1 = new JPanel(new GridLayout(3, 2));

		menu1.setBounds(0, 100, 800, 700);
		menu1.setBackground(Color.BLUE);
		menu1.setVisible(true);
		
		// 분식 메뉴 버튼 생성
		JButton[] menuSnack = new JButton[4];
		menuSnack[0] = new JButton("김밥");
		menuSnack[1] = new JButton("라면");
		menuSnack[2] = new JButton("쫄면");
		menuSnack[3] = new JButton("떡볶이");

		for (int i = 0; i < menuSnack.length; i++) {
			menuSnack[i].setFont(kfont);
			menu1.add(menuSnack[i]);
		}
		
		menuGUI.add(menu1);
		return menu1;
	}
	
	public JPanel addMenu2() { // 일식
		
		JPanel menu2 = new JPanel(new GridLayout(3, 2));

		menu2.setBounds(0, 100, 800, 700);
		menu2.setBackground(Color.YELLOW);	
		menu2.setVisible(false);
		
		// 일식 메뉴 버튼 생성
		JButton[] menuJapanese = new JButton[4];
		menuJapanese[0] = new JButton("초밥");
		menuJapanese[1] = new JButton("우동");
		menuJapanese[2] = new JButton("돈까스");
		menuJapanese[3] = new JButton("모밀");

		for (int i = 0; i < menuJapanese.length; i++) {
			menuJapanese[i].setFont(kfont);
			menu2.add(menuJapanese[i]);
		}
		
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
		
		JButton payment = new JButton("결제");
		JButton back = new JButton("처음으로 돌아가기");
		payment.setFont(kfont);
		payment.setBounds(600, 0, 190, 140);
		back.setFont(kfont);
		back.setBounds(600, 140, 190, 80);
		
		ActionListener basketButtonAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "처음으로 돌아가기") {
					menuGUI.setVisible(false);
					basketGUI.setVisible(false);
					creditGUI.setVisible(false);
					takeOutOrEat.setVisible(true);
				} else if (e.getActionCommand() == "결제") {
					menuGUI.setVisible(false);
					basketGUI.setVisible(false);
					creditGUI.setVisible(true);
				}
			}
		};
		
		payment.addActionListener(basketButtonAction);
		back.addActionListener(basketButtonAction);	
				
		// 가격 텍스트창 객체 불러오기
		addbastketArea();
		JTextArea basketText = getBasketText();
		//basketGUI.add(basketText);
		//basketText.setVisible(true);
		
		// 가격 텍스트창 객체용 ScrollPane 생성
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
		JScrollPane textScroll = new JScrollPane(basketText, v, h);
		textScroll.setBounds(10, 50, 580, 150);
		
	
		
		// 객체들을 basketGUI에 추가
		basketGUI.add(basketTitle);
		basketGUI.add(chosenMenu);
		basketGUI.add(payment);
		basketGUI.add(back);
		basketGUI.add(textScroll);	

		// 장바구니 객체 추가
		add(basketGUI);	
		setBasketPanel(basketGUI);

	} // end addBasketGUI --------------------------------------------------------
	
	// 장바구니 메뉴 창 추가 --------------------------------------------------------
	public void addbastketArea() {
		
		basketText = new JTextArea(15, 20);
		
		basketText.setVisible(false);
		basketText.setEditable(false);
		basketText.setFont(kfont);
		
		basketText.setText("장바구니 테스트용 에리어 \n");
		basketText.append("지금까지 고른 메뉴의 이름과 가격을 확인할 수 있습니다. \n");
		basketText.append("메뉴를 삭제할 수 있습니다. \n");
		basketText.append("\n");
		basketText.append("메뉴를 삭제할 수 있습니다.2 \n");
		basketText.append("장바구니 테스트용 에리어2 \n");
		basketText.append("지금까지 고른 메뉴의 이름과 가격을 확인할 수 있습니다.2 \n");
		basketText.append("메뉴를 삭제할 수 있습니다.3 \n");	
		basketText.append("장바구니 테스트용 에리어3 \n");
		basketText.append("지금까지 고른 메뉴의 이름과 가격을 확인할 수 있습니다.3 \n");
		basketText.append("메뉴를 삭제할 수 있습니다.3 \n");

		// 가격 텍스트창 객체용 ScrollPane 생성
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
		JScrollPane basketPane = new JScrollPane(basketText, v, h);
		basketPane.setBounds(10, 50, 580, 150);
		
		// basketText 객체를 새로 지정
		//setBasketText(basketText);
		setBasketPane(basketPane);
	}
	
	// 결제창 추가(JPanel creditGUI) ===============================================
	public void addCreditPanel() {
		
		creditGUI = new JPanel(null);
		creditGUI.setBounds(0, 350, frameWidth, frameHeight-350);
		creditGUI.setVisible(false);
		creditGUI.setBackground(Color.WHITE);	
		
		JLabel paymentMethod = new JLabel("결제 방법을 선택해주세요.");
		paymentMethod.setFont(kfont);
		paymentMethod.setBounds(frameWidth/2-150, 0, 300, 50);
		paymentMethod.setHorizontalAlignment(JLabel.CENTER);
		
		// 뒤로 가기 버튼 추가 => 메뉴 선택으로 돌아감
		JButton back = new JButton("뒤로 돌아가기");
		back.setFont(kfont);
		back.setBounds(600, 440, 190, 80);
		
		// 결제방법 버튼 추가 => creditGUI에 추가
		JButton[] paybt = new JButton[numOfPayment];
		paybt[0] = new JButton("신용/체크/지역화폐 카드");
		paybt[1] = new JButton("현금");
		paybt[2] = new JButton("페이앱");
		paybt[3] = new JButton("기프트카드/쿠폰");
		
		paybt[0].setBounds(frameWidth/2-300, 80, 250, 150);
		paybt[1].setBounds(frameWidth/2+50, 80, 250, 150);
		paybt[2].setBounds(frameWidth/2-300, 280, 250, 150);
		paybt[3].setBounds(frameWidth/2+50, 280, 250, 150);
		paybt[0].setFont(kfont);
		paybt[1].setFont(kfont);
		paybt[2].setFont(kfont);
		paybt[3].setFont(kfont);
		
		// 결제 방법 화면들 추가 => 결제방법 버튼에서 이어짐
		JPanel[] creditMethod = new JPanel[numOfPayment];
		creditMethod[0] = addCredit1();
		creditMethod[1] = addCredit2();
		creditMethod[2] = addCredit3();
		creditMethod[3] = addCredit4();

		
		// 결제 방법 버튼에 액션 추가
		ActionListener paybtActionListener = new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				creditGUI.setVisible(false);
				basketGUI.setVisible(false);
				if(e.getActionCommand() == "신용/체크/지역화폐 카드") {
					creditMethod[0].setVisible(true);
					creditMethod[1].setVisible(false);
					creditMethod[2].setVisible(false);
					creditMethod[3].setVisible(false);
				} else if (e.getActionCommand() == "현금") {
					creditMethod[0].setVisible(false);
					creditMethod[1].setVisible(true);
					creditMethod[2].setVisible(false);
					creditMethod[3].setVisible(false);
				} else if (e.getActionCommand() == "페이앱") {	
					creditMethod[0].setVisible(false);
					creditMethod[1].setVisible(false);
					creditMethod[2].setVisible(true);
					creditMethod[3].setVisible(false);
				} else if (e.getActionCommand() == "기프트카드/쿠폰") {
					creditMethod[0].setVisible(false);
					creditMethod[1].setVisible(false);
					creditMethod[2].setVisible(false);
					creditMethod[3].setVisible(true);
				} 
			}
		};
		
		// 메뉴로 돌아가기 버튼에 액션 추가
		back.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "뒤로 돌아가기") {
					basketGUI.setVisible(true);
					menuGUI.setVisible(true);
					creditGUI.setVisible(false);
				}
			}
		});
		
		for (int i = 0; i < numOfPayment; i++) {
			paybt[i].addActionListener(paybtActionListener);
			creditGUI.add(paybt[i]);
		}
		
		// creditGUI에 객체 추가
		creditGUI.add(paymentMethod);
		creditGUI.add(back);
		
		// 결제창 객체 추가
		add(creditGUI);
		setCreditPanel(creditGUI);
	} // end addCreditGUI ------------------------------------------------------
	
	// 결제 방법 별 결제창에서 뒤로 가기 버튼 추가
	public JButton backButton(JPanel credit) {
		// 뒤로 가기 버튼 추가 => 메뉴 선택으로 돌아감
		JButton back = new JButton("뒤로 돌아가기");
		back.setFont(kfont);
		back.setBounds(600, 690, 190, 80);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				credit.setVisible(false);
				creditGUI.setVisible(true);
			}
		});
		return back;
	}
	
	// 결제 금액 확인용 창 생성
	public JPanel paymentConfirm() {
		
		JPanel payConfirm = new JPanel();
		payConfirm.setBounds(frameWidth/2-700/2, 50, 700, 300);
		payConfirm.setBackground(Color.BLACK);
		payConfirm.setVisible(true);
		// 결제할 금액 띄우기
		
		return payConfirm;
	}
	
	// 결제 확인 후 새 창 띄우기
	/*
	public boolean paymentCheck(String payment) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		try {
			if (input.readLine().equals(payment)) {
				return true;
			}
		} catch (IOException e) {
			System.out.println("금액이 정확하지 않습니다. 결제를 취소합니다.");
		}

		return false;
	}
	*/
	
	// 결제 방법 별 결제창 생성 ------------------------------------------------------------
	public JPanel addCredit1() {
		
		JPanel credit1 = new JPanel(null);		
		credit1.setBounds(0, 100, frameWidth, frameHeight-100);
		credit1.setBackground(Color.CYAN);
		credit1.setVisible(false);
			
		// 카드 결제 안내 그림 추가
		//
		
		credit1.add(backButton(credit1));
		credit1.add(paymentConfirm());
		add(credit1);
		return credit1;
	}
	public JPanel addCredit2() {
		
		JPanel credit2 = new JPanel(null);	
		credit2.setBounds(0, 100, frameWidth, frameHeight-100);
		credit2.setBackground(Color.PINK);
		credit2.setVisible(false);
		
		// 현금 결제 안내 그림 추가
		//
		credit2.add(backButton(credit2));
		credit2.add(paymentConfirm());
		add(credit2);
		return credit2;
	}
	public JPanel addCredit3() {
		
		JPanel credit3 = new JPanel(null);		
		credit3.setBounds(0, 100, frameWidth, frameHeight-100);
		credit3.setBackground(Color.YELLOW);
		credit3.setVisible(false);
		
		// 페이앱 결제 안내 그림 추가
		//
		credit3.add(backButton(credit3));
		credit3.add(paymentConfirm());
		add(credit3);
		return credit3;
	}
	public JPanel addCredit4() {
		
		JPanel credit4 = new JPanel(null);		
		credit4.setBounds(0, 100, frameWidth, frameHeight-100);
		credit4.setBackground(Color.GREEN);
		credit4.setVisible(false);
		
		// 상품권, 기프티콘 결제 안내 그림 추가
		//
		credit4.add(backButton(credit4));
		credit4.add(paymentConfirm());
		add(credit4);
		return credit4;
	}// end addCreditConfirmPanel===============================================
	
	// 주문 완료 패널 추가
	public void addOrderEndPanel() {
		
		JPanel orderEndGUI = new JPanel(null);
		orderEndGUI.setVisible(false);
		orderEndGUI.setBounds(0, 100, 800, 800);
		
		// 주문 완료 및 홈버튼 추가
		JLabel info = new JLabel("<html><body style='text-align: center'>결제가 완료되었습니다.<br>주문 번호표를 확인해주세요.</html>");
		JButton home = new JButton("처음 화면으로 돌아가기");
		
		home.setBounds(0, 500, frameWidth/2, 300);
		home.setFont(kfont);
		
		info.setBounds(frameWidth/2-150, 400, 300, 100);
		info.setFont(kfont);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setHorizontalTextPosition(JLabel.CENTER);
		
		// 홈버튼 액션 추가
		home.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				takeOutOrEat.setVisible(true);
				menuGUI.setVisible(false);
				creditGUI.setVisible(false);
				basketGUI.setVisible(false);
				orderEndGUI.setVisible(false);
				
				// 저장되었던 메뉴 객체 및 가격 리셋 필요
				//
			}
		});
		
		takeOutOrEat.add(info);
		
		// 주문완료 객체 추가
		add(orderEndGUI);
		setOrderEndPanel(orderEndGUI);
	}	
	
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
	
	public void setOrderEndPanel(JPanel orderEndGUI) {
		this.orderEndGUI = orderEndGUI;
	}
	
	public JPanel getOrderEndPanel() {
		return orderEndGUI;
	}
	
	public void setBasketText(JTextArea basketText) {
		this.basketText = basketText;
	}
	
	public JTextArea getBasketText() {
		return basketText;
	}
	
	public void setBasketPane(JScrollPane basketPane) {
		this.basketPane = basketPane;
	}
	
	public JScrollPane getBasketPane() {
		return basketPane;
	}
	// end setter getter ======================================================
	
}
