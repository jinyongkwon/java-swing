package site.metacoding.bubble.ex07;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class backgroundMapService implements Runnable {

	private Player player;
	private BufferedImage image;

	public backgroundMapService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png")); // �̹��� ������ ���۷� ����
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
				Color leftcolor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
				Color rightcolor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));
				System.out.println(image.getRGB(player.getX(), player.getY() + 50 + 5)); //
				// �ٴڰ� Ȯ��
				int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5) // -1 // �ٴ��� ������ ���� -2
						+ image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5); // -1

				if (bottomColor != -2) { // ���� �浹 ����.
					player.setDown(false);
				} else if (bottomColor == -2) { // �ٴ��� ������ ������.
					if (player.isDown() == false && player.isUp() == false) {
						player.down();
					}
				}

				if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
					// System.out.println("���ʺ��� �浹��");
					player.setLeftWallCrash(true);
					player.setLeft(false);
				} else if (rightcolor.getRed() == 255 && rightcolor.getGreen() == 0 && rightcolor.getBlue() == 0) {
					// System.out.println("�����ʺ��� �浹��");
					player.setRightWallCrash(true);
					player.setRight(false);
				} else {
					player.setLeftWallCrash(false);
					player.setRightWallCrash(false);
				}

				Thread.sleep(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
