/**
 * 各个下拉框初始化值的设置
 * @param data
 */
function setDropdownValue(data) {
	// 清空所有值，然后重新查询最新的值
	$("#calPerf").empty();
	$("#operSys").empty();
	$("#storCap").empty();
	
	//计算机性能下拉框值获取
	$.ajax({
		type : "post",
		url : "../init/calPerformanceInit.do",
		async : true,
		success : function(result) {
			if(result != null){
				// 请求成功时处理
				var data = eval(result);// 得到实体类，实体类已定通过Json 格式转一下 ，JS中通过eval
				var param = new Object();
				for (var i = 0; i < data.length; i++) {
					var html = "";					 
					param.name = data[i].name;
					param.uuid = data[i].uuid;
					html = "<li><a onclick=setCalPerformanceValue('" + JSON.stringify(param) + "') id='" + data[i].uuid + "' code='" + data[i].uuid + "'>" 
							+ "名称：" + data[i].name 
							+ "<br>CPU数量：" + data[i].cpuNum 
							+ "<br>内存：" + data[i].memorySize
							+ "</a></li>";// 定义a 标签 给 a标签定义属性Code，赋值
					$("#calPerf").append(html);// 给li追加 a标签
				}
				param.name = data[0].name;
				param.uuid = data[0].uuid;
				setCalPerformanceValue(JSON.stringify(param));
			}
		}
	});
	
	//操作系统下拉框值获取
	$.ajax({
		type : "post",
		url : "../init/operSystemInit.do",
		async : true,
		success : function(result) {
			if(result != null){
				// 请求成功时处理
				var data = eval(result);// 得到实体类，实体类已定通过Json 格式转一下 ，JS中通过eval
				var param = new Object();
				for (var i = 0; i < data.length; i++) {
					var html = "";
					param.name = data[i].name;
					param.uuid = data[i].uuid;
					html = "<li><a onclick=setOperSystemValue('" + JSON.stringify(param) + "') id='" + data[i].uuid + "' code='" + data[i].uuid + "'>" 
							+ data[i].name 
							+ "</a></li>";// 定义a 标签 给 a标签定义属性Code，赋值
					$("#operSys").append(html);// 给li追加 a标签
				}
				param.name = data[0].name;
				param.uuid = data[0].uuid;
				setOperSystemValue(JSON.stringify(param));
			}
		}
	});	
	
	//存储容量临时隐藏，需要时候删除hide
	$("#storCapacityGroup").hide();	
	//存储容量下拉框值获取(存储容量临时隐藏,需要时候删除if条件)
	if(false){
		$.ajax({
			type : "post",
			url : "../init/storCapacityInit.do",
			async : true,
			success : function(result) {
				// 请求成功时处理
				var data = eval(result);// 得到市的实体类，实体类已定通过Json 格式转一下 ，JS中通过eval
				var param = new Object();
				for (var i = 0; i < data.length; i++) {
					var html = "";
					param.name = data[i].name;
					param.uuid = data[i].uuid;
					html = "<li><a onclick=setStorCapacityValue('" + JSON.stringify(param) + "') id='" + data[i].uuid + "' code='" + data[i].uuid + "'>" 
							+ data[i].name
							+ "</a></li>";// 定义a 标签 给 a标签定义属性Code，赋值
					$("#storCap").append(html);// 给li追加 a标签
				}
				param.name = data[0].name;
				param.uuid = data[0].uuid;
				setStorCapacityValue(JSON.stringify(param));
			}
		});
	}	
}

/**
 * 设置计算机性能下拉框选中值
 * @param param
 */
function setCalPerformanceValue(param) {
	var data = JSON.parse(param);
	creatVmObj.instanceOfferingUuid=data.uuid;
	$("#calPerformance").text(data.name);
		//$("#numAlert").html("<font color='red'>虚拟机创建数量最多为10台</font>");
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
					creatVmNumber=data.result;
					if (!isEmpty($("#creatNum").val()) && ($("#creatNum").val()) > creatVmNumber){
					$("#numAlert").html("<font color='red'>此计算规格最多创建"+data.result+"台</font>");
					   setCreatVmBtnStatue();
					}
					if (!isEmpty($("#creatNum").val()) && ($("#creatNum").val()) <= creatVmNumber){
						$("#numAlert").html("");
						   setCreatVmBtnStatue();
						}	
				}
				else{
					$("#numAlert").html("<font color='red'>系统资源不够，无法再创建！</font>");
				}
			},
			error : function() {
				bootbox.alert("操作系统选择存在问题！");
			}
		});
}

/**
 * 设置操作系统下拉框选中值
 * @param param
 */
function setOperSystemValue(param) {
	var data = JSON.parse(param);
	creatVmObj.imageUuid=data.uuid;
	$("#operSystem").text(data.name);
	//$("#numAlert").html("<font color='red'>虚拟机创建数量最多为10台</font>");
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
				creatVmNumber=data.result;
				if (!isEmpty($("#creatNum").val()) && ($("#creatNum").val()) > creatVmNumber){
				$("#numAlert").html("<font color='red'>此镜像最多创建"+data.result+"台</font>");
				   setCreatVmBtnStatue();
				}
				if (!isEmpty($("#creatNum").val()) && ($("#creatNum").val()) <= creatVmNumber){
					$("#numAlert").html("");
					   setCreatVmBtnStatue();
					}	
			}
			else{
				$("#numAlert").html("<font color='red'>系统资源不够，无法再创建！</font>");
			}
		},
		error : function() {
			bootbox.alert("操作系统选择存在问题！");
		}
	});
}

/**
 * 设置存储容量下拉框选中值
 * @param param
 */
function setStorCapacityValue(param) {
	var data = JSON.parse(param);	
	$("#storCapacity").text(data.name);
}