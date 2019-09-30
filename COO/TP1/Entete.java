import java.util.Date;

public class Entete {
	private Titre titre;
	private Titre auteur;
	Titre date;
	
	public Entete(String t, String a, Date d) {
		setTitre(new Titre(t));
		setAuteur(new Titre(a));
		date = new Titre(d.toString());
	}
	
	public Entete(String t, String a) {
		setTitre(new Titre(t));
		setAuteur(new Titre(a));
		date = new Titre(new Date().toString());
	}

	public void fixeLargeur(int l) {
		getTitre().fixeLargeur(l);
		getAuteur().fixeLargeur(l);
		date.fixeLargeur(l);
	}
	
	public String toString() {
		return getTitre().toString() + "\n" + getAuteur().toString() + "\n" + date.toString() + "\n";
	}

	public Titre getTitre() {
		return titre;
	}

	public void setTitre(Titre titre) {
		this.titre = titre;
	}

	public Titre getAuteur() {
		return auteur;
	}

	public void setAuteur(Titre auteur) {
		this.auteur = auteur;
	}
	
}
