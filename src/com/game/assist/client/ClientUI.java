package com.game.assist.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.game.assist.task.Information;
import com.game.assist.task.Setting;

public abstract class ClientUI extends JFrame implements Runnable, ActionListener, Observer {
	private ClientModel model;
	private UDPClientModel udpModel;
	private UDPClient udpClient;
	private Vector<String> names;
	private JList nameList;
	private JTextPane sendArea;
	private JTextPane receiveArea;
	protected java.text.SimpleDateFormat format;
	protected String newline = System.getProperty("line.separator");
	protected String name;
	protected SimpleAttributeSet sourceAttribute;
	protected SimpleAttributeSet serverAttribute;

	/**
	 * Method ClientUI
	 * 
	 * 
	 */
	public ClientUI(ClientModel mod) {
		// TODO: 在这添加你的代码
		model = mod;
		try {
			udpModel = new UDPClientModel(model.getLocalPort());
			udpModel.addObserver(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			names = model.getNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		name = model.getName();
		format = new java.text.SimpleDateFormat("HH:mm:ss");
		nameList = new JList(names);
		sendArea = new JTextPane();
		receiveArea = new JTextPane();
		nameList.setCellRenderer(new CellRenderer());
		layoutUI();
		addUDPListenning();
		createAttributeSets();
		new Thread(this).start();
		addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				sendArea.requestFocusInWindow();
			}
		});
	}

	private void layoutUI() {
		// 設定用户列表大小
		nameList.setFixedCellWidth(140);
		nameList.setFixedCellHeight(20);
		receiveArea.setEditable(false);
		// 加入滚动栏
		JScrollPane scrollPane1 = new JScrollPane(nameList);
		JScrollPane scrollPane2 = new JScrollPane(sendArea);
		JScrollPane scrollPane3 = new JScrollPane(receiveArea);
		scrollPane1.setBorder(BorderFactory.createTitledBorder("用户列表"));
		scrollPane1.setOpaque(false);
		scrollPane2.setOpaque(false);
		scrollPane3.setOpaque(false);
		JPanel work_pane = new JPanel(new BorderLayout()), button_pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		work_pane.setOpaque(false);
		button_pane.setOpaque(false);
		// button_pane.add(new JLabel("双击用户列表进入单聊界面"));
		// 设定sendButton的快捷键为ctrl+Enter
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, ActionEvent.CTRL_MASK, true);
		Setting.createButton("退出(E)", 'E', "exit", null, button_pane, this);
		Setting.createButton("发送(S)", 'S', "send", stroke, button_pane, this);
		work_pane.add(new EditToolBar(sendArea), BorderLayout.NORTH);
		work_pane.add(scrollPane2);
		work_pane.add(button_pane, BorderLayout.SOUTH);
		setLayout(new BorderLayout());
		JSplitPane sp1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, scrollPane3, work_pane);
		sp1.setResizeWeight(0.75);
		sp1.setPreferredSize(new Dimension(350, 400));
		sp1.setOpaque(false);
		sp1.setDividerSize(1);
		sp1.setBorder(BorderFactory.createEmptyBorder(18, 10, 0, 0));
		Container contentPane = getContentPane();
		contentPane.add(sp1);
		contentPane.add(scrollPane1, BorderLayout.EAST);
		contentPane.setBackground(Setting.color1);
		pack();
	}

	private void addUDPListenning() {
		nameList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String remoteName = (String) nameList.getSelectedValue();
					if (remoteName.equals(name)) {
						JOptionPane.showMessageDialog(ClientUI.this, "您不能和自己交谈！");
					} else {
						if (udpClient == null) {
							udpClient = new UDPClient(udpModel, name);
							udpClient.addObserver(ClientUI.this);
						}
						udpClient.setRemoteSymbol(remoteName, model.getAddress(remoteName));
						udpClient.showIn(ClientUI.this);
					}
				}
			}
		});
	}

	private void createAttributeSets() {
		sourceAttribute = new SimpleAttributeSet();
		serverAttribute = new SimpleAttributeSet();
		StyleConstants.setForeground(sourceAttribute, Color.blue);
		StyleConstants.setForeground(serverAttribute, new Color(0, 128, 64));
	}

	/**
	 * Method run
	 * 
	 * 
	 */
	public void run() {
		while (true) {
			Information info = model.getMessage();
			if (info == null) {
				continue;
			} else if (info.type == Information.ENTER) {
				if (!names.contains(info.source)) {
					String serverMessage = format.format(new Date()) + "\t" + info.source + " 进来了..." + newline;
					try {
						insertMessage(serverMessage, serverAttribute);
					} catch (BadLocationException e) {
						System.err.println(e.getMessage());
					}
					names.add(info.source);
					nameList.updateUI();
				}
			} else if (info.type == Information.EXIT) {
				if (info.source == Setting.SERVER) {
					doWhenStop();
					break;
				} else {
					String serverMessage = format.format(new Date()) + "\t" + info.source + " 离开了..." + newline;
					try {
						insertMessage(serverMessage, serverAttribute);
					} catch (BadLocationException e) {
						System.err.println(e.getMessage());
					}
					names.remove(info.source);
					nameList.updateUI();
				}
			} else if (info.type == Information.MESSAGE) {
				try {
					if (info.source.equals(Setting.SERVER)) {
						insertMessage(format.format(new Date()) + newline + "[系统消息]  " + info.content + newline, serverAttribute);
						Mp3Player.play();
					} else {
						String source = info.source + "  (" + format.format(new Date()) + ")" + newline;
						insertMessage(source, sourceAttribute);
						insertMessage((StyledDocument) info.content);
					}
				} catch (BadLocationException e) {
					System.err.println(e.getMessage());
				}
			}
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
		if (command.equals("exit")) {
			exit();
		} else if (command.equals("send")) {
			DefaultStyledDocument doc = (DefaultStyledDocument) sendArea.getStyledDocument();
			if (doc.getLength() == 0) {
				JOptionPane.showMessageDialog(this, "请不要发送空信息！");
			} else {
				model.putMessage(doc);
				sendArea.setDocument(sendArea.getEditorKit().createDefaultDocument());
			}
		}
	}

	protected void insertMessage(String message, SimpleAttributeSet attset) throws BadLocationException {
		Document docs = receiveArea.getDocument();
		docs.insertString(docs.getLength(), message, attset);
		receiveArea.setCaretPosition(docs.getLength());
	}

	protected void insertMessage(StyledDocument doc) throws BadLocationException {
		StyledDocument receive_doc = receiveArea.getStyledDocument();
		int base = receive_doc.getLength();
		String text = doc.getText(0, doc.getLength()) + newline;

		receive_doc.insertString(base, text, null);
		LinkedList<Element> list = new LinkedList<Element>();
		for (Element e : doc.getRootElements()) {
			Setting.getAllElements(list, e);
		}
		for (Element e : list) {
			int offset = base + e.getStartOffset(), length = e.getEndOffset() - e.getStartOffset();
			receive_doc.setCharacterAttributes(offset, length, e.getAttributes(), false);
		}
		receiveArea.setCaretPosition(receive_doc.getLength());
	}

	protected void exit() {
		int option = JOptionPane.showConfirmDialog(this, "程序正连接到服务器上，您确定退出吗？", "请您选择", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	protected abstract void doWhenStop();

	/**
	 * Method update
	 * 
	 * 
	 * @param o
	 * @param arg
	 * 
	 */
	public void update(Observable o, Object object) {
		if (o == udpModel) {
			if (object instanceof Information) {
				Information info = (Information) object;
				try {
					String source = info.source + "  (" + format.format(new Date()) + ")  悄悄对你说" + newline;
					insertMessage(source, sourceAttribute);
					insertMessage((StyledDocument) info.content);
				} catch (BadLocationException e) {
					System.err.println(e.getMessage());
				}

			}
		} else if (o == udpClient) {
			Information info = (Information) object;
			try {
				String source = format.format(new Date()) + "\t你  悄悄对" + info.source + "说" + newline;
				insertMessage(source, sourceAttribute);
				insertMessage((StyledDocument) info.content);
			} catch (BadLocationException e) {
				System.err.println(e.getMessage());
			}
		}

	}

	protected class CellRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = -6531234009296274150L;
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value.equals(name))
				setForeground(Color.red);
			return this;
		}
	}

}
