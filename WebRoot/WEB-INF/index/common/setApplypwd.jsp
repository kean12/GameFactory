<%@ page contentType="text/html; charset=utf-8"%>
<script type='text/javascript' src='${ctx}/dwr/interface/account.js'></script>
<style>
#MyDiv{
	position:absolute;
	z-index:10001;
	display:none;
	background:#fff;
	width:300px;
	height:160px
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
function afterSetApplyPwd(){
	var txt_setApplyPwd1 = $("txt_setApplyPwd1").value;
	var txt_setApplyPwd2 = $("txt_setApplyPwd2").value;
	account.setApplyPwd(txt_setApplyPwd1,txt_setApplyPwd2,function(data){
		if(data=='true'){
			alertDialog('更改成功！');
			closeDiv('MyDiv');
			closeDiv('BigDiv');
			window.location.reload() ;
		}else if(data == 'false'){
			alertDialog('更改失败！');
		}else if(data == 'NO_LOGIN'){
			alertDialog('登录状态已经失效，请重新登录！');
			window.location.href = AP + '/user/login.shtml' ;
		}else{
			alertDialog(data);
		}
	});
	$("txt_setApplyPwd1").value="";
	$("txt_setApplyPwd2").value="";
}
</script>
<div class="bbs_orange" style="display: none" id="MyDiv">
<div class="title tl"><a href="javascript:void(0)" onclick="CloseDiv();">关闭</a><span class="ico_7">&nbsp;</span> 设置支付密码</div>
<ul>
<li>新设支付密码：<input id="txt_setApplyPwd1" type="password" onfocus="this.select();" /></li>
<li>确认支付密码：<input id="txt_setApplyPwd2" type="password" onfocus="this.select();" /></li>
</ul>
<div class="pd10 tc"><button type="button" class="loginbutton" onclick="afterSetApplyPwd();">设 定</button></div>
</div>