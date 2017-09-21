$(function() {});

//创建存储分配全局对象定义
var creatSgObj = new Object();
var htmlImgLeft='<div style="padding:8px;display:table-cell;text-align:center;vertical-align:middle"><span style="font-size:18px;"><img src="';
var htmlImgRight='"/></span></div><div style="padding:3px;display:table-cell;vertical-align:middle">';
var htmlEnd='</div>';


/**
 * 存储管理功能的窗口初始化
 */
$("#storage").click(function() {
	$('#creatStorageModal').modal('show');
	// 初始化下拉框的相关值：参见dropdownInitController.js
	setDropdownValue();
	//清空账户输入框
	$("#sgLogin").val("");
	// 清空用户名输入框
	$("#sgName").val("");
	// 清空分配存储大小输入框
	$("#sgNum").val("");
	// 清空用户名检查相关错误信息与样式
	$("#sgNameAlert").html("");
	$("#sgName").removeClass("warning");
	//清空账户检查相关错误信息与样式
	$("#sgLoginAlert").html("");
	$("#sgLogin").removeClass("warning");	// 清空存储大小检查相关错误信息与样式
	//清空分配存储检查相关错误信息与样式
	$("#sgNumAlert").html("");
	$("#sgNum").removeClass("warning");
	//设置创建用户存储的按钮可用状态
	setCreatSgBtnStatue();
	
	// 存储管理全局对象清空处理
	creatSgObj.sgName  = ""; 
	creatSgObj.sgNum = "";
});

/**
 * 分配存储事件
 */
$("#creatSgSumbit").click(function() {
	creatSgObj.sgLogin = $("#sgLogin").val();
	creatSgObj.sgName = $("#sgName").val();
	creatSgObj.sgNum  = parseInt($("#sgNum").val());
	
	// 将JSON对象转化为JSON字符
	var creatSgJson = JSON.stringify(creatSgObj);
	
	$.ajax({
		data : $.param({
			creatSgJson : creatSgJson
		}),
		type : "post",
		url : "../storage/createSg.do",
		async : true,
		traditional:true,
		beforeSend : function() {
			// 请求前的处理
			$('#creatStorageModal').modal('hide');
		},
		success : function(data) {				
			// 请求成功时处理
			if (data.result) {
				sgprependRowData(data.row);
				bootbox.alert({
					title:'Succeed',
					message:htmlImgLeft+'../img/ok.png'+htmlImgRight+'用户{'+data.row[0].username+'}的云盘分配成功，密码为初始默认密码888888'+htmlEnd,
					size:'small',
				});
			}else{
				bootbox.alert({
					title:'Failed',
					message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'操作云盘处理失败，请稍后再次尝试！'+htmlEnd,
					size:'small',
				});	
			}
		},
		complete : function() {
			refreshStorageInfo();
		},
		error : function() {				
			bootbox.alert({
				title:'Failed',
				message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'创建云盘出现未知错误，请稍后操作！'+htmlEnd,
				size:'small',
			});
			refreshStorageInfo();
		}
	});
});



/**
 * 设置创建存储用户的按钮可用状态
 * 只有当所有参数都选择并且错误信息框里面为空白的时候设置为可用
 */
function setCreatSgBtnStatue() {
	if (  !isEmpty($("#sgName").val()) && isEmpty($("#sgNameAlert").html())
			&& !isEmpty($("#sgNum").val()) && isEmpty($("#sgNumAlert").html())
			&& !isEmpty($("#sgLogin").val()) && isEmpty($("#sgLoginAlert").html())) {
		$("#creatSgSumbit").attr("disabled", false);
	}else{
		$("#creatSgSumbit").attr("disabled", true);
	}
}

/**
 * 设置修改存储大小的按钮可用状态
 * 只有当存储大小输入并且错误信息框里面为空白的时候设置为可用
 */
function setUpdateSgNumBtnState() {
	if (!isEmpty($("#newSgNum").val()) && isEmpty($("#checkSgNumAlert").html())) {
		$("#updateSgNum").attr("disabled", false);
	}else{
		$("#updateSgNum").attr("disabled", true);
	}
}

/**
 * 存储用户的账号相关check
 */
$("#sgLogin").bind("input propertychange", function(){
	sgLoginCheck("sgLogin" , "sgLoginAlert");			
});

/**
 * 存储用户的用户名相关check
 */
$("#sgName").bind("input propertychange", function(){
	sgNameCheck("sgName" , "sgNameAlert");			
});

/**
 * 存储用户的分配大小相关check
 */
$("#sgNum").bind("input propertychange", function(){
	sgNumCheck("sgNum" , "sgNumAlert");			
});

