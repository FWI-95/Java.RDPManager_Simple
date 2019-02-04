package code.source;

import java.util.ArrayList;

public class Profile {
	ArrayList<Integer> Groups;
	ArrayList<Group> Grouplist;
	int ID;
	String Caption;
	boolean Default;
	
	Group CurrentGroup;
	
	public Profile(int parID, String parCaption, boolean parDefault) {
		ID = parID;
		Caption = parCaption;
		Default = parDefault;
		
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
	
	public void setDefault(boolean parDefault) {
		Default = parDefault;
	}
	
	public boolean isDefault() {
		return Default;
	}
	
	public Group getCurrentGroup() {
		return CurrentGroup;
	}
	
	public Server GetServerFromID(int parID) {
		for(int g = 0; g < Grouplist.size();g++) {
			Group CurGroup = Grouplist.get(g);
			if(CurGroup.GetServerFromID(parID) != null) return CurGroup.GetServerFromID(parID);
		}
		return null;
	}
	
	public int GetHighestGroupID() {
		int max = 0;
		for(int x = 0; x < Groups.size(); x++) {
			int cur = Groups.get(x);
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max;
	}
	
	public int GetHighestServerID() {
		int max = 0;
		for(int g = 0; g < Groups.size(); g++) {
			int cur = Grouplist.get(g).GetHighestServerID();
			if(cur >= max) {
				max = cur;
			}
		}
		return max;
	}
	
	public void DeleteGroup(int parID) {
		if(Groups.contains(parID)) {
			Grouplist.remove(Groups.indexOf(parID));
			Groups.remove(Groups.indexOf(parID));
		}
	}
	
	public void DeleteServer(int parID) {
		for(int g = 0; g < Grouplist.size(); g++) {
			Grouplist.get(g).DeleteServer(parID);
		}
	}
	
	public void SetCurrentGroup(int parID) {
		if(Groups.contains(parID)) {
			Group tmpGroup = Grouplist.get(Groups.indexOf(parID));
			SetCurrentGroup(tmpGroup);
		}
	}
	
	public void SetCurrentGroup(Group parGroup) {
		CurrentGroup = parGroup;
	}
}
