package code.source;

import java.util.ArrayList;

import code.main.Engine;

public class ProfileManager {
	Engine engine;
	Settings settings;
	
	Profile CurrentProfile;
	ArrayList<Profile> ProfileList;
	ArrayList<Integer> Profiles;
	
	public ProfileManager(Engine parEngine) {
		engine = parEngine;
		settings = engine.getSettings();
		
		ProfileList = new ArrayList<Profile>();
		Profiles = new ArrayList<Integer>();
		
	}
	
	public void addProfile(String[] parProfile) {
		switch(parProfile.length) {
		case 2:
			loadProfile(parProfile[0], parProfile[1]);
			break;
		case 3:
			loadProfile(parProfile[0], parProfile[1], parProfile[2]);
			break;
		default:
			break;
		}
	}
	
	public void loadProfile(String parID, String parName) {
		
		addProfile(Integer.parseInt(parID), parName, false);
	}
	
	public void loadProfile(String parID, String parName, String parDefault) {
		addProfile(Integer.parseInt(parID), parName, Boolean.valueOf(parDefault));
	}
	
	public void addProfile(int parID, String parName, boolean parDefault) {
		Profile tmpProfile = new Profile(parID, parName, parDefault);
		if(parDefault) CurrentProfile = tmpProfile;
		ProfileList.add(tmpProfile);
		Profiles.add(tmpProfile.getID());
		engine.debug("Loading Profile " + tmpProfile.getID());
	}
	
	public void addGroup(String[] parGroup) {
		switch(parGroup.length) {
		case 3:
			loadGroup(parGroup[0], parGroup[1], parGroup[2]);
		}
	}
	
	public void loadGroup(String parID, String parName, String parProfile) {
		addGroup(Integer.parseInt(parID), parName, Integer.parseInt(parProfile));
	}
	
	public void addGroup(int parID, String parName) {
		addGroup(parID, parName, CurrentProfile.getID());
	}
	
	public void addGroup(int parID, String parName, int parProfile) {
		if(Profiles.contains(parProfile)) {
			Profile tmpProfile = ProfileList.get(Profiles.indexOf(parProfile));
			Group tmpGroup = new Group(parID, parName, parProfile);
			
			tmpProfile.add(tmpGroup);
		}
	}
	
	public void addServer(String[] parServer) {
		switch(parServer.length) {
		case 13:
			loadServer(parServer[0],
					parServer[1],
					parServer[2],
					parServer[3],
					parServer[4],
					parServer[5],
					parServer[6],
					parServer[7],
					parServer[8],
					parServer[9],
					parServer[10],
					parServer[11],
					parServer[12]);
			break;
		default:
			break;
		}
	}
	
	public void loadServer(String parID, String parURL, String parCaption, String parGroup, String parVPN, String parUser, String parFullscreen, String parMultimon, String parSpan, String parAdmin, String parPublic, String parWidth, String parHeight) {
		addServer(
				Integer.parseInt(parID), 		//ID
				parURL,							//URL
				parCaption,						//Caption
				Integer.parseInt(parGroup),		//Group
				parVPN,							//VPN
				parUser,						//User
				Boolean.valueOf(parFullscreen),	//Fullscreen
				Boolean.valueOf(parMultimon),	//Multimon
				Boolean.valueOf(parSpan),		//Span
				Boolean.valueOf(parAdmin),		//Admin
				Boolean.valueOf(parPublic),		//Public
				Integer.parseInt(parWidth),		//Width
				Integer.parseInt(parHeight)		//Height
				);
	}
	
	public void addServer(int parID, String parURL, String parCaption, int parGroup, String parVPN, String parUser, boolean parFullscreen, boolean parMultimon, boolean parSpan, boolean parAdmin, boolean parPublic, int parWidth, int parHeight) {
		Server tmpServer = new Server(
				engine,
				parID, 			//ID
				parURL,			//URL
				parCaption,		//Caption
				parGroup,		//Group
				parVPN,			//VPN
				parUser,		//User
				parFullscreen,	//Fullscreen
				parMultimon,	//Multimon
				parSpan,		//Span
				parAdmin,		//Admin
				parPublic,		//Public
				parWidth,		//Width
				parHeight		//Height
				);
		
		for(int p = 0; p < ProfileList.size(); p++) {
			if(ProfileList.get(p).GetGroups().contains(tmpServer.getGroupID())){
				Group tmpGroup = ProfileList.get(p).GetGrouplist().get(ProfileList.get(p).GetGroups().indexOf(tmpServer.getGroupID()));
				tmpGroup.add(tmpServer);
			}
		}
		
	}
	
	public void addComment(String [] parComment) {
		switch(parComment.length) {
		case 3:
			loadComment(Integer.parseInt(parComment[0]), Integer.parseInt(parComment[1]), parComment[2]);
		}
	}
	
