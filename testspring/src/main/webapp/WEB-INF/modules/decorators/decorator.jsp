<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html;charset=utf-8">
        <title>
            <sitemesh:write property='title' />
        </title>
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <script type="text/javascript" src="assets/scripts/require.2.2.0.js"></script>
        <script type="text/javascript" src="assets/scripts/main.js"></script>
        <sitemesh:write property='head' />
    </head>
    <body>
        <sitemesh:write property='body' />
    </body>
</html>