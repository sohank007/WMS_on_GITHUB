<md-dialog ng-app="WMS" layout="column" ng-cloak="" >

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2>Order Details</h2>
   </div>

<!--           <md-input-container class="md-block" flex-gt-sm="" >
            <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Order Details </label>
            <input style="width:40%;" ng-model="Order_id">
          </md-input-container>     --> 

<div style="margin:10px 10px;">
    <table class="table" style="width:100%;">
      <thead>
         <tr class="tr">
<!--             <th>Order Id</th> -->
           <th style="text-align: left;">Material Name</th>
           <th style="text-align: right;">Order Quantity</th>
<!--            <th>Return Quantity</th> -->
           <th style="text-align: right;">Issue date</th>
<!--            <th>Return Date</th> -->
        </tr>
      </thead>
      <tbody>
<tr ng-repeat="orderDet in orderDetail " class="tr">        

<!--     <td>{{orderDet[0]}}</td> -->
    <td style="text-align: left;">{{orderDet[1]}}</td>
    <td style="text-align: right;">{{orderDet[2]}}</td>
<!--     <td>{{orderDet[3]}}</td> -->
    <td style="text-align: right;">{{orderDet[4] | date : 'dd/MM/yyyy' }}</td>
<!--     <td>{{orderDet[5] | date : 'yyyy-MM-dd' }}</td> -->
  </tr>
    </tbody>
  </table>
</div>

</md-dialog>