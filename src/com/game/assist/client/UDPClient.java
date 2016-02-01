package com.game.assist.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.SocketAddress;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultStyledDocument;

import com.game.assist.task.Information;
import com.game.assist.task.Setting;

/**
 * UDP客户端，用于用户之间单独聊天 本类含有一个独立的JFrame,当发送完信息之后隐藏窗口。
 */
@SuppressWarnings("unused")
public class UDPClient extends Observable implements ActionListener {
	protected JFrame frame;
	protected UDPClientModel model;
	private JLabel label;
	private JTextPane editor;
	protected String name;
	protected String remoteName;
	protected SocketAddress remoteAddress;

	/**
	 * Method UDPClient
	 * 
	 * 
	 */
	public UDPClient(UDPClientModel model, String name) {
		this.model = model;
		this.name = name;
		label = new JLabel();

		frame = new JFrame();
		// 创建编辑框
		editor = new JTextPane();
		editor.setPreferredSize(new Dimension(350, 180));
		JScrollPane editorPane = new JScrollPane(editor);
		editorPane.setOpaque(false);
		// 创建按钮
		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setOpaque(false);
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,
				ActionEvent.CTRL_MASK, true);
		Setting.createButton("关闭(C)", 'C', "close", null, buttonPane, this);
		Setting.createButton("发送(S)", 'C', "send", stroke, buttonPane, this);

		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Setting.color1);
		contentPane.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
		contentPane.add(new EditToolBar(editor), BorderLayout.NORTH);
		contentPane.add(editorPane, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		frame.setContentPane(contentPane);
		frame.pack();
	}

	public void setRemoteSymbol(String name, SocketAddress address) {
		remoteName = name;
		remoteAddress = address;
		frame.setTitle(remoteName + " - 发送消息");
	}

	// 按指定窗口的位置显示
	public void showIn(Component owner) {
		frame.setLocationRelativeTo(owner);
		frame.setVisible(true);
	}

	// 发送信息过程
	protected boolean send() throws java.io.IOException {
		DefaultStyledDocument doc = (DefaultStyledDocument) editor.getStyledDocument();
		if (doc.getLength() == 0) {
			JOptionPane.showMessageDialog(frame, "请不要发送空消息！");
			return false;
		} else {
			Information info = new Information(Information.MESSAGE, name, doc);
			model.send(info, remoteAddress);
			setChanged();
			notifyObservers(new Information(Information.MESSAGE, remoteName, doc));
			editor.setDocument(editor.getEditorKit().createDefaultDocument());
			return true;
		}
	}

	/**
	 * Method actionPerformed
	 * 
	 * 
	 * @param e
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO: 在这添加你的代码
		String command = e.getActionCommand();
		if (command.equals("close")) {
			frame.dispose();
		} else if (command.equals("send")) {
			try {
				if (send()) {
					frame.dispose();
				}
			} catch (Exception ie) {
				ie.printStackTrace();
			}

		}
	}
}
