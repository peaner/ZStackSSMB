$(function() {
	initZoom();	
});

/**
 * 设置修改密码的按钮可用状态
 * 只有当所有参数都选择并且错误信息框里面为空白的时候设置为可用
 */
function setUpdatePasswordBtnStatue() {
	if (!isEmpty($("#changePassword").val()) && !isEmpty($("#checkPassword").val())
			&& isEmpty($("#changeAlert").html()) && isEmpty($("#checkAlert").html())) {
		$("#updatePassword").attr("disabled", false);
	}else{
		$("#updatePassword").attr("disabled", true);
	}
}

/**
 * 登出鼠标移动改变字体颜色问题
 */
$("#logOut").mouseover(function() {
	$("#logOutFont").addClass('mouse-on-title');
	$(this).css("cursor","pointer");
}).mouseout(function() {
	$("#logOutFont").removeClass('mouse-on-title');
	$(this).css("cursor","default");
});

/**
 * 修改密码鼠标移动改变字体颜色问题
 */
$("#updatePwd").mouseover(function() {
	$("#updatePwdFont").addClass('mouse-on-title');
	$(this).css("cursor","pointer");
}).mouseout(function() {
	$("#updatePwdFont").removeClass('mouse-on-title');
	$(this).css("cursor","default");
});

/**
 * 配置鼠标移动改变字体颜色问题
 */
$("#highSetting").mouseover(function() {
	$("#highSettingFont").addClass('mouse-on-title');
	$(this).css("cursor","pointer");
}).mouseout(function() {
	$("#highSettingFont").removeClass('mouse-on-title');
	$(this).css("cursor","default");
});

/**
 * 更多服务的悬浮框
 */
$("[data-toggle='popover']").popover({  
    html : true,    
    title: title(),    
    placement:'top',
    delay:{show:0, hide:0},  
    content: function() {  
      return content();    
    }   
});

//模拟动态加载标题(真实情况可能会跟后台进行ajax交互)
function title() {  
    return '增值服务';  
}

//模拟动态加载内容(真实情况可能会跟后台进行ajax交互)  
function content() {  
    var data = $("<li><div style='text-align: center'><img id='storage' alt='' src='../img/bg-problem.png' /><h5>故障修复</h5></div></li>" 
    		+"<li><div style='text-align: center'><img id='storage' alt='' src='../img/bg-appdownload.png' /><h5>&nbsp;&nbsp;应用下载</h5></div></li>");   
    return data;  
}

/**
 * 图片变大效果初始化
 */
function initZoom(){
	$("#computer").zoomImgRollover();
	$("#application").zoomImgRollover();
	$("#storage").zoomImgRollover();
}

/**
 * 打开修改密码窗口
 */
$("#updatePwd").click(function() {
	$("#checkPassword").removeClass("warning");
	$("#changePassword").removeClass("warning");
	$("#changeAlert").html("");
	$("#checkAlert").html("");
	$('#changePwdModal').modal('show');
	$("#updatePassword").attr("disabled", true);
	//清空密码输入框
	$("#changePassword").val("");
	//清空密码确认输入框
	$("#checkPassword").val("");
});

/**
 * 主页跳转(该功能暂时删除)
 */
$("#homePage").click(function(){
	window.location.href="";
});

/**
 * 高级设置跳转(地址待修改)
 */
function highSetting(settingUrl){
	settingUrl = settingUrl + ":5000/#/main/instance";
	window.open(settingUrl);
}
/**
 * enter键也实现登录提交
 */
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		login();
	}
});

/**
 * 登录按钮
 */
$("#btn_login").click(function() {
	login();
});

/**
 * 登录实现
 */
function login() {
	$("#pwdDiv").removeClass("has-error");
	$("#errMsg").addClass("hidden");
	var loginObj = new Object();
	loginObj.accountName = $("#inputAccount").val();
	loginObj.password = $("#inputPassword").val();
	//判断用户名密码是否为空
	if (isEmpty(loginObj.accountName)) {
		$("#pwdDiv").addClass("has-error");
		$("#errMsg").removeClass("hidden");
		$("#errMsg").text("请输入用户名");
		$("#inputAccount").focus();
	}
	else if(isEmpty(loginObj.password)) {
		$("#pwdDiv").addClass("has-error");
		$("#errMsg").removeClass("hidden");
		$("#errMsg").text("请输入密码");
		$("#inputPassword").focus();
	}
	else {
		// 将JSON对象转化为JSON字符
		var loginJson = JSON.stringify(loginObj);
		$.post('login/validateLogin.do', {
			"loginObj" : loginJson
		}, function(e) {
			if (e.errMsg != null && e.errMsg != "") {
				$("#pwdDiv").addClass("has-error");
				$("#errMsg").removeClass("hidden");
				$("#errMsg").text(e.errMsg);
			} else {
				$("#loginForm").submit();
			}
		});
	}
}

