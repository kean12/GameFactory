
function gameList(id) {
	var arr = gameListVal.split(";;");
	var game_sel = $(id);
	game_sel.options.length = arr.length;
	for (var i = 0; i < arr.length - 1; i++) {
		var game_obj = arr[i].split("::");
		var id = game_obj[0];
		var gameName = game_obj[1];
		var gameIndex = game_obj[2];
		var company = game_obj[3];
		var gameHot = game_obj[4];
		game_sel.options[i + 1].value = id;
		game_sel.options[i + 1].text = gameName;
	}
}

