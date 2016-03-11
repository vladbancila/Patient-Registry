package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import control.MyEventListener;
import domain.Pacient;
import sevice.Grup;

public class GUI extends JFrame {

	/**
	 * Clasa principala GUI ce contine fereastra programului, paneluri, butoane,
	 * campuri si clase interne
	 */
	private static final long serialVersionUID = 154353L;
	JButton buton1;
	JButton buton2;
	JButton buton3;
	JButton buton4;
	JButton buton5;
	JButton buton6;
	JList<Pacient> list;
	JScrollPane scroll;
	JRadioButton rbuton1;
	JRadioButton rbuton2;
	JRadioButton rbuton3;
	JLabel nume;
	JLabel prenume;
	JLabel telefon;
	JLabel email;
	JLabel adeverinta;
	JLabel cautare;
	public JTextField camp1;
	public JTextField camp2;
	public JTextField camp3;
	public JTextField camp4;
	public JTextField camp5;
	public JTextField camp6;
	public JPanel panel;
	Grup grup;

	public GUI() {
		initializeaza();
	}

	public void initializeaza() {
		MyEventListener listener = new MyEventListener(this);
		panel = new JPanel();

		nume = new JLabel("Nume : ");
		nume.setForeground(Color.CYAN);
		camp1 = new JTextField(25);
		camp1.setText("Introduceti numele");

		telefon = new JLabel(" Telefon mobil: ");
		telefon.setForeground(Color.CYAN);
		camp3 = new JTextField(25);
		camp3.setText("Introduceti numar telefon mobil");

		prenume = new JLabel("Prenume : ");
		prenume.setForeground(Color.CYAN);
		camp2 = new JTextField(25);
		camp2.setText("Introduceti prenumele");

		email = new JLabel("Email :");
		email.setForeground(Color.CYAN);
		camp4 = new JTextField(25);
		camp4.setText("Introduceti email persoana");

		adeverinta = new JLabel("CNP :");
		adeverinta.setForeground(Color.CYAN);
		camp5 = new JTextField(25);
		camp5.setText("Adauga seria  CNP ");

		cautare = new JLabel("Cautare pacient dupa CNP :");
		cautare.setForeground(Color.CYAN);
		camp6 = new JTextField(25);
		camp6.setText("Adauga serie CNP ");

		camp1.addMouseListener(listener);
		camp2.addMouseListener(listener);
		camp3.addMouseListener(listener);
		camp4.addMouseListener(listener);
		camp5.addMouseListener(listener);
		camp6.addMouseListener(listener);

		buton1 = new JButton("Salveaza datele pacientului");
		buton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nume = camp1.getText();
				String prenume = camp2.getText();
				String telefon = camp3.getText();
				String email = camp4.getText();
				String adeverinta = camp5.getText();

				try {
					Pacient pacient = new Pacient(nume, prenume, telefon, email, adeverinta);
					if (grup.pacienti.contains(pacient)) {
						camp1.setText("Pacient existent !");
					} else if (grup.verificaAdeverinta(adeverinta)) {
						camp1.setText("CNP duplicat !");
					} else {
						grup.adaugaPacient(pacient);
					}
				} catch (Exception ex) {
					System.out.println("Eroare la crearea obiectului Pacient. Nu corespund parametrii.");
					camp1.setText("Va rugam introduceti date valide !");

				}

			}
		});

		buton2 = new JButton("Actualizeaza datele pacientului selectat");
		buton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nume = camp1.getText();
				String prenume = camp2.getText();
				String telefon = camp3.getText();
				String email = camp4.getText();
				String adeverinta = camp5.getText();
				int index = list.getSelectedIndex();
				if (index != -1) {
					if (!grup.actualizeazaDatele(nume, prenume, telefon, email, adeverinta, index)) {
						camp1.setText("Actualizare esuata, verificati datele introduse!");
					}
				}
			}

		});

		buton3 = new JButton("Sterge datele pacientului selectat");
		buton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if (index != -1) {
					grup.stergePacient(index);
				}
			}
		});
		
		buton4 = new JButton("Cauta pacientul in lista dupa CNP");
		buton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String cnp = camp6.getText();
				int index = grup.cautaPacient(cnp);
				list.setSelectedIndex(index);

			}
		});
		
		buton5 = new JButton("Salveaza intreaga lista pentru utilizare ulterioara si in fisier ListaPacienti.txt");
		buton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Se salveaza in fisierul text 
				File file = new File("ListaPacienti.txt");
				String textIntrodus = "";
				for (Pacient pacient : grup.pacienti) {
					char i = 13;// 'Enter' caracter...
					textIntrodus += pacient.toString() + i;
				}
				try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
					br.write(textIntrodus + "\n");
				} catch (IOException exceptie) {
					System.out.println("Nu s-a putut scrie fisierul..." + file.toString());
				}
				// SCRIEREA OBIECTELOR IN FISIER

				ObjectOutputStream oos = null;
				FileOutputStream fos = null;

				try {
					fos = new FileOutputStream(new File("obiect"));// Creaza si
																	// scrie
																	// fisierul nou
					oos = new ObjectOutputStream(fos);

					for (Pacient pacient : grup.pacienti) {
						oos.writeObject(pacient);
					}
					oos.flush();
				} catch (IOException exceptie) {
					exceptie.printStackTrace();
				} finally {
					try {
						fos.close();
						oos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		
		buton6 = new JButton("Afisare date pacient selectat din lista");
		buton6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if (index != -1) {
					Pacient pacient = grup.returneazaPacient(index);
					camp1.setText(pacient.getNume());
					camp2.setText(pacient.getPrenume());
					camp3.setText(pacient.getTelefon());
					camp4.setText(pacient.getEmail());
					camp5.setText(pacient.getAdeverinta());
				}

			}
		});
		grup = new Grup(this);
		list = new JList<Pacient>();
		list.setBackground(Color.yellow);
		list.setForeground(Color.RED);
		list.setModel(grup);

		scroll = new JScrollPane();
		scroll.setViewportView(list);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		getContentPane().add(panel, BorderLayout.WEST);
		panel.setBackground(Color.blue);

		panel.add(nume);
		panel.add(camp1);
		panel.add(prenume);
		panel.add(camp2);
		panel.add(telefon);
		panel.add(camp3);
		panel.add(email);
		panel.add(camp4);
		panel.add(adeverinta);
		panel.add(camp5);
		panel.add(cautare);
		panel.add(camp6);

		panel.add(buton1);
		panel.add(buton2);
		panel.add(buton3);
		panel.add(buton4);
		panel.add(buton6);
		panel.add(buton5);

		panel.setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().add(scroll, BorderLayout.CENTER);

		grup.importaDatePacienti();// Se realizeaza importul pacientilor salvati

		this.setSize(800, 600);
		this.pack();
		this.setTitle("Ciorna registru pacienti");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
