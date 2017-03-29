<md-dialog class="AddInwardClass" layout="column" id="addInward">

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Inward</h2></div>

	<form name="AddInwardForm">
		<md-input-container  flex style="margin:16px 10px 0px;"> 
	<label>Inward Date</label>
	<input ng-model="inward.inDate" type="date" disabled="disabled">
	</md-input-container>
	
	<div  layout="row" style="margin: 0px 10px;height: 60px; ">
	
	   <md-autocomplete ng-disabled="disableW" 
          md-search-text="warehouseName"
          md-selected-item="warehouse1"
          md-selected-item-change="sendW(warehouse1)"
          md-items="warehouse in warehouses | filter:warehouseName"
          md-item-text="warehouse.warehouseName"
          md-min-length="0"
           md-floating-label="Warehouse" flex="50">
        <md-item-template>
          <span md-highlight-text="warehouseName" md-highlight-flags="^i">{{warehouse.warehouseName}}</span>
        </md-item-template>
      </md-autocomplete>
      
        <md-autocomplete 
          md-search-text="categoryName"
          md-selected-item="allCat"
          md-selected-item-change="addCat(allCat)"
          md-items="cat in categories | filter:categoryName"
          md-item-text="cat.categoryName"
          md-min-length="0"
           md-floating-label="Category" flex="50">
        <md-item-template>
          <span md-highlight-text="categoryName" md-highlight-flags="^i">{{cat.categoryName}}</span>
        </md-item-template>
      </md-autocomplete>
      
	       <!--   <md-input-container  flex="50" style="margin:20px 10px 0px;"> 
	         <label>Warehouse</label>
	         <md-select
				ng-model="inward.warehouseId" name="warehouseId" required
				 ng-change="sendW(inward.warehouseId)" > <md-option
				ng-repeat="warehouse in warehouses" ng-value="warehouse.warehouseId">{{warehouse.warehouseName}}
			</md-option> </md-select>
			     <div ng-messages="AddInwardForm.warehouseId.$error">
			            <div ng-message="required">Warehouse is required.</div>
          </div>
	         </md-input-container> -->

	</div>
	
	<div layout="row" style="margin: 0px 10px;height: 60px; ">
	
<!-- 	<md-input-container style="margin:10px 10px 0px;" flex="50">
		<label>Category</label>
		<md-select ng-model="allCat" ng-change="addCat(allCat)" name="categoryName" required> 
		<md-option ng-repeat="category in categories" ng-value="category.categoryId">{{category.categoryName}}</md-option> 
		</md-select>
		<div ng-messages="AddInwardForm.materialName.$error">
            <div ng-message="required">Category is required.</div>
          </div>
	</md-input-container> -->
	
	 <md-autocomplete style="margin-right:0px;" id="id"
          md-search-text="materialName" md-no-cache="true"
          md-selected-item="all"
          md-selected-item-change="addM(all)"
          md-items="material in materials | filter:materialName"
          md-item-text="material.materialName"
          md-min-length="0"
           md-floating-label="Material" flex="50">
        <md-item-template>
          <span md-highlight-text="materialName" md-highlight-flags="^i">{{material.materialName}}</span>
        </md-item-template>
      </md-autocomplete>
      
	<md-input-container style="margin:0px;" flex="50">
		<label>Material Code</label>
		<input ng-model="inwardDetails.materialCode" disabled="disabled">
		</md-input-container>
	
<!-- 	<md-input-container style="margin:10px 10px 0px;" flex="50">
		<label>Material</label>
		<md-select ng-model="all" ng-change="addM(all)" name="materialName" required> 
		<md-option ng-repeat="material in materials" ng-value="material">{{material.materialName}}</md-option> 
		</md-select>
		<div ng-messages="AddInwardForm.materialName.$error">
            <div ng-message="required">Material is required.</div>
          </div>
	</md-input-container> -->
	
	</div>
	
		<div layout="row" style="margin: 0px 10px;height: 60px; ">		
		<md-input-container style="margin:0px;" flex="50">
		<label>Unit of Measure</label>
		<input ng-model="inwardDetails.unitOfMeasure" disabled="disabled">
		</md-input-container>
		
		
		<md-input-container style="margin:0px;" flex="50">
		<label>Unit Price</label>
		<input ng-model="inwardDetails.unitPrice" disabled="disabled">
		</md-input-container>
		
		
		</div>	
			
		<md-input-container style="margin:0px 10px;" flex>
		<label >Quantity</label>
		<input ng-model="inwardDetails.inQty" ng-disabled="mNotSelected" name="inQty" type="number" required
		>
		<div ng-messages="AddInwardForm.inQty.$error">
            <div ng-message="required">Quantity is required.</div>
        <!--     <div ng-message="md-maxlength">Quantity must be less than 5 char long.</div> -->
          </div>
		</md-input-container>




	  <div   layout="row" layout-align="center center"> 
	<md-input-container style="margin: 0px 10px;"> 	
