package site.metacoding.practice;

public interface Moveable { // �������̽��� ����ȭ
	void left();

	void right();

	void up();

	default void down() {
	}; // ������ �ٿ��� ����.
}
