<div ng-app="WMS"  layout="column" ng-cloak="">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Material Details</h2>
   </div>
   
   <div layout="row" style="height: 60px;margin-top: 20px;">
   <md-input-container style="margin: 10px 10px 0px;" flex="30">
   <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Material</label>
   <input type="text" ng-model="material_id">
      </md-input-container>
   <md-input-container layout="row" style="margin: 0px 10px;" flex="30">     
<md-button class="md-raised" type="Submit" style="color: gray" ng-click="addMtr($event)">Add Material</md-button>
           </md-input-container>   

           </div>

 



<div style="margin:10px 10px;">
<table style="width:100%;" class="table">
<thead>
	<tr class="tr">
		<th style="text-align: left;">Material</th>
		<th style="text-align: left;">Description</th>
<!-- 		<th>Unit Of Measure</th> -->
		<th style="text-align: right;">Unit Prize</th>
		<th style="text-align: right;"> </th>
	</tr>
	</thead>
	<tbody>
	<tr ng-repeat="material in material_details | orderBy:'-materialID' | filter:material_id" class="tr">
		<td style="text-align: left;">{{material.materialName}}</td>
		<td style="text-align: left;">{{material.materialDesc}}</td>
<!-- 		<td></td> -->
		<td style="text-align: right;">{{material.unitPrice}}/{{material.unitOfMeasure}}</td>
		<td style="text-align: right;">
		<md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="edit_material($event,material)"></md-icon>
		<md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="delete_material(material)" ></md-icon></md-button>
		</td>		
	</tr>
	</tbody>
</table>          
</div>
</div>

