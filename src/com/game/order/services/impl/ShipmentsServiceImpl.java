package com.game.order.services.impl;

import java.util.List;

import com.game.order.services.ShipmentsService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Shipments;

public class ShipmentsServiceImpl extends GenericServiceImpl<Shipments, Long> implements ShipmentsService {
	public Shipments createShipments(Shipments shipments) throws Exception {
		return baseDAO.saveEntity(shipments);
	}

	public void updateShipments(List<Shipments> list) throws Exception {
		baseDAO.saveorupdatecoll(list);
	}

	public List<Shipments> getShipments(Long orderId) throws Exception {
		return baseDAO.findEntity("from Shipments a where a.order.id=? order by a.time", orderId);
	}

	public Shipments getShipmentsById(Long id) throws Exception {
		return baseDAO.getEntity(Shipments.class, id);
	}

	public void removeShipments(Long id) throws Exception {
		baseDAO.removeEntity(this.getShipmentsById(id));
	}

	public void removeShipments(Shipments shipments) throws Exception {
		baseDAO.removeEntity(shipments);
	}

	public void removeShipments(List<Shipments> list) throws Exception {
		baseDAO.removeAllEntity(list);
	}

	public void updateShipments(Shipments shipments) throws Exception {
		baseDAO.updateEntity(shipments);
	}

}
