$(function() {});

//判断对象是否为空的方法
function isEmpty(data) {
	if (data != null && data != undefined && data != "") {
		return false;
	}

	return true;
}

//请求模拟休眠方法
function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
			return;
	}
}

