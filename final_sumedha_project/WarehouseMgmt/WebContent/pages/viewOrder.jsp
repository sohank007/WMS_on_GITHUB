<div>

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Orders</h2>
   </div>
<div layout="row" style="margin: 20px 10px 0px;height:50px;" flex>

      <!--   <md-input-container style="margin: 10px 10px 0px 5px;" flex="15"> -->
      <!--     <label>Region</label> -->
            <md-autocomplete
          md-selected-item=""
          md-search-text="warehouseRegion"
          md-selected-item-change=""
          md-items="region in regions | filter:warehouseRegion"
          md-item-text="region"
          md-min-length="0"
           md-floating-label="Select Region" flex="15">
        <md-item-template>
          <span md-highlight-text="region" md-highlight-flags="^i">{{region}}</span>
        </md-item-template>
      </md-autocomplete>
      
           <md-autocomplete flex="15"
          md-selected-item=""
          md-search-text="warehouseName"
          md-selected-item-change=""
          md-items="warehouse in warehouses | filter:warehouseName"
          md-item-text="warehouse.warehouseName"
          md-min-length="0"
           md-floating-label="Select Warehouse">
        <md-item-template>
          <span md-highlight-text="warehouse.warehouseName" md-highlight-flags="^i">{{warehouse.warehouseName}}</span>
        </md-item-template>
      </md-autocomplete>
      
                 <md-autocomplete flex="15"
          md-selected-item=""
          md-search-text="contractorName"
          md-selected-item-change=""
          md-items="contractor in contractors | filter:contractorName"
          md-item-text="contractor.ctrName"
          md-min-length="0"
           md-floating-label="Select Contractor">
        <md-item-template>
          <span md-highlight-text="contractor.ctrName" md-highlight-flags="^i">{{contractor.ctrName}}</span>
        </md-item-template>
      </md-autocomplete> 
      
<!--       
          <md-select ng-model="warehouseRegion" >
            <md-option ng-value=""><em>None</em></md-option>
            <md-option ng-repeat="region in regions" ng-value="region" >
              {{region}}
            </md-option>
          </md-select>
        </md-input-container>
        
               <md-input-container style="margin: 10px 10px 0px 5px;" flex="15">
          <label>Warehouse</label>
          <md-select ng-model="warehouseName" >
            <md-option ng-value=""><em>None</em></md-option>
            <md-option ng-repeat="warehouse in warehouses" ng-value="warehouse.warehouseName" >
              {{warehouse.warehouseName}}
            </md-option>
          </md-select>
        </md-input-container>
        
        <md-input-container style="margin: 10px 10px 0px 5px;" flex="15">
          <label>Contractor</label>
          <md-select ng-model="contractorName">
            <md-option ng-value=""><em>None</em></md-option>
            <md-option ng-repeat="contractor in contractors" ng-value="contractor.ctrName">
              {{contractor.ctrName}}
            </md-option>
          </md-select>
        </md-input-container>
         -->
        
             <!--    <md-input-container  flex="15" style="margin:10px;">
          <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Order</label>
          <input  ng-model="Order_id" type="text" >
        </md-input-container> -->
        
                         <md-input-container layout="row"  flex="40" style="margin: -10px 10px;">
               <md-button type="Submit" class="md-raised" style="margin:0px 10px;" 
               ng-click="generateO($event)">Create Order</md-button>
               <md-button type="Submit" class="md-raised" style="margin:0px 10px;" 
                ng-click="showData()" ng-hide="selectionItem.length==0 || role=='ADM'">Generate Invoice </md-button>
                </md-input-container>
        
        </div>
<!--  <div layout="row" style="margin: 0px 10px;height:50px;" flex>   -->     

<!--           <md-icon md-svg-icon="scripts/svgImgs/search.svg" style="margin:auto 0px;"></md-icon>
            <input placeholder="Search Order" class="NormalInput" type="text"> -->
<!--         <h1 style=""></h1> -->






