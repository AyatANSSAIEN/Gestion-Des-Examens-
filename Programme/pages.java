
/**
cette class contient les pages principale pour ajouter et visualiser les donnees enregistres dans la base de donnees
pour tout les choix dans le menu existant 
 */
import java.awt.*;
import javax.swing.*;
import java.sql.*;

import com.github.lgooddatepicker.components.TimePicker;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class pages extends Items {

	public static JDatePickerImpl T_datePicker = newDP();
	public static TimePicker T_TpDebut = newTP();
	public static TimePicker T_TpFin = newTP();

	ConnectionDb cnt = new ConnectionDb();

	// retourne la page de connexion pour se connecter comme un admin
	public Component FirstInter() {

		JPanel FirstPane = new JPanel();
		// division de la partie droite
		FirstPane.setPreferredSize(new Dimension(1000, 600));
		FirstPane.setBackground(blackColor);

		JPanel pdh = PDH(); // panel droite Haut
		JPanel pdc = PDC(GrayColor);// panel droite centre
		pdc.setPreferredSize(new Dimension(800, 350));
		JPanel pdb = PDB();// panel droite bas

		FirstPane.add(pdh);
		FirstPane.add(pdc);
		FirstPane.add(pdb);

		Icon AdminIcon = new ImageIcon(".\\..\\icons\\admin.png");
		Icon ProfIcon = new ImageIcon(".\\..\\icons\\teacher.png");
		Icon StudentIcon = new ImageIcon(".\\..\\icons\\student.png");

		JButton Admin = NewButton("Admin", AdminIcon, "Admin", null, null, BlueColor, 200, 60);
		JButton Prof = NewButton("Professeur", ProfIcon, "Prof", null, null, BlueColor, 200, 60);
		JButton Student = NewButton("Etudiant", StudentIcon, "Student", null, null, BlueColor, 200, 60);

		// ajouter les elements a l'interface
		pdc.add(CenterTitle(""));
		pdc.add(CenterTitle("Welcome, Sign In As "));
		pdc.add(Admin);
		pdc.add(Prof);
		pdc.add(Student);

		return FirstPane;
	}

	public Component AdminHome() {

		JPanel AdminPane = new JPanel();
		// division de la partie droite
		AdminPane.setPreferredSize(new Dimension(1000, 600));
		AdminPane.setBackground(blackColor);

		JPanel pdh = PDH(); // panel droite Haut
		JPanel pdc = PDC(GrayColor);// panel droite centre
		pdc.setPreferredSize(new Dimension(800, 350));
		JPanel pdb = PDB();// panel droite bas

		AdminPane.add(pdh);
		AdminPane.add(pdc);
		AdminPane.add(pdb);

		// declaration des titre et des inputs boxs
		JLabel userName = SmallTitle("User name");
		JLabel passwd = SmallTitle("Password");

		JTextField loginText = TextBox();
		JPasswordField passwordField = PasswordBox();

		JTextField[] text = { loginText, passwordField };
		Icon loginIcon = new ImageIcon(".\\..\\icons\\login.png");
		Icon logoutIcon = new ImageIcon(".\\..\\icons\\logout.png");

		JButton logIn = NewButton("Log in", loginIcon, "login", text, null, BlueColor, 200, 60);
		JButton Cancel = NewButton("Cancel", logoutIcon, "Cancel", null, null, BlueColor, 200, 60);

		// ajouter les elements a l'interface
		pdc.add(CenterTitle("Welcome To Admin's Space"));
		pdc.add(userName);
		pdc.add(loginText);
		pdc.add(passwd);
		pdc.add(passwordField);
		pdc.add(logIn);
		pdc.add(Cancel);

		return AdminPane;
	}

	public Component StudentLogin() {

		JPanel StudentPane = new JPanel();
		// division de la partie droite
		StudentPane.setPreferredSize(new Dimension(1000, 600));
		StudentPane.setBackground(blackColor);

		JPanel pdh = PDH(); // panel droite Haut
		JPanel pdc = PDC(GrayColor);// panel droite centre
		pdc.setPreferredSize(new Dimension(800, 350));
		JPanel pdb = PDB();// panel droite bas

		StudentPane.add(pdh);
		StudentPane.add(pdc);
		StudentPane.add(pdb);

		// declaration des titre et des inputs boxs
		JLabel CodeApoge = SmallTitle("Code Apogée");
		JLabel passwd = SmallTitle("Password");

		JTextField loginText = TextBox();
		JPasswordField passwordField = PasswordBox();

		JTextField[] text = { loginText, passwordField };
		Icon loginIcon = new ImageIcon(".\\..\\icons\\login.png");
		Icon logoutIcon = new ImageIcon(".\\..\\icons\\logout.png");

		JButton logIn = NewButton("Log In", loginIcon, "logStud", text, null, BlueColor, 200, 60);
		JButton Cancel = NewButton("Cancel", logoutIcon, "Cancel", null, null, BlueColor, 200, 60);

		// ajouter les elements a l'interface
		pdc.add(CenterTitle("Welcome To Student's Space"));
		pdc.add(CodeApoge);
		pdc.add(loginText);
		pdc.add(passwd);
		pdc.add(passwordField);
		pdc.add(logIn);
		pdc.add(Cancel);

		return StudentPane;
	}

	public Component ProfessorLogin() {

		JPanel ProfPane = new JPanel();
		// division de la partie droite
		ProfPane.setPreferredSize(new Dimension(1000, 600));
		ProfPane.setBackground(blackColor);

		JPanel pdh = PDH(); // panel droite Haut
		JPanel pdc = PDC(GrayColor);// panel droite centre
		pdc.setPreferredSize(new Dimension(800, 350));
		JPanel pdb = PDB();// panel droite bas

		ProfPane.add(pdh);
		ProfPane.add(pdc);
		ProfPane.add(pdb);

		// declaration des titre et des inputs boxs
		JLabel CIN = SmallTitle("CIN");
		JLabel passwd = SmallTitle("Password");

		JTextField loginText = TextBox();
		JPasswordField passwordField = PasswordBox();

		JTextField[] text = { loginText, passwordField };
		Icon loginIcon = new ImageIcon(".\\..\\icons\\login.png");
		Icon logoutIcon = new ImageIcon(".\\..\\icons\\logout.png");

		JButton logIn = NewButton("Log In", loginIcon, "logProf", text, null, BlueColor, 200, 60);
		JButton Cancel = NewButton("Cancel", logoutIcon, "Cancel", null, null, BlueColor, 200, 60);

		// ajouter les elements a l'interface
		pdc.add(CenterTitle("Welcome To Professor's Space"));
		pdc.add(CIN);
		pdc.add(loginText);
		pdc.add(passwd);
		pdc.add(passwordField);
		pdc.add(logIn);
		pdc.add(Cancel);

		return ProfPane;
	}

	// retourne la page principale ou s'affiche les donnees de tout les examnes
	// sauvgardees ou ajoutes
	public Component ExamsHomeProfs(String CIN) throws Exception {

		JPanel panelDroite = PD();
		// subdiviser panelDroite

		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();
		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut

		pdh = divPDH2(pdh, "E");
		// panelDroiteCentre

		// tableau des Exams
		JPanel ExamsTable = new JPanel();
		ExamsTable.setBackground(GrayColor);

		JScrollPane scrollPane = new JScrollPane(ExamsTable);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);
		ResultSet rs0 = cnt.getProfData(CIN);// nom prenom filiere
		// ___
		int ExamTableHeight = 160;
		ExamsTable.add(CenterTitle("Hello " + rs0.getString(2) + " " + rs0.getString(3) + "!")); // nom et prenom
		JTextArea colones = textArea("\tDate\tDebut\tFin\tModules\tSalles\t\n\n", new Dimension(740, 40));
		ExamsTable.add(colones);

		ExamTableHeight += 120; // 80 de centreTitle + 40 de nom des colonnes

		ResultSet rs = cnt.GetIdExamProf(CIN);// id_p

		while (rs.next()) {
			String str = "";
			str += cnt.showAffectationProf(rs.getString(1),CIN);
			int countRows = 0;// pour calculer le nombre des ligne dans chaque RowExam
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '\n') {
					countRows++;
				}
			}
			JTextArea phrase = textArea(str, new Dimension(650, 20 * countRows));// countRows est le nombre des
																					// lignes

			JPanel RowExam = new JPanel(new BorderLayout());

			RowExam.setPreferredSize(new Dimension(740, 20 * countRows));
			RowExam.setBackground(whiteColor);

			ExamTableHeight += 20 * countRows;

			JPanel pBtns = new JPanel();
			pBtns.setBackground(GrayColor);
			pBtns.setPreferredSize(new Dimension(70, 62));

			RowExam.add(phrase, BorderLayout.WEST);
			RowExam.add(pBtns, BorderLayout.CENTER);
			ExamsTable.add(RowExam);
		}
		// ___

		// ExamsTable soit 70*ExamTableHeight
		ExamTableHeight += 100;
		ExamsTable.setPreferredSize(new Dimension(680, ExamTableHeight));

		return panelDroite;

	}

	public Component ExamsHomeStudent(String CodeAppoge) throws Exception {

		JPanel panelDroite = PD();
		// subdiviser panelDroite

		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();
		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut

		pdh = divPDH2(pdh, "E");
		// panelDroiteCentre

		// tableau des Exams
		JPanel ExamsTable = new JPanel();
		ExamsTable.setBackground(GrayColor);

		JScrollPane scrollPane = new JScrollPane(ExamsTable);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);
		ResultSet rs0 = cnt.getStudData(CodeAppoge);// nom prenom filiere
		// ___
		int ExamTableHeight = 160;
		ExamsTable.add(CenterTitle("Hello " + rs0.getString(2) + " " + rs0.getString(3) + "!")); // nom et prenom
		JTextArea rappel = textArea("Rappel : Votre numero est "+rs0.getString(6), new Dimension(740, 20));

		JTextArea colones = textArea("Date\tDebut-Fin\tModules\tSalles\tProfesseurs\n\n", new Dimension(740, 40));
		ExamsTable.add(CenterTitle(cnt.NomFiliere(rs0.getString(4))));
		ExamsTable.add(rappel);
		ExamsTable.add(colones);

		ExamTableHeight += 120; // 80 de centreTitle + 40 de nom des colonnes

		ResultSet rs = cnt.GetIdExamAffecte(rs0.getString(4));// id_f

		while (rs.next()) {
			String str = "";
			str += cnt.showAffectation(rs.getString(1));
			int countRows = 0;// pour calculer le nombre des ligne dans chaque RowExam
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '\n') {
					countRows++;
				}
			}
			JTextArea phrase = textArea(str, new Dimension(650, 20 * countRows));// countRows est le nombre des
																					// lignes

			JPanel RowExam = new JPanel(new BorderLayout());

			RowExam.setPreferredSize(new Dimension(740, 20 * countRows));
			RowExam.setBackground(whiteColor);

			ExamTableHeight += 20 * countRows;

			JPanel pBtns = new JPanel();
			pBtns.setBackground(GrayColor);
			pBtns.setPreferredSize(new Dimension(70, 62));

			RowExam.add(phrase, BorderLayout.WEST);
			RowExam.add(pBtns, BorderLayout.CENTER);
			ExamsTable.add(RowExam);
		}
		// ___

		ExamsTable.setPreferredSize(new Dimension(680, ExamTableHeight));

		return panelDroite;

	}

	public Component ExamsHome() throws Exception {

		JPanel panelDroite = PD();
		// subdiviser panelDroite

		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();
		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut

		pdh = divPDH(pdh, "E", blackColor, BlueColor);
		// panelDroiteCentre

		// tableau des Exams
		JPanel ExamsTable = new JPanel();
		ExamsTable.setBackground(GrayColor);

		JScrollPane scrollPane = new JScrollPane(ExamsTable);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);

		ExamsTable.add(CenterTitle("Tables Des Examens"));
		ResultSet rs0 = cnt.idFiliereAffecteList();
		int ExamTableHeight = 80; // initialisation par height de " CenterTitle("Tables Des Examens")"

		while (rs0.next()) {

			JTextArea colones = textArea("Date\tDebut-Fin\tModules\tSalles\tProfesseurs\n\n", new Dimension(740, 40));
			ExamsTable.add(CenterTitle(cnt.NomFiliere(rs0.getString(1))));
			ExamsTable.add(colones);

			ExamTableHeight += 120; // 80 de centreTitle + 40 de nom des colonnes

			ResultSet rs = cnt.GetIdExamAffecte(rs0.getString(1));

			while (rs.next()) {
				String str = "";
				str += cnt.showAffectation(rs.getString(1));
				int countRows = 0;// pour calculer le nombre des ligne dans chaque RowExam
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == '\n') {
						countRows++;
					}
				}
				JTextArea phrase = textArea(str, new Dimension(650, 20 * countRows));// countRows est le nombre des
																						// lignes
				JButton edit = NewButton("Edit", null, "edtE" + rs.getString(1), null, null, BlueColor, 70, 30);
				JButton delete = NewButton("Delete", null, "dltE" + rs.getString(1), null, null, blackColor, 70, 30);

				JPanel RowExam = new JPanel(new BorderLayout());

				RowExam.setPreferredSize(new Dimension(740, Math.max(20 * countRows, 70)));
				RowExam.setBackground(whiteColor);

				ExamTableHeight += Math.max(20 * countRows, 2 * (30 + 1));// 20 : row's height , 70 buttons's height

				JPanel pBtns = new JPanel();
				pBtns.setBackground(GrayColor);
				pBtns.setPreferredSize(new Dimension(70, 62));
				pBtns.add(edit);
				pBtns.add(delete);

				RowExam.add(phrase, BorderLayout.WEST);
				RowExam.add(pBtns, BorderLayout.CENTER);
				ExamsTable.add(RowExam);
			}
		}
		// ExamsTable soit 70*ExamTableHeight
		rs0.last();
		ExamTableHeight += 100 * rs0.getRow();
		ExamsTable.setPreferredSize(new Dimension(680, ExamTableHeight));

		JPanel home = MainPanel(menuPrincipale("e"), panelDroite);
		return home;

	}

	// retourne la page pour ajouter un examen contient le formulaire pour
	// enregistrer les donnees
	public Component AjouterExam() throws Exception {
		// Subdivise frame

		JPanel panelDroite = PD();
		// subdiviser panelDroite
		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();

		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut
		pdh = divPDH2(pdh, "E");
		// panelDroiteCentre

		// tableau des Exams
		JPanel TableExams = new JPanel();
		TableExams.setPreferredSize(new Dimension(680, 600));
		TableExams.setBackground(GrayColor);
		JScrollPane scrollPane = new JScrollPane(TableExams);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);

		// initialisation des titre pour les formulaires
		JLabel Date = SmallTitle("Date d'examen");
		JLabel Debut = SmallTitle("L'heure de Début");
		JLabel Fin = SmallTitle("l'heure de la Fin");
		JLabel Modules = SmallTitle("Module d'examen");
		JLabel NbrSalles = SmallTitle("Nombre des salles");
		JLabel Nbrprofs = SmallTitle("Nombre des professeurs/salle");

		String[] NSD_table = {};
		JComboBox<String> T_NbrSalles = ChoiceBox(NSD_table, "JCB_NS");

		String[] NPD_table = {};
		JComboBox<String> T_Nbrprofs = ChoiceBox(NPD_table, "JCB_NP");
		JComboBox<String> T_Modules = ChoiceBox(cnt.getTabModulesJC(), "JCB_MD");
		JComboBox[] box = { T_Modules, T_NbrSalles, T_Nbrprofs };

		JButton Suivant = NewButton("Suivant", null, "Suivant", null, box, BlueColor, 200, 60);

		// ajouter les elements a la fenetre
		TableExams.add(CenterTitle("Ajouter Des Examens"));
		TableExams.add(Date);
		TableExams.add(pages.T_datePicker);
		TableExams.add(Debut);
		TableExams.add(pages.T_TpDebut);
		TableExams.add(Fin);
		TableExams.add(pages.T_TpFin);
		TableExams.add(Modules);
		TableExams.add(T_Modules);
		TableExams.add(NbrSalles);
		TableExams.add(T_NbrSalles);
		TableExams.add(Nbrprofs);
		TableExams.add(T_Nbrprofs);
		TableExams.add(Suivant);

		JPanel home = MainPanel(menuPrincipale("e"), panelDroite);
		return home;
	}

	// retourne la fenetre pour completer l'insertion des donnees d'exames dans la
	// base de donnees lors de clique sur continuer dans la page ajouterExamen
	public Component AffectationSallesProfs() throws SQLException, Exception {
		// Subdivise frame

		JPanel panelDroite = PD();
		// subdiviser panelDroite
		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();

		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut
		pdh = divPDH(pdh, "E", BlueColor, blackColor);
		// panelDroiteCentre

		// tableau des Exams
		JPanel TableExams = new JPanel();
		TableExams.setBackground(GrayColor);
		JScrollPane scrollPane = new JScrollPane(TableExams);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);

		TableExams.add(CenterTitle("Affecter des Salles et des Professeurs"));

		// fornbrsalle..
		// for nbrSalle / for nbrProfsParSalle

		ResultSet rs = cnt.showExam();
		int height = 600;
		int nbSalle = 0;
		int nbProfSalle = 0;
		String id = "0";
		JComboBox[] box = {};
		while (rs.next()) {
			if (rs.isLast()) {
				id = rs.getString(1);
				nbSalle = Integer.parseInt(rs.getString(6));
				nbProfSalle = Integer.parseInt(rs.getString(7));
				height = 250 + 60 * (nbSalle + nbSalle * nbProfSalle);
				box = new JComboBox[nbSalle + nbSalle * nbProfSalle + 1];

				// fornbrsalle..
				for (int s = 0, i = 1; s < nbSalle + nbSalle * nbProfSalle; s += nbProfSalle + 1, i++) {
					JLabel salles = SmallTitle("Salle " + i);
					JComboBox<String> boxSalle = ChoiceBox(
							cnt.salleDisponible(rs.getString(3), rs.getString(4), rs.getString(5)), "nbSalle" + s);
					box[s] = boxSalle;
					TableExams.add(salles);
					TableExams.add(boxSalle);
					// for nbrSalle / for nbrProfsParSalle
					for (int p = s + 1, j = 1; p < s + nbProfSalle + 1; p++, j++) {

						JLabel profs = SmallTitle("Professeur " + j);
						JComboBox<String> boxProf = ChoiceBox(
								cnt.profDisponible(rs.getString(3), rs.getString(4), rs.getString(5)),
								"nbProfSAlle" + p);
						box[p] = boxProf;
						TableExams.add(profs);
						TableExams.add(boxProf);
					}
				}
			}
		}
		JButton Affecter = NewButton("Affecter", null, "Affe" + id, null, box, BlueColor, 200, 60);
		TableExams.add(Affecter);
		TableExams.setPreferredSize(new Dimension(680, height));
		JPanel home = MainPanel(menuPrincipale("e"), panelDroite);
		return home;
	}

	// retourne la page pour visualiser tout les modules ajoutes dans la base de
	// donnees
	public Component ModulesHome() throws Exception {

		// --------------Subdivise frame--------------

		JPanel panelDroite = PD();
		// subdiviser panelDroite

		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();
		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut

		pdh = divPDH(pdh, "M", blackColor, BlueColor);
		// panelDroiteCentre

		ResultSet rs0 = cnt.showModule();
		int rows = 2;
		while (rs0.next()) {
			rows++;
		}

		JPanel TableModules = new JPanel(new GridLayout(rows, 1));
		TableModules.setPreferredSize(new Dimension(680, 100 * rows));
		TableModules.setBackground(GrayColor);

		JScrollPane scrollPane = new JScrollPane(TableModules);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);

		TableModules.add(CenterTitle("Tables des Modules"));

		// selectionner tout les donnees enregistres
		ResultSet rs = cnt.showModule();
		while (rs.next()) {

			JTextArea phrase = new JTextArea(" Nom : " + rs.getString(2) + "\n Code : " + rs.getString(3));

			phrase.setPreferredSize(new Dimension(430, 60));
			phrase.setBackground(GrayColor);
			phrase.setEditable(false);
			phrase.setFont(calibri20);
			phrase.setForeground(blackColor);

			JButton edit = NewButton("Edit", null, "edtM" + rs.getString(1), null, null, BlueColor, 110, 40);
			JButton delete = NewButton("Delete", null, "dltM" + rs.getString(1), null, null, blackColor, 110, 40);
			JPanel RowDemande = new JPanel();
			RowDemande.setPreferredSize(new Dimension(680, 100));
			RowDemande.setBackground(GrayColor);

			RowDemande.setLayout(new FlowLayout(1, 4, 30));
			RowDemande.add(phrase);
			RowDemande.add(edit);
			RowDemande.add(delete);
			TableModules.add(RowDemande);
		}

		JPanel home = MainPanel(menuPrincipale("m"), panelDroite);
		return home;
	}

	// retourne la page pour ajouter des modules
	public Component AjouterModule() throws Exception {
		// Subdivise frame
		JPanel panelDroite = PD();
		// subdiviser panelDroite
		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();

		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut
		pdh = divPDH(pdh, "M", BlueColor, blackColor);
		// panelDroiteCentre
		// tableau des Exams
		JPanel AjouterModule = new JPanel();
		AjouterModule.setPreferredSize(new Dimension(680, 600));
		AjouterModule.setBackground(GrayColor);
		JScrollPane scrollPane = new JScrollPane(AjouterModule);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);

		// initialiser les titres et les imputs
		JLabel CodeM = SmallTitle("Code du Module");
		JLabel NomM = SmallTitle("Nom du module");
		JLabel Filiere = SmallTitle("Filière");

		JTextField T_CodeM = TextBox();
		JTextField T_NomM = TextBox();
		String[] choix = cnt.FiliereList();
		JComboBox<String> Cbox = ChoiceBox(choix, "JCB_FL");

		JTextField[] text = { T_CodeM, T_NomM };
		JComboBox[] box = { Cbox };
		JButton AjouteM = NewButton("Ajouter", null, "AjouterM", text, box, BlueColor, 200, 60);

		// ajouter les elemets a la page
		AjouterModule.add(CenterTitle("Ajouter Des Modules"));
		AjouterModule.add(CodeM);
		AjouterModule.add(T_CodeM);
		AjouterModule.add(NomM);
		AjouterModule.add(T_NomM);

		AjouterModule.add(Filiere);
		AjouterModule.add(Cbox);
		AjouterModule.add(AjouteM);

		JPanel home = MainPanel(menuPrincipale("m"), panelDroite);
		return home;
	}

	// retourne la page qui affiche l'ensemble des professeurs enregistres dans la
	// base de donnees
	public Component ProfsHome() throws Exception {

		// Subdivise frame

		JPanel panelDroite = PD();
		// subdiviser panelDroite

		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();
		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut

		pdh = divPDH(pdh, "P", blackColor, BlueColor); // panelDroiteCentre

		ResultSet rs0 = cnt.showProf();
		int rows = 2;
		while (rs0.next()) {
			rows++;
		}

		JPanel TableProfs = new JPanel(new GridLayout(rows, 1));
		TableProfs.setPreferredSize(new Dimension(680, 100 * rows));
		TableProfs.setBackground(GrayColor);

		JScrollPane scrollPane = new JScrollPane(TableProfs);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);

		TableProfs.add(CenterTitle("Tables Des Professeurs"));

		// selectionner tout les donnees des professeurs
		ResultSet rs = cnt.showProf();
		while (rs.next()) {

			JTextArea phrase = new JTextArea(
					rs.getString(2) + " " + rs.getString(3) + "-" + rs.getString(1) + "\nEmail : " + rs.getString(4));
			phrase.setPreferredSize(new Dimension(430, 60));
			phrase.setBackground(GrayColor);
			phrase.setEditable(false);
			phrase.setFont(calibri20);
			phrase.setForeground(blackColor);
			JButton edit = NewButton("Edit", null, "edtP" + rs.getString(1), null, null, BlueColor, 110, 40);
			JButton delete = NewButton("Delete", null, "dltP" + rs.getString(1), null, null, blackColor, 110, 40);
			JPanel RowDemande = new JPanel();
			RowDemande.setPreferredSize(new Dimension(680, 100));
			RowDemande.setBackground(GrayColor);
			RowDemande.setLayout(new FlowLayout(1, 4, 30));
			RowDemande.add(phrase);
			RowDemande.add(edit);
			RowDemande.add(delete);
			TableProfs.add(RowDemande);
		}

		JPanel home = MainPanel(menuPrincipale("p"), panelDroite);
		return home;
	}

	// retourne la page qui contient le formulaire pour ajouter les professeurs
	public Component AjouterProf() {

		// --------------Subdivise frame--------------

		JPanel panelDroite = PD();
		// subdiviser panelDroite

		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();
		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);
		// subdivise panelDroiteHaut

		pdh = divPDH(pdh, "P", BlueColor, blackColor);
		// panelDroiteCentre
		JPanel TableClasses = new JPanel();
		TableClasses.setPreferredSize(new Dimension(680, 550));
		TableClasses.setBackground(GrayColor);
		JScrollPane scrollPane = new JScrollPane(TableClasses);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);

		JLabel CIN = SmallTitle("CIN");
		JLabel Nom = SmallTitle("Nom");
		JLabel Prenom = SmallTitle("Prenom");
		JLabel Email = SmallTitle("Email");

		JTextField CIN_text = TextBox();
		JTextField Nom_text = TextBox();
		JTextField Prenom_text = TextBox();
		JTextField Email_text = TextBox();
		JTextField[] text = { CIN_text, Nom_text, Prenom_text, Email_text };
		JButton btn = NewButton("Ajouter", null, "AjouterP", text, null, BlueColor, 200, 60);

		TableClasses.add(CenterTitle("Ajouter Des Professeurs"));
		TableClasses.add(CIN);
		TableClasses.add(CIN_text);
		TableClasses.add(Nom);
		TableClasses.add(Nom_text);
		TableClasses.add(Prenom);
		TableClasses.add(Prenom_text);
		TableClasses.add(Email);
		TableClasses.add(Email_text);
		TableClasses.add(btn);

		JPanel home = MainPanel(menuPrincipale("p"), panelDroite);
		return home;
	}

	// retourne la page qui affiche tout les donnees a propos dles salles
	public Component ClassesHome() throws Exception {

		// --------------Subdivise frame--------------

		JPanel panelDroite = PD();
		// subdiviser panelDroite

		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();
		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut

		pdh = divPDH(pdh, "S", blackColor, BlueColor);
		// panelDroiteCentre

		// extraire les donnees des salles
		ResultSet rs0 = cnt.showSalle();
		int rows = 2;
		// calcule nombre des salles
		while (rs0.next()) {
			rows++;
		}

		JPanel TableClasses = new JPanel(new GridLayout(rows, 1));
		TableClasses.setPreferredSize(new Dimension(680, 100 * rows));
		TableClasses.setBackground(GrayColor);

		JScrollPane scrollPane = new JScrollPane(TableClasses);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);

		TableClasses.add(CenterTitle("Tables Des Salles"));

		ResultSet rs = cnt.showSalle();
		while (rs.next()) {

			JTextArea phrase = new JTextArea("Type - Numéro : " + rs.getString(4) + " - " + rs.getString(2)
					+ "\n Capacité : " + rs.getString(3));

			phrase.setPreferredSize(new Dimension(430, 60));
			phrase.setBackground(GrayColor);
			phrase.setEditable(false);
			phrase.setFont(calibri20);
			phrase.setForeground(blackColor);

			JButton edit = NewButton("Edit", null, "edtS" + rs.getString(1), null, null, BlueColor, 110, 40);
			JButton delete = NewButton("Delete", null, "dltS" + rs.getString(1), null, null, blackColor, 110, 40);
			JPanel RowDemande = new JPanel();
			RowDemande.setPreferredSize(new Dimension(680, 100));
			RowDemande.setBackground(GrayColor);

			RowDemande.setLayout(new FlowLayout(1, 4, 30));
			RowDemande.add(phrase);
			RowDemande.add(edit);
			RowDemande.add(delete);
			TableClasses.add(RowDemande);
		}

		JPanel home = MainPanel(menuPrincipale("s"), panelDroite);
		return home;
	}

	// retourne la page pour ajouter des salles en affichant le formulaire pour
	// entrez les donnees necessaires
	public Component AjouterClasse() {

		JPanel panelDroite = PD();
		// subdiviser panelDroite

		JPanel pdh = PDH();
		JPanel pdc = PDC(blackColor);
		JPanel pdb = PDB();
		panelDroite.add(pdh);
		panelDroite.add(pdc);
		panelDroite.add(pdb);

		// subdivise panelDroiteHaut

		pdh = divPDH(pdh, "S", BlueColor, blackColor);
		// panelDroiteCentre

		JPanel TableClasses = new JPanel();
		TableClasses.setPreferredSize(new Dimension(680, 500));
		TableClasses.setBackground(GrayColor);
		JScrollPane scrollPane = new JScrollPane(TableClasses);
		scrollPane.setPreferredSize(new Dimension(770, 425));
		pdc.add(scrollPane);

		JLabel NumSalle = SmallTitle("Numero salle");
		JLabel Capacite = SmallTitle("Capacite");
		JLabel TypeSalle = SmallTitle("Type de salle");

		JTextField NumSalle_text = TextBox();
		JTextField Capacite_text = TextBox();

		String[] choices = { "Amphi", "TD", "TP" };
		JComboBox<String> Type_salleBox = ChoiceBox(choices, "JCB_S");

		JTextField[] text = { NumSalle_text, Capacite_text };
		JComboBox[] box = { Type_salleBox };
		JButton btn = NewButton("Ajouter", null, "AjouterS", text, box, BlueColor, 200, 60);

		TableClasses.add(CenterTitle("Ajouter Des Salles"));
		TableClasses.add(NumSalle);
		TableClasses.add(NumSalle_text);
		TableClasses.add(Capacite);
		TableClasses.add(Capacite_text);
		TableClasses.add(TypeSalle);
		TableClasses.add(Type_salleBox);
		TableClasses.add(btn);

		JPanel home = MainPanel(menuPrincipale("s"), panelDroite);
		return home;
	}

}
