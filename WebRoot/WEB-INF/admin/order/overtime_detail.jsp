<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/editor/FCKeditor/fckeditor.js"></script>
<div class="main_top_title">
	<dl id="manage_top">
		<dt class="manage_top_title">
			超时订单详细信息查看
		</dt>
	</dl>
</div>
<form name="overtimeform" action="${ctx}/admin/order/overtime_detail_save.shtml" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
		<tr>
			<th width="10%">订单号:</th>
			<td width="40%">
				${assign.order.orderNum}
			</td>
			<th width="10%">挂卖类别:</th>
			<td width="40%">
				${assign.order.bizKind.kindName}
			</td>
		</tr>
		<tr>
			<th width="10%">发布者:</th>
			<td width="40%">
				${assign.order.owner.username}
			</td>
			<th width="10%">购买者:</th>
			<td width="40%">
				${assign.order.consumer.username}
			</td>
		</tr>
		<tr>
			<th width="10%">物品单价:</th>
			<td width="40%">
				${assign.order.price}
			</td>
			<th width="10%">购买数量:</th>
			<td width="40%">
				${assign.order.buyBum}
			</td>
		</tr>
		<tr>
			<th width="10%">总价格:</th>
			<td width="40%">
				${assign.order.sumPrice}
			</td>
			<th width="10%">订单状态:</th>
			<td width="40%">
				${assign.order.state}
			</td>
		</tr>
		<tr>
			<th width="10%">卖家电话:</th>
			<td width="40%">
				${assign.order.sellPhoneNum}
			</td>
			<th width="10%">卖家QQ:</th>
			<td width="40%">
				${assign.order.sellQQ}
			</td>
		</tr>
		<tr>
			<th width="10%">买家电话:</th>
			<td width="40%">
				${assign.order.playPhoneNum}
			</td>
			<th width="10%">买家QQ:</th>
			<td width="40%">
				${assign.order.playQQ}
			</td>
		</tr>
		<tr>
			<th width="10%">买家角色名:</th>
			<td width="40%">
				${assign.order.playerRole}
			</td>
			<th width="10%">买家角色等级:</th>
			<td width="40%">
				${assign.order.playerGrade}
			</td>
		</tr>
		<tr>
			<th width="10%">下单时间:</th>
			<td width="40%">
				${assign.order.orderTime}
			</td>
			<th width="10%">成功时间:</th>
			<td width="40%">
				${assign.order.succTime}
			</td>
		</tr>
		<tr>
			<th width="10%">交易地点:</th>
			<td width="40%">
				${assign.order.site}
			</td>
			<th width="10%">交易花费时间:</th>
			<td width="40%">
				${assign.order.costTime}
			</td>
		</tr>
		<tr>
			<th width="10%">商品标题:</th>
			<td width="40%">
				${assign.order.title}
			</td>
			<th width="10%">所属区服:</th>
			<td width="40%">
				<s:if test="assign.order.game!=null">
					${assign.order.game.gameName}
				</s:if>
				<s:else>
					${assign.order.server.area.game.gameName}/${assign.order.server.area.areaName}/${assign.order.server.serverName}
				</s:else>
			</td>
		</tr>
		<tr>
			<th width="10%">是否赔付:</th>
			<td width="40%">
				<s:select list="#{'':'','0':'否','1':'是'}" name="compensate" listKey="key" listValue="value" theme="simple"></s:select>
			</td>
			<th width="10%">赔付金额:</th>
			<td width="40%">
				<s:if test="assign.order.quantity==null">
					--
				</s:if>
				<s:else>
					${assign.order.quantity}
				</s:else>
			</td>
		</tr>
		<tr>
			<th width="10%">申诉内容:</th>
			<td width="40%">
				<textarea name="reason" rows="10" style="width:98%;">${assign.reason}</textarea>
			</td>
			<th width="10%">处理结果:</th>
			<td width="40%">
				<textarea name="outcome" rows="10" style="width:98%;">${assign.outcome}</textarea>
			</td>
		</tr>
		<tr>
			<th colspan="4" style="text-align: center;height: 50px" class="bigbutton">
				<input type="hidden" name="assignID" value="${assign.id}" />
				<button type="submit">
					提交
				</button>
				<button type="reset" class="ml20">
					复位
				</button>
			</th>
		</tr>
	</table>
</form>
<s:if test="reload">
	<script type="text/javascript">
		window.parent.location.reload();
	</script>
</s:if>
<font color="red" size="2"><s:property value="errorMessage"/></font>