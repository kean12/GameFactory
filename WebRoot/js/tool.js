function $(id){
	return document.getElementById(id);
}

var $import=function(s){
	var script=document.createElement("script");
	script.setAttribute("type","text/javascript"); 
	script.setAttribute("src",s); 
	try { 
		document.appendChild(script); 
	}catch(e){
	} 
};

var $include=function(s){
	var link=document.createElement("link");
	link.setAttribute("href",s); 
	link.setAttribute("rel","stylesheet");
	link.setAttribute("type","text/css");
	try { 
		document.appendChild(link); 
	}catch(e){
	} 
};

function relImg(imgID){
	document.getElementById(imgID).src=AP+"/authImg?h="+Math.random();
}

function firstfocus(flag,div){
	var imgobj=$(div);
	if(flag){
		if(imgobj.src.indexOf("identifyingCodeImage.gif")!=-1){
			relImg(div);
		}
	}
}

String.prototype.getByteLength=function(){ 
	return this.replace(/[^\x00-\xff]/g,"**").length;
}

String.prototype.Trim = function(){     
	return this.replace(/(^\s*)|(\s*$)/g, "");     
}

String.prototype.LTrim = function(){     
	return this.replace(/(^\s*)/g, "");     
}
  
String.prototype.RTrim = function(){     
	return this.replace(/(\s*$)/g, "");
}

function fEvent(sType, oInput) {
	if(sType=="focus"){
		oInput.isfocus = true;
		oInput.style.backgroundColor = 'FFFFD8';
		oInput.select();
	}else{
		oInput.isfocus = false;
	}
}

function showDiv(obj){
	$(obj).style.display="";
}

function showBlock(obj){
	$(obj).style.display="block";
}

function closeDiv(obj){
	$(obj).style.display="none";
}

function jumpPage(goPage){
	if(goPage=="" || isNaN(goPage)){
		alertDialog('请输入一个数字！');
		return false;
	}
	document.pageForm.goPage.value=goPage;
	document.pageForm.submit();
}

$import(AP + "/js/dialog.js");
$import(AP + "/artDialog/artDialog.js");
$include(AP + "/artDialog/skin/default.css");
$import(AP + "/calender/al-calender.js");
$include(AP + "/calender/al-calender.css");