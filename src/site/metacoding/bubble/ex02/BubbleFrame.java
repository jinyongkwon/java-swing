package site.metacoding.bubble.ex02;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author codingfarm ����: ���ȭ�� ����,ĳ���� �߰�.
 */
public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		setVisible(true); // ���ο� paintComponent()ȣ�� �ڵ尡 �ִ�.
	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		player = new Player();
		add(player);

	}

	private void initSetting() {
		setSize(1000, 640);
		getContentPane().setLayout(null); // JFRAME�� �⺻ JPanel�� ���̾ƿ� ����.
		setLocationRelativeTo(null); // ��� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư Ŭ���� jvm ���� ������
	}

	public static void main(String[] args) {
		new BubbleFrame();

	}

}
