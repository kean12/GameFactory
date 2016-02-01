<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert image</title>
	</head>
	<body>
		<script language="javascript" type="text/javascript">
			parent.KE.plugin["image"].insert("${id}", "${saveUrl}", "${imgTitle}","${imgWidth}", "${imgHeight}", "${imgBorder}");
		</script>
	</body>
</html>