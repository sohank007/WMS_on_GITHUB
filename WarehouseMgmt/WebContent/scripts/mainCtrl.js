var wms=angular.module('WMS');
wms.controller('mainCtrl',['$http','$scope','$location','$mdSidenav',"$mdColorPalette",
                           function($http,$scope,$location,$mdSidenav,$mdColorPalette){
	$scope.lockLeft=true;
/*	$scope.variable={
			"height":"100%"
	}*/
	/*$scope.variable=false;*/
	$scope.setRoute=function(route){
		$location.path(route);
		}
	
	
	
	$http.get('/WarehouseMgmt/roleMenu/roleMenuByOrgIdRoleCode/CTR')
    .then(function(response){
    $scope.rolemenus=response.data;
    console.log($scope.rolemenus); 
    
    });
	
	
	
	
/*
        $scope.openLeftMenu = function() {
        $mdSidenav('left').toggle();

        };*/
}])

