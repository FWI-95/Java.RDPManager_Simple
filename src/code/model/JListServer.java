package code.model;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import code.main.Engine;
import code.panels.ContentPanel;

public class JListServer extends JList<String> implements ListSelectionListener{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	ContentPanel contentpanel;
	
	ServerListModel listmodel;
	
	public JListServer(Engine parEngine, ContentPanel parContentPanel) {
		engine = parEngine;
		contentpanel = parContentPanel;
		
		listmodel = new ServerListModel(engine);
		
		listmodel.UpdateContent();
		
		this.addListSelectionListener(this);
	}
	
	public void UpdateContent() {
		
		
		if(contentpanel.CurrentGroup != null) {
		
			if(engine.GetServerList().size() > 0) {
				
				this.removeListSelectionListener(this);
				
				listmodel.UpdateContent();
				
				this.setModel(listmodel);
				if(this.isSelectionEmpty()) {
					this.setSelectedIndex(0);
				}
				
				engine.log(this.getSelectedIndex());
				contentpanel.CurrentServer = listmodel.getServerAt(this.getSelectedIndex());
				this.addListSelectionListener(this);
			}else {
				engine.log("Keine Server für Gruppe: " + contentpanel.CurrentGroup.GetCaption());
				contentpanel.CurrentServer = null;
			}
		} else {
			engine.log("Keine Gruppe ausgewählt");
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if(listmodel.size() > 0) {
			engine.debug(this.getSelectedIndex());
			
			contentpanel.CurrentServer = listmodel.getServerAt(this.getSelectedIndex());
			contentpanel.PushCurrent();
			contentpanel.UpdateInfo();
		}			
	}

}
