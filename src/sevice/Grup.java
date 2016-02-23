package sevice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

import domain.Pacient;
import view.GUI;

public class Grup extends AbstractListModel<Pacient> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 15453L;
	private GUI gui;

	public Grup(GUI gui) {
		super();
		this.gui = gui;
	}

	public ArrayList<Pacient> pacienti = new ArrayList<Pacient>();

	@Override
	public int getSize() {
		return pacienti.size();
	}

	@Override
	public Pacient getElementAt(int index) {
		return pacienti.get(index);
	}

	public void adaugaPacient(Pacient pacient) {
		pacienti.add(pacient);
		this.fireContentsChanged(this, -1, -1);
	}

	public void stergePacient(int index) {
		pacienti.remove(index);
		this.fireContentsChanged(this, -1, -1);
	}

	public boolean actualizeazaDatele(String nume, String prenume, String telefon, String email, String adeverinta,
			int index) {
		Pacient pacient = pacienti.get(index);
		if (!pacient.setNume(nume) || !pacient.setPrenume(prenume) || !pacient.setTelefon(telefon)
				|| !pacient.setEmail(email) || !pacient.setAdeverinta(adeverinta)) {
			return false;
		}
		this.fireContentsChanged(this, -1, -1);
		return true;
	}

	public int cautaPacient(String cnp) {
		for (int i = 0; i < pacienti.size(); i++) {
			if (pacienti.get(i).getAdeverinta().equals(cnp)) {
				return i;
			}
		}
		return -1;

	}

	public boolean verificaAdeverinta(String adeverinta) {
		for (Pacient pacient : pacienti) {
			if (pacient.getAdeverinta().equals(adeverinta)) {
				return true;
			}
		}
		return false;
	}

	public Pacient returneazaPacient(int index) {
		Pacient pacient = pacienti.get(index);

		return pacient;
	}

	public void importaDatePacienti() {
		System.out.println("Citim fisierul:");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		File fisierCeTrebuieCitit = new File("obiect");

		try {
			fis = new FileInputStream(fisierCeTrebuieCitit);
			ois = new ObjectInputStream(fis);
			Pacient pacient;

			while ((pacient = (Pacient) ois.readObject()) != null) {

				pacienti.add(pacient);
				System.out.println(pacient);
			}

		} catch (IOException | ClassNotFoundException e) {

			System.out.println("Inghitim eroarea EndOfFileException sau alte erori.");

		} finally {

			try {
				fis.close();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
