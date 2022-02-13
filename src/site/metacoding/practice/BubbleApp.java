package site.metacoding.practice;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class BubbleApp extends JFrame implements Init {

	private static final String TAG = "BubbleApp : "; // �׽�Ʈ�� ���� �±�

	private BubbleApp mContext = this; // ���������� ���� context

	private BackgroundMap backgroundMap; // backgroundMap�� ����
	private Player player; // player�� ����

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BubbleApp getmContext() {
		return mContext;
	}

	public void setmContext(BubbleApp mContext) {
		this.mContext = mContext;
	}

	public BubbleApp() {
		// System.out.println(TAG + "������");
		initObject();
		initSetting();
		initLister();

		setVisible(true); // GUI�� ȭ�鿡 �ٿ�.
	}

	@Override
	public void initObject() {
		backgroundMap = new BackgroundMap(); // backgroundMap����
		setContentPane(backgroundMap); // DIV�ڽ� ��ü�� ���ȭ������ ����

		player = new Player(backgroundMap); // �÷��̾� ����.
		add(player); // �÷��̾� �߰�.
	}

	@Override
	public void initSetting() {
		setSize(1000, 640); // ��ü ����� x = 1000, y = 640���� ����
		getContentPane().setLayout(null); // JFrame�� �⺻ JPanel�� ���̾ƿ� ����
		setLocationRelativeTo(null); // ��� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư Ŭ���� JVM ���� �����ϱ�
	}

	@Override
	public void initLister() {
		mContext.addKeyListener(new KeyAdapter() { // key�� ���� �̺�Ʈ�� Ȯ���ϱ� ���� ������ ����.
			@Override
			public void keyPressed(KeyEvent e) { // Ű�� ���������� Ȯ��
				switch (e.getKeyCode()) { // ��! == case ��
				case KeyEvent.VK_RIGHT: // ������ ����Ű�� ��������
					if (player.isRight() == false && !player.isRightWallCrash()) {
						player.right(); // �÷��̾ ���������� �̵������� �ʰ�, ������ ���� �ε����� �ʾ����� ���������� �̵�.
					}
					break;// switch ����
				case KeyEvent.VK_LEFT: // ���� ����Ű�� ��������
					if (!player.isLeft() && !player.isLeftWallCrash())
						player.left(); // �������� ���� ���� ��� // �÷��̾ �������� �̵������� �ʰ�, ���� ���� �ε����� �ʾ����� �������� �̵�.
					break;
				case KeyEvent.VK_UP: // ���� ����Ű�� ��������
					if (player.isUp() == false && !player.isDown()) {
						// System.out.println(player.isUp());
						player.up(); // �÷��̾ �������� �̵������� �ʰ�, �Ʒ������� �̵� ������ ������ up() ȣ��.
					}
					break;
				case KeyEvent.VK_SPACE: // �����̽��ٸ� ��������
					if (player.isAttack() == false) {
						player.attack(); // �÷��̾ ���������� ������ ���ݽ���.
					}
					break;
				}

			}

			@Override
			public void keyReleased(KeyEvent e) { // Ű�� �������� Ȯ��
				switch (e.getKeyCode()) { // ��! == case ��
				case KeyEvent.VK_RIGHT: // ������ ����Ű�� ������
					player.setRight(false); // ������ �̵� ����.
					break; // switch ����
				case KeyEvent.VK_LEFT: // ���� ����Ű�� ������
					player.setLeft(false); // ���� �̵� ����.
					break;

				}
			}
		});
	}

	public static void main(String[] args) {
		new BubbleApp(); // bubbleApp ����

	}

}
