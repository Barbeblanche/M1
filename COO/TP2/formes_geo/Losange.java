package formes_geo;

public class Losange extends Forme{

	public Losange(MachineTrace m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	void dessiner() {
		// TODO Auto-generated method stub
		//on se place dans un coin du carré dans lequel notre forme est contenue
		m.placer(x-t/2, y);
		m.orienter(45);
		m.baisser();
		for(int i=0;i<4;i++) {
			m.avancer(t);
			m.tournerDroite(90);
		}
		m.lever();
	}

}
