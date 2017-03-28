var WMS=angular.module('WMS')
WMS.controller('RoleMasterCtrl',['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog){
	$scope.organisationId = AllFactory.getOrganisationId();
	console.log($scope.organisationId);
	$scope.roleCode = AllFactory.getRole();
	console.log($scope.roleCode);
	debugger;
    		$http.get('/WarehouseMgmt/roleMaster/roleByOrgIdRoleCode/' + $scope.organisationId + '/'+ $scope.roleCode)
            .then(function(response){
            $scope.rolemaster=response.data;
            console.log($scope.rolemaster);
            }); 
    				
    		$scope.sorting=true;
			$scope.sortingRole=function(vari){
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
    
    $scope.addRole=function(ev){
    	$mdDialog.show({
   		 targetEvent: ev,
   		 templateUrl: '/WarehouseMgmt/pages/addRole.jsp',
   		 controller:'AddRoleCtrl',
   		 parent: angular.element(document.body)
   	});
    };
    

	
    $scope.editRole=function(ev,role){
    	
    	AllFactory.setRole(role);
    	$mdDialog.show({
      		 targetEvent: ev,
      		 templateUrl: '/WarehouseMgmt/pages/editRole.jsp',
      		 controller:'EditRoleCtrl',
      		 parent: angular.element(document.body)
      	});
    	
 /*   	$window.location.href = '#/EditRole';*/
    	
    };
	
    $scope.deleteRole=function(role){
    	$http({
            method  : 'POST',
            url     : '/WarehouseMgmt/role/delete/role',
            data    : JSON.stringify(role),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(){
            $window.location.reload();
           });
    	
    };
    
}]);

WMS.controller('AddRoleCtrl',['$scope','$http','$window','$mdDialog','AllFactory',function($scope,$http,$window,$mdDialog,AllFactory){
	debugger;
	var orgId = AllFactory.getOrganisationId();
    $scope.role={};
    $scope.role.organisationId = orgId;
    console.log($scope.role.organisationId);
    console.log($scope.role);
	$scope.addRole=function(){
		debugger;
	$scope.NewRole = JSON.stringify($scope.role);
	console.log($scope.NewRole);
    $http({
              method  : 'POST',
              url     : '/WarehouseMgmt/roleMaster/addRole',
              data    : $scope.NewRole,
              headers : {'Content-Type': 'application/json; charset=utf-8'}
              
             }).then(function(){
            	 alert("Role inserted successfully")
            	 $window.location.reload();
             });
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	};
}]); 

/*WMS.controller("EditRoleCtrl",['$scope','$http','$window','AllFactory','$mdDialog', function($scope,$http,$window,AllFactory,$mdDialog) {
	//debugger;
	var Menu=AllFactory.getRole();
   
    console.log(Role);
	$scope.role=Role;
	$scope.submit=function(){
		console.log( JSON.stringify($scope.role));	
	    $http({
            method  : 'POST',
            url     : '/WarehouseMgmt/role/editRole',
            data    : JSON.stringify($scope.role),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(){
        	   $window.location.reload();
         });
		
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	};
}]);*/

