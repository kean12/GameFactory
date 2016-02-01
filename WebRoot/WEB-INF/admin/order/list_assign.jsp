<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/admin/base.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">var AP="${ctx}";</script>
<script type="text/javascript" src="${ctx}/js/tool.js"></script>
<script type="text/javascript">
	function chk_select(){
		var arr=document.getElementsByName("manageObj");
		var str="";
		var parm="";
		for(var i=0;i<arr.length;i++){
			parm=arr[i].options[arr[i].selectedIndex].value;
			if(parm!=""){
				str+=parm+";";
			}
		}
		document.assignForm.content.value=str;
		document.assignForm.submit();
	}
</script>
<div class="main_top_title">
	<dl id=manage_top>
		<dt class="manage_top_title">
			<s:if test='manageSession.role.name=="admin" || manageSession.role.name=="temp"'>
				<button style="float:right; margin-right:12%; vertical-align: middle; margin-top:-5px" onclick="chk_select();">保　存 </button>
			</s:if>
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>寄售订单分配
		</dt>
	</dl>
</div>
<div class="blank0"></div>
<form name="assignForm" action="${ctx}/admin/order/reset.shtml">
	<input type="hidden" id="content" name="content" value="" />
</form>
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
		<th width="8%">分配客服</th>
	<s:if test='manageSession.role.name=="admin" || manageSession.role.name=="temp"'>
		<th width="10%">订单重分配</th>
	</s:if>
		<th width="10%">处理状态</th>
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
				<s:elseif test="order.state==4">退款处理</s:elseif>
				<s:elseif test="order.state==5">退款完成</s:elseif>
				<s:elseif test="order.state==6">交易成功</s:elseif>
				<s:elseif test="order.state==7">拒绝退款</s:elseif>
				<s:else>
					<font color="red">错误状态</font>
				</s:else>
			</td>
			<td>
				<s:property value="manage.name" />
			</td>
		<s:if test='manageSession.role.name=="admin" || manageSession.role.name=="temp"'>
			<td>
				<select name="manageObj">
					<option value="">交易员分配</option>
					<s:iterator value="manageList">
						<option value="${id}|${order.id}|<s:property value="#assign.id" />">${name}</option>
					</s:iterator>
				</select>
			</td>
		</s:if>
			<td>
				<s:if test="state==1">
					已处理
				</s:if>
				<s:else>
					<span class="red">未处理</span><input type="button" value="处理" onclick="location.href='${ctx}/admin/order/sale_detail.shtml?orderNum=${order.orderNum }'" />
				</s:else>
			</td>
		</tr>
	</s:iterator>
</table>
<div class="manu">
	<form name="pageForm" action="${ctx}/admin/order/list_assign.shtml" method="post">
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