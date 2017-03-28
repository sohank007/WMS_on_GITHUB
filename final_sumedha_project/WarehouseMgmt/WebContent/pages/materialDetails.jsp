<div layout="column" >
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Materials</h2>
   </div>
   
   <div  layout="row" style="margin: 20px 10px 0px;height:50px;" flex>
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
   
		 <!--   <md-input-container style="margin: 10px 10px 0px;" flex="15">
		  	 	<label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Material</label>
		   		<input type="text" ng-model="material_id">
		   </md-input-container> -->
		   
  		 <md-input-container layout="row" style="margin: -10px 10px;" flex>     
				<md-button class="md-raised" type="Submit" 
				 ng-click="addMtr($event)">Add Material</md-button>
           </md-input-container>  
            
<!-- 		   <md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex="20">
                 <dir-pagination-controls  
			       max-size="2"
			       direction-links="true"
			       boundary-links="true" >
   				 </dir-pagination-controls>
           </md-input-container> -->
           </div>

<div style="margin:10px 10px;">
<table style="width:100%;" class="mtrlTable">
<thead>
	<tr class="tr">
	<th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('category.categoryName')">Category
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='category.categoryName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='category.categoryName' && !reverse"  flex></md-icon>
    </th>
		<th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('materialName')">Material
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='materialName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='materialName' && !reverse"  flex></md-icon>
    </th>
    		<th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('materialCode')">Material Code
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='materialCode' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='materialCode' && !reverse"  flex></md-icon>
    </th>
		<th style="text-align: left;outline: 0px;" ng-click="sortingMaterial('materialDesc')">Description
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='materialDesc' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='materialDesc' && !reverse"  flex></md-icon>
    </th>
    
		<th  style="text-align: left;outline: 0px;" ng-click="sortingMaterial('unitOfMeasure')">Unit Of Measure
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='unitOfMeasure' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='unitOfMeasure' && !reverse"  flex></md-icon>
    </th>
    
		<th style="text-align: right;outline: 0px;" ng-click="sortingMaterial('unitPrice')">Unit Price 
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='unitPrice' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='unitPrice' && !reverse"  flex></md-icon>
    </th>
<!--     <th style="text-align: left;">organization Id</th> -->
		<th style="text-align: right;"> </th>
	</tr>
	</thead>
	<tbody>
	<tr dir-paginate="material in material_details | orderBy:variable:reverse | filter:categoryName | filter:materialCode
	  | filter:material_id | itemsPerPage: 10" class="tr" ng-click="ViewMaterialDetails($event,material.materialID)">
	  <td style="text-align: left;">{{material.category.categoryName}}</td>
		<td style="text-align: left;">{{material.materialName}}</td>
		<td style="text-align: left;">{{material.materialCode}}</td>
		<td style="text-align: left;">{{material.materialDesc}}</td>
		<td style="text-align: left;">{{material.unitOfMeasure}}</td>
		<td style="text-align: right;padding-right: 20px;">{{material.unitPrice | number:2}}</td>
<!-- 		<td style="text-align: left;">{{material.organisationId}}</td> -->
		<td style="text-align: right;" ng-click="$event.stopPropagation()">
		<md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="edit_material($event,material)"></md-icon>
		<md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="delete_material(material)" ></md-icon></md-button>
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
</div>

