$(function() {});

// 创建虚拟机全局对象定义
var creatVmObj = new Object();
var creatVmNumber = new Object();
var htmlImgLeft='<div style="padding:8px;display:table-cell;text-align:center;vertical-align:middle"><span style="font-size:18px;"><img src="';
var htmlImgRight='"/></span></div><div style="padding:3px;display:table-cell;vertical-align:middle">';
var htmlEnd='</div>';
/**
 * 虚拟机创建的窗口初始化
 */
$("#computer").click(function() {
	$('#creatComputeModal').modal('show');
	// 初始化下拉框的相关值：参见dropdownInitController.js
	setDropdownValue();
	// 清空电脑名称输入框
	$("#cpName").val("");
	// 清空电脑数量输入框
	$("#creatNum").val("");
	// 清空电脑名称检查相关错误信息与样式
	$("#cpAlert").html("");
	$("#cpName").removeClass("warning");
	// 清空电脑数量检查相关错误信息与样式
	$("#numAlert").html("");
	$("#creatNum").removeClass("warning");
	//设置创建虚拟机的按钮可用状态
	setCreatVmBtnStatue();
	// 虚拟机全局对象清空处理
	creatVmObj.name = ""; 						// 虚拟机名称
	creatVmObj.instanceOfferingUuid = ""; 		// 计算性能UUID
	creatVmObj.imageUuid = ""; 					// 操作系统UUID
	creatVmObj.l3NetworkUuids = new Array(); 	// 三层网络：后台代码中获取
	creatVmObj.defaultL3NetworkUuid = ""; 		// 三层默认网络：后台代码中获取
	creatVmObj.resourceUuid = ""; 				// 后台代码中获取
});

/**
 * 重置密码
 */
$("#resetUserPassword").click(function() {
	var allName = getAllComputerName();
	if(isEmpty(allName)){
		bootbox.alert({
			title:'Tip',
			message:htmlImgLeft+'../img/tip.png'+htmlImgRight+'请选择需要重置密码的电脑！'+htmlEnd,
			size:'small',
				});
		return;
	}
	bootbox.confirm({
		title:'重置',
		size:'small',
		message : htmlImgLeft+'../img/reset.png'+htmlImgRight+'确认将电脑名称' + allName + '的密码重置为初始密码888888？'+htmlEnd,
		buttons : {
			confirm : {
				label : '确认'
			},
			cancel : {
				label : '取消'
			}
		},
		callback : function(result) {
			var uuidArray = getAllUuid();
			// 点击确认的时候继续下面操作
			if (result) {				
				$.ajax({
					type : "post",
					url : "../vm/resetUserPassword.do",
					async : true,
					dataType: "json",
					data : {
						uuids : uuidArray
					},
					success : function(data) {
						if (data.result) {
							bootbox.alert({
								title:'Success',
								message:htmlImgLeft+'../img/ok.png'+htmlImgRight+'选中电脑密码重置成功，密码已经设置为默认密码888888！'+htmlEnd,
								size:'small',
							});
						} else {
							bootbox.alert({
								title:'Failed',
								message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'重置密码失败！'+htmlEnd,
								size:'small',
							});
						}
					},
					error : function() {
						bootbox.alert({
							title:'Failed',
							message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'重置密码出错！'+htmlEnd,
							size:'small',
						});
					}
				});				
			}
		}
	});
});

/**
 * 创建虚拟机事件
 */
$("#creatVmSumbit").click(function() {
	// 创建多台的时候进行多个异步请求获取到对应的请求后刷新数据表
	var creatNumInt = parseInt($("#creatNum").val());
	for (var i = 1; i < creatNumInt+1; i++) {
		if (creatNumInt == 1) {
			// 只创建一台的时候名称就为页面显示
			creatVmObj.name = $("#cpName").val();
		} else {
			// 只创建多台的时候名称设置为页面名称+"-" + 数量
			creatVmObj.name = $("#cpName").val() + "-" + i.toString();
		}

		// 将JSON对象转化为JSON字符
		var creatVmJson = JSON.stringify(creatVmObj);
		$.ajax({
			data : $.param({
				creatVmJson : creatVmJson
			}),
			type : "post",
			url : "../vm/createVm.do",
			async : true,
			traditional:true,
			beforeSend : function() {
				// 请求前的处理
				$('#creatComputeModal').modal('hide');
			},
			success : function(data) {				
				// 请求成功时处理
				if (data.result) {
					//insertRowData(data.row);
					prependRowData(data.row);					
					//refreshComputerInfo();
				}else{
					bootbox.alert({
						title:'Failed',
						message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'操作电脑处理失败，请稍后再次尝试！'+htmlEnd,
						size:'small',
					});	
				}				
			},
			complete : function() {
				//更新获取饼图数据
				updateRowDataIndex();
				updateChartValue();
			},
			error : function() {				
				bootbox.alert({
					title:'Failed',
					message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'创建虚拟机出现未知错误，请稍后操作！'+htmlEnd,
					size:'small',
				});
				refreshComputerInfo();
			}
		});
		//休眠10毫秒
		sleep(10);
	}
});

