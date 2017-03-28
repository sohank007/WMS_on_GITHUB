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
							debugger;
							var orgId=AllFactory.getOrganisationId();
							console.log(orgId);
							var category = AllFactory.getCategory();
							console.log(category);
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
							$scope.category = {};
							$scope.orderDetail = {};
							$scope.mtrls = [];
							$scope.ctgrys = [];
							$scope.category={};
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
									'/WarehouseMgmt/category/categoryByOrgId/'+orgId)
							.then(function(response) {
								$scope.categories = response.data;
							});
							$scope.category={};
							console.log($scope.category);

							$http
									.get(
											'/WarehouseMgmt/inventory/inventoryByOrgId/'+orgId)
									.then(function(response) {
										$scope.inventory = response.data;
									});

							// ----------------------------------warehouse
							// select ng-change
							// method------------------------------//
							$scope.passW = function(wh) {
								$scope.disableC = false;
								$scope.order.warehouseId = wh.warehouseId;
								$scope.order.warehouseName = wh.warehouseName;
								for (var i = 0; i < $scope.inventory.length; i++) {
									if (wh.warehouseId == $scope.inventory[i].warehouse.warehouseId) {
										$scope.ctgrys.push($scope.inventory[i].material.category);
										/*$scope.mtrls
										.push($scope.inventory[i].material);*/
									}

								}
								console.log($scope.mtrls);
								
								/*for (var i = 0; i < $scope.inventory.length; i++) {
									if (wh.warehouseId == $scope.inventory[i].warehouse.warehouseId) {
										$scope.ctgrys
										.push($scope.inventory[i].material.category);
									}

								}
								console.log($scope.ctgrys);*/
								AllFactory.setWarehouse(wh.warehouseId);
								$scope.disableW = true;
							}

							// ---------------------------------- contractor
							// ng-change method --------------------------//
							$scope.passC = function(contractorID) {
								$scope.chWare = false;
								$scope.disableC = true;
							}
							var availableQty = 0;
							 $scope.mOverflow=false; 
							 
							 $scope.indexMtr=null;
							 
							// --------------------------------material select
								// ng-change
								// method----------------------------------//
							 $scope.passCat = function(CatId){
								 debugger;
								 AllFactory.setCategory(CatId);								 	
								 console.log(CatId);
									$http
									.get(
											'/WarehouseMgmt/material/materialByCategoryId/' + CatId)
									.then(function(response) {
										$scope.materials = response.data;
										console.log($scope.materials);
									});
									
								 
							 }
							 
							// --------------------------------material select
							// ng-change
							// method----------------------------------//
							$scope.pass = function(materialID) {
								debugger;
							/*	$scope.sampleMaterialID=materialID;*/
								$scope.enableMaterialQ=false;
								wID = AllFactory.getWarehouse();
								catId = AllFactory.getCategory();
								/* console.log(AllFactory.getWarehouse()); */

								for (var i = 0; i < $scope.inventory.length; i++) {

									if (materialID == $scope.inventory[i].material.materialID
											&& wID == $scope.inventory[i].warehouse.warehouseId) {
										availableQty = parseInt($scope.inventory[i].availableQty);
										$scope.availableQty = availableQty;
									}

								}
								for (var i = 0; i < $scope.materials.length; i++) {
									if (materialID == $scope.materials[i].materialID) {
										$scope.orderDetail.unitMeasure = $scope.materials[i].unitOfMeasure;
										$scope.orderDetail.unitPrice=$scope.materials[i].unitPrice;
										$scope.orderDetail.materialCode=$scope.materials[i].materialCode;
										$scope.orderDetail.availableQty=$scope.materials[i].availableQty;
										$scope.orderDetail.materialName = $scope.materials[i].materialName;
	
										
									}
								}
								
								for(var i=0;i<$scope.mtrls.length;i++){
									if(materialID== $scope.mtrls[i].materialID){
										$scope.object=$scope.mtrls[i];
										$scope.indexMtr=$scope.mtrls.indexOf($scope.object);
									}
								}
							
							}
							
							// submit material method
							$scope.addOrder = function() {
								
								$scope.onClickOfDone=true;
								$scope.orderDetails.push($scope.orderDetail);
								$scope.mtrls.splice($scope.indexMtr,1);

								$scope.orderDetail = null;
								$scope.availableQty = null;

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
							var orgId=AllFactory.getOrganisationId();
							$http
									.get(
											'/WarehouseMgmt/order/orderByOrgId/'+orgId)
									.then(function(response) {
										console.log(response.data);
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
							$scope.status = false;
							$scope.orderDetail = [];
							$scope.orderId = AllFactory.getOrder();
							$http
									.get(
											'/WarehouseMgmt/order/orderDetailsJson')
									.then(
											function(response) {
												console.log(response.data);
												$scope.orderDetails = response.data;
												for (var i = 0; i < $scope.orderDetails.length; i++) {
													if($scope.orderDetails[i].order.orderId==$scope.orderId){
														$scope.orderDetail
																.push($scope.orderDetails[i]);
														}
													if($scope.orderDetails[i].order.orderMasterInvoiceStatus=='Invoiced'){
														$scope.status = true;
													}
												}
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
