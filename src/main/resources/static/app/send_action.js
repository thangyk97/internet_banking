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

function activeAndDeactive(type) {
	$("#banker-active-annount").hide();
	
    stompClient.send("/app/activeAndDeactive", {}, 
			JSON.stringify({
			   "username" : $("#active-username").val(),
	           "type": type
			}));
}

function addUser() {
    stompClient.send("/app/addUser", {}, 
			JSON.stringify({
			   "address" 		: $("#new-address").val(),
	           "birthday"		: $("#new-birthday").val(),
	           "email" 			: $("#new-email").val(),
	           "firstName"		: $("#new-first_name").val(),
	           "gender" 		: $("#new-gender").val(),
	           "lastName"		: $("#new-last_name").val(),
	           "password" 		: $("#new-password").val(),
	           "phone"			: $("#new-phone").val(),
	           "username" 		: $("#new-username").val(),
	           "balance"		: $("#new-balance").val(),
	           "ac_no"			: $("#new-account_ac_no").val()
			}));

}

function addUser2Account() {
    stompClient.send("/app/addUser2Account", {}, 
			JSON.stringify({
			   "address" 		: $("#add-address").val(),
	           "birthday"		: $("#add-birthday").val(),
	           "email" 			: $("#add-email").val(),
	           "firstName"		: $("#add-first_name").val(),
	           "gender" 		: $("#add-gender").val(),
	           "lastName"		: $("#add-last_name").val(),
	           "password" 		: $("#add-password").val(),
	           "phone"			: $("#add-phone").val(),
	           "username" 		: $("#add-username").val(),
	           "balance"		: $("#add-balance").val(),
	           "ac_no"			: $("#add-ac_no").val()
			}));
}

//function addAccount() {
//    stompClient.send("/app/addAccount", {}, 
//    JSON.stringify({
//        "balance": 1111111,
//        "openDate" : "2018-11-27",
//		}));
//}

function getInforAccount() {
	stompClient.send("/app/getInforAccount", {}, "username");
}

function addDeposit() {
	stompClient.send("/app/deposit", {},
			JSON.stringify({
				"username": $("#deposit-username").val(),
				"ac_no": $("#deposit-ac_no").val(),
				"amount": $("#deposit-amount").val()
			}));
}


function addWithdraw() {
	stompClient.send("/app/withdraw", {},
			JSON.stringify({
				"password": $("#withdraw-password").val(),
				"amount": $("#withdraw-amount").val()
			}));
}








