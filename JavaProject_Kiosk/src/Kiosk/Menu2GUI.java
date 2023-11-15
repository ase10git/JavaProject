package Kiosk;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu2GUI extends JPanel{
	private static final long serialVersionUID = 1L;
	public Menu2GUI() {
		setLayout(null);
		
		JLabel test2 = new JLabel();
		add(test2);
		
		setBounds(100, 100, 500, 700);
		setVisible(true);
		setBackground(Color.WHITE);	
	}
}
