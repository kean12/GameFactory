<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="yhzx_rightside">
			<div class="yhzx_title">
				我要投诉
			</div>
			<div class="usergamelead">
				<form name="complaint_form" action="${ctx}/user/trade/order/complaint_submit.shtml" method="post">
					<ul>
						<li>
							<label>
								<span class="red">*</span>投诉对象：
							</label>
							<span class="blue"><s:if test="type==1">买家</s:if><s:elseif test="type==2">卖家</s:elseif>：${defendant.username}</span>
						</li>
						<li class="usertxt1">
							<div class="fr" style="line-height: 180%; width: 140px;">
								请准确描述您的投诉理由，以便客服及时作出判断。（500个汉字以内）
								<br />
								投诉内容最好包括：
								<br />
								1.旺旺聊天记录
								<br />
								2.QQ聊天记录
								<br />
								3.您希望的处理结果
							</div>

							<label>
								<span class="red">*</span>投诉内容：
							</label>
							<input type="hidden" name="orderID" value="${orderID }" />
							
<script type="text/javascript" charset="utf-8" src="${ctx}/kindeditor/kindeditor.js"></script>
<style type="text/css" rel="stylesheet">
    form {
        margin: 0;
    }
    .editor {
        margin-top: 5px;
        margin-bottom: 5px;
    }
</style>
<script type="text/javascript">
    KE.show({
        id : 'content',
        resizeMode : 1,
        cssPath : '${ctx}/kindeditor/index.css'
    });
</script>
							<textarea id="content" name="content" cols="" rows="" class="ts" style="height:300px;visibility:hidden;"></textarea>
						</li>
						<li class="tc">
							<button type="submit" onclick="return chk_form();" class="loginbutton" style="margin-left: 65px">
								确定提交
							</button>
						</li>
					</ul>
				</form>
<script type="text/javascript">
	function chk_form(){
		var content=KE.util.getPureData('content');
		if(content==""){
			alertDialog('请填写投诉内容！');
			return false;
		}
	}
</script>
			</div>
		</div>
		<div class="clear"></div>
	</body>
</html>
