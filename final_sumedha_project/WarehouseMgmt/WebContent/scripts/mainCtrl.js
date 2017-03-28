var wms=angular.module('WMS');
wms.controller('mainCtrl',['$http','$scope','$location','$mdSidenav',"$mdColorPalette","$window",
                           function($http,$scope,$location,$mdSidenav,$mdColorPalette,$window){
	$scope.lockLeft=true;
	  $scope.selectedIndex = null;
/*	$scope.variable={
			"height":"100%"
	}*/
	/*$scope.variable=false;*/
	$scope.setRoute=function(route,index){
		$location.path(route);
		if ($scope.selectedIndex === null) {
		      $scope.selectedIndex = index;
		    }
		    else if ($scope.selectedIndex === index) {
		      $scope.selectedIndex = null;
		    }
		    else {
		      $scope.selectedIndex = index;
		    }
		};
		
		
	var role="CTR";
	$http.get('/WarehouseMgmt/roleMenu/roleMenuByOrgIdRoleCode/'+role)
    .then(function(response){
    $scope.rolemenus=response.data;
    console.log($scope.rolemenus); 
    
    });

		$window.localStorage["role"]=role;

	
	
	
/*
        $scope.openLeftMenu = function() {
        $mdSidenav('left').toggle();

        };*/
}])

