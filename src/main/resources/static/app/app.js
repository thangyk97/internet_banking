$(document).ready(function () {
	hideAllMain();
	$("#main_login").show();
	window.stompClient = null;
	window.user = null;
	
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
			case "deposit":
				break;
			case "withdraw":
				showWithdrawResponse(response.content);
				break;
			case "inforAccount":
				showInforAccount(response.content);
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
	    	addDeposit();
	    });
	    $("#customer_click").click(function() {
	    	addWithdraw();
	    });
		$('#cus-logout').click(function() {
		    location.reload();
		});
		$('#header-withdraw').click(function() {
			showWithDraw();
		});
		$('#header-info').click(function() {
			getInforAccount();
		});
		$('#header-home').click(function() {
			hideAllMain();
			$('#header').show();
			$('#main_cus').show();
		});
		$('#btn-withdraw').click(function() {
			addWithdraw();
		});
		$('#header-create-account').click(function() {
			showCreateNewAccount();
		});
		$('#btn-create-account').click(function() {
			addUser();
		});
		$('#header-add-cus-2-account').click(function() {
			showAddUser2Account();
		});
		$('#"btn-add-user-2-account"').click(function() {
			addUser2Account();
		})
	});
})
