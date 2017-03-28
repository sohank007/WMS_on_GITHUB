
  <div ng-app="WMS" layout="column" ng-cloak="">
  <div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Contractor Details</h2>
   </div>
   
         <div layout="row" style="height: 60px;margin-top: 20px;">
   <md-input-container style="margin: 10px 10px 0px;" flex="30">
   <label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Contractor</label>
   <input type="text" ng-model="Contractorid">
      </md-input-container>
   <md-input-container layout="row" style="margin: 0px 10px;" flex="30">     
               <md-button type="Submit" class="md-raised" style="color: gray" ng-click="addCon($event)">
               Add Contractor</md-button>
           </md-input-container>   

           </div>
   

   
<div style="margin: 10px 10px;">     
    <table class="table" style="width:100%">
      <thead>
      <tr class="tr">
		<th style="text-align: left;">Name</th>
		<th style="text-align: left;">Address</th>
		<th style="text-align: left;">Email Id</th>
		<th style="text-align: right;width:200px;">Contact Number</th>
		<th style="text-align: right;"></th>
		</tr>
      </thead>
      <tbody>
<tr ng-repeat="contractor in contractor_details | orderBy:'-ctrId' | filter:Contractorid" class="tr">        
    <td style="text-align: left;">{{contractor.ctrName}}</td>
    <td style="text-align: left;">{{contractor.ctrAddress}}</td>
    <td style="text-align: left;">{{contractor.ctrEmailId}}</td>
    <td style="text-align: right;width:200px;">{{contractor.ctrNum}}</td>

    <td style="text-align: right;">
               <md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="editCon($event,contractor)"></md-icon>
               <md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="deleteCon(contractor)" ></md-icon> 
    </td>
  </tr>
    </tbody>
  </table>
    </div>
</div>
