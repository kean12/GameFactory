package com.game.util.system.action;

import java.util.List;

import com.game.order.services.OrderService;
import com.game.util.admin.affiche.services.AfficheService;
import com.game.util.admin.game.services.GameService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Affiche;
import com.game.util.domain.Game;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.CryptTool;
import com.game.util.web.MD5;
import com.game.util.web.MailUtil;
import com.game.util.web.Struts2Util;
import com.game.util.web.TradeMD5;

public class IndexAction extends BaseAction {
	private static final long serialVersionUID = 2873601361810506068L;
	private AfficheService afficheService;
	private List<Affiche> afficheList;
	private List<Affiche> newsList;
	private OrderService orderService;
	private List<Order> orderList;
	private List<Object[]> rankList;
	private List<Object[]> rank_merchant_list;
	private Long gameID;
	private Game game;
	private GameService gameService;

	private String username;
	private Integer pswtype;// 1.登录密码 2.支付密码
	private Integer findtype;
	private String vercode;

	private String email;
	private String question;
	private String answer;
	private String password;

	private UserService userService;

	/**
	 * @name 首页
	 */
	public String indexPage() throws Exception {
		afficheList = afficheService.findAfficheByCount(1, 1, 7);// 公告
		newsList = afficheService.findAfficheByCount(2, 1, 5);// 最新动态
		orderList = orderService.findOrderByCount(6, 9);// 成功订单
		rankList = orderService.game_ranking();// 游戏排行

		return "index";
	}

	/**
	 * @name 游戏排行榜
	 */
	public String ranking() throws Exception {
		rankList = orderService.game_ranking();// 游戏排行
		if (gameID != null) {
			rank_merchant_list = orderService.game_ranking_merchant(gameID);
			game = gameService.getEntity(Game.class, gameID);
		}
		return "ranking";
	}

	/**
	 * 忘记密码
	 */
	public String findpswIndex() throws Exception {
		return "findpswIndex";
	}

	/**
	 * 找回密码--一
	 */
	public String findpswAnswer() throws Exception {
		if (username == null || pswtype == null || findtype == null || vercode == null) {
			return INPUT;
		}
		String ver2 = (String) Struts2Util.getSession().get("randomCode");
		if (!ver2.equals(vercode)) {
			throw new Exception("验证码不正确!");
		}

		username = CryptTool.base64Encode(username);
		return "findpswAnswer";
	}

	/**
	 * 找回密码
	 */
	public String findpswSubmit() throws Exception {
		User user;
		if (findtype == 2) {
			if (email == null) {
				return INPUT;
			}
			username = CryptTool.base64Decode(username);
			user = userService.findUserByName(username, 1);
			if (user == null || !user.getEmail().equals(email)) {
				throw new Exception("填写的邮箱与用户注册邮箱不一致");
			}
			MailUtil.getPassword(1, user);// 发送邮件

			email = CryptTool.base64Encode(email);// url重定向 加密
		} else {
			if (question == null || question == null || password == null) {
				return INPUT;
			}
			username = CryptTool.base64Decode(username);
			user = userService.findUserByName(username, 1);

			if (!user.getAnswer().equals(answer) || !user.getHint().equals(question)) {
				throw new Exception("密码提示问题回答不正确");
			}

			if (pswtype == 1) {// 登录密码
				user.setPassword(TradeMD5.toMD5(password));
				userService.updateUser(user);
			} else if (pswtype == 2) {// 支付密码
				user.getUserInfo().setApplyPwd(MD5.toMD5(password));
				userService.updateUser(user);
			}

		}

		return "findpswSubmit";
	}

	public String findpswSuccess() throws Exception {
		if (email != null) {
			email = CryptTool.base64Decode(email);
		}
		return "findpswSuccess";
	}

	// /****************************************************
	public AfficheService getAfficheService() {
		return afficheService;
	}

	public void setAfficheService(AfficheService afficheService) {
		this.afficheService = afficheService;
	}

	public List<Affiche> getAfficheList() {
		return afficheList;
	}

	public void setAfficheList(List<Affiche> afficheList) {
		this.afficheList = afficheList;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Long getGameID() {
		return gameID;
	}

	public void setGameID(Long gameID) {
		this.gameID = gameID;
	}

	public List<Object[]> getRank_merchant_list() {
		return rank_merchant_list;
	}

	public void setRank_merchant_list(List<Object[]> rank_merchant_list) {
		this.rank_merchant_list = rank_merchant_list;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPswtype() {
		return pswtype;
	}

	public void setPswtype(Integer pswtype) {
		this.pswtype = pswtype;
	}

	public Integer getFindtype() {
		return findtype;
	}

	public void setFindtype(Integer findtype) {
		this.findtype = findtype;
	}

	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null && !email.trim().equals("")) {
			this.email = email.trim();
		} else {
			this.email = null;
		}
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		if (question != null && !question.trim().equals("")) {
			this.question = question.trim();
		} else {
			this.question = null;
		}
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		if (answer != null && !answer.trim().equals("")) {
			this.answer = answer.trim();
		} else {
			this.answer = null;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<Affiche> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<Affiche> newsList) {
		this.newsList = newsList;
	}

	public List<Object[]> getRankList() {
		return rankList;
	}

	public void setRankList(List<Object[]> rankList) {
		this.rankList = rankList;
	}

}