package com.game.util.web;

import com.game.util.admin.manage.services.AssignService;
import com.game.util.admin.message.services.MessageService;
import com.game.util.admin.message.services.PostService;
import com.game.util.domain.Assign;
import com.game.util.domain.Extract;
import com.game.util.domain.Message;
import com.game.util.domain.Order;
import com.game.util.domain.Post;
import com.game.util.domain.User;

public class MessageUtil {

	private static MessageService messageService;
	private static PostService postService;
	private static AssignService assignService;

	private static void getservice() {
		if (messageService == null || assignService == null || postService == null) {
			if (messageService == null) {
				messageService = (MessageService) SpringUtil.getBean("messageService");
			}
			if (assignService == null) {
				assignService = (AssignService) SpringUtil.getBean("assignService");
			}
			if (postService == null) {
				postService = (PostService) SpringUtil.getBean("postService");
			}
		}
	}

	/**
	 * @param title 4.提现申请通过 5.提现申请拒绝
	 */
	public static void toMessage(int title, Extract extract, String time, User user) throws Exception {
		getservice();// 获得服务
		Message message = new Message();
		message.setTime(time);
		message.setAddresser("游戏买卖网");
		message.setType(1);

		getTitle(title, extract, message);
		messageService.createEntity(message);
		Post post = new Post();
		post.setMessage(message);
		post.setUser(user);
		post.setType(1);
		post.setState(0);
		postService.updateEntity(post);
	}

	/**
	 * 提现申请获得标题 4.提现申请通过 5.提现申请拒绝
	 */
	private static void getTitle(int title, Extract extract, Message message) throws Exception {
		switch (title) {
		case 4: {
			message.setTitle("您的提现申请已通过，请查收");
			setContent(extract, message);
			break;
		}
		case 5: {
			message.setTitle("您的提现申请被拒绝，如有疑问请直接联系客服");
			setContent(extract, message);
			break;
		}
		}
	}

	/**
	 * 提现申请站内信 内容
	 */
	private static void setContent(Extract extract, Message message) throws Exception {
		String content = "";
		content += "交易编号：" + extract.getExtractNum();
		content += "<br />";
		content += "提现金额：" + extract.getMoney();
		content += "<br />";
		content += "手续费：" + extract.getCharge();
		content += "<br />";
		content += "提现银行：" + extract.getBank();
		content += "<br />";
		content += "银行账号：" + extract.getAccount();
		content += "<br />";
		content += "申请时间：" + extract.getTime();
		content += "<br />";
		content += "处理时间：" + extract.getProcessTime();
		message.setContent(content);
	}

	/**
	 * @param title
	 *            1.拍下宝贝时 2.付款时 3.商家已发货时 4.退款申请通过 5.退款申请拒绝 6.寄售订单已成功售出
	 */
	public static void toMessage(int title, Order order, String time, User user) throws Exception {
		getservice();// 获得服务
		Message message = new Message();
		message.setTime(time);
		message.setAddresser("游戏买卖网");
		message.setType(1);
		getTitle(title, order, message);
		messageService.createEntity(message);
		Post post = new Post();
		post.setMessage(message);
		post.setUser(user);
		post.setType(1);
		post.setState(0);
		postService.saveorupdate(post);
	}

	/**
	 * Message private String title; //信息标题 private String content; //信息内容
	 * private String time; //信息时间 private String addresser; //发布者 private
	 * Integer type; //信息类别：1.系统信息
	 * Post private Message message; //信息id private User user; //用户id private
	 * Integer type; //类别：1，收件，0，发件 private Integer state; //状态：1，已读
	 */

	/**
	 * 交易过程站内信 获得标题 1.拍下宝贝时 2.付款时 3.商家已发货时 4.退款申请通过 5.退款申请拒绝 6.寄售订单已成功售出
	 */
	private static void getTitle(int title, Order order, Message message) throws Exception {
		switch (title) {
		case 1: {
			message.setTitle("您已拍下物品请尽快付款");
			setContent(order, message);
			break;
			// 订单编号+订单标题+单价 +数量+总价
		}
		case 2: {
			if (order.getBuyType() == 1) {
				message.setTitle("您已付款，请耐心等待交易员发货");
			} else {
				message.setTitle("您已付款，请耐心等待商家发货");
			}
			setContent(order, message);
			break;
		}
		case 3: {
			message.setTitle("商家已发货请查收");
			setContent(order, message);
			break;
		}
		case 4: {
			message.setTitle("您的退款申请已通过，请查收");
			setContent(order, message);
			break;
		}
		case 5: {
			message.setTitle("您的退款申请被拒绝，如有疑问请直接联系客服");
			setContent(order, message);
			break;
		}
		case 6: {
			message.setTitle("你的寄售订单已成功售出，相应款项已打入您的账户请查收。");
			setContent(order, message);
			break;
		}
		}

	}

	/**
	 * 交易状态更改 站内信提醒
	 */
	private static void setContent(Order order, Message message) throws Exception {
		String content = "商品标题：<a href=\"/user/trade/bizInfo/detail.shtml?bizInfoID=" + order.getBizInfo().getId() + "\">" + order.getTitle();
		content += "</a><br />";
		content += "游戏/区/服：";
		if (order.getGame() != null) {
			content += order.getGame().getGameName();
		} else {
			content += order.getServer().getArea().getGame().getGameName() + "/";
			content += order.getServer().getArea().getAreaName() + "/";
			content += order.getServer().getServerName();
		}
		content += "<br />";
		content += "订单号：" + order.getOrderNum();
		content += "<br />";
		content += "单价：" + order.getPrice();
		content += "<br />";
		content += "发货件数：" + order.getBuyBum();
		content += "<br />";
		content += "总价：" + order.getSumPrice();
		content += "<br />";
		content += "买家信息：";
		content += "<br />";
		content += "&nbsp;&nbsp;&nbsp;&nbsp;游戏角色名：" + order.getPlayerRole();
		content += "<br />";
		content += "&nbsp;&nbsp;&nbsp;&nbsp;买家角色等级：" + order.getPlayerGrade();
		content += "<br />";
		if (order.getSite().trim().equals("邮寄")) {
			content += "&nbsp;&nbsp;&nbsp;&nbsp;交易方式：" + order.getSite();
		} else {
			content += "&nbsp;&nbsp;&nbsp;&nbsp;交易地点：" + order.getSite();
		}
		content += "<br />";
		content += "买家联系电话：" + order.getPlayPhoneNum();
		content += "<br />";
		content += "买家联系QQ：" + order.getPlayQQ();
		content += "<br />";
		Assign assign = assignService.getAssignByOrderNum(order.getOrderNum(), null, null);
		if (assign != null) {
			content += "交易员联系QQ：" + assign.getManage().getQq();
		}
		message.setContent(content);
	}
}
