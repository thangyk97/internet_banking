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
			case "blah":
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
	    }, function (frame) { // success callback
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/user/queue/reply', route);
	        findRole();
	    }, function (frame) { // fail callback
	    	reLogin();
	    });
	}
	
	/**
	 * after connect socket, send username and password to find role of user
	 * route "role" handle respone
	 * if banker then show banker interface else show customer interface
	 */
	function findRole() {
		$("#anounce").hide();
	    stompClient.send("/app/room", {}, 
				JSON.stringify({
				   "username" : $("#username").val(),
		           "password": $("#password").val()
				}));
	}
	
	/**
	 * if login fail, show message require user relogin
	 */
	function reLogin() {
		$("#anounce").show();
	}

	/**
	 * Run first
	 */
	$(function () {
	    $( "#login" ).click(function() { 
	    	connect();
	    });
	});
})
