<body layout="column" ng-cloak="">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Warehouse Details</h2>
   </div>
   
      <div layout="row" style="height: 60px;margin-top: 20px;">
   <md-input-container style="margin: 10px 10px 0px;" flex="30">
   <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Warehouse</label>
   <input ng-model="warehouse_id" type="text" >
      </md-input-container>
   <md-input-container layout="row" style="margin: 0px 10px;" flex="30">     
               <md-button class="md-raised" type="Submit" style="color: gray" ng-click="addWare($event)">
               Add Warehouse</md-button>
           </md-input-container>   

           </div>
   
<div style="margin: 10px 10px;">

    <table class="table" style="width:100%">
      <thead>
      <tr class="tr">
		<th style="text-align: left;">Warehouse Name</th>
		<th style="text-align: left;">Warehouse Location</th>
		<th style="text-align: left;">Region</th>
		<th style="text-align: left;"></th>
	  </tr>
      </thead>
      <tbody>
		<tr ng-repeat="wrhouse in warehouses | orderBy:'-warehouseId' | filter:warehouse_id  " class="tr">

			<td style="text-align: left;">{{wrhouse.warehouseName}}</td>
			<td style="text-align: left;">{{wrhouse.warehouseLoc}}</td>
			<td style="text-align: left;">{{wrhouse.wRegion}}</td>
			<td style="text-align: left;">
<md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="editWare($event,wrhouse)"></md-icon>
<md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="delete_wrh(wrhouse)"></md-icon>
		   </td>
		</tr>
     </tbody>
   </table>
   </div>
</body>
