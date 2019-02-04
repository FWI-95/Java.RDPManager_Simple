package code;

import javax.swing.JOptionPane;

public class GUI {
	
	public GUI() {
		
	}
	
	public void message(String msg) {
		JOptionPane.showMessageDialog(null, 
										msg, 
										"Info", 
										JOptionPane.INFORMATION_MESSAGE);
	}
	
	public boolean confirm(String qst) {
		return (JOptionPane.showConfirmDialog(null, 
												qst, 
												"Bestätigen",
												JOptionPane.YES_NO_OPTION,
												JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION);
	}
	
	public String input(String qst) {
		return (String)JOptionPane.showInputDialog(
                null,
                qst,
                "Bitte eingeben.",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
	}

}
