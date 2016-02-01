package com.game.affiche.action;

import java.util.List;

import com.game.util.admin.affiche.services.AfficheService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Affiche;
import com.game.util.web.Page;

public class AfficheAction extends BaseAction {
	private static final long serialVersionUID = -606623643693877198L;
	private AfficheService afficheService;
	private Affiche affiche;
	private Long afficheID;
	private Integer type;
	private List<Affiche> afficheList;
	private Page<Affiche> page;

	public String more() throws Exception {
		if (type == null) {
			type = 1;
		}
		page = afficheService.searchAffiche(type, 1, 15, super.getGoPage());
		afficheList = page.getResultlist();
		return "more";
	}

	public String show() throws Exception {
		if (afficheID == null) {
			return INPUT;
		}
		affiche = afficheService.getEntity(Affiche.class, afficheID);
		if (affiche == null) {
			return INPUT;
		}
		return "show";
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
