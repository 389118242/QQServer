package qq.dao;

import java.util.List;

import qq.entity.User;

public interface IUserDao {

	/**
	 * ����û�
	 * 
	 * @param userId
	 *            id
	 * @param userName
	 *            �ǳ�
	 * @param userPassword
	 *            ����
	 * @param userAvatar
	 *            ͷ��
	 * @param userAge
	 *            ����
	 * @param userEmail
	 *            ����
	 * @return
	 */
	int addUser(String userId, String userName, String userPassword, String userAvatar, int userAge, String userEmail);

	int updateUserData(String sql, Object[] param);

	List<User> queryAllUser();

	User queryUserById(String id);

	String getUserId();
}
