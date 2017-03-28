<div ng-app="WMS" >
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Invoice</h2>
   </div>
<div style="margin: 10px 10px;">
    <table class="table" style="width: 100%">
      <thead>
      <tr class="tr">
<!--       <th></th> -->
      <th>Invoice No</th>
            <th style="text-align: left;width: 150px;">Contractor Name</th>
      <th>Invoice Date</th>
      
      <th>Due Date</th>
<!--       <th>Discount</th> -->

<!--       <th>Orders</th> -->
      <th style="text-align: right;width:150px;">Bill Amount</th>

  	  </tr>
      </thead>
      <tbody>
	  <tr ng-repeat="invoice in invoices | orderBy:'-invoiceId'" class="tr" ng-click="sendInvoiceId(invoice.invoiceId)"> 
<!-- 			<td><input type="checkbox" ng-checked="orders.indexOf(order.orderId) > -1" style="display:inline-block;"
			 ng-click="addToInvoice(order.orderId)">{{order.orderId}}
          </td> -->
<!--     <td >{{order.orderId}}</td> -->
<td>{{invoice.sequenceId}}</td>
    <td style="text-align: left;width: 150px;">{{invoice.contractor.ctrName}}</td>
    <td>{{invoice.invoiceDate | date:'yyyy-MM-dd'}}</td>
    <td style="margin:0px 40px;">{{invoice.validDate | date:'yyyy-MM-dd'}}</td>
<!--     <td>{{invoice.discount | number : 2}}</td> -->

<!--         <td>{{invoice.purchaseOrder}}</td> -->
        <td style="text-align: right;width: 150px;">{{invoice.invoiceAmtTotal | number: 2}}</td>
<!--     <td><md-button type="Submit" class="md-raised" style="color: gray" ng-click="viewO($event,order.orderId)"  
    ng-cloak="" >View Details</md-button></td> -->
  	 </tr>
    </tbody>
  </table>
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
