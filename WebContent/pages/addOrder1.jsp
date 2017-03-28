<md-dialog ng-app="WMS" ng-cloak layout="column" class="AddOrderClass" flex>

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Order</h2></div>

      <form name="AddOrderForm" novalidate>
      <div  style="margin:30px 10px 0px;" layout="row">
              <md-input-container id="addOrder">
          <label>Warehouse</label>
          <md-select ng-model="warehouse1"  aria-label="wareId" 
             	ng-change="passW(warehouse1)" ng-disabled="disableW"  required>
              <md-option ng-repeat="warehouse in warehouses" ng-value="warehouse"  >{{warehouse.warehouseName}}
              </md-option>
            </md-select>
        </md-input-container>
           <md-input-container id="addOrder">
           <label>Contractor</label>
             <md-select ng-model="order.contractorID" ng-change="passC(order.contractorID)" 
               aria-label="contrId" ng-disabled="disableC" required>
              <md-option ng-repeat="contractor in contractors" ng-value="contractor.ctrId" 
             >{{contractor.ctrName}}
              </md-option>
            </md-select>
           </md-input-container>
         <md-input-container id="addOrder">
         <label>Order Date</label>
         <input type="date"  class="orderDate"
			ng-model="order.orderDate" disabled>		
<!--          <input ng-model="order.orderDate" id="orderDate" disabled style="border: none;
             border-bottom: 1px solid gray;"> -->
         </md-input-container>
      </div>  
      
        <md-input-container id="addOrder">       
 <md-button type="Submit" class="md-raised" style="color: gray;" ng-disabled="chWare" ng-click="addNewOrder()" aria-label="addnewOrder">Add Material</md-button>
             <md-button type="Submit" class="md-raised" style="color: gray;" ng-disabled="AddOrderForm.orderQty.$invalid || mOverflow" 
             ng-click="addOrder()" aria-label="subM">Done</md-button>
            <md-button type="Submit" class="md-raised" style="color: gray;" ng-click="submit()" ng-show="onClickOfDone" aria-label="submitO">Order</md-button>
            <md-button type="Submit" class="md-raised" style="color: gray;" aria-label="cancelO" ng-click="cancel()">Cancel</md-button>
</md-input-container>


          <div  ng-show="variable" style="margin:30px 10px 0px;" layout="row">
       <md-input-container id="addOrder"> 
       <label>Material</label>
       <md-select ng-model="orderDetail.materialId" 
          				ng-change="pass(orderDetail.materialId)" aria-label="mtrId" required>
              		<md-option ng-repeat="material in mtrls" ng-value="{{material.materialID}}" >{{material.materialName}}
              		</md-option>
            		</md-select>
       </md-input-container>
       
              <md-input-container id="addOrder"> 
       <label>Material Quantity</label>
		<input ng-model="orderDetail.orderQty" name="orderQty" type="number" ng-disabled="enableMaterialQ" 
            		ng-change="someVar(orderDetail.orderQty)" required
            		 style="width:150px;">
<!--             		    <span style="color:red;font-size: x-small;" ng-if="mOverflow">Reduce material Quantity</span>
            			<span style="color:red;font-size: x-small;" ng-show="AddOrderForm.orderQty.$touched && 
            			AddOrderForm.orderQty.$invalid">Enter Material Quantity</span> -->
       </md-input-container>
       
       <md-input-container id="addOrder"> 
		<label >Unit of Measure</label>
		<input ng-model="orderDetail.unitMeasure" disabled id="unitMeasure" 
          		style="width:150px;">
       </md-input-container>
       
              <md-input-container id="addOrder"> 
		<label >Available Quantity</label>
         <input ng-model="availableQty" disabled id="availQty" style="width: 150px;">
       </md-input-container>
       

<!--              <div ng-messages="AddOrderForm.orderQty.$error"  >
               <div ng-message="validationError" >reduce material Quantity</div>
            </div> -->

          </div>
<div style="margin: 10px 10px;">
            <table class="table" style="width:100%;" ng-hide="orderDetails.length==0">
      <thead>
      <tr class="tr">
<!-- 		<th>Contractor</th> -->
		<th>Material</th>
<!-- 		<th>Material</th> -->
		<th>Material Quantity</th>
 		<th>Unit of Measure</th>

	  </tr>
      </thead>
      <tbody>
		<tr ng-repeat="ordr in orderDetails" class="tr">
			<td>{{ordr.materialName}}</td>
<!-- 			<td>{{ordr.contractorID}}</td> -->
			<td>{{ordr.orderQty}}</td>
			<td>{{ordr.unitMeasure}}</td>

		</tr>
     </tbody>
   </table>
 </div>   
    
      </form>

</md-dialog>