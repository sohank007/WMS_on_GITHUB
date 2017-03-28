var WMS=angular.module('WMS');
WMS.controller('ContractorDetailsCtrl',['$scope','$http','$window','AllFactory','$mdDialog',
                                        function($scope,$http,$window,AllFactory,$mdDialog){
	var orgId=AllFactory.getOrganisationId();
    		$http.get('/WarehouseMgmt/contractor/contractorByOrgId/'+orgId)
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
            
           }).then(function(response){
        	   
        	   $mdDialog.show({
        		   template: "<md-dialog id='alertAddMaterial'>" +   
        		  
        		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your contractor with id " 
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
	vari="ctrId";
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


WMS.controller('AddContractorCtrl',['$scope','$http','$window','$mdDialog','AllFactory',
                                    function($scope,$http,$window,$mdDialog,AllFactory){
	var orgId=AllFactory.getOrganisationId();
    $scope.contractor={};
	$scope.add_contr=function(){
		//debugger;
	$scope.contractor.organisationId=orgId;
	$scope.NewCtr = JSON.stringify($scope.contractor);
	console.log($scope.NewCtr);
    $http({
              method  : 'POST',
              url     : '/WarehouseMgmt/contractor/addCtr',
              data    : $scope.NewCtr,
              headers : {'Content-Type': 'application/json; charset=utf-8'}
              
             }).then(function(response){
            	 $mdDialog.show({
          		   template: "<md-dialog id='alertAddMaterial'>" +   
          		  
          		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your contractor with id " 
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
		$window.location.reload();
	}
}]);

WMS.controller("EditContractorCtrl",['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog) {
	var contractor=AllFactory.getContractor();
	var orgId=AllFactory.getOrganisationId();
	$scope.contractor=contractor;
	$scope.edit_contr=function(){
		$scope.contractor.organisationId=orgId;
		console.log( JSON.stringify($scope.contractor));	
	    $http({
            method  : 'POST',
            url     : '/WarehouseMgmt/contractor/editCtr',
            data    : JSON.stringify($scope.contractor),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(response){
        	   $mdDialog.show({
        		   template: "<md-dialog id='alertAddMaterial'>" +   
        		  
        		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your contractor with id " 
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
		$window.location.reload();
	}
}]);
