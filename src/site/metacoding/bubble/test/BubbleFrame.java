package site.metacoding.bubble.test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import site.metacoding.bubble.ex05.Player;

public class BubbleFrame extends JFrame { // JFrame�� ����ؼ� JFrame���� �޼��� ��� ����.

	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject(); // new �ϴ°�
		initSetting(); // ���� ����
		initListener(); // ������.
		setVisible(true); // ���ο� paintComponent() ȣ�� �ڵ尡 �ִ�.
	}

	private void initObject() {
		backgroundMap = new JLabel();
		backgroundMap.setIcon(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap); // ��׶��� ȭ�� ����

		player = new Player(); // player ����.
		add(player); // player �׸�
	}

	private void initSetting() {
		setSize(1000, 640);
		getContentPane().setLayout(null); // JFrame�� �⺻ JPanel�� ���̾ƿ� ����
		setLocationRelativeTo(null); // ��� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư Ŭ���� JVM ���� �����ϱ�
	}

	private void initListener() {
		// Ű���带 �����°��� ���� ������.
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			// Ű���带 ���� ��
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("Ű���� ������");

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					// isRight�� false
					player.setRight(false);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					// isLeft�� false
					player.setLeft(false);
				}
			}

			// Ű���带 ������ ��
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					// Ű���带 ������ �ִ� ���� right() �޼��带 �ѹ��� ����
					if (player.isRight() == false) {
						player.right();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					// Ű���带 ������ �ִ� ���� left() �޼��带 �ѹ��� ����
					if (player.isLeft() == false) {
						player.left();
					}
				} else if (e.getKeyCode() == 38) {
					// Ű���带 ������ �ִ� ���� jump() �޼��带 �ѹ��� ����
					if (player.isJump() == false) {
						player.jump();
					}
				}

			}
		});
	}

	public static void main(String[] args) {
		new BubbleFrame();
	}

}
