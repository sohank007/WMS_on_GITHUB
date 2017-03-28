<md-dialog layout="column"  class="AddContractorClass" id="addContractor">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Contractor</h2></div>
      <form name="AddContractorForm" novalidate>
      
     <div style="margin: 30px 10px 0px;" layout="column">
      <md-input-container style="margin:0px 10px;"> 
      <label style="font-weight: 100;">Role</label>
      <input ng-model="role.role" name="role" required  type="text" md-maxlength="50">
      <div ng-messages="AddContractorForm.ctrName.$error">
            <div ng-message="required">Role is required.</div>
            <div ng-message="md-maxlength">Role must be less than 50 characters long.</div>
          </div>
      </md-input-container>
      </div>
      
      <div style="margin: 30px 10px 0px;" layout="column">
	  <md-input-container style="margin:0px 10px;"> 
      <label style="font-weight: 100;">Role Code</label>
      <input ng-model="role.roleCode" name="roleCode" required  type="text" md-maxlength="100">
      <div ng-messages="AddContractorForm.ctrAddress.$error">
            <div ng-message="required">Role Code is required.</div>
            <div ng-message="md-maxlength">Role Code must be less than 50 characters long.</div>
          </div>
	  </md-input-container>
      </div>
      
      	  <div   layout="row" layout-align="center center"> 
<md-input-container style="margin:0px 10px;" >
            <md-button type="Submit" class="md-raised"  ng-click="addRole()" 
            ng-disabled="AddContractorForm.$invalid" >Submit</md-button>
			<md-button type="Submit" class="md-raised" ng-click="cancel()">Cancel</md-button>
</md-input-container></div>
     </form>  



</md-dialog>
