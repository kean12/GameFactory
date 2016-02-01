<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
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
	
	<script type="text/javascript" src="${ctx}/js/image.js"></script>
		<div class="contaner tc">
			<div class=" ptb10">
				您的位置：
				<a href="${ctx}" class="blue">首页</a> >
				<a href="${ctx}/user/home.shtml" class="blue">用户中心</a> >
				<a href="${ctx}/user/trade/order/list_sold_items.shtml" class="blue">已卖出的宝贝</a> > 上传交易截图
			</div>
			<div class="attention bbs_orange red f14">
				提交相关交易截图，作为交易凭证。
			</div>
		
		<form name="ship" action="${ctx}/user/trade/order/shipments.shtml" method="post" enctype="multipart/form-data">
			<!--上传图片1 -->
			<div class="scjc">
				<div  id="preview_fake" class="preview_fake">
					<img id="preview" class="preview" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)'/>
					<img id="preview_size_fake" class="preview_size_fake" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)' />
				</div>
				<div class="preview_input">
					<strong>图片位置：</strong><br />
					<input id="file" name="upload" type="file" class="vam mr5" size="50" onchange="onUploadImgChange('preview','preview_fake','preview_size_fake',this);" />
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
				<button type="submit" class="abutton3">
					上传截图
				</button>
			</div>
			<input type="hidden" name="orderID" value="${order.id }">
		</form>
		</div>
	</body>
</html>

