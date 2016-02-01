
function bizKindList(id) {
	var arr = bizKindListVal.split(";;");
	var bizKind_sel = $(id);
	bizKind_sel.options.length = arr.length;
	for (var i = 0; i < arr.length - 1; i++) {
		var bizKind_obj = arr[i].split("::");
		var id = bizKind_obj[0];
		var kindName = bizKind_obj[1];
		bizKind_sel.options[i + 1].value = id;
		bizKind_sel.options[i + 1].text = kindName;
	}
}

