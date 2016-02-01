package com.game.assist.client;

import com.game.assist.task.ManageModel;

public class Tool {

	public static String login(String username, String password) throws Exception {
		ManageModel manageModel = new ManageModel(username, password);
		manageModel = (ManageModel) TunnelClient.get(manageModel);
		if (manageModel == null || manageModel.getName() == null) {
			return null;
		} else {
			return manageModel.getName();
		}
	}

}
