import java.util.Set;
import java.util.HashSet;

public class Gardien extends Employe {

	
   private Set Cages = new HashSet();
   
   public Gardien(String NomE, String adresse) {
	   super(NomE,adresse);
   }
 
   public Set getCages() {
    return Cages;
  }
  
  public void setCages(Set S) {
    Cages = S;
  }
  
  public void addCage(Cage c) {
    Cages.add(c);
    if (! c.getGardiens().contains(this)) { c.addGardien(this);}
  }
}
