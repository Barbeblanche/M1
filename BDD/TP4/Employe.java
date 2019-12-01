
public class Employe {
  private long Ep_id;
  private String NomE ;
  private String Adresse ;
  
  public Employe(String NomE, String adresse) {
	  setNomE(NomE);
	  setAdresse(adresse);
  }
  
  public long getEp_id() {
    return Ep_id;
  }

  public void setEp_id(long l) {
    Ep_id = l;
  }
  
  public String getNomE() {
    return NomE;
  }
  
  public void setNomE(String n) {
    NomE = n;
  }
 
  public String getAdresse() {
    return Adresse;
  }
  
  public void setAdresse(String a) {
    Adresse = a;
  }
  
}
