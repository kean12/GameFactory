<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id="manage_top">
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>挂卖列表>>查看挂卖信息
		</dt>
		<dd class="p9"><a href="#" onclick="history.back();">回上一级</a></dd>
	</dl>
</div>
<form action="${ctx}/admin/bizInfo/bizInfo!saveBizInfo.shtml" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
	<tr>
		<th width="10%">标识列:</th>
		<td width="40%">
			<input type="text" name="bizInfo.id" value="${bizInfo.id}" readonly />
		</td>
		<th width="10%">挂卖编号:</th>
		<td width="40%">
			<input type="text" name="bizInfo.serial" size="25" value="${bizInfo.serial}" readonly/>
		</td>
	</tr>
	<tr>
		<th width="10%">交易类型:</th>
		<td width="40%">
		<s:select list="#{1:'寄售',2:'担保'}"  name="bizInfo.buyType"  listValue="value" listKey="key" theme="simple"   />
		</td>
		<th width="10%">库存量:</th>
		<td width="40%">
			<input type="text" name="bizInfo.stock" value="${bizInfo.stock}" />
		</td>
	</tr>
	<tr>
		<th width="10%">单价:</th>
		<td width="40%">
			<input type="text" name="bizInfo.price" value="${bizInfo.price}" />
		</td>
		<th width="10%">是否上架:</th>
		<td width="40%">
			<s:select list="#{1:'上架',0:'下架'}"  name="bizInfo.isBuy"  listValue="value" listKey="key" theme="simple"   />
		</td>
	</tr>
	<tr>
		<th width="10%">添加时间:</th>
		<td width="40%">
			<input type="text" name="bizInfo.bizCreTime" value="${bizInfo.bizCreTime}" readonly />
		</td>
		<th width="10%">比例:</th>
		<td width="40%">
			<input type="text" name="bizInfo.proportion" value="${bizInfo.proportion}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">单位:</th>
		<td width="40%">
			<input type="text" name="bizInfo.unit" value="${bizInfo.unit}" />
		</td>
		<th width="10%">交易方式:</th>
		<td width="40%">
			<s:select list="#{1:'游戏中当面',2:'邮寄',3:'账号交易'}"  name="bizInfo.sellModel"  listValue="value" listKey="key" theme="simple"   />
		</td>
	</tr>
	<tr>
		<th width="10%">交易地点:</th>
		<td width="40%">
			<input type="text" name="bizInfo.site" value="${bizInfo.site}" />
		</td>
		<th width="10%">联系QQ:</th>
		<td width="40%">
			<input type="text" name="bizInfo.qq" value="${bizInfo.qq}" />
		</td>
	</tr>
	<tr>
		<th width="10%">开始销售时间:</th>
		<td width="40%">
			<input type="text" name="bizInfo.startSellTime" value="${bizInfo.startSellTime}" readonly />
		</td>
		<th width="10%">结束销售时间:</th>
		<td width="40%">
			<input type="text" name="bizInfo.endSellTime" value="${bizInfo.endSellTime}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">方便交易时间:</th>
		<td width="40%">
			<input type="text" name="bizInfo.tradeStart" value="${bizInfo.tradeStart}" readonly />
		</td>
		<th width="10%">方便交易时间:</th>
		<td width="40%">
			<input type="text" name="bizInfo.tradeEnd" value="${bizInfo.tradeEnd}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">联系电话:</th>
		<td width="40%">
			<input type="text" name="bizInfo.phoneNum" value="${bizInfo.phoneNum}" />
		</td>
		<th width="10%">商品标题:</th>
		<td width="40%">
			<input type="text" name="bizInfo.title" size="40" value="${bizInfo.title}" />
		</td>
	</tr>
	
	<tr>
		<th width="10%">所属服务器:</th>
		<td width="80%" colspan="3">
			<s:if test="bizInfo.game!=null">
				${bizInfo.game.gameName}
			</s:if>
			<s:else>
				${bizInfo.server.area.game.gameName}&nbsp;/&nbsp;${bizInfo.server.area.areaName}&nbsp;/&nbsp;${bizInfo.server.serverName}
			</s:else>
		</td>
	</tr>
	<tr>
		<th width="10%">挂卖图片信息集:</th>
		<td width="80%" colspan="3">
			<s:iterator value="bizInfo.pictures">
				<img src="${ctx}${route }" />
			</s:iterator>
		</td>
	</tr>
	<tr>
		<th width="10%">商品描述信息:</th>
		<td width="80%" colspan="3">
			<FCK:editor instanceName="infoContent" inputName="bizInfo.info" width="70%" height="300">
				<jsp:attribute name="value">
					${bizInfo.info}
				</jsp:attribute>
				<jsp:body>
				<FCK:config SkinPath="skins/office2003/"/>
				</jsp:body>
			</FCK:editor>
		</td>
	</tr>
	<tr>
		<th width="10%">操作:</th>
		<td width="80%" colspan="3">
		<input type="submit" value="保 存"/>&nbsp;<input type="button" onclick="history.back();" value="取 消"/>
		<font color="red" size="2">${errorMessage}</font>
		</td> 
	</tr>
</table>
</form>