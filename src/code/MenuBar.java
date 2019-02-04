package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	Window window;
	Engine engine;
	Settings settings;
	
	JMenu menuFile;
	JMenu menuHelp;
	
	JMenu menuProfile;
	
	JMenuItem miExit;
	JMenuItem miHelp;
	JMenuItem miOptions;
	JMenuItem miClearLog;
	
	ArrayList<JMenuItem> miProfiles;
	
	public MenuBar(Window parWindow) {
		window = parWindow;
		engine = window.getEngine();
		settings = engine.getSettings();
		
		InitMenus();
	}
	
	private void InitMenus() {
		loadProfiles();
		miExit = new JMenuItem("Beenden");
		miExit.addActionListener(this);
		
		miHelp = new JMenuItem("Hilfe");
		
		miOptions = new JMenuItem("Optionen");
		miOptions.addActionListener(this);
		
		miClearLog = new JMenuItem("Log löschen");
		miClearLog.addActionListener(this);
		
		menuFile = new JMenu("Datei");
		menuHelp = new JMenu("Hilfe");
		menuProfile = new JMenu("Profile");
				
		for(int x = 0; x < miProfiles.size(); x++) {
			menuProfile.add(miProfiles.get(x));
		}
		
		menuFile.add(miOptions);
		menuFile.add(menuProfile);
		menuFile.add(miClearLog);
		menuFile.addSeparator();
		menuFile.add(miExit);
		
		menuHelp.add(miHelp);
		
		this.add(menuFile);
		this.add(menuHelp);
	}
	
	private void loadProfiles() {
		miProfiles = new ArrayList<JMenuItem>();
		
		for(int p = 0; p < engine.GetProfileList().size(); p++) {
			JMenuItem mitmp = new JMenuItem(engine.GetProfileList().get(p).getCaption());
			mitmp.setActionCommand(String.valueOf(engine.GetProfileList().get(p).getID()));
			mitmp.addActionListener(this);
			miProfiles.add(mitmp);
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		engine.log(ae.getSource());
		
		if(ae.getSource().equals(miExit)) {
			if(engine.confirm("Möchten Sie das Programm beenden?")) {
				System.exit(0);
			}
		}else if(ae.getSource().equals(miHelp)) {
			
		}else if(ae.getSource().equals(miOptions)){
			window.ShowOptions();
		} else if(ae.getSource().equals(miClearLog)){
			engine.ClearLog();
		} else {
			engine.SetCurrentProfile(Integer.parseInt(ae.getActionCommand()));
		}
	}

}
