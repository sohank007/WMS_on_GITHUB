angular.module('WMS').controller('InventoryDetailsCtrl',['$scope','$http','$window',function($scope,$http,$window){
	$http.get('/WarehouseMgmt/warehouse/warehouseDetailsJson')
	.then(function(response){
		$scope.warehouses=response.data;
	})

$http.get('/WarehouseMgmt/inventory/inventoryDetailsJson')
  .then(function(response){
  $scope.inventoryDetails=response.data; 

  });
	
	
$scope.sorting=true;
$scope.sortingMaterial=function(vari){
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

						
}]);

