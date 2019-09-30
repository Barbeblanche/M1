import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class TestParagraphe {
	public static void main(String[] args) {
		String nomFichier = "Candide.txt";
		try {
			InputStream in = new FileInputStream(nomFichier);
			Scanner s = new Scanner(in);
			Paragraphe p = null;
			while (s.hasNextLine()) {
				String ligne = s.nextLine().trim();
				if (ligne.length() == 0) {
					if (p != null) {
						p.fixeLargeur(40);
						System.out.println(p);
						p = null;
					}
				} else {
					if (p == null)
						p = new Paragraphe();
					String[] mots = ligne.split("\\s\\s*");
					for (int i = 0; i < mots.length; i++)
						p.ajoute(mots[i]);
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.err.println("Impossible de trouver " + nomFichier);
		}
	}
}