	public void loadComment(int parServer, int parLine, String parContent) {
		for(int p = 0; p < ProfileList.size(); p++) {
			Profile tmpProfile = ProfileList.get(p);
			for(int g = 0; g < tmpProfile.GetGrouplist().size(); g++) {
				Group tmpGroup = tmpProfile.GetGrouplist().get(g);
				if(tmpGroup.GetServers().contains(parServer)){
					Server tmpServer = tmpGroup.GetServerList().get(tmpGroup.GetServers().indexOf(parServer));
					tmpServer.addComment(parLine, parContent);
				}
			}

		}
	}
	
	public void DeleteGroup(int parID) {
		if(CurrentProfile.GetGroups().contains(parID)) {
			CurrentProfile.DeleteGroup(parID);
		}else {
			for(int p = 0; p < ProfileList.size(); p++) {
				ProfileList.get(p).DeleteGroup(parID);
			}
		}
	}
	
	public void DeleteServer(int parID) {
		if(CurrentProfile.getCurrentGroup().GetServers().contains(parID)) {
			CurrentProfile.getCurrentGroup().GetServerList().remove(CurrentProfile.getCurrentGroup().GetServers().indexOf(parID));
		}else {
			for(int p = 0; p < ProfileList.size(); p++) {
				ProfileList.get(p).DeleteServer(parID);
			}
		}
	}
	
	public int GetNextProfileID() {
		int max = 0;
		for(int x = 0; x < ProfileList.size(); x++) {
			int cur = ProfileList.get(x).getID();
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max + 1;
	}
	
	//Search Group in Current Profile, if not found go on to GetGroupFromAll
	public Group GetGroupFromID(int parID) {
		if(CurrentProfile.GetGroups().contains(parID)) {
			return CurrentProfile.GetGrouplist().get(CurrentProfile.GetGroups().indexOf(parID));
		}else {
			return GetGroupFromAll(parID);
		}
	}
	
	//if Group not found in Current Profile, then search in every Profile
	public Group GetGroupFromAll(int parID) {
		for(int p = 0; p < ProfileList.size(); p++) {
			Profile CurProfile = ProfileList.get(p);
			
			if (CurProfile.GetGroups().contains(parID)){
				return CurProfile.GetGrouplist().get(CurProfile.GetGroups().indexOf(parID));
			}
		}
		return null;
	}
	
	public Server GetServerFromID(int parID) {
		for(int p = 0; p < ProfileList.size(); p++) {
			Profile CurProfile = ProfileList.get(p);
			if(CurProfile.GetServerFromID(parID) != null) return CurProfile.GetServerFromID(parID);
		}
		return null;
	}
	
	public int GetNextGroupID() {
		int max = 0;
		for(int p = 0; p < ProfileList.size(); p++) {
			int cur = ProfileList.get(p).GetHighestGroupID();
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max + 1;
	}
	
	public int GetNextServerID() {
		int max = 0;
		for(int p = 0; p < ProfileList.size(); p++) {
			int cur = ProfileList.get(p).GetHighestServerID();
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max + 1;
	}
	
	public ArrayList<Server> GetCurrentServerlist(){
		if(CurrentProfile != null) {
			if(CurrentProfile.getCurrentGroup() != null) {
				return CurrentProfile.getCurrentGroup().GetServerList();
			}
		}
		return null;
	}
	
	public ArrayList<Comment> GetCurrentCommentlist(){
		if(CurrentProfile != null) {
			if(CurrentProfile.getCurrentGroup() != null) {
				if(CurrentProfile.getCurrentGroup().GetCurrentServer() != null) {
					return CurrentProfile.getCurrentGroup().GetCurrentServer().GetCommentList();
				}
			}
		}
		return null;
	}
	
	public void ChooseProfile() {
		if(CurrentProfile == null) {
			if(ProfileList.size() > 0) {
				CurrentProfile = ProfileList.get(0);
			}else {
				ProfileList.add(new Profile(0,"Standard", true));
				CurrentProfile = ProfileList.get(0);
			}
		}
	}
	
	public ArrayList<Integer> GetCurrentServers(){
		return CurrentProfile.getCurrentGroup().GetServers();
	}
	
	public ArrayList<Group> GetCurrentGrouplist(){
		if(CurrentProfile != null) return CurrentProfile.GetGrouplist();
		return null;
	}
	
	public ArrayList<Integer> GetCurrentGroups(){
		return CurrentProfile.GetGroups();
	}

	public Profile getCurrentProfile() {
		return CurrentProfile;
	}
	
	public void setCurrentProfile(int parProfileID) {
		if(Profiles.contains(parProfileID)){
			setCurrentProfile(ProfileList.get(Profiles.indexOf(parProfileID)));
		}
	}
	
	public void setCurrentProfile(Profile currentProfile) {
		CurrentProfile = currentProfile;
	}

	public ArrayList<Profile> getProfileList() {
		return ProfileList;
	}
}
