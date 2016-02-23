package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.GUI;

public class MyEventListener implements MouseListener {

	GUI gui;

	public MyEventListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == gui.camp1) {
			gui.camp1.setText("");
		} else if (e.getSource() == gui.camp2) {
			gui.camp2.setText("");
		} else if (e.getSource() == gui.camp3) {
			gui.camp3.setText("");
		} else if (e.getSource() == gui.camp4) {
			gui.camp4.setText("");
		} else if (e.getSource() == gui.camp5) {
			gui.camp5.setText("");
		} else if (e.getSource() == gui.camp6) {
			gui.camp6.setText("");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
