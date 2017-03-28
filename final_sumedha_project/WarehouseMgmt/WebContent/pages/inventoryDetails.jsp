<div>
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Inventory Details</h2>
   </div>
  
        <div  layout="row" style="margin: 20px 10px 0px;height:50px;" flex>
        
 <!--                <md-input-container style="margin: 10px 10px 0px 5px;" flex="15">
        <label>Warehouse</label>
				<md-select ng-model="warehouseName" 
        		style="color:gray;" ng-change="passW(warehouseName)">
        			<md-option ng-value=""><em>None</em></md-option>
              		<md-option ng-repeat="warehouse in warehouses" ng-value="warehouse.warehouseName">{{warehouse.warehouseName}}
              		</md-option>
            	</md-select>
        </md-input-container> -->
         <md-autocomplete 
          md-search-text="warehouseName"
          md-selected-item-change=""
          md-items="warehouse in warehouses | filter:warehouseName"
          md-item-text="warehouse.warehouseName"
          md-min-length="0"
           md-floating-label="Select Warehouse" flex="15">
        <md-item-template>
          <span md-highlight-text="warehouse.warehouseName" md-highlight-flags="^i">{{warehouse.warehouseName}}</span>
        </md-item-template>
      </md-autocomplete>
      
      
         <md-autocomplete 
          md-search-text="categoryName"
          md-selected-item-change=""
          md-items="cat in categories | filter:categoryName"
          md-item-text="cat.categoryName"
          md-min-length="0"
           md-floating-label="Select Category" flex="15">
        <md-item-template>
          <span md-highlight-text="cat.categoryName" md-highlight-flags="^i">{{cat.categoryName}}</span>
        </md-item-template>
      </md-autocomplete>
      
      
        <md-autocomplete 
          md-search-text="materialCode"
          md-selected-item-change=""
          md-items="material in material_details | filter:materialCode"
          md-item-text="material.materialCode"
          md-min-length="0"
           md-floating-label="Select Material Code" flex="15">
        <md-item-template>
          <span md-highlight-text="material.materialCode" md-highlight-flags="^i">{{material.materialCode}}</span>
        </md-item-template>
      </md-autocomplete> 
      
        
<!--                 <md-input-container style="margin: 10px 10px 0px 5px;" flex="15">
          <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search </label>
          <input  ng-model="inventory" type="text" >
        </md-input-container> -->
        
  <!--      <md-input-container flex="15" style="margin:10px;">
        <label style="font-weight: 400;">Organization</label>
				<md-select ng-model="org_id" 
        		style="color:gray;" ng-change="setOrg(org_id)">
              		<md-option ng-repeat="org in organizations" ng-value="org">{{org}}
              		</md-option>
            	</md-select>
        </md-input-container> -->
        
        
        </div>
<div style="margin: 0px 10px;">
 <table class="inventoryTable"  flex style="width: 100%;">
<thead>
  <tr class="tr" >
      <th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('warehouse.warehouseName')">Warehouse
      <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='warehouse.warehouseName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='warehouse.warehouseName' && !reverse"  flex></md-icon>
       </th>
        <th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('material.category.categoryName')">Category 
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='material.category.categoryName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='material.category.categoryName' && !reverse"  flex></md-icon>
    </th>
    <th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('material.materialName')">Material 
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='material.materialName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='material.materialName' && !reverse"  flex></md-icon>
    </th>
    
    <th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('material.materialCode')">Material Code
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='material.materialCode' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='material.materialCode' && !reverse"  flex></md-icon>
    </th>
    
        <th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('material.unitOfMeasure')">Unit of Measurement 
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='material.unitOfMeasure' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='material.unitOfMeasure' && !reverse"  flex></md-icon>
    </th>
    
        <th style="text-align: right; outline: 0px;"  ng-click="sortingMaterial('material.unitPrice')" >Unit Price
        <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='material.unitPrice' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='material.unitPrice' && !reverse"  flex></md-icon>
    </th>

    <th style="text-align: right;outline: 0px;padding-right: 40px;"  ng-click="sortingMaterial('totalQty')">Total Inward Quantity
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='totalQty' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='totalQty' && !reverse"  flex></md-icon>
    </th>
    <th style="text-align: right;outline: 0px;padding-right: 40px;"  ng-click="sortingMaterial('availableQty')">Available Quantity
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='availableQty' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='availableQty' && !reverse"  flex></md-icon>
    </th>

  </tr>
  </thead>
  <tbody>
  <tr class="tr" dir-paginate="inventory in inventoryDetails | 
  filter:materialCode| filter:warehouseName | filter:categoryName |filter:inventory
  |orderBy:variable:reverse |itemsPerPage:10 ">
      <td style="text-align: left;">{{inventory.warehouse.warehouseName}}</td>
          <td style="text-align: left;">{{inventory.material.category.categoryName}}</td>
    <td style="text-align: left;">{{inventory.material.materialName}}</td>
        <td style="text-align: left;">{{inventory.material.materialCode}}</td>
    <td style="text-align: left;">{{inventory.material.unitOfMeasure}}</td>
        <td style="text-align: right;padding-right: 8px;">{{inventory.material.unitPrice |number:2}}</td>
<!--     <td>{{inventory.warehouse.warehouseName}}</td> -->
    <td style="text-align: right;">{{inventory.totalQty |number:2}}</td>
    <td style="text-align: right;">{{inventory.availableQty |number:2}}</td>

    </tr>
   

   
    </tbody>

</table>
</div>
     <div  layout="row" style="margin: 0px 10px;height:50px;" flex>
             <md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex>
        <dir-pagination-controls 
       max-size="5"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls>
        </md-input-container>
     
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