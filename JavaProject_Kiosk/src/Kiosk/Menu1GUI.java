package Kiosk;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu1GUI extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public Menu1GUI() {
		setLayout(null);
		
		JLabel test1 = new JLabel();
		add(test1);
		
		setBounds(100, 100, 500, 700);
		setVisible(true);
		setBackground(Color.WHITE);	
	}
	
}
