package com.game.util.admin.message.services;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Post;
import com.game.util.web.Page;

public interface PostService extends GenericService<Post, Long> {
	public void removePost(Long id) throws Exception;

	public Post getPost(Long id, Long userID) throws Exception;

	/**
	 * 根据是否已读 查询出相关条数
	 * 
	 * @param type 类别：1，收件 0，发件 null,查询所有
	 * @param state 状态：1，已读 0,未读 null,查询所有
	 */
	public int findPostByCount(Long userID, Integer type, Integer state) throws Exception;

	/**
	 * @param type 类别：1，收件 0，发件 null,查询所有
	 * @param state 状态：1，已读 0,未读 null,查询所有
	 */
	public Page<Post> searchPost(Long userID, Integer type, Integer state, int size, int goPage) throws Exception;

}
