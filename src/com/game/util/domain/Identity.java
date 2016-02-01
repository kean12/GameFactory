package com.game.util.domain;

/**
 * 身份证保存路径
 */
public class Identity implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3085517311825442299L;
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
