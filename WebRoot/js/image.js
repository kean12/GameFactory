function loaded(myimg,mywidth,myheight)
{
 var tmp_img = new Image();
 tmp_img.src = myimg.src;
 image_x = tmp_img.width;
 image_y=tmp_img.height;

 if(image_x > mywidth)
 {
  tmp_img.height = image_y * mywidth / image_x;
  tmp_img.width = mywidth;

  if(tmp_img.height > myheight)
  {
   tmp_img.width = tmp_img.width * myheight / tmp_img.height;
   tmp_img.height=myheight;
  }
 }
 else if(image_y > myheight)
 {
  tmp_img.width = image_x * myheight / image_y;
  tmp_img.height=myheight;
  
  if(tmp_img.width > mywidth)
  {
   tmp_img.height = tmp_img.height * mywidth / tmp_img.width;
   tmp_img.width=mywidth;
  }
 }
  
 myimg.width = tmp_img.width;
 myimg.height = tmp_img.height;
}

var i=1;
function add(){
	var tbody=document.getElementById("img_content");
//	var i=tbody.childNodes.length+1;//标记
	var div = document.createElement('div');
	div.className="scjc";
	div.id="content"+i;
	tbody.appendChild(div);

	var div1 = document.createElement('div');
	div1.id="preview_fake"+i;
	div1.className="preview_fake";
	div.appendChild(div1);
	
	var img1=document.createElement('img');
	img1.id="preview"+i;
	img1.className="preview";
	img1.src=AP+"/images/slt.gif";
	img1.align="left";
	img1.height="60";
	img1.width="90";
	img1.onload='loaded(this,90,60)';
	div1.appendChild(img1);

	var img2=document.createElement('img');
	img2.id="preview_size_fake"+i;
	img2.className="preview_size_fake";
	img2.src=AP+"/images/slt.gif";
	img2.align="left";
	img2.height="60";
	img2.width="90";
	img2.onload='loaded(this,90,60)';
	img2.style.display="none";
	div1.appendChild(img2);
	
	var div2=document.createElement('div');
	div2.className="preview_input";
	div.appendChild(div2);
	
	var strong=document.createElement('strong');
	strong.appendChild(document.createTextNode("图片位置："));
	div2.appendChild(strong);
	
	if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){
		var input=document.createElement("input");
		input.name="upload";
		input.type="file";
		input.className="vam mr5";
		input.size="50";
		AttachEvent(input,"change",onUploadImgChange,'preview'+i,'preview_fake'+i,'preview_fake'+i,input);
		div2.appendChild(input);
	}else{ 
		var str="'preview"+i+"','preview_fake"+i+"','preview_fake"+i+"',this";
		var input=document.createElement("<input name='upload' type='file' class='vam mr5' size='50' onchange=\"onUploadImgChange("+str+");\" />");
		div2.appendChild(input);
		var delInput=document.createElement("<input type=\"button\" value=\"删 除\" class=\"vam mr5\" style=\"height: 21px\" onclick=\"removeObj('content"+i+"')\" />");
		div2.appendChild(delInput);
		
	}
	
	var div3=document.createElement('<div style="clear:both;height:10px"></div>');
	div.appendChild(div3);
	i=i+1;
	return false;
}

function removeObj(objdiv){
	var obj=document.getElementById(objdiv);
	obj.parentNode.removeChild(obj);
}

function AttachEvent(target, eventName, handler, args1,args2,args3,args4){
    var eventHandler = handler;
    if(arguments.length>3){
        eventHander = function(e){
        	handler.call(handler, args1,args2,args3,args4);
        }
    }
    if(window.attachEvent)//IE
        target.attachEvent("on" + eventName, eventHander );
    else//FF
        target.addEventListener(eventName, eventHander, false);
}





function onUploadImgChange(preview,preview_fake,preview_size_fake,sender){
    if( !sender.value.match( /.jpg|.gif|.png|.bmp/i ) ){
        alertDialog('图片格式无效！');
        return false;
    }
         
    var objPreview = document.getElementById( preview );
    var objPreviewFake = document.getElementById( preview_fake );
    var objPreviewSizeFake = document.getElementById( preview_size_fake );
         
    if( sender.files &&  sender.files[0] ){
    	objPreview.style.display='';
        objPreview.style.width = '90';
        objPreview.style.height = '60';
        
        // Firefox 因安全性问题已无法直接通过 input[file].value 获取完整的文件路径     
        objPreview.src = sender.files[0].getAsDataURL();
    }else if( objPreviewFake.filters ){      
        // IE7,IE8 在设置本地图片地址为 img.src 时出现莫名其妙的后果     
        //（相同环境有时能显示，有时不显示），因此只能用滤镜来解决     
        // IE7, IE8因安全性问题已无法直接通过 input[file].value 获取完整的文件路径     
        sender.select();
        var imgSrc = document.selection.createRange().text;
        objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
        objPreviewSizeFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
        var zoomParam = clacImgZoomParam( 90, 60, 90, 60 );
        objPreviewSizeFake.style.width = '90px';
        objPreviewSizeFake.style.height = '60px';
        objPreviewSizeFake.style.marginTop = '0px';
        objPreviewSizeFake.style.marginLeft = '0px';
        objPreview.style.display = 'none';
        
    }
}
    
function clacImgZoomParam( maxWidth, maxHeight, width, height ){     
    var param = { width:width, height:height, top:0, left:0 }; 
         
    if( width>maxWidth || height>maxHeight ){     
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
             
        if( rateWidth > rateHeight ){     
            param.width =  maxWidth;     
            param.height = height / rateWidth;     
        }else{     
            param.width = width / rateHeight;     
            param.height = maxHeight;     
        }     
    }
         
    param.left = (maxWidth - param.width) / 2;     
    param.top = (maxHeight - param.height) / 2;     
         
    return param;     
}