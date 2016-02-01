<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.game.util.domain.User"%>
<%@page import="com.game.util.user.services.UserService"%>
<%@page import="com.game.util.user.services.impl.UserServiceImpl"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>
<%-- 文件头部 --%>

<!--左侧菜单 -->
  <div class="yhzx_leftside">
    <dl class="yhzx_left_info">
      <dt>
      	<img src="
      		<s:if test="userSession.userInfo.route==null">${ctx}/images/00001.jpg</s:if>
      		<s:else>${ctx}${userSession.userInfo.route}</s:else>
      	" onclick="alertOpen('修改头像','${ctx}/user/portrait.shtml',480,315);" style="cursor: pointer;" />
      </dt>
      <dd><strong>用户名：</strong>${userSession.username}<br />
        <strong>可用余额：</strong>${userSession.userInfo.money}<br />
        <strong>冻结金额：</strong>${userSession.userInfo.freemoney}<br />
      </dd>
    </dl>
    <dl class="yhzx_left_nav">
      <dt><a href="#">欢迎页面</a></dt>
	      <dd><a href="${ctx}/user/home.shtml">用户中心首页</a></dd>
	      <dd><a href="${ctx}/user/message/message.shtml">站内消息</a></dd>
	      <dd><a href="${ctx}/user/trade/order/list_complaint.shtml">投诉列表</a></dd>
	      <dd><a href="${ctx}/user/trade/credit/userCredit1.shtml?type=1&userID=${userSession.id}">我的信誉</a></dd>
      <dt><a href="#">我是买家</a></dt>
      	<dd><a href="${ctx}/user/trade/order/list_bought_items.shtml">已买入的宝贝</a></dd>
      <dt><a href="#">我是卖家</a></dt>
	      <dd><a href="${ctx}/user/trade/order/list_sold_items.shtml">已卖出的宝贝</a></dd>
	      <dd><a href="${ctx}/user/trade/bizInfo/sale.shtml">出售中的宝贝</a></dd>
	      <dd><a href="${ctx}/user/trade/bizInfo/storehouse.shtml">下架的宝贝</a></dd>
	      <dd><a href="${ctx}/user/trade/bizInfo/publish.shtml">发布商品</a></dd>
      <dt><a href="#">我要充值</a></dt>
	      <dd><a href="${ctx}/user/charge/deposit.shtml">立即充值</a></dd>
	      <dd><a href="${ctx}/user/charge/record.shtml">充值记录</a></dd>
      <dt><a href="#">我要提现</a></dt>
	      <dd><a href="${ctx}/user/extract/extract_apply.shtml">提现申请</a></dd>
	      <dd><a href="${ctx}/user/extract/list_extract_record.shtml">提现记录</a></dd>
      <dt><a href="#">我的账户</a></dt>
	      <dd><a href="${ctx}/user/account/my_account.shtml">我的账户</a></dd>
	      <dd><a href="${ctx}/user/account/particulars.shtml">账户明细</a></dd>
    </dl>
  </div>
