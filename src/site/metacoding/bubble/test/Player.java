package site.metacoding.bubble.test;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel { // // Player가 label의 상속을 받고 있으니, seticon 사용할 수 있다.
	private int x; // 플레이어 좌표 x
	private int y; // 플레이어 좌표 y
	private ImageIcon playerL, playerR;
	// 플레이어 왼쪽으로 움직일때 아이콘
	// 플레이어 오른쪽으로 움직일때 아이콘

	private boolean isRight; // 오른쪽을 눌렀는지 확인
	private boolean isLeft; // 왼쪽을 눌렀는지 확인
	private boolean isJump; // 위쪽을 눌렀는지 확인
	private static final int JUMPSPEED = 2; // 점프 속도
	private static final int SPEED = 4; // 이동 속도

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isJump() {
		return isJump;
	}

	public void setJump(boolean isJump) {
		this.isJump = isJump;
	}

	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerL = new ImageIcon("image/playerL.png");
		playerR = new ImageIcon("image/playerR.png");
	}

	private void initSetting() {
		x = 70; // 시작위치
		y = 535; // 시작위치
		setIcon(playerR); // 시작 이미지 설정
		setSize(50, 50); // 플레이어 크기 설정
		setLocation(x, y); // 배치 위치 설정
		isRight = false; // 오른쪽을 눌렀는가에 대한 기본 값
		isLeft = false; // 오른쪽을 눌렀는가에 대한 기본 값
	}

	public void left() {
		isLeft = true; // 왼쪽을 눌렀음.
		setIcon(playerL); // 왼쪽 이미지로 설정
		System.out.println("왼쪽이동");
		new Thread(() -> {
			while (isLeft) { // 왼쪽을 눌르는 동안 반복
				x = x - SPEED; // speed만큼 왼쪽으로 이동 (-)
				setLocation(x, y); // 플레이어 재 배치
				try {
					Thread.sleep(10); // 0.01초씩 쉬면서 while을 돌림
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start(); // 스레드 시작.
	}

	public void right() {
		isRight = true; // 오른쪽을 눌렀음.
		setIcon(playerR); // 오른쪽 이미지로 설정
		System.out.println("오른쪽이동");
		new Thread(() -> {
			while (isRight) { // 오른쪽을 눌르는 동안 반복
				x = x + SPEED; // speed만큼 오른쪽으로 이동 (+)
				setLocation(x, y); // 플레이어 재 배치
				try {
					Thread.sleep(10); // 0.01초씩 쉬면서 while을 돌림
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start(); // 스레드 시작.
	}

	public void jump() {
		System.out.println("위쪽이동");
		isJump = true; // 위쪽을 눌렀음.
		new Thread(() -> {
			// 정해진 높이까지 올라야 하므로 for문을 돌림
			for (int i = 0; i < 130 / JUMPSPEED; i++) {
				y = y - JUMPSPEED; // jumpspeed만큼 위로 이동(-)
				setLocation(x, y); // 플레이어 재 배치
				try {
					Thread.sleep(10); // 0.01초씩 쉬면서 for를 돌림
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 올라갔다가 중력에 의해 내려오는 것.
			for (int i = 0; i < 130 / JUMPSPEED; i++) {
				y = y + JUMPSPEED; // jumpspeed만큼 아래로 이동
				setLocation(x, y);
				try {
					Thread.sleep(3); // 0.003초씩 쉬면서 for를 돌림.
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			isJump = false; // 연속해서 실행이 안되게끔 thread의 끝부분에 false처리.
		}).start();
	}
}
