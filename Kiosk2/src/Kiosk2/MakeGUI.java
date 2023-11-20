package Kiosk2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MakeGUI {

	public static void main(String[] args) {
		MainGUI2 main = new MainGUI2();
		
		WelcomePanel welcomePanel = new WelcomePanel();
		JPanel welcom = welcomePanel.makeWelcomePanel();
		JLabel welcomeLabel = welcomePanel.makeWelcomeLabel();	
		welcomePanel.addComponentToPanel(welcom, welcomeLabel);
		
		TakeOrEatPanel takeOrEatPanel = new TakeOrEatPanel();
		JPanel takeOrEat = takeOrEatPanel.makeTakeOrEatPanel();
		JLabel info = takeOrEatPanel.makeInfoLabel();
		JButton[] takeEatbt = takeOrEatPanel.makeButton();
		takeOrEatPanel.addComponentToPanel(takeOrEat, info, takeEatbt);
		
		main.add(welcom);
		main.add(takeOrEat);
		
		main.setVisible(true);
		//BasketPanel basketPanel = new BasketPanel();
		//MenuPanel menuPanel = new MenuPanel();
		//CreditPanel creditPanel = new CreditPanel();
		
		/*
		menuPanel.init(); // 큰 메뉴판 생성
		basketPanel.init();
		takeOrEatPanel.init(menuPanel, basketPanel);
		creditPanel.init(basketPanel);
		
		basketPanel.addBasketButton(takeOrEatPanel, menuPanel, creditPanel);		
		menuPanel.addMenus(basketPanel); // 카테고리별 메뉴판 생성 및 객체저장
		
		main.add(takeOrEatPanel.getTakeOrEatPanel());
		main.add(menuPanel.getMenuPanel());
		main.add(basketPanel.getBasketPanel());
		main.add(creditPanel.getCreditPanel());
		*/

	}

}
