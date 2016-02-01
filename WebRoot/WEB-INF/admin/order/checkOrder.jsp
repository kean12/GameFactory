<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id="manage_top">
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>订单列表>>订单查看
		</dt>
		<dd class="p9"><a href="#" onclick="history.back();">回上一级</a></dd>
	</dl>
</div>
<!--<form action="${ctx}/admin/order/order!saveOrder.shtml" method="post">-->
<input type="hidden" name="order.owner.id" value="${order.owner.id }" />
<input type="hidden" name="order.consumer.id" value="${order.consumer.id }" />
<input type="hidden" name="order.bizInfo.id" value="${order.bizInfo.id }" />
<input type="hidden" name="order.game.id" value="${order.game.id }" />
<input type="hidden" name="order.server.id" value="${order.server.id }" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
	<tr>
		<th width="10%">标识列:</th>
		<td width="40%">
			<input type="text" name="order.id" value="${order.id}" readonly />
		</td>
		<th width="10%">订单号:</th>
		<td width="40%">
			<input type="text" name="order.orderNum" value="${order.orderNum}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">订单标题:</th>
		<td width="40%">
			<input type="text" name="order.title" value="${order.title}" readonly />
		</td>
		<th width="10%">购买数量:</th>
		<td width="40%">
			<input type="text" name="order.buyBum" value="${order.buyBum}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">物品单价:</th>
		<td width="40%">
			<input type="text" name="order.price" value="${order.price}" readonly />
		</td>
		<th width="10%">总价格:</th>
		<td width="40%">
			<input type="text" name="order.sumPrice" value="${order.sumPrice}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">中间金额:</th>
		<td width="40%">
			<input type="text" name="order.assureMoney" value="${order.assureMoney}" readonly />
		</td>
		<th width="10%">订单状态:</th>
		<td width="40%">
			<s:select list="#{-1:'--选择订单状态--',0:'待付款',1:'已付款',2:'已发货',3:'交易关闭',4:'退款处理',5:'退款完成',6:'交易成功',7:'拒绝退款'}"  name="order.state"  listValue="value" listKey="key" theme="simple"   />
<!--			<input type="submit" value="保存状态" />-->
		</td>
	</tr>
	<tr>
		<th width="10%">买家角色名:</th>
		<td width="40%">
			<input type="text" name="order.playerRole" value="${order.playerRole}" readonly />
		</td>
		<th width="10%">买家角色等级:</th>
		<td width="40%">
			<input type="text" name="order.playerGrade" value="${order.playerGrade}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">买家QQ:</th>
		<td width="40%">
			<input type="text" name="order.playQQ" value="${order.playQQ}" readonly />
		</td>
		<th width="10%">买家电话:</th>
		<td width="40%">
			<input type="text" name="order.playPhoneNum" value="${order.playPhoneNum}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">卖家QQ:</th>
		<td width="40%">
			<input type="text" name="order.sellQQ" value="${order.sellQQ}" readonly />
		</td>
		<th width="10%">卖家电话:</th>
		<td width="40%">
			<input type="text" name="order.sellPhoneNum" value="${order.sellPhoneNum}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">下单时间:</th>
		<td width="40%">
			<input type="text" name="order.orderTime" value="${order.orderTime}" readonly />
		</td>
		<th width="10%">成功时间:</th>
		<td width="40%">
			<input type="text" name="order.succTime" value="${order.succTime}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">评论状态:</th>
		<td width="40%">
			<s:if test="order.state==6">
				<s:if test="order.isAssess==1">
					双方未评
				</s:if>
				<s:elseif test="order.isAssess==2">
					<FONT color="orange" size="2">买家已评</FONT>
				</s:elseif>
				<s:elseif test="order.isAssess==3">
					<a class="orange">商家已评</a>
				</s:elseif>
				<s:elseif test="order.isAssess==4">
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
		<td colspan="2"><hr color="red"/></td>
	</tr>
	<tr>
		<th width="10%">发布者:</th>
		<td width="40%">
			<input type="text" name="order.owner.username" value="${order.owner.username}" disabled="disabled" />
			<button onclick="window.open('${ctx}/admin/user/user!checkUser.shtml?id=${order.owner.id}');">查看发布者信息</button>
		</td>
		<th width="10%">购买者:</th>
		<td width="40%">
			<input type="text" name="order.consumer.username" value="${order.consumer.username}" disabled="disabled" />
			<button onclick="window.open('${ctx}/admin/user/user!checkUser.shtml?id=${order.consumer.id}');">查看购买者信息</button>
		</td>
	</tr>
	<s:if test="order.bizInfo != null">
		<tr>
			<th width="10%">挂卖信息编号:</th>
			<td width="40%">
				<input type="text" size="30" name="order.bizInfo.serial" value="${order.bizInfo.serial}" disabled="disabled" />
				<button onclick="window.open('${ctx}/admin/bizInfo/bizInfo!checkBizInfo.shtml?bizInfo.id=${order.bizInfo.id}');">查看挂卖信息</button>
			</td>
			<th width="10%">所属服务器</th>
			<td width="40%">
				<s:if test="order.game!=null">
				${order.game.gameName}
			</s:if>
			<s:else>
				${order.server.area.game.gameName}&nbsp;/&nbsp;${order.server.area.areaName}&nbsp;/&nbsp;${order.server.serverName}
			</s:else>
			</td>
		</tr>
	</s:if>
</table>
<!--</form>-->
<font color="red" size="2"><s:property value="errorMessage"/></font>
	private Game game;			//'游戏表外键'
	private Server server;		//'服务器表外键'
	private BizInfo bizInfo;		//挂卖信息外键
	待显示