package Kiosk;

import java.util.HashMap;

public class Food_Data {

	private String[][] menu_list = {
			{"김밥","참치김밥","치즈김밥","쫄면","떡볶이","로제 떡볶이","순대","핫도그","라면","오뎅탕"},   // 분식
			{"모듬초밥","우동","돈까스","치즈돈까스","모밀","덴푸라","돈코츠라멘","에비동","차슈동"},		// 일식
			{"비빔밥","불고기","갈비탕","육개장","김치찌개","된장찌개","순두부찌개","부대찌개","칼국수"},		// 한식
			{"토마토파스타","크림파스타","리조또","조각피자","찹스테이크","리코타치즈샐러드"}				// 양식
	};

	private int[][] menu_price = {
			{3000,4000,4000,5000,5000,5500,4000,2500,3500,5500},// 분식가격
			{15000,5500,8000,9000,5000,7000,8000,8000,8000},	// 일식 가격
			{10000,8000,11000,9000,6000,6500,6000,7000,6500},	// 한식 가격
			{8000,8000,7500,3000,11000,10000}					// 양식 가격
	};
	
	private HashMap<String, Integer> menuAndPrice = new HashMap<String, Integer>();
	
	// 생성자로 호출하면 menuAndPrice에 key, value를 넣어주기
	public Food_Data() {
		 makeMenuAndPrice();
	}
	
	// makeMenuAndPrice에 key로 메뉴 이름을, value로 메뉴 가격을 저장하기
	public void makeMenuAndPrice() {
		for (int i = 0; i < menu_list.length; i++) {
			for (int j = 0; j < menu_list[i].length; j++) {
				menuAndPrice.put(menu_list[i][j], menu_price[i][j]);
			}
		}
	}

	public String[][] getMenu_list() {
		return menu_list;
	}

	public void setMenu_list(String[][] menu_list) {
		this.menu_list = menu_list;
	}

	public int[][] getMenu_price() {
		return menu_price;
	}

	public void setMenu_price(int[][] menu_price) {
		this.menu_price = menu_price;
	}

	public HashMap<String, Integer> getMenuAndPrice() {
		return menuAndPrice;
	}

	public void setMenuAndPrice(HashMap<String, Integer> menuAndPrice) {
		this.menuAndPrice = menuAndPrice;
	}
}