package code.model;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import code.main.Engine;
import code.panels.ContentPanel;
import code.source.Group;

public class JListGroup extends JList<String> implements ListSelectionListener{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	ContentPanel contentpanel;
	GroupListModel listmodel;
	
	public JListGroup(Engine parEngine, ContentPanel parContentPanel) {
		engine = parEngine;
		contentpanel = parContentPanel;
		
		listmodel = new GroupListModel(engine);
		
		this.addListSelectionListener(this);
		
	}
	
	public void UpdateContent() {
		
		listmodel.UpdateContent();
		
		for(Group gr : engine.GetGrouplist()) {
			engine.debug("Cur: " + gr.GetCaption());
		}
		
//		engine.log(lmodelGroup.firstElement());
		
		this.setModel(listmodel);
		
		if(listmodel.size() > 0) {
		
			if(this.isSelectionEmpty()) {
				this.setSelectedIndex(0);
			}
			
			contentpanel.CurrentGroup = listmodel.getGroupAt(this.getSelectedIndex());
		} else {
			contentpanel.CurrentGroup = null;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if(listmodel.size() > 0) {
			engine.debug(this.getSelectedIndex());

			contentpanel.CurrentGroup = listmodel.getGroupAt(this.getSelectedIndex());	
			
			engine.debug(contentpanel.CurrentGroup.GetCaption());
			for(int x = 0; x < contentpanel.CurrentGroup.GetServerList().size(); x++) {
				engine.debug(contentpanel.CurrentGroup.getListAsArrayList().get(x).getCaption());
			}
			contentpanel.PushCurrent();
			contentpanel.UpdateServer();
			contentpanel.UpdateInfo();
		}
	}

}
