package com.game.assist.client;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.game.assist.task.ConfigUtil;

/**
 * 登录面板
 */
@SuppressWarnings("unused")
public class LogonPane extends JPanel implements ActionListener {
	private static final long serialVersionUID = 7041141842939798009L;
	private ClientModel client;
	private JTextField nameField;
	private JPasswordField pwdField;
	private JTextField ipField;
	private JTextField portField;
	private JButton relatedButton = null;

	/**
	 * Method LogonPane
	 * 
	 * 
	 */
	public LogonPane() {
		// TODO: 在这添加你的代码
		super(new SpringLayout());
		ipField = addLabeledField(this, "服务器IP", this);
		portField = addLabeledField(this, "服务器端口", this);
		nameField = addLabeledField(this, "用户名", this);
		pwdField = addLabeledPasswordField(this, "密码", this);

		ipField.setText(ConfigUtil.getValue("ip"));
		portField.setText("8001");
		com.game.assist.task.SpringUtilities.makeCompactGrid(this, 4, 2, // rows, // cols
				10, 10, // initX, initY
				6, 10); // xPad, yPad

	}

	public String getIP() {
		return ipField.getText();
	}

	public int getPort() throws NumberFormatException {
		return Integer.parseInt(portField.getText());
	}

	public String getName() {
		return nameField.getText();
	}

	@SuppressWarnings("deprecation")
	public String getPassword() {
		return pwdField.getText();
	}

	public void setRelatedButton(JButton button) {
		relatedButton = button;
	}

	protected static JTextField addLabeledField(Container c, String label, ActionListener als) {
		JLabel l = new JLabel(label);
		c.add(l);
		JTextField field = new JTextField(15);
		field.addActionListener(als);
		l.setLabelFor(field);
		c.add(field);
		return field;
	}

	protected static JPasswordField addLabeledPasswordField(Container c, String label, ActionListener als) {
		JLabel l = new JLabel(label);
		c.add(l);
		JPasswordField field = new JPasswordField(15);
		field.addActionListener(als);
		l.setLabelFor(field);
		c.add(field);
		return field;
	}

	/**
	 * Method actionPerformed
	 * 
	 * 
	 * @param e
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == ipField) {
			portField.grabFocus();
			portField.selectAll();
		} else if (source == portField) {
			nameField.grabFocus();
			nameField.selectAll();
		} else if (source == nameField) {
			if (relatedButton != null)
				relatedButton.doClick();
		}
	}

}
