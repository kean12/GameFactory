package com.game.bizinfo.action;

import java.util.ArrayList;
import java.util.List;

import com.game.bizinfo.services.BizInfoService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.BizInfo;
import com.game.util.domain.User;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;

public class BizInfoAction extends BaseAction {
	private static final long serialVersionUID = 7174135656526331959L;
	private BizInfoService bizInfoService;
	private List<BizInfo> bizInfoList;
	private Page<BizInfo> page;
	private Long[] idKey;
	private Long bizInfoID;

	/**
	 * 商家—-出售中的宝贝
	 */
	public String sale() throws Exception {
		User user = Struts2Util.getUserSession();
		// userID 用户ID null为所有|gameID 游戏ID|areaID 分区ID|serverID 服务器ID|bizKindID
		// 分类ID|content 搜索内容|state 状态 null 所有状态|
		// nowTime 现在时间 null|order_by 排序字段 属性名|sort 排序方式 降序 desc 升序为null|size
		// 每页显示数量|goPage 第几页
		page = bizInfoService.findBizInfoByState(user.getId(), null, null,
				null, null, null, 1, null, null, null, 15, super.getGoPage());
		bizInfoList = page.getResultlist();
		return "sale";
	}

	/**
	 * 商家—-仓库中的宝贝/下架的宝贝
	 */
	public String storehouse() throws Exception {
		User user = Struts2Util.getUserSession();
		// userID 用户ID null为所有|gameID 游戏ID|areaID 分区ID|serverID 服务器ID|bizKindID
		// 分类ID|content 搜索内容|state 状态 null 所有状态|
		// nowTime 现在时间 null|order_by 排序字段 属性名|sort 排序方式 降序 desc 升序为null|size
		// 每页显示数量|goPage 第几页
		page = bizInfoService.findBizInfoByState(user.getId(), null, null,
				null, null, null, 0, null, null, null, 15, super.getGoPage());
		bizInfoList = page.getResultlist();
		return "storehouse";
	}

	/**
	 * 商家—出售中的宝贝-删除宝贝
	 */
	public String saleDelete() throws Exception {
		delete();
		return "saleDelete";
	}

	/**
	 * 商家—仓库-删除宝贝
	 */
	public String storehouseDelete() throws Exception {
		delete();
		return "storehouseDelete";
	}

	/**
	 * 删除宝贝
	 */
	public void delete() throws Exception {
		User user = Struts2Util.getUserSession();
		BizInfo bi = null;
		if (bizInfoID != null) {
			bi = bizInfoService.getBizInfo(user.getId(), bizInfoID);
			if (bi != null) {
				bizInfoService.removeEntity(bi);
			}
		} else if (idKey != null && idKey.length > 0) {
			for (Long id : idKey) {
				bi = bizInfoService.getBizInfo(user.getId(), id);
				if (bi != null) {
					bizInfoService.removeEntity(bi);
				}
			}
		}
	}

	/**
	 * 商家—-上架宝贝
	 */
	public String top() throws Exception {
		User user = Struts2Util.getUserSession();
		BizInfo bi = null;
		long time = System.currentTimeMillis();
		if (bizInfoID != null) {
			bi = bizInfoService.getBizInfo(user.getId(), bizInfoID);
			if (bi != null) {
				if (Integer.parseInt(bi.getStock()) > 0) {
					bi.setIsBuy(1);
					if (DateUtil.convertTimeMillis(bi.getEndSellTime()) <= time) {
						bi.setEndSellTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM, DateUtil.Ds(7)));
					}
					bizInfoService.saveorupdate(bi);
				}
			}
		} else if (idKey != null && idKey.length > 0) {
			List<BizInfo> list = new ArrayList<BizInfo>();
			for (Long id : idKey) {
				bi = bizInfoService.getBizInfo(user.getId(), id);
				if (bi != null) {
					if (Integer.parseInt(bi.getStock()) > 0) {
						bi.setIsBuy(1);
						if (DateUtil.convertTimeMillis(bi.getEndSellTime()) <= time) {
							bi.setEndSellTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM, DateUtil.Ds(7)));
						}
						list.add(bi);
					}
				}
			}
			bizInfoService.saveorupdatecoll(list);
		}
		return "top";
	}

	/**
	 * 商家—-下架宝贝
	 */
	public String withdraw() throws Exception {
		User user = Struts2Util.getUserSession();
		BizInfo bi = null;
		if (bizInfoID != null) {
			bi = bizInfoService.getBizInfo(user.getId(), bizInfoID);
			if (bi != null) {
				bi.setIsBuy(0);
				bizInfoService.saveorupdate(bi);
			}
		} else if (idKey != null && idKey.length > 0) {
			List<BizInfo> list = new ArrayList<BizInfo>();
			for (Long id : idKey) {
				bi = bizInfoService.getBizInfo(user.getId(), id);
				if (bi != null) {
					bi.setIsBuy(0);
					list.add(bi);
				}
			}
			bizInfoService.saveorupdatecoll(list);
		}
		return "withdraw";
	}

	public BizInfoService getBizInfoService() {
		return bizInfoService;
	}

	public void setBizInfoService(BizInfoService bizInfoService) {
		this.bizInfoService = bizInfoService;
	}

	public List<BizInfo> getBizInfoList() {
		return bizInfoList;
	}

	public void setBizInfoList(List<BizInfo> bizInfoList) {
		this.bizInfoList = bizInfoList;
	}

	public Page<BizInfo> getPage() {
		return page;
	}

	public void setPage(Page<BizInfo> page) {
		this.page = page;
	}

	public Long[] getIdKey() {
		return idKey;
	}

	public void setIdKey(Long[] idKey) {
		this.idKey = idKey;
	}

	public Long getBizInfoID() {
		return bizInfoID;
	}

	public void setBizInfoID(Long bizInfoID) {
		this.bizInfoID = bizInfoID;
	}
}
