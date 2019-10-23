package formes_geo;

public class TrianglePulsant extends FormePulsante {

	
	public TrianglePulsant(MachineTrace m) {
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

}