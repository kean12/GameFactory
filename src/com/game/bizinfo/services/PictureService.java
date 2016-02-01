package com.game.bizinfo.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Picture;

public interface PictureService extends GenericService<Picture, Long> {
	public List<Picture> findPictureByBizInfo(Long bizInfoID) throws Exception;

	public Picture getPictureByID(Long id) throws Exception;

	public void removePicture(List<Picture> list) throws Exception;

	public void removePicture(Long id) throws Exception;

	public void updatePicture(Picture picture) throws Exception;
}
