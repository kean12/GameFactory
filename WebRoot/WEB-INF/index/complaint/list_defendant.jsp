<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<!--left end -->
		<div class="yhzx_rightside">
			<div class="yhzx_title">
				投诉列表
			</div>
			<div id="rightnav">
				<ul>
					<li class="normal_nav">
						<span onclick="window.location='${ctx}/user/trade/order/list_complaint.shtml'">发出投诉</span>
					</li>
					<li class="action_nav">
						<span>接收投诉</span>
					</li>
				</ul>
			</div>
			<div class="contentbox" id="box0" style="display: block">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="yhzx_list" style="background: none; border: 0">
					<tr>
						<th scope="col">订单号</th>
						<th scope="col">类别</th>
						<th scope="col">被投诉人</th>
						<th scope="col">投诉时间</th>
						<th scope="col">状态</th>
						<th scope="col">操作</th>
					</tr>
				<s:iterator value="complaintList">
					<tr>
						<td>${order.orderNum}</td>
						<td><s:if test="type==1">商家投诉</s:if><s:elseif test="type==2">客户投诉</s:elseif></td>
						<td>${defendant.username }</td>
						<td>${applyTime }</td>
						<td><s:if test="state==1">已处理</s:if><s:elseif test="state==2">处理中</s:elseif><s:elseif test="state==0">等待处理</s:elseif></td>
						<td><a href="${ctx}/user/trade/order/complaint.shtml?orderID=${order.id}" class="blue_u">查看 </a></td>
					</tr>
				</s:iterator>
					
				</table>
				
				<div class="manu">
					<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
					<form name="pageForm" action="${ctx}/user/trade/order/list_defendant.shtml" method="get">
						<input type="hidden" id="goPage" name="goPage" />
					</form>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</body>
</html>
