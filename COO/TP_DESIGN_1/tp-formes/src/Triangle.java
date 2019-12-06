/**
 * Spécialisation de la forme pour dessiner un triangle. Le dessin est entièrement
 * relatif pour être facilement compatible avec le reste.
 * @author Guillaume Huard
 */
public class Triangle extends Forme {
	double angle;
	double coteLong;

	/**
	 * Pas de paramètres particuliers, tout se règle via les méthodes de la forme.
	 * @param m MachineTrace à utiliser pour le dessin.
	 */
	Triangle(MachineTrace m) {
		super(m);
		angle = 180 * Math.atan2(1, 0.5) / Math.PI;
		coteLong = Math.sqrt(5) / 2.0;
	}

	/**
	 * Spécialisation du dessin pour représenter un triangle.
	 */
	@Override
	void dessiner() {
		reset();
		m.avancer(taille / 2); // dessiner base triangle isocele
		m.baisser();
		m.tournerGauche(90 + angle);
		m.avancer(coteLong * taille);
		m.tournerGauche(180 - angle);
		m.avancer(taille);
		m.tournerGauche(180 - angle);
		m.avancer(coteLong * taille);
		m.tournerDroite(90 - angle);
	}
}
