package formes_geo;

public class Cercle extends Forme{

	public Cercle(MachineTrace m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	void dessiner() {
		// TODO Auto-generated method stub
		//on se place dans un coin du carré dans lequel notre forme est contenue
		m.placer(x-t/2, y);
		m.orienter(90);
		m.baisser();
		int ard_sup = (int) ((Math.PI*t)/(90*4)) +1;
		float nb_rotation = (float) ((Math.PI*t/4)/ard_sup);
		float degree = 90/nb_rotation;
		for (int j=0;j<4;j++) {
			for(int i=0;i<nb_rotation;i++) {
				m.avancer(ard_sup);
				m.tournerDroite(degree);
				
			}
		}
		
		m.lever();
	}
    
    void avancer(){ //par default une forme quand elle avance ne fait rien à part dessiner, on override si besoin dasn les classes filles
        this.dessiner();
    }
}
