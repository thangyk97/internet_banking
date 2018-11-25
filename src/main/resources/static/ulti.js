function hideAllMain() {
	$("#main_login").hide();
	$("#main_cus").hide();
	$("#main_banker").hide();
}

function showViewForSpecificUser(content) {
	hideAllMain();
	if (content.role == 0) {
		$("#main_cus").show();
	} else {
		$("#main_banker").show();
	}
}

