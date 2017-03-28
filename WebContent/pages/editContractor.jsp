<md-dialog ng-app="WMS" layout="column" ng-cloak="" class="AddContractorClass" id="addContractor">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Edit Contractor</h2></div>

      <form name="AddContractorForm" novalidate>
    <div style="margin: 30px 10px 0px;" layout="column">
      <md-input-container style="margin:0px 10px;"> 
      <label>Contractor Name</label>
      <input ng-model="contractor.ctrName" name="ctrName" required  type="text" md-maxlength="50">
      <div ng-messages="AddContractorForm.ctrName.$error">
            <div ng-message="required">Contractor Name is required.</div>
            <div ng-message="md-maxlength">Contractor name must be less than 50 characters long.</div>
          </div>
      </md-input-container>
            <md-input-container style="margin:0px 10px;"> 
      <label>Contractor Address</label>
      <input ng-model="contractor.ctrAddress" name="ctrAddress" required  type="text" md-maxlength="100">
      <div ng-messages="AddContractorForm.ctrAddress.$error">
            <div ng-message="required">Contractor address is required.</div>
            <div ng-message="md-maxlength">Contractor address must be less than 100 characters long.</div>
          </div>
      </md-input-container>
      <div layout="row">
               <md-input-container style="margin:0px 10px;" flex="50"> 
      <label>Contractor Number</label>
      <input ng-model="contractor.ctrNum" name="ctrNum" required  type="text">
      <div ng-messages="AddContractorForm.ctrNum.$error">
            <div ng-message="required">Contractor number is required.</div>
          </div>
      </md-input-container>      
      
          <md-input-container  style="margin:0px 10px;" flex="50">
            <label>Contractor Email Id</label>
            <input ng-model="contractor.ctrEmailId" name="ctrEmail" required type="email" ng-pattern="/^.+@.+\..+$/" >
                  <div ng-messages="AddContractorForm.ctrEmail.$error">
            <div ng-message="required">Contractor email id is required.</div>
            <div ng-message="pattern">Enter valid email id</div>
          </div>
          </md-input-container>
      
      </div>
      
   
      </div>
<md-input-container style="margin:0px 10px;" layout="row">
            <md-button type="Submit" class="md-raised" style="color: gray" ng-click="edit_contr()"
            ng-disabled="AddContractorForm.$invalid">Submit</md-button>
			<md-button type="Submit" class="md-raised" style="color: gray" ng-click="cancel()">Cancel</md-button>
	</md-input-container>	

     </form>  


</md-dialog>
