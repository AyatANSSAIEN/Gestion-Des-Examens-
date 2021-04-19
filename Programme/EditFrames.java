/*
cette class contient les different fenetres de modification(il s'affiche lors de clique sur le buttons de modification)
*/ 
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import com.github.lgooddatepicker.components.TimePicker;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
public class EditFrames extends Items {
    public static JFrame  FSalle = new JFrame();
    public static JFrame  FModule = new JFrame();
    public static JFrame  FProf = new JFrame();
    public static JFrame  FExam = new JFrame();

    public static JDatePickerImpl T_datePicker2 = newDP();
	public static TimePicker T_TpDebut2 = newTP();
    public static TimePicker T_TpFin2 = newTP();

    ConnectionDb cnt = new ConnectionDb();

    //cette methode retourne la fenetre principale 
    public JFrame mainF(JPanel p, String title) {	
      JFrame  mainF = new JFrame();
        mainF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainF.setBounds(150, 100, 700, 400);
        mainF.setLayout(new BorderLayout());
        mainF.add(p);
        mainF.setVisible(true);
        mainF.setTitle(title);
        return mainF;
    }

    //cette methode retourne la fenetre pour modifier les modules
    public JFrame EditModule(String id, String nomModule, String CodeModule) {

        JPanel p = new JPanel();

        JPanel EditModule = new JPanel();
        EditModule.setPreferredSize(new Dimension(680, 400));
        EditModule.setBackground(GrayColor);
        p.add(EditModule);

        //affichage des form pour remplir les donnees
        JLabel CodeM = SmallTitle("Code Module");
        JLabel NomM = SmallTitle("Le nom du module");

        JTextField T_CodeM = TextBox();
        JTextField T_NomM = TextBox();
        T_CodeM.setText(CodeModule);
        T_NomM.setText(nomModule);

        JTextField[] text = { T_CodeM, T_NomM };

        JButton edit = NewButton("Edit",null, "edMv" + id, text, null, BlueColor, 200, 60);

        //ajouter les elements declares a la fenetre
        EditModule.add(CenterTitle("Modifier Un Module"));
        EditModule.add(CodeM);
        EditModule.add(T_CodeM);
        EditModule.add(NomM);
        EditModule.add(T_NomM);
        EditModule.add(edit);
        FModule = mainF(p, "Edit Module " + id);
        return  FModule;
    }

    //cette methode retourne la fenetre pour modifier les salles
    public JFrame EditSalle(String id, String numS, String Cap, String TypeS) {

        JPanel EditSalle = new JPanel();
        JPanel p = new JPanel();

        EditSalle.setPreferredSize(new Dimension(680, 400));
        EditSalle.setBackground(GrayColor);

        p.add(EditSalle);

        //affichage des lables et des input pour entrer les donnees necessaires
        JLabel NumSalle = SmallTitle("Numero salle");
        JLabel Capacite = SmallTitle("Capacite");
        JLabel TypeSalle = SmallTitle("Type de salle");

        JTextField NumSalle_text = TextBox();
        NumSalle_text.setText(numS);
        JTextField Capacite_text = TextBox();
        Capacite_text.setText(Cap);

        String[] choices = { "Amphi", "TD", "TP" };
        JComboBox<String> Type_salleBox = ChoiceBox(choices, "JCB_S");
        Type_salleBox.setSelectedItem(TypeS);

        JTextField[] text = { NumSalle_text, Capacite_text };
        JComboBox[] box = { Type_salleBox };

        JButton edit = NewButton("Edit",null, "edSv" + id, text, box, BlueColor, 200, 60);

        //ajouter les differents elements a la fenetre (frame)
        EditSalle.add(CenterTitle("Modifier une salle"));
        EditSalle.add(NumSalle);
        EditSalle.add(NumSalle_text);
        EditSalle.add(Capacite);
        EditSalle.add(Capacite_text);
        EditSalle.add(TypeSalle);
        EditSalle.add(Type_salleBox);
        EditSalle.add(edit);
        FSalle =mainF(p, "Edit Salle " + id);
        return FSalle;
    }

