<md-dialog layout="column"  class="AddMaterialClass" id="addmaterial">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Category</h2></div>
 
      <form name="AddMaterialForm" novalidate>
 <div style="margin: 30px 10px 0px;" layout="column">
                <md-input-container style="margin:10px 10px;"> 
            <label style="font-weight: 100;">Category Name</label>
            <input required  md-maxlength="50" name="categoryName" ng-model="category.categoryName" >
                     <!-- <div ng-messages="AddMaterialForm.categoryName.$error">
             <div ng-message="required">Category Name is required.</div>
            <div ng-message="md-maxlength">The Category name must be less than 50 characters long.</div>
          </div> -->
          </md-input-container>
          
          <md-input-container style="margin:10px 10px;">
            <label style="font-weight: 100;">Category Description</label>
            <input ng-model="category.categoryDesc" name="categoryDesc" required  md-maxlength="100">
             <!-- <div ng-messages="AddMaterialForm.categoryDesc.$error">
            <div ng-message="required">Category Description is required.</div>
            <div ng-message="md-maxlength">The Category description must be less than 100 characters long.</div>
          </div> -->
          </md-input-container>
</div>
<div layout="row" style="margin: 0px 10px 0px;">
          <md-input-container style="margin:10px 10px;" flex="50"> 
          <label >Status</label>
          <input ng-model="category.categoryStatus" name="categoryStatus" required  md-maxlength="100">
             <!-- <md-select ng-model="category.categoryStatus" name="categoryStatus" required 
              aria-label="categoryStatus" >
              <md-option ng-repeat="unit in units" value="{{unit.unitOfMsmt}}" >{{unit.unitOfMsmt}}
              </md-option>
            </md-select> -->
            <!-- <div ng-message="required">Category Status is required.</div>
            <div ng-message="md-maxlength">The Category Status must be less than 50 characters long.</div>
            <div ng-messages="AddMaterialForm.categoryStatus.$error">
            <div ng-message="required">Category Status is required.</div> 
          </div>-->
          </md-input-container>

</div>
	  <div   layout="row" layout-align="center center"> 
  <md-input-container style="margin:0px 10px;">
            <md-button type="Submit" class="md-raised"  ng-click="submit()" 
            ng-disabled="AddMaterialForm.$invalid">Submit</md-button>
            <md-button type="Submit" class="md-raised"  ng-click="cancel()">Cancel</md-button>
</md-input-container>
</div>
      </form>


</md-dialog>