<div layout="column" >
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Category Details</h2>
   </div>
   
   <div  layout="row" style="margin: 0px 10px;height:50px;" flex>
		   <md-input-container style="margin: 10px 10px 0px;" flex="20">
		  	 	<label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Category</label>
		   		<input type="text" ng-model="material_id">
		   </md-input-container>
		   
  		 <md-input-container layout="row" style="margin: 0px 10px;" flex>     
				<md-button class="md-raised" type="Submit" 
				 ng-click="addCategory($event)">Add Category</md-button>
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
<!-- add table css here -->
<table style="width:100%;" class="mtrlTable">
<thead>
	<tr class="tr">
		<th style="text-align: left;outline: 0px;"  ng-click="sortingCategory('categoryName')">Category
		<!-- <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='materialName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='materialName' && !reverse"  flex></md-icon> -->
    </th>
    
		<th style="text-align: left;outline: 0px;" ng-click="sortingCategory('categoryDesc')">Description
<!-- 		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='materialDesc' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='materialDesc' && !reverse"  flex></md-icon> -->
    </th>
    
		<th  style="text-align: left;outline: 0px;" ng-click="sortingCategory('categoryStatus')">Status
<!-- 		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='unitOfMeasure' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='unitOfMeasure' && !reverse"  flex></md-icon> -->
    </th>
    
<!-- 		<th style="text-align: right;outline: 0px;" ng-click="sortingCategory('unitPrice')">Unit Price 
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='unitPrice' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='unitPrice' && !reverse"  flex></md-icon>
    </th> -->
<!--     <th style="text-align: left;">organization Id</th> -->
		<th style="text-align: right;"> </th>
	</tr>
	</thead>
	<tbody>
	<tr dir-paginate="category in category_details | orderBy:variable:reverse  | filter:category_id | itemsPerPage: 10" class="tr">
		<td style="text-align: left;">{{category.categoryName}}</td>
		<td style="text-align: left;">{{category.categoryDesc}}</td>
		<td style="text-align: left;">{{category.categoryStatus}}</td>
		<!-- <td style="text-align: right;padding-right: 20px;">{{category.unitPrice | number:2}}</td> -->
<!-- 		<td style="text-align: left;">{{material.organisationId}}</td> -->
		<td style="text-align: right;">
		<md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="edit_category($event,category)"></md-icon>
		<md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="delete_category(category)" ></md-icon></md-button>
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