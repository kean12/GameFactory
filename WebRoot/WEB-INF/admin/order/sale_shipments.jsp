<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">    
<html xmlns="http://www.w3.org/1999/xhtml">    
<head>    
	<title>上传发货图片</title>
	<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
		.preview_fake{ /* 该对象用户在IE下显示预览图片 */     
		    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
		    float:left; padding:3px; border:#ccc 1px solid; background:#fff; margin-right:10px; width:90px; height:60px; text-align: center
		}  
		.preview_size_fake{ /* 该对象只用来在IE下获得图片的原始尺寸，无其它用途 */
		    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);       
		    display:none;
		}     
		.scjc{margin-top:10px; width:100%; padding:10px; margin-left:0; border:#ccc 1px solid}
		.preview_input{width:500px;float:left;}
		.preview_input strong{display: block; padding:5px 0}
	</style>
	<script type="text/javascript">var AP="${ctx}";</script>
	<script type="text/javascript" src="${ctx}/js/image.js"></script>
</head>
<body>
	<div class="main_top_title">
		<dl id="manage_top">
			<dt class="manage_top_title">
				<img src="${ctx}/images/admin/ico01.gif" width="6" height="10" />
				<strong>当前位置：</strong>发货管理&gt;&gt;上传发货截图
			</dt>
			<dd class="p9"><a href="#" onclick="history.back();">回上一级</a></dd>
		</dl>
	</div>


	<form action="${ctx}/admin/order/sale_shipments_submit.shtml" method="post" enctype="multipart/form-data">
		<!--上传图片1 -->
		<div class="scjc">
			<div  id="preview_fake" class="preview_fake">
				<img id="preview" class="preview" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)'/>
				<img id="preview_size_fake" class="preview_size_fake" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)' />
			</div>
			<div class="preview_input">
				<strong>图片位置：</strong>
				<input name="upload" type="file" class="vam mr5" size="50" onchange="onUploadImgChange('preview','preview_fake','preview_size_fake',this);" />
			</div>
			<div style="clear:both;height:10px"></div>
		</div>
		
		<div id="img_content"></div>
			<!--上传图片2 -->
		<div style="clear:both; height:10px"></div>
		<div class="pd10">
			<button class="abutton31 mr5" onclick="return add();">
				添加图片
			</button>
		</div>
		<div class="enter_button" style="margin-top:50px; margin-left:200px">
			<button type="submit">提　交</button>
		</div>
		
		<input type="hidden" name="assignID" value="${assign.id }" />
	</form>
</body>
</html>   