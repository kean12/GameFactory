package com.game.order.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Order;
import com.game.util.web.Page;

public interface OrderService extends GenericService<Order, Long> {

	/**
	 * 获得分页数据
	 * @param size
	 * @param goPage
	 * @param filterOrder 过滤对象
	 * @return Page
	 * @throws Exception
	 */
	Page<Order> getOrderPage(int size, int goPage, String orderTimeStart,
			String orderTimeEnd, String succTimeStart, String succTimeEnd,
			double sumPriceMin, double sumPriceMax, Order filterOrder)
			throws Exception;

	/**
	 * @name 创建一个订单
	 */
	public Order createOrder(Order order) throws Exception;

	/**
	 * @name 获得语句更新订单
	 */
	public void updateOrder(String hql) throws Exception;

	public Order getOrder(Long owner_id, Long consumer_id, Long order_id) throws Exception;

	public Order getOrder(String orderNum, Long consumer_id) throws Exception;

	/**
	 * @name 根据ID删除一个订单
	 */
	public void removeOrder(Long id) throws Exception;

	/**
	 * @name 获得订单
	 * @param userID
	 *            用户ID
	 * @param state
	 *            状态
	 * @param type
	 *            类型
	 * @param size
	 *            每页条数
	 * @param goPage
	 *            第几页
	 */
	public Page<Order> searchOrder(Long userID, Integer state, Integer type, int size, int goPage) throws Exception;

	public List<Order> findOrderByCount(int state, int count) throws Exception;

	/**
	 * 根据hql语句查询
	 */
	public List<Order> findOrderByHql(String hql) throws Exception;

	public List<Object[]> game_ranking() throws Exception;

	public List<Object[]> game_ranking_merchant(Long gameID) throws Exception;

	/**
	 * 查询作为卖家待评价的订单数量
	 * 
	 * @param ownerID
	 * @return
	 * @throws Exception
	 */
	public List<Order> findSellerOrder(Long ownerID) throws Exception;

	/**
	 * 查询作为买家待评价的订单数量
	 * 
	 * @param consumerID
	 * @return
	 * @throws Exception
	 */
	public List<Order> findBuyerOrder(Long consumerID) throws Exception;

	/**
	 * 获得寄售订单
	 */
	public Page<Order> findOrderBySale(String ownerName, String consumerName,
			String orderNum, Integer state, String startTime, String endTime,
			int size, int goPage) throws Exception;

}
