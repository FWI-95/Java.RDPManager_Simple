package code.debug;

import javax.swing.table.DefaultTableModel;

import code.main.Engine;
import code.source.Comment;
import code.source.Group;
import code.source.Profile;
import code.source.Server;

public class CommentModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	CurrentOptions opt;
	Engine engine;
	
	public CommentModel(CurrentOptions parOpt) {
		opt = parOpt;
		engine = opt.getEngine();
		
		addColumn("Server");
		addColumn("Line");
		addColumn("Content");
		
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
					Server CurServer = CurGroup.GetServerList().get(s);
					for(int c = 0; c < CurServer.GetCommentList().size(); c++) {
						addRow(Append(CurServer.GetCommentList().get(c)));
					}
				}
			}
		}
	}
	
	
	public String[] Append(Comment parComment) {
		String[] row = new String[3];
		
		row[0] = String.valueOf(parComment.getServer());
		row[1] = String.valueOf(parComment.pos());
		row[2] = parComment.getContent();
		
		return row;
	}

}
