package site.metacoding.bubble.ex02;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author codingfarm 목적: 배경화면 설정,캐릭터 추가.
 */
public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		setVisible(true); // 내부에 paintComponent()호출 코드가 있다.
	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		player = new Player();
		add(player);

	}

	private void initSetting() {
		setSize(1000, 640);
		getContentPane().setLayout(null); // JFRAME의 기본 JPanel의 레이아웃 설정.
		setLocationRelativeTo(null); // 가운데 배
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼 클릭시 jvm 같이 종료하
	}

	public static void main(String[] args) {
		new BubbleFrame();

	}

}
