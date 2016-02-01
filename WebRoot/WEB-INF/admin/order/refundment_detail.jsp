<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/admin/style.css" rel="stylesheet" type="text/css" />

<style>
 #MyDiv{
		position:absolute;
		z-index:10001;
		display:none;
		background:#fff;
		width:300px;
		height:200px
  }
     #MyDiv ul{width:280px; margin-left:10px; margin-top:15px}
   #MyDiv li{height:25px; line-height:25px; border-bottom:#ccc 1px dotted; text-align:left; padding-left:5px}
 </style>
<script type="text/javascript">
	function setDiv(){

		var sWidth,sHeight;
		sWidth = 100;
		//屏幕可用工作区高度： window.screen.availHeight;
		//屏幕可用工作区宽度： window.screen.availWidth;
		//网页正文全文宽：     document.body.scrollWidth;
		//网页正文全文高：     document.body.scrollHeight;
		if(window.screen.availHeight > document.body.scrollHeight){  //当高度少于一屏
			sHeight = window.screen.availHeight;  
		}else{//当高度大于一屏
			sHeight = document.body.scrollHeight;   
		}

		//创建遮罩背景
		var maskDiv = document.createElement("div");  //根据指定的标签建立新的对象实例
		maskDiv.setAttribute("id","BigDiv");
		maskDiv.style.position = "absolute";
		maskDiv.style.top = "0";
		maskDiv.style.left = "0";
		maskDiv.style.background = "#777";
		maskDiv.style.filter = "Alpha(opacity=30);";
		maskDiv.style.opacity = "0.3";
		//maskDiv.style.width = sWidth + "px";
		//maskDiv.style.height = sHeight + "px";
		maskDiv.style.width = sWidth + "%";
		maskDiv.style.height = sHeight + "px";
		maskDiv.style.zIndex = "10000";
		document.body.appendChild(maskDiv);
		
		
		//动态设置div的上边距和左边距，使弹出div居中打开
		
		var MyDiv =document.getElementById("MyDiv");
		var MyDiv_w = getStyle(MyDiv,"width");
		var MyDiv_h = getStyle(MyDiv,"height");
	    
		MyDiv_w = parseInt(MyDiv_w); //去掉 单位 "px"
		MyDiv_h = parseInt(MyDiv_h);

		var width = pageWidth(); 
		var height = pageHeight();
		var left = leftPosition();
		var top = topPosition();

		var Div_topposition = top + (height / 2) - (MyDiv_h / 2); //计算上边距
		var Div_leftposition = left + (width / 2) - (MyDiv_w / 2); //计算左边距

		MyDiv.style.left = Div_leftposition + "px";  //拼接上 单位"px"
		MyDiv.style.top =   Div_topposition + "px";
		MyDiv.style.display = "block";  //设置弹出div显示
	}
	
	//关闭窗口和遮罩层。
		function CloseDiv(){
			var Bigdiv = document.getElementById("BigDiv");
			var Mydiv = document.getElementById("MyDiv");
			document.body.removeChild(Bigdiv); 
			Mydiv.style.display="none";
		}
		
	//弹出层跟随滚动条滚动
	window.onscroll = window_onscroll;
	function window_onscroll(){
		var MyDiv =document.getElementById("MyDiv");
		var MyDiv_h = getStyle(MyDiv,"height");
		MyDiv_h = parseInt(MyDiv_h);
		var height = pageHeight();
		var top = topPosition();
		var Div_topposition = top + (height / 2) - (MyDiv_h / 2); //计算上边距
		MyDiv.style.top =  Div_topposition + "px";
	}
				
// 计算当前窗口的宽度 //
function pageWidth(){
			 return window.innerWidth != null ? window.innerWidth : document.documentElement && document.documentElement.clientWidth ? document.documentElement.clientWidth : document.body != null ? document.body.clientWidth : null;
}

// 计算当前窗口的高度 //
function pageHeight(){
			 return window.innerHeight != null? window.innerHeight : document.documentElement && document.documentElement.clientHeight ? document.documentElement.clientHeight : document.body != null? document.body.clientHeight : null;
}

// 计算当前窗口的上边滚动条//
function topPosition(){
			  return typeof window.pageYOffset != 'undefined' ? window.pageYOffset : document.documentElement && document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop ? document.body.scrollTop : 0;
}

// 计算当前窗口的左边滚动条//
function leftPosition(){
			 return typeof window.pageXOffset != 'undefined' ? window.pageXOffset : document.documentElement && document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft ? document.body.scrollLeft : 0;
}
			
//解决外嵌样式style , 用js获取不到的问题。
function getStyle(elem, name){
			if(elem.style[name])
				return elem.style[name];
			else if(elem.currentStyle)	//ie
				return elem.currentStyle[name];
			else if(document.defaultView && document.defaultView.getComputedStyle){	//w3c
				name = name.replace(/([A-Z])/g,"-$1");
				name = name.toLowerCase();
				
				var s = document.defaultView.getComputedStyle(elem,"");
				return s && s.getPropertyValue(name);
			} else
				return null
}
</script>


<div class="main_top_title">
	<dl id="manage_top">
		<dt class="manage_top_title">
			<img src="/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>退款管理>>详细信息
		</dt>
		<dd class="p9">
			<a href="#" onclick="history.back();">回上一级</a>
		</dd>
	</dl>
