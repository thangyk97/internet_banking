function hideAllMain() {
	$("#main_login").hide();
	$("#main_cus").hide();
	$("#main_banker").hide();
}

function showViewForSpecificUser(user) {
	hideAllMain();
	if (user.role == 0) {
		$("#main_cus").show();
	} else if (user.role == 2) {
		$("#main_banker").show();
	} 
}

