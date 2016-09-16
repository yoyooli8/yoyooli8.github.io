angular.module('testApp',[])
.controller('navController',['$scope',function($scope){
    $scope.navigations = [
        {lable:'模板',navId:1,pnavId:0,href:''},
        {lable:'手机',navId:2,pnavId:1,href:''},
        {lable:'品牌',navId:3,pnavId:1,href:''}
        ];    
}])
.controller('tableController',['$scope',function($scope){
    $scope.tableHeaders = [
        {name:'姓名',index:1},
        {name:'地址',index:2},
        {name:'角色',index:3},
        {name:'年龄',index:4}
        ];
    $scope.tablePages = [
        {name:'← 前一页',pageNum:-1,disabled:1},
        {name:'1',pageNum:1,active:1},
        {name:'2',pageNum:2},
        {name:'3',pageNum:3},
        {name:'4',pageNum:4},
        {name:'5',pageNum:5},
        {name:'6',pageNum:6},
        {name:'下一页 → ',pageNum:-1,disabled:1},
    ];    
    $scope.tableRows = [
        {index:1,rowColumns:[
            {name:'小王',  index:1},
            {name:'上海',  index:2},
            {name:'管理员',index:3},
            {name:20,      index:4}
        ]},
        {index:2,rowColumns:[
            {name:'小李',  index:1},
            {name:'广州',  index:2},
            {name:'快递员',index:3},
            {name:20,      index:4}
        ]},
        {index:3,rowColumns:[
            {name:'小乔',  index:1},
            {name:'昆山',  index:2},
            {name:'美甲师',index:3},
            {name:20,      index:4}
        ]},
        {index:4,rowColumns:[
            {name:'小张',  index:1},
            {name:'杭州',  index:2},
            {name:'程序员',index:3},
            {name:20,      index:4}
        ]},
        {index:5,rowColumns:[
            {name:'小赵',  index:1},
            {name:'深圳',  index:2},
            {name:'飞行员',index:3},
            {name:20,      index:4}
        ]}
    ];
}])
;