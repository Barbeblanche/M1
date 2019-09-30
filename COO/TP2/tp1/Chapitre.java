package tp1;

import java.io.PrintStream;

public class Chapitre {
	private BlocDeTexte bloc;
	private Titre titre;
	
	public Chapitre(String titre) {
		setTitre(new TitreGauche(titre));
		bloc = new BlocDeTexte();
		bloc.setChapitre(this);
	}
	
	public void ajoute(Paragraphe p) {
		getBloc().ajoute(p);
	}
	
	public void fixeLargeur(int l) {
		getTitre().fixeLargeur(l);
		getBloc().fixeLargeur(l);
	}
	
	public void ecris(PrintStream p) {
		System.setOut(p);
		System.out.println(getTitre());
		getBloc().ecris(p);
	}
	
	public String toString() {
		return "Titre : " + getTitre().getTexte() + " ; "
				+ "Infos bloc de texte : " + getBloc().toString();
	}
	
	Titre getTitre() {
		return titre;
	}

	void setTitre(Titre titre) {
		this.titre = titre;
	}

	BlocDeTexte getBloc() {
		return bloc;
	}

	void setBloc(BlocDeTexte bloc) {
		this.bloc = bloc;
	}
}
