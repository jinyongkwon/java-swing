package site.metacoding.practice;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel implements Init, Moveable {

	private int move; // 이동할때 쓰는 변수.
	private int x; // 방울의 위치 x
	private int y; // 방울의 위치 y
	private int touch; // 초기값 = 0, 1이면 아군, 2이면 적군이 닿음.
	private ImageIcon bubble, bomb; // 눈에 보이는 이미지 bubble, bomb
	private Player player; // player와 관계를 맺기 위한 변수
	private BackgroundMap backgroundMap; // backgroundmap과 관계를 맺기 위한 변수
	private boolean isleft, isright, isup; // 방향으로 움직이고 있는중 인가에 대한 변수

	public Bubble(Player player, BackgroundMap backgroundMap) { // 컴포지션 => player와 backgroundMap과 관계를 맺음
		this.player = player;
		this.backgroundMap = backgroundMap;

		initObject();
		initSetting();
	}

	private void checkRightWall() { // 오른쪽 벽 충돌 체크
		Color rightColor = new Color(backgroundMap.getImage().getRGB(getX() + 50, getY() + 5)); // 버블의 오른쪽 값을 color로 확인
		if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			isright = false; // 오른쪽 값이 빨강이면 오른쪽 이동 중지.
		}
	}

	private void checkLeftWall() {
		Color leftColor = new Color(backgroundMap.getImage().getRGB(getX() + 10, getY() + 5));// 버블의 왼쪽 값을 color로 확인
		if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			isleft = false;// 왼쪽 값이 빨강이면 왼쪽 이동 중지.
		}
	}

	private void checkUpWall() {
		Color upleftColor = new Color(backgroundMap.getImage().getRGB(getX() + 10, getY() + 5));// 버블의 왼쪽 위 값을 color로 확인
		Color uprightColor = new Color(backgroundMap.getImage().getRGB(getX() + 50 - 10, getY() + 5)); // 버블의 오른쪽 위 값을
																										// color로 확인
		if ((upleftColor.getRed() == 255 && upleftColor.getGreen() == 0 && upleftColor.getBlue() == 0)
				&& (uprightColor.getRed() == 255 && uprightColor.getGreen() == 0 && uprightColor.getBlue() == 0)) {
			isup = false; // 왼쪽 위의 값과 오른쪽 위의 값이 둘다 빨강이면 상승 중지.
		} else {
			if (!isleft && !isright && !isup)
				up(); // 버블이 오른쪽, 왼쪽, 위쪽으로 이동중이지 않을때 상승.
		}
	}

	@Override
	public void initObject() {
		bubble = new ImageIcon("image/bubble.png"); // bubble 이미지 설정
		bomb = new ImageIcon("image/bomb.png"); // bomb 이미지 설정.
	}

	@Override
	public void initSetting() {
		x = player.getX(); // 버블의 시작 x위치를 player의 현재 x위치로 설정
		y = player.getY(); // 버블의 시작 y위치를 player의 현재 y위치로 설정
		isleft = false; // 왼쪽 이동 초기값 => 이동 x
		isright = false; // 오른쪽 이동 초기값 => 이동 x
		isup = false; // 위쪽 이동 초기값 => 이동 x
		touch = 0; // 버블이 아무데도 안 닿임
		setIcon(bubble); // 첫 이미지를 bubble로 설정
		setSize(50, 50); // 이미지 크기를 x=50, y=50으로 설정
		if (!player.isDirection())
			right(); // 플레이어가 오른쪽 방향을 보고 있으면 right() 실행
		else if (player.isDirection())
			left(); // 플레이어가 왼쪽 방향을 보고 있으면 left() 실행
	}

	private void nextAttack() { // 첫 공격후 다음 공격까지의 시간 계산
		new Thread(() -> { // 쓰레드를 사용하여 계산만 실행
			try {
				player.setAttack(true); // 공격 시작
				Thread.sleep(200); // 0.2초 수면
				player.setAttack(false); // 공격 끝
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}).start();

	}

	private void checkBubble() { // 버블이 누구에게 닿였는지 확인.
		touch = 0; // 방울 체크 초기화
		if (x + 50 - 5 + y + 50 - 5 <= player.getX() + 50 + player.getY() + 25
				&& player.getX() + 50 + player.getY() + 25 <= x + 5 + y + 5) {
			touch = 1;
		}

		System.out.println("x + 50 + y + 50 : " + ((x + 50 - 5) * (y + 50 - 5)));
		System.out
				.println("player.getX() + 50 + player.getY() + 25 : " + ((player.getX() + 25) * (player.getY() + 25)));
		System.out.println("x + y : " + ((x + 5) * (y + 5)));
		// System.out.println(touch);

		if (touch == 1) { // 아군에게 닿이면 첫번째 방법 실행
			clearBubble();
		} else if (touch == 2) { // 적군에게 닿이면 두번째 방법 실행.
			clearBubble2();
		}
	}

	private void clearBubble0() { // 아무일 없을때 자동으로 방울 터지게 만듬.
		try {
			Thread.sleep(4000); // 방울 발사 4초후
			clearBubble(); // 방울을 터지게 만듬.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearBubble() { // 방울을 터지게 만듬.
		try {
			setIcon(bomb); // bomb이미지로 변환
			Thread.sleep(500); // 0.5초후
			backgroundMap.remove(this); // 메모리에서 방울 삭제
			backgroundMap.repaint(); // 삭제되고 난후 다시 그림 그리기.
			isup = false; // 상승 중지.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearBubble2() { // 미완성
		try {
			Thread.sleep(2000);
			setIcon(bomb);
			Thread.sleep(500);
			backgroundMap.remove(this);
			backgroundMap.repaint();
			isup = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void left() { // 왼쪽 이동.
		nextAttack(); // 현재공격 후 다음 공격까지 계산
		isleft = true; // 이동 중으로 바꿈
		new Thread(() -> {
			try {
				while (isleft) { // 이동 중지 할때 까지 왼쪽으로 이동
					move++; // 이동하면 1씩 늘어남.
					x = x - 2; // 왼쪽으로 2씩 위치 이동
					setLocation(x, y); // 계속 이동할때마다 그림을 그려줌 // 스레드 안쓰면 반복문이 돌고나서 그림을 그림.
					Thread.sleep(1); // 0.001초 대기
					checkLeftWall(); // 왼쪽벽에 닿이는지 체크
					if (move == 100) {
						isleft = false; // 이동이 끝나면 중지
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			up(); // 왼쪽으로 모두 이동후 위로 상승 실행
		}).start();
	}

	@Override
	public void right() { // left()메서드와 설명 동일.
		nextAttack();
		isright = true;
		new Thread(() -> {
			try {
				while (isright) {
					move++;
					x = x + 2; // 오른쪽으로 2씩 위치 이동
					setLocation(x, y);
					Thread.sleep(1);
					checkRightWall();
					System.out.println(x);
					if (move == 100) {
						isright = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			up();

		}).start();
	}

	@Override
	public void up() { // 위로 상승 실행
		isup = true; // 이동 중
		new Thread(() -> {
			try {
				while (isup) { // 이동 중지 할때 까지 위쪽으로 이동
					y = y - 1;// 위쪽으로 1씩 위치 이동
					setLocation(x, y); // 계속 이동할때마다 그림을 그려줌 // 스레드 안쓰면 반복문이 돌고나서 그림을 그림.
					Thread.sleep(4); // 0.004초 대기
					checkUpWall(); // 위쪽벽에 닿이는지 체크
					checkBubble(); // player나 적군에게 닿이는지 체크
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (touch == 0)
				clearBubble0(); // 상승종료 후 아무에게 닿이지 않았으면 버블이 터지는것 실행.
		}).start();
	}
}
