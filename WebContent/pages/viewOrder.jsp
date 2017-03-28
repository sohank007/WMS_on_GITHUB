<div ng-app="WMS"  flex ng-cloak="" style="overflow:hidden;">
<md-content layout-padding="">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Orders</h2>
   </div>
<div layout="row" style="margin: 0px 10px;height:50px;" flex>
        <md-input-container style="margin: 10px 10px 0px;" flex="20">
          <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Order</label>
          <input  ng-model="Order_id" type="text" >
        </md-input-container>
<!--           <md-icon md-svg-icon="scripts/svgImgs/search.svg" style="margin:auto 0px;"></md-icon>
            <input placeholder="Search Order" class="NormalInput" type="text"> -->
<!--         <h1 style=""></h1> -->


        <md-input-container style="margin: 10px 10px 0px;" flex="15">
          <label>Contractor</label>
          <md-select ng-model="search.contractorName">
            <md-option ng-value=""><em>None</em></md-option>
            <md-option ng-repeat="contractor in contractors" ng-value="contractor.ctrName" style="width:150px;">
              {{contractor.ctrName}}
            </md-option>
          </md-select>
        </md-input-container>



<!--              <md-select ng-model="search.contractorName" aria-label="contId" style="width:auto;" >
             <md-option ng-value="">Contractor</md-option>
              <md-option ng-repeat="contractor in contractors" ng-value="contractor.ctrName" >{{contractor.ctrName}}
              </md-option>
            </md-select>
          &nbsp;&nbsp; -->
        <md-input-container style="margin: 10px 10px 0px;" flex="15">
          <label>Region</label>
          <md-select ng-model="search.warehouseName" >
            <md-option ng-value=""><em>None</em></md-option>
            <md-option ng-repeat="region in regions" ng-value="region" style="width:150px;">
              {{region}}
            </md-option>
          </md-select>
        </md-input-container>
        
<!--         
             <md-select ng-model="search.warehouseName" aria-label="wareId" style="width:auto" >
             <md-option ng-value="">Region</md-option>
              <md-option ng-repeat="region in regions" ng-value="region">
              {{region}}
              </md-option>
            </md-select> -->
                 <md-input-container layout="row" style="margin: 0px 10px;" flex="30">
               <md-button type="Submit" class="md-raised" style="color: gray;max-height: 10px;background-color: lightgray;" 
               ng-click="generateO($event)">Order</md-button>
               <md-button type="Submit" class="md-raised" style="color: gray;max-height: 10px;background-color: lightgray;"
                ng-click="showData()" ng-hide="selectionItem.length==0">Invoice </md-button>
                </md-input-container>
</div>

<!-- 			    <md-button type="Submit" class="md-raised" style="color: gray" ng-click="sample()">Invoice </md-button>  -->              

<div style="margin: 10px 10px;width">
    <table class="table" style="width: 100%;" ng-cloak="" >
      <thead>
      <tr class="tr">
      <th style="width:50px;" ></th>
      <th style="width: 80px;">Order Id</th>
      <th style="text-align: left;">Warehouse</th>
      <th style="text-align: left;">Contractor</th>
      <th style="text-align: left;">Order Date</th>
      <th ></th>
  	  </tr>
      </thead>
      <tbody>
	  <tr ng-repeat="order in orders | orderBy:'-orderId' | filter:search.contractorName | 
	  filter:search.warehouseName |limitTo: 5" class="tr"> 
			<td style="width: 50px;"><input type="checkbox" ng-checked="orders.indexOf(order.orderId) > -1" 
			style="display:inline-block;"
			 ng-click="addToInvoice(order.orderId)" >
          </td>
    <td style="text-align: right;width:80px;">{{order.orderId | number}}</td>
    <td style="text-align: left;">{{order.warehouse.warehouseName}}</td>
    <td style="text-align: left;">{{order.contractor.ctrName}}</td>
    <td style="text-align: left;">{{order.orderDate | date:'dd/MM/yyyy'}}</td>
    <td style="text-align: right;">
    <md-icon md-svg-icon="scripts/svgImgs/details.svg" ng-click="viewO($event,order.orderId)"
      flex></md-icon>
 <!--    <md-button type="Submit" class="md-raised" style="color: gray" 
    ng-cloak="" >View Details</md-button> --></td>
  	 </tr>
    </tbody>
  </table>
<!-- <label ng-click="pagination()">></label> -->
</div>
</md-content>
</div>
