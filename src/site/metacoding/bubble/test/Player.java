package site.metacoding.bubble.test;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel { // // Player�� label�� ����� �ް� ������, seticon ����� �� �ִ�.
	private int x; // �÷��̾� ��ǥ x
	private int y; // �÷��̾� ��ǥ y
	private ImageIcon playerL, playerR;
	// �÷��̾� �������� �����϶� ������
	// �÷��̾� ���������� �����϶� ������

	private boolean isRight; // �������� �������� Ȯ��
	private boolean isLeft; // ������ �������� Ȯ��
	private boolean isJump; // ������ �������� Ȯ��
	private static final int JUMPSPEED = 2; // ���� �ӵ�
	private static final int SPEED = 4; // �̵� �ӵ�

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
		x = 70; // ������ġ
		y = 535; // ������ġ
		setIcon(playerR); // ���� �̹��� ����
		setSize(50, 50); // �÷��̾� ũ�� ����
		setLocation(x, y); // ��ġ ��ġ ����
		isRight = false; // �������� �����°��� ���� �⺻ ��
		isLeft = false; // �������� �����°��� ���� �⺻ ��
	}

	public void left() {
		isLeft = true; // ������ ������.
		setIcon(playerL); // ���� �̹����� ����
		System.out.println("�����̵�");
		new Thread(() -> {
			while (isLeft) { // ������ ������ ���� �ݺ�
				x = x - SPEED; // speed��ŭ �������� �̵� (-)
				setLocation(x, y); // �÷��̾� �� ��ġ
				try {
					Thread.sleep(10); // 0.01�ʾ� ���鼭 while�� ����
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start(); // ������ ����.
	}

	public void right() {
		isRight = true; // �������� ������.
		setIcon(playerR); // ������ �̹����� ����
		System.out.println("�������̵�");
		new Thread(() -> {
			while (isRight) { // �������� ������ ���� �ݺ�
				x = x + SPEED; // speed��ŭ ���������� �̵� (+)
				setLocation(x, y); // �÷��̾� �� ��ġ
				try {
					Thread.sleep(10); // 0.01�ʾ� ���鼭 while�� ����
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start(); // ������ ����.
	}

	public void jump() {
		System.out.println("�����̵�");
		isJump = true; // ������ ������.
		new Thread(() -> {
			// ������ ���̱��� �ö�� �ϹǷ� for���� ����
			for (int i = 0; i < 130 / JUMPSPEED; i++) {
				y = y - JUMPSPEED; // jumpspeed��ŭ ���� �̵�(-)
				setLocation(x, y); // �÷��̾� �� ��ġ
				try {
					Thread.sleep(10); // 0.01�ʾ� ���鼭 for�� ����
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// �ö󰬴ٰ� �߷¿� ���� �������� ��.
			for (int i = 0; i < 130 / JUMPSPEED; i++) {
				y = y + JUMPSPEED; // jumpspeed��ŭ �Ʒ��� �̵�
				setLocation(x, y);
				try {
					Thread.sleep(3); // 0.003�ʾ� ���鼭 for�� ����.
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			isJump = false; // �����ؼ� ������ �ȵǰԲ� thread�� ���κп� falseó��.
		}).start();
	}
}
