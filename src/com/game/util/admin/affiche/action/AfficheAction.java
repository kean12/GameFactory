package com.game.util.admin.affiche.action;

import java.util.List;

import com.game.util.admin.affiche.services.AfficheService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Affiche;
import com.game.util.domain.Manage;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;

public class AfficheAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8915250363276061023L;
	private AfficheService afficheService;
	private Affiche affiche;
	private Long afficheID;
	private List<Affiche> afficheList;
	private Page<Affiche> page;

	/**
	 * @name 获得大类
	 */
	public String listAffiche() throws Exception {
		page = afficheService.searchAffiche(null, 20, super.getGoPage());
		afficheList = page.getResultlist();
		return "listaffiche";
	}

	/**
	 * @name 进入添加更改页面
	 */
	public String add() throws Exception {
		if (afficheID != null) {
			affiche = afficheService.getEntity(Affiche.class, afficheID);
		}
		return "add";
	}

	/**
	 * @name 保存菜单
	 */
	public String save() throws Exception {
		Manage manage = new Manage();
		manage.setId(new Long(1));
		manage.setUsername("系统管理员");
		affiche.setManage(manage);
		if (affiche.getId() != null) {
			afficheService.updateEntity(affiche);
		} else {
			affiche.setTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
			afficheService.createEntity(affiche);
		}
		return "saveaffiche";
	}

	/**
	 * @name 删除一个公告
	 */
	public String delete() throws Exception {
		if (afficheID != null) {
			afficheService.removeAffiche(afficheID);
		}
		return "saveaffiche";
	}

	// *******get/set方法*********
	public AfficheService getAfficheService() {
		return afficheService;
	}

	public void setAfficheService(AfficheService afficheService) {
		this.afficheService = afficheService;
	}

	public Affiche getAffiche() {
		return affiche;
	}

	public void setAffiche(Affiche affiche) {
		this.affiche = affiche;
	}

	public Long getAfficheID() {
		return afficheID;
	}

	public void setAfficheID(Long afficheID) {
		this.afficheID = afficheID;
	}

	public List<Affiche> getAfficheList() {
		return afficheList;
	}

	public void setAfficheList(List<Affiche> afficheList) {
		this.afficheList = afficheList;
	}

	public Page<Affiche> getPage() {
		return page;
	}

	public void setPage(Page<Affiche> page) {
		this.page = page;
	}

}
