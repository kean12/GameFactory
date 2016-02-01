
function changeType(type) {
	if (type == 1) {
		closeDiv("typeMess");
		showDiv("picDiv");
	} else {
		if (type == 0) {
			closeDiv("typeMess");
			closeDiv("picDiv");
		}
	}
}
var count = 2;
function addPic() {
	var ul = document.getElementById("img_content");
	if (ul.childNodes.length > 5) {
		showDiv("picMess");
		return false;
	} else {
		var li = document.createElement("li");
		li.className = "bbd_ccc";
		li.id = "pic" + count;
		ul.appendChild(li);
		if (isFirefox = navigator.userAgent.indexOf("Firefox") > 0) {
			var input = document.createElement("input");
			input.name = "attributeInfo.file";
			input.type = "file";
			input.id = "file" + count;
			input.size = "50";
			li.appendChild(input);
		} else {
			var input = document.createElement("<input type='file' id='file" + count + "' name='attributeInfo.file' size='50' />");
			li.appendChild(input);
		}
		var a = document.createElement("<a href=\"javascript:void(0);\" class=\"blue_u ml20\" onclick=\"deletePic('pic" + count + "');\" ></a>");
		a.appendChild(document.createTextNode("\u5220\u9664"));
		li.appendChild(a);
		count = count + 1;
		return true;
	}
}
function deletePic(id) {
	var obj = document.getElementById(id);
	obj.parentNode.removeChild(obj);
}
	
	//验证
function chkTitle1() {
	var flag = true;
	var title = "";
	var professionObj = $("professionName");
	var sexObj = $("sex");
	var grade = $("grade").value.Trim();
	if (professionObj.options[professionObj.selectedIndex].value == "") {
		flag = false;
		showDiv("professionNameMess");
	} else {
		closeDiv("professionNameMess");
		title = title + professionObj.options[professionObj.selectedIndex].value + " ";
	}
	title = title + sexObj.options[sexObj.selectedIndex].value + " ";
	if (!grade.match(/^[0-9]*[1-9][0-9]*$/)) {
		flag = false;
		showDiv("gradeMess");
	} else {
		closeDiv("gradeMess");
		title = title + grade + "\u7ea7";
		$("grade").value = $("grade").value.Trim();
	}
	$("title1").value = title;
	return flag;
}
function chkTitle2() {
	var title = $("title2").value.Trim();
	if (title.getByteLength() > 60 || title == "") {
		showDiv("title2Mess");
		return false;
	} else {
		$("title2").value = title;
		closeDiv("title2Mess");
		return true;
	}
}
function chkPrice() {
	var price = $("price").value.Trim();
	if (price == "" || isNaN(price) || price < 15) {
		showDiv("priceMess");
		return false;
	} else {
		closeDiv("priceMess");
		$("price").value = parseFloat(price).toFixed(2);
		return true;
	}
}
function chkQq() {
	var qq = $("qq").value.Trim();
	if (!qq.match(/^\d{5,}$/)) {
		showDiv("qqMess");
		return false;
	} else {
		qq = $("qq").value = qq;
		closeDiv("qqMess");
		return true;
	}
}
function chkphoneNum() {
	var phoneNum = $("phoneNum").value.Trim();
	if (!phoneNum.match(/^1[3,5]\d{9}$/)) {
		showDiv("phoneNumMess");
		return false;
	} else {
		phoneNum = $("phoneNum").value = phoneNum;
		closeDiv("phoneNumMess");
		return true;
	}
}
function chkFile() {
	var type = document.getElementsByName("attributeInfo.type");
	for (var i = 0; i < type.length; i++) {
		if (type[i].checked) {
			if (type[i].value == 1) {
				var file = document.getElementsByName("attributeInfo.file");
				var flag = false;
				for (var j = 0; j < file.length; j++) {
					if (file[j].value != "") {
						flag = true;
					}
				}
				if (!flag) {
					showDiv("typeMess");
				} else {
					closeDiv("typeMess");
				}
				return flag;
			} else {
				return true;
			}
		}
	}
}
function chkPublicAccountAttForm() {
	chkContent();
	if (chkTitle1() && chkTitle2() && chkPrice() && chkQq() && chkphoneNum() && chkFile()) {
		return true;
	} else {
		return false;
	}
}

