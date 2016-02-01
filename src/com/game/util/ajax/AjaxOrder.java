package com.game.util.ajax;

import com.game.order.services.OrderService;
import com.game.util.admin.manage.services.AssignService;
import com.game.util.domain.Assign;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.web.CryptTool;
import com.game.util.web.DWRUtil;
import com.game.util.web.DateUtil;

public class AjaxOrder {
	private OrderService orderService;
	private AssignService assignService;
	private Order order = null;
	private Assign assign = null;

	protected void getService() {
		if (orderService == null) {
			orderService = (OrderService) DWRUtil.getBean("orderService");
		}
		if (assignService == null) {
			assignService = (AssignService) DWRUtil.getBean("assignService");
		}
	}

	public int verify(String assignID) throws Exception {
		getService();// 获得服务
		User user = DWRUtil.getUserSession();
		if (user == null) {
			return 0;// 你尚未登录,或登录已超时,请重新登录!
		}
		assignID = CryptTool.base64Decode(assignID);

		assign = assignService.getEntity(Assign.class, Long.valueOf(assignID));
		order = assign.getOrder();
		if (!order.getConsumer().getId().equals(user.getId())) {
			return 1;// 非法操作,此订单不属于你!
		}
		if (order.getState() != 1) {
			return 6;// 订单状态已改变,请到已买到的宝贝中查看
		}

		try {
			String tradeTime = assign.getOrder().getBizKind().getTradeTime();
			if (DateUtil.costTime(assign.getStartTime(), tradeTime) < 0) {
				return 9;// 发货超时,你将获得10元超时赔付
			}
		} catch (Exception e) {
			return 7;// 此订单已结束
		}

		return 100;
	}
}
