package site.metacoding.practice;

// 어댑터 패턴 예제

interface Knife {
	void attack();

	void cook();
}

abstract class 요리사어댑터 implements Knife { // 추상메서드는 메서드를 구현할 필요 x => 오류 x
	@Override
	public void attack() {
	}
}

abstract class 싸움꾼어댑터 implements Knife {
	@Override
	public void cook() {
	}
}

class 백종원 extends 요리사어댑터 { // 부모가 attack을 구현했음.

	@Override
	public void cook() {
	}

}

class 검투사 extends 싸움꾼어댑터 {

	@Override
	public void attack() {
	}

}

public class PattenTest {

}
