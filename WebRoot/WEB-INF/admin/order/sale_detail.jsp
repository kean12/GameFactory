<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id="manage_top">
		<dt class="manage_top_title">
			<img src="/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>发货管理>>详细信息
		</dt>
		<dd class="p9">
			<a href="#" onclick="history.back();">回上一级</a>
		</dd>
	</dl>
</div>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
	<tr>
		<th width="10%">
			标识列:
		</th>
		<td width="40%">
			<input type="text" value="${order.id }" readonly />
		</td>
		<th width="10%">
			订单号:
		</th>
		<td width="40%">
			<input type="text" value="${order.orderNum }" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			订单标题:
		</th>
		<td width="40%">
			<input type="text" value="${order.title }" readonly />
		</td>
		<th width="10%">
			下单时间:
		</th>
		<td width="40%">
			<input type="text" value="${order.orderTime}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			物品单价:
		</th>
		<td width="40%">
			<input type="text" value="${order.price}" readonly />
		</td>
		<th width="10%">
			中间金额:
		</th>
		<td width="40%">
			<input type="text" value="${order.assureMoney }" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			购买数量:
		</th>
		<td width="40%">
			<input type="text" value="${order.buyBum}" readonly />
		</td>
		<th width="10%">
			发布者:
		</th>
		<td width="40%">
			<input type="text" value="${order.owner.username}" disabled="disabled" />
		</td>
	</tr>
	<tr>
		<th width="10%">
			总价格:
		</th>
		<td width="40%">
			<input type="text" name="" value="${order.sumPrice}" readonly />
		</td>
		<th width="10%">
			购买者:
		</th>
		<td width="40%">
			<input type="text" value="${order.consumer.username}" disabled="disabled" />
		</td>
	</tr>
	<tr>
		<th width="10%">
			买家角色名:
		</th>
		<td width="40%">
			<input type="text" value="${order.playerRole}" readonly />
		</td>
		<th width="10%">
			卖家QQ:
		</th>
		<td width="40%">
			<input type="text" value="${order.sellQQ}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			买家角色等级:
		</th>
		<td width="40%">
			<input type="text" value="${order.playerGrade}" readonly />
		</td>
		<th width="10%">
			卖家电话:
		</th>
		<td width="40%">
			<input type="text" value="${order.sellPhoneNum }" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			买家QQ:
		</th>
		<td width="40%">
			<input type="text" value="${order.playQQ}" readonly />
		</td>
		<th width="10%">
			订单状态:
		</th>
		<td width="40%">
			<%--0 待付款|1 已付款|2 已发货3 交易关闭|4 退款处理|5 退款完成|6 交易成功|7拒绝客户退款--%>
			<s:if test="order.state==0">待付款</s:if>
			<s:elseif test="order.state==1">已付款</s:elseif>
			<s:elseif test="order.state==2">已发货</s:elseif>
			<s:elseif test="order.state==3">交易关闭</s:elseif>
			<s:elseif test="order.state==4">退款处理</s:elseif>
			<s:elseif test="order.state==5">退款完成</s:elseif>
			<s:elseif test="order.state==6">交易成功</s:elseif>
			<s:elseif test="order.state==7">拒绝客户退款</s:elseif>
		</td>
	</tr>
	<tr>
		<th width="10%">
			买家电话:
		</th>
		<td width="40%">
			<input type="text" value="${order.playPhoneNum}" readonly />
		</td>
		<th width="10%">
			游戏/区/服:
		</th>
		<td width="40%">
			<s:if test="game!=null">
				${order.game.gameName }
			</s:if>
			<s:else>
				${order.server.area.game.gameName }&nbsp;/&nbsp;${order.server.area.areaName }&nbsp;/&nbsp;${order.server.serverName }
			</s:else>
		</td>
	</tr>
	<tr>
		<th width="10%">
			商品编号:
		</th>
		<td width="40%">
			<input type="text" size="30" value="${order.bizInfo.serial }" disabled="disabled" />
		</td>
		<th width="10%">
			交易地点:
		</th>
		<td width="40%">
			${order.site}
		</td>
	</tr>
	<tr>
		<th width="10%">
			寄售账号:
		</th>
		<td width="40%">
			<input type="text" size="30" value="${order.bizInfo.account }" readonly="readonly" />
		</td>
		<th width="10%">
			寄售账号密码:
		</th>
		<td width="40%">
			<input type="text" size="30" value="${order.bizInfo.password }" readonly="readonly" />
		</td>
	</tr>
	
	<tr>
		<th width="10%">
			账号角色名:
		</th>
		<td width="40%">
			<input type="text" size="30" value="${order.bizInfo.roleName }" readonly="readonly" />
		</td>
		<th width="10%">
			密码锁:
		</th>
		<td width="40%">
			<input type="text" size="30" value="${order.bizInfo.coded_lock }" readonly="readonly" />
		</td>
	</tr>
	<tr>
		<th width="10%">
			物品存放处:
		</th>
		<td colspan="3">
			<input type="text" size="60" value="${order.bizInfo.place }" readonly="readonly" />
		</td>
	</tr>
	
	
	<tr>
		<td colspan="4">
			<form action="${ctx}/admin/order/sale_shipments.shtml" method="post">
				<input type="hidden" name="assignID" value="${assignID }" />
				<input type="submit" value="发   货" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="无货退款" onclick="chk_refundment('${order.orderNum}');" />&nbsp;&nbsp;&nbsp;&nbsp;
				
				<s:if test="order.bizInfo.sellModel==3">
					<input type="button" value="查看身份证" onclick="chkDiv('identityDiv');" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="查看账号其他信息" onclick="chkDiv('accountInfo');" />&nbsp;&nbsp;&nbsp;&nbsp;
				</s:if>
				<s:else>
					<input type="button" value="查看密保卡" onclick="chkDiv('pwdSrc');" />&nbsp;&nbsp;&nbsp;&nbsp;
				</s:else>
				<input type="button" value="查看宝贝图片" onclick="window.open('${ctx}/admin/order/sale_look_pic.shtml?assignID=${assignID}','blank');" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="查看宝贝介绍" onclick="window.open('${ctx}/admin/order/sale_look_info.shtml?assignID=${assignID}','blank');" />&nbsp;&nbsp;&nbsp;&nbsp;
			</form>
		</td>
	</tr>
