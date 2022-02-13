package site.metacoding.practice;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private Player player; // player context
	private static final String TAG = "Player : ";

	// 컴포지션
	private BackgroundMap backgroundMap;

	private ImageIcon playerR, playerL; // 왼쪽 방향 이미지, 오른쪽 방향 이미지.

	private int x, y; // 플레이어의 위치 x,y

	private boolean isLeft, isRight, isUp, isDown, isAttack, leftWallCrash, rightWallCrash, direction;
	// 현재 실행중인가에 대한 변수들.
	// direction : 오른쪽 false,왼쪽은 true

	public boolean isDirection() {
		return direction;
	}

	public boolean isAttack() {
		return isAttack;
	}

	public void setAttack(boolean isAttack) {
		this.isAttack = isAttack;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

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

	public boolean isUp() {
		return isUp;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public Player(BackgroundMap backgroundMap) { // backgroundmap과 관계를 맺음 => 컴포지션
		player = this; // 버블에 전달해주기 위한 context
		this.backgroundMap = backgroundMap; // player에서 선언한 backgroundmap에 관계를 맺은 backgroundmap을 대입.
		// 즉 backgroundmap으로 backgroundmap내부에있는 public 메서드 사용가능.
		playerR = new ImageIcon("image/playerR.png"); // playerR이미지 설정
		playerL = new ImageIcon("image/playerL.png"); // playerL이미지 설정.

		x = 70; // 플레이어의 첫 x 위치
		y = 535; // 플레이어의 첫 y 위치
		// y = 180; // 바닥충돌감지 확인용 위치
		setIcon(playerR); // 시작 이미지를 playerR로 설정
		setSize(50, 50); // 이미지 크기를 x=50, y= 50으로 설정.
		setLocation(x, y); // JLabel의 위치 // player의 시작 위치를 x,y값으로 설정.

		isLeft = false; // 초기 왼쪽 이동중 확인 값 => 이동 x
		isRight = false; // 초기 오른쪽 이동중 확인 값 => 이동 x
		isUp = false; // 초기 위쪽 이동중 확인 값 => 이동 x
		isDown = false; // 초기 아래쪽 이동중 확인 값 => 이동 x
		leftWallCrash = false; // 초기 왼쪽벽에 부딪혔는지 확인 값 => 부딪힘 x
		rightWallCrash = false; // 초기 오른쪽벽에 부딪혔는지 확인 값 => 부딪힘 x
		direction = false; // 초기 플레이어가 보고있는 방향 값 => 오른쪽.
	}

	public void attack() { // 공격
		Bubble bubble = new Bubble(player, backgroundMap); // 방울 생성
		backgroundMap.add(bubble); // 배경에 버블을 추가
	}

	// left(), right(), down()
	private void 바닥충돌감지() { // 바닥에 부딪혔는지 확인
		// System.out.println(TAG + "바닥충돌계산중");
		int bottomColor = backgroundMap.getImage().getRGB(getX() + 10, getY() + 50 + 5) // -1 // 바닥이 있으면 값이 -2
				+ backgroundMap.getImage().getRGB(getX() + 50 - 10, getY() + 50 + 5); // -1 // 바닥 있는지 확인 값.

		if (bottomColor == -2) { // 바닥이 흰색 => 바닥에 아무것도 없으면.
			// System.out.println("바닥이 흰색이에요");
			if (isUp == false && isDown == false)
				down(); // 올라가지 않는중이고 내려가지 않는중에만 아래로 이동.
		} else if (bottomColor != -2) { // 바닥에 장애물이 있다는 것.
			// System.out.println("바닥에 장애물이 있어요.");
			isDown = false; // 바닥에 뭐가 있으면 아래로 이동 중지.
		}
	}

	private void 왼쪽벽충돌감지() { // 왼쪽 벽에 부딪혔는지 확인.
		// System.out.println(TAG + "왼쪽 충돌 계산중");
		Color leftcolor = new Color(backgroundMap.getImage().getRGB(getX() - 10, getY() + 25)); // 플레이어 기준 왼쪽을 color값으로
																								// 바꿈.
		if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
			// System.out.println(TAG + "왼쪽벽에 충돌했어요");
			leftWallCrash = true;
			isLeft = false; // 플레이어 기준 왼쪽 색이 빨강이면 왼쪽이 부딪혔다는거 확인후 왼쪽 이동 중지.
		}

	}

	private void 오른쪽벽충돌감지() {// 오른쪽 벽에 부딪혔는지 확인.
		// System.out.println(TAG + "오른쪽 충돌 계산중");
		Color leftcolor = new Color(backgroundMap.getImage().getRGB(getX() + 50 + 10, getY() + 25));// 플레이어 기준 오른쪽을
																									// color값으로 바꿈.
		if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
			// System.out.println(TAG + "오른쪽벽에 충돌했어요");
			rightWallCrash = true;
			isRight = false; // 플레이어 기준 오른쪽 색이 빨강이면 오른쪽이 부딪혔다는거 확인후 오른쪽 이동 중지.
		}

	}

	@Override
	public void left() { // 왼쪽 이동
		// System.out.println(TAG + "left()");
		setIcon(playerL); // 이미지를 PlayerL로 설정
		rightWallCrash = false; // 오른쪽 벽에서 떼졌으니 안부딪혔다고 확인
		isLeft = true; // 이동중으로 바꿈
		direction = true; // 보고있는 방향을 왼쪽으로 설정
		new Thread(() -> {
			try { // for문 안에 넣으면 try-catch가 for문 돌때마다 만들어짐
				while (isLeft) { // 이동 중지로 바뀔때 까지 이동.
					x = x - 4; // 4씩 왼쪽으로 이동
					setLocation(x, y); // paintComponent()를 자동 호출해준다. 내부적으로 repaint가 된다.
					Thread.sleep(10); // 0.01초 대기
					왼쪽벽충돌감지(); // 왼쪽 벽 충돌 확인
					바닥충돌감지(); // 바닥이 없을 경우 내려가야하므로 바닥 충돌 확인
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).start();
	}

	@Override
	public void right() { // left()설명과 동일.
		// System.out.println(TAG + "right()");
		setIcon(playerR);
		leftWallCrash = false;
		isRight = true;
		direction = false;
		new Thread(() -> {
			try { // for문 안에 넣으면 try-catch가 for문 돌때마다 만들어짐
				while (isRight) {
					x = x + 4;
					setLocation(x, y); // paintComponent()를 자동 호출해준다. 내부적으로 repaint가 된다.
					Thread.sleep(10);
					오른쪽벽충돌감지();
					바닥충돌감지();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).start();

	} // 이게 종료될 때 paintComponent() -> repaint()가 호출 됨.

	@Override
	public void up() { // 위쪽 이동.
		// System.out.println(TAG + "up()");
		isUp = true; // 위쪽으로 이동중
		new Thread(() -> {
			try {
				for (int i = 0; i < 70; i++) { // 정해진 위치(140)만큼 위쪽으로 이동
					y = y - 2; // 위로 2씩 이동
					setLocation(x, y); // 그림을 다시 그려줌
					Thread.sleep(5); // 0.005초 대기
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			isUp = false; // 위쪽 이동 끝.
			down(); // 올라갔으면 중력에 의해 바닥까지 내려옴.
		}).start();
		// System.out.println(isUp);
	}

	@Override
	public void down() { // 아래로 이동

		// System.out.println(isUp);
		isDown = true; // 이동중
		// System.out.println(TAG + "down()");
		new Thread(() -> {
			try {
				while (isDown) { // 이동 중지할때까지 이동
					y = y + 2; // 아래로 2씩 이동
					setLocation(x, y); // 그림을 다시 그림.
					Thread.sleep(3); // 0.003초 대기
					바닥충돌감지(); // 바닥충돌감지 실행.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
