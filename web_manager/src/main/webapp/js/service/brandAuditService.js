// 定义服务层:
app.service("brandAuditService", function ($http) {
    /**
     * 从redis中获取
     */
    this.findAll = function () {
        return $http.get("../brandAudit/findAll.do");
    }
    /**
     * 进行分页查询
     * @param page
     * @param rows
     */
    this.findByPage = function (page, rows) {
        return $http.get("../brandAudit/findPage.do?page=" + page + "&rows=" + rows);
    }
    /**
     * 审核通过的话，就保存到数据库
     * @param entity
     * @returns {*}
     */
    this.save = function (entity) {
        return $http.post("../brandAudit/add.do", entity);
    }
    /**
     * 审核不通过就从redis中删除
     * @param ids
     */
    this.dele = function (ids) {
        return $http.get("../brandAudit/delete.do?ids=" + ids);
    }


    // this.savetosql = function(entity){
    //     return $http.post('../brandAudit/savetosql.do',entity);
    //     $scope.findAll().reload();
    // }

    this.updateStatus = function (ids) {
        return $http.get('../brandAudit/savetosql.do?ids='+ids );
        // $scope.findAll().reload();
    }

    // this.search = function(page,rows,searchEntity){
    // 	return $http.post("../brand/search.do?page="+page+"&rows="+rows,searchEntity);
    // }
    //
    // this.selectOptionList = function(){
    // 	return $http.get("../brand/selectOptionList.do");
    // }
    //
    // this.update=function(entity){
    // 	return $http.post("../brand/update.do",entity);
    // }
    //
    // this.findById=function(id){
    // 	return $http.get("../brand/findOne.do?id="+id);
    // }
});
