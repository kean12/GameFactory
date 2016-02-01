﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script language="javascript" type="text/javascript">
			function showmain(index) {
				var menu_num = document.getElementById("left_menu").getElementsByTagName("dl");
				if(menu_num[index].style.display=="none"){
					menu_num[index].style.display="block";
				}else{
					menu_num[index].style.display="none";
				}
			}
		</script>
	</head>
	<body style="overflow-x:hidden; overflow-y:auto">
		<s:set name="roleId" value="manageSession.role.id" />
		<s:set name="index" value="0" />
		<div id="left_menu">
		<s:iterator value="menuList">
			<s:if test="checkPower(#roleId)">
				<a href="#" class="lefta left_ma2" onclick="showmain(<s:property value="#index" />);">
					<s:property value="menuName" />
				</a>
				<s:set name="index" value="#index + 1" />
				<dl class="left_list" style="display: none;">
					<s:iterator value="child">
						<s:if test="checkPower(#roleId)">
							<dt>
								<s:if test="actionURL!=null && target!=null&&checkPower(#roleId)">
									<a href="${actionURL }" target="${target }"><s:property value="menuName" /></a>
								</s:if>
								<s:else>
									<s:property value="menuName" />
								</s:else>
							</dt>
							<s:iterator value="child">
								<s:if test="checkPower(#roleId)">
									<dd><a href="${actionURL }" target="${target }"><s:property value="menuName" /></a></dd>
								</s:if>
							</s:iterator>
						</s:if>
					</s:iterator>
				</dl>
			</s:if>
		</s:iterator>
		</div>
	</body>
</html>