var WMS = angular.module('WMS');

WMS
		.controller(
				"AddOrderCtrl",
				[
						'$scope',
						'$http',
						'$window',
						'$mdDialog',
						'AllFactory',
						function($scope, $http, $window, $mdDialog, AllFactory) {
							var orgId= $window.localStorage['orgId'];
						/*	$scope.variable = false; */// to show add material
														// block
							$scope.disableW = false; // disable
														// warehouse_select when
														// warehouse is selected
							$scope.disableC = true; // disable contractor_select
													// when contractor is
													// selected
							$scope.chWare = true;
							/*$scope.mOverflow = true;*/
							$scope.onClickOfDone = false;
							$scope.enableMaterialQ=true;
							$scope.orderDetails = [];
							$scope.order = {};
							$scope.orderDetail = {};
							$scope.mtrls = [];
							$scope.categories=[];
							

							/* $scope.whenSubmit=false; */// show table
																// when adding
																// materials and
																// on submit
																// hide it.
							$scope.order.orderDate = new Date();
							$http
									.get(
											'/WarehouseMgmt/warehouse/warehouseByOrgId/'+orgId)
									.then(function(response) {
										$scope.warehouses = response.data;
									});

							$http
									.get(
											'/WarehouseMgmt/contractor/contractorByOrgId/'+orgId)
									.then(function(response) {
										$scope.contractors =response.data;
									});

							$http
									.get(
											'/WarehouseMgmt/material/materialByOrgId/'+orgId)
									.then(function(response) {
										$scope.materials = response.data;
									});

							$http
									.get(
											'/WarehouseMgmt/inventory/inventoryByOrgId/'+orgId)
									.then(function(response) {
										$scope.inventory = response.data;
									});
							  /* $http
								.get(
										'/WarehouseMgmt/category/categoryByOrgId/' + orgId)
								.then(function(response) {
									$scope.categories = response.data;
									console.log($scope.categories);
							});*/
							
							
							// ----------------------------------warehouse
							// select ng-change
							// method------------------------------//
							$scope.passW = function(wh) {
								
								$scope.disableC = false;
								$scope.order.warehouseId = wh.warehouseId;
								$scope.order.warehouseName = wh.warehouseName;
								
								
								for (var i = 0; i < $scope.inventory.length; i++) {
									
									if (wh.warehouseId == $scope.inventory[i].warehouse.warehouseId) {
										$scope.exists=false;
										for(var j=0;j<$scope.categories.length;j++){
											if($scope.inventory[i].material.category.categoryId==$scope.categories[j].categoryId)
												{
												$scope.exists=true;
												}
											else {$scope.exists=false;}
										}
										if(!$scope.exists){
											$scope.categories
											.push($scope.inventory[i].material.category);
										}
										
										/*var catObject123=$scope.inventory[i].material.category;
										if($scope.categories.indexOf(catObject123)==-1){
										$scope.categories
												.push($scope.inventory[i].material.category);
										}*/
									}

								}

								/*AllFactory.setWarehouse(wh.warehouseId);*/
								$scope.disableW = true;
								console.log($scope.categories);
							}

							// ---------------------------------- contractor
							// ng-change method --------------------------//
							$scope.passC = function(contractorObj) {
								
								$scope.order.contractorID=contractorObj.ctrId;
								$scope.chWare = false;
								$scope.disableC = true;
							}
							var availableQty = 0;
							 $scope.mOverflow=false; 
							 
							 $scope.indexMtr=null;
							 
							 //-------------Category select ng-change method----------//
							$scope.passCat=function(categoryObj){
								
								console.log(categoryObj);
								for(var i=0;i<$scope.inventory.length;i++){
									if($scope.order.warehouseId==$scope.inventory[i].warehouse.warehouseId 
											&& categoryObj.categoryId==$scope.inventory[i].material.category.categoryId){
											$scope.mtrls.push($scope.inventory[i].material);
									}
								}
								console.log($scope.mtrls);
							}
							 
							 
							 
							// --------------------------------material select
							// ng-change
							// method----------------------------------//
							$scope.pass = function() {
								
							/*	$scope.sampleMaterialID=materialID;*/
								$scope.enableMaterialQ=false;
								wID =$scope.order.warehouseId;
								/* console.log(AllFactory.getWarehouse()); */
								if($scope.material1 !== null){
								for (var i = 0; i < $scope.inventory.length; i++) {

									if ($scope.material1.materialID == $scope.inventory[i].material.materialID
											&& wID == $scope.inventory[i].warehouse.warehouseId) {
										availableQty = parseInt($scope.inventory[i].availableQty);
										$scope.availableQty = availableQty;
									}

								}
								for (var i = 0; i < $scope.mtrls.length; i++) {
									/*debugger;*/
									if ($scope.material1.materialID == $scope.mtrls[i].materialID) {
										console.log($scope.mtrls[i]);
										$scope.orderDetail.unitMeasure = $scope.mtrls[i].unitOfMeasure;
										$scope.orderDetail.unitPrice=$scope.mtrls[i].unitPrice;
										$scope.orderDetail.materialName = $scope.mtrls[i].materialName;
										$scope.orderDetail.materialId = $scope.mtrls[i].materialID;
										$scope.orderDetail.materialCode=$scope.mtrls[i].materialCode;
										/*$scope.indexMtr=$scope.mtrls.indexOf($scope.mtrls[i]);*/
										
									}
								}
								
								for(var i=0;i<$scope.mtrls.length;i++){
								/*	debugger;*/
									if($scope.material1.materialID== $scope.mtrls[i].materialID){
										$scope.object=$scope.mtrls[i];
										$scope.indexMtr=$scope.mtrls.indexOf($scope.object);
									}
								}
							}
							}
							
							// submit material method
							$scope.addOrder = function() {
								debugger;
								$scope.onClickOfDone=true;
								$scope.orderDetails.push($scope.orderDetail);
								$scope.mtrls.splice($scope.indexMtr,1);

								$scope.orderDetail = {};
								$scope.availableQty = null;
								/*$scope.material1=null;*/
								$scope.materialName=null;
								console.log($scope.mtrls);
							}
							
							$scope.someVar = function(Qty) {
								if (Qty > availableQty) {
									$scope.mOverflow = true;
								} else {
									$scope.mOverflow = false;
								}
							}

							// ---------------------------add material div show
							// method--------------------------------------//
							/*$scope.addNewOrder = function() {

								 $scope.whenSubmit=true; 
							}*/

					

							// --------------------------------submit all
							// materials
							// method------------------------------------------//
							$scope.submit = function() {

								$scope.order.orderDate = new Date();
								$scope.order.organisationId=orgId;
								var all = {
									'order' : $scope.order,
									'orderDetails' : {
										"orderList" : $scope.orderDetails
									}
								};
								$scope.NewAll = JSON.stringify(all);
								console.log($scope.NewAll);
							
							
								/* $scope.whenSubmit=false; */
								$http(
										{
											method : 'POST',
											url : '/WarehouseMgmt/order/add_order',
											data : $scope.NewAll,
											headers : {
												'Content-Type' : 'application/json; charset=utf-8'
											}

										}).then(function(response) {
											debugger;
											 $mdDialog.show({
							            		   template: "<md-dialog id='alertAddMaterial'>" +   
							            		  
							            		   " <div style='margin: 10px 10px 0px;'>" +"<label style='font-weight:100'>Your order with id " 
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
								
								$scope.order = {};
								/* console.log(JSON.stringify($scope.orders)); */

								// $window.location.href="#/orderDetails";
							}

							$scope.cancel = function() {
								$mdDialog.cancel();
							}
						} ]);

WMS
		.controller(
				'OrderCtrl',
				[
						'$scope',
						'$http',
						'$window',
						'AllFactory',
						'$mdDialog',
						function($scope, $http, $window, AllFactory, $mdDialog) {
							var orgId= $window.localStorage['orgId'];
							$scope.role=$window.localStorage['role'];
							$http
									.get(
											'/WarehouseMgmt/order/orderByOrgId/'+orgId)
									.then(function(response) {
									/*	console.log(response.data);*/
										$scope.orders = response.data;
									});
							$http
									.get(
									'/WarehouseMgmt/contractor/contractorByOrgId/'+orgId)
							.then(function(response) {
								$scope.contractors = response.data;
							});
							$http
									.get(
											'/WarehouseMgmt/warehouse/findRegionJson')
									.then(function(response) {
										$scope.regions = response.data;
							});
							$http
							.get(
									'/WarehouseMgmt/warehouse/warehouseByOrgId/'+orgId)
							.then(function(response) {
								$scope.warehouses = response.data;
					});
/*							$scope.pagination=function(){
								console.log($scope.orders.length);
								console.log(Math.ceil($scope.orders.length/5));
								$scope.orders
								for(var i=$scope.orders.length;i>$scope.length-5;i++){
									$scope.orderDetai[i]=$scope.orders[i];
								}
								
							}*/
							// ----------------------------view pop up window of
							// order details of selected order
							// id------------------//
							$scope.viewO = function(ev, id) {
								AllFactory.setOrder(id);

								$mdDialog
										.show({
											targetEvent : ev,
											templateUrl : '/WarehouseMgmt/pages/OrderDetails.jsp',
											controller : 'OrderDetailsCtrl',
											parent : angular
													.element(document.body),
											clickOutsideToClose : true,
										});

							}
							$scope.generateO = function(ev) {
								$mdDialog
										.show({
											targetEvent : ev,
											templateUrl : '/WarehouseMgmt/pages/addOrder1.jsp',
											controller : 'AddOrderCtrl',
											parent : angular
													.element(document.body)
										});

							};
							$scope.selectionItem=[];
							
								$scope.addToInvoice=function(orderId){
									var id=$scope.selectionItem.indexOf(orderId);
									
									if(id>-1){
										$scope.selectionItem.splice(id,1);
									}
									else{
										$scope.selectionItem.push(orderId);
									}
								}
								/*var obj=[];*/
								$scope.showData=function(){
								/*	var a=JSON.stringify($scope.selectionItem);*/
									var selectedItem=$scope.selectionItem.toString();
									console.log(selectedItem);
									AllFactory.setGivenId($scope.selectionItem);
									$window.location.href="#!/generateInvoice";
/*									$http.post("http://localhost:9090/WarehouseMgmt/invoice/oDetailsByIdForInvoiceJson",selectedItem)
									.success(function(data){
										console.log(data);
										console.log("success");
										AllFactory.setGivenId(data);
										AllFactory.setGivenId(2);
										$window.location.href="#/generateInvoice";
										$window.location.href="#/viewInvoice";
									})*/
/*									$scope.orderDetail = [];
									console.log($scope.selectionItem);
									if($scope.selectionItem!=null){
									for(var i=0;i<$scope.selectionItem.length;i++){
									$http
									.post(
											'http://localhost:9090/WarehouseMgmt/order/oDetailsByIdForInvoiceJson',$scope.selectionItem[i])
									.success(
											function(data) {
												console.log(data);
												$scope.orderDetails = data;
												for (var i = 0; i < $scope.orderDetails.length; i++) {
														$scope.orderDetail
																.push($scope.orderDetails[i]);
												}
											});
									
									}
									 obj=$scope.orderDetail[0];
									console.log(obj);
									console.log($scope.orderDetail);
								}*/
									}

								$scope.sorting=true;
								vari="orderId";
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
						} ]);
WMS
		.controller(
				"OrderDetailsCtrl",
				[
						'$scope',
						'$http',
						'$window',
						'AllFactory',
						'$mdDialog',
						function($scope, $http, $window, AllFactory,$mdDialog) {
							$scope.role=$window.localStorage['role'];
							$scope.orderDetail = [];
							$scope.orderId = AllFactory.getOrder();
							$scope.disableButton=false;
							var orgId= $window.localStorage['orgId'];
							$http
									.get(
											'/WarehouseMgmt/order/orderDetailsJson')
									.then(
											function(response) {
												console.log(response.data);
												$scope.orderDetails = response.data;
											/*	debugger;*/
												for (var i = 0; i < $scope.orderDetails.length; i++) {
													if($scope.orderDetails[i].order.orderId==$scope.orderId){
														$scope.orderDetail
																.push($scope.orderDetails[i]);
														}
												
												}
												
												
												 for(var i=0;i<$scope.orderDetail.length;i++){
														if($scope.orderDetail[i].order.orderMasterInvoiceStatus=="Invoiced"){
															$scope.disableButton=true;
														}	
												 }
												  /* $http
													.get(
															'/WarehouseMgmt/category/categoryByOrgId/' + orgId)
													.then(function(response) {
														$scope.categories = response.data;
														console.log($scope.categories);
												});
												   
													$http.get('/WarehouseMgmt/material/materialByOrgId/'+orgId)
											        .then(function successCallback(response){
											        $scope.material_details=response.data; 
											        });*/
												
												console.log($scope.disableButton);
												
											});
							
							
							$scope.showData=function(){
								/*	var a=JSON.stringify($scope.selectionItem);*/
								$scope.selectionItem=[];
								$scope.selectionItem.push($scope.orderId);
								var selectedItem=$scope.selectionItem.toString();
								console.log(selectedItem);
								AllFactory.setGivenId($scope.selectionItem);
								$mdDialog.hide();
									$window.location.href="#!/generateInvoice";
							}
						} ])
