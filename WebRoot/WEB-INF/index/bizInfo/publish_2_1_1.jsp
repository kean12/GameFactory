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
					<span class="ico_4">&nbsp;</span> 帐号注册信息
				</div>
				<form name="publish_search_form" action="${ctx}/user/trade/bizInfo/publish.shtml" method="post">
					<s:if test="game!=null">
						<input type="hidden" name="gameName" value="${game.gameName}" />
					</s:if>
					<s:else>
						<input type="hidden" name="gameName" value="${server.area.game.gameName}" />
					</s:else>
				</form>
			<form action="${ctx}/user/trade/bizInfo/publish_data_1_2.shtml" method="post" enctype="multipart/form-data">
				<div class="f14 mainreg" style="width: 910px; padding: 19px;">
					<ul class="form_fill">
						<li>
							<div class="leftbox">
								<span class="red">*</span> 已选商品分类：
							</div>
							<div class="rightbox">
								<s:if test="typeID==1">
									寄售交易 &gt;&gt; 
								</s:if>
								<s:elseif test="type==2">
									自主交易 &gt;&gt; 
								</s:elseif>
								<s:if test="game!=null">
									<s:property value="game.gameName" />
								</s:if>
								<s:else>
									<s:property value="server.area.game.gameName" /> &gt;&gt; <s:property value="server.area.areaName" /> &gt;&gt; <s:property value="server.serverName" />
								</s:else>
								&gt;&gt; <s:property value="gameKind.bizKind.kindName" />
								<a href="#" class="green_u" onclick="javascript:document.publish_search_form.submit();">[更改分类]</a>
								<span class="orange">${session.publishErrorInfo.bizKind}</span>
							</div>
						</li>
						<li>
							<div class="leftbox">
								<span class="red">*</span> 帐号信息：
							</div>
							<div class="rightbox">
								<span class="red">您填写的以下资料经过系统加密，绝不外泄，请放心填写。</span>
								<br />
								为了保证帐号顺利上架出售，请如实填写以下资料。买家购买后，以下资料（包含邮箱）将全部移交给买家。
								<br />
								<br />
								<table cellpadding="0" cellspacing="0" width="640"
									style="background: #f9f9f9">
									<tr>
										<th width=35%>
											游戏帐号：
										</th>
										<td>
											<input type="text" id="account" name="accountInfo.account" value="${accountInfo.account}" style="width: 150px;" />&nbsp;<span class="red">*</span>
											<s:if test="!#session.accountInfo.error.account">
												<span id="accountMess"  class="ts_sell_wrong">游戏帐号不能为空！</span>
											</s:if>
											<s:else>
												<span id="accountMess" style="display: none;" class="ts_sell_wrong">游戏帐号不能为空！</span>
											</s:else>
											
										</td>
									</tr>
									<tr>
										<th>
											再次输入游戏帐号：
										</th>
										<td>
											<input type="text" id="r_account" name="accountInfo.r_account" value="${accountInfo.r_account}" style="width: 150px;" />&nbsp;<span class="red">*</span>
											<s:if test="!#session.accountInfo.error.r_account">
												<span id="r_accountMess" class="ts_sell_wrong">再次输入的游戏帐号要一致！</span>
											</s:if>
											<s:else>
												<span id="r_accountMess" style="display: none" class="ts_sell_wrong">再次输入的游戏帐号要一致！</span>
											</s:else>
											
										</td>
									</tr>
									<tr>
										<th>
											游戏密码：
										</th>
										<td>
											<input type="password" id="password" name="accountInfo.password" style="width: 150px;" />&nbsp;<span class="red">*</span>
											<s:if test="!#session.accountInfo.error.password">
												<span id="passwordMess" class="ts_sell_wrong" >密码不能为空！</span>
											</s:if>
											<s:else>
												<span id="passwordMess" class="ts_sell_wrong" style="display: none">密码不能为空！</span>
											</s:else>
										</td>
									</tr>
									<tr>
										<th>
											再次输入游戏密码：
										</th>
										<td>
											<input type="password" id="r_password" name="accountInfo.r_password" style="width: 150px;" />&nbsp;<span class="red">*</span>
											<s:if test="!#session.accountInfo.error.r_password">
												<span id="r_passwordMess" class="ts_sell_wrong">再次输入的内容需一致！</span>
											</s:if>
											<s:else>
												<span id="r_passwordMess" class="ts_sell_wrong" style="display: none">再次输入的内容需一致！</span>
											</s:else>
										</td>
									</tr>
								<s:if test="details.isUser==1 && details.parent.isUser==1">
									<s:iterator value="details.child">
										<s:if test="isUser==1">
											<tr>
												<th>
													<s:property value="attributeName" />：
												</th>
												<td>
													<input type="text" id="${formName}" value="${accountInfo.map.attributeName }" style="width: 150px;" />&nbsp;<span class="red">*</span>
													<s:if test='!#session.accountInfo.error.get("%{attributeName}")'>
														<span id="${formName}Mess" class="ts_sell_wrong" >${attributeName}不能为空！</span>
													</s:if>
													<s:else>
														<span id="${formName}Mess" class="ts_sell_wrong" style="display: none">${attributeName}不能为空！</span>
													</s:else>
												</td>
											</tr>
										</s:if>
									</s:iterator>
								</s:if>
								
								</table>
							</div>
						</li>
						<li>
							<div class="leftbox">
								<span class="red">*</span> 上传身份证：
							</div>
							<div class="rightbox">
								<s:if test="#session.accountInfo.identityType==1">
									<s:radio list="#{'0':'否','1':'一代','2':'二代'}" id="identityType" name="accountInfo.identityType" listKey="key" listValue="value" value="1" onclick="changeIdentity(this.value);"></s:radio>
									<s:if test="!#session.accountInfo.error.file">
										<span id="fileMess" class="ts_sell_wrong pd8 ml10">请正确上传身份证图片</span>
									</s:if>
									<s:else>
										<span id="fileMess" class="ts_sell_wrong pd8 ml10" style="display:none;">请正确上传身份证图片</span>
									</s:else>
									<div class="clear"></div>
									<div class="update_frame" id="picAll">
										<ul>
											<li class="bbd_ccc f14">
												图片应小于512K jpg格式，建议为500x500像素
											</li>
											<li id="pic1">
												正面
												<input type="file" id="file1" name="file" size="50" />
												<a style="cursor: pointer;" class="blue_u ml20" onclick="clearInputFile('file1');">清空</a>
											</li>
											<li id="pic2" style="display: none;">
												反面
												<input type="file" id="file2" name="file" size="50" />
												<a style="cursor: pointer;" class="blue_u ml20" onclick="clearInputFile('file2');">清空</a>
											</li>
										</ul>
									</div>
								</s:if>
								<s:elseif test="#session.accountInfo.identityType==2">
									<s:radio list="#{'0':'否','1':'一代','2':'二代'}" id="identityType" name="accountInfo.identityType" listKey="key" listValue="value" value="2" onclick="changeIdentity(this.value);"></s:radio>
									<s:if test="!#session.accountInfo.error.file">
										<span id="fileMess" class="ts_sell_wrong pd8 ml10">请正确上传身份证图片</span>
									</s:if>
									<s:else>
										<span id="fileMess" class="ts_sell_wrong pd8 ml10" style="display:none;">请正确上传身份证图片</span>
									</s:else>
									<div class="clear"></div>
									<div class="update_frame" id="picAll">
										<ul>
											<li class="bbd_ccc f14">
												图片应小于512K jpg格式，建议为500x500像素
											</li>
											<li id="pic1">
												正面
												<input type="file" id="file1" name="file" size="50" />
												<a style="cursor: pointer;" class="blue_u ml20" onclick="clearInputFile('file1');">清空</a>
											</li>
											<li id="pic2">
												反面
												<input type="file" id="file2" name="file" size="50" />
												<a style="cursor: pointer;" class="blue_u ml20" onclick="clearInputFile('file2');">清空1</a>
											</li>
										</ul>
									</div>
								</s:elseif>
								<s:else>
									<s:radio list="#{'0':'否','1':'一代','2':'二代'}" id="identityType" name="accountInfo.identityType" listKey="key" listValue="value" value="0" onclick="changeIdentity(this.value);"></s:radio>
									<span id="fileMess" class="ts_sell_wrong pd8 ml10" style="display:none;">请正确上传身份证图片</span>
									
									<div class="clear"></div>
									<div class="update_frame" id="picAll" style="display: none;">
										<ul>
											<li class="bbd_ccc f14">
												图片应小于512K jpg格式，建议为500x500像素
											</li>
											<li id="pic1">
												正面
												<input type="file" id="file1" name="accountInfo.file" size="50" />
												<a style="cursor: pointer;" class="blue_u ml20" onclick="clearInputFile('file1');">清空</a>
											</li>
											<li id="pic2">
												反面
												<input type="file" id="file2" name="accountInfo.file" size="50" />
												<a style="cursor: pointer;" class="blue_u ml20" onclick="clearInputFile('file2');">清空</a>
											</li>
										</ul>
									</div>
								</s:else>
								
								
							<%--	
								<div class="update_frame">
									<ul>
										<li class="bbd_ccc f14">
											已上传图片
										</li>
										<li>
											<a href="javascript:void(0);" class="blue_u">删除</a>
											<span>身份证图片.jpg</span>
										</li>
									</ul>
								</div>
							 --%>
							</div>
						</li>
					</ul>
					<script type="text/javascript">
						function clearInputFile(id){
							var obj=document.getElementById(id);
							obj.outerHTML=obj.outerHTML;
						}

						function chkIdentity(){
							var identity=document.getElementsByName("accountInfo.identityType");
							for(var i=0;i<identity.length;i++){
								if(identity[i].checked){
									if(identity[i].value==1){
										if($("file1").value==""){
											showDiv("fileMess");
											return false;
										}else{
											closeDiv("fileMess");
											return true;
										}
									}else if(identity[i].value==2){
										if($("file1").value=="" || !$("file2").value==""){
											showDiv("fileMess");
											return false;
										}else{
											closeDiv("fileMess");
											return true;
										}
									}else if(identity[i].value==0){
										return true;
									}
								}
							}

						}
					
						function changeIdentity(val){
							if(val==1){
								showDiv("picAll");
								showDiv("pic1");
								closeDiv("pic2");
								closeDiv("fileMess");
							}else if(val==2){
								showDiv("picAll");
								showDiv("pic1");
								showDiv("pic2");
								closeDiv("fileMess");
							}else{
								closeDiv("picAll");
								closeDiv("pic1");
								closeDiv("pic2");
								closeDiv("fileMess");
							}
							clearInputFile("file1");
							clearInputFile("file2");
						}
					
						function chkAccountForm(){
							var account=$("account").value.Trim();
							var r_account=$("r_account").value.Trim();
							var password=$("password").value.Trim();
							var r_password=$("r_password").value.Trim();
							if(!account.match(/^\S+$/)){
								showDiv("accountMess");
								$("account").select();
								return false;
							}else{
								closeDiv("accountMess");
								$("account").value;
							}
							if(!r_account.match(/^\S+$/)){
								showDiv("r_accountMess");
								$("r_account").select();
								return false;
							}else{
								closeDiv("r_accountMess");
								$("r_account").value;
							}
							if(account!=r_account){
								showDiv("r_accountMess");
								$("r_account").select();
								return false;
							}else{
								closeDiv("r_accountMess");
							}
							
							if(!password.match(/^\S+$/)){
								showDiv("passwordMess");
								$("password").select();
								return false;
							}else{
								closeDiv("passwordMess");
							}
							if(!r_password.match(/^\S+$/)){
								showDiv("r_passwordMess");
								$("r_password").select();
								return false;
							}else{
								closeDiv("r_passwordMess");
							}
							if(password!=r_password){
								showDiv("r_passwordMess");
								$("r_password").select();
								return false;
							}else{
								closeDiv("r_passwordMess");
							}

							var content="";
				<s:if test="details.isUser==1 && details.parent.isUser==1">
					<s:iterator value="details.child">
						<s:if test="isUser==1">
							if(!$("${formName}").value.Trim().match(/^\S+$/)){
								showDiv("${formName}Mess");
								$("${formName}").select();
								return false;
							}else{
								closeDiv("r_passwordMess");
								$("${formName}").value=$("${formName}").value.Trim();
								content=content+"${attributeName}"+"::"+$("${formName}").value+";;";
							}
						</s:if>
					</s:iterator>
				</s:if>
							$("content").value=content;
							if(!chkIdentity()){
								return false;
							}

						}
					</script>
					
					<div class="pd10 tc">
							<s:if test="game!=null">
								<input type="hidden" name="gameID" value="${game.id}" />
							</s:if>
							<s:else>
								<input type="hidden" name="serverID" value="${server.id}" />
							</s:else>
						<input type="hidden"  id="content" name="accountInfo.content" />
						<input type="hidden" name="gameKindID" value="${gameKind.id}" />
						<input type="hidden" name="parentID" value="${details.id}" />
						<input type="hidden" name="typeID" value="${typeID}">
						<button type="submit" class="orangebutton_big" onclick="return chkAccountForm();">
							下一步,填写帐号信息
						</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	</body>
</html>
