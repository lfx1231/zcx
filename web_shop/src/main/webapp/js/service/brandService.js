//服务层
app.service('brandService',function($http){
	    	

    this.save = function(entity){
        return $http.post("../brand/tianjia.do",entity);
    }

});
