package code.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import code.main.Engine;
import code.source.Profile;

public class ProfileListModel extends DefaultListModel<String>{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	ArrayList<Profile> Profilelist;
	
	public ProfileListModel(Engine parEngine) {
		engine = parEngine;
		
		UpdateContent();
	}
	
	public Profile getProfileAt(int index) {
		return Profilelist.get(index);
	}
	
	public void addElement(Profile parProfile) {
		Profilelist.add(parProfile);
		this.add(this.getSize(), parProfile.getCaption());
	}
	
	public void UpdateContent() {
		this.removeAllElements();
		Profilelist = new ArrayList<Profile>();
		
		for(int p = 0; p < engine.GetProfileList().size(); p++) {
			this.addElement(engine.GetProfileList().get(p).getCaption());
			Profilelist.add(engine.GetProfileList().get(p));
		}
	}
}
