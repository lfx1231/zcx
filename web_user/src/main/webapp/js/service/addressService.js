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

	
});
