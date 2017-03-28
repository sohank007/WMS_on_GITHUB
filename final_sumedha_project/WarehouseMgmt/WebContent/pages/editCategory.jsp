<md-dialog layout="column" id="addmaterial">

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Edit Category</h2></div>

      <form name="AddMaterialForm" novalidate>
 <div style="margin: 30px 10px 0px;" layout="column">
                <md-input-container style="margin:10px 10px;"> 
            <label>Category Name</label>
            <input required  md-maxlength="50" name="categoryName" ng-model="category.categoryName" >
                      <div ng-messages="AddMaterialForm.materialName.$error">
            <div ng-message="required">Category Name is required.</div>
            <div ng-message="md-maxlength">The category name must be less than 50 characters long.</div>
          </div>
          </md-input-container>
          
          <md-input-container style="margin:10px 10px;">
            <label>Category Description</label>
            <input ng-model="category.categoryDesc" name="categoryDesc" required  md-maxlength="100">
             <div ng-messages="AddMaterialForm.materialDesc.$error">
            <div ng-message="required">Category Description is required.</div>
            <div ng-message="md-maxlength">The category description must be less than 100 characters long.</div>
          </div>
          </md-input-container>
</div>
<div layout="row" style="margin: 0px 10px 0px;">
         

         <md-input-container style="margin:10px 10px;" flex="50">
            <label>Category Status</label>
            <input ng-model="category.categoryStatus" name="categoryStatus" required type="text">
            <div ng-messages="AddMaterialForm.unitPrice.$error">
            <div ng-message="required">Category Status is required.</div>
          </div>
          </md-input-container>

</div>
	  <div   layout="row" layout-align="center center"> 
  <md-input-container style="margin:0px 10px;">
            <md-button type="Submit" class="md-raised"  ng-click="submit()"
            ng-disabled="AddMaterialForm.materialName.$invalid || AddMaterialForm.materialDesc.$invalid
            || AddMaterialForm.unitOfMeasure.$invalid || AddMaterialForm.unitPrice.$invalid">Submit</md-button>
            <md-button type="Submit" class="md-raised"  ng-click="cancel()">Cancel</md-button>
</md-input-container>
</div>
      </form>

</md-dialog>