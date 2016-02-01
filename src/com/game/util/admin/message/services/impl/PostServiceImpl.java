package com.game.util.admin.message.services.impl;

import java.util.List;

import com.game.util.admin.message.services.PostService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Post;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class PostServiceImpl extends GenericServiceImpl<Post, Long> implements
		PostService {

	public int findPostByCount(Long userID, Integer type, Integer state) throws Exception {
		String hql = "select count(*) from Post a where 1=1";
		if (userID != null) {
			hql += " and a.user.id=" + userID;
		}
		if (type != null) {
			hql += " and a.type=" + type;
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		List<Object> list = baseDAO.findList(hql);
		int count = Integer.parseInt(list.get(0).toString());
		return count;
	}

	public Page<Post> searchPost(Long userID, Integer type, Integer state, int size, int goPage) throws Exception {
		String hql = "from Post a where 1=1";
		if (userID != null) {
			hql += " and a.user.id=" + userID;
		}
		if (type != null) {
			hql += " and a.type=" + type;
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		hql += " order by a.message.time desc,a.message.id desc";
		return baseDAO.search(hql, size, goPage);
	}

	public Post getPost(Long id, Long userID) throws Exception {
		Long[] arr = { id, userID };
		List<Post> list = baseDAO.findEntity("from Post a where a.id=? and a.user.id=?", arr);
		if (!Validator.isEmpty(list)) {
			return (Post) list.get(0);
		}
		return null;
	}

	public void removePost(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Post.class, id));
	}

}
