import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.io.*;
import java.util.*;

 
public class CreateZoo {
  public static void main(String[] args) {
    Session session = null;
    Configuration cf ;
    long id=0;
    try{
        // This step will read hibernate.cfg.xmland prepare hibernate for use
        System.out.println("Configuration \n");
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		tx.commit();
	
    }catch(Exception e){
      System.out.println("catch !:");e.printStackTrace();
    }finally{
      session.close();
      }
    
  }
}
