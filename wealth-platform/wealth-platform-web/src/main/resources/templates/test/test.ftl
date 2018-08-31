<html>
<head>
<title>helloworld.</title>
</head>
<body>
<p>${name}</p>
<p>
<#list countries as country>
	${country_index + 1}. ${country}
</#list>
</p>
</body>
</html>