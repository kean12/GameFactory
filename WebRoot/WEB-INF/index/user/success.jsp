<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	var url="${session.url}";
	<%
		request.getSession().removeAttribute("url");
	%>
	if(url!=null && url!=""){
		window.location=url;
	}else{
		window.location="${ctx}/index.shtml"
	}
</script>