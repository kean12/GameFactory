package com.game.util.admin.message.services;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Message;
import com.game.util.web.Page;

public interface MessageService extends GenericService<Message, Long> {

	/**
	 * @name 根据ID删除站内信
	 */
	public void removeMessage(Long id) throws Exception;

	/**
	 * @name 分页查询
	 */
	public Page<Message> searchMessage(int size, int goPage) throws Exception;

}
