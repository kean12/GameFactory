package com.game.bizinfo.services.impl;

import java.util.List;

import com.game.bizinfo.services.PictureService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Picture;

public class PictureServiceImpl extends GenericServiceImpl<Picture, Long> implements PictureService {

	public void removePicture(List<Picture> list) throws Exception {
		baseDAO.removeAllEntity(list);
	}

	public void removePicture(Long id) throws Exception {
		baseDAO.removeEntity(getPictureByID(id));
	}

	public void updatePicture(Picture picture) throws Exception {
		baseDAO.saveorupdate(picture);
	}

	public List<Picture> findPictureByBizInfo(Long bizInfoID) throws Exception {
		return baseDAO.findEntity("from Picture a where a.bizInfo.id=?",
				bizInfoID);
	}

	public Picture getPictureByID(Long id) throws Exception {
		return baseDAO.getEntity(Picture.class, id);
	}

}
