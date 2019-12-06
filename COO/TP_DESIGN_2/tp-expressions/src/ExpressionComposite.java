/**
 * Une expression qui est une composition de deux sous expression
 * @author Guillaume Huard
 */
abstract class ExpressionComposite extends Expression {
	Expression gauche, droite;
	@Override
	void ajouteOperandeGauche(Expression e) {
		gauche = e;
	}
	@Override
	void ajouteOperandeDroite(Expression e) {
		droite = e;
	}
	@Override
	String formeParenthesee() {
		return "("+gauche.formeParenthesee()+toString()+droite.formeParenthesee()+")";
	}
	@Override
	String formePostfixee() {
		return gauche.formePostfixee()+" "+droite.formePostfixee()+" "+toString();
	}
}