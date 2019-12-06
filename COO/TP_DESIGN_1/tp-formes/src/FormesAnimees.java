import java.util.Random;

/**
 * Version modifiée du programme de base gérant les animations.
 * Les principaux changements sont :
 * <ul>
 * <li>le programme travaille maintenant sur des formes animées</li>
 * <li>la création d'une forme lui ajoute aléatoirement des caractéristiques d'animation</li>
 * <li>la gestion des pulsations est passée dans la forme animée, le programme principal ne fait plus que faire avancer les étapes</li>
 * </ul>
 * @author Guillaume Huard
 */
public class FormesAnimees {
	final static int nbFormes = 4;
	final static int nbObjets = 10;
	final static int largeur = 400;
	final static int hauteur = 400;
	final static int etapesPulsations = 20;
	final static int amplitudePulsation = 20;
	final static int vitesseRotation = 1;
	final static int lenteurClignotement = 3;
	final static int delai = 100;

	static FormeAnimee creerForme(MachineTrace m) {
		Random r = new Random();
		Forme f;
		switch (r.nextInt(nbFormes)) {
		case 0:
			f = new Carre(m);
			break;
		case 1:
			f = new Triangle(m);
			break;
		case 2:
			f = new Cercle(m);
			break;
		case 3:
			f = new Losange(m);
			break;
		default:
			throw new RuntimeException("Forme Inconnue");
		}
		f.fixerPosition(r.nextInt(largeur/2) - 100, r.nextInt(hauteur/2) - 100);
		int taille = r.nextInt(20) + 10;
		f.fixerTaille(taille);
		FormeAnimee fa =  new FormeAnimee(f);
		int caracteristiques = r.nextInt(8);
		if ((caracteristiques & 1) != 0)
			fa.activerPulsations(etapesPulsations, amplitudePulsation, taille);
		if ((caracteristiques & 2) != 0)
			fa.activerRotation(vitesseRotation);
		if ((caracteristiques & 4) != 0)
			fa.activerClignotement(lenteurClignotement);
		return fa;
	}

	public static void main(String[] args) {
		MachineTrace m;
		FormeAnimee[] f;

		m = new MachineTrace(largeur, hauteur);
		m.masquerPointeur();
		m.rafraichissementAutomatique(false);

		f = new FormeAnimee[nbObjets];
		for (int i = 0; i < f.length; i++) {
			f[i] = creerForme(m);
		}

		while (true) {
			for (int j = 0; j < etapesPulsations; j++) {
				m.effacerTout();
				for (int i = 0; i < f.length; i++) {
					f[i].avancerEtape();
					f[i].dessiner();
				}
				m.rafraichir();
				m.attendre(delai);
			}
		}
	}
}
