package formes_geo;

public class Triangle extends Forme {

	
	public Triangle(MachineTrace m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	void dessiner() {
		// TODO Auto-generated method stub
		//on se place dans un coin du carré dans lequel notre forme est contenue
		m.placer(x-t/2, y-t/2);
		m.orienter(60);
		m.baisser();
		for(int i=0;i<3;i++) {
			m.avancer(Math.sqrt(5)*t/2);
			m.tournerDroite(120);
		}
		m.lever();
	}
    void avancer(){ //par default une forme quand elle avance ne fait rien à part dessiner, on override si besoin dasn les classes filles
        this.dessiner();
    }
}
