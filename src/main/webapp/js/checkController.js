$(function() {});

/**
 * 修改存储分配用户名称相关check
 */
$("#newSgName").bind("input propertychange", function(){
	sgNameCheck("newSgName" , "checkVmNameAlert");			
});

/**
 * 电脑名称相关check
 */
$("#sgName").bind("input propertychange", function(){
	sgNameCheck("sgName" , "sgAlert");			
});

/**
 * 电脑名称相关check
 * @param nameId 需要check的用户名的画面Id  sgName:创建虚拟机  newSgName：修改虚拟机名称
 * @param messageId check后错误信息显示Id
 */
function sgNameCheck(nameId, messageId){	
	//检查之前判断按钮可用性
	setCreatSgBtnStatue();
	setUpdateSgNameBtnStatue();
	
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
