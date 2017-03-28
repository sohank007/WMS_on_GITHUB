<md-dialog layout="column" style="width:700px;min-width: 700px;">

<div style="background-color: lightgray; margin:10px 10px;border-radius:4px;"><h2>Inward Details</h2></div>

<!-- <div style="margin: 0px 10px;    height: 50px;" layout="row">
       <md-autocomplete 
          md-search-text="categoryName"
          md-selected-item-change=""
          md-items="cat in categories | filter:categoryName"
          md-item-text="cat.categoryName"
          md-min-length="0"
           md-floating-label="Select Category" flex="15">
        <md-item-template>
          <span md-highlight-text="cat.categoryName" md-highlight-flags="^i">{{cat.categoryName}}</span>
        </md-item-template>
      </md-autocomplete>
      
      
<md-autocomplete 
          md-search-text="materialCode"
          md-selected-item-change=""
          md-items="material in material_details | filter:materialCode"
          md-item-text="material.materialCode"
          md-min-length="0"
           md-floating-label="Select Material Code" flex="15">
        <md-item-template>
          <span md-highlight-text="material.materialCode" md-highlight-flags="^i">{{material.materialCode}}</span>
        </md-item-template>
      </md-autocomplete> 
 </div> -->


<div style=" margin:10px 10px;">


<table class="table" style="width:100%;" >
<thead>
  <tr class="tr">
<!-- 	<th>Inward Id</th> -->
<!-- 	<th style="text-align: left;">Inward Date</th> -->
	<th style="text-align: left;">Category </th>
	<th style="text-align: left;">Material </th>
		<th style="text-align: left;">Material Code</th>
	<th style="text-align: left;">Material Description</th>
	<th style="text-align: left;">Unit of Measure</th>
	<th style="text-align: right;">Unit Price</th>
	<th style="text-align: right;">Inward Quantity</th>
  </tr>
  </thead>
  <tbody>
  <tr dir-paginate=" inwardDet in inwardDetail |filter:categoryName|filter:materialCode| itemsPerPage:2" class="tr">
 <!--    <td>{{inwardDet.inward.inId}}</td> -->
<!--     <td style="text-align: left;">{{inwardDet.inward.inDate | date : 'dd/MM/yyyy'}}</td> -->
        <td style="text-align: left;">{{inwardDet.material.category.categoryName}}</td>
        <td style="text-align: left;">{{inwardDet.material.materialName}}</td>
         <td style="text-align: left;">{{inwardDet.material.materialCode}}</td>
    <td style="text-align: left;">{{inwardDet.material.materialDesc}}</td>
    <td style="text-align: left;">{{inwardDet.material.unitOfMeasure}}</td>
    <td style="text-align: right;">{{inwardDet.material.unitPrice | number:2}}</td>
    <td style="text-align: right;">{{inwardDet.inQty | number:2}}</td>
  </tr>
  </tbody>
</table>
</div>
    <div  layout="row" style="margin: 0px 10px;height:50px;" flex>
             <md-input-container style="margin: 0px;padding: 0px;display: inline-block; float: right;
                 text-align: right;" flex>
        <dir-pagination-controls 
       max-size="5"
       direction-links="true"
       boundary-links="true" >
    </dir-pagination-controls>
        </md-input-container>
        </div>

</md-dialog>