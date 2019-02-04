package code.debug;

import javax.swing.table.DefaultTableModel;

import code.main.Engine;
import code.source.Group;
import code.source.Profile;
import code.source.Server;

public class ServerModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	CurrentOptions opt;
	Engine engine;
	
	public ServerModel(CurrentOptions parOpt) {
		opt = parOpt;
		engine = opt.getEngine();
		
		addColumn("ID");
		addColumn("URL");
		addColumn("Caption");
		addColumn("Group");
		addColumn("VPN");
		addColumn("User");
		addColumn("Fullscreen");
		addColumn("Multimon");
		addColumn("Admin");
		addColumn("Public");
		addColumn("Width");
		addColumn("Height");
		addColumn("Span");
		
	}
	
	public void UpdateContent() {
		for(int x = 0; x < getRowCount(); x++) {
			removeRow(x);
		}
		
		for(int p = 0; p < engine.GetProfileList().size(); p ++) {
			Profile CurProfile = engine.GetProfileList().get(p);
			for(int g = 0; g < CurProfile.GetGrouplist().size(); g++) {
				Group CurGroup = CurProfile.GetGrouplist().get(g);
				for(int s = 0; s < CurGroup.GetServerList().size(); s++) {
					addRow(Append(CurGroup.GetServerList().get(s)));
				}
			}
		}
	}
	
	
	public String[] Append(Server parServer) {
		String[] row = new String[13];
		
		row[0] = String.valueOf(parServer.getID());
		row[1] = parServer.getURL();
		row[2] = parServer.getCaption();
		row[3] = String.valueOf(parServer.getGroupID());
		row[4] = parServer.getVPN();
		row[5] = parServer.getUser();
		row[6] = String.valueOf(parServer.isFullscreen());
		row[7] = String.valueOf(parServer.isMultimon());
		row[8] = String.valueOf(parServer.isAdmin());
		row[9] = String.valueOf(parServer.isPublic());
		row[10] = String.valueOf(parServer.getWidth());
		row[11] = String.valueOf(parServer.getHeight());
		row[12] = String.valueOf(parServer.isSpan());
		
		return row;
	}

}
