package site.metacoding.bubble.ex04;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * @author codingfarm �÷��̾�� �¿� �̵��� �����ϴ�. ������ �����ϴ� ��� ��
 * 
 */

public class Player extends JLabel { // Player�� label�� ����� �ް� ������, seticon ����� �� �ִ�.
	private int x;
	private int y;
	private ImageIcon playerL, playerR;

	private boolean isRight;
	private boolean isLeft;

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

	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerR = new ImageIcon("Image/playerR.png");
		playerL = new ImageIcon("Image/playerL.png");
	}

	private void initSetting() {
		x = 70;
		y = 535;
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y); // paintComponent ȣ������
		isRight = false;
		isLeft = false;
	}

	public void left() {
		setIcon(playerL);
		System.out.println("�����̵�");
		x = x - 10;
		setLocation(x, y); // paintcomponent �ϴ� ��.

	}

	public void right() {
		isRight = true;
		setIcon(playerR);
		System.out.println("������ �̵�");
		new Thread(() -> {
			while (isRight) {
				x = x + 10;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

}
