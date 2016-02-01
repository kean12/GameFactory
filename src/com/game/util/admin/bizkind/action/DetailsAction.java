package com.game.util.admin.bizkind.action;

import java.util.List;

import com.game.util.admin.bizkind.services.DetailsService;
import com.game.util.admin.bizkind.services.GameKindService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Details;
import com.game.util.domain.GameKind;
import com.game.util.web.PinYin;

public class DetailsAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5737237284533118717L;
	private GameKindService gameKindService;
	private DetailsService detailsService;
	private GameKind gameKind;
	private Details details;
	private List<Details> detailsList;
	private Long gameKindID;
	private Long detailsID;
	private Long parentID;
	private Details parent;

	/**
	 * @name 查看此类别的属性
	 */
	public String listDetails() throws Exception {
		if (gameKindID != null) {
			detailsList = detailsService.findDetailsByGameKind(gameKindID);
		} else {
			return INPUT;
		}
		return "listDetails";
	}

	/**
	 * @name 添加大类
	 */
	public String addDetails() throws Exception {
		if (gameKindID != null) {
			if (detailsID != null) {
				details = detailsService.getEntity(Details.class, detailsID);
			}
			gameKind = gameKindService.getEntity(GameKind.class, gameKindID);
		} else {
			return "listDetails";
		}
		return "addDetails";
	}

	/**
	 * @name 添加大类-保存
	 */
	public String saveDetails() throws Exception {
		if (details != null) {
			Details tmp = detailsService.findDetailsByGameKind(gameKindID, details.getType());

			if (tmp != null && tmp.getId().equals(details.getId()) || tmp == null) {
				if (details.getType() == 1) {
					details.setAttributeName("账号交易");
				} else if (details.getType() == 2) {
					details.setAttributeName("自定义属性");
				}
				details.setGameKind(gameKindService.getEntity(GameKind.class, gameKindID));
				if (details.getId() == null) {
					detailsService.createEntity(details);
				} else {
					tmp.setAttributeName(details.getAttributeName());
					tmp.setChild(details.getChild());
					tmp.setFormName(details.getFormName());
					tmp.setGameKind(details.getGameKind());
					tmp.setIsUser(details.getIsUser());
					tmp.setOrderIndex(details.getOrderIndex());
					tmp.setParent(details.getParent());
					tmp.setType(details.getType());
					detailsService.updateEntity(tmp);
				}
			} else {
				throw new Exception("该大类已存在");
			}
		}
		return "saveDetails";
	}

	/**
	 * @name 添加大类-删除
	 */
	public String deleteDetails() throws Exception {
		if (gameKindID != null && detailsID != null) {
			detailsService.removeDetails(detailsID);
		}
		return "deleteDetails";
	}

	/**
	 * @name 显示分组
	 */
	public String listGroup() throws Exception {
		details = detailsService.getEntity(Details.class, parentID);
		return "listGroup";
	}

	/**
	 * @name 添加分组
	 */
	public String addGroup() throws Exception {
		if (detailsID != null) {
			details = detailsService.getEntity(Details.class, detailsID);
		}
		parent = detailsService.getEntity(Details.class, parentID);
		return "addGroup";
	}

	/**
	 * @name 保存分组
	 */
	public String saveGroup() throws Exception {
		if (details != null) {
			parent = detailsService.getEntity(Details.class, parentID);
			details.setParent(parent);
			details.setAttributeName(details.getAttributeName().trim());
			if (details.getId() == null) {
				detailsService.createEntity(details);
			} else {
				detailsService.updateEntity(details);
			}
		}
		return "saveGroup";
	}

	/**
	 * @name 删除分组
	 */
	public String deleteGroup() throws Exception {
		if (parentID != null && detailsID != null) {
			detailsService.removeDetails(detailsID);
		}
		return "saveGroup";
	}

	/**
	 * @name 显示属性
	 */
	public String listAttribute() throws Exception {
		details = detailsService.getEntity(Details.class, parentID);
		return "listAttribute";
	}

	/**
	 * @name 添加属性
	 */
	public String addAttribute() throws Exception {
		if (detailsID != null) {
			details = detailsService.getEntity(Details.class, detailsID);
		}
		parent = detailsService.getEntity(Details.class, parentID);
		return "addAttribute";
	}

	/**
	 * @name 保存属性
	 */
	public String saveAttribute() throws Exception {
		if (details != null) {
			String formName = PinYin.getFullSpell(details.getAttributeName());
			parent = detailsService.getEntity(Details.class, parentID);
			details.setParent(parent);
			details.setAttributeName(details.getAttributeName().trim());
			if (details.getId() == null) {
				details.setFormName(chkFormName(formName, null));
				detailsService.createEntity(details);
			} else {
				details.setFormName(chkFormName(formName, details.getId()));
				detailsService.updateEntity(details);
			}
		}
		return "saveAttribute";
	}

	// 验证表单名是否重复
	private String chkFormName(String formName, Long notID) throws Exception {
		Details tmp = detailsService.findDetailsByFormName(parent.getParent()
				.getGameKind().getId(), formName, notID);
		if (tmp != null) {
			String str = System.currentTimeMillis() + "";
			formName = formName + str.substring(8);
			return chkFormName(formName, notID);
		} else {
			return formName;
		}
	}

	/**
	 * @name 删除属性
	 */
	public String deleteAttribute() throws Exception {
		if (parentID != null && detailsID != null) {
			detailsService.removeDetails(detailsID);
		}
		return "saveAttribute";
	}

	// *************get/set*******************
	public GameKindService getGameKindService() {
		return gameKindService;
	}

	public void setGameKindService(GameKindService gameKindService) {
		this.gameKindService = gameKindService;
	}

	public DetailsService getDetailsService() {
		return detailsService;
	}

	public void setDetailsService(DetailsService detailsService) {
		this.detailsService = detailsService;
	}

	public GameKind getGameKind() {
		return gameKind;
	}

	public void setGameKind(GameKind gameKind) {
		this.gameKind = gameKind;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public List<Details> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<Details> detailsList) {
		this.detailsList = detailsList;
	}

	public Long getGameKindID() {
		return gameKindID;
	}

	public void setGameKindID(Long gameKindID) {
		this.gameKindID = gameKindID;
	}

	public Long getDetailsID() {
		return detailsID;
	}

	public void setDetailsID(Long detailsID) {
		this.detailsID = detailsID;
	}

	public Long getParentID() {
		return parentID;
	}

	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}

	public Details getParent() {
		return parent;
	}

	public void setParent(Details parent) {
		this.parent = parent;
	}

}
