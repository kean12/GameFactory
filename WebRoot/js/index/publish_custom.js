
function chk_pic(flag, picDiv) {
	if (flag) {
		document.getElementById(picDiv).style.display = "";
	} else {
		document.getElementById(picDiv).style.display = "none";
	}
}
function clearInputFile(id) {
	var obj = document.getElementById(id);
	obj.outerHTML = obj.outerHTML;
}
var count = 2;
function addPic() {
	var ul = document.getElementById("img_content");
	if (ul.childNodes.length > 5 - 1) {
		showDiv("picMess");
		return false;
	} else {
		var li = document.createElement("li");
		li.className = "bbd_ccc";
		li.id = "pic" + count;
		ul.appendChild(li);
		if (isFirefox = navigator.userAgent.indexOf("Firefox") > 0) {
			var input = document.createElement("input");
			input.name = "customInfo.file";
			input.type = "file";
			input.id = "file" + count;
			input.size = "50";
			li.appendChild(input);
		} else {
			var input = document.createElement("<input type='file' id='file" + count + "' name='customInfo.file' size='50' />");
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
function chkTitle() {
	var title = $("title").value.Trim();
	if (title == "" || title.getByteLength() > 60) {
		showDiv("titleMess");
		return false;
	} else {
		$("title").value = title;
		closeDiv("titleMess");
		return true;
	}
}
function chkPrice() {
	var price = $("price").value.Trim();
	if (price == "" || isNaN(price) || price <= 0) {
		showDiv("priceMess");
		return false;
	} else {
		closeDiv("priceMess");
		$("price").value = parseFloat(price).toFixed(2);
		return true;
	}
}
function chkStock() {
	var stock = $("stock").value.Trim();
	if (!stock.match(/^[0-9]*[1-9][0-9]*$/)) {
		flag = false;
		showDiv("stockMess");
	} else {
		closeDiv("stockMess");
		$("stock").value = stock;
		return true;
	}
}
function chkType() {
	if ($("type").checked) {
		var file = document.getElementsByName("customInfo.file");
		var flag = false;
		for (var i = 0; i < file.length; i++) {
			if (file[i].value != "") {
				flag = true;
				break;
			}
		}
		if (flag) {
			closeDiv("typeMess");
		} else {
			showDiv("typeMess");
		}
		return flag;
	} else {
		closeDiv("typeMess");
		return true;
	}
}
function chkSellModel() {
	var sellModel = document.getElementsByName("bizInfo.sellModel");
	for (var i = 0; i < sellModel.length; i++) {
		if (sellModel[i].checked) {
			if (sellModel[i].value == 1) {
				var site = $("site").value.Trim();
				if (site == "") {
					showDiv("siteMess");
					return false;
				} else {
					closeDiv("siteMess");
					$("site").value = site;
					return true;
				}
			} else {
				return true;
			}
			break;
		}
	}
}
function chkAccount() {
	var account = $("account").value.Trim();
	if (account == "") {
		showDiv("accountMess");
		return false;
	} else {
		$("account").value = account;
		closeDiv("accountMess");
		return true;
	}
}
function chkPassword() {
	var password = $("password").value.Trim();
	if (password == "") {
		showDiv("passwordMess");
		return false;
	} else {
		$("password").value = password;
		closeDiv("passwordMess");
		return true;
	}
}
function chkRoleName() {
	var roleName = $("roleName").value.Trim();
	if (roleName == "") {
		showDiv("roleNameMess");
		return false;
	} else {
		$("roleName").value = roleName;
		closeDiv("roleNameMess");
		return true;
	}
}
function chkPlace() {
	var place = $("place").value.Trim();
	if (place == "") {
		showDiv("placeMess");
		return false;
	} else {
		$("place").value = place;
		closeDiv("placeMess");
		return true;
	}
}
function chkPwdType() {
	if ($("pwdType").checked) {
		if ($("pwdfile").value == "") {
			showDiv("pwdTypeMess");
			return false;
		} else {
			closeDiv("pwdTypeMess");
			return true;
		}
	} else {
		closeDiv("pwdTypeMess");
		clearInputFile("pwdfile");
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
function chkPublicCustom() {
	chkContent();
	if (chkTitle() && chkPrice() && chkStock() && chkType() && chkSellModel() && chkAccount() && chkPassword() && chkRoleName() && chkPlace() && chkPwdType() && chkQq() && chkphoneNum()) {
		return true;
	} else {
		return false;
	}
}

