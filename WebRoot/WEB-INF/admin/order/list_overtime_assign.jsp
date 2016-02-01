<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
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
	</head>
	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>超时订单管理
				</dt>
			</dl>
		</div>
		<div class="blank0"></div>
		<div id="orderList">
			<form action="${ctx}/admin/order/list_overtime_assign.shtml" method="post">
				<div class="sBox">
					分配客服:<s:select list="manageList" name="manageID" listValue="name" listKey="id" headerKey="" headerValue="-请选择交易客服-" theme="simple"  />
					订单编号：<input type="text" name="orderNum" value="${orderNum}" size="20" />
					<input type="submit" value="搜 索" />
				</div>
			</form>
		</div>
		<hr color="red" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr>
				<th>订单号</th>
				<th width="8%">发布者</th>
				<th width="8%">购买者</th>
				<th width="5%">物品单价</th>
				<th width="5%">数量</th>
				<th width="5%">总价格</th>
				<th width="8%">下单时间</th>
				<th width="10%">分配时间</th>
				<th width="5%">订单状态</th>
				<th width="8%">分配客服</th>
				<th width="10%">备注</th>
				
				<th width="6%">是否已申述</th>
				<th width="6%">是否已处理</th>
				<th width="8%">操作</th>
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
						<s:property value="order.orderTime" />
					</td>
					<td>
						<s:property value="startTime" />
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
					<td>
						<div style="height:30px; overflow:hidden;">
							<a href="javascript:void(0);" class="blue_u" onclick="alertMessage('${remark}',false);" title="${remark}">
								<s:property value="remark" />
							</a>
						</div>
					</td>
					
					<td>
						<s:if test="reason==null">
							否
						</s:if>
						<s:else>
							<span class="red">已申诉</span>
						</s:else>
					</td>
					<td>
						<s:if test="order.compensate==null">
							否
						</s:if>
						<s:else>
							<span class="red">已处理</span>
						</s:else>
					</td>
					<td>
						<button onclick="art.dialog({title:'查看详情-${manage.name}', iframe:'${ctx}/admin/order/overtime_detail.shtml?manageID=${manage.id}&orderNum=${order.orderNum}',lock:true,width:'1000',height:'530'});">
							查看详情
						</button>
					</td>
				</tr>
			</s:iterator>
		</table>
		
		<div class="manu">
			<form name="pageForm" action="${ctx}/admin/order/list_assign.shtml" method="post">
				<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
				<input type="hidden" id="goPage" name="goPage" />
				<input type="hidden" name="consumerName" value="${consumerName}" />
				<input type="hidden" name="ownerName" value="${ownerName}" />
				<input type="hidden" name="startTime" value="${startTime}" />
				<input type="hidden" name="endTime" value="${endTime}" />
				<input type="hidden" name="state" value="${state}" />
				<input type="hidden" name="orderNum" value="${orderNum}" />
			</form>
		</div>
	</body>
</html>