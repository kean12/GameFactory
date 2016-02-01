<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="yhzx_rightside">
			<div class="yhzx_title">
				用户中心
			</div>
			<div class="yhzx_hy">
				<strong class="f18 blue">${userSession.username}</strong>（游戏买卖网欢迎您！）
				<br />
				可用余额：${userSession.userInfo.money} （<a href="${ctx}/user/charge/deposit.shtml" class="orange">我要充值</a>）
				<br />
				冻结金额：${userSession.userInfo.freemoney}
				<br />
				买家信用：<span class="blue">${userSession.userInfo.buyerCredit}</span> <a href="#" onclick="javascript:window.open('${ctx}/user/trade/credit/userCredit2.shtml?type=0')"><img src="${ctx}/images/ico/b_red_1.gif" width="16" height="16" /></a> 
				卖家信用：<span class="blue">${userSession.userInfo.sellerCredit}</span> <a href="#" onclick="javacript:window.open('${ctx}/user/trade/credit/userCredit1.shtml?type=1')"><img src="${ctx}/images/ico/s-Diamond-1.gif" width="13" height="11" /></a><br />
				您有
				<span class="blue">${notRead}</span> 条新消息，
				<a href="${ctx}/user/message/message.shtml" class="blue">点此查看</a> 。
				上次登录:${userSession.userInfo.tmpTime}&nbsp;上次登录地址：<s:property value='@com.game.util.web.IPSeeker@getInstance().getAddress(""+userSession.userInfo.tmpIp)' />&nbsp;IP：${userSession.userInfo.tmpIp}
				<div class="clear bbd_ccc"></div>
				<div class="pop1 mt8">
					买家提醒：待评价(${buyerAssessCount}) 三个月买入 (${buyerCount})<br />
					卖家提醒：待评价(${sellerAssessCount}) 三个月卖出 (${sellerCount}) 
				</div>
			</div>


		</div>
		<div class="clear"></div>
	</body>
</html>


