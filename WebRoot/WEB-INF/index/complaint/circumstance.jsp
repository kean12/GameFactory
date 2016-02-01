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
				<form>
					<ul>
						<li>
							<label>
								投诉对象：
							</label>
							<span class="blue"><s:if test="complaint.type==1">买家</s:if><s:elseif test="complaint.type==2">卖家</s:elseif>：${complaint.defendant.username }</span>
						</li>
						<li style="height: auto">
							<label>
								投诉内容：
							</label>
							<div class="ts fr" style="width: 640px; height: auto; margin-right: 20px">
								${complaint.content}
							</div>
							<div class="blank10"></div>
						</li>
					<s:if test="complaint.state==0">
						<li class="tc">
							<strong class="red">您的投诉已提交，请耐心等待…… 如有疑问请联系客服</strong>
						</li>
					</s:if>
					<s:elseif test="complaint.state==2">
						<li class="tc">
							<strong class="red">您的投诉正在处理中…… 如有疑问请联系客服</strong>
						</li>
					</s:elseif>
					<s:elseif test="complaint.state==1">
						<li class="tc">
							<strong class="red">您的投诉已处理…… 如有疑问请联系客服</strong>
						</li>
						<li class="tc" style="height: auto">
							<strong class="red">投诉结果：</strong>
							<div class="ts fr" style="width: 640px; height: auto; margin-right: 20px">
								${complaint.answer}
							</div>
							<div class="blank10"></div>
						</li>
					</s:elseif>
						
					</ul>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</body>
</html>
