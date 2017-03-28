var WMS=angular.module("WMS",['ngMaterial','ngRoute','ngMessages',"ng-fusioncharts",'smart-table','ngMdIcons'
                              ,'angularUtils.directives.dirPagination'])
.config(function($routeProvider){
	$routeProvider
	.when('/',{
		templateUrl:"/WarehouseMgmt/pages/dashboard.jsp",
		controller:'dashboardCtrl'
	})
	
	.when('/materialDetails',{
		templateUrl:"/WarehouseMgmt/pages/materialDetails.jsp",
		controller : "MaterialDetailsCtrl"
	})
	
	.when('/inventoryDetails',{
		templateUrl:"/WarehouseMgmt/pages/inventoryDetails.jsp",
		controller : "InventoryDetailsCtrl"
	})

	.when('/warehouseDetails',{
		templateUrl:"/WarehouseMgmt/pages/warehouseDetails.jsp",
		controller : "WarehouseDetailsCtrl"
	})

	
	.when('/contractorDetails',{
		templateUrl:"/WarehouseMgmt/pages/contractorDetails.jsp",
		controller : "ContractorDetailsCtrl"
	})

	
	.when('/viewInward',{
		templateUrl:"/WarehouseMgmt/pages/viewInward.jsp",
		controller : "InwardCtrl"
	})	
	.when('/InwardDetails',{
		templateUrl:"/WarehouseMgmt/pages/inwardDetails.jsp",
		controller : "InwardDetailsCtrl"
	})	
	.when('/AddInward',{
		templateUrl:"/WarehouseMgmt/pages/addInward.jsp",
		controller : "AddInwardCtrl"
	})
	
	.when('/ViewOrder',{
		templateUrl:"/WarehouseMgmt/pages/viewOrder.jsp",
		controller : "OrderCtrl"
	})
	.when('/OrderDetails',{
		templateUrl:"/WarehouseMgmt/pages/OrderDetails.jsp",
		controller : "OrderDetailsCtrl"
	})
	
	.when('/AddOrder',{
		templateUrl:"/WarehouseMgmt/pages/addOrder1.jsp",
		controller : "AddOrderCtrl"
	})
	
	.when('/viewInvoice',{
		templateUrl:"/WarehouseMgmt/pages/viewInvoice.jsp",
		controller : "invoiceCtrl"
	})
	
		.when('/generateInvoice',{
		templateUrl:"/WarehouseMgmt/pages/generateInvoice.jsp",
		controller : "generateInvoiceCtrl"
	})
			.when('/viewInvoiceDetails',{
		templateUrl:"/WarehouseMgmt/pages/viewInvoiceDetails.jsp",
		controller : "viewInvoiceDetailsCtrl"
	})
	
	 .otherwise({
        redirectTo : "/"
    });


})
