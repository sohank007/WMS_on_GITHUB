<md-dialog  layout="column" class="AddOrderClass" flex id="addOrder">

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Order</h2></div>

      <form name="AddOrderForm" novalidate>
      
      <div  layout="row" style="margin: 0px 10px;    height: 60px; ">
                      <md-input-container  flex style="margin:16px 10px 0px;"> 
         <label>Order Date</label>
         <input type="date"  class="orderDate"
			ng-model="order.orderDate" disabled>		
<!--          <input ng-model="order.orderDate" id="orderDate" disabled style="border: none;
             border-bottom: 1px solid gray;"> -->
         </md-input-container>
</div>
      <div  layout="row" style="margin: 10px 10px 0px;height:60px; ">
         <md-autocomplete 
          md-search-text="warehouseName"
          md-selected-item="warehouse1"
          md-selected-item-change="passW(warehouse1)"
          md-items="warehouse in warehouses | filter:warehouseName"
          md-item-text="warehouse.warehouseName"
          md-min-length="0"
           md-floating-label="Warehouse" flex="50">
        <md-item-template>
          <span md-highlight-text="warehouseName" md-highlight-flags="^i">{{warehouse.warehouseName}}</span>
        </md-item-template>
      </md-autocomplete>
      
               <md-autocomplete 
          md-search-text="contractorName"
          md-selected-item="contractor1"
          md-selected-item-change="passC(contractor1)"
          md-items="contractor in contractors | filter:contractorName"
          md-item-text="contractor.ctrName"
          md-min-length="0"
           md-floating-label="Contractor" flex="50">
        <md-item-template>
          <span md-highlight-text="contractorName" md-highlight-flags="^i">{{contractor.ctrName}}</span>
        </md-item-template>
      </md-autocomplete>
      
<!--       
           <md-input-container  flex="50" style="margin:0px 10px;"> 
          <label>Warehouse</label>
          <md-select ng-model="warehouse1"  aria-label="wareId" name="warehouseId"
             	ng-change="passW(warehouse1)" ng-disabled="disableW"  required>
              <md-option ng-repeat="warehouse in warehouses" ng-value="warehouse"  >{{warehouse.warehouseName}}
              </md-option>
            </md-select>
              <div ng-messages="AddOrderForm.warehouseId.$error">
			            <div ng-message="required">Warehouse is required.</div>
          </div>
        </md-input-container> -->
<!--         
          <md-input-container  flex="50" style="margin:0px 10px;"> 
           <label>Contractor</label>
             <md-select ng-model="order.contractorID" ng-change="passC(order.contractorID)" name="ctrId"
               aria-label="contrId" ng-disabled="disableC" required>
              <md-option ng-repeat="contractor in contractors" ng-value="contractor.ctrId" 
             >{{contractor.ctrName}}
              </md-option>
            </md-select>
             <div ng-messages="AddOrderForm.ctrId.$error">
			            <div ng-message="required">Contractor is required.</div>
          </div>
           </md-input-container> -->
             </div>  
                 <div layout="row" style="margin: 0px 10px;height: 60px; ">
  <md-autocomplete 
          md-search-text="categoryName"
          md-selected-item="categoryObj"
          md-selected-item-change="passCat(categoryObj)"
          md-items="cat in categories | filter:categoryName"
          md-item-text="cat.categoryName"
          md-min-length="0"
           md-floating-label="Category" flex="50">
        <md-item-template>
          <span md-highlight-text="categoryName" md-highlight-flags="^i">{{cat.categoryName}}</span>
        </md-item-template>
      </md-autocomplete>
      
      
        <md-autocomplete 
          md-search-text="materialName" md-no-cache="true"
          md-selected-item="material1"
          md-selected-item-change="pass()"
          md-items="material in mtrls | filter:materialName"
          md-item-text="material.materialName"
          md-min-length="0"
           md-floating-label="Material" flex="50">
        <md-item-template>
          <span md-highlight-text="materialName" md-highlight-flags="^i">{{material.materialName}}</span>
        </md-item-template>
      </md-autocomplete>
    
     </div> 
      
      

