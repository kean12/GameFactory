<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>游戏买卖</title>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	</head>
	<body style="background: #151515">
		<table border="0" cellspacing="0" cellpadding="0" style="width: 99%; height: 99%; border: #333 1px solid">
			<tr>
				<td colspan="3" bgcolor="#151515" height="20" class="pd5 tc">
					<A href="#"><IMG class=prev alt=上一张 onclick="chk_pic(-1);" src="${ctx}/images/prev.gif" align=absMiddle></A>
					<A href="#"><IMG class=next alt=下一张 onclick="chk_pic(1);" src="${ctx}/images/next.gif" align=absMiddle></A>
				</td>
			</tr>
			<tr>
				<td width="79" align="center" valign="middle" bgcolor="#151515">
					<A href="#"><IMG height=86 alt=上一张 onclick="chk_pic(-1);" src="${ctx}/images/prev_arr.jpg" width=76></A>
				</td>
				<td align="center" valign="middle" bgcolor="#151515">
					<s:iterator value="pictureList" id="index">
						<s:if test="pictureID==id">
							<img name="imgobject" src="${ctx}${route}" />
						</s:if>
						<s:else>
							<img name="imgobject" src="${ctx}${route}" style="display: none;" />
						</s:else>
					</s:iterator>
				</td>
				<td width="79" align="center" valign="middle" bgcolor="#151515">
					<A href="#"><IMG height=86 alt=下一张 onclick="chk_pic(1);" src="${ctx}/images/next_arr.jpg" width=76></A>
				</td>
			</tr>
			<tr>
				<td colspan="3" height="20" bgcolor="#151515" class="pd5 tc">
					<A href="#"><IMG class=prev alt=上一张 onclick="chk_pic(-1);" src="${ctx}/images/prev.gif" align=absMiddle></A>
					<A href="#"><IMG class=next alt=下一张 onclick="chk_pic(1);" src="${ctx}/images/next.gif" align=absMiddle></A>
				</td>
			</tr>
		</table>
		
<script type="text/javascript">
	function chk_pic(pre){
		var arr=document.getElementsByName("imgobject");
		var index;
		for(var i=0;i<arr.length;i++){
			if(arr[i].style.display==""){
				index=i;
			}
		}
		
		var obj_index=index+pre;
		if(obj_index<0){
			obj_index=arr.length-1;
		}else if(obj_index>arr.length-1){
			obj_index=0;
		}
		for(var i=0;i<arr.length;i++){
			if(obj_index==i){
				arr[i].style.display="";
			}else{
				arr[i].style.display="none";
			}
			
		}
	}
</script>
	</body>
</html>

