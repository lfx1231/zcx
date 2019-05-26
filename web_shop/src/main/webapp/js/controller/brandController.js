//控制层
app.controller('brandController' ,function($scope,$location,brandService){

// 保存品牌的方法:
    $scope.save = function(){

        brandService.save($scope.entity).success(function(response){
            // {success:true,message:xxx}
            // 判断保存是否成功:
            if(response.success==true){
                // 保存成功
                alert(response.message);
                $location.href="home.html";
                // $scope.reloadList();
            }else{
                // 保存失败
                alert(response.message);
            }
        });
    }
});	
