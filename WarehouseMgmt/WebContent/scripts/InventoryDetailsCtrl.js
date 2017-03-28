angular.module('WMS').controller('InventoryDetailsCtrl',['$scope','$http','$window','AllFactory',
                                                         function($scope,$http,$window,AllFactory){
/*	$scope.organizations=["ORG-11","ORG-12","ORG-13","ORG-14"];*/
	/*$scope.setOrg=function(id){
		//AllFactory.setOrganisationId(id);
		console.log(AllFactory.getOrganisationId());
		
		var orgId=AllFactory.getOrganisationId();
		$http.get('/WarehouseMgmt/warehouse/warehouseByOrgId/'+orgId)
		.then(function(response){
			$scope.warehouses=response.data;
		})

	$http.get('/WarehouseMgmt/inventory/inventoryByOrgId/'+orgId)
	  .then(function(response){
		  console.log(response.data);
	  $scope.inventoryDetails=response.data; 

	  });
		
		   $http
			.get(
					'/WarehouseMgmt/category/categoryByOrgId/' + orgId)
			.then(function(response) {
				$scope.categories = response.data;
				console.log($scope.categories);
		});
		   
			$http.get('/WarehouseMgmt/material/materialByOrgId/'+orgId)
	        .then(function successCallback(response){
	        $scope.material_details=response.data; 
	        });
		
		
		
	}*/
	

	
	var orgId=AllFactory.getOrganisationId();
	$http.get('/WarehouseMgmt/warehouse/warehouseByOrgId/'+orgId)
	.then(function(response){
		$scope.warehouses=response.data;
	})
	
		   $http
		.get(
				'/WarehouseMgmt/category/categoryByOrgId/' + orgId)
		.then(function(response) {
			$scope.categories = response.data;
			console.log($scope.categories);
	});
	   
		$http.get('/WarehouseMgmt/material/materialByOrgId/'+orgId)
        .then(function successCallback(response){
        $scope.material_details=response.data; 
        });
	

$http.get('/WarehouseMgmt/inventory/inventoryByOrgId/'+orgId)
  .then(function(response){
/*	  console.log(response.data);*/
  $scope.inventoryDetails=response.data; 

  });

	
	
$scope.sorting=true;
vari="serialId";
$scope.reverse=true;
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

