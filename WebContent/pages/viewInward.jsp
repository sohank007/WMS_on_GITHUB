
<body layout="row" ng-cloak="">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Inward</h2>
   </div>
   <div layout="row" style="height: 60px;margin-top: 20px;">
   <md-input-container style="margin: 10px 10px 0px;" flex="30">
   <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Inward</label>
   <input type="text" ng-model="inwardId">
      </md-input-container>
   <md-input-container layout="row" style="margin: 0px 10px;" flex="30">     
           <md-button type="Submit" class="md-raised" style="color: gray" ng-click="genInward($event)">
           Generate Inward</md-button>
           </md-input-container>

            

           </div>

<div style="border-radius:4px;margin: 10px 10px;">
<table  class="table" style="width:100%;" ng-cloak="">
<thead>
  <tr class="tr">
    <th style="text-align: right;width: 100px;">Inward Id</th>
    <th style="text-align: right;width:200px;">Inward Date</th>
    <th style="text-align: left;">Inward Warehouse</th>
    <th style="text-align: right;"></th>
  </tr>
  </thead>
  <tbody>
  <tr ng-repeat="inward in inwards| orderBy:'-inId' | filter:inwardId" class="tr" flex>
    <td style="text-align: right;width:100px;">{{inward.inId}}</td>
    <td style="text-align: right;width:200px;">{{inward.inDate | date :'dd/MM/yyyy'}}</td>
    <td style="text-align: left;">{{inward.warehouse.warehouseName}}</td>
    <td style="text-align: right;"><md-icon md-svg-icon="scripts/svgImgs/details.svg" ng-click="inwardDet($event,inward.inId)"
      flex></md-icon></td>
    </tr>
    
  </tbody>
</table>
</div>

</body>
