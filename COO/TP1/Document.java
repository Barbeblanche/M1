import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Document {
	private Entete entete;
	private BlocDeTexte prologue;
	private ArrayList<Chapitre> chapitres;
	
	public Document(Entete e,InputStream in) {
		setEntete(e);
		setPrologue(new BlocDeTexte());
		setChapitres(new ArrayList<Chapitre>());
		initDocument(in);
	}
	
	public void initDocument(InputStream in) {
		Scanner s = new Scanner(in);
		Paragraphe p = null;
		Boolean sommaire=false;
		Boolean chapSuivant=false;
		Chapitre c =null;
		int numchap = 1;
		while (s.hasNextLine()) {
			String ligne = s.nextLine().trim();
			if (ligne.toLowerCase().contains("1.")) {
				sommaire = true;
			}
			if (!sommaire) {
				if (ligne.length() == 0) {
					if (p != null) {
						p.fixeLargeur(40);
						prologue.ajoute(p);
						p = null;
					}
				} else {
					if (p == null)
						p = new Paragraphe();
					String[] mots = ligne.split("\\s\\s*");
					for (int i = 0; i < mots.length; i++)
						p.ajoute(mots[i]);
				}
			}else {
				/*if (ligne.contains("CHAPITRE")) {
					if (c != null) {
						chapitres.add(c);
						c = new Chapitre(ligne);
						ligne = s.nextLine().trim();
					}else {
						c = new Chapitre(ligne);
						ligne = s.nextLine().trim();
					}
				}
				if (c!=null) {
					if (ligne.length() == 0) {
						if (p != null) {
							p.fixeLargeur(40);
							c.ajoute(p);
							p = null;
						}
					} else {
						if (p == null)
							p = new Paragraphe();
						String[] mots = ligne.split("\\s\\s*");
						for (int i = 0; i < mots.length; i++)
							p.ajoute(mots[i]);
					}
				}*/
				
			}
		}
		//chapitres.add(c); //Pour avoir le dernier chapitre
		s.close();
	}
	
	public void fixeLargeur(int l) {
		getEntete().fixeLargeur(l);
		getPrologue().fixeLargeur(l);
		for (Chapitre c : chapitres) {
			c.fixeLargeur(l);
		}
	}
	
	public void ecris(PrintStream p) {
		System.setOut(p);
		System.out.println(getEntete());
		getPrologue().ecris(p);
		for (Chapitre c : chapitres) {
			c.ecris(p);
		}
	}
	
	public Entete getEntete() {
		return entete;
	}

	public void setEntete(Entete entete) {
		this.entete = entete;
	}

	public BlocDeTexte getPrologue() {
		return prologue;
	}

	public void setPrologue(BlocDeTexte prologue) {
		this.prologue = prologue;
	}

	public ArrayList<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(ArrayList<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}
}
