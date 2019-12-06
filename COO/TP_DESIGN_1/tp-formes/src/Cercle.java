/**
 * Spécialisation de la forme pour dessiner un cercle. Le dessin est entièrement relatif pour être facilement compatible avec le reste.
 * @author Guillaume Huard
 */
public class Cercle extends Forme {
	/**
	 * Pas de paramètres particuliers, tout se règle via les méthodes de la forme.
	 * @param m MachineTrace à utiliser pour le dessin.
	 */
	Cercle(MachineTrace m) {
		super(m);
	}

	/**
	 * Spécialisation du dessin pour représenter un cercle.
	 */
	@Override
	void dessiner() {
		reset();
		m.avancer(taille / 2);
		m.baisser();
		m.tournerGauche(90);
		for (int i = 0; i < 360; i++) {
			m.avancer(Math.PI * taille / 360);
			m.tournerGauche(1);
		}
		m.tournerDroite(90);
	}
}
