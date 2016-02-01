package com.game.util.domain;

public class Picture implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8352770273260275131L;
	private Long id;
	private BizInfo bizInfo;
	private String route;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BizInfo getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(BizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

}
