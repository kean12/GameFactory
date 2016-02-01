String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {   
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {   
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);   
    } else {   
        return this.replace(reallyDo, replaceWith);   
    }   
}
String.prototype.isNumber = function() {
	if(! /^\d+(\.\d+)?$/.test(this)) return false;
	try{ 
		return parseFloat(this) == this ;
	}catch(ex){
		return false ; 
	}
}
String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
/*ajax*/
function createXMLHTTP(){
	if(window.ActiveXObject){
		var arrSignatures = ["MSXML2.XMLHTTP.5.0","MSXML2.XMLHTTP.4.0","MSXML2.XMLHTTP.3.0","MSXML2.XMLHTTP", "Microsoft.XMLHTTP"];
		for(var i=0;i<arrSignatures.length;i++){
			try{
	             return new ActiveXObject(arrSignatures[i]);
			}catch(e){}
		}
	}else if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	}
}

function callAjax(url, callback){
	oRequest=createXMLHTTP();
    oRequest.open("get",url,false);
    if(!callback){
    	callback=function(){};
    }
    oRequest.onreadystatechange = callback;
	oRequest.setRequestHeader("If-Modified-Since","0"); 
    oRequest.send(null);
}

function addFavorite(title, url){
	if(!url || url == 'undefined'){url=document.location.href;}
	if(window.sidebar){ // Firefox
		window.sidebar.addPanel(title, url,'');
	}else if(window.opera){ // Opera
		var a = document.createElement("A");
		a.rel = "sidebar";
		a.target = "_search";
		a.title = title;
		a.href = url;
		a.click();
	} else if(document.all){ // IE
		if(window.external){	// IE 8
			window.external.AddFavorite(url, title);
		}else{	// IE 6, IE 7
			window.AddFavorite(url, title);
		}
	}
}
function addBM(){	// add BookMark
	addFavorite('游戏买卖网', location.href);
}
function CopyID(id, msg){
	if(!id){id=location.href;}
	window.clipboardData.setData('text',id);
	alert((!msg || msg == 'undefined')? '复制成功':msg);
}
function topNav(index){
	var nav = document.getElementById(index + 'Nav');
	if(!nav){return;}
	nav.className = 'topmenu_current';
}

function gridColor(tableId, color1, color2, color3, start){
	if(!tableId || tableId == undefined){return;}
	var obj=document.getElementById(tableId);
	if(!obj){return;}
	obj = obj.getElementsByTagName("tr");
	if(!obj || obj.length < 2){return;}
	if(color1 == undefined || !color1){color1="#fff";}
	if(color2 == undefined || !color2){color2="#f7f7f7";}
	if(color3 == undefined || !color3){color3="#ffc";}
	if(start == undefined || !start || start < 0){start=1;}
	for(var i=start; i<obj.length;i++){
		obj[i].style.background = obj[i].oldBg = (i%2!=0)? color1 : color2;
		obj[i].onmouseover=function(){this.style.background=color3;}
		obj[i].onmouseout=function(){this.style.background=this.oldBg;}
	}
}
function showDivTable(tableid,num){// 构建函数showtable
	var odd=false;
	var tablename=document.getElementById(tableid);
	if(!tablename || tablename== undefined){return;}
	var rows=tablename.getElementsByTagName("div");
	for (var i=0; i<rows.length/num; i++) {
	if (odd){
	   odd=false;
	}else{
	   rows[i*num].style.backgroundColor="#f5f5f5";
	   odd=true;
	}
	rows[i*num].bgc=rows[i*num].style.backgroundColor;
	rows[i*num].onmouseover=function(){this.style.backgroundColor="#ffc";}
	rows[i*num].onmouseout=function(){this.style.backgroundColor=this.bgc;}
	} 
}


/**
* 选中sleect组建的值
* id select 的id
* value 想要选中的value值，前提是这值在select中必须包含
*/
function setSelectValue(id,value){
	if(! value || value == -1) return;
	var sel = document.getElementById(id);
	if(! sel || sel == 'undefined') return ;
	for(var i = 0; sel.options.length; i++ ){
		if(sel.options[i].value == value){
			sel.options[i].selected = true;
			return;
		}
	}
}
/**
* 选中指定name的checkbox
* o 出发事件的本身
* tagname name
*/
function select_all_checkbox(o , tagname){
	var f = false; 
	if(o.type =='button'){
		f = (o.value == '全 选') ? true : false;
		o.value = (o.value == '全 选') ? '全不选' : '全 选';
	}else{
		f = o.checked;
	}
	var checkboxs = document.getElementsByName(tagname);
	if(! checkboxs || checkboxs == undefined) return ;
	for(var i = 0; i < checkboxs.length ; i++){
		if(! checkboxs[i].disabled ){
			checkboxs[i].checked = f;
		}
	}
}
/**
* 获得url参数
	如：http://localhost/game/user/register.shtml?registerType=completeInformation&u=%E4%BD%A0%E5%A5%BD&p=ni+hao+
	截取location.search 即 ?registerType=completeInformation&u=%E4%BD%A0%E5%A5%BD&p=ni+hao+ 参数进行解析并转码
*/
function getUrlParam( paramName ){
	var oRegex = new RegExp( '[\?&]' + paramName + '=([^&]+)', 'i' ) ;
	var oMatch = oRegex.exec( location.search ) ;
	
	if ( oMatch && oMatch.length > 1 )
		return decodeURIComponent( oMatch[1] ) ;
	else
		return '' ;
}