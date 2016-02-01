package com.game.util.admin.bizkind.services.impl;

import java.util.List;

import com.game.util.admin.bizkind.services.GameKindService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.GameKind;
import com.game.util.web.Validator;

public class GameKindServiceImpl extends GenericServiceImpl<GameKind, Long>
		implements GameKindService {

	public List<GameKind> findGameKindByGame(Long gameID) throws Exception {
		return baseDAO.findEntity("from GameKind a where a.game.id=? order by a.bizKind.orderIndex", gameID);
	}

	public void removeGameKind(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(GameKind.class, id));
	}

	public GameKind getGameKind(Long bizKind, Long gameID) throws Exception {
		Long[] param = { bizKind, gameID };
		List<GameKind> list = baseDAO.findEntity("from GameKind a where a.bizKind.id=? and a.game.id=?", param);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
