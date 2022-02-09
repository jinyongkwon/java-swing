package site.metacoding.bubble.test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import site.metacoding.bubble.ex05.Player;

public class BubbleFrame extends JFrame { // JFrame을 상속해서 JFrame내부 메서드 사용 가능.

	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject(); // new 하는것
		initSetting(); // 각종 세팅
		initListener(); // 리스너.
		setVisible(true); // 내부에 paintComponent() 호출 코드가 있다.
	}

	private void initObject() {
		backgroundMap = new JLabel();
		backgroundMap.setIcon(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap); // 백그라운드 화면 설정

		player = new Player(); // player 생성.
		add(player); // player 그림
	}

	private void initSetting() {
		setSize(1000, 640);
		getContentPane().setLayout(null); // JFrame의 기본 JPanel의 레이아웃 설정
		setLocationRelativeTo(null); // 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼 클릭시 JVM 같이 종료하기
	}

	private void initListener() {
		// 키보드를 누르는가에 대한 리스너.
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			// 키보드를 뗏을 때
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("키보드 릴리즈");

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					// isRight를 false
					player.setRight(false);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					// isLeft를 false
					player.setLeft(false);
				}
			}

			// 키보드를 눌렀을 때
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					// 키보드를 누르고 있는 동안 right() 메서드를 한번만 실행
					if (player.isRight() == false) {
						player.right();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					// 키보드를 누르고 있는 동안 left() 메서드를 한번만 실행
					if (player.isLeft() == false) {
						player.left();
					}
				} else if (e.getKeyCode() == 38) {
					// 키보드를 누르고 있는 동안 jump() 메서드를 한번만 실행
					if (player.isJump() == false) {
						player.jump();
					}
				}

			}
		});
	}

	public static void main(String[] args) {
		new BubbleFrame();
	}

}
