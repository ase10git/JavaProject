package Kiosk;


public class Total {
	int total = 0;
	int menu = 0;
	
	public void cal(int menu_price, int num) { 
		total+=menu_price*num;
		
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void calMenu(int price, int num) {
		menu+=price*num;
	}
	public int getMenuTotal() {
		return menu;
	}
	public void all() {
		total = 0;
	}
	public void menuAll() {
		menu = 0;
	}
}
