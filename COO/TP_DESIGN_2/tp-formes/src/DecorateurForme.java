
public abstract class DecorateurForme extends Forme{
	Forme  forme;
	DecorateurForme(Forme f) {
		forme = f;
		}
	void  dessine () {
		forme.dessine ();
		}
	void  ajoute(Forme f) {
		forme.ajoute(f);
		}
	void  supprime(Forme f) {
		forme.supprime(f);
		}
	Forme  obtenirFils(int num) {
		return  forme.obtenirFils(num);
		}
}
