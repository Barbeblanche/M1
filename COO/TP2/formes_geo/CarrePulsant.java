package formes_geo;

public class CarrePulsant extends FormePulsante {

	public CarrePulsant(MachineTrace m) {
		super(m);
		// TODO Auto-generated constructor stub
	}


	@Override
	void dessiner() {
		// TODO Auto-generated method stub
		//on se place dans un coin du carré dans lequel notre forme est contenue
		m.placer(x-t/2, y-t/2);
		m.orienter(90);
		m.baisser();
		for(int i=0;i<4;i++) {
			m.avancer(t);
			m.tournerDroite(90);
		}
		m.lever();
	}
    
}