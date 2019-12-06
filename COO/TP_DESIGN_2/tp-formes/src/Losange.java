/**
 * Spécialisation de la forme pour dessiner un losange. Le dessin est
 * entièrement relatif pour être facilement compatible avec le reste.
 * @author Guillaume Huard
 */
public class Losange extends Forme {
	/**
	 * Pas de paramètres particuliers, tout se règle via les méthodes de la forme.
	 * @param m MachineTrace à utiliser pour le dessin.
	 */
	Losange(MachineTrace m) {
		super(m);
	}

	/**
	 * Spécialisation du dessin pour représenter un losange.
	 */
	@Override
	void dessiner() {
		reset();
		m.avancer(taille / 2);
		m.tournerGauche(135);
		m.baisser();
		double cote = taille * Math.sqrt(2) / 2;
		for (int i = 0; i < 4; i++) {
			m.avancer(cote);
			m.tournerGauche(90);
		}
		m.tournerDroite(135);
	}
}
