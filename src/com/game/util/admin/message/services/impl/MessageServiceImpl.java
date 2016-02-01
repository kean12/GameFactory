package com.game.util.admin.message.services.impl;

import com.game.util.admin.message.services.MessageService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Message;
import com.game.util.web.Page;

public class MessageServiceImpl extends GenericServiceImpl<Message, Long> implements MessageService {

	public void removeMessage(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Message.class, id));
	}

	public Page<Message> searchMessage(int size, int goPage) throws Exception {
		String hql = "from Message a order by a.time desc,a.id desc";
		return baseDAO.search(hql, size, goPage);
	}

}
