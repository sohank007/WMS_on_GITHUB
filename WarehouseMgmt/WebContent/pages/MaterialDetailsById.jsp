<md-dialog layout="column"  style="width:500px;min-width: 500px;">



   <md-card md-theme="{{ showDarkTheme ? 'dark-purple' : 'default' }}" md-theme-watch>
        <md-card-title>
          <md-card-title-text>
            <span class="md-headline">Material: {{material.materialName}}</span>
            <span class="md-subhead">Category: {{material.category.categoryName}}</span>
            <span class="md-subhead">Material Code: {{material.materialCode}}</span>
            <span class="md-subhead">Description: {{material.materialDesc}}</span>
            <span class="md-subhead">Unit Of Measure: {{material.unitOfMeasure}}</span>
             <span class="md-subhead">Unit Price: {{material.unitPrice |number:2}}</span>
          </md-card-title-text>
          <md-card-title-media>
            <div class="md-media-sm card-media" style="height: 200px;width: 200px;">
            <img ng-src="{{'http://192.168.100.6:1339/'+material.materialImage}}" 
            class="md-card-image" alt="{{material.materialImage}}"  
            style="height: 200px;width: 200px;margin: 0px;">
            </div>
          </md-card-title-media>
        </md-card-title>
      </md-card>

























<!-- <div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Material Details</h2></div>

<div style="margin: 10px 10px 0px;height:70px;" layout="row">
                 <md-input-container style="margin:10px 10px;" flex> 
          <label >Category</label>
           <input ng-model="material.category.categoryName" disabled>
          </md-input-container>
       </div>
      
 <div style="margin: 0px 10px 0px;" layout="row">
                <md-input-container style="margin:10px 10px;" flex="50"> 
            <label style="font-weight: 100;">Material Name</label>
            <input ng-model="material.materialName" disabled>
          </md-input-container>
          
                 <md-input-container style="margin:10px 10px;" flex="50"> 
            <label style="font-weight: 100;">Material Code</label>
            <input    ng-model="material.materialCode" disabled>
          </md-input-container>
          </div>
  <div layout="row" style="margin: 0px 10px 0px;">        
          <md-input-container style="margin:10px 10px;" flex>
            <label style="font-weight: 100;">Material Description</label>
            <input ng-model="material.materialDesc" disabled>
          </md-input-container>
          </div>

<div layout="row" style="margin: 0px 10px 0px;">
          <md-input-container style="margin:10px 10px;" flex="50"> 
          <label >Unit Of Measurement</label>
          <input ng-model="material.unitOfMeasure" disabled>
          </md-input-container>

         <md-input-container style="margin:10px 10px;" flex="50">
            <label style="font-weight: 100;">Unit Price</label>
            <input ng-model="material.unitPrice"  >
          </md-input-container>

</div>
<div layout="row" style="margin: 0px 10px 0px;">
  <img ng-src="{{'http://192.168.100.6:1339/'+material.materialImage}}"  alt="image caption"  style="height: 90px;width: 150px;margin: 10px;" >
</div> -->

</md-dialog>