package formes_geo;


import java.util.Random;

public class FormePulsante extends Forme{
    final static int nbFormes = 8;//pour le main -> on a 8 formes avec les nouvelles formes pulsante donc =8
    final static int nbObjets = 5;//pour le main
    final static int etapesPulsations = 20;
    final static int amplitudePulsation = 20;
    final static int delai = 100;
    public FormePulsante(MachineTrace m) {
		super(m);
		// TODO Auto-generated constructor stub
	}
    
    static Forme creerForme(int type, MachineTrace m) {
        switch (type) {
            case 0 :
                return new Carre(m);
            case 1 :
                return new Triangle(m);
            case 2:
                return new Cercle(m);
            case 3:
                return new Losange(m);
            case 4 :
                return new CarrePulsant(m);
            case 5 :
                return new TrianglePulsant(m);
            case 6:
                return new CerclePulsant(m);
            case 7:
                return new LosangePulsant(m);
            default :
                throw new RuntimeException("Forme Inconnue");
        }
    }
    
    void avancer(){
        int ajout = (int) (amplitudePulsation*(Math.sin(etape*2*Math.PI/etapesPulsations)+1)/2);
        this.fixerTaille(t+ajout);
        this.dessiner();
    }
    
    public static void main(String [] args) {
        MachineTrace m;
        Forme [] f;
        Random r;
        
        m = new MachineTrace(400, 400);
        m.masquerPointeur();
        m.rafraichissementAutomatique(false);
        
        f = new Forme[nbObjets];
        r = new Random();
        for (int i=0; i<f.length; i++) {
            f[i] = creerForme(r.nextInt(nbFormes), m);
            f[i].fixerPosition(r.nextInt(200)-100, r.nextInt(200)-100);
            f[i].fixerTaille(r.nextInt(20)+5);
        }
        
        while (true) {
            for (int j=0; j<=etapesPulsations; j++) {
                m.effacerTout();
                for (int i=0; i<f.length; i++) {
                    f[i].fixerEtape(j);
                    f[i].avancer();
                }
                m.rafraichir();
                m.attendre(delai);                
            }
        }
    }
}
