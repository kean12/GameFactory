<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="yhzx_rightside">
			<div class="yhzx_title">
				收货角色名管理
			</div>
			<div class="yhzx_hy" style="height: 35px">
				<div class="pop1 mt8">
					<form action="${ctx}/user/account/save_role.shtml" method="post">
						<input type="text" class="vam" name="userRole.roleName" size="40" />
						<button type="submit" class="abutton3 ml10">
							添加角色名
						</button>
					</form>
				</div>
			</div>
			<div class="contentbox" id="box0" style="display: block">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="yhzx_list" style="background: none; border: 0; margin-top: 10px">
					<tr>
						<th width="38" scope="col">
							编号
						</th>
						<th width="614" scope="col">
							角色名
						</th>
						<th width="88" scope="col">
							操作
						</th>
					</tr>
					<s:iterator value="userRoleList" status="index">
						<tr>
							<td align="center">
								<s:property value="#index.index+1" />
							</td>
							<td>
								${roleName}
							</td>
							<td>
								<a style="cursor: pointer;" onclick="window.location='${ctx}/user/account/delete_role.shtml?roleID=${id }'" class="blue_u">删除 </a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<div class="clear"></div>
	</body>
</html>