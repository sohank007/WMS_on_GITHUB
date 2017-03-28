var WMS=angular.module('WMS')
WMS.controller('menuMasterCtrl',['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog){
	$scope.organisationId = AllFactory.getOrganisationId();
	console.log($scope.organisationId);
	
    		$http.get('/WarehouseMgmt/menuMaster/menuByOrgId/' + $scope.organisationId)
            .then(function(response){
            $scope.menumaster=response.data; 
            }); 
    		
    		
    		
    		$scope.sorting=true;
			$scope.sortingMenu=function(vari){
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
    
    $scope.addMenu=function(ev){
    	$mdDialog.show({
   		 targetEvent: ev,
   		 templateUrl: '/WarehouseMgmt/pages/addMenu.jsp',
   		 controller:'AddMenuCtrl',
   		 parent: angular.element(document.body)
   	});
    };
    

	
    $scope.editMenu=function(ev,menu){
    	
    	AllFactory.setMenu(menu);
    	$mdDialog.show({
      		 targetEvent: ev,
      		 templateUrl: '/WarehouseMgmt/pages/editMenu.jsp',
      		 controller:'EditMenuCtrl',
      		 parent: angular.element(document.body)
      	});
    	
 /*   	$window.location.href = '#/EditMenu';*/
    	
    };
	
    $scope.deleteMenu=function(menu){
    	$http({
            method  : 'POST',
            url     : '/WarehouseMgmt/menu/delete/menu',
            data    : JSON.stringify(menu),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(){
            $window.location.reload();
           });
    	
    };
    
}]);


WMS.controller('AddMenuCtrl',['$scope','$http','$window','$mdDialog',function($scope,$http,$window,$mdDialog){
    $scope.menu={};
	$scope.add_menu=function(){
		//debugger;
	$scope.NewMenu = JSON.stringify($scope.menu);
	console.log($scope.NewMenu);
    $http({
              method  : 'POST',
              url     : '/WarehouseMgmt/menu/addMenu',
              data    : $scope.NewMenu,
              headers : {'Content-Type': 'application/json; charset=utf-8'}
              
             }).then(function(){
            	 $window.location.reload();
             });
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	}
}]);

WMS.controller("EditMenuCtrl",['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog) {
	var menu=AllFactory.getMenu();
	$scope.menu=menu;
	$scope.edit_contr=function(){
		console.log( JSON.stringify($scope.menu));	
	    $http({
            method  : 'POST',
            url     : '/WarehouseMgmt/menu/editMenu',
            data    : JSON.stringify($scope.menu),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(){
        	   $window.location.reload();
           });
		
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	}
}])




