package site.metacoding.practice;

// ����� ���� ����

interface Knife {
	void attack();

	void cook();
}

abstract class �丮������ implements Knife { // �߻�޼���� �޼��带 ������ �ʿ� x => ���� x
	@Override
	public void attack() {
	}
}

abstract class �ο�۾���� implements Knife {
	@Override
	public void cook() {
	}
}

class ������ extends �丮������ { // �θ� attack�� ��������.

	@Override
	public void cook() {
	}

}

class ������ extends �ο�۾���� {

	@Override
	public void attack() {
	}

}

public class PattenTest {

}
