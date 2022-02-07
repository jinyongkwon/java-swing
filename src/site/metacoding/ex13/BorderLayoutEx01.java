package site.metacoding.ex13;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BorderLayoutEx01 extends MyFrame {

	public BorderLayoutEx01() {
		super(500, 500);

		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(new BorderLayout()); // 생략가능!! 디폴트가 BorderLayout 이니까!!

		JButton btnNorth = new JButton("북쪽");
		JButton btnEast = new JButton("동쪽");
		JButton btnWest = new JButton("서쪽");
		JButton btnSouth = new JButton("남쪽");
		JButton btnCenter = new JButton("가운데");

//		panel.add(btnNorth, BorderConstant.NORTH); // "North"가 들어 가지 않으면 에러가 뜸.
//		panel.add(btnEast, BorderConstant.EAST); // 내가 만약 팀장이면 신입이 실수할수 있으니 BorderConstant를 만들어줌
//		panel.add(btnWest, BorderConstant.WEST); // 이렇게 실수할까봐 Java에서 만들어 놓은게 있음.
//		panel.add(btnSouth, BorderConstant.SOUTH);
//		panel.add(btnCenter, BorderConstant.CENTER);

		panel.add(btnNorth, BorderLayout.NORTH); // 그것이 BorderLayout으로 만들어 놓음.
		panel.add(btnEast, BorderLayout.EAST);
		panel.add(btnWest, BorderLayout.WEST);
		panel.add(btnSouth, BorderLayout.SOUTH);
		panel.add(btnCenter, BorderLayout.CENTER);
		setVisible(true); // paint 메서드
	}

	public static void main(String[] args) {
		new BorderLayoutEx01();

	}

}
