var WMS=angular.module('WMS');
WMS.controller('ContractorDetailsCtrl',['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog){
    		$http.get('/WarehouseMgmt/contractor/contractorDetailsJson')
            .then(function(response){
            $scope.contractor_details=response.data; 
            }); 
    
    $scope.addCon=function(ev){
    	$mdDialog.show({
   		 targetEvent: ev,
   		 templateUrl: '/WarehouseMgmt/pages/addContractor.jsp',
   		 controller:'AddContractorCtrl',
   		 parent: angular.element(document.body)
   	});
    };
    

	
    $scope.editCon=function(ev,contractor){
    	
    	AllFactory.setContractor(contractor);
    	$mdDialog.show({
      		 targetEvent: ev,
      		 templateUrl: '/WarehouseMgmt/pages/editContractor.jsp',
      		 controller:'EditContractorCtrl',
      		 parent: angular.element(document.body)
      	});
    	
 /*   	$window.location.href = '#/EditContractor';*/
    	
    };
	
    $scope.deleteCon=function(contractor){
    	$http({
            method  : 'POST',
            url     : '/WarehouseMgmt/contractor/delete/ctr',
            data    : JSON.stringify(contractor),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(){
            $window.location.reload();
           });
    	
    };
    
}]);


WMS.controller('AddContractorCtrl',['$scope','$http','$window','$mdDialog',function($scope,$http,$window,$mdDialog){
    $scope.contractor={};
	$scope.add_contr=function(){
		//debugger;
	$scope.NewCtr = JSON.stringify($scope.contractor);
	console.log($scope.NewCtr);
    $http({
              method  : 'POST',
              url     : '/WarehouseMgmt/contractor/addCtr',
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

WMS.controller("EditContractorCtrl",['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog) {
	var contractor=AllFactory.getContractor();
	$scope.contractor=contractor;
	$scope.edit_contr=function(){
		console.log( JSON.stringify($scope.contractor));	
	    $http({
            method  : 'POST',
            url     : '/WarehouseMgmt/contractor/editCtr',
            data    : JSON.stringify($scope.contractor),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(){
        	   $window.location.reload();
           });
		
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	}
}]);
