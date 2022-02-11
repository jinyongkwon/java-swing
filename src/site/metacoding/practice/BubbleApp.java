package site.metacoding.practice;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class BubbleApp extends JFrame implements Init {

	private static final String TAG = "BubbleApp : ";

	private BubbleApp mContext = this;

	private BackgroundMap backgroundMap;
	private Player player;

	public BubbleApp() {
		// System.out.println(TAG + "생성자");
		initObject();
		initSetting();
		initLister();

		setVisible(true);
	}

	@Override
	public void initObject() {
		backgroundMap = new BackgroundMap();
		setContentPane(backgroundMap); // DIV박스 전체를 배경화면으로 설정

		player = new Player(backgroundMap);
		add(player);
	}

	@Override
	public void initSetting() {
		setSize(1000, 640);
		getContentPane().setLayout(null); // JFrame의 기본 JPanel의 레이아웃 설정
		setLocationRelativeTo(null); // 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼 클릭시 JVM 같이 종료하기
	}

	@Override
	public void initLister() {
		mContext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) { // 값! == case 값
				case KeyEvent.VK_RIGHT:
					if (player.isRight() == false && !player.isRightWallCrash()) {
						player.right();
					}
					break;// switch 종료
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isLeftWallCrash())
						player.left(); // 가독성이 좋게 만든 방법
					break;
				case KeyEvent.VK_UP:
					if (player.isUp() == false) {
						System.out.println(player.isUp());
						player.up();
					}
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) { // 값! == case 값
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break; // switch 종료
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;

				}
			}
		});
	}

	public static void main(String[] args) {
		new BubbleApp();

	}

}
