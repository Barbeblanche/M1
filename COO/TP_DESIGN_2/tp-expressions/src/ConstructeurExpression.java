import java.io.IOException;
import java.io.InputStream;

class ConstructeurExpression {
	final static char EOF = (char) -1;
	final static char EOL = '\n';
	InputStream in;
	int courant;

	ConstructeurExpression(InputStream i) {
		in = i;
	}

	void avance() {
		try {
			courant = in.read();
			while ((courant != -1) && (courant == ' ')) {
				courant = in.read();
			}
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

	char courant() {
		return (char) courant;
	}

	boolean reconnait(char c) {
		if (courant() == c) {
			avance();
			return true;
		}
		return false;
	}

	Expression expression() {
		avance();
		Expression a = somme();
		if ((courant() == EOF) || (courant == '\n')) {
			return a;
		} else
			throw new RuntimeException("Expression invalide");
	}

	Expression somme() {
		Expression a = produit();
		if (reconnait('+')) {
			Expression p = new Somme();
			p.ajouteOperandeGauche(a);
			p.ajouteOperandeDroite(somme());
			return p;
		} else
			return a;
	}

	Expression produit() {
		Expression a = facteur();
		if (reconnait('*')) {
			Expression p = new Produit();
			p.ajouteOperandeGauche(a);
			p.ajouteOperandeDroite(produit());
			return p;
		} else
			return a;
	}

	Expression facteur() {
		if (reconnait('(')) {
			Expression val = somme();
			if (!reconnait(')'))
				throw new RuntimeException("Expression invalide");
			return val;
		} else {
			return nombre();
		}
	}

	Expression nombre() {
		int val = chiffre();
		if (val != -1) {
			int nouveau = chiffre();
			while (nouveau != -1) {
				val = val * 10 + nouveau;
				nouveau = chiffre();
			}
			return new Naturel(val);
		} else {
			throw new RuntimeException("Expression invalide");
		}
	}

	int chiffre() {
		if ((courant() >= '0') && (courant() <= '9')) {
			int val = courant() - '0';
			avance();
			return val;
		} else {
			return -1;
		}
	}

	public static void main(String[] args) {
		ConstructeurExpression r = new ConstructeurExpression(System.in);
		try {
			Expression e1 = null;
			while (true) {
				Expression e2 = r.expression();
				System.out.println("Forme totalement parenthésée : " + e2.formeParenthesee());
				System.out.println("Forme postfixée : " + e2.formePostfixee());
				if (e1 != null) {
					Expression p = new Produit();
					p.ajouteOperandeGauche(e1);
					p.ajouteOperandeDroite(e2);
					System.out.println("Produit des deux dernières expressions : " + p.formeParenthesee());
				}
				e1 = e2;
			}
		} catch (Exception e) {
			System.out.println("Terminé");
		}
	}
}
