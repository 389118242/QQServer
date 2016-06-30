package qq.dao;

import java.util.List;

import qq.entity.User;

public interface IUserDao {

	/**
	 * 添加用户
	 * 
	 * @param userId
	 *            id
	 * @param userName
	 *            昵称
	 * @param userPassword
	 *            密码
	 * @param userAvatar
	 *            头像
	 * @param userAge
	 *            年龄
	 * @param userEmail
	 *            邮箱
	 * @return
	 */
	int addUser(String userId, String userName, String userPassword, String userAvatar, int userAge, String userEmail);

	int updateUserData(String sql, Object[] param);

	List<User> queryAllUser();

	User queryUserById(String id);

	String getUserId();
}
