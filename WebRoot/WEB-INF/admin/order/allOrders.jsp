<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">var AP="${ctx}";</script>
<script type="text/javascript" src="${ctx}/js/tool.js"></script>

<div class="main_top_title">
	<dl id=manage_top>
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>订单管理>>查看订单
		</dt>
	</dl>
</div>
<div class="blank0"></div>









<form action="${ctx}/admin/order/order.shtml" method="post">
<table id="orderList">
	<tr class="sBox">
		<td>购买者：<input type="text" name="order.consumer.username" value="${order.consumer.username}"/></td>
		<td>发布者：<input type="text" name="order.owner.username" value="${order.owner.username}"/></td>
		<td width="350">下单时间：从<input class="ipt-date from" size="8" id="orderTimeStart" name="orderTimeStart" value="${orderTimeStart }" readonly/>～<input class="ipt-date to" size="8" id="orderTimeEnd" name="orderTimeEnd" value="${orderTimeEnd}" readonly/>
		</td>
		<td width="350">成功时间：从<input class="ipt-date from" size="8" id="succTimeStart" name="succTimeStart" value="${succTimeStart }" readonly/>～<input class="ipt-date to" size="8" id="succTimeEnd" name="succTimeEnd" value="${succTimeEnd}" readonly/>
		</td>
	</tr>
	<tr>
		<td>总价格：从<input type="text" name="sumPriceMin" value="${sumPriceMin }" size="5"/>～<input type="text" name="sumPriceMax" value="${sumPriceMax }" size="5"/>
		</td>
		<td>订单状态:
		<s:select list="#{-1:'--选择订单状态--',0:'待付款',1:'已付款',2:'已发货',3:'交易关闭',4:'退款处理',5:'退款完成',6:'交易成功',7:'拒绝退款'}"  name="order.state"  listValue="value" listKey="key" theme="simple"   />
		</td>
		<td>订单编号：<input type="text" name="order.orderNum" value="${order.orderNum }" size="20"/></td>
		<td><input type="submit" value="搜 索" /></td>
	</tr>
</table>
</form>
<hr color="red"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
	<tr>
		<th width="10%">订单号</th>
		<th width="10%">发布者</th>
		<th width="10%">购买者</th>
		<th width="10%">物品单价</th>
		<th width="3%">数量</th>
		<th width="10%">总价格</th>
		<th width="10%">中间金额</th>
		<th width="10%">下单时间</th>
		<th width="10%">成功时间</th>
		<th width="10%">订单状态</th>
		<th width="10%">是否评价</th>
		<th width="10%">&nbsp;</th>
	</tr>
	<s:if test="page.resultlist.size() < 1">
		<tr><td colspan="5">暂无订单信息...</td></tr>
	</s:if>
	<s:iterator value="page.resultlist">
		<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
		<td><s:property value="orderNum" /></td>
		<td><s:property value="owner.username" /></td>
		<td><s:property value="consumer.username" /></td>
		<td><s:property value="price" /></td>
		<td><s:property value="buyBum" /></td>
		<td><s:property value="sumPrice" /></td>
		<td><s:property value="assureMoney" /></td>
		<td><s:property value="orderTime" /></td>
		<td><s:property value="succTime" />&nbsp;</td>
		<td>
			<s:if test="state==0">待付款</s:if>
			<s:elseif test="state==1">已付款</s:elseif>
			<s:elseif test="state==2">已发货</s:elseif>
			<s:elseif test="state==3">交易关闭</s:elseif>
			<s:elseif test="state==4">退款处理</s:elseif>
			<s:elseif test="state==5">退款完成</s:elseif>
			<s:elseif test="state==6">交易成功</s:elseif>
			<s:elseif test="state==7">拒绝退款</s:elseif>
			<s:else><font color="red">错误状态</font></s:else>
		</td>
		<td>
			<s:if test="state==6">
				<s:if test="isAssess==1">
					双方未评
				</s:if>
				<s:elseif test="isAssess==2">
					<FONT color="orange" size="2">买家已评</FONT>
				</s:elseif>
				<s:elseif test="isAssess==3">
					<a class="orange">商家已评</a>
				</s:elseif>
				<s:elseif test="isAssess==4">
					<a class="green">双方都已评</a>
				</s:elseif>
				<s:else>
					双方未评
				</s:else>
			</s:if>
			<s:else>
				-
			</s:else>
		</td>
		<td><input type="button" value="查看" onclick="location.href='${ctx}/admin/order/order!checkOrder.shtml?order.id=${id}'" /></td>
		</tr>
	</s:iterator>
</table>
<div class="manu">
	<form name="pageForm" action="${ctx}/admin/order/order.shtml" method="get">
	<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
		<input type="hidden" id="goPage" name="goPage" />
		<input type="hidden" name="order.state" value="${order.state}" />
		<input type="hidden" name="order.consumer.username" value="${order.consumer.username}" />
		
		<input type="hidden" name="order.owner.username" value="${order.owner.username}"/>
		<input type="hidden" name="orderTimeStart" value="${orderTimeStart }"/>
		<input type="hidden" name="orderTimeEnd"  value="${orderTimeEnd }"  />
		
		<input type="hidden" name="succTimeStart" value="${succTimeStart }"/>
		<input type="hidden" name="succTimeEnd"  value="${succTimeEnd }"/>
		<input type="hidden" name="sumPriceMin" value="${sumPriceMin }" />
		<input type="hidden" name="sumPriceMax" value="${sumPriceMax }" />
	</form>
</div>