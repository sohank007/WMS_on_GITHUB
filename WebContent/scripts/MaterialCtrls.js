var WMS=angular.module('WMS')

WMS.controller('MaterialDetailsCtrl',['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog){		
	
	$http.get('/WarehouseMgmt/material/materialDetailsJson')
            .then(function(response){
            $scope.material_details=response.data; 
            }); 
    		
    	    $scope.edit_material=function(ev,material){
    	    	AllFactory.setMaterial(material);
    	    	$mdDialog.show({
    	    		targetEvent: ev,
    	    		templateUrl: "/WarehouseMgmt/pages/editMaterial.jsp",
    	    		controller: 'EditMaterialCtrl',
   	    		 	parent: angular.element(document.body)
   	    		 
    	    	});
    	    	
    	    };
    		
    	    $scope.delete_material=function(material){
    	    	$http({
    	            method  : 'POST',
    	            url     : '/WarehouseMgmt/material/delete/mtr',
    	            data    : JSON.stringify(material),
    	            headers : {'Content-Type': 'application/json; charset=utf-8'}
    	            
    	           }).then(function(){
    	            $window.location.reload();
    	           });
    	    	
    	    }; 
    	    
    	    $scope.addMtr=function(ev){
    	    	$mdDialog.show({
    	    		 targetEvent: ev,
    	    		 templateUrl: '/WarehouseMgmt/pages/addMaterial.jsp',
    	    		 controller:'AddMaterialCtrl',
    	    		 parent: angular.element(document.body),
    	    		
    	    	});
    	    };
    		
}]);


WMS.controller('AddMaterialCtrl',['$scope','$http','$window','$mdDialog',function($scope,$http,$window,$mdDialog){
    $scope.material={};
    $scope.units = ('Meters Kilometers Liters Dozons Kilograms').split(' ').map(function(units) 
    	    {
    	        return {unitOfMsmt: units};
    	    });
	$scope.submit=function(){
		//debugger;
	$scope.NewMtr = JSON.stringify($scope.material);
/*	console.log($scope.material);*/
    $http({
              method  : 'POST',
              url     : '/WarehouseMgmt/material/add_mtr',
              data    : $scope.NewMtr,
              headers : {'Content-Type': 'application/json; charset=utf-8'}
              
             }).then(function(){
            	 alert("material inserted successfully")
            	 $window.location.reload();
             });
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	};
}]);

WMS.controller("EditMaterialCtrl",['$scope','$http','$window','AllFactory','$mdDialog', function($scope,$http,$window,AllFactory,$mdDialog) {
	//debugger;
	var material=AllFactory.getMaterial();
    $scope.units = ('Meters Kilometers Liters Dozons Kilograms').split(' ').map(function(units) 
    	    {
    	        return {unitOfMsmt: units};
    	    });
    //console.log(material);
	$scope.material=material;
	$scope.submit=function(){
/*		console.log( JSON.stringify($scope.material));	*/
	    $http({
            method  : 'POST',
            url     : '/WarehouseMgmt/material/editMtr',
            data    : JSON.stringify($scope.material),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(){
        	   $window.location.reload();
           });
		
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	};
}]);