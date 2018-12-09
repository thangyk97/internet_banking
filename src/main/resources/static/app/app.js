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
				if(response.content == "oke") {
					alert("Tạo mới tài khoản thành công !");
				} else {
					alert("Tên đăng nhập đã có người sử dụng, vui lòng chọn lại !")
				};
				break;
			case "addAccountResponse":
				console.log(response.content);
				break;
			case "deposit":
				showDepositResponse(response.content);
				break;
			case "withdraw":
				showWithdrawResponse(response.content);
				break;
			case "inforAccount":
				showInforAccount(response.content);
				break;
			case "addUser2Account":
				if(response.content == "oke") {
					alert("Thêm mới người dùng vào tài khoản thành công !");
				} else {
					alert("Tên đăng nhập đã có người sử dụng, vui lòng chọn lại !")
				};
				break;
			case "activeAndDeactive":
				if (response.content == "oke") {
					$("#banker-active-annount").hide();
					alert("Đã cập nhật trạng thái cho tài khoản");
				} else {
					$("#banker-active-annount").show();
				}
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
	    	alert("oc cho nay");
//	    	addDeposit();
	    });
	    $("#customer_click").click(function() {
//	    	addWithdraw();
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
			if(user.role == 2) {
				$('#main_banker').show();
			} else {
				$('#main_cus').show();
			}
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
		$('#btn-add-user-2-account').click(function() {
			addUser2Account();
		});
		$("#header-deposit").click(function() {
			showDepositForm();
		});
		$("#btn-deposit").click(function() {
			addDeposit();
		});
		$("#header-active").click(function() {
			showActiveView();
		});
		$("#btn-active").click(function() {
			activeAndDeactive("active");
		});
		$("#btn-de-active").click(function() {
			activeAndDeactive("deactive");
		})
	});
})
