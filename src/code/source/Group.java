package code.source;

import java.util.ArrayList;

public class Group {
	ArrayList <Server> Serverlist;
	ArrayList<Integer> Servers;
	
	int ID;
	String Name;
	int Profile;
	
	Server CurrentServer;
	
	public Group(int parID,String parName, int parProfile) {
		ID = parID;
		Name = parName;
		Profile = parProfile;
		Serverlist = new ArrayList<Server>();
	}
	
	public void add(Server parServer) {
		Serverlist.add(parServer);
		Servers.add(parServer.getID());
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
	
	public int GetProfileID() {
		return Profile;
	}
	
	public ArrayList<Server> GetServerList(){
		return Serverlist;
	}
	
	public ArrayList<Integer> GetServers(){
		return Servers;
	}
	
	public Server GetServerFromID(int parID) {
		if(Servers.contains(parID)) {
			return Serverlist.get(Servers.indexOf(parID));
		}else {
			return null;
		}
	}
	
	public int GetHighestServerID() {
		int max = 0;
		for(int g = 0; g < Serverlist.size(); g++) {
			int cur = Serverlist.get(g).getID();
			if(cur >= max) {
				max = cur;
			}
		}
		return max;
	}
	
	public void DeleteServer(int parID) {
		if(Servers.contains(parID)) {
			Serverlist.remove(Servers.indexOf(parID));
			Servers.remove(Servers.indexOf(parID));
		}
	}
	
	public Server GetCurrentServer() {
		return CurrentServer;
	}
	
	public void SetCurrentServer(Server parServer) {
		CurrentServer = parServer;
	}
	
//	public ArrayList<Integer> GetServerList(){
//		ArrayList<Integer> IDList = new ArrayList<Integer>();
//		
//		for(int x = 0; x < Serverlist.size(); x++) {
//			IDList.add(Serverlist.get(x).getID());
//		}
//		return IDList;
//	}
//	
//	public ArrayList<String> getCaptionListAsStringList(){
//		ArrayList<String> stringlist = new ArrayList<String>();
//		for(int x = 0; x < Serverlist.size(); x++) {
//			stringlist.add(Serverlist.get(x).getCaption());
//		}
//		
//		return stringlist;
//	}
//	
//	public ArrayList<String> getURLListAsStringList(){
//		ArrayList<String> stringlist = new ArrayList<String>();
//		for(int x = 0; x < Serverlist.size(); x++) {
//			stringlist.add(Serverlist.get(x).getURL());
//		}
//		
//		return stringlist;
//	}

	public void removeServer(Server parServer) {
		for(int x = 0; x < Serverlist.size(); x++) {
			if(Serverlist.get(x).getID().equals(parServer.getID())) {
				Serverlist.remove(x);
			}
		}
	}

}
