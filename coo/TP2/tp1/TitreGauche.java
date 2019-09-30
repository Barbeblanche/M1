package tp1;

public class TitreGauche extends Titre{

	public TitreGauche(String t) {
		super(t);
	}

	@Override
	public String toString() {
		int nbEspaces = (largeur - texte.length()) ;
		String texteGauche = "";
		texteGauche += texte;
		for(int i = 0; i < nbEspaces; i++) {
			texteGauche += " ";
		}
		return texteGauche;
	}

}
