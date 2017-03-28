<md-dialog layout="column" id="addmaterial">

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Edit Material</h2></div>

      <form name="AddMaterialForm" novalidate>
       <div style="margin: 10px 10px 0px;height:70px;" layout="row">
                 <md-input-container style="margin:10px 10px;" flex> 
          <label >Category</label>
             <md-select ng-model="cat" name="category" required 
              aria-label="unitOfMeasure" >
              <md-option ng-repeat="category in categories" ng-value="category" >{{category.categoryName}}
              </md-option>
            </md-select>
            <div ng-messages="AddMaterialForm.category.$error">
            <div ng-message="required">Category is required.</div>
          </div>
          </md-input-container>
       </div>
      
 <div style="margin: 0px 10px 0px;" layout="row">
                <md-input-container style="margin:10px 10px;" flex="50"> 
            <label style="font-weight: 100;">Material Name</label>
            <input required  md-maxlength="50" name="materialName" ng-model="material.materialName" >
                      <div ng-messages="AddMaterialForm.materialName.$error">
            <div ng-message="required">Material Name is required.</div>
            <div ng-message="md-maxlength">The material name must be less than 50 characters long.</div>
          </div>
          </md-input-container>
          
                 <md-input-container style="margin:10px 10px;" flex="50"> 
            <label style="font-weight: 100;">Material Code</label>
            <input required   name="materialCode" ng-model="material.materialCode" >
                      <div ng-messages="AddMaterialForm.materialCode.$error">
            <div ng-message="required">Material Code is required.</div>

          </div>
          </md-input-container>
          </div>
  <div layout="row" style="margin: 0px 10px 0px;">        
          <md-input-container style="margin:10px 10px;" flex>
            <label style="font-weight: 100;">Material Description</label>
            <input ng-model="material.materialDesc" name="materialDesc" required  md-maxlength="100">
             <div ng-messages="AddMaterialForm.materialDesc.$error">
            <div ng-message="required">Material Description is required.</div>
            <div ng-message="md-maxlength">The material description must be less than 100 characters long.</div>
          </div>
          </md-input-container>
          </div>

<div layout="row" style="margin: 0px 10px 0px;">
          <md-input-container style="margin:10px 10px;" flex="50"> 
          <label >Unit Of Measurement</label>
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
            <label style="font-weight: 100;">Unit Price</label>
            <input ng-model="material.unitPrice" name="unitPrice" required type="number">
            <div ng-messages="AddMaterialForm.unitPrice.$error">
            <div ng-message="required">Unit price is required.</div>
          </div>
          </md-input-container>

</div>
<div layout="row">
<div   id="upload" ngf-drop  ng-model="imagePath"  ngf-drag-over-class="'dragover'"
 accept="image/*" ngf-select="uploadFiles($file)" ngf-multiple="false" ngf-drop="uploadFiles($file)">
	&nbsp;&nbsp;Drop image or click to upload image	
</div>
  <img ng-src="{{image}}"  alt="image caption"  style="height: 90px;width: 150px;margin: 10px;" ng-show="imagevisible">
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