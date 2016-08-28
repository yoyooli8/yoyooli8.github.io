<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>登录</title>
    <style type="text/css">
        body{
            padding: 0 0;
            margin:  0 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-1"><div class="btn btn-xs btn-primary">测试</div></div>
            <div class="col-sm-2">2</div>
            <div class="col-sm-3">3</div>
            <div class="col-sm-4">4</div>
            <div class="col-sm-2">2</div>
        </div>
    </div>
    <!-- 复杂对象参数传递给springMvc contentType：需要用application/json，参数要使用json字符串，而不是key,value字符串.-->
    <!-- controller层接受要用@RequestBody，例如：-->
    <!-- @ResponseBody-->
    <!-- public JsonResult test(@RequestBody InputObject<User> inobj)-->
    <script type="text/javascript" src="assets/scripts/login/login.js"></script>
</body>
</html>