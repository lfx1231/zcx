// 定义控制器:
app.controller("brandAuditController", function ($scope, $controller, $http, brandAuditService) {
    // AngularJS中的继承:伪继承
    $controller('baseController', {$scope: $scope});

    // 查询所有的品牌列表的方法:
    $scope.findAll = function () {
        // 向后台发送请求:
        brandAuditService.findAll().success(function (response) {
            $scope.list = response;
        });
    }

    // 分页查询
    $scope.findByPage = function (page, rows) {
        // 向后台发送请求获取数据:
        brandAuditService.findByPage(page, rows).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        });
    }

    // 保存品牌的方法:
    $scope.save = function () {
        // 区分是保存还是修改
        var object;

        // 保存
        object = brandAuditService.save($scope.entity);

        object.success(function (response) {
            // {success:true,message:xxx}
            // 判断保存是否成功:
            if (response.success == true) {
                // 保存成功
                alert(response.message);
                $scope.reloadList();
            } else {
                // 保存失败
                alert(response.message);
            }
        });

    }


    // 删除品牌:
    $scope.dele = function () {
        brandAuditService.dele($scope.selectIds).success(function (response) {
            // 判断保存是否成功:
            if (response.success == true) {
                // 保存成功
                // alert(response.message);
                $scope.reloadList();
                $scope.selectIds = [];
            } else {
                // 保存失败
                alert(response.message);
            }
        });
    }

// 审核的方法:
    $scope.updateStatus = function () {
        brandAuditService.updateStatus($scope.selectIds).success(function (response) {

            if (response.success) {
                alert(response.message);
                // $scope.reloadList();//刷新列表
                location.href = "brandAudit.html";
                $scope.selectIds = [];
            } else {
                alert(response.message);
            }
        });
    }

});