/**
 * 修改存储大小的相关check
 */
$("#newSgNum").bind("input propertychange", function(){
	sgNumCheck("newSgNum" , "checkSgNumAlert");	
});

/**
 * 存储大小相关的check
 * @param nameId  sgNum分配存储  newSgNum修改存储
 * @param messageId sgNumAlert checkSgNumAlert 错误信息
 */
function sgNumCheck(nameId, messageId){
	//检查之前判断按钮可用性
	setCreatSgBtnStatue();
	setUpdateSgNumBtnState();
	if(nameId == "newSgNum"){
		var rows = $("#tb_storage").bootstrapTable('getSelections');
		var value = rows[0].usedSize;
	}
	//检查之前的清空处理
	$("#"+ messageId).html("");
	$("#"+ nameId).removeClass("warning");	
	var sgNum = $("#"+ nameId).val();
	if (isEmpty(sgNum) && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$('#'+ messageId).html("<font color='red'>请输入分配存储</font>");
	}
	if ($.trim(sgNum) == "" && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$("#"+ messageId).html("<font color='red'>存储不能为空格</font>");
	}
	if((sgNum <= 0 || isNaN(sgNum) == true || sgNum.indexOf('0') == 0) && isEmpty($("#"+ messageId).html())){
		$("#"+ nameId).addClass("warning");
		$("#"+ messageId).html("<font color='red'>输入为无效字符</font>");
	}
	//存储大小控制(现为虚拟)
	if(sgNum >=1000 && isEmpty($("#"+ messageId).html())){
		$("#"+ nameId).addClass("warning");
		$("#"+ messageId).html("<font color='red'>已超出分配内存</font>");
	}
	if(value.indexOf("KB") > 0 ){
		if(sgNum*1024*1024 <= parseFloat(value) && isEmpty($("#"+ messageId).html())){
			$("#"+ nameId).addClass("warning");
			$("#"+ messageId).html("<font color='red'>不能小于已用容量，请重新设置！</font>");
		}
	}
	if(value.indexOf("MB") > 0){
		if(sgNum*1024 <= parseFloat(value) && isEmpty($("#"+ messageId).html())){
			$("#"+ nameId).addClass("warning");
			$("#"+ messageId).html("<font color='red'>不能小于已用容量，请重新设置！</font>");
		}
	}
	if(value.indexOf("G") > 0){
		if(sgNum <= parseFloat(value) && isEmpty($("#"+ messageId).html())){
			$("#"+ nameId).addClass("warning");
			$("#"+ messageId).html("<font color='red'>不能小于已用容量，请重新设置！</font>");
		}
	}
	
	//判断按钮可用性
	setCreatSgBtnStatue();
	setUpdateSgNumBtnState();
}

/**
 * 用户名相关check
 * @param nameId 需要check的用户名的画面Id  sgName:用户名  newSgName：修改用户名
 * @param messageId check后错误信息显示Id
 */
function sgNameCheck(nameId, messageId){	
	//检查之前判断按钮可用性
	setCreatSgBtnStatue();
	
	//检查之前的清空处理
	$("#"+ messageId).html("");
	$("#"+ nameId).removeClass("warning");	
	var sgName = $("#"+ nameId).val();
	if (isEmpty(sgName) && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$('#'+ messageId).html("<font color='red'>请输入用户名</font>");
	}
	if ($.trim(sgName) == "" && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$("#"+ messageId).html("<font color='red'>用户名不能为空格</font>");
	}
	if (sgName.length > 20 && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$("#"+ messageId).html("<font color='red'>用户名过长</font>");
	}
	/*if(isEmpty($("#"+ messageId).html())){
		// 用户名是否存在问题检查
		$.ajax({
			type : "post",
			url : "../storage/checkSg.do",
			dataType : "json",
			data : {
				sgName : $("#"+ nameId).val()
			},
			success : function(data) {
				if (data.result == 1) {
					$("#"+ nameId).addClass("warning");
					$("#"+ messageId).html("<font color='red'>用户名已使用</font>");
				} else {
					$("#"+ nameId).removeClass("warning");
					$("#"+ messageId).html("");					
				}
			},
			complete : function() {
				//判断按钮可用性
				setCreatSgBtnStatue();
			},
			error : function() {
				bootbox.alert({
					title:'Failed',
					message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'用户名验证失败！'+htmlEnd,
					size:'small',
				});			
			}
		});	
	}*/
	//判断按钮可用性
	setCreatSgBtnStatue();
}


/**
 * 账号相关check
 * @param nameId 需要check的账号的画面Id  sgLogin:账号
 * @param messageId check后错误信息显示Id
 */
