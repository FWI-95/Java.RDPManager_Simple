package code.source;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Interface.global;
import code.main.Engine;

public class Settings extends Properties {
	private static final long serialVersionUID = 1L;

	public Settings(Engine engine) {
		try {
			File path = new File(global.SettingsPath);
			File file = new File(global.SettingsPath + global.SettingsFile);
			if(!path.exists()) {
				path.mkdirs();
			}

			if(!file.exists()) {
				file.createNewFile();
				LoadInitSettings();
				SaveSettings();
			}
			
			FileReader fr = new FileReader(file);
			
			this.load(fr);
			
			LoadInitSettings();
			SaveSettings();
						
		}catch(Exception e) {
			e.printStackTrace();
			engine.message("Critical error. Can not load settings at " + global.SettingsPath + global.SettingsFile);
		}
	}

	private void LoadInitSettings() {
		if(getProperty("Version") == null) setProperty("Version", global.Version);
		if(getProperty("Serverlist") == null) setProperty("Serverlist", global.SettingsPath + "serverlist.txt");
		if(getProperty("Grouplist") == null) setProperty("Grouplist", global.SettingsPath + "grouplist.txt");
		if(getProperty("Profilelist") == null) setProperty("Profilelist", global.SettingsPath + "profiles.txt");
		if(getProperty("Commentlist") == null) setProperty("Commentlist", global.SettingsPath + "Commentlist.txt");
		if(getProperty("Standardprofile") == null) setProperty("Standardprofile", global.StandardStr);
		if(getProperty("Logfile") == null) setProperty("Logfile", global.SettingsPath + "logfile.txt");
		if(getProperty("Debug") == null) setProperty("Debug", String.valueOf(global.Debug));
		if(getProperty("FileSplitter") == null) setProperty("FileSplitter", global.FileSplitter);
	}
	
	public void RestoreDefaults() {
		setProperty("Version", global.Version);
		setProperty("Serverlist", global.SettingsPath + "serverlist.txt");
		setProperty("Grouplist", global.SettingsPath + "grouplist.txt");
		setProperty("Profilelist", global.SettingsPath + "profiles.txt");
		setProperty("Commentlist", global.SettingsPath + "Commentlist.txt");
		setProperty("Standardprofile", global.StandardStr);
		setProperty("Logfile", global.SettingsPath + "logfile.txt");
		setProperty("Debug", String.valueOf(global.Debug));
		setProperty("FileSplitter", global.FileSplitter);
		
		SaveSettings();
	}
	
	public boolean SaveSettings() {
		try {
			FileOutputStream fos = new FileOutputStream(new File(global.SettingsPath + global.SettingsFile));
			
			this.store(fos, "");
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String Get(String Key) {
		return this.getProperty(Key);
	}
	
	public void Set(String Key, String Value) {
		this.setProperty(Key, Value);
	}
	

}
