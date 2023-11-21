package Kiosk;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
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
import javax.swing.Timer;

/* 키오스크의 GUI를 생성하는 클래스
 * 메인 화면, 메뉴 화면, 결제화면
 */
public class MainGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// 프레임 사이즈
	static final int frameWidth = 800;
	static final int frameHeight = 900;
	
	// 글자 폰트 사이즈
	static Font bfont = new Font("", Font.BOLD, 25);
	static Font kfont = new Font("", Font.BOLD, 18);
	static Font mfont = new Font("", Font.BOLD, 16);
	
	int numOfMenu = 4; // 메뉴 카테고리 개수
	int numOfPayment = 4; // 결제 방법 개수
	int homeCount = 10; // 홈으로 돌아가기 카운트
	Color background = new Color(255, 210, 89); // 배경 색상
	Color light_red = new Color(255, 110, 98); // 밝은 빨강
	Color light_background = new Color(255, 239, 196); // 밝은 배경색
	
	String imageAddr = "src/Kiosk/Images/"; // 로컬 이미지 주소
	String announce1 = "원하시는 음식의 수량을 [+](더하기) 버튼으로 추가 후, [확인] 버튼을 눌러주세요.";
	String announce2 = "메뉴를 제거하려면 제거할 메뉴를 선택하고, [선택한 메뉴 제거] 버튼을 눌러주세요.";

	boolean paymentEnd = false; // 결제 완료 여부
	boolean isHome = false; // takeOrEat 패널이 보이는지 여부
	boolean isCredit = false; // 결제 패널이 보이는지 여부
	boolean isCreditMethod = false; // 각 결제 방법 패널이 보이는지 여부
	boolean isOrderEnd = false; // 주문 완료 패널이 보이는지 여부
	
	Total total = new Total(); // 총 가격 계산 클래스
	
	private JPanel mainTitle; // 맨 처음 환영 문구 패널
	private JPanel takeOrEat; // 매장 이용 방법 선택 패널
	private JPanel menuGUI; // 메뉴 패널
	private JPanel[] menu; // 카테고리별 메뉴 종류 패널
	private JPanel creditGUI; // 결제 패널
	private JPanel[] creditMethod; // 결재 방법 패널
	private JPanel orderEndGUI; // 주문 완료 패널
	private JList<String> basketList; // 장바구니 보여주는 JList
	private DefaultListModel<String> model; // 선택한 메뉴를 String으로 저장하고 JList에서 보여줄 model
	private HashMap<String, Integer> bucketlist; // 선택한 메뉴의 이름과 수를 저장하는 HashMap
	private JScrollPane basketPane; // JList의 스크롤 팬
	
	Food_Data food = new Food_Data(); // 메뉴 정보를 저장한 클래스
	String[][] menu_list = food.getMenu_list(); // 메뉴 이름
	int[][] menu_price = food.getMenu_price(); // 메뉴 가격
	HashMap<String, Integer> menuAndPrice = food.getMenuAndPrice(); // 메뉴 이름과 가격을 저장한 HashMap
	
	//=========================================================================================================================================================================
	//
	// 메인 프레임 생성
	// 클래스 생성자에서 메인 프레임을 생성하고, 모든 패널과 객체들을 생성하도록 메소드를 호출한다.
	public MainGUI() {
		
		super("mainFrame");
		setLayout(null);
		
		// 메인 화면 환영 문구(상시 고정 패널)
		// 화면 맨 위에 배치할 패널
		JPanel mainTitle = new JPanel(new BorderLayout());
		mainTitle.setBounds(0, 0, 800, 100);
		mainTitle.setBackground(Color.WHITE);
		mainTitle.setVisible(true);
		
		// 메인 화면 환영 글
		JLabel titleName = new JLabel("<html><body style='text-align: center'>부평 휴게소<br>부평 휴게소에 온 것을 환영합니다!</html>");
		titleName.setSize(300, 100);
		titleName.setFont(bfont);
		titleName.setHorizontalAlignment(JLabel.CENTER);
		titleName.setHorizontalTextPosition(JLabel.CENTER);
		
		mainTitle.add("Center", titleName);
		setMainTitlePanel(mainTitle);
		add(mainTitle);
		
		// 패널 추가
		addTakeOrEatPanel(); // 매장 이용 선택 패널 추가
		addMenuPanel(); // 메뉴 패널 추가
		
		// 패널 표시
		takeOrEat = getTakeOrEatPanel();
		menuGUI = getMenuPanel();
		
		takeOrEat.setVisible(true);
		menuGUI.setVisible(false); // 메뉴 패널은 매장 이용 패널에서 버튼을 누르면 보이게 설정
		
		// 메인 프레임 크기 설정
		setBounds(100, 100, frameWidth, frameHeight);
		setVisible(true);
		setBackground(Color.WHITE);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false); // 창 크기 변경 불가
		
	} // end mainGUI
	
	//=========================================================================================================================================================================
	//
	// 매장 이용 방법 선택
	// 음식을 포장할건지, 아니면 먹고 갈 건지 선택
	// MainGUI()에서 호출
	public void addTakeOrEatPanel() {
		// takeOrEat 패널 생성
		takeOrEat = new JPanel(null);
		takeOrEat.setVisible(true);
		takeOrEat.setBounds(0, 100, 800, 800);
		takeOrEat.setBackground(background);
		
		// 버튼 및 안내 문구 추가
		JLabel info = new JLabel("매장 이용 방법을 선택해주세요.");
		info.setBounds(frameWidth/2-400/2, 350, 400, 100);
		info.setFont(bfont);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setHorizontalTextPosition(JLabel.CENTER);
		
		takeOrEat.add(info); // 패널에 안내 문구 추가
		
		// 버튼 설정
		JButton[] takeEatbt = new JButton[2];
		String[] name = {"포장", "매장에서 식사"};
		for (int i = 0; i < takeEatbt.length; i++) {
			takeEatbt[i] = new JButton(new ImageIcon(imageAddr+name[i]+".png")); // 버튼은 이미지로 채움
			takeEatbt[i].setBounds(40+(frameWidth/2)*i, 460, 300, 280);
			takeEatbt[i].setFont(bfont);
			takeEatbt[i].setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 2)); // 버튼 테두리를 선으로 설정
			
			takeEatbt[i].addActionListener(takeEatButtonAction); // 버튼 액션 리스너 추가
			takeOrEat.add(takeEatbt[i]); // 패널에 버튼 추가
		}

		// takeOrEat을 프레임에 추가하고 객체 저장
		add(takeOrEat);
		setTakeOrEatPanel(takeOrEat);
		
		// ComponentListener 추가
		takeOrEatComponentLS();
	} // end addTakeOrEatPanel *************************************************************************************************************************
	
	// 매장이용 버튼 액션 추가 
	// 포장/매장에서 식사 버튼을 누르면 takeOrEat을 안보이게 하고
	// 메뉴 화면을 보이게 설정
	// addTakeOrEatPanel() 에서 호출
	ActionListener takeEatButtonAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {		
			takeOrEat.setVisible(false);
			mainTitle.setVisible(false);
			// 메뉴 패널 등장
			menuGUI.setVisible(true);
		}
	};
	
	// takeOrEat 패널의 ComponenetListener 추가
	// takeOrEat을 홈으로 설정
	// 홈으로 돌아왔을 때 이전에 사용한 데이터 저장 객체들 리셋
	// addTakeOrEatPanel() 에서 호출
	private void takeOrEatComponentLS() {
		takeOrEat.addComponentListener(new ComponentAdapter() {		
			@Override
			public void componentShown(ComponentEvent e) {
				// takeOrEat 패널이 뜨면 홈
				isHome = true;
				// model에 저장된 데이터 리셋
				getModel().removeAllElements();
				getModel().addElement(announce1);
				getModel().addElement(announce2);
				
				// 선택했던 메뉴들 리셋
				getBucketlist().clear();
				
				// 메뉴 가격 총 합 리셋
				total.setTotal(0);								
				JLabel totalPrice = (JLabel)menuGUI.getComponent(2);				
				totalPrice.setText("총 합 : "+String.valueOf(total.getTotal())+" 원"); // 변경한 total을 새로 화면에 보여줌
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// takeOrEat 패널이 사라지면 홈이 아님
				isHome = false;
			}
		});	
	} // end takeOrEatComponentLS
	
	//=========================================================================================================================================================================
	//
	// 메뉴 패널 추가(JPanel menuGUI)
	// 음식들을 카테고리별로, 각 카테고리에 여러 음식이 존재
	// 메뉴를 선택하고 장바구니에 담는 패널
	// MainGUI() 에서 호출
	public void addMenuPanel() {
		// menuGUI 패널 생성
		menuGUI = new JPanel(null);
		menuGUI.setBounds(0, 0, frameWidth, frameHeight);
		menuGUI.setBackground(background);
		menuGUI.setVisible(false);

		// 장바구니 항목 추가
		addBasket();
		
		// 카테고리별 메뉴 화면들 추가
		addMenus();

		// 카테고리 메뉴 버튼 추가
		JButton[] menubt = new JButton[numOfMenu];
		String[] name = {"분식", "일식", "한식", "양식"};
		for (int i = 0; i < name.length; i++) {
			menubt[i] = new JButton(name[i]);
			menubt[i].setBounds(i*100, 0, 100, 100);
			menubt[i].setBackground(background);
			menubt[i].setBorderPainted(false);
			menubt[i].setFont(bfont);
			
			menubt[i].addActionListener(menuButtonAction); // 버튼을 누르면 메뉴 카테고리 창이 바뀜
			menuGUI.add(menubt[i]);
		}	
	
		// 메뉴 패널 객체 저장
		add(menuGUI);
		setMenuPanel(menuGUI);
	} // end addMenuGUI *************************************************************************************************************************
	
	// 메뉴 버튼 액션 추가
	// addMenuPanel() 에서 호출
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
	}; // end menuButtonAction *************************************************************************************************************************
	
	// 메뉴 종류 화면 추가
	// 각 카테고리 메뉴 별 음식 사진, 중복 추가 및 삭제, 장바구니 담기 확인 버튼 추가
	// addMenuPanel() 에서 호출
	public void addMenus() { 
		// 카테고리별 메뉴 패널 생성
		JPanel[] menu = new JPanel[numOfMenu];
		
		// 음식 이름 데이터와 가격은 Food_Data food 에서 가져옴
		//String[][] menu_list = food.getMenu_list(); // 메뉴 이름
		//int[][] menu_price = food.getMenu_price(); // 메뉴 가격
		
		// 카테고리별 메뉴 패널 설정
		for (int i = 0; i < menu.length; i++) { // 카테고리는 4개
			menu[i] = new JPanel(null);
			menu[i].setBounds(0, 100, frameWidth, frameHeight-100);
			menu[i].setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
			menu[i].setBackground(background);
			if (i==0) menu[i].setVisible(true); // 처음은 분식 화면이 보이도록 설정
			else menu[i].setVisible(false); // 나머지는 안보이게 설정
			
			JButton[] bt = new JButton[menu_list[i].length]; // 음식 이미지를 담을 버튼 객체
			TextField num[] = new TextField[menu_list[i].length]; // 중복 추가 개수를 보여주는 텍스트 필드
			JLabel snackName[] = new JLabel[menu_list[i].length]; // 음식 이름과 가격 표시
			Button minus[] = new Button[menu_list[i].length]; // 중복 개수 감소
			Button plus[] = new Button[menu_list[i].length]; // 중복 개수 추가
			JButton ok[] = new JButton[menu_list[i].length]; // 장바구니에 담기
						
			// 카테고리별 메뉴 버튼 설정
			for (int j = 0; j < menu_list[i].length; j++) {
				// 음식 이미지를 버튼에 넣기
				bt[j] = new JButton(new ImageIcon(imageAddr+menu_list[i][j]+".jpg"));
				bt[j].setBorder(null);
				if (j<5) { // 한 줄에 5개씩
					bt[j].setBounds(25+j*150,70,130,130);
				} else if(j<10) {
					bt[j].setBounds(25+(j-5)*150,340,130,130);
				}
				
				// 음식 이름과 가격 표시
				String snackNamePrice = "<html><body style='text-align: center'>"+menu_list[i][j]+"<br>"+menu_price[i][j]+" 원</html>";
				snackName[j] = new JLabel(snackNamePrice);
					int namepos = bt[j].getX() + (bt[j].getWidth())/2 - 150/2;
				snackName[j].setBounds(namepos,bt[j].getY()-50,150,50);
				snackName[j].setFont(kfont);
				snackName[j].setHorizontalAlignment(JLabel.CENTER);
				snackName[j].setHorizontalTextPosition(JLabel.CENTER);
				
				// 중복 추가 개수를 보여주는 텍스트 필드
				num[j] = new TextField("0");
				num[j].setBackground(Color.white);
					int numpos = bt[j].getX() + ((int)bt[j].getBounds().getWidth())/2 - 40/2;
				num[j].setBounds(numpos,bt[j].getY()+135,40,30);
				num[j].setFont(kfont);
				num[j].setEditable(false);
				
				// 중복 개수 감소 버튼
				minus[j] = new Button("-");
				minus[j].setBounds(bt[j].getX()+10,num[j].getY()+5,25,20);
				minus[j].setEnabled(true);
				minus[j].setFont(kfont);
				
				// 중복 개수 추가 버튼
				plus[j] = new Button("+");
					int pluspos = bt[j].getX() + bt[j].getWidth() - (10 + 25);
				plus[j].setBounds(pluspos,num[j].getY()+5,25,20);
				plus[j].setEnabled(true);
				plus[j].setFont(kfont);
				
				// 장바구니에 담기 버튼
				ok[j] = new JButton("확인");
					int okpos = bt[j].getX() + (bt[j].getWidth())/2 - 80/2;
				ok[j].setBounds(okpos,num[j].getY()+35,80,30);
				ok[j].setBackground(light_background);
				ok[j].setBorder(BorderFactory.createRaisedBevelBorder());
				ok[j].setFont(kfont);
				ok[j].setHorizontalAlignment(JLabel.CENTER);
				ok[j].setHorizontalTextPosition(JLabel.CENTER);			
				
				// 카테고리별 메뉴 패널에 버튼, 메뉴이름, 메뉴 수량 컴포넌트 추가	
				menu[i].add(bt[j]);
				menu[i].add(snackName[j]);
				menu[i].add(num[j]);
				
				// 버튼 액션 리스너에서 사용할 인덱스 저장(내부클래스에서 사용할 지역변수의 final 판정을 위한 저장)
				int menuNum = j; int menuCat = i;
				
				// "+", "-", "확인" 버튼 액션 리스너
				ActionListener plusMinusAction = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						TextField jf = num[menuNum]; // 중복 수량을 보여줄 텍스트 필드 호출
						int nums = Integer.parseInt(jf.getText()); // 중복된 메뉴의 수량
						StringBuilder sb = new StringBuilder(); // model에 데이터를 저장할 StringBuilder
						
						if (e.getActionCommand() == "+") { // 수량 추가
							jf.setText(String.valueOf(nums+1)); // 텍스트 필드의 숫자를 1 증가
						} else if (e.getActionCommand() == "-") { // 수량 감소
							if (nums>0) {
								jf.setText(String.valueOf(nums-1)); // 텍스트 필드의 숫자를 1 감소
							} else jf.setText("0"); // 텍스트 필드의 숫자가 음수면 0으로 설정
						} else if (e.getActionCommand() == "확인") { // 장바구니(bucketlist, model)에 데이터 저장
							String prevStr = "원하시는";
							String prevStr2 = "메뉴를 제거";
							boolean prevStrRemain = (model.getElementAt(model.getSize()-1).contains(prevStr) // 안내 문구가 model에 남아있는지 판단
													||model.getElementAt(model.getSize()-1).contains(prevStr2));
							if (nums != 0) { // 텍스트 필드에 숫자가 0이 아닐 때만 장바구니에 추가
								if (prevStrRemain) model.removeAllElements(); // model에 남아있는 안내 문구 삭제
								
								// 선택한 메뉴와 개수를 bucketlist에 저장(중복 제거 및 중복된 메뉴 카운트 증가)
								bucketlist.put(menu_list[menuCat][menuNum], bucketlist.getOrDefault(menu_list[menuCat][menuNum], 0)+nums);
								
								// model에 표시할 데이터 생성
								sb.append(menu_list[menuCat][menuNum]).append(" : ").append(bucketlist.get(menu_list[menuCat][menuNum])).append(" 개");							
	
								// 중복으로 선택해서 추가 확인된 메뉴 표시를 한 줄에 업데이트해서 출력
								modelUpdate(menu_list[menuCat][menuNum], sb.toString(), model);
								
								// 총 가격 계산
								total.cal(menu_price[menuCat][menuNum], nums);								
								JLabel totalPrice = (JLabel)menuGUI.getComponent(2); // 총 합을 업데이트 해서 표시		
								totalPrice.setText("총 합 : "+String.valueOf(total.getTotal())+" 원");
	
								// 텍스트 필드의 메뉴 중복 수량 초기화
								jf.setText("0");
								sb.setLength(0);
							}
						}
					}
				}; // end plusMinusAction

				// 버튼에 액션 리스너 추가
				minus[j].addActionListener(plusMinusAction);
				plus[j].addActionListener(plusMinusAction);
				ok[j].addActionListener(plusMinusAction);
				
				// 수량 증감, 확인 버튼을 카테고리별 메뉴 패널에 추가
				menu[i].add(minus[j]);
				menu[i].add(plus[j]);
				menu[i].add(ok[j]);
			}
			// 메뉴 패널에 카테고리별 메뉴 추가
			menuGUI.add(menu[i]);
		}
		// 저장된 메뉴 패널 배열 저장
		setMenus(menu);
	} // end addMenus *************************************************************************************************************************

	// 장바구니 항목들 추가
	// 메뉴 화면 아래에 장바구니 항목들을 추가
	// addMenuPanel() 에서 호출
	public void addBasket() {
		
		// 선택한 메뉴 title 추가
		JLabel basketTitle = new JLabel("선택한 메뉴");
		basketTitle.setFont(kfont);
		basketTitle.setBounds(10, 650, 150, 50);
		basketTitle.setHorizontalTextPosition(JLabel.LEFT);

		// 선택한 메뉴 리스트와 스크롤 팬 생성
		// bucketlist, model, basketList, scrollPane 생성
		addBasketList();
		
		// 총 합 텍스트
		JLabel totalPrice = new JLabel();
		
		totalPrice.setFont(kfont);
		totalPrice.setHorizontalTextPosition(JLabel.RIGHT);
		totalPrice.setText("총 합 : 0 원");
		totalPrice.setBounds(430, 650, 200, 50);
		
		// 버튼 추가
		JButton[] basketbt = new JButton[4];
		String[] name = {"결제", "처음으로 돌아가기", "모두 제거", "선택한 메뉴 제거"};
		int[] removebtWidth = {120, 160}; // "모두 제거", "선택한 메뉴 제거" 버튼의 폭
		int[] removebtX = {130, 130+removebtWidth[0]}; // "모두 제거", "선택한 메뉴 제거" 버튼의 x 위치
		
		for (int i = 0; i < basketbt.length; i++) {
			basketbt[i] = new JButton(name[i]);	
			basketbt[i].setHorizontalTextPosition(JLabel.CENTER);
			basketbt[i].setBorder(BorderFactory.createRaisedBevelBorder());
			if (i<2) {
				basketbt[i].setBounds(600, 650+i*140, 190, 140-i*60);
				basketbt[i].setFont(kfont);
			} else {
				basketbt[i].setBounds(removebtX[i-2], 650+5, removebtWidth[i-2], 40);
				basketbt[i].setFont(mfont);
				basketbt[i].setBackground(new Color(107, 212, 231));
			}
		}
		basketbt[0].setBackground(light_red);
		basketbt[1].setBackground(light_background);

		// 객체들을 menuGUI에 추가
		menuGUI.add(basketTitle);
		menuGUI.add(getBasketPane());
		menuGUI.add(totalPrice); // 총 합이 변경될 때 마다 새로 업데이트 해줘야 함 -------> menuGUI.getComponent(2)
		
		// 버튼에 액션 추가 및 menuGUI에 추가
		for (int i = 0; i < basketbt.length; i++) {
			basketbt[i].addActionListener(basketButtonAction);
			menuGUI.add(basketbt[i]);
		}
	} // end addBasket() *************************************************************************************************************************
	
	// bucketlist, model, basketList, scrollPane 생성
	// addBasket() 에서 호출
	public void addBasketList() {	
		// 선택한 메뉴를 표시해줄 데이터를 저장할 model 생성
		model = new DefaultListModel<String>();
		model.addElement(announce1); // 처음 사용 시엔 메뉴 사용 방법을 안내하는 문구만 보이도록 설정
		model.addElement(announce2);

		// 메뉴를 보여줄 basketList 생성
		// 사용자가 실제로 데이터를 보고 상호작용 하는 객체
		basketList = new JList<String>(model);
		basketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		basketList.setFont(kfont);
		
		// 가격 텍스트창 객체용 ScrollPane 생성
		// 메뉴가 많이 추가되면 basketList를 아래로 내리도록 해줄 스크롤 팬
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane basketPane = new JScrollPane(basketList, v, h);
		basketPane.setBounds(10, 700, 580, 150);	
		
		// 선택한 메뉴와 개수를 저장하는 HashMap 만들기
		HashMap<String, Integer> bucketlist = new HashMap<>();

		// 객체들 저장
		setBasketList(basketList);
		setModel(model);
		setBasketPane(basketPane);
		setBucketlist(bucketlist);
	} // end addBasketList() *************************************************************************************************************************
	
	// model에서 중복된 메뉴의 개수를 증가시키고 처리하는 메소드
	// addMenus() 에서 호출
	public void modelUpdate(String original, String change, DefaultListModel<String> model) {
		// original : 메뉴, change : 추가하거나 업데이트할 메뉴 내용
		if (model.getSize()==0) { // 최초 추가 시엔 새 element를 그대로 추가
			model.addElement(change);
		} else {
			for (int k = 0; k < model.getSize(); k++) { // model 전체 element들을 확인
				if (model.getElementAt(k).contains(original)) { // original(메뉴)이 model에 있는지 확인
					model.setElementAt(change, k); // 이미 존재한다면 element를 새로 업데이트
					break;
				}
				if (k == model.getSize()-1) { // original(메뉴)이 model에 존재하지 않으면
					model.addElement(change); // model에 change(메뉴)를 추가
				}
			}
		}
	} // end modelUpdate *************************************************************************************************************************
	
	// payment , back에 액션 추가
	// addBasket() 에서 호출
	ActionListener basketButtonAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "처음으로 돌아가기") {
				returnToHome(); // takeOrEat 패널을 표시
				if (creditGUI!=null) creditGUI.setVisible(false); // ----------------> 프로그램 최초 실행 시 결제 패널은 결제 버튼을 눌러야만 생성됨
				mainTitle.setVisible(true);

			} else if (e.getActionCommand() == "결제") {
				if (creditGUI==null) addCreditPanel(); // ----------------> 프로그램 최초 실행 시 결제 패널은 결제 버튼을 눌러야만 생성됨
				if (!noOrder()) { // 선택한 메뉴가 존재할 때만 결제창을 표시
					mainTitle.setVisible(true);
					menuGUI.setVisible(false);
					creditGUI.setVisible(true);
				}
				// 메뉴를 바꿨을 때 가격 다시 갱신해주기
				JLabel totalPrice = (JLabel)creditGUI.getComponent(2);				
				totalPrice.setText("<html><body style='text-align: center'>결제할 금액<br>"+String.valueOf(total.getTotal())+"원</html>");
				
			} else if (e.getActionCommand() == "모두 제거") {
				// model의 모든 데이터 제거 및 안내문구 보여주기
				model.removeAllElements();
				model.addElement(announce1);
				model.addElement(announce2);
				
				// 저장해둔 메뉴 제거
				bucketlist.clear();
				// 저장해둔 총 합 제거
				total.setTotal(0);
				
				// 화면에 바뀐 내용 표시하기
				JLabel totalPrice = (JLabel)menuGUI.getComponent(2);				
				totalPrice.setText("총 합 : "+String.valueOf(total.getTotal())+" 원");
				
			} else if (e.getActionCommand() == "선택한 메뉴 제거") {
				 // 선택된 메뉴를 model, bucketlist, total에서 제거하기
				removeSelectedMenu();
				
				// 화면에 바뀐 내용 표시하기
				JLabel totalPrice = (JLabel)menuGUI.getComponent(2);				
				totalPrice.setText("총 합 : "+String.valueOf(total.getTotal())+" 원");
			}
		}
	}; // end basketButtonAction *************************************************************************************************************************
	
	// 주문을 안 넣었을 때 결제 방지
	// basketButtonAction 에서 호출
	private boolean noOrder() {
		DefaultListModel<String> model = getModel(); // model 가져옴
		for (int i = 0; i < model.getSize(); i++) {
			if (model.get(i).contains(announce1) || model.get(i).contains(announce2)) { 
				return true; // model에 안내 문구가 있다면 true를 반환(문구 있음)
			}
		}
		return false; // model에 안내 문구가 없다면 false를 반환(문구 없음)
	} // end noOrder() *************************************************************************************************************************
	
	// 선택된 메뉴를 model, bucketlist, total에서 제거하기
	// basketButtonAction 에서 호출
	public void removeSelectedMenu() {	
		if (basketList.getSelectedIndex()!=-1) { // basketList에서 선택을 했을 때만 실행
			if (model.getSize()==1) {
				// 고른 메뉴가 1개밖에 없다면 model의 모든 정보 제거하고 안내문구 보여주기
				model.removeAllElements();
				model.addElement(announce1);
				model.addElement(announce2);
				// 저장해둔 메뉴 제거
				bucketlist.clear();
				// 저장해둔 총 합 제거
				total.setTotal(0);				
			} else if (model.getSize()>1) {
				// 선택한 위치의 인덱스 가져오기
				int index = basketList.getSelectedIndex();				
				// 선택한 위치의 인덱스에 해당하는 model 내용 가져오기
				String str = model.getElementAt(index);				
				if (!str.contains(announce1) && !str.contains(announce2)) {
					// model 내용 중 메뉴 이름만 가져오기
					for (int i = 0; i < str.length()-1; i++) {
						if(str.charAt(i)==' ' && str.charAt(i+1)==':') {
							str = str.substring(0, i);
							break;
						}
					}				
					// 선택한 메뉴의 가격을 뺀 새 총 합 구하기				
					int newTotal = total.getTotal() - bucketlist.get(str)*menuAndPrice.get(str); // menuAndPrice는 Food_Data에서 가져옴			
					// 선택한 위치의 model 내용 삭제하고, 저장해둔 메뉴와 개수 제거하기
					model.remove(index);				
					bucketlist.remove(str);
					total.setTotal(newTotal);
				}
			}
		}
	} // end removeSelectedMenu()
	
	//=========================================================================================================================================================================
	//
	// 결제창 추가(JPanel creditGUI)
	// 결제 종류를 선택하고 결제를 진행하는 패널
	// basketButtonAction 에서 호출
	public void addCreditPanel() {
		// creditGUI 패널 생성
		creditGUI = new JPanel(null);
		creditGUI.setBounds(0, 100, frameWidth, frameHeight-100);
		creditGUI.setVisible(false);
		creditGUI.setBackground(background);
		
		// 결제방법 안내 추가
		JLabel paymentMethod = new JLabel("결제 방법을 선택해주세요.");
		paymentMethod.setFont(bfont);
		paymentMethod.setBounds(frameWidth/2-350/2, 250, 350, 50);
		paymentMethod.setHorizontalAlignment(JLabel.CENTER);
		
		// 뒤로 가기 버튼 추가 ---------> 메뉴 선택으로 돌아감
		JButton back = new JButton("뒤로 돌아가기");
		back.setFont(kfont);
		back.setBounds(600, 690, 190, 80);
		back.setBackground(light_background);
		back.setBorder(BorderFactory.createRaisedBevelBorder());
		
		// 결제방법 버튼과 결제 방법 별 패널 추가 => creditGUI에 추가
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
			paybt[i].setBackground(light_background);
			paybt[i].setBorder(BorderFactory.createRaisedBevelBorder());
		}
				
		// 메뉴로 돌아가기 버튼에 액션 추가
		back.addActionListener(returnToMenu);

		// 결제할 금액 표시
		JLabel totalPrice = new JLabel();
		totalPrice.setBounds(frameWidth/2-500/2, 0, 500, 300);
		totalPrice.setText("<html><body style='text-align: center'>결제할 금액<br>"+String.valueOf(total.getTotal())+"원</html>");
		totalPrice.setHorizontalTextPosition(JLabel.CENTER);
		totalPrice.setHorizontalAlignment(JLabel.CENTER);
		totalPrice.setFont(new Font("", Font.PLAIN, 34));
		
		// creditGUI에 객체 추가
		creditGUI.add(paymentMethod);
		creditGUI.add(back);
		creditGUI.add(totalPrice);
		
		// 버튼 설정 및 creditGUI에 버튼 객체 추가
		for (int i = 0; i < numOfPayment; i++) {
			paybt[i].addActionListener(paybtActionListener);
			creditGUI.add(paybt[i]);
		}
		
		// 결제창 객체 추가
		add(creditGUI);
		setCreditPanel(creditGUI);
		
		// ComponentListener 추가
		addCreditComponentLS();
	} // end addCreditGUI *************************************************************************************************************************
	
	// 결제 패널이 화면에 보이는지 안보이는지 알려줌
	// addCreditPanel() 에서 호출
	private void addCreditComponentLS() {
		creditGUI.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				// 결제 패널이 떴음을 알려줌
				isCredit = true;
			}
		
			@Override
			public void componentHidden(ComponentEvent e) {
				// 결제 패널이 화면에서 안보임을 알려줌
				isCredit = false;
			}
		});
	} // end addCreditComponentLS *************************************************************************************************************************
	
	// 메뉴로 돌아가기 액션
	// 결제창을 안보이게 설정하고 다시 메뉴 선택 화면을 보여줌
	// addCreditPanel() 에서 호출
	ActionListener returnToMenu = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "뒤로 돌아가기") {
				mainTitle.setVisible(false);
				menuGUI.setVisible(true);
				creditGUI.setVisible(false);
			}
		}
	}; // end returnToMenu *************************************************************************************************************************

	// 결제 방법 버튼에 액션 추가
	// addCreditPanel() 에서 호출
	ActionListener paybtActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(creditMethod==null) addCreditMethod(); // -----------> 프로그램 최초 실행 시 버튼을 눌러야만 creditMethod 창 생성
			if(orderEndGUI == null) addOrderEndPanel(); // -----------> 프로그램 최초 실행 시 버튼을 눌러야만 orderEndGUI 창 생성			
			creditGUI.setVisible(false);	
		
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
	}; //end paybtActionListener *************************************************************************************************************************
	
	// 결제 방법 별 결제창 생성
	// paybtActionListener 에서 호출
	public void addCreditMethod() {
		JPanel[] creditMethod = new JPanel[numOfPayment];
		JLabel[] creditImage = new JLabel[numOfPayment];
		String[] imageName = {"카드", "현금", "페이앱", "기프티콘"};
		String[] creditAnnounce = {"카드를 넣어주세요", "현금은 카운터에서 결제해주세요", 
									"페이앱의 QR코드를 리더기에 인식해주세요", 
									"<html>기프트콘 및 쿠폰의 바코드를<br>리더기에 인식해주세요</html>"};
		
		for (int i = 0; i < numOfPayment; i++) {
			// 결제 방법 별 패널 설정
			creditMethod[i] = new JPanel(null);
			creditMethod[i].setBounds(0, 100, frameWidth, frameHeight-100);
			creditMethod[i].setBackground(background);
			creditMethod[i].setVisible(false);
			
			// 결제 방법 별 패널에서 이미지 추가
			creditImage[i] = new JLabel(new ImageIcon(imageAddr+imageName[i]+".png"));
			creditImage[i].setBounds(frameWidth/2-250, 90, 500, frameHeight-100);
			creditImage[i].setVisible(true);
			
			// 결제 방법 별 결제 안내 문구 추가
			JLabel a = new JLabel(creditAnnounce[i]);
			a.setBounds(frameWidth/2-700/2, 80, 700, 100);
			a.setFont(bfont);
			a.setHorizontalAlignment(JLabel.CENTER);
			a.setHorizontalTextPosition(JLabel.CENTER);
			
			// 객체들을 결제 방법 별 패널에 추가
			creditMethod[i].add(a);
			creditMethod[i].add(creditImage[i]);
			//creditMethod[i].add(backButton(creditMethod[i])); // 버그로 인한 버튼 제거
			creditMethod[i].revalidate();
			
			// 결제 방법 별 패널을 메인 프레임에 추가
			add(creditMethod[i]);
		}
		// 결제 방법 창 객체 저장 및 반환
		setCreditMethodPanel(creditMethod);
		
		// 각 결제 방법 창에 ComponenetListener 추가
		addCreditMethodComponentLS();
	}// end addCreditMethod() *************************************************************************************************************************
	
	// 결제 방법 별 패널인지를 알려줌
	// 결제 임의구현 타이머 creditTimer의 제어를 위한 boolean을 설정하는 메소드
	// addCreditMethod() 에서 호출
	private void addCreditMethodComponentLS() {
		for (int i = 0; i < creditMethod.length; i++) { // 모든 결제 방법별 패널에 컴포넌트 리스너 추가
			creditMethod[i].addComponentListener(new ComponentAdapter() {			
			@Override
				public void componentShown(ComponentEvent e) {
					// 결제 방법 별 패널이 보이는지 알려줌
					isCreditMethod = true;
					// creditTimer 가동
					startCreditTimer();
				}	
			@Override
				public void componentHidden(ComponentEvent e) {
					// 결제 방법 별 패널이 안보이면 알려줌
					isCreditMethod = false;
				}
			});
		}
	} // end addCreditMethodComponentLS *************************************************************************************************************************
	
	// 결제 방법 별 결제창에서 뒤로 가기 버튼 추가
	// addCreditMethod() 에서 호출
	/*
	public JButton backButton(JPanel credit) {
		// 뒤로 가기 버튼 추가 --> menuGUI 패널(메뉴 선택)로 돌아감
		JButton back = new JButton("뒤로 돌아가기");
		back.setFont(kfont);
		back.setBounds(600, 690, 190, 80);
		back.setBackground(light_background);
		back.setBorder(BorderFactory.createRaisedBevelBorder());

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				credit.setVisible(false); // 결제 방법별 창 숨기기
				creditGUI.setVisible(true); // 결제 화면 숨기기
			}
		});
		return back;
	} // end backButton() 
	*/
	//=========================================================================================================================================================================
	//
	// 주문 완료 패널 추가
	// 주문이 완료되고 프로그램을 다시 홈(takeOrEat)으로 이동시키 위한 패널
	// paybtActionListener 에서 호출
	public void addOrderEndPanel() {
		// orderEndGUI 패널 추가
		JPanel orderEndGUI = new JPanel(null);
		orderEndGUI.setVisible(false);
		orderEndGUI.setBounds(0, 100, 800, 800);
		orderEndGUI.setBackground(background);
		
		// 주문 완료 및 홈버튼 추가
		JLabel info = new JLabel("결제가 완료되었습니다.");
		info.setBounds(frameWidth/2-400/2, 100, 400, 100);
		info.setFont(bfont);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setHorizontalTextPosition(JLabel.CENTER);

		// 자동으로 홈(takeOrEat) 돌아가기 카운트 문구 추가
		JLabel countDown = new JLabel(homeCount+"초 뒤 처음 화면으로 돌아갑니다.");
		countDown.setBounds(frameWidth/2-400/2, 250, 400, 100);
		countDown.setFont(bfont);
		countDown.setHorizontalAlignment(JLabel.CENTER);
		countDown.setHorizontalTextPosition(JLabel.CENTER);
		
		// 홈(takeOrEat)으로 돌아가기 버튼 추가
		JButton home = new JButton("처음 화면으로 돌아가기");
		home.setBounds(frameWidth/2-300/2, 350, 300, 100);
		home.setFont(bfont);
		home.setBackground(light_red);
		home.setBorder(BorderFactory.createRaisedBevelBorder());
		
		// 홈버튼 액션 추가
		home.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				returnToHome();
			}
		});
		
		// 객체들을 orderEndGUI에 추가
		orderEndGUI.add(info);
		orderEndGUI.add(countDown);
		orderEndGUI.add(home);
				
		// 주문완료 객체 추가 및 객체 저장
		add(orderEndGUI);
		setOrderEndPanel(orderEndGUI);
		
		// ComponentListener 추가
		// 결제 화면 표시 및 자동 복귀용 ComponentListener와 Timer 추가
		addOrderEndComponentLS();

	} // end addOrderEndPanel() *************************************************************************************************************************
	
	// orderEndGUI 패널이 보이는지 알려줌
	// startHomeTimer의 홈 타이머를 제어하기 위한 boolean을 설정하는 메소드
	// 주문 완료 화면이 뜨면 홈으로 자동으로 돌아가기를 실행
	// 사용자가 버튼을 누르지 않아도 홈으로 돌아가도록 구현
	// addOrderEndPanel() 에서 호출
	private void addOrderEndComponentLS() {
		orderEndGUI.addComponentListener(new ComponentAdapter() {
		@Override
			public void componentShown(ComponentEvent e) {
			// orderEndGUI 패널이 보이면 알려줌
				isOrderEnd = true;
				startHomeTimer(orderEndGUI); // 자동으로 홈 돌아가기 타이머를 실행
			}
		@Override
			public void componentHidden(ComponentEvent e) {
			// orderEndGUI 패널이 화면에서 안보이면 알려줌
				isOrderEnd = false;
				homeCount = 10;
				
				// orderEndGUI의 카운트다운 라벨 리셋
				((JLabel)orderEndGUI.getComponent(1)).setText(homeCount+"초 뒤 처음 화면으로 돌아갑니다."); // 화면에 남은 시간을 표시	
				setOrderEndPanel(orderEndGUI); // orderEndGUI 패널 갱신
			}
		});
	} // end addOrderEndComponentLS *************************************************************************************************************************
	
	// 결제 대기 타이머 생성 및 가동
	// 실제 결제를 진행할 수 없으므로, 결제를 했다는 임의구현으로 3초의 시간이 경과하면 결제 완료 패널을 표시해줌
	// addCreditMethodComponentLS() 에서 호출
	public void startCreditTimer() {
		// Thread 대신 Timer 클래스로 딜레이를 넣음
		Timer timer = new Timer(3000, new ActionListener() { // 3초 딜레이, 버튼 액션 활성화 시 Timer도 작동 시작
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!paymentEnd && isCreditMethod) { // 결제 완료 상태가 아니고, 결제 화면인 경우에만 동작
					System.out.println("3초 경과 : 결제 완료"); // 타이머 동작 확인용 출력
					for (int i = 0; i < numOfPayment; i++) {
						creditMethod[i].setVisible(false); // 모든 결제 방법 별 패널을 안보이게 설정
					}
					paymentEnd = true; // 결제 완료 상태를 true로 바꿈
					getOrderEndPanel().setVisible(true); // 주문 완료 패널을 보이게 설정
				} else { // 결제 완료 상태거나, 결제 화면이 아닌 경우 타이머는 정지해야 함
					((Timer) e.getSource()).stop(); // 타이머 정지
					paymentEnd = false; // 결제 완료 상태를 false로 바꿈
				}
			}
		});
		timer.start(); // 타이머 작동 시작
	} // end startCreditTimer() *************************************************************************************************************************

	// 자동 홈 복귀 타이머
	// autoHome() 에서 호출
	public void startHomeTimer(JPanel orderEndGUI) {
		Timer timer = new Timer(1000, new ActionListener() { // 딜레이 1초		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isHome && isOrderEnd) { // 홈 화면(takeOrEat)이 아니고, 주문 완료 패널(orderEndGUI)일 때만 Timer 가동
					
					if (homeCount > 0) { // 홈으로 돌아가기 카운트(homeCount)가 0 이상일 때		
						homeCount--; // homeCount 다운
						System.out.println(homeCount+"초 뒤에 홈으로 복귀"); // 타이머 가동 확인용 출력						
						((JLabel)orderEndGUI.getComponent(1)).setText(homeCount+"초 뒤 처음 화면으로 돌아갑니다."); // 화면에 남은 시간을 표시	
						setOrderEndPanel(orderEndGUI); // orderEndGUI 패널 갱신

					} else if (homeCount == 0) {
						((Timer) e.getSource()).stop(); // 홈 Timer 종료
						homeCount = 10; // homeCount 리셋
						returnToHome(); // 홈으로 돌아가기
					}
				} else { // 홈 화면이거나, 주문 완료 화면이 아닐 때 Timer는 중지되어야 함
					((Timer) e.getSource()).stop(); 
					homeCount = 10; // homeCount 리셋
				}
			}
		});
		timer.start(); // 홈 Timer 가동
	} // end startHomeTimer() *************************************************************************************************************************
	
	// 처음 화면으로 돌아가기
	// 홈(takeOrEat) 화면을 보여주고, 나머지 패널은 모두 안보이게 설정
	public void returnToHome() {
		// home에서 매장 이용 방법 패널 소환
		// 매장 이용 방법 패널 소환 시 자동으로 메뉴를 저장해둔 데이터들 리셋
		// takeOrEatComponentLS() 참고
		takeOrEat.setVisible(true);
		
		// 다른 패널 숨기기
		menuGUI.setVisible(false);
		if (creditGUI!=null) creditGUI.setVisible(false); // -----------> 프로그램 최초 실행 시 "결제" 버튼을 눌러야만 creditGUI 생성
		if (orderEndGUI!=null) orderEndGUI.setVisible(false); // -----------> 프로그램 최초 실행 시 결제 방법별 버튼을 눌러야만 orderEndGUI 생성
	} // end retrunToHome()
	
	//=========================================================================================================================================================================
	//
	// 패널 객체 저장 및 불러오기 (Setter와 Getter)
	public void setMainTitlePanel(JPanel mainTitle) {
		this.mainTitle = mainTitle;
	}
	
	public JPanel getMainTitlePanel() {
		return mainTitle;
	}
	
	public void setTakeOrEatPanel(JPanel takeOrEat) {
		this.takeOrEat = takeOrEat;
	}
	
	public JPanel getTakeOrEatPanel() {
		return takeOrEat;
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
	
	public HashMap<String, Integer> getBucketlist() {
		return bucketlist;
	}

	public void setBucketlist(HashMap<String, Integer> bucketlist) {
		this.bucketlist = bucketlist;
	}
		
}