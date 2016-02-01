<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>

	<div class="contaner pd10">
		<div class="bs_ccc wrong w480" style="margin: 0 auto">
			<span class="red">${exception.message}</span>
			<br />
			您可以：
			<br />
			1.检查刚才的输入
			<br />
			2.去其它地方逛逛
			<a onclick="window.location='${ctx}/index.shtml'" style="cursor: pointer;" class="blue">首页</a> |
			<a onclick="window.location='${ctx}/user/home.shtml'" style="cursor: pointer;" class="blue">用户中心</a> |
			<a href="javascript:void(0)" onclick="javascript:history.back();" class="blue">返回</a>
		</div>
	</div>
</body>
</html>
