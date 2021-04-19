
/*
cette classe contient les differents methodes implemente pour etablir les actions de clique et de flottement

*/
import java.awt.*;
import java.awt.event.*;

import java.sql.*;
import javax.swing.*;

import net.sourceforge.jdatepicker.*;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class Actions extends pages implements ActionListener, MouseListener {

    ConnectionDb cnt = new ConnectionDb();
    EditFrames fe = new EditFrames();

    public static JFrame fr = new JFrame();
    public static String id2;
    public static Color BtnColor;

    private static JComboBox<String> choiceBoxNS = null;
    private JComboBox<String> choiceBox;

    private JComboBox[] box;
    private JTextField[] text;
    private String[] choix;

    private String name;
    private JButton btn;
    private Icon icon;
    private String id;

    private JDatePickerImpl datePicker;
    private JComboBox<String> t_Modules;
    private JComboBox<String> t_NbrSalles;
	private JComboBox<String> t_Nbrprofs;

    public Actions() {
    }

    Actions(String id, JTextField[] text, JComboBox[] box) {
        this.id = id;
        this.text = text;
        this.box = box;
        id2 = id.substring(4);
    }

    public Actions(String[] choix, JComboBox choiceBox, String name) {
        this.name = name;
        this.choiceBox = choiceBox;
        this.choix = choix;
    }

    public Actions(JButton btn, Icon icon) {
        this.btn = btn;
        this.icon = icon;
    }

  

	public Actions(JComboBox[] box) {
                this.box = box;     
	}
    

	@Override
    public void actionPerformed(ActionEvent e) {

        // MenuPrincipale Et Accueils
        // Permet de passer d'une Jpanel a une autre
        if (id.equals("Professeurs") || id.equals("AccP")) {
            Main.c.show(Main.parentPanel, "ProfHome");
        }

        if (id.equals("Salles") || id.equals("AccS")) {
            Main.c.show(Main.parentPanel, "ClassHome");
        }

        if (id.equals("Modules") || id.equals("AccM")) {
            Main.c.show(Main.parentPanel, "ModuleHome");
        }

        if (id.equals("Exams") || id.equals("AccE")) {
            Main.c.show(Main.parentPanel, "ExamHome");
        }

        // Menu : Ajouter
        if (id.equals("AjtP")) {
            Main.c.show(Main.parentPanel, "ProfAdd");
        }

        if (id.equals("AjtS")) {
            Main.c.show(Main.parentPanel, "ClassAdd");
        }

        if (id.equals("AjtM")) {
            Main.c.show(Main.parentPanel, "ModuleAdd");
        }

        if (id.equals("AjtE")) {
            Main.c.show(Main.parentPanel, "ExamAdd");
        }
        // to admin
        if (id.equals("Admin")) {
            Main.c.show(Main.parentPanel, "AdminHome");
        }
        // to professor
        if (id.equals("Prof")) {
            Main.c.show(Main.parentPanel, "ProfLogin");
        }
        // to student
        if (id.equals("Student")) {
            Main.c.show(Main.parentPanel, "StudentLogin");
        }
        // to firstInter FirstInter
        if (id.equals("Cancel")) {
            Main.c.show(Main.parentPanel, "FirstInter");
        }
        // LogOut
        if (id.equals("logout")) {
            Main.c.show(Main.parentPanel, "FirstInter");
        }

        // Login as Admin
        if (id.equals("login")) {
            try {
                // cnt.isAdmin(username, pass)
                if (cnt.isAdmin(text[0].getText(), text[1].getText())) {
                    Main.c.show(Main.parentPanel, "ExamHome");
                } else {
                    JOptionPane.showMessageDialog(Main.frame, "Login ou password est incorrect");
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec d'authentification");
            }
        }

        // Login as Professor
        if (id.equals("logProf")) {
            try {
                // cnt.isAdmin(username, pass)
                if (cnt.isProfessor(text[0].getText(), text[1].getText())) {
                    Main.parentPanel.add(ExamsHomeProfs(text[0].getText()), "ExamHomeProf");
                    Main.c.show(Main.parentPanel, "ExamHomeProf");
                } else {
                    JOptionPane.showMessageDialog(Main.frame, "Login ou password est incorrect");
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec d'authentification");
            }
        }
        // Login as Student
        if (id.equals("logStud")) {
            try {
                // cnt.isAdmin(username, pass)
                if (cnt.isStudent(text[0].getText(), text[1].getText())) {
                    Main.parentPanel.add(ExamsHomeStudent(text[0].getText()), "ExamHomeStud");
                    Main.c.show(Main.parentPanel, "ExamHomeStud");
                } else {
                    JOptionPane.showMessageDialog(Main.frame, "Login ou password est incorrect");
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec d'authentification");
            }
        }

        // Ajouter à la base des données
        // Ajouter Ptofesseurs en cliquant sur Ajouter
        if (id.equals("AjouterP")) {
            try {
                // cnt.addProf(Cni, FirstName, LastName, Email);
                cnt.addProf(text[0].getText(), text[1].getText(), text[2].getText(), text[3].getText());
                Main.parentPanel.add(ProfsHome(), "ProfHome");
                Main.c.show(Main.parentPanel, "ProfHome");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "CIN déja existe");
            }

        }
        // Ajouter Salles en cliquant sur Ajouter
        if (id.equals("AjouterS")) {
            try {
                // cnt.addSalle(num, capacite, type);
                cnt.addSalle(text[0].getText(), text[1].getText(), box[0].getSelectedItem().toString());
                Main.parentPanel.add(ClassesHome(), "ClassHome");
                Main.c.show(Main.parentPanel, "ClassHome");

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Salle déja  exite");
            }
        }
        // Ajouter Modules en cliquant sur Ajouter
        if (id.equals("AjouterM")) {
            try {
                // cnt.addModule(code_module, nom_module, id_f);
                cnt.addModule(text[0].getText(), text[1].getText(), cnt.idFiliere(box[0].getSelectedItem().toString()));
                Main.parentPanel.add(ModulesHome(), "ModuleHome");
                Main.c.show(Main.parentPanel, "ModuleHome");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, " Le module est déja inseré pour cette filière");

            }
        }

        // Suivant
        if (id.equals("Suivant")) {
            DateModel cpn = T_datePicker.getModel();
            String str = cpn.getYear() + "-" + (cpn.getMonth() + 1) + "-" + cpn.getDay();
            try {
                String debut = T_TpDebut.toString();
                String fin = T_TpFin.toString();

                // affichage message d'erreur lors l'heure fin est superieur que l'heure de
                // debut
                if (debut.compareTo(fin) > 0) {
                    JOptionPane.showMessageDialog(Main.frame, "Début doit être avant la fin d'examen");
                }
                // si la meme date ou le meme module
                else {
                    String strModule = box[0].getSelectedItem().toString();
                    int indexM1 = strModule.indexOf("(") + 1;

                    // cnt.addExam(module, dateExam, debut, fin, nbSalle, nbProfSalle);
                    cnt.addExam(strModule.substring(0, indexM1 - 1), str, T_TpDebut.toString(), T_TpFin.toString(),
                            box[1].getSelectedItem().toString(), box[2].getSelectedItem().toString());
                    Main.parentPanel.add(AffectationSallesProfs(), "ExamAff");
                    Main.c.show(Main.parentPanel, "ExamAff");
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec !");
            }

        }
        // affecter
        if (id.substring(0, 4).equals("Affe")) {

            try {

                // cnt.getNsalleNprof(idExam)
                ResultSet rs = cnt.getNsalleNprof(id.substring(4));
                int nbSalle = Integer.parseInt(rs.getString(1));
                int nbProfSalle = Integer.parseInt(rs.getString(2));
                String[] tab = new String[nbSalle];
                int i = 0;
                for (int s = 0; s < nbSalle + nbSalle * nbProfSalle; s += nbProfSalle + 1) {
                    String strGetSalle = box[s].getSelectedItem().toString();
                    tab[i] = strGetSalle.substring(0, 3);
                    i++;
                }

                // cnt.tabRep(tab) evalue si les elements d'un tableau sont uniques
                // si les element de tableau ne se repetent pas
                if (cnt.tabRep(tab)) {
                    for (int s = 0; s < nbSalle + nbSalle * nbProfSalle; s += nbProfSalle + 1) {

                        for (int p = s + 1; p < s + nbProfSalle + 1; p++) {

                            String strGetSalle = box[s].getSelectedItem().toString();
                            String strGetProf = box[p].getSelectedItem().toString();
                            int indexS1 = strGetSalle.indexOf("(") + 1;
                            int indexS2 = strGetSalle.indexOf(")");
                            int indexP1 = strGetProf.indexOf("(") + 1;
                            int indexP2 = strGetProf.indexOf(")");

                            // cnt.addAffect(idExam, idSalle, idProf);
                            cnt.addAffect(id.substring(4), strGetSalle.substring(indexS1, indexS2),
                                    strGetProf.substring(indexP1, indexP2));
                        }
                    }

                    Main.parentPanel.add(ExamsHome(), "ExamHome");
                    Main.c.show(Main.parentPanel, "ExamHome");
                    fr.dispose();
                } else
                    JOptionPane.showMessageDialog(Main.frame, "Vous avez dupliqué les salles ");
            }

            catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Vous avez dupliqué les professeurs");
                try {
                    // cnt.deleteToEditAffect(id);
                    cnt.deleteToEditAffect(id.substring(4));
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(Main.frame, "Echec de modification");
                }
            }
        }

        // Editer et supprimner Module
        // supprimer un module
        if (id.substring(0, 4).equals("dltM")) {

            try {
                // cnt.deleteModule(id); il prend id module a modifier comme parametre
                cnt.deleteModule(id.substring(4));
                Main.parentPanel.add(ModulesHome(), "ModuleHome");
                Main.c.show(Main.parentPanel, "ModuleHome");

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "le module est deja affecté");
            }

        }

        // modifier un module
        if (id.substring(0, 4).equals("edtM")) {

            ResultSet rs;
            try {
                // cnt.showModule() return tout les donnees dans le tableau module
                rs = cnt.showModule();
                while (rs.next()) {
                    // si id_module egale au valeur passe en parametre
                    if (rs.getString(1).equals(id.substring(4)))
                        // fe.EditModule(id, nomModule, CodeModule)
                        fe.EditModule(rs.getString(1), rs.getString(2), rs.getString(3));
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec de modification");
            }

        }

        // Editer et supprimer Salle
        // supprimer une salle
        if (id.substring(0, 4).equals("dltS")) {
            try {
                // cnt.deleteSalle(id); id de la salle
                cnt.deleteSalle(id.substring(4));
                Main.parentPanel.add(ClassesHome(), "ClassHome");
                Main.c.show(Main.parentPanel, "ClassHome");

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "la salle est deja affecté");
            }
        }
        // modifier une salle
        if (id.substring(0, 4).equals("edtS")) {

            ResultSet rs;
            try {
                rs = cnt.showSalle();
                while (rs.next()) {
                    if (rs.getString(1).equals(id.substring(4)))
                        // fe.EditSalle(id, numSalle, Capacite, TypeSalle)
                        fe.EditSalle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec de modification");
            }
        }

        // Editer et supprimer Proffesseurs
        // supprimer un professeur
        if (id.substring(0, 4).equals("dltP")) {
            try {
                // cnt.deleteProf(Cni); Cni cle primaire
                cnt.deleteProf(id.substring(4));
                Main.parentPanel.add(ProfsHome(), "ProfHome");
                Main.c.show(Main.parentPanel, "ProfHome");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "le professeur est deja affecté");
            }
        }

        // modifier un professeur
        if (id.substring(0, 4).equals("edtP")) {
            ResultSet rs;
            try {
                rs = cnt.showProf();
                while (rs.next()) {
                    if (rs.getString(1).equals(id.substring(4)))
                        // fe.EditProf(cin, nom, prenom, email)
                        fe.EditProf(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec de modification");
            }
        }

        // Editer et supprimer Examens
        // supprimer un examen
        if (id.substring(0, 4).equals("dltE")) {
            try {
                // cnt.deleteAffectation(id_e); id de l'examen
                cnt.deleteAffectation(id.substring(4));
                Main.parentPanel.add(ExamsHome(), "ExamHome");
                Main.c.show(Main.parentPanel, "ExamHome");

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec de supprision");
            }
        }

        // modifier un examen
        if (id.substring(0, 4).equals("edtE")) {
            ResultSet rs;
            try {
                rs = cnt.showExam();
                while (rs.next()) {
                    if (rs.getString(1).equals(id.substring(4))) {
                        // fe.EditExam(id, module, date, debut, fin, nbSalle, nbProfSalle)
                        Main.EditparentPanel.add(fe.EditExam(rs.getString(1), rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)), "editExam");
                        Main.EditparentPanel.add(fe.EditAff(), "editAff");
                        fr = fe.mainF(Main.EditparentPanel, "edit exam");
                        Main.EditC.show(Main.EditparentPanel, "editExam");
                    }
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec de modification");
            }
        }
        /// actions sur les boutton de modification sur les fenetres de modification
        // proffesseur
        if (id.substring(0, 4).equals("edPv")) {
            try {
                // cnt.editProf(OldCni, Cni, LastName, FirstName, Email);
                cnt.editProf(id.substring(4), text[0].getText(), text[1].getText(), text[2].getText(),
                        text[3].getText());
                EditFrames.FProf.dispose();
                Main.parentPanel.add(ProfsHome(), "ProfHome");
                Main.c.show(Main.parentPanel, "ProfHome");

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Le CIN déja existe");

            }
        }

        // salles
        if (id.substring(0, 4).equals("edSv")) {
            try {
                // cnt.editSalle(id, num, capacite, type);
                cnt.editSalle(id.substring(4), text[0].getText(), text[1].getText(),
                        box[0].getSelectedItem().toString());
                EditFrames.FSalle.dispose();
                Main.parentPanel.add(ClassesHome(), "classHome");
                Main.c.show(Main.parentPanel, "classHome");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "La salle déja existe");

            }
        }

        // modules
        if (id.substring(0, 4).equals("edMv")) {
            try {
                cnt.editModule(id.substring(4), text[1].getText(), text[0].getText());
                EditFrames.FModule.dispose();
                Main.parentPanel.add(ModulesHome(), "ModulesHome");
                Main.c.show(Main.parentPanel, "ModulesHome");
            } catch (Exception e1) {

                JOptionPane.showMessageDialog(Main.frame, " Le module est déja inseré pour cette filière");
            }
        }

        // examens
        // pour modifier examen on efface les affectation effectue, apres on fait la
        // mise a jour avec les nouvelle valeurs
        // et refaire les affectations necessaires.
        if (id.substring(0, 4).equals("edEv")) {
            DateModel cpn = EditFrames.T_datePicker2.getModel();
            String str = cpn.getYear() + "-" + (cpn.getMonth() + 1) + "-" + cpn.getDay();

            String strModule = box[0].getSelectedItem().toString();
            int indexM1 = strModule.indexOf("(") + 1;
            try {

                cnt.deleteToEditAffect(id.substring(4));

                cnt.editExam(id.substring(4), strModule.substring(0, indexM1 - 1), str,
                        EditFrames.T_TpDebut2.toString(), EditFrames.T_TpFin2.toString(),
                        box[1].getSelectedItem().toString(), box[2].getSelectedItem().toString());
                Main.EditparentPanel.add(fe.EditAff(), "editAff");
                Main.EditC.show(Main.EditparentPanel, "editAff");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(Main.frame, "Echec");
            }
        }
    }

    // MouseListener pour utiliser les actions sur l'entre/sortie , clique de
    // souris.
    /*
     * on a utilise ces methodes lors d'ajout d'un examen pour extraire juste les
     * donne disponible pour une date heure debut/fin entree
     */
    public void mousePressed(MouseEvent me) {
        if (EditFrames.T_datePicker2 != null && box != null){
            for (JComboBox b :  box){
                b.removeAllItems();
            }
        }
        if (EditFrames.T_TpDebut2 != null && box != null ){
            for (JComboBox b :  box){
                b.removeAllItems();
            }
        }
        if (EditFrames.T_TpFin2 != null && box != null){
            for (JComboBox b :  box){
                b.removeAllItems();
            }
        }
        if (name != null) {

            // mise a jour de liste de choix des modules
            if (name.equals("JCB_MD")) {
                DateModel cpn = T_datePicker.getModel();
                String str = cpn.getYear() + "-" + (cpn.getMonth() + 1) + "-" + cpn.getDay();
                try {
                    choiceBox.removeAllItems();
                    // cnt.getTabModulesJC(date, debut, fin) il ya 2 fonction une sans parametre
                    choix = cnt.getTabModulesJC(str, T_TpDebut.toString(), T_TpFin.toString());
                    for (String i : choix)
                        choiceBox.addItem(i);

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(Main.frame, "Echec");
                }
            }

            // mise a jour de liste de choix de nombre des salles
            if (name.equals("JCB_NS")) {
                choiceBoxNS = choiceBox;
                DateModel cpn = T_datePicker.getModel();
                String str = cpn.getYear() + "-" + (cpn.getMonth() + 1) + "-" + cpn.getDay();
                try {
                    choiceBox.removeAllItems();
                    // cnt.indexSalleDispoList(date, debut, fin)
                    choix = cnt.indexSalleDispoList(str, T_TpDebut.toString(), T_TpFin.toString());
                    for (String i : choix)
                        choiceBox.addItem(i);

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(Main.frame, "Echec");
                }
            }

            // mise a jour de nombre des professeurs par salle
            if (name.equals("JCB_NP")) {
                DateModel cpn = T_datePicker.getModel();
                String str = cpn.getYear() + "-" + (cpn.getMonth() + 1) + "-" + cpn.getDay();
                try {

                    // le nombre des salles id.substring(4)

                    choiceBox.removeAllItems();
                    if (choiceBoxNS == null) {
                        // cnt.getNbrSalleOfExam(id_e)
                        String nbr_salle = cnt.getNbrSalleOfExam(id2);
                        choix = cnt.indexProfDispoList(str, T_TpDebut.toString(), T_TpFin.toString(),
                                Integer.parseInt(nbr_salle));

                    } else {
                        // cnt.indexProfDispoList(date, debut, fin, nbrSalle)
                        choix = cnt.indexProfDispoList(str, T_TpDebut.toString(), T_TpFin.toString(),
                                Integer.parseInt(choiceBoxNS.getSelectedItem().toString()));
                    }
                    for (String i : choix)
                        choiceBox.addItem(i);

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(Main.frame, "Echec");
                }
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // effectuer un effet lorsque la souris entre sur un button (changer des
    // couleurs, ajouter des icons)
    @Override
    public void mouseEntered(MouseEvent e) {
        if (btn != null) {

            BtnColor = btn.getBackground();
            btn.setBackground(BlueSkyColor);
            btn.setForeground(blackColor);
            btn.setIcon(icon);
        }
    }

    // effectuer un effet lorsque la souris sortie d'un button (changer des
    // couleurs)
    @Override
    public void mouseExited(MouseEvent e) {
        if (btn != null) {

            btn.setBackground(BtnColor);
            btn.setIcon(null);
            btn.setForeground(whiteColor);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

}