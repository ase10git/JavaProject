package foodPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class KoreaFood {

List<Menu> koreaFood = new ArrayList<>();


public KoreaFood() {
	
JFrame f = new JFrame();
JPanel p6 = new JPanel();

f.setVisible(true);
f.setSize(512, 512);

p6.setVisible(true);
p6.setSize(512, 512);
p6.setLayout(null);
	
koreaFood.add(new Menu("비빔밥", 8000));
koreaFood.add(new Menu("불고기", 9000));
koreaFood.add(new Menu("갈비탕", 10000));
koreaFood.add(new Menu("육개장", 10000));
}
}


