package com.game.assist.server;

import java.io.Serializable;

public class OnlineUser implements Serializable {
	private static final long serialVersionUID = -2446738639505257993L;
	private String socket_string;
	private String name_string;

	public OnlineUser() {
	}

	public OnlineUser(String name_string) {
		this.name_string = name_string;
	}

	public String getSocket_string() {
		return socket_string;
	}

	public void setSocket_string(String socketString) {
		socket_string = socketString;
	}

	public String getName_string() {
		return name_string;
	}

	public void setName_string(String nameString) {
		name_string = nameString;
	}

}
