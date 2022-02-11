package site.metacoding.practice;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class BubbleApp extends JFrame implements Init {

	private static final String TAG = "BubbleApp : ";

	private BubbleApp mContext = this;

	private BackgroundMap backgroundMap;
	private Player player;

	public BubbleApp() {
		// System.out.println(TAG + "������");
		initObject();
		initSetting();
		initLister();

		setVisible(true);
	}

	@Override
	public void initObject() {
		backgroundMap = new BackgroundMap();
		setContentPane(backgroundMap); // DIV�ڽ� ��ü�� ���ȭ������ ����

		player = new Player(backgroundMap);
		add(player);
	}

	@Override
	public void initSetting() {
		setSize(1000, 640);
		getContentPane().setLayout(null); // JFrame�� �⺻ JPanel�� ���̾ƿ� ����
		setLocationRelativeTo(null); // ��� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư Ŭ���� JVM ���� �����ϱ�
	}

	@Override
	public void initLister() {
		mContext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) { // ��! == case ��
				case KeyEvent.VK_RIGHT:
					if (player.isRight() == false && !player.isRightWallCrash()) {
						player.right();
					}
					break;// switch ����
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isLeftWallCrash())
						player.left(); // �������� ���� ���� ���
					break;
				case KeyEvent.VK_UP:
					if (player.isUp() == false) {
						System.out.println(player.isUp());
						player.up();
					}
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) { // ��! == case ��
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break; // switch ����
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;

				}
			}
		});
	}

	public static void main(String[] args) {
		new BubbleApp();

	}

}
