var WMS=angular.module('WMS');
WMS.controller('WarehouseDetailsCtrl',['$scope','$http','$window','AllFactory','$mdDialog',
                                       function($scope,$http,$window,AllFactory,$mdDialog){
	var orgId= $window.localStorage['orgId'];
    		$http.get('/WarehouseMgmt/warehouse/warehouseByOrgId/'+orgId)
            .then(function(response){
            $scope.warehouses=response.data; 
            }); 

    	    $scope.addWare=function(ev){

    	    	$mdDialog.show({
   	    		 targetEvent: ev,
   	    		 templateUrl: '/WarehouseMgmt/pages/addWarehouse.jsp',
   	    		 controller:'AddWarehouseCtrl',
   	    		 parent: angular.element(document.body)
   	    	});
    	    	
    	    };
    		
    		
    	    $scope.editWare=function(ev,warehouse){
    	    	AllFactory.setWarehouse(warehouse);
    	    	$mdDialog.show({
   	    		 targetEvent: ev,
   	    		 templateUrl: '/WarehouseMgmt/pages/editWarehouse.jsp',
   	    		 controller:'EditWarehouseCtrl',
   	    		 parent: angular.element(document.body)
   	    	});
    	    	
    	    };
    		
    	    $scope.delete_wrh=function(warehouse){
    	    	$http({
    	            method  : 'POST',
    	            url     : '/WarehouseMgmt/warehouse/delete/wre',
    	            data    : JSON.stringify(warehouse),
    	            headers : {'Content-Type': 'application/json; charset=utf-8'}
    	            
    	           }).then(function(response){
    	        	   $mdDialog.show({
                		   template: "<md-dialog id='alertAddMaterial'>" +   
                		  
                		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your warehouse with id " 
                		   +response.data +" has been deleted successfully.</label> </div>"+
                		   "<div style='margin: 10px 10px;border-radius:4px;'" +
                		   " layout='row' layout-align='center center'>" +
                		   "    <md-button class='md-raised' ng-click='closeDialog()' >" +
                		   '      Ok' +
                		   '    </md-button>' +
                		   '  </div>' +
                		   '</md-dialog>',
                		   controller: function($scope, $mdDialog,$window){
                		      $scope.closeDialog = function(){
                		       $mdDialog.hide();
                		       $window.location.reload();
                		     };
                		   },
                		  
                		});
    	           });
    	    	
    	    };
    	    
    	    $scope.sorting=true;
    		vari="warehouseId";
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


WMS.controller('AddWarehouseCtrl',['$scope','$http','$window','$mdDialog','AllFactory',
                                   function($scope,$http,$window,$mdDialog,AllFactory){
	var orgId= $window.localStorage['orgId'];	
    $scope.warehouse={};
	$scope.regions=['MAHARASHTRA','UTTAR PRADESH','TAMILNADU','GUJARAT','WEST BENGAL',
	                'KARNATAKA','RAJASTHAN','ANDHRA PRADESH','UTTARPRADESH','DELHI','BANGLORE',
	                'PUNJAB','UTTARAKHAND'];
	$scope.add_wrh=function(){
		//debugger;

	$scope.warehouse.organisationId=orgId;	
	$scope.NewCtr = JSON.stringify($scope.warehouse);
	console.log($scope.NewCtr);
    $http({
              method  : 'POST',
              url     : '/WarehouseMgmt/warehouse/add_wrh',
              data    : $scope.NewCtr,
              headers : {'Content-Type': 'application/json; charset=utf-8'}
              
             }).then(function(response){
            	 $mdDialog.show({
          		   template: "<md-dialog id='alertAddMaterial'>" +   
          		  
          		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your warehouse with id " 
          		   +response.data +" has been inserted successfully.</label> </div>"+
          		   "<div style='margin: 10px 10px;border-radius:4px;'" +
          		   " layout='row' layout-align='center center'>" +
          		   "    <md-button class='md-raised' ng-click='closeDialog()' >" +
          		   '      Ok' +
          		   '    </md-button>' +
          		   '  </div>' +
          		   '</md-dialog>',
          		   controller: function($scope, $mdDialog,$window){
          		      $scope.closeDialog = function(){
          		       $mdDialog.hide();
          		       $window.location.reload();
          		     };
          		   },
          		  
          		});
             });
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	}
}]);

WMS.controller("EditWarehouseCtrl",['$scope','$http','$window','AllFactory','$mdDialog', function($scope,$http,$window,AllFactory,$mdDialog) {
	var warehouse=AllFactory.getWarehouse();	
	var orgId= $window.localStorage['orgId'];	

	$scope.warehouse=warehouse;
	$scope.regions=['MAHARASHTRA','UTTAR PRADESH','TAMILNADU','GUJARAT','WEST BENGAL',
	                'KARNATAKA','RAJASTHAN','ANDHRA PRADESH','UTTARPRADESH','DELHI','BANGLORE',
	                'PUNJAB','UTTARAKHAND'];
	$scope.add_wrh=function(){
		$scope.warehouse.organisationId=orgId;
		console.log( JSON.stringify($scope.warehouse));	
	    $http({
            method  : 'POST',
            url     : '/WarehouseMgmt/warehouse/editWre',
            data    : JSON.stringify($scope.warehouse),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(response){
        	   $mdDialog.show({
        		   template: "<md-dialog id='alertAddMaterial'>" +   
        		  
        		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your warehouse with id " 
        		   +response.data +" has been updated successfully.</label> </div>"+
        		   "<div style='margin: 10px 10px;border-radius:4px;'" +
        		   " layout='row' layout-align='center center'>" +
        		   "    <md-button class='md-raised' ng-click='closeDialog()' >" +
        		   '      Ok' +
        		   '    </md-button>' +
        		   '  </div>' +
        		   '</md-dialog>',
        		   controller: function($scope, $mdDialog,$window){
        		      $scope.closeDialog = function(){
        		       $mdDialog.hide();
        		       $window.location.reload();
        		     };
        		   },
        		  
        		});
           });
		
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	}
}]);
