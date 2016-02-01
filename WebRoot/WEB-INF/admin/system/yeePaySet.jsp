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
	<form action="${ctx}/admin/system/yeePaySet!save.shtml" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr class="hback">
				<td width="140" style="text-align:right">
					商户编号：
				</td>
				<td style="text-align:left"> 
					<input type="text" name="p1_MerId" value="<s:property value='@com.game.util.web.YeePayConfig@get("p1_MerId")'/>" size="50" />
				</td>
			</tr>
			<tr class="hback">
				<td style="text-align:right">
					商户密钥：
				</td>
				<td style="text-align:left">
					<input type="text" name="keyValue" value="<s:property value='@com.game.util.web.YeePayConfig@get("keyValue")'/>" size="50" />
				</td>
			</tr>
			<tr class="hback">
				<td style="text-align:right">
					交易请求地址：
				</td>
				<td style="text-align:left">
					<input type="text" name="onlinePaymentReqURL" value="<s:property value='@com.game.util.web.YeePayConfig@get("onlinePaymentReqURL")'/>" size="50" />
				</td>
			</tr>
			<tr class="hback">
				<td style="text-align:right">
					查询和退款地址：
				</td>
				<td style="text-align:left">
					<input type="text" name="queryRefundReqURL" value="<s:property value='@com.game.util.web.YeePayConfig@get("queryRefundReqURL")'/>" size="50" />
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