</table>
<font color="red" size="2"></font>
<div class="piclist">
	<div id="pwdSrc" class="imgbox" style="display:none;">
		<h3>查看密保卡</h3>
		<s:if test='order.bizInfo.pwdSrc==null || order.bizInfo.pwdSrc==""'>
			用户没有上传密保卡!
		</s:if>
		<s:else>
			<img src="${ctx}${order.bizInfo.pwdSrc }" />
		</s:else>
	</div>
	
	<div id="identityDiv" class="imgbox" style="display:none;">
		<h3>查看身份证图片</h3>
		<s:if test='order.bizInfo.identities==null || order.bizInfo.identities.size()==0'>
			用户没有上传身份证!
		</s:if>
		<s:else>
			<s:iterator value="order.bizInfo.identities">
				<img src="${ctx}${route}" />
			</s:iterator>
		</s:else>
		
	</div>
	
	<div id="accountInfo" class="imgbox" style="display:none;">
		<h3>查看账号其他信息</h3>
		<div class="txtshow">
			<s:iterator value='order.bizInfo.accountInfo.split(";;")' id="arr">
				<s:set name="att" value='#arr.split("::")' />
				<strong><s:property value="#att[0]" /></strong>：<s:property value="#att[1]" /><br/>
			</s:iterator>
		</div>
	</div>
</div>

<script type="text/javascript">
	function chkDiv(div){
		var obj=document.getElementById(div);
		if(obj.style.display=="none"){
			obj.style.display="";
		}else{
			obj.style.display="none";
		}
		
	}
	
	function chk_refundment(orderNum){
		if(orderNum.length==0){
			alert("请输入订单编号！");
			return false;
		}
		
		if(confirm("您确定商户缺货，退款给用户吗？")){
			window.location="${ctx}/admin/order/oos_refundment.shtml?orderNum="+orderNum;
		}
	}
</script>


