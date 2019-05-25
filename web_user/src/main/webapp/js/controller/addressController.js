 //控制层 
app.controller('addressController' ,function($scope,$controller,$location,addressService){

    $controller('baseController',{$scope:$scope});//继承
	//查询所有
	$scope.findAll=function () {
		addressService.findAll().success(
			function(response){
           		 $scope.list=response;
			}
		);
    }

    //保存
    $scope.save=function(){
        // 区分是保存还是修改
        var object;
        if($scope.entity.id != null){
            // 更新
            object = addressService.update($scope.entity);
        }else{
            // 保存
            object = addressService.add($scope.entity);
        }
        object.success(function(response){
            // 判断保存是否成功:
            if(response.success==true){
                // 保存成功
                alert(response.message);
                location.reload();
            }else{
                // 保存失败
                alert(response.message);
            }
        });
    }

    //查询一个
    $scope.findById=function () {
        addressService.findById(id).success(
            function (response) {
                $scope.entity= response;
            }
        )
    }

    //删除
	$scope.delete=function () {
		addressService.delete(id).success(
			function(response){
                // 判断删除是否成功:
                if(response.success==true){
                    // 删除成功
                    alert(response.message);
                    location.reload();
                }else{
                    // 删除失败
                    alert(response.message);
                }
			}
		)
    }


    //查询所有省份集合
    $scope.selectProvincesList=function () {
        addressService.selectProvincesList().success(
            function (response) {
                $scope.provincesList=response;
            }
        )
    }

    // 查询二级城市列表:
    $scope.$watch("entity.provincesid",function (newValue, oldValue) {
        addressService.findByprovinceid(newValue).success(function(response){
            $scope.citiesList = response;
        });
    })

    //查询三级城市列表：
    $scope.$watch("entity.citiesid",function (newValue, oldValue) {
        addressService.findBycityid(newValue).success(function(response){
            $scope.areasList = response;
        });
    })

});	
