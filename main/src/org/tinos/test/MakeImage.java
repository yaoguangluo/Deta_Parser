package org.tinos.test;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class MakeImage {
	public static void main(String[] args) throws IOException {
		MakeImage makeImage = new MakeImage();
		int height = 600;
		int weight = 800;
		BufferedImage image = new BufferedImage(weight, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.white);
		g.setFont(g.getFont());
		String prePrint = "测试文字";
		Font font = new Font(prePrint,20, 20);
		int stringW = g.getFontMetrics().stringWidth(prePrint);
		int stringH = g.getFontMetrics().getHeight();
		System.out.println(stringW + ":" + stringH);
		int positionX = 10;
		int positionY = 20;
		makeImage.weightPrint(prePrint, g, stringW, stringH, positionX, positionY, font);
		makeImage.heightPrint(prePrint,g, stringW, stringH, positionX, positionY, font);	
		String pathBin = "C:\\Users\\Administrator\\Desktop\\书\\images\\output.jpg";//output path
		File outputBin = new File(pathBin);
		ImageIO.write(image, "jpeg", outputBin);
	}

	public void weightPrint(String prePrint, Graphics2D g, int stringW, int stringH, int positionX, int positionY, Font font) {
		g.setFont(font);
		g.drawString(prePrint, positionX, positionY);
	}
	public void heightPrint(String prePrint, Graphics2D g, int stringW, int stringH, int positionX, int positionY, Font font) {
		g.setFont(font);
		g.drawString(prePrint, positionX, positionY);
	}
}
