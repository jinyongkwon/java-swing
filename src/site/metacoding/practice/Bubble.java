package site.metacoding.practice;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel implements Init, Moveable {

	private int move; // �̵��Ҷ� ���� ����.
	private int x; // ����� ��ġ x
	private int y; // ����� ��ġ y
	private int touch; // �ʱⰪ = 0, 1�̸� �Ʊ�, 2�̸� ������ ����.
	private ImageIcon bubble, bomb; // ���� ���̴� �̹��� bubble, bomb
	private Player player; // player�� ���踦 �α� ���� ����
	private BackgroundMap backgroundMap; // backgroundmap�� ���踦 �α� ���� ����
	private boolean isleft, isright, isup; // �������� �����̰� �ִ��� �ΰ��� ���� ����

	public Bubble(Player player, BackgroundMap backgroundMap) { // �������� => player�� backgroundMap�� ���踦 ����
		this.player = player;
		this.backgroundMap = backgroundMap;

		initObject();
		initSetting();
	}

	private void checkRightWall() { // ������ �� �浹 üũ
		Color rightColor = new Color(backgroundMap.getImage().getRGB(getX() + 50, getY() + 5)); // ������ ������ ���� color�� Ȯ��
		if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			isright = false; // ������ ���� �����̸� ������ �̵� ����.
		}
	}

	private void checkLeftWall() {
		Color leftColor = new Color(backgroundMap.getImage().getRGB(getX() + 10, getY() + 5));// ������ ���� ���� color�� Ȯ��
		if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			isleft = false;// ���� ���� �����̸� ���� �̵� ����.
		}
	}

	private void checkUpWall() {
		Color upleftColor = new Color(backgroundMap.getImage().getRGB(getX() + 10, getY() + 5));// ������ ���� �� ���� color�� Ȯ��
		Color uprightColor = new Color(backgroundMap.getImage().getRGB(getX() + 50 - 10, getY() + 5)); // ������ ������ �� ����
																										// color�� Ȯ��
		if ((upleftColor.getRed() == 255 && upleftColor.getGreen() == 0 && upleftColor.getBlue() == 0)
				&& (uprightColor.getRed() == 255 && uprightColor.getGreen() == 0 && uprightColor.getBlue() == 0)) {
			isup = false; // ���� ���� ���� ������ ���� ���� �Ѵ� �����̸� ��� ����.
		} else {
			if (!isleft && !isright && !isup)
				up(); // ������ ������, ����, �������� �̵������� ������ ���.
		}
	}

	@Override
	public void initObject() {
		bubble = new ImageIcon("image/bubble.png"); // bubble �̹��� ����
		bomb = new ImageIcon("image/bomb.png"); // bomb �̹��� ����.
	}

	@Override
	public void initSetting() {
		x = player.getX(); // ������ ���� x��ġ�� player�� ���� x��ġ�� ����
		y = player.getY(); // ������ ���� y��ġ�� player�� ���� y��ġ�� ����
		isleft = false; // ���� �̵� �ʱⰪ => �̵� x
		isright = false; // ������ �̵� �ʱⰪ => �̵� x
		isup = false; // ���� �̵� �ʱⰪ => �̵� x
		touch = 0; // ������ �ƹ����� �� ����
		setIcon(bubble); // ù �̹����� bubble�� ����
		setSize(50, 50); // �̹��� ũ�⸦ x=50, y=50���� ����
		if (!player.isDirection())
			right(); // �÷��̾ ������ ������ ���� ������ right() ����
		else if (player.isDirection())
			left(); // �÷��̾ ���� ������ ���� ������ left() ����
	}

	private void nextAttack() { // ù ������ ���� ���ݱ����� �ð� ���
		new Thread(() -> { // �����带 ����Ͽ� ��길 ����
			try {
				player.setAttack(true); // ���� ����
				Thread.sleep(200); // 0.2�� ����
				player.setAttack(false); // ���� ��
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}).start();

	}

	private void checkBubble() { // ������ �������� �꿴���� Ȯ��.
		touch = 0; // ��� üũ �ʱ�ȭ
		if (x + 50 - 5 + y + 50 - 5 <= player.getX() + 50 + player.getY() + 25
				&& player.getX() + 50 + player.getY() + 25 <= x + 5 + y + 5) {
			touch = 1;
		}

		System.out.println("x + 50 + y + 50 : " + ((x + 50 - 5) * (y + 50 - 5)));
		System.out
				.println("player.getX() + 50 + player.getY() + 25 : " + ((player.getX() + 25) * (player.getY() + 25)));
		System.out.println("x + y : " + ((x + 5) * (y + 5)));
		// System.out.println(touch);

		if (touch == 1) { // �Ʊ����� ���̸� ù��° ��� ����
			clearBubble();
		} else if (touch == 2) { // �������� ���̸� �ι�° ��� ����.
			clearBubble2();
		}
	}

	private void clearBubble0() { // �ƹ��� ������ �ڵ����� ��� ������ ����.
		try {
			Thread.sleep(4000); // ��� �߻� 4����
			clearBubble(); // ����� ������ ����.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearBubble() { // ����� ������ ����.
		try {
			setIcon(bomb); // bomb�̹����� ��ȯ
			Thread.sleep(500); // 0.5����
			backgroundMap.remove(this); // �޸𸮿��� ��� ����
			backgroundMap.repaint(); // �����ǰ� ���� �ٽ� �׸� �׸���.
			isup = false; // ��� ����.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearBubble2() { // �̿ϼ�
		try {
			Thread.sleep(2000);
			setIcon(bomb);
			Thread.sleep(500);
			backgroundMap.remove(this);
			backgroundMap.repaint();
			isup = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void left() { // ���� �̵�.
		nextAttack(); // ������� �� ���� ���ݱ��� ���
		isleft = true; // �̵� ������ �ٲ�
		new Thread(() -> {
			try {
				while (isleft) { // �̵� ���� �Ҷ� ���� �������� �̵�
					move++; // �̵��ϸ� 1�� �þ.
					x = x - 2; // �������� 2�� ��ġ �̵�
					setLocation(x, y); // ��� �̵��Ҷ����� �׸��� �׷��� // ������ �Ⱦ��� �ݺ����� ������ �׸��� �׸�.
					Thread.sleep(1); // 0.001�� ���
					checkLeftWall(); // ���ʺ��� ���̴��� üũ
					if (move == 100) {
						isleft = false; // �̵��� ������ ����
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			up(); // �������� ��� �̵��� ���� ��� ����
		}).start();
	}

	@Override
	public void right() { // left()�޼���� ���� ����.
		nextAttack();
		isright = true;
		new Thread(() -> {
			try {
				while (isright) {
					move++;
					x = x + 2; // ���������� 2�� ��ġ �̵�
					setLocation(x, y);
					Thread.sleep(1);
					checkRightWall();
					System.out.println(x);
					if (move == 100) {
						isright = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			up();

		}).start();
	}

	@Override
	public void up() { // ���� ��� ����
		isup = true; // �̵� ��
		new Thread(() -> {
			try {
				while (isup) { // �̵� ���� �Ҷ� ���� �������� �̵�
					y = y - 1;// �������� 1�� ��ġ �̵�
					setLocation(x, y); // ��� �̵��Ҷ����� �׸��� �׷��� // ������ �Ⱦ��� �ݺ����� ������ �׸��� �׸�.
					Thread.sleep(4); // 0.004�� ���
					checkUpWall(); // ���ʺ��� ���̴��� üũ
					checkBubble(); // player�� �������� ���̴��� üũ
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (touch == 0)
				clearBubble0(); // ������� �� �ƹ����� ������ �ʾ����� ������ �����°� ����.
		}).start();
	}
}