/**
 * 将创建虚拟机信息显示到第一行
 * @param row	创建虚拟机信息
 */
function insertRowData(row){
	if(!isEmpty(row)){
		$("#tb_computerInfo").bootstrapTable('insertRow', {
			index : 0,
			row : {
				name : row.name,
				ip : row.ip,
				platform : row.platform,
				createDate : row.createDate,
				state : row.state,
				imageName : row.imageName
			}
		});		
	}	
}

/**
 * 插入数据到表格在现有数据之前
 * @param row	最新行的数据
 */
function prependRowData(row){	
	if(!isEmpty(row)){
		var totalRows = $('#tb_computerInfo').bootstrapTable('getOptions').totalRows;
		$('#tb_computerInfo').bootstrapTable('getOptions').totalRows = totalRows + 1;
		$("#tb_computerInfo").bootstrapTable('prepend', row);
	}
	
}

/**
 * 更新序号
 */
function updateRowDataIndex(){
	var rows = $("#tb_computerInfo").bootstrapTable('getData');
	for (var i = 0; i < rows.length; i++) {
		$("#tb_computerInfo").bootstrapTable('updateRow', {
			index : i,
			row : {
				index : i
			}
		});
	}
}

/**
 * 修改电脑用户名窗口初始化
 */
function updateVmName(index) {
	//清空之前的check值以及输入框
	$('#checkAlert').html("");
	$('#newVmName').val("");
	//修改密码按钮置为初始状态不可用
	$("#updateVmName").attr("disabled", true);
	//保存选中的用户信息
	$('#vmNameIndex').val(index);	
	//赋值到modal的当前用户名
	var row = $("#tb_computerInfo").bootstrapTable('getRowByUniqueId', index);	
	$('#currentVmName').val(row.name);
	//打开修改电脑的modal框
	$('#updateVmNameModal').modal('show');	
}

/**
 * 修改电脑用户名
 */
$("#updateVmName").click(function() {
	var index = $('#vmNameIndex').val();
	var row = $("#tb_computerInfo").bootstrapTable('getRowByUniqueId', index);	
	if(!isEmpty(row)){
		$.ajax({
			data : $.param({
				vmName : $('#newVmName').val(),
				vmUuid : row.uuid
			}),
			type : "post",
			url : "../vm/updateVmInstanceName.do",
			async : true,
			traditional:true,
			beforeSend : function() {
				// 请求前的处理
			},
			success : function(data) {				
				// 请求成功时处理
				if (data.result) {
					$('#updateVmNameModal').modal('hide');
					bootbox.alert({
						title:'Success',
						message:htmlImgLeft+'../img/ok.png'+htmlImgRight+'用户名修改成功！'+htmlEnd,
						size:'small',
					});
					updateVmNameRow();
				}else{	
					$("#checkVmNameAlert").html("<font color='red'>修改失败，稍后尝试!</font>");
				}				
			},
			complete : function() {
			},
			error : function() {				
				$("#checkVmNameAlert").html("<font color='red'>修改异常，稍后尝试!</font>");;
			}
		});
	}	
});

/**
 * 更新指定行的用户名称
 */
function updateVmNameRow() {
	var index = Number($('#vmNameIndex').val());
	var newVmName = $('#newVmName').val();
	$("#tb_computerInfo").bootstrapTable('updateCell', {
		index : index,
		field : "name",
		value : newVmName			
	});
}

/**
 * 修改电脑用户名称相关check
 */
$("#newVmName").bind("input propertychange", function(){
	vmNameCheck("newVmName" , "checkVmNameAlert");			
});

/**
 * 电脑名称相关check
 */
