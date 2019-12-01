import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.*;


public class QueryZoo {
  
public static void main(String[] args) {
Session session = null;
Configuration cf;
List results;
Iterator iter;

try{
	// This step will read hibernate.cfg.xmland prepare hibernate for use
	System.out.println("Configuration \n");
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	System.out.println("sessionFactory: OK \n");

	session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

    results = session.createQuery("from Animal A").list();
	iter = results.iterator();
    while (iter.hasNext()) {
    	Animal a = (Animal) iter.next();
    	System.out.println("\t"+a.getLaCage().getNoCage());
    }
        
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
