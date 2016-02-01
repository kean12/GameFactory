<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="contaner tc">
			<div>
				<img src="${ctx}/images/fb02.gif" width="951" height="34" />
			</div>
		<%--	
			<div class="fb_warning bs_ccc mt8">
				提交相关交易截图，作为交易凭证。提交相关交易截图，作为交易凭证。提交相关交易截图，作为交易凭证。提交相关交易截图，作为交易凭证。提交相关交易截图，作为交易凭证。提交相关交易截图，作为交易凭证。提交相
			</div>
		 --%>	
			<div class="bs_ccc tl mt10">
				<div class="fbxx_title" style="border-top: 0; padding-left: 20px">
					<span class="ico_4">&nbsp;</span> 商品信息
				</div>
			<form action="${ctx}/user/trade/bizInfo/publish_data_1_2_save.shtml" method="post" enctype="multipart/form-data">
				<div class="f14 mainreg" style="width: 910px; padding: 19px;">
					<ul class="form_fill">
						<li>
							<div class="leftbox">
								<span class="red">*</span> 商品属性：
							</div>
							<div class="rightbox">
								请务必正确填写以下信息，否则无法通过信息审核
								<br />
								<table cellpadding="0" cellspacing="0" width="640" style="background: #f9f9f9">
									<tr>
										<th width=30%>
											<span class="red">*</span> 职业：
										</th>
										<td>
											<s:select list="professionList" id="professionName" name="attributeInfo.professionName" listValue="professionName" listKey="professionName" theme="simple" headerKey="" headerValue="请选择" onblur="chkTitle1();" />
											<s:if test="attributeInfo==null || attributeInfo.error.professionName">
												<span id="professionNameMess" style="display:none;" class="ts_sell_wrong">请正确描述该属性！</span>
											</s:if>
											<s:else>
												<span id="professionNameMess" class="ts_sell_wrong">请正确描述该属性！</span>
											</s:else>
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span> 性别：
										</th>
										<td>
											<s:select list="#{'男':'男','女':'女'}" id="sex" name="attributeInfo.sex" listValue="value" listKey="key" theme="simple" onblur="chkTitle1();" />
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span> 等级：
										</th>
										<td>
											<input type="text" id="grade" name="attributeInfo.grade" value="${attributeInfo.grade}" size="10" onblur="chkTitle1();" />
											级 &nbsp;
											<s:if test="attributeInfo==null || attributeInfo.error.grade">
												<span id="gradeMess" style="display: none;" class="ts_sell_wrong">请输入正确的角色等级！</span>
											</s:if>
											<s:else>
												<span id="gradeMess" class="ts_sell_wrong">请输入正确的角色等级！</span>
											</s:else>
										</td>
									</tr>
								</table>
							</div>
						</li>
						<li>
							<div class="leftbox">
								<span class="red">*</span> 商品标题：
							</div>
							<div class="rightbox">
								&nbsp;
								<input type="text" id="title1" name="attributeInfo.title1" value="${attributeInfo.title1}" style="width: 400px" readonly="readonly"/>
								<br />
								&nbsp;
								<input type="text" id="title2" name="attributeInfo.title2" value="${attributeInfo.title2}" style="width: 400px" onblur="chkTitle2();"/>
								<s:if test="attributeInfo==null || attributeInfo.error.title2">
									<span class="orange" id="title2Mess" style="display:none;">最长60个字符30个汉字！</span>
								</s:if>
								<s:else>
									<span class="orange" id="title2Mess">最长60个字符30个汉字！</span>
								</s:else>
							</div>
						</li>
						<li>
							<div class="leftbox">
								<span class="red">*</span> 商品单价：
							</div>
							<div class="rightbox">
								&nbsp;
								<input type="text" id="price" name="attributeInfo.price" value="${attributeInfo.price}" size="12" onblur="chkPrice();" />
								元
								<span class="c666 f12">单价应&ge;</span>
								<span class="red">15.00</span>
								<span class="c666 f12">元</span>
								<s:if test="attributeInfo==null || attributeInfo.error.price">
									<span class="orange" id="priceMess" style="display:none;">请输入一个正确的价格！</span>
								</s:if>
								<s:else>
									<span class="orange" id="priceMess">请输入一个正确的价格！</span>
								</s:else>
							</div>
						</li>
						<li>
							<div class="leftbox">
								<span class="red">*</span> 您的QQ：
							</div>
							<div class="rightbox">
								&nbsp;
								<s:if test='attributeInfo!=null || attributeInfo.qq!=""'>
									<input type="text" id="qq" name="attributeInfo.qq" value="${attributeInfo.qq}" style="width: 400px" onblur="chkQq();" />
								</s:if>
								<s:else>
									<input type="text" id="qq" name="attributeInfo.qq" value="${userSession.qq}" style="width: 400px" onblur="chkQq();" />
								</s:else>
								<s:if test="attributeInfo==null || attributeInfo.error.qq">
									<span class="orange" id="qqMess" style="display:none;">请输入一个正确的QQ号！</span>
								</s:if>
								<s:else>
									<span class="orange" id="qqMess">请输入一个正确的QQ号！</span>
								</s:else>
							</div>
						</li>
						<li>
							<div class="leftbox">
								<span class="red">*</span> 您的电话：
							</div>
							<div class="rightbox">
								&nbsp;
								<s:if test='attributeInfo!=null || attributeInfo.phoneNum!=""'>
									<input type="text" id="phoneNum" name="attributeInfo.phoneNum" value="${attributeInfo.phoneNum}" style="width: 400px" onblur="chkphoneNum();" />
								</s:if>
								<s:else>
									<input type="text" id="phoneNum" name="attributeInfo.phoneNum" value="${userSession.phoneNum}" style="width: 400px"  onblur="chkphoneNum();" />
								</s:else>
								<s:if test="attributeInfo==null || attributeInfo.error.phoneNum">
									<span class="orange" id="phoneNumMess" style="display:none;">请输入一个联系电话！</span>
								</s:if>
								<s:else>
									<span class="orange" id="phoneNumMess">请输入一个联系电话！</span>
								</s:else>
							</div>
						</li>
						<li>
							<div class="leftbox">
								商品描述：
							</div>
							<div class="rightbox">
								请您将不出售的商品转移出帐号，如未转移，则视为赠品一起赠送给买家。为了您的交易安全，请不要在此留下任何联系方式。
								<br />
								<br />
								
								<dl class="zhmm_spxx">
									<s:iterator value="detailsList">
										<s:if test="isUser==1 && parent.isUser==1">
											<dt>${attributeName}:</dt>
											<s:iterator value="child" id="chd">
												<s:if test="isUser==1">
													<dd><em class="mintxt">${attributeName}:</em><input type="text" id="${formName}" name="${formName}" value="<s:property value="attributeInfo.map.get('%{#chd.attributeName})'" />" /></dd>
												</s:if>
											</s:iterator>
										</s:if>
									</s:iterator>
								</dl>
							</div>
						</li>
						<li>
							<div class="leftbox">
								商品图片：
							</div>
							<div class="rightbox">
							
								<s:if test="attributeInfo==null">
									<s:radio list="#{'1':'我要上传','0':'不需要'}" id="type" name="attributeInfo.type" listKey="key" listValue="value" value="1" onclick="changeType(this.value)"></s:radio>
								</s:if>
								<s:else>
									<s:radio list="#{'1':'我要上传','0':'不需要'}" id="type" name="attributeInfo.type" listKey="key" listValue="value" value="%{attributeInfo.type==0?0:1}" onclick="changeType(this.value)"></s:radio>
								</s:else>
								
								<s:if test="attributeInfo==null || attributeInfo.error.type">
									<span id="typeMess" class="ts_sell_wrong pd8" style="display: none;">请正确上传商品图片</span>
								</s:if>
								<s:else>
									<span id="typeMess" class="ts_sell_wrong pd8">请正确上传商品图片</span>
								</s:else>
								
								<span class="f12 c666">截图时，请不要给角色添加提高能力值的状态！</span>
								<div class="clear"></div>
								
								<div id="picDiv" class="update_frame" <s:if test="attributeInfo.type==0">style="display:none;"</s:if>>
									<ul id="img_content">
										<li class="bbs_ccc f14">
											图片应小于512K jpg格式，建议为500x500像素，不能带有人物角色名
										</li>
										<li id="pic1" class="bbd_ccc">
											<input type="file" id="file1" name="attributeInfo.file" size="50" /><a href="javascript:void(0);" class="blue_u ml20" onclick="deletePic('pic1');">删除</a>
										</li>
									</ul>
									<div><a href="javascript:void(0);" class="blue_u ml20" onclick="addPic();">增加一张</a>
									<span id="picMess" class="ts_sell_wrong pd8" style="display: none;">最多只能上传5张图片！</span></div>
								</div>
							</div>
						</li>
					</ul>
					
					<div class="pd10 tc">
						<input type="hidden" id="content" name="attributeInfo.content" value="${attributeInfo.content}" />
						<input type="hidden" name="gameKindID" value="${gameKindID }" />
						<input type="hidden" name="parentID" value="${parentID }" />
						<input type="hidden" name="gameID" value="${gameID }" />
						<input type="hidden" name="serverID" value="${serverID }" />
						<input type="hidden" name="typeID" value="${typeID}">
						<button type="submit" class="orangebutton_big" onclick="return chkPublicAccountAttForm();">
							确认，提交审核
						</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	<script type="text/javascript">
		function chkContent(){
			var content="";
			<s:iterator value="detailsList">
				<s:if test="isUser==1 && parent.isUser==1">
					<s:iterator value="child" id="chd">
						<s:if test="isUser==1">
							content=content+"${attributeName}"+"::"+$("${formName}").value.Trim()+";;";
						</s:if>
					</s:iterator>
				</s:if>
			</s:iterator>
			$("content").value=content;
			return true;
		}
	</script>
<script type="text/javascript" src="${ctx}/js/index/publish_account.js"></script>
	</body>
</html>
