<div ng-app="WMS"  ng-controller="dashboardCtrl" layout="column"  flex>
<span layout="row">
<span style="color: blue;text-decoration: none;">click here</span>
<span class="blink" style="color: red;padding: 0px;margin: 5px;"
 ng-click="showMaterialShortageDiv=!showMaterialShortageDiv" ng-hide="hiddenObj">Material Shortage</span></span>
<!-- <h4 class="blink" style="color: red;" ng-click="getGivenDateDetails=!getGivenDateDetails">Get inward and outward details</h4> -->
<div ng-show="showMaterialShortageDiv"  class="well" flex>
		<md-content>
		<md-input-container style="padding:5px;width:30%;">
        		<md-select ng-model="warehouseN" placeholder="Warehouse" style="color:lightgray;" >
              <md-option ng-repeat="warehouse in wNames" ng-value="warehouse">{{warehouse}}
              </md-option>
            	</md-select>
		</md-input-container>
		
		</md-content> 
<table style="width:100%">
		<thead>
			  	<tr>
<!-- 			  		<th>Warehouse</th> -->
				    <th>Material</th>
				    <th>Quantity</th>
			 	</tr>
	   </thead>
	   <tbody>
			   <tr ng-repeat="material in ShortageMaterials |filter:warehouseN">
<!-- 			   		<td>{{ material[0] }}</td> -->
			   		<td>{{ material[1] }}</td>
			   		<td>{{ material[2] }}</td>
			   </tr>
	   </tbody>
			 </table>
</div>


<div layout="row" layout-sm="column"  style="max-width: 100%">

	<div class="well" style="max-width: 100%" flex>

   
											<fusioncharts
											height=150px width=100%
										       type="column2d"
										       dataFormat="json"  
										       dataSource="{{dataSource1}}" >
										   </fusioncharts>
	</div>

	<div class="well" style="max-width: 100%" flex>
   
											<fusioncharts
											  height=150px width=100%
										       type="column2d"
										       dataFormat="json"
										       dataSource="{{dataSource2}}" >
										   </fusioncharts>
	</div>
	
	
	<div  class="well" style="max-width: 100%" flex>
   
											<fusioncharts
										height=150px  width=100%
										       type="column2d"
										       dataFormat="json"
										       dataSource="{{dataSource3}}" >
										   </fusioncharts>
	</div>
	


</div>
<div layout="row" layout-sm="column" style="max-width: 100%;">
<div class="well" style="width:auto;max-width: 100%;" flex>
		<md-content>
		<md-input-container style="padding:5px; ">
        		<md-select ng-model="warehouseName" placeholder="Warehouse" style="color:lightgray" ng-change="passW(warehouseName)">
              <md-option ng-repeat="warehouse in warehouses" ng-value="warehouse.warehouseName">{{warehouse.warehouseName}}
              </md-option>
            	</md-select>
		</md-input-container>
		
		</md-content>     
											<fusioncharts
											  width=100% height=250
										       type="pie2d"
										       dataFormat="json" id="hi"
										       dataSource="{{dataSource}}">
										   </fusioncharts>
</div>
<div  layout="column" style="width:auto;max-width: 100%;" flex>
<div class="show">
		<md-content  layout="row">
		  <md-input-container> 
		  <label>Date</label>
            <input ng-model="myDate" type="date" ng-change="sendDate(myDate)" style="color: gray;" aria-label="mydate">
          </md-input-container>
		<md-input-container>
        		<md-select ng-model="warehouseName" placeholder="Warehouse" style="color:lightgray" ng-change="TWInward(warehouseName)">
              <md-option ng-repeat="warehouse in warehouses" ng-value="warehouse.warehouseName">{{warehouse.warehouseName}}
              </md-option>
            	</md-select>
            </md-input-container>	
		</md-content>
		</div> 

<div class="well" style="width:100%;max-width: 100%;" flex >

		
											<fusioncharts
											height=120px  width=100%
										       type="column2d"
										       dataFormat="json"
										       dataSource="{{dataSource5}}">
										   </fusioncharts>

</div>
<div class="well" style="width:100%;max-width: 100%;" flex>
											<fusioncharts
											height=120px  width=100%
										       type="column2d"
										       dataFormat="json"
										       dataSource="{{dataSource6}}" >
										   </fusioncharts>


</div>

</div>
</div>
</div>
