
function delOptions(obj, flag) {
	if (flag == 0) {
		obj.innerHTML = "<div style='display:block'><a href='javascript:void(0)'>\u8bf7\u9009\u62e9\u6e38\u620f\u5206\u533a</a></div>";
	} else {
		obj.innerHTML = "<div style='display:block'><a href='javascript:void(0)'>\u6ca1\u6709\u76f8\u5173\u4fe1\u606f</a></div>";
	}
}
function doSelectGame(gameName, gameID) {
	delOptions(document.getElementById("areaList"), 1);
	delOptions(document.getElementById("serverList"), 0);
	document.getElementById("yxfl").innerHTML = "";
	evaluate("searchGameVal", "searchGameName", gameID, gameName);
	evaluate("searchAreaVal", "searchAreaName", "", "\u6e38\u620f\u5206\u533a");
	evaluate("searchServerVal", "searchServerName", "", "\u6e38\u620f\u670d\u52a1\u5668");
	evaluate("searchBizKindVal", "searchBizKindName", "", "\u5168\u90e8\u5206\u7c7b");
	selectclose("search_select_box");
	ajaxList.areaList(gameID, callBackGame);
	ajaxList.bizKindList(gameID, callBackBizKind);
}
function callBackGame(data) {
	if (data.length > 0) {
		var selectobj = document.getElementById("areaList");
		var areaListVal = "";
		for (var i = 0; i < data.length; i++) {
			areaListVal += "<div style=\"display:block;\">";
			areaListVal += "<a href=javascript:doSelectArea(\"" + data[i].areaName + "\",\"" + data[i].id + "\")>";
			areaListVal += data[i].areaName;
			areaListVal += "</a></div>";
		}
		document.getElementById("areaList").innerHTML = areaListVal;
	}
}
function callBackBizKind(data) {
	if (data.length > 0) {
		var search_bizKind_String = "<li onclick=\"doSelectBizKind('\u5168\u90e8\u5206\u7c7b','');\">\u5168\u90e8\u5206\u7c7b</li>";
		for (var i = 0; i < data.length; i++) {
			var id = data[i].id;
			var kindName = data[i].kindName;
			search_bizKind_String += "<li onclick=\"doSelectBizKind('" + kindName + "','" + id + "');\">" + kindName + "</li>";
		}
		$("yxfl").innerHTML = search_bizKind_String;
	} else {
		var searchBizKindArray = bizKindListVal.split(";;");
		var search_bizKind_sel = $("yxfl");
		var search_bizKind_String = "<li onclick=\"doSelectBizKind('\u5168\u90e8\u5206\u7c7b','');\">\u5168\u90e8\u5206\u7c7b</li>";
		for (var i = 0; i < searchBizKindArray.length - 1; i++) {
			var bizKind_obj = searchBizKindArray[i].split("::");
			var id = bizKind_obj[0];
			var kindName = bizKind_obj[1];
			search_bizKind_String += "<li onclick=\"doSelectBizKind('" + kindName + "','" + id + "');\">" + kindName + "</li>";
		}
		$("yxfl").innerHTML = search_bizKind_String;
	}
}
function doSelectBizKind(kindName, bizKindID) {
	evaluate("searchBizKindVal", "searchBizKindName", bizKindID, kindName);
}
function doSelectArea(areaName, areaID) {
	delOptions(document.getElementById("serverList"), 1);
	evaluate("searchAreaVal", "searchAreaName", areaID, areaName);
	evaluate("searchServerVal", "searchServerName", "", "\u6e38\u620f\u670d\u52a1\u5668");
	selectclose("search_select_qu");
	ajaxList.serverList(areaID, callBackArea);
}
function callBackArea(data) {
	if (data.length > 0) {
		var selectobj = document.getElementById("serverList");
		var serverListVal = "";
		for (var i = 0; i < data.length; i++) {
			serverListVal += "<div style=\"display:block;\">";
			serverListVal += "<a href=javascript:doSelectServer(\"" + data[i].serverName + "\",\"" + data[i].id + "\")>";
			serverListVal += data[i].serverName;
			serverListVal += "</a></div>";
		}
		document.getElementById("serverList").innerHTML = serverListVal;
	}
}
function doSelectServer(serverName, serverID) {
	evaluate("searchServerVal", "searchServerName", serverID, serverName);
	selectclose("search_select_fu");
}
					
					
					
					//赋值
function evaluate(searchValDiv, searchNameDiv, val, name) {
	document.getElementById(searchNameDiv).innerHTML = name;
	document.getElementById(searchValDiv).value = val;
}
					
					//关闭弹出层
function selectclose(selectDiv) {
	document.getElementById(selectDiv).style.display = "none";
	document.getElementById("DivShim").style.display = "none";
}

