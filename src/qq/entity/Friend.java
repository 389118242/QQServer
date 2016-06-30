package qq.entity;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class Friend implements Serializable {
	private String userId;
	private String friendId;
	private int friendState;

	public Friend() {

	}

	public Friend(Map<String, Object> m) {
		this.userId = m.get("user_id").toString();
		this.friendId = m.get("friend_id").toString();
		this.friendState = Integer.parseInt(m.get("friend_state").toString());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public int getFriendState() {
		return friendState;
	}

	public void setFriendState(int friendState) {
		this.friendState = friendState;
	}

}
