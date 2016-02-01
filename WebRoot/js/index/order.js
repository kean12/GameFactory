
function chk_state(id, state, type, goPage) {
	var action = "";
	$("orderID").value = id;
	$("state").value = state;
	if (goPage != null && goPage != "" || type != null && type != "") {
		action += "?goPage=" + goPage + "&type=" + type;
		$("form1").action = action;
	} else {
		$("form1").action = "";
	}
	$("form1").submit();
}
function chk_pay(id) {
	$("orderID").value = id;
	$("form1").action = AP + "/user/trade/order/trade_payment.shtml";
	$("form1").submit();
}
function detail(id) {
	$("orderID").value = id;
	$("form1").action = AP + "/user/trade/order/detail.shtml";
	$("form1").submit();
}

