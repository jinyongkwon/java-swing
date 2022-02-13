package site.metacoding.practice;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private Player player; // player context
	private static final String TAG = "Player : ";

	// ��������
	private BackgroundMap backgroundMap;

	private ImageIcon playerR, playerL; // ���� ���� �̹���, ������ ���� �̹���.

	private int x, y; // �÷��̾��� ��ġ x,y

	private boolean isLeft, isRight, isUp, isDown, isAttack, leftWallCrash, rightWallCrash, direction;
	// ���� �������ΰ��� ���� ������.
	// direction : ������ false,������ true

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

	public Player(BackgroundMap backgroundMap) { // backgroundmap�� ���踦 ���� => ��������
		player = this; // ���� �������ֱ� ���� context
		this.backgroundMap = backgroundMap; // player���� ������ backgroundmap�� ���踦 ���� backgroundmap�� ����.
		// �� backgroundmap���� backgroundmap���ο��ִ� public �޼��� ��밡��.
		playerR = new ImageIcon("image/playerR.png"); // playerR�̹��� ����
		playerL = new ImageIcon("image/playerL.png"); // playerL�̹��� ����.

		x = 70; // �÷��̾��� ù x ��ġ
		y = 535; // �÷��̾��� ù y ��ġ
		// y = 180; // �ٴ��浹���� Ȯ�ο� ��ġ
		setIcon(playerR); // ���� �̹����� playerR�� ����
		setSize(50, 50); // �̹��� ũ�⸦ x=50, y= 50���� ����.
		setLocation(x, y); // JLabel�� ��ġ // player�� ���� ��ġ�� x,y������ ����.

		isLeft = false; // �ʱ� ���� �̵��� Ȯ�� �� => �̵� x
		isRight = false; // �ʱ� ������ �̵��� Ȯ�� �� => �̵� x
		isUp = false; // �ʱ� ���� �̵��� Ȯ�� �� => �̵� x
		isDown = false; // �ʱ� �Ʒ��� �̵��� Ȯ�� �� => �̵� x
		leftWallCrash = false; // �ʱ� ���ʺ��� �ε������� Ȯ�� �� => �ε��� x
		rightWallCrash = false; // �ʱ� �����ʺ��� �ε������� Ȯ�� �� => �ε��� x
		direction = false; // �ʱ� �÷��̾ �����ִ� ���� �� => ������.
	}

	public void attack() { // ����
		Bubble bubble = new Bubble(player, backgroundMap); // ��� ����
		backgroundMap.add(bubble); // ��濡 ������ �߰�
	}

	// left(), right(), down()
	private void �ٴ��浹����() { // �ٴڿ� �ε������� Ȯ��
		// System.out.println(TAG + "�ٴ��浹�����");
		int bottomColor = backgroundMap.getImage().getRGB(getX() + 10, getY() + 50 + 5) // -1 // �ٴ��� ������ ���� -2
				+ backgroundMap.getImage().getRGB(getX() + 50 - 10, getY() + 50 + 5); // -1 // �ٴ� �ִ��� Ȯ�� ��.

		if (bottomColor == -2) { // �ٴ��� ��� => �ٴڿ� �ƹ��͵� ������.
			// System.out.println("�ٴ��� ����̿���");
			if (isUp == false && isDown == false)
				down(); // �ö��� �ʴ����̰� �������� �ʴ��߿��� �Ʒ��� �̵�.
		} else if (bottomColor != -2) { // �ٴڿ� ��ֹ��� �ִٴ� ��.
			// System.out.println("�ٴڿ� ��ֹ��� �־��.");
			isDown = false; // �ٴڿ� ���� ������ �Ʒ��� �̵� ����.
		}
	}

	private void ���ʺ��浹����() { // ���� ���� �ε������� Ȯ��.
		// System.out.println(TAG + "���� �浹 �����");
		Color leftcolor = new Color(backgroundMap.getImage().getRGB(getX() - 10, getY() + 25)); // �÷��̾� ���� ������ color������
																								// �ٲ�.
		if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
			// System.out.println(TAG + "���ʺ��� �浹�߾��");
			leftWallCrash = true;
			isLeft = false; // �÷��̾� ���� ���� ���� �����̸� ������ �ε����ٴ°� Ȯ���� ���� �̵� ����.
		}

	}

	private void �����ʺ��浹����() {// ������ ���� �ε������� Ȯ��.
		// System.out.println(TAG + "������ �浹 �����");
		Color leftcolor = new Color(backgroundMap.getImage().getRGB(getX() + 50 + 10, getY() + 25));// �÷��̾� ���� ��������
																									// color������ �ٲ�.
		if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
			// System.out.println(TAG + "�����ʺ��� �浹�߾��");
			rightWallCrash = true;
			isRight = false; // �÷��̾� ���� ������ ���� �����̸� �������� �ε����ٴ°� Ȯ���� ������ �̵� ����.
		}

	}

	@Override
	public void left() { // ���� �̵�
		// System.out.println(TAG + "left()");
		setIcon(playerL); // �̹����� PlayerL�� ����
		rightWallCrash = false; // ������ ������ �������� �Ⱥε����ٰ� Ȯ��
		isLeft = true; // �̵������� �ٲ�
		direction = true; // �����ִ� ������ �������� ����
		new Thread(() -> {
			try { // for�� �ȿ� ������ try-catch�� for�� �������� �������
				while (isLeft) { // �̵� ������ �ٲ� ���� �̵�.
					x = x - 4; // 4�� �������� �̵�
					setLocation(x, y); // paintComponent()�� �ڵ� ȣ�����ش�. ���������� repaint�� �ȴ�.
					Thread.sleep(10); // 0.01�� ���
					���ʺ��浹����(); // ���� �� �浹 Ȯ��
					�ٴ��浹����(); // �ٴ��� ���� ��� ���������ϹǷ� �ٴ� �浹 Ȯ��
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).start();
	}

	@Override
	public void right() { // left()����� ����.
		// System.out.println(TAG + "right()");
		setIcon(playerR);
		leftWallCrash = false;
		isRight = true;
		direction = false;
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

	} // �̰� ����� �� paintComponent() -> repaint()�� ȣ�� ��.

	@Override
	public void up() { // ���� �̵�.
		// System.out.println(TAG + "up()");
		isUp = true; // �������� �̵���
		new Thread(() -> {
			try {
				for (int i = 0; i < 70; i++) { // ������ ��ġ(140)��ŭ �������� �̵�
					y = y - 2; // ���� 2�� �̵�
					setLocation(x, y); // �׸��� �ٽ� �׷���
					Thread.sleep(5); // 0.005�� ���
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			isUp = false; // ���� �̵� ��.
			down(); // �ö����� �߷¿� ���� �ٴڱ��� ������.
		}).start();
		// System.out.println(isUp);
	}

	@Override
	public void down() { // �Ʒ��� �̵�

		// System.out.println(isUp);
		isDown = true; // �̵���
		// System.out.println(TAG + "down()");
		new Thread(() -> {
			try {
				while (isDown) { // �̵� �����Ҷ����� �̵�
					y = y + 2; // �Ʒ��� 2�� �̵�
					setLocation(x, y); // �׸��� �ٽ� �׸�.
					Thread.sleep(3); // 0.003�� ���
					�ٴ��浹����(); // �ٴ��浹���� ����.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
