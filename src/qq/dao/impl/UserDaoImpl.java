package qq.dao.impl;

import java.util.List;
import java.util.Random;

import qq.dao.IUserDao;
import qq.entity.User;
import qq.util.DatabaseUtil;

public class UserDaoImpl implements IUserDao {

	@Override
	public int addUser(String userId, String userName, String userPassword, String userAvatar, int userAge,
			String userEmail) {
		return DatabaseUtil.updateData(
				"insert into qq_users(user_id,user_name,user_password,user_avatar,user_age,user_email) values(?,?,?,?,?,?)",
				new Object[] { userId, userName, userPassword, userAvatar, userAge, userEmail });
	}

	@Override
	public int updateUserData(String sql, Object[] param) {
		return DatabaseUtil.updateData(sql, param);
	}

	@Override
	public List<User> queryAllUser() {
		return DatabaseUtil.query(User.class, "select * from qq_users", null);
	}

	@Override
	public User queryUserById(String id) {
		List<User> user = DatabaseUtil.query(User.class, "select * from qq_users where user_id=?", new Object[] { id });
		User result = null;
		if (user.size() == 1) {
			result = user.get(0);
		}
		return result;
	}

	public String getUserId() {
		String id = null;
		Random rd = new Random();
		do {
			id = rd.nextInt(10000000) + 10000000 + "";
		} while (queryUserById(id) != null);
		return id;
	}

}
