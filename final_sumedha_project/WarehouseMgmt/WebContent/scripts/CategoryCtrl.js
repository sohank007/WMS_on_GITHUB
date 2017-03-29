var wms=angular.module("WMS");
wms.controller('CategoryDetailsCtrl',['$scope','$http','$window','AllFactory','$mdDialog',
                                      function($scope,$http,$window,AllFactory,$mdDialog){		
	var orgId= $window.localStorage['orgId'];	
	$http.get('/WarehouseMgmt/category/categoryByOrgId/'+orgId)
            .then(function(response){
            $scope.category_details=response.data; 
            }); 
    		
    	    $scope.edit_category=function(ev,category){
    	    	AllFactory.setCategory(category);
    	    	$mdDialog.show({
    	    		targetEvent: ev,
    	    		templateUrl: "/WarehouseMgmt/pages/editCategory.jsp",
    	    		controller: 'EditCategoryCtrl',
   	    		 	parent: angular.element(document.body)
   	    		 
    	    	});
    	    	
    	    };
       	 // delete category remains   
    	    $scope.delete_category=function(category){
    	    	$http({
    	            method  : 'POST',
    	            url     : '/WarehouseMgmt/category/delete/ctgry',
    	            data    : JSON.stringify(category),
    	            headers : {'Content-Type': 'application/json; charset=utf-8'}
    	            
    	           }).then(function(){
    	            $window.location.reload();
    	           });
    	    	
    	    }; 
    	 // add category remains   
    	    $scope.addCategory=function(ev){
    	    	$mdDialog.show({
    	    		 targetEvent: ev,
    	    		 templateUrl: '/WarehouseMgmt/pages/addCategory.jsp',
    	    		 controller:'AddCategoryCtrl',
    	    		 parent: angular.element(document.body),
    	    		
    	    	});
    	    };
    	    
    	    $scope.sorting=true;
    		vari="categoryId";
    		$scope.reverse=true;
    	    $scope.sortingCategory=function(vari){
    	    	$scope.variable=vari;
    	    	if($scope.sorting==true){//true for up arrow
    	    		$scope.sorting=false;
    	    		$scope.reverse=true;

    	    	}
    	    	else{//false for down arrow
    	    		$scope.sorting=true;
    	    		$scope.reverse=false;
    	    	}
    	    }

    		
}])



WMS.controller('AddCategoryCtrl',['$scope','$http','$window','$mdDialog','AllFactory',function($scope,$http,$window,$mdDialog,AllFactory){
    $scope.category={};
    $scope.orgId =  $window.localStorage['orgId'];
    $scope.category.organisationId =  $window.localStorage['orgId'];
	$scope.submit=function(){
		//debugger;
	$scope.NewCtgry = JSON.stringify($scope.category);
	console.log($scope.NewCtgry);
/*	console.log($scope.material);*/
    $http({
              method  : 'POST',
              url     : '/WarehouseMgmt/category/add_ctgry',
              data    : $scope.NewCtgry,
              headers : {'Content-Type': 'application/json; charset=utf-8'}
              
             }).then(function(){
            	 alert("category inserted successfully")
            	 $window.location.reload();
             });
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	};
}]);

WMS.controller("EditCategoryCtrl",['$scope','$http','$window','AllFactory','$mdDialog', function($scope,$http,$window,AllFactory,$mdDialog) {
	//debugger;
	var category=AllFactory.getCategory();
/*    $scope.units = ('Meters Kilometers Liters Dozons Kilograms').split(' ').map(function(units) 
    	    {
    	        return {unitOfMsmt: units};
    	    });*/
    console.log(category);
	$scope.category=category;
	$scope.submit=function(){
/*		console.log( JSON.stringify($scope.material));	*/
	    $http({
            method  : 'POST',
            url     : '/WarehouseMgmt/category/editCtgry',
            data    : JSON.stringify($scope.category),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(){
        	   $window.location.reload();
           });
		
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	};
}]);
