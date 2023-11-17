package Kiosk;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

/* 키오스크의 GUI를 생성하는 클래스
 * 메인 화면, 메뉴 화면, 결제화면
 * 
 */
public class MainGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	static final int frameWidth = 800;
	static final int frameHeight = 900;
	static Font bfont = new Font("", Font.PLAIN, 25);
	static Font kfont = new Font("", Font.PLAIN, 18);
	static Font mfont = new Font("", Font.PLAIN, 16);
	
	int numOfMenu = 4;
	int numOfPayment = 4;
	Color background = new Color(255, 210, 89);
	String imageAddr = "src/Kiosk/Images/";
	String announce1 = "원하시는 음식의 수량을 [+](더하기) 버튼으로 추가 후, [확인] 버튼을 눌러주세요.";
	String announce2 = "메뉴를 제거하려면 제거할 메뉴를 선택하고, [선택한 메뉴 제거] 버튼을 눌러주세요.";
	
	private JPanel mainTitle;
	private JPanel takeOutOrEat;
	private JPanel menuGUI;
	private JPanel[] menu;
	private JPanel creditGUI;
	private JPanel[] creditMethod;
	private JPanel basketGUI;
	private JPanel orderEndGUI;
	private JList<String> basketList;
	private DefaultListModel<String> model;
	private JScrollPane basketPane;
	
	String payment = "";
	
	// 메인 프레임 생성
	public MainGUI() {
		
		super("mainFrame");
		setLayout(null);
		
		// 메인 화면 환영 문구(상시 고정 패널)
		JPanel mainTitle = new JPanel(new BorderLayout());
		mainTitle.setBounds(0, 0, 800, 100);
		mainTitle.setBackground(Color.WHITE);
		mainTitle.setVisible(true);
		
		JLabel titleName = new JLabel("<html><body style='text-align: center'>부평 휴게소<br>부평 휴게소에 온 것을 환영합니다!</html>");
		titleName.setSize(200, 100);
		titleName.setFont(bfont);
		titleName.setHorizontalAlignment(JLabel.CENTER);
		titleName.setHorizontalTextPosition(JLabel.CENTER);
		
		mainTitle.add("Center", titleName);
		setMainTitlePanel(mainTitle);
		add(mainTitle);
		
		// 패널 추가
		addTakeOrEatPanel();
		addMenuPanel();
		addBasketPanel();
		addCreditPanel();
		
		// 패널 표시
		takeOutOrEat = getTakeOrEatPanel();
		menuGUI = getMenuPanel();
		basketGUI = getBasketPanel();
		creditGUI = getCreditPanel();	
		
		takeOutOrEat.setVisible(true);
		menuGUI.setVisible(false);
		basketGUI.setVisible(false);
		creditGUI.setVisible(false);
		
		// 메인 프레임 크기 설정
		setBounds(100, 100, frameWidth, frameHeight);
		setVisible(true);
		setBackground(Color.WHITE);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
	} // end mainGUI ============================================================
	
	// 매장 이용 방법 선택 (JPanel takeOutOrEat)
	public void addTakeOrEatPanel() {
		
		takeOutOrEat = new JPanel(null);
		takeOutOrEat.setVisible(true);
		takeOutOrEat.setBounds(0, 100, 800, 800);
		takeOutOrEat.setBackground(background);
		
		// 버튼 및 안내 문구 추가
		JLabel info = new JLabel("매장 이용 방법을 선택해주세요.");
		info.setBounds(frameWidth/2-350/2, 350, 350, 100);
		info.setFont(bfont);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setHorizontalTextPosition(JLabel.CENTER);
			
		// 버튼 설정 및 추가
		JButton[] takeEatbt = new JButton[2];
		String[] name = {"포장", "매장에서 식사"};
		for (int i = 0; i < takeEatbt.length; i++) {
			takeEatbt[i] = new JButton(new ImageIcon(imageAddr+name[i]+".png"));
			takeEatbt[i].setBounds(40+(frameWidth/2)*i, 460, 300, 280);
			takeEatbt[i].setFont(bfont);
			takeEatbt[i].addActionListener(takeEatButtonAction);
			takeOutOrEat.add(takeEatbt[i]);
		}

		takeOutOrEat.add(info);
		
		// JPanel 객체 저장
		add(takeOutOrEat);
		setTakeOrEatPanel(takeOutOrEat);
	} // end add TakeOrEatPanel ===============================================
	
	// 메뉴 패널 추가(JPanel menuGUI)
	public void addMenuPanel() {
		
		menuGUI = new JPanel(null);
		menuGUI.setBounds(0, 0, frameWidth, 650);
		menuGUI.setBackground(background);
		menuGUI.setVisible(false);
		
		// 메뉴 버튼 추가
		JButton[] menubt = new JButton[numOfMenu];
		String[] name = {"분식", "일식", "한식", "양식"};
		for (int i = 0; i < name.length; i++) {
			menubt[i] = new JButton(name[i]);
		}
		
		// 메뉴 화면들 추가
		addMenus();
		
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
	public JPanel[] addMenus() { 
		
		JPanel[] menu = new JPanel[numOfMenu];
		String[][] menu_list = {
				{"김밥","참치김밥","치즈김밥","쫄면","떡볶이","로제 떡볶이","순대","핫도그","라면","오뎅탕"},   // 분식
				{"모듬초밥","우동","돈까스","치즈돈까스","모밀","덴푸라","돈코츠라멘","에비동","차슈동"},		// 일식
				{"비빔밥","불고기","갈비탕","육개장","김치찌개","된장찌개","순두부찌개","부대찌개","칼국수"},		// 한식
				{"토마토파스타","크림파스타","리조또","조각피자","찹스테이크","리코타치즈샐러드"}				// 양식
		};
	
		int[][] menu_price = {
				{3000,4000,4000,5000,5000,5500,4000,2500,3500,5500},// 분식가격
				{15000,5500,8000,9000,5000,7000,8000,8000,8000},	// 일식 가격
				{10000,8000,11000,9000,6000,6500,6000,7000,6500},	// 한식 가격
				{8000,8000,7500,3000,11000,10000}					// 양식 가격
		};
		
		// 메뉴 카테고리별 패널 설정
		for (int i = 0; i < menu.length; i++) {
			menu[i] = new JPanel(null);
			JButton[] bt = new JButton[menu_list[i].length];
			TextField num[] = new TextField[menu_list[i].length];
			JLabel snackName[] = new JLabel[menu_list[i].length];
			Button minus[] = new Button[menu_list[i].length];
			Button plus[] = new Button[menu_list[i].length];
			JButton ok[] = new JButton[menu_list[i].length];
						
			// 메뉴 카테고리별 버튼 추가
			for (int j = 0; j < menu_list[i].length; j++) {
				bt[j] = new JButton(new ImageIcon(imageAddr+menu_list[i][j]+".jpg"));
			
				if (j<5) {
					bt[j].setBounds(25+j*150,70,130,130);
				} else if(j<10) {
					bt[j].setBounds(25+(j-5)*150,340,130,130);
				}
				String snackNamePrice = "<html><body style='text-align: center'>"+menu_list[i][j]+"<br>"+menu_price[i][j]+" 원</html>";
				snackName[j] = new JLabel(snackNamePrice);
				snackName[j].setBounds(bt[j].getX()+10,bt[j].getY()-50,115,50);
				snackName[j].setFont(kfont);
				snackName[j].setHorizontalAlignment(JLabel.CENTER);
				snackName[j].setHorizontalTextPosition(JLabel.CENTER);
				
				num[j] = new TextField("0");
				num[j].setBackground(Color.white);
				num[j].setBounds(bt[j].getX()+45,bt[j].getY()+135,40,30);
				num[j].setFont(kfont);
				num[j].setEditable(false);
				
				minus[j] = new Button("-");
				minus[j].setBounds(bt[j].getX()+10,num[j].getY()+5,25,20);
				minus[j].setEnabled(true);
				minus[j].setFont(kfont);
				
				plus[j] = new Button("+");
				plus[j].setBounds(bt[j].getX()+95,num[j].getY()+5,25,20);
				plus[j].setEnabled(true);
				plus[j].setFont(kfont);
				
				ok[j] = new JButton("확인");
				ok[j].setBounds(bt[j].getX()+25,num[j].getY()+35,80,30);
				ok[j].setFont(kfont);
				ok[j].setHorizontalAlignment(JLabel.CENTER);
				ok[j].setHorizontalTextPosition(JLabel.CENTER);
				
				// 메뉴에 버튼, 메뉴이름, 메뉴 수량 컴포넌트 추가	
				menu[i].add(bt[j]);
				menu[i].add(snackName[j]);
				menu[i].add(num[j]);
				
				int menuNum = j; int menuCat = i;
				// "+", "-", "확인" 버튼 액션 리스너
				ActionListener plusMinusAction = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						TextField jf = num[menuNum];
						int nums = Integer.parseInt(jf.getText());// 중복 메뉴의 수량 추가
						StringBuilder sb = new StringBuilder();
						
						if (e.getActionCommand() == "+") { // 수량 추가
							jf.setText(String.valueOf(nums+1));
						} else if (e.getActionCommand() == "-") { // 수량 감소
							if (nums>0) {
								jf.setText(String.valueOf(nums-1));
							} else jf.setText("0");
						} else if (e.getActionCommand() == "확인") { // model 에 추가
							String prevStr = "원하시는";
							String prevStr2 = "메뉴를 제거";
							boolean prevStrRemain = (model.getElementAt(model.getSize()-1).contains(prevStr) 
													||model.getElementAt(model.getSize()-1).contains(prevStr2));
							if (prevStrRemain) {
								model.removeAllElements();
							}
							sb.append(menu_list[menuCat][menuNum]).append(" : ").append(jf.getText()).append(" 개");
							model.addElement(sb.toString());
							jf.setText("0");
							sb.setLength(0);
						}
					}
				};

				minus[j].addActionListener(plusMinusAction);
				plus[j].addActionListener(plusMinusAction);
				ok[j].addActionListener(plusMinusAction);
				
				// 수량 증감, 확인 버튼 추가
				menu[i].add(minus[j]);
				menu[i].add(plus[j]);
				menu[i].add(ok[j]);
			}
			
			menu[i].setBounds(0, 100, 800, 700);
			menu[i].setBackground(Color.LIGHT_GRAY);
			if (i==0) menu[i].setVisible(true);
			else menu[i].setVisible(false);
		
			menuGUI.add(menu[i]);
		}
		// 저장된 메뉴 패널 배열 반환 및 객체 저장
		setMenus(menu);
		return menu;
	} // end addMenus ========================================================

	// 장바구니 패널 추가(JPanel basketGUI) ========================================
	public void addBasketPanel() {
		
		basketGUI = new JPanel(null);
		basketGUI.setBounds(0, 650, frameWidth, 250);
		basketGUI.setVisible(false);
		basketGUI.setBackground(background);	
		
		// 선택한 메뉴 title 추가
		JLabel basketTitle = new JLabel("선택한 메뉴");
		basketTitle.setFont(kfont);
		basketTitle.setBounds(10, 0, 150, 50);
		basketTitle.setHorizontalTextPosition(JLabel.LEFT);

		// 선택한 메뉴 리스트와 스크롤 팬 생성
		addBasketList();
		
		// 총 합 텍스트
		JLabel totalPrice = new JLabel();
		String[] disp = {"총 합 : ", "0", " 원"};
		
		totalPrice.setFont(kfont);
		totalPrice.setHorizontalTextPosition(JLabel.RIGHT);
		totalPrice.setText(disp[0]+disp[1]+disp[2]);
		totalPrice.setBounds(430, 0, 200, 50);
		
		// 버튼 추가
		JButton[] basketbt = new JButton[4];
		String[] name = {"결제", "처음으로 돌아가기", "모두 제거", "선택한 메뉴 제거"};
		int[] removebtWidth = {120, 160};
		int[] removebtX = {130, 130+removebtWidth[0]};
		for (int i = 0; i < basketbt.length; i++) {
			basketbt[i] = new JButton(name[i]);	
			basketbt[i].setHorizontalTextPosition(JLabel.CENTER);
			if (i<2) {
				basketbt[i].setBounds(600, i*140, 190, 140-i*60);
				basketbt[i].setFont(kfont);
			} else {
				basketbt[i].setBounds(removebtX[i-2], 5, removebtWidth[i-2], 40);
				basketbt[i].setFont(mfont);
			}
		}

		// 버튼에 액션 추가 및 basketGUI에 추가
		for (int i = 0; i < basketbt.length; i++) {
			basketbt[i].addActionListener(basketButtonAction);
			basketGUI.add(basketbt[i]);
		}
		
		// 객체들을 basketGUI에 추가
		basketGUI.add(basketTitle);
		basketGUI.add(getBasketPane());
		basketGUI.add(totalPrice);
	
		// 장바구니 객체 추가
		add(basketGUI);	
		setBasketPanel(basketGUI);
	} // end addBasketGUI --------------------------------------------------------
		
	public void addBasketList() {	
		model = new DefaultListModel<String>();
		model.addElement(announce1);
		model.addElement(announce2);
		
		basketList = new JList<String>(model);
		basketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		basketList.setFont(kfont);
		
		// 가격 텍스트창 객체용 ScrollPane 생성
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane basketPane = new JScrollPane(basketList, v, h);
		basketPane.setBounds(10, 50, 580, 150);
		
		// 객체들 저장
		setBasketList(basketList);
		setModel(model);
		setBasketPane(basketPane);
	}
	
	// 결제창 추가(JPanel creditGUI) ===============================================
	public void addCreditPanel() {
		
		creditGUI = new JPanel(null);
		creditGUI.setBounds(0, 100, frameWidth, frameHeight-100);
		creditGUI.setVisible(false);
		creditGUI.setBackground(background);
		
		// 결제방법 안내 추가
		JLabel paymentMethod = new JLabel("결제 방법을 선택해주세요.");
		paymentMethod.setFont(bfont);
		paymentMethod.setBounds(frameWidth/2-150, 250, 300, 50);
		paymentMethod.setHorizontalAlignment(JLabel.CENTER);
		
		// 뒤로 가기 버튼 추가 => 메뉴 선택으로 돌아감
		JButton back = new JButton("뒤로 돌아가기");
		back.setFont(kfont);
		back.setBounds(600, 690, 190, 80);
		
		// 결제방법 버튼과 패널 추가 => creditGUI에 추가
		JButton[] paybt = new JButton[numOfPayment];
		String[] name = {"신용/체크/e음 카드", "현금", "페이앱", "기프트카드/쿠폰"};
		
		for (int i = 0; i < numOfPayment; i++) {
			paybt[i] = new JButton(name[i]);
			if (i%2==0) {
				paybt[i].setBounds(frameWidth/2-300, 330+i*100, 250, 150);
			} else {
				paybt[i].setBounds(frameWidth/2+50, 230+i*100, 250, 150);
			}
			paybt[i].setFont(bfont);
		}
		
		// 결제 방법 패널 생성
		addCreditMethod();
		
		// 메뉴로 돌아가기 버튼에 액션 추가
		back.addActionListener(returnToMenu);

		// 버튼 설정 및 creditGUI에 버튼 객체 추가
		for (int i = 0; i < numOfPayment; i++) {
			paybt[i].addActionListener(paybtActionListener);
			creditGUI.add(paybt[i]);
		}
		
		JLabel totalPrice = new JLabel();
		totalPrice.setBounds(frameWidth/2-300/2, 0, 300, 100);
		totalPrice.setText("<html><body style='text-align: center'>결제할 금액<br>"+"원</html>");
		totalPrice.setHorizontalTextPosition(JLabel.CENTER);
		totalPrice.setHorizontalAlignment(JLabel.CENTER);
		totalPrice.setFont(new Font("", Font.PLAIN, 34));
		
		// creditGUI에 객체 추가
		creditGUI.add(paymentMethod);
		creditGUI.add(back);
		creditGUI.add(totalPrice);
		
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
	
	// 결제 방법 별 결제창 생성 ------------------------------------------------------------
	public JPanel[] addCreditMethod() {
		JPanel[] creditMethod = new JPanel[numOfPayment];
		JLabel[] creditImage = new JLabel[numOfPayment];
		String[] imageName = {"카드", "현금", "페이앱", "기프티콘"};
		
		for (int i = 0; i < numOfPayment; i++) {
			creditMethod[i] = new JPanel(null);
			creditMethod[i].setBounds(0, 100, frameWidth, frameHeight-100);
			creditMethod[i].setBackground(background);
			creditMethod[i].setVisible(false);
			
			creditImage[i] = new JLabel(new ImageIcon(imageAddr+imageName[i]+".png"));
			creditImage[i].setBounds(frameWidth/2-250, 90, 500, frameHeight-100);
			creditImage[i].setVisible(true);
			
			if(i==0) {
				JLabel a = new JLabel("카드를 넣어주세요");
				a.setBounds(frameWidth/2-300/2, 80, 300, 100);
				a.setFont(bfont);
				a.setHorizontalAlignment(JLabel.CENTER);
				a.setHorizontalTextPosition(JLabel.CENTER);
				creditMethod[i].add(a);
				creditMethod[i].add(creditImage[i]);
			
				creditMethod[i].revalidate();
				JLabel b = new JLabel("결제가 완료되었습니다. 카드를 뽑아주세요...");
				a.setBounds(frameWidth/2-500/2, 80, 500, 100);
				a.setFont(bfont);
				a.setHorizontalAlignment(JLabel.CENTER);
				a.setHorizontalTextPosition(JLabel.CENTER);
				//creditMethod[i].remove(a);
				creditMethod[i].add(b);
				
			}
			else if(i==1) {
				JLabel a = new JLabel("현금은 카운터에서 결제해주세요");
				a.setBounds(frameWidth/2-400/2, 80, 400, 100);
				a.setFont(bfont);
				a.setHorizontalAlignment(JLabel.CENTER);
				a.setHorizontalTextPosition(JLabel.CENTER);
				creditMethod[i].add(a);
				creditMethod[i].add(creditImage[i]);
				//creditMethod[i].remove(a);
				
			}
			else if(i==2) {
				JLabel a = new JLabel("페이앱의 QR코드를 리더기에 인식해주세요");
				a.setBounds(frameWidth/2-600/2, 80, 600, 100);
				a.setFont(bfont);
				a.setHorizontalAlignment(JLabel.CENTER);
				a.setHorizontalTextPosition(JLabel.CENTER);
				creditMethod[i].add(a);
				creditMethod[i].add(creditImage[i]);
				
				JLabel b = new JLabel("결제가 완료되었습니다. 잠시만 기다려주세요...");
				a.setBounds(frameWidth/2-500/2, 80, 500, 100);
				a.setFont(bfont);
				a.setHorizontalAlignment(JLabel.CENTER);
				a.setHorizontalTextPosition(JLabel.CENTER);
				//creditMethod[i].remove(a);
				creditMethod[i].add(b);
			}
			else if(i==3) {
				JLabel a = new JLabel("<html>기프트콘 및 쿠폰의 바코드를<br>리더기에 인식해주세요</html>");
				a.setBounds(frameWidth/2-700/2, 80, 500, 100);
				a.setFont(bfont);
				a.setHorizontalAlignment(JLabel.CENTER);
				a.setHorizontalTextPosition(JLabel.CENTER);
				creditMethod[i].add(a);
				creditMethod[i].add(creditImage[i]);
				
				JLabel b = new JLabel("결제가 완료되었습니다. 잠시만 기다려주세요...");
				a.setBounds(frameWidth/2-500/2, 80, 500, 100);
				a.setFont(bfont);
				a.setHorizontalAlignment(JLabel.CENTER);
				a.setHorizontalTextPosition(JLabel.CENTER);
				//creditMethod[i].remove(a);
				creditMethod[i].add(b);
			}
			
			creditMethod[i].add(backButton(creditMethod[i]));
			//creditMethod[i].add(paymentConfirm());
			add(creditMethod[i]);
		}
		// 결제 방법 창 객체 저장 및 반환
		setCreditMethodPanel(creditMethod);
		return creditMethod;
	}// end addCreditMethod ===============================================
	
//	public void delay() {
//		try {
//            Thread.sleep(3000);
//            System.out.println("3초 경과");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//	}
	
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
		home.addActionListener(returnToHome);

		takeOutOrEat.add(info);
		
		// 주문완료 객체 추가
		add(orderEndGUI);
		setOrderEndPanel(orderEndGUI);
	}	
	
	
	// ActionListener 클래스들 ==============================================================================
	// 매장이용 버튼 액션 추가 
	ActionListener takeEatButtonAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			takeOutOrEat.setVisible(false);
			mainTitle.setVisible(false);
			// 메뉴, 바구니 패널 등장
			menuGUI.setVisible(true);
			basketGUI.setVisible(true);
		}
	}; // end takeEatButtonAction =======================================================================
	
	// 메뉴 버튼 액션 추가
	ActionListener menuButtonAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			menu = getMenus();
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
	}; // end menuButtonAction ============================================================================
	
	// payment , back에 액션 추가
	ActionListener basketButtonAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "처음으로 돌아가기") {
				mainTitle.setVisible(true);
				menuGUI.setVisible(false);
				basketGUI.setVisible(false);
				creditGUI.setVisible(false);
				takeOutOrEat.setVisible(true);
				
				model.removeAllElements();
				model.addElement(announce1);
				model.addElement(announce2);
				
			} else if (e.getActionCommand() == "결제") {
				mainTitle.setVisible(true);
				menuGUI.setVisible(false);
				basketGUI.setVisible(false);
				creditGUI.setVisible(true);
			} else if (e.getActionCommand() == "모두 제거") {
				model.removeAllElements();
				model.addElement(announce1);
				model.addElement(announce2);
			} else if (e.getActionCommand() == "선택한 메뉴 제거") {
				if (basketList.getSelectedIndex()!=-1) {
					if (model.getSize()==1) {
						model.removeAllElements();
						model.addElement(announce1);
						model.addElement(announce2);
					} else if (model.getSize()>1) {
						model.remove(basketList.getSelectedIndex());
					}
				}
			}
		}
	}; // end basketButtonAction ============================================================================
	
	// 메뉴로 돌아가기 액션
	ActionListener returnToMenu = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "뒤로 돌아가기") {
				mainTitle.setVisible(false);
				basketGUI.setVisible(true);
				menuGUI.setVisible(true);
				creditGUI.setVisible(false);
			}
		}
	};// end returnToMenu =================================================================================
	
	// 결제 방법 버튼에 액션 추가
	ActionListener paybtActionListener = new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			creditMethod = getCreditMethodPanel();
			creditGUI.setVisible(false);
			basketGUI.setVisible(false);
			if(e.getActionCommand() == "신용/체크/e음 카드") {
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
	}; //end paybtActionListener =================================================================================
	
	ActionListener returnToHome = new ActionListener() {		
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
	}; // end returnToHome =================================================================================
	
	// 패널 객체 저장 및 불러오기 =======================================================
	public void setMainTitlePanel(JPanel mainTitle) {
		this.mainTitle = mainTitle;
	}
	
	public JPanel getMainTitlePanel() {
		return mainTitle;
	}
	
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
	
	public void setMenus(JPanel[] menu) {
		this.menu = menu;
	}
	
	public JPanel[] getMenus() {
		return menu;
	}
	
	public void setCreditPanel(JPanel creditGUI) {
		this.creditGUI = creditGUI;
	}
	
	public JPanel getCreditPanel() {
		return creditGUI;
	}
	
	public void setCreditMethodPanel(JPanel[] creditMethod) {
		this.creditMethod = creditMethod;
	}
	
	public JPanel[] getCreditMethodPanel() {
		return creditMethod;
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
	
	public void setBasketPane(JScrollPane basketPane) {
		this.basketPane = basketPane;
	}
	
	public JScrollPane getBasketPane() {
		return basketPane;
	}
	
	public void setBasketList(JList<String> basketList) {
		this.basketList = basketList;
	}
	
	public JList<String> setBasketList() {
		return basketList;
	}
	
	public void setModel(DefaultListModel<String> model) {
		this.model = model;
	}
	
	public DefaultListModel<String> getModel() {
		return model;
	}

	// end setter getter ======================================================
	
	// 데이터 호출하기
	// 총 가격 가져오기
	public void getTotalPrice() {

	}// end getTotalPrice =======================================================
	
}