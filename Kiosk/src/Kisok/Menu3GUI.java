package Kiosk;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu3GUI extends JPanel{
	private static final long serialVersionUID = 1L;
	public Menu3GUI() {
		setLayout(null);
		
		JLabel test3 = new JLabel();
		add(test3);
		
		setBounds(100, 100, 500, 700);
		setVisible(true);
		setBackground(Color.WHITE);	
	}
}
