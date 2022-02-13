package site.metacoding.practice;

public interface Moveable { // 인터페이스로 강제화
	void left();

	void right();

	void up();

	default void down() {
	}; // 버블은 다운이 없음.
}
