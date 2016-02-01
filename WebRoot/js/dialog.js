function alertDialog(content,lock){
	if(lock==null){
		art.dialog({content:content, fixed:true,lock:true,style:'succeed',width:'280',height:'80'},function(){});
	}else{
		art.dialog({content:content, fixed:true,lock:lock,style:'succeed',width:'280',height:'80'},function(){});
	}
}

function alertMessage(content,lock){
	if(lock==null){
		art.dialog({content:content, fixed:true,width:'390',height:'105'});
	}else{
		art.dialog({content:content, fixed:true,lock:lock,width:'390',height:'105'});
	}
}

function alertOpen(title,url,width,height){
	art.dialog({title:title,iframe:url,lock:true,width:width,height:height});
}
