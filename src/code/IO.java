package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class IO {
	Engine engine;
	Settings settings;
	
	ArrayList<Integer> Profiles;
	ArrayList<Profile> Profilelist;
	
	ArrayList<Integer> Groups;
	ArrayList<Group> Grouplist;
	
	ArrayList<Integer> Servers;
	ArrayList<Server> Serverlist;
	
	File Profilefile;
	File Serverfile;
	File Groupfile;	
	
	public IO(Engine parEngine) {
		engine = parEngine;
		settings = engine.getSettings();
	}
	
	public void SaveList() {
		try {
			Profilefile = new File(settings.Get("Profilelist"));
			Groupfile = new File(settings.Get("Grouplist"));
			Serverfile = new File(settings.Get("Serverlist"));
			
			if(!Profilefile.exists()) {
				Profilefile.createNewFile();
			}
			
			if(!Groupfile.exists()) {
				Groupfile.createNewFile();
			}
			
			if(!Serverfile.exists()) {
				Serverfile.createNewFile();
			}
			
			FileWriter pfw = new FileWriter(Profilefile);
			FileWriter gfw = new FileWriter(Groupfile);
			FileWriter sfw = new FileWriter(Serverfile);
			
			BufferedWriter pbw = new BufferedWriter(pfw);
			BufferedWriter gbw = new BufferedWriter(gfw);
			BufferedWriter sbw = new BufferedWriter(sfw);

			for(int p = 0; p < Profilelist.size(); p++) {
				engine.log("Saving Profile: " + AppendProfile(Profilelist.get(p)));
				pbw.write(AppendProfile(Profilelist.get(p)));
				pbw.newLine();
				
				for(int g = 0; g < Grouplist.size(); g++) {
					engine.log("Saving Group: " + AppendGroup(Grouplist.get(g)));
					gbw.write(AppendGroup(Grouplist.get(g)));
					gbw.newLine();
					
					for(int s = 0; s < Grouplist.get(g).getListAsArrayList().size(); s++) {
						engine.log("Saving Server: " + AppendServer(Grouplist.get(g).getListAsArrayList().get(s)));
						sbw.write(AppendServer(Grouplist.get(g).getListAsArrayList().get(s)));
						sbw.newLine();
					}
				}
			}
			
			pbw.flush();
			gbw.flush();
			sbw.flush();
			
			pbw.close();
			gbw.close();
			sbw.close();
			
		} catch (Exception e) {
			engine.log(e.getStackTrace());
		}
	}

	public void loadServerList() {
		Profiles = new ArrayList<Integer>();
		Profilelist = new ArrayList<Profile>();
		Groups = new ArrayList<Integer>();
		Grouplist = new ArrayList<Group>();
		Servers = new ArrayList<Integer>();
		Serverlist = new ArrayList<Server>();
		
		try {
			Profilefile = new File(settings.Get("Profilelist"));
			Groupfile = new File(settings.Get("Grouplist"));
			Serverfile = new File(settings.Get("Serverlist"));
			
			if(!Profilefile.exists()) {
				Profilefile.createNewFile();
			}
			
			if(!Groupfile.exists()) {
				Groupfile.createNewFile();
			}
			
			if(!Serverfile.exists()) {
				Serverfile.createNewFile();
			}
			
			FileReader pfr = new FileReader(Profilefile);
			FileReader gfr = new FileReader(Groupfile);
			FileReader sfr = new FileReader(Serverfile);
			
			BufferedReader pbr = new BufferedReader(pfr);
			BufferedReader gbr = new BufferedReader(gfr);
			BufferedReader sbr = new BufferedReader(sfr);
			
			String line = "";
			
			while((line = pbr.readLine()) != null) {
				String[] ProfileOptions = line.split(";");
				Profile tmpProfile = new Profile(
										Integer.parseInt(ProfileOptions[0]),
										ProfileOptions[1]);
				Profiles.add(tmpProfile.getID());
				Profilelist.add(tmpProfile);
				engine.log("Loading Profile " + tmpProfile.getID());
			}
			
			if (Profilelist.size() == 0){
				Profilelist.add(new Profile(0,"Standard"));
				Profiles.add(0);
			}
			
			line = "";

			while((line = gbr.readLine()) != null) {
				String[] GroupOptions = line.split(";");
				Group tmpGroup = new Group(
										Integer.parseInt(GroupOptions[0]),
										GroupOptions[1],
										Integer.parseInt(GroupOptions[2]));
				
				Groups.add(tmpGroup.GetID());
				Grouplist.add(tmpGroup);
				
				engine.log("Loading Group " + tmpGroup.GetID());
				
				String profile = GroupOptions[2];
				
				Profile tmpProfile;
				
				if(Profiles.contains(Integer.parseInt(profile))) {
					engine.log("Profile " + profile + " found for Group: " + tmpGroup.GetCaption());
					
					tmpProfile = Profilelist.get(Profiles.indexOf(Integer.parseInt(profile)));
				
				}else {
					engine.log("Profile " + profile + " not found for Group: " + tmpGroup.GetID());
					tmpProfile = Profilelist.get(0);
				}
				
				tmpProfile.add(tmpGroup);
				tmpGroup.SetProfile(tmpProfile);
			}
			
			if (Grouplist.size() == 0){
				Grouplist.add(new Group(0,"Standard",0));
				Groups.add(0);
			}

			line = "";
			
			while((line = sbr.readLine()) != null) {
				String[] ServerOptions = line.split(";");
				
				Server tmpServer = new Server(
						engine,
						Integer.parseInt(ServerOptions[0]), 	//ID
						ServerOptions[1],						//URL
						ServerOptions[2],						//Caption
						Integer.parseInt(ServerOptions[3]),		//Group
						ServerOptions[4],						//VPN
						ServerOptions[5],						//User
						Boolean.valueOf(ServerOptions[6]),		//Fullscreen
						Boolean.valueOf(ServerOptions[7]),		//Multimon
						Boolean.valueOf(ServerOptions[8]),		//Admin
						Boolean.valueOf(ServerOptions[9]),		//Public
						Integer.parseInt(ServerOptions[10]),	//Width
						Integer.parseInt(ServerOptions[11]),	//Height
						Boolean.valueOf(ServerOptions[12])		//Span
						);
				
				String group = ServerOptions[3];
				
				Servers.add(tmpServer.getID());
				Serverlist.add(tmpServer);
				
				engine.log("Loading Server " + tmpServer.getID() + " Name " + tmpServer.getCaption() + " Group " + tmpServer.getGroupID());
				
				Group tmpGroup;
				
				if(Groups.contains(Integer.parseInt(group))) {
					engine.log("Group " + group + " found for Server: " + tmpServer.getID());
					tmpGroup = Grouplist.get(Groups.indexOf(Integer.parseInt(group)));

				}else {
					engine.log("Group " + group + " not found for Server: " + tmpServer.getID());
					tmpGroup = Grouplist.get(0);
				}
				
				tmpGroup.add(tmpServer);
				tmpServer.SetGroup(tmpGroup);
			}
			
			pbr.close();
			gbr.close();
			sbr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			engine.log(e.getStackTrace());
			settings.RestoreDefaults();
			engine.message("Critical error. Settings restored. Please reboot " + global.WindowTitle);
		}
	}
	
	private String AppendProfile(Profile parProfile) {
		String tmp = String.valueOf(parProfile.getID());
		tmp = tmp + ";" + parProfile.getCaption();
		
		return tmp;
	}
	
	private String AppendGroup(Group parGroup) {
		String tmp = String.valueOf(parGroup.GetID());
		tmp = tmp + ";" + parGroup.GetCaption();
		tmp = tmp + ";" + parGroup.getProfileID();
		return tmp;
	}
	
	private String AppendServer(Server parServer) {
		String serv = "";
		
		serv = serv + parServer.getID(); 				//ID
		serv = serv + ";" + parServer.getURL();			//URL
		serv = serv + ";" + parServer.getCaption();		//Caption
		serv = serv + ";" + parServer.getGroupID();		//Group
		serv = serv + ";" + parServer.getVPN();			//VPN
		serv = serv + ";" + parServer.getUser();		//User
		serv = serv + ";" + parServer.isFullscreen();	//Fullscreen
		serv = serv + ";" + parServer.isMultimon();		//Multimon
		serv = serv + ";" + parServer.isAdmin();		//Admin
		serv = serv + ";" + parServer.isPublic();		//Public
		serv = serv + ";" + parServer.getWidth();		//Width
		serv = serv + ";" + parServer.getHeight();		//Height
		serv = serv + ";" + parServer.isSpan();			//Span
		
		return serv;
	}

	public ArrayList<Profile> GetProfilelist(){
		return Profilelist;
	}
	
	public ArrayList<Integer> GetProfiles(){
		return Profiles;
	}
	
	public void SetProfileList(ArrayList<Profile> parProfiles) {
		Profilelist = parProfiles;
		Profiles = new ArrayList<Integer>();
		for(int p = 0; p < Profilelist.size(); p++) {
			Profiles.add(Profilelist.get(p).getID());
		}
	}
	
	public void SetGroupList(ArrayList<Group> parGroupList) {
		Grouplist = parGroupList;
		Groups = new ArrayList<Integer>();
		for(int g = 0; g < Grouplist.size(); g++) {
			Groups.add(Grouplist.get(g).GetID());
		}
	}
	
	public ArrayList<Server> GetAllServers(){
		return Serverlist;
	}
	
	public ArrayList<Integer> GetAllServerIDs(){
		return Servers;
	}
}