    //cette methode retourne la fenetre pour modifier les professeurs
    public JFrame EditProf(String cin, String nom, String prenom, String email) {

        JPanel p = new JPanel();

        JPanel EditProf = new JPanel();
        EditProf.setPreferredSize(new Dimension(680, 500));
        EditProf.setBackground(GrayColor);

        JScrollPane scrollPane = new JScrollPane(EditProf);
        scrollPane.setPreferredSize(new Dimension(680, 400));
        p.add(scrollPane);

        //affichage des lables et des input pour entrer les donnees necessaires
        JLabel CIN = SmallTitle("CIN");
        JLabel Nom = SmallTitle("Nom");
        JLabel Prenom = SmallTitle("Preom");
        JLabel Email = SmallTitle("Email");

        JTextField T_CIN = TextBox();
        JTextField T_Nom = TextBox();
        JTextField T_Prenom = TextBox();
        JTextField T_Email = TextBox();

        T_CIN.setText(cin);
        T_Nom.setText(nom);
        T_Prenom.setText(prenom);
        T_Email.setText(email);

        JTextField[] text = { T_CIN, T_Nom, T_Prenom, T_Email };
        JButton edit = NewButton("Edit",null, "edPv" + cin, text, null, BlueColor, 200, 60);

        //ajouter les differents elements a la fenetre (frame)
        EditProf.add(CenterTitle("Modifier Un Professeur"));
        EditProf.add(CIN);
        EditProf.add(T_CIN);
        EditProf.add(Nom);
        EditProf.add(T_Nom);
        EditProf.add(Prenom);
        EditProf.add(T_Prenom);
        EditProf.add(Email);
        EditProf.add(T_Email);
        EditProf.add(edit);
        FProf =  mainF(p, "Edit Prof " + cin);
        return FProf;
    }

    //cette methode retourne la fenetre pour modifier les examens
    public JPanel EditExam(String id, String module, String date, String debut, String fin, String nbSalle,

        String nbProfSalle) throws Exception {

        JPanel p = new JPanel();
        JPanel EditExam = new JPanel();
        EditExam.setPreferredSize(new Dimension(660, 650));
        EditExam.setBackground(GrayColor);
        JScrollPane scrollPane = new JScrollPane(EditExam);
        scrollPane.setPreferredSize(new Dimension(680, 360));
        p.add(scrollPane);

        //affichage des lables et des input pour entrer les donnees necessaires
        JLabel Date = SmallTitle("Date d'examen");
        JLabel Debut = SmallTitle("L'heure de Début");
        JLabel Fin = SmallTitle("l'heure de la Fin");
        JLabel Modules = SmallTitle("Module d'examen");
        JLabel NbrSalles = SmallTitle("Nombre des salles");
        JLabel Nbrprofs = SmallTitle("Nombre des professeurs/salle");

        //initialiser la date d'apres la base de donnees
        T_datePicker2.getModel().setDate(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(8, 10)));
        T_datePicker2.getModel().setSelected(true);
    

        T_TpDebut2.setText(debut);
        T_TpFin2.setText(fin);

        String[] NSD_table = {};
        JComboBox<String> T_NbrSalles = ChoiceBox(NSD_table, "JCB_NS");
        String[] NPD_table = {};
        JComboBox<String> T_Nbrprofs = ChoiceBox(NPD_table, "JCB_NP");

        JComboBox<String> T_Modules = ChoiceBox(new ConnectionDb().getTabModulesJC(), "JCB_MD");
        String str = cnt.getModuleName(module)+"("+cnt.filiereModule(module)+")";
        System.out.println(str);
        T_Modules.setSelectedItem(str);
        T_NbrSalles.addItem(nbSalle);
        T_NbrSalles.setSelectedItem(nbSalle);
        T_Nbrprofs.addItem(nbProfSalle);
        T_Nbrprofs.setSelectedItem(nbProfSalle);

        JComboBox[] box = {T_Modules,T_NbrSalles,T_Nbrprofs}; 