function sgLoginCheck(nameId, messageId){	
	//检查之前判断按钮可用性
	setCreatSgBtnStatue();
	
	//检查之前的清空处理
	$("#"+ messageId).html("");
	$("#"+ nameId).removeClass("warning");	
	var sgLogin = $("#"+ nameId).val();
	if (isEmpty(sgLogin) && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$('#'+ messageId).html("<font color='red'>请为用户分配账号</font>");
	}
	if ($.trim(sgLogin) == "" && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$("#"+ messageId).html("<font color='red'>账号不能为空格</font>");
	}
	if (sgLogin.length > 20 && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$("#"+ messageId).html("<font color='red'>用账号过长</font>");
	}
	if(isEmpty($("#"+ messageId).html())){
		// 账号是否存在问题检查
		$.ajax({
			type : "post",
			url : "../storage/checkSg.do",
			dataType : "json",
			data : {
				sgLogin : $("#"+ nameId).val()
			},
			success : function(data) {
				if (data.result == 1) {
					$("#"+ nameId).addClass("warning");
					$("#"+ messageId).html("<font color='red'>账号已使用</font>");
				} else {
					$("#"+ nameId).removeClass("warning");
					$("#"+ messageId).html("");					
				}
			},
			complete : function() {
				//判断按钮可用性
				setCreatSgBtnStatue();
			},
			error : function() {
				bootbox.alert({
					title:'Failed',
					message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'账号验证失败！'+htmlEnd,
					size:'small',
				});			
			}
		});	
	}
	//判断按钮可用性
	setCreatSgBtnStatue();
}


/**
 * 插入数据到表格在现有数据之前
 * @param row	最新行的数据
 */
function sgprependRowData(row){	
	if(!isEmpty(row)){
		var totalRows = $('#tb_storage').bootstrapTable('getOptions').totalRows;
		$('#tb_storage').bootstrapTable('getOptions').totalRows = totalRows + 1;
		$("#tb_storage").bootstrapTable('prepend', row);
	}
	
}

/**
 * 修改分配存储大小窗口初始化
 */
function updateSgNum(row) {
	//清空之前的check值以及输入框
	$('#checkSgNumAlert').html("");
	$('#newSgNum').val("");
	$("#newSgNum").removeClass("warning");
	//修改存储按钮置为初始状态不可用
	$("#updateSgNum").attr("disabled", true);
	//保存选中的用户信息
	$('#userID').val(row.userID);	
	$('#currentSgNum').val(row.totalSize);
	//打开修改存储的modal框
	$('#updateSgNumModal').modal('show');
	setUpdateSgNumBtnState();
}


/**
 * 修改分配存储大小
 */
$("#updateSgNum").click(function() {
	var index = $('#userID').val();
	var row = $("#tb_storage").bootstrapTable('getRowByUniqueId', index);	
	if(!isEmpty(row)){
		$.ajax({
			data : $.param({
				sgNum : $('#newSgNum').val(),
				userID : row.userID
			}),
			type : "post",
			url : "../storage/updateSgNum.do",
			async : true,
			traditional:true,
			beforeSend : function() {
				// 请求前的处理
			},
			success : function(data) {				
				// 请求成功时处理
				if (data.result) {
					$('#updateSgNumModal').modal('hide');
					bootbox.alert({
						title:'Success',
						message:htmlImgLeft+'../img/ok.png'+htmlImgRight+'存储空间修改成功！'+htmlEnd,
						size:'small',
					});
					refreshStorageInfo();
				}else{	
					$("#checkSgNumAlert").html("<font color='red'>修改失败，稍后尝试!</font>");
				}				
			},
			complete : function() {
			},
			error : function() {				
				$("#checkSgNumAlert").html("<font color='red'>修改异常，稍后尝试!</font>");;
			}
		});
	}
	
});


/**
 * 云盘操作
 * @param controlType 1:删除 2:重置密码
 */
