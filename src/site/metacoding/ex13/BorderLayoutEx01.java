package site.metacoding.ex13;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BorderLayoutEx01 extends MyFrame {

	public BorderLayoutEx01() {
		super(500, 500);

		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(new BorderLayout()); // ��������!! ����Ʈ�� BorderLayout �̴ϱ�!!

		JButton btnNorth = new JButton("����");
		JButton btnEast = new JButton("����");
		JButton btnWest = new JButton("����");
		JButton btnSouth = new JButton("����");
		JButton btnCenter = new JButton("���");

//		panel.add(btnNorth, BorderConstant.NORTH); // "North"�� ��� ���� ������ ������ ��.
//		panel.add(btnEast, BorderConstant.EAST); // ���� ���� �����̸� ������ �Ǽ��Ҽ� ������ BorderConstant�� �������
//		panel.add(btnWest, BorderConstant.WEST); // �̷��� �Ǽ��ұ�� Java���� ����� ������ ����.
//		panel.add(btnSouth, BorderConstant.SOUTH);
//		panel.add(btnCenter, BorderConstant.CENTER);

		panel.add(btnNorth, BorderLayout.NORTH); // �װ��� BorderLayout���� ����� ����.
		panel.add(btnEast, BorderLayout.EAST);
		panel.add(btnWest, BorderLayout.WEST);
		panel.add(btnSouth, BorderLayout.SOUTH);
		panel.add(btnCenter, BorderLayout.CENTER);

		setVisible(true); // paint �޼���
	}

	public static void main(String[] args) {
		new BorderLayoutEx01();

	}

}
