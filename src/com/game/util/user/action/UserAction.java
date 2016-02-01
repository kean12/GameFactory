package com.game.util.user.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.game.order.services.OrderService;
import com.game.util.admin.message.services.MessageService;
import com.game.util.admin.message.services.PostService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Order;
import com.game.util.domain.Particulars;
import com.game.util.domain.Post;
import com.game.util.domain.User;
import com.game.util.domain.UserInfo;
import com.game.util.domain.UserRole;
import com.game.util.user.services.ParticularsService;
import com.game.util.user.services.UserInfoService;
import com.game.util.user.services.UserRoleService;
import com.game.util.user.services.UserService;
import com.game.util.web.Help;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;

public class UserAction extends BaseAction {
	private static final long serialVersionUID = -7507531382288549211L;
	private MessageService messageService;
	private UserService userService;
	private OrderService orderService;
	private UserRoleService userRoleService;
	private UserInfoService userInfoService;
	private PostService postService;
	private ParticularsService particularsService;
	private int notRead = 0;
	private Page<?> page;
	private List<Post> postList;
	private List<Particulars> particularsList;
	private Integer type;
	private Post post;
	private Long postID;

	private String orderNum;
	private String runningNum;
	private String bank;
	private String beginTime;
	private String endTime;
	private Integer buyerAssessCount;
	private Integer sellerAssessCount;
	private Integer buyerCount;
	private Integer sellerCount;

	private Long idKey[];
	private UserRole userRole;
	private Long roleID;
	private List<UserRole> userRoleList;

	private String bankName; // 银行名称
	private String bankSite; // 开户所在地
	private String bankNum; // 银行账号
	private String bankUserName;

	private File imgFile;
	private String imgFileContentType;
	private String imgFileFileName;
	private boolean reload;

	/**
	 * 用户中心首页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String home() throws Exception {
		User user = Struts2Util.getUserSession();
		notRead = postService.findPostByCount(user.getId(), 1, 0);
		List<Order> buyOrder = orderService.findBuyerOrder(user.getId());
		buyerAssessCount = buyOrder.size();
		List<Order> sellOrder = orderService.findSellerOrder(user.getId());
		sellerAssessCount = sellOrder.size();
		Page<Order> page1 = orderService.searchOrder(user.getId(), 6, 0, 5, super.getGoPage());
		buyerCount = page1.getResultlist().size();
		Page<Order> page2 = orderService.searchOrder(user.getId(), 6, 1, 5, super.getGoPage());
		sellerCount = page2.getResultlist().size();
		return "home";
	}

	/**
	 * @name 上传头像
	 */
	public String portrait() throws Exception {
		return "portrait";
	}

	/**
	 * @name 上传头像--保存
	 */
	public String portraitSave() throws Exception {
		reload = false;
		if (imgFile != null) {
			User user = Struts2Util.getUserSession();
			String subdirectory = user.getUsername() + "/portrait";
			// 删除之前的头像
			Help.removeTempImage(Struts2Util.getRealPath(user.getUserInfo().getRoute()));
			
			Help.chkImage(imgFile, imgFileFileName, 30);//图片验证
			String saveUrl = Help.uploadImageToUserPath(imgFile, imgFileFileName, subdirectory);
			user = userService.getEntity(User.class, user.getId());
			user.getUserInfo().setRoute(saveUrl);
			userService.updateEntity(user);
			Struts2Util.setUserSession(user);
			reload = true;
		}
		return portrait();
	}

	/**
	 * @name 我的账户
	 */
	public String account() throws Exception {
		User user = Struts2Util.getUserSession();
		Struts2Util.setUserSession(userService.getUserById(user.getId()));
		return "account";
	}

	/**
	 * @name 账户明细
	 */
	@SuppressWarnings("unchecked")
	public String particulars() throws Exception {
		User user = Struts2Util.getUserSession();
		page = particularsService.findParticulars(user.getId(), orderNum, runningNum, bank, type, beginTime, endTime, 20, super.getGoPage());
		particularsList = (List<Particulars>) page.getResultlist();
		return "particulars";
	}

	/**
	 * @name 收货角色管理
	 */
	public String role() throws Exception {
		User user = Struts2Util.getUserSession();
		userRoleList = userRoleService.findUserRoleByUser(user.getId());
		return "role";
	}

	/**
	 * @name 收货角色删除
	 */
	public String deleteRole() throws Exception {
		if (roleID != null) {
			userRoleService.removeUserRole(roleID);
		}
		return "deleteRole";
	}

	/**
	 * @name 收货角色添加修改
	 */
	public String saveRole() throws Exception {
		if (userRole != null && userRole.getRoleName() != null && !userRole.getRoleName().trim().equals("")) {
			User user = Struts2Util.getUserSession();
			userRole.setUser(user);
			userRoleService.createUserRole(userRole);
		}

		return "saveRole";
	}

	/**
	 * @name 修改银行信息
	 */
	public String bank() throws Exception {
		User user = Struts2Util.getUserSession();
		if (user != null) {
			user = userService.getUserById(user.getId());
			Struts2Util.setUserSession(user);
		}
		return "bank";
	}

