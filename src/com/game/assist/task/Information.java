package com.game.assist.task;

import java.io.Serializable;

/**
 * 在聊天室通信中被用来传递的对象信息类 之所以用Information作为类名而不是Message，是因为这时设定它不仅仅作为聊天时
 * 所传递的消息，还包括一些其他信息，加入和退出信息。 本类有三个字段，分别是信息类型、信息来源、信息内容。其中信息来源包含加入、 退出、消息三种类型。
 * 由于这个类的实低例对象是要被用来在网络是传递的，所以必须被序列化，所以这里 让它实现了Serializable接口。
 * 注：消息的content字段也必须是被序列化了的，也就是实现了Serializable接口
 */
public class Information implements Serializable {
	private static final long serialVersionUID = -2399161151198312784L;
	public int type;// 信息类型
	public String source;// 信息来源
	public Object content;// 信息内容
	public String username;// 用户名
	public boolean assign;// 是否分配
	public Object obj;// 临时存放对象
	public static final int ENTER = 1;// 加入类型
	public static final int EXIT = 2;// 退出类型
	public static final int MESSAGE = 3;// 消息类型

	/**
	 * Method Information 构造方法 param type 信息类型，可以是ENTER、EXIT、MESSAGE三种类型 param
	 * source 信息来源 param cotent 信息内容，必须是被序列化了的对象
	 */
	public Information(int type, String source, Object content, String username, boolean assign, Object obj) {
		this.type = type;
		this.source = source;
		this.content = content;
		this.username = username;
		this.assign = assign;
		this.obj = obj;
	}

	/**
	 * Method Information 构造方法 param type 信息类型，可以是ENTER、EXIT、MESSAGE三种类型 param
	 * source 信息来源 param cotent 信息内容，必须是被序列化了的对象
	 */
	public Information(int type, String source, Object content) {
		this.type = type;
		this.source = source;
		this.content = content;
	}
}
