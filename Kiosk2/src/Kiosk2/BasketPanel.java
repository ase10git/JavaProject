package Kiosk2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class BasketPanel extends MainGUI2{
	// 장바구니 패널 추가(JPanel basketGUI) ========================================
	private JPanel basketGUI;
	private JPanel orderEndGUI;
	private JList<String> basketList;
	private DefaultListModel<String> model;
	private JScrollPane basketPane;
	
	MenuPanel menuPanel;
	JPanel menu = menuPanel.getMenuPanel();
	CreditPanel creditPanel;
	JPanel credit = creditPanel.getCreditPanel();
	TakeOrEatPanel takeOrEatPanel;
	JPanel takeOrEat = takeOrEatPanel.getTakeOrEatPanel();
	
	public BasketPanel() {
		
		basketGUI = new JPanel(null);
		basketGUI.setBounds(0, 650, frameWidth, 250);
		basketGUI.setVisible(false);
		basketGUI.setBackground(Color.WHITE);	
		
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
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
		JScrollPane basketPane = new JScrollPane(basketList, v, h);
		basketPane.setBounds(10, 50, 580, 150);
		
		// 객체들 저장
		setBasketList(basketList);
		setModel(model);
		setBasketPane(basketPane);
	}
	
	// payment , back에 액션 추가
	ActionListener basketButtonAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "처음으로 돌아가기") {
				
				menu.setVisible(false);
				basketGUI.setVisible(false);
				credit.setVisible(false);
				takeOrEat.setVisible(true);
				
				model.removeAllElements();
				model.addElement(announce1);
				model.addElement(announce2);
				
			} else if (e.getActionCommand() == "결제") {
				menu.setVisible(false);
				basketGUI.setVisible(false);
				credit.setVisible(true);
			} else if (e.getActionCommand() == "모두 제거") {
				model.removeAllElements();
				model.addElement(announce1);
				model.addElement(announce2);
			} else if (e.getActionCommand() == "선택한 메뉴 제거") {
				if (model.getSize()==1) {
					model.removeAllElements();
					model.addElement(announce1);
					model.addElement(announce2);
				} else if (model.getSize()>1 && basketList.getSelectedIndex()>0)
					model.remove(basketList.getSelectedIndex());
			}
		}
	}; // end basketButtonAction ============================================================================
	
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
}
