//设置当前日期 
function JSTime(time, obj) {
	var thisTime = new Date();
	var datearr = time.split(" ");
	var arr = datearr[0].split("-");
	vYear = arr[0];
	vMonth = arr[1];
	vDate = arr[2];
	var timearr = datearr[1].split(":");
	vHour = timearr[0];
	vMinute = timearr[1];
	//设置下单时间日期 
	var expireDate = new Date(vYear, vMonth - 1, vDate, vHour, vMinute);
	
	//总计剩余时间(毫秒)
	var remainTime = expireDate - thisTime;
	if (remainTime > 0) {
		var remainDay;
		remainDay = Math.floor(remainTime / 1000 / 3600 / 24);
		if (remainDay > 0) {
			document.getElementById(obj).innerHTML = remainDay + "\u5929";
		} else {
			if (remainDay == 0) {
				remainDay = Math.floor(remainTime / 1000 / 3600);
				if (remainDay > 0) {
					document.getElementById(obj).innerHTML = remainDay + "\u5c0f\u65f6";
				} else {
					remainDay = Math.floor(remainTime / 1000 / 60);
					if (remainDay == 0) {
						remainDay = Math.floor(remainTime / 1000);
						if (remainDay == 0) {
							document.getElementById(obj).innerHTML = "\u5230\u671f";
						} else {
							document.getElementById(obj).innerHTML = remainDay + "\u79d2";
						}
					} else {
						document.getElementById(obj).innerHTML = remainDay + "\u5206\u949f";
					}
				}
			}
		}
	} else {
		document.getElementById(obj).innerHTML = "\u5230\u671f";
	}
}

