package com.game.util.admin.credit.action;

import java.util.ArrayList;
import java.util.List;

import com.game.util.admin.credit.services.CreditService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Credit;
import com.game.util.web.Constant;

public class CreditAction extends BaseAction {

	private static final long serialVersionUID = -6418965433936600070L;
	
	private CreditService creditService;
	private Credit credit;
	private Long creditID;
	private List<Credit> creditList;
	private String content;
	private Integer type;

	/**
	 * @name 显示所有积分设置
	 */
	public String listCredit() throws Exception {
		creditList = creditService.findCredit(null, null);
		return "listcredit";
	}

	/**
	 * @name 进入批量添加页面
	 */
	public String batchAdd() throws Exception {
		return "batchaddcredit";
	}

	/**
	 * @name 批量保存
	 */
	public String batchSave() throws Exception {
		String creditarray[] = content.trim().split("\r\n");
		List<Credit> creditlist = new ArrayList<Credit>();
		Credit tmpcredit = null;
		for (int i = 0; i < creditarray.length; i++) {
			if (creditarray[i] != null && !creditarray[i].trim().equals("")) {
				String[] value = creditarray[i].split("[\t]+");
				if (value != null && !value.equals("")) {
					if (value.length != 3) {
						throw new Exception("你输入的格式不正确");
					}
					tmpcredit = creditService.findCreditByGrade(type, Integer.valueOf(value[0]));
					if (tmpcredit != null) {
						tmpcredit.setLower(Integer.valueOf(value[1]));
						tmpcredit.setUpper(Integer.valueOf(value[2]));
					} else {
						tmpcredit = new Credit();
						tmpcredit.setGrade(Integer.valueOf(value[0]));
						tmpcredit.setLower(Integer.valueOf(value[1]));
						tmpcredit.setUpper(Integer.valueOf(value[2]));
						tmpcredit.setType(type);
					}
					creditlist.add(tmpcredit);
				}
			}
		}
		creditService.saveorupdatecoll(creditlist);
		creditService.updateCreditByMaxGrade(Constant.MAX_CREDIT, type);
		return "savecredit";
	}

	/**
	 * @name 进入添加更改页面
	 */
	public String add() throws Exception {
		if (creditID != null) {
			credit = creditService.getEntity(Credit.class, creditID);
		}
		return "add";
	}

	/**
	 * @name 保存菜单
	 */
	public String save() throws Exception {
		if (credit.getId() != null) {
			creditService.updateEntity(credit);
		} else {
			creditService.createEntity(credit);
		}
		creditService.updateCreditByMaxGrade(Constant.MAX_CREDIT, credit.getType());
		return "savecredit";
	}

	/**
	 * @name 删除一个积分
	 */
	public String delete() throws Exception {
		if (creditID != null) {
			Credit tmp = creditService.getEntity(Credit.class, creditID);
			type = tmp.getType();
			creditService.removeEntity(tmp);
			creditService.updateCreditByMaxGrade(Constant.MAX_CREDIT, type);
		}
		return "savecredit";
	}

	// *******get/set方法*********
	public CreditService getCreditService() {
		return creditService;
	}

	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public Long getCreditID() {
		return creditID;
	}

	public void setCreditID(Long creditID) {
		this.creditID = creditID;
	}

	public List<Credit> getCreditList() {
		return creditList;
	}

	public void setCreditList(List<Credit> creditList) {
		this.creditList = creditList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
