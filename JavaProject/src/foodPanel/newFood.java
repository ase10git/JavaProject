package foodPanel;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class newFood {

	public newFood() {
		
		JFrame f = new JFrame("부평 휴게소");
		JPanel p5 = new JPanel();		
		
		f.setVisible(true);
		f.setSize(512, 512);

		p5.setVisible(true);
		p5.setSize(512, 512);
		p5.setLayout(null);
		
		
		String westMenu[] = { "스파게티", "피자", "햄버거" };
		int westPrice[] = { 9000, 20000, 5000};
		
		String koreaMenu[] = { "비빔밥", "불고기", "갈비탕", "육개장" };
		int koreaPrice[] = { 5500, 9000, 10000, 10000 };
}
	
public static void main(String[] args) {
	newFood nF = new newFood();
}
}
