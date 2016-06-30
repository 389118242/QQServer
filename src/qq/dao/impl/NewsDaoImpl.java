package qq.dao.impl;

import java.util.List;

import qq.dao.INewsDao;
import qq.entity.News;
import qq.util.DatabaseUtil;

public class NewsDaoImpl implements INewsDao {

	@Override
	public int addNews(String senderId, String receiverId, String sendDate, String newsType, String newsContent,
			int newsState) {
		return DatabaseUtil.updateData("insert into qq_news values(?,?,?,?,?,?)",
				new Object[] { senderId, receiverId, sendDate, newsType, newsContent, newsState });
	}

	@Override
	public List<News> readNews(String senderId, String receiverId) {
		return DatabaseUtil.query(News.class, "select * from qq_news where news_state=0 and sender_id=? and receiver_id=?",
				new Object[] { senderId, receiverId });
	}

	@Override
	public List<News> getHistoryNews(String senderId, String receiverId, int page, int count) {
		return DatabaseUtil.query(News.class, "select * from qq_news where sender_id=? and receiver_id=? limit ?,?",
				new Object[] { senderId, receiverId, (page - 1) * count, count });
	}

	@Override
	public int changeNewsState(String senderId, String receiverId) {
		return DatabaseUtil.updateData("update qq_news set news_state=1 where sender_id=? and receiver_id=?",
				new Object[] { senderId, receiverId });
	}

	@Override
	public int clearHaveReadNews(String senderId, String receiverId) {
		return DatabaseUtil.updateData("delete from qq_news where news_state=1 and sender_id=? and receiver_id=?",
				new Object[] { senderId, receiverId });
	}


}
