package foodPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Food {

//한식, 양식 리스트 설정
List<Menu> koreaFood = new ArrayList<>(); 
List<Menu> westFood = new ArrayList<>();

public Food() {

JFrame f = new JFrame();
JPanel p5 = new JPanel();

f.setVisible(true);
f.setSize(512, 512);

p5.setVisible(true);
p5.setSize(512, 512);
p5.setLayout(null);

//한식 이름, 가격 설정
koreaFood.add(new Menu("비빔밥", 8000));
koreaFood.add(new Menu("불고기", 9000));
koreaFood.add(new Menu("갈비탕", 10000));
koreaFood.add(new Menu("육개장", 10000));

//양식 이름, 가격 설정
westFood.add(new Menu("스파게티", 9000));
westFood.add(new Menu("피자", 20000));
westFood.add(new Menu("햄버거", 5000));
}
}

