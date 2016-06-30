package qq.dao;

import java.util.List;

import qq.entity.News;

public interface INewsDao {

	/**
	 * �����Ϣ
	 * 
	 * @param senderId
	 *            ������id
	 * @param receiverId
	 *            ������id
	 * @param sendDate
	 *            ����ʱ��
	 * @param newsType
	 *            ��Ϣ����
	 * @param newsContent
	 *            ��Ϣ����
	 * @param newsState
	 *            ��Ϣ״̬
	 * @return 0��ʧ�ܣ�1���ɹ�
	 */
	int addNews(String senderId, String receiverId, String sendDate, String newsType, String newsContent,
			int newsState);

	/**
	 * ��ȡδ����Ϣ
	 * 
	 * @param senderId
	 *            ������id
	 * @param receiverId
	 *            ������id
	 * @return
	 */
	List<News> readNews(String senderId, String receiverId);

	/**
	 * ��ȡ��Ϣ��ʷ
	 * 
	 * @param senderId
	 *            ������id
	 * @param receiverId
	 *            ������id
	 * @param page
	 *            ҳ��
	 * @param count
	 *            ÿҳ��ʾ����
	 * @return
	 */
	List<News> getHistoryNews(String senderId, String receiverId, int page, int count);

	/**
	 * �ı���Ϣ״̬Ϊ�Ѷ���1��
	 * 
	 * @param senderId
	 *            ������id
	 * @param receiverId
	 *            ������id
	 * @return
	 */
	int changeNewsState(String senderId, String receiverId);

	/**
	 * ����Ѷ���Ϣ
	 * 
	 * @param senderId
	 *            ������id
	 * @param receiverId
	 *            ������id
	 * @return
	 */
	int clearHaveReadNews(String senderId, String receiverId);
}
