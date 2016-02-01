﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>游戏挂卖种类列表
				</dt>
				<dd class="p1" onclick=CreateFolder();>
					<a onclick="document.gameKindForm.submit();" style="cursor: pointer;">保存</a>
				</dd>
				<dd class="p9">
					<a href="#" onclick="history.back();">回上一级</a>
				</dd>
			</dl>
		</div>
		<div class="blank0"></div>
	<form name="gameKindForm" action="${ctx}/admin/gameKind/gameKindAdmin!addGameKind.shtml" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			<s:iterator value="bizKindList" status="index" id="bizKind">
				<s:if test="#index.first">
					<tr>
				</s:if>
				<s:elseif test="(#index.index+1)%5==1">
					</tr>
					<tr>
				</s:elseif>
				<td>
					<input type="checkbox" id="kind<s:property value="#index.index+1" />" name="idKey" value="${id}" <s:if test="tempList.contains(#bizKind)">checked="checked"</s:if> />
					<s:if test="toleration==1">
						<label class="red" for="kind<s:property value="#index.index+1" />">${kindName}</label>
					</s:if>
					<s:else>
						<label for="kind<s:property value="#index.index+1" />">${kindName}</label>
					</s:else>
				</td>
				<s:if test="#index.last">
					</tr>
				</s:if>
			</s:iterator>
		</table>
		<input type="hidden" name="gameID" value="${gameID }" />
	</form>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable" >
			<tr>
				<th width="10%">
					编号
				</th>
				<th width="10%">
					种类名称
				</th>
				<th width="10%">
					单位
				</th>
				<th width="10%">
					是否使用
				</th>
				<th width="10%">
					挂卖方式
				</th>
				<th width="30%">
					备注
				</th>
				<th width="10%">
					所属游戏
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
		
		<s:iterator value="gameKindList" status="index">
			<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
				<td>
					<s:property value="id" />&nbsp;
				</td>
				<td>
					<s:property value="bizKind.kindName" />&nbsp;
				</td>
				<td>
					<span onclick="this.style.display='none';$('txt_unit<s:property value="#index.index+1" />').style.display='';$('txt_unit<s:property value="#index.index+1" />').select();">
						<s:property value="unit" />&nbsp;
					</span>
					<input type="text" id="txt_unit<s:property value="#index.index+1" />" value="${unit}" onblur="chk_unit(this.value,${id});" style="display: none;" />
				</td>
				<td>
					<s:if test="bizKind.isUse==1">
						启用
					</s:if>
					<s:else>
						<span class="red">禁用</span>
					</s:else>&nbsp;
				</td>
				<td>
					<s:select list="#{0:'普通方式',1:'账号交易',2:'自定义属性'}" name="tradeType" listValue="value" listKey="key" theme="simple" onchange="chk_tradeType(this,%{id});" />
				</td>
				<td>
					<s:property value="bizKind.remark" />&nbsp;
				</td>
				<td>
					<s:property value="game.gameName" />&nbsp;
				</td>
				<td>
					<s:if test="tradeType==1">
						<input type="button" value="查看" onclick="window.location='${ctx}/admin/details/detailsAdmin.shtml?gameKindID=${id}'" />
						<input type="button" value="职业设置" onclick="window.location='${ctx}/admin/profession/professionAdmin.shtml?gameID=${gameID}'" />
					</s:if>
					<s:elseif test="tradeType==2">
						<input type="button" value="查看" onclick="window.location='${ctx}/admin/details/detailsAdmin.shtml?gameKindID=${id}'" />
					</s:elseif>
					<s:else>
						&nbsp;
					</s:else>
				</td>
			</tr>
		</s:iterator>
		</table>
		<form name="unitForm" action="${ctx}/admin/gameKind/gameKindAdmin!editUnit.shtml" method="post">
			<input type="hidden" id="unit" name="unit" />
			<input type="hidden" id="type" name="type" />
			<input type="hidden" id="gameKindID" name="gameKindID" />
			<input type="hidden" name="gameID" value="${gameID}" />
		</form>
		<script type="text/javascript">
			function chk_unit(unit,id){
				unit=unit.Trim();
				if(unit!=""){
					$("unit").value=unit;
					$("gameKindID").value=id;
					document.unitForm.action="${ctx}/admin/gameKind/gameKindAdmin!editUnit.shtml";
					document.unitForm.submit();
				}
			}

			function chk_tradeType(obj,id){
				var type=obj.options[obj.options.selectedIndex].value;
				$("type").value=type;
				$("gameKindID").value=id;
				document.unitForm.action="${ctx}/admin/gameKind/gameKindAdmin!editTradeType.shtml";
				document.unitForm.submit();
				
			}
		</script>
	</body>
</html>