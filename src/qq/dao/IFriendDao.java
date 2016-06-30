package qq.dao;

import java.util.List;

import qq.entity.Friend;

public interface IFriendDao {

	int addFriend(String userId,String friendId,int friendState);

	int removeFriend(String userId,String friendId);

	int changeFriendState(String userId,int friendState);

	List<Friend> getFriendsList(String userId);
}
