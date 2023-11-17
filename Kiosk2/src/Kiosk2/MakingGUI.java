package Kiosk2;

import javax.swing.JPanel;

public class MakingGUI extends MainGUI2{
	
	public static void main(String[] args) {
		
		MainGUI2 main = new MainGUI2();
		
		TakeOrEatPanel takeOrEatPanel = new TakeOrEatPanel();
		BasketPanel basketPanel = new BasketPanel();
		MenuPanel menuPanel = new MenuPanel();
		CreditPanel creditPanel = new CreditPanel();
		
		menuPanel.addMenus();
		JPanel[] menu = menuPanel.getMenus();
		for (int i = 0; i < numOfMenu; i++) {
			menuPanel.add(menu[i]);
		}
		
		basketPanel.addBasketList();

		creditPanel.addCreditMethod();
		
		main.add(takeOrEatPanel);
		main.add(menuPanel);
		main.add(basketPanel);
		main.add(creditPanel);
	}
}
