<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<% String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<base href="<%=basePath%>">
<title>俊健养生后台员工注册</title>

<link href="content/static/css/bootstrap/bootstrap.css"
	rel="stylesheet">

<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="content/static/img/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="content/static/img/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="content/static/img/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="content/static/img/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="content/static/img/favicon.png">

<script type="text/javascript"
	src="content/static/js/lib/jquery.min.js"></script>
<script type="text/javascript"
	src="content/static/js/lib/bootstrap.min.js"></script>
<script type="text/javascript" src="content/static/js/lib/scripts.js"></script>

<link href="content/static/css/datetime/jquery-clockpicker.min.css"
	rel="stylesheet">
<link href="content/static/css/datetime/jquery.datetimepicker.css"
	rel="stylesheet">
<script type="text/javascript"
	src="content/static/js/datetime/bootstrap-clockpicker.min.js"></script>
<script type="text/javascript"
	src="content/static/js/datetime/jquery.datetimepicker.js"></script>
<style>
.frred {
	color: #FF0000;
	font-size: 12px;
}

.frbule {
	color: #0066CC;
	font-size: 12px;
}

.fgren {
	color: #339933;
	font-size: 12px;
}
</style>
</head>
<body>

	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<br> <br>
				</div>
				<div class="row clearfix">
					<div class="col-md-3 column"></div>
					<div class="col-md-6 column">
						<form class="form-horizontal" action="personnel/regist"
							method="POST">
							<div class="form-group" style="text-align: center;">
								<label><font style="font-size: 40px;">用户注册</font> </label> <br>
								<br>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label"><a
									class="frred">*</a>用户名</label>
								<div class="col-sm-10">
									<input type="text" name="loginName" class="form-control"
										id="inputEmail3" onblur="checkformname()"> <span
										id="sname" class="frbule">输入至少2位用户名</span>
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label"><a
									class="frred">*</a>密码 </label>
								<div class="col-sm-10">
									<input type="password" name="password1" class="form-control"
										id="inputPassword3" onblur="checkpwd()"><span
										id="spwd" class="frbule"> 请输入至少6为密码</span>
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword4" class="col-sm-2 control-label"><a
									class="frred">*</a>确认密码</label>
								<div class="col-sm-10">
									<input type="password" name="password" class="form-control"
										id="inputPassword4" onblur="rcheckpwd()"><span
										id="srpwd" class="frbule"> 请输入重复密码</span>
								</div>
							</div>
							<div class="form-group">
								<label for="inputPhone" class="col-sm-2 control-label">姓名</label>
								<div class="col-sm-10">
									<input type="tel" id="inputPatientName" name="userName"
											class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPhone" class="col-sm-2 control-label">性别</label>
								<div class="col-sm-10">
								<input type="radio" id="inputSex" name="sex" value="Male"
											checked="checked" class="audio">男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"
											id="inputSex" name="sex" value="Female" class="audio">女
											
								</div>
							</div>
							<div class="form-group">
									<label for="inputPhone" class="col-sm-2 control-label">电话</label>
									<div class="col-sm-10">
										<input type="tel" id="inputPatientName" name="phone"
											class="form-control">
									</div>
							</div>
							<div class="form-group">
									<label for="inputPhone" class="col-sm-2 control-label">Email</label>
									<div class="col-sm-10">
										<input type="email" id="inputPatientName" name="email"
											class="form-control">
									</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10"
									style="text-align: center;">
									<button type="submit" class="btn btn-default"
										onClick="return closeAndRefresh();">注册</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-default"
										onclick="cancel()">取消</button>
								</div>
							</div>
						</form>
					</div>
					<div class="col-md-3 column"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">

		function cancel() {
			window.location.href = "login";
		}
		function checkformname() {
			var fname = document.getElementById("inputEmail3");
			var ftname = document.getElementById("sname");
			if (fname.value == ""
					|| fname.value.length<2||fname.value.length>10) {
				ftname.className = "frred";
				fname.focus();
				ftname.innerHTML = "× 请输入2-10位用户名，可包含数字、字母、下划线";
			} else {
				ftname.className = "fgren";
				ftname.innerHTML = "√用户名可用!";
			}
		}
		function checkpwd() {
			var fpwd = document.getElementById("inputPassword3");
			var ftpwd = document.getElementById("spwd");
			if (fpwd.value == "" || fpwd.value.length<6||fpwd.value.length>20) {
				ftpwd.className = "frred";
				fpwd.focus();
				ftpwd.innerHTML = "× 请输入6-20位用户密码！";
			} else {
				ftpwd.className = "fgren";
				ftpwd.innerHTML = "√密码可用！"
			}
		}
		function rcheckpwd() {
			var fpwd = document.getElementById("inputPassword3");
			var frpwd = document.getElementById("inputPassword4");
			var ftrpwd = document.getElementById("srpwd");
			if (frpwd.value == "") {
				ftrpwd.className = "frred";
				frpwd.focus();
				ftrpwd.innerHTML = "× 请输入您的重复密码！"
			} else {
				if (frpwd.value != fpwd.value) {
					ftrpwd.className = "frred";
					fpwd.focus();
					ftrpwd.innerHTML = "× 俩次密码输入不一致，请重新输入！";
				} else {
					ftrpwd.className = "fgren";
					ftrpwd.innerHTML = " √ 密码输入正确"
				}
			}
		}
	</script>

</body>
</html>
