package Kiosk;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu4GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	public Menu4GUI() {
		setLayout(null);
		
		JLabel test4 = new JLabel();
		add(test4);
		
		setBounds(100, 100, 500, 700);
		setVisible(true);
		setBackground(Color.WHITE);	
	}
}
