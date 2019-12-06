import java.io.IOException;
import java.io.InputStream;

class Reconnaisseur {
	final static char EOF = (char) -1;
	final static char EOL = '\n';
	InputStream in;
	int courant;

	Reconnaisseur(InputStream i) {
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

	int expression() {
		avance();
		int a = somme();
		if ((courant() == EOF) || (courant == '\n')) {
			return a;
		} else
			throw new RuntimeException("Expression invalide");
	}

	int somme() {
		int a = produit();
		if (reconnait('+'))
			return a + somme();
		else
			return a;
	}

	int produit() {
		int a = facteur();
		if (reconnait('*'))
			return a * produit();
		else
			return a;
	}

	int facteur() {
		if (reconnait('(')) {
			int val = somme();
			if (!reconnait(')'))
				throw new RuntimeException("Expression invalide");
			return val;
		} else {
			return nombre();
		}
	}

	int nombre() {
		int val = chiffre();
		if (val != -1) {
			int nouveau = chiffre();
			while (nouveau != -1) {
				val = val * 10 + nouveau;
				nouveau = chiffre();
			}
			return val;
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
		Reconnaisseur r = new Reconnaisseur(System.in);
		try {
			while (true)
				System.out.println("Valeur de l'expression : " + r.expression());
		} catch (Exception e) {
			System.out.println("Terminé");
		}
	}
}
