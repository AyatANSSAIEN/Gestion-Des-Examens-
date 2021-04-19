
/*
cette class contient tout les methodes qu'ont une relation avec la base des donnees
effectuer tout les differents operation ajouter, modifier, recuperer et supprimer
*/
import java.sql.*;

public class ConnectionDb {
   // etablir la connection avec la base des donnees
   public Connection ConnectToDb(String dbName, String userName, String pass) throws Exception {
      Connection conn = null;
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, userName, pass);
      return conn;
   }

   // retourne true si les informations passees en parametres sont egaux avec les
   // donnees enregistres
   public boolean isAdmin(String username, String pass) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery(
            "SELECT * FROM users WHERE login=\"" + username + "\" AND password= \"" + pass + "\" AND isAdmin = 1 ");
      if (rs.next())
         return true;
      else
         return false;
   }

   // retourne true si les informations passees en parametres sont egaux avec les
   // donnees enregistres
   public boolean isProfessor(String CIN, String pass) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt
            .executeQuery("SELECT * FROM professeurs WHERE CIN=\"" + CIN + "\" AND password= \"" + pass + "\" ");
      if (rs.next())
         return true;
      else
         return false;
   }

   // retourne true si les informations passees en parametres sont egaux avec les
   // donnees enregistres
   public boolean isStudent(String CodeApoge, String pass) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery(
            "SELECT * FROM etudiants WHERE CodeApoge=\"" + CodeApoge + "\" AND password= \"" + pass + "\"");
      if (rs.next())
         return true;
      else
         return false;
   }

   // Student section getStudData SELECT * FROM `etudiants` WHERE 1
   public ResultSet getStudData(String CodeApoge) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT * FROM `etudiants` WHERE CodeApoge = \"" + CodeApoge + "\"");
      if (rs.next())
         return rs;
      return null;
   }

   // professeurs section
   // ajouter proffesseur a la base des donnees
   public void addProf(String Cni, String FirstName, String LastName, String Email) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("INSERT INTO `professeurs`(`CIN`, `nom`, `prenom`, `Email`, `password`) VALUES ( \"" + Cni + "\",\""
            + FirstName + "\",\"" + LastName + "\",\"" + Email + "\",\"" + Cni + "\")");
   }

   // editer proffesseur dans la base des donnees
   public void editProf(String OldCni, String Cni, String LastName, String FirstName, String Email) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("UPDATE professeurs SET CIN=\"" + Cni + "\" , prenom=\"" + FirstName + "\" , nom=\"" + LastName
            + "\", email = \" " + Email + "\" WHERE CIN=\"" + OldCni + "\"");
   }

   // retourne les informations de tout les professeurs
   public ResultSet showProf() throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT * FROM professeurs ORDER BY CIN ");
      return rs;
   }

   // retourne les informations de tout les professeurs
   public ResultSet getProfData(String CIN) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT * FROM professeurs WHERE CIN  = \"" + CIN + "\"");
      if (rs.next())
         return rs;
      return null;
   }

   // supprimer un professeur a l'aide de son cin (cle primaire)
   public void deleteProf(String Cni) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("DELETE  FROM professeurs WHERE CIN= \"" + Cni + "\"");
   }

   // salles sections
   // ajouter une salle a la base des donnees
   public void addSalle(String num, String capacite, String type) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("INSERT INTO `salles`(`num_salle`, `capacitee`, `Type`) VALUES (\"" + num + "\",\"" + capacite
            + "\",\"" + type + "\")");

   }

   // editer une salle dans la base des donnees
   public void editSalle(String id, String num, String capacite, String type) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("UPDATE salles SET num_salle=\"" + num + "\", capacitee=\"" + capacite + "\" , type=\"" + type
            + "\" WHERE id_salle=" + id);
   }

   // retourne les informations de tout les salles
   public ResultSet showSalle() throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT * FROM salles ORDER BY id_salle");
      return rs;
   }

   // supprimer une salle par son id
   public void deleteSalle(String id) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("DELETE  FROM salles WHERE id_salle=" + id);
   }

   // modules sections
   // ajouter des modules a la base des donnee
   public void addModule(String code_module, String nom_module, String id_f) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("INSERT INTO `modules`( `nom_module`, `code_module`, `filiere`) VALUES (\"" + nom_module
            + "\",\"" + code_module + "\"," + id_f + ")");

   }

   // editer un module dans la base des donnees
   public void editModule(String id, String nom_module, String code_module) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("UPDATE modules SET nom_module=\"" + nom_module + "\", code_module= \"" + code_module
            + "\" WHERE num_module=" + id);
   }

   // retourne les information de tout les modules
   public ResultSet showModule() throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT * FROM modules ORDER BY num_module");
      return rs;
   }

   // retourne l'id du filiere d'apres l'id du module
   public String filiereModule(String idModule) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT `filiere` FROM modules WHERE num_module = " + idModule);
      if (rs.next())
         return rs.getString(1);
      return "";
   }

   // supprimer un module par son id
   public void deleteModule(String id) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("DELETE  FROM modules WHERE num_module=" + id);
   }

   // retourne le nom de module en donnant le numero du module
   public String getModuleName(String num_module) throws Exception {

      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT nom_module FROM modules WHERE num_module=" + num_module);
      if (rs.next())
         return rs.getString(1);
      return "";
   }

   // retourne tableau des modules dont les elements sont sous forme NOM MODULE
   // (FILIERE) qui ont disponible
   // c'est a dire les modules qui n'ont pas affecte dans la date entre ou a
   // l'intersection des heures debut et fin
   public String[] getTabModulesJC(String date, String debut, String fin) throws Exception {

      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = stt.executeQuery(
            "SELECT nom_module,filiere FROM modules WHERE filiere NOT IN( SELECT filiere FROM modules INNER JOIN exams ON exams.num_module = modules.num_module WHERE ( exams.date_exam = \""
                  + date + "\" AND( ( exams.debut_exam BETWEEN \"" + debut + "\" AND \"" + fin
                  + "\" ) OR(exams.fin_exam) BETWEEN \"" + debut + "\" AND \"" + fin
                  + "\" ) ) ) ORDER BY `modules`.`filiere` ASC");
      int N = 0;
      if (rs.last())
         N = rs.getRow();
      String[] tab_modules = new String[N];

      rs.beforeFirst();
      int i = 0;
      while (rs.next()) {
         tab_modules[i] = rs.getString(1) + "(" + rs.getString(2) + ")";
         i++;
      }
      return tab_modules;
   }

   // retourne tableau des modules dont les elements sont sous forme NOM MODULE
   // (FILIERE)
   public String[] getTabModulesJC() throws Exception {

      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = stt.executeQuery("SELECT nom_module,filiere FROM modules");
      int N = 0;
      if (rs.last())
         N = rs.getRow();
      String[] tab_modules = new String[N];

      rs.beforeFirst();
      int i = 0;
      while (rs.next()) {
         tab_modules[i] = rs.getString(1) + "(" + rs.getString(2) + ")";
         i++;
      }
      return tab_modules;
   }

   // retourne le numero du module (id cle primaire) en donnant le nom du module
   public String getNumModule(String nomModule) throws Exception {

      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT num_module From modules WHERE nom_module = \"" + nomModule + "\"");
      if (rs.next())
         return rs.getString(1);
      return "";
   }

   // exam sections
   // ajouter les examens a la base des donnees
   public void addExam(String module, String dateExam, String debut, String fin, String nbSalle, String nbProfSalle)
         throws Exception {

      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate(
            "INSERT INTO `exams`(`num_module`, `date_exam`, `debut_exam`, `fin_exam`, `nbr_salles`, `nbr_profs_salle`) VALUES (\""
                  + getNumModule(module) + "\",\"" + dateExam + "\",\"" + debut + "\",\"" + fin + "\"," + nbSalle + ","
                  + nbProfSalle + ")");

   }

   // editer un examen dans la base des donnees
   public void editExam(String id, String module, String dateExam, String debut, String fin, String nbSalle,
         String nbProfSalle) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("UPDATE exams SET num_module=" + getNumModule(module) + ", date_exam= \"" + dateExam
            + "\",debut_exam= \"" + debut + "\",fin_exam= \"" + fin + "\", nbr_salles= " + nbSalle + ",nbr_profs_salle="
            + nbProfSalle + " WHERE num_exam=" + id);

   }

   // retourne les informations de tout les examens
   public ResultSet showExam() throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT * FROM exams ORDER BY num_exam");
      return rs;
   }

   // supprimer un examen par son id
   public void deleteExam(String id) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("DELETE FROM exams WHERE num_exam=" + id);
   }

   // retourne le nombres des salles pour un examen donne par son id
   public String getNbrSalleOfExam(String id_e) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT nbr_salles FROM exams WHERE num_exam=" + id_e);
      if (rs.next())
         return rs.getString(1);
      return "";
   }

   // retourne le nombre des salles et le nombre des proffesseurs par salle
   public ResultSet getNsalleNprof(String idExam) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT nbr_salles , nbr_profs_salle FROM exams WHERE num_exam =" + idExam);
      if (rs.next())
         return rs;
      return null;
   }

   // retourne un tableau des indices de 1..N avec (N est le nombre min des (
   // sallesdispo , prof dispo ) )
   public String[] indexSalleDispoList(String date, String debut, String fin) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery(
            "SELECT COUNT(CIN) FROM professeurs WHERE CIN NOT IN( SELECT DISTINCT (id_p) FROM affectations INNER JOIN professeurs ON professeurs.CIN = affectations.id_p INNER JOIN exams ON affectations.id_e = exams.num_exam WHERE exams.date_exam =  \""
                  + date + "\" AND  ( (exams.debut_exam BETWEEN \"" + debut + "\" AND \"" + fin
                  + "\") OR (exams.fin_exam BETWEEN \"" + debut + "\" AND \"" + fin + "\" )))");

      Statement stt2 = cnt.createStatement();
      ResultSet rs2 = stt2.executeQuery(
            "SELECT COUNT(id_salle) FROM salles WHERE id_salle NOT IN( SELECT DISTINCT (id_s) FROM affectations INNER JOIN salles ON salles.id_salle = affectations.id_s INNER JOIN exams ON affectations.id_e = exams.num_exam WHERE exams.date_exam =  \""
                  + date + "\" AND  ( (exams.debut_exam BETWEEN \"" + debut + "\" AND \"" + fin
                  + "\") OR (exams.fin_exam BETWEEN \"" + debut + "\" AND \"" + fin + "\" )))");
      int Np = 0;
      int Ns = 0;
      int N = 0;
      String[] tab_nbrSalle_dispo = {};

      if (rs.next()) {
         Np = Integer.parseInt(rs.getString(1));
      }

      if (rs2.next()) {
         Ns = Integer.parseInt(rs2.getString(1));
      }
      N = Math.min(Ns, Np);
      tab_nbrSalle_dispo = new String[N];

      for (int i = 0; i < N; i++)
         tab_nbrSalle_dispo[i] = String.valueOf(i + 1);

      return tab_nbrSalle_dispo;
   }

   // retourne un tableau des indices de 1..N avec (N est le nombre des profs
   // dispo)
   public String[] indexProfDispoList(String date, String debut, String fin, int nbrSalle) throws Exception {

      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery(
            "SELECT COUNT(CIN) FROM professeurs WHERE CIN NOT IN( SELECT DISTINCT (id_p) FROM affectations INNER JOIN professeurs ON professeurs.CIN = affectations.id_p INNER JOIN exams ON affectations.id_e = exams.num_exam WHERE exams.date_exam =  \""
                  + date + "\" AND  ( (exams.debut_exam BETWEEN \"" + debut + "\" AND \"" + fin
                  + "\") OR (exams.fin_exam BETWEEN \"" + debut + "\" AND \"" + fin + "\" )))");

      int N = 0;
      String[] nbrProfDispoTable = {};
      if (rs.next()) {
         N = Integer.parseInt(rs.getString(1));
      }
      nbrProfDispoTable = new String[(int) N / nbrSalle];
      for (int i = 0; i < N / nbrSalle; i++)
         nbrProfDispoTable[i] = String.valueOf(i + 1);

      return nbrProfDispoTable;
   }

   // retourne un tableau contient les salles dispo sous forme "NUMERO (ID)"
   public String[] salleDisponible(String date, String debut, String fin) throws Exception {

      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = stt.executeQuery(
            "SELECT num_salle,id_salle FROM `salles` WHERE salles.num_salle NOT IN( SELECT salles.num_salle FROM affectations  INNER JOIN salles ON affectations.id_s = salles.id_salle INNER JOIN exams ON affectations.id_e = exams.num_exam WHERE ( exams.date_exam = \""
                  + date + "\" AND ( exams.debut_exam BETWEEN \"" + debut + "\" AND \"" + fin
                  + "\" OR exams.fin_exam BETWEEN \"" + debut + "\" AND \"" + fin + "\") ) )");
      int size = 1;
      if (rs.last())
         size = rs.getRow();
      String[] table = new String[size];

      rs.beforeFirst();
      int i = 0;
      while (rs.next()) {

         table[i] = rs.getString(1) + " (" + rs.getString(2) + ")";
         i++;
      }

      return table;
   }

   // retourne un tableau contient les professeurs dispo sous forme "NOM PRENOM
   // (CIN :cle primaire)"
   public String[] profDisponible(String date, String debut, String fin) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = stt.executeQuery(
            "SELECT CIN, nom, prenom FROM professeurs WHERE professeurs.CIN NOT IN( SELECT professeurs.CIN FROM affectations INNER JOIN professeurs ON affectations.id_p = professeurs.CIN INNER JOIN exams ON affectations.id_e = exams.num_exam WHERE ( exams.date_exam = \""
                  + date + "\" AND ( exams.debut_exam BETWEEN \"" + debut + "\" AND \"" + fin
                  + "\" OR exams.fin_exam BETWEEN \"" + debut + "\" AND \"" + fin + "\") ) )");

      int size = 1;
      if (rs.last())
         size = rs.getRow();
      String[] table = new String[size];

      rs.beforeFirst();
      int i = 0;
      while (rs.next()) {
         table[i] = rs.getString(2) + " " + rs.getString(3) + " (" + rs.getString(1) + ")";
         i++;
      }
      return table;
   }

   // retourne id_examen (cle primaire dans le tableau examen) qui ont affecte dans
   // le tableau affectations
   public ResultSet GetIdExamAffecte(String id_f) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery(
            "SELECT DISTINCT id_e FROM affectations INNER JOIN exams ON affectations.id_e = exams.num_exam INNER JOIN modules ON exams.num_module = modules.num_module WHERE modules.filiere ="
                  + id_f + " ORDER BY exams.date_exam");
      return rs;
   }

   // retourne id_examen (cle primaire dans le tableau examen) qui ont affecte dans
   // le tableau affectations
   public ResultSet GetIdExamProf(String CIN) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery(
            "SELECT id_e FROM affectations INNER JOIN exams ON exams.num_exam = affectations.id_e WHERE id_p = '" + CIN
                  + "'ORDER BY exams.date_exam ");
      return rs;
   }


   // affectation sections
   // ajouter une affectation
   public void addAffect(String idExam, String idSalle, String idProf) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("INSERT INTO `affectations`(`id_e`, `id_s`, `id_p`) VALUES (" + idExam + "," + idSalle + ",\""
            + idProf + "\")");
   }

   // retourne l'affichage d'une seule affectaion
   public String showAffectation(String id_e) throws Exception {
      String str = "";
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      // chercher module,date,debut,fin de l'examen
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery(
            "SELECT DISTINCT modules.code_module, exams.date_exam, exams.debut_exam, exams.fin_exam FROM exams INNER JOIN modules ON exams.num_module = modules.num_module WHERE num_exam = "
                  + id_e);
      while (rs.next()) {
         // -> "date debut-fin code_module "
         str = rs.getString(2) + "\t" + rs.getString(3).substring(0, 5) + "-" + rs.getString(4).substring(0, 5) + "\t"
               + rs.getString(1) + " \t";
         // Chercher Les Salles de l'examen
         Statement stt1 = cnt.createStatement();
         ResultSet rs1 = stt1.executeQuery(
               "SELECT DISTINCT  id_s, salles.num_salle, salles.capacitee FROM affectations INNER JOIN salles ON affectations.id_s=salles.id_salle WHERE id_e = "
                     + id_e);
         while (rs1.next()) {
            // Pour chaque salle -> "Numero de la salle" 
            str += rs1.getString(2) +"("+rs1.getString(3)+ ")\t";
             
            // chercher les profs
            Statement stt2 = cnt.createStatement();
            ResultSet rs2 = stt2.executeQuery(
                  "SELECT professeurs.cIN , professeurs.nom , professeurs.prenom  FROM affectations INNER JOIN professeurs ON affectations.id_p=professeurs.CIN WHERE id_s = "
                        + rs1.getString(1) + " AND id_e =" + id_e);
            while (rs2.next()) {
               // Pour chaque prof -> "Nom prenom (CIN)"
               str += rs2.getString(2) + " " + rs2.getString(3) + "\t";
               str += "\n\t\t\t\t";
            }
            str += "\n\t\t\t";
         }
      }
      return str;
   }

   public String showAffectationProf(String id_e, String CIN) throws Exception {
      String str = "";
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      // chercher module,date,debut,fin de l'examen
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery(
            "SELECT  modules.code_module, exams.date_exam, exams.debut_exam, exams.fin_exam FROM affectations INNER JOIN exams ON exams.num_exam = affectations.id_e INNER JOIN modules ON exams.num_module = modules.num_module WHERE id_e = "
                  + id_e + " AND id_p = '" + CIN + "'");
      while (rs.next()) {
         // -> "date debut fin code_module "
         str = "\t"+rs.getString(2) + "\t" + rs.getString(3).substring(0, 5) + "\t" + rs.getString(4).substring(0, 5) + "\t"
               + rs.getString(1) + " \t";
         // Chercher Les Salles de l'examen
         Statement stt1 = cnt.createStatement();
         ResultSet rs1 = stt1.executeQuery(
               "SELECT DISTINCT  id_s, salles.num_salle FROM affectations INNER JOIN salles ON affectations.id_s=salles.id_salle WHERE id_e = "
                     + id_e + " AND id_p = '" + CIN + "' ");
         while (rs1.next()) {
            // Pour chaque salle -> "Numero de la salle"
            str += rs1.getString(2) + "\t";
            str += "\n\t\t\t";
         }
      }
      return str;
   }

   // supprimer une affectation et examen
   public void deleteAffectation(String id_e) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("DELETE  FROM affectations WHERE id_e= " + id_e);
      deleteExam(id_e);
   }

   // supprimer seulement l'affectation
   public void deleteToEditAffect(String id) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      stt.executeUpdate("DELETE FROM affectations WHERE id_e=" + id);
   }

   // filiere
   // retourne id et le nom de filiere
   public String[] FiliereList() throws Exception {
      int N = NbrFiliere();
      String[] filiereliste = new String[N];
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT `id_filiere`, `nom_filiere` FROM `filieres` ");
      int i = 0;
      while (rs.next()) {
         filiereliste[i] = rs.getString(2);
         i++;
      }
      return filiereliste;
   }

   // retourne le nombre des filiere
   public int NbrFiliere() throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT COUNT(*) FROM  `filieres` ");
      if (rs.next())
         return Integer.parseInt(rs.getString(1));
      return 0;
   }

   // retourne id filiere d'apres le nom de filiere
   public String idFiliere(String nomFil) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT id_filiere FROM  `filieres` WHERE nom_filiere = \"" + nomFil + "\"");
      if (rs.next())
         return rs.getString(1);
      return "";
   }

   // retourne le nom de filiere d'apres id de filiere
   public String NomFiliere(String id_f) throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement();
      ResultSet rs = stt.executeQuery("SELECT nom_filiere FROM  `filieres` WHERE id_filiere = " + id_f);
      if (rs.next())
         return rs.getString(1);
      return "";
   }

   // retourne les id filiere qui ont affectees dans le tableau affectations
   public ResultSet idFiliereAffecteList() throws Exception {
      Connection cnt = this.ConnectToDb("gestionexamens", "root", "");
      Statement stt = cnt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = stt.executeQuery(
            "SELECT DISTINCT id_filiere FROM affectations INNER JOIN exams ON affectations.id_e=exams.num_exam INNER JOIN modules ON exams.num_module = modules.num_module INNER JOIN filieres ON modules.filiere = filieres.id_filiere");

      return rs;
   }

   /*
    * public boolean examSameFiliere(String date, String debut, String fin, String
    * id_f) throws Exception { Connection cnt = this.ConnectToDb("gestionexamens",
    * "root", ""); Statement stt = cnt.createStatement(); ResultSet rs =
    * stt.executeQuery(
    * "SELECT COUNT(id_filiere) FROM affectations INNER JOIN exams ON affectations.id_e = exams.num_exam INNER JOIN modules ON exams.num_module = modules.num_module INNER JOIN filieres ON modules.filiere = filieres.id_filiere WHERE filieres.id_filiere ="
    * + id_f + " AND exams.date_exam = \"" + date +
    * "\" AND ( (exams.debut_exam BETWEEN \"" + debut + "\" AND \"" + fin +
    * "\") OR(exams.fin_exam BETWEEN \"" + debut + "\" AND \"" + fin + "\") )"); if
    * (rs.next()) { if (rs.getString(1).equals("0")) return true; } return false; }
    */

   // cette methode retourne true si les elements de tableau sont uniques
   public boolean tabRep(String[] tab) {
      for (int i = 0; i < tab.length; i++) {
         for (int j = i + 1; j < tab.length; j++) {
            if (tab[i].equals(tab[j]))
               return false;
         }
      }
      return true;
   }

}