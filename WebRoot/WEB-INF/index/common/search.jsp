<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>

<!--search -->
<script src="${ctx}/js/GameInfoSelectBar.js" type="text/javascript"></script>
<script src="${ctx}/js/index/dynamic_select.js" type="text/javascript"></script>
<script type='text/javascript' src='${ctx}/dwr/interface/ajaxList.js'></script>

<form name="bizInfo_serach_form" action="${ctx}/user/trade/bizInfo/browse.shtml" method="get" onsubmit="javascript:$('searchContent').value=encodeURIComponent($('searchText').value);">
	<div class="select" onclick="show_nav_game('search_select_box');">
		<span id="searchGameName">
			全部游戏
		</span>
	</div>
	<div class="select" onclick="show_nav_game('search_select_qu');">
		<span id="searchAreaName">
			游戏分区
		</span>
	</div>
	<div class="select" onclick="show_nav_game('search_select_fu');">
		<span id="searchServerName">
			游戏服务器
		</span>
	</div>
	<div class="select" onclick="showlist('yxfl');">
	    <span id="searchBizKindName">
	   		全部分类
	    </span>
	    <ul id="yxfl" style="display:none">
	    </ul>
	    <script type="text/javascript">
	    	var searchBizKindArray=bizKindListVal.split(";;");
			var search_bizKind_sel=$("yxfl");
			var search_bizKind_String="<li onclick=\"doSelectBizKind('全部分类','');\">全部分类</li>";
			for(var i=0;i<searchBizKindArray.length-1;i++){
				var bizKind_obj=searchBizKindArray[i].split("::");
				var id=bizKind_obj[0];
				var kindName=bizKind_obj[1];
				search_bizKind_String+="<li onclick=\"doSelectBizKind('"+kindName+"','"+id+"');\">"+kindName+"</li>";
			}
			$("yxfl").innerHTML=search_bizKind_String;
	    </script>
	    <script type="text/javascript">
	    	var tmp_searchText;
	    	function searchText_focus(obj){
	    		tmp_searchText=obj.value;
	    		obj.value="";
	    		obj.select();
	    	}
	    	function searchText_blur(obj){
	    		if(obj.value==""){
	    			tmp_searchText="请输入关键词";
	    			obj.value=tmp_searchText;
	    		}
	    	}
	    </script>
    </div>
	<div class="input">
		<input id="searchText" type="text" value="请输入关键词" onfocus="searchText_focus(this)" onblur="searchText_blur(this)" />
		<input type="hidden" id="searchGameVal" name="gameID"  />
		<input type="hidden" id="searchBizKindVal" name="bizKindID"  />
		<input type="hidden" id="searchAreaVal" name="areaID"  />
		<input type="hidden" id="searchServerVal" name="serverID"  />
		<input type="hidden" id="searchContent" name="searchContent"  />
	</div>
	<button type="submit"></button>
</form>


<!--search -->
	<jsp:include page="/WEB-INF/index/common/search_gameList.jsp"></jsp:include>
<!--游戏 end -->

<div class="search_select" id="search_select_qu" style="display:none">
	<div class="search_select_in">
		<h1><a href="javascript:close_nav('search_select_qu')" target="_self">[关闭]</a><strong>请选择游戏分区</strong></h1>
		
		<div id="areaList" class="search_listbox">
			<div style="display:block"><a href="javascript:void(0)">请选择游戏</a></div>
		</div>
		
		<div class="blank5"></div>
	</div>
</div>
<!--区 end -->

<div class="search_select" id="search_select_fu" style="display:none">
	<div class="search_select_in">
		<h1><a href="javascript:close_nav('search_select_fu')" target="_self">[关闭]</a><strong>请选择服务器</strong></h1>
		
		<div id="serverList" class="search_listbox">
			<div style="display:block"><a href="javascript:void(0)">请选择游戏分区</a></div>
		</div>
		
		<div class="blank5"></div>
	</div>
</div>
<!--服 end -->

<iframe id="DivShim" src="javascript:false" ></iframe>
<!-- search end --> 
