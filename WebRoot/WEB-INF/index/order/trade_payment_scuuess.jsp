<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="blank10"></div>
		<div class="contaner tc">
			<div class="zf_success bs_ccc">
				<ul>
					<li class="bbd_ccc">
						<h2>
							付款成功!
						</h2>
					</li>
					<li>
						流水号:<span class="red">${runningNum}</span>
					</li>
					<li>
						如果您有未付款的交易，
						<a style="cursor: pointer;" class="blue" onclick="document.list_bought_form.submit();">查看并完成交易</a>
						<form name="list_bought_form" action="${ctx}/user/trade/order/list_bought_items.shtml" method="post">
							<input type="hidden" name="type" value="0" />
						</form>
					</li>
					<li>
						您可能需要：
						<a style="cursor: pointer;" onclick="window.location='${ctx}/user/trade/order/list_bought_items.shtml'" class="blue">查看已买到的宝贝</a> |
						<a style="cursor: pointer;" onclick="window.location='${ctx}/user/home.shtml'" class="blue">用户中心</a> |
						<a style="cursor: pointer;" onclick="window.location='${ctx}/user/trade/order/list_bought_items.shtml'" class="blue">交易管理</a>
					</li>
					<s:if test='orderNum!=null && orderNum!=""'>
						<li class="tr"">
							<a style="cursor: pointer;" onclick="window.location='${ctx}/user/trade/order/examine/examine_order_info.shtml?orderNum=${orderNum }'" class="gray f12">查看订单信息&gt;&gt;</a>
						</li>
					</s:if>
				</ul>
			</div>
		</div>
	</body>
</html>

