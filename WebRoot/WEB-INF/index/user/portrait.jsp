<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<form action="${ctx}/user/portrait!portraitSave.shtml" method="post" enctype="multipart/form-data">
		<div style="padding:15px; margin:0 auto">
			<fieldset>
				<legend><strong>头像资料修改</strong></legend>
				<img src="
					<s:if test="userSession.userInfo.route==null">${ctx}/images/00001.jpg</s:if>
      				<s:else>${ctx}${userSession.userInfo.route}</s:else>
					" style="background:#fff; padding:3px; border:#ccc 1px solid; margin:10px 0 20px; width:158px; height:106px" /><br />
				<input type="file" name="imgFile" size="40" style="height:25px; line-height:25px"/><br />
	            <span class="red">图片最佳尺寸158像素*106像素,大小不超过30kb</span>
			</fieldset>
			<div class="tr pd10">
				<button type="submit">确定修改</button>
			</div>
	    <div>
	</form>
		<s:if test="reload">
			<script type="text/javascript">
				window.parent.location.reload();
			</script>
		</s:if>
	</body>
</html>