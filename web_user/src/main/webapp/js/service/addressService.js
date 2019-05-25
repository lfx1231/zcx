//服务层
app.service('addressService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../address/findAll.do');
	}

	//查询实体
	this.findById=function(id){
		return $http.get('../address/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../address/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../address/update.do',entity );
	}
	//通过id删除
	this.dele=function(id){
		return $http.get('../address/delete.do?id='+id);
	}
	//查询所有省份
	this.selectProvincesList=function () {
		return $http.get('../address/selectProvincesList.do?');
    }
    //查询省份下的所有城市列表
    this.findByprovinceid=function (provincesid) {
		return $http.get('../address/findByprovinceid.do?provincesid='+provincesid);
    }
    //查询城市下的所有区域列表：
    this.findBycityid=function (citiesid) {
        return $http.get('../address/findBycityid.do?citiesid='+citiesid);
    }

});
