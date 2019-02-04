package code.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import code.main.Engine;
import code.model.GroupListModel;
import code.model.JListGroup;
import code.source.Group;
import code.source.Settings;

public class GroupPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	Settings settings;
	ContentPanel contentpanel;
	
	JScrollPane scrollGroup;
	JListGroup listGroup;
	
	JButton buttonNewGroup;
	JButton buttonDeleteGroup;

	public GroupPanel(Engine parEngine, ContentPanel parContentPanel) {
		engine = parEngine;
		contentpanel = parContentPanel;
		settings = engine.getSettings();
		
		Init();
	}
	
	public void Init() {
		scrollGroup = new JScrollPane();
		
		buttonNewGroup = new JButton("Neu");
		buttonNewGroup.setActionCommand("Group;New");
		buttonNewGroup.addActionListener(this);
		
		buttonDeleteGroup = new JButton("Löschen");
		buttonDeleteGroup.setActionCommand("Group;Delete");
		buttonDeleteGroup.addActionListener(this);
		
		this.setLayout(null);
		this.setBounds(0, 0, contentpanel.getWidth() / 4, contentpanel.getHeight());
		
		buttonNewGroup.setBounds(0,(this.getHeight() / 10) * 9, this.getWidth() / 2, (this.getHeight() / 10) * 1); 
		buttonDeleteGroup.setBounds(this.getWidth() / 2,(this.getHeight() / 10) * 9, this.getWidth() / 2, (this.getHeight() / 10) * 1);
		
		
		listGroup = new JListGroup(engine, contentpanel);
		listGroup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listGroup.setBounds(0,0,this.getWidth(),(this.getHeight() / 10) * 9);
		listGroup.setBorder(BorderFactory.createLineBorder(Color.black));
		
//		UpdateGroups();
		
		this.add(listGroup);
		this.add(buttonNewGroup);
		this.add(buttonDeleteGroup);
	}
	
	public void Update() {		
		listGroup.UpdateContent();
	}
	
	public int getSelectedIndex() {
		return listGroup.getSelectedIndex();
	}
	
	public void setSelectedIndex(int index) {
		listGroup.setSelectedIndex(index);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(buttonNewGroup))contentpanel.NewGroup();
		if(ae.getSource().equals(buttonDeleteGroup))contentpanel.DeleteGroup();
	}

}
