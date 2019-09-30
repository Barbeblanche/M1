package tp1;

import java.util.Date;

public class Entete {
	Titre titre;
	Titre auteur;
	Titre date;
	
	public Entete(String t, String a, Date d) {
		titre = new TitreGauche(t);
		auteur = new TitreGauche(a);
		date = new TitreGauche(d.toString());
	}
	
	public Entete(String t, String a) {
		titre = new TitreGauche(t);
		auteur = new TitreGauche(a);
		date = new TitreGauche(new Date().toString());
	}

	public void fixeLargeur(int l) {
		titre.fixeLargeur(l);
		auteur.fixeLargeur(l);
		date.fixeLargeur(l);
	}
	
	public String toString() {
		return titre.toString() + "\n" + auteur.toString() + "\n" + date.toString() + "\n";
	}
	
}
