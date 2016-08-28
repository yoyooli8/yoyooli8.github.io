require(["require","jquery","hbs!template/one"],function(){
	var $   = require('jquery');
	var tpl = require('hbs!template/one');
	
	console.log(111111);
	$('#loginBtn').click(function(){
        var username = $('input[name="username"]').val();
        var passwd   = $('input[name="passwd"]').val();
        console.log(username+'---'+passwd);
        $.ajax({
            url:'user/login',
            type:'POST',
            data:'username='+username+'&passwd='+passwd,
            dataType:'json',
            success:function(data){
                console.log(data.rtnCode);
                if(data.rtnCode ==1){
                    console.log('success');
                    window.location = 'go2Page/loginSuc';
                    //window.location = "index";
                }else{
                    $('.alert').removeClass('hide');    
                }
            }
        });
    });
});