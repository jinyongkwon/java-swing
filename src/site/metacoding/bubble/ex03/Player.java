package site.metacoding.bubble.ex03;

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
	}

	public void left() {
		setIcon(playerL);
		System.out.println("�����̵�");
		x = x - 10;
		setLocation(x, y); // paintcomponent �ϴ� ��.

	}

	public void right() {
		setIcon(playerR);
		System.out.println("������ �̵�");
		x = x + 10;
		setLocation(x, y);
	}

}
