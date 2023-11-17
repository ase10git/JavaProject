package Kiosk2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditPanel extends MainGUI2{
	// 결제창 추가(JPanel creditGUI) ===============================================
	private JPanel creditGUI;
	private JPanel[] creditMethod;
	BasketPanel basketPanel;
	JPanel basket = basketPanel.getBasketPanel();
	MenuPanel menuPanel;
	JPanel menu = menuPanel.getMenuPanel();
	
	public CreditPanel() {
		
		creditGUI = new JPanel(null);
		creditGUI.setBounds(0, 100, frameWidth, frameHeight-100);
		creditGUI.setVisible(false);
		creditGUI.setBackground(Color.WHITE);	
		
		// 결제방법 안내 추가
		JLabel paymentMethod = new JLabel("결제 방법을 선택해주세요.");
		paymentMethod.setFont(kfont);
		paymentMethod.setBounds(frameWidth/2-150, 250, 300, 50);
		paymentMethod.setHorizontalAlignment(JLabel.CENTER);
		
		// 뒤로 가기 버튼 추가 => 메뉴 선택으로 돌아감
		JButton back = new JButton("뒤로 돌아가기");
		back.setFont(kfont);
		back.setBounds(600, 690, 190, 80);
		
		// 결제방법 버튼과 패널 추가 => creditGUI에 추가
		JButton[] paybt = new JButton[numOfPayment];
		String[] name = {"신용/체크/지역화폐 카드", "현금", "페이앱", "기프트카드/쿠폰"};
		
		for (int i = 0; i < numOfPayment; i++) {
			paybt[i] = new JButton(name[i]);
			if (i%2==0) {
				paybt[i].setBounds(frameWidth/2-300, 330+i*100, 250, 150);
			} else {
				paybt[i].setBounds(frameWidth/2+50, 230+i*100, 250, 150);
			}
			paybt[i].setFont(kfont);
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
		setCreditPanel(creditGUI);
	} // end addCreditGUI ------------------------------------------------------
	
	// 결제 방법 별 결제창 생성 ------------------------------------------------------------
	public JPanel[] addCreditMethod() {
		JPanel[] creditMethod = new JPanel[numOfPayment];
		
		for (int i = 0; i < numOfPayment; i++) {
			creditMethod[i] = new JPanel(null);
			creditMethod[i].setBounds(0, 100, frameWidth, frameHeight-100);
			creditMethod[i].setBackground(Color.LIGHT_GRAY);
			creditMethod[i].setVisible(false);
			
			creditMethod[i].add(backButton(creditMethod[i]));
			//creditMethod[i].add(paymentConfirm());
			add(creditMethod[i]);
		}
		// 결제 방법 창 객체 저장 및 반환
		setCreditMethodPanel(creditMethod);
		return creditMethod;
	}// end addCreditMethod ===============================================
	
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
	
	// 결제 방법 버튼에 액션 추가
	ActionListener paybtActionListener = new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			creditMethod = getCreditMethodPanel();
			creditGUI.setVisible(false);
			basket.setVisible(false);
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
	}; //end paybtActionListener =================================================================================

	// 메뉴로 돌아가기 액션
	ActionListener returnToMenu = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "뒤로 돌아가기") {
				basket.setVisible(true);
				menu.setVisible(true);
				creditGUI.setVisible(false);
			}
		}
	};// end returnToMenu =================================================================================
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
}
