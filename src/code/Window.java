package code;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener,WindowListener{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	Settings settings;
	
	OptionFrame optionframe;
	
	MenuBar menubar;
	ContentPanel contentpanel;
	
	Popup popup;
	SystemTray tray;
	
	Image image;
	
	TrayIcon trayIcon;
	
	ArrayList<Integer> Groups;
	ArrayList<Group> Grouplist;
	
	public Window(Engine parEngine, Settings parSettings) {
		engine = parEngine;
		settings = parSettings;
		
		Groups = engine.GetGroups();
		Grouplist = engine.GetGrouplist();
		
		this.setSize(global.Width + global.WidthOffset, global.Height + global.HeightOffset);
		this.setTitle(global.WindowTitle);
		
		optionframe = new OptionFrame(engine);
		
		popup = new Popup(this);
		
		menubar = new MenuBar(this);
		contentpanel = new ContentPanel(this);
		
		image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/gfx/TrayiconMini.png"));		
		int trayIconWidth = new TrayIcon(image).getSize().width;
	    trayIcon = new TrayIcon(image.getScaledInstance(trayIconWidth, -1, Image.SCALE_SMOOTH), "PCManager", popup);
		
	    trayIcon.setPopupMenu(popup);
		
		tray = SystemTray.getSystemTray();

		this.setIconImage(image);
		
		this.setJMenuBar(menubar);
		
		this.setContentPane(contentpanel);
		
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(global.IsResizable);

		this.setLocationRelativeTo(null);
		this.centerFrame();
		
		Maximize();
		Minimize();
		
		this.addWindowListener(this);
		
	}
	
	public void init() {
		this.add(popup);
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public void Maximize() {
		this.setState(JFrame.NORMAL);
		this.setVisible(true);
	}
	
	public void Minimize() {
		this.setState(JFrame.ICONIFIED);
		this.setVisible(false);
	}
	
	public void ShowOptions() {
		optionframe.Maximize();
	}
	
	public void SetServer(ArrayList<Integer> parGroup, ArrayList<Group> parGrouplist) {
		Groups = parGroup;
		Grouplist = parGrouplist;
		
		contentpanel.SetServer(Groups,Grouplist);
		popup.SetServer(Groups, Grouplist);
	}
	
    private void centerFrame() {

        Dimension windowSize = this.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        this.setLocation(dx, dy);
    }
    
	@Override
	public void actionPerformed(ActionEvent ae) {

		
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