/**
 * 登出操作
 */
$("#logOut").click(function(){
	bootbox.confirm({
		title:"登出",
		size: "small", 
		buttons : {
			confirm : {
				label : '确认'
			},
			cancel : {
				label : '取消'
			}
		},
		message: '<div style="padding:8px;display:table-cell;text-align:center;vertical-align:middle"><span style="font-size:18px;"><img src="../img/lgn-out.png"/></span></div><div style="padding:3px;display:table-cell;vertical-align:middle">请确认是否登出?</div>', 
		callback: function(result){ 
			if(result){
				$.ajax({
					data : {},
					type : "post",
					url : "../logout/validate.do",
					async : true,
					success : function(data) {
							// 请求成功时处理
						if(!isEmpty(data)){
							window.location.href = data;
						}else{
							bootbox.alert("登出操作失败！");
						}			
					},
					error : function() {
						bootbox.alert("登出操作失败！");
					}
				});
			}
		}
	});
	
	$(".modal-sm .bootbox-body").css({
		"padding":10,
		});
});

/**
 * 修改密码相关check
 */
$("#changePassword").bind("input propertychange", function(){
	$("#changeAlert").html("");
	$("#changePassword").removeClass("warning");
	var changePassword = $("#changePassword").val();
	var checkPassword = $("#checkPassword").val();
	if (isEmpty(changePassword) && isEmpty($("#changeAlert").html())) {
		$("#changeAlert").html("<font color='red'>请输入修改密码</font>");
		$("#changePassword").addClass("warning");
	}
	if (changePassword.length < 6 || changePassword.length>30){
		$("#changeAlert").html("<font color='red'>密码位数应为6~30</font>");
		$("#changePassword").addClass("warning");
	}
	if ($.trim(changePassword) == $.trim(checkPassword) && !isEmpty(changePassword)) {
		$("#checkAlert").html("");
		$("#checkPassword").removeClass("warning");
	}
	//判断按钮可用性
	setUpdatePasswordBtnStatue();		
});

/**
 * 确认密码相关check
 */
$("#checkPassword").bind("input propertychange", function(){
	$("#checkPassword").removeClass("warning");
	$("#checkAlert").html("");
	var changePassword = $("#changePassword").val();
	var checkPassword = $("#checkPassword").val();
	if (isEmpty(checkPassword) && isEmpty($("#checkAlert").html())) {
		$("#checkAlert").html("<font color='red'>请输入确认密码</font>");
		$("#checkPassword").addClass("warning");
	}
	if ($.trim(changePassword) != $.trim(checkPassword)) {
		$("#checkAlert").html("<font color='red'>两次密码不一致</font>");
		$("#checkPassword").addClass("warning");
	}
	//判断按钮可用性
	setUpdatePasswordBtnStatue();		
});

/**
 * 更新密码
 */
$("#updatePassword").click(function() {
	var changePassword = $("#changePassword").val();
	var checkPassword = $("#checkPassword").val();
	var updatePasswordObj = new Object();
	updatePasswordObj.changePassword = changePassword;
	updatePasswordObj.checkPassword = checkPassword;
	var updatePasswordJson = JSON.stringify(updatePasswordObj);
	$.ajax({
		data : $.param({
			updatePasswordJson : updatePasswordJson
		}),
		type : "post",
		url : "../login/updatePassword.do",
		async : true,
		beforeSend : function() {
			// alert("请求前的处理");
		},
		success : function(data) {
			//  alert("请求成功时处理");
			if($.trim(data.success)=="true"){
				bootbox.alert("修改密码成功");
			}else{
				bootbox.alert("修改密码失败");
			}
			$('#changePwdModal').modal('hide');
		},
		complete : function(data) {
			// alert("请求完成的处理");
		},
		error : function() {
			bootbox.alert("修改密码出现异常");
		}
	});
	
});

/**
 * 关闭窗口时去掉session
 */
//window.onbeforeunload =function() {
//	$.ajax({
//		data : {},
//		type : "post",
//		url : "../logout/closeWindow.do",
//		async : true,
//	});	
//};

