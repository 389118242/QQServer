package qq.biz;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerBiz {
	public static Map<String, Socket> online = null;
	private ServerSocket server = null;

	public void startServer() {
		try {
			online = new HashMap<>();
			server = new ServerSocket(3927);
			while (true) {
				Socket s = server.accept();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
