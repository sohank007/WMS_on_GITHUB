var WMS=angular.module("WMS",['ngMaterial','ngRoute','ngMessages',"ng-fusioncharts",'smart-table','ngMdIcons'
                              ,'angularUtils.directives.dirPagination','ngFileUpload'])
.config(function($routeProvider){
	$routeProvider
	/*.when('/',{
		templateUrl:"/WarehouseMgmt/pages/dashboard.jsp",
		controller:'dashboardCtrl'
	})*/
	
	.when('/Dashboard',{
		templateUrl:"/WarehouseMgmt/pages/dashboard.jsp",
		controller:'dashboardCtrl'
	})
//materialDetails//Material
	.when('/Material',{
		templateUrl:"/WarehouseMgmt/pages/materialDetails.jsp",
		controller : "MaterialDetailsCtrl"
	})
//inventoryDetails//Inventory	
	.when('/Inventory',{
		templateUrl:"/WarehouseMgmt/pages/inventoryDetails.jsp",
		controller : "InventoryDetailsCtrl"
	})
//warehouseDetails//Warehouse
	.when('/Warehouse',{
		templateUrl:"/WarehouseMgmt/pages/warehouseDetails.jsp",
		controller : "WarehouseDetailsCtrl"
	})

//contractorDetails	//Contractor
	.when('/Contractor',{
		templateUrl:"/WarehouseMgmt/pages/contractorDetails.jsp",
		controller : "ContractorDetailsCtrl"
	})

//viewInward	//Inward
	.when('/Inward',{
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
//ViewOrder	//Order
	.when('/Order',{
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
//viewInvoice	//Invoice
	.when('/Invoice',{
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
//categoryDetails	//Category
	.when('/Category',{
		templateUrl:"/WarehouseMgmt/pages/categoryDetails.jsp",
		controller : "CategoryDetailsCtrl"
	})
	
	.when('/menuMaster',{
		templateUrl:"/WarehouseMgmt/pages/menuMaster.jsp",
		controller : "menuMasterCtrl"
	})
	
	.when('/roleMaster',{
		templateUrl:"/WarehouseMgmt/pages/roleMaster.jsp",
		controller : "RoleMasterCtrl"
	})
	
	.when('/roleMenu',{
		templateUrl:"/WarehouseMgmt/pages/roleMenu.jsp",
		controller : "RoleMenuCtrl"
	})
	
	
	 .otherwise({
        redirectTo : "/"
    });


},function($mdDateLocaleProvider){
	 $mdDateLocaleProvider.formatDate = function(date) {
	       return moment(date).format('dd/MM/yyyy');
	    };
	
},
function($mdThemingProvider){
	 $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
});

WMS.run(function($http,AllFactory){
	AllFactory.setOrganisationId("ORG-11");
})
// ++++++++++ ORIGINAL CODE AS IT IS +++++++++++++++++++++++++++++ // 

/*
WMS.run(function($http,$rootScope, $location,$window,auth) {
var ip="http://192.168.100.19:1337/users/";     // local pfactory
//var ip="http://43.242.214.42:1337/users/";    //cloud 43 pfactory
var getParameterByName = function(name, url) {
  debugger;
  if (!url) {
    url = window.location.href;
  }
  name = name.replace(/[\[\]]/g, "\\$&");
  var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
      results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return '';
  return decodeURIComponent(results[2].replace(/\+/g, " "));
}
  
    if (auth.isLoggedIn()) {
      //$window.location.href = "http://localhost:1337/#/home";

var access_tokn = getParameterByName('access_token');
console.log(access_tokn);
var appid = getParameterByName('appid');
console.log(appid);
//$window.location.href = $window.location.href.substring(0, $window.location.href.lastIndexOf('/'));

if(access_tokn && appid)
{

var req = {
method: 'POST',
url: ip+ 'validateUser',
headers: {
'Content-Type': 'application/json; charset=UTF-8'

},
data: {'token':access_tokn,'appid':appid}
}

$http(req).then(function(response) {
console.log(response.data);
auth.saveToken(access_tokn);
var qa = response.data;
console.log(qa);
if(access_tokn){
	var d = Date.new();
}
var datam=auth.currentUser();
   if(!($window.localStorage['rolecode']))
	 {
	   var datam=auth.currentUser();
	   //console.log(datam);
	   $window.localStorage['rolecode'] = datam.rolecode;
	 }
  if(!($window.localStorage['useremail']))
	  {
	     var datam=auth.currentUser();
	     //console.log(datam);
	      $window.localStorage['useremail'] = datam.email;
	  }
  if(!($window.localStorage['username']))
	  {
	     var datam=auth.currentUser();
	      $window.localStorage['username'] = datam.name;
	  }
  if(!($window.localStorage['org_name']))
	  {
	     var datam=auth.currentUser();
	      $window.localStorage['org_name'] = datam.org_name;
	  }
  if(!($window.localStorage['org_id']))
	  {
	     var datam=auth.currentUser();
	      $window.localStorage['org_id'] = datam.org_id;
	  }
  var org_id = auth.saveorganisationId($window.localStorage['org_id']);
	 console.log(org_id);
$window.location.href = $window.location.href.replace($window.location.search,'');

},function(data, status, headers, config) {
  if(status == 404)
  {
      $window.location.href = "http://192.168.100.19:1337/#/home";

  }
  
});
}
else
{
$window.location.href = "http://192.168.100.19:1337/#/home";
}
    }
 else
 {
  
 if($window.location.href.split("/")[4].length>0)
 {
  $window.location.href = $window.location.href.replace($window.location.search,'');
 }
 
   
 
  //$window.location.href = $window.location.href.replace($window.location.search,'');
 }

  $rootScope.$on('$routeChangeStart', function(event) {
    if (!auth.isLoggedIn()) {
      console.log($window.location.href.split("/")[4].length);
      
      if($window.location.href.split("/")[4].length>1)
      {
      $window.location.href = "http://192.168.100.19:1337/";
      }
    }
  });




});
*/








// +++ CODE FROM ALLFACTORY TO APP JS  PASTED ++++++++++++





