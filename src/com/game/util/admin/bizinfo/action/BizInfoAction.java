package com.game.util.admin.bizinfo.action;

import com.game.bizinfo.services.BizInfoService;
import com.game.util.admin.log.services.LogService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.BizInfo;
import com.game.util.domain.Log;
import com.game.util.domain.Manage;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;

/**
 * 后台-商品Action类
 */
public class BizInfoAction extends BaseAction {
	private static final long serialVersionUID = -7303460668908195113L;
	private BizInfoService bizInfoService;
	private Page<BizInfo> page;
	private BizInfo bizInfo;
	private LogService logService;

	// //////////搜索条件集////////////
	private String order_by;
	private String searchContent;

	/**
	 * 分页得到所有的商品
	 * 
	 * @return String struts2配置返回路径
	 * @throws Exception
	 */
	public String allBizInfos() throws Exception {
		page = bizInfoService.findBizInfoByState(null, null, null, null, null, searchContent, null, null, order_by, null, 15, super.getGoPage());
		return "allBizInfos";
	}

	/**
	 * 根据ID得到商品的信息
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String checkBizInfo() throws Exception {
		if (bizInfo.getId() != null && bizInfo.getId() > 0) {
			bizInfo = bizInfoService.getEntity(BizInfo.class, bizInfo.getId());
		}
		return "checkBizInfo";
	}

	/**
	 * 保存商品实体--记录日志
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String saveBizInfo() throws Exception {
		if (bizInfo != null && bizInfo.getId() != null) {
			BizInfo tmp = bizInfoService.getEntity(BizInfo.class, bizInfo.getId());
			tmp.setBuyType(bizInfo.getBuyType());
			tmp.setStock(bizInfo.getStock());
			tmp.setPrice(bizInfo.getPrice());
			tmp.setIsBuy(bizInfo.getIsBuy());
			tmp.setUnit(bizInfo.getUnit());
			tmp.setSellModel(bizInfo.getSellModel());
			tmp.setSite(bizInfo.getSite());
			tmp.setQq(bizInfo.getQq());
			tmp.setPhoneNum(bizInfo.getPhoneNum());
			tmp.setTitle(bizInfo.getTitle());
			tmp.setInfo(bizInfo.getInfo());
			// tmp.setBizCreTime(bizInfo.getBizCreTime()); //readonly
			// tmp.setProportion(bizInfo.getProportion()); //readonly
			// tmp.setStartSellTime(bizInfo.getStartSellTime()); //readonly
			// tmp.setEndSellTime(bizInfo.getEndSellTime()); //readonly
			// tmp.setTradeStart(bizInfo.getTradeStart()); //readonly
			// tmp.setTradeEnd(bizInfo.getTradeEnd()); //readonly
			bizInfo = tmp;
			bizInfoService.updateEntity(tmp);
			setErrorMessage("成功被保存！");
			Manage manage = Struts2Util.getManageSession();
			logService.saveLog(bizInfo, manage + " 更改商品信息", Log.INFO);
		}
		return "successupdate";
	}

	// ~=======================================
	public BizInfo getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(BizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public BizInfoService getBizInfoService() {
		return bizInfoService;
	}

	public void setBizInfoService(BizInfoService bizInfoService) {
		this.bizInfoService = bizInfoService;
	}

	public LogService getLogService() {
		return logService;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public Page<BizInfo> getPage() {
		return page;
	}

	public void setPage(Page<BizInfo> page) {
		this.page = page;
	}
}