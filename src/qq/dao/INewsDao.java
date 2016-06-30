package qq.dao;

import java.util.List;

import qq.entity.News;

public interface INewsDao {

	/**
	 * 添加消息
	 * 
	 * @param senderId
	 *            发送者id
	 * @param receiverId
	 *            接受者id
	 * @param sendDate
	 *            发送时间
	 * @param newsType
	 *            消息类型
	 * @param newsContent
	 *            消息内容
	 * @param newsState
	 *            消息状态
	 * @return 0：失败；1：成功
	 */
	int addNews(String senderId, String receiverId, String sendDate, String newsType, String newsContent,
			int newsState);

	/**
	 * 读取未读消息
	 * 
	 * @param senderId
	 *            发送者id
	 * @param receiverId
	 *            接收者id
	 * @return
	 */
	List<News> readNews(String senderId, String receiverId);

	/**
	 * 获取消息历史
	 * 
	 * @param senderId
	 *            发送者id
	 * @param receiverId
	 *            接受者id
	 * @param page
	 *            页码
	 * @param count
	 *            每页显示数量
	 * @return
	 */
	List<News> getHistoryNews(String senderId, String receiverId, int page, int count);

	/**
	 * 改变消息状态为已读（1）
	 * 
	 * @param senderId
	 *            发送者id
	 * @param receiverId
	 *            接收者id
	 * @return
	 */
	int changeNewsState(String senderId, String receiverId);

	/**
	 * 清空已读消息
	 * 
	 * @param senderId
	 *            发送者id
	 * @param receiverId
	 *            接受者id
	 * @return
	 */
	int clearHaveReadNews(String senderId, String receiverId);
}
