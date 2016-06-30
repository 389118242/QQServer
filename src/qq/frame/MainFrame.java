package qq.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import qq.dao.IFriendDao;
import qq.dao.IUserDao;
import qq.dao.impl.FriendDaoImpl;
import qq.dao.impl.UserDaoImpl;
import qq.thread.ClientThread;

public class MainFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ServerSocket server = null;
	private JButton startServer;
	private JButton closeServer;
	private IUserDao userDao;
	private IFriendDao friendDao;
	private JTextArea text;

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		this.userDao = new UserDaoImpl();
		this.friendDao = new FriendDaoImpl();
		getView();
	}

	public void getView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		startServer = new JButton("\u542F\u52A8\u670D\u52A1\u5668");
		startServer.setFont(new Font("宋体", Font.PLAIN, 23));
		startServer.addActionListener(this);
		closeServer = new JButton("\u5173\u95ED\u670D\u52A1\u5668");
		closeServer.addActionListener(this);
		closeServer.setFont(new Font("宋体", Font.PLAIN, 23));
		closeServer.setEnabled(false);
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(34).addComponent(startServer)
						.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE).addComponent(closeServer)
						.addGap(38))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(2).addComponent(scrollPane_1,
										GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))
						.addGap(18).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(startServer).addComponent(closeServer))
				.addContainerGap(14, Short.MAX_VALUE)));

		text = new JTextArea();
		scrollPane_1.setViewportView(text);
		contentPane.setLayout(gl_contentPane);
		text.setEditable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = ((JButton) e.getSource()).getText();
		if (name.equals("启动服务器")) {
			if (server != null) {
				System.out.println("服务已启动");
				text.append("服务已启动\r\n");
			} else {
				System.out.println("服务器启动中，请稍后...");
				text.append("服务器启动中，请稍后...\r\n");
				try {
					setServer(new ServerSocket(3927));
					new Thread() {
						@Override
						public void run() {
							try {
								while (true) {

									new ClientThread(server.accept(), userDao, friendDao).start();
								}
							} catch (IOException e) {
								System.out.println("服务器关闭成功");
								text.append("服务关闭成功\r\n");
							}
						};
					}.start();
					System.out.println("服务器启动成功");
					startServer.setEnabled(false);
					closeServer.setEnabled(true);
					text.append("服务器启动成功\r\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			if (server == null) {
				System.out.println("服务器已关闭");
				text.append("服务器已关闭\r\n");
			} else {
				System.out.println("服务器关闭中，请稍后...");
				text.append("服务器关闭中，请稍后...\r\n");
				startServer.setEnabled(true);
				closeServer.setEnabled(false);
				try {
					server.close();
					setServer(null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		text.setCaretPosition(text.getText().length());
	}
}
