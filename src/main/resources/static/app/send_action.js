/**
 * after connect socket, send username and password to find role of user
 * route "role" handle respone
 * if banker then show banker interface else show customer interface
 */
function findRole() {
	$("#anounce").hide();
	
    stompClient.send("/app/findRole", {}, 
			JSON.stringify({
			   "username" : $("#username").val(),
	           "password": $("#password").val()
			}));
}


function addUser() {
    stompClient.send("/app/addUser", {}, 
			JSON.stringify({
			   "address" 		: $("#new-address").val(),
	           "birthday"		: $("#new-birthday").val(),
	           "email" 			: $("#new-email").val(),
	           "first_name"		: $("#new-first_name").val(),
	           "gender" 		: $("#new-gender").val(),
	           "last_name"		: $("#new-last_name").val(),
	           "password" 		: $("#new-password").val(),
	           "phone"			: $("#new-phone").val(),
	           "username" 		: $("#new-username").val(),
	           "balance"		: $("#new-balance").val(),
	           "account_ac_no"	: $("#new-account_ac_no").val()
			}));

}

function addUser2Account() {
	
}

function addAccount() {
    stompClient.send("/app/addAccount", {}, 
//			JSON.stringify({
//			   "address" : $("#address").val(),
//	           "birthday": $("#birthday").val(),
//	           "email" : $("#email").val(),
//	           "first_name": $("#first_name").val(),
//	           "gender" : $("#gender").val(),
//	           "last_name": $("#last_name").val(),
//	           "password" : $("#password").val(),
//	           "phone": $("#phone").val(),
//	           "role" : $("#role").val(),
//	           "start_time": $("#start_time").val(),
//	           "username" : $("#username").val(),
//	           "account_ac_no": $("#account_ac_no").val()
//			}));
    JSON.stringify({
        "balance": 1111111,
        "openDate" : "2018-11-27",
		}));
}

function getInforAccount() {
	stompClient.send("/app/getInforAccount", {}, "username");
}

function addDeposit() {
	stompClient.send("/app/deposit", {},
			JSON.stringify({
				"username": "thangnd",
				"amount": 100
			}));
}


function addWithdraw() {
	stompClient.send("/app/withdraw", {},
			JSON.stringify({
				"password": $("#withdraw-password").val(),
				"amount": $("#withdraw-amount").val()
			}));
}








