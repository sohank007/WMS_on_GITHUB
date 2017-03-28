<div  >
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Invoice</h2>
   </div>

        <div  layout="row" style="margin: 0px 10px;height:50px;" flex>
        <md-input-container style="margin: 10px 10px 0px;" flex="20">
          <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search invoice</label>
          <input  ng-model="invoice" type="text" >
        </md-input-container>
        
<!--        <md-input-container style="margin: 0px 10px;padding: 0px;display: inline-block; float: right;
                 text-align: right;height:50px;" flex>
        <dir-pagination-controls  
       max-size="2"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls>
        </md-input-container>   -->   
        
        </div>
   
<div style="margin: 10px 10px;">
    <table class="invoiceTable" style="width: 100%;">
      <thead>
      <tr class="tr">
<!--       <th></th> -->
      <th style="text-align: right;width: 100px;outline: 0px;padding-right: 40px;" ng-click="sortingMaterial('invoiceId')">Invoice No
            <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='invoiceId' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='invoiceId' && !reverse"  flex></md-icon>
      </th>
            <th style="text-align: left;padding-left: 50px;width:20%;outline: 0px;" ng-click="sortingMaterial('contractor.ctrName')">
            Contractor Name
             <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='contractor.ctrName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='contractor.ctrName' && !reverse"  flex></md-icon>     
            </th>
      <th style="width: 25%;text-align: right;outline: 0px;padding-right: 40px;" ng-click="sortingMaterial('invoiceDate')">Invoice Date
            <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='invoiceDate' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='invoiceDate' && !reverse"  flex></md-icon>
      </th>
      
      <th style="width: 25%;text-align: right;outline: 0px;padding-right: 40px;" ng-click="sortingMaterial('validDate')">Due Date
            <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='validDate' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='validDate' && !reverse"  flex></md-icon>
      </th>
<!--       <th>Discount</th> -->

<!--       <th>Orders</th> -->
      <th style="text-align: right;width:150px;outline: 0px;padding-right: 40px;" ng-click="sortingMaterial('invoiceAmtTotal')">Bill Amount
            <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='invoiceAmtTotal' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='invoiceAmtTotal' && !reverse"  flex></md-icon>
    </th>
  	  </tr>
      </thead>
      <tbody>
	  <tr dir-paginate="invoice in invoices | filter:invoice |  orderBy:variable:reverse  |itemsPerPage:10" class="tr" ng-click="sendInvoiceId(invoice.invoiceId)"> 
<!-- 			<td><input type="checkbox" ng-checked="orders.indexOf(order.orderId) > -1" style="display:inline-block;"
			 ng-click="addToInvoice(order.orderId)">{{order.orderId}}
          </td> -->
<!--     <td >{{order.orderId}}</td> -->
<td  style="text-align: right;width: 100px;">{{invoice.sequenceId}}</td>
    <td style="text-align: left;padding-left: 50px;width:20%;">{{invoice.contractor.ctrName}}</td>
    <td style="width: 25%;text-align: right;">{{invoice.invoiceDate | date:'dd/MM/yyyy'}}</td>
    <td style="width: 25%;text-align: right;">{{invoice.validDate | date:'dd/MM/yyyy'}}</td>
<!--     <td>{{invoice.discount | number : 2}}</td> -->

<!--         <td>{{invoice.purchaseOrder}}</td> -->
        <td style="text-align: right;width: 150px;">{{invoice.invoiceAmtTotal | number: 2}}</td>
<!--     <td><md-button type="Submit" class="md-raised" style="color: gray" ng-click="viewO($event,order.orderId)"  
    ng-cloak="" >View Details</md-button></td> -->
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
<!-- <br></br>
    <table class="table" style="width: 100%" ng-show="whenInvoiceClicked">
      <thead>
      <tr class="tr">
      <th></th>
      <th>Invoice No</th>
      <th>Material</th>
      <th>Unit Price</th>
      <th>MaterialQuantity</th>
      <th>Total amount</th>
  	  </tr>
      </thead>
      <tbody>
	  <tr ng-repeat="invoiceDetail in invoiceDetails" class="tr" > 
			<td><input type="checkbox" ng-checked="orders.indexOf(order.orderId) > -1" style="display:inline-block;"
			 ng-click="addToInvoice(order.orderId)">{{order.orderId}}
          </td>
    <td >{{order.orderId}}</td>
<td>{{invoiceDetail.invoice.sequenceId}}</td>
    <td>{{invoiceDetail.material.materialName}}</td>
    <td>{{invoiceDetail.material.unitPrice}}</td>
    <td>{{invoiceDetail.quantity}}</td>
    <td>{{invoiceDetail.totalAmount | number: 2}}</td>

    <td><md-button type="Submit" class="md-raised" style="color: gray" ng-click="viewO($event,order.orderId)"  
    ng-cloak="" >View Details</md-button></td>
  	 </tr>
    </tbody>
  </table>
 -->
</div>
