package com.game.order.services.impl;

import java.util.List;
import java.util.Random;

import com.game.order.services.OrderService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Order;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class OrderServiceImpl extends GenericServiceImpl<Order, Long> implements OrderService {
	public Order createOrder(Order order) throws Exception {
		String riqi = DateUtil.nowDate(Constant.YYYYMMDD);
		Random rd = new java.util.Random(System.currentTimeMillis());
		long num = rd.nextLong();
		num = Math.abs(num);
		order.setOrderNum(riqi + String.valueOf(num).substring(9));// 订单编号
		return baseDAO.saveEntity(order);
	}

	public Order getOrder(Long ownerId, Long consumerId, Long orderId) throws Exception {
		String hql = "from Order a where 1=1";
		if (ownerId != null) {
			hql += " and a.owner.id=" + ownerId;
		}
		if (consumerId != null) {
			hql += " and a.consumer.id=" + consumerId;
		}
		if (orderId != null) {
			hql += " and a.id=" + orderId;
		}
		List<Order> list = baseDAO.findEntity(hql);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public Order getOrder(String orderNum, Long consumerId) throws Exception {
		String hql = "from Order a where a.orderNum=? and a.consumer.id=" + consumerId;
		List<Order> list = baseDAO.findEntity(hql, orderNum);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public void removeOrder(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Order.class, id));
	}

	public Page<Order> searchOrder(Long userID, Integer state, Integer type, int size, int goPage) throws Exception {
		String hql = "from Order a where 1=1";
		if (userID != null) {
			if (type == 1) {// 卖
				hql += " and a.owner=" + userID;
			} else { // 买
				hql += " and a.consumer=" + userID;
			}
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		hql += "order by a.orderTime desc";
		return baseDAO.search(hql, size, goPage);
	}

	public void updateOrder(String hql) throws Exception {
		baseDAO.updateEntity(hql);
	}

	public List<Order> findOrderByCount(int state, int count) throws Exception {
		String hql = "from Order a where a.state=" + state + " and a.bizInfo.serial is not null order by a.orderTime desc";
		return baseDAO.findEntity(hql, 0, count);
	}

	public List<Order> findOrderByHql(String hql) throws Exception {
		return baseDAO.findEntity(hql);
	}

	public List<Object[]> game_ranking() throws Exception {
		String hql = "select a.server.area.game.gameName,a.server.area.game.id,count(*) from Order a where a.state=6 and a.game.id is null group by a.server.area.game.gameName,a.server.area.game.id order by count(*) desc";
		return baseDAO.findListArray(hql, 0, 10);
	}

	public List<Object[]> game_ranking_merchant(Long gameID) throws Exception {
		String hql = "select a.owner.username,a.owner.id,count(*) from Order a where a.state=6 and a.server.area.game.id=" + gameID
				+ " and a.game.id is null group by a.owner.username,a.owner.id order by count(*) desc";
		return baseDAO.findListArray(hql, 0, 50);
	}

	public List<Order> findSellerOrder(Long ownerID) throws Exception {
		String hql = "from Order o where o.state = 6 and o.isAssess in(0,1,2) and o.owner.id=" + ownerID;
		return baseDAO.findEntity(hql);
	}

	public List<Order> findBuyerOrder(Long consumerID) throws Exception {
		String hql = "from Order o where o.state = 6 and o.isAssess in(0,1,3) and o.consumer.id=" + consumerID;
		return baseDAO.findEntity(hql);
	}

	public Page<Order> getOrderPage(int size, int goPage,
			String orderTimeStart, String orderTimeEnd, String succTimeStart,
			String succTimeEnd, double sumPriceMin, double sumPriceMax,
			Order filterOrder) throws Exception {
		
		String hql = "from Order o where 1=1 ";
		if (filterOrder != null) {
			if (!Validator.isBlank(filterOrder.getOrderNum())) {
				hql += " and o.orderNum='" + filterOrder.getOrderNum() + "'";
			}
			if (filterOrder.getState() != null && filterOrder.getState().intValue() >= 0) {
				hql += " and o.state=" + filterOrder.getState().intValue();
			}
			if (filterOrder.getOwner() != null && !Validator.isBlank(filterOrder.getOwner().getUsername())) {
				hql += " and o.owner.username like '%" + filterOrder.getOwner().getUsername() + "%'";
			}
			if (filterOrder.getConsumer() != null && !Validator.isBlank(filterOrder.getConsumer().getUsername())) {
				hql += " and o.consumer.username like '%" + filterOrder.getConsumer().getUsername() + "%'";
			}
		}
		if (!Validator.isBlank(orderTimeStart)) {
			hql += " and o.orderTime >= '" + orderTimeStart + "'";
		}
		if (!Validator.isBlank(orderTimeEnd)) {
			hql += " and o.orderTime <= '" + orderTimeEnd + "'";
		}
		if (!Validator.isBlank(succTimeStart)) {
			hql += " and o.succTime >= '" + succTimeStart + "'";
		}
		if (!Validator.isBlank(succTimeEnd)) {
			hql += " and o.succTime <= '" + succTimeEnd + "'";
		}
		if (sumPriceMin > 0) {
			hql += " and to_number(o.sumPrice) >= " + sumPriceMin;
		}
		if (sumPriceMax > 0) {
			hql += " and to_number(o.sumPrice) <= " + sumPriceMax;
		}
		return baseDAO.search(hql, size, goPage);
	}

	public Page<Order> findOrderBySale(String ownerName, String consumerName,
			String orderNum, Integer state, String startTime, String endTime,
			int size, int goPage) throws Exception {
		String hql = "from Order a where a.buyType=1";
		if (ownerName != null) {
			hql += " and a.owner.username like '%" + ownerName + "%'";
		}

		if (consumerName != null) {
			hql += " and a.consumer.username like '%" + consumerName + "%'";
		}

		if (orderNum != null) {
			hql += " and a.orderNum='" + orderNum + "'";
		}

		if (state != null) {
			hql += " and a.state=" + state;
		}

		if (startTime != null) {
			hql += " and a.orderTime >='" + startTime + "'";
		}

		if (endTime != null) {
			hql += " and a.orderTime <='" + endTime + " 23:59:59'";
		}

		return baseDAO.search(hql, size, goPage);
	}

}
