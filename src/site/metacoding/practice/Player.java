package site.metacoding.practice;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private static final String TAG = "Player : ";

	// ��������
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
		// y = 180; // �ٴ��浹���� Ȯ�ο� ��ġ
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y); // JLabel�� ��ġ

		isLeft = false;
		isRight = false;
		isUp = false;
		isDown = false;
		leftWallCrash = false;
		rightWallCrash = false;
	}

	// left(), right(), down()
	private void �ٴ��浹����() {
		// System.out.println(TAG + "�ٴ��浹�����");
		int bottomColor = backgroundMap.getImage().getRGB(getX() + 10, getY() + 50 + 5) // -1 // �ٴ��� ������ ���� -2
				+ backgroundMap.getImage().getRGB(getX() + 50 - 10, getY() + 50 + 5); // -1

		if (bottomColor == -2) { // �ٴ��� ���
			// System.out.println("�ٴ��� ����̿���");
			if (isUp == false && isDown == false)
				down();
		} else if (bottomColor != -2) { // �ٴڿ� ��ֹ��� �ִٴ� ��.
			// System.out.println("�ٴڿ� ��ֹ��� �־��.");
			isUp = false;
			isDown = false;
		}
	}

	private void ���ʺ��浹����() {
		// System.out.println(TAG + "���� �浹 �����");
		Color leftcolor = new Color(backgroundMap.getImage().getRGB(getX() - 10, getY() + 25));
		if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
			// System.out.println(TAG + "���ʺ��� �浹�߾��");
			leftWallCrash = true;
			isLeft = false;
		}

	}

	private void �����ʺ��浹����() {
		// System.out.println(TAG + "������ �浹 �����");
		Color leftcolor = new Color(backgroundMap.getImage().getRGB(getX() + 50 + 10, getY() + 25));
		if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
			// System.out.println(TAG + "�����ʺ��� �浹�߾��");
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
			try { // for�� �ȿ� ������ try-catch�� for�� �������� �������
				while (isLeft) {
					x = x - 4;
					setLocation(x, y); // paintComponent()�� �ڵ� ȣ�����ش�. ���������� repaint�� �ȴ�.
					Thread.sleep(10);
					���ʺ��浹����();
					�ٴ��浹����();
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
			try { // for�� �ȿ� ������ try-catch�� for�� �������� �������
				while (isRight) {
					x = x + 4;
					setLocation(x, y); // paintComponent()�� �ڵ� ȣ�����ش�. ���������� repaint�� �ȴ�.
					Thread.sleep(10);
					�����ʺ��浹����();
					�ٴ��浹����();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).start();

	} // �̰� ����� �� paintComponent() -> repint()�� ȣ�� ��.

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
					�ٴ��浹����();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
