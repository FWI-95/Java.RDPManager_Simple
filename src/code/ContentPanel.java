package code;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ContentPanel extends JPanel implements ActionListener, ListDataListener, ListSelectionListener{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	Window window;
	Settings settings;
	
	JPanel panelGroup;
	JPanel panelServer;
	JPanel panelInfo;
	JPanel panelInfoMandatory;
	JPanel panelInfoExtra;
	
	JScrollPane scrollGroup;
	JScrollPane scrollServer;
	JScrollPane scrollInfo;
	
	JList<String> listGroup;
	JList<String> listServer;
	
	DefaultListModel<String> lmodelGroup;
	DefaultListModel<String> lmodelServer;
	
	JButton buttonSaveServer;
	JButton buttonCancelServer;
	JButton buttonConnectServer;
	
	JButton buttonNewGroup;
	JButton buttonDeleteGroup;
	
	JButton buttonNewServer;
	JButton buttonDeleteServer;
	
	JLabel labelID;
	JLabel labelURL;
	JLabel labelPort;
	JLabel labelCaption;
	JLabel labelGroup;
	JLabel labelVPN;
	JLabel labelUser;
	JLabel labelFullscreen;
	JLabel labelMultimon;
	JLabel labelAdmin;
	JLabel labelPublic;
	JLabel labelWidth;
	JLabel labelHeight;
	JLabel labelSpan;
	
	JTextField textfieldID;
	JTextField textfieldURL;
	JTextField textfieldPort;
	JTextField textfieldCaption;
	JComboBox<String> comboGroup;
	JTextField textfieldVPN;
	JTextField textfieldUser;
	JTextField textfieldWidth;
	JTextField textfieldHeight;
	JCheckBox checkboxFullscreen;
	JCheckBox checkboxMultimon;
	JCheckBox checkboxAdmin;
	JCheckBox checkboxPublic;
	JCheckBox checkboxSpan;
	
	ArrayList<Integer> Groups;
	ArrayList<Group> Grouplist;
	
	ArrayList<Server> CurrentServerList;
	
	Group CurrentGroup;
	Server CurrentServer;
	
	public ContentPanel(Window parWindow) {
		window = parWindow;
		engine = window.getEngine();
		settings = engine.getSettings();
		
		Groups = engine.GetGroups();
		Grouplist = engine.GetGrouplist();
		
		this.setLayout(null);
		this.setBounds(0, 0, global.Width, global.Height);
		
		InitGroupComponents();
		InitServerComponents();
		InitInfoComponents();			
		
//		this.repaint();

	}
	
	public void SetServer(ArrayList<Integer> parGroups, ArrayList<Group> parGrouplist) {
		Groups = parGroups;
		Grouplist = parGrouplist;
		
		UpdateGroups();
		UpdateServer();
		UpdateInfo();
	}
	

	
	public void NewGroup() {
		String name = "";
		name = engine.input("Wie soll die Gruppe heißen?");
		if(name != "" && name != null) {
			engine.addGroup(name);
		}
		UpdateGroups();
	}
	
	public void DeleteGroup() {
		engine.deleteGroup(listGroup.getSelectedValue());
	}
	
	public void NewServer() {
		if(CurrentGroup != null) {
			int tmpSelGroup = listGroup.getSelectedIndex();
			int tmpSelServ = listServer.getSelectedIndex();
			
			engine.log("Gruppe: " + CurrentGroup.GetID() + " - " + CurrentGroup.GetCaption());
			
			
			Server tmpServer = new Server(engine,
					engine.GetNextServerID(),
					"",
					"Neuer Server",
					CurrentGroup.GetID(),
					"",
					"",
					false,
					false,
					false,
					false,
					0,
					0,
					false); 
			engine.log("Group for Server " + tmpServer.getID() + ": " + tmpServer.getGroupID());
			engine.AddServer(tmpServer);
			
			listGroup.setSelectedIndex(tmpSelGroup);
			listServer.setSelectedIndex(tmpSelServ);
		}else {
			engine.log("Bitte wählen Sie eine Gruppe");
		}
	}
	
	public void DeleteServer() {
		
	}
	
	public void SaveServer() {
		if(CurrentServer != null) {

			
			if(engine.confirm("Möchten Sie die Änderungen speichern?")){
				int tmpSelGroup = listGroup.getSelectedIndex();
				int tmpSelServ = listServer.getSelectedIndex();
				
				CurrentServer.setURL(textfieldURL.getText());
				CurrentServer.setPort(Integer.valueOf(textfieldPort.getText()));
				CurrentServer.setCaption(textfieldCaption.getText());
				CurrentServer.SetGroup(engine.GetGroupFromID(Groups.get(comboGroup.getSelectedIndex())));
				CurrentServer.setVPN(textfieldVPN.getText());
				CurrentServer.setUser(textfieldUser.getText());
				CurrentServer.setWidth(Integer.valueOf(textfieldWidth.getText()));
				CurrentServer.setHeight(Integer.valueOf(textfieldHeight.getText()));
				CurrentServer.setFullscreen(checkboxFullscreen.isSelected());
				CurrentServer.setMultimon(checkboxMultimon.isSelected());
				CurrentServer.setAdmin(checkboxAdmin.isSelected());
				CurrentServer.setPublic(checkboxPublic.isSelected());
				CurrentServer.setSpan(checkboxSpan.isSelected());
				
				if(!engine.SaveServer(CurrentServer)) {
					engine.message("Ein Fehler ist aufgetreten.");
				}else {
					CurrentServer.SetUnsavedChanges(false);
				}
				
				listGroup.setSelectedIndex(tmpSelGroup);
				if (listServer.getModel().getSize() <= tmpSelServ) {
						listServer.setSelectedIndex(tmpSelServ);
				}
				
	//			engine.message("Gesichert");
			}
		}
	}
	
	public void CancelServer() {
		if(engine.confirm("Änderungen rückgängig machen?")) {
			UpdateInfo();
//			engine.message("Zurückgesetzt.");
		}
	}
	
	public void UpdateGroups() {
		listGroup.removeListSelectionListener(this);
		lmodelGroup.removeAllElements();
		
		for(int x = 0; x < Grouplist.size();x++) {
			lmodelGroup.addElement(Grouplist.get(x).GetCaption());
			engine.log("Group " + Grouplist.get(x).GetCaption());
		}
		
		for(Group gr : Grouplist) {
			engine.log("Cur: " + gr.GetCaption());
		}
		
//		engine.log(lmodelGroup.firstElement());
		
		listGroup.setModel(lmodelGroup);
		
		if(lmodelGroup.size() > 0) {
		
			if(listGroup.isSelectionEmpty()) {
				listGroup.setSelectedIndex(0);
			}
			
			CurrentGroup = Grouplist.get(listGroup.getSelectedIndex());
		} else {
			CurrentGroup = null;
		}
		listGroup.addListSelectionListener(this);
		
	}
	
	public void UpdateServer() {
		lmodelServer.removeAllElements();
		
		if(CurrentGroup != null) {
			CurrentServerList = CurrentGroup.getListAsArrayList();
		
			if(CurrentServerList.size() > 0) {
				
				listServer.removeListSelectionListener(this);
				
				for(int x = 0; x < CurrentServerList.size(); x++) {
					engine.log(CurrentServerList.get(x).getCaption());
					lmodelServer.addElement(CurrentServerList.get(x).getCaption());
				}
	
				listServer.setModel(lmodelServer);
				if(listServer.isSelectionEmpty()) {
					listServer.setSelectedIndex(0);
				}
				
				engine.log(listServer.getSelectedIndex());
				CurrentServer = CurrentServerList.get(listServer.getSelectedIndex());
				listServer.addListSelectionListener(this);
			}else {
				engine.log("Keine Server für Gruppe: " + CurrentGroup.GetCaption());
				CurrentServer = null;
			}
		} else {
			engine.log("Keine Gruppe ausgewählt");
		}
	}
	
	public void UpdateInfo() {
		if(CurrentServer != null) {
			ShowServer(CurrentServer);
		}else {
			BlockFields(false);
		}
	}
	
	public void ShowServer(Server server) {
		BlockFields(true);
		textfieldID.setText(String.valueOf(server.getID()));
		textfieldURL.setText(server.getURL());
		textfieldPort.setText(String.valueOf(server.getPort()));
		textfieldCaption.setText(server.getCaption());
		comboGroup.setSelectedIndex(Groups.indexOf(server.getGroupID()));
		textfieldVPN.setText(server.getVPN());
		textfieldUser.setText(server.getUser());
		textfieldWidth.setText(String.valueOf(server.getWidth()));
		textfieldHeight.setText(String.valueOf(server.getHeight()));
		checkboxFullscreen.setSelected(server.isFullscreen());
		checkboxMultimon.setSelected(server.isMultimon());
		checkboxAdmin.setSelected(server.isAdmin());
		checkboxPublic.setSelected(server.isPublic());
		checkboxSpan.setSelected(server.isSpan());
	}
	
	private void BlockFields(boolean Editable) {
		textfieldID.setText("");
		textfieldURL.setText("");
		textfieldPort.setText("");
		textfieldCaption.setText("");
		comboGroup.setSelectedItem(null);
		textfieldVPN.setText("");
		textfieldUser.setText("");
		textfieldWidth.setText("");
		textfieldHeight.setText("");
		checkboxFullscreen.setSelected(false);
		checkboxMultimon.setSelected(false);
		checkboxAdmin.setSelected(false);
		checkboxPublic.setSelected(false);
		checkboxSpan.setSelected(false);
		
		textfieldID.setEditable(Editable);
		textfieldURL.setEditable(Editable);
		textfieldPort.setEditable(Editable);
		textfieldCaption.setEditable(Editable);
		comboGroup.setEditable(Editable);
		textfieldVPN.setEditable(Editable);
		textfieldUser.setEditable(Editable);
		textfieldWidth.setEditable(Editable);
		textfieldHeight.setEditable(Editable);
		checkboxFullscreen.setEnabled(Editable);
		checkboxMultimon.setEnabled(Editable);
		checkboxAdmin.setEnabled(Editable);
		checkboxPublic.setEnabled(Editable);
		checkboxSpan.setEnabled(Editable);
	}
	
	private void InitGroupComponents() {
		panelGroup = new JPanel();
		
		buttonNewGroup = new JButton("Neu");
		buttonNewGroup.setActionCommand("Group;New");
		buttonNewGroup.addActionListener(this);
		
		buttonDeleteGroup = new JButton("Löschen");
		buttonDeleteGroup.setActionCommand("Group;Delete");
		buttonDeleteGroup.addActionListener(this);
		
		panelGroup.setLayout(null);
		panelGroup.setBounds(0, 0, this.getWidth() / 4, this.getHeight());
		
		buttonNewGroup.setBounds(0,(panelGroup.getHeight() / 10) * 9, panelGroup.getWidth() / 2, (panelGroup.getHeight() / 10) * 1); 
		buttonDeleteGroup.setBounds(panelGroup.getWidth() / 2,(panelGroup.getHeight() / 10) * 9, panelGroup.getWidth() / 2, (panelGroup.getHeight() / 10) * 1);
		
		lmodelGroup = new DefaultListModel<>();
		
		listGroup = new JList<String>();
		listGroup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listGroup.setBounds(0,0,panelGroup.getWidth(),(panelGroup.getHeight() / 10) * 9);
		listGroup.setBorder(BorderFactory.createLineBorder(Color.black));
		
		UpdateGroups();
		
		scrollGroup = new JScrollPane(listGroup);
//		scrollGroup.setViewportView(listGroup);
		scrollGroup.setBounds(0,0,panelGroup.getWidth(), (panelGroup.getHeight() / 10) * 9);
		
		panelGroup.add(scrollGroup);
		panelGroup.add(buttonNewGroup);
		panelGroup.add(buttonDeleteGroup);
		
		this.add(panelGroup);

	}
	
	private void InitServerComponents() {
		panelServer = new JPanel();
		
		buttonNewServer = new JButton("Neu");
		buttonNewServer.setActionCommand("Server;New");
		buttonNewServer.addActionListener(this);
		
		buttonDeleteServer = new JButton("Löschen");
		buttonDeleteServer.setActionCommand("Server;Delete");
		buttonDeleteServer.addActionListener(this);
		
		panelServer.setLayout(null);
		
		panelServer.setBounds(this.getWidth() / 4, 0, this.getWidth() / 4, this.getHeight());
		
		buttonNewServer.setBounds(0,(panelServer.getHeight() / 10) * 9, panelServer.getWidth() / 2, (panelServer.getHeight() / 10) * 1); 
		buttonDeleteServer.setBounds(panelServer.getWidth() / 2,(panelServer.getHeight() / 10) * 9, panelServer.getWidth() / 2, (panelServer.getHeight() / 10) * 1);
		
		listServer = new JList<String>();
		
		lmodelServer = new DefaultListModel<String>();
		
		listServer = new JList<String>();
		listServer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listServer.setBounds(0,0,panelServer.getWidth(),(panelServer.getHeight() / 10) * 9);
		listServer.setBorder(BorderFactory.createLineBorder(Color.black));
		
		UpdateServer();
		
		scrollServer = new JScrollPane(listServer);
//		scrollServer.setViewportView(listServer);
		scrollServer.setBounds(0,0,panelServer.getWidth(), (panelServer.getHeight() / 10) * 9);

		panelServer.add(scrollServer);
		panelServer.add(buttonNewServer);
		panelServer.add(buttonDeleteServer);
		
		this.add(panelServer);
	}
	
	private void InitInfoComponents() {
		String[] Groupbox = engine.GatherGroups();
		
		panelInfo = new JPanel();
		
		panelInfoMandatory = new JPanel();
		panelInfoExtra = new JPanel();
		
		scrollInfo = new JScrollPane();
		
		labelID = new JLabel("ID");
		labelURL = new JLabel("URL");
		labelPort = new JLabel("Port");
		labelCaption = new JLabel("Name");
		labelGroup = new JLabel("Gruppe");
		labelVPN = new JLabel("VPN");
		labelUser = new JLabel("Benutzer");
		labelFullscreen = new JLabel("Vollbild");
		labelMultimon = new JLabel("MultiMonitor");
		labelAdmin = new JLabel("Administrator");
		labelPublic = new JLabel("Öffentlich");
		labelWidth = new JLabel("Breite");
		labelHeight = new JLabel("Höhe");
		labelSpan = new JLabel("Monitor-Übergreifend");
		
		textfieldID = new JTextField();
		textfieldURL = new JTextField();
		textfieldPort = new JTextField();
		textfieldCaption = new JTextField();
		comboGroup = new JComboBox<String>(Groupbox);
		textfieldVPN = new JTextField();
		textfieldUser = new JTextField();
		textfieldWidth = new JTextField();
		textfieldHeight = new JTextField();
		checkboxFullscreen = new JCheckBox();
		checkboxMultimon = new JCheckBox();
		checkboxAdmin = new JCheckBox();
		checkboxPublic = new JCheckBox();
		checkboxSpan = new JCheckBox();
		
		textfieldID.addActionListener(this);
		textfieldURL.addActionListener(this);
		textfieldPort.addActionListener(this);
		textfieldCaption.addActionListener(this);
		comboGroup.addActionListener(this);
		textfieldVPN.addActionListener(this);
		textfieldUser.addActionListener(this);
		textfieldWidth.addActionListener(this);
		textfieldHeight.addActionListener(this);
		checkboxFullscreen.addActionListener(this);
		checkboxMultimon.addActionListener(this);
		checkboxAdmin.addActionListener(this);
		checkboxPublic.addActionListener(this);
		checkboxSpan.addActionListener(this);
		
		textfieldID.setActionCommand("Info;Changed");
		textfieldURL.setActionCommand("Info;Changed");
		textfieldPort.setActionCommand("Info;Changed");
		textfieldCaption.setActionCommand("Info;Changed");
		comboGroup.setActionCommand("Info;Changed");
		textfieldVPN.setActionCommand("Info;Changed");
		textfieldUser.setActionCommand("Info;Changed");
		textfieldWidth.setActionCommand("Info;Changed");
		textfieldHeight.setActionCommand("Info;Changed");
		checkboxFullscreen.setActionCommand("Info;Changed");
		checkboxMultimon.setActionCommand("Info;Changed");
		checkboxAdmin.setActionCommand("Info;Changed");
		checkboxPublic.setActionCommand("Info;Changed");
		checkboxSpan.setActionCommand("Info;Changed");
		
		buttonSaveServer = new JButton("Speichern");
		buttonSaveServer.setActionCommand("Info;Save");
		buttonSaveServer.addActionListener(this);
		
		buttonCancelServer = new JButton("Abbrechen");
		buttonCancelServer.setActionCommand("Info;Cancel");
		buttonCancelServer.addActionListener(this);
		
		buttonConnectServer = new JButton("Verbinden");
		buttonConnectServer.setActionCommand("Info;Connect");
		buttonConnectServer.addActionListener(this);
		
		panelInfo.setLayout(null);
		panelInfoMandatory.setLayout(null);
		panelInfoExtra.setLayout(null);
		
		int hgt = 40;
		int offset = 5;
		
		panelInfo.setBounds((this.getWidth() / 4) * 2, 0, (this.getWidth() / 4) * 2, this.getHeight());
		
		panelInfoMandatory.setBounds(0,0,panelInfo.getWidth(),panelInfo.getHeight() / 3);
		scrollInfo.setBounds(0,panelInfo.getHeight() / 3, panelInfo.getWidth(),(panelInfo.getHeight() / 3) * 2);
		panelInfoExtra.setBounds(0,0,scrollInfo.getWidth(), hgt * 9);		
		
		labelID.setBounds(offset, (panelInfoMandatory.getHeight() / 5) * 0, panelInfoMandatory.getWidth() / 3, hgt);
		labelURL.setBounds(offset, (panelInfoMandatory.getHeight() / 5) * 1, panelInfoMandatory.getWidth() / 3, hgt);
		labelCaption.setBounds(offset, (panelInfoMandatory.getHeight() / 5) * 2, panelInfoMandatory.getWidth() / 3, hgt);
		labelGroup.setBounds(offset, (panelInfoMandatory.getHeight() / 5) * 3, panelInfoMandatory.getWidth() / 3, hgt);
		
		textfieldID.setBounds(panelInfoMandatory.getWidth() / 3, (panelInfoMandatory.getHeight() / 5) * 0, (panelInfoMandatory.getWidth() / 3) * 2, hgt);
		textfieldURL.setBounds(panelInfoMandatory.getWidth() / 3, (panelInfoMandatory.getHeight() / 5) * 1, (panelInfoMandatory.getWidth() / 3) * 2, hgt);
		textfieldCaption.setBounds(panelInfoMandatory.getWidth() / 3, (panelInfoMandatory.getHeight() / 5) * 2, (panelInfoMandatory.getWidth() / 3) * 2, hgt);
		comboGroup.setBounds(panelInfoMandatory.getWidth() / 3, (panelInfoMandatory.getHeight() / 5) * 3, (panelInfoMandatory.getWidth() / 3) * 2, hgt);
		
		buttonSaveServer.setBounds((panelInfoMandatory.getWidth() / 3) * 0, (panelInfoMandatory.getHeight() / 5) * 4, panelInfoMandatory.getWidth() / 3, hgt);
		buttonConnectServer.setBounds((panelInfoMandatory.getWidth() / 3) * 1, (panelInfoMandatory.getHeight() / 5) * 4, panelInfoMandatory.getWidth() / 3, hgt);
		buttonCancelServer.setBounds((panelInfoMandatory.getWidth() / 3) * 2, (panelInfoMandatory.getHeight() / 5) * 4, panelInfoMandatory.getWidth() / 3, hgt);
		
		labelPort.setBounds(offset, hgt * 0, panelInfoExtra.getWidth() / 3, hgt);
		labelVPN.setBounds(offset, hgt * 1, panelInfoExtra.getWidth() / 3, hgt);
		labelUser.setBounds(offset, hgt * 2, panelInfoExtra.getWidth() / 3, hgt);
		labelFullscreen.setBounds(offset, hgt * 3, panelInfoExtra.getWidth() / 3, hgt);
		labelMultimon.setBounds(offset, hgt * 4, panelInfoExtra.getWidth() / 3, hgt);
		labelSpan.setBounds(offset, hgt * 5, panelInfoExtra.getWidth() / 3, hgt);
		labelWidth.setBounds(offset, hgt * 6, panelInfoExtra.getWidth() / 3, hgt);
		labelHeight.setBounds(offset, hgt * 7, panelInfoExtra.getWidth() / 3, hgt);
		labelAdmin.setBounds(offset, hgt * 8, panelInfoExtra.getWidth() / 3, hgt);
		labelPublic.setBounds(offset, hgt * 9, panelInfoExtra.getWidth() / 3, hgt);
		
		textfieldPort.setBounds(panelInfoExtra.getWidth() / 3, hgt * 0, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		textfieldVPN.setBounds(panelInfoExtra.getWidth() / 3, hgt * 1, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		textfieldUser.setBounds(panelInfoExtra.getWidth() / 3, hgt * 2, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		checkboxFullscreen.setBounds(panelInfoExtra.getWidth() / 3, hgt * 3, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		checkboxMultimon.setBounds(panelInfoExtra.getWidth() / 3, hgt * 4, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		checkboxSpan.setBounds(panelInfoExtra.getWidth() / 3, hgt * 5, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		textfieldWidth.setBounds(panelInfoExtra.getWidth() / 3, hgt * 6, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		textfieldHeight.setBounds(panelInfoExtra.getWidth() / 3, hgt * 7, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		checkboxAdmin.setBounds(panelInfoExtra.getWidth() / 3, hgt * 8, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		checkboxPublic.setBounds(panelInfoExtra.getWidth() / 3, hgt * 9, (panelInfoExtra.getWidth() / 3) * 2, hgt);
		
		panelInfoMandatory.add(labelID);
		panelInfoMandatory.add(labelURL);
		panelInfoMandatory.add(labelCaption);
		panelInfoMandatory.add(labelGroup);
		panelInfoMandatory.add(textfieldID);
		panelInfoMandatory.add(textfieldURL);
		panelInfoMandatory.add(textfieldCaption);
		panelInfoMandatory.add(comboGroup);
		panelInfoMandatory.add(buttonSaveServer);
		panelInfoMandatory.add(buttonConnectServer);
		panelInfoMandatory.add(buttonCancelServer);
		
		panelInfoExtra.add(labelPort);
		panelInfoExtra.add(textfieldPort);
		panelInfoExtra.add(labelUser);
		panelInfoExtra.add(textfieldUser);
		panelInfoExtra.add(labelVPN);
		panelInfoExtra.add(textfieldVPN);
		panelInfoExtra.add(labelFullscreen);
		panelInfoExtra.add(checkboxFullscreen);
		panelInfoExtra.add(labelMultimon);
		panelInfoExtra.add(checkboxMultimon);
		panelInfoExtra.add(labelSpan);
		panelInfoExtra.add(checkboxSpan);
		panelInfoExtra.add(labelWidth);
		panelInfoExtra.add(textfieldWidth);
		panelInfoExtra.add(labelHeight);
		panelInfoExtra.add(textfieldHeight);
		panelInfoExtra.add(labelAdmin);
		panelInfoExtra.add(checkboxAdmin);
		panelInfoExtra.add(labelPublic);
		panelInfoExtra.add(checkboxPublic);
		
		UpdateInfo();
		scrollInfo.setViewportView(panelInfoExtra);
		
		panelInfo.add(panelInfoMandatory);
		panelInfo.add(scrollInfo);

		this.add(panelInfo);
		
	}


	public Group getGroupFor(String parGroup) {
		return(Grouplist.get(Groups.indexOf(Integer.parseInt(parGroup))));	
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		engine.log(ae.getActionCommand());
		
		String[] cmd = ae.getActionCommand().split(";");
		
		switch(cmd[0]) {
		case "Group":
			switch(cmd[1]) {
			case "New":
				NewGroup();
				break;
			case "Delete":
				DeleteGroup();
				break;
			}
			break;
		case "Server":
			switch(cmd[1]) {
			case "New":
				NewServer();
				break;
			case "Delete":
				DeleteServer();
				break;
			}
			break;
		case "Info":
			switch(cmd[1]) {
			case "Save":
				SaveServer();
				break;
			case "Cancel":
				CancelServer();
				break;
			case "Connect":
				if(CurrentServer != null) {
					CurrentServer.connect();
				}
				
				break;
			case "Changed":
				if(CurrentServer != null) {
					CurrentServer.SetUnsavedChanges(true);
				}
			}
		}
	}

	@Override
	public void contentsChanged(ListDataEvent lde) {
	}
	@Override
	public void intervalAdded(ListDataEvent lde) {
	}
	@Override
	public void intervalRemoved(ListDataEvent lde) {
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		boolean GoOn = true;
//		ToDo
//		if(CurrentServer != null && CurrentServer.HasUnsavedChanges()) {
//			if(engine.confirm("Der Server hat ungespeicherte Änderungen. Möchten Sie fortfahren?")) {
//				GoOn = true;
//			}
//		}else {
//			GoOn = true;
//		}
		
		if(GoOn) {
			if(lse.getSource() == listGroup && lmodelGroup.size() > 0) {
				engine.log(listGroup.getSelectedIndex());
	
				CurrentGroup = Grouplist.get(listGroup.getSelectedIndex());	
				
				engine.log(CurrentGroup.GetCaption());
				for(int x = 0; x < CurrentGroup.GetServerList().size(); x++) {
					engine.log(CurrentGroup.getListAsArrayList().get(x).getCaption());
				}
				UpdateServer();
				UpdateInfo();
				
			}
			
			if(lse.getSource() == listServer && lmodelServer.size() > 0) {
				engine.log(listServer.getSelectedIndex());
				
				CurrentServer = CurrentServerList.get(listServer.getSelectedIndex());
				UpdateInfo();
				
			}
		}
	}

}
