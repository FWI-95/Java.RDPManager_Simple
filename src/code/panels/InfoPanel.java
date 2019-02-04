package code.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import code.main.Engine;
import code.source.Server;

public class InfoPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	ContentPanel contentpanel;
	
	JPanel panelInfoMandatory;
	JPanel panelInfoExtra;
	
	JScrollPane scrollInfo;
	
	JButton buttonSaveServer;
	JButton buttonCancelServer;
	JButton buttonConnectServer;
	
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
	
	public InfoPanel(Engine parEngine, ContentPanel parContentPanel) {
		engine = parEngine;
		contentpanel = parContentPanel;
		
		Init();
	}
	
	public void Init() {

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
		labelSpan = new JLabel("Spannen");
		
		textfieldID = new JTextField();
		textfieldURL = new JTextField();
		textfieldPort = new JTextField();
		textfieldCaption = new JTextField();
		comboGroup = new JComboBox<String>();
		textfieldVPN = new JTextField();
		textfieldUser = new JTextField();
		textfieldWidth = new JTextField();
		textfieldHeight = new JTextField();
		checkboxFullscreen = new JCheckBox();
		checkboxMultimon = new JCheckBox();
		checkboxAdmin = new JCheckBox();
		checkboxPublic = new JCheckBox();
		checkboxSpan = new JCheckBox();
		
		buttonSaveServer = new JButton("Speichern");
		buttonSaveServer.setActionCommand("Info;Save");
		buttonSaveServer.addActionListener(this);
		
		buttonCancelServer = new JButton("Abbrechen");
		buttonCancelServer.setActionCommand("Info;Cancel");
		buttonCancelServer.addActionListener(this);
		
		buttonConnectServer = new JButton("Verbinden");
		buttonConnectServer.setActionCommand("Info;Connect");
		buttonConnectServer.addActionListener(this);
		
		this.setLayout(null);
		panelInfoMandatory.setLayout(null);
		panelInfoExtra.setLayout(null);
		
		int hgt = 40;
		int offset = 5;
		
		this.setBounds((contentpanel.getWidth() / 4) * 2, 0, (contentpanel.getWidth() / 4) * 2, contentpanel.getHeight());
		
		panelInfoMandatory.setBounds(0,0,this.getWidth(),this.getHeight() / 3);
		scrollInfo.setBounds(0,this.getHeight() / 3, this.getWidth(),(this.getHeight() / 3) * 2);
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
		
//		UpdateInfo();
		scrollInfo.setViewportView(panelInfoExtra);
		
		this.add(panelInfoMandatory);
		this.add(scrollInfo);
	}
	
	public void UpdateInfo() {
		if(contentpanel.CurrentServer != null) {
			ShowServer(contentpanel.CurrentServer);
		}else {
			BlockFields(false);
		}
	}
	
	public void ShowServer(Server server) {
		BlockFields(true);
		UpdateGroupBox();
		
		textfieldID.setText(String.valueOf(server.getID()));
		textfieldURL.setText(server.getURL());
		textfieldPort.setText(String.valueOf(server.getPort()));
		textfieldCaption.setText(server.getCaption());
		comboGroup.setSelectedIndex(engine.GetGroups().indexOf(server.getGroupID()));
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
		
		textfieldID.setEditable(false);
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
	
	public void CancelServer() {
		if(engine.confirm("Änderungen rückgängig machen?")) {
			UpdateInfo();
//			engine.message("Zurückgesetzt.");
		}
	}
	
	private void SaveServer() {
		contentpanel.CurrentServer.setURL(textfieldURL.getText());
		contentpanel.CurrentServer.setPort(Integer.valueOf(textfieldPort.getText()));
		contentpanel.CurrentServer.setCaption(textfieldCaption.getText());
		contentpanel.CurrentServer.setGroup(engine.GetGroups().get(comboGroup.getSelectedIndex()));
		contentpanel.CurrentServer.setVPN(textfieldVPN.getText());
		contentpanel.CurrentServer.setUser(textfieldUser.getText());
		contentpanel.CurrentServer.setWidth(Integer.valueOf(textfieldWidth.getText()));
		contentpanel.CurrentServer.setHeight(Integer.valueOf(textfieldHeight.getText()));
		contentpanel.CurrentServer.setFullscreen(checkboxFullscreen.isSelected());
		contentpanel.CurrentServer.setMultimon(checkboxMultimon.isSelected());
		contentpanel.CurrentServer.setAdmin(checkboxAdmin.isSelected());
		contentpanel.CurrentServer.setPublic(checkboxPublic.isSelected());
		contentpanel.CurrentServer.setSpan(checkboxSpan.isSelected());
		
		contentpanel.SaveServer();
	}
	
	
	public void UpdateGroupBox() {
		String[] Groupbox = engine.GatherGroups();
		
		comboGroup.removeAllItems();
		
		for(int x = 0; x < comboGroup.getItemCount(); x++) {
			engine.debug("Nach RemoveAll vorhanden: " + comboGroup.getItemAt(x).toString());
		}
		
		for(int x = 0; x < Groupbox.length; x++) {
			engine.debug("Zu ComboGroup hinzugefügt: " + Groupbox[x]);
			comboGroup.addItem(Groupbox[x]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(buttonSaveServer))SaveServer();
		if(ae.getSource().equals(buttonCancelServer))CancelServer();
		if(ae.getSource().equals(buttonConnectServer))contentpanel.Connect();
		
	}

}
