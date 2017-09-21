<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body style="padding-top: 30%">
	<!-- 修改存储大小模型 -->
	<div class="modal" id="updateSgNumModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4>修改存储大小</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label">当前存储大小:</label>
							<div class="col-sm-6">
								<input type="text" readonly="true" class="form-control"
									id="currentSgNum">
							</div>
						</div>
						<div class="form-group">
							<label for="newSgNum" class="col-sm-3 control-label">修改存储大小:</label>
							<div class="col-sm-6">
								<div class="input-group">
									<input type="text" class="form-control" id="newSgNum"
										placeholder="请输入存储大小">
									<div class="input-group-addon"
										style="background-color: #D3F8F8">G</div>
								</div>
							</div>
							<div style="margin-top: 5px" id="checkSgNumAlert" name="checkSgNumAlert"></div>
						</div>
					</form>
				</div>
				<div style="background: #F0F7FC" class="modal-footer">					
					<button type="button" class="btn btn-primary"
						style="margin-left: 20px" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="updateSgNum">修改</button>
				</div>
			</div>
			<input id="userID" name="userID" hidden ="hidden"/>
		</div>
	</div>

</body>
</html>