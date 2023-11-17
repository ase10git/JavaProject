package Kiosk2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TakeOrEatPanel extends MainGUI2{
	// 매장 이용 방법 선택 (JPanel takeOutOrEat)
	private JPanel takeOrEat;
	
	MenuPanel menuPanel;
	JPanel menu = menuPanel.getMenuPanel();
	BasketPanel basketPanel;
	JPanel basket = basketPanel.getBasketPanel();
	
	public TakeOrEatPanel() {
		
		takeOrEat = new JPanel(null);
		takeOrEat.setVisible(true);
		takeOrEat.setBounds(0, 100, 800, 800);
		
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

		// 버튼 설정 및 추가
		takeOut.addActionListener(takeEatButtonAction);
		eat.addActionListener(takeEatButtonAction);
		
		takeOrEat.add(info);
		takeOrEat.add(takeOut);
		takeOrEat.add(eat);
		
		setTakeOrEatPanel(takeOrEat);
	} // end add TakeOrEatPanel ===============================================
		
	// ActionListener 클래스들 ==============================================================================
	// 매장이용 버튼 액션 추가 
	ActionListener takeEatButtonAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			takeOrEat.setVisible(false);			
			// 메뉴 패널 등장
			menu.setVisible(true);
			// 바구니 패널 등장
			basket.setVisible(true);
		}
	}; // end takeEatButtonAction =======================================================================
	

	// 패널 객체 저장 및 불러오기 =======================================================
	public void setTakeOrEatPanel(JPanel takeOutOrEat) {
		this.takeOrEat = takeOutOrEat;
	}
	
	public JPanel getTakeOrEatPanel() {
		return takeOrEat;
	}

}