$("#cpName").bind("input propertychange", function(){
	vmNameCheck("cpName" , "cpAlert");			
});

/**
 * 电脑名称相关check
 * @param nameId 需要check的电脑名称的画面Id  cpName:创建虚拟机  newVmName：修改虚拟机名称
 * @param messageId check后错误信息显示Id
 */
function vmNameCheck(nameId, messageId){	
	//检查之前判断按钮可用性
	setCreatVmBtnStatue();
	setUpdateVmNameBtnStatue();
	
	//检查之前的清空处理
	$("#"+ messageId).html("");
	$("#"+ nameId).removeClass("warning");	
	var vmName = $("#"+ nameId).val();
	if (isEmpty(vmName) && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$('#'+ messageId).html("<font color='red'>请输入电脑用户名</font>");
	}
	if ($.trim(vmName) == "" && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$("#"+ messageId).html("<font color='red'>电脑用户名不能为空格</font>");
	}
	if (vmName.length > 20 && isEmpty($("#"+ messageId).html())) {
		$("#"+ nameId).addClass("warning");
		$("#"+ messageId).html("<font color='red'>电脑用户名过长</font>");
	}
	if(isEmpty($("#"+ messageId).html())){
		// 电脑名称是否存在问题检查
		$.ajax({
			type : "post",
			url : "../init/checkValues.do",
			dataType : "json",
			data : {
				cpName : $("#"+ nameId).val()
			},
			success : function(data) {
				if (data.result == 1) {
					$("#"+ nameId).addClass("warning");
					$("#"+ messageId).html("<font color='red'>电脑用户名已使用</font>");
				} else {
					$("#"+ nameId).removeClass("warning");
					$("#"+ messageId).html("");					
				}
			},
			complete : function() {
				//判断按钮可用性
				setCreatVmBtnStatue();
				setUpdateVmNameBtnStatue();
			},
			error : function() {
				bootbox.alert({
					title:'Failed',
					message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'电脑用户名验证失败！'+htmlEnd,
					size:'small',
				});			
			}
		});	
	}
	//判断按钮可用性
	setCreatVmBtnStatue();
	setUpdateVmNameBtnStatue();
}

/**
 * 设置创建虚拟机的按钮可用状态
 * 只有当所有参数都选择并且错误信息框里面为空白的时候设置为可用
 */
function setCreatVmBtnStatue() {
	if (!isEmpty($("#calPerformance").text()) && !isEmpty($("#operSystem").text())
			&& !isEmpty($("#cpName").val()) && !isEmpty($("#creatNum").val())
			&& isEmpty($("#cpAlert").html()) && isEmpty($("#numAlert").html())) {
		$("#creatVmSumbit").attr("disabled", false);
	}else{
		$("#creatVmSumbit").attr("disabled", true);
	}
}



/**
 * 设置修改虚拟机名称的按钮可用状态
 * 只有当名称输入并且错误信息框里面为空白的时候设置为可用
 */
function setUpdateVmNameBtnStatue() {
	if (!isEmpty($("#newVmName").val()) && isEmpty($("#checkVmNameAlert").html())) {
		$("#updateVmName").attr("disabled", false);
	}else{
		$("#updateVmName").attr("disabled", true);
	}
}

/**
 * 电脑数量相关check（光标移开事件）
 */
$("#creatNum").bind("input propertychange", function(){	
	//判断按钮可用性
	setCreatVmBtnStatue();
	//清空电脑数量检查相关错误信息与样式
	$("#numAlert").html("");
	$("#creatNum").removeClass("warning");
	// 虚拟机数量为空检查
	if (isEmpty($("#creatNum").val())) {
		$("#numAlert").html("<font color='red'>请输入虚拟机创建数量</font>");
	}
	// 虚拟机数量为数字检查
	if ((isNaN($("#creatNum").val()) || $.trim($("#creatNum").val()) == "") 
			&& isEmpty($("#numAlert").html())) {
		$("#numAlert").html("<font color='red'>虚拟机创建数量必须为数字</font>");
	}
	// 虚拟机数量为整数检查
	if (($("#creatNum").val())%1!=0 && isEmpty($("#numAlert").html())) {
		$("#numAlert").html("<font color='red'>虚拟机创建数量必须为整数</font>");
	}
	// 虚拟机数量为数字检查
	if (parseInt($("#creatNum").val()) < 1 && isEmpty($("#numAlert").html())) {
		$("#numAlert").html("<font color='red'>虚拟机创建数量最少为1台</font>");		
	}	
	//虚拟机创建数量上限检查
	if (($("#creatNum").val()) > creatVmNumber && isEmpty($("#numAlert").html())) {
		$("#numAlert").html("<font color='red'>创建数量超限，请重新输入！</font>");		
	}	
	//判断按钮可用性
	setCreatVmBtnStatue();
});

