import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.*;


public class PopulateZoo {
  
public static void main(String[] args) {
Session session = null;
Configuration cf;
List results;
Iterator iter;

try{
	// This step will read hibernate.cfg.xmland prepare hibernate for use
	
	Cage cage1 = new Cage()
	
	System.out.println("Configuration \n");
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	System.out.println("sessionFactory: OK \n");

	session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

    
        
	/* A COMPLETER */
	
	tx.commit();
        
}catch(Exception e){
      System.out.println("catch !:");e.printStackTrace();
}finally{
      try {session.flush();} catch(Exception e){System.out.println("catch !:");e.printStackTrace();};
      session.close();
}
    
}

}
