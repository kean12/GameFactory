<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	<meta name=GENERATOR content="MSHTML 8.00.7600.16444">
</head>
<body>
<!--menu end -->
<div class="contaner" style="background:url(images/bg/line_bg.gif) left top repeat-y">
	<div class="yhzx_leftside" style="border-top:#F90 3px solid">
		<dl class="yhzx_left_nav">
      		<dt><a href="${ctx}/affiche/more.shtml?type=1">网站公告</a></dt>
			<dt><a href="${ctx}/affiche/more.shtml?type=2">最近动态</a></dt>
			<dt><a href="${ctx}/affiche/more.shtml?type=3">官方新闻</a></dt>
		</dl>
	</div>
  <!--left end -->
	<div class="yhzx_rightside">
		<div class="yhzx_title">
			<s:if test="type==3">
				官方新闻
			</s:if>
			<s:elseif test="type==2">
				最新动态
			</s:elseif>
			<s:elseif test="type==1">
				网站公告
			</s:elseif>
		</div>
		<table width="100%" class="yhzx_list" style="background:none; border:0">
      		<tr>
        		<th width="63%" scope="col">标题</th>
        		<th width="16%" scope="col">作者</th>
        	</tr>
        	<s:iterator value="afficheList">
        		<tr>
	        		<td class="news_list_title"><a href="${ctx}/affiche/show.shtml?afficheID=${id}" target="_blank"> <s:property value="title" /></a></td>
	        		<td><div><s:property value="time.substring(0,10)" /></div></td>
	        	</tr>
        	</s:iterator>
		</table>
		<div class="manu">
			<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
			<form name="pageForm" action="${ctx}/affiche/more.shtml" method="get">
				<input type="hidden" id="goPage" name="goPage" />
				<input type="hidden" id="type" name="type" value="${type}" />
			</form>
		</div>
	
</div>
<div class="clear"></div>
</div>
</body>
</html>


