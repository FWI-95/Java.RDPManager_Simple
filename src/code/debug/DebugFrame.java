package code.debug;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DebugFrame {

	private JFrame frame;
	private JTable ProfileTable;
	private JTable GroupTable;
	private JTable ServerTable;
	private JTable table_3;
	
	private CurrentOptions opt;
	private JTable CommentTable;

	/**
	 * Launch the application.
	 */
	public DebugFrame(CurrentOptions parOpt) {
		opt = parOpt;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 930, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 914, 488);
		frame.getContentPane().add(tabbedPane);
		
		JPanel GeneralTab = new JPanel();
		tabbedPane.addTab("General", null, GeneralTab, null);
		GeneralTab.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel ProfilePanel = new JPanel();
		GeneralTab.add(ProfilePanel);
		ProfilePanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 227, 155);
		ProfilePanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel pflID = new JLabel("ID");
		panel_1.add(pflID);
		
		JLabel pflcapID = new JLabel("New label");
		panel_1.add(pflcapID);
		
		JLabel pflCaption = new JLabel("Profil");
		panel_1.add(pflCaption);
		
		JLabel pflcapCaption = new JLabel("New label");
		panel_1.add(pflcapCaption);
		
		JLabel pflDefault = new JLabel("Default");
		panel_1.add(pflDefault);
		
		JLabel pflcapDefault = new JLabel("New label");
		panel_1.add(pflcapDefault);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 154, 227, 306);
		ProfilePanel.add(scrollPane_1);
		
		JList CurProfileList = new JList();
		CurProfileList.setModel(opt.cpmodel);
		scrollPane_1.setViewportView(CurProfileList);
		
		JPanel GroupPanel = new JPanel();
		GeneralTab.add(GroupPanel);
		GroupPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 227, 156);
		GroupPanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel grpID = new JLabel("ID");
		panel.add(grpID);
		
		JLabel grpcapID = new JLabel("New label");
		panel.add(grpcapID);
		
		JLabel grpCaption = new JLabel("Caption");
		panel.add(grpCaption);
		
		JLabel grpcapCaption = new JLabel("New label");
		panel.add(grpcapCaption);
		
		JLabel grpProfile = new JLabel("Profile");
		panel.add(grpProfile);
		
		JLabel grpcapProfile = new JLabel("New label");
		panel.add(grpcapProfile);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 154, 227, 306);
		GroupPanel.add(scrollPane);
		
		JList CurGroupList = new JList();
		CurGroupList.setModel(opt.cgmodel);
		scrollPane.setViewportView(CurGroupList);
		
		JPanel CurrentGroups = new JPanel();
		GeneralTab.add(CurrentGroups);
		CurrentGroups.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 227, 156);
		CurrentGroups.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel srvID = new JLabel("ID");
		panel_2.add(srvID);
		
		JLabel srvcapID = new JLabel("New label");
		panel_2.add(srvcapID);
		
		JLabel srvCaption = new JLabel("Caption");
		panel_2.add(srvCaption);
		
		JLabel srvcapCaption = new JLabel("New label");
		panel_2.add(srvcapCaption);
		
		JLabel srvGroup = new JLabel("Group");
		panel_2.add(srvGroup);
		
		JLabel srvcapGroup = new JLabel("New label");
		panel_2.add(srvcapGroup);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 154, 227, 306);
		CurrentGroups.add(scrollPane_2);
		
		JList CurServerlist = new JList();
		CurServerlist.setModel(opt.csmodel);
		scrollPane_2.setViewportView(CurServerlist);
		
		JPanel CurrentServers = new JPanel();
		GeneralTab.add(CurrentServers);
		CurrentServers.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 227, 156);
		CurrentServers.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel cmtServer = new JLabel("Server");
		panel_3.add(cmtServer);
		
		JLabel cmtcapServer = new JLabel("New label");
		panel_3.add(cmtcapServer);
		
		JLabel cmtLine = new JLabel("Line");
		panel_3.add(cmtLine);
		
		JLabel cmtcapLine = new JLabel("New label");
		panel_3.add(cmtcapLine);
		
		JLabel cmtContent = new JLabel("Content");
		panel_3.add(cmtContent);
		
		JLabel cmtcapContent = new JLabel("New label");
		panel_3.add(cmtcapContent);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 154, 227, 306);
		CurrentServers.add(scrollPane_3);
		
		JList CurCommentList = new JList();
		CurCommentList.setModel(opt.ccmodel);
		scrollPane_3.setViewportView(CurCommentList);
		
		JPanel ProfileTab = new JPanel();
		tabbedPane.addTab("Profile", null, ProfileTab, null);
		ProfileTab.setLayout(null);
		
		JScrollPane ProfilelistComplete = new JScrollPane();
		ProfilelistComplete.setBounds(0, 0, 909, 460);
		ProfileTab.add(ProfilelistComplete);
		
		ProfileTable = new JTable();
		ProfileTable.setModel(opt.pmodel);
		ProfilelistComplete.setViewportView(ProfileTable);
		
		JPanel GroupTab = new JPanel();
		tabbedPane.addTab("Group", null, GroupTab, null);
		GroupTab.setLayout(null);
		
		JScrollPane GrouplistComplete = new JScrollPane();
		GrouplistComplete.setBounds(0, 0, 909, 460);
		GroupTab.add(GrouplistComplete);
		
		GroupTable = new JTable();
		GroupTable.setModel(opt.gmodel);
		GrouplistComplete.setViewportView(GroupTable);
		
		JPanel ServerTab = new JPanel();
		tabbedPane.addTab("Server", null, ServerTab, null);
		ServerTab.setLayout(null);
		
		JScrollPane ServerlistComplete = new JScrollPane();
		ServerlistComplete.setBounds(0, 0, 909, 460);
		ServerTab.add(ServerlistComplete);
		
		ServerTable = new JTable();
		ServerTable.setModel(opt.smodel);
		ServerlistComplete.setViewportView(ServerTable);
		
		JPanel CommentsTab = new JPanel();
		tabbedPane.addTab("Comments", null, CommentsTab, null);
		CommentsTab.setLayout(null);
		
		JScrollPane CommentlistComplete = new JScrollPane();
		CommentlistComplete.setBounds(0, 0, 909, 460);
		CommentsTab.add(CommentlistComplete);
		
		CommentTable = new JTable();
		CommentTable.setModel(opt.cmodel);
		CommentlistComplete.setViewportView(CommentTable);
		
		JPanel SettingsTab = new JPanel();
		tabbedPane.addTab("Settings", null, SettingsTab, null);
		SettingsTab.setLayout(null);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(0, 0, 909, 460);
		SettingsTab.add(scrollPane_6);
		
		table_3 = new JTable();
		scrollPane_6.setViewportView(table_3);
	}
}
