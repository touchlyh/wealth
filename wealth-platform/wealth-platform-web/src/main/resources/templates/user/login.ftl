<html>
<head>
<title>wealth</title>
</head>
<body>
<script type="text/javascript">
function doRegister() {
 	document.getElementById("login_form").action="/api/user/register.do";
 	document.getElementById("login_form").submit();
}
function doLogin() {
	document.getElementById("login_form").action="/api/user/login.do";
	document.getElementById("login_form").submit();
}
</script>
<form id="login_form" action="" method="post" name="login_form">
<label>用户名:</label>
<input type="text" name="thirdId" value="${thirdId!}"></input>
<label>密码:</label>
<input type="password" name="password"></input>
<input type="hidden" name="next" value="${next!}"></input>
<input type="button" name="register" value="注册" onclick='doRegister()'></input>
<input type="button" name="login" value="登录" onclick='doLogin()'></input>
</form>

<div id="error">
${msg!}
${code!}
</div>
</body>
</html>