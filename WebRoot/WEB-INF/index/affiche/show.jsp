<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>游戏买卖</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<meta name=GENERATOR content="MSHTML 8.00.7600.16444">
</head>
<body>
<div class="contaner" style="background:url(images/bg/line_bg.gif) left top repeat-y">
	<div class="yhzx_leftside" style="border-top:#F90 3px solid">
		<dl class="yhzx_left_nav">
			<dt><a href="${ctx}/affiche/more.shtml?type=1">网站公告</a></dt>
			<dt><a href="${ctx}/affiche/more.shtml?type=2">最近动态</a></dt>
			<dt><a href="${ctx}/affiche/more.shtml?type=3">官方新闻</a></dt>
		</dl>
	</div>
  <!--left end -->
	<div class="yhzx_rightside" style="border-top:#F90 3px solid">
		<div class="txtbox">
			<h2><s:property value="affiche.title" /></h2>
			<div class="date">
				类别：
				<s:if test="affiche.type==1">网站公告</s:if>
				<s:elseif test="affiche.type==2">最近动态</s:elseif>
				<s:elseif test="affiche.type==3">官方新闻</s:elseif>
				&nbsp;&nbsp;发布时间： <s:property value="affiche.time.substring(0,10)" /> </div>
			<div class="txt">
				${affiche.content}
			</div>
		</div>
	</div>
    <div class="clear"></div>
</div>
</body>
</html>
