package test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainGUI extends JFrame {
	
	public mainGUI() {
		super("메인 프레임");
		setBounds(100, 100, 500, 700);
		setVisible(true);
		setBackground(Color.GRAY);	
	}
	
	public void window1() {
		JPanel takeOutOrEat = new JPanel(new FlowLayout());
		JButton takeOut = new JButton("테이크아웃");
		JButton Eat = new JButton("매장식사");
		
		
	}
}
