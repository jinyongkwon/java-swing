package site.metacoding.practice;

/*
 *
 * @ author 권진용
 * 생성자 실행 순서
 *  initObject 1번실행
 *  initSetting 2번실행
 *  initListener 3번실행 (default)
 * 
 */

public interface Init { // 인터페이스로 강제화
	void initObject();

	void initSetting();

	default void initLister() {
	}; // 어댑터 패턴대신 default를 사용하면됨. => 구현해도 되고 안해도됨 => 강제성 x

}
