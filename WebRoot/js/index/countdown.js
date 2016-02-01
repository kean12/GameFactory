//time:原始时间 如:2010-05-06 15:26:49
//restricted_time:限制时间-分钟
//obj:文本框ID
function setExpireDate(time, thisTime, restricted_time, obj) {
	var datearr = time.split(" ");
	var arr = datearr[0].split("-");
	vYear = arr[0];
	vMonth = arr[1];
	vDate = arr[2];
	var timearr = datearr[1].split(":");
	vHour = timearr[0];
	vMinute = timearr[1];
	vSecond = timearr[2];
	//设置原始时间日期 
	var expireDate = new Date(vYear, vMonth - 1, vDate, vHour, vMinute, vSecond);
	var remainTime = (restricted_time * 60 * 1000) - (thisTime - expireDate);
	getRTime(remainTime, obj);
}
//倒计时 
var timeout;
function getRTime(remainTime, obj) {
	var nMS = remainTime;
	if (remainTime > 0) {
		$("describe").innerHTML = "\u5269\u4f59\u65f6\u95f4";
	} else {
		$("describe").innerHTML = "\u8d85\u65f6";
		nMS = Math.abs(nMS);
	}
	var nH = Math.floor(nMS / (1000 * 60 * 60));
	var nM = Math.floor(nMS / (1000 * 60)) % 60;
	var nS = Math.floor(nMS / 1000) % 60;
	if (nH < 10) {
		nH = "0" + nH;
	}
	if (nM < 10) {
		nM = "0" + nM;
	}
	if (nS < 10) {
		nS = "0" + nS;
	}
	document.getElementById(obj).innerHTML = nH + ":" + nM + ":" + nS;
	if (nS == 0) {
		ajaxOrder.verify(timeAssignId, function (data) {
			switch (data) {
			  case 0:
				alertDialog("\u4f60\u5c1a\u672a\u767b\u5f55,\u6216\u767b\u5f55\u5df2\u8d85\u65f6,\u8bf7\u91cd\u65b0\u767b\u5f55!");
				clearTimeout(timeout);
				return false;
			  case 1:
				alertDialog("\u975e\u6cd5\u64cd\u4f5c,\u6b64\u8ba2\u5355\u4e0d\u5c5e\u4e8e\u4f60!");
				clearTimeout(timeout);
				return false;
			  case 6:
				window.location.reload();
				return false;
			  case 7:
				alertDialog("\u6b64\u8ba2\u5355\u5df2\u7ed3\u675f!");
				clearTimeout(timeout);
				return false;
			  case 9:
				showDiv("overtimeMess");
				break;
			  default:
				break;
			}
		});
	}
	remainTime = remainTime - 1000;
	timeout = setTimeout("getRTime(" + remainTime + ",'" + obj + "')", 100);
}

