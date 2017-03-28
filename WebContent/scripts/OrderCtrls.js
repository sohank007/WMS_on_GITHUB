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
							$scope.variable = false; // to show add material
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

							/* $scope.whenSubmit=false; */// show table
																// when adding
																// materials and
																// on submit
																// hide it.
							$scope.order.orderDate = new Date();
							$http
									.get(
											'/WarehouseMgmt/warehouse/warehouseDetailsJson')
									.then(function(response) {
										$scope.warehouses = response.data;
									});

							$http
									.get(
											'/WarehouseMgmt/contractor/contractorDetailsJson')
									.then(function(response) {
										$scope.contractors =response.data;
									});

							$http
									.get(
											'/WarehouseMgmt/material/materialDetailsJson')
									.then(function(response) {
										$scope.materials = response.data;
									});

							$http
									.get(
											'/WarehouseMgmt/inventory/inventoryDetailsJson')
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
										$scope.mtrls
												.push($scope.inventory[i].material);
									}

								}

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
							// --------------------------------material select
							// ng-change
							// method----------------------------------//
							$scope.pass = function(id) {
								$scope.enableMaterialQ=false;
								wID = AllFactory.getWarehouse();
								/* console.log(AllFactory.getWarehouse()); */

								for (var i = 0; i < $scope.inventory.length; i++) {

									if (id == $scope.inventory[i].material.materialID
											&& wID == $scope.inventory[i].warehouse.warehouseId) {
										availableQty = parseInt($scope.inventory[i].availableQty);
										$scope.availableQty = availableQty;
									}

								}
								for (var i = 0; i < $scope.materials.length; i++) {
									if (id == $scope.materials[i].materialID) {
										$scope.orderDetail.unitMeasure = $scope.materials[i].unitOfMeasure;
										$scope.orderDetail.materialName = $scope.materials[i].materialName;
									}
								}
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
							$scope.addNewOrder = function() {
								$scope.variable = true;
								$scope.orderDetail = {};
								$scope.availableQty = 0;
								/* $scope.whenSubmit=true; */
							}

							// submit material method
							$scope.addOrder = function() {
								$scope.onClickOfDone=true;
								/* $scope.whenSubmit=true; */
								$scope.orderDetails.push($scope.orderDetail);
								$scope.variable = false;

							}

							// --------------------------------submit all
							// materials
							// method------------------------------------------//
							$scope.submit = function() {

								$scope.order.orderDate = new Date();
								var all = {
									'order' : $scope.order,
									'orderDetails' : {
										"orderList" : $scope.orderDetails
									}
								};
								$scope.NewAll = JSON.stringify(all);
								console.log($scope.NewAll);
								debugger;
								$scope.order = {};
								/* $scope.whenSubmit=false; */
								$http(
										{
											method : 'POST',
											url : '/WarehouseMgmt/order/add_order',
											data : $scope.NewAll,
											headers : {
												'Content-Type' : 'application/json; charset=utf-8'
											}

										}).then(function() {
									$window.location.reload();
								});
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
							$http
									.get(
											'/WarehouseMgmt/order/viewOrderJson')
									.then(function(response) {
										console.log(response.data);
										$scope.orders = response.data;
									});
							$http
									.get(
									'/WarehouseMgmt/contractor/contractorDetailsJson')
							.then(function(response) {
								$scope.contractors = response.data;
							});
							$http
									.get(
											'/WarehouseMgmt/warehouse/findRegionJson')
									.then(function(response) {
										$scope.regions = response.data;
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


						} ]);
WMS
		.controller(
				"OrderDetailsCtrl",
				[
						'$scope',
						'$http',
						'$window',
						'AllFactory',
						function($scope, $http, $window, AllFactory) {
							$scope.orderDetail = [];
							$scope.orderId = AllFactory.getOrder();
							$http
									.post(
											'/WarehouseMgmt/order/orderDetailsByIdJson',$scope.orderId)
									.then(
											function(response) {
												console.log(response.data);
												$scope.orderDetails = response.data;
												for (var i = 0; i < $scope.orderDetails.length; i++) {
														$scope.orderDetail
																.push($scope.orderDetails[i]);
												}
											});
						} ])
