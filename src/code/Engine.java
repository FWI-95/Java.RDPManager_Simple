package code;

import java.util.ArrayList;

public class Engine implements global{
	
	static Settings settings;
	static Window window;
	
	ArrayList<Integer> Profiles;
	ArrayList<Profile> Profilelist;
	
	ArrayList<Integer> Groups;
	ArrayList<Group> Grouplist;
	
	GUI gui;
	IO io;
	Log log;
	
	Profile CurrentProfile;
	
	public Engine() {
		settings = new Settings(this);
		log = new Log(this);
		gui = new GUI();
		io = new IO(this);
		io.loadServerList();
		
		Profilelist = io.GetProfilelist();
		Profiles = io.GetProfiles();
		
		if(settings.get("Standardprofile").equals(global.StandardStr)) {
			CurrentProfile = Profilelist.get(0); 
		}else {
			if(Profilelist.size() <= Integer.parseInt(settings.Get("Standardprofile"))){
				CurrentProfile = Profilelist.get(Integer.parseInt(settings.Get("Standardprofile")));
			}
		}
		Grouplist = CurrentProfile.GetGrouplist();
		Groups = CurrentProfile.GetGroups();
		window = new Window(this, settings);
		
		UpdateLists();
		
		window.init();
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	public void ClearLog() {
		if(confirm("Möchten Sie das Log löschen?")) {
			log.clear();
		}
	}
	
	public void UpdateLists() {
		SaveLists();
		io.loadServerList();
		Grouplist = CurrentProfile.GetGrouplist();
		Groups = CurrentProfile.GetGroups();
		window.SetServer(Groups, Grouplist);
	}
	
	public void SaveLists() {
		io.SetProfileList(Profilelist);
		io.SetGroupList(Grouplist);
		io.SaveList();
	}
	
	public ArrayList<Profile> GetProfileList(){
		return io.GetProfilelist();
	}
	
	public void SetCurrentProfile(int profileID) {
		if(Profiles.contains(profileID)) {
			CurrentProfile = Profilelist.get(Profiles.indexOf(profileID));
			UpdateLists();
		}
	}
	
	public void CreateProfile(String parName) {
		Profilelist.add(new Profile(GetNextProfileID(), parName));
		SaveLists();
	}
	
	public int GetNextProfileID() {
		int max = 0;
		for(int x = 0; x < Profilelist.size(); x++) {
			int cur = Profilelist.get(x).getID();
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max + 1;
	}
	
	public Group GetGroupFromID(int parID) {
		return(Grouplist.get(Groups.indexOf(parID)));
	}
	
	public Server GetServerFromID(int parID) {
		return(io.GetAllServers().get(io.GetAllServerIDs().indexOf(parID)));
	}
	
	public int GetNextGroupID() {
		int max = 0;
		for(int x = 0; x < Groups.size(); x++) {
			int cur = Groups.get(x);
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max + 1;
	}
	
	public int GetNextServerID() {
		int max = 0;
		for(int x = 0; x < io.GetAllServerIDs().size(); x++) {
			int cur = io.GetAllServerIDs().get(x);
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max + 1;
	}
	
	public String[] GatherGroups() {
		String[] GroupCaps = new String[Grouplist.size()];
		for(int x = 0; x < Grouplist.size(); x++) {
			GroupCaps[x] = Grouplist.get(x).GetCaption();
		}
		return GroupCaps;
	}
	
	public ArrayList<Integer> GetGroups(){
		return Groups;
	}
	
	public ArrayList<Group> GetGrouplist(){
		return Grouplist;
	}
		
	public void addGroup(int parID, String parName) {
		if(!Groups.contains(parID)) {
			Group tmpGroup = new Group(parID, parName, CurrentProfile.getID());
			Groups.add(tmpGroup.GetID());
			Grouplist.add(tmpGroup);
		}
		
		UpdateLists();
	}
	
	public void addGroup(String parName) {
		addGroup(GetNextGroupID(), parName);
	}
	
	public void deleteGroup(String parID) {
		if(Groups.contains(Integer.parseInt(parID))) {
			Grouplist.remove(Groups.indexOf(Integer.parseInt(parID)));
			Groups.remove(Groups.indexOf(Integer.parseInt(parID)));
		}
	}
	
	public void AddServer(Server parServer) {
		if(Groups.contains(parServer.getGroupID())) {
			Group tmpGroup = Grouplist.get(Groups.indexOf(parServer.getGroupID()));
			tmpGroup.add(parServer);
			
			UpdateLists();
		}else {
			log("Gruppe nicht gefunden");
		}
	}
	
	public boolean SaveServer(Server parServer) {
		if(Groups.contains(parServer.getGroupID())) {
			Group tmpGroup = Grouplist.get(Groups.indexOf(parServer.getGroupID()));
			if(tmpGroup.GetServerList().contains(parServer.getID())) {
				tmpGroup.getListAsArrayList().set(tmpGroup.GetServerList().indexOf(parServer.getID()), parServer);
				UpdateLists();
				return true;
			}
		}
		return false;
	}
	
	public void log(String message) {
		log.LogMessage(message);
	}
	
	public void log(int message) {
		log.LogMessage(String.valueOf(message));
	}
	
	public void log(Object o) {
		log.LogMessage(o.toString());
	}

	public boolean confirm(String qst) {
		return gui.confirm(qst);
	}

	public void message(String msg) {
		gui.message(msg);
	}
	
	public String input(String qst) {
		return gui.input(qst);
	}
}
