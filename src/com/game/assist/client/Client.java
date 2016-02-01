package com.game.assist.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * 聊天室客户端主程序 这是运行聊天室客户端程序的入口
 */
public class Client implements ActionListener {
	private LogonPane logonPane;// 登录界面
	private ClientModel client;// 客户端数据模型
	private ClientUI clientUI;// 客户端聊天界面
	private JButton enterButton, exitButton;// 登录按钮
	/**
	 * 登录容器，本程序的设计是将登录窗口和聊天窗口分别采用两个JFrame盛放 当登录成功时，登录窗口隐藏，显示聊天容器，当意外断开连接时，再次显示
	 * 登录窗口以便重新登录。所以有必要设置一个logonFrame指针。
	 */
	private JFrame logonFrame;

	/**
	 * 构造方法，用于创建登录界面
	 * 
	 * 
	 */
	public Client() {

		logonFrame = new JFrame("登录");
		// 将登录界面各元素加入登录窗口
		Container contentPane = logonFrame.getContentPane();
		logonPane = new LogonPane();
		enterButton = new JButton("登录");
		exitButton = new JButton("退出");
		logonPane.setRelatedButton(enterButton);
		enterButton.addActionListener(this);
		exitButton.addActionListener(this);
		JPanel controlPane = new JPanel();
		controlPane.add(enterButton);
		controlPane.add(exitButton);
		contentPane.add(logonPane, BorderLayout.CENTER);
		contentPane.add(controlPane, BorderLayout.SOUTH);
	}

	/**
	 * Method main
	 * 
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// TODO: 在这添加你的代码
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		createAndShowGUI();
	}

	/**
	 * 按钮事件处理 此事件源包括"登录"、"退出"两个按钮。
	 */
	public void actionPerformed(ActionEvent e) {
		// "登录"按钮的事件处理，此过程包括连接服务器，创建聊天界面以及各种异常处理
		if (e.getSource() == enterButton) {
			String ip = logonPane.getIP();
			String name = logonPane.getName();
			String password = logonPane.getPassword();

			int port;
			try {
				port = logonPane.getPort();
			} catch (NumberFormatException ne) {// 非数字字符在端口一栏
				JOptionPane.showMessageDialog(logonFrame, ne.getMessage());
				return;
			}
			try {
				client = new ClientModel(ip, port);
			} catch (java.net.UnknownHostException ue) {// ip地址出错
				JOptionPane.showMessageDialog(logonFrame, "不可知的服务器："
						+ ue.getMessage());
				return;
			} catch (IOException ie) {
				JOptionPane.showMessageDialog(logonFrame, ie.getMessage());
				return;
			}

			try {
				name = Tool.login(name, password);
				if (name == null) {
					JOptionPane.showMessageDialog(logonFrame, "用户名或密码不正确");
					return;
				} else if (name.trim().equals("")) {
					JOptionPane.showMessageDialog(logonFrame,
							"真实姓名不能为空，请到管理中心设置");
					return;
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(logonFrame, "数据库连接异常");
				return;
			}

			boolean valid;
			try {
				valid = client.validate(name);// 检查用户名是否有效
			} catch (IOException ie) {
				JOptionPane.showMessageDialog(logonFrame, "服务器连接已满，请稍后重试！");
				return;
			}

			if (!valid) {
				JOptionPane.showMessageDialog(logonFrame, "无效或已经被使用的名字：" + name);
				return;
			} else {
				clientUI = new ClientUI(client) {
					private static final long serialVersionUID = 1114777373697486673L;

					protected void doWhenStop() {
						JOptionPane.showMessageDialog(clientUI, "与服务器的连接中断，请重新登录。");
						clientUI.dispose();
						logonFrame.setVisible(true);
					}
				};
				clientUI.setTitle(client.name + " 的客户端");
				clientUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				clientUI.setLocationRelativeTo(logonFrame);
				clientUI.setVisible(true);
				client.start();
				logonFrame.dispose();
			}
		} else if (e.getSource() == exitButton) {
			System.exit(1);
		}
	}

	/**
	 * 退出处理，加入一个选择对话框
	 */
	protected void exit() {
		int option = JOptionPane.showConfirmDialog(logonFrame, "程序正连接到服务器上，您确定退出吗？", "请您选择", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public static void createAndShowGUI() {
		JFrame frame = new Client().logonFrame;
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
