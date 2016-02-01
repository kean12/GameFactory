package com.game.util.domain;

import java.util.ArrayList;
import java.util.List;

import sun.net.util.IPAddressUtil;

import com.game.util.web.Validator;
public class Manage implements Domain {
	public static final long serialVersionUID = -4225197768382582908L;
	private Long id;// 编号
	private String username;// 管理员登录名称
	private String password;// 登录密码
	private String password2;
	private String name;// 真实姓名
	private String registerTime;// 添加时间
	private String loginTime;// 登录时间
	private String ip;// 登录地址
	private String regip;// 添加地址
	private String tmpTime;// 上一次登录时间
	private Integer isuse;// 是否启用
	private String lockAccessIp;// 后台用户绑定IP设置，每个IP以"，"分割
	private Integer state; // 登录状态 b/s小软件控制
	private String qq;// 联系QQ

	private Integer limits;// 权限--已去除（数据库映射字段）
	private Role role;

	/*
	 * 判断帐户的IP限制规则
	 * 
	 * 1，当允许ip [getAccessIp()]为空，锁定ip [getLockIp()]为空 ，通过
	 * 
	 * 2，当允许ip [getAccessIp()]不为空且包含该IP时，通过
	 * 
	 * 3，当允许ip [getAccessIp()]不为空且不包含该IP时，不通过
	 * 
	 * 4，当允许ip [getAccessIp()]为空，锁定ip [getLockIp()]不为空 且锁定ip包含IP时，不通过
	 * 
	 * 5，当允许ip [getAccessIp()]为空，锁定ip [getLockIp()]不为空 且锁定ip不包含IP时，通过
	 * 
	 */
	public List<String> getAccessIp() {
		return parseLockIp("^");
	}

	public List<String> getLockIp() {
		return parseLockIp("!");
	}

	private List<String> parseLockIp(String s) {
		if (Validator.isBlank(lockAccessIp))
			return null;
		List<String> list = new ArrayList<String>();
		String[] ips = lockAccessIp.split(",");
		for (int i = 0; i < ips.length; i++) {
			if (!Validator.isBlank(ips[i]) && ips[i].indexOf(s) != -1
					&& IPAddressUtil.isIPv4LiteralAddress(ips[i].substring(1))) {
				list.add(ips[i].substring(1));
			}
		}
		return list;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLimits() {
		return limits;
	}

	public void setLimits(Integer limits) {
		this.limits = limits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRegip() {
		return regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	public String getTmpTime() {
		return tmpTime;
	}

	public void setTmpTime(String tmpTime) {
		this.tmpTime = tmpTime;
	}

	public Integer getIsuse() {
		return isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getLockAccessIp() {
		return lockAccessIp;
	}

	public void setLockAccessIp(String lockAccessIp) {
		this.lockAccessIp = lockAccessIp;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Override
	public String toString() {
		return "权限为 " + role.getDescription() + " 的 " + username;
	}
}
