
var publish_gameName = "";
var publish_typeName = "";
var publish_kindName = "";
var publish_areaName = "";
var publish_serverName = "";
function fb_select(obj) {
	var fbsp = obj.parentNode;
	var menu_num = fbsp.getElementsByTagName("li");
	for (i = 0; i < menu_num.length; i++) {
		menu_num[i].className = "";
	}
	obj.className = "fb_select_action";
}
function showMessageDiv(index, div) {
	for (var i = 1; i <= 5; i++) {
		if (i <= index) {
			showDiv("select" + i);
		} else {
			closeDiv("select" + i);
		}
	}
	closeDiv("publish_gameMessage");
	closeDiv("publish_typeMessage");
	closeDiv("publish_bizKindMessage");
	closeDiv("publish_areaMessage");
	closeDiv("publish_serverMessage");
	showDiv(div);
}
function closeMessageDiv() {
	closeDiv("publish_gameMessage");
	closeDiv("publish_typeMessage");
	closeDiv("publish_bizKindMessage");
	closeDiv("publish_areaMessage");
	closeDiv("publish_serverMessage");
}
function selectLetter(gameIndex, obj) {
	showMessageDiv(1, "publish_gameMessage");
	fb_select(obj);
	ajaxList.gameList(gameIndex, callBackGameList);
}
function selectGame(gameID, gameName, obj) {
	showMessageDiv(2, "publish_typeMessage");
	$("publish_gameID").value = gameID;
	$("publish_typeID").value = "";
	$("publish_bizKindID").value = "";
	$("publish_areaID").value = "";
	$("publish_serverID").value = "";
	publish_gameName = gameName;
	$("selectName").innerHTML = publish_gameName;
	fb_select(obj);
	ajaxList.bizKindList(gameID, publish_bizKindList);
	ajaxList.areaList(gameID, callBackAreaList);
}
function selectTradeType(typeID, typeName, obj) {
	showMessageDiv(3, "publish_bizKindMessage");
	$("publish_typeID").value = typeID;
	$("publish_bizKindID").value = "";
	$("publish_areaID").value = "";
	$("publish_serverID").value = "";
	publish_typeName = typeName;
	$("selectName").innerHTML = publish_typeName + " \xbb " + publish_gameName;
	fb_select(obj);
}
function selectBizKind(BizKindID, kindName, obj) {
	showMessageDiv(4, "publish_areaMessage");
	$("publish_bizKindID").value = BizKindID;
	$("publish_areaID").value = "";
	$("publish_serverID").value = "";
	publish_kindName = kindName;
	$("selectName").innerHTML = publish_typeName + " \xbb " + publish_gameName + " \xbb " + publish_kindName;
	fb_select(obj);
}
function selectArea(areaID, areaName, obj) {
	showMessageDiv(5, "publish_serverMessage");
	$("publish_areaID").value = areaID;
	$("publish_serverID").value = "";
	publish_areaName = areaName;
	$("selectName").innerHTML = publish_typeName + " \xbb " + publish_gameName + " \xbb " + publish_kindName + " \xbb " + publish_areaName;
	fb_select(obj);
	ajaxList.serverList(areaID, callBackServerList);
}
function selectServer(serverID, serverName, obj) {
	closeMessageDiv();
	$("publish_serverID").value = serverID;
	publish_serverName = serverName;
	$("selectName").innerHTML = publish_typeName + " \xbb " + publish_gameName + " \xbb " + publish_kindName + " \xbb " + publish_areaName + " \xbb " + publish_serverName;
	fb_select(obj);
}
function callBackGameList(data) {
	if (data.length > 0) {
		var selectobj = $("select1");
		var gameListVal = "<ul>";
		for (var i = 0; i < data.length; i++) {
			gameListVal += "<li onclick=\"selectGame(" + data[i].id + ",'" + data[i].gameName + "',this);\"><span>";
			gameListVal += data[i].gameIndex.charAt(0).toUpperCase() + "-" + data[i].gameName;
			gameListVal += "</span></li>";
		}
		gameListVal += "</ul>";
		selectobj.innerHTML = gameListVal;
	} else {
		alertDialog("\u5bf9\u4e0d\u8d77\uff01\u6ca1\u6709\u627e\u5230\u76f8\u5173\u6e38\u620f\u3002");
		window.location.reload();
	}
}
function publish_bizKindList(data) {
	if (data.length > 0) {
		var bizKind_sel = $("select3");
		var publish_bizKindVal = "<ul>";
		for (var i = 0; i < data.length; i++) {
			var id = data[i].id;
			var kindName = data[i].kindName;
			publish_bizKindVal += "<li onclick=\"selectBizKind(" + id + ",'" + kindName + "',this);\"><span>";
			publish_bizKindVal += kindName;
			publish_bizKindVal += "</span></li>";
		}
		publish_bizKindVal += "</ul>";
		bizKind_sel.innerHTML = publish_bizKindVal;
	} else {
		var arr = bizKindListVal.split(";;");
		var bizKind_sel = $("select3");
		var publish_bizKindVal = "<ul>";
		for (var i = 0; i < arr.length - 1; i++) {
			var bizKind_obj = arr[i].split("::");
			publish_bizKindVal += "<li onclick=\"selectBizKind(" + bizKind_obj[0] + ",'" + bizKind_obj[1] + "',this);\"><span>";
			publish_bizKindVal += bizKind_obj[1];
			publish_bizKindVal += "</span></li>";
		}
		publish_bizKindVal += "</ul>";
		bizKind_sel.innerHTML = publish_bizKindVal;
	}
}
function callBackAreaList(data) {
	if (data.length > 0) {
		var selectobj = $("select4");
		var gameListVal = "<ul>";
		for (var i = 0; i < data.length; i++) {
			gameListVal += "<li onclick=\"selectArea(" + data[i].id + ",'" + data[i].areaName + "',this);\"><span>";
			gameListVal += data[i].areaName;
			gameListVal += "</span></li>";
		}
		gameListVal += "</ul>";
		selectobj.innerHTML = gameListVal;
	}
}
function callBackServerList(data) {
	if (data.length > 0) {
		var selectobj = $("select5");
		var gameListVal = "<ul>";
		for (var i = 0; i < data.length; i++) {
			gameListVal += "<li onclick=\"selectServer(" + data[i].id + ",'" + data[i].serverName + "',this);\"><span>";
			gameListVal += data[i].serverName;
			gameListVal += "</span></li>";
		}
		gameListVal += "</ul>";
		selectobj.innerHTML = gameListVal;
	}
}

