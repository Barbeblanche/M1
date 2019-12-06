/**
 * Un entier naturel.
 * @author Guillaume Huard
 */
class Naturel extends Expression {
	int valeur;
	Naturel(int v) {
		valeur = v;
	}
	@Override
	public String toString() {
		return Integer.toString(valeur);
	}
}