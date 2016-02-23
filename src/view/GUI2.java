package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

////   Aceasta este doar o fereastra de inceput, o interfata, sau un Logo al programului, 
//// des intalnit la toate marile firme si jocuri, ex: interfata de la Eclipse la pornire.

public class GUI2 {
	public JFrame frame;

	public GUI2() {
		initiializeaza();
	}

	private void initiializeaza() {

		frame = new JFrame();
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img = null;
		try {
			img = ImageIO.read(new File("image.jpg")).getScaledInstance(600, 400, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(img);

		JLabel imagine1 = new JLabel(imageIcon);
		frame.setContentPane(imagine1);
		frame.setLocationRelativeTo(null);

	}

}
