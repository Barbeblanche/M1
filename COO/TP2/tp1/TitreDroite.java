package tp1;

public class TitreDroite extends Titre {

	public TitreDroite(String t) {
		super(t);
	}

	@Override
	public String toString() {
		int nbEspaces = (largeur - texte.length());
		String texteDroite = "";
		
		for(int i = 0; i < nbEspaces; i++) {
			texteDroite += " ";
		}
		texteDroite += texte;
		return texteDroite;
	}

}
