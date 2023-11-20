package Kiosk2;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 매장 이용 방법 패널과 버튼을 생성하는 클래스
public class TakeOrEatPanel extends MainGUI2{
	// 매장 이용 방법 선택 (JPanel takeOutOrEat)
	
	private JPanel takeOrEat;
	private JLabel info;
	private JButton[] takeEatbt;
	private JButton takeEatbt2;
	
	// 클래스 기본 생성자
	// 매장 이용 방법 패널과 버튼을 생성하고, 객체 정보를 저장할 클래스
	public TakeOrEatPanel() {}
	
	// 매장 이용 패널 생성
	public JPanel makeTakeOrEatPanel() {		
		JPanel takeOrEat = new JPanel(null);
		takeOrEat.setVisible(true);
		takeOrEat.setBounds(0, 100, 800, 800);
		
		return takeOrEat;
	}
	
	// 매장 이용 안내 문구 만들기
	public JLabel makeInfoLabel() {
		JLabel info = new JLabel("매장 이용 방법을 선택해주세요.");
		info.setBounds(frameWidth/2-350/2, 350, 350, 100);
		info.setFont(bfont);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setHorizontalTextPosition(JLabel.CENTER);
		
		return info;
	}
	
	// 버튼 만들기
	public JButton[] makeButton() {
		// 버튼 및 안내 문구 추가		
		JButton[] takeEatbt = new JButton[2];
		String[] name = {"포장", "매장에서 식사"};
		for (int i = 0; i < takeEatbt.length; i++) {
			takeEatbt[i] = new JButton(new ImageIcon(imageAddr+name[i]+".png"));
			takeEatbt[i].setBounds(40+(frameWidth/2)*i, 460, 300, 280);
			takeEatbt[i].setFont(bfont);
		}		
		return takeEatbt;
	}
	
	// 패널에 버튼과 라벨 부착 (버튼 배열)
	public void addComponentToPanel(JPanel takeOrEat, JLabel info, JButton[] takeEatbt) {	
		
		takeOrEat.add(info);
		for (int i = 0; i < takeEatbt.length; i++) {
			takeOrEat.add(takeEatbt[i]);
		}

		// 클래스에 객체 저장하기
		 setTakeOrEat(takeOrEat);
		 setInfo(info);
		 setTakeEatbt(takeEatbt);
	}
	
	// 패널에 버튼과 라벨 부착 (단일 버튼)
	public void addComponentToPanel(JPanel takeOrEat, JLabel info, JButton takeEatbt2) {	
		
		takeOrEat.add(info);
		takeOrEat.add(takeEatbt2);

		// 클래스에 객체 저장하기
		 setTakeOrEat(takeOrEat);
		 setInfo(info);
		 setTakeEatbt2(takeEatbt2);
	}
	
	/*
	private void takeOrEatComponentLS() {
		takeOrEat.addComponentListener(new ComponentAdapter() {		
			@Override
			public void componentShown(ComponentEvent e) {
				isHome = true;
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				isHome = false;
			}
		});	
	} 
	*/
	public JPanel getTakeOrEat() {
		return takeOrEat;
	}

	public void setTakeOrEat(JPanel takeOrEat) {
		this.takeOrEat = takeOrEat;
	}

	public JLabel getInfo() {
		return info;
	}

	public void setInfo(JLabel info) {
		this.info = info;
	}

	public JButton[] getTakeEatbt() {
		return takeEatbt;
	}

	public void setTakeEatbt(JButton[] takeEatbt) {
		this.takeEatbt = takeEatbt;
	}

	public JButton getTakeEatbt2() {
		return takeEatbt2;
	}

	public void setTakeEatbt2(JButton takeEatbt2) {
		this.takeEatbt2 = takeEatbt2;
	}

}