	/**
	 * @name 修改银行信息----提交
	 */
	public String updateBank() throws Exception {
		if (bankName == null || bankNum == null || bankSite == null) {
			throw new Exception("非法操作");
		}
		User user = Struts2Util.getUserSession();
		if (user.getRealName() == null) {
			throw new Exception("<a class=\"red\" href=\"/user/account/my_account.shtml\">请先设置你的身份证和真实姓名</a>");
		}

		if (!user.getRealName().equals(bankUserName)) {
			throw new Exception("真实姓名和开户名必须一致");
		}

		UserInfo userInfo = user.getUserInfo();
		userInfo.setBankName(bankName);
		if (!bankNum.substring(12, 16).equals("****")) {
			userInfo.setBankNum(bankNum);
		}

		userInfo.setBankSite(bankSite);
		userInfoService.updateUserInfo(userInfo);

		return "updateBank";
	}

	/**
	 * 站内信
	 */
	@SuppressWarnings("unchecked")
	public String message() throws Exception {
		User user = Struts2Util.getUserSession();
		if (type != null) {
			page = postService.searchPost(user.getId(), 1, null, 15, super.getGoPage());
		} else {
			page = postService.searchPost(user.getId(), 1, 0, 15, super.getGoPage());
		}
		postList = (List<Post>) page.getResultlist();
		return "message";
	}

	public String read() throws Exception {
		User user = Struts2Util.getUserSession();
		if (postID == null) {
			return "inputmessage";
		}
		post = postService.getPost(postID, user.getId());
		if (post.getState() == null || post.getState() != 1) {
			post.setState(1);
			postService.updateEntity(post);
		}
		return "read";
	}

	public String deleteMessage() throws Exception {
		if (postID == null) {
			return "inputmessage";
		}
		User tmp_user = Struts2Util.getUserSession();
		Post tmp_post = postService.getPost(postID, tmp_user.getId());
		if (tmp_post != null) {
			postService.removeEntity(tmp_post);
		}

		return "deleteMessage";
	}

	public String deleteMessageAll() throws Exception {
		if (idKey != null && idKey.length > 0) {
			User tmp_user = Struts2Util.getUserSession();
			Post tmp_post = null;
			for (Long id : idKey) {
				tmp_post = postService.getPost(id, tmp_user.getId());
				if (tmp_post != null) {
					postService.removeEntity(tmp_post);
				}
			}
		}
		return "deleteMessageAll";
	}

	// *****************************
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getNotRead() {
		return notRead;
	}

	public void setNotRead(int notRead) {
		this.notRead = notRead;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public Page<?> getPage() {
		return page;
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getPostID() {
		return postID;
	}

	public void setPostID(Long postID) {
		this.postID = postID;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public ParticularsService getParticularsService() {
		return particularsService;
	}

	public void setParticularsService(ParticularsService particularsService) {
		this.particularsService = particularsService;
	}

	public List<Particulars> getParticularsList() {
		return particularsList;
	}

	public void setParticularsList(List<Particulars> particularsList) {
		this.particularsList = particularsList;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum)
			throws UnsupportedEncodingException {
		if (orderNum != null && !orderNum.trim().equals("")) {
			this.orderNum = URLDecoder.decode(orderNum, "utf-8");
			;
		} else {
			this.orderNum = null;
		}

	}

	public String getRunningNum() {
		return runningNum;
	}

	public void setRunningNum(String runningNum)
			throws UnsupportedEncodingException {
		if (runningNum != null && !runningNum.trim().equals("")) {
			this.runningNum = URLDecoder.decode(runningNum, "utf-8");
			;
		} else {
			this.runningNum = null;
		}
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) throws UnsupportedEncodingException {
		if (bank != null && !bank.trim().equals("")) {
			this.bank = URLDecoder.decode(bank, "utf-8");
			;
		} else {
			this.bank = null;
		}
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Integer getBuyerAssessCount() {
		return buyerAssessCount;
	}

	public void setBuyerAssessCount(Integer buyerAssessCount) {
		this.buyerAssessCount = buyerAssessCount;
	}

	public Integer getSellerAssessCount() {
		return sellerAssessCount;
	}

	public void setSellerAssessCount(Integer sellerAssessCount) {
		this.sellerAssessCount = sellerAssessCount;
	}

	public Integer getBuyerCount() {
		return buyerCount;
	}

	public void setBuyerCount(Integer buyerCount) {
		this.buyerCount = buyerCount;
	}

	public Integer getSellerCount() {
		return sellerCount;
	}

	public void setSellerCount(Integer sellerCount) {
		this.sellerCount = sellerCount;
	}

	public Long[] getIdKey() {
		return idKey;
	}

	public void setIdKey(Long[] idKey) {
		this.idKey = idKey;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		if (bankName != null && !bankName.trim().equals("")) {
			this.bankName = bankName;
		} else {
			this.bankName = null;
		}
	}

	public String getBankSite() {
		return bankSite;
	}

	public void setBankSite(String bankSite) {
		if (bankSite != null && !bankSite.trim().equals("")) {
			this.bankSite = bankSite;
		} else {
			this.bankSite = null;
		}
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		if (bankNum != null && !bankNum.trim().equals("")) {
			this.bankNum = bankNum;
		} else {
			this.bankNum = null;
		}
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		if (bankUserName != null && !bankUserName.trim().equals("")) {
			this.bankUserName = bankUserName;
		} else {
			this.bankUserName = null;
		}
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public boolean isReload() {
		return reload;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}

}
