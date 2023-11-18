package Kiosk2;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends MainGUI2{
	// 메뉴 패널 추가(JPanel menuGUI)
	private JPanel menuGUI;
	private JPanel[] menu;
	
	public MenuPanel() {
		
		JPanel menuGUI = new JPanel(null);
		menuGUI.setBounds(0, 100, frameWidth, 550);
		menuGUI.setBackground(Color.WHITE);
		menuGUI.setVisible(false);	
		setMenuPanel(menuGUI);
	}
	
	public void init() {
		// 메뉴 버튼 추가
		JButton[] menubt = new JButton[numOfMenu];
		String[] name = {"분식", "일식", "한식", "양식"};
		for (int i = 0; i < name.length; i++) {
			menubt[i] = new JButton(name[i]);
		}
		
		// 버튼 설정 및 버튼 추가
		for (int i = 0; i < menubt.length; i++) {
			menubt[i].setBounds(i*100, 0, 100, 100);
			menubt[i].setFont(kfont);
			menuGUI.add(menubt[i]);
			addMenuButtonAction(menubt[i]);
		}	
	
		// 메뉴 패널 객체 저장
		setMenuPanel(menuGUI);
	}
	
	// 메뉴 종류 화면 추가 ---------------------------------------------------------
	public void addMenus(BasketPanel baksetPanel) { 
		
		JPanel[] menu = new JPanel[numOfMenu];
		String[][] menu_list = {
				{"김밥","참치김밥","치즈김밥","쫄면","떡볶이","떡볶이","떡볶이"}, // 분식
				{"초밥","우동","돈까스","모밀","튀김"},				// 일식
				{"테스트1","테스트2","테스트3","테스트4","테스트5"},	// 한식
				{"test1","test2","test3","test4","test5"}	// 양식
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
				bt[j] = new JButton(menu_list[i][j]); // imageAddr+menu_list[i][j]
			
				if (j<5) {
					bt[j].setBounds(20+j*150,30,130,130);
				} else if(j<10) {
					bt[j].setBounds(20+(j-5)*150,250,130,130);
				}
				
				snackName[j] = new JLabel(menu_list[i][j]);
				snackName[j].setBounds(bt[j].getX()+10,bt[j].getY()-25,115,20);
				snackName[j].setFont(mfont);
				snackName[j].setHorizontalAlignment(JLabel.CENTER);
				snackName[j].setHorizontalTextPosition(JLabel.CENTER);
				
				num[j] = new TextField("0");
				num[j].setBackground(Color.white);
				num[j].setBounds(bt[j].getX()+40,bt[j].getY()+140,50,20);
				num[j].setFont(mfont);
				num[j].setEditable(false);
				
				minus[j] = new Button("-");
				minus[j].setBounds(bt[j].getX()+10,num[j].getY(),20,20);
				minus[j].setEnabled(true);
				minus[j].setFont(mfont);
				
				plus[j] = new Button("+");
				plus[j].setBounds(bt[j].getX()+100,num[j].getY(),20,20);
				plus[j].setEnabled(true);
				plus[j].setFont(mfont);
				
				ok[j] = new JButton("확인");
				ok[j].setBounds(bt[j].getX()+15,num[j].getY()+30,100,20);
				ok[j].setFont(mfont);
				ok[j].setHorizontalAlignment(JLabel.CENTER);
				ok[j].setHorizontalTextPosition(JLabel.CENTER);
				
				// 메뉴에 버튼, 메뉴이름, 메뉴 수량 컴포넌트 추가	
				menu[i].add(bt[j]);
				menu[i].add(snackName[j]);
				menu[i].add(num[j]);
				
				// "+", "-", "확인" 버튼 액션 리스너
				addPlusMinusAction(j, minus[j], null, num, snackName, baksetPanel);
				addPlusMinusAction(j, plus[j], null, num, snackName, baksetPanel);
				addPlusMinusAction(j, null, ok[j], num, snackName, baksetPanel);
				
				// 수량 증감, 확인 버튼 추가
				menu[i].add(minus[j]);
				menu[i].add(plus[j]);
				menu[i].add(ok[j]);
			}
			
			menu[i].setBounds(0, 100, 800, 700);
			menu[i].setBackground(Color.LIGHT_GRAY);
			if (i==0) menu[i].setVisible(true);
			else menu[i].setVisible(false);	
			add(menu[i]);
		}
		// 저장된 메뉴 패널 배열 객체 저장
		setMenus(menu);
	} // end addMenus ========================================================
	
	// 메뉴 버튼 액션 추가
	public void addMenuButtonAction(JButton bt) {
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
		};
		bt.addActionListener(menuButtonAction);
	}// end menuButtonAction ============================================================================

	public void addPlusMinusAction(int j, Button bt, JButton jbt, TextField[] num, JLabel[] snackName, BasketPanel basketPanel) {
		DefaultListModel<String> model = basketPanel.getModel();
		
		ActionListener plusMinusAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TextField jf = num[j];
				JLabel name = snackName[j];
				int nums = Integer.parseInt(jf.getText());// 중복 메뉴의 수량 추가
				StringBuilder sb = new StringBuilder();
				
				if (e.getActionCommand() == "+") { // 수량 추가
					jf.setText(String.valueOf(nums+1));
				} else if (e.getActionCommand() == "-") { // 수량 감소
					if (nums>0) {
						jf.setText(String.valueOf(nums-1));
					} else jf.setText("0");
				} else if (e.getActionCommand() == "확인") { // model 에 추가
					String prevStr = "음식의 수량";
					
					if (model.getElementAt(model.getSize()-1).contains(prevStr)) {
						model.removeAllElements();
					}
					sb.append(name.getText()).append(" :\t").append(jf.getText()).append(" 개");
					model.addElement(sb.toString());
					jf.setText("0");
					sb.setLength(0);
				}
			}
		};		
		if (bt != null && jbt == null) {
			bt.addActionListener(plusMinusAction);
		} else {
			jbt.addActionListener(plusMinusAction);
		}
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
	
}
