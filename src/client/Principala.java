package client;

import javax.swing.SwingUtilities;

import view.GUI;
import view.GUI2;

public class Principala {

	public static void main(String[] args) {

		GUI2 window = new GUI2();// Creeam o instanta din interfata GUI2.
		window.frame.setVisible(true);// Devine vizibila,pentru 2 secunde...

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(2000);// Delay de 2 secunde.
					new GUI();// Pornim programul principal.
					window.frame.setVisible(false);// GUI2 devine invizibila.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
