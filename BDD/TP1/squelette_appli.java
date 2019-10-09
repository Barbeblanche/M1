import java.sql.*;
import java.util.Scanner;

public class squelette_appli {

	static final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";

	static final String USER = "salamanl";
	static final String PASSWD = "XXXX";

	static Connection conn;


    public static void main(String args[]) {

        try {

  	    // Enregistrement du driver Oracle
  	    System.out.print("Loading Oracle driver... ");
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  	    System.out.println("loaded");

  	    // Etablissement de la connection
  	    System.out.print("Connecting to the database... ");
 	    conn = DriverManager.getConnection(CONN_URL,USER,PASSWD);
   	    System.out.println("connected");

  	    // Desactivation de l'autocommit
	  	conn.setAutoCommit(false);
  	    System.out.println("Autocommit disabled");


  	    // Liberation des ressources et fermeture de la connexion...


		// REQUETE 1
		/*
		Statement requete = conn.createStatement();
		ResultSet resultat =  requete.executeQuery("select * from lescages");

		while(resultat.next()) {
			System.out.println("Numéro de cage = " + resultat.getString("nocage") +
								", Fonction = " + resultat.getString("fonction")+
								", Numéro Allée = " + resultat.getString("noallee"));
		}

		resultat.close();

		Scanner sc = new Scanner(System.in);
		System.out.println("numéro de cage à modifier : ");
		String nocage = sc.nextLine();
		System.out.println("nouvelle fonction de la cage : ");
		String nvfct = sc.nextLine();
		ResultSet update_lescages =  requete.executeQuery("update lescages set fonction = '" + nvfct.toLowerCase() + "' where nocage = " + nocage);
		ResultSet affichage_cage =  requete.executeQuery("select * from lescages");

		while(affichage_cage.next()) {
			System.out.println("Numéro de cage = " + affichage_cage.getString("nocage") +
								", Fonction = " + affichage_cage.getString("fonction")+
								", Numéro Allée = " + affichage_cage.getString("noallee"));
		}

		update_lescages.close();
		affichage_cage.close();
		requete.close();
		*/
		// FIN REQUETE 1

		//REQUETE 2
		Statement requete2 = conn.createStatement();
		ResultSet affichage_gardiens =  requete2.executeQuery("select * from lesgardiens");
		while(affichage_gardiens.next()) {
			System.out.println("Numéro de cage = " + affichage_gardiens.getString("nocage") +
								", Nom = " + affichage_gardiens.getString("nome"));
		}
		affichage_gardiens.close();
		Scanner sc = new Scanner(System.in);
		System.out.println("nom du gardien a réaffecter : ");
		String nome = sc.nextLine();
		ResultSet affichage_gardien_cage =  requete2.executeQuery("select * from lesgardiens inner join lescages on lesgardiens.nocage = lescages.nocage where nome = '" + nome + "'");
		while(affichage_gardien_cage.next()) {
			System.out.println("Numéro de cage = " + affichage_gardien_cage.getString("nocage") +
								", Fonction = " + affichage_gardien_cage.getString("fonction") +
								", Numero allee = " + affichage_gardien_cage.getString("noallee") );
		}
		affichage_gardien_cage.close();
		System.out.println("numéro de cage où il sera retiré : ");
		String nocage_r = sc.nextLine();
		ResultSet test_cage_non_vide = requete2.executeQuery("select * from lescages where nocage=" + nocage_r + " and nocage not in (select nocage from lesanimaux) ");
		ResultSet test_cage_non_gardee = requete2.executeQuery("select * from lescages inner join lesgardiens on lescages.nocage=lesgardiens.nocage  where lescages.nocage="+ nocage_r + " and nome not in (select nome from lesgardiens where nome='" + nome +"')");
		if (!test_cage_non_vide.next()){
			if (!test_cage_non_gardee.next()){
				System.out.println("La cage contient des animaux et ne sera plus surveuillé, veuillez choisir une autre cage : ");
				nocage_r = sc.nextLine();
			}
		}
		ResultSet delete_cage_gardien =  requete2.executeQuery("delete from lesgardiens where nome = '" + nome + "' and nocage = '" + nocage_r + "'");
		delete_cage_gardien.close();
		ResultSet affichage_nv_cage = requete2.executeQuery("select * from LesSpecialites inner join LesCages on LesSpecialites.fonction_cage=LesCages.fonction where LesSpecialites.nome = '" + nome + "'");
		while(affichage_nv_cage.next()) {
			System.out.println("Numéro de cage = " + affichage_nv_cage.getString("nocage") +
								", Fonction = " + affichage_nv_cage.getString("fonction") +
								", Numero allee = " + affichage_nv_cage.getString("noallee") );
		}
		System.out.println("numéro de cage à affecter : ");
		affichage_nv_cage.close();
		String nocage_nv = sc.nextLine();
		ResultSet test_cage_vide_gardee = requete2.executeQuery("select * from lesgardiens inner join lescages on lesgardiens.nocage = lescages.nocage where lescages.nocage=" + nocage_nv + " and lescages.nocage not in (select nocage from lesanimaux)");

		if (test_cage_vide_gardee.next()){
			System.out.println("La cage est vide, et un gardien est déjà affecté, veuillez choisir un autre cage : ");
			nocage_nv = sc.nextLine();
		}
		ResultSet insert_gardien =  requete2.executeQuery("insert into LesGardiens values(" + nocage_nv + ", '" + nome + "')");
		insert_gardien.close();
		requete2.close();
 		conn.close();

  	    System.out.println("bye.");

  	    // traitement d'exception
          } catch (SQLException e) {
              System.err.println("failed");
              System.out.println("Affichage de la pile d'erreur");
  	          e.printStackTrace(System.err);
              System.out.println("Affichage du message d'erreur");
              System.out.println(e.getMessage());
              System.out.println("Affichage du code d'erreur");
  	          System.out.println(e.getErrorCode());

          }
     }


}
