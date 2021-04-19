import java.awt.*;
import javax.swing.*;
public class Main extends pages {

	ConnectionDb cnt = new ConnectionDb();
	public static JFrame frame = new JFrame();// PARENT FRAME

	static CardLayout c = new CardLayout();
	static JPanel parentPanel = new JPanel();

	static CardLayout EditC = new CardLayout();
	static JPanel EditparentPanel = new JPanel();

	public static void main(String[] args) throws Exception {
		Main m = new Main();	
		
		parentPanel.setLayout(c);
		EditparentPanel.setLayout(EditC);
		parentPanel.add(m.FirstInter(), "FirstInter");
		parentPanel.add(m.AdminHome(), "AdminHome");
		parentPanel.add(m.StudentLogin(), "StudentLogin"); aji morah
		parentPanel.add(m.ProfessorLogin(), "ProfLogin");
		parentPanel.add(m.ExamsHome(), "ExamHome");
		parentPanel.add(m.ProfsHome(), "ProfHome");
		parentPanel.add(m.ClassesHome(), "ClassHome");
		parentPanel.add(m.ModulesHome(), "ModuleHome");	
		parentPanel.add(m.AjouterProf(), "ProfAdd");	
		parentPanel.add(m.AjouterClasse(), "ClassAdd");
		parentPanel.add(m.AjouterModule(), "ModuleAdd");
		parentPanel.add(m.AjouterExam(), "ExamAdd");
		parentPanel.add(m.AffectationSallesProfs(), "ExamAff");	

		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		Main.frame.setLayout(new BorderLayout());
		Main.frame.add(parentPanel);
		Main.frame.setVisible(true);
		Main.frame.setResizable(false);
		Main.frame.setTitle("Gestion Des Exams");
		Main.frame.pack();
	}

}