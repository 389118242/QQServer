package qq.dao.impl;

import java.util.List;

import qq.dao.IFriendDao;
import qq.entity.Friend;
import qq.util.DatabaseUtil;

public class FriendDaoImpl implements IFriendDao {


	@Override
	public int addFriend(String userId, String friendId, int friendState) {
		return DatabaseUtil.updateData("insert into qq_friends values(?,?,?)",
				new Object[] { userId, friendId, friendState });
	}

	@Override
	public int removeFriend(String userId, String friendId) {
		return DatabaseUtil.updateData(
				"delete from qq_friends where (user_id=? and friend_id=?)or(user_id=? and friend_id=?)",
				new Object[] { userId, friendId, friendId, userId });
	}

	@Override
	public int changeFriendState(String userId, int friendState) {
		return DatabaseUtil.updateData("update qq_friends set friend_state=? where friend_id=?",
				new Object[] { friendState, userId });
	}

	@Override
	public List<Friend> getFriendsList(String userId) {
		return DatabaseUtil.query(Friend.class, "select * from qq_friends where user_id=?", new Object[] { userId });
	}


}
