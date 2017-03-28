<md-dialog ng-app="WMS" ng-cloak="" class="AddInwardClass" layout="column" id="addInward">

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Inward</h2></div>

	<div layout="column">
	<form name="AddInwardForm">
	<div style="margin: 0px 10px;height: 70px;" layout="row">
	         <md-input-container  flex="50"> 
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
	<md-input-container  flex="50"> 
	<label>Inward Date</label>
	<input ng-model="inward.inDate" type="date" disabled="disabled">
	</md-input-container>
	
	</div>
	       
	<md-input-container  layout="row" style="margin: 0px 10px;"> 	
<!-- 		<md-button type="Submit" class="md-raised" style="color: gray"
			ng-disabled="dUWare" ng-click="addNewMaterial()">Add Material</md-button> -->
		<md-button type="Submit" class="md-raised" style="color: gray;"
			ng-disabled="AddInwardForm.inQty.$invalid" ng-click="addMaterial()">Add</md-button>
		<md-button type="Submit" class="md-raised" style="color: gray;"
			ng-show="whenAddMaterialClick" ng-click="submit()">Submit</md-button>
		<md-button type="Submit" class="md-raised" style="color: gray;"
			ng-click="cancel()">Cancel</md-button>
			</md-input-container>
	
	




 <!--             <div ng-messages="AddInwardForm.warehouseId.$error" role="alert">
              <div ng-message="required" >Warehouse is required.</div>
            </div> --> 

		


		<div layout="row" style="margin: 20px 10px 5px;height: 100px;">
		
		<md-input-container style="margin:0px 10px;" flex="30">
		<label>Material</label>
		<md-select ng-model="all" ng-change="addM(all)" name="materialName" required> 
		<md-option ng-repeat="material in materials" ng-value="material">{{material.materialName}}</md-option> 
		</md-select>
		<div ng-messages="AddInwardForm.materialName.$error">
            <div ng-message="required">Material Name is required.</div>
          </div>
		</md-input-container>
		
		<md-input-container style="margin:0px 10px;" flex="30">
		<label >Quantity</label>
		<input ng-model="inwardDetails.inQty" ng-disabled="mNotSelected" name="inQty" type="number" required>
		<div ng-messages="AddInwardForm.inQty.$error">
            <div ng-message="required">Material quantity is required.</div>
          </div>
		</md-input-container>

		<md-input-container style="margin:0px 10px;" flex="20">
		<label>Unit of Measure</label>
		<input ng-model="inwardDetails.unitOfMeasure" disabled="disabled">
		</md-input-container>

		</div>
		
	<div style="margin:0px 10px; height: 130px;">
		<table class="table" style="width: 100%;" ng-hide="inwDetails.length==0">
			<thead>
				<tr class="tr">
					<th>Material</th>
					<th>Material Quantity</th>

				</tr>
			</thead>
			<tbody>
				<tr dir-paginate="inwa in inwDetails | itemsPerPage:2" class="tr">
					<td>{{inwa.materialName}}</td>
					<td>{{inwa.inQty}}</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div style="height: 40px;width: 40px;"></div>
		<dir-pagination-controls 
       max-size="5"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls>

	</form>
</div>
</md-dialog>