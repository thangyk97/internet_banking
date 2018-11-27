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
	    "address" : "hanoi",
        "birthday": "1997-10-22",
        "email" : "thangyk97@gmail.com",
        "firstName": "thang",
        "gender" : 1,
        "lastName": "nguyen dinh",
        "password" : "123456",
        "phone": "0334492548",
        "role" : 1,
        "startTime": "2018-11-27",
        "username" : "thangnd",
        "ac_no": 1
		}));
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