/*
cette classe contient les differents element utilises dans les interfaces et les fenetres 
comme les couleurs, les fonts , les Jlabel et les inputs
*/
import java.awt.*;
import javax.swing.*;

import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeArea;

import org.jdatepicker.DateLabelFormatter;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Items {

    static int numbutton;
    // fonts and colors
    final static Color BlueColor = new Color(66, 114, 202);
    final static Color BlueSkyColor = new Color(136, 166, 221);
    final static Color blackColor = new Color(0, 0, 0);
    final static Color whiteColor = new Color(255, 255, 255);
    final static Color GrayColor = new Color(204, 204, 204);
    final static Font AgencyFB36 = new Font("Agency FB", Font.BOLD, 36);
    final static Font calibri24 = new Font("Calibri", Font.BOLD, 24);
    final static Font calibri20 = new Font("Calibri", Font.BOLD, 20);
    final static Font calibri16 = new Font("Calibri", Font.BOLD, 16);

    // Titles
    public JPanel CenterTitle(String title) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(680, 80));

        panel.setBackground(GrayColor);
        panel.add(BigTitle(title));
        return panel;
    }

    public JLabel BigTitle(String title) {

        JLabel Title = new JLabel(title);
        Title.setFont(AgencyFB36);
        Title.setForeground(blackColor);
        return Title;
    }

    public JLabel SmallTitle(String title) {
        JLabel smallTitle = new JLabel(title);
        smallTitle.setFont(calibri24);
        smallTitle.setPreferredSize(new Dimension(320, 50));
        smallTitle.setForeground(blackColor);

        return smallTitle;
    }

    // Boxes
    public JTextArea textArea(String str, Dimension dim) {
        JTextArea phrase = new JTextArea(str);
        phrase.setPreferredSize(dim);
        phrase.setBackground(GrayColor);
        phrase.setEditable(false);
        phrase.setFont(calibri16);
        phrase.setForeground(blackColor);
        return phrase;
    }

    public JTextField TextBox() {
        JTextField textBox = new JTextField();
        textBox.setFont(calibri20);
        textBox.setForeground(whiteColor);
        textBox.setBackground(blackColor);
        textBox.setPreferredSize(new Dimension(320, 50));
        return textBox;
    }

    public JPasswordField PasswordBox() {
        JPasswordField passwordBox = new JPasswordField();
        passwordBox.setFont(calibri20);
        passwordBox.setForeground(whiteColor);
        passwordBox.setBackground(blackColor);
        passwordBox.setPreferredSize(new Dimension(320, 50));
        return passwordBox;
    }

    //retourne la forme pour entrer la date
    public static JDatePickerImpl newDP() {
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePanel.setPreferredSize(new Dimension(320, 200));
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        datePicker.getComponent(0).setPreferredSize(new Dimension(300, 50)); // JFormattedTextField
        datePicker.getComponent(0).setBackground(blackColor);
        datePicker.getComponent(0).setForeground(whiteColor);
        datePicker.getComponent(0).setFont(calibri20);

        datePicker.getComponent(1).setPreferredSize(new Dimension(20, 50));// JButton
        datePicker.getComponent(1).setBackground(blackColor);
        datePicker.getComponent(1).setForeground(whiteColor);
        datePicker.getComponent(1).setFont(calibri20);
        datePicker.setPreferredSize(new Dimension(320, 50));

        return datePicker;
    }

    //retourne la forme pour entrer les heures
    public static TimePicker newTP() {
        TimePickerSettings timeSettings = new TimePickerSettings();

        timeSettings.setColor(TimeArea.TextFieldBackgroundValidTime, blackColor);
        timeSettings.setColor(TimeArea.TextFieldBackgroundInvalidTime, blackColor);
        timeSettings.setColor(TimeArea.TimePickerTextInvalidTime, Color.red);
        timeSettings.setColor(TimeArea.TimePickerTextValidTime, whiteColor);
        timeSettings.fontInvalidTime=calibri20;
        timeSettings.fontValidTime=calibri20;

        TimePicker   T_timePicker = new TimePicker(timeSettings);
        T_timePicker.getComponent(0).setPreferredSize(new Dimension(300,50));
        
		T_timePicker.getComponent(1).setBackground(blackColor);
        T_timePicker.getComponent(1).setForeground(whiteColor);
        T_timePicker.getComponent(1).setFont(calibri20);
        T_timePicker.getComponent(1).setPreferredSize(new Dimension(20,50));
        
        return T_timePicker;
    }
   
    //retourne la liste des choix
    public JComboBox<String> ChoiceBox(String choix[] , String name) {
        JComboBox<String> choiceBox = new JComboBox<String>(choix);

        choiceBox.setSelectedItem(null);
        choiceBox.setFont(calibri20);
        choiceBox.setForeground(whiteColor);
        choiceBox.setBackground(blackColor);
        choiceBox.setPreferredSize(new Dimension(320, 50));
        choiceBox.setName(name);
        choiceBox.getComponent(0).addMouseListener(new Actions(choix,choiceBox,name) );
        return choiceBox;
    }

    // Les Boutons
    public JButton NewButton(String name,Icon icon, String id, JTextField[] text, JComboBox<String>[] box, Color color, int width,
            int height) {

        JButton btn = new JButton(name);
        btn.setHorizontalTextPosition(SwingConstants.LEFT);  
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(width, height));
        btn.setFont(calibri20);
        btn.setForeground(whiteColor);
        btn.setBackground(color);
        btn.setName(id);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        btn.addActionListener(new Actions(id, text, box));
        btn.addMouseListener(new Actions(btn,icon));

        return btn;
    }

    // Les Panels
    //le menu principale au gauche de notre fenetre de notre programe
    public JPanel menuPrincipale(String id) {

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(GrayColor);
        menuPanel.setPreferredSize(new Dimension(200, 600));
        JLabel title = BigTitle("Menu");

        //initialisation des icons
        Icon ProfIcon = new ImageIcon(".\\..\\icons\\teacher.png");
        Icon ClassIcon = new ImageIcon(".\\..\\icons\\blackboard.png");
        Icon CoursIcon = new ImageIcon(".\\..\\icons\\cours.png");
        Icon ExamIcon = new ImageIcon(".\\..\\icons\\test.png");

        JButton profs = NewButton("Professeurs",ProfIcon,"Professeurs", null, null, blackColor, 200, 80);
        JButton salles = NewButton("Salles",ClassIcon, "Salles", null, null, blackColor, 200, 80);
        JButton modules = NewButton("Modules",CoursIcon, "Modules", null, null, blackColor, 200, 80);
        JButton exams = NewButton("Exams",ExamIcon, "Exams", null, null, blackColor, 200, 80);

        if (id.equals("p"))
            profs.setBackground(BlueColor);
        if (id.equals("s"))
            salles.setBackground(BlueColor);
        if (id.equals("m"))
            modules.setBackground(BlueColor);
        if (id.equals("e"))
            exams.setBackground(BlueColor);

        //ajouter les bottons dans le menu
        menuPanel.add(title);
        menuPanel.add(profs);
        menuPanel.add(salles);
        menuPanel.add(modules);
        menuPanel.add(exams);

        return menuPanel;

    }

    //la division de la partie droite de la fenetre de notre programe(definr les couleurs, les dimenssions)
    public JPanel PD() {
        JPanel panelDroite = new JPanel();
        panelDroite.setBackground(blackColor);
        panelDroite.setPreferredSize(new Dimension(800, 600));
        return panelDroite;
    }

    public JPanel PDH() {
        JPanel panelDroiteHaut = new JPanel();
        panelDroiteHaut.setPreferredSize(new Dimension(780, 100));
        panelDroiteHaut.setBackground(blackColor);
        return panelDroiteHaut;
    }

    public JPanel PDC(Color color) {
        JPanel panelDroiteCentre = new JPanel();
        panelDroiteCentre.setPreferredSize(new Dimension(770, 460));
        panelDroiteCentre.setBackground(color);
        return panelDroiteCentre;

    }

    public JPanel PDB() {
        JPanel panelDroiteBas = new JPanel();
        panelDroiteBas.setPreferredSize(new Dimension(780, 10));
        panelDroiteBas.setBackground(blackColor);
        return panelDroiteBas;
    }

    //retourne le menu pour ajouter et afficher (au haut de la page) 
    public JPanel divPDH(JPanel pdh, String id, Color AccColor, Color AjtColor) {
        JPanel menuPanelHaut = new JPanel();
        menuPanelHaut.setBackground(blackColor);
        Icon HomeIcon = new ImageIcon(".\\..\\icons\\Home.png");
        Icon AddIcon = new ImageIcon(".\\..\\icons\\plus.png");
        Icon LogOutIcon = new ImageIcon(".\\..\\icons\\logout.png");

        JButton homebtn = NewButton("Accueil",HomeIcon, "Acc" + id, null, null, AccColor, 200, 60);// *
        JButton add = NewButton("Ajouter",AddIcon, "Ajt" + id, null, null, AjtColor, 200, 60);// *
        JButton logout = NewButton("Log Out",LogOutIcon, "logout", null, null, BlueColor, 200, 60);

        pdh.setLayout(new BorderLayout());
        menuPanelHaut.add(homebtn);
        menuPanelHaut.add(add);
        menuPanelHaut.add(logout);
        pdh.add(menuPanelHaut, BorderLayout.EAST);
        return pdh;
    }
    //retourne le menu pour ajouter et afficher (au haut de la page) 
    public JPanel divPDH2(JPanel pdh, String id) {
        JPanel menuPanelHaut = new JPanel();
        menuPanelHaut.setBackground(blackColor);
        Icon LogOutIcon = new ImageIcon(".\\..\\icons\\logout.png");
        JButton logout = NewButton("Log Out",LogOutIcon, "logout", null, null, BlueColor, 200, 60);
        pdh.setLayout(new BorderLayout());
        menuPanelHaut.add(logout);
        pdh.add(menuPanelHaut, BorderLayout.EAST);
        return pdh;
    }

    //retourne la fenetre principale de notre programme
    public JPanel MainPanel(JPanel panelGauche, JPanel panelDroite) {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setSize(1000, 600);
        mainPanel.add(panelGauche, BorderLayout.WEST);
        mainPanel.add(panelDroite, BorderLayout.CENTER);
        return mainPanel;
    }
    
}