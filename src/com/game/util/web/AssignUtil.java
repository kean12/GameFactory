package com.game.util.web;

import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.game.assist.server.Server;
import com.game.util.admin.manage.services.AssignService;
import com.game.util.admin.manage.services.ManageService;
import com.game.util.domain.Assign;
import com.game.util.domain.Manage;
import com.game.util.domain.Order;

/**
 * 寄售订单分配
 */
public class AssignUtil {
	private static ManageService manageService;
	private static AssignService assignService;
	private static Server orderserver;

	/**
	 * 订单分配
	 */
	public static void to_assign(Order order) throws Exception {
		if (orderserver == null) {
			orderserver = (Server) SpringUtil.getBean("orderserver");
		}
		String message = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS) + ":你有新订单，单号" + order.getOrderNum();
		orderserver.orderMessage(message, null, true, order);

	}

	private static Random rd = null;
	private static Manage manage = null;
	private static Assign assign = null;

	/**
	 * 根据软件在线用户分配
	 * 
	 * @param socket_name_map
	 * @return username 返回所分配的用户名
	 * @throws Exception
	 */
	public static String to_allocate(Map<Socket, String> socket_name_map, Object obj) throws Exception {
		if (manageService == null) {
			manageService = (ManageService) SpringUtil.getBean("manageService");
		}
		if (assignService == null) {
			assignService = (AssignService) SpringUtil.getBean("assignService");
		}
		String username = null;
		if (socket_name_map != null && socket_name_map.size() > 0) {
			Object[] coll = socket_name_map.values().toArray();
			rd = new Random(System.currentTimeMillis());
			int num = rd.nextInt(coll.length);
			manage = manageService.findManageByName(coll[num].toString(), "trade");
			if (manage == null) {
				username = assign_temp(obj);
			} else {
				assign = new Assign((Order) obj, manage, DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS), 0, null);
				assignService.createEntity(assign);
				username = manage.getName();
			}
		} else {
			// 当交易员全部不在线的时候，查询出临时客服 将交易全部分配给临时客服，由临时客服手动去分配
			username = assign_temp(obj);
		}

		return username;
	}

	/**
	 * 将订单分配给临时客服
	 */
	private static String assign_temp(Object obj) throws Exception {
		List<Manage> manageList = manageService.findManageByRole("temp", null);
		rd = new Random(System.currentTimeMillis());
		int num = rd.nextInt(manageList.size());
		manage = manageList.get(num);
		assign = new Assign((Order) obj, manage, DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS), 0, "临时客服");
		assignService.createEntity(assign);
		return manage.getName();
	}

}
