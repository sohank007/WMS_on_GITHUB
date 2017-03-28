
<md-dialog layout="column"  class="AddMenuClass" id="addMenu">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Menu</h2></div>
      <form name="AddMenuForm" novalidate>
      
     <div style="margin: 30px 10px 0px;" layout="column">
      
      <md-input-container style="margin:0px 10px;"> 
      <label>Menu Name</label>
      <input ng-model="menu.menuName" name="menuName" required  type="text" md-maxlength="100">
      <div ng-messages="AddMenuForm.menuName.$error">
            <div ng-message="required">Menu Name is required.</div>
            <div ng-message="md-maxlength">Menu Name must be less than 100 characters long.</div>
          </div>
      </md-input-container>
      
      <div layout="row">
                  <md-input-container style="margin:0px 10px;" flex="50"> 
      <label>Menu Icon</label>
      <input ng-model="menu.menuIcon" name="menuIcon" required  type="text">
      <div ng-messages="AddMenuForm.menuIcon.$error">
            <div ng-message="required">Menu Icon is required.</div>
          </div>
      </md-input-container>      
      
         
      </div>

      
      </div>
<md-input-container style="margin:0px 10px;" layout="row">
            <md-button type="Submit" class="md-raised" style="color: gray" ng-click="add_menu()" 
            ng-disabled="AddMenuForm.$invalid" >Submit</md-button>
			<md-button type="Submit" class="md-raised" style="color: gray" ng-click="cancel()">Cancel</md-button>
</md-input-container>
     </form>  



</md-dialog>