        T_datePicker2.getComponent(0).addMouseListener(new Actions(box));
        T_datePicker2.getComponent(1).addMouseListener(new Actions(box));

        T_TpDebut2.getComponent(0).addMouseListener(new Actions(box));
        T_TpDebut2.getComponent(1).addMouseListener(new Actions(box)); 
        
        T_TpFin2.getComponent(0).addMouseListener(new Actions(box));
        T_TpFin2.getComponent(1).addMouseListener(new Actions(box));

        JButton Suivant = NewButton("Edit",null, "edEv"+id,null,box, BlueColor, 200, 60);
        
        //ajouter les differents elements a la fenetre (frame)
        EditExam.add(CenterTitle("Modifier Un Examen"));
        EditExam.add(Date);
        EditExam.add(T_datePicker2); 
        EditExam.add(Debut);
        EditExam.add(T_TpDebut2);
        EditExam.add(Fin);
        EditExam.add(T_TpFin2);
        EditExam.add(Modules);
        EditExam.add(T_Modules);
        EditExam.add(NbrSalles);
        EditExam.add(T_NbrSalles);
        EditExam.add(Nbrprofs);
        EditExam.add(T_Nbrprofs);
        EditExam.add(Suivant);
       // FExam = mainF(p, "Edit Exam " + id);
        return p;
    }
    
    //cette methode retourne la fenetre pour modifier les affectations apres la modication des examens
    public JPanel EditAff() throws SQLException, Exception {

        JPanel p = new JPanel();

        JPanel EditProf = new JPanel();
        EditProf.setPreferredSize(new Dimension(680, 500));
        EditProf.setBackground(GrayColor);
        
        JPanel TableExams = new JPanel();
		TableExams.setBackground(GrayColor);
		JScrollPane scrollPane = new JScrollPane(TableExams);
        scrollPane.setPreferredSize(new Dimension(680, 360));
		p.add(scrollPane);

		TableExams.add(CenterTitle("Affecter des Salles et des Professeurs"));

		// fornbrsalle..
		// for nbrSalle / for nbrProfsParSalle

		ResultSet rs = cnt.showExam();

		int nbSalle = 0;
        int nbProfSalle = 0;
        int height = 600;
		String id = "0";
		JComboBox[] box = {};
		while (rs.next()) {
			if (rs.isLast()) {
				id= rs.getString(1);
				nbSalle = Integer.parseInt(rs.getString(6));
                nbProfSalle = Integer.parseInt(rs.getString(7));
                height = 250+ 60*(nbSalle +nbSalle*nbProfSalle) ; 

				box = new JComboBox[nbSalle + nbSalle * nbProfSalle+1];

				//remplir les listes des choix des salles et des professeurs par les valeurs disponibles au date et heure entres
				for (int s = 0,j=1; s <nbSalle + nbSalle * nbProfSalle ; s += nbProfSalle + 1,j++) {
					JLabel salles = SmallTitle("Salle "+j);
					JComboBox<String> boxSalle = ChoiceBox(
							cnt.salleDisponible(rs.getString(3), rs.getString(4), rs.getString(5)), "nbSalle" + s);
					box[s] = boxSalle;
					TableExams.add(salles);
					TableExams.add(boxSalle);
					
					for (int pr = s + 1, i = 1 ; pr < s + nbProfSalle + 1 ; pr++,i++) {
                        
                        JLabel profs = SmallTitle("Professeur "+i);
                        
						JComboBox<String> boxProf = ChoiceBox(
								cnt.profDisponible(rs.getString(3), rs.getString(4), rs.getString(5)),
								"nbProfSAlle" + pr);
						box[pr] = boxProf;
						TableExams.add(profs);
						TableExams.add(boxProf);
					}
				}
			}
        }
        TableExams.setPreferredSize(new Dimension(660, height));

		JButton Affecter = NewButton("Affecter",null, "Affe"+id, null, box, BlueColor, 200, 60);
        TableExams.add(Affecter);
        
        return p;
    }

}
