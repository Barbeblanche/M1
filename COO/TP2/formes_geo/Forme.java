package formes_geo;

public abstract class Forme {
	MachineTrace m;
	float t;
	float x;
	float y;
    int etape;
    
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
    void fixerEtape(int e) {
		this.etape=e;
	}
	
	abstract void dessiner();
    
    abstract void avancer();
	
}
