package formes_geo;

public abstract class Forme {
	MachineTrace m;
	float t;
	float x;
	float y;
	public Forme(MachineTrace m) {
		this.m = m;
	}
	
	void fixerPosition(int x, int y) {
		this.x=(float)x;
		this.y=(float)y;
	}
	void fixerTaille(int t) {
		this.t=(float)t;
	}
	
	abstract void dessiner();
	
}
