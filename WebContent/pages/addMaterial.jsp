<md-dialog ng-app="WMS"  layout="column" ng-cloak="" class="AddMaterialClass" id="addmaterial">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Material</h2></div>
 
      <form name="AddMaterialForm" novalidate>
 <div style="margin: 30px 10px 0px;" layout="column">
                <md-input-container style="margin:10px 10px;"> 
            <label>Material Name</label>
            <input required  md-maxlength="50" name="materialName" ng-model="material.materialName" >
                      <div ng-messages="AddMaterialForm.materialName.$error">
            <div ng-message="required">Material Name is required.</div>
            <div ng-message="md-maxlength">The material name must be less than 50 characters long.</div>
          </div>
          </md-input-container>
          
          <md-input-container style="margin:10px 10px;">
            <label>Material Description</label>
            <input ng-model="material.materialDesc" name="materialDesc" required  md-maxlength="100">
             <div ng-messages="AddMaterialForm.materialDesc.$error">
            <div ng-message="required">Material Description is required.</div>
            <div ng-message="md-maxlength">The material description must be less than 100 characters long.</div>
          </div>
          </md-input-container>
</div>
<div layout="row" style="margin: 0px 10px 0px;">
          <md-input-container style="margin:10px 10px;" flex="50"> 
          <label>Unit Of Measurement</label>
             <md-select ng-model="material.unitOfMeasure" name="unitOfMeasure" required 
              aria-label="unitOfMeasure" >
              <md-option ng-repeat="unit in units" value="{{unit.unitOfMsmt}}" >{{unit.unitOfMsmt}}
              </md-option>
            </md-select>
            <div ng-messages="AddMaterialForm.unitOfMeasure.$error">
            <div ng-message="required">Unit of measure is required.</div>
          </div>
          </md-input-container>

         <md-input-container style="margin:10px 10px;" flex="50">
            <label>Unit Price</label>
            <input ng-model="material.unitPrice" name="unitPrice" required type="number">
            <div ng-messages="AddMaterialForm.unitPrice.$error">
            <div ng-message="required">Unit price is required.</div>
          </div>
          </md-input-container>

</div>
  <md-input-container style="margin:0px 10px;" layout="row">
            <md-button type="Submit" class="md-raised" style="color: gray" ng-click="submit()" 
            ng-disabled="AddMaterialForm.$invalid">Submit</md-button>
            <md-button type="Submit" class="md-raised" style="color: gray" ng-click="cancel()">Cancel</md-button>
</md-input-container>

      </form>


</md-dialog>
