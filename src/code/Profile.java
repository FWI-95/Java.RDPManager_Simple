package code;

import java.util.ArrayList;

public class Profile {
	ArrayList<Integer> Groups;
	ArrayList<Group> Grouplist;
	int ID;
	String Caption;
	
	public Profile(int parID, String parCaption) {
		ID = parID;
		Caption = parCaption;
		
		Grouplist = new ArrayList<Group>();
		Groups = new ArrayList<Integer>();
	}
	
	public void add(Group parGroup) {
		if(!Groups.contains(parGroup.GetID())) {
			Groups.add(parGroup.GetID());
			Grouplist.add(parGroup);
		}
	}
	
	public ArrayList<Group> GetGrouplist(){
		return Grouplist;
	}
	
	public ArrayList<Integer> GetGroups(){
		return Groups;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCaption() {
		return Caption;
	}

	public void setCaption(String caption) {
		Caption = caption;
	}
}
