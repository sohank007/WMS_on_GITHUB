<div ng-app="WMS" ng-cloak="" style="overflow: hidden;">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Inventory Details</h2>
   </div>

<!--  <dir-pagination-controls  class="text-center"
       max-size="5"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls> -->
<div layout="row" flex style="margin:0px 10px;">

<!--         <md-input-container style="margin: 10px 10px 0px;">    	
            	
            	      </md-input-container> -->
<!-- <table style="width: 50%;">
<tr>
<td style="text-align: left;width:30%;padding-left: 10px;">Select Warehouse</td><td style="width: 70%;">
<md-select ng-model="warehouseName" placeholder="warehouse"
        	style="color:gray;width:200px;min-width: 150px;" ng-change="passW(warehouseName)">
              <md-option ng-repeat="warehouse in warehouses" ng-value="warehouse.warehouseName">{{warehouse.warehouseName}}
              </md-option>
            	</md-select> </td>

</tr></table> -->
 </div>    

 
            	<div style="margin: 10px 10px;">
            	<table style="width: 100%;">
            	<tr>
            	<td colspan="2" style="text-align: left;float: left;height: 60px;">
            	<md-input-container style="margin: 0px;height:60px;padding-top: 10px;" flex="70">
				<label>Warehouse</label>
				<md-select ng-model="warehouseName" 
        		style="color:gray;width:150px;min-width: 100px;" ng-change="passW(warehouseName)">
        			<md-option ng-value=""><em>None</em></md-option>
              		<md-option ng-repeat="warehouse in warehouses" ng-value="warehouse.warehouseName">{{warehouse.warehouseName}}
              		</md-option>
            			</md-select></md-input-container>
            	</td>
            	<td colspan="2" style="text-align: right;float: right;height: 60px;padding:0px;margin:0px;">
            	<md-input-container style="margin: 0px;" flex="30">
            	<dir-pagination-controls  
       max-size="5"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls></md-input-container></td>
            	</tr>
            	</table>
 <table class="table"  flex>
<thead>
  <tr class="tr" >
    <th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('material.materialName')">Material 
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='material.materialName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='material.materialName' && !reverse" ng-click="sortingMaterial('material.materialName')" flex></md-icon>
    </th>
        <th style="text-align: right;outline: 0px;"  ng-click="sortingMaterial('material.unitPrice')" >Unit Price
        <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='material.unitPrice' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='material.unitPrice' && !reverse"  flex></md-icon>
    </th>
<!--     <th>Warehouse </th> -->
    <th style="text-align: right;outline: 0px;"  ng-click="sortingMaterial('totalQty')">Total Quantity
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='totalQty' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='totalQty' && !reverse"  flex></md-icon>
    </th>
    <th style="text-align: right;outline: 0px;"  ng-click="sortingMaterial('availableQty')">Available Quantity
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='availableQty' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='availableQty' && !reverse"  flex></md-icon>
    </th>

  </tr>
  </thead>
  <tbody>
  <tr class="tr" dir-paginate="inventory in inventoryDetails | filter:warehouseName |filter:search
  |orderBy:variable:reverse |itemsPerPage:10 ">
    <td style="text-align: left;">{{inventory.material.materialName}}</td>
        <td style="text-align: right;">{{inventory.material.unitPrice |number:2}}/{{inventory.material.unitOfMeasure}}</td>
<!--     <td>{{inventory.warehouse.warehouseName}}</td> -->
    <td style="text-align: right;">{{inventory.totalQty | number:2}}</td>
    <td style="text-align: right;">{{inventory.availableQty | number:2}}</td>

    </tr>
   

   
    </tbody>

</table>

</div>
<!-- 
     <fusioncharts
      
	   width=50% height=50%;
       type="pie2d"
       dataFormat="json"
       dataSource="{{dataSource}}">
   </fusioncharts>
 -->

</div>