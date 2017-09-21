<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.warning{
	    border: 1px red solid;
	    color: red;
	}
	.success{
		border:1px #37CC70 solid;
		color:black;
	}
</style>
</head>
<body>
	<!-- 模态框示例（Modal） -->
	<form method="post" action="" class="form-horizontal" role="form"
		id="form_data" onsubmit="return submit_form()"
		style="margin: 20px;">
		<div class="modal fade" id="creatComputeModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel" >电脑基本信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">												
							<div class="form-group">
								<label for="calPerformance" class="col-sm-3 control-label">计算性能：</label>
								<div class="col-sm-5">
									<div class="dropdown">
										<button class="btn btn-default dropdown-toggle"
											type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
											<span id="calPerformance"></span>
											<span class="caret"> </span>
										</button>
										
										<ul class="dropdown-menu" id="calPerf" role="menu" aria-labelledby="calPerformance"></ul>
									</div>
								</div>
							</div>												
							<div class="form-group">
								<label for="operSystem" class="col-sm-3 control-label">操作系统：</label>
								<div class="col-sm-5">													
									<div class="dropdown">
										<button class="btn btn-default dropdown-toggle"
											type="button" data-toggle="dropdown">
											<span id="operSystem"></span>
											<span class="caret"> </span>
										</button>
										<ul class="dropdown-menu" id="operSys" role="menu" aria-labelledby="operSystem"></ul>
									</div>
								</div>
							</div>												
							<div class="form-group" id="storCapacityGroup">
								<label for="storCapacity" class="col-sm-3 control-label">存储容量：</label>
								<div class="col-sm-5">
									<div class="dropdown">
										<button class="btn btn-default dropdown-toggle"
											type="button" id="storCapacity" data-toggle="dropdown"><span class="caret"></span>
										</button>
										<ul class="dropdown-menu" id="storCap" role="menu" aria-labelledby="storCapacity"></ul>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="cpName" class="col-sm-3 control-label">电脑用户名：</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" name="cpName"
										value="" id="cpName" placeholder="电脑用户名">
								</div>
								<div style="margin-top:5px" id="cpAlert" name="cpAlert"></div>
							</div>
							<div class="form-group">
								<label for="creatNum" class="col-sm-3 control-label">创建数量：</label>
								<div class="col-sm-5">
									<input type="number" class="form-control" name="creatNum"
										value="" id="creatNum" placeholder="创建数量">
								</div>
								<div style="margin-top:5px" id="numAlert" name="numAlert"></div>
							</div>
						</form>
					</div>
					<div class="modal-footer" style="background:#F0F7FC">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" id="creatVmSumbit" class="btn btn-primary">提交</button>
						<span id="tip"> </span>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
</body>
</html>