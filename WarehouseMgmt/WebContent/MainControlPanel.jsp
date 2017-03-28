<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Master Page</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular-route.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular-aria.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular-messages.min.js"></script> -->
    <script type="text/javascript" src="scripts/angualrLib/angular.js"></script><!-- 1.6.X version-->
    <script type="text/javascript" src="scripts/angualrLib/angular-animate.min.js"></script>
    <script type="text/javascript" src="scripts/angualrLib/angular-route.min.js"></script>
    <script type="text/javascript" src="scripts/angualrLib/angular-aria.min.js"></script>
    <script type="text/javascript" src="scripts/angualrLib/angular-messages.min.js"></script>


    <script type="text/javascript" src="scripts/angualrLib/fusioncharts.js"></script>
    <script type="text/javascript" src="scripts/angualrLib/fusioncharts.charts.js"></script>
    <script type="text/javascript" src="scripts/angualrLib/angular-fusioncharts.min.js"></script>
    <script type="text/javascript" src="scripts/angualrLib/angular-material.js"></script><!--  v1.1.3 -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-smart-table/2.1.8/smart-table.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-smart-table/2.1.8/smart-table.min.js"></script>
<!--     <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-smart-table/2.1.8/smart-table.min.js.map"></script> -->
    <link rel="stylesheet" href="scripts/angualrLib/angular-material.css">
<!--         <script src="http://cdn.rawgit.com/angular/bower-material/v0.10.0/angular-material.js"></script>
    <link rel="stylesheet" href="http://cdn.rawgit.com/angular/bower-material/v0.10.0/angular-material.css"/>
 -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/angular-material-icons/0.7.1/angular-material-icons.min.js"></script> 
    <script type="text/javascript" src="scripts/angualrLib/dirPagination.js"></script>
    <script type="text/javascript" src="scripts/angualrLib/ng-file-upload-shim.min.js"></script>
        <script type="text/javascript" src="scripts/angualrLib/ng-file-upload.min.js"></script>

    
    <script type="text/javascript" src="scripts/app.js" ></script>
    <script type="text/javascript" src='scripts/mainCtrl.js'></script>
    <script type="text/javascript" src='scripts/MaterialCtrls.js'></script>
    <script type="text/javascript" src='scripts/OrderCtrls.js' ></script>
    <script type="text/javascript" src="scripts/ContractorCtrls.js"></script>
    <script type="text/javascript" src="scripts/WarehouseCtrls.js"></script>
    <script type="text/javascript" src="scripts/AllFactory.js"></script>
    <script type="text/javascript" src="scripts/InwardCtrls.js"></script>
    <script type="text/javascript" src="scripts/InventoryDetailsCtrl.js"></script>
    <script type="text/javascript" src="scripts/dashboardCtrl.js"></script>
    <script type="text/javascript" src="scripts/invoiceCtrl.js"></script>
	<script type="text/javascript" src='scripts/CategoryCtrl.js'></script>
    <script type="text/javascript" src="scripts/RoleMasterCtrls.js"></script>
	<script type="text/javascript" src="scripts/menuMasterCtrls.js"></script>
	<script type="text/javascript" src="scripts/RoleMenuCtrls.js"></script>
	        
            
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="scripts/AllCSS.css">
   <style type="text/css">
   display:none;
   </style>

</head>
<body  ng-app="WMS" ng-controller="mainCtrl" style="width:100%;height: 100%; overflow:hidden;" layout="column">

<!--   <div style="height:100%; width:100%;"> -->
      
         <md-toolbar class="md-hue-2" style="background-color: #1874CD;
         max-height:50px;min-height:50px;">
     <div class="md-toolbar-tools"  >
        <!-- 	<md-button class="md-icon-button" aria-label="Settings" > -->
          <md-icon md-svg-icon="scripts/svgImgs/menu1.svg "
           ng-click="lockLeft=!lockLeft" hide-gt-sm style="margin: auto 0px;"></md-icon>
        	<!-- </md-button> --><!-- <span>  -->
          <a ng-href="#/">Warehouse Management System</a><!-- </span> -->
		</div>
    </md-toolbar>
