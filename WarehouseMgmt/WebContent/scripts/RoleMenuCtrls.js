var WMS=angular.module('WMS');
var controller=WMS.controller('RoleMenuCtrl',['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog){
	$scope.organisationId = AllFactory.getOrganisationId();
	console.log($scope.organisationId);
	$scope.roleCode = AllFactory.getRole();
	console.log($scope.roleCode);
    		$http.get('/WarehouseMgmt/roleMenu/roleMenuByOrgIdRoleCode/' + $scope.roleCode )
            .then(function(response){
            $scope.rolemenus=response.data;
            console.log($scope.rolemenus);
            
            });
            
      
    	    $scope.editMenu=function(ev,role){
    	    	AllFactory.setRole(role);
    	    	$mdDialog.show({
    	    		targetEvent: ev,
    	    		templateUrl: "/WarehouseMgmt/pages/editRole.jsp",
    	    		controller: 'EditRoleCtrl',
   	    		 	parent: angular.element(document.body)
   	    		 
    	    	});
    	    	
    	    };
    		
    	    $scope.deleteMenu=function(role){
    	    	console.log(JSON.stringify(role));
    	    	$http({
    	            method  : 'POST',
    	            url     : '/WarehouseMgmt/role/delete/role',
    	            data    : JSON.stringify(role),
    	            headers : {'Content-Type': 'application/json; charset=utf-8'}
    	            
  	           }).then(function(){
  	            $window.location.reload();
   	           });
    	    	
    	    }; 
    	    
    	    $scope.addRole=function(ev){
    	    	$mdDialog.show({
    	    		 targetEvent: ev,
    	    		 templateUrl: '/WarehouseMgmt/pages/addRole.jsp',
    	    		 controller:'AddRoleCtrl',
    	    		 parent: angular.element(document.body),
    	    		
    	    	});
    	    };
    		
}]);

    		
    		/*WMS.controller('AddRoleCtrl',['$scope','$http','$window','$mdDialog',function($scope,$http,$window,$mdDialog){
    		    $scope.menu={};
    			$scope.submit=function(){
    				//debugger;
    			$scope.NewRole = JSON.stringify($scope.role);
    			console.log($scope.role);
    		    $http({
    		              method  : 'POST',
    		              url     : '/WarehouseMgmt/menu/add_role',
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
    		
    		WMS.controller("EditRoleCtrl",['$scope','$http','$window','AllFactory','$mdDialog', function($scope,$http,$window,AllFactory,$mdDialog) {
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
