package code.debug;

import javax.swing.table.DefaultTableModel;

import code.main.Engine;
import code.source.Profile;

public class ProfileModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	CurrentOptions opt;
	Engine engine;
	
	public ProfileModel(CurrentOptions parOpt) {
		opt = parOpt;
		engine = opt.getEngine();
		
		addColumn("ID");
		addColumn("Caption");
		addColumn("Default");
	}
	
	public void UpdateContent() {
		for(int x = 0; x < getRowCount(); x++) {
			removeRow(x);
		}
		
		for(int x = 0; x < engine.GetProfileList().size(); x ++) {
			addRow(Append(engine.GetProfileList().get(x)));
		}
	}
	
	
	public String[] Append(Profile parProfile) {
		String[] row = new String[3];
		row[0] = String.valueOf(parProfile.getID());
		row[1] = parProfile.getCaption();
		row[2] = String.valueOf(parProfile.isDefault());
		
		return row;
	}

}
