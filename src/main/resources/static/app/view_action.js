function hideAllMain() {
	$("#main_login").hide();
	$("#main_cus").hide();
	$("#main_banker").hide();
	$("#header").hide();
	$("#cus-withdraw").hide();
	$("#cus-information").hide();
	$("#banker-create-account").hide();
	$("#banker-add-user-2-account").hide();
}

/**
 * if login fail, show message require user relogin
 */
function reLogin() {
	$("#anounce").show();
}

function showInforAccount(account) {
	console.log(account);
	hideAllMain();
	$("#header").show();
	$("#cus-information").show();
	$("#cus-ac_no").empty().append(account.ac_no);
	$("#cus-balance").empty().append(account.balance);
	$("#cus-open_date").empty().append(account.openDate);
}

function showWithdrawResponse(content) {
	$("#cus-withdraw-password-fail").hide();
	$("#cus-withdraw-not-enough-money").hide();
	if(content == "pass_wrong") {
		$("#cus-withdraw-password-fail").show();
	} else if (content == "not_enough_money") {
		$("#cus-withdraw-not-enough-money").show();
	} else {
		alert("oke");
	}
}

function showWithDraw() {
	hideAllMain();
	$("#header").show();
	$("#cus-withdraw").show();
	
}

function showViewForSpecificUser(user) {
	window.user = user;
	hideAllMain();
	$("#header").show();
	if (user.role == 0 || user.role == 1) {
		$("#main_cus").show();
		$("#header-deposit").hide();
		$("#header-withdraw").show();
		$("#header-add-cus-2-account").hide();
		$("#header-create-account").hide();
		$("#header-history").show();
		$("#header-info").show();
		
		showInfoCus(user);
	} else if (user.role == 2) {
		$("#header-withdraw").hide();
		$("#header-deposit").show();
		$("#main_banker").show();
		$("#header-add-cus-2-account").show();
		$("#header-create-account").show();
		$("#header-history").hide();
		$("#header-info").hide();
	} 
}

function showInfoCus(user) {
	$("#cus-home-name").append(
			user.last_name.toUpperCase() + " " + user.first_name.toUpperCase());
	$("#cus-home-no").append(user.ac_no);
	$("#cus-home-open-date").append(user.open_date);
}

function showCreateNewAccount() {
	hideAllMain();
	$("#header").show();
	$("#banker-create-account").show();
}

function showAddUser2Account() {
	hideAllMain();
	$("#header").show();
	$("#banker-add-user-2-account").show();
}
























