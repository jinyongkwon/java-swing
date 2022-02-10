package site.metacoding.bubble.ex06;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 백그라운드 서비스(데몬) : 독립적인 스레드로 돌려야 한다. ex)리스너, 카카오톡에서 메세지 오는지 체크하는 것
// 의존 해야될 애들을 항상 Composition 해야함
public class BackgroundMapService implements Runnable {

	// 컴포지션
	private Player player; // 플레이어가 많을경우 List사용
	private BufferedImage image;

	// 컴포지션을 위한 기술 => 의존성 주입 (생성자를 통해서 주입) = DI (Dependency Injection)
	public BackgroundMapService(Player player) {
		this.player = player;
		try {
			// raw 하게 읽는다는 뜻 : 날것 그대로 읽는 것
			// 화면에 붙일게 아니라서 JLabel로 읽을 필요 x
			image = ImageIO.read(new File("image/test.png"));
			// System.out.println(image);
			// System.out.println(image.getRGB(10, 10));
			System.out.println("플레이어 위치 x : " + player.getX());
			System.out.println("플레이어 위치 y : " + player.getY());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				Color color = new Color(image.getRGB(player.getX() + 50, player.getY()));
				System.out.println(color.getRed());
				System.out.println(color.getBlue());
				System.out.println(color.getGreen());
				System.out.println("==================");
				Thread.sleep(10); // 충돌감지를 미세하게 하는 조절법
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