<!--              <md-select ng-model="search.contractorName" aria-label="contId" style="width:auto;" >
             <md-option ng-value="">Contractor</md-option>
              <md-option ng-repeat="contractor in contractors" ng-value="contractor.ctrName" >{{contractor.ctrName}}
              </md-option>
            </md-select>
          &nbsp;&nbsp; -->

        
<!--         
             <md-select ng-model="search.warehouseName" aria-label="wareId" style="width:auto" >
             <md-option ng-value="">Region</md-option>
              <md-option ng-repeat="region in regions" ng-value="region">
              {{region}}
              </md-option>
            </md-select> -->

<!--                 
                 <md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex>
        <dir-pagination-controls  
       max-size="2"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls>
        </md-input-container> 
</div>-->

<!-- 			    <md-button type="Submit" class="md-raised" style="color: gray" ng-click="sample()">Invoice </md-button>  -->              

<div style="margin: 10px 10px;width">
    <table class="odrTable" style="width: 100%;" ng-cloak="" >
      <thead>
      <tr class="tr">
      <th style="width:70px;text-align: center;" ></th>
      <th style="width: 120px;outline: 0px;padding-right: 40px;text-align: right;"  ng-click="sortingMaterial('orderId')">Order Id
      <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='orderId' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='orderId' && !reverse"  flex></md-icon>
      </th>
      <th style="text-align: left;outline: 0px;padding-left: 20px;"  ng-click="sortingMaterial('warehouse.warehouseName')">Warehouse
      <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='warehouse.warehouseName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='warehouse.warehouseName' && !reverse"  flex></md-icon>
      </th>
      <th style="text-align: left;outline: 0px;padding-left: 20px;"  ng-click="sortingMaterial('contractor.ctrName')">Contractor
      <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='contractor.ctrName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='contractor.ctrName' && !reverse"  flex></md-icon>
      </th>
            <th style="text-align: right;outline: 0px;padding-right: 40px;"  ng-click="sortingMaterial('orderDate')">Order Date
      <md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='orderDate' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='orderDate' && !reverse"  flex></md-icon>
    </th>
      <th style="text-align: left;outline: 0px;padding-left: 40px;">Status
      </th>
<!--       <th>Org Id</th> -->


 <!--      <th ></th> -->
  	  </tr>
      </thead>
      <tbody>
	  <tr dir-paginate="order in orders |  orderBy:variable:reverse | filter:warehouseRegion | 
	   filter:warehouseName | filter:contractorName | filter:Order_id   |itemsPerPage: 10" class="tr" 
	  ng-click="viewO($event,order.orderId)"> 
			<td style="width: 50px;text-align: center;padding-right: 8px;" ng-click="$event.stopPropagation()"><input type="checkbox" ng-checked="orders.indexOf(order.orderId) > -1" 
			style="display:inline-block;"
			 ng-click="addToInvoice(order.orderId)" ng-disabled="order.orderMasterInvoiceStatus=='Invoiced'" ng-hide="role=='CTR'">
          </td>
    <td style="text-align: right;width:12px;padding-right: 4s0px;">{{order.sequenceId}}</td>
    <td style="text-align: left;padding-left:20px; width: 300px;">{{order.warehouse.warehouseName}}</td>
    <td style="text-align: left;padding-left:20px; width: 300px;">{{order.contractor.ctrName}}</td>
        <td style="text-align: right;width: 150px;padding-right: 40px;">{{order.orderDate | date:'dd/MM/yyyy'}}</td>
    <td style="text-align: left;padding-right: 20px;padding-left:40px;">{{order.orderMasterInvoiceStatus}}</td>
<!-- 	<td>{{order.organisationId}}</td> -->

 <!--    <td style="text-align: right;">
    <md-icon md-svg-icon="scripts/svgImgs/details.svg" ng-click="viewO($event,order.orderId)"
      flex></md-icon> -->
 <!--    <md-button type="Submit" class="md-raised" style="color: gray" 
    ng-cloak="" >View Details</md-button></td> -->
  	 </tr>
    </tbody>
  </table>
<!-- <label ng-click="pagination()">></label> -->
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
