package code.debug;

import javax.swing.table.DefaultTableModel;

import code.main.Engine;
import code.source.Group;
import code.source.Profile;

public class GroupModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	CurrentOptions opt;
	Engine engine;
	
	public GroupModel(CurrentOptions parOpt) {
		opt = parOpt;
		engine = opt.getEngine();
		
		addColumn("ID");
		addColumn("Caption");
		addColumn("Profile");
	}
	
	public void UpdateContent() {
		for(int x = 0; x < getRowCount(); x++) {
			removeRow(x);
		}
		
		for(int p = 0; p < engine.GetProfileList().size(); p ++) {
			Profile CurProfile = engine.GetProfileList().get(p);
			for(int g = 0; g < CurProfile.GetGrouplist().size(); g++) {
				addRow(Append(CurProfile.GetGrouplist().get(g)));
			}
			
		}
	}
	
	
	public String[] Append(Group parGroup) {
		String[] row = new String[3];
		
		row[0] = String.valueOf(parGroup.GetID());
		row[1] = parGroup.GetCaption();
		row[2] = String.valueOf(parGroup.GetProfileID());
		
		return row;
	}

}
