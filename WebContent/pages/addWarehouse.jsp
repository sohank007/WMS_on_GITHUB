<md-dialog  ng-app="WMS" layout="column" ng-cloak="" class="AddWarehouseClass" id="addwarehouse">
<div style="background-color: lightgray;margin: 10px 10px;border-radius:4px;">
<h2 style="padding: 10px;">Add Warehouse</h2></div>

      <form name="AddWarehouseForm" novalidate>
      
       <div style="margin: 30px 10px 0px;" layout="column">
                <md-input-container style="margin:0px 10px;"> 
            <label>Warehouse Name</label>
            <input ng-model="warehouse.warehouseName" name="warehouseName" required md-maxlength="50">
                      <div ng-messages="AddWarehouseForm.warehouseName.$error">
            <div ng-message="required">Warehouse Name is required.</div>
            <div ng-message="md-maxlength">Warehouse name must be less than 50 characters long.</div>
          </div>
          </md-input-container>
          
          <md-input-container style="margin:0px 10px;">
            <label>Warehouse Location</label>
             <input ng-model="warehouse.warehouseLoc" name="warehouseLoc" required  md-maxlength="100">
             <div ng-messages="AddWarehouseForm.warehouseLoc.$error">
            <div ng-message="required">Warehouse location is required.</div>
            <div ng-message="md-maxlength">Warehouse description must be less than 100 characters long.</div>
          </div>
          </md-input-container>
          
          <md-input-container style="margin:0px 10px;">
           <label>Warehouse Region</label>
           <input ng-model="warehouse.wRegion" name="wRegion" required md-maxlength="50">
             <div ng-messages="AddWarehouseForm.wRegion.$error">
            <div ng-message="required">Warehouse region is required.</div>
            <div ng-message="md-maxlength">Warehouse region must be less than 100 characters long.</div>
          </div>
          </md-input-container>
          </div> 
                    <md-input-container style="margin:0px 10px;" layout="row">
           <md-button class="md-raised" style="color: gray" type="Submit" ng-click="add_wrh()" 
            ng-disabled="AddWarehouseForm.$invalid">Submit</md-button>
			<md-button class="md-raised" style="color: gray" type="Submit" ng-click="cancel()">Cancel</md-button>
                    </md-input-container>

         
           

<!--             <div ng-messages="AddWarehouseForm.warehouseLoc.$error"  ng-show='AddWarehouseForm.warehouseLoc.$dirty || AddWarehouseForm.warehouseLoc.$touched'>
               <div ng-message="required" >Warehouse location is required.</div>
            </div> -->
       

       
            

         <!--    <div ng-messages="AddWarehouseForm.wRegion.$error"  ng-show='AddWarehouseForm.wRegion.$dirty || AddWarehouseForm.wRegion.$touched'>
               <div ng-message="required" >Warehouse region is required.</div>
            </div> -->
   
          

          

</form>

</md-dialog>
