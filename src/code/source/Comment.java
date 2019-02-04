package code.source;

public class Comment {
	int Server;
	int Line;
	String Content;
	
	public Comment(int parServer, int parLine, String parContent) {
		Server = parServer;
		Line = parLine;
		Content = parContent;
	}

	public int pos() {
		return Line;
	}

	public int getServer() {
		return Server;
	}

	public void setServer(int server) {
		Server = server;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
