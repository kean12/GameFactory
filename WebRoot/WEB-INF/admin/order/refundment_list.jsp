<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>退款管理</title>
	</head>
	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>退款管理
				</dt>
			</dl>
		</div>
		<div class="blank0"></div>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr>
				<th>订单号</th>
				<th width="8%">发布者</th>
				<th width="8%">购买者</th>
				<th width="8%">物品单价</th>
				<th width="5%">数量</th>
				<th width="8%">总价格</th>
				<th width="8%">中间金额</th>
				<th width="10%">下单时间</th>
				<th width="8%">订单状态</th>
				<th width="18%">分配客服|实际操作</th>
				<th width="10%">操作</th>
			</tr>
			<s:if test="assignList.size() < 1">
				<tr>
					<td colspan="5">
						暂无订单信息...
					</td>
				</tr>
			</s:if>
			<s:iterator value="assignList" id="assign">
				<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background = '#ffffff'">
					<td>
						<s:property value="order.orderNum" />
					</td>
					<td>
						<s:property value="order.owner.username" />
					</td>
					<td>
						<s:property value="order.consumer.username" />
					</td>
					<td>
						<s:property value="order.price" />
					</td>
					<td>
						<s:property value="order.buyBum" />
					</td>
					<td>
						<s:property value="order.sumPrice" />
					</td>
					<td>
						<s:property value="order.assureMoney" />
					</td>
					<td>
						<s:property value="order.orderTime" />
					</td>
					<td>
						<s:if test="order.state==0">待付款</s:if>
						<s:elseif test="order.state==1">已付款</s:elseif>
						<s:elseif test="order.state==2">已发货</s:elseif>
						<s:elseif test="order.state==3">交易关闭</s:elseif>
						<s:elseif test="order.state==4"><span class="red">等待退款处理</span></s:elseif>
						<s:elseif test="order.state==5">退款完成</s:elseif>
						<s:elseif test="order.state==6">交易成功</s:elseif>
						<s:elseif test="order.state==7">拒绝退款</s:elseif>
						<s:else>
							<font color="red">错误状态</font>
						</s:else>
					</td>
					<td>
						<s:property value="manage.name" />
						<s:if test="operate.id!=null">
							|&nbsp;&nbsp;<s:property value="operate.name" />
						</s:if>
					</td>
					<td>
						<input type="button" value="查看" onclick="location.href='${ctx}/admin/order/refundment_detail.shtml?assignID=${id }'" />
					</td>
				</tr>
			</s:iterator>
		</table>
		<div class="manu">
			<form name="pageForm" action="${ctx}/admin/order/refundment_list.shtml" method="post">
				<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
				<input type="hidden" id="goPage" name="goPage" />
				<input type="hidden" name="consumerName" value="${consumerName }" />
				<input type="hidden" name="ownerName" value="${ownerName }" />
				<input type="hidden" name="startTime" value="${startTime }" />
				<input type="hidden" name="endTime" value="${endTime }" />
				<input type="hidden" name="state" value="${state }" />
				<input type="hidden" name="orderNum" value="${orderNum }" />
			</form>
		</div>
		
	</body>
</html>
