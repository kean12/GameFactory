<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>挂卖自定义分类</title>
	</head>
	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>挂卖自定义分类-属性
				</dt>
				<dd class="p1">
					<a href="${ctx}/admin/details/detailsAdmin!addAttribute.shtml?parentID=${parentID}">添加属性</a>
				</dd>
				<dd class="p9">
					<a href="#" onclick="history.back();">回上一级</a>
				</dd>
			</dl>
		</div>
		<div class="blank0"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr>
				<th>
					名称
				</th>
				<th>
					表单名
				</th>
				<th>
					类型
				</th>
				<th>
					所属类别
				</th>
				<th>
					所属游戏
				</th>
				<th>
					排列顺序
				</th>
				<th>
					是否启用
				</th>
				<th width="35%">
					操作
				</th>
			</tr>
			<s:iterator value="details.child">
				<tr onMouseOver="this.style.background = '#d6e5ff';" onMouseOut="this.style.background ='#ffffff';">
					<td>
						<s:property value="attributeName" />
					</td>
					<td>
						<s:property value="formName" />
					</td>
					<td>
						<s:if test="parent.parent.type==1">
							账号属性
						</s:if>
						<s:elseif test="parent.parent.type==2">
							普通属性
						</s:elseif>
					</td>
					<td>
						<s:property value="parent.parent.gameKind.bizKind.kindName" />
					</td>
					<td>
						<s:property value="parent.parent.gameKind.game.gameName" />
					</td>
					<td>
						<s:property value="orderIndex" />
					</td>
					<td>
						<s:if test="isUser==1">
							启用
						</s:if>
						<s:else>
							<span class="red">禁用</span>
						</s:else>
					</td>
					<td>
						<input type="button" value="编辑" onclick="location.href='${ctx}/admin/details/detailsAdmin!addAttribute.shtml?detailsID=${id}&parentID=${parent.id}'" />
						<input type="button" value="删除" onclick="location.href='${ctx}/admin/details/detailsAdmin!deleteAttribute.shtml?detailsID=${id}&parentID=${parent.id}'" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
