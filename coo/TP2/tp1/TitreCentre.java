package tp1;

public class TitreCentre extends Titre{

	public TitreCentre(String t) {
		super(t);
	}

	@Override
	public String toString() {
		int nbEspaces = (largeur - texte.length()) / 2;
		String texteCentre = "";
		
		for(int i = 0; i < nbEspaces; i++) {
			texteCentre += " ";
		}
		texteCentre += texte;
		for(int i = 0; i < nbEspaces; i++) {
			texteCentre += " ";
		}
		return texteCentre;
	}

}
