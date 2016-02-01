package com.game.util.system.timer;

import java.util.List;

import com.game.bizinfo.services.BizInfoService;
import com.game.order.services.OrderService;
import com.game.util.admin.manage.services.AssignService;
import com.game.util.domain.Assign;
import com.game.util.domain.BizInfo;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.domain.UserInfo;
import com.game.util.user.services.UserInfoService;
import com.game.util.user.services.UserService;
import com.game.util.web.Arith;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Record;

public class PublishQuartz {
	private BizInfoService bizInfoService;
	private OrderService orderService;
	private AssignService assignService;
	private UserService userService;
	private UserInfoService userInfoService;
	private Runnable bizInfo_under = null;
	private Thread t1 = null;

	/**
	 * 执行定时器
	 */
	public void quartzDao() throws Exception {
		String nowTime = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM);
		bizInfo_under = new BizInfoUnder(nowTime);
		t1 = new Thread(bizInfo_under);
		t1.start();
	}

	/**
	 * 挂卖信息下架 _结束时间到时 将订单下架
	 * 
	 * @throws Exception
	 */
	class BizInfoUnder extends Thread {
		String nowTime = null;

		public BizInfoUnder(String nowTime) {
			this.nowTime = nowTime;
		}

		public void run() {
			try {
				Thread.sleep(200);
				// 将过期的订单下架
				bizInfoService.updateBizInfoByHql("update BizInfo a  set a.isBuy=0 where a.isBuy=1 and a.endSellTime<='" + nowTime + "'");

				// 将30分钟内没有付款的寄售订单自动关闭
				List<Order> orderList = orderService.findOrderByHql("from Order a where a.state=0 and a.buyType=1 and floor(to_number(sysdate-to_date(a.orderTime,'yyyy-mm-dd hh24:mi:ss'))*24*60)>30");
				
				BizInfo bizInfo = null;
				int stock;
				int buyBum;
				for (Order order : orderList) {
					try {
						order.setState(3);// 将订单关闭
						orderService.updateEntity(order);
						bizInfo = bizInfoService.getBizInfo(order.getOwner().getId(), order.getBizInfo().getId());
						if (bizInfo != null) {
							stock = Integer.parseInt(bizInfo.getStock());
							buyBum = Integer.parseInt(order.getBuyBum());
							if (stock == 0 && buyBum > 0) {
								bizInfo.setIsBuy(1);
							}
							bizInfo.setStock((stock + buyBum) + "");
							bizInfoService.updateEntity(bizInfo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 寄售单子发后后超过20分钟的自动确认
				List<Assign> assignList = null;
				Order order = null;
				User owner = null;
				UserInfo userInfo = null;
				String assureMoney = null;
				assignList = assignService.findAssignByHql("from Assign a where a.order.state=2 and a.order.buyType=1 and floor(to_number(sysdate-to_date(a.endTime,'yyyy-mm-dd hh24:mi:ss'))*24*60)>20");
				
				for (Assign assign : assignList) {
					order = assign.getOrder();
					owner = userService.getUserById(order.getOwner().getId());
					userInfo = userInfoService.getUserInfoById(owner.getId());
					assureMoney = order.getAssureMoney();// 中间金额
					userInfo.setMoney(Arith.intercept(Arith.add(assureMoney, userInfo.getMoney()), 2) + "");
					order.setState(6);
					order.setAssureMoney("0.00");
					order.setSuccTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));

					userInfoService.updateUserInfo(userInfo);
					orderService.updateEntity(order);

					String tol = "" + Arith.intercept(Arith.add(userInfo.getMoney(), userInfo.getFreemoney()), 2);
					String synopsis = "订单编号：" + order.getOrderNum() + " " + order.getTitle() + " 交易收款";
					Record.set(owner, null, null, "游戏买卖网", 2, assureMoney, null, tol, synopsis);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setBizInfoService(BizInfoService bizInfoService) {
		this.bizInfoService = bizInfoService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAssignService(AssignService assignService) {
		this.assignService = assignService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
