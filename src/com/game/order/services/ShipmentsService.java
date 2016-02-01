package com.game.order.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Shipments;

public interface ShipmentsService extends GenericService<Shipments, Long> {
	/**
	 * @name 创建一个实体
	 */
	public Shipments createShipments(Shipments shipments) throws Exception;

	/**
	 * @name 更新或创建一个集合
	 */
	public void updateShipments(List<Shipments> list) throws Exception;

	/**
	 * @name 更新一个实体
	 */
	public void updateShipments(Shipments shipments) throws Exception;

	/**
	 * @name 根据ID获得实体
	 */
	public Shipments getShipmentsById(Long id) throws Exception;

	/**
	 * @name 根据订单获得
	 */
	public List<Shipments> getShipments(Long order_id) throws Exception;

	/**
	 * @name 根据ID删除一个实体
	 */
	public void removeShipments(Long id) throws Exception;

	/**
	 * @name 删除一个实体
	 */
	public void removeShipments(Shipments shipments) throws Exception;

	/**
	 * @name 删除实体
	 */
	public void removeShipments(List<Shipments> list) throws Exception;

}
