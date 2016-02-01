<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>宝贝图片查看</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<style>
			img {
				padding: 5px;
				border: #ccc 1px solid;
				clear: right;
				display: block;
				margin: 5px 0
			}
			
			hr {
				display: block;
				height: 0;
				padding: 0;
				margin: 0;
				border-bottom: #ccc 1px dashed;
				font-size: 0
			}
		</style>
	</head>
	<body>
		<s:if test='order.bizInfo.pictures==null || order.bizInfo.pictures.size()==0'>
			用户没有上传商品图片!
		</s:if>
		<s:else>
			<s:iterator value="order.bizInfo.pictures" status="index">
				<img src="${ctx}${route}" />
				<s:if test="!#index.last"><hr /></s:if>
			</s:iterator>
		</s:else>
	</body>
</html>
