<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%-- 网站描述 --%>
		<jsp:include page="/WEB-INF/index/common/description.jsp" />
	</head>
	<body>
		<%-- 头部插入位置 --%>
		<jsp:include page="/WEB-INF/index/common/top_bizInfo.jsp"></jsp:include>
		<decorator:body />
		<%-- 插入底部页面 --%>
		<jsp:include page="/WEB-INF/index/common/buttomSimple.jsp" />
	</body>
</html>