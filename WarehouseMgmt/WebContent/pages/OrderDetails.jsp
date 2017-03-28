<md-dialog layout="column"  style="width:700px;min-width: 700px;">

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2>Order Details</h2>
   </div>

<!--           <md-input-container class="md-block" flex-gt-sm="" >
            <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Order Details </label>
            <input style="width:40%;" ng-model="Order_id">
          </md-input-container>     --> 


<div style="margin: 0px 10px;">
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



<md-input-container>
<md-button type="Submit" class="md-raised" 
               ng-click="showData()" ng-disabled="orderMasterInvoiceStatus">Generate Invoice</md-button>
</md-input-container>
 </div>
<div style="margin:10px 10px;">
    <table class="table" style="width:100%;">
      <thead>
         <tr class="tr">
 <!--            <th>Order Id</th> -->
 			<th style="text-align: left;">Category</th>
           <th style="text-align: left;">Material Name</th>
           <th style="text-align: left;">Material Description</th>
           <th style="text-align: left;">Unit of measure</th>
           <th style="text-align: right;">Unit Price</th>
           <th style="text-align: left;">Status</th>
           <th style="text-align: right;">Order Quantity</th>
<!--            <th>Return Quantity</th> -->
<!--            <th style="text-align: right;">Issue date</th> -->
<!--            <th>Return Date</th> -->
        </tr>
      </thead>
      <tbody>
<tr ng-repeat="orderDet in orderDetail " class="tr">        

<!--     <td>{{orderDet.order.orderId}}</td> -->
        <td style="text-align: left;">{{orderDet.material.category.categoryName}}</td>
        <td style="text-align: left;">{{orderDet.material.materialName}}</td>
        <td style="text-align: left;">{{orderDet.material.materialDesc}}</td>
        <td style="text-align: left;">{{orderDet.material.unitOfMeasure}}</td>
        <td style="text-align: right;">{{orderDet.material.unitPrice | number:2}}</td>
    	<td style="text-align: left;">{{orderDet.invoiceStatus}}</td>
    <td style="text-align: right;">{{orderDet.orderQty | number:2}}</td>
<!--     <td>{{orderDet[3]}}</td> -->
<!--     <td style="text-align: right;">{{orderDet[4] | date : 'dd/MM/yyyy' }}</td> -->
<!--     <td>{{orderDet[5] | date : 'yyyy-MM-dd' }}</td> -->
  </tr>
    </tbody>
  </table>
</div>

</md-dialog>