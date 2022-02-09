package site.metacoding.bubble.ex03;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author codingfarm 목적: 캐릭터 좌우 이동.
 * 
 */
public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true); // 내부에 paintComponent()호출 코드가 있다.
	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		player = new Player();
		add(player);
	}

	private void initListener() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("키보드 릴리즈");

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// 왼쪽 37,오른쪽 39, 위쪽 38, 아래쪽 40
				System.out.println("키보드 프레스:" + e.getKeyCode());
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.right();
				} else if (e.getKeyCode() == 37) {
					player.left();
				}
			}

		});
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
