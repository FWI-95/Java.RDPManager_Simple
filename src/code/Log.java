package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Log {
	Engine engine;
	Settings settings;
	File logfile;
	
	public Log(Engine parEngine) {
		engine = parEngine;
		settings = engine.getSettings();
		
		logfile = new File(settings.Get("Logfile"));
		if(!logfile.exists()) {
			try {
				logfile.createNewFile();
			} catch (IOException e) {
				settings.RestoreDefaults();
				e.printStackTrace();
				engine.message("Critical error. Settings restored. Please reboot " + global.WindowTitle);
			}
		}
		
	}
	
	public void LogMessage(String message) {
		if(global.Debug) {
			System.out.println(message);
		}
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		String[] error = new String[2];
		
		error[0] = timeStamp;
		error[1] = message;
		
		if(Boolean.parseBoolean(settings.getProperty("Log"))){
			Save(error[0] + ": " + error[1]);
		}
	}
	
	private void Save(String msg) {
		try {
			ArrayList<String> tmp = new ArrayList<String>();
			
			FileReader fr = new FileReader(logfile);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null) {
				tmp.add(line);
			}
			
			FileWriter fw = new FileWriter(logfile);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int x = 0; x < tmp.size(); x++) {
				bw.write(tmp.get(x));
				bw.newLine();
			}
			
			bw.write(msg);
			bw.flush();
			bw.close();
			br.close();
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clear() {
		try {
			FileWriter fw = new FileWriter(logfile);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
