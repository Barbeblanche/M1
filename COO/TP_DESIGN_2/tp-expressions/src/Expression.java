/**
 * Une expression qui peut être constituée d'une valeurs ou d'une composition d'opérateurs
 * @author Guillaume Huard
 */
abstract class Expression {
	/**
	 * Ajoute une expression donnée comme opérande gauche.
	 * @param e l'expression à prendre comme opérande.
	 */
	void ajouteOperandeGauche(Expression e) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Ajoute une expression donnée comme opérande droite.
	 * @param e l'expression à prendre comme opérande.
	 */
	void ajouteOperandeDroite(Expression e) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Forme textuelle complètement parenthésée.
	 * @return la forme textuelle de l'expression.
	 */
	String formeParenthesee() {
		return toString();
	}
	/**
	 * Forme textuelle postfixée.
	 * @return la forme textuelle de l'expression.
	 */
	String formePostfixee() {
		return toString();
	}
}