</div>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
	<tr>
		<th width="10%">
			标识列:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.id }" readonly />
		</td>
		<th width="10%">
			订单号:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.orderNum }" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			订单标题:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.title }" readonly />
		</td>
		<th width="10%">
			下单时间:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.orderTime}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			物品单价:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.price}" readonly />
		</td>
		<th width="10%">
			中间金额:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.assureMoney }" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			购买数量:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.buyBum}" readonly />
		</td>
		<th width="10%">
			发布者:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.owner.username}" disabled="disabled" />
		</td>
	</tr>
	<tr>
		<th width="10%">
			总价格:
		</th>
		<td width="40%">
			<input type="text" name="" value="${assign.order.sumPrice}" readonly />
		</td>
		<th width="10%">
			购买者:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.consumer.username}" disabled="disabled" />
		</td>
	</tr>
	<tr>
		<th width="10%">
			买家角色名:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.playerRole}" readonly />
		</td>
		<th width="10%">
			卖家QQ:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.sellQQ}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			买家角色等级:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.playerGrade}" readonly />
		</td>
		<th width="10%">
			卖家电话:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.sellPhoneNum }" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">
			买家QQ:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.playQQ}" readonly />
		</td>
		<th width="10%">
			订单状态:
		</th>
		<td width="40%">
			<%--0 待付款|1 已付款|2 已发货3 交易关闭|4 退款处理|5 退款完成|6 交易成功|7拒绝客户退款--%>
			<s:if test="assign.order.state==0">待付款</s:if>
			<s:elseif test="assign.order.state==1">已付款</s:elseif>
			<s:elseif test="assign.order.state==2">已发货</s:elseif>
			<s:elseif test="assign.order.state==3">交易关闭</s:elseif>
			<s:elseif test="assign.order.state==4">退款处理</s:elseif>
			<s:elseif test="assign.order.state==5">退款完成</s:elseif>
			<s:elseif test="assign.order.state==6">交易成功</s:elseif>
			<s:elseif test="assign.order.state==7">拒绝客户退款</s:elseif>
		</td>
	</tr>
	<tr>
		<th width="10%">
			买家电话:
		</th>
		<td width="40%">
			<input type="text" value="${assign.order.playPhoneNum}" readonly />
		</td>
		<th width="10%">
			游戏/区/服:
		</th>
		<td width="40%">
			<s:if test="game!=null">
				${assign.order.game.gameName }
			</s:if>
			<s:else>
				${assign.order.server.area.game.gameName }&nbsp;/&nbsp;${assign.order.server.area.areaName }&nbsp;/&nbsp;${assign.order.server.serverName }
			</s:else>
		</td>
	</tr>
	<tr>
		<th width="10%">
			商品编号:
		</th>
		<td width="40%">
			<input type="text" size="30" value="${assign.order.bizInfo.serial }" disabled="disabled" />
		</td>
		<th width="10%">
			交易地点:
		</th>
		<td width="40%">
			${assign.order.site}
		</td>
	</tr>
	<tr>
		<th width="10%">
			寄售账号:
		</th>
		<td width="40%">
			<input type="text" size="30" value="${assign.order.bizInfo.account }" readonly="readonly" />
		</td>
		<th width="10%">
			寄售账号密码:
		</th>
		<td width="40%">
			<input type="text" size="30" value="${assign.order.bizInfo.password }" readonly="readonly" />
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<form name="refundmentForm" action="${ctx}/admin/order/refundment_process.shtml" method="post">
				<input type="hidden" name="assignID" value="${assign.id }" />
				<input type="hidden" name="orderState" id="orderState" value="" />
				<input type="hidden" name="outcome" id="outcomeVal" value="" />
				<input type="button" value="发货截图" onclick="chk_shipment('shipmentDiv');" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="退款理由" onclick="chk_shipment('reasonDiv');" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="退  款"  onclick="chk_refundment('5');" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="拒绝退款" onclick="setDiv();" />
			</form>
		</td>
	</tr>
</table>
<font color="red" size="2"></font>




<div id="shipmentDiv" style="display:none;">
	<s:if test="assign.shipmentList.size>0">
		<s:iterator value="assign.shipmentList">
			<img src="${ctx}${src }" /><br /><hr class="red" />
		</s:iterator>
	</s:if>
	<s:else>
		不存在发货截图
	</s:else>
</div>

<div id="reasonDiv" style="display:none;">
	<s:if test="assign.order.reason!=null">
		${assign.order.reason}
		<br /><hr class="red" />
	</s:if>
	<s:else>
		不存在退款理由
		<br /><hr class="red" />
	</s:else>
</div>

<div class="bbs_orange"  id="MyDiv">
	<ul>
		<li>
			请输入拒绝退款原因：
		</li>
		<li>
			<textarea id="outcomeTxt" rows="10" cols="31"></textarea>
		</li>
		
	</ul>
	<div class="pd10 tc">
		<button type="button" class="loginbutton" onclick="chk_refundment('7');">提&nbsp;&nbsp;交</button>
		&nbsp;&nbsp;
		<button type="button" class="loginbutton" onclick="CloseDiv();">取&nbsp;&nbsp;消</button>
	</div>
</div>
	



<script type="text/javascript">
function chk_shipment(obj){
	if(document.getElementById(obj).style.display=="none"){
		document.getElementById(obj).style.display="";
	}else{
		document.getElementById(obj).style.display="none";
	}
}

	function chk_refundment(orderState){
		if(orderState==5){
			if(confirm("您确定退款给用户吗？")){
				document.getElementById("orderState").value=orderState;
				document.refundmentForm.submit();
			}
		}else if(orderState==7){
			if(confirm("您确定拒绝退款吗？")){
				var outcomeTxt=document.getElementById("outcomeTxt").value;
				if(outcomeTxt.length==0){
					alert("请输入拒绝退款原因");
					return false;
				}
				
				document.getElementById("orderState").value=orderState;
				document.getElementById("outcomeVal").value=outcomeTxt;
				document.refundmentForm.submit();
			}
		}
	}
</script>


