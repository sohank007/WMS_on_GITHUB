var WMS=angular.module('WMS')
WMS.controller('MaterialDetailsCtrl',['$scope','$http','$window','AllFactory','$mdDialog',function($scope,$http,$window,AllFactory,$mdDialog){		
	var orgId=$window.localStorage['orgId']	;

	$http.get('/WarehouseMgmt/material/materialByOrgId/'+orgId)
            .then(function successCallback(response){
            $scope.material_details=response.data; 
            }); 

	   $http
		.get(
				'/WarehouseMgmt/category/categoryByOrgId/' + orgId)
		.then(function(response) {
			$scope.categories = response.data;
			console.log($scope.categories);
	});
	   
	
		
    	    $scope.edit_material=function(ev,material){
    	    	AllFactory.setMaterial(material);
    	    	$mdDialog.show({
    	    		targetEvent: ev,
    	    		templateUrl: "/WarehouseMgmt/pages/editMaterial.jsp",
    	    		controller: 'EditMaterialCtrl',
   	    		 	parent: angular.element(document.body)
   	    		 
    	    	});
    	    	
    	    };
    		
    	    $scope.delete_material=function(material){
    	    	$http({
    	            method  : 'POST',
    	            url     : '/WarehouseMgmt/material/delete/mtr',
    	            data    : JSON.stringify(material),
    	            headers : {'Content-Type': 'application/json; charset=utf-8'}
    	            
    	           }).then(function(response){
    	        	   $mdDialog.show({
                		   template: "<md-dialog id='alertAddMaterial'>" +   
                		  
                		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your material with id " 
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
    	    
    	    $scope.addMtr=function(ev){
    	    	$mdDialog.show({
    	    		 targetEvent: ev,
    	    		 templateUrl: '/WarehouseMgmt/pages/addMaterial.jsp',
    	    		 controller:'AddMaterialCtrl',
    	    		 parent: angular.element(document.body),
    	    		
    	    	});
    	    };
    	    
    	    
    	    $scope.ViewMaterialDetails=function(ev,materialId){
    	    	AllFactory.setMaterial(materialId);
    	    	$mdDialog
				.show({
					targetEvent : ev,
					templateUrl : '/WarehouseMgmt/pages/MaterialDetailsById.jsp',
					controller : 'materialDetailsByIdCtrl',
					parent : angular
							.element(document.body),
					clickOutsideToClose : true,
				});
    	    	
    	    }
    	    
    	    $scope.sorting=true;
    		vari="materialID";
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


WMS.controller('AddMaterialCtrl',['$scope','$http','$window','$mdDialog','AllFactory','Upload','$timeout',
                                  function($scope,$http,$window,$mdDialog,AllFactory,Upload,$timeout){
	var orgId=$window.localStorage['orgId'];
	$scope.imagevisible=false;
    $scope.material={};
    $scope.units =['Meters','Kilometers','Liters','Dozons','Kilograms'] ;
    
    $http
	.get(
			'/WarehouseMgmt/category/categoryByOrgId/' + orgId)
	.then(function(response) {
		$scope.categories = response.data;
		console.log($scope.categories);
});
    $scope.uploadFiles = function(file) {
        $scope.f = file;
        if (file) {
            Upload.upload({
                url: 'http://192.168.100.8:1339/users/materialUpload',
                data: {
                    file: file
                }
            }).then(function (response) {
                $timeout(function () {
                	$scope.result=response.data;
                	console.log(response.data);
                    $scope.material.materialImage =($scope.result.path).split('Material\\')[1];
                    console.log($scope.material.materialImage);
                    $scope.image="http://192.168.100.8:1339/"+$scope.material.materialImage;
                    console.log($scope.image);
                    $scope.imagevisible=true;
                });
            }, function (response) {
                if (response.status > 0) {
                    $scope.errorMsg = response.status + ': ' + response.data;
                }
            });
        }   
    }

	$scope.submit=function(ev){
		debugger;
		$scope.material.organisationId=orgId;
	$scope.NewMtr = JSON.stringify($scope.material);
	console.log($scope.material);
    $http({
              method  : 'POST',
              url     : '/WarehouseMgmt/material/add_mtr',
              data    : $scope.NewMtr,
              headers : {'Content-Type': 'application/json; charset=utf-8'}
              
             }).then(function(response){
            	 $mdDialog.show({
            		   template: "<md-dialog id='alertAddMaterial'>" +   
            		  
            		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your material with id " 
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
            			 /*     $mdDialog.alert()
            			        .parent(angular.element(document.body))
            			        .title('Add Material response')
            			        .textContent('Your material with id '+response.data+' has been inserted successfully.')
            			        .ariaLabel('Alert Dialog Demo')
            			        .ok('Got it!');*/
            			    
            	 
/*            	 $mdDialog.show(
            		      $mdDialog.alert()
            		        parent: angular.element(document.body),
            		        .title('Add Material response')
            		        .textContent('Your material with id '+response.data+' has been inserted successfully.')
            		        .ariaLabel('Alert Dialog Demo')
            		        .ok('Ok')
            		        .targetEvent(ev)
            		    );*/
            	 /*alert("id:"+response.data);*/
            	/* $window.location.reload();*/
             });
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	};
}]);

WMS.controller("EditMaterialCtrl",['$scope','$http','$window','AllFactory','$mdDialog','Upload','$timeout',
                                   function($scope,$http,$window,AllFactory,$mdDialog,Upload,$timeout) {
	//debugger;
	var material=AllFactory.getMaterial();
	var orgId=$window.localStorage['orgId'];
    $scope.units = ['Meters' ,'Kilometers', 'Liters' ,'Dozons', 'Kilograms'];
    	    
    $http
	.get(
			'/WarehouseMgmt/category/categoryByOrgId/' + orgId)
	.then(function(response) {
		$scope.categories = response.data;
		console.log($scope.categories);
});
    
    //console.log(material);
	$scope.material=material;
	$scope.cat=$scope.material.category.categoryName;
	$scope.image="http://192.168.100.8:1339/"+$scope.material.materialImage;
	console.log($scope.image);
	
	
	
    $scope.uploadFiles = function(file) {
        $scope.f = file;
        if (file) {
            Upload.upload({
                url: 'http://192.168.100.8:1339/users/materialUpload',
                data: {
                    file: file
                }
            }).then(function (response) {
                $timeout(function () {
                	$scope.result=response.data;
                	console.log(response.data);
                    $scope.material.materialImage =($scope.result.path).split('Material\\')[1];
                    console.log($scope.material.materialImage);
                    $scope.image="http://192.168.100.8:1339/"+$scope.material.materialImage;
                    console.log($scope.image);
                    $scope.imagevisible=true;
                });
            }, function (response) {
                if (response.status > 0) {
                    $scope.errorMsg = response.status + ': ' + response.data;
                }
            });
        }   
    }
	
 
	$scope.submit=function(){
		debugger;
/*		console.log( JSON.stringify($scope.material));	*/
		$scope.material.organisationId=orgId;
		for(var i=0;i<$scope.categories.length;i++){
			if($scope.cat.categoryName==$scope.categories[i].categoryName){
				$scope.material.category=$scope.categories[i];
			}
		}
		
		
		/*for(var i=0;i<$scope.categories.length;i++){
			if()
			
		}*/
	    $http({
            method  : 'POST',
            url     : '/WarehouseMgmt/material/editMtr',
            data    : JSON.stringify($scope.material),
            headers : {'Content-Type': 'application/json; charset=utf-8'}
            
           }).then(function(response){
        	   
        	   $mdDialog.show({
        		   template: "<md-dialog id='alertAddMaterial'>" +   
        		  
        		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your material with id " 
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
        	/*   
        	   alert("orgId:"+response.data);
        	   $window.location.reload();*/
           });
		
	};
	$scope.cancel=function(){
		$mdDialog.cancel();
	};
}]);

WMS.controller('materialDetailsByIdCtrl',['$http','$scope','AllFactory',function($http,$scope,AllFactory){
	var mId=AllFactory.getMaterial();
	$http.get("/WarehouseMgmt/material/materialDetail/"+mId)
	.then(function(response){
		$scope.material=response.data;
		console.log($scope.material);
	})
	
	
}]);

