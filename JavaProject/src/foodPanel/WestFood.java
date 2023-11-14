package foodPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WestFood {
	
List<Menu> westFood = new ArrayList<>();


public WestFood() {
	
JFrame f = new JFrame();
JPanel p5 = new JPanel();

f.setVisible(true);
f.setSize(512, 512);

p5.setVisible(true);
p5.setSize(512, 512);
p5.setLayout(null);

westFood.add(new Menu("스파게티", 9000));
westFood.add(new Menu("피자", 20000));
westFood.add(new Menu("햄버거", 5000));
}
}



