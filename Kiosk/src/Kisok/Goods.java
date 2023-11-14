package Kisok;

public class Goods extends Menu {

	private int price;
	private int totalp; // 메뉴 가격의 총 합
	private int number; // 메뉴의 갯수
	private int number_total = 0; // 메뉴의 총 갯수

	public int getNumber_total() {
		return number_total;
	}

	// 메뉴 가격 갖고오기
	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public String getDetail() {
		return super.getDetail();
	}

	@Override
	public String getNum() {
		return super.getNum();
	}

	// 메뉴 가격 갖고오기
	private int getPrice() {
		return this.price;
	}

	public int getTotalp() {
		this.totalp = price * number_total;
		return this.totalp;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int i) {
		this.number = i;
	}

	public void set_Number(int ii) {
		this.number_total += ii;
	}

	public Goods() {

	}

	public Goods(String num, String name, int price, String detail) {
		super(num,name,detail);
		this.price = price;
	}

}
