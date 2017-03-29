<div layout="column" >
  <div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
   <h2 style="padding: 10px;">Contractor Details</h2>
   </div>
   
         <div  layout="row" style="margin: 0px 10px;height:50px;" flex>
   				<md-input-container style="margin: 10px 10px 0px;" flex="20">
   					<label><md-icon md-svg-icon="scripts/svgImgs/search.svg"></md-icon>Search Contractor</label>
   					<input type="text" ng-model="Contractorid">
      			</md-input-container>
   				
   				<md-input-container layout="row" style="margin: 0px 10px;" flex>     
               <md-button type="Submit" class="md-raised" 
				
				 ng-click="addCon($event)">
               Add Contractor</md-button>
           		</md-input-container>   

<!-- 				<md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex="20">
                 <dir-pagination-controls  
			       max-size="2"
			       direction-links="true"
			       boundary-links="true" >
   				 </dir-pagination-controls>
           </md-input-container> -->
           </div>
   

   
<div style="margin: 10px 10px;">     
    <table class="ctrTable" style="width:100%;">
      <thead>
      <tr class="tr">
		<th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('ctrName')">Name
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='ctrName' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='ctrName' && !reverse"  flex></md-icon>
		</th>
		<th style="text-align: left;outline: 0px;"  ng-click="sortingMaterial('ctrAddress')">Address
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='ctrAddress' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='ctrAddress' && !reverse"  flex></md-icon>
		</th>
		<th style="text-align: left;outline: 0px;width:350px;"  ng-click="sortingMaterial('ctrEmailId')">Email Id
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='ctrEmailId' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='ctrEmailId' && !reverse"  flex></md-icon>
		</th>
		<th style="text-align: right;outline: 0px;padding-right: 40px;"  ng-click="sortingMaterial('ctrNum')">Contact Number
		<md-icon md-svg-icon="scripts/svgImgs/uparrow.svg" ng-if="variable=='ctrNum' && reverse" flex></md-icon>
    <md-icon md-svg-icon="scripts/svgImgs/downarrow.svg" ng-if="variable=='ctrNum' && !reverse"  flex></md-icon>
		</th>
<!-- 		<th>Organization Id</th> -->
		<th style="text-align: right;width:100px;"></th>
		</tr>
      </thead>
      <tbody>
<tr dir-paginate="contractor in contractor_details | orderBy:variable:reverse | filter:Contractorid | itemsPerPage:10" class="tr">        
    <td style="text-align: left;">{{contractor.ctrName}}</td>
    <td style="text-align: left;">{{contractor.ctrAddress}}</td>
    <td style="text-align: left;width:350px;">{{contractor.ctrEmailId}}</td>
    <td style="text-align: right;">{{contractor.ctrNum}}</td>
<!--     <td style="text-align: right;">{{contractor.organisationId}}</td> -->

    <td style="text-align: right;width:100px;">
               <md-icon md-svg-icon="scripts/svgImgs/edit.svg" ng-click="editCon($event,contractor)"></md-icon>
               <md-icon md-svg-icon="scripts/svgImgs/delete.svg" ng-click="deleteCon(contractor)" ></md-icon> 
    </td>
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
    
</div>
