<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />

	</head>
	<body>
		<div class="yhzx_rightside">
			<div class="yhzx_title">
				出售中的宝贝
			</div>
			<div class="sale_list_title">
				<div class="sale_u1"></div>
				<div class="sale_u21">
					编号
				</div>
				<div class="sale_u3">
					信息标题、物品类型、游戏/区/服
				</div>
				<div class="sale_u4">
					价格
				</div>
				<div class="sale_u5">
					件数
				</div>
				<div class="sale_u6">
					剩余时间
				</div>
				<div class="sale_u7">操作</div>
				<div class="clear"></div>
			</div>
			<!--list -->
		<script type="text/javascript" src="${ctx}/js/index/time_compute.js"></script>
		<form name="store_form" action="" method="post">
		<s:iterator value="bizInfoList" status="index">
			<div class="sale_list" >
				<div class="sale_u1"><input type="checkbox" name="idKey" value="${id}" /></div>
				<div class="sale_u21">${serial }</div>
				<div class="sale_u3"><a href="${ctx}/user/trade/bizInfo/detail.shtml?bizInfoID=${id}" class="blue">${title}</a></div>
				<div class="sale_u4 orange">${price }</div>
				<div class="sale_u5">${stock }</div>
				<div class="sale_u6" id="surplus${index.index+1}" /><script type="text/javascript">JSTime("${endSellTime}","surplus${index.index+1}");</script></div>
				<div class="sale_u7">
					<a onclick="window.location='${ctx}/user/trade/bizInfo/withdraw.shtml?bizInfoID=${id}&goPage=${goPage}'" class="red" style="cursor: pointer;">下架</a>|<a onclick="window.location='${ctx}/user/trade/bizInfo/sale_delete.shtml?bizInfoID=${id}&goPage=${goPage}'" class="red" style="cursor: pointer;">删除</a>
				</div>
				<div class="clear"></div>
			</div>
		</s:iterator>
		</form>
			<div class="manu" style="text-align:left; padding:5px 5px 3px">
				<input type="checkbox" onclick="chk_checkbox(this);" />全选
				<button class="delbutton ml20" onclick="chk_delete();">删除</button>
				<button class="delbutton ml10" onclick="chk_withdraw();">下架</button>
			</div>
<script type="text/javascript">
	function chk_checkbox(obj){
		var arr = document.getElementsByName("idKey");
		for(var i=0;i<arr.length;i++){
			arr[i].checked=obj.checked;
		}
	}
	function chk_delete(){
		if(confirm("删除后数据不能恢复，你确定要删除数据？")){
			document.store_form.action="${ctx}/user/trade/bizInfo/sale_delete.shtml?goPage=${goPage}";
			document.store_form.submit();
		}
	}
	function chk_withdraw(){
		document.store_form.action="${ctx}/user/trade/bizInfo/withdraw.shtml?goPage=${goPage}";
		document.store_form.submit();
	}
</script>
			<!--list end -->
			
		</div>
		<div class="clear"></div>
	</body>
</html>