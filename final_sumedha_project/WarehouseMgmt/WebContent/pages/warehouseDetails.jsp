<body layout="column" ng-cloak="">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Warehouse Details</h2>
   </div>
   
      <div  layout="row" style="margin: 0px 10px;height:50px;" flex>
   			<md-input-container style="margin: 10px 10px 0px;" flex="20">
   				<label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Warehouse</label>
   				<input ng-model="warehouse_id" type="text" >
      		</md-input-container>
      		
   			<md-input-container layout="row" style="margin: 0px 10px;" flex>     
               <md-button class="md-raised" type="Submit"
                ng-click="addWare($event)">
               Add Warehouse</md-button>
           </md-input-container>   
           
<!--             <md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex="20">
                 <dir-pagination-controls  
			       max-size="2"
			       direction-links="true"
			       boundary-links="true" >
   				 </dir-pagination-controls>
           </md-input-container> -->

           </div>
   
<div style="margin: 10px 10px;">

    <table class="wareTable" style="width:100%;">
      <thead>
      <tr class="tr">
		<th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('warehouseName')">Warehouse Name
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='warehouseName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='warehouseName' && !reverse"  flex></md-icon>
		</th>
		<th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('warehouseLoc')">Warehouse Location
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='warehouseLoc' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='warehouseLoc' && !reverse"  flex></md-icon>
		</th>
		<th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('wRegion')">Region
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='wRegion' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='wRegion' && !reverse"  flex></md-icon>
		</th>
<!-- 		<th style="text-align: left;">Organization Id</th> -->
		<th style="text-align: left;"></th>
	  </tr>
      </thead>
      <tbody>
		<tr dir-paginate="wrhouse in warehouses  | orderBy:variable:reverse | filter:warehouse_id |itemsPerPage:10 " class="tr">

			<td style="text-align: left;">{{wrhouse.warehouseName}}</td>
			<td style="text-align: left;">{{wrhouse.warehouseLoc}}</td>
			<td style="text-align: left;">{{wrhouse.wRegion}}</td>
<!-- 			<td style="text-align: left;">{{wrhouse.organisationId}}</td> -->
			<td style="text-align: left;">
<md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="editWare($event,wrhouse)"></md-icon>
<md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="delete_wrh(wrhouse)"></md-icon>
		   </td>
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
</body>
