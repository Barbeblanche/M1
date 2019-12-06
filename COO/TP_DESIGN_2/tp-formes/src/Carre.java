/**
 * Spécialisation de la forme pour dessiner un carré. Le dessin est entièrement relatif pour être facilement compatible avec le reste.
 * @author Guillaume Huard
 */
public class Carre extends Forme {
	/**
	 * Pas de paramètres particuliers, tout se règle via les méthodes de la forme.
	 * @param m MachineTrace à utiliser pour le dessin.
	 */
	Carre(MachineTrace m) {
		super(m);
	}

	/**
	 * Spécialisation du dessin pour représenter un carré.
	 */
	@Override
	void dessiner() {
		reset();
		m.avancer(taille/2);
		m.tournerGauche(90);
		m.avancer(taille/2);
		m.baisser();
		for (int i = 0; i < 4; i++) {
			m.tournerGauche(90);
			m.avancer(taille);
		}
		m.tournerDroite(90);
	}
}