function sgControl(controlType){
	//操作类型的处理
	var controlName = null;
	switch (controlType) {
	case 1:
		controlName = "删除";
		break;
	case 2:	
		controlName = "重置密码";
		break;
	case 3:	
		controlName = "变更存储";
		break;
	default:
		return;
	}
	//未选择操作的电脑情况弹出消息框
	var rows = $("#tb_storage").bootstrapTable('getSelections');
	if(isEmpty(rows)){
		bootbox.alert({
			title:controlName,
			size:'small',
			message:htmlImgLeft+'../img/tip.png'+htmlImgRight+'请选择需要' + controlName + '的云盘！'+htmlEnd,
			});
		return;	
	}
	if(controlName == "变更存储"){
		if(rows.length != 1){
			bootbox.alert({
				title:controlName,
				size:'small',
				message:htmlImgLeft+'../img/tip.png'+htmlImgRight+'只能' + controlName + '一台云盘！'+htmlEnd,
				});
			return;	
		}
	}
	
	//根据状态获取所有云盘的用户id
	var allUserID = getAllUserID();
	var allUserName = getAllUserName();
	var rows = $("#tb_storage").bootstrapTable('getSelections');
	//开始对存储云盘操作
	if(controlName == "删除"){
		bootbox.confirm({
			title:controlName,
			size:'small',
			message:htmlImgLeft+'../img/tip.png'+htmlImgRight+'确认' +controlName+'{'+ allUserName+ '}的云盘?'+htmlEnd,
			buttons : {
				confirm : {
					label : '确认'
				},
				cancel : {
					label : '取消'
				}
			},
			callback : function(result) {
				if(result){
					// 删除云盘
					$.ajax({
						data : $.param({
							userID : allUserID,
						}),
						type : "post",
						url : "../storage/deleteSgInstance.do",
						async : true,
						beforeSend : function() {
							// 请求前的处理
						},
						success : function(data) {
							// 请求成功时处理
							if(data.result){
								bootbox.alert({
									title:'Success',
									size:'small',
									message:htmlImgLeft+'../img/ok.png'+htmlImgRight+allUserName+'已删除'+htmlEnd,
								});
							}else{
								bootbox.alert({
									title:'Failed',
									message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'操作云盘处理失败，请稍后再次尝试！' + htmlEnd,
									size:'small',
								});
							}
						},
						complete : function() {
							refreshStorageInfo();
						},
						error : function() {
							bootbox.alert({
								title:'Failed',
								message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'处理过程发生未知异常，请稍后再次尝试！'+ htmlEnd,
								size:'small',
							});
							refreshStorageInfo();
						}
					});
				}
			}	
		});
	}
	if(controlName == "重置密码"){
		bootbox.confirm({
			title:controlName,
			size:'small',
			message:htmlImgLeft+'../img/tip.png'+htmlImgRight+'确认重置用户{' + allUserName+ '}的密码?'+htmlEnd,
			buttons : {
				confirm : {
					label : '确认'
				},
				cancel : {
					label : '取消'
				}
			},
			callback : function(result) {
				if(result){
					// 重置密码
					$.ajax({
						data : $.param({
							userID : allUserID,
						}),
						type : "post",
						url : "../storage/resetSgPassword.do",
						async : true,
						beforeSend : function() {
							// 请求前的处理
						},
						success : function(data) {
							// 请求成功时处理
							if(data.result){
								bootbox.alert({
									title:'Success',
									message:htmlImgLeft+'../img/ok.png'+htmlImgRight+'密码重置成功，密码已经设置为默认密码888888！'+htmlEnd,
									size:'small',
								});
							}else{
								bootbox.alert({
									title:'Failed',
									message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'操作云盘处理失败，请稍后再次尝试！' + htmlEnd,
									size:'small',
								});
							}
							
						},
						complete : function() {
							refreshStorageInfo();
						},
						error : function() {
							bootbox.alert({
								title:'Failed',
								message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'处理过程发生未知异常，请稍后再次尝试！'+ htmlEnd,
								size:'small',
							});
							refreshStorageInfo();
						}
					});
					
				}
			}
			
		});
	}
	if(controlName == "变更存储"){
		updateSgNum(rows[0]);
	}	
}


/**
 * 获取所有的选中云盘用户userID
 * @returns {Array}
 */
function getAllUserID() {
	var rows = $("#tb_storage").bootstrapTable('getSelections');
	var allUserID = new Array();
	for (var i = 0; i < rows.length; i++) {		
		allUserID[i]= rows[i].userID;	
	}
	return allUserID;
}

/**
 * 获取所有的选中云盘用户名
 * @returns {String}
 */
function getAllUserName(){
	var rows = $("#tb_storage").bootstrapTable('getSelections');
	var allUserName = "";
	for(var i = 0; i < rows.length ; i++){
		if(allUserName == ""){
			allUserName = rows[i].userName;
		}else{
			allUserName = allUserName + ", "+rows[i].userName;
		}
	}
	return allUserName;
}


/**
 * 删除云盘
 */
$("#btn_destroySg").click(function(){
	sgControl(1);
});

/**
 * 重置密码
 */
$("#btn_resetPwdSg").click(function(){
	sgControl(2);
});

/**
 * 修改存储
 */
$("#btn_updateSg").click(function(){
	sgControl(3);
});