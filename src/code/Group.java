package code;

import java.util.ArrayList;

public class Group {
	ArrayList <Server> Serverlist;
	int ID;
	int Profile;
	String Name;
	
	Profile profile;
	
	public Group(int parID,String parName,int parProfile) {
		ID = parID;
		Name = parName;
		Profile = parProfile;
		Serverlist = new ArrayList<Server>();
	}
	
	public void SetProfile(Profile parProfile) {
		Profile = parProfile.getID();
		profile = parProfile;
	}
	
	public void add(Server parServer) {
		Serverlist.add(parServer);
	}
	
	public int getProfileID() {
		return Profile;
	}
	
	public ArrayList<Server> getListAsArrayList() {
		return Serverlist;
	}
	
	public String GetCaption() {
		return Name;
	}
	
	public int GetID() {
		return ID;
	}
	
	public ArrayList<Integer> GetServerList(){
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		
		for(int x = 0; x < Serverlist.size(); x++) {
			IDList.add(Serverlist.get(x).getID());
		}
		return IDList;
	}
	
	public ArrayList<String> getCaptionListAsStringList(){
		ArrayList<String> stringlist = new ArrayList<String>();
		for(int x = 0; x < Serverlist.size(); x++) {
			stringlist.add(Serverlist.get(x).getCaption());
		}
		
		return stringlist;
	}
	
	public ArrayList<String> getURLListAsStringList(){
		ArrayList<String> stringlist = new ArrayList<String>();
		for(int x = 0; x < Serverlist.size(); x++) {
			stringlist.add(Serverlist.get(x).getURL());
		}
		
		return stringlist;
	}

	public void removeServer(Server parServer) {
		for(int x = 0; x < Serverlist.size(); x++) {
			if(Serverlist.get(x).getID().equals(parServer.getID())) {
				Serverlist.remove(x);
			}
		}
	}

}