<div layout="row" layout-align="start stretch" flex>
<!--       <div style="float: left;background-color:#eeeeee;" flex> -->
         <md-sidenav  class="md-sidenav-left" ng-cloak="" md-is-locked-open="lockLeft || $mdMedia('gt-sm')" 
         flex style="max-width:200px;height:100%;min-height:650px;overflow-y:hidden;">
      <!--    <md-content style="background-color:#104E8B;" flex> -->
			<md-list flex style="height:100%;" ng-repeat="menu in rolemenus">
			<!-- ng-repeat="menu in rolemenus" up anvoe in th sky -->
 			 <md-list-item flex ng-click="setRoute(menu.menuName)">
 					<md-icon md-svg-icon="{{menu.menuIcon}}" flex style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  					<label style="color: white;">{{menu.menuName}}</label>
            </md-list-item>
 						<!-- <md-list-item>
  							<a  ng-click="setRoute('/')" class="sidenavClass" ><md-icon md-svg-icon="scripts/svgImgs/inventory.svg"></md-icon>Warehouse Management</a>
 						</md-list-item>  -->
			  	<!-- 		 <md-list-item flex  ng-click="setRoute('inventoryDetails')">
  							<md-icon md-svg-icon="scripts/svgImgs/inventory.svg" flex 
  							style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  							<label style="color: white;">Inventory</label> 
 						</md-list-item>
 						<md-list-item flex ng-click="setRoute('ViewOrder')">
  							<md-icon md-svg-icon="scripts/svgImgs/order1.svg" flex
  							style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  							<label style="color: white;">Order</label>
 						</md-list-item>				
 						<md-list-item flex ng-click="setRoute('viewInward')">
  							<md-icon md-svg-icon="scripts/svgImgs/inward.svg" flex
  							style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  							<label style="color: white;">Inward</label>
 						</md-list-item>
  						<md-list-item flex  ng-click="setRoute('materialDetails')">
  							<md-icon md-svg-icon="scripts/svgImgs/material.svg" flex
  							style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  							<label style="color: white;">Material</label>
 						</md-list-item>	
 						<md-list-item flex ng-click="setRoute('warehouseDetails')">
  							<md-icon md-svg-icon="scripts/svgImgs/warehouse.svg" flex
  							style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  							<label style="color: white;">Warehouse</label>
 						</md-list-item>
 						<md-list-item flex  ng-click="setRoute('contractorDetails')">
  							<md-icon md-svg-icon="scripts/svgImgs/contractor.svg" flex
  							style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  							<label style="color: white;">Contractor</label>
 						</md-list-item>
 						<md-list-item flex ng-click="setRoute('viewInvoice')">
  							<md-icon md-svg-icon="scripts/svgImgs/approve.svg" flex
  							style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  							<label style="color: white;">invoice</label>
 						</md-list-item>
 						<md-list-item flex ng-click="setRoute('categoryDetails')">
  							<md-icon md-svg-icon="scripts/svgImgs/material.svg" flex
  							style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  							<label style="color: white;">Category</label>
 						</md-list-item>	 						
 						<md-list-item flex ng-click="setRoute('menuMaster')">
 						<md-icon md-svg-icon="scripts/svgImgs/order1.svg" flex
  							style="padding:0px;margin:0px;max-width: 60px;"></md-icon>
  							<label style="color: white;">Role Menu Mapping</label>
            			</md-list-item>   -->
 					
			</md-list>
	<!-- 		</md-content> -->
         </md-sidenav><!-- </div> -->



    <div ng-view style="height:100%;overflow: auto;"  flex></div>
    </div>
<!-- </div> -->

</body>

</html>