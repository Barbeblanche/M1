import java.util.Set;
import java.util.HashSet;

public class Responsable extends Employe {
  private long NoAlle;
  
  public Responsable(String nome, String adresse, long NoAlle) {
	  super(nome,adresse);
	  setNoAlle(NoAlle);
  }

  public long getNoAlle() {
    return NoAlle;
  }
  
  public void setNoAlle(long n) {
    NoAlle = n;
  }
 
}
