<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body onload="topNav('index')">
<div class="contaner">
<div class="w223 bbs_orange fl">
<div class="title w213">
	<a href="${ctx}/affiche/more.shtml">更多&gt;&gt;</a><span class="ico_4">&nbsp;</span>网站公告</div>
	<ul class="index_new">
	<s:iterator value="afficheList">
		<li><a href="${ctx}/affiche/show.shtml?afficheID=${id}" target="_blank" title="<s:property value="title" />&nbsp;&nbsp;<s:property value="time.substring(0,10)" />" ><b><s:property value="time.substring(0,10)" /></b><span><s:property value="title" /></span></a></li>
	</s:iterator>
	</ul>
</div>
<!--news end -->
<div class="w480 fl ml10">
<div class="focus_nt">
<script type="text/javascript" src="${ctx}/js/xcargetflash.js"></script>
<div id="focusViwer" align=center> </div>
<script type="text/javascript">
<!--
var focus_width=480;
var focus_height=230;
var text_height=0;
var swf_height = focus_height+text_height;
var curhref=document.location.href;
var FocusFlash = new xcarFlash("${ctx}/media/pixviewer.swf", "focusflash", focus_width, swf_height, "6", "#CCCCCC", false, "High");
FocusFlash.addParam("allowScriptAccess", "sameDomain");
FocusFlash.addParam("menu", "false");
FocusFlash.addParam("wmode", "opaque");
FocusFlash.addVariable("pics", '${ctx}/uploadFiles/jbhl.jpg|${ctx}/uploadFiles/banner.jpg|${ctx}/uploadFiles/banner_qiugou.jpg');
<%--
FocusFlash.addVariable("links", '${ctx}/jbhl.html|${ctx}/uploadFiles/52pk.htm|/uploadFiles/qiugou.htm');
--%>

//FocusFlash.addVariable("texts", texts);
FocusFlash.addVariable("borderwidth", focus_width);
FocusFlash.addVariable("borderheight", focus_height);
FocusFlash.addVariable("curhref", curhref);
FocusFlash.addVariable("textheight", text_height);
FocusFlash.write("focusViwer");
//-->
</script>
</div>
</div>
<!--banner end -->
<div class="w225 ml10 fl">
	<button class="index_xszn"></button>
	<button class="index_zxwd"></button>
	<div class="blank10"></div>
	<div class="w223 bbs_orange fl">
		<div class="title w213">
			<a href="${ctx}/affiche/more.shtml?type=2">更多&gt;&gt;</a><span class="ico_5">&nbsp;</span>最新动态
		</div>
		<ul class="index_new">
			<s:iterator value="newsList">
				<li>
					<a href="${ctx}/affiche/show.shtml?afficheID=${id}" target="_blank" title="<s:property value="title" />&nbsp;&nbsp;<s:property value="time.substring(0,10)" />" ><b><s:property value="time.substring(0,10)" /></b><span><s:property value="title" /></span></a>
				</li>
			</s:iterator>
		</ul>
		<div class="blank10"></div>
	</div>
</div>
<!--card end -->
</div>

<div class="blank10"></div>

<div class="contaner">
<div class="w223 bbs_orange fl">
<div class="title w213"><span class="ico_6">&nbsp;</span>交易排行榜</div>
<ul class="index_sale">
	<s:iterator value="rankList" id="rank">
		<li><a href="${ctx}/ranking.shtml?gameID=${rank[1]}" target="_blank">${rank[0]}</a></li>
	</s:iterator>
	<s:if test="rankList.size()<10">
		<s:bean name="org.apache.struts2.util.Counter" id="counter">
			<s:param name="first" value="rankList.size()+1" />
			<s:param name="last" value="10" />   
			<s:iterator>
				<li>虚位以待</li>
			</s:iterator>
		</s:bean>
	</s:if>
</ul>
</div>
<!--交易排行榜 -->
<div class="w713 bbs_orange fl ml10">
<div class="title w703"><span class="ico_7">&nbsp;</span>最新成功订单</div>
<div class="index_sale1">
<table>
  <tr>
    <th scope="col">订单编号</th>
    <th scope="col">商品名称</th>
    <th scope="col">下单用户</th>
    <th scope="col">状态</th>
    <th scope="col">下单时间</th>
    <th scope="col">金额</th>
  </tr>
<tbody id="salelist">
<s:iterator value="orderList">
	<tr>
		<td>${orderNum }</td>
		<td>
			<span class="mintxt" style="width:280px"><s:if test="bizInfo.game!=null">${bizInfo.game.gameName}</s:if>
			<s:else>${bizInfo.server.area.game.gameName}/${bizInfo.server.area.areaName}/${bizInfo.server.serverName}</s:else>/${bizInfo.bizKind.kindName}
		</span></td>
	    <td><s:property value="consumer.username.substring(0,2)" />****</td>
	    <td class="red">完成</td>
	    <td>${orderTime}</td>
	    <td>${sumPrice}</td>
	</tr>
</s:iterator>
</tbody>
</table>
</div>
<script src="js/list.js" type="text/javascript">--</script>
<script type="text/javascript">gridView('salelist', 'tr1', 'tr2', 'tr3');</script>
</div>
<!--交易排行榜 -->
</div>
	
</body>
</html>

