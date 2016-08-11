<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
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
    <script type="text/javascript">
        class User{
        	constructor(userId,userName,age,userpwd){
        		this.userId  = userId;
        		this.userpwd = userpwd||'';
        		this.age     = age||0;
        		this.userName= userName||'';
        		this.roles   = [];
        	}
        	setRoles(role){
        		this.roles[this.roles.length] = role;
        	}
        }
        class Role{
        	constructor(roleName,roleId){
        		this.roleName = roleName;
        		this.roleId   = roleId;
        	}
        }
        require(["jquery","bootstrap"],function($){
        	$('.container').append('<div class="row"><div class="col-sm-5">5</div><div class="col-sm-7">7</div></div>');
        	$('.container .row > div').css({borderColor:'gray',borderStyle:'solid',borderWidth:'1px'});
        	var xiaoming = new User('1','xiaoming',19,'abcd');
        	var wangwu   = new User('2','wangwu',21,'qweasd');
        	var role1    = new Role('role1',1);
        	var role2    = new Role('role2',2);
        	xiaoming.setRoles(role1);
        	wangwu.setRoles(role1);
        	wangwu.setRoles(role2);
        	
        	var param = {params:[xiaoming,wangwu]};
        	
        	console.log(param);
        	$('.btn').on('click',function(){
        		$.ajax({
        			url:'test',
        			data:JSON.stringify(param),
        			type:'post',
        			dataType:'json',
        			contentType:'application/json',
        			success:function(result){
        				console.log(result);
        			}
        		});
        	});
        });
    </script>
</body>
</html>