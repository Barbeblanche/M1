public class ParagrapheGauche extends Paragraphe {
	// Produit une ligne alignée à gauche en ne faisant rien de spécial (pas d'indentation)
	@Override
	protected String produitLigne(StringBuilder ligneCourante) {
		// Suppression de l'espace terminal
		ligneCourante.deleteCharAt(ligneCourante.length()-1);
		return ligneCourante.toString();
	}
}
