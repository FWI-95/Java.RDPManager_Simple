package code.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import code.main.Engine;
import code.model.JListServer;

public class ServerPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	ContentPanel contentpanel;
	
	JScrollPane scrollServer;
	JListServer listServer;
	
	JButton buttonNewServer;
	JButton buttonDeleteServer;
	
	public ServerPanel(Engine parEngine, ContentPanel parContentPanel) {
		engine = parEngine;
		contentpanel = parContentPanel;
		
		Init();
	}
	
	public void Init() {
		scrollServer = new JScrollPane();
		
		buttonNewServer = new JButton("Neu");
		buttonNewServer.setActionCommand("Server;New");
		buttonNewServer.addActionListener(this);
		
		buttonDeleteServer = new JButton("Löschen");
		buttonDeleteServer.setActionCommand("Server;Delete");
		buttonDeleteServer.addActionListener(this);
		
		this.setLayout(null);
		
		this.setBounds(contentpanel.getWidth() / 4, 0, contentpanel.getWidth() / 4, contentpanel.getHeight());
		
		buttonNewServer.setBounds(0,(this.getHeight() / 10) * 9, this.getWidth() / 2, (this.getHeight() / 10) * 1); 
		buttonDeleteServer.setBounds(this.getWidth() / 2,(this.getHeight() / 10) * 9, this.getWidth() / 2, (this.getHeight() / 10) * 1);
		
		listServer = new JListServer(engine, contentpanel);
		listServer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listServer.setBounds(0,0,this.getWidth(),(this.getHeight() / 10) * 9);
		listServer.setBorder(BorderFactory.createLineBorder(Color.black));
		
//		UpdateServer();

		this.add(listServer);
		this.add(buttonNewServer);
		this.add(buttonDeleteServer);
	}
	
	public void Update() {
		listServer.UpdateContent();
	}
	
	public int getSelectedIndex() {
		return listServer.getSelectedIndex();
	}
	
	public void setSelectedIndex(int index) {
		listServer.setSelectedIndex(index);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(buttonNewServer))contentpanel.NewServer();
		if(ae.getSource().equals(buttonDeleteServer))contentpanel.DeleteServer();
	}
}
