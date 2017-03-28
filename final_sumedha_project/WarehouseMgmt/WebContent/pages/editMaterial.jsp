<md-dialog layout="column" id="addmaterial">

<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Edit Material</h2></div>

      <form name="AddMaterialForm" novalidate>
     <!--   <div style="margin: 10px 10px 0px;height:70px;" layout="row">
                 <md-input-container style="margin:10px 10px;" flex> 
          <label >Category</label>
             <md-select ng-model="cat" name="category" required 
              aria-label="unitOfMeasure" >
              <md-option ng-repeat="category in categories" ng-value="category.categoryName" >{{category.categoryName}}
              </md-option>
            </md-select>
            <div ng-messages="AddMaterialForm.category.$error">
            <div ng-message="required">Category is required.</div>
          </div>
          </md-input-container>
       </div>
       -->
        <div style="margin: 10px 10px 0px;height:70px;" layout="row">
          <md-autocomplete required md-input-name="category" class="addmaterialClass"
          md-search-text="categoryName" md-select-on-match md-match-case-insensitive
            md-selected-item="cat"
          md-items="category in categories| filter:categoryName"
          md-item-text="category.categoryName"
          md-min-length="0"
           md-floating-label="Category" flex="50">
        <md-item-template>
          <span md-highlight-text="category.categoryName" md-highlight-flags="^i">{{category.categoryName}}</span>
        </md-item-template>
      </md-autocomplete>
      
       <md-input-container style="margin:10px 10px 10px 0px;"  flex="50"> 
            <label style="font-weight: 100;">Material Name</label>
            <input required  md-maxlength="50" name="materialName" ng-model="material.materialName" >
                      <div ng-messages="AddMaterialForm.materialName.$error">
            <div ng-message="required">Material Name is required.</div>
            <div ng-message="md-maxlength">The material name must be less than 50 characters long.</div>
          </div>
          </md-input-container>
      
      
      </div>
 <div style="margin: 0px 10px 0px;" layout="row">
           
          
         <md-input-container style="margin:10px 0px 10px 10px;" flex="50"> 
            <label style="font-weight: 100;">Material Code</label>
            <input required md-maxlength="50"  name="materialCode" ng-model="material.materialCode" disabled >
          </md-input-container>
      
          
           <md-input-container style="margin:10px 10px 10px 0px;" flex="50">
            <label style="font-weight: 100;">Material Description</label>
            <input ng-model="material.materialDesc" name="materialDesc" required  md-maxlength="100">
             <div ng-messages="AddMaterialForm.materialDesc.$error">
            <div ng-message="required">Material Description is required.</div>
            <div ng-message="md-maxlength">The material description must be less than 100 characters long.</div>
          </div>
          </md-input-container>
    </div>

<div layout="row" style="margin: 0px 10px 0px;">
           <md-autocomplete required class="addmaterialClass"
          md-search-text="unitOfM" md-select-on-match md-match-case-insensitive
            md-selected-item="material.unitOfMeasure"
          md-items="unit in units | filter:unitOfM"
          md-item-text="unit"
          md-min-length="0"
           md-floating-label="Unit of Measure" flex="50">
        <md-item-template>
          <span md-highlight-text="unit" md-highlight-flags="^i">{{unit}}</span>
        </md-item-template>
      </md-autocomplete>

         <md-input-container style="margin:10px 10px 10px 0px;" flex="50">
            <label style="font-weight: 100;">Unit Price</label>
            <input ng-model="material.unitPrice" name="unitPrice" required type="number">
            <div ng-messages="AddMaterialForm.unitPrice.$error">
            <div ng-message="required">Unit price is required.</div>
          </div>
          </md-input-container>

</div>
<div layout="row">
<div   id="upload"  ng-model="imagePath"  ngf-drag-over-class="'dragover'"
 accept="image/*" ngf-select="uploadFiles($file)" ngf-multiple="false" ngf-drop="uploadFiles($file)">
	&nbsp;&nbsp;Drop image or click to upload image	
</div>
  <img ng-src="{{image}}"  alt="image caption"  style="height: 150px;width: 150px;margin: 10px;">
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