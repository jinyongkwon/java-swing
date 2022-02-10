package site.metacoding.bubble.ex06;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// ��׶��� ����(����) : �������� ������� ������ �Ѵ�. ex)������, īī���忡�� �޼��� ������ üũ�ϴ� ��
// ���� �ؾߵ� �ֵ��� �׻� Composition �ؾ���
public class BackgroundMapService implements Runnable {

	// ��������
	private Player player; // �÷��̾ ������� List���
	private BufferedImage image;

	// ���������� ���� ��� => ������ ���� (�����ڸ� ���ؼ� ����) = DI (Dependency Injection)
	public BackgroundMapService(Player player) {
		this.player = player;
		try {
			// raw �ϰ� �д´ٴ� �� : ���� �״�� �д� ��
			// ȭ�鿡 ���ϰ� �ƴ϶� JLabel�� ���� �ʿ� x
			image = ImageIO.read(new File("image/test.png"));
			// System.out.println(image);
			// System.out.println(image.getRGB(10, 10));
			System.out.println("�÷��̾� ��ġ x : " + player.getX());
			System.out.println("�÷��̾� ��ġ y : " + player.getY());
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
				Thread.sleep(10); // �浹������ �̼��ϰ� �ϴ� ������
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
