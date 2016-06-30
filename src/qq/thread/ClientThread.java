package qq.thread;

import java.io.IOException;
import java.net.Socket;

import qq.dao.IFriendDao;
import qq.dao.IUserDao;
import qq.entity.Message;
import qq.entity.User;
import qq.util.I_OUtil;

public class ClientThread extends Thread {

	private Socket s;
	private IUserDao userDao;
	private IFriendDao friendDao;

	public ClientThread(Socket s, IUserDao userDao, IFriendDao friendDao) {
		this.s = s;
		this.userDao = userDao;
		this.friendDao = friendDao;
	}

	public void run() {
		try {
			while (true) {
				Message mess = (Message) I_OUtil.receiveNews(s);
				String messType = mess.getType();
				if (messType.equals("register")) {
					User user = (User) mess.getContent();
					String userId = userDao.getUserId();
					int retCode = userDao.addUser(userId, user.getUserName(), user.getUserPassword(),
							user.getUserAvatar(), user.getUserAge(), user.getUserEmail());
					mess.setContent(retCode == 0 ? null : userId);
					I_OUtil.sendNews(s, mess);
				} else if (messType.equals("login")) {
					String[] param = mess.getContent().toString().split(":");
					User user = userDao.queryUserById(param[0]);
					if (user == null || !user.getUserPassword().equals(param[1])) {
						mess.setContent(false);
					} else {
						mess.setContent(true);
					}
					I_OUtil.sendNews(s, mess);
					I_OUtil.sendNews(s, friendDao.getFriendsList(param[0]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
