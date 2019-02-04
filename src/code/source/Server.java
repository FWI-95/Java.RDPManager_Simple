package code.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import code.main.Engine;

public class Server implements Runnable{
	Engine engine;
	Settings settings;
	int ID;
	String URL;
	int Port;
	String Caption;
	int Group;
	String VPN;
	String User;
	boolean Fullscreen;
	boolean Multimon;
	boolean Admin;
	boolean Public;
	int Width;
	int Height;
	boolean Span;
	
	Group group;
	
	ArrayList<Comment> Commentlist;
	
	public Server(Engine parEngine, int parID, String parURL, String parCaption, int parGroup, String parVPN, String parUser, boolean parFullscreen, boolean parMultimon,boolean parSpan, boolean parAdmin, boolean parPublic, int parWidth, int parHeight) {
		engine = parEngine;
		settings = engine.getSettings();
		
		ID = parID;
		URL = parURL;
		Caption = parCaption;
		Group = parGroup;
		VPN = parVPN;
		User = parUser;
		Fullscreen = parFullscreen;
		Multimon = parMultimon;
		Admin = parAdmin;
		Public = parPublic;
		Width = parWidth;
		Height = parHeight;
		Span = parSpan;
		
		Commentlist = new ArrayList<Comment>();
	}
	
	public void addComment(int parLine, String parContent) {
		Comment tmpComment = new Comment(ID, parLine, parContent);
		Commentlist.add(tmpComment);
		SortList();
	}
	
	public void SortList() {
		while(!IsSorted()) {
			for(int c = 0; c < Commentlist.size() - 1; c++) {
				Comment c1 = Commentlist.get(c);
				Comment c2 = Commentlist.get(c + 1);
				if(c1.pos() > c2.pos()) {
					Commentlist.set(c, c2);
					Commentlist.set(c + 1, c1);
				}else if(c1.pos() == c2.pos()) {
					return;
				}
			}
		}
	}
	
	public boolean IsSorted() {
		for(int c = 0; c < Commentlist.size() - 1; c++) {
			if(Commentlist.get(c).pos() >= Commentlist.get(c).pos()) {
				return false;
			}
		}
		return true;
	}
	
	public String GetConnectionString() {
		String parameter = "mstsc.exe";
		
		parameter = addURL(parameter);
		parameter = addPort(parameter);
		parameter = addFullscreen(parameter);
		parameter = addMultimon(parameter);
		parameter = addSpan(parameter);
		parameter = addAdmin(parameter);
		parameter = addPublic(parameter);
		parameter = addWidth(parameter);
		parameter = addHeigth(parameter);
		
		
		return parameter;
	}
	
	private String addURL(String par) {
		return par + " /v:" + URL;
	}
	
	private String addPort(String par) {
		if(Port != 0) {
			return par + ":" + Port;
		}else {
			return par;
		}
	}

	private String addFullscreen(String par) {
		if(Fullscreen) {
			return par + " /f";
		}else {
			return par;
		}
	}
	
	private String addMultimon(String par) {
		if(Multimon) {
			return par + " /multimon";
		}else {
			return par;
		}
	}
	
	private String addSpan(String par) {
		if(Span) {
			return par + " /span";
		}else {
			return par;
		}
	}
	
	public boolean isSpan() {
		return Span;
	}

	public void setSpan(boolean span) {
		Span = span;
	}

	private String addAdmin(String par) {
		if(Admin) {
			return par + " /admin";
		}else {
			return par;
		}
	}
	
	private String addPublic(String par) {
		if(Public) {
			return par + " /public";
		}else {
			return par;
		}
	}
	
	private String addWidth(String par) {
		if(Width != 0) {
			return par + " /w:" + Width;
		}else {
			return par;
		}
	}
	
	private String addHeigth(String par) {
		if(Height != 0) {
			return par + " /h:" + Height;
		}else {
			return par;
		}
	}
	
	@Override
	public void run() {
		String line;
	    Process process;
		try {
			engine.debug(GetConnectionString());
			process = Runtime.getRuntime().exec("cmd /c " + GetConnectionString());
		    Reader r = new InputStreamReader(process.getInputStream());
		    BufferedReader in = new BufferedReader(r);
		    while((line = in.readLine()) != null) engine.log(line);
		    in.close();
		} catch (IOException e) {
			engine.log(e.getStackTrace());
		}

	}
	
	
	public void connect() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public Integer getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public int getGroup() {
		return Group;
	}
	
	public void setGroup(int parID) {
		Group = parID;
	}
	
	public int getPort() {
		return Port;
	}
	
	public void setPort(int port) {
		Port = port;
	}

	public String getCaption() {
		return Caption;
	}

	public void setCaption(String caption) {
		Caption = caption;
	}

	public String getVPN() {
		return VPN;
	}

	public void setVPN(String vPN) {
		VPN = vPN;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public boolean isFullscreen() {
		return Fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		Fullscreen = fullscreen;
	}

	public boolean isMultimon() {
		return Multimon;
	}

	public void setMultimon(boolean multimon) {
		Multimon = multimon;
	}

	public boolean isAdmin() {
		return Admin;
	}

	public void setAdmin(boolean admin) {
		Admin = admin;
	}

	public boolean isPublic() {
		return Public;
	}

	public void setPublic(boolean public1) {
		Public = public1;
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}
	
	public int getGroupID() {
		return Group;
	}

	public ArrayList<Comment> GetCommentList(){
		return Commentlist;
	}

}
