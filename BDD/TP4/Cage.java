import java.util.Set;
import java.util.HashSet;
 
public class Cage {
  private long Cg_id;
  private int NoCage;
  private String Fonction;
  private long NoAlle;
  private Set Gardiens = new HashSet();
  private Set Animals= new HashSet();
  
  public Cage(int NoCage, String fct, long NoAlle) {
	  setNoCage(NoCage);
	  setFonction(fct);
	  setNoAlle(NoAlle);
  }

  public long getCg_id() {
    return Cg_id;
  }

  public void setCg_id(long l) {
    Cg_id = l;
  }

  public int getNoCage() {
    return NoCage;
  }

  public void setNoCage(int c) {
    NoCage = c;
  }
  
  public String getFonction() {
    return Fonction;
  }  

  public void setFonction(String f) {
    Fonction = f;
  }

  public long getNoAlle() {
    return NoAlle;
  }
 
  public void setNoAlle(long n) {
    NoAlle = n;
  }

  public Set getGardiens() {
    return Gardiens;
  }

  public Set getAnimals() {
    return Animals;
  }

  public void setGardiens(Set S) {
     Gardiens  = S;
  }

  public void setAnimals(Set S) {
     Animals  = S;
  }

  public void addGardien(Gardien g) {
     Gardiens.add(g);
     if (! g.getCages().contains(this)) { g.addCage(this); }
  }

  public void addAnimal(Animal a) {
     Animals.add(a);
     a.setLaCage(this); 
  }

}