/**
 * 获取提示最大创建电脑数量（光标聚焦事件）
 */
$("#creatNum").focus(function(){
	$.ajax({
		type : "post",
		url : "../init/createVmNumber.do",// 接口
		dataType : "json",
		data : {
			imageUuid : creatVmObj.imageUuid,
			instanceOfferingUuid : creatVmObj.instanceOfferingUuid
		},
		success : function(data) {
			if (!isEmpty(data.result)) {
				// 在数量input上显示最大值
				$("#numAlert").html("<font color='green'>创建数量上限为"+data.result+"台</font>");
				creatVmNumber=data.result;
			}
			else{
				$("#numAlert").html("<font color='red'>系统资源不够，无法再创建！</font>");
			}
		},
		error : function() {
			bootbox.alert({
				title:'failed',
				message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'操作系统选择存在问题！'+htmlEnd,
				size:'small',
			});
		}
	});
});


/**
 * 虚拟机操作各种事件处理
 * @param controlType 1:启动 2:停止 3:重启 4:删除 5:恢复 6:彻底删除
 */
function vmControl(controlType) {
	//获取需要操作的选中电脑的状态集合
	var allNameType = null;
	//操作类型的处理
	var controlName = null;
	//操作过程中临时状态的处理
	var controlTempType = null;
	var destroyingType = null;
	switch (controlType) {
	case 1:		
		controlName = "启动";
		allNameType = "Stopped";
		controlTempType = "Starting";
		break;
	case 2:
		controlName = "停止";
		allNameType = "Running";
		controlTempType = "Stopping";
		break;
	case 3:
		controlName = "重启";
		allNameType = "Running";
		controlTempType = "Rebooting";
		break;
	case 4:
		controlName = "删除";
		allNameType = "Running";
		destroyingType = "Stopped"; // 待特殊处理
		controlTempType = "Destroying";
		break;
	case 5:
		controlName = "恢复";
		allNameType = "Destroyed";
		break;
	case 6:
		controlName = "彻底删除";
		allNameType = "Destroyed";
		break;
	default:
		return;
	}
	//未选择操作的电脑情况弹出消息框
	var rows = $("#tb_computerInfo").bootstrapTable('getSelections');
	if(isEmpty(rows)){
		bootbox.alert({
			title:'Tip',
			size:'small',
			message:htmlImgLeft+'../img/tip.png'+htmlImgRight+'请选择需要' + controlName + '的电脑！'+htmlEnd,
			});
		return;	
	}	
	//根据状态获取所有电脑的名称
	var allName = getAllName(allNameType);
	var destroyingName = "";
	//删除情况有多个状态可以删除，需要特殊处理
	if(!isEmpty(destroyingType)){
		destroyingName = getAllName(destroyingType);
		if(!isEmpty(destroyingName)){
			if(!isEmpty(allName)){
				allName = allName + ", "+ destroyingName;
			}else{
				allName = destroyingName;
			}
		}
		allNameType = allNameType + ", "+ destroyingType;
	}
	//判断选择电脑是否符合要操作的要求
	if (allName == "") {		
		bootbox.alert({
			title:'Tip',
			message:htmlImgLeft+'../img/tip.png'+htmlImgRight+'只能' + controlName + allNameType + '状态的电脑，请重新选择!'+htmlEnd,
			size:'small',
			});
		return;
	}
	//开始进行电脑操作
	bootbox.confirm({
		title: controlName,
		message : htmlImgLeft+'../img/tip.png'+htmlImgRight+'只能' 
			+ controlName + allNameType + '状态的电脑，确认' + controlName + allName+'？' + htmlEnd,
		size:'small',
		buttons : {
			confirm : {
				label : '确认'
			},
			cancel : {
				label : '取消'
			}
		},
		callback : function(result) {
			// 点击确认的时候继续下面操作
			if (result) {
				var rows = $("#tb_computerInfo").bootstrapTable('getSelections');
				for (var i = 0; i < rows.length; i++) {
					if (allNameType.indexOf(rows[i].state) >= 0) {
						// 将状态修改为临时状态
						if(!isEmpty(controlTempType)){
							updateStateRow(Number(rows[i].index), controlTempType);
						}						
						// 启动虚拟机
						$.ajax({
							data : $.param({
								cpUuid : rows[i].uuid,
								index : rows[i].index,
								controlType : controlType
							}),
							type : "post",
							url : "../vm/vmControl.do",
							async : true,
							beforeSend : function() {
								// 请求前的处理
							},
							success : function(data) {
								// 请求成功时处理
								if (!data.result) {
									bootbox.alert({
										title:'Failed',
										message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'操作电脑处理失败，请稍后再次尝试！' + htmlEnd,
										size:'small',
									});									
								}
								//彻底删除情况要单独删除具体行
								if(!isEmpty(data.expunge)){
									refreshComputerInfo();
								}else{
									//更新处理结果信息
									updateRowData(data.index, data.row);
								}
							},
							complete : function() {
								// 更新获取饼图数据
								updateChartValue();
							},
							error : function() {
								bootbox.alert({
									title:'Failed',
									message:htmlImgLeft+'../img/failed.png'+htmlImgRight+'处理过程发生未知异常，请稍后再次尝试！'+ htmlEnd,
									size:'small',
								});
								refreshComputerInfo();
							}
						});
					}
					// 休眠10毫秒
					sleep(1);
				}
			}
		}
	});
}

