var WMS=angular.module('WMS');
WMS.controller('WarehouseDetailsCtrl',['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog){
    		$http.get('/WarehouseMgmt/warehouse/warehouseDetailsJson')
            .then(function(response){
            $scope.warehouses=response.data; 
            }); 

    	    $scope.addWare=function(ev){

    	    	$mdDialog.show({
   	    		 targetEvent: ev,
   	    		 templateUrl: '/WarehouseMgmt/pages/addWarehouse.jsp',
   	    		 controller:'AddWarehouseCtrl',
   	    		 parent: angular.element(document.body)
   	    	});
    	    	
    	    };
    		
    		
    	    $scope.editWare=function(ev,warehouse){
    	    	AllFactory.setWarehouse(warehouse);
    	    	$mdDialog.show({
   	    		 targetEvent: ev,
   	    		 templateUrl: '/WarehouseMgmt/pages/editWarehouse.jsp',
   	    		 controller:'EditWarehouseCtrl',
   	    		 parent: angular.element(document.body)
   	    	});
    	    	
    	    };
    		
    	    $scope.delete_wrh=function(warehouse){
    	    	$http({
    	            method  : 'POST',
    	            url     : '/WarehouseMgmt/warehouse/delete/wre',
    	            data    : JSON.stringify(warehouse),
    	            headers : {'Content-Type': 'application/json; charset=utf-8'}
    	            
    	           }).then(function(){
    	            $window.location.reload();
    	           });
    	    	
    	    };
}]);


WMS.controller('AddWarehouseCtrl',['$scope','$http','$window','$mdDialog',function($scope,$http,$window,$mdDialog){
    $scope.warehouse={};
	$scope.add_wrh=function(){
		//debugger;
	$scope.NewCtr = JSON.stringify($scope.warehouse);
	console.log($scope.NewCtr);
    $http({
              method  : 'POST',
              url     : '/WarehouseMgmt/warehouse/add_wrh',
              data    : $scope.NewCtr,
              headers : {'Content-Type': 'application/json; charset=utf-8'}
              
             }).then(function(){
            	 $window.location.reload();
             });
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	}
}]);

WMS.controller("EditWarehouseCtrl",['$scope','$http','$window','AllFactory','$mdDialog', function($scope,$http,$window,AllFactory,$mdDialog) {
	var warehouse=AllFactory.getWarehouse();
	$scope.warehouse=warehouse;
	$scope.add_wrh=function(){
		console.log( JSON.stringify($scope.warehouse));	
	    $http({
            method  : 'POST',
            url     : '/WarehouseMgmt/warehouse/editWre',
            data    : JSON.stringify($scope.warehouse),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(){
        	   $window.location.reload();
           });
		
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	}
}]);
