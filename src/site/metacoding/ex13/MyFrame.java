package site.metacoding.ex13;

import javax.swing.JFrame;

// Ctrl + Shift + O : import��������
// Ctrl + Shift + F : �ڵ�����
public class MyFrame extends JFrame {
	public MyFrame() {
		setSize(600, 400); // w,h
		System.out.println("MyFrame ����Ʈ");
		setLocationRelativeTo(null); // ������ ȭ�� �߾� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư Ŭ���� main ����
	}

	// �����ε��� �Ͽ� ���ϴ� ũ�⸦ ���Ҽ�����.
	public MyFrame(int w, int h) {
		setSize(w, h); // w,h
		System.out.println("MyFrame �����ε��� ������");
		setLocationRelativeTo(null); // ������ ȭ�� �߾� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư Ŭ���� main ����
	}
}
