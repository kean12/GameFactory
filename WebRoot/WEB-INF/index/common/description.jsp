<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<s:property value='@com.game.util.web.SysConfig@get("siteName")'/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="<s:property value='@com.game.util.web.SysConfig@get("description")'/>"/>
<meta name="keywords" content="<s:property value='@com.game.util.web.SysConfig@get("keywords")'/>"/>
<meta http-equiv="X-UA-Compatible" content="IE=7" />

<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0);
%> 


<link href="${ctx}/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="${ctx}/favicon.ico" rel="icon" type="image/x-icon" />
<link href="${ctx}/favicon.ico" rel="bookmark" type="image/x-icon" />
<link rel="search" type="application/opensearchdescription+xml" href="/provider.xhtml" title="买卖网搜索" />
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">var AP="${ctx}";</script>
<script type='text/javascript' src='${ctx}/dwr/prototype.js'></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<script type="text/javascript" src="${ctx}/js/tool.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>

