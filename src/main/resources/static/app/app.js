$(document).ready(function () {
	hideAllMain();
	$("#main_login").show();
	window.stompClient = null;
	$("#anounce").hide();
	
	/**
	 * route of client app
	 * switch case by title
	 * data in content
	 */
	function route(msgOut) {
		var response = JSON.parse(msgOut.body);
		switch (response.title) {
			case "role":
				showViewForSpecificUser(response.content);
				break;
			case "addUserResponse":
				console.log(response.content);
				break;
			case "addAccountResponse":
				console.log(response.content);
				break;
			case "":
				break;
			case "":
				break;
			case "":
				break;
			case "":
				break;
			case "":
				break;
			default:console.log("deo hieu no tra ve gi !");
		}
	}
	/**
	 * Connect socket to server by username, password, endtry point
	 * success callback: client subcribe 
	 * fail callback: call reLogin() to require user relogin
	 */
	function connect() {
	    var socket = new SockJS('/room');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({
	           "username" : $("#username").val(),
	           "password": $("#password").val()
	    },
	    function (frame) { // success callback
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/user/queue/reply', route);
	        findRole();
	    },
	    function (frame) { // fail callback
	    	reLogin();
	    });
	}

	/**
	 * Run first
	 */
	$(function () {
	    $( "#login" ).click(function() { 
	    	connect();
	    });
	    $("#banker_click").click(function() {
	    	addUser();
	    })
	});
})