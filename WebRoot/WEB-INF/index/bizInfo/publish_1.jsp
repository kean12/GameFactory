<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<script type='text/javascript' src='${ctx}/dwr/interface/ajaxList.js'></script>
		<script type="text/javascript" src="${ctx}/js/index/dynamic_publish.js"></script>
		<!--menu end -->
		<div class="contaner">
			<div>
				<img src="${ctx}/images/fb01.gif" width="951" height="34" />
			</div>
			<style>
				.sale_list_lead div {
					padding: 5px 0 10px 0;
					_padding: 4px 0 2px 0;
					height: 15px;
					line-height: 15px;
					text-align: center
				}
				
				.sale_list_lead div button {
					margin-left: 5px;
					vertical-align: middle
				}
			</style>
			<script type="text/javascript">
				function chk_publish_search(){
					document.publish_search_form.action="${ctx}/user/trade/bizInfo/publish.shtml";
				}
			</script>
			<div class="sale_list_lead mt10" style="border: #ddd 1px solid">
				<form name="publish_search_form" action="" method="post">
					<div style="background: url(${ctx}/images/bg/ico.gif) left -800px repeat-x">
						<label class="ml20 f14">
							快速查找游戏：
						</label>
							<input name="gameName" type="text" />
							<button type="submit" onclick="return chk_publish_search();" class="delbutton">
								搜索
							</button>
					</div>
				</form>
			</div>
		</div>
		<div class="blank10"></div>
		<div class="contaner" id="fbsp">
			<div class="fb_tip">
				<div class="fb_tip_00" id="publish_gameMessage">
					请选择游戏
				</div>
				<div class="fb_tip_01" id="publish_typeMessage" style="display:none;">
					请选择挂卖方式
				</div>
				<div class="fb_tip_02" id="publish_bizKindMessage" style="display:none;">
					请选择挂卖种类
				</div>
				<div class="fb_tip_03" id="publish_areaMessage" style="display:none;">
					请选择游戏分区
				</div>
				<div class="fb_tip_04" id="publish_serverMessage" style="display:none;">
					请选择游戏服武器
				</div>
			</div>
			
			<div class="blank10"></div>
			<div class="fb_select fb_select0" id="select0">
				<ul>
					<s:set name="lettList" value="{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}" />
					<s:iterator value="lettList">
						<li onclick="selectLetter('<s:property />',this);"><s:property /></li>
					</s:iterator>
				</ul>
			</div>
			<!-- 游戏列表 -->
			<div class="ml10 fb_select" id="select1">
				<s:if test="gameList.size()>0">
					<ul>
						<s:iterator value="gameList">
							<li onclick="selectGame(${id},'${gameName}',this);"><span><s:property value="gameIndex.toUpperCase().charAt(0)" />-${gameName}</span></li>
						</s:iterator>
					</ul>
				</s:if>
				<s:else>
					<script type="text/javascript">
						function publish_gameList(id){
							var arr=gameListVal.split(";;");
							var game_sel=$(id);
							var publish_gameVal="<ul>";
							for(var i=0;i<arr.length-1;i++){
								var game_obj=arr[i].split("::");
								var id=game_obj[0];
								var gameName=game_obj[1];
								var gameIndex=game_obj[2];
								
								publish_gameVal += "<li onclick=\"selectGame("+id+",'"+gameName+"',this);\"><span>";
								publish_gameVal += game_obj[2].charAt(0).toUpperCase()+"-"+gameName;
								publish_gameVal += "</span></li>";
							}
							publish_gameVal +="</ul>";
							game_sel.innerHTML=publish_gameVal;
						}
						publish_gameList("select1");
					</script>
				</s:else>
			</div>
			<!-- 挂卖方式 -->
			<div class="ml10 fb_select" id="select2" style="display:none;">
				<ul>
					<s:iterator value="tradeType" id="stack">
						<s:if test="#stack==1">
							<li onclick="selectTradeType(1,'寄售交易',this);"><span>寄售交易</span></li>
						</s:if>
						<s:elseif test="#stack==2">
							<li onclick="selectTradeType(2,'自主交易',this);"><span>自主交易</span></li>
						</s:elseif>
						<s:else>
							<script type="text/javascript">
								alertDialog("未设置交易类型,请联系客服!");
							</script>
						</s:else>
					</s:iterator>
				</ul>
			</div>
			
			<div class="ml10 fb_select" id="select3" style="display:none;">
				<!-- 分类列表 -->
			</div>
			<div class="ml10 fb_select" id="select4" style="display:none;">
				<!-- 分区列表 -->
			</div>
			<div class="ml10 fb_select" id="select5" style="display:none;">
				<!-- 服务器列表 -->
			</div>
			<div class="blank10"></div>
			<div class="mainpop">
				您当前选择的是：
				<span class="orange" id="selectName"></span>
			</div>
		</div>
		
	<form name="publish_next_form" action="${ctx}/user/trade/bizInfo/publish_data.shtml" method="post">
		<input type="hidden" name="gameID" id="publish_gameID" />
		<input type="hidden" name="typeID" id="publish_typeID" />
		<input type="hidden" name="bizKindID" id="publish_bizKindID" />
		<input type="hidden" name="areaID" id="publish_areaID" />
		<input type="hidden" name="serverID" id="publish_serverID" />
		
		<div class="contaner pd10" style="text-align: center">
			<button type="submit" class="orangebutton_big">
				继续下一步>>
			</button>
		</div>
	</form>
	</body>
</html>
