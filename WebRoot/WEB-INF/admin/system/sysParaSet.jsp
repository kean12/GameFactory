<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/admin/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">var AP="${ctx}";</script>
		<script src="${ctx}/js/tool.js"></script>
	</head>
	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>参数设置
				</dt>
			</dl>
		</div>
		<div class="blank0"></div>
	<form action="${ctx}/admin/system/sysParaSet!save.shtml" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr class="hback">
				<td width="140" style="text-align:right">
					本站域名：
				</td>
				<td style="text-align:left"> 
					<input type="text" name="siteDoMain" value="<s:property value='@com.game.util.web.SysConfig@get("siteDoMain")'/>" size="50" />
					<br>
					<span class="red">不要带http:// ,如果有虚拟目录，后面请带虚拟目录:如：www.gamemaimai.com/game</span>
				</td>
			</tr>
			<tr class="hback">
				<td style="text-align:right">
					站点名称：
				</td>
				<td style="text-align:left">
					<input type="text" name="siteName" value="<s:property value='@com.game.util.web.SysConfig@get("siteName")'/>" size="50" />
				</td>
			</tr>
			
			<tr class="hback">
				<td style="text-align:right">
					站点描述：
				</td>
				<td style="text-align:left">
					<input type="text" name="description" value="<s:property value='@com.game.util.web.SysConfig@get("description")'/>" size="50" />
					<span class="red">用","分隔</span>
				</td>
			</tr>
			<tr class="hback">
				<td style="text-align:right">
					关键字：
				</td>
				<td style="text-align:left">
					<input type="text" name="keywords" value="<s:property value='@com.game.util.web.SysConfig@get("keywords")'/>" size="50" />
					<span class="red">用","分隔</span>
				</td>
			</tr>
			
			
			<tr class="hback">
				<td style="text-align:right">
					站长信箱：
				</td>
				<td style="text-align:left">
					<input type="text" name="siteMail" value="<s:property value='@com.game.util.web.SysConfig@get("siteMail")'/>" size="50" />
				</td>
			</tr>
			<tr class="hback">
				<td style="text-align:right">
					允许上传图片大小：
				</td>
				<td style="text-align:left">
					<input type="text" name="upImg_Size" value="<s:property value='@com.game.util.web.SysConfig@get("upImg_Size")'/>" size="50" onChange="if(/\D/.test(this.value)){alert('只能输入非负整数');this.value='';}"/>&nbsp;&nbsp;&nbsp;kb
				</td>
			</tr>
			<tr class="hback"> 
				<td style="text-align:right">发送邮件服务器：</td>
      			<td style="text-align:left">
      				<input type="text" name="mail_Server" value="<s:property value='@com.game.util.web.SysConfig@get("mail_Server")'/>" size="50" />
					<br />
					<span class="red">不要带"@"符号,如:admin@gamemaimai.com&nbsp;&nbsp;只需输入admin</span>
				</td>
			</tr>
			<tr class="hback"> 
      			<td style="text-align:right">发送邮件用户名：</td>
      			<td style="text-align:left">
					<input type="text" name="mail_Name" value="<s:property value='@com.game.util.web.SysConfig@get("mail_Name")'/>" size="50" />
				</td>
    		</tr>
		    <tr class="hback"> 
				<td style="text-align:right">发送邮件的密码：</td>
		      	<td style="text-align:left">
					<input type="password" name="mail_Pass" value="<s:property value='@com.game.util.web.SysConfig@get("mail_Pass")'/>" size="50" />
				</td>
		    </tr>
		    <tr class="hback"> 
				<td style="text-align:right">交易方式：</td>
		      	<td style="text-align:left">
		      		<s:checkboxlist name="trade_Type" list="#{'1':'开启寄售交易','2':'开启自主交易'}" listKey="key" listValue="value" value="trade_Type" theme="simple"></s:checkboxlist>
				</td>
		    </tr>
			<tr class="hback">
				<td style="text-align:right">
					版权信息：
				</td>
				<td style="text-align:left">
					<textarea name="copyRight" cols="80" rows="10"><s:property value='@com.game.util.web.SysConfig@get("copyRight")'/></textarea>
				</td>
			</tr>
			<tr class="hback">
				<td style="text-align:right">
					&nbsp;
				</td>
				<td style="text-align:left">
					<input type="submit" value="保存" />
					<input type="reset" value="重置" />
				</td>
			</tr>
		</table>
	</form>
	<s:if test="isSuccess">
		<script type="text/javascript">
			alertDialog('已成功保存！');
		</script>
	</s:if>
	</body>
</html>