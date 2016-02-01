<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>
<%-- 文件头部 --%>
<div class="main_menu contaner">
	<s:if test="userSession==null">
		<div><a href="${ctx}/user/register.shtml">马上注册</a></div>
	</s:if>
	<ul>
		<li><a href="${ctx}/index.shtml" class="topmenu_normal" id="indexNav">首 页</a></li>
	  	<li><a href="${ctx}/user/trade/bizInfo/publish.shtml" class="topmenu_normal" id="saleNav">出售</a></li>
	  	<li><a href="${ctx}/user/trade/bizInfo/search.shtml" class="topmenu_normal" id="buyNav">购买</a></li>
	  	<li><a href="${ctx}/user/home.shtml" class="topmenu_normal" id="userNav">我的买卖</a></li>
	  	<li><a href="${ctx}/user/charge/deposit.shtml" class="topmenu_normal" id="moneyNav">充值</a></li>
	  	<li><a href="${ctx}/user/extract/extract_apply.shtml" class="topmenu_normal" id="moneyNav">提现</a></li>
	</ul>
</div>
