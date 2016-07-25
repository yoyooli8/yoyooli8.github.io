define(["jquery","hbs!template/one"],function($,tmplOne){
	console.log('---test----');
	console.log(tmplOne);
	var html = tmplOne({adjective : 'favorite',listofstuff : ['bananas', 'democracy', 'expired milk']});
	$('#demo-app-container').html(html);
	return $;
});