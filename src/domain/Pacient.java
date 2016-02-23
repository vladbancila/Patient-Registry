package domain;

import java.io.Serializable;

public class Pacient implements Serializable {

	private static final long serialVersionUID = 12552522L;
	private String nume;
	private String prenume;
	private String telefon;
	private String email;
	private String cnp;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pacient other = (Pacient) obj;
		if (cnp == null) {
			if (other.cnp != null)
				return false;
		} else if (!cnp.equals(other.cnp))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nume == null) {
			if (other.nume != null)
				return false;
		} else if (!nume.equals(other.nume))
			return false;
		if (prenume == null) {
			if (other.prenume != null)
				return false;
		} else if (!prenume.equals(other.prenume))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n" + "Pacient nume = " + nume + ", prenume = " + prenume + ", telefon = " + telefon + ", email = "
				+ email + ", cnp = " + cnp;
	}

	public Pacient(String nume, String prenume, String telefon, String email, String adeverinta) throws Exception {
		super();
		// (negatia conjunctiei === disjunctia negatiilor)
		if (!setNume(nume) || !setPrenume(prenume) || !setTelefon(telefon) || !setEmail(email)
				|| !setAdeverinta(adeverinta)) {
			throw new Exception();
		}

	}

	public String getNume() {
		return nume;
	}

	public boolean setNume(String nume) {
		String regex = "[A-Z][a-z]+";
		nume = nume.trim();
		if (nume.matches(regex)) {
			this.nume = nume;
			return true;
		}
		System.out.println("Va rugam repetati procedura.");		
		return false;
	}

	public String getPrenume() {
		return prenume;
	}

	public boolean setPrenume(String prenume) {
		// System.out.println(prenume + "se verifica");
		String regex = "[A-Z][a-z]+";
		prenume = prenume.trim();
		if (prenume.matches(regex)) {
			this.prenume = prenume;
			return true;
		}
		System.out.println("Va rugam repetati procedura.");
		return false;
	}

	public String getTelefon() {
		return telefon;
	}

	public boolean setTelefon(String telefon) {
		String regex = "07[0-9]{8}";
		if (telefon.matches(regex)) {
			this.telefon = telefon;
			return true;
		}
		System.out.println("Va rugam repetati procedura.");
		return false;
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		String regex = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (email.matches(regex)) {
			this.email = email;
			return true;
		}
		System.out.println("Va rugam repetati procedura ati introdus un email gresit");
		
		return false;
	}

	public String getAdeverinta() {
		return cnp;
	}

	public boolean setAdeverinta(String adeverinta) {
		String regex = "[0-9]{13}";
		if (adeverinta.matches(regex)) {
			this.cnp = adeverinta;
			return true;

		}
		System.out.println("Va rugam repetati procedura.");
		return false;
	}

}
