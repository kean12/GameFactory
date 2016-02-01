package com.game.credit.action;

import java.util.List;

import com.game.assess.services.AssessService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Assess;
import com.game.util.domain.CreditCount;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;

public class CreditAction extends BaseAction {

	private static final long serialVersionUID = 6998766976923464607L;
	private UserService userService;
	private AssessService assessService;
	private Long userID;
	private User user;
	private List<Assess> sellerAssess;
	private Page<Assess> page;
	private CreditCount creditCount;
	private Integer grade;
	private Integer type;
	private Integer isSeller;
	
	List<Runnable> runningThreads = null;

	public String execute() throws Exception {
		if (userID != null) {
			user = userService.getUserById(userID);
		} else {
			user = Struts2Util.getUserSession();
			userID = user.getId();
		}
		creditCount = assessService.findCountByTime(userID);
		
		page = assessService.findAssess(userID, type, grade, isSeller, 5, super.getGoPage());
		sellerAssess = page.getResultlist();
		
		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AssessService getAssessService() {
		return assessService;
	}

	public void setAssessService(AssessService assessService) {
		this.assessService = assessService;
	}

	public List<Assess> getSellerAssess() {
		return sellerAssess;
	}

	public void setSellerAssess(List<Assess> sellerAssess) {
		this.sellerAssess = sellerAssess;
	}

	public Page<Assess> getPage() {
		return page;
	}

	public void setPage(Page<Assess> page) {
		this.page = page;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsSeller() {
		return isSeller;
	}

	public void setIsSeller(Integer isSeller) {
		this.isSeller = isSeller;
	}

	public CreditCount getCreditCount() {
		return creditCount;
	}

	public void setCreditCount(CreditCount creditCount) {
		this.creditCount = creditCount;
	}

}