<!--       <md-input-container  flex="50" style="margin:0px 10px;"> 
      <label>Material</label>
       <md-select ng-model="orderDetail.materialId" name="mtrlId"
          				ng-change="pass(orderDetail.materialId)" aria-label="mtrId" required>
              		<md-option ng-repeat="material in mtrls" ng-value="material.materialID">{{material.materialName}}
              		</md-option>
            		</md-select>
            		<div ng-messages="AddOrderForm.mtrlId.$error" md-auto-hide="true">
			            <div ng-message="required">Material is required.</div>
			            </div>
      
      </md-input-container> -->
         <div layout="row" style="margin: 5px 10px;height: 60px; ">
          <md-input-container  flex="50" style="margin:0px;">
        <label>Material Code</label>
         <input ng-model="orderDetail.materialCode" disabled id="mtrCode" >
        </md-input-container>
        
              <md-input-container  flex="50" style="margin:0px;">
       <label >Unit of Measure</label>
		<input ng-model="orderDetail.unitMeasure" disabled id="unitMeasure" >
		</md-input-container> 
		
		       
            </div>
            
            <div layout="row" style="margin: 5px 10px;height:60px;">
            
		      <md-input-container  flex="50" style="margin:0px;">
        <label>Available Quantity</label>
         <input ng-model="availableQty" disabled id="availQty" >
        </md-input-container>      

      <md-input-container  flex="50" style="margin:0px;">
            <label>Order Quantity</label>
		<input ng-model="orderDetail.orderQty" name="orderQty" type="number" ng-disabled="enableMaterialQ" 
            		ng-change="someVar(orderDetail.orderQty)" required max="{{availableQty}}" min="1">
          <div ng-messages="AddOrderForm.orderQty.$error" md-auto-hide="true">
			            <div ng-message="required">Quantity is required.</div>
			            <div ng-message="max">Reduce order Quantity.</div>
			            <div ng-message="min">Minimum order quantity should be 1.</div>
          </div>   		
            </md-input-container>
      </div>
      <div layout="row" layout-align="center center">
       <md-input-container   style="margin: 0px 10px;">       
             <md-button type="Submit" class="md-raised" 
              ng-disabled="AddOrderForm.$invalid" 
             ng-click="addOrder()" aria-label="subM">Add Material</md-button>
            <md-button type="Submit" class="md-raised" 
             ng-click="submit()" ng-show="onClickOfDone" aria-label="submitO">Order</md-button>
            <md-button type="Submit" class="md-raised" 
			aria-label="cancelO" ng-click="cancel()">Cancel</md-button>
</md-input-container>
</div>

<!--           <div  style="margin:30px 10px 0px;" layout="row">
       
              <md-input-container id="addOrder"> 
       
            		    <span style="color:red;font-size: x-small;" ng-if="mOverflow">Reduce material Quantity</span>
            			<span style="color:red;font-size: x-small;" ng-show="AddOrderForm.orderQty.$touched && 
            			AddOrderForm.orderQty.$invalid">Enter Material Quantity</span>
       </md-input-container>
       
       <md-input-container id="addOrder"> 
		
       </md-input-container>
       
              <md-input-container id="addOrder"> 
		
       </md-input-container>
       

             <div ng-messages="AddOrderForm.orderQty.$error"  >
               <div ng-message="validationError" >reduce material Quantity</div>
            </div>

          </div> -->
<div style="margin: 5px 10px;">
            <table class="table" style="width:100%;padding: 0px;margin: 0px;" >
      <thead>
      <tr class="tr">
<!-- 		<th>Contractor</th> -->
		<th style="padding: 5px;text-align: left;width:25%;">Material</th>
<!-- 		<th>Material</th> -->
		<th style="padding: 5px;text-align: left;width:25%;">Unit of Measure</th>
		<th style="padding: 5px;text-align: right;width:25%;">Unit Price</th>
		<th style="padding: 5px;text-align: right;width:25%;">Order Quantity</th>
 		

	  </tr>
      </thead>
      <tbody>
		<tr dir-paginate="ordr in orderDetails | itemsPerPage:2" class="tr">
			<td style="padding: 5px;text-align: left;width:25%;">{{ordr.materialName}}</td>
<!-- 			<td>{{ordr.contractorID}}</td> -->
			<td style="padding: 5px;text-align: left;width:25%;">{{ordr.unitMeasure}}</td>
			<td style="padding: 5px;text-align: right;width:25%;">{{ordr.unitPrice}}</td>
			<td style="padding: 5px;text-align: right;width:25%;">{{ordr.orderQty}}</td>
			

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
 </div>   
    
      </form>
</md-dialog>