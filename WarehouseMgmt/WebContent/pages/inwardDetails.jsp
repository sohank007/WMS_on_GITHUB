<md-dialog layout="column" style="width:700px;min-width: 700px;">

<div style="background-color: lightgray; margin:10px 10px;border-radius:4px;"><h2>Inward Details</h2></div>
<div style=" margin:10px 10px;">
<table class="table" style="width:100%;" >
<thead>
  <tr class="tr">
<!-- 	<th>Inward Id</th> -->
<!-- 	<th style="text-align: left;">Inward Date</th> -->
	<th style="text-align: left;">Material </th>
	<th style="text-align: left;">Material Description</th>
	<th style="text-align: left;">Unit of Measure</th>
	<th style="text-align: right;">Unit Price</th>
	<th style="text-align: right;">Inward Quantity</th>
  </tr>
  </thead>
  <tbody>
  <tr ng-repeat=" inwardDet in inwardDetail" class="tr">
 <!--    <td>{{inwardDet.inward.inId}}</td> -->
<!--     <td style="text-align: left;">{{inwardDet.inward.inDate | date : 'dd/MM/yyyy'}}</td> -->
    <td style="text-align: left;">{{inwardDet.material.materialName}}</td>
    <td style="text-align: left;">{{inwardDet.material.materialDesc}}</td>
    <td style="text-align: left;">{{inwardDet.material.unitOfMeasure}}</td>
    <td style="text-align: right;">{{inwardDet.material.unitPrice | number:2}}</td>
    <td style="text-align: right;">{{inwardDet.inQty | number:2}}</td>
  </tr>
  </tbody>
</table>
</div>

</md-dialog>