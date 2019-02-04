package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OptionFrame extends JFrame implements ActionListener, WindowListener{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	Settings settings;
	
	JPanel panelContent;
	JPanel panelButtons;
	
	JLabel labelGrouplist;
	JLabel labelServerlist;
	JLabel labelProfilelist;
	JLabel labelLogfile;
	JLabel labelStandardprofile;
	
	JTextField textfieldGrouplist;
	JTextField textfieldServerlist;
	JTextField textfieldProfilelist;
	JTextField textfieldLogfile;
	JComboBox<String> comboStandardProfile;
	JButton buttonNewProfile;
	JButton buttonDeleteProfile;
	
	JButton buttonSave;
	JButton buttonDefault;
	JButton buttonCancel;
	
	ArrayList<Profile> Profiles;
	
	public OptionFrame(Engine parEngine) {
		engine = parEngine;
		settings = engine.getSettings();
		
		this.setTitle("Optionen");
		this.setSize((((global.Width) / 3 ) * 2) + global.WidthOffset, ((global.Height)/2) + global.HeightOffset);
		this.setResizable(global.IsResizable);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		Init();
		
		Maximize();
		Minimize();
	}
	
	public void Init() {
		panelContent = new JPanel();
		panelButtons = new JPanel();
		
		labelGrouplist = new JLabel("Gruppen");
		labelServerlist = new JLabel("Server");
		labelProfilelist = new JLabel("Profile");
		labelLogfile = new JLabel("Log");
		labelStandardprofile = new JLabel("Standardprofil");
		
		textfieldGrouplist = new JTextField();
		textfieldServerlist = new JTextField();
		textfieldProfilelist = new JTextField();
		textfieldLogfile = new JTextField();
		comboStandardProfile = new JComboBox<String>();
		
		buttonNewProfile = new JButton("Neu");
		buttonDeleteProfile = new JButton("Löschen");
		
		panelContent.setLayout(null);
		panelContent.setBounds(0,0,(global.Width / 3) * 2, ((global.Height / 2) / 5) * 4); 
		
		panelButtons.setLayout(null);
		panelButtons.setBounds(0,((global.Height / 2) / 5) * 4, this.getWidth(), ((global.Height / 2) / 5) * 1);
		
		int buttonWidth = (panelButtons.getWidth() / 3) / 2;
		int buttonHeight = (panelButtons.getHeight() / 3) * 2;
		
		int ContentHeight = (panelContent.getHeight() / 10) * 1;
		
		labelGrouplist.setBounds(0,ContentHeight * 0,panelContent.getWidth() / 2, ContentHeight);
		labelServerlist.setBounds(0,ContentHeight * 1,panelContent.getWidth() / 2, ContentHeight);
		labelProfilelist.setBounds(0,ContentHeight * 2,panelContent.getWidth() / 2, ContentHeight);
		labelLogfile.setBounds(0,ContentHeight * 3,panelContent.getWidth() / 2, ContentHeight);
		labelStandardprofile.setBounds(0,ContentHeight * 4,panelContent.getWidth() / 2, ContentHeight);
		
		textfieldGrouplist.setBounds(panelContent.getWidth() / 2, ContentHeight * 0, panelContent.getWidth() / 2, ContentHeight);
		textfieldServerlist.setBounds(panelContent.getWidth() / 2, ContentHeight * 1, panelContent.getWidth() / 2, ContentHeight);
		textfieldProfilelist.setBounds(panelContent.getWidth() / 2, ContentHeight * 2, panelContent.getWidth() / 2, ContentHeight);
		textfieldLogfile.setBounds(panelContent.getWidth() / 2, ContentHeight * 3, panelContent.getWidth() / 2, ContentHeight);
		comboStandardProfile.setBounds(panelContent.getWidth() / 2, ContentHeight * 4, panelContent.getWidth() / 2, ContentHeight);
		
		buttonNewProfile.setBounds((panelContent.getWidth() / 4) * 2, ContentHeight * 5, panelContent.getWidth() / 4, ContentHeight);
		buttonDeleteProfile.setBounds((panelContent.getWidth() / 4) * 3, ContentHeight * 6, panelContent.getWidth() / 4, ContentHeight);
		
		buttonNewProfile.addActionListener(this);
		buttonDeleteProfile.addActionListener(this);
		
		buttonSave = new JButton("Speichern");
		buttonDefault = new JButton("Standard");
		buttonCancel = new JButton("Abbrechen");
		
		buttonSave.setBounds(((panelButtons.getWidth() / 3) / 2) - buttonWidth / 2, (panelButtons.getHeight() / 2) - buttonHeight / 2, buttonWidth, buttonHeight);
		buttonDefault.setBounds((panelButtons.getWidth() / 3) * 2 - ((panelButtons.getWidth() / 3) / 2) - buttonWidth / 2, (panelButtons.getHeight() / 2) - buttonHeight / 2, buttonWidth, buttonHeight);
		buttonCancel.setBounds((panelButtons.getWidth() / 3) * 3 - ((panelButtons.getWidth() / 3) / 2) - buttonWidth / 2, (panelButtons.getHeight() / 2) - buttonHeight / 2, buttonWidth, buttonHeight);
		
		buttonSave.addActionListener(this);
		buttonDefault.addActionListener(this);
		buttonCancel.addActionListener(this);
		
		panelContent.add(labelGrouplist);

		panelContent.add(labelGrouplist);
		panelContent.add(labelServerlist);
		panelContent.add(labelProfilelist);
		panelContent.add(labelLogfile);
		panelContent.add(labelStandardprofile);
		
		panelContent.add(textfieldGrouplist);
		panelContent.add(textfieldServerlist);
		panelContent.add(textfieldProfilelist);
		panelContent.add(textfieldLogfile);
		panelContent.add(comboStandardProfile);
		
		panelContent.add(buttonNewProfile);
		panelContent.add(buttonDeleteProfile);
		
		panelButtons.add(buttonSave);
		panelButtons.add(buttonDefault);
		panelButtons.add(buttonCancel);
		
		this.getContentPane().setLayout(null);
		this.getContentPane().add(panelContent);
		this.getContentPane().add(panelButtons);
		
		Load();
	}
	
	public void Load() {
		Profiles = engine.GetProfileList();
		
		textfieldGrouplist.setText(settings.Get("Grouplist"));
		textfieldServerlist.setText(settings.Get("Serverlist"));
		textfieldProfilelist.setText(settings.Get("Profilelist"));
		textfieldLogfile.setText(settings.Get("Logfile"));
		
		comboStandardProfile.removeAll();
		for(int p = 0; p < Profiles.size(); p++) {
			comboStandardProfile.addItem(Profiles.get(p).getCaption());
		}
		
		if(settings.Get("Standardprofile").equals(global.StandardStr)) {
			comboStandardProfile.setSelectedIndex(0);
		}else if(Integer.parseInt(settings.Get("Standardprofile")) <= Profiles.size()) {
			comboStandardProfile.setSelectedIndex(Integer.parseInt(settings.Get("Standardprofile")));
		}
	}
	
	public void SaveOptions() {
		settings.Set("Grouplist", textfieldGrouplist.getText());
		settings.Set("Serverlist", textfieldServerlist.getText());
		settings.Set("Profilelist", textfieldProfilelist.getText());
		settings.Set("Logfile", textfieldLogfile.getText());
		settings.Set("Standardprofile", String.valueOf(Profiles.get(comboStandardProfile.getSelectedIndex()).getID()));
		
		settings.SaveSettings();
	}
	
	public void Maximize() {
		this.setState(JFrame.NORMAL);
		this.setVisible(true);
	}
	
	public void Minimize() {
		this.setState(JFrame.ICONIFIED);
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(buttonSave)) {
			if(engine.confirm("Änderungen speichern?")){
				SaveOptions();
			}
		}else if(ae.getSource().equals(buttonDefault)) {
			if(engine.confirm("Zurücksetzen?")) {
				settings.RestoreDefaults();
				Load();
			}
		}else if(ae.getSource().equals(buttonCancel)) {
			Load();
			Minimize();
		}else if(ae.getSource().equals(buttonNewProfile)) {
			String tmpname = engine.input("Wie soll das Profil heißen?");
				if(tmpname != "" && tmpname != null) {
					engine.CreateProfile(tmpname);
				}
				
		}else if(ae.getSource().equals(buttonDeleteProfile)) {
			
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		Minimize();		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
