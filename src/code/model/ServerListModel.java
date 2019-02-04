package code.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import code.main.Engine;
import code.source.Server;

public class ServerListModel extends DefaultListModel<String>{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	
	ArrayList<Server> Serverlist;
	
	public ServerListModel(Engine parEngine) {
		engine = parEngine;
		
	}
	
	public Server getServerAt(int index) {
		return Serverlist.get(index);
	}
	
	public void UpdateContent() {
		this.removeAllElements();
		Serverlist = new ArrayList<Server>();
		if(engine.GetServerList() != null) {
			for(int s = 0; s < engine.GetServerList().size(); s++) {
				this.addElement(engine.GetServerList().get(s).getCaption());
				Serverlist.add(engine.GetServerList().get(s));
			}
		}
	}

}
