<md-dialog  layout="column"  class="AddContractorClass" id="addContractor">
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
      <input ng-model="contractor.ctrNum" name="ctrNum" required max="9999999999">
      <div ng-messages="AddContractorForm.ctrNum.$error">
            <div ng-message="required">Contractor number is required.</div>
            <div ng-message="max">Contractor number must be less than 10 digit.</div>
          </div>
      </md-input-container>      
      
          <md-input-container  style="margin:0px 10px;" flex="50">
            <label>Contractor Email Id</label>
            <input ng-model="contractor.ctrEmailId" name="ctrEmail"  md-maxlength="100" required type="email" ng-pattern="/^.+@.+\..+$/" >
                  <div ng-messages="AddContractorForm.ctrEmail.$error">
            <div ng-message="required">Contractor email id is required.</div>
            <div ng-message="pattern">Enter valid email id</div>
             <div ng-message="md-maxlength">Contractor email must be less than 100 characters long.</div>
          </div>
          </md-input-container>
      
      </div>
      
   
      </div>
      	  <div   layout="row" layout-align="center center"> 
<md-input-container style="margin:0px 10px;" >
            <md-button type="Submit" class="md-raised"  ng-click="edit_contr()"
            ng-disabled="AddContractorForm.$invalid">Submit</md-button>
			<md-button type="Submit" class="md-raised"  ng-click="cancel()">Cancel</md-button>
	</md-input-container>	
</div>
     </form>  


</md-dialog>
