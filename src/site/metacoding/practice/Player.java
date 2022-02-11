package site.metacoding.practice;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private static final String TAG = "Player : ";

	// 컴포지션
	private BackgroundMap backgroundMap;

	private ImageIcon playerR, playerL;

	private int x, y;

	private boolean isLeft, isRight, isUp, isDown, leftWallCrash, rightWallCrash;

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

	public Player(BackgroundMap backgroundMap) {
		this.backgroundMap = backgroundMap;
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");

		x = 70;
		y = 535;
		// y = 180; // 바닥충돌감지 확인용 위치
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y); // JLabel의 위치

		isLeft = false;
		isRight = false;
		isUp = false;
		isDown = false;
		leftWallCrash = false;
		rightWallCrash = false;
	}

	// left(), right(), down()
	private void 바닥충돌감지() {
		// System.out.println(TAG + "바닥충돌계산중");
		int bottomColor = backgroundMap.getImage().getRGB(getX() + 10, getY() + 50 + 5) // -1 // 바닥이 있으면 값이 -2
				+ backgroundMap.getImage().getRGB(getX() + 50 - 10, getY() + 50 + 5); // -1

		if (bottomColor == -2) { // 바닥이 흰색
			// System.out.println("바닥이 흰색이에요");
			if (isUp == false && isDown == false)
				down();
		} else if (bottomColor != -2) { // 바닥에 장애물이 있다는 것.
			// System.out.println("바닥에 장애물이 있어요.");
			isUp = false;
			isDown = false;
		}
	}

	private void 왼쪽벽충돌감지() {
		// System.out.println(TAG + "왼쪽 충돌 계산중");
		Color leftcolor = new Color(backgroundMap.getImage().getRGB(getX() - 10, getY() + 25));
		if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
			// System.out.println(TAG + "왼쪽벽에 충돌했어요");
			leftWallCrash = true;
			isLeft = false;
		}

	}

	private void 오른쪽벽충돌감지() {
		// System.out.println(TAG + "오른쪽 충돌 계산중");
		Color leftcolor = new Color(backgroundMap.getImage().getRGB(getX() + 50 + 10, getY() + 25));
		if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
			// System.out.println(TAG + "오른쪽벽에 충돌했어요");
			rightWallCrash = true;
			isRight = false;
		}

	}

	@Override
	public void left() {
		// System.out.println(TAG + "left()");
		setIcon(playerL);
		rightWallCrash = false;
		isLeft = true;
		new Thread(() -> {
			try { // for문 안에 넣으면 try-catch가 for문 돌때마다 만들어짐
				while (isLeft) {
					x = x - 4;
					setLocation(x, y); // paintComponent()를 자동 호출해준다. 내부적으로 repaint가 된다.
					Thread.sleep(10);
					왼쪽벽충돌감지();
					바닥충돌감지();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).start();
	}

	@Override
	public void right() {
		// System.out.println(TAG + "right()");
		setIcon(playerR);
		leftWallCrash = false;
		isRight = true;
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

	} // 이게 종료될 때 paintComponent() -> repint()가 호출 됨.

	@Override
	public void up() {
		System.out.println(TAG + "up()");
		isUp = true;
		new Thread(() -> {
			try {
				for (int i = 0; i < 75; i++) {
					y = y - 2;
					setLocation(x, y);
					Thread.sleep(5);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// isUp = false;
			down();
		}).start();

	}

	@Override
	public void down() {

		isDown = true;
		System.out.println(TAG + "down()");
		new Thread(() -> {
			try {
				while (isDown) {
					y = y + 2;
					setLocation(x, y);
					Thread.sleep(3);
					바닥충돌감지();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
