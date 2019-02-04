package code.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import code.main.Engine;
import code.source.Group;

public class GroupListModel extends DefaultListModel<String> {
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	
	ArrayList<Group> Grouplist;
	
	public GroupListModel(Engine parEngine) {
		engine = parEngine;
		
		UpdateContent();
	}
	
	public Group getGroupAt(int index) {
		return Grouplist.get(index);
	}
	
	public void UpdateContent() {
		this.removeAllElements();
		Grouplist = new ArrayList<Group>();
		
		if(engine.GetGrouplist() != null) {
			for(int x = 0; x < engine.GetGrouplist().size();x++) {
				this.addElement(engine.GetGrouplist().get(x).GetCaption());
				Grouplist.add(engine.GetGrouplist().get(x));
				
				engine.debug("Group " + engine.GetGrouplist().get(x).GetCaption());
			}
		}
	}

}
