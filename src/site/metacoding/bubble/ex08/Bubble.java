package site.metacoding.bubble.ex08;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// ��, ����1, ����2, �÷��̾�1, �÷��̾�2 = context
public class Bubble extends JLabel {

	private BubbleFrame context;
	private Player player;

	private int x;
	private int y;
	private int speed;

	private ImageIcon bubble, bomb;

	public Bubble(BubbleFrame context) {// ���� ������ �÷��̾ �ѱ�°� ���� => ������ ���Ǽ��� �������� ���� context�� �ѱ�.
		this.context = context;
		this.player = context.getPlayer();
		initObject();
		initSetting();
		initcheck();
	}

	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bomb = new ImageIcon("image/bomb.png");
	}

	private void initcheck() {
		if (player.getDirection() == -1) {
			left();
		} else if (player.getDirection() == 1 || player.getDirection() == 0) {
			right();
		}
	}

	private void initSetting() {
		x = player.getX();
		y = player.getY();
		setIcon(bubble);
		setSize(50, 50);
		speed = 1;
	}

	public void left() {
		x = x - 30;
		new Thread(() -> {
			for (int i = 0; i < 130; i++) {
				x = x - speed;
				setLocation(x, y);
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	public void right() {
		x = x + 30;
		new Thread(() -> {
			for (int i = 0; i < 130; i++) {
				x = x + speed;
				setLocation(x, y);
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
