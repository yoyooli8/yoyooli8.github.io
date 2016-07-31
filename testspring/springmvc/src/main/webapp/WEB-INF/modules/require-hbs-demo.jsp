<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>模块化js测试</title>
</head>
<body>
    <div class="container" id="demo-app-container"></div>
    
    <script type="text/javascript">
        require(["login/testmodule"],function () {
        	console.log('-aaaa---');
	    	console.log($);
	    });
    </script>
</body>
</html>