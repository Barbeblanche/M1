public class ParagrapheDroite extends Paragraphe {
	// Produit une ligne alignée à droite en ajoutant des espaces devant
	@Override
	protected String produitLigne(StringBuilder ligneCourante) {
		// Suppression de l'espace terminal
		ligneCourante.deleteCharAt(ligneCourante.length()-1);
		// Indentation
		if (ligneCourante.length() < largeur) {
			int diff = largeur - ligneCourante.length();
			String complement = new String(new char[diff]).replace('\0', ' ');
			ligneCourante.insert(0, complement);
		}		
		return ligneCourante.toString();
	}
}
