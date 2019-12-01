import java.util.Set;
import java.util.HashSet;
 
public class Animal {
  private long An_id;
  private String NomA;
  private String Sexe;
  private String Type;
  private String FonctionCage;
  private String Pays;
  private int AnNais;
  private Cage LaCage;
  private Set Maladies = new HashSet();

  public Animal(String noma, String type, String fct_cage,String pays,int AnNais,Cage LaCage, Set mal) {
	  setNomA(noma);
	  setType(type);
	  setFonctionCage(fct_cage);
	  setPays(pays);
	  setAnNais(AnNais);
	  setLaCage(LaCage);
	  setMaladies(mal);
  }
  public long getAn_id() {
    return An_id;
  }

  public void setAn_id(long l) {
    An_id = l;
  }

  public String getNomA() {
    return NomA;
  }  

  public void setNomA(String n) {
    NomA = n;
  }

  
  public String getSexe() {
    return Sexe;
  }

  public void setSexe(String s) {
    Sexe = s;
  }
 
  public String getType() {
    return Type;
  }  

  public void setType(String t) {
    Type = t;
  }
 
  public String getFonctionCage() {
    return FonctionCage;
  }  

  public void setFonctionCage(String f) {
    FonctionCage = f;
  }

  public String getPays() {
    return Pays;
  }  

  public void setPays(String p) {
    Pays = p;
  }

  public int  getAnNais() {
    return AnNais;
  }
 
  public void setAnNais(int a) {
    AnNais = a;
  }

    public Cage getLaCage() {
    return LaCage;
  }  

  public void setLaCage(Cage C) {
    LaCage = C;
  }

  public Set getMaladies() {
	     return Maladies;
	  }

    public void setMaladies(Set M) {
     Maladies  = M;
  }

  public void addMaladie(String m) {
     Maladies.add(m);
  }

}
