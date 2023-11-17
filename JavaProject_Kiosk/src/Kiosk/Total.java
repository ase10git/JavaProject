package Kiosk;

public class Total {
	int total = 0;
	public void cal(int price) { 
		total+=price;
	}
	public int getTotal() {
		return total;
	}
	public void all() {
		total = 0;
	}
}
