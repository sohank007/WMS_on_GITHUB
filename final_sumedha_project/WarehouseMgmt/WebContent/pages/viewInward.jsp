
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Inward</h2>
   </div>
   
   
   
   <div layout="row" style="margin: 20px 10px 0px;height:50px;" flex>
<!--   		 <md-input-container style="margin: 10px 10px 0px;" flex="20">
		   <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Inward</label>
		   <input type="text" ng-model="inwardId">
      	</md-input-container> -->
      	  <md-autocomplete flex="15"
          md-selected-item=""
          md-search-text="warehouseName"
          md-selected-item-change=""
          md-items="warehouse in warehouses | filter:warehouseName"
          md-item-text="warehouse.warehouseName"
          md-min-length="0"
           md-floating-label="Select Warehouse">
        <md-item-template>
          <span md-highlight-text="warehouse.warehouseName" md-highlight-flags="^i">{{warehouse.warehouseName}}</span>
        </md-item-template>
      </md-autocomplete>
      
   		<md-input-container  layout="row" style="margin: -10px 10px 0px;" flex>     
           <md-button type="Submit" class="md-raised" 
            ng-click="genInward($event)">
           Create Inward</md-button>
           </md-input-container>
  <!--    
         <md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex="20" >        	
			<dir-pagination-controls 
       max-size="5"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls>	
	
</md-input-container> -->
           </div> 


<div style="margin: 10px 10px;">
<table  class="inwardTable" style="width:100%;" ng-cloak="" >
<thead>
  <tr class="tr">
    <th style="text-align: right;width: 150px;padding-right: 20px;" ng-click="sortingMaterial('inId')">Inward Id
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='inId' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='inId' && !reverse"  flex></md-icon>
    </th>
    
    <th style="text-align:left;width: 30%;padding-left: 40px;" ng-click="sortingMaterial('warehouse.warehouseName')">Inward Warehouse
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='warehouse.warehouseName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='warehouse.warehouseName' && !reverse"  flex></md-icon>
    </th>
    <th style="text-align: right; padding-right: 40px;" ng-click="sortingMaterial('inDate')">Inward Date
    <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='inDate' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='inDate' && !reverse"  flex></md-icon>
    </th>
<!--     <th style="text-align: right;"></th> -->
  </tr>
  </thead>
  <tbody>
  <tr dir-paginate="inward in inwards| orderBy:variable:reverse | filter:warehouseName |itemsPerPage:10" 
  class="tr" flex ng-click="inwardDet($event,inward.inId)">
    <td style="text-align: right;width:150px;padding-right: 20px;">{{inward.inId}}</td>
    
    <td style="text-align: left;width:30%;padding-left: 40px;">{{inward.warehouse.warehouseName}}</td>
    <td style="text-align: right;">{{inward.inDate | date :'dd/MM/yyyy'}}</td>
<!--     <td style="text-align: right;"><md-icon md-svg-icon="scripts/svgImgs/details.svg" 
      flex></md-icon></td -->
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

