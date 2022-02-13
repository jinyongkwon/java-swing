package site.metacoding.practice;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class BubbleApp extends JFrame implements Init {

	private static final String TAG = "BubbleApp : "; // 테스트를 위한 태그

	private BubbleApp mContext = this; // 컴포지션을 위한 context

	private BackgroundMap backgroundMap; // backgroundMap과 관계
	private Player player; // player와 관계

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BubbleApp getmContext() {
		return mContext;
	}

	public void setmContext(BubbleApp mContext) {
		this.mContext = mContext;
	}

	public BubbleApp() {
		// System.out.println(TAG + "생성자");
		initObject();
		initSetting();
		initLister();

		setVisible(true); // GUI를 화면에 뛰움.
	}

	@Override
	public void initObject() {
		backgroundMap = new BackgroundMap(); // backgroundMap생성
		setContentPane(backgroundMap); // DIV박스 전체를 배경화면으로 설정

		player = new Player(backgroundMap); // 플레이어 생성.
		add(player); // 플레이어 추가.
	}

	@Override
	public void initSetting() {
		setSize(1000, 640); // 전체 사이즈를 x = 1000, y = 640으로 설정
		getContentPane().setLayout(null); // JFrame의 기본 JPanel의 레이아웃 설정
		setLocationRelativeTo(null); // 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼 클릭시 JVM 같이 종료하기
	}

	@Override
	public void initLister() {
		mContext.addKeyListener(new KeyAdapter() { // key에 대한 이벤트를 확인하기 위한 리스너 생성.
			@Override
			public void keyPressed(KeyEvent e) { // 키를 눌렀을때를 확인
				switch (e.getKeyCode()) { // 값! == case 값
				case KeyEvent.VK_RIGHT: // 오른쪽 방향키를 눌렀을때
					if (player.isRight() == false && !player.isRightWallCrash()) {
						player.right(); // 플레이어가 오른쪽으로 이동중이지 않고, 오른쪽 벽에 부딪히지 않았으면 오른쪽으로 이동.
					}
					break;// switch 종료
				case KeyEvent.VK_LEFT: // 왼쪽 방향키를 눌렀을때
					if (!player.isLeft() && !player.isLeftWallCrash())
						player.left(); // 가독성이 좋게 만든 방법 // 플레이어가 왼쪽으로 이동중이지 않고, 왼쪽 벽에 부딪히지 않았으면 왼쪽으로 이동.
					break;
				case KeyEvent.VK_UP: // 위쪽 방향키를 눌렀을때
					if (player.isUp() == false && !player.isDown()) {
						// System.out.println(player.isUp());
						player.up(); // 플레이어가 위쪽으로 이동중이지 않고, 아래쪽으로 이동 중이지 않을때 up() 호출.
					}
					break;
				case KeyEvent.VK_SPACE: // 스페이스바를 눌렀을때
					if (player.isAttack() == false) {
						player.attack(); // 플레이어가 공격중이지 않으면 공격실행.
					}
					break;
				}

			}

			@Override
			public void keyReleased(KeyEvent e) { // 키를 땟을때를 확인
				switch (e.getKeyCode()) { // 값! == case 값
				case KeyEvent.VK_RIGHT: // 오른쪽 방향키를 뗏을때
					player.setRight(false); // 오른쪽 이동 중지.
					break; // switch 종료
				case KeyEvent.VK_LEFT: // 왼쪽 방향키를 뗏을때
					player.setLeft(false); // 왼쪽 이동 중지.
					break;

				}
			}
		});
	}

	public static void main(String[] args) {
		new BubbleApp(); // bubbleApp 실행

	}

}