/**
 * 更新处理结果信息
 * @param index 更新行的行数
 * @param row	最新行的数据
 */
function updateRowData(index, row){
	if(!isEmpty(row) && !isEmpty(row.uuid)){
		$("#tb_computerInfo").bootstrapTable('updateRow', {
			index : Number(index),
			row : {
				name : row.name,
				ip : row.ip,
				platform : row.platform,
				createDate : row.createDate,
				state : row.state,
				imageName : row.imageName
			}
		});
	}	
}

/**
 * 启动虚拟机
 */
$("#btn_startVm").click(function() {
	vmControl(1);	
});

/**
 * 停止虚拟机
 */
$("#btn_stopVm").click(function() {
	vmControl(2);
});

/**
 * 重启虚拟机
 */
$("#rebootVm").click(function() {
	vmControl(3);
});

/**
 * 删除虚拟机
 */
$("#destroyVm").click(function() {
	vmControl(4);
});

/**
 * 恢复虚拟机
 */
$("#recoverVm").click(function() {
	vmControl(5);
});

/**
 * 彻底删除虚拟机
 */
$("#expungeVm").click(function() {
	vmControl(6);
});

/**
 * 更新指定行的状态
 * @param index
 * @param runType
 */
function updateStateRow(index, runType) {
	$("#tb_computerInfo").bootstrapTable('updateRow', {
		index : Number(index),
		row : {
			state : runType
		}
	});
}


/**
 * 根据状态获取需要操作的选中电脑名称
 * 
 * @returns {String}
 */
function getAllName(state) {
	var rows = $("#tb_computerInfo").bootstrapTable('getSelections');
	var allName = "";
	for (var i = 0; i < rows.length; i++) {
		if(rows[i].state == state){
			if (allName == "") {
				allName = rows[i].name;
			} else {
				allName = allName + ", " + rows[i].name;
			}	
		}		
	}

	return allName;
}

/**
 * 获取所有的选中电脑名称
 * @returns {String}
 */
function getAllComputerName() {
	var rows = $("#tb_computerInfo").bootstrapTable('getSelections');
	var allName = "";
	for (var i = 0; i < rows.length; i++) {		
		if (allName == "") {
			allName = rows[i].name;
		} else {
			allName = allName + ", " + rows[i].name;
		}	
	}

	return allName;
}

/**
 * 获取所有的选中电脑UUID
 * @returns {Array}
 */
function getAllUuid() {
	var rows = $("#tb_computerInfo").bootstrapTable('getSelections');
	var uuidArray = new Array();
	for (var i = 0; i < rows.length; i++) {		
		uuidArray[i]= rows[i].uuid;	
	}
	
	return uuidArray;
}

/**
 * 应用部署功能
 */
$("#application").click(function() {
	bootbox.alert({
		title:'Tip',
		message:htmlImgLeft+'../img/tip.png'+htmlImgRight+'应用部署功能还在研究中，敬请期待！' + htmlEnd,
		size:'small',
	});	
});
