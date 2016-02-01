package com.game.util.admin.message.action;

import java.util.ArrayList;
import java.util.List;

import com.game.util.admin.message.services.MessageService;
import com.game.util.admin.message.services.PostService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Message;
import com.game.util.domain.Post;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class MessageAction extends BaseAction {
	private static final long serialVersionUID = -8129214689409136582L;
	private MessageService messageService;
	private PostService postService;
	private Message message;
	private Long messageID;
	private List<Message> messageList;
	private UserService userService;
	private Page<Message> page;

	public String list() throws Exception {
		page = messageService.searchMessage(18, super.getGoPage());
		messageList = page.getResultlist();
		return "list";
	}

	public String add() throws Exception {
		if (messageID != null) {
			message = messageService.getEntity(Message.class, messageID);
		}
		return "add";
	}

	public String save() throws Exception {
		if (message != null) {
			if (message.getId() != null) {
				messageService.updateEntity(message);
			} else {
				message.setTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
				messageService.createEntity(message);
				List<User> list = userService.findUserByIsUse(1);
				List<Post> postList = null;
				if (!Validator.isEmpty(list)) {
					postList = new ArrayList<Post>(list.size());
					Post post = null;
					for (User user : list) {
						post = new Post();
						post.setMessage(message);
						post.setType(1);
						post.setState(0);
						post.setUser(user);
						postList.add(post);
					}
					postService.saveorupdatecoll(postList);
				}
			}
		}
		return "save";
	}

	// *******get/set方法*********
	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Long getMessageID() {
		return messageID;
	}

	public void setMessageID(Long messageID) {
		this.messageID = messageID;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Page<Message> getPage() {
		return page;
	}

	public void setPage(Page<Message> page) {
		this.page = page;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

}