<!-- 		<md-button type="Submit" class="md-raised" style="color: gray"
			ng-disabled="dUWare" ng-click="addNewMaterial()">Add Material</md-button> -->
		<md-button type="Submit" class="md-raised" 
			ng-disabled="AddInwardForm.inQty.$invalid" ng-click="addMaterial()">Add Material</md-button>
		<md-button type="Submit" class="md-raised"
			ng-show="whenAddMaterialClick" ng-click="submit()">Submit</md-button>
		<md-button type="Submit" class="md-raised"
			ng-click="cancel()">Cancel</md-button>
			</md-input-container>
	</div>   
	




 <!--             <div ng-messages="AddInwardForm.warehouseId.$error" role="alert">
              <div ng-message="required" >Warehouse is required.</div>
            </div> --> 

		


		

		<table class="table" style="width: 96%;margin: 10px;" >
			<thead>
				<tr class="tr" style="height: 5px;">
					<th style="text-align: left;">Material</th>
					<th style="text-align: left;">Unit of Measure</th>
					<th style="text-align:right;">Unit price</th>
					<th style="text-align: right;">Material Quantity</th>
					

				</tr>
			</thead>
			<tbody>
				<tr dir-paginate="inwa in inwDetails | itemsPerPage:2" class="tr">
					<td style="text-align: left;">{{inwa.materialName}}</td>
					<td style="text-align: left;">{{inwa.unitOfMeasure}}</td>
					<td style="text-align: right;">{{inwa.unitPrice}}</td>
					<td style="text-align: right;">{{inwa.inQty}}</td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: right;margin: 0px 20px;">	
			<dir-pagination-controls 
       max-size="5"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls>	
		</div>
	</form>

</md-dialog>



<!-- <md-dialog class="AddInwardClass" layout="column" id="addInward">

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Inward</h2></div>

	<form name="AddInwardForm">
	<div  layout="row">
	         <md-input-container  flex="50" style="margin:20px 10px 0px;"> 
	         <label>Warehouse</label>
	         <md-select
				ng-model="inward.warehouseId" name="warehouseId" required
				 ng-change="sendW(inward.warehouseId)" ng-disabled="disableW"> <md-option
				ng-repeat="warehouse in warehouses" ng-value="warehouse.warehouseId">{{warehouse.warehouseName}}
			</md-option> </md-select>
			     <div ng-messages="AddInwardForm.warehouseId.$error">
			            <div ng-message="required">Warehouse is required.</div>
          </div>
	         </md-input-container>
	<md-input-container  flex="50" style="margin:20px 10px 0px;"> 
	<label>Inward Date</label>
	<input ng-model="inward.inDate" type="date" disabled="disabled">
	</md-input-container>
	
	</div>
	
		<div layout="row" >
		
		<md-input-container style="margin:10px 10px 0px;" flex="50">
		<label>Material</label>
		<md-select ng-model="all" ng-change="addM(all)" name="materialName" required> 
		<md-option ng-repeat="material in materials" ng-value="material">{{material.materialName}}</md-option> 
		</md-select>
		<div ng-messages="AddInwardForm.materialName.$error">
            <div ng-message="required">Material is required.</div>
          </div>
		</md-input-container>
				<md-input-container style="margin:10px 10px 0px;" flex="50">
		<label>Unit of Measure</label>
		<input ng-model="inwardDetails.unitOfMeasure" disabled="disabled">
		</md-input-container>
			</div>	
			
		<md-input-container style="margin:10px 10px 0px;" flex>
		<label >Quantity</label>
		<input ng-model="inwardDetails.inQty" ng-disabled="mNotSelected" name="inQty" type="number" required
		>
		<div ng-messages="AddInwardForm.inQty.$error">
            <div ng-message="required">Quantity is required.</div>
            <div ng-message="md-maxlength">Quantity must be less than 5 char long.</div>
          </div>
		</md-input-container>




	  <div   layout="row" layout-align="center center"> 
	<md-input-container style="margin: 0px 10px;"> 	
		<md-button type="Submit" class="md-raised" style="color: gray"
			ng-disabled="dUWare" ng-click="addNewMaterial()">Add Material</md-button>
		<md-button type="Submit" class="md-raised" 
			ng-disabled="AddInwardForm.inQty.$invalid" ng-click="addMaterial()">Add Material</md-button>
		<md-button type="Submit" class="md-raised"
			ng-show="whenAddMaterialClick" ng-click="submit()">Submit</md-button>
		<md-button type="Submit" class="md-raised"
			ng-click="cancel()">Cancel</md-button>
			</md-input-container>
	</div>   
	




             <div ng-messages="AddInwardForm.warehouseId.$error" role="alert">
              <div ng-message="required" >Warehouse is required.</div>
            </div> 

		


		

		<table class="table" style="width: 96%;margin: 10px;" >
			<thead>
				<tr class="tr" style="height: 5px;">
					<th style="text-align: left;">Material</th>
					<th style="text-align: left;">Unit of Measure</th>
					<th style="text-align:right;">Unit price</th>
					<th style="text-align: right;">Material Quantity</th>
					

				</tr>
			</thead>
			<tbody>
				<tr dir-paginate="inwa in inwDetails | itemsPerPage:2" class="tr">
					<td style="text-align: left;">{{inwa.materialName}}</td>
					<td style="text-align: left;">{{inwa.unitOfMeasure}}</td>
					<td style="text-align: right;">{{inwa.unitPrice}}</td>
					<td style="text-align: right;">{{inwa.inQty}}</td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: right;margin: 0px 20px;">	
			<dir-pagination-controls 
       max-size="5"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls>	
		</div>
	</form>

</md-dialog